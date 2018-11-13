package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class Third {

	@Autowired
	private TaskExecutor taskExecutor;
	
	public WriteFile m1(){
		WriteFile wf= new WriteFile();
		taskExecutor.execute(wf);
		return wf;
		
	}
}
