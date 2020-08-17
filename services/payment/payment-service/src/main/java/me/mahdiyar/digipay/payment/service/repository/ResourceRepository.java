package me.mahdiyar.digipay.payment.service.repository;

import me.mahdiyar.digipay.base.jpa.BaseRepository;
import me.mahdiyar.digipay.payment.contract.domain.enums.ResourceType;
import me.mahdiyar.digipay.payment.service.domain.ResourceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends BaseRepository<ResourceEntity> {
    @Query("SELECT r FROM ResourceEntity r WHERE r.userId = :user_id " +
            "AND (:resource_type IS NULL OR r.resourceType = :resource_type) " +
            "AND (r.deleted IS NULL OR r.deleted = FALSE )" +
            "ORDER BY r.creationTime DESC ")
    List<ResourceEntity> findAllUserResources(
            @Param("user_id") String userId,
            @Param("resource_type") ResourceType resourceType);


    //    todo index based on these values
    @Query("SELECT r FROM ResourceEntity r WHERE " +
            "r.userId= :user_id AND r.resourceType = :resource_type AND r.resource = :resource")
    ResourceEntity getExistingResource(
            @Param("user_id") String userId,
            @Param("resource") String resource,
            @Param("resource_type") ResourceType resourceType);
}
