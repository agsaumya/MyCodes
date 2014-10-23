/**
 * 
 */
package com.mycodes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;



/**
 * @author sauagarwal
 * 
 * Demo to test the use of Trie to store dictionary of words for a spell checker
 *
 */
public class MySpellChecker {

	public class MyTrieNode{

		private char keyOfNode;
		private String word;
		private MyTrieNode[] childNodes = new MyTrieNode[26];

		//constructor of Node
		public MyTrieNode(){
			//childNodes = new MyTrieNode[256];
			for(int i=0; i< 26; i++)
				childNodes[i] = null;
			keyOfNode = ' ';
			word = "";			
		}

		// ----- start of getters/setters ---- 
		public char getKeyOfNode() {
			return keyOfNode;
		}

		public void setKeyOfNode(char keyOfNode) {
			this.keyOfNode = keyOfNode;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public MyTrieNode[] getChildNodes() {
			return childNodes;
		}

		public void setChildNodes(MyTrieNode[] childNodes) {
			this.childNodes = childNodes;
		}
		// ----- end of getters/setters ---- 

		//insert the current word in the Trie
		public void insertWord(String word, int pos, MyTrieNode root) {
			if(word.length() == pos){
				root.word = word;
				return;
			}

			if( root.childNodes[word.charAt(pos)-'a'] == null){
				//create a child node with char at word's current position as the key of that child node
				MyTrieNode node = new MyTrieNode();
				node.setKeyOfNode(word.charAt(pos));
				root.getChildNodes()[word.charAt(pos)-'a'] = node;	
				insertWord(word, pos+1, root.getChildNodes()[word.charAt(pos)-'a']);
			}
			else
				insertWord(word, pos+1, root.getChildNodes()[word.charAt(pos)-'a']);	

		}//end of insertWord method

		//print all words in the dictionary
		public void printAllWords(MyTrieNode root)
		{
			for(int i=0; i<26; i++)
			{
				if(root.getChildNodes()[i] != null)
					printAllWords(root.getChildNodes()[i]);
			}
			if(root.getWord() != "")
				System.out.println(root.getWord());
		}

		//print 100 words in the dictionary
		public int print10Words(MyTrieNode root, int count)
		{

			if(count == 0)
				return 0;
			if(root.getWord() != "")
			{
				System.out.println(root.getWord());
				return --count;
			}

			for(int i=0; i<26; i++)
			{
				if(root.getChildNodes()[i] != null)
					count = print10Words(root.getChildNodes()[i], count);				
			}

			return count;

		}

		public boolean findKey(MyTrieNode root, String key, int pos){
			if( !(root.getWord().equalsIgnoreCase(key)) && root.getChildNodes()[key.charAt(pos) - 'a'] != null)
				return findKey(root.getChildNodes()[key.charAt(pos) - 'a'], key, pos+1);
			else
				if(root.getWord().equalsIgnoreCase(key))
					return true;
			return false;		    
		}

		public void suggest(MyTrieNode root, String key, int pos){
			
			//System.out.println("pos:" + pos);
			/*if(key.length() == pos){
				return;
			}
			System.out.println(key.charAt(pos) - 'a');
			if(root.getChildNodes()[key.charAt(pos) - 'a']!=null)
				System.out.println("hell yeah");
			else
				System.out.println("hell nah");*/
			if( (!root.getWord().equalsIgnoreCase(key)) && key.length() != pos && root.getChildNodes()[key.charAt(pos) - 'a'] != null)
				suggest(root.getChildNodes()[key.charAt(pos) - 'a'], key, pos+1);
			else
				print10Words(root,10);
		}



	}//end of class Node

	public void initializeDictionary(MyTrieNode dictionary){
		File file = new File("wordlist.txt");
		try {
			BufferedReader buffer = new BufferedReader (new FileReader(file));
			String line;
			while ((line = buffer.readLine()) != null) {
				//System.out.println(line);
				line = line.replaceAll("[^a-z]","");
				//System.out.println(line);
				dictionary.insertWord(line, 0, dictionary);
			}
			buffer.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public void printDictionary(MyTrieNode dictionary)
	{
		dictionary.printAllWords(dictionary);
	}

	public void printFirst10Words(MyTrieNode dictionary)
	{
		dictionary.print10Words(dictionary,10);
	}

	public void findWord(MyTrieNode dictionary, String word){
		if(dictionary.findKey(dictionary,word,0))
			System.out.println("found");
		else
			System.out.println("not found");
	}

	public void spellCheck(MyTrieNode dictionary, String word){
		dictionary.suggest(dictionary,word,0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MySpellChecker spellChecker = new MySpellChecker();
		MyTrieNode dictionary = spellChecker.new MyTrieNode();
		spellChecker.initializeDictionary(dictionary);
		System.out.println("\nPrinting First 10 words of Dictionary : ");
		//spellChecker.printDictionary(dictionary);
		spellChecker.printFirst10Words(dictionary);
		System.out.println("\n Find word: abacus");
		spellChecker.findWord(dictionary, "abacus");
		System.out.println("Auto suggestion (spell check) for word: abcs");
		spellChecker.spellCheck(dictionary,"abcs");


	}//end of main method
}
