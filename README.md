# Plagiarism Detector

This application can be used to detect the plagiarism of two files.

## Compilation:
- javac PlagiarismDetector/*.java

## How to run:
- java PlagiarismDetector.PlagiarismDetector <Synonyms File Path> <Input File 1 Path> <Input File 2 Path> <Optional Tuple Size>
OR
- an executable jar file has been generated and can be used by the command:
	java -jar PlagiarismDetector.jar <Synonyms File Path> <Input File 1 Path> <Input File 2 Path> <Optional Tuple Size>
The first three arguments are required and the fourth argument that is, tuple size is optional.
Example: java -jar PlagiarismDetector.jar /Users/nishimehta/eclipse-workspace/PlagiarismDetector/src/syn.txt /Users/nishimehta/eclipse-workspace/PlagiarismDetector/src/file1.txt /Users/nishimehta/eclipse-workspace/PlagiarismDetector/src/file2.txt 3

## Sample Input:
1. Synonyms File: syn.txt
run jog sprint
happy gay jovial cheerful
sad dismal sorrowful

2. Input File 1 file1.txt
go for a jog.
She is happy,
He is sad.

3. Input File 2 file2.txt
go for a run.
she. is 'cheerful'
he is sorrowful.

4. Tuple Size N = 3

## Sample Output:
Plagiarism detected: 100.00 % 

Justification of output: All the tuples of size 3 present in File 1 are present in File 2 in the form or synonyms or as the same text.

## Assumptions:
1. Tuples present between multiple lines should also be considered as in a real-world scenario. For example:
File.txt:
I am happy
and sad
then the tuples of size 3 for this file would be "I am happy", "am happy and", "happy and sad".
2. Special characters like ";","." and others should not be considered and removed from the file before generating n-tuples.
3. If a tuple is present twice in the file it is counted twice and the percentage of plagiarism is calculated accordingly. (Refer Future Enhacement 1)
4. If the tuple size is greater than the number of words present in either file then the plagiarism won't be calculated and the user will be notified.

## Methodology:
1. This application contains different classes like TupleMatcher and FileHelper which can be reused and extented if needed in other contexts.
2. The algorithm is easily understandable as it is commented heavily to explain all its functionalities.

## Time Complexity:
n = Tuple Size
w1 = number of words in file 1
w2 = number of words in file 2
f = number of tuples in file 1 = (w1 - n + 1)
g = number of tuples in file 2 = (w2 - n + 1)
then the complexity of the algorithm used is theta(f*g).

## Future Enhancements:
1. One can use HashSet to store the n-tuples of both files instead of ArrayList to compare the tuples of the files. 
   By using hashset, we can reduce the time taken for checking if a tuple is present in another file as insert() and contains() operations take O(1) time on a HashSet.
   However, we need to assume that the percentage is calculated for the unique tuples and a unique tuple is not counted more than once.
2. This application can be used to compare two websites or any two online articles/documents also by adding a text extractor for the website or online document and passing it to the same functions of tuple matcher and plagiarismc detector. 
