package org.bigtows.esptemperaturecontroller.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public final class TemperatureDevice {


    private Date date;

    private Float temperature;
}
