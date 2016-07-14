import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Main {

	static Map<String, String> values = new HashMap<String, String>();
	static InputStream input = null;
	static Properties prop = new Properties();
	static ScriptEngineManager mgr = new ScriptEngineManager();
	static ScriptEngine engine = mgr.getEngineByName("JavaScript");

	public static void main(String[] args) throws IOException, ScriptException {
		// TODO Auto-generated method stub
		// Currently I am desiging system for individual tax filling and not for Joint/spouse.

		Scanner scan = new Scanner(System.in);

		System.out.println("Hi Welcome to Intuit Tax Calulator(1040EZ)");
		System.out.println("For getting us started, let me ask you some personal information");


		System.out.println("Please enter your first name");
		String first_name = scan.next();
		values.put("Fname", first_name);

		System.out.println("Please enter your last name");
		String last_name = scan.next();
		values.put("Lname", last_name);

		// Rules need to be implemented.
		// Need to check if this is less than 1500$
		System.out.println("So "+first_name+". Now lets check if 1040EZ is the right form for you.");		
		System.out.println("Please enter your Taxable Interest.");
		String field2_taxinterest = scan.next();
		values.put("Field2", field2_taxinterest);
		Methods_1040.VerifyEligible(values);

		if(values.get("Verify").equals("Fail")){
			System.out.println(first_name + ", Looks like 1040EZ is not a right form for you. Your Taxable income is more than 1500$.");
			System.out.println("Please Select another form from menu. Thanks");
		}else{
			System.out.println("So "+first_name+". Now lets get started with the numbers.");

			System.out.println("Please enter your Wage,salaries,and Tips. This should be shown in box 1 of your Form(s) W-2.");
			String field1_wage = scan.next();
			values.put("Field1", field1_wage);		

			System.out.println("Please enter Unemployemnt compensation and Alaska Permanent fund dividends");
			String field3_compensation = scan.next();
			values.put("Field3", field3_compensation);

			System.out.println("Can someone claim you as a dependent, if Yes please enter YES otherwise NO (PLEASE CONSIDER THE CAPS ON)");
			String field5_dependent = scan.next();
			field5_dependent = field5_dependent.toLowerCase();
			if (field5_dependent.equals("yes")){
				values.put("Field5", "Field5_True");
			}else if (field5_dependent.equals("no")){
				values.put("Field5", "Field5_False");
			}

			System.out.println("What was the federal income tax witheld, you can refer to W-2 and 1099");
			String field7_federaltax = scan.next();
			values.put("Field7", field7_federaltax);

			System.out.println("What was your Earned Income Credit(EIC)");
			String field8a_eic = scan.next();
			values.put("Field8", field8a_eic);

			System.out.println("What was your Health care - Individual Responsibilty");
			String field11_healthcare = scan.next();
			values.put("Field11", field11_healthcare);

			scan.close();

			// Rule needed to calculate 
			// Field 4 - Adjusted Gross Salary.
			// Field 6 - Taxable income.
			// Field 9 - Total Payments.
			// Field 10 - Define Tax (Line 10 using the standard Tax Table)
			System.out.println(first_name+" "+last_name+", The details for 1040EZ are as follow:");
			Methods_1040.CalculateAdjustedGrossIncome(values);
			Methods_1040.CalculateDependentAmount(values);
			Methods_1040.CalculateTaxableIncome(values);
			Methods_1040.CalculateTotalPayment(values);
			Methods_1040.LookupTax(values);
			Methods_1040.TotalTax(values);
			Methods_1040.CalculateRefundAndAmount(values);
		}

	}

}
