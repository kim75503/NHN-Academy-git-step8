package com.nhnacademy.practice_problems_2_1;

public class Main {
    public static void main(String[] args) {
        /*
        ####문제 2-1: 다음 실행 결과가 나오도록 increment() 메서드를 try-finally로 수정하세요.

        배경 설명: 현재 코드는 음수 입력 시 예외가 발생하면 unlock()이 호출되지 않아 잠금이 해제되지 않는 문제가 있습니다.

        public class Counter {
            private int count = 0;
            private boolean locked = false;

            public void lock() {
                locked = true;
                System.out.println("잠금 시작");
            }

            public void unlock() {
                locked = false;
                System.out.println("잠금 해제");
            }

            public void increment(int value) {
                lock();

              if (value < 0) {
                    throw new IllegalArgumentException("음수는 허용되지 않습니다.");
                }

                count += value;
                System.out.println("현재 값: " + count);

                unlock();
            }
        }
        실행 결과 (음수 입력 시에도 잠금이 해제되어야 함):

        잠금 시작
        잠금 해제
        Exception: 음수는 허용되지 않습니다.
        // increment() 메서드를 try-finally로 수정하세요
        */

        
    }
}
class Counter {
    private int count = 0;
    private boolean locked = false;



    public void lock() {
        locked = true;
        System.out.println("잠금 시작");
    }

    public void unlock() {
        locked = false;
        System.out.println("잠금 해제");
    }

    public void increment(int value) {
       
        lock();
        
         try{
        if (value < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        } 
    

        count += value;
        System.out.println("현재 값: " + count);
    } finally{
    unlock();
    }
       
    }
}