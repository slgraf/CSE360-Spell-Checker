import java.io.File;
import java.io.FileNotFoundException;

public class SpellChecker {

		/******************************************
 			Setting up the SpellChecker Class
		 *********************************************/
		
			Manager manager;
			FileParser inputParser;
			List [] inputList;
			SpellChecker(){
			
				manager = new Manager();
				inputParser = new FileParser();
				inputList = new List[26];	
				for (int i = 0; i < 26; i++){
					inputList[i] = new List();
				}
			}
		
		/******************************************
		 			Get Methods
		*********************************************/
		
			String getDictionary(){
				
				return manager.getDictionary();
			}
			
			String getInputList(){
				
				String completeList = "";
				for (int i = 0; i < 26; i++)
				{
					completeList = completeList + inputList[i].toString() ;
				}
				return completeList;
			}
			
			String getIgnore(){
				
				return manager.getIgnore();		
			}
			
			String getAdded(){
				
				return manager.getAdded();			
			}
			
			
		/******************************************
		 				Add Methods
		*********************************************/
			
			void addToDictionary(String word){
				
				manager.addToDictionary(word,hash(word));
				inputList[hash(word)].delete(word);
			}
			
			void addToIgnore(String word){
				
				manager.addToIgnore(word,hash(word));	
				inputList[hash(word)].delete(word);
			}
			
			void addRemainingToDictionary(){
				
				manager.addRemaining(this.inputList);
				for (int i = 0; i < 26; i++){
					inputList[i].deleteAll();
				}
			}
			
			void ignoreRemaining(){
				
				manager.ignoreRemaining(this.inputList);	
				for (int i = 0; i < 26; i++){
					inputList[i].deleteAll();
				}
			}
	
		/********************************************************************
				Methods for creation of the dictionary and input list
		*********************************************************************/	
			
			void createDictionary(File dictionary){
				
				manager.createDictionary(dictionary);
			}
			
			void createInputList(File inputFile){
				
				try {
					inputParser.parse(inputList,inputFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				manager.updateInputList(inputList);
			}
			
			private int hash(String word)
			{
				char firstLetter;
				int ascii;
				firstLetter = word.charAt(0);
				ascii = (int) Character.toLowerCase(firstLetter);
				return ascii - 97;
			}
	
}
