package me.mahdiyar.digipay.payment.service.mapper;

import me.mahdiyar.digipay.payment.contract.domain.Resource;
import me.mahdiyar.digipay.payment.service.domain.ResourceEntity;

public class ResourceMapper {
    private ResourceMapper() {
    }

    public static Resource map(ResourceEntity resourceEntity) {
        if (resourceEntity == null)
            return null;
        return new Resource()
                .setId(resourceEntity.getId())
                .setResourceId(resourceEntity.resourceId)
                .setUserId(resourceEntity.getUserId())
                .setValue(resourceEntity.getResource())
                .setTitle(resourceEntity.getTitle())
                .setResourceType(resourceEntity.getResourceType())
                .setCreationTime(resourceEntity.getCreationTime());
    }
}
