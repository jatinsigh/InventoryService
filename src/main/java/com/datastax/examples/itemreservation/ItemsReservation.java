package com.datastax.examples.itemreservation;

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
@Table(value="itemsreservation")
public class ItemsReservation {
    @PrimaryKey
    @Column("product_id")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String productId;

    @Column("destination_location")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String destinationLocation;

    @Column("source_location")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String sourceLocation;

    @Column("source_id")
    @CassandraType(type = CassandraType.Name.UUID)
    private int sourceId;
}
