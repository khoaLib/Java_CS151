import java.time.LocalDate;

public class TimeInterval {
	private LocalDate startDate;
	private LocalDate endDate;
	private String startTime;
	private String endTime;
	private String timePerWeek; // this will receive day of week
	private String schedule; // string of full schedule
	// sunday is 7 and monday 1

	public TimeInterval() {
		// TODO Auto-generated constructor stub
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(int year, int month, int dayOfMonth) {
		startDate = LocalDate.of(year, month, dayOfMonth);
		// this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(int year, int month, int dayOfMonth) {
		this.endDate = LocalDate.of(year, month, dayOfMonth);
	}

	public String getTimePerWeek() {
		return timePerWeek;
	}

	public void setTimePerWeek(String timePerWeek) {
		this.timePerWeek = timePerWeek;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

}
