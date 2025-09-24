package org.simplerental;

import java.util.Scanner;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
        //目标：面向对象编程实现智能家居控制系统
        //角色：设备（吊灯，电视机，洗衣机，落地窗。。。）
        // 具备功能：开和关
        // 谁在控制他们：智能控制系统（单例对象），控制调用设备的开和管
        //1.定义设备类：创建设备对象代表家里的设备
        //2.准备数据
        JD[] jds = new JD[4];
        jds[0] = new TV("TV", true);
        jds[1] = new WashMachine("WashMachine", true);
        jds[2] = new Air("TV", false);
        jds[3] = new Lamp("TV", true);

        //3.设计一个开和关的功能（接口实现开关功能）

        //4.创建智能控制系统对象，控制设备的开和关
        SmartHomeControl samrtHomeControl = SmartHomeControl.getInstance();
        //控制电视机
        //samrtHomeControl.control(jds[0]);

        //6.提示用户操作 a.展示全部设备的当前情况
        //b.让用户选择一种操作
        //打印全部设备信息以及开和关的状态

        while (true) {
            samrtHomeControl.printAllstatus(jds);
            System.out.println("Choose the device or Input Exit ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();
            switch (command){
                case "1":
                    samrtHomeControl.control(jds[0]);
                    break;
                case "2":
                    samrtHomeControl.control(jds[1]);
                    break;
                case "3":
                    samrtHomeControl.control(jds[2]);
                    break;
                case "4":
                    samrtHomeControl.control(jds[3]);
                    break;
                case "exit":
                    System.out.println("Exit APP");
                    return;
                default:
                    System.out.println("Error Input ");

            }
        }


    }
}