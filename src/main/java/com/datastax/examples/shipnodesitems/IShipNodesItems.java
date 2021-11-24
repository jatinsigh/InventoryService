package com.datastax.examples.shipnodesitems;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IShipNodesItems extends CassandraRepository<ShipNodesItems,String> {

    @Query("select * from shipnodesitems where product_id=?0 order by quantity_available desc limit 1;")
    public Optional<ShipNodesItems> getWareHouseByProductId(String product_id);

    @Query("select * from shipnodesitems where product_id=?0 and shipnodes_location=?1 allow filtering;")
    public Optional<ShipNodesItems> getShipNodesByProductIdAndShipNodes_Location(String product_id, String shipnodes_location);

    @Query("select * from shipnodesitems where product_id=?0;")
    List<ShipNodesItems> findAllitemsById(String product_id);

    @Query("DELETE FROM shipnodesitems WHERE quantity_available=?0 AND product_id=?1 AND shipnodes_id=?2;")
    void deleteItemsFromShipNodes(int quantity_available, String product_id, UUID shipnodes_id);

//    @AllowFiltering()
//    public void delete(ShipNodesItems shipNodesItems);
}
