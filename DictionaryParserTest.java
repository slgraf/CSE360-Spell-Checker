import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;

public class DictionaryParserTest {

	@Test
	public void testParse() throws IOException {
		List[] dictionary = new List[26];
		for (int i = 0; i < 26; i++)
		{
			dictionary[i] = new List();
		}
		DictionaryParser parser = new DictionaryParser();
		File file = new File("dictionary.txt");
		try {
			parser.parse(dictionary, file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*assertEquals("a\nabout\nall\nalso\nand\nas\nat",dictionary[0].toString());
		assertEquals("be\nbecause\nbut\nby",dictionary[1].toString());
		assertEquals("can\ncome\ncould",dictionary[2].toString());
		assertEquals("I\nif\nin\ninto\nit\nits",dictionary[8].toString());
		assertEquals("zebra",dictionary[25].toString());*/
		PrintWriter PrintWriter = new PrintWriter ("dictionary.txt");
		for (int i = 0; i < 25; i++)
		{
			if (dictionary[i].toString().compareTo("") != 0) //prevents empty lines from being written out
			{
				PrintWriter.println(dictionary[i].toString());
			}
		}
		//ensures there is is no empty line at the end of the dictionary file
		PrintWriter.print(dictionary[25].toString());
		PrintWriter.close();
	}

}
