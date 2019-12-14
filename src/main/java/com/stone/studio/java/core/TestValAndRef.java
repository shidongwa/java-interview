package com.stone.studio.java.core;

public class TestValAndRef {

    /**
     * java中参数是传值
     * 如果参数是对象，传递的就是对象的地址，对该地址指向的对象的修改对调用方和被调用方都是一致的；
     * 如果在被调用方函数中把参数对象指向的地址换了（重新new对象），那么调用方和被调用方指向的就是不同对象，值是不一致的，该场景同样应用在
     * 不变对象和原生类型参数。
     * @param args
     */
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("好爸爸");
        TestValAndRef test = new TestValAndRef();
        // before change:好爸爸
        System.out.println("before change:" + builder.toString());
        test.change1(builder);
        // after change1:好爸爸
        System.out.println("after change1:" + builder.toString());

        // 重新初始化
        builder = new StringBuilder("好爸爸");
        test.change2(builder);
        // after change2:坏爸爸
        System.out.println("after change2:" + builder.toString());
    }

    public void change1(StringBuilder builder) {
        builder = new StringBuilder("坏爸爸");
    }

    public void change2(StringBuilder builder) {
        builder.replace(0, 1, "坏");
    }
}
