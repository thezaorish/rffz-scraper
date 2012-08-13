package com.bunker.rffz.domain

import java.util.Date;

class ScheduledTask {

    ScheduledTaskName name
	boolean active
	Date lastRun
	
	static constraints = {
		lastRun(nullable: true)
	}
	
	static mapping = {
		version false
	}
	
}
