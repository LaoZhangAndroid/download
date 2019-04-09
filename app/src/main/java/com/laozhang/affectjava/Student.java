package com.laozhang.affectjava;

import android.support.annotation.NonNull;

/**
 * Created by zzc on 2019/4/9.
 * 实现Comparable接口， 加快排序速度
 */
public class Student implements Comparable<Student>{

    private String name;
    private double score;

    public Student(String name, double score){
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(@NonNull Student o) {
        return (int) (score - o.score);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
