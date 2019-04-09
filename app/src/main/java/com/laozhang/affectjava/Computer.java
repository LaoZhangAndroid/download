package com.laozhang.affectjava;

/**
 * Created by zzc on 2019/4/9.
 * 建造者模式创建对象：多参数的对象创建使用build模式比较方便
 */
public class Computer {

    private final double weight;
    private final int size;
    private final String color;
    private final String type;
    private final String ram;
    private final String cpu;
    private final String diskSize;
    private final String mouse;


    public static class Builder{
        private double weight = 0.0;
        private int size = 13;
        private String color = "black";
        private String type = "dell";
        private String ram = "8g";
        private String cpu = "core i5";
        private String diskSize = "256g";
        private String mouse = "thinkpad";

        public Builder weight(double weight){
            this.weight = weight;
            return this;
        }

        public Builder size(int size){
            this.size = size;
            return this;
        }

        public Builder color(String color){
            this.color = color;
            return this;
        }

        public Builder type(String type){
            this.type = type;
            return this;
        }

        public Builder ram(String ram){
            this.ram = ram;
            return this;
        }

        public Builder cpu(String cpu){
            this.cpu = cpu;
            return this;
        }

        public Builder diskSize(String diskSize){
            this.diskSize = diskSize;
            return this;
        }

        public Builder mouse(String mouse){
            this.mouse = mouse;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }

    }

    private Computer(Builder builder){
        weight = builder.weight;
        size = builder.size;
        color = builder.color;
        type = builder.type;
        cpu = builder.cpu;
        ram = builder.ram;
        diskSize = builder.diskSize;
        mouse = builder.mouse;
    }
}
