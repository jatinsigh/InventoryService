package com.datastax.examples.warehouse;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IWarehouse extends CassandraRepository<Warehouse, UUID> {

    @Query("Select * from warehouse where warehouse_location=?0 allow filtering;")
    public List<Warehouse> findWarehouseByWarehouse_location(String warehouse_location);
}
