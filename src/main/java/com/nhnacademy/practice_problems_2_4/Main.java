package com.nhnacademy.practice_problems_2_4;

public class Main {
    public static void main(String[] args) {
        /*
        문제 2-4: 다음 실행 결과가 나오도록 processData() 메서드를 try-finally로 수정하세요.

배경 설명: 현재 코드는 음수 데이터가 있으면 예외가 발생하고 timer.stop()이 호출되지 않습니다.

public class Timer {
    private long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
        System.out.println("타이머 시작");
    }

    public void stop() {
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("타이머 종료: " + elapsed + "ms");
    }
}

public int processData(int[] data) {
    Timer timer = new Timer();
    timer.start();

    int sum = 0;
    for (int value : data) {
        if (value < 0) {
            throw new IllegalArgumentException("음수 데이터: " + value);
        }
        sum += value;
    }

    timer.stop();
    return sum;
}
실행 결과 (음수 데이터가 있어도 타이머가 종료되어야 함):

타이머 시작
타이머 종료: 0ms
Exception: 음수 데이터: -2
// processData() 메서드를 try-finally로 수정하세요
         */
        
       System.out.println(processData(new int[]{1, 2, 3}));
        System.out.println(processData(new int[]{1, -2, 3}));
    }


    public static int processData(int[] data) {
        Timer timer = new Timer();
        timer.start();

        int sum = 0;

        try {
            for (int value : data) {
                if (value < 0) {
                    throw new IllegalArgumentException("음수 데이터: " + value);
                }
                sum += value;
            }
            return sum;
        } finally {
            // 예외가 발생하든 말든 무조건 실행됨
            timer.stop();
        }
    }
}


 class Timer {
    private long startTime;

    public void start() {
        startTime = System.currentTimeMillis();
        System.out.println("타이머 시작");
    }

    public void stop() {
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("타이머 종료: " + elapsed + "ms");
    }
}

