package com.datastax.examples.warehouse;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWarehouse extends CassandraRepository<Warehouse,Integer> {
    @AllowFiltering
    public List<Warehouse> findWarehouseByWarehouseLocation(String location);

}
