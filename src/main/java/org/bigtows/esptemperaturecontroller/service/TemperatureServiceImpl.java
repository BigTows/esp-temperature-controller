package org.bigtows.esptemperaturecontroller.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.bigtows.esptemperaturecontroller.domain.DeviceEntity;
import org.bigtows.esptemperaturecontroller.domain.TemperatureEntity;
import org.bigtows.esptemperaturecontroller.repository.DeviceEntityRepository;
import org.bigtows.esptemperaturecontroller.repository.TemperatureEntityRepository;
import org.bigtows.esptemperaturecontroller.service.dto.TemperatureDevice;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TemperatureServiceImpl implements TemperatureService {

    private final DeviceEntityRepository deviceEntityRepository;

    private final TemperatureEntityRepository temperatureEntityRepository;

    @Override
    public void addTemperature(Long deviceId, Long timestamp, float temperature, String signature) {
        var device = deviceEntityRepository.findById(deviceId).orElseThrow();
        this.validateSignature(device, timestamp, temperature, signature);
        var maxTimestamp = temperatureEntityRepository.getMaxTimestampByDeviceEntityId(deviceId);

        if (maxTimestamp != null && maxTimestamp >= timestamp) {
            throw new RuntimeException("Outdated timestamp");
        }

        temperatureEntityRepository.save(
                TemperatureEntity.builder()
                        .deviceEntity(device)
                        .temperature(temperature)
                        .timestampDevice(timestamp)
                        .serverDate(new Date())
                        .build()
        );
    }

    @Override
    public List<TemperatureDevice> getLast20(Long deviceId) {
        var device = deviceEntityRepository.findById(deviceId).orElseThrow();
        return temperatureEntityRepository.findAllByDeviceEntity(device,
                PageRequest.of(0, 20,
                        Sort.sort(TemperatureEntity.class).by(TemperatureEntity::getServerDate).descending())
        ).stream().map(
                temperatureEntity -> TemperatureDevice.builder()
                        .date(temperatureEntity.getServerDate())
                        .temperature(temperatureEntity.getTemperature())
                        .build()
        ).collect(Collectors.toList());
    }

    private void validateSignature(DeviceEntity deviceEntity, Long timestamp, float temperature, String signature) {
        if (!sha1(sha1(deviceEntity.getId()) + "&" + sha1(timestamp) + "&" + sha1(String.format("%.2f", temperature)) + "&" + deviceEntity.getHashToken())
                .equals(signature)) {
            throw new RuntimeException("Invalid signature");
        }

    }

    @SneakyThrows
    private String sha1(Object input) {
        MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
        msdDigest.update(input.toString().getBytes(StandardCharsets.UTF_8), 0, input.toString().length());
        return DatatypeConverter.printHexBinary(msdDigest.digest()).toLowerCase();
    }
}
