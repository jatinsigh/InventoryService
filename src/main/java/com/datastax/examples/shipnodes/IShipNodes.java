package com.datastax.examples.shipnodes;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IShipNodes extends CassandraRepository<ShipNodes, UUID> {

    @Query("Select * from shipnodes where shipnodes_location=?0 allow filtering;")
    public List<ShipNodes> findshipnodesByshipnodes_location(String shipnodes_location);
}
