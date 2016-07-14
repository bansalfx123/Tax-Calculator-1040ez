import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Methods_1040 {

	static InputStream input = null;
	static Properties prop = new Properties();
	static ScriptEngineManager mgr = new ScriptEngineManager();
	static ScriptEngine engine = mgr.getEngineByName("JavaScript");


	public static void VerifyEligible(Map<String, String> values) throws IOException, ScriptException{

		input = new FileInputStream("C:/Users/Mohit/Workspace/TaxCalculator/src/rules.properties");
		prop.load(input);
		String expression = prop.getProperty("Field2_verify");

		for (Map.Entry<String, String> entry : values.entrySet()){
			expression = expression.replace(entry.getKey(), entry.getValue().toString());
		}

		int verify = (int) engine.eval(expression);
		if (verify < 0){
			values.put("Verify", "Pass");
		}else{
			values.put("Verify", "Fail");
		}
	}


	public static void CalculateAdjustedGrossIncome(Map<String, String> values) throws IOException, ScriptException{

		input = new FileInputStream("src/rules.properties");

		prop.load(input);
		String expression = prop.getProperty("Field4");

		for (Map.Entry<String, String> entry : values.entrySet()){
			expression = expression.replace(entry.getKey(), entry.getValue().toString());
		}

		values.put("Field4", String.valueOf(engine.eval(expression)));

		System.out.println("Adjusted Gross Income =" + values.get("Field4"));

	}

	public static void CalculateDependentAmount(Map<String, String> values) throws IOException, ScriptException{

		try {
			input = new FileInputStream("src/rules.properties");
			prop.load(input);

			String expression = prop.getProperty(values.get("Field5"));
			values.put("Field5", String.valueOf(engine.eval(expression)));
		} catch (Exception e) {
			System.out.println("Please see if input entered for Dependent Field. Please consider the caps on");

		}

		System.out.println("DependentAmount =" + values.get("Field5"));


	}	

	public static void CalculateTaxableIncome(Map<String, String> values) throws IOException, ScriptException{

		input = new FileInputStream("src/rules.properties");
		prop.load(input);
		String expression = prop.getProperty("Field6");

		for (Map.Entry<String, String> entry : values.entrySet()){
			expression = expression.replace(entry.getKey(), entry.getValue().toString());
		}

		int field5 = (int) engine.eval(expression);
		if (field5 > 0){
			values.put("Field6", String.valueOf(field5));	
		}else{
			values.put("Field6", "0");
		}

		System.out.println("TaxableIncome =" + values.get("Field6"));

	}

	public static void CalculateTotalPayment(Map<String, String> values) throws IOException, ScriptException{

		input = new FileInputStream("src/rules.properties");
		prop.load(input);
		String expression = prop.getProperty("Field9");

		for (Map.Entry<String, String> entry : values.entrySet()){
			expression = expression.replace(entry.getKey(), entry.getValue().toString());
		}

		values.put("Field9", String.valueOf(engine.eval(expression)));

		System.out.println("Total Payment =" + values.get("Field9"));


	}

	public static void LookupTax(Map<String, String> values){
		values.put("Field10", "5000");
	}

	public static void TotalTax(Map<String, String> values) throws IOException, ScriptException{
		input = new FileInputStream("src/rules.properties");
		prop.load(input);
		String expression = prop.getProperty("Field12");

		for (Map.Entry<String, String> entry : values.entrySet()){
			expression = expression.replace(entry.getKey(), entry.getValue().toString());
		}

		values.put("Field12", String.valueOf(engine.eval(expression)));

		System.out.println("Total Tax =" + values.get("Field12"));

	}


	public static void CalculateRefundAndAmount(Map<String, String> values) throws IOException, ScriptException{

		input = new FileInputStream("src/rules.properties");
		prop.load(input);
		String expression = prop.getProperty("Field13");

		for (Map.Entry<String, String> entry : values.entrySet()){
			expression = expression.replace(entry.getKey(), entry.getValue().toString());
		}

		int field13 = (int) engine.eval(expression);
		if (field13 > 0){
			values.put("Field13", String.valueOf(field13));	
		}else{
			values.put("Field13", "0");
		}

		System.out.println("Refund / Amount Owe =" + values.get("Field13"));


	}

}
