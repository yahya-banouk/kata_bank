package com.miolaZsoft.test;


import com.miolaZsoft.domain.Statement;
import com.miolaZsoft.domain.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintStream;

import static com.miolaZsoft.builderds.DateCreator.date;
import static com.miolaZsoft.builders.TransactionBuilder.aTransaction;
import static com.miolaZsoft.domain.Amount.amountOf;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementTest {
	
	@Mock PrintStream printer;
	@Mock
	Transaction transaction;
	private Statement statement;
	
	@Before
	public void initialise() {
		statement = new Statement();
	}
	
	@Test public void
	should_print_statement_header() {
		statement.printTo(printer);
		
		verify(printer).println(Statement.STATEMENT_HEADER);
	}
	
	@Test public void
	should_print_deposit() {
		statement.addLineContaining(aTransaction()
										.with(amountOf(1000))
										.with(date("10/01/2012")).build(), 
										amountOf(1000));
		
		statement.printTo(printer);
		
		verify(printer).println("10/01/2012 | 1000.00  |          | 1000.00");
	}
	
	@Test public void
	should_print_withdrawal() {
		statement.addLineContaining(aTransaction()
										.with(amountOf(-1000))
										.with(date("10/01/2012")).build(), 
										amountOf(-1000));
		
		statement.printTo(printer);
		
		verify(printer).println("10/01/2012 |          | 1000.00  | -1000.00");
	}
	
	@Test public void
	should_print_two_deposits_in_reverse_order() {
		statement.addLineContaining(aTransaction()
										.with(amountOf(1000))
										.with(date("10/01/2012")).build(), 
										amountOf(1000));
		statement.addLineContaining(aTransaction()
										.with(amountOf(2000))
										.with(date("13/01/2012")).build(), 
										amountOf(3000));
		
		statement.printTo(printer);
		
		InOrder inOrder = Mockito.inOrder(printer);
		inOrder.verify(printer).println("13/01/2012 | 2000.00  |          | 3000.00");
		inOrder.verify(printer).println("10/01/2012 | 1000.00  |          | 1000.00");
		
	}
	
}
