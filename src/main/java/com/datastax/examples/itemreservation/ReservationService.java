package com.datastax.examples.itemreservation;

import com.datastax.examples.shipnodesitems.IShipNodesItems;
import com.datastax.examples.shipnodesitems.ShipNodesItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    IShipNodesItems iShipNodesItems;

    @Autowired
    IItemReservation iItemReservation;
    public String getItemReserved(ItemsReservationDTO itemsReservationDTO) throws Exception{


        String productId = itemsReservationDTO.getProductId();
        String location = itemsReservationDTO.getLocation();
        Optional<ShipNodesItems> shipNodesItems = iShipNodesItems.getShipNodesByProductIdAndShipNodes_Location(productId, location);
        if(shipNodesItems.isPresent()){

            ItemsReservation itemsReservation=ItemsReservation.builder().sourceLocation(shipNodesItems.get().getShipNodesLocation())
                    .destinationLocation(itemsReservationDTO.getLocation()).sourceId(shipNodesItems.get().getShipNodesId())
                    .productId(itemsReservationDTO.getProductId()).build();

            int qty = shipNodesItems.get().getQuantityAvailable() - 1;
            iShipNodesItems.deleteItemsFromShipNodes(shipNodesItems.get().getQuantityAvailable(),shipNodesItems.get().getProductId(),shipNodesItems.get().getShipNodesId());
            if(qty > 0){
                shipNodesItems.get().setQuantityAvailable(qty);
                iShipNodesItems.save(shipNodesItems.get());
            }
            iItemReservation.save(itemsReservation);
            return "Product Reserved in ShipNode Location: " + shipNodesItems.get().getShipNodesLocation();
        }


        Optional<ShipNodesItems> warehouseItems= iShipNodesItems.getWareHouseByProductId(itemsReservationDTO.getProductId());
        if(!warehouseItems.isPresent()){
            throw new Exception("ShipNode not found");
        }


        ItemsReservation itemsReservation=ItemsReservation.builder().sourceLocation(warehouseItems.get().getShipNodesLocation())
                .destinationLocation(itemsReservationDTO.getLocation()).sourceId(warehouseItems.get().getShipNodesId())
                .productId(itemsReservationDTO.getProductId()).build();

        iShipNodesItems.deleteItemsFromShipNodes(warehouseItems.get().getQuantityAvailable(),warehouseItems.get().getProductId(),warehouseItems.get().getShipNodesId());
        int quantity = warehouseItems.get().getQuantityAvailable() - 1;

        if(quantity>0){
            warehouseItems.get().setQuantityAvailable(quantity);
            iShipNodesItems.save(warehouseItems.get());
        }

        iItemReservation.save(itemsReservation);
        return "Product Reserved in ShipNode Location: " + warehouseItems.get().getShipNodesLocation();
    }
}
