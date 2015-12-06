import java.io.File;
import java.io.FileNotFoundException;

/*
*	This class manages data access and manipulation.
*	It keeps all lists updated, so the information
*	presented to the user is current and accurate.  
*/

public class Manager{

	// data structures are an array of lists
	// there are 26 indices for each letter
	// of the alphabet. words are added 
	// to the appropriate index per their first
	// letter. from there they are inserted in
	// alphabetical order into the list.
	private List dictionary[];
	private List ignore[];
	private List added[];

	// used to parse the dictionary file
	private FileParser dictParser;

	// constructor for manager
	// instantiates private data
	public Manager(){
		dictionary = new List[26];
		ignore = new List[26];
		added = new List[26];
		for (int i = 0; i < 26; i++)
		{
			dictionary[i] = new List();
			ignore[i] = new List();
			added[i] = new List();
		}
		dictParser = new FileParser();
	}

	// returns dictionary as a string
	public String getDictionary(){
		String tmp = "";

		for(int index = 0; index < 26; index++){
				tmp+=dictionary[index].toString();
		}

		return tmp;
	}

	// returns ignored list as a string
	public String getIgnore(){
		String tmp = "";

		for(int index = 0; index < 26; index++){
			tmp+=ignore[index].toString();
		}

		return tmp;
	}

	// returns added list as a string
	public String getAdded(){
		String tmp = "";

		for(int index = 0; index < 26; index++){
			tmp+=added[index].toString();
		}

		return tmp;
	}

	// parses the dictionary file into the dictionary data structure
	public void createDictionary(File dict){
		try{
			dictParser.parse(dictionary, dict);
		}
		catch(FileNotFoundException e){}
	}

	// adds a word to the dictionary data structure
	public void addToDictionary(String word, int index){
		dictionary[index].insert(word);
		added[index].insert(word);
	}

	// adds the remaining words in the input list to the dictionary
	// data structure
	public void addRemaining(List[] inputList){
		for(int index = 0; index < 26; index++){
			dictionary[index] = dictionary[index].merge(inputList[index]);
			added[index] = added[index].merge(inputList[index]);
			inputList[index].deleteAll();
		}
	}

	// adds a word to the ignore list data structure
	public void addToIgnore(String word, int index){
		ignore[index].insert(word);		
	}

	// adds the remaining words in the input list to the ignore
	// data structure
	public void ignoreRemaining(List[] inputList){
		for(int index = 0; index < 26; index++){
			ignore[index] = ignore[index].merge(inputList[index]);
			inputList[index].deleteAll();
		}
	}

	// cross references the input list with the dictionary and 
	// ignore data structures. any matching words are deleted
	// from the input list
	public void updateInputList(List inputList[]){
		for(int index = 0; index < 26; index++){
				inputList[index].delete(dictionary[index]);
				inputList[index].delete(ignore[index]);
		}
	}
} //end manager class
