package com.filber.spring.transaction;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

@Service("ticketTxManager")
@Scope("prototype")
public class TicketTransactionManager extends AbstractPlatformTransactionManager{

	@Override
	protected Object doGetTransaction() throws TransactionException {
		System.out.println("Ticket Transaction Manager doGetTransaction");
		return new HashMap();
	}
	
	@Override
	protected void doBegin(Object arg0, TransactionDefinition arg1)
			throws TransactionException {
		System.out.println("Ticket Transaction Manager doBegin");
	}

	@Override
	protected void doCommit(DefaultTransactionStatus arg0)
			throws TransactionException {
		System.out.println("Ticket Transaction Manager doCommit");
	}

	@Override
	protected void doRollback(DefaultTransactionStatus arg0)
			throws TransactionException {
		System.out.println("Ticket Transaction Manager doRollback");
	}
}
