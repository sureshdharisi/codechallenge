package com.home.code.challenge.findcityconnect.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class RoadConnectivityLoader {
	Logger logger = LoggerFactory.getLogger(RoadConnectivityLoader.class);
	
	@Autowired
	private ConnectivityRegistry registry;
	
	/**
	 * This component will read data from city.txt and hold in the cities list variable.
	 * 
	 * This data will be act as a cache in the container.
	 */
	@PostConstruct
	public void loadConnectivityData() {
		BufferedReader br=null;
		try {
			File file = ResourceUtils.getFile("classpath:city.txt");
			br=new BufferedReader(new InputStreamReader(new FileInputStream(file),Charset.forName("UTF-8"))) ;
					br.lines().forEach(line->{
				String[] cities=line.split(",");
				registry.registerConnectivity(StringUtils.upperCase(StringUtils.trim( cities[0])), StringUtils.upperCase(StringUtils.trim(cities[1])));
			});
		} catch (Exception e) {
			logger.error("Exception while reading data from file",e);
		}finally {
			IOUtils.closeQuietly(br);
		}
	}
	
	
}
