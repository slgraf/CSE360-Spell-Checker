import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ListTest {
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testList() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		List dictionary = new List();
		dictionary.insert("the");
		dictionary.insert("be");
		dictionary.insert("and");
		dictionary.insert("of");
		dictionary.insert("a");
		dictionary.insert("in");
		dictionary.insert("to");
		dictionary.insert("have");
		dictionary.insert("it");
		dictionary.insert("I");
		dictionary.insert("that");
		dictionary.insert("for");
		dictionary.insert("you");
		dictionary.insert("he");
		dictionary.insert("with");
		dictionary.insert("on");
		dictionary.insert("do");
		dictionary.insert("say");
		dictionary.insert("this");
		dictionary.insert("they");
		
		assertEquals("a\nand\nbe\ndo\nfor\nhave\nhe\nI\nin\nit\nof\non\nsay\nthat\nthe\nthey\nthis\nto\nwith\nyou\n", dictionary.toString());


	}

	@Test
	public void testDeleteString() {
		List inputList = new List();
		inputList.insert("the");
		inputList.insert("be");
		inputList.insert("and");
		inputList.insert("of");
		inputList.insert("a");
		inputList.insert("in");
		inputList.insert("to");
		inputList.insert("have");
		inputList.insert("it");
		inputList.insert("I");
		inputList.insert("that");
		inputList.insert("for");
		inputList.insert("you");
		inputList.insert("he");
		inputList.insert("with");
		inputList.insert("on");
		inputList.insert("do");
		inputList.insert("say");
		inputList.insert("this");
		inputList.insert("they");
		inputList.delete("I");
		inputList.delete("that");
		inputList.delete("for");
		inputList.delete("you");
		assertEquals("a\nand\nbe\ndo\nhave\nhe\nin\nit\nof\non\nsay\nthe\nthey\nthis\nto\nwith\n", inputList.toString());
		inputList.delete("the");
		inputList.delete("be");
		inputList.delete("and");
		inputList.delete("of");
		inputList.delete("a");
		inputList.delete("in");
		inputList.delete("to");
		inputList.delete("have");
		inputList.delete("it");
		inputList.delete("I");
		inputList.delete("that");
		inputList.delete("for");
		inputList.delete("you");
		inputList.delete("he");
		inputList.delete("with");
		inputList.delete("on");
		inputList.delete("do");
		inputList.delete("say");
		inputList.delete("this");
		inputList.delete("they");
		assertEquals("", inputList.toString());

	}

	@Test
	public void testDeleteList() {
		List dictionary = new List();
		List inputList = new List();
		inputList.insert("the");
		inputList.insert("be");
		inputList.insert("and");
		inputList.insert("of");
		inputList.insert("a");
		inputList.insert("in");
		inputList.insert("to");
		inputList.insert("have");
		inputList.insert("it");
		inputList.insert("I");
		inputList.insert("that");
		inputList.insert("for");
		inputList.insert("you");
		inputList.insert("he");
		inputList.insert("with");
		inputList.insert("on");
		inputList.insert("do");
		inputList.insert("say");
		inputList.insert("this");
		inputList.insert("they");
		dictionary.insert("I");
		dictionary.insert("that");
		dictionary.insert("for");
		dictionary.insert("you");
		inputList.delete(dictionary);
		assertEquals("a\nand\nbe\ndo\nhave\nhe\nin\nit\nof\non\nsay\nthe\nthey\nthis\nto\nwith\n", inputList.toString());

	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testMerge() {
		List dictionary = new List();
		List inputList = new List();
		inputList.insert("the");
		inputList.insert("be");
		inputList.insert("and");
		inputList.insert("of");
		inputList.insert("a");
		inputList.insert("in");
		inputList.insert("to");
		inputList.insert("have");
		inputList.insert("it");
		inputList.insert("I");
		dictionary.insert("that");
		dictionary.insert("for");
		dictionary.insert("you");
		dictionary.insert("he");
		dictionary.insert("with");
		dictionary.insert("on");
		dictionary.insert("do");
		dictionary.insert("say");
		dictionary.insert("this");
		dictionary.insert("they");
		dictionary = dictionary.merge(inputList);
		assertEquals("a\nand\nbe\ndo\nfor\nhave\nhe\nI\nin\nit\nof\non\nsay\nthat\nthe\nthey\nthis\nto\nwith\nyou\n", dictionary.toString());
	}

	@Test
	public void testSearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
