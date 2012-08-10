package com.bunker.rffz.service

import static org.junit.Assert.*
import org.junit.*

import com.bunker.rffz.domain.OfficialArticle;

class OfficialArticlesServiceIntegrationTests {

    private OfficialArticlesService officialArticlesService
	
    @Before
    void setUp() {
		officialArticlesService = new OfficialArticlesService()
		
		def existingArticle = OfficialArticle.create()
		existingArticle.title = 'title'
		existingArticle.description = 'description'
		existingArticle.content = 'content'
		existingArticle.thumbnail = 'thumbnail'
		existingArticle.image = 'image'
		existingArticle.published = 'published'
		existingArticle.save()
    }

    @After
    void tearDown() {
        // 
    }

    @Test
    void shouldSaveOnlyNewArticles() {
        // given an existing article
		assert OfficialArticle.count() == 1 
		
		// and an article to be saved with the same title as an existing article
		def newArticle = OfficialArticle.create()
		newArticle.title = 'title'
		newArticle.description = 'description'
		newArticle.content = 'content'
		newArticle.thumbnail = 'thumbnail'
		newArticle.image = 'image'
		newArticle.published = 'published'
		
		def articles = []
		articles.add(newArticle)
		
		// when
		officialArticlesService.saveArticles(articles)
		
		// then
		assert OfficialArticle.count() == 1 
    }
	@Test
	void shouldSaveOnlyNewArticles2() {
		// given an existing article
		assert OfficialArticle.count() == 1
		
		// and a new article to be saved
		def newArticle = OfficialArticle.create()
		newArticle.title = 'title2'
		newArticle.description = 'description'
		newArticle.content = 'content'
		newArticle.thumbnail = 'thumbnail'
		newArticle.image = 'image'
		newArticle.published = 'published'
		
		def articles = []
		articles.add(newArticle)
		
		// when
		officialArticlesService.saveArticles(articles)
		
		// then
		assert OfficialArticle.count() == 2
	}
}
