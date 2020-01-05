package com.stone.studio.java.tricky;

class AbstractFruit {
    protected static String TYPE = "Apple";
}

class Fruit2 extends AbstractFruit {

}

/**
 * AbstractFruit 中声明的静态变量会被子类继承
 */
public class TestClassInheritance {

    public static void main(String[] args) {
        // Apple
        System.out.println(Fruit2.TYPE);
        System.out.println(AbstractFruit.TYPE);
    }
}
