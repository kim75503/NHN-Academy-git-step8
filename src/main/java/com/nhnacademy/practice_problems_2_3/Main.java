package com.nhnacademy.practice_problems_2_3;

public class Main {
    public static void main(String[] args) {
        /*
        문제 2-3: 네트워크 연결을 시뮬레이션하는 다음 코드를 완성하세요. finally를 사용하여 연결이 항상 종료되도록 하세요.

public class NetworkConnection {
    private boolean connected = false;

    public void connect() {
        connected = true;
        System.out.println("네트워크 연결됨");
    }

    public void disconnect() {
        connected = false;
        System.out.println("네트워크 연결 해제됨");
    }

    public String sendRequest(String request) {
        if (!connected) {
            throw new IllegalStateException("연결되지 않음");
        }
        if (request.contains("ERROR")) {
            throw new RuntimeException("요청 실패");
        }
        return "응답: " + request;
    }
}

// 아래 코드를 완성하세요
public void executeRequest(String request) {
    NetworkConnection network = new NetworkConnection();

    // TODO: try-catch-finally를 사용하여
    // 1. network.connect() 호출
    // 2. network.sendRequest(request) 실행 및 결과 출력
    // 3. 예외 발생 시 "요청 오류" 출력
    // 4. 예외 발생 여부와 관계없이 network.disconnect() 호출
} */    

    executeRequest("GET /users");
    executeRequest("GET ERROR");
        
    
        
    }


    public static void executeRequest(String request) {
        NetworkConnection network = new NetworkConnection();

         try {
            network.connect();
            String result = network.sendRequest(request);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println("요청 오류: " + e.getMessage());
        } finally {
            network.disconnect();
        }
    }
}


 class NetworkConnection {
    private boolean connected = false;

    public void connect() {
        connected = true;
        System.out.println("네트워크 연결됨");
    }

    public void disconnect() {
        connected = false;
        System.out.println("네트워크 연결 해제됨");
    }

    public String sendRequest(String request) {
        if (!connected) {
            throw new IllegalStateException("연결되지 않음");
        }
        if (request.contains("ERROR")) {
            throw new RuntimeException("요청 실패");
        }
        return "응답: " + request;
    }
}

