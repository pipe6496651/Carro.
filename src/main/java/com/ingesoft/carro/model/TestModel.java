package com.ingesoft.carro.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestModel implements Serializable {
    private static final long serialVersionUID = -8783783030860258092L;

    private Long id;
    private String name;
    private Float price;
}
