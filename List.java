/**
 * The List class implements the linked list using String as the word
 * It provides insertion, deletion, merge, and toString() methods.
 * to process word from the linked list 
 * The class is made of up a list which holds the root node of the linked list
 * an int numNodes which is the number of nodes in the linked list
 * the List class also holds the Node class which is used to implement the linked list
 * Noted Variable names:
 * temp is a temporary String object
 * iterator is a Node used to iterate through the list
 * behind is a node that is always one node behind the iterator
 */

public class List {
	private Node list;
	private int numNodes;

	/**
	 * Constructor sets the list to null to represent and empty list
	 * and sets the numNodes to 0 again an empty list
	 */
	List () 
	{
		list = null;
		numNodes = 0;
	}
	
	/**
	 * Inserts the parameter into the List object so the values in the List object are in 
	 * ascending order by String and then by number  
	 * If the value is already in the array, do not insert it.  
	 * @param inserted_word the Comparable object to insert
	 */
	public void insert (String inserted_word) 
	{
		Node iterator;
		Node behind;
		Node inserted_node = new Node(inserted_word);
		boolean is_inserted = false;
		if (list == null)
		{
			list = inserted_node;
			numNodes++;
		}
		else
		{
			iterator = list;
			behind = iterator;
			if (iterator.word.compareToIgnoreCase(inserted_word) > 0)
			{
				inserted_node.next = list;
				list = inserted_node;
				numNodes++;
			}
			else
			{
				while (iterator != null && !is_inserted) //iterate the list until a position is found or the end
				{
					if (iterator.word.compareToIgnoreCase(inserted_word) > 0)
					{
						behind.next = inserted_node;
						inserted_node.next = iterator;
						numNodes++;
						is_inserted = true; //finish the method word inserted
					}
					else if(iterator.word.compareToIgnoreCase(inserted_word) == 0)
					{
						is_inserted = true; //finish the method word is already in the list
					}
					behind = iterator;
					iterator = iterator.next;
				}
				if (!is_inserted) //end of list reached and was not inserted
				{
					behind.next = inserted_node;
					numNodes++;
				}
			}
		}
	}
	
	/**
	 * delete the string from the list that is input
	 * @param deleted_word; word to be deleted
	 */
	public void delete (String deleted_word) 
	{
		Node iterator;
		Node behind;
		boolean is_deleted = false;
		if (list == null)
		{
			// list is empty, nothing to delete
		}
		else
		{
			iterator = list;
			behind = list;
			while (iterator != null && !is_deleted) //iterates the list until a position is found or the end
			{
				if (iterator.word.compareToIgnoreCase(deleted_word) == 0) 
				{
					if (list == iterator) //the first word is a match and needs to be deleted
						list = list.next;
					else
						behind.next = iterator.next; //skip over the iterator and allow garbage collector to sweep it up
					behind = iterator;
					iterator = iterator.next;
					is_deleted = true;
					numNodes--;
				}
				else if(iterator.word.compareToIgnoreCase(deleted_word) > 0) //the word isn't in the list nothing to delete
				{
					is_deleted = true;
				}
				else //No action is required and the iterator can move forward
				{
					behind = iterator;
					iterator = iterator.next;
				}
			}
		}
	}
	
	/**
	 * delete(List) will remove everything in this.list that matches the deleted_list
	 * @param deleted_List: list of words to delete
	 */
	public void delete (List deleted_List) 
	{
		Node iterator;
		Node behind;
		Node deleted_word;
		boolean is_deleted = false;
		if (list == null || deleted_List == null)
		{
			// list is empty, nothing to delete
		}
		else
		{
			deleted_word = deleted_List.list;
			iterator = list;
			behind = list;
			while (iterator != null && !is_deleted) //iterates the list until a position is found or the end
			{
				if (deleted_word == null) //no more words to delete the loop can end
				{
					is_deleted = true;
				}
				else if (iterator.word.compareToIgnoreCase(deleted_word.word) == 0) 
				{
					if (list == iterator) //the first word is a match and needs to be deleted
						list = list.next;
					else
						behind.next = iterator.next; //skip over the iterator and allow garbage collector to sweep it up
					deleted_word = deleted_word.next;
					behind = iterator;
					iterator = iterator.next;
					numNodes--;
				}
				else if(iterator.word.compareToIgnoreCase(deleted_word.word) > 0) //the iterator has reach a point where this.list's words are farther in the alphabet than the iterator
				{
					deleted_word = deleted_word.next;
				}
				else //No action is required and the iterator can move forward
				{
					behind = iterator;
					iterator = iterator.next;
				}
			}
		}
	}
	/**
	 * Completely wipes the list clean of any words
	 */
	public void deleteAll ()
	{
		this.list = null;
	}
	
