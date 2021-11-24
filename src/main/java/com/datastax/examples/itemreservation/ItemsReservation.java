package com.datastax.examples.itemreservation;

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
@Table(value="itemsreservation")
public class ItemsReservation {
    @PrimaryKeyColumn(name = "product_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String productId;

    @Column("destination_location")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String destinationLocation;

    @Column("source_location")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String sourceLocation;

    @PrimaryKeyColumn(name = "source_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID sourceId;
}