import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class HashClass {
	
	private WordList[] wordListArray;
	int size;
	
	HashClass(int size){
		wordListArray = new WordList[size];
		for(int i = 0 ; i< size ; i++) {
			wordListArray[i] = new WordList();
		}
		this.size = size;
	}
	
	public void readFile(File file) {
		String essay = "";
		try {
			Scanner sc = new Scanner(file);
			String line;
			while(sc.hasNextLine()) {
				line = sc.nextLine();
				essay+=line+"\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String [] arrEssay = essay.split("\\W+");
		for(String str : arrEssay) {
			if(str!=null)
				str = str.toLowerCase();
			String word = str;
			increase(word);
		}
		
	}
	
	public void readClass(String strUrl) {
		String essay="";
		try {
			URL url = new URL(strUrl);
			Scanner sc = new Scanner(url.openStream());
			String line;
			while(true) {
				line = sc.nextLine();
				essay += line+"\n";
				if(line == null) {
					break;
				}
			}
		} catch (Exception e) {}
		
		String[] arrEssay = essay.split("\\W+");
		for(String str : arrEssay) {
			if(str!=null)
				str = str.toLowerCase();
			String word = str;
			increase(word);
		}
	}
	
	void display() {
		String output = "";
		for(int i = 0 ; i < wordListArray.length ; i++) {
			output += "location:" + i+" ";
			Word wordList = wordListArray[i].start;
			while(wordList.next!=null) {
				output += wordList.index + "." + wordList.count + "->";
				wordList = wordList.next;
			}
			output+="\n";
		
		}
		System.out.println(output);
	}	
	void writeInFile() {
		String output = "";
		for(int i = 0 ; i < wordListArray.length ; i++) {
			output += "location:" + i+" ";
			Word wordList = wordListArray[i].start;
			while(wordList.next!=null) {
				output += wordList.index + "." + wordList.count + "->";
				wordList = wordList.next;
			}
			output+="\n";
		}
		try {
			File file = new File("output.txt");
			FileWriter fw = new FileWriter(file);
			fw.write(output);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int hashVal(String index) {
		if(index!=null) {
			index = index.toLowerCase();
			int total = 0;
			for(int i = 0 ; i< index.length() ; i++) {
				int asciiOfChar  = (int) index.charAt(i);
				total += asciiOfChar;
			}
			return total%size;
		}
		return 0;
	}
	
	// OFFICIAL FUNCTIONS //
	public void delete(String index) {
		if(index!=null) {
			index = index.toLowerCase();
			// find and delete the index. or point to the other shit
			int hash = hashVal(index);
			if(wordListArray[hash]!=null) {
				wordListArray[hash].deleteWordFromList(index);
			}
		}
	}
	public boolean find(String index) {
		if(index!=null) {
			// do search in wordListArray and return its index
			index = index.toLowerCase();
			int hash = hashVal(index);
			return (wordListArray[hash].searchWord(index) != null);
		}
		return false;
	}
	
	public void insert(String index, int value){
		if(index!=null) {
			index = index.toLowerCase();
			int hash = hashVal(index);
			wordListArray[hash].insertOrUpdateWordInList(index, value);
		}
	}
	public void increase(String index) {
		if(index!=null) {
			index = index.toLowerCase();
			int hash = hashVal(index);
			wordListArray[hash].insertOrUpdateWordInList(index, 1);
		}
	}
	
	public class Word{
		int count;
		String index;
		Word next;
		
		Word(){
			index = null;
			count = 1;
		}
		Word(String index){
			if(index!=null)
				index = index.toLowerCase();
			this.index = index;
			count = 1;
			next = new Word();
		}
	}
	
	public class WordList{
		Word current;
		Word start;
		
		public WordList(){
			current = new Word(null);
			start = current;
		}
		
		public WordList(Word word){
			current = word;
		}
		
		void insertOrUpdateWordInList(String word, int increment){
			if(word!=null)
				word = word.toLowerCase();
			boolean flag = false;
			current = start.next;
			while(current.next != null) {
				if(current.index == null) {
					current = current.next;
				}
				else {
					if(current.index.equals(word)) {
						flag = true;
						current.count = current.count + increment;
						break;
					}
					current = current.next;
				}
			}
			if(flag == false) {
				current.index = word;
				current.count = increment;
				current.next = new Word();
			}
		}
		
		Word searchWord(String word) {
			if(word!=null)
				word = word.toLowerCase();
			current = start;
			current = current.next;
			while(current.next!=null) {
				if(!current.index.equals(word))
					current = current.next;
				else
					return current;
			}
			return null;
		}
		
		void deleteWordFromList(String word) {
			if(word!=null)
				word = word.toLowerCase();
			current = start;
			current = current.next;
			while(current.next!=null) {
				if(current.next.index !=null && current.next.index.equals(word)) {
					current.next = current.next.next;
					break;
				}
				current = current.next;
//				if(current.next!=null)
//					current.next = current.next.next;
			}
		}
	}
	
	public static void main(String [] args) {
		HashClass hs = new HashClass(701);
		hs.readClass("http://www.ccs.neu.edu/home/vip/teach/Algorithms//7_hash_RBtree_simpleDS/hw_hash_RBtree/alice_in_wonderland.txt");
		hs.writeInFile();
		hs.increase("dhruv");
		hs.display();
		System.out.println(hs.find("double"));
		hs.delete("double");
		System.out.println(hs.find("double"));
	}
}
