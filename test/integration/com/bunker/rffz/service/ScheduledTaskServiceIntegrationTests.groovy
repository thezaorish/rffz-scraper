package com.bunker.rffz.service

import static org.junit.Assert.*
import org.junit.*

import com.bunker.rffz.domain.ScheduledTask;
import com.bunker.rffz.domain.ScheduledTaskName;

class ScheduledTaskServiceIntegrationTests {

    private ScheduledTaskService scheduledTaskService
	
    @Before
    void setUp() {
        scheduledTaskService = new ScheduledTaskService() 
    }

    @After
    void tearDown() {
        // 
    }

    @Test
    void shouldMarkTaskAsActive() {
        // given
		def task = ScheduledTask.create()
		task.active = true
		task.name = ScheduledTaskName.RankingRetrievalJob
		task.save()
		
		// when
		boolean active = scheduledTaskService.isActive(ScheduledTaskName.RankingRetrievalJob)
		
		// then
		assert active
    }
	@Test
	void shouldMarkTaskAsInactive() {
		// given
		def task = ScheduledTask.create()
		task.active = false
		task.name = ScheduledTaskName.RankingRetrievalJob
		task.save()
		
		// when
		boolean active = scheduledTaskService.isActive(ScheduledTaskName.RankingRetrievalJob)
		
		// then
		assert !active
	}
	@Test
	void shouldMarkTaskAsInactiveForNonExistingTask() {
		// given
		
		// when
		boolean active = scheduledTaskService.isActive(ScheduledTaskName.RankingRetrievalJob)
		
		// then
		assert !active
	}
	
	@Test
	void shouldUpdateTaskLastRun() {
		// given
		Date initialDate = new Date(0) 
		
		def task = ScheduledTask.create()
		task.active = true
		task.name = ScheduledTaskName.RankingRetrievalJob
		task.lastRun = initialDate
		task.save()
		
		// when
		scheduledTaskService.updateLastRun(ScheduledTaskName.RankingRetrievalJob)
		
		// then
		def job = ScheduledTask.findByName(ScheduledTaskName.RankingRetrievalJob)
		assert job.lastRun.after(initialDate)
	}
}
