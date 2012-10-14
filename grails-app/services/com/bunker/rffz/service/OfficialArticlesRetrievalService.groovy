package com.bunker.rffz.service

import com.bunker.rffz.domain.OfficialArticle;

import geb.*

class OfficialArticlesRetrievalService {

    def officialArticlesService

	static transactional = false
	
    def retrieveOfficialArticles() {
		log.info 'retrieveOfficialArticles: started'
		
		def articles = []
		
		Browser.drive{
			// driver.webClient.javaScriptEnabled = true
			go "http://www.steauafc.com/ro/stiri/"
			//go "http://www.steauafc.com/ro/stiri/-/2012-09-13/2012-10-13/-/2/"
					 
			def articleLinksWithThumnails = [:] 
			def articleLinks = [] // will ensure the order of the articles
			
			$(class: 'stire_gradient').each {
				def full = it.getAttribute('onclick')
				def thumbnail = it.find('img').@src
				
				String url = full.substring(full.indexOf("'") + 1, full.lastIndexOf("'"))
				articleLinksWithThumnails[url] = thumbnail
				articleLinks.add(url)
			}
			log.info 'retrieveOfficialArticles: articleLinksWithThumnails ' + articleLinksWithThumnails
			
			for (articleLink in articleLinks.reverse()) {
				log.info 'retrieveOfficialArticles: processing ' + articleLink
				
				go articleLink
				Thread.sleep(3000)
				
				def articleTitle = $(class: "top_articol").text()
				def articleDescription = $(class: "desc_articol").text()
				def articleContent = $(class: "text_articol").text()
				def articleData = $(class: "data_articol").text()
				def articleImage = $(".head_articol img").@src
				def articleThumbnail = articleLinksWithThumnails.get(articleLink)
								
				def article = new OfficialArticle(
					title: articleTitle,
					description: articleDescription,
					content: articleContent,
					published: articleData,
					image: articleImage,
					thumbnail: articleThumbnail
					)
				
				articles << article
			}
		}
		
		officialArticlesService.saveArticles(articles)
		log.info 'retrieveOfficialArticles: ended'
	}
}
