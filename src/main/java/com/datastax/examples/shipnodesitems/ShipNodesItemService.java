package com.datastax.examples.shipnodesitems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ShipNodesItemService {
    @Autowired
    IShipNodesItems iShipNodesItems;

    public List<ShipNodesItems> getOnhandSupplyOfItems(String productId){
        List<String> prodId = new ArrayList<>();
        prodId.add(productId);
        List<ShipNodesItems> shipNodesItems = iShipNodesItems.findAllitemsById(productId);
        return shipNodesItems;
    }
}
