import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author danielrydstrom2
 *
 */
public class FileParser 
{
	private Scanner scan;
		//constructor sets the scanner as null until a file is added to parse
		FileParser()
		{
			scan = null;
		}
		/**
		 * parse will in-take the an array of lists and a file and
		 * fill the array with all of the words from the file
		 * @param newList: list to be created
		 * @param file: file to be parsed
		 * @throws FileNotFoundException: ensures a valid file is used
		 */
		public void parse(List[] newList, File file) throws FileNotFoundException
		{
			String line;
			int index;
			scan = new Scanner(file);
			scan.useDelimiter("[^\\p{Alpha}-|']+"); //remove any characters that aren't a series of letters that might contain either - or '
			while (scan.hasNext())
			{
				line = scan.next();
				addWord(newList, line);
			}
			scan.close();
		}
		/**
		 * Method to perform the hash function by taking the first letter 
		 * and converting it's lower case to ascii and subtracting by 97
		 * it subtracts by 97 because the alphabet starts at ascii value 97
		 * @param word: determines which list to insert into
		 * @return
		 */
		private int hash(String word)
		{
			char firstLetter;
			int ascii;
			firstLetter = word.charAt(0);
			ascii = (int) Character.toLowerCase(firstLetter);
			return ascii - 97;
		}
		/**
		 * addWord will take the word and then add it to the List
		 * it checks if it is a valid input first if not, it will
		 * be checked with another delimiter that removes known
		 * false positives and then tried again
		 * @param inputList: list to insert the word into
		 * @param word: word to be inserted
		 */
		private void addWord(List[] newList, String word)
		{
			if (word.compareTo("") != 0)
			{
				int index = hash(word);
				try{
					newList[index].insert(word);
				}
				catch(ArrayIndexOutOfBoundsException e ){
					addWord(newList, word.replaceAll("\\-|\\'|\\|", "")); //removes the flags
				}
			}
		}
}
