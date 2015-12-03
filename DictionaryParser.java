/*
 * The DictionaryParser class can take in a file and an array of lists
 * It will fill it with any words that might be in the file. 
 * This is to be used with the dictionary.txt file 
 * which will be the format of word\n....word\nword
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryParser {
	
	//constructor does nothing since the DictionaryParse doesn't hold any data
	DictionaryParser()
	{}
	
	//parse will in-take the dictionary array and dictionary.txt file and fill the array with all of the words from the file
	public void parse(List[] dictionary, File file) throws FileNotFoundException
	{
		String line;
		int index;
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine())
		{
			line = scan.nextLine();
			line.trim();
			index = hash(line);
			try{
				dictionary[index].insert(line);
			}catch(NullPointerException e ) {
				e.printStackTrace();
				}
		}
		scan.close();
	}
	//Method to perform the hash function by taking the first letter 
	//and converting it's lower case to ascii and subtracting by 97
	//it subtracts by 97 because the alphabet starts at ascii value 97
	private int hash(String word)
	{
		char firstLetter;
		int ascii;
		firstLetter = word.charAt(0);
		ascii = (int) Character.toLowerCase(firstLetter);
		return ascii - 97;
	}
}
