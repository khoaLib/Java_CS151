/*Name: Khoa Pham
 * SJSU
 * CS151- Assignment 1 My Calendar
 * Due date: 2/16/2019
 * ID: 013562718
 * */
import java.time.LocalDate;
import java.util.Scanner;

/*for my window I need to use double "\\" instead to connect the text file like below*/
public class MyCalendertester {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		LocalDate today = LocalDate.now();
		MyCalendar calendar = new MyCalendar();
		calendar.TodayCalendar(today);
		calendar.readFile("C:\\Users\\hp\\eclipse-workspace\\CS151_Assignment1_Khoa_Pham\\src\\events.txt");
		calendar.controller(scan);
	}

}
