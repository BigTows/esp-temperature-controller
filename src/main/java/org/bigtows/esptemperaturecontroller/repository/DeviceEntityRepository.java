package org.bigtows.esptemperaturecontroller.repository;

import org.bigtows.esptemperaturecontroller.domain.DeviceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DeviceEntityRepository extends CrudRepository<DeviceEntity, Long> {

    @Override
    Collection<DeviceEntity> findAll();
}
