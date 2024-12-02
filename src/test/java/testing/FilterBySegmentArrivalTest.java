package testing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterBySegmentArrivalTest {
    @Test
    void testFilter() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter filter = new FilterBySegmentArrival();
        List<Flight> filteredFlights = filter.filter(flights);

        long invalidFlightsCount = flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .count();

        assertEquals(flights.size() - invalidFlightsCount, filteredFlights.size());
    }
}