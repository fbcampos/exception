package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entitites.Reservations;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try{
			System.out.print("Room number: ");
			Integer roomNumber = sc.nextInt();
			System.out.print("CheckIn date (dd/mm/yyyy): ");
			Date checkin = sdf.parse(sc.next());
			System.out.print("CheckOut date (dd/mm/yyyy): ");
			Date checkout = sdf.parse(sc.next());		
			
			Reservations reservation = new Reservations(roomNumber, checkin, checkout);
			System.out.println("Reservation: " + reservation.toString());
			
			System.out.println();
			System.out.print("Enter the data to update the reservation: ");		
			System.out.print("CheckIn date (dd/mm/yyyy): ");
			checkin = sdf.parse(sc.next());
			System.out.print("CheckOut date (dd/mm/yyyy): ");
			checkout = sdf.parse(sc.next());
			
			reservation.updateDates(checkin, checkout);
			System.out.println("Reservation: " + reservation.toString());
		}
		catch(ParseException e){
			System.out.println("Invalid date format!");			
		}
		catch(DomainException e){
			System.out.println("Error in reservation: " + e.getMessage());			
		}
		catch(RuntimeException e){
			System.out.println("Unexpected Error!");
		}
		
		sc.close();	

	}

}
