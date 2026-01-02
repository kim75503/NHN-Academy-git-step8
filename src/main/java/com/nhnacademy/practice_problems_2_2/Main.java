package com.nhnacademy.practice_problems_2_2;

public class Main {
    public static void main(String[] args) {
        /*
        ####문제 2-2: 다음 실행 결과가 나오도록 divide() 메서드에 try-catch-finally를 추가하세요.

        요구사항:

        0으로 나누면 "0으로 나눌 수 없습니다" 출력
        정상 계산 시 결과 출력
        예외 발생 여부와 관계없이 "계산 종료" 출력
        실행 결과:

        계산 시작
        0으로 나눌 수 없습니다
        계산 종료
public class Calculator {
    public void divide(int a, int b) {
        System.out.println("계산 시작");

        // TODO: try-catch-finally를 사용하여
        // 1. a / b 계산 및 결과 출력
        // 2. ArithmeticException 발생 시 "0으로 나눌 수 없습니다" 출력
        // 3. 예외 발생 여부와 관계없이 "계산 종료" 출력

        int result = a / b;
        System.out.println("결과: " + result);
    }
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.divide(10, 0);
    }
}
         */

        Calculator calc = new Calculator();
        calc.divide(10, 0);
    }
}
class Calculator {
    public void divide(int a, int b) {
        System.out.println("계산 시작");

        // TODO: try-catch-finally를 사용하여
        // 1. a / b 계산 및 결과 출력
        // 2. ArithmeticException 발생 시 "0으로 나눌 수 없습니다" 출력
        // 3. 예외 발생 여부와 관계없이 "계산 종료" 출력
        int result =0;
        try{
        result = a / b;
        System.out.println("결과: " + result);
        }catch(ArithmeticException e)
        {
        System.out.println("0으로 나눌 수 없습니다");

        }finally{
            System.out.println("계산 종료");
        }   

    
    }
}

