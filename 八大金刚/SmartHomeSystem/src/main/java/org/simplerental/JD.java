package org.simplerental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JD implements Switch{
    private String name;
    //状态：开或者关
    private boolean status; //默认false

    @Override
    public void press() {
        //控制当前设备开和关
        status = !status;
        //System.out.println();
    }
}
