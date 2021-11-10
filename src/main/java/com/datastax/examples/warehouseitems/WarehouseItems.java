package com.datastax.examples.warehouseitems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(value="warehouseitems")
public class WarehouseItems {
    @PrimaryKeyColumn(name="product_id",ordinal = 0,type= PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String productId;

    @PrimaryKeyColumn(name="quantity_available",ordinal=1,type=PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.INT)
    private int quantityAvailable;

    @PrimaryKeyColumn(name="warehouse_id",ordinal = 2,type=PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID warehouseId;


    @PrimaryKeyColumn(name="warehouse_location",ordinal = 3,type=PrimaryKeyType.CLUSTERED)
//    @Column("warehouse_location")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String warehouseLocation;
}
