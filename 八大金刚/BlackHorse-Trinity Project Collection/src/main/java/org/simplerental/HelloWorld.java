package org.simplerental;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class HelloWorld {
    public static void main(String[] args) {
//        String result = VerificationCode(4);
//        System.out.println(result);
//        System.out.println(VerificationCode(4));
//
//        int i = 999999999;
//        System.out.println(i);
//        byte j = (byte) i;
//        System.out.println(j);
//        System.out.println(i);
//        loop();
//
//        HelloWorld instance = new HelloWorld();
//
//        System.out.println(instance.sumNumbers(10));
//        System.out.println(instance.jishuSum(10));
//
//
//
//        DeviceInfoReq req = null;
////        log.info("getDeviceInfo req: {}", req);
//
//        //System.out.println(myThinking(1000));
//        System.out.println(qumoFa(1000));

        mapDeviceType(10);

    }


    @Data
    public static class DeviceInfoReq {
        private Long id;
        private Long companyId;
        private Long userId;
        private Long employeeId;
        private Integer deviceType;
        private Long postId;

    }


    public static String VerificationCode(int len) {
        String code = "";

        for (int i = 0; i < len; i++) {
            int num = (int) (Math.random()*10);
            System.out.println(num);
            code = code + num;
        }
        return code;
    }

    public static void yunsuanfu(int a, int b) {
        System.out.println(a-b);
        System.out.println(a+b);
        System.out.println(a / b);
        System.out.println(a % b);
        System.out.println();
    }

    public static void loop() {
        for (int i = 0; i<5; ++i){
            System.out.println(i);
            System.out.println("Helloworld");
        }
    }

    public static Integer sumNumbers(Integer many) {
        Integer sum = 0;
        for (int i =1; i<= many; i++) {
            sum += i;
        }
        return sum;
    }

    public static int jishuSum(int c){
        int sum = 0;
        for (int i = 1; i < c; i++){
            sum += i % 2 == 0 ? 0:i;

        }
        return sum;
    }

    public static String myThinking(int param) {
        int sum = 0;
        //HelloWorld instance = new HelloWorld();
        for (int i =100;i < param;i++ ) {
            int baiwei = i / 100;
            int shiwei = (i - baiwei * 100) / 10;
            int gewei = i - (baiwei * 100 + shiwei * 10);
            int baiwei3 = (int) Math.pow(baiwei ,3);
            int shiwei3 = (int) Math.pow(shiwei ,3);
            int gewei3 =(int) Math.pow(gewei ,3);

            int plusNum =  baiwei3 + shiwei3 + gewei3;
            //Object o = plusNum == i ? i : "不是水仙花数";
            if (plusNum == i) {
                System.out.println(i);
                sum++;

            }
        }
        return "水仙花数一共有" + sum;
    }


    public static String qumoFa(int param) {
        int sum = 0;
        for (int i = 100; i < param; i++) {
            int gewei = i % 10;
            int shiwei = (i/10) % 10;
            int baiwei =  i/100;
            int baiwei3 = (int) Math.pow(baiwei ,3);
            int shiwei3 = (int) Math.pow(shiwei ,3);
            int gewei3 =(int) Math.pow(gewei ,3);
            int plusNum =  baiwei3 + shiwei3 + gewei3;
            if (plusNum == i) {
                System.out.println(i);
                sum++;
            }
        }

        return "水仙花数一共有" + sum;
    }


    public static String mapDeviceType(Integer deviceType) {
        switch (deviceType) {
            case 1:
                System.out.println("门锁");
            case 2:
                System.out.println("电表");
            case 3:
                System.out.println("冷水表");
            case 7:
                System.out.println("热水表");
            default:
                return "未知类型";
        }
    }

}





