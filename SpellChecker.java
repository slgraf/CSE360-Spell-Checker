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
				inputParser = new FileParser();
				inputList = new List[26];		
			}
		
		/******************************************
		 			Get Methods
		*********************************************/
		
			String getDictionary(){
				
				return manager.getDictionary();
			}
			
			String getInpuList(){
				
				String completeList = "";
				String inputListOutput;
				//PrintWriter PrintWriter = new PrintWriter ("dictionary.txt");
				for (int i = 0; i < 25; i++)
				{
					inputListOutput = inputList[i].toString();
					if (inputListOutput.compareTo("") != 0) //prevents empty lines from being written out
					{
						completeList = completeList + inputListOutput + "\n";
						//PrintWriter.println(inputList[i].toString());
					}
				}
				//ensures there is is no empty line at the end of the dictionary/input file
				inputListOutput = inputList[25].toString();
				if (inputListOutput.compareTo("") != 0) //prevents empty lines from being written out
				{
					completeList = completeList + inputListOutput;
				}
				//PrintWriter.print(inputList[25].toString());
				//PrintWriter.print(completeoutput);
				return completeList;
				//PrintWriter.close();	
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
			
			void addToDictionary(String word, int index){
				
				manager.addToDictionary(word,index);			
			}
			
			void addToIgnore(String word, int index){
				
				manager.addToIgnore(word,index);			
			}
			
			void addRemainngToDictionary(){
				
				manager.addRemaining(this.inputList);			
			}
			
			void ignoreRemaining(){
				
				manager.ignoreRemaining(this.inputList);			
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
			
			private int hash(String word)
			{
				char firstLetter;
				int ascii;
				firstLetter = word.charAt(0);
				ascii = (int) Character.toLowerCase(firstLetter);
				return ascii - 97;
			}
	
}
