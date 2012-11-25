package com.bunker.rffz.scheduled

import com.bunker.rffz.domain.ScheduledTaskName;
import com.bunker.rffz.domain.ScheduledTask;

class OfficialArticlesRetrievalJob {
	
    def officialArticlesRetrievalService

	def scheduledTaskService

	static triggers = {
		simple repeatInterval: 4 * 60 * 60 * 1000l // every 4 hours
	}

	def execute() {
		if (scheduledTaskService.isActive(ScheduledTaskName.OfficialArticlesRetrievalJob)) {
			log.info 'execute: started'
			officialArticlesRetrievalService.retrieveOfficialArticles()
			scheduledTaskService.updateLastRun(ScheduledTaskName.OfficialArticlesRetrievalJob)
			log.info 'execute: ended'
		} else {
			log.info 'execute: not active'
		}
	}
}
