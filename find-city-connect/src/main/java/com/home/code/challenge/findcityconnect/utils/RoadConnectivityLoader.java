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
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

@Component
public class RoadConnectivityLoader {

	private List<String> citiesList=new ArrayList<>();
	
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
			System.out.println(citiesList);
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
