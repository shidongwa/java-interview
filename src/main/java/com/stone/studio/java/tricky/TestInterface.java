package com.stone.studio.java.tricky;

interface IFruit {
    String TYPE = "Apple";
}

class Fruit implements IFruit {

}

/**
 * 任何interface中声明的变量都是public static final的
 */
public class TestInterface {

    public static void main(String[] args) {
        // Apple
        System.out.println(Fruit.TYPE);
    }
}
