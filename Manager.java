import java.io.File;
import java.io.FileNotFoundException;



public class Manager{
	private List dictionary[];
	private List ignore[];
	private List added[];

	private FileParser dictParser;

	public Manager(){
		dictionary = new List[26];
		ignore = new List[26];
		added = new List[26];

		dictParser = new DictionaryParser();
	}

	public String getDictionary(){
		String tmp = "";

		for(int index = 0; index < 26; index++){
			tmp+=dictionary[index].toString();
			tmp+="\n";
		}

		return tmp;
	}

	public String getIgnore(){
		String tmp = "";

		for(int index = 0; index < 26; index++){
			tmp+=ignore[index].toString();
			tmp+="\n";
		}

		return tmp;
	}

	public String getAdded(){
		String tmp = "";

		for(int index = 0; index < 26; index++){
			tmp+=added[index].toString();
			tmp+="\n";
		}

		return tmp;
	}

	public void createDictionary(File dict){
		try{
			dictParser.parse(dictionary, dict);
		}
		catch(FileNotFoundException e){}
	}

	public void addToDictionary(String word, int index){
		dictionary[index].insert(word);		
	}

	public void addRemaining(List[] inputList){
		for(int index = 0; index < 26; index++){
			dictionary[index] = dictionary[index].merge(inputList[index]);
		}
	}

	public void addToIgnore(String word, int index){
		ignore[index].insert(word);		
	}

	public void ignoreRemaining(List[] inputList){
		for(int index = 0; index < 26; index++){
			ignore[index] = ignore[index].merge(inputList[index]);
		}
	}

	public void updateInputList(List inputList[]){

	}

}