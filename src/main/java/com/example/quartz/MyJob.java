package com.example.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Author:Sphinx
 * Date:2019/03/26 21:56
 * Description:
 */
public class MyJob implements Job {
    //执行任务调度方法
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("quartz MyJob date:" +new Date().getTime());
    }
}
