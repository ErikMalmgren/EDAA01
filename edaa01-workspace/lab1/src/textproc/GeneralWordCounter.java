package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> wordMap = new TreeMap<String, Integer>();
	private Set<String> stopwordSet = new HashSet<String>();
	
	public GeneralWordCounter(Set<String> stopwords) {
		for(String s : stopwords){
			stopwordSet.add(s);
		}
	}
	
	public List<Map.Entry<String, Integer>> getWordList(){
		return new ArrayList<Map.Entry<String, Integer>>(wordMap.entrySet());
	}
	
	@Override
	public void process(String w) {
		if (!stopwordSet.contains(w)) {
			if(!wordMap.containsKey(w)) {
				wordMap.put(w, 1);
			}
			int freq = wordMap.get(w);
			wordMap.put(w, freq + 1);
		}
		
	}

	@Override
	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = wordMap.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
		for ( int i = 0; i < 600; i++) {
			System.out.println(wordList.get(i).toString());
		}
		
//		for (String key : wordMap.keySet()) {
//			if(wordMap.get(key) >= 200) {
//				System.out.println(key.toString() + ": " + wordMap.get(key));
//			}
//		}
	}

}
