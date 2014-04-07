package Database;
/**
 * Class define pair parameters by filter.
 * 1. Name fields for filter.
 * 2. Phrase for filter field.
 * @author KArt
 *
 */
public class PairFilter {
	
	String name;
	String phrase;
	
	public PairFilter(String name, String phrase) {
		this.name = name;
		this.phrase = phrase;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhrase() {
		return phrase;
	}
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
	
	

}
