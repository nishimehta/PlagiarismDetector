package PlagiarismDetector;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import PlagiarismDetector.FileHelper;
import PlagiarismDetector.TupleMatcher;

public class PlagiarismDetector {
	
	static int tupleSize = 3;
	public static void main(String args[]) {
	
		 //Check if all required arguments are present.
		if(args.length !=3 && args.length!=4) {
			System.out.println("Atleast 3 arguments required: a synonyms file and 2 files to compare for plagiarism ");
			return;
		}
		
		// Check if Synonyms file exists.
		File synFile = new File(args[0]);
		HashMap<String,String> synonymMap = new HashMap<String,String>();
		if(!synFile.exists()) {
			System.out.println("Synonyms file does not exist");
			return;
		} else {
			// Generates a HashMap of synonyms if synonyms file exists. (Further details in FileHelper.java)
			synonymMap = FileHelper.generateSynonymsMap(synFile);
		}
		
		File inputFile1 = new File(args[1]);
		File inputFile2 = new File(args[2]);
		if(!inputFile1.exists() && !inputFile2.exists()) {
			System.out.println("Input file 1 and 2 do not exist");
			return;
		} else if(!inputFile1.exists()) {
			System.out.println("Input file 1 does not exist");
			return;
		} else if(!inputFile2.exists()) {
			System.out.println("Input file 2 does not exist");
			return;
		} 
		
		if(args.length==4) {
			tupleSize = Integer.parseInt(args[3]);
		}
		
		// Generates words from both files. (Further details in FileHelper.java)
		ArrayList<String> file1_words = FileHelper.getWordsList(inputFile1);
		ArrayList<String> file2_words = FileHelper.getWordsList(inputFile2);
		
		// Condition for when the tuple size is greater than the total number of words in either file.
		if(file1_words.size()<tupleSize) {
			System.out.println("Tuple Size given is greater than the number of words in first file.");
		}else if(file2_words.size()<tupleSize) {
			System.out.println("Tuple Size given is greater than the number of words in second file.");
		}else {
			/* Calculates the plagiarism percentage given words of both files, synonyms and the tuple size. 
			 * Here, plagiarism percentage refers to the percentage of n-tuples of file 1 which appear in file 2.
			 * Further details in TupleMatcher.java
			 */
			float plagiarismPercentage = TupleMatcher.calculatePlagiarismPercentage(file1_words,file2_words,tupleSize,synonymMap);
			System.out.printf("Plagiarism detected: %.2f %% \n",plagiarismPercentage);
		}
	}
    

}
