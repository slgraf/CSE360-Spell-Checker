import java.io.File;

public class SpellChecker {

		/******************************************
 			Setting up the SpellChecker Class
		 *********************************************/
		
			Manager manager;
			InputParser inputParser;
			List [] inputList;
			
			SpellChecker(){
			
				manager = new Manager();
				inputParser = new InputParser();
				inputList = new List[26];		
			}
		
		/******************************************
		 			Get Methods
		*********************************************/
		
			List[] getDictionary(){
				
				return manager.getDictionary();
			}
			
			List[] getInpuList(){
				
				return this.inputList;		
			}
			
			List[] getIgnore(){
				
				return manager.getIgnore();		
			}
			
			List[] getAdded(){
				
				return manager.getAdded();			
			}
			
			
		/******************************************
		 				Add Methods
		*********************************************/
			
			void addToDictionary(String word, int index){
				
				manager.addToDictionary(word,index);			
			}
			
			void addToIgnore(String word, int index){
				
				manager.addToIgnore(word,index);			
			}
			
			void addRemainngToDictionary(){
				
				manager.addRemainingToDictionary();			
			}
			
			void ignoreRemaining(){
				
				manager.ignoreRemaining();			
			}
	
		/********************************************************************
				Methods for creation of the dictionary and input list
		*********************************************************************/	
			
			void createDictionary(File dictionary){
				
				manager.createDictionary(dictionary);
			}
			
			void createInputList(File inputFile){
				
				inputParser.parse(inputList,inputFile);
				
				
			}
			
			
	
}
