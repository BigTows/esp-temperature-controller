package org.bigtows.esptemperaturecontroller.service;

import org.bigtows.esptemperaturecontroller.service.dto.Device;

import java.util.List;

public interface DeviceService {


    List<Device> getAll();

    Device getById(Long id);
}
