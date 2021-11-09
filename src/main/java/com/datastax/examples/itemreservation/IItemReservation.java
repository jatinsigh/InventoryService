package com.datastax.examples.itemreservation;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemReservation extends CassandraRepository<ItemsReservation,String> {
}
