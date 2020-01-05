package com.stone.studio.java.tricky;

public class TestPlus {

    public static void main(String[] args) {
        int i = 10 + +11 - -12 + +13 - -14 + +15;
        // 75, 加减号不能和正负号连起来写，否则有编译错误
        System.out.println(i);
    }
}
