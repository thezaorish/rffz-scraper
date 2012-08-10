package com.bunker.rffz.service

import com.bunker.rffz.domain.ScheduledTask;
import com.bunker.rffz.domain.ScheduledTaskName;

class ScheduledTaskService {

    boolean isActive(ScheduledTaskName name) {
		def job = ScheduledTask.findByNameAndActive(name, true)
		return job != null
	}

	def updateLastRun(ScheduledTaskName name) {
		def job = ScheduledTask.findByName(name)
		job.lastRun = new Date()
		job.save()
	}
}
