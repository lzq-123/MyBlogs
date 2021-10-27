package com.lzq.demo;

import org.junit.jupiter.api.Test;

import java.util.Objects;

public class Test4 {
    @Override
    public String toString() {
        return "Test4{" +
                "a=" + a +
                ", name='" + name + '\'' +
                '}';
    }

    int a;
    String name;

    public Test4(int a, String name) {
        this.a = a;
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test4 test4 = (Test4) o;
        return a == test4.a && Objects.equals(name, test4.name);

    }
    //    @Override
//    public int hashCode() {
//        return Objects.hash(a, name);
//    }

}
