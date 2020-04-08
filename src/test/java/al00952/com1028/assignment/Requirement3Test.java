package al00952.com1028.assignment;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class Requirement3Test {

	@Test
	public void requirement3Test() {
		BaseQuery baseQuery = new BaseQuery("root", "password123");
		try {
			Requirement3 requirement3 = new Requirement3();
			ResultSet results = baseQuery.customSQLstatement("SELECT customers.customerName, customers.customerNumber, orderDetails.orderNumber, SUM(orderDetails.priceEach * orderDetails.quantityOrdered) AS orderTotal FROM customers INNER JOIN orders ON orders.customerNumber = customers.customerNumber INNER JOIN orderDetails ON orderDetails.orderNumber = orders.orderNumber GROUP BY orderNumber HAVING orderTotal > 25000");
			
			StringBuffer buffer = new StringBuffer();
			
			while (results.next()) {
				String customerName = results.getString("customers.customerName");
				int customerNumber = results.getInt("customers.customerNumber");
				int orderNumber = results.getInt("orderDetails.orderNumber");
				double orderTotal = results.getDouble("orderTotal");
				
				buffer.append("CustomerNumber: ");
				buffer.append(customerNumber);
				buffer.append("  ");
				buffer.append("OrderNumber: ");
				buffer.append(orderNumber);
				buffer.append("  ");
				buffer.append("OrderTotal: ");
				buffer.append(String.format("%1$8.2f", orderTotal));
				buffer.append("  ");
				buffer.append("CustomerName: ");
				buffer.append(customerName);
				buffer.append("\n");
			}
			
			assertEquals(buffer.toString(), requirement3.printMatchingRequirement());
			
			System.out.println("Expected Results:\n------------------------------------");
			System.out.println(buffer);
			System.out.println("Actual Results:\n------------------------------------");
			System.out.println(requirement3.printMatchingRequirement());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}