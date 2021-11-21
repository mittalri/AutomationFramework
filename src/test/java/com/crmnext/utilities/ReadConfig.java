package com.crmnext.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties pro;
	
	public ReadConfig ()
	{
		File src = new File ("./Configurations/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}catch (Exception e) {
			System.out.println("Exceptio is "+ e.getMessage());
		}
	}	
		public String getApplicationURL()
		{
			String URL = pro.getProperty("baseURL");
			return URL;
		}
				
		public String getUserName()
		{
			String uname = pro.getProperty("username");
			return uname;
		}
		
		public String getPassword()
		{
			String pass = pro.getProperty("password");
			return pass;
		}		
			
		

}
