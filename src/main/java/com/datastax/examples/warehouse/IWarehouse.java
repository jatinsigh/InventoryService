package com.datastax.examples.warehouse;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IWarehouse extends CassandraRepository<Warehouse,String> {
    @AllowFiltering
    public List<Warehouse> findWarehouseByWarehouseLocation(String location);
}
