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


        String productId = itemsReservationDTO.getProductId();
        String location = itemsReservationDTO.getLocation();
        Optional<WarehouseItems> warehouseItemsByLocation = iWarehouseItems.getWareHouseByProductIdAndWarehouseLocation(productId, location);
        if(warehouseItemsByLocation.isPresent()){

            ItemsReservation itemsReservation=ItemsReservation.builder().sourceLocation(warehouseItemsByLocation.get().getWarehouseLocation())
                    .destinationLocation(itemsReservationDTO.getLocation()).sourceId(warehouseItemsByLocation.get().getWarehouseId())
                    .productId(itemsReservationDTO.getProductId()).build();

            int qty = warehouseItemsByLocation.get().getQuantityAvailable() - 1;
            iWarehouseItems.delete(warehouseItemsByLocation.get());
            if(qty > 0){
                warehouseItemsByLocation.get().setQuantityAvailable(qty);
                iWarehouseItems.save(warehouseItemsByLocation.get());
            }
            iItemReservation.save(itemsReservation);
            return "Product Reserved in Warehouse Location: " + warehouseItemsByLocation.get().getWarehouseLocation();
        }


        Optional<WarehouseItems> warehouseItems=iWarehouseItems.getWareHouseByProductId(itemsReservationDTO.getProductId());
        if(!warehouseItems.isPresent()){
            throw new Exception("Warehouse not found");
        }


        ItemsReservation itemsReservation=ItemsReservation.builder().sourceLocation(warehouseItems.get().getWarehouseLocation())
                .destinationLocation(itemsReservationDTO.getLocation()).sourceId(warehouseItems.get().getWarehouseId())
                .productId(itemsReservationDTO.getProductId()).build();

        iWarehouseItems.delete(warehouseItems.get());
        int quantity = warehouseItems.get().getQuantityAvailable() - 1;

        if(quantity>0){
            warehouseItems.get().setQuantityAvailable(quantity);
            iWarehouseItems.save(warehouseItems.get());
        }

        iItemReservation.save(itemsReservation);
        return "Product Reserved in Warehouse Location: " + warehouseItems.get().getWarehouseLocation();
    }
}
