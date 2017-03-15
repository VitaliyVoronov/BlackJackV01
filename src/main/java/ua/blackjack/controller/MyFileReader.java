package ua.blackjack.controller;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Администратор on 09.11.2016.
 */
public class MyFileReader {
    public String fileReader(String fileName) {

        String str = "";

        try (FileReader reader = new FileReader(fileName)) {
            int c;
            while ((c = reader.read()) != -1) {

                str += (char) c;
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return str;
    }
}
