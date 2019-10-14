import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Model {
	public static String FILE;
	public String monthAr[] = { "Jan", "Feb", "Mar", "Apr", "May", "June", "Aug", "Sep", "Oct", "Nov", "Dev" };
	public String dayOfWeek[] = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	private ArrayList<Event> eventlist;
	private ArrayList<LocalDate> eventdates;
	private ArrayList<ChangeListener> listens;
	private LocalDate localdate;

	public Model() {
		localdate = LocalDate.now();
		setEventlist(new ArrayList<Event>());
		setEventdates(new ArrayList<LocalDate>());
		setListens(new ArrayList<ChangeListener>());
		// monthAr =
		// {"Jan","Feb","Mar","Apr","May","June","Aug","Sep","Oct","Nov","Dev"};
	}

	public void nextDay() {
		localdate = localdate.plusDays(1);
		System.out.println("Next: " + localdate.getDayOfMonth());
		//update();
	}

	public void update() {
		for (ChangeListener change : listens) {
			change.stateChanged(new ChangeEvent(this));
		}
	}

	public void lastDay() {
		localdate =localdate.minusDays(1);
		System.out.println("previous: " + localdate.getDayOfMonth());
		//changeState();
	}

	public LocalDate getDate() {
		return localdate;
	}

	public ArrayList<Event> getEventlist() {
		return eventlist;
	}

	public void setEventlist(ArrayList<Event> eventlist) {
		this.eventlist = eventlist;
	}

	public ArrayList<LocalDate> getEventdates() {
		return eventdates;
	}

	public void setEventdates(ArrayList<LocalDate> eventdates) {
		this.eventdates = eventdates;
	}

	public ArrayList<ChangeListener> getListens() {
		return listens;
	}

	public void setListens(ArrayList<ChangeListener> listens) {
		this.listens = listens;
	}

	public void addEvent(Event event) {
		eventlist.add(event);
	}

	public void addListener(ChangeListener l) {
		listens.add(l);
	}

	public int searchName(String event) {
		for (int i = 0; i < monthAr.length; i++) {
			if (event.equals(monthAr[i])) {
				return i;
			}
		}
		return 0;
	}

}
