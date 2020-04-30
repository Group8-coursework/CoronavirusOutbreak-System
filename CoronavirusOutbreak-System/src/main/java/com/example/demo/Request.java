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
	private static String headtitle = "����,�ִ�ȷ��,�ܼ�ȷ����Ʋ�����������������������";
	private static List<String> data=new ArrayList<String>();
	private static List<String> datac=new ArrayList<String>();
	private static List<String> data1=new ArrayList<String>();
	private static List<String> datac1=new ArrayList<String>();	
	private static String filename_china=("d:"+Tellingtime.time+"china.html");
	//private static String outfile_china = ("d:"+Tellingtime.time+"china.csv");
	private static String chart_china = ("d:"+"chartofchina.csv");
	private static String chart_world = ("d:"+"chartofworld.csv");
	private static String headtitle2 = "ʡ��,�ִ�ȷ��,�ܼ�ȷ����Ʋ�����������������������";
    

	/**��ȡurl*/
 //   public static void main(String[] args) throws Exception {
    	
    //	 Request q=new Request();
      	// q.China("script[ id=getAreaStat]");
      	 //q.world("script[ id=getListByCountryTypeService2true]");   	    	
   //  }

       /**��ȡ�й���ʡ�����е�����
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
        
        private static void outputRc() throws Exception {
            
            String strout = "";
            for (int i = 0; i < datac.size(); i++) {
                strout = strout + datac.get(i) + ",";      //��ȡdata�����е�ÿһ�����ݣ�����һ���ַ���
            }
            System.out.println(strout); 
            if (true) {
            	//ʵ�����ļ������ ������ض�����ʱ����ļ�����
                FileWriter fw = new FileWriter("d:"+Tellingtime.time+"china.csv", true);    
                PrintWriter out = new PrintWriter(fw);                  //ʵ������ӡ��
                if (countrs == 0)
                    out.println(headtitle2);                     //���ͷ����
                out.println(strout);                        //����ոմ�������strout�ַ���
                out.close();                                //�رմ�ӡ��
                fw.close();                             //�ر������
            }
            countrs = countrs + 1;
           // System.out.println(countrs + "  " + strout); 
   
            
            /* �������ݿ�*/
            if (true) {
                mysql.insertchina(datac);
                
            }     
        }
		private static void updateRc() throws Exception {
		            
		            String strout = "";
		            for (int i = 0; i < datac1.size(); i++) {
		                strout = strout + datac1.get(i) + ",";      //��ȡdata�����е�ÿһ�����ݣ�����һ���ַ���
		            }
		            System.out.println(strout); 
		            if (true) {
		            	//ʵ�����ļ������ ������ض�����ʱ����ļ�����
		                FileWriter fw = new FileWriter("d:"+Tellingtime.time+"china.csv", true);    
		                PrintWriter out = new PrintWriter(fw);                  //ʵ������ӡ��
		                if (countrs == 0)
		                    out.println(headtitle2);                     //���ͷ����
		                out.println(strout);                        //����ոմ�������strout�ַ���
		                out.close();                                //�رմ�ӡ��
		                fw.close();                             //�ر������
		            }
		            countrs = countrs + 1;
		           // System.out.println(countrs + "  " + strout); 
		   
		            
		            /* �������ݿ�*/
		            if (true) {
		                mysql.updateChina(datac1);
		               
		            }     
		        }
        
        /**
         * ���html�ļ�
         */
        public static void htmltoFile2(String htmlString) throws Exception {
            // ����ļ������
            FileOutputStream output = new FileOutputStream(filename_china);
            // ��utf-8�������ʽ������ļ���utf-8�����ı��룬ISO-8859-1��Ӣ�ı��룩
            output.write(htmlString.getBytes("utf-8"));
            if (output != null) {
                output.close();
            }
        }
 
        
        
       /**��ȡ�������������
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
             
             //���body�µ����ݣ��Լ�����ʽ����
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
              
        private static void outputRs() throws Exception {
          
            String strout = "";
            for (int i = 0; i < data.size(); i++) {
                strout = strout + data.get(i) + ",";      //��ȡdata�����е�ÿһ�����ݣ�����һ���ַ���
            }
            System.out.println(strout); 
            if (true) {
            	//ʵ�����ļ������ ������ض�����ʱ����ļ�����
                FileWriter fw = new FileWriter( "d:"+Tellingtime.time+"world.csv", true); 
                PrintWriter out = new PrintWriter(fw);                  //ʵ������ӡ��
                if (countrs == 0)
                    out.println(headtitle);                     //���ͷ����
                out.println(strout);                        //����ոմ�������strout�ַ���
                out.close();                                //�رմ�ӡ��
                fw.close();                             //�ر������
            }
            countrs = countrs + 1;
        
            /* �������ݿ�*/
            if (true) {
                mysql.insertworld(data);
                
            }     
        }
        private static void updateRs() throws Exception {
            
            String strout = "";
            for (int i = 0; i < data1.size(); i++) {
                strout = strout + data1.get(i) + ",";      //��ȡdata�����е�ÿһ�����ݣ�����һ���ַ���
            }
            System.out.println(strout); 
            if (true) {
            	//ʵ�����ļ������ ������ض�����ʱ����ļ�����
                FileWriter fw = new FileWriter( "d:"+Tellingtime.time+"world.csv", true); 
                PrintWriter out = new PrintWriter(fw);                  //ʵ������ӡ��
                if (countrs == 0)
                    out.println(headtitle);                     //���ͷ����
                out.println(strout);                        //����ոմ�������strout�ַ���
                out.close();                                //�رմ�ӡ��
                fw.close();                             //�ر������
            }
            countrs = countrs + 1;
        
            /* �������ݿ�*/
            if (true) {
                mysql.updateWorld(data1);
                
            }     
        }
        
        /**
         * ���html�ļ�
         */
        public static void htmltoFile(String htmlString) throws Exception {
            // ����ļ������
            FileOutputStream output = new FileOutputStream(filename_world);
            // ��utf-8�������ʽ������ļ���utf-8�����ı��룬ISO-8859-1��Ӣ�ı��룩
            output.write(htmlString.getBytes("utf-8"));
            if (output != null) {
                output.close();
            }
        }
 
	/**��������ǽ�InputStreamת��ΪString*/
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
  
	     // ���ݿ���û��������룬��Ҫ�����Լ�������
	      String USER = "root";
	      String PASS = "1286287511";
	      try {
	    	  // ע�� JDBC ����
	    	  	Class.forName(JDBC_DRIVER);
	    	  	//System.out.println("connection success");             
	    	  	// ������
	    	  	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      }catch(Exception e) {
	    	  System.out.println("fail"+e.getMessage());
	      }
	       return conn;
	   	  
	     }
	 
	 
	     //����ͼ�й�����
	     public static void chartchina() throws SQLException {
	    	Connection conn=getConnection();	    	  
	    	Statement state=conn.createStatement();   //����	    	
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
	     
	     //����ͼ��������
	     public static void chartworld() throws SQLException {
	    	Connection conn=getConnection();	    	  
	    	Statement state=conn.createStatement();   //����	    	
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
		  
	     
		  //�����й����ݵ����ݿ�
		 public static boolean insertchina(List<String> data) throws SQLException {
		    	Connection conn=getConnection();
		    	int res=0;
		    	
		    	try {
		           //����sql������
		    	   
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
	 
	 //�����й����� ���
	    public static boolean updateChina(List<String> data) throws SQLException {
	    	Connection conn=getConnection();	    	
	    	int res=0;
	    	try {
	           //����sql������
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
	    
       //����������ݵ����ݿ�
	    public static boolean insertworld(List<String> data) throws SQLException {
	    	Connection conn=getConnection();
	    	int res=0;
	    	try {
	           //����sql������
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
	    
	   
	    //������������
	    public static boolean updateWorld(List<String> data) throws SQLException {
	    	Connection conn=getConnection();
	    	int res=0;
	    	try {
	           //����sql������
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

            
           
  




