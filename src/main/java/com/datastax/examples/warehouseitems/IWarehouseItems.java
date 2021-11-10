package com.datastax.examples.warehouseitems;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWarehouseItems extends CassandraRepository<WarehouseItems,String> {

    @Query("select * from warehouseitems where product_id=?0 order by quantity_available desc limit 1;")
    public Optional<WarehouseItems> getWareHouseByProductId(String product_id);

    @AllowFiltering
    @Query("select * from warehouseitems where product_id=?0 and warehouse_location=?1 allow filtering;")
    public Optional<WarehouseItems> getWareHouseByProductIdAAndWarehouseLocation(String product_id, String warehouse_location);

}
