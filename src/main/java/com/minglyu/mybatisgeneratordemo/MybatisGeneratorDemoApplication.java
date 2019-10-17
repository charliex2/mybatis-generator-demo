package com.minglyu.mybatisgeneratordemo;

import com.minglyu.mybatisgeneratordemo.entities.Coffee;
import com.minglyu.mybatisgeneratordemo.entities.CoffeeCriteria;
import com.minglyu.mybatisgeneratordemo.mappers.auto.CoffeeMapper;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@MapperScan("com.minglyu.mybatisgeneratordemo.mappers.auto")
public class MybatisGeneratorDemoApplication implements ApplicationRunner {

    @Autowired
    private CoffeeMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorDemoApplication.class, args);
    }


    private void generateArtifacts() throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(
                this.getClass().getResourceAsStream("/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    private void playWithArtifacts() {
//        Coffee espresso = new Coffee();
//                .withName("espresso");
        Coffee espresso = new Coffee()
                .withName("Espresso")
                .withPrice(Money.of(CurrencyUnit.of("CNY"), 30L))
                .withCreatedAt(new Date())
                .withUpdatedAt(new Date());

        coffeeMapper.insert(espresso);

        Coffee s = coffeeMapper.selectByPrimaryKey(1L);

        CoffeeCriteria coffeeCriteria = new CoffeeCriteria();
        coffeeCriteria.createCriteria().andNameEqualTo("Espresso");

        List<Coffee> coffees = coffeeMapper.selectByExample(coffeeCriteria);
        coffees.forEach(coffee -> System.out.println(coffee));

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Start ....");
        generateArtifacts();
        playWithArtifacts();
    }
}
