package com.datastax.examples.itemreservation;

import com.datastax.examples.warehouse.IWarehouse;
import com.datastax.examples.warehouse.Warehouse;
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
    IItemReservation itemReservationRepo;

    @Autowired
    ReservationService reservationService;

//    getWarehouseByLocation
    @GetMapping("/warehouse/{location}")
    public ResponseEntity<List<Warehouse>> getWarehouseByLocation(@PathVariable String location){
        return new ResponseEntity<>(warehouseRepo.findWarehouseByWarehouseLocation(location), HttpStatus.OK);
    }

//  createWarehouse
    @PostMapping("/warehouse")
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse){
        System.out.println(warehouse.toString());
        return new ResponseEntity<>(warehouseRepo.save(warehouse), HttpStatus.OK);
    }

    @PostMapping("/warehouseitems")
    public ResponseEntity<WarehouseItems> createWarehouseItems(@RequestBody WarehouseItems warehouseItems){
        System.out.println(warehouseItems.toString());
        return new ResponseEntity<>(warehouseItemsRepo.save(warehouseItems), HttpStatus.OK);
    }

//    getOnhandSupplyOfItemByProductId
    @GetMapping("/warehouse/items/{productId}")
    public ResponseEntity<List<WarehouseItems>> getOnHandSupplyOfItemByProductId(@PathVariable int productId){
        List<Integer> prodId = new ArrayList<>();
        prodId.add(productId);
        return new ResponseEntity<>(warehouseItemsRepo.findAllById(prodId), HttpStatus.OK);
    }

//    getItemsReserve
    @PostMapping("itemsreserve")
    public ResponseEntity<String> getItemReserved(@RequestBody ItemsReservationDTO itemsReservationDTO){
        return new ResponseEntity<>(reservationService.getItemReserved(itemsReservationDTO),HttpStatus.OK);
    }
}
