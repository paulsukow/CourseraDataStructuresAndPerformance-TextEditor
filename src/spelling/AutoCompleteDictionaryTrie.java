package spelling;

import java.util.List;
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
	public boolean addWord(String word) {
		word = word.toLowerCase();
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char key = word.charAt(i);
            node = node.getChild(key) != null ? node.getChild(key) : node.insert(key);

            if (i == word.length() - 1 && !node.endsWord()) {
                node.setEndsWord(true);
                size++;
                return true;
            }
        }

        return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size() {
        return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) {
        s = s.toLowerCase();
        char key;
        TrieNode node = root;

        for (int i = 0; i < s.length(); i++) {
            key = s.charAt(i);
            node = node.getChild(key);

            if (node == null) {
                return false;
            }

            if (i == s.length() - 1 && node.endsWord()) {
                return true;
            }
        }
		return false;
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
     public List<String> predictCompletions(String prefix, int numCompletions) {
        TrieNode stem = root;
        LinkedList<String> words = new LinkedList<>();

        for (int i = 0; i < prefix.length(); i++) {
            char key = prefix.charAt(i);
            stem = stem.getChild(key);

            if (stem == null) {
                return words;
            }
        }

        LinkedList<TrieNode> nodes = new LinkedList<>();
        nodes.add(stem);

        while (words.size() < numCompletions && nodes.size() > 0) {
            TrieNode node = nodes.remove(0);

            if (node.endsWord()) {
                words.add(node.getText());
            }

            for (char c : node.getValidNextCharacters()) {
                nodes.add(node.getChild(c));
            }
        }
        return words;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr) {
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