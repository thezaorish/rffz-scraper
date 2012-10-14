dataSource {
	pooled = true
	driverClassName = "org.h2.Driver"
	username = "sa"
	password = ""
}

hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

// environment specific settings
environments {
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
		}
	}
	
	development {
		dataSource {
			dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:mysql://localhost:3306/rffz?useUnicode=true&characterEncoding=UTF8"
			driverClassName = "com.mysql.jdbc.Driver"
			username = "rffz"
			password = "rffzmy5ql"
		}
	}
	
	production {
		dataSource {
			dbCreate = "none" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:mysql://localhost:3306/rffz?useUnicode=true&characterEncoding=UTF8"
			driverClassName = "com.mysql.jdbc.Driver"
			username = "rffz"
			password = "rffzmy5ql"
		}
	}
}
