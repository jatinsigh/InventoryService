package com.datastax.examples.shipnodesitems;

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
@Table(value="shipnodesitems")
public class ShipNodesItems {
    @PrimaryKeyColumn(name="product_id",ordinal = 0,type= PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String productId;

    @PrimaryKeyColumn(name="quantity_available",ordinal=1,type=PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.INT)
    private int quantityAvailable;

    @PrimaryKeyColumn(name="shipnodes_id",ordinal = 2,type=PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID shipNodesId;

    @PrimaryKeyColumn(name="shipnodes_location",ordinal = 3,type=PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String shipNodesLocation;
}
