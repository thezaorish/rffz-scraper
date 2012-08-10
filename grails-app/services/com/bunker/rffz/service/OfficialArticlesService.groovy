package com.bunker.rffz.service

import java.util.List;
import com.bunker.rffz.domain.OfficialArticle;

class OfficialArticlesService {

    def saveArticles(List<OfficialArticle> articles) {
		for (article in articles) {
			OfficialArticle official = OfficialArticle.findByTitle(article.title)
			if (official == null) {
				log.info 'saveArticles: going to save ' + article.title
				article.save(failOnError: true, flush: true)
			} else {
				log.info 'saveArticles: skiping ' + article.title + ' because it already exists' 
			}
		}
    }
}
