package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Исходные перелёты:");
        flights.forEach(System.out::println);

        FlightFilterManager filterManager = new FlightFilterManager();

        filterManager.addFilter(new FilterByDepartureTime());
        List<Flight> filteredByDepartureTime = filterManager.applyFilters(flights);
        System.out.println("\nПолеты после фильтрации по времени вылета:");
        filteredByDepartureTime.forEach(System.out::println);


        filterManager = new FlightFilterManager();
        filterManager.addFilter(new FilterBySegmentArrival());
        List<Flight> filteredByArrival = filterManager.applyFilters(flights);
        System.out.println("\nПолеты после фильтрации по времени прилёта:");
        filteredByArrival.forEach(System.out::println);

        filterManager = new FlightFilterManager();
        filterManager.addFilter(new FilterByGroundTime());
        List<Flight> filteredByGroundTime = filterManager.applyFilters(flights);
        System.out.println("\nПолеты фильтрации по времени на земле:");
        filteredByGroundTime.forEach(System.out::println);
    }
}