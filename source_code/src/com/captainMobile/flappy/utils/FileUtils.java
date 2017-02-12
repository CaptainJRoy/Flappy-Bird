package com.captainMobile.flappy.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author joaor
 */
class FileUtils {
    private FileUtils() {
    }
    
    public static String loadAsString(String file) throws FileNotFoundException, IOException {
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String buffer = "";
            while((buffer = reader.readLine()) != null) {
                result.append(buffer + '\n');
            }
            reader.close();
        }
        catch (IOException e) { e.printStackTrace(); }
        return result.toString();
    }
}