package com.start.produtos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="locations")
@Getter
@Setter
public class LocationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocations;
    private String city;
    private String state;
    private String country;
    private String cep;
}
