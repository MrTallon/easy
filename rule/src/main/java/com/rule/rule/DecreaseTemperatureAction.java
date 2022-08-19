package com.rule.rule;

import org.jeasy.rules.api.Action;
import org.jeasy.rules.api.Facts;

/**
 * 单独写的执行器
 */
public class DecreaseTemperatureAction implements Action {

    /**
     * 开空调后温度降1，then执行的操作
     * @param facts
     */
    @Override
    public void execute(Facts facts) {
        //得到温度
        Integer temperature = facts.get("temperature");
        System.out.println("测试二：空调运行中，正在持续降温, 当前温度temperature=" + temperature);
        //温度降1
        facts.put("temperature", temperature - 1);
    }
}
