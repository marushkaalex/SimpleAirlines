package com.epam.am.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class RandomStringGetter {
    public static final int MAX_LINE_NUMBER = 15;
    public static final String PASSENGER_NAME = "passenger_name.txt";
    public static final String PASSENGER_SURNAME = "passenger_surname.txt";
    public static final String PLANE_MODEL = "plane_model.txt";
    public static final String NO_RESULT = "no result";
    private static Random RND = new Random();

    public static String getString(String fileName) {
        try {
            return runThroughFile(fileName, RND.nextInt(MAX_LINE_NUMBER));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NO_RESULT;
    }

    private static String runThroughFile(String fileName, int lineNumber) throws IOException {
        InputStream is = RandomStringGetter.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int i = 0;
        while (i < lineNumber) {
            br.readLine();
            i++;
        }
        String result = br.readLine();
        if (result != null) {
            return result;
        }
        return runThroughFile(fileName, RND.nextInt(MAX_LINE_NUMBER));
    }
}
