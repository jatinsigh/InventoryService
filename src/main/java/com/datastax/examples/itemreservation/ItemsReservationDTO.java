package com.datastax.examples.itemreservation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsReservationDTO {
    public String productId;
    public String location;
}
