package com.datastax.examples.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(value="warehouse")
public class Warehouse {
    @PrimaryKey
    @Column("warehouse_id")
    @CassandraType(type = CassandraType.Name.UUID)
    private int warehouseId;

    @Column("warehouse_capacity")
    @CassandraType(type = CassandraType.Name.INT)
    private int warehouseCapacity;

    @Column("warehouse_location")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String warehouseLocation;
}
