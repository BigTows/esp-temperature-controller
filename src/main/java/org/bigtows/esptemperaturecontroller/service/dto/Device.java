package org.bigtows.esptemperaturecontroller.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public final class Device {

    private Long id;

    private String name;

    private String description;
}
