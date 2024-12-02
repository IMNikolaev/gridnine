package com.gridnine.testing;

import java.util.List;

@FunctionalInterface
public interface FlightFilter {
    List<Flight> filter(List<Flight> flights);
}