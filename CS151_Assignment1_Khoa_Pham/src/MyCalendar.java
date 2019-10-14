import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MyCalendar {
	private ArrayList<Event> listevent = new ArrayList<Event>();

	public MyCalendar() {// constructor
	}

	/*
	 * TodayCalendar function will print the calendar to the user Todat date is put
	 * in a bracket [] with no other events occur
	 */
	// Beginning of the program printing the calendar with braket on today date
	public void TodayCalendar(LocalDate localdate) {
		System.out.println("\n    " + localdate.getMonth() + " " + localdate.getYear());
		System.out.println("Su Mo Tu We Th Fr Sa");

		// now we try to get the firstday of the year and we want to know
		// what date in the week that day is
		LocalDate firstday = LocalDate.of(localdate.getYear(), localdate.getMonth(), 1); // 1 for Januaray
		int firstDateInYear = firstday.getDayOfWeek().getValue();

		for (int i = 0; i < firstDateInYear; i++) {
			System.out.print("   ");
		}

		// array of days in each month 1-12
		int array[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		if (localdate.isLeapYear()) {
			array[1] += 1;
		}

		// find today
		int todayNum = localdate.getDayOfMonth();

		for (int i = 0; i < array[localdate.getMonthValue() - 1]; i++) {
			if (i == todayNum) {
				System.out.printf("[%2d] ", (i + 1));
			} else {
				System.out.printf("%2d ", (i + 1));
			}

			if (((i + 1 + firstDateInYear) % 7 == 0 || i == array[localdate.getMonthValue()])) {
				System.out.println();
			}
		}
		System.out.println("\nCurrent Month is: " + localdate.getMonthValue());
	}// end method print calendar

	/*
	 * MonthCalendar function print the Calendar to the Screen for the user All of
	 * the event dates are putted inside a bracket {} Parameter localdate: to keep
	 * track the current month==> the program can go Next or Previous
	 */
	// print the Calendar with a specific month
	public void MonthCalendar(LocalDate localdate) {
		System.out.println("\n    " + localdate.getMonth() + " " + localdate.getYear());
		System.out.println("Su Mo Tu We Th  Fr  Sa");

		// now we try to get the firstday of the year and we want to know
		// what date in the week that day is
		LocalDate firstday = LocalDate.of(localdate.getYear(), localdate.getMonth(), 1); // 1 for Januaray
		int firstDateInYear = firstday.getDayOfWeek().getValue();

		for (int i = 0; i < firstDateInYear; i++) {
			System.out.print("   ");
		}

		// array of days in each month 1-12
		int array[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int eventCheck[] = new int[array[localdate.getMonth().getValue() - 1]];
		for (int i = 0; i < eventCheck.length; i++) {
			eventCheck[i] = 0;
		}
		LocalDate startDayOfMonth = localdate.withDayOfMonth(1);

		if (localdate.isLeapYear()) {
			array[1] += 1;
		}

		// Check the events date in a month
		for (int i = 0; i < listevent.size(); i++) {
			String dayInWeek = listevent.get(i).getTimeInterval().getTimePerWeek();
			if (dayInWeek.equals(" ")) {
				// one time event only need to check its date with today
				LocalDate nextDay = startDayOfMonth;
				for (int j = 0; j < eventCheck.length; j++) {
					if (nextDay.compareTo(listevent.get(i).getTimeInterval().getStartDate()) == 0) {
						eventCheck[listevent.get(i).getTimeInterval().getStartDate().getDayOfMonth()] = 1;

					}
					nextDay = nextDay.plusDays(1);
				}

			} else {
				int timePerWeek = dayInWeek.length();
				int[] arr = new int[timePerWeek];
				ArrayList<LocalDate> totalDates = new ArrayList<>(); // all the date in a time period
				ArrayList<LocalDate> totalDates1 = new ArrayList<>();
				for (int j = 0; j < arr.length; j++) {
					if (dayInWeek.charAt(j) == 'M') {
						arr[j] = 1;
					} else if (dayInWeek.charAt(j) == 'T') {
						arr[j] = 2;
					} else if (dayInWeek.charAt(j) == 'W') {
						arr[j] = 3;
					} else if (dayInWeek.charAt(j) == 'R') {
						arr[j] = 4;
					} else if (dayInWeek.charAt(j) == 'F') {
						arr[j] = 5;
					} else if (dayInWeek.charAt(j) == 'A') {
						arr[j] = 6;
					} else {
						arr[j] = 7;
					}
					// System.out.println("Got here test regular: "+arr[j]);
				} // end for

				LocalDate startDate = listevent.get(i).getTimeInterval().getStartDate();
				LocalDate endDate = listevent.get(i).getTimeInterval().getEndDate();
				while (!startDate.isAfter(endDate)) {
					totalDates.add(startDate);
					startDate = startDate.plusDays(1);
				} // end while

				// get days by the time they appear in a week
				for (int j = 0; j < totalDates.size(); j++) {
					for (int a = 0; a < arr.length; a++) {
						if (totalDates.get(j).getDayOfWeek().getValue() == arr[a]) {
							LocalDate day = totalDates.get(j);
							totalDates1.add(day);
						}
					}
				} // end for loo

				ArrayList<LocalDate> listOfEventInMonth = new ArrayList<>();
				for (int j = 0; j < totalDates1.size(); j++) {
					if (totalDates1.get(j).getMonthValue() == localdate.getMonthValue()) {
						LocalDate day1 = totalDates1.get(j);
						listOfEventInMonth.add(day1);
					}
				}

				// compare the series of events with a specific date to see if that day has any
				// event
				LocalDate nextDay = startDayOfMonth;
				for (int k = 0; k < eventCheck.length; k++) {
					for (int j = 0; j < listOfEventInMonth.size(); j++) {
						if (nextDay.compareTo(listOfEventInMonth.get(j)) == 0) {
							eventCheck[listOfEventInMonth.get(j).getDayOfMonth() - 1] = 1;
						}
					}
					nextDay = nextDay.plusDays(1);
				}
			} // end else clause
		}

		// find today
		for (int i = 0; i < array[localdate.getMonthValue() - 1]; i++) {
			if (eventCheck[i] == 1) {
				System.out.printf("{%2d} ", (i + 1));
			} else {
				System.out.printf("%2d ", (i + 1));
			}

			if (((i + 1 + firstDateInYear) % 7 == 0 || i == array[localdate.getMonthValue() - 1])) {
				System.out.println();
			}
		}
		System.out.println("\nCurrent Month is: " + localdate.getMonth());
	}

	/*
	 * readFile() function will work as an IOFile, it will read the information of
	 * events in the event.txt and store in an ArrayList<Evenet> Parameter: fileName
	 * , the event.txt
	 */
	public boolean readFile(String fileName) {
		try {
			FileReader file = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(file);
			String line = null;

			while ((line = buff.readLine()) != null) {
				int count = 0; // this count help keep increasing and decreasing 1 to help
				// the program to read the file
				Event event = new Event();
				if (line.length() == 0) {
					break;
				}
				if (count == 0) {
					String eventName = line;
					event.setName(eventName);
					count += 1;
				}

				if (count == 1) {
					line = buff.readLine();
					event.getTimeInterval().setSchedule(line);
					String schedule = line;
					char firstLetter = schedule.charAt(0);

					if (!Character.isDigit(firstLetter)) {
						String[] splitStr = schedule.split(" ");
						String startDay = splitStr[3];
						String endDay = splitStr[4];
						String startTime = splitStr[1]; // string
						String endTime = splitStr[2]; // string
						String[] splitStartDay = startDay.split("/");

						event.getTimeInterval().setTimePerWeek(splitStr[0]);
						event.getTimeInterval().setStartDate(Integer.parseInt(splitStartDay[2]),
								Integer.parseInt(splitStartDay[0]), Integer.parseInt(splitStartDay[1]));
						String[] splitEndDay = endDay.split("/");
						event.getTimeInterval().setEndDate(Integer.parseInt(splitEndDay[2]),
								Integer.parseInt(splitEndDay[0]), Integer.parseInt(splitEndDay[1]));
						event.getTimeInterval().setStartTime(startTime);
						event.getTimeInterval().setEndTime(endTime);
					} else {
						String[] splitStr = schedule.split(" ");
						String startDay = splitStr[0];
						String startTime = splitStr[1]; // string
						String endTime = splitStr[2]; // string
						String[] splitStartDay = startDay.split("/");

						event.getTimeInterval().setTimePerWeek(" ");
						event.getTimeInterval().setStartTime(startTime);
						event.getTimeInterval().setEndTime(endTime);

						event.getTimeInterval().setStartDate(Integer.parseInt(splitStartDay[2]),
								Integer.parseInt(splitStartDay[0]), Integer.parseInt(splitStartDay[1]));
						event.getTimeInterval().setEndDate(Integer.parseInt(splitStartDay[2]),
								Integer.parseInt(splitStartDay[0]), Integer.parseInt(splitStartDay[1]));
					}
					listevent.add(event);
				}
			}
		} catch (Exception e) {
			System.out.println("Error.. We can't load the file ");
			e.printStackTrace();
			System.exit(101);
		}

		if (!listevent.isEmpty()) {
			System.out.println("Loading is done");
			System.out.println("Number of events in the list: " + listevent.size());
			return true;
		} else {
			System.out.println("Failed to load the file");
			return false;
		}
	}// End method readFile()

	/*
	 * Here is the menu of avtivities
	 */
	public void menu() {
		System.out.println("\n\t************************************************");
		System.out.println("\t*Menu of Calendar             	               *");
		System.out.println("\t*[V]iew by                                     *");
		System.out.println("\t*[C]reate                                      *");
		System.out.println("\t*[G]o to                                       *");
		System.out.println("\t*[E]vent list                                  *");
		System.out.println("\t*[D]elete                                      *");
		System.out.println("\t*[Q]uit                                        *");
		System.out.println("\t* Please enter option as v, c, g, e, d, or q   *");
		System.out.println("\t************************************************");
	}

	/*
	 * controller function will reduce codes from MyCalendartester This will keep
	 * track of the activites by switch and menu()
	 */
	public void controller(Scanner scan) {
		boolean status = false;
		do {
			menu();
			String option = scan.nextLine();
			option = option.toLowerCase();
			while (!option.equals("v") && !option.equals("c") && !option.equals("g") && !option.equals("e")
					&& !option.equals("d") && !option.equals("q")) {
				System.out.println("Invalid option input\nPlease Enter again: ");
				option = scan.nextLine();
				option = option.toLowerCase();
			}

			switch (option) {
			case "v": {
				LocalDate today = LocalDate.now();
				System.out.println("[D]ay view or [M]onth view?");
				String dayOrMonth = scan.nextLine();
				dayOrMonth = dayOrMonth.toLowerCase();
				while (!dayOrMonth.equals("d") && !dayOrMonth.equals("m")) {
					System.out.println("Invalid option input\nPlease Enter again: ");
					dayOrMonth = scan.nextLine();
					dayOrMonth = dayOrMonth.toLowerCase();
				}

				if (dayOrMonth.equals("d")) {
					System.out.println("Today is: " + today);
					todayEvents(scan, today);
					boolean status1 = false;
					do {
						System.out.println("Select: [P]revious, [N]ext or return [M]ain menu? ");
						String option1 = scan.nextLine();
						option1 = option1.toLowerCase();
						while (!option1.equals("p") && !option1.equals("n") && !option1.equals("m")) {
							System.out.println("Invalid option input\nPlease Enter again: ");
							option1 = scan.nextLine();
							option1 = option1.toLowerCase();
						}

						if (option1.equals("n") && dayOrMonth.equals("d")) {
							LocalDate next = today.plusDays(1);
							System.out.println("Next day is: " + next);
							todayEvents(scan, next);
							today = next;
						} else if (option1.equals("p") && dayOrMonth.equals("d")) {
							LocalDate previous = today.minusDays(1);
							today.minusDays(1);
							System.out.println("Yesterday is: " + previous);
							todayEvents(scan, previous);
							today = previous;
						} else if (option1.equals("m") && dayOrMonth.equals("d")) {
							System.out.println("You decided to go back to menu!");
							status1 = !status1;
						}
					} while (!status1);
				} else {
					MonthCalendar(today);
					boolean status1 = false;
					do {
						System.out.println("Select: [P]revious, [N]ext or return [M]ain menu? ");
						String option1 = scan.nextLine();
						option1 = option1.toLowerCase();
						while (!option1.equals("p") && !option1.equals("n") && !option1.equals("m")) {
							System.out.println("Invalid option input\nPlease Enter again: ");
							option1 = scan.nextLine();
							option1 = option1.toLowerCase();
						}

						if (option1.equals("n")) {
							LocalDate next = today.plusMonths(1);
							MonthCalendar(next);
							today = next;
						} else if (option1.equals("p")) {
							LocalDate previous = today.minusMonths(1);
							MonthCalendar(previous);
							today = previous;
						} else if (option1.equals("m")) {
							System.out.println("You decided to go back to menu!");
							status1 = !status1;
						}
					} while (!status1);
				}

				break;
			}

			case "c": {
				createEvent(scan);
				break;
			}

			case "g": {
				gotodate(scan);
				break;
			}

			case "e": {
				printEventlist();
				break;
			}

			case "d": {
				deleteOpt(scan);
				break;
			}

			case "q": {
				System.out.println("Good Bye!");
				writeFile();
				status = !status;
				break;
			}

			}
		} while (!status);
	}

	/*
	 * printEventlist is the function of ativity Event List: print all event in
	 * order
	 */
	public void printEventlist() {
		if (listevent.size() == 0) {
			System.out.println("Empty calendar, no Events to show");
			return;
		} else {
			Collections.sort(listevent, new EventSorting());
			for (int i = 0; i < listevent.size(); i++) {
				System.out.println(listevent.get(i).getName() + "_______");
				System.out.print(listevent.get(i).getTimeInterval().getTimePerWeek() + "  "
						+ listevent.get(i).getTimeInterval().getStartTime() + "  ");
				System.out.print(listevent.get(i).getTimeInterval().getEndTime() + "  "
						+ listevent.get(i).getTimeInterval().getStartDate());
				System.out.println("  " + listevent.get(i).getTimeInterval().getEndDate());
			}
		}
	}

	/*
	 * gotodate is the function that will ask the user for a date nd then print that
	 * date as view DAY activity Go to
	 */
	public void gotodate(Scanner scan) {
		System.out.println("Please enter the date you want to see(MM/DD/YYYY): ");
		String holdDay = scan.nextLine();
		String split[] = holdDay.split("/");
		LocalDate local1 = LocalDate.of(Integer.parseInt(split[2]), Integer.parseInt(split[0]),
				Integer.parseInt(split[1]));
		todayEvents(scan, local1);
	}

	/*
	 * createEvent function will help the user to create a new event and check if
	 * that new event is collapse with the old one or not with a warning before
	 * return to menu
	 */
	public void createEvent(Scanner scan) {
		Event event1 = new Event();
		System.out.println("Please enter event name: ");
		String read = scan.nextLine();
		String temp = read;
		event1.setName(temp);
		System.out.println("Enter date: MM/DD/YYYY: ");
		read = scan.nextLine();
		String ar[] = read.split("/");
		event1.getTimeInterval().setStartDate(Integer.parseInt(ar[2]), Integer.parseInt(ar[0]),
				Integer.parseInt(ar[1]));
		event1.getTimeInterval().setEndDate(Integer.parseInt(ar[2]), Integer.parseInt(ar[0]), Integer.parseInt(ar[1]));
		System.out.println("Please enter start time: HH:MM");
		read = scan.nextLine();
		event1.getTimeInterval().setStartTime(read);
		System.out.println("Please enter end time: HH:MM");
		read = scan.nextLine();
		event1.getTimeInterval().setEndTime(read);
		event1.getTimeInterval().setTimePerWeek(" ");
		listevent.add(event1);
		System.out.println("Number of events in our list: #" + listevent.size());
		System.out.println("Name of created event: #" + listevent.get(listevent.size() - 1).getName());
		System.out.println("Start date #" + listevent.get(listevent.size() - 1).getTimeInterval().getStartDate());
		System.out.println("Start time #" + listevent.get(listevent.size() - 1).getTimeInterval().getStartTime());
		System.out.println("End time: #" + listevent.get(listevent.size() - 1).getTimeInterval().getEndTime());

		// now we check if the time of new event collapse with the old one we already
		// had
		for (int i = 0; i < listevent.size() - 1; i++) {
			if (checkCollapse(listevent.get(i), event1)) {
				System.out.println("The new event is collapsed with old events");
				return;
			}
		}
		System.out.println("The new event isn't collapsed with old events");
	}

	/*
	 * this function check if 2 events are collapse in time and date Event d1 event
	 * want to check Event d2: new input event from createEvent First I get all of
	 * the date from starting to ending dates of an event then I check to take only
	 * the date they appear by checking dayOfWeek then I compare with the newEvent
	 * to see if they are collapsed
	 */
	public boolean checkCollapse(Event d1, Event d2) {
		// d2 will be the new event
		LocalDate begind2 = d2.getTimeInterval().getStartDate();
		LocalDate begind1 = d1.getTimeInterval().getStartDate();
		LocalDate endd1 = d1.getTimeInterval().getEndDate();

		String concat1 = d2.getTimeInterval().getStartTime();
		String strcatt[] = concat1.split(":");
		LocalTime timeA = LocalTime.of(Integer.parseInt(strcatt[0]), Integer.parseInt(strcatt[1]));

		concat1 = d2.getTimeInterval().getEndTime();
		String strcatt1[] = concat1.split(":");
		LocalTime timeB = LocalTime.of(Integer.parseInt(strcatt1[0]), Integer.parseInt(strcatt1[1]));

		concat1 = d1.getTimeInterval().getStartTime();
		String strcatt2[] = concat1.split(":");
		LocalTime timeC = LocalTime.of(Integer.parseInt(strcatt2[0]), Integer.parseInt(strcatt2[1]));

		concat1 = d1.getTimeInterval().getEndTime();
		String strcatt3[] = concat1.split(":");
		LocalTime timeD = LocalTime.of(Integer.parseInt(strcatt3[0]), Integer.parseInt(strcatt3[1]));

		if (begind2.isAfter(begind1) && begind2.isBefore(endd1)) {
			if (!d1.getTimeInterval().getTimePerWeek().equals(" ")) {
				// checking if they have a common day in week
				String dayInWeek = d1.getTimeInterval().getTimePerWeek();

				int timePerWeek = dayInWeek.length();

				int[] arr = new int[timePerWeek];
				for (int j = 0; j < arr.length; j++) {
					if (dayInWeek.charAt(j) == 'M') {
						arr[j] = 1;
					} else if (dayInWeek.charAt(j) == 'T') {
						arr[j] = 2;
					} else if (dayInWeek.charAt(j) == 'W') {
						arr[j] = 3;
					} else if (dayInWeek.charAt(j) == 'R') {
						arr[j] = 4;
					} else if (dayInWeek.charAt(j) == 'F') {
						arr[j] = 5;
					} else if (dayInWeek.charAt(j) == 'A') {
						arr[j] = 6;
					} else {
						arr[j] = 7;
					}
				}

				boolean sameDay = false;
				for (int count = 0; count < arr.length; count++) {
					if (d2.getTimeInterval().getStartDate().getDayOfWeek().getValue() == arr[count]) {
						sameDay = true;
						if ((timeA.isBefore(timeD) && timeA.isAfter(timeC))
								|| (timeB.isBefore(timeD) && timeB.isAfter(timeC))) {
							return sameDay;
						} else {

							return false;
						}
					}
				}
				return false;
			}
		} else if ((begind2.compareTo(begind1) == 0) || (begind2.compareTo(endd1) == 0)) {
			if ((timeA.isBefore(timeD) && timeA.isAfter(timeC)) || (timeB.isBefore(timeD) && timeB.isAfter(timeC))) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/* This function will print what is the date today */
	public void printToday() {
		LocalDate now = LocalDate.now();
		System.out.println("Today date is: " + now);
	}

	/*
	 * This function will check all the event in a day and print it out in order if
	 * the ArrayList is not empty
	 */
	public void todayEvents(Scanner scan, LocalDate now) {
		ArrayList<Event> todayEve = new ArrayList<Event>();

		for (int i = 0; i < listevent.size(); i++) {
			String dayInWeek = listevent.get(i).getTimeInterval().getTimePerWeek();
			if (dayInWeek.equals(" ")) {
				// one time event only need to check its date with today
				if (now.compareTo(listevent.get(i).getTimeInterval().getStartDate()) == 0) {
					Event event1 = listevent.get(i);
					todayEve.add(event1);
				}
			} else {
				int timePerWeek = dayInWeek.length();
				int[] arr = new int[timePerWeek];
				ArrayList<LocalDate> totalDates = new ArrayList<>();
				ArrayList<LocalDate> totalDates1 = new ArrayList<>();
				for (int j = 0; j < arr.length; j++) {
					if (dayInWeek.charAt(j) == 'M') {
						arr[j] = 1;
					} else if (dayInWeek.charAt(j) == 'T') {
						arr[j] = 2;
					} else if (dayInWeek.charAt(j) == 'W') {
						arr[j] = 3;
					} else if (dayInWeek.charAt(j) == 'R') {
						arr[j] = 4;
					} else if (dayInWeek.charAt(j) == 'F') {
						arr[j] = 5;
					} else if (dayInWeek.charAt(j) == 'A') {
						arr[j] = 6;
					} else {
						arr[j] = 7;
					}
				} // end for

				LocalDate startDate = listevent.get(i).getTimeInterval().getStartDate();
				LocalDate endDate = listevent.get(i).getTimeInterval().getEndDate();
				while (!startDate.isAfter(endDate)) {
					totalDates.add(startDate);
					startDate = startDate.plusDays(1);
				} // end while

				// get days by the time they appear in a week
				for (int j = 0; j < totalDates.size(); j++) {
					for (int a = 0; a < arr.length; a++) {
						if (totalDates.get(j).getDayOfWeek().getValue() == arr[a]) {
							LocalDate day = totalDates.get(j);
							totalDates1.add(day);
						}
					}
				} // end for loo

				// compare the series of events with a specific date to see if that day has any
				// event
				for (int j = 0; j < totalDates1.size(); j++) {
					if (now.compareTo(totalDates1.get(j)) == 0) {
						Event event1 = listevent.get(i);
						todayEve.add(event1);
						break;
					}
				}
			} // end else clause
		}

		if (!todayEve.isEmpty()) {
			Collections.sort(todayEve, new EventSorting());
			for (int i = 0; i < todayEve.size(); i++) {
				System.out.println(todayEve.get(i).getName());
				System.out.println(todayEve.get(i).getTimeInterval().getSchedule());
			}
		}
	}

	/*
	 * deleteOpt function will privid the user the ability to delete events
	 * Moreover, I provide a way to return to menu by choosing Q
	 */
	public void deleteOpt(Scanner scan) {
		boolean status1 = false;
		do {
			System.out.println("Please [S]elected or [A]ll  or [D] or [Q]uit  ");
			System.out.println(
					"Hint: S]elected: the user specifies the date and name of an ONE TIME event. The specific one time event will be deleted.\r\n"
							+ "[A]ll: the user specifies a date and all ONE TIME events scheduled on the date will be deleted.\r\n"
							+ "[D]: the user specifies the name of a REGULAR event. The specfied regular event will be deleted.\n"
							+ "[Q] to quit the Deleting mode");
			String option1 = scan.nextLine();
			option1 = option1.toLowerCase();
			while (!option1.equals("s") && !option1.equals("a") && !option1.equals("d") && !option1.equals("q")) {
				System.out.println("Invalid option input\nPlease Enter again: ");
				option1 = scan.nextLine();
				option1 = option1.toLowerCase();
			}

			if (option1.equals("s")) {
				System.out.println("Please enter the specific name of the event you want to delete: ");
				String read = scan.nextLine();
				String name = read;
				System.out.println("Please enter date of the Event you want to delete: Format: MM/DD/YYYY");
				read = scan.nextLine();
				Event sample = new Event();
				if (!listevent.isEmpty()) {
					for (int i = 0; i < listevent.size(); i++) {
						if (name.equals(listevent.get(i).getName())) {
							sample = listevent.get(i);
							break;
						}
					}
				}
				listevent.remove(sample);
				System.out.println("Event " + name + " has been removed from the list!\n");
			} else if (option1.equals("a")) {
				System.out.println("Please enter a date to delete all One time event in that date: format(MM/DD/YYYY)");
				String read = scan.nextLine();
				String[] split = read.split("/");
				ArrayList<Event> container = new ArrayList<Event>();
				Event sample = new Event();
				sample.setName(" ");
				sample.getTimeInterval().setStartDate(Integer.parseInt(split[2]), Integer.parseInt(split[0]),
						Integer.parseInt(split[1]));
				for (int i = 0; i < listevent.size(); i++) {
					if (sample.getTimeInterval().getStartDate()
							.equals(listevent.get(i).getTimeInterval().getStartDate())) {
						Event temp = listevent.get(i);
						container.add(temp);
					}
				}
				if (!listevent.isEmpty()) {
					for (int i = 0; i < container.size(); i++) {
						listevent.remove(container.get(i));
					}

					System.out.println("Events on " + read + " have been removed from the list!\n");
				}

			} else if (option1.equals("q")) {
				System.out.println("You decided to go back to menu!");
				status1 = !status1;
			} else {
				System.out.println("Please enter the specific name of the event you want to delete: ");
				String read = scan.nextLine();
				String name = read;
				Event sample = new Event();
				if (!listevent.isEmpty()) {
					for (int i = 0; i < listevent.size(); i++) {
						if (name.equals(listevent.get(i).getName())) {
							sample = listevent.get(i);
							break;
						}
					}
					listevent.remove(sample);
					System.out.println("Event " + name + " has been removed from the list!\n");
				}
			}
		} while (!status1);
	}

	/*
	 * write (or save) the current events the program has to an .txt file
	 */
	public void writeFile() {
		try {
			FileWriter writer = new FileWriter("output.txt", false);
			BufferedWriter buffer = new BufferedWriter(writer);
			Collections.sort(listevent, new EventSorting());
			for (int i = 0; i < listevent.size(); i++) {
				buffer.write(listevent.get(i).getName() + " ");
				buffer.write(listevent.get(i).getTimeInterval().getTimePerWeek() + " "
						+ listevent.get(i).getTimeInterval().getStartTime() + " ");
				buffer.write(listevent.get(i).getTimeInterval().getEndTime() + " "
						+ listevent.get(i).getTimeInterval().getStartDate() + " "
						+ listevent.get(i).getTimeInterval().getEndDate());
				buffer.newLine();
			}
			buffer.close();
		} catch (IOException en) {
			System.out.println("Error: ");
			en.printStackTrace();
		}
	}
}
