package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Holgersson {
	
	private static Set<String> scanStopWords() throws FileNotFoundException{
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while(scan.hasNext()) {
			stopwords.add(scan.next());
		}
		scan.close();
		return stopwords;
	}

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		List<TextProcessor> counters = new ArrayList<TextProcessor>();
		
		counters.add(new SingleWordCounter("nils"));
		counters.add(new SingleWordCounter("norge"));
		counters.add(new MultiWordCounter(REGIONS));
		counters.add(new GeneralWordCounter(scanStopWords()));
		

		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning

		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			
			for(TextProcessor p: counters) {
				p.process(word);
			}

	
		}

		s.close();
		for(TextProcessor p: counters) {
			p.report();
		}
		

	long t1 = System.nanoTime();
	System.out.println("Tid: " + (t1-t0)/1000000 + "ms");
	// TreeMap ca 100 ms snabbare
	}
}