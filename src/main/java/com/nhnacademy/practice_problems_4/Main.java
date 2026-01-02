package com.nhnacademy.practice_problems_4;

import java.util.ArrayList;
import java.util.List;

public class Main {    
    private static List<Todo> todoList = new ArrayList<>();
    public static void main(String[] args) {
        /*
        ####문제 4: 사용자 정의 Exception을 사용하는 findById() 메서드를 완성하세요.

        public Todo findById(int id) {
            for (Todo todo : todoList) {
                if (todo.getId() == id) {
                    return todo;
                }
            }
            // TODO를 찾지 못한 경우
            ___(1)___;
        }
         */
        todoList.add(new Todo(1, "공부"));
        todoList.add(new Todo(2, "운동"));

        try {
            Todo todo = findById(2);
            System.out.println("찾음: " + todo.getId());
        } catch (TodoNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public static Todo findById(int id) {
    for (Todo todo : todoList) {
        if (todo.getId() == id) {
            return todo;
        }
    }
    throw new TodoNotFoundException(id);  // (1)
}
}
 class Todo {
    private int id;
    private String title;

    public Todo(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }
}