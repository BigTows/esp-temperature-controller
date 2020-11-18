package org.bigtows.esptemperaturecontroller.repository;

import org.bigtows.esptemperaturecontroller.domain.DeviceEntity;
import org.bigtows.esptemperaturecontroller.domain.TemperatureEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureEntityRepository extends JpaRepository<TemperatureEntity, Long> {


    @Query(value = "SELECT MAX(timestamp_device) FROM temperature_entity where device_id = ?1", nativeQuery = true)
    Long getMaxTimestampByDeviceEntityId(Long id);


    List<TemperatureEntity> findAllByDeviceEntity(DeviceEntity deviceEntity, Pageable pageable);
}
