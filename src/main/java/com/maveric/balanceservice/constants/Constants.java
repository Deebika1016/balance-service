package com.maveric.balanceservice.constants;

import java.time.LocalDateTime;
public class Constants {

    private Constants()
    {

    }
    public static LocalDateTime getCurrentDateTime() {
        return (LocalDateTime.now());
    }
}

