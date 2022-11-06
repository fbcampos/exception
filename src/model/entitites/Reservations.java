package model.entitites;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservations {
	
	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public Reservations(Integer roomNumber, Date checkin, Date checkout) throws DomainException {
		if (!checkout.after(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Date getCheckin() {
		return checkin;
	}
	
	public Date getCheckout() {
		return checkout;
	}
	
	public Long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public void updateDates(Date checkin, Date checkout) throws DomainException{
		Date now = new Date();
		if(checkin.before(now) || checkout.before(now)){
			throw new DomainException("Reservation dates for updates must be future dates!");
		}
		if(!checkout.after(checkin)){
			throw new DomainException("CheckOut date must be after CheckIn date!");			
		}
		this.checkin = checkin;
		this.checkout = checkout;		
	}
	
	@Override
	public String toString(){
		return "Room " + roomNumber + ", checkIn: " + sdf.format(checkin) + ", checkOut: " + sdf.format(checkout) + ", " + duration() + " nights";
	}
}
