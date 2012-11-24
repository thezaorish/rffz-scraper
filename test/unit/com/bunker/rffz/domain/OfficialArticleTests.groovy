package com.bunker.rffz.domain

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(OfficialArticle)
class OfficialArticleTests {

    @Test
	void shouldSaveOfficialArticle() {
		// given
		def article = OfficialArticle.create()
		article.title = 'title'
		article.description = 'description'
		article.content = 'content'
		article.thumbnail = 'thumbnail'
		article.image = 'image'
		article.published = 'published'
		article.url = 'www.url.com'

		// when
		article.save()

		// then
		assert article.id
	}

	@Test
	void shouldNotSaveInvalidArticle() {
		// given
		def article = OfficialArticle.create()

		// when
		boolean valid = article.validate()

		// then
		assert !valid
		assert article.errors.getFieldError('title')
		assert article.errors.getFieldError('description')
		assert article.errors.getFieldError('content')
		assert article.errors.getFieldError('image')
		assert article.errors.getFieldError('thumbnail')
		assert article.errors.getFieldError('url')
	}
	
	@Test
	void shouldEnsureTitleUniqueness() {
		// given an article
		def article = OfficialArticle.create()
		article.title = 'title'
		article.description = 'description'
		article.content = 'content'
		article.thumbnail = 'thumbnail'
		article.image = 'image'
		article.published = 'published'
		article.url = 'www.url.com'
		article.save()
		
		// and another one with the same title
		def duplicateArticle = OfficialArticle.create()
		duplicateArticle.title = 'title'
		duplicateArticle.description = 'description'
		duplicateArticle.content = 'content'
		duplicateArticle.thumbnail = 'thumbnail'
		duplicateArticle.image = 'image'
		duplicateArticle.published = 'published'
		
		// when
		boolean valid = duplicateArticle.validate()
		
		// then
		assert !valid
		assert duplicateArticle.errors.getFieldError('title')
	}
}
