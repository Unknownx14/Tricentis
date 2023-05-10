package Academy.Tricentis;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.lang.*;

public class something {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		String myValueString01 ="10.01";
		double myValueDouble01 = Double.valueOf(myValueString01);
		System.out.println(myValueDouble01);
		
		String myValueString02 ="3";
		double myValueDouble02 = Double.valueOf(myValueString02);
		System.out.println(myValueDouble02);
		
		double mySum = myValueDouble01 * myValueDouble02;
		System.out.println(mySum);
		
		
		/*
		String dennis = "0.00000008880000";
		double f = Double.parseDouble(dennis);
		System.out.println(f);
		System.out.println(String.format("%.7f", f));
		System.out.println(String.format("%.9f", new BigDecimal(f)));
		System.out.println(String.format("%.35f", new BigDecimal(f)));
		System.out.println(String.format("%.2f", new BigDecimal(f)));
		*/
		
		/*
		String s = "0.01";
		double d = something.parseDouble(s);
		int i = (int) d;
		
		*/
		/*
		String str = "123.45";
		Double d = Double.valueOf(str); // returns Double object
		System.out.println(d); //123.45
		*/
		
		
		//String something = "10.00";
		
		// convert string to double
		
				//double doubleDecimalValue = Double.parseDouble(something);
		
		/*
		Double valueToDouble = Double.parseDouble(something);
		int valueint = valueToDouble .intValue();
		*/
		
		/*
		 String str = "123.45";
Double d = Double.valueOf(str); // returns Double object
System.out.println(d); //123.45

System.out.println(Double.valueOf("123.45d")); //123.45
System.out.println(Double.valueOf("123.4500d")); //123.45
System.out.println(Double.valueOf("123.45D")); //123.45

*/
		 
		
	}

}
