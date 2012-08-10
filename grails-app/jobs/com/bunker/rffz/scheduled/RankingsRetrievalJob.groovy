package com.bunker.rffz.scheduled

import com.bunker.rffz.domain.ScheduledTaskName;
import com.bunker.rffz.domain.ScheduledTask;

class RankingsRetrievalJob {
	
    def rankingsRetrievalService

	def scheduledTaskService

	static triggers = {
		simple repeatInterval: 60 * 60 * 1000l // every hour
	}

	def execute() {
		if (scheduledTaskService.isActive(ScheduledTaskName.RankingRetrievalJob)) {
			log.info 'RankingRetrievalJob started'
			rankingsRetrievalService.retrieveNationalLeagueRanking()
			scheduledTaskService.updateLastRun(ScheduledTaskName.RankingRetrievalJob)
			log.info 'RankingRetrievalJob ended'
		} else {
			log.info 'RankingRetrievalJob not active'
		}
	}
}
