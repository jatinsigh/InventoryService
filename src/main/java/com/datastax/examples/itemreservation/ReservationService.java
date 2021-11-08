package com.datastax.examples.itemreservation;

import com.datastax.examples.warehouseitems.IWarehouseItems;
import com.datastax.examples.warehouseitems.WarehouseItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    IWarehouseItems iWarehouseItems;

    @Autowired
    IItemReservation iItemReservation;
    public String getItemReserved(ItemsReservationDTO itemsReservationDTO) throws Exception{
        Optional<WarehouseItems> warehouseItems=iWarehouseItems.getWareHouseByProductId(itemsReservationDTO.getProductId());
        if(!warehouseItems.isPresent()){
            throw new Exception("Warehouse not found");
        }
        ItemsReservation itemsReservation=ItemsReservation.builder().sourceLocation(warehouseItems.get().getWarehouseLocation())
                .destinationLocation(itemsReservationDTO.getLocation()).sourceId(warehouseItems.get().getWarehouseId())
                .productId(itemsReservationDTO.getProductId()).build();

        iItemReservation.save(itemsReservation);
        return "Product Reserved!";
    }
}
