package org.bigtows.esptemperaturecontroller.controller.page;

import lombok.AllArgsConstructor;
import org.bigtows.esptemperaturecontroller.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class IndexPageController {

    private final DeviceService deviceService;

    @GetMapping
    public String getMainPage(Model model){

        model.addAttribute("devices",deviceService.getAll());
        return "index";
    }
}
