package helper;
import java.util.HashMap;
import java.util.Set;

import java.util.Iterator;
import java.util.Map;

class Type
{
	String number;
	String name;
}

public class Constants {
	
	static HashMap<String,String> types=new HashMap<String,String>(); 
	
	public Constants()
	{
		types.put("-7","BIT");
		types.put("-6","TINYINT");
		types.put("-5","BIGINT");
		types.put("-4","LONGVARBINARY");
		types.put("-3","VARBINARY");
		types.put("-2","BINARY");
		types.put("-1","LONGVARCHAR");
		types.put("0"	,"NULL");
		types.put("1"	,"CHAR");
		types.put("2"	,"NUMERIC");
		types.put("3"	,"DECIMAL");
		types.put("4"	,"INTEGER");
		types.put("5"	,"SMALLINT");
		types.put("6"	,"FLOAT");
		types.put("7"	,"REAL");
		types.put("8","DOUBLE");
		types.put("12","VARCHAR");
		types.put("91","DATE");
		types.put("92","TIME");
		types.put("93","TIMESTAMP");
		types.put("1111","OTHER");
	}
	
	public static String getType(String key)
	{
		String type="";
		
		 Set set = types.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	         System.out.println(mentry.getValue());
	         if(key.equals(mentry.getKey()))
	        	 {
	        	 	type=(String)mentry.getValue();
	        	 }
	      }
		return type;
	}
}
