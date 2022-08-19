package com.rule.service.impl;

import com.rule.controller.TestController;
import com.rule.rule.BasicClassRule;
import com.rule.rule.DecreaseTemperatureAction;
import com.rule.rule.HighTemperatureCondition;
import com.rule.rule.LowTemperatureCondition;
import com.rule.rule.TemperatureRule;
import com.rule.service.TestService;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.JsonRuleDefinitionReader;
import org.jeasy.rules.support.UnitRuleGroup;
import org.jeasy.rules.support.YamlRuleDefinitionReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;

/**
 * impl
 *
 * @author tallon
 * @version v1.0.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    public void singleRule(TestController.EventReq req) {

        // 事实（输入输出值）
        Facts facts = new Facts();
        facts.put("temperature", 27);// 设置温度

        // 条件
        HighTemperatureCondition condition = new HighTemperatureCondition();

        // 动作
        DecreaseTemperatureAction action = new DecreaseTemperatureAction();

        // 规则（包含了条件、动作的引用）
        Rule rule = new RuleBuilder()
                .name("开空调规则")
                .when(condition)// 条件
                .then(action)// 命中条件后的动作
                .build();

        // 规则组
        Rules rules = new Rules();

        // 注册规则
        rules.register(rule);

        // 规则引擎【根据规则的自然顺序(默认为优先级)执行规则，只执行一次】
        RulesEngine rulesEngine = new DefaultRulesEngine();

        // 规则引擎启动（按照规则组、事实开始执行）
        rulesEngine.fire(rules, facts);

        // 结果值
        System.out.println("结果值" + facts.get("temperature"));
    }

    @Override
    public void cycleRule(TestController.EventReq req) {
        // 事实（输入输出值）
        Facts facts = new Facts();
        facts.put("temperature", 30);// 设置温度

        // 规则（整合了Condition和Action）
        TemperatureRule rule = new TemperatureRule();

        // 规则组
        Rules rules = new Rules();

        // 注册规则
        rules.register(rule);

        // 规则引擎（持续执行规则，直到没有满足规则为止）
        RulesEngine rulesEngine = new InferenceRulesEngine();

        // 开始执行
        rulesEngine.fire(rules, facts);
    }

    @Override
    public void basicRule(TestController.EventReq req) {
        Facts facts = new Facts();
        facts.put("isBasic", req.getIsBasic());
        Rules r1 = new Rules();
        r1.register(new BasicClassRule());
        RulesEngine re = new DefaultRulesEngine();
        re.fire(r1, facts);

        // 循环执行，直到不符合条件，类似 while
        RulesEngine r2 = new InferenceRulesEngine();
        r2.fire(r1, facts);
    }

    /*
    UnitRuleGroup：单元规则组是作为一个单元使用的组合规则，要么应用所有规则，要么不应用任何规则。
    ActivationRuleGroup：激活规则组触发第一个适用规则并忽略组中的其他规则。规则首先按照其在组中的自然顺序(默认情况下优先级)进行排序。
    ConditionalRuleGroup：条件规则组将具有最高优先级的规则作为条件，如果具有最高优先级的规则的计算结果为true，那么将触发其余的规则。
     */
    @Override
    public void unitRule(TestController.EventReq req) {

        // 事实（输入输出值）
        Facts facts = new Facts();
        facts.put("temperature", 27);// 设置温度

        // 规则（包含了条件、动作的引用）
        Rule rule_1 = new RuleBuilder()
                .name("开空调规则")
                .when(new HighTemperatureCondition())// 条件
                .then(new DecreaseTemperatureAction())// 命中条件后的动作
                .build();

        Rule rule_2 = new RuleBuilder()
                .name("开空调规则")
                .when(new LowTemperatureCondition())// 条件
                .then(new DecreaseTemperatureAction())// 命中条件后的动作
                .build();


        // 从两个原始规则创建组合规则
        UnitRuleGroup myUnitRuleGroup =
                new UnitRuleGroup("myUnitRuleGroup", "unit of myRule1 and myRule2");
        myUnitRuleGroup.addRule(rule_1);
        myUnitRuleGroup.addRule(rule_2);

        // 像常规规则一样注册组合规则
        Rules rules = new Rules();
        rules.register(myUnitRuleGroup);

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }

    @Override
    public void elRule(TestController.EventReq req) {
        Person p = Person.builder().age(20).build();
        Facts facts = new Facts();
        facts.put("person", p);
        System.out.println("初始化person" + facts.get("person"));
        Rule rule = new MVELRule()
                .name("age rule")
                .description("Check if person's age is > 18 and marks the person as adult")
                .priority(1)
                .when("person.age > 18")
                .then("person.setAdult(true);");
        Rules rules = new Rules();
        rules.register(rule);
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
        System.out.println("结束person" + facts.get("person"));
    }

    @Override
    public void yamlRule(TestController.EventReq req) throws Exception {
        Person p = Person.builder().age(20).build();
        Facts facts = new Facts();
        facts.put("person", p);
        // 当多条rule，mvel缺失条件会报错，但是会正常执行程序
        facts.put("rain", true);
        System.out.println("初始化person" + facts.get("person"));

        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        Rules rules = ruleFactory.createRules(new FileReader("rule/rules.yml"));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
        System.out.println("结束person" + facts.get("person"));
    }

    @Override
    public void jsonRule(TestController.EventReq req) throws Exception {
        Person p = Person.builder().age(20).name("tallon").build();
        Facts facts = new Facts();
        facts.put("person", p);
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new JsonRuleDefinitionReader());
        Rules rules = ruleFactory.createRules(new FileReader("rule/rules.json"));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}
