package com.stone.studio.java.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ChangeFinal {

    /**
     * 通过反射去掉字段modifier的final属性，修改字段值对于Long，StringBuilder都是成功的；
     * String不行，猜测原因跟static final String 编译期已经固化了有关。
     * @param args
     */
    public static void main(String[] args) {
        // name: Sam, birth: 2000000000, testName: default
        Person.print();

        try {
            changeField(Person.class.getDeclaredField("name"), "Sam-2");
            // name: Sam, birth: 2000000000, testName: default
            Person.print();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            changeField(Person.class.getDeclaredField("birth"), 3000000L);
            // name: Sam, birth: 3000000, testName: default
            Person.print();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            changeField(Person.class.getDeclaredField("testName"), new StringBuilder("Success"));
            // name: Sam, birth: 3000000, testName: Success
            Person.print();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void changeField(Field field, Object value) {
        field.setAccessible(true);
        try {
            Field modifier = field.getClass().getDeclaredField("modifiers");
            modifier.setAccessible(true);
            modifier.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            field.set(null, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static class Person {
        private final static String name = "Sam"; // 天下人都一样！！！！
        private final static Long birth = 2000000000L; // long time的出生日期，无敌了
        private final static StringBuilder testName = new StringBuilder("default");

        public static void print() {
            System.out.println(String.format("name: %s, birth: %d, testName: %s", name, birth, testName));
        }
    }
}
