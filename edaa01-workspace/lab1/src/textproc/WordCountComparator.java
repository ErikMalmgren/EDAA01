package textproc;

import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> a, Entry<String, Integer> b) {
		if(b.getValue() > a.getValue()) {
			return 1;
		} else if (b.getValue() < a.getValue()){
			return -1;
		}
		return a.getKey().compareTo(b.getKey());
	}

}
