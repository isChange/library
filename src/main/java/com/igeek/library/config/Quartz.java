package com.igeek.library.config;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Quartz {
    @Bean
    public JobDetail newJob(){
        return JobBuilder.newJob(TipsJob.class)
                .withIdentity("job")
                .storeDurably()
                .build();
    }
    @Bean
    public Trigger printTimeJobTrigger(){
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(newJob())
                .withIdentity("trigger")
                .withSchedule(cronScheduleBuilder)
                .build();

    }

    public static void Timing() throws Exception {
        //1. 创建一个JobDetail，把实现了Job接口的类邦定到JobDetail
        JobDetail jobDetail= JobBuilder.newJob(TipsJob.class).withIdentity("job").build();
        //指定时间触发任务
        CronTrigger trigger= TriggerBuilder.newTrigger().withIdentity("trigger").withSchedule(CronScheduleBuilder.cronSchedule("0/1 * * * * ?")).build();
        //创建schedule实例
        StdSchedulerFactory factory = new StdSchedulerFactory();
        //获取调度器实例
        Scheduler scheduler = factory.getScheduler();
        //开启调度器
        scheduler.start();
        //把SimpleTrigger和JobDetail注册给调度器
        scheduler.scheduleJob(jobDetail,trigger);
    }

}
