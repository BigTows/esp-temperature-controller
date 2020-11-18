package org.bigtows.esptemperaturecontroller.service;

import org.bigtows.esptemperaturecontroller.service.dto.TemperatureDevice;

import java.util.List;

public interface TemperatureService {


     void addTemperature(Long deviceId, Long timestamp, float temperature, String signature) ;


     /**
      * Get last 60
      * @param deviceId
      */
     List<TemperatureDevice> getLast20(Long deviceId);
}
