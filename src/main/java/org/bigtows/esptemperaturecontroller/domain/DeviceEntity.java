package org.bigtows.esptemperaturecontroller.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class DeviceEntity {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * Hash of token (SHA1)
     */
    @Column(nullable = false)
    private String hashToken;

    private String name;

    private String description;


    @OneToMany(mappedBy = "deviceEntity")
    private List<TemperatureEntity> temperatureEntityList;
}
