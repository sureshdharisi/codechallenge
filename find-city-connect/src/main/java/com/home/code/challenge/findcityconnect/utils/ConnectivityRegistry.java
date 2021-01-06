package com.home.code.challenge.findcityconnect.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
public class ConnectivityRegistry {
	private Logger logger = LoggerFactory.getLogger(ConnectivityRegistry.class);
	private Map<String, Set<String>> cityConnectivityRegistry = new ConcurrentHashMap<>();

	public synchronized void registerConnectivity(String origin, String destination) {
		startRegistration(origin, destination, new ArrayList<>());
	}

	private void startRegistration(String origin, String destination, List<String> processedKeys) {
		Set<String> originConnectedCitiesList = cityConnectivityRegistry.getOrDefault(origin, new HashSet<>());
		Set<String> destinationConnectedCitiesList = cityConnectivityRegistry.getOrDefault(destination,
				new HashSet<>());
		Set<String> destinationConnectedCitiesListDup = new HashSet<>(destinationConnectedCitiesList);
		originConnectedCitiesList.addAll(destinationConnectedCitiesList);
		processedKeys.add(destination);
		originConnectedCitiesList.add(destination);
		originConnectedCitiesList.add(origin);
		cityConnectivityRegistry.put(origin, originConnectedCitiesList);
		cityConnectivityRegistry.put(destination, originConnectedCitiesList);
		destinationConnectedCitiesListDup.removeAll(processedKeys);

		if (!CollectionUtils.isEmpty(destinationConnectedCitiesListDup)) {
			destinationConnectedCitiesListDup.stream().forEach(dest -> {
				startRegistration(origin, dest, processedKeys);
			});
		}

	}

	/**
	 * It will accept two cities and verify both cities are connected or not. If
	 * both cities are connected in either direction, then it will return true.
	 * 
	 * If both are not connected, then it will return false.
	 * 
	 * @param city1
	 * @param city2
	 * @return
	 */
	public boolean isCitiesConnected(String origin, String destination) {
		origin = StringUtils.upperCase(origin);
		destination = StringUtils.upperCase(destination);
		Set<String> connectivityList = cityConnectivityRegistry.getOrDefault(origin,new HashSet<>());
		logger.debug("Connectivity = "+connectivityList);
		return connectivityList.contains( origin)&&connectivityList.contains(destination);
	}
}
