package org.bigtows.esptemperaturecontroller.controller.api;

import lombok.AllArgsConstructor;
import org.bigtows.esptemperaturecontroller.service.TemperatureService;
import org.bigtows.esptemperaturecontroller.service.dto.TemperatureDevice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/temperature")
public class TemperatureController {

    private final TemperatureService temperatureService;

    @GetMapping
    public String addTemperature(@RequestParam("id") Long idDevice,
                                 @RequestParam("timestamp") Long timestamp,
                                 @RequestParam("temperature") float temperature,
                                 @RequestParam("signature") String signature
    ) {
        try {
            temperatureService.addTemperature(idDevice, timestamp, temperature, signature);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return "";
    }

    @GetMapping("last")
    public List<TemperatureDevice> getLastTemperature(@RequestParam("deviceId") Long deviceId) {
        return temperatureService.getLast20(deviceId);
    }
}
//1605701955490
//4 294 967 295