package me.mahdiyar.digipay.payment.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mahdiyar.digipay.auth.service.base.domain.BaseUserCredential;
import me.mahdiyar.digipay.payment.contract.domain.Resource;
import me.mahdiyar.digipay.payment.contract.domain.enums.ResourceType;
import me.mahdiyar.digipay.payment.contract.domain.request.CreateResourceRequestDto;
import me.mahdiyar.digipay.payment.contract.domain.request.UpdateResourceRequestDto;
import me.mahdiyar.digipay.payment.service.domain.ResourceEntity;
import me.mahdiyar.digipay.payment.service.exceptions.ResourceIsNotYoursException;
import me.mahdiyar.digipay.payment.service.exceptions.ResourceNotFoundException;
import me.mahdiyar.digipay.payment.service.mapper.ResourceMapper;
import me.mahdiyar.digipay.payment.service.repository.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public List<Resource> getUserResources(BaseUserCredential baseUserCredential, ResourceType resourceType) {
        List<ResourceEntity> resourceEntities =
                resourceRepository.findAllUserResources(baseUserCredential.getUserId(), resourceType);
        if (CollectionUtils.isEmpty(resourceEntities))
            return Collections.emptyList();
        return resourceEntities.stream().map(ResourceMapper::map).collect(Collectors.toList());
    }

    public Resource createUserResource(BaseUserCredential baseUserCredential, CreateResourceRequestDto requestDto) {
        ResourceEntity resourceEntity = resourceRepository.getExistingResource(baseUserCredential.getUserId(),
                requestDto.getResource(), requestDto.getResourceType());
        if (resourceEntity != null)
            updateResource(resourceEntity, requestDto);
        else
            resourceEntity = createResource(requestDto, baseUserCredential.getUserId());
        resourceEntity = resourceRepository.save(resourceEntity);
        return ResourceMapper.map(resourceEntity);
    }

    private ResourceEntity createResource(CreateResourceRequestDto requestDto, String userId) {
        return new ResourceEntity().setResource(requestDto.getResource())
                .setResourceType(requestDto.getResourceType())
                .setTitle(requestDto.getTitle())
                .setUserId(userId)
                .setDeleted(false);

    }

    private void updateResource(ResourceEntity resourceEntity, CreateResourceRequestDto requestDto) {
        resourceEntity.setDeleted(true);
        resourceEntity.setTitle(requestDto.getTitle());
    }

    private ResourceEntity updateResource(ResourceEntity resourceEntity, UpdateResourceRequestDto requestDto) {
        resourceEntity.setTitle(requestDto.getTitle());
        return resourceEntity;
    }

    public Resource updateUserResource(
            BaseUserCredential baseUserCredential, String resourceId, UpdateResourceRequestDto requestDto)
            throws ResourceNotFoundException, ResourceIsNotYoursException {
        ResourceEntity resourceEntity = findById(resourceId);
        checkResourceOwner(resourceEntity, baseUserCredential);
        resourceEntity = resourceRepository.save(updateResource(resourceEntity, requestDto));
        return ResourceMapper.map(resourceEntity);
    }

    private void checkResourceOwner(ResourceEntity resourceEntity, BaseUserCredential baseUserCredential)
            throws ResourceIsNotYoursException {
        if (!Objects.equals(resourceEntity.getUserId(), baseUserCredential.getUserId()))
            throw new ResourceIsNotYoursException();
    }

    public Void deleteUserResource(BaseUserCredential baseUserCredential, String resourceId)
            throws ResourceNotFoundException, ResourceIsNotYoursException {
        ResourceEntity resourceEntity = findById(resourceId);
        checkResourceOwner(resourceEntity, baseUserCredential);
        resourceEntity.setDeleted(true);
        resourceRepository.save(resourceEntity);
        return null;
    }

    public ResourceEntity findById(String id) throws ResourceNotFoundException {
        return resourceRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }
}
