package com.home.code.challenge.findcityconnect.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class RoadConnectivityLoader {
	Logger logger = LoggerFactory.getLogger(RoadConnectivityLoader.class);
	private List<String> citiesList=new ArrayList<>();
	
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
			citiesList=br.lines().map(line->{
				String[] cities=line.split(",");
				return StringUtils.trim(StringUtils.upperCase(cities[0]))+StringUtils.trim(StringUtils.upperCase(cities[1]));
			}).collect(Collectors.toList());
			logger.debug(citiesList.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(br!=null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * It will accept two cities and verify both cities are connected or not. If both cities are connected in either direction, then it will return true.
	 * 
	 * If both are not connected, then it will return false.
	 * 
	 * @param city1
	 * @param city2
	 * @return
	 */
	public boolean isCitiesConnected(String city1,String city2) {
		String connectivity=StringUtils.trim(StringUtils.upperCase(city1))+StringUtils.trim(StringUtils.upperCase(city2));
		if(citiesList.contains(connectivity)) {
			return true;
		}else {
			connectivity=StringUtils.trim(StringUtils.upperCase(city2))+StringUtils.trim(StringUtils.upperCase(city1));
			return citiesList.contains(connectivity);
		}
	}
}
