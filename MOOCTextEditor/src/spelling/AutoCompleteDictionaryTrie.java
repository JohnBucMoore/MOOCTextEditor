package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{	
		if (word == null) {
			throw new NullPointerException();
		}
		
		word = word.toLowerCase();
		TrieNode curr = root;
		TrieNode next = new TrieNode();
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			
			if (i == 0 && curr.getChild(c) == null) {
				next = curr.insert(c);
			} else if (i == 0) {
				next = curr.getChild(c);
			} else if (i != 0 && next.getChild(c) == null) {
				next = next.insert(c);
			} else {
				next = next.getChild(c);
			}
			
			//set end of word
			if (i == word.length() - 1 && next.endsWord()) {
				return false;
			} else if (i == word.length() - 1 && !next.endsWord()) {
				next.setEndsWord(true);
			}
		}
		return true;
	}
	
	
	private int countWords(TrieNode curr) {
		
		if (curr == null) {
			return size;
		}
	 		 		
	 	TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			if (next.endsWord()) {
 				size++;
 				//System.out.println(next.getText() + "\t" + size);
 			}
 			countWords(next);
 		}
 		return size;
	}
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return countWords(root);
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{	
		s = s.toLowerCase();
		TrieNode curr = root;
		TrieNode next = new TrieNode();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (i == 0) {
				next = curr.getChild(c);
			} else {
				next = next.getChild(c);
			}
			
			if (next == null) {
				return false;
			}
		}
		return (!s.equals("") && s.compareTo(next.getText()) == 0);
	}

    public TrieNode getPrefix(String prefix) {
   	 TrieNode curr = root;
   	 for (int i = 0; i < prefix.length(); i++) {
   		 char c = prefix.charAt(i);
   		 if (curr.getChild(c) == null) {
   			 return null;
   		 }
   		 curr = curr.getChild(c);
   	 }
   	 return curr;
    }
	
	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 List<String> completions = new LinkedList<String>();
    	 TrieNode curr = getPrefix(prefix);
    	 
    	 if (curr == null || numCompletions == 0) {
    		 return completions;
    	 }
    	 //add TrieNodes to search next level if we complete our first level
    	Queue< TrieNode > q = new LinkedList< TrieNode >();
    	// add prefix node to q
    	if (curr.endsWord()) {
    		completions.add(curr.getText());
    	}
    	q.add(curr);
    	while(!q.isEmpty()) {
    		curr = q.remove();
    		for (char c : curr.getValidNextCharacters()) {
    			if (curr.getChild(c) != null) {
    				q.add(curr.getChild(c));
    				if (curr.getChild(c).endsWord()) {
    					completions.add(curr.getChild(c).getText());
    					if (completions.size() == numCompletions) {
    						q.clear();
    		    			return completions;
    		    		}
    				}
    			}
    		}
   		}
        return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}