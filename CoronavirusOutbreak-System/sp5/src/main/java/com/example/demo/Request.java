package com.example.demo;

import com.example.demo.Tellingtime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import com.example.demo.Tellingtime;
public class Request {
	

	private static int countrs=0;
	private static String filename_world=("d:"+ Tellingtime.time+"world.html");
	//private static String outfile_world =( "d:"+Tellingtime.time+"world.csv");
	private static String headtitle = "国家,现存确诊,总计确诊，疑似病例，治愈人数，死亡人数";
	private static List<String> data=new ArrayList<String>();
	private static List<String> datac=new ArrayList<String>();
	private static List<String> data1=new ArrayList<String>();
	private static List<String> datac1=new ArrayList<String>();	
	private static String filename_china=("d:"+Tellingtime.time+"china.html");
	//private static String outfile_china = ("d:"+Tellingtime.time+"china.csv");
	private static String chart_china = ("d:"+"chartofchina.csv");
	private static String chart_world = ("d:"+"chartofworld.csv");
	private static String headtitle2 = "省级,现存确诊,总计确诊，疑似病例，治愈人数，死亡人数";
    


	/**获取中国各省及城市的数据
	 * @throws Exception */
        public void China(String information) throws Exception {
        	  Document document = null;
			try {
				document = Jsoup.connect("https://ncov.dxy.cn/ncovh5/view/pneumonia").get();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
              
              Elements info=((Element)document).getElementsByTag("body");
           
              Elements select = info.select(information);
       
              String tt=select.html();
              List<String> list= new ArrayList<String>();
              	try {
          	   String[] Array= tt.split("\\]\\}\\,\\{");
          	   
          	   for(int i=0;i<Array.length;i++) {
          		   list.add(Array[i]);
          		   //System.out.println(Array);
          	   }
             }catch(Exception e) {
          	   e.printStackTrace();
             }finally {
          	   
             }
             
             int front;
             int later;
            
              for(String text:list) { 
            	   
            	  datac.clear();
            	  datac1.clear();
            	  
            	  front=text.indexOf("provinceName\":");
                  later=text.indexOf("provinceShortName");
                  String provincename=text.substring(front+14, later-2); 
                  datac.add(provincename);
            	  
            	     
              front=text.indexOf("currentConfirmedCount\":");
              later=text.indexOf("confirmedCount");
              String currentconfirmed=text.substring(front+23, later-2); 
              datac.add(currentconfirmed);
              datac1.add(currentconfirmed);
              
              front=text.indexOf("confirmedCount\":");
              later=text.indexOf("suspectedCount");
              String confirmed=text.substring(front+16, later-2); 
              datac.add(confirmed);
              datac1.add(confirmed);
              
              front=text.indexOf("suspectedCount\":");
              later=text.indexOf("curedCount");
              String suspectedcount=text.substring(front+16, later-2); 
              datac.add(suspectedcount);
              datac1.add(suspectedcount);
              
              front=text.indexOf("curedCount\":");
              later=text.indexOf("deadCount");
              String curedcount=text.substring(front+12, later-2); 
              datac.add(curedcount);
              datac1.add(curedcount);
              
              front=text.indexOf("deadCount\":");
              later=text.indexOf("comment");
              String deadcount=text.substring(front+11, later-2); 
              datac.add(deadcount);
              datac1.add(deadcount);
              datac1.add(provincename);
     
              //outputRc();
               updateRc();
              }
        	
        }
	//第一次将爬取数据插入数据库 --china
        private static void outputRc() throws Exception {
            
            String strout = "";
            for (int i = 0; i < datac.size(); i++) {
                strout = strout + datac.get(i) + ",";     //获取data集合中的每一条数据，串成一个字符串
            }
            System.out.println(strout); 
            if (true) {
				//实例化文件输出流 输出到特定爬虫时间的文件夹上
                FileWriter fw = new FileWriter("d:"+Tellingtime.time+"china.csv", true);    
                PrintWriter out = new PrintWriter(fw);                 //实例化打印流
                if (countrs == 0)
                    out.println(headtitle2);                    //输出头标题
                out.println(strout);                        //输出刚刚串起来的strout字符串
                out.close();                                //关闭打印流
                fw.close();                             //关闭输出流
            }
            countrs = countrs + 1;
           // System.out.println(countrs + "  " + strout); 


			/* 插入数据库*/
            if (true) {
                mysql.insertchina(datac);
                
            }     
        }
	//更新数据库-- china
		private static void updateRc() throws Exception {
		            
		            String strout = "";
		            for (int i = 0; i < datac1.size(); i++) {
		                strout = strout + datac1.get(i) + ",";     //获取data集合中的每一条数据，串成一个字符串
		            }
		            System.out.println(strout); 
		            if (true) {
		            	//ʵ�����ļ������ ������ض�����ʱ����ļ�����
		                FileWriter fw = new FileWriter("d:"+Tellingtime.time+"china.csv", true);    
		                PrintWriter out = new PrintWriter(fw);                  //实例化打印流
		                if (countrs == 0)
		                    out.println(headtitle2);                     //输出头标题
		                out.println(strout);                        //输出刚刚串起来的strout字符串
		                out.close();                                //关闭打印流
		                fw.close();                             //关闭输出流
		            }
		            countrs = countrs + 1;
		           // System.out.println(countrs + "  " + strout); 


			/* 插入数据库*/
		            if (true) {
		                mysql.updateChina(datac1);
		               
		            }     
		        }

	/**
	 * 输出html文件
	 */
        public static void htmltoFile2(String htmlString) throws Exception {
			// 获得文件输出流
            FileOutputStream output = new FileOutputStream(filename_china);
			// 以utf-8编码的形式输出到文件（utf-8是中文编码，ISO-8859-1是英文编码）
            output.write(htmlString.getBytes("utf-8"));
            if (output != null) {
                output.close();
            }
        }



	/**获取世界各国的数据
	 * @return
	 * @throws Exception */
        public  void world(String information) throws Exception {
        	
        	
        	 Document document = null;
			try {
				document = Jsoup.connect("https://ncov.dxy.cn/ncovh5/view/pneumonia").get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
             Elements info=((Element)document).getElementsByTag("body");
          
             Elements select1 = info.select(information);
             

             String tt=select1.html();
             List<String> list= new ArrayList<String>();
            try {
            	
         	   String[] Array= tt.split("\\}\\,\\{");
         	   for(int i=0;i<Array.length;i++) {
         		   
         		   list.add(Array[i]);
 		           
         	   }
            }catch(Exception e) {
         	   e.printStackTrace();
            }finally {
            	// System.out.println(list);
            }

            int front;
            int later;
                 
             for(String text:list) {
            
             data.clear();
             data1.clear();
           
             front =text.indexOf("provinceName\":");
             later= text.indexOf("provinceShortName");
             String provincename=text.substring(front+14, later-2); 
             data.add(provincename);
                
             front=text.indexOf("currentConfirmedCount\":");
             later=text.indexOf("confirmedCount");
             String currentconfirmed=text.substring(front+23, later-2); 
             data.add(currentconfirmed);
             data1.add(currentconfirmed);
                   
             front=text.indexOf("confirmedCount\":");
             later=text.indexOf("confirmedCountRank");
             String confirmedcount=text.substring(front+16, later-2); 
             data.add(confirmedcount);
             data1.add(confirmedcount);
                     
             front=text.indexOf("suspectedCount\":");
             later=text.indexOf("curedCount");
             String suspectedcount=text.substring(front+16, later-2); 
             data.add(suspectedcount);
             data1.add(suspectedcount);
             
             front=text.indexOf("curedCount\":");
             later=text.indexOf("deadCount");
              String curedcount=text.substring(front+12, later-2); 
             data.add(curedcount);
             data1.add(curedcount);
               
             front=text.indexOf("deadCount\":");
             later=text.indexOf("deadCountRank");
             String  deadcount=text.substring(front+11, later-2); 
             data.add(deadcount);
             data1.add(deadcount);
            data1.add(provincename);
        
             //outputRs();
             updateRs();
                  
             }                   
        }

	//第一次将爬取数据插入数据库 --world
        private static void outputRs() throws Exception {
          
            String strout = "";
            for (int i = 0; i < data.size(); i++) {
                strout = strout + data.get(i) + ",";      //获取data集合中的每一条数据，串成一个字符串
            }
            System.out.println(strout); 
            if (true) {
				//实例化文件输出流 输出到 以爬虫时间命名的文件夹上
                FileWriter fw = new FileWriter( "d:"+Tellingtime.time+"world.csv", true); 
                PrintWriter out = new PrintWriter(fw);                  //实例化打印流
                if (countrs == 0)
                    out.println(headtitle);                     //输出头标题
                out.println(strout);                        //输出刚刚串起来的strout字符串
                out.close();                                //关闭打印流
                fw.close();                             //关闭输出流
            }
            countrs = countrs + 1;

			/* 插入数据库*/
            if (true) {
                mysql.insertworld(data);
                
            }     
        }

	//更新数据库-- world
        private static void updateRs() throws Exception {
            
            String strout = "";
            for (int i = 0; i < data1.size(); i++) {
                strout = strout + data1.get(i) + ",";      //获取data集合中的每一条数据，串成一个字符串
            }
            System.out.println(strout); 
            if (true) {
				//实例化文件输出流 输出到特定爬虫时间的文件夹上
                FileWriter fw = new FileWriter( "d:"+Tellingtime.time+"world.csv", true); 
                PrintWriter out = new PrintWriter(fw);                   //实例化打印流
                if (countrs == 0)
                    out.println(headtitle);                    //输出头标题
                out.println(strout);                        //输出刚刚串起来的strout字符串
                out.close();                                //关闭打印流
                fw.close();                             //关闭输出流
            }
            countrs = countrs + 1;
        
            /* �������ݿ�*/
            if (true) {
                mysql.updateWorld(data1);
                
            }     
        }

	/**
	 * 输出html文件
	 */
        public static void htmltoFile(String htmlString) throws Exception {

            FileOutputStream output = new FileOutputStream(filename_world);

            output.write(htmlString.getBytes("utf-8"));
            if (output != null) {
                output.close();
            }
        }

	/**这个方法是将InputStream转化为String*/
    public static String convertStreamToString(InputStream is) throws IOException {
        if (is == null){
            return "";
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf8"));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);  
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        reader.close();
        return sb.toString();
    }

}


 class mysql {
	 
	 public static Connection getConnection(){
	   	 Connection conn=null;
	   	 String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	     String DB_URL = "jdbc:mysql://localhost:3306/webone?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

		 // 数据库的用户名与密码，需要根据自己的设置
	      String USER = "root";
	      String PASS = "1286287511";
	      try {
			  // 注册 JDBC 驱动
	    	  	Class.forName(JDBC_DRIVER);
	    	  	//System.out.println("connection success");             
			  // 打开链接
	    	  	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      }catch(Exception e) {
	    	  System.out.println("fail"+e.getMessage());
	      }
	       return conn;
	   	  
	     }


	 //折线图中国数据
	     public static void chartchina() throws SQLException {
	    	Connection conn=getConnection();	    	  
	    	Statement state=conn.createStatement();   //容器
 	        String sql1="INSERT INTO sumc(day,sumcurr, sumconfir,sumsusp, sumcure, sumdead) "
 	        		+ " SELECT '7', sum(currentc),sum(confirmedc),sum(suspectedc),sum(curedc),sum(deadc) FROM china";   //SQL���
 	        state.executeUpdate(sql1);
 	        conn.close();
	     }
	     public static void updatechartchina() throws SQLException {
	    	 Connection conn=getConnection();
	 	       try { 	    	
	 	        String sql="UPDATE sumc SET day=day-1 ";
	 	        PreparedStatement state=conn.prepareStatement(sql); 	    
			    state.executeUpdate(sql);
	 	       }catch(SQLException e) {
	 	    	   e.printStackTrace();
	 	       }finally {
	 	    	  if(conn!=null)
	       		      conn.close();
		     } 	      	        
	    }  
	     public static void deletechartchina() throws SQLException {
	    	 Connection conn=getConnection();
	 	       try { 	    	
	 	        String sql="DELETE  FROM sumc WHERE day=0";
	 	        PreparedStatement state=conn.prepareStatement(sql); 	    
			    state.executeUpdate(sql);
	 	       }catch(SQLException e) {
	 	    	   e.printStackTrace();
	 	       }finally {
	 	    	  if(conn!=null)
	       		      conn.close();
			 } 	      	        
		  }

	 //折线图世界数据
	     public static void chartworld() throws SQLException {
	    	Connection conn=getConnection();	    	  
	    	Statement state=conn.createStatement();   //容器
 	        String sql1="INSERT INTO sumw(day,sumcurr, sumconfir, sumcure, sumdead) "
 	        		+ " SELECT '7',sum(currentc),sum(confirmedc),sum(curedc),sum(deadc) FROM world";   //SQL���
 	        state.executeUpdate(sql1);
 	        conn.close();
	     }
	     public static void updatechartworld() throws SQLException {
	    	 Connection conn=getConnection();
	 	       try { 	    	
	 	        String sql="UPDATE sumw SET day=day-1 ";
	 	        PreparedStatement state=conn.prepareStatement(sql); 	    
			    state.executeUpdate(sql);
	 	       }catch(SQLException e) {
	 	    	   e.printStackTrace();
	 	       }finally {
	 	    	  if(conn!=null)
	       		      conn.close();
		     } 	      	        
	    }  
	     public static void deletechartworld() throws SQLException {
	    	 Connection conn=getConnection();
	 	       try { 	    	
	 	        String sql="DELETE FROM sumW WHERE day=0";	
	 	        PreparedStatement state=conn.prepareStatement(sql); 	    
			    state.executeUpdate(sql);
	 	       }catch(SQLException e) {
	 	    	   e.printStackTrace();
	 	       }finally {
	 	    	  if(conn!=null)
	       		      conn.close();
			 } 	      	        
		  }


	 //插入中国数据到数据库
		 public static boolean insertchina(List<String> data) throws SQLException {
		    	Connection conn=getConnection();
		    	int res=0;
		    	
		    	try {
					//创建sql语句对象
		    	   
		           String sql="INSERT INTO china(provincename,currentc,confirmedc,suspectedc,curedc,deadc) VALUES(?,?,?,?,?,?)";
		        	
		           PreparedStatement pst = conn.prepareStatement(sql);
		           
		              for(int i=0;i<data.size();i++) {
		            	String str=data.get(i);
		            	 pst.setString(i+1, str);
		            }
		            	res = pst.executeUpdate();            	
		            	pst.close();       	 
		           
				    }catch(Exception e) {
				    	  e.printStackTrace();
				   }	    	 
		           finally {
		        	   
		        	   if(conn!=null)
		        		   conn.close();
		           }
		    	    return false;	     
				    }

	 //更新中国数据 语句
	    public static boolean updateChina(List<String> data) throws SQLException {
	    	Connection conn=getConnection();	    	
	    	int res=0;
	    	try {
				//创建sql语句对象
	            String sql="update china SET currentc=?,confirmedc=?,suspectedc=?,curedc=?,deadc=? where provincename=?";
	        	 PreparedStatement  pst = conn.prepareStatement(sql);
	        	      
	            for(int i=0;i<data.size();i++) {
	            	String str=data.get(i);
	            	pst.setString(i+1, str);
	            }
	            	res = pst.executeUpdate();           	
	            	pst.close();
	            	
	      
	            	if(res>0) {
	            		return true;
	            	} 
	            	
			    }catch(Exception e) {
			    	  e.printStackTrace();
			   }  
	    	
	           finally {  
	        	   if(conn!=null)
	        		   conn.close();
	           }	
	    	 return false;     
			  }

	 //插入国外数据到数据库
	    public static boolean insertworld(List<String> data) throws SQLException {
	    	Connection conn=getConnection();
	    	int res=0;
	    	try {

	            String sql="INSERT INTO world(name,currentc,confirmedc,suspectedc,curedc,deadc) VALUES(?,?,?,?,?,?)";
	           
	        	 PreparedStatement  pst = conn.prepareStatement(sql);
	        	    
	            for(int i=0;i<data.size();i++) {
	            	String str=data.get(i);
	            	pst.setString(i+1, str);
	            }
	            	res = pst.executeUpdate();
	            	pst.close();
	      
	            	if(res>0) {
	            		return true;
	            	}     
			    }catch(Exception e) {
			    	  e.printStackTrace();
			   }    	 
	           finally {
	        	   
	        	   if(conn!=null)
	        		   conn.close();
	           }
	    	
	    	 return false;     
			  }


	 //更新世界数据
	    public static boolean updateWorld(List<String> data) throws SQLException {
	    	Connection conn=getConnection();
	    	int res=0;
	    	try {
				//创建sql语句对象
	            String sql="update world SET currentc=? ,confirmedc=?,suspectedc=?,curedc=?,deadc=? where name=?";
	           
	        	 PreparedStatement  pst = conn.prepareStatement(sql);
	           
	            for(int i=0;i<data.size();i++) {
	            	String str=data.get(i);
	            	pst.setString(i+1, str);
	            }
	            	res = pst.executeUpdate();
	            	
	            	pst.close();
	      
	            	if(res>0) {
	            		return true;
	            	}
	                
			    }catch(Exception e) {
			    	  e.printStackTrace();
			   }
	    	 
	           finally {
	        	   
	        	   
	        	   if(conn!=null)
	        		   conn.close();
	           }
	    	
	    	 return false;     
			  }	    
	   
 }

            
           
  




