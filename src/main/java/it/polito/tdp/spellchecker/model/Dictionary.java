package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {

	List <String> dizionario;

	public Dictionary() {
		dizionario = new LinkedList <String>();
	}

	public void loadDictionary(String language) {
		this.dizionario.clear();
		
		if(language.equals("Italian")) {
			try {
				FileReader fr = new FileReader("English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					// Aggiungere parola alla struttura dati
					dizionario.add(word);	
				}
				br.close();
				} catch (IOException e){				
					System.out.println("Errore nella lettura del file 'English.txt'");
				}
			System.out.println(" lettura del file 'English.txt'");
			}
		
		else if(language.equals("English")) {
			try {
				FileReader fr = new FileReader("Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					dizionario.add(word);				
				}
				br.close();
				} catch (IOException e){ 
					System.out.println("Errore nella lettura del file 'Italian.txt'");
				}
			System.out.println("lettura del file 'Italian.txt'");
			}
	}
	
	
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		 List<RichWord> outputList = new LinkedList <RichWord>();
		 
		 for(String s: inputTextList) {
			 if(!this.dizionario.contains(s)) {
				 outputList.add(new RichWord(s, false));
			 } else {
				 outputList.add(new RichWord(s, true)); 
			 }
		 }
		 return outputList;
		 
		
	}
	
}
