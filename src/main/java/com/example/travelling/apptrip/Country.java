package com.example.travelling.apptrip;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_country")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Country  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iso;
    private String name;
    private String niceName;
    private String iso3;
    private Integer numCode;
    private Integer phoneCode;

}
