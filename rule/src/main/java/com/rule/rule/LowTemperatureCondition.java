package com.rule.rule;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;

/**
 * 单独写的规则
 */
public class LowTemperatureCondition implements Condition {

    @Override
    public boolean evaluate(Facts facts) {
        //拿到温度值
        Integer temperature = facts.get("temperature");
        //判断是否小于45度
        return temperature < 45;
    }
}
