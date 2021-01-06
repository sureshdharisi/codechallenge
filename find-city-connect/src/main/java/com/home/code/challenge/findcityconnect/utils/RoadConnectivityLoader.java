package com.home.code.challenge.findcityconnect.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
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
			br=new BufferedReader(new FileReader(file)) ;
					br.lines().forEach(line->{
				String[] cities=line.split(",");
				registry.registerConnectivity(StringUtils.upperCase(StringUtils.trim( cities[0])), StringUtils.upperCase(StringUtils.trim(cities[1])));
			});
		} catch (FileNotFoundException e) {
			logger.error("Exception while reading data from file",e);
		}finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		registry.flatConnectivity();
	}
	
	
}
