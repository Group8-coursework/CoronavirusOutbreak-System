package com.example.demo;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.example.demo.Request;

public class Tellingtime implements Job
{
	static String time;
	

    public void execute(JobExecutionContext context)
    throws JobExecutionException {

		//设置时间输出格式
    	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
         time = simpleDateFormat.format(new Date());
		//打印爬虫执行时间
    	Request re=new Request();
    	try {
			re.China("script[ id=getAreaStat]");
			re.world("script[ id=getListByCountryTypeService2true]");			
			 System.out.println("Hello Quartz!"+time);  
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
    }
 
}
