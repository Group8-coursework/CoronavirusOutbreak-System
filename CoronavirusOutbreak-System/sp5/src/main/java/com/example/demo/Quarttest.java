package com.example.demo;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import com.example.demo.Tellingtime;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class Quarttest extends Thread {
      public void run(){
 //   public static void main(String[] args) {
        try {
            //创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //定义一个JobDetail
            JobDetail job = newJob(Tellingtime.class) //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                .withIdentity("job1", "group1") //定义name/group
                .usingJobData("name", "quartz") //定义属性
                .build();
          //定义一个Trigger
            Trigger trigger = newTrigger().withIdentity("trigger1", "group1") //定义name/group   
                .withSchedule(CronScheduleBuilder.cronSchedule("0 10 12 ? * *")) //规定每天20；50爬虫一次
                .build();
            
            mysql.updatechartchina();
            mysql.deletechartchina();
            mysql.chartchina();
          
            
            mysql.updatechartworld();
            mysql.deletechartworld();
            mysql.chartworld();

            //加入这个调度
            scheduler.scheduleJob(job, trigger);

            //start scheduler
            scheduler.start();

            //运行一段时间后关闭
           // Thread.sleep(10000);
            //scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}