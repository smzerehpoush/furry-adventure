package me.mahdiyar.digipay.payment.service.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.mahdiyar.digipay.auth.service.base.annotation.WithUser;
import me.mahdiyar.digipay.auth.service.base.domain.BaseUserCredential;
import me.mahdiyar.digipay.base.exceptions.BaseException;
import me.mahdiyar.digipay.payment.contract.domain.Resource;
import me.mahdiyar.digipay.payment.contract.domain.enums.ResourceType;
import me.mahdiyar.digipay.payment.contract.domain.request.CreateResourceRequestDto;
import me.mahdiyar.digipay.payment.contract.domain.request.UpdateResourceRequestDto;
import me.mahdiyar.digipay.payment.service.service.ResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Api
@RestController
@RequestMapping("/api/v1/resources")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @ApiOperation("Get User Resources")
    @GetMapping
    public ResponseEntity<List<Resource>> getUserResources(
            @WithUser BaseUserCredential baseUserCredential,
            @RequestParam(name = "resourceType", required = false) ResourceType resourceType) {
        return ok(resourceService.getUserResources(baseUserCredential, resourceType));
    }

    @ApiOperation("Create User Resources")
    @PostMapping
    public ResponseEntity<Resource> createUserResource(
            @WithUser BaseUserCredential baseUserCredential,
            @RequestBody @Valid CreateResourceRequestDto requestDto) {
        return ok(resourceService.createUserResource(baseUserCredential, requestDto));
    }

    @ApiOperation("Update User Resources")
    @PutMapping("{resourceId}")
    public ResponseEntity<Resource> getUserResources(
            @WithUser BaseUserCredential baseUserCredential,
            @PathVariable("resourceId") String resourceId,
            @RequestBody @Valid UpdateResourceRequestDto requestDto) throws BaseException {
        return ok(resourceService.updateUserResource(baseUserCredential, resourceId, requestDto));
    }

    @ApiOperation("Delete User Resources")
    @DeleteMapping("{resourceId}")
    public ResponseEntity<Void> getUserResources(
            @WithUser BaseUserCredential baseUserCredential,
            @PathVariable("resourceId") String resourceId) throws BaseException {
        return ok(resourceService.deleteUserResource(baseUserCredential, resourceId));
    }
}
