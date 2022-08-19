package com.rule.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

/**
 * 规则（整合了Condition和Action）
 */
@Rule(name = "Hello World rule", description = "Please say hello world",priority = 1)
public class TemperatureRule {

    @Condition
    public boolean evaluate(Facts facts) {
        //拿到温度值
        Integer temperature = facts.get("temperature");
        //判断是否大于27度
        return temperature > 27;
    }

    @Action(order = 1)
    public void execute(Facts facts) throws Exception {
        //得到温度
        Integer temperature = facts.get("temperature");
        System.out.println("测试一：空调运行中，正在持续降温, 当前温度temperature=" + temperature);
        //温度降1
        facts.put("temperature", temperature - 1);
    }
}
