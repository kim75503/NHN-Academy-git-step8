package com.nhnacademy.practice_problems_1;

public class Main {
    public static void main(String[] args) {
        /*
        ###연습: try-catch 기본
        ####문제 1: 다음 코드에 예외 처리를 추가하세요.

        String input = "abc";
        int number = Integer.parseInt(input);
        System.out.println("결과: " + number);
         */

        String input = "abc";
        try {
            int number = Integer.parseInt(input);
            System.out.println("결과: " + number);
        } catch (NumberFormatException e) {
            System.out.println("숫자 형식이 아닙니다: " + input);
        }
         
    }
}
