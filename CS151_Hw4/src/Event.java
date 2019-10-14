/*
 * */
public class Event {
	private String name;
	private TimeInterval timeInterval = new TimeInterval();

	public Event(String name, String date, String month, String year) {
		this.name = name;
		int year1 = Integer.parseInt(year);
		int month1 = Integer.parseInt(month);
		int date1 = Integer.parseInt(date);
		timeInterval.setStartDate(year1, month1, date1);
	}

	public Event(String name, String startTime, String endTime) {
		this.name = name;
		this.timeInterval.setStartTime(startTime);
		this.timeInterval.setEndTime(endTime);
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
