package testing;

import java.util.ArrayList;
import java.util.List;

public class FlightFilterManager {
    private final List<FlightFilter> filters = new ArrayList<>();

    public void addFilter(FlightFilter filter) {
        filters.add(filter);
    }

    public List<Flight> applyFilters(List<Flight> flights) {
        List<Flight> filteredFlights = flights;
        for (FlightFilter filter : filters) {
            filteredFlights = filter.filter(filteredFlights);
        }
        return filteredFlights;
    }
}