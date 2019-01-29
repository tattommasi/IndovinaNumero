/**
 * Sample Skeleton for 'IndoNumero.fxml' Controller Class
 */

package it.polito.tdp.indonumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {

	private int NMAX = 100;
	private int TMAX = 7;
	
	private int secret; // number to guess
	private int attempts; // attempts already done by the user
	
	boolean inGame = false;
		
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNew"
    private Button btnNew; // Value injected by FXMLLoader

    @FXML // fx:id="txtCurrent"
    private TextField txtCurrent; // Value injected by FXMLLoader

    @FXML // fx:id="txtMax"
    private TextField txtMax; // Value injected by FXMLLoader

    @FXML // fx:id="boxGame"
    private HBox boxGame; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumber"
    private TextField txtNumber; // Value injected by FXMLLoader

    @FXML // fx:id="txtLog"
    private TextArea txtLog; // Value injected by FXMLLoader

    @FXML
    void handleNew(ActionEvent event) {
    	this.secret = (int)(Math.random()*NMAX)+1;
    	this.attempts = 0;
    	this.inGame = true;

    	btnNew.setDisable(true);
    	boxGame.setDisable(false);
    	txtCurrent.setText(String.format("%d", this.attempts));
    	txtMax.setText(String.format("%d", this.TMAX));
    	
		txtLog.clear();
		txtNumber.clear();
		txtLog.setText(String.format("Guess a number between %d and %d\n", 1, NMAX));
    }

    @FXML
    void handleTry(ActionEvent event) {
    	String numS = txtNumber.getText();
    	if(numS.length()==0) {
    		txtLog.appendText("You need to choose a number!\n");
    		return;
    	}
    	try {
    		int num = Integer.parseInt(numS);
    		if(num==this.secret) {
    			txtLog.appendText("Correct, you won!\n");
    			boxGame.setDisable(true);
    			btnNew.setDisable(false);
    			//this.inGame = false;
    			//txtLog.clear();
    			//txtNumber.clear();
    			//txtLog.setText(String.format("Guess a number between %d and %d\n", 1, NMAX));
    		}else {
    			this.attempts++;
    			txtCurrent.setText(String.format("%d", this.attempts));
    			
    			if (this.attempts == this.TMAX) {
    				txtLog.appendText(String.format("Sorry, game over! The number was: %d\n", this.secret));
        			boxGame.setDisable(true);
        			btnNew.setDisable(false);
        			//this.inGame = false;
        			//txtLog.clear();
        			//txtNumber.clear();
        			//txtLog.setText(String.format("Guess a number between %d and %d\n", 1, NMAX));
        			
    			}else {
        			if(num>this.secret) {
        				txtLog.appendText("Too big\n");
        			}else {
        				txtLog.appendText("Too small\n");
        			}
    			}
    			

    			
    		}
    	}catch(NumberFormatException ex) {
    		txtLog.appendText("Add a number, not a character\n");
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNew != null : "fx:id=\"btnNew\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtCurrent != null : "fx:id=\"txtCurrent\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtMax != null : "fx:id=\"txtMax\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert boxGame != null : "fx:id=\"boxGame\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtNumber != null : "fx:id=\"txtNumber\" was not injected: check your FXML file 'IndoNumero.fxml'.";
        assert txtLog != null : "fx:id=\"txtLog\" was not injected: check your FXML file 'IndoNumero.fxml'.";

    }
}
