package org.bigtows.esptemperaturecontroller.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("api/v1/timestamp")
public class TimeController {



    @GetMapping
    public String getServerTimestamp(){
        return String.valueOf(new Date().getTime());
    }
}
