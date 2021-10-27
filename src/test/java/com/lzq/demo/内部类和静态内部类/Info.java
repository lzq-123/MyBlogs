package com.lzq.demo.内部类和静态内部类;

public class Info {
    String name;

    public Info(String name) {
        this.name = name;
    }

    // 非静态内部类在编译完成之后会隐含地保存着一个引用，该引用是指向创建它的外围类
    class InfoShare{
        String name ="saff";
        public void getInfo(){
            System.out.println("内部类 ：" + name);
            // 调用外部类的成员变量 -> Class.this.name
            System.out.println("外部类 ： "+ Info.this.name);
        }
    }
}
