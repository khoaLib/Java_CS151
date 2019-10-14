/*
 * */
public class Event {
	private String name;
	private TimeInterval timeInterval;

	public Event() {
		timeInterval = new TimeInterval();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TimeInterval getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(TimeInterval timeInterval) {
		this.timeInterval = timeInterval;
	}

}
