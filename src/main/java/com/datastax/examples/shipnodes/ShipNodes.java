package com.datastax.examples.shipnodes;

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
@Table(value="shipnodes")
public class ShipNodes {
    @PrimaryKeyColumn(name = "shipnodes_id",ordinal = 0,type=PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.UUID)
    private UUID shipNodeId;

    @PrimaryKeyColumn(name = "shipnodes_location",ordinal=1,type= PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String shipNodeLocation;

    @Column("shipnodes_capacity")
    @CassandraType(type = CassandraType.Name.INT)
    private int shipNodeCapacity;

    @Column("shipnodes_type")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String shipNodeType;
}
