package com.bunker.rffz.domain

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Ranking)
class RankingTests {

    @Test
	void shouldSaveRanking() {
		// given
		def ranking = Ranking.create()
		ranking.team = 'Steaua'
		ranking.type = RankingType.NationalLeague
		ranking.rank = 1
		ranking.points = 10

		// when
		ranking.save()

		// then
		assert ranking.id
	}

	@Test
	void shouldNotSaveInvalidRanking() {
		// given
		def ranking = Ranking.create()

		// when
		boolean valid = ranking.validate()

		// then
		assert !valid
		assert ranking.errors.getFieldError('team')
		assert ranking.errors.getFieldError('type')
	}

	@Test
	void shouldRetrieveRanking() {
		// given
		def ranking = Ranking.create()
		ranking.team = 'Steaua'
		ranking.type = RankingType.NationalLeague
		ranking.rank = 1
		ranking.points = 10
		ranking.save()

		// when
		def retrievedRanking = Ranking.findByTeamAndRank('Steaua', 1)

		// then
		assert retrievedRanking == ranking
	}
	
	@Test
	public void shouldDeleteByType() {
		def ranking = Ranking.create()
		ranking.team = 'Steaua'
		ranking.type = RankingType.NationalLeague
		ranking.rank = 1
		ranking.points = 10
		ranking.save()
		
		ranking = Ranking.create()
		ranking.team = 'Dinamo'
		ranking.type = RankingType.NationalLeague
		ranking.rank = 2
		ranking.points = 6
		ranking.save()
		
		ranking = Ranking.create()
		ranking.team = 'Rapid'
		ranking.type = RankingType.NationalLeague
		ranking.rank = 3
		ranking.points = 3
		ranking.save()
		
		assert Ranking.count() == 3
	
		Ranking.where{
			type == RankingType.NationalLeague
		}.deleteAll()
	
		assert Ranking.count() == 0
	}
}
