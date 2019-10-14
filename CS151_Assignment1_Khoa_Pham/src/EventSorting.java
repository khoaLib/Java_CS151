import java.util.Comparator;

public class EventSorting implements Comparator<Event> {

	public EventSorting() {

	}

	// Overwrite method compare of Comparator library to help the Collections
	// sorting the
	// arrayList with a specific type of data
	public int compare(Event e1, Event e2) {
		if (e1.getTimeInterval().getStartDate().equals(e2.getTimeInterval().getStartDate())) {
			return (e1.getTimeInterval().getStartTime().compareTo(e2.getTimeInterval().getStartTime()));
		}
		return (e1.getTimeInterval().getStartDate().compareTo(e2.getTimeInterval().getStartDate()));
	}
}
