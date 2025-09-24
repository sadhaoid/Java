package org.simplerental;

//智能控制系统
public class SmartHomeControl {
    private static final SmartHomeControl samrtHomeControl = new SmartHomeControl();
    private SmartHomeControl(){
    }
    public static SmartHomeControl getInstance(){
        return samrtHomeControl;
    }
    public void control(JD jd){
        System.out.println(jd.getName() + "Currently Status: " + (jd.isStatus() ? true:false));
        System.out.println("开始操作");
        jd.press();
        System.out.println(jd.getName() + "Status: " + (jd.isStatus() ? true:false));
    }

    public void printAllstatus(JD[] jds){
        for (int i = 0; i < jds.length; i++) {
            System.out.println((i+1) + ","+ jds[i].toString() /*+ "Currently Status: " + (jds[i].isStatus() ? true:false)*/);

        }

    }

}
