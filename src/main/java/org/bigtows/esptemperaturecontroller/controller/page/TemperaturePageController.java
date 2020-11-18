package org.bigtows.esptemperaturecontroller.controller.page;

import lombok.AllArgsConstructor;
import org.bigtows.esptemperaturecontroller.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@RequestMapping("temperature")
public class TemperaturePageController {

    private final DeviceService deviceService;

    @GetMapping
    public String getTemperaturePageForDevice(Model model, @RequestParam("deviceId") Long deviceId) {
        model.addAttribute("device", deviceService.getById(deviceId));

        return "temperature";
    }
}
