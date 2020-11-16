package com.cs.dpg.assignment.application.cfg;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cs.dpg.assignment.application.constant.DBConstants;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class C3poDataSourceConnPool {

	private static Logger log = Logger.getLogger(C3poDataSourceConnPool.class);

	@Bean(name = "dataSource")
	public ComboPooledDataSource getComboPooledDataSource() throws Exception {
		try {
			ComboPooledDataSource cpds = new ComboPooledDataSource();
			cpds.setDriverClass(DBConstants.DB_DRIVER);
			cpds.setJdbcUrl(DBConstants.DB_URL);
			cpds.setUser(DBConstants.DB_USR);
			cpds.setPassword(DBConstants.DB_PSWRD);
			cpds.setMaxPoolSize(50);
			cpds.setMinPoolSize(10);
			cpds.setAcquireIncrement(10);
			cpds.setInitialPoolSize(40);
			return cpds;
		} catch (Exception e) {
			log.error(e);
			throw new Exception("Getting exception for Connection Pool ", e);
		}
	}

}
