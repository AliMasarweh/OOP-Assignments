package tests;

import java.util.Date;

public interface car {  
	public boolean isRunning(); 
	public boolean open(String key);
	public Date lastRun();
}