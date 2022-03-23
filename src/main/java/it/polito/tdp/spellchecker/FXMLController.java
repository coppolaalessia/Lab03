package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSpell;

    @FXML
    private ComboBox <String> cmbLingua;

    @FXML
    private TextArea txtInput;

    @FXML
    private Label txtNumErrori;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Label txtTempo;

    @FXML
    void doClearText(ActionEvent event) {
    	this.txtInput.clear();
    	this.txtOutput.clear();
    }
    
    public void setModel() {
    //	String lingua = this.cmbLingua.getValue();
    	this.model.loadDictionary(this.cmbLingua.getValue());
    }

    @FXML
    void doSpellCheck(ActionEvent event) {

    	String input = this.txtInput.getText().toLowerCase().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", ""); 
    	List <String> listaDaVerificare = new LinkedList <String>();
    	
    	StringTokenizer st = new StringTokenizer(input, " ");

		// Controllo su String Tokenizer (superfluo)
		if (!st.hasMoreElements()) {
			this.txtOutput.setText("Inserire una o più parole.");
			return;
		}

		while (st.hasMoreElements()) {
			listaDaVerificare.add(st.nextToken());
		}    	
		
		List<RichWord> result = this.model.spellCheckText(listaDaVerificare);
		
		for (RichWord r: result){
			if(!r.isCorretta()) {
				this.txtOutput.appendText(r.getParola() + "\n");
			}
		}
    	
    }
    
    
    
    @FXML
    void initialize() {
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLingua != null : "fx:id=\"cmbLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumErrori != null : "fx:id=\"txtNumErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbLingua.getItems().clear();
        cmbLingua.getItems().add("English");
        cmbLingua.getItems().add("Italian");
        
        model = new Dictionary();
    	
    }

}




