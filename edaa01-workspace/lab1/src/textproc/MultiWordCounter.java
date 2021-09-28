package textproc;

import java.util.*;

public class MultiWordCounter implements TextProcessor {
	private Map<String, Integer> m = new TreeMap<String, Integer>();
	
	public MultiWordCounter(String[] landskap) {
		for(String k : landskap) {
			m.put(k,  0);
		}
	}
	@Override
	public void process(String w) {
		for (String key : m.keySet()) {
			if(key.equalsIgnoreCase(w)) {
				int freq = m.get(key);
				m.put(key, freq +1);
			}
		}
		
	}

	@Override
	public void report() {
		for(String key : m.keySet()) {
			System.out.println(key.toString() + ": " +m.get(key));
		}
		
	}

}
