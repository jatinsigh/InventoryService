package com.datastax.examples.itemreservation;

import com.datastax.examples.shipnodes.IShipNodes;
import com.datastax.examples.shipnodes.ShipNodes;
import com.datastax.examples.shipnodesitems.ShipNodesItemService;
import com.datastax.examples.shipnodesitems.IShipNodesItems;
import com.datastax.examples.shipnodesitems.ShipNodesItems;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api")
public class InventoryController {
    @GetMapping("/")
    public String check(){
        return "Hello!!";
    }

    @Autowired
    IShipNodes shipnodesRepo;

    @Autowired
    IShipNodesItems shipnodesItemsRepo;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ShipNodesItemService shipNodesItemService;

//    @GetMapping("/shipnodes")
//    public ResponseEntity<List<ShipNodes>> getshipnodes() throws Exception{
//        return new ResponseEntity<>(shipnodesRepo.findAll(), HttpStatus.OK);
//    }
    @GetMapping("/shipnodesitems")
    public ResponseEntity<List<ShipNodesItems>> getshipnodesitems() throws Exception{
        return new ResponseEntity<>(shipnodesItemsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/shipnodes")
    public ResponseEntity<List<ShipNodes>> getshipnodesByLocation() throws Exception{
        return new ResponseEntity<>(shipnodesRepo.findAll(), HttpStatus.OK);
    }
//    getshipnodesByLocation
    @GetMapping("/shipnodes/{location}")
    public ResponseEntity<List<ShipNodes>> getshipnodesByLocation(@PathVariable String location) throws Exception{
        return new ResponseEntity<>(shipnodesRepo.findshipnodesByshipnodes_location(location), HttpStatus.OK);
    }

//  createshipnodes
    @PostMapping("/shipnodes")
    public ResponseEntity<ShipNodes> createshipnodes(@RequestBody ShipNodes shipNodes) throws Exception{
//        System.out.println(shipnodes.toString());
        return new ResponseEntity<>(shipnodesRepo.save(shipNodes), HttpStatus.OK);
    }

    @PostMapping("/shipnodesitems")
    public ResponseEntity<ShipNodesItems> createshipnodesItems(@RequestBody ShipNodesItems shipNodesItems) throws Exception{
//        System.out.println(shipnodesItems.toString());
        return new ResponseEntity<>(shipnodesItemsRepo.save(shipNodesItems), HttpStatus.OK);
    }

//    getOnhandSupplyOfItemByProductId
    @GetMapping("/shipnodes/items/{productId}")
    public ResponseEntity<Object> getOnHandSupplyOfItemByProductId(@PathVariable String productId) throws Exception{
        try {
            return new ResponseEntity<>(shipNodesItemService.getOnhandSupplyOfItems(productId), HttpStatus.OK);
        }
        catch (Exception e){
            log.warn("Exception : {}", e);
            return new ResponseEntity<>("Product Not Found!!", HttpStatus.BAD_REQUEST);
        }
    }

//    getItemsReserve
    @PostMapping("/itemsreserve")
    public ResponseEntity<Object> getItemReserved(@RequestBody ItemsReservationDTO itemsReservationDTO) throws Exception {
        try {
            return new ResponseEntity<>(reservationService.getItemReserved(itemsReservationDTO), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}