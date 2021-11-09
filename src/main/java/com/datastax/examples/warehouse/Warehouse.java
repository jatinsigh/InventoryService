package com.datastax.examples.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(value="warehouse")
public class Warehouse {
    @PrimaryKeyColumn(name = "warehouse_id",ordinal = 0,type=PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID warehouseId;

    @PrimaryKeyColumn(name = "warehouse_location",ordinal=1,type= PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String warehouseLocation;

    @Column("warehouse_capacity")
    @CassandraType(type = CassandraType.Name.INT)
    private int warehouseCapacity;
}
