# SStep 8: exception processing & User definitions

[← 이전: Step 7](step_07.md) | [목차](00.overview.md) | [다음: Step 9 →](step_09.md)

---

**사전 지식**:
- Step 7 내용: enum, 확장된 Todo 클래스
- try-catch 기본 구조

**학습 목표**:
- try-catch-finally 구조 이해
- 사용자 정의 Exception 클래스 생성
- 적절한 예외 처리 전략 적용

**핵심 내용**:
- `try-catch-finally` 구문
- `throws` 선언
- 사용자 정의 Exception
- try-with-resources 패턴

**실습 과제**:
1. `TodoNotFoundException` 정의
2. `InvalidInputException` 정의
3. 예외 발생 시 로그 기록 및 사용자 안내

**산출물**:
- [ ] 사용자 정의 Exception 클래스 구현
- [ ] 적절한 예외 처리 적용
- [ ] 예외 발생 시 로그 기록

**참고 자료**:

> 📚 분류 기준: [Java Basic](https://kizoo.gitlab.io/archive/javabasic/)

| 분류 | 주제 | 링크 |
|------|------|------|
| [Essential Classes](https://kizoo.gitlab.io/archive/javabasic/#essential-java-classes) | Exceptions | [Oracle: Exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html) |
| [Essential Classes](https://kizoo.gitlab.io/archive/javabasic/#essential-java-classes) | Exception Handling | [Baeldung: Exception Handling](https://www.baeldung.com/java-exceptions) |

---

## 학습 자료

<details>
<summary><strong>📘 예외(Exception) 처리 개념</strong></summary>

**예외란?**
- 프로그램 실행 중 발생하는 **비정상적인 상황**
- 처리하지 않으면 프로그램이 중단됨

**예외의 종류**:
| 종류 | 설명 | 예시 |
|------|------|------|
| **Checked Exception** | 컴파일 시 체크, 반드시 처리 필요 | `IOException`, `SQLException` |
| **Unchecked Exception** | 런타임에 발생, 처리 선택적 | `NullPointerException`, `NumberFormatException` |
| **Error** | 심각한 오류, 복구 불가 | `OutOfMemoryError` |

**try-catch 기본 구조**:
```java
try {
    // 예외가 발생할 수 있는 코드
    int num = Integer.parseInt("abc");
} catch (NumberFormatException e) {
    // 예외 발생 시 실행되는 코드
    System.out.println("숫자 형식이 아닙니다.");
}
```

**try-catch-finally**:
```java
try {
    // 예외 발생 가능 코드
} catch (Exception e) {
    // 예외 처리
} finally {
    // 예외 발생 여부와 관계없이 항상 실행
    // 리소스 정리에 사용
}
```

</details>

<details>
<summary><strong>📘 사용자 정의 Exception</strong></summary>

**TodoNotFoundException**:
```java
public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(String message) {
        super(message);
    }

    public TodoNotFoundException(int id) {
        super("TODO를 찾을 수 없습니다. ID: " + id);
    }
}
```

**InvalidInputException**:
```java
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String field, String value) {
        super(field + " 값이 유효하지 않습니다: " + value);
    }
}
```

**사용 예시**:
```java
public Todo findById(int id) {
    for (Todo todo : todoList) {
        if (todo.getId() == id) {
            return todo;
        }
    }
    throw new TodoNotFoundException(id);
}
```

</details>

<details>
<summary><strong>📘 try-with-resources</strong></summary>

**자동 리소스 관리**:
```java
// try-with-resources: 자동으로 close() 호출
try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
    String line = reader.readLine();
    // ...
} catch (IOException e) {
    log.error("파일 읽기 실패", e);
}
// reader.close() 자동 호출됨
```

**AutoCloseable 인터페이스**:
- try-with-resources에서 사용하려면 `AutoCloseable` 구현 필요
- `BufferedReader`, `FileInputStream` 등 I/O 클래스들은 이미 구현됨

</details>

<details>
<summary><strong>📘 예외 처리 Best Practices</strong></summary>

**1. 구체적인 예외 처리**:
```java
// ❌ 너무 넓은 범위
try {
    // ...
} catch (Exception e) {
    // 모든 예외를 같은 방식으로 처리
}

// ✅ 구체적인 예외 처리
try {
    // ...
} catch (NumberFormatException e) {
    log.warn("숫자 형식 오류: {}", e.getMessage());
} catch (DateTimeParseException e) {
    log.warn("날짜 형식 오류: {}", e.getMessage());
}
```

**2. 예외 로깅**:
```java
try {
    // ...
} catch (IOException e) {
    log.error("파일 처리 중 오류 발생", e);  // 스택 트레이스 포함
    throw new RuntimeException("파일 처리 실패", e);
}
```

**3. 예외 전환**:
```java
public void save(Todo todo) {
    try {
        fileRepository.write(todo);
    } catch (IOException e) {
        throw new TodoSaveException("TODO 저장 실패: " + todo.getTitle(), e);
    }
}
```

</details>

---

## 연습 문제

### 연습: try-catch 기본

**문제 1**: 다음 코드에 예외 처리를 추가하세요.

```java
String input = "abc";
int number = Integer.parseInt(input);
System.out.println("결과: " + number);
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
String input = "abc";
try {
    int number = Integer.parseInt(input);
    System.out.println("결과: " + number);
} catch (NumberFormatException e) {
    System.out.println("숫자 형식이 아닙니다: " + input);
}
```

</details>

---

**문제 2**: try-catch-finally 구조에서 finally 블록은 언제 실행되나요?

<details>
<summary><strong>정답 보기</strong></summary>

**finally 블록은 예외 발생 여부와 관계없이 항상 실행됩니다.**

- try 블록이 정상 완료되어도 실행
- catch 블록이 실행되어도 실행
- 리소스 정리(파일 닫기, DB 연결 해제 등)에 주로 사용

</details>

---

**문제 2-1**: 다음 실행 결과가 나오도록 `increment()` 메서드를 try-finally로 수정하세요.

**배경 설명**:
현재 코드는 음수 입력 시 예외가 발생하면 `unlock()`이 호출되지 않아 잠금이 해제되지 않는 문제가 있습니다.

```java
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
```

**실행 결과** (음수 입력 시에도 잠금이 해제되어야 함):
```
잠금 시작
잠금 해제
Exception: 음수는 허용되지 않습니다.
```

```java
// increment() 메서드를 try-finally로 수정하세요
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public void increment(int value) {
    lock();

    try {
        if (value < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다.");
        }

        count += value;
        System.out.println("현재 값: " + count);

    } finally {
        unlock();  // 예외 발생 여부와 관계없이 항상 실행
    }
}
```

**설명**: finally 블록은 예외 발생 여부와 관계없이 항상 실행되므로, 잠금이 항상 해제됩니다.

</details>

---

**문제 2-2**: 다음 실행 결과가 나오도록 `divide()` 메서드에 try-catch-finally를 추가하세요.

**요구사항**:
- 0으로 나누면 "0으로 나눌 수 없습니다" 출력
- 정상 계산 시 결과 출력
- 예외 발생 여부와 관계없이 "계산 종료" 출력

**실행 결과**:
```
계산 시작
0으로 나눌 수 없습니다
계산 종료
```

```java
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
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public void divide(int a, int b) {
    System.out.println("계산 시작");

    try {
        int result = a / b;
        System.out.println("결과: " + result);
    } catch (ArithmeticException e) {
        System.out.println("0으로 나눌 수 없습니다");
    } finally {
        System.out.println("계산 종료");
    }
}
```

**테스트**:
```java
calc.divide(10, 2);
// 출력:
// 계산 시작
// 결과: 5
// 계산 종료

calc.divide(10, 0);
// 출력:
// 계산 시작
// 0으로 나눌 수 없습니다
// 계산 종료
```

</details>

---

**문제 2-3**: 네트워크 연결을 시뮬레이션하는 다음 코드를 완성하세요. finally를 사용하여 연결이 항상 종료되도록 하세요.

```java
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
}
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public void executeRequest(String request) {
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
```

**테스트**:
```java
executeRequest("GET /users");
// 출력:
// 네트워크 연결됨
// 응답: GET /users
// 네트워크 연결 해제됨

executeRequest("GET ERROR");
// 출력:
// 네트워크 연결됨
// 요청 오류: 요청 실패
// 네트워크 연결 해제됨  ← finally 덕분에 예외 발생해도 연결 해제됨
```

</details>

---

**문제 2-4**: 다음 실행 결과가 나오도록 `processData()` 메서드를 try-finally로 수정하세요.

**배경 설명**:
현재 코드는 음수 데이터가 있으면 예외가 발생하고 `timer.stop()`이 호출되지 않습니다.

```java
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
```

**실행 결과** (음수 데이터가 있어도 타이머가 종료되어야 함):
```
타이머 시작
타이머 종료: 0ms
Exception: 음수 데이터: -2
```

```java
// processData() 메서드를 try-finally로 수정하세요
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public int processData(int[] data) {
    Timer timer = new Timer();
    timer.start();

    try {
        int sum = 0;
        for (int value : data) {
            if (value < 0) {
                throw new IllegalArgumentException("음수 데이터: " + value);
            }
            sum += value;
        }
        return sum;

    } finally {
        timer.stop();  // 예외 발생 여부와 관계없이 항상 실행
    }
}
```

**테스트**:
```java
processData(new int[]{1, 2, 3});
// 출력:
// 타이머 시작
// 타이머 종료: 0ms
// 반환값: 6

processData(new int[]{1, -2, 3});
// 출력:
// 타이머 시작
// 타이머 종료: 0ms  ← finally 덕분에 예외 발생해도 타이머 종료됨
// Exception: 음수 데이터: -2
```

</details>

---

### 연습: 사용자 정의 Exception

**문제 3**: `TodoNotFoundException`을 정의하세요.

요구사항:
- RuntimeException 상속
- 메시지를 받는 생성자
- ID를 받아 메시지를 생성하는 생성자

<details>
<summary><strong>정답 보기</strong></summary>

```java
public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(String message) {
        super(message);
    }

    public TodoNotFoundException(int id) {
        super("TODO를 찾을 수 없습니다. ID: " + id);
    }
}
```

</details>

---

**문제 4**: 사용자 정의 Exception을 사용하는 `findById()` 메서드를 완성하세요.

```java
public Todo findById(int id) {
    for (Todo todo : todoList) {
        if (todo.getId() == id) {
            return todo;
        }
    }
    // TODO를 찾지 못한 경우
    ___(1)___;
}
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
public Todo findById(int id) {
    for (Todo todo : todoList) {
        if (todo.getId() == id) {
            return todo;
        }
    }
    throw new TodoNotFoundException(id);  // (1)
}
```

</details>

---

### 연습: try-with-resources

**문제 5**: 다음 코드를 try-with-resources로 변환하세요.

```java
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
```

<details>
<summary><strong>정답 보기</strong></summary>

```java
try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
    String line = reader.readLine();
} catch (IOException e) {
    e.printStackTrace();
}
// reader.close() 자동 호출
```

</details>

---

[← 이전: Step 7](step_07.md) | [목차](00.overview.md) | [다음: Step 9 →](step_09.md)