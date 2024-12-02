package testing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilterByGroundTimeTest {
    @Test
    void testFilter() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilter filter = new FilterByGroundTime();
        List<Flight> filteredFlights = filter.filter(flights);

        long invalidFlightsCount = flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    long totalGroundTime = 0;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        totalGroundTime += java.time.Duration.between(
                                segments.get(i).getArrivalDate(),
                                segments.get(i + 1).getDepartureDate()
                        ).toMinutes();
                    }
                    return totalGroundTime > 120;
                }).count();

        assertEquals(flights.size() - invalidFlightsCount, filteredFlights.size());
    }
}