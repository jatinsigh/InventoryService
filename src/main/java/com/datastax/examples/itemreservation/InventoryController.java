package com.datastax.examples.itemreservation;

import com.datastax.examples.warehouse.IWarehouse;
import com.datastax.examples.warehouse.Warehouse;
import com.datastax.examples.warehouse.WarehouseItemService;
import com.datastax.examples.warehouseitems.IWarehouseItems;
import com.datastax.examples.warehouseitems.WarehouseItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class InventoryController {
    @GetMapping("/")
    public String check(){
        return "Hello!!";
    }

    @Autowired
    IWarehouse warehouseRepo;

    @Autowired
    IWarehouseItems warehouseItemsRepo;

    @Autowired
    ReservationService reservationService;

    @Autowired
    WarehouseItemService warehouseItemService;

    @GetMapping("/warehouses")
    public ResponseEntity<List<Warehouse>> getWarehouse() throws Exception{
        return new ResponseEntity<>(warehouseRepo.findAll(), HttpStatus.OK);
    }
    @GetMapping("/warehouseitems")
    public ResponseEntity<List<WarehouseItems>> getWarehouseitems() throws Exception{
        return new ResponseEntity<>(warehouseItemsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/warehouse")
    public ResponseEntity<List<Warehouse>> getWarehouseByLocation() throws Exception{
        return new ResponseEntity<>(warehouseRepo.findAll(), HttpStatus.OK);
    }
//    getWarehouseByLocation
    @GetMapping("/warehouse/{location}")
    public ResponseEntity<List<Warehouse>> getWarehouseByLocation(@PathVariable String location) throws Exception{
        return new ResponseEntity<>(warehouseRepo.findWarehouseByWarehouse_location(location), HttpStatus.OK);
    }

//  createWarehouse
    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) throws Exception{
        System.out.println(warehouse.toString());
        return new ResponseEntity<>(warehouseRepo.save(warehouse), HttpStatus.OK);
    }

    @PostMapping("/warehouseitems")
    public ResponseEntity<WarehouseItems> createWarehouseItems(@RequestBody WarehouseItems warehouseItems) throws Exception{
        System.out.println(warehouseItems.toString());
        return new ResponseEntity<>(warehouseItemsRepo.save(warehouseItems), HttpStatus.OK);
    }

//    getOnhandSupplyOfItemByProductId
    @GetMapping("/warehouse/items/{productId}")
    public ResponseEntity<List<WarehouseItems>> getOnHandSupplyOfItemByProductId(@PathVariable String productId) throws Exception{
        return new ResponseEntity<>(warehouseItemService.getOnhandSupplyOfItems(productId), HttpStatus.OK);
    }

//    getItemsReserve
    @PostMapping("itemsreserve")
    public ResponseEntity<String> getItemReserved(@RequestBody ItemsReservationDTO itemsReservationDTO) throws Exception {
        return new ResponseEntity<>(reservationService.getItemReserved(itemsReservationDTO), HttpStatus.OK);
    }
}
