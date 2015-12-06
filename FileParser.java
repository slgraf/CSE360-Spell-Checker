import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author danielrydstrom2
 *
 */
public class FileParser 
{
	//constructor does nothing since the InputParser doesn't hold any data
		FileParser()
		{}
		//parse will in-take the inputList array and inputList.txt file and fill the array with all of the words from the file
		public void parse(List[] inputList, File file) throws FileNotFoundException
		{
			String line;
			int index;
			Scanner scan = new Scanner(file);
			scan.useDelimiter("[^\\p{Alpha}-|']+"); //remove any characters that aren't a series of letters that might contain either - or '
			while (scan.hasNext())
			{
				line = scan.next();
				index = hash(line);
				try{
					inputList[index].insert(line);
				}catch(ArrayIndexOutOfBoundsException e ) {}
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
