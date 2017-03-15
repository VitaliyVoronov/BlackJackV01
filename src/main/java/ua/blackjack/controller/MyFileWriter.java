package ua.blackjack.controller;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Администратор on 09.11.2016.
 */
public class MyFileWriter {

    public void writeToFile(String string, String fileName){
		MyFileReader fread = new MyFileReader();
		String str = fread.fileReader(fileName);
		String addStr = string;
		StringBuffer sb = new StringBuffer(str);
		String newStr = sb.insert(str.indexOf("<players>")+9,"\n"+ addStr).toString();
		System.out.println("Hiiiii"+newStr);

		try {
			FileWriter fr = new FileWriter(fileName, false);
			fr.write(newStr.trim());
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    }