	/**
	 * Return the number of Comparable objects are in the List object
	 * @return count of Comparable objects
	 */
	public int count () 
	{
		return numNodes;
	}
	
	/**
	 * Merge the current List object with the parameter List object.  
	 * The results should be a new List object in ascending order with no duplicates.
	 * @param list List object to merge with the current List object
	 * @return new List object with merged values
	 */
	public List merge (List newList) 
	{
		List mergedList = new List();
		Node thisiterator = newList.list;
		Node newiterator = list;
		Node mergediterator = mergedList.list;
		
		while (thisiterator != null && newiterator != null)
		{
			//if this word is less than the new word add this word
			if (thisiterator.word.compareToIgnoreCase(newiterator.word) < 0) { 
				addToList(mergedList, mergediterator, thisiterator);
				thisiterator = thisiterator.next;
			}
			//if the new word is less than this word add the new word
			else if (thisiterator.word.compareToIgnoreCase(newiterator.word) > 0) {
				addToList(mergedList, mergediterator, newiterator);
				newiterator = newiterator.next;
			}
			else
				thisiterator = thisiterator.next; //the words are the same skip to the next word
		}
		
		while (thisiterator != null) {
			//newiterator reached the end of it's list and now it needs to add the rest of thisList to the merged list
			addToList(mergedList, mergediterator, thisiterator);
			thisiterator = thisiterator.next;
		}
		
		while (newiterator != null) {
			//thisiterator reached the end of it's list and now it needs to add the rest of newList to the merged list
			addToList(mergedList, mergediterator, newiterator);
			newiterator = newiterator.next;
		}
		mergedList.numNodes = numNodes + newList.numNodes;
		return mergedList;	
	} 
	/**
	 * method to add to a list given the final node and the head used for the merge
	 * @param mainList: the main list of that the node will be added to
	 * @param mainIterator: an iterator indicating the last position of the list
	 * @param addedNode: the node to be added to the list
	 */
	private void addToList(List mainList, Node mainIterator, Node addedNode)
	{
		if (mainIterator == null)
		{
			mainList.insert(addedNode.word);
			mainIterator = mainList.list;
		}
		else
		{
			mainIterator.next = new Node(addedNode.word);
			mainIterator = mainIterator.next;
		}
	}
	/**
	 * Search will search for the list for the given word a
	 */
	public boolean search (String searching) 
	{
		Node iterator = list;
		int pos = 0;
		boolean found = false;
		while (pos < numNodes && !found)
		{
			if (iterator.word.compareToIgnoreCase(searching) == 0)
			{
				found = true;
			}
			else
			{
				pos ++;
				iterator = iterator.next;
			}
		}
		return found;
	}
	
	/**
	 * Returns a string of the words in the list separated by the \n char
	 */
	 public String toString() 
	 {
		String outputString = "";
		Node iterator = list;
		while (iterator != null)
		{
			outputString = outputString + iterator.word;
			outputString = outputString + "\n";
			iterator = iterator.next;
		}
		return outputString;
	 }
	 
	 /**
	  * 
	  * The Node class is implemented in the linked list to store all the words
	  *
	  */
	 private class Node
	 {
		 private String word= "";
		 private Node next = null;
		 Node(String new_word)
		 {
			 word = new_word;
		 }
	 }
}