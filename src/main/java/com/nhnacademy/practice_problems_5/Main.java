package com.nhnacademy.practice_problems_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        /*
        ###연습: try-with-resources
        ####문제 5: 다음 코드를 try-with-resources로 변환하세요.

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("file.txt"));
            String line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
         */

        try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
            String line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // reader.close() 자동 호출
    }
}
