package com.bunker.rffz.domain

import java.util.Date;

class OfficialArticle {

    String title
	String description
	String content

	String thumbnail
	String image

	String published

	Date createDate = new Date()

	static constraints = {
		title(unique: true)
	}

	static mapping = {
		description type: 'text'
		content type: 'text'
		version false
	}

	@Override
	public String toString() {
		return "OfficialArticle [title=" + title + ", description=" + description + ", content=" + content + ", thumbnail=" + thumbnail + ", image=" + image + ", published=" + published + "]";
	}
	
}