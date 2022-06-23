package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserGenerator {
    private final String city;
    private final String phone;
    private final String date;
    private final String name;
}
