package com.lexi.comp4004.common.game.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lexi.comp4004.common.game.util.Config;

public class Results {

	private Map<Integer, Map<String, Object>> results;
	
	public Results() {
		results = new HashMap<Integer, Map<String, Object>>();
	}
	
	public void addResult(int rank, String user, String outcome, List<Card> cards) {
		Map<String, Object> userResults = new HashMap<String, Object>();
		userResults.put(Config.USER, user);
		userResults.put(Config.OUTCOME, outcome);
		userResults.put(Config.CARDS, cards);
		
		results.put(rank, userResults);
	}
	
	public Map<Integer, Map<String, Object>> getResults() {
		return results;
	}
	
}
