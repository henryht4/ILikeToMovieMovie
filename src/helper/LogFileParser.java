package helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LogFileParser {

	public static void main(String[] args) {
		try {
			long totalTS = 0;
			long totalTJ = 0;
			int count = 0;
			File file = new File("C:\\Users\\jenny\\Desktop\\CS 122B\\project\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\cs122b\\log.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				totalTS += Long.parseLong(line.split(",")[0].toString());
				totalTJ += Long.parseLong(line.split(",")[1].toString());
				count++;
			}
			double averageTS = (totalTS/count) / 1000000000.0;
			double averageTJ = (totalTJ/count) / 1000000000.0;
			System.out.println("Average TS = "+ averageTS + " seconds");
			System.out.println("Average TJ = "+ averageTJ + " seconds");
			fileReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
