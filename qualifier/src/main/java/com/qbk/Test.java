package com.qbk;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 初始化List的不同方式
 */
public class Test {
    static class User{
        private Integer id;
        private String name;
        User(){}
        User(Integer id,String name){
            this.id = id;
            this.name = name;
        }
        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    public static void main(String[] args) {
        List<User> users = new ArrayList<User>() {{
            add(new User(2, "Van"));
            add(new User(1, "Tom"));
            add(new User(4, "Jesse"));
            add(new User(3, "Randy"));
        }};
        System.out.println(users);
        List<User> fewUsers = new ArrayList<User>(Arrays.asList(
                new User(2, "Van"),
                new User(5, "Lance"),
                new User(6, "Evan"),
                new User(4, "Jesse")
        ));
        System.out.println(fewUsers);
        List<Integer> emptyList = Collections.emptyList();
        //emptyList.add(1);
        System.out.println(emptyList);
    }
}
