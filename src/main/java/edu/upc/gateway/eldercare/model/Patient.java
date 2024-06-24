
package edu.upc.gateway.eldercare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private int id;

    private String name;

    private String address;

    private String date;

    private int weight;

    private int height;
}
