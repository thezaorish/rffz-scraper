package com.bunker.rffz.domain

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ScheduledTask)
class ScheduledTaskTests {

    @Test
	void shouldSaveScheduledTask() {
		// given
		def task = ScheduledTask.create()
		task.active = false
		task.name = ScheduledTaskName.RankingRetrievalJob

		// when
		task.save()

		// then
		assert task.id
	}

	@Test
	void shouldNotSaveInvalidTask() {
		// given
		def task = ScheduledTask.create()

		// when
		boolean valid = task.validate()

		// then
		assert !valid
		assert task.errors.getFieldError('name')
	}

	@Test
	void shouldRetrieveTask() {
		// given
		def task = ScheduledTask.create()
		task.active = false
		task.name = ScheduledTaskName.RankingRetrievalJob
		task.save()

		// when
		def retrievedTask = ScheduledTask.findByName(ScheduledTaskName.RankingRetrievalJob)

		// then
		assert retrievedTask == task
	}
}
