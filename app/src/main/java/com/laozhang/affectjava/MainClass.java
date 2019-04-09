package com.laozhang.affectjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zzc on 2019/4/9.
 */
public class MainClass {

    public static void main(String[] args) {
//        Computer computer = new Computer.Builder()
//                .color("black")
//                .cpu("i7")
//                .diskSize("512")
//                .ram("16")
//                .type("mac")
//                .build();
//
//        System.out.println(TimeUtil.currentDate());
//        System.out.println(TimeUtil.currentTime());

        Student s1 = new Student("A", 92.0);
        Student s2 = new Student("B", 92.0);
        Student s3 = new Student("C", 80.0);
        Student s4 = new Student("D", 100.0);
        Student s5 = new Student("E", 61.0);
        Student s6 = new Student("F", 99.0);
        Student s7 = new Student("G", 78.0);
        Student s8 = new Student("I", 84.0);


        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);
        students.add(s8);

        Collections.sort(students);  //对list进行排序

        for(Student s : students){
            System.out.println(s.toString());
        }

//        Student[] arr = new Student[students.size()];
//        arr = students.toArray(arr);
//
//        System.out.println("排序前：");
//        for(int i=0; i<arr.length; i++){
//            System.out.println(arr[i].toString());
//        }
//
//
//        Arrays.sort(arr); //对数组进行排序
//
//        System.out.println("排序后：");
//        for(int i=0; i<arr.length; i++){
//            System.out.println(arr[i].toString());
//        }
    }

}
