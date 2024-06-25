package edu.upc.gateway.eldercare.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private int id;

    private double heartRate;

    private double breathingFrequency;

    private double temperature;

    private String longitude;

    private String latitude;

    private String reportTime;

}

/*
{
    "id": 0,
    "heartRate": 0,
    "breathingFrequency": 0,
    "temperature": 0,
    "longitude": "string",
    "latitude": "string",
    "reportTime": "2024-06-25T22:51:41.026Z"
  }
*/