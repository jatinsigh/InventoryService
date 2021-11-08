package com.datastax.examples.warehouseitems;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(value="warehouseitems")
public class WarehouseItems {
    @PrimaryKey
    @Column("product_id")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String productId;

    @Column("quantity_available")
    @CassandraType(type = CassandraType.Name.INT)
    private int quantityAvailable;

    @Column("warehouse_id")
    @CassandraType(type = CassandraType.Name.UUID)
    private int warehouseId;

    @Column("warehouse_location")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String warehouseLocation;
}
