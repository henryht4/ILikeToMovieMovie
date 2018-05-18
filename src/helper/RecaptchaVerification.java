package helper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class RecaptchaVerification {
	
	  public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
	  
	  public static void verify( String gRecaptchaResponse) throws Exception {
	        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
	        		throw new Exception("recaptcha verification failed: gRecaptchaResponse is null or empty");
	        }
	        
	        URL verifyUrl = new URL(SITE_VERIFY_URL);
	        
	        HttpsURLConnection con = (HttpsURLConnection) verifyUrl.openConnection();
	        
	        con.setRequestMethod("POST");
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");
	        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	        
	        String postParams = "secret=" + SecretKey.key + "&response=" + gRecaptchaResponse;
	        
	        con.setDoOutput(true);
	        
	        OutputStream outStream = con.getOutputStream();
	        outStream.write(postParams.getBytes());
	        
	        outStream.flush();
	        outStream.close();
	        
	        int responseCode = con.getResponseCode();
	        System.out.println("responseCode=" + responseCode);
	        
	        InputStream inputStream = con.getInputStream();
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

	        JsonObject jsonObject = new Gson().fromJson(inputStreamReader, JsonObject.class);
	        
	        inputStreamReader.close();
	        
	        System.out.println("Response: " + jsonObject.toString());
	        
	        if (jsonObject.get("success").getAsBoolean()) {
	        		// verification succeed
	        		return;
	        }
	        
	        throw new Exception("recaptcha verification failed: response is " + jsonObject.toString());
	
	    }

	}	
	  
	  
