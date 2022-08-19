package com.rule.rule;

/**
 * rule_1
 *
 * @author tallon
 * @version v1.0.0
 * @date 2022-08-18 10:29
 */

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

/**
 * 规则（整合了Condition和Action）
 */
@Rule(name = "体验课规则", description = "单次判断，是否为体验课", priority = 1)
public class BasicClassRule {

    @Condition
    public boolean evaluate(@Fact("isBasic") boolean isBasic) {
        return isBasic;
    }

    @Action(order = 1)
    public void execute_1(Facts facts) {
        System.out.println("我是体验课执行的第一条方法");
        facts.put("isBasic", false);
    }

    @Action(order = 2)
    public void execute_2(Facts facts) {
        System.out.println("我是体验课执行的第二条方法");
    }
}