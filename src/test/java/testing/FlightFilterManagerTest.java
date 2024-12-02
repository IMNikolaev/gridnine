package testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightFilterManagerTest {
    @Test
    void testApplyFilters() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilterManager manager = new FlightFilterManager();

        manager.addFilter(new FilterByDepartureTime());
        manager.addFilter(new FilterBySegmentArrival());
        manager.addFilter(new FilterByGroundTime());

        List<Flight> filteredFlights = manager.applyFilters(flights);

        boolean allFlightsValid = filteredFlights.stream().allMatch(flight -> {
            boolean departureAfterNow = flight.getSegments().stream()
                    .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()));
            boolean segmentTimesValid = flight.getSegments().stream()
                    .allMatch(segment -> !segment.getArrivalDate().isBefore(segment.getDepartureDate()));
            List<Segment> segments = flight.getSegments();
            long totalGroundTime = 0;
            for (int i = 0; i < segments.size() - 1; i++) {
                totalGroundTime += java.time.Duration.between(
                        segments.get(i).getArrivalDate(),
                        segments.get(i + 1).getDepartureDate()
                ).toMinutes();
            }
            boolean groundTimeValid = totalGroundTime <= 120;

            return departureAfterNow && segmentTimesValid && groundTimeValid;
        });
        assertEquals(true, allFlightsValid);
    }
}