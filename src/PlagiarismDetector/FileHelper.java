package PlagiarismDetector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileHelper {
	
	/**
	 * This function generates an ArrayList of words contained in the file given as argument.
	 * By using the split element as "\\W+" we consider only word elements and eliminate the punctuations which is a form of preprocessing.
	 * @param  File file          Input file
	 * @return ArrayList<String>  returns a list of words present in the file
	 */
	public static ArrayList<String> getWordsList(File file){
		ArrayList<String> wordsList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line=br.readLine()) != null) {
				String[] words = line.split("\\W+");
				for(String word:words) {
					wordsList.add(word);
				}
			}
	}catch (FileNotFoundException e) {
		System.out.println("Input file not found.");
	} catch (IOException e) {
		System.out.println("Input file IOException.");
	} 
	return wordsList;
	}
	
	/**
	 * This function generates a HashMap of the synonyms file given as argument.
	 * The HashMap consists of key as synonym word and value as the first synonym of the list.
	 * For example, in case the first line of the file is "run sprint jog" a HashMap like the following will be created.
	 * { "run"    : "run",
	 * 	 "sprint" : "run",
	 *   "jog"    : "run"}
	 * @param  File synonymsFile      synonyms file
	 * @return HashMap<String,String> HashMap of synonyms
	 */
	public static HashMap<String,String> generateSynonymsMap(File synonymsFile){
		HashMap<String,String> synonymMap = new HashMap<String,String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(synonymsFile));
			String line; 
			  while ((line = br.readLine()) != null) {
			    String[] synonyms =  line.split("\\s+");
			    String firstSynonym = synonyms[0].toLowerCase();
			    for(int i =0;i<synonyms.length;i++) {
			    	synonymMap.put(synonyms[i].toLowerCase(),firstSynonym);
			    }
			  } 
		} catch (FileNotFoundException e) {
			System.out.println("Synonyms file not found.");
		} catch (IOException e) {
			System.out.println("Synonyms file IOException.");
		} 
		return synonymMap;
	}
}
