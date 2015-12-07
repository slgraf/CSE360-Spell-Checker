import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class SpellCheckerTest {

	@Test
	public void testSpellChecker() {
		SpellChecker inputListManager = new SpellChecker();
		assertNotNull(inputListManager.manager);
		assertNotNull(inputListManager.inputParser);
		assertNotNull(inputListManager.inputList);
	}

	@Test
	public void testGetDictionary() {
		SpellChecker inputListManager = new SpellChecker();
		File file = new File("dictionary.txt");
		inputListManager.createDictionary(file);
		String output = inputListManager.getDictionary();
		assertEquals("a\nabout\nall\nalso\nand\nas\nat\nbe\nbecause\nbut\ncan\ncome\ncould\nday\ndo\neven\nfirst\nfrom\nget\ngive\n"
				+ "go\nhave\nhe\nher\nhere\nhim\nhis\nhow\nI\nif\nin\ninto\nit\nits\njust\nknow\nlike\nlook\nmake\nman\nmany\nme\nmore\n"
				+ "my\nnew\nno\nnot\nof\non\none\nonly\nor\nother\nour\nout\npeople\nsay\nsee\nshe\nso\nsome\ntake\ntell\nthan\nthat\nthe\n"
				+ "their\nthem\nthen\nthere\nthese\nthey\nthing\nthink\nthis\nthose\ntime\nto\ntwo\nuse\nvery\nwant\nway\nwe\nwell\nwhat\nwhen\n"
				+ "which\nwho\nwill\nwith\nwould\nyear\nyou\nyour\n", output);
	}

	@Test
	public void testgetInputList() {
		SpellChecker inputListManager = new SpellChecker();
		File file2 = new File("dictionary.txt");
		inputListManager.createDictionary(file2);
		File file = new File("input.txt");
		inputListManager.createInputList(file);
		String output = inputListManager.getInputList();
		assertEquals("by\nfind\nfor\nnow\nup\n", output);
	}

	@Test
	public void testGetIgnore() {
		SpellChecker inputListManager = new SpellChecker();
		File file = new File("input.txt");
		inputListManager.createInputList(file);
		inputListManager.addToIgnore("by");
		inputListManager.addToIgnore("for");
		inputListManager.addToIgnore("now");
		String output = inputListManager.getIgnore();
		assertEquals("by\nfor\nnow\n", output);

	}

	@Test
	public void testGetAdded() {
		SpellChecker inputListManager = new SpellChecker();
		File file2 = new File("dictionary.txt");
		File file = new File("input.txt");
		inputListManager.createDictionary(file2);
		inputListManager.createInputList(file);
		inputListManager.addToDictionary("for");
		inputListManager.addToDictionary("by");
		inputListManager.addToDictionary("up");
		inputListManager.addToDictionary("now");
		inputListManager.addToDictionary("find");
		String output = inputListManager.getAdded();
		assertEquals("by\nfind\nfor\nnow\nup\n", output);
		assertEquals("", inputListManager.getInputList()); //test to see if the things added are taken from the input list
	}

	@Test
	public void testAddToDictionary() {
		SpellChecker inputListManager = new SpellChecker();
		File file2 = new File("dictionary.txt");
		inputListManager.createDictionary(file2);
		inputListManager.addToDictionary("for");
		inputListManager.addToDictionary("by");
		inputListManager.addToDictionary("up");
		assertEquals("a\nabout\nall\nalso\nand\nas\nat\nbe\nbecause\nbut\nby\ncan\ncome\ncould\nday\ndo\neven\nfirst\nfor\nfrom\nget\ngive"
				+ "\ngo\nhave\nhe\nher\nhere\nhim\nhis\nhow\nI\nif\nin\ninto\nit\nits\njust\nknow\nlike\nlook\nmake\nman\nmany\nme\nmore\nmy\nnew"
				+ "\nno\nnot\nof\non\none\nonly\nor\nother\nour\nout\npeople\nsay\nsee\nshe\nso\nsome\ntake\ntell\nthan\nthat\nthe\ntheir\nthem"
				+ "\nthen\nthere\nthese\nthey\nthing\nthink\nthis\nthose\ntime\nto\ntwo\nup\nuse\nvery\nwant\nway\nwe\nwell\nwhat\nwhen\nwhich\nwho"
				+ "\nwill\nwith\nwould\nyear\nyou\nyour\n", inputListManager.getDictionary());
		inputListManager.addToDictionary("now");
		inputListManager.addToDictionary("find");
		assertEquals("a\nabout\nall\nalso\nand\nas\nat\nbe\nbecause\nbut\nby\ncan\ncome\ncould\nday\ndo\neven\nfind\nfirst\nfor\nfrom\nget\ngive"
				+ "\ngo\nhave\nhe\nher\nhere\nhim\nhis\nhow\nI\nif\nin\ninto\nit\nits\njust\nknow\nlike\nlook\nmake\nman\nmany\nme\nmore\nmy\nnew"
				+ "\nno\nnot\nnow\nof\non\none\nonly\nor\nother\nour\nout\npeople\nsay\nsee\nshe\nso\nsome\ntake\ntell\nthan\nthat\nthe\ntheir\nthem"
				+ "\nthen\nthere\nthese\nthey\nthing\nthink\nthis\nthose\ntime\nto\ntwo\nup\nuse\nvery\nwant\nway\nwe\nwell\nwhat\nwhen\nwhich\nwho"
				+ "\nwill\nwith\nwould\nyear\nyou\nyour\n", inputListManager.getDictionary());
	}

	@Test
	public void testAddToIgnore() {
		SpellChecker inputListManager = new SpellChecker();
		File file = new File("input.txt");
		File file2 = new File("dictionary.txt");
		inputListManager.createDictionary(file2);
		inputListManager.createInputList(file);
		inputListManager.addToIgnore("by");
		inputListManager.addToIgnore("for");
		inputListManager.addToIgnore("now");
		assertEquals("by\nfor\nnow\n", inputListManager.getIgnore());
		assertEquals("find\nup\n", inputListManager.getInputList());
	}

	@Test
	public void testAddRemainngToDictionary() {
		SpellChecker inputListManager = new SpellChecker();
		File file = new File("input.txt");
		File file2 = new File("dictionary.txt");
		inputListManager.createDictionary(file2);
		inputListManager.createInputList(file);
		inputListManager.addRemainngToDictionary();
		assertEquals("a\nabout\nall\nalso\nand\nas\nat\nbe\nbecause\nbut\nby\ncan\ncome\ncould\nday\ndo\neven\nfind\nfirst\nfor\nfrom\nget\ngive"
				+ "\ngo\nhave\nhe\nher\nhere\nhim\nhis\nhow\nI\nif\nin\ninto\nit\nits\njust\nknow\nlike\nlook\nmake\nman\nmany\nme\nmore\nmy\nnew"
				+ "\nno\nnot\nnow\nof\non\none\nonly\nor\nother\nour\nout\npeople\nsay\nsee\nshe\nso\nsome\ntake\ntell\nthan\nthat\nthe\ntheir\nthem"
				+ "\nthen\nthere\nthese\nthey\nthing\nthink\nthis\nthose\ntime\nto\ntwo\nup\nuse\nvery\nwant\nway\nwe\nwell\nwhat\nwhen\nwhich\nwho"
				+ "\nwill\nwith\nwould\nyear\nyou\nyour\n", inputListManager.getDictionary());
		assertEquals("", inputListManager.getInputList());
	}

	@Test
	public void testIgnoreRemaining() {
		SpellChecker inputListManager = new SpellChecker();
		File file = new File("input.txt");
		File file2 = new File("dictionary.txt");
		inputListManager.createDictionary(file2);
		inputListManager.createInputList(file);
		assertEquals("by\nfind\nfor\nnow\nup\n", inputListManager.getInputList());
		inputListManager.ignoreRemaining();
		assertEquals("by\nfind\nfor\nnow\nup\n", inputListManager.getIgnore());
		assertEquals("", inputListManager.getInputList());
	}

	@Test
	public void testCreateDictionary() {
		SpellChecker inputListManager = new SpellChecker();
		File file = new File("dictionary.txt");
		inputListManager.createDictionary(file);
		String output = inputListManager.getDictionary();
		assertEquals("a\nabout\nall\nalso\nand\nas\nat\nbe\nbecause\nbut\ncan\ncome\ncould\nday\ndo\neven\nfirst\nfrom\nget\ngive\n"
				+ "go\nhave\nhe\nher\nhere\nhim\nhis\nhow\nI\nif\nin\ninto\nit\nits\njust\nknow\nlike\nlook\nmake\nman\nmany\nme\nmore\n"
				+ "my\nnew\nno\nnot\nof\non\none\nonly\nor\nother\nour\nout\npeople\nsay\nsee\nshe\nso\nsome\ntake\ntell\nthan\nthat\nthe\n"
				+ "their\nthem\nthen\nthere\nthese\nthey\nthing\nthink\nthis\nthose\ntime\nto\ntwo\nuse\nvery\nwant\nway\nwe\nwell\nwhat\nwhen\n"
				+ "which\nwho\nwill\nwith\nwould\nyear\nyou\nyour\n", output);
	}

	@Test
	public void testCreateInputList() {
		SpellChecker inputListManager = new SpellChecker();
		File file = new File("input.txt");
		File file2 = new File("dictionary.txt");
		inputListManager.createDictionary(file2);
		inputListManager.createInputList(file);
		String output = inputListManager.getInputList();
		assertEquals("by\nfind\nfor\nnow\nup\n", output); //verifies the inputList was updated with the items in the dictionary
	}

}
