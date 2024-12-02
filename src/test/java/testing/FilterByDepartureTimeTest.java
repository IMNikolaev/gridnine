package testing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByDepartureTimeTest {
    @Test
    void testFilter() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter filter = new FilterByDepartureTime();
        List<Flight> filteredFlights = filter.filter(flights);
        assertEquals(flights.size() - 1, filteredFlights.size());
    }
}