package com.epam.am.entity;

import java.io.*;
import java.util.Random;

public class RandomStringGetter {
    public static final int MAX = 30;
    public static final String PASSENGER_NAME = "/passenger_name.txt";
    public static final String PASSENGER_SURNAME = "/passenger_surname.txt";
    public static final String PLANE_MODEL = "/plane_model.txt";
    public static final String NO_RESULT = "no result";
    private static Random RND = new Random();

    public static String getString(String fileName) {
        try {
            File file = new File(RandomStringGetter.class.getResource(fileName).getPath());
            return runThroughFile(file, RND.nextInt(MAX));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NO_RESULT;
    }

    private static String runThroughFile(File file, int lineNumber) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        int i = 0;
        while (i < lineNumber) {
            br.readLine();
            i++;
        }
        String result = br.readLine();
        if (result != null) {
            return result;
        }
        return runThroughFile(file, RND.nextInt(MAX));
    }
}
