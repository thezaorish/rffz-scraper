package com.bunker.rffz.service

import org.springframework.transaction.annotation.Transactional;

import com.bunker.rffz.domain.Ranking;
import com.bunker.rffz.domain.RankingType;

import geb.*

class RankingsRetrievalService {

    static transactional = true

	// the configuration of pointsPerTeam: pointsPerTeam: M  V 	E 	I 	GD 	GP 	Dif 	P
	private static final int gamesPlayedIdx = 0
	private static final int gamesWonIdx = 1
	private static final int gamesTiedIdx = 2
	private static final int gamesLostIdx = 3
	private static final int goalsScoredIdx = 4
	private static final int goalsReceivedIdx = 5
	private static final int pointsIdx = 7
	
    def retrieveNationalLeagueRanking() {
		// http://stackoverflow.com/questions/3147537/split-collection-into-sub-collections-in-groovy
		List.metaClass.partition = {size ->
			if (!delegate)
				return []
		
			def result = delegate.inject([[]]) {ret, elem ->
				(ret.last() << elem).size() >= size ? (ret << []) : ret
			}
			!result.last() ? result[0..-2] : result
		}
		
		def teams = []
		def points = []
		
		Browser.drive{
			// driver.webClient.javaScriptEnabled = true
			go "http://www.sport.ro/clasament/liga1"
			
			$('.competitieTabel .titlu').each { 
				teams << it.text()
			}
			$('.competitieTabel .puncte').each {
				points << it.text()
			}
		}
		
		def pointsPerTeam = points.partition(8)
		def rankings = []
		
		log.error 'deleting ..'
		Ranking.where{
			type == RankingType.NationalLeague
		}.deleteAll()
		
		for (int i=0; i<teams.size; i++) {
			def ranking = new Ranking(
				type: RankingType.NationalLeague,
				rank:  i + 1,
				team: teams[i],
				points: pointsPerTeam[i][pointsIdx],
				gamesPlayed: pointsPerTeam[i][gamesPlayedIdx],
				gamesWon: pointsPerTeam[i][gamesWonIdx],
				gamesTied: pointsPerTeam[i][gamesTiedIdx],
				gamesLost: pointsPerTeam[i][gamesLostIdx],
				goalsScored: pointsPerTeam[i][goalsScoredIdx],
				goalsReceived: pointsPerTeam[i][goalsReceivedIdx]
			)
			rankings << ranking
			ranking.save()
		}
		
		log.error 'pointsPerTeam: ' + rankings
    }
}
