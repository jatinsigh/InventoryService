package com.datastax.examples.warehouseitems;

import com.datastax.examples.warehouseitems.IWarehouseItems;
import com.datastax.examples.warehouseitems.WarehouseItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseItemService {
    @Autowired
    IWarehouseItems iWarehouseItems;

    public List<WarehouseItems> getOnhandSupplyOfItems(String productId){
        List<String> prodId = new ArrayList<>();
        prodId.add(productId);
        List<WarehouseItems> warehouseItems = iWarehouseItems.findAllById(prodId);
        return warehouseItems;

    }
}
