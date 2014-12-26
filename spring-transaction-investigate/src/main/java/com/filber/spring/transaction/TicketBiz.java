package com.filber.spring.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("ticketBiz")
@Transactional(value = "ticketTxManager",propagation=Propagation.NESTED)
public class TicketBiz {

	public static void randomError() {
		throw new RuntimeException();
	}
	
	@Transactional(value = "ticketTxManager",propagation=Propagation.NESTED)	
	public void queryTicket() {
		randomError();
		System.out.println("queryTicket");
	}

	public void bookTicket() {
		System.out.println("bookTicket");
	}

	public void purchaseTicket() {
		System.out.println("purchaseTicket");
	}

	public void issueTicket() {
		System.out.println("issueTicket");
	}
}
