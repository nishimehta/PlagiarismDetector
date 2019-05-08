package PlagiarismDetector;

import java.util.ArrayList;
import java.util.HashMap;

public class TupleMatcher {
	/**
	 * This function generates n-tuples given the tuple size and the words ArrayList.
	 * If the Boolean replaceSynonyms is true, then all the synonyms will be replaced by its base word.
	 * For example, if "run", "jog" and "sprint" are synonyms, all word occurrences of jog and sprint will be replaced by its synonym "run".
	 * @param words				 an ArrayList of words
	 * @param tupleSize			 the size of tuples to be generated
	 * @param synonymMap		 the map of synonyms if words need to be replaced by its synonym.
	 * @param replaceSynonyms    whether or not the words should be replaced by its synonym.
	 * @return ArrayList<String> returns list of n-tuples of size given by tupleSize param.
	 */
	public static ArrayList<String> generateNtuples(ArrayList<String> words,int tupleSize,
			HashMap<String,String> synonymMap,Boolean replaceSynonyms) {
    	ArrayList<String> nTuples = new ArrayList<String>();
        for (int i = 0; i < words.size() - tupleSize + 1; i++) {
        	int from = i;
        	int to = i+tupleSize;
        	StringBuilder builder = new StringBuilder();
        	for(int j = from; j < to; j++) {
        		String word = words.get(j).toLowerCase();
        		if(replaceSynonyms)
	        		if(synonymMap.containsKey(word))
	        			word = synonymMap.get(word);
        		if(j!=from){
        			builder.append(" ");
        			builder.append(word);
        		}else
        			builder.append(word);
        	}
        	nTuples.add(builder.toString());
        }
        return nTuples;
    }
	
	/**
	 * This function calculates the count of plagiarised tuples that is the count of tuples of size tupleSize present in words1
	 * which are also present in words2.
	 * @param words1 		ArrayList of words for which plagiarism count needs to be computed.
	 * @param words2		ArrayList of words from which plagiarism needs to be checked.
	 * @param tupleSize     Size of n-tuples.
	 * @param synonymMap	the map of synonyms.
	 * @return int 			count of plagiarised tuples present in words1.
	 */
    public static int calculateCountPlagiarisedTuples(ArrayList<String> words1, ArrayList<String> words2,
    		int tupleSize,HashMap<String,String> synonymMap) {
		ArrayList<String> tuples1 = generateNtuples(words1,tupleSize,synonymMap,true);
		ArrayList<String> tuples2 = generateNtuples(words2,tupleSize,synonymMap,true);
    	int plagiarisedTuples = 0;
    	
    	for(String tuple:tuples1) {
    		if(tuples2.contains(tuple))
    			plagiarisedTuples++;
    	}
    	return plagiarisedTuples;
    }
    
    /**
     * This function calculates the percentage of tuples in words1 present in words2 both given as params.
     * @param words1       ArrayList of words for which plagiarism percentage needs to be computed.
     * @param words2	   ArrayList of words from which plagiarism needs to be checked.
     * @param tupleSize    Size of n-tuples.
     * @param synonymMap   the map of synonyms.
     * @return float       percentage of plagiarised tuples present in words1.
     */
    public static float calculatePlagiarismPercentage(ArrayList<String> words1, ArrayList<String> words2,
    		int tupleSize,HashMap<String,String> synonymMap) {
		int plagiarisedTuples = calculateCountPlagiarisedTuples(words1,words2,tupleSize,synonymMap);
		int numberOfTuplesFile1 = calculateNumberOfTuples(words1,tupleSize);
    	return ((float)plagiarisedTuples/(float)numberOfTuplesFile1)*100;
    }
    
    /**
     * This function calculates the number of tuples which can be generated through the words ArrayList given as param.
     * @param words       ArrayList of words for which number of tuples need to be calculated.
     * @param tupleSize	  Size of n-tuples.
     * @return			  Count of number of tuples which can be generated through the words ArrayList.
     */
    public static int calculateNumberOfTuples(ArrayList<String> words,int tupleSize) {
    	return words.size() - tupleSize + 1;
    }
}
