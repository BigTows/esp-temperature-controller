package org.bigtows.esptemperaturecontroller.service;

import lombok.AllArgsConstructor;
import org.bigtows.esptemperaturecontroller.repository.DeviceEntityRepository;
import org.bigtows.esptemperaturecontroller.service.dto.Device;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {


    private final DeviceEntityRepository deviceEntityRepository;

    @Override
    public List<Device> getAll() {
        return deviceEntityRepository.findAll().stream().map(deviceEntity ->
                Device.builder()
                        .id(deviceEntity.getId())
                        .name(deviceEntity.getName())
                        .description(deviceEntity.getDescription())
                        .build()
        ).collect(Collectors.toList());
    }


    @Override
    public Device getById(Long id) throws NoSuchElementException {
        var deviceEntity = deviceEntityRepository.findById(id).orElseThrow();


        return Device.builder()
                .id(deviceEntity.getId())
                .name(deviceEntity.getName())
                .description(deviceEntity.getDescription())
                .build();
    }
}
