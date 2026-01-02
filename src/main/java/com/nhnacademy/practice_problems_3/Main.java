package com.nhnacademy.practice_problems_3;

public class Main {
    public static void main(String[] args) {
        /*
        ###연습: 사용자 정의 Exception
        ####문제 3: TodoNotFoundException을 정의하세요.

        요구사항:

        RuntimeException 상속
        메시지를 받는 생성자
        ID를 받아 메시지를 생성하는 생성자

         */


    }
}

class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(String message) {
        super(message);
    }

    public TodoNotFoundException(int id) {
        super("TODO를 찾을 수 없습니다. ID: " + id);
    }
}