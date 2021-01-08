package com.batch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonWithDate {
    private String name;
    private String surName;
    private LocalDate dateOfBirth;
}
