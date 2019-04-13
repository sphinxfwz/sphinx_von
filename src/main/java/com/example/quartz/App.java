package com.example.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Primary;

import java.util.Date;

/**
 * Author:Sphinx
 * Date:2019/03/26 22:12
 * Description:
 */
public class App {

    public static void main(String[] args) throws SchedulerException {
        //1.创建Scheduler工厂
        SchedulerFactory sf = new StdSchedulerFactory();
        //2.从工厂中获取调度器实例
        Scheduler scheduler = sf.getScheduler();
        //3.创建JobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withDescription("this is my job")//任务描述
                .withIdentity("ramJob", "ramGroup")//job的name和group
                .build();
        //运行任务的时间，SimpleSchedule类型触发器有效
        long time=System.currentTimeMillis()+3*1000L;//3秒后启动任务
        Date startTime=new Date(time);

        //4.创建Trigger
        //使用SimpleScheduleBuilder或者CronScheduleBuilder
        Trigger t = TriggerBuilder.newTrigger().withDescription("===trigger===")
                .withIdentity("name", "group")
                .startAt(startTime)//默认当前时间启动
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))//每两秒调用一次
                .build();

        //5.注册任务和定时器
        scheduler.scheduleJob(jobDetail,t);

        //6.启动调度器
        scheduler.start();



    }
}
