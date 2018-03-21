package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Model {
	
	private IntegerProperty NMAX = new SimpleIntegerProperty(100) ;
	private IntegerProperty TMAX = new SimpleIntegerProperty(7) ;
	
	private int segreto ; // numero da indovinare
	//private int tentativi ; // tentativi già fatti
	private IntegerProperty tentativi = new SimpleIntegerProperty();
	
	private BooleanProperty inGame = new SimpleBooleanProperty(false) ;


	public Model() {
		this.inGame.set(false) ;
	}
	
	/**
	 * Avvia una nuova partita, generando un nuovo numero segreto.
	 */
	public void newGame() {	
    	this.segreto = (int)(Math.random()*NMAX.get())+1 ;
    	
    	this.tentativi.set(0) ;
    	this.inGame.set(true) ;
	}
	
	/**
	 * Fai un tentativo di indovinare il numero segreto
	 * @param t valore numerico del tentativo
	 * @return 0 se è indovinato, +1 se è troppo grande, -1 se è troppo piccolo
	 */
	public int tentativo(int t) {
		
		if(!inGame.get()) {
			throw new IllegalStateException("Partita non attiva") ;
		}
		if(!valoreValido(t)) {
			throw new InvalidParameterException("Tentativo fuori range") ;
		}
		
		this.tentativi.set(this.tentativi.get()+1);
		if(this.tentativi.get()==this.TMAX.get()) {
			this.inGame.set(false) ;
		}
		
		if(t==this.segreto) {
			this.inGame.set(false) ;
			return 0 ;
		}
		if(t<this.segreto)
			return -1 ;
		return +1 ;
		
	}

	/**
	 * Controlla se il tentativo fornito rispetta le regole formali
	 * del gioco, cioè è nel range [1, NMAX]
	 * @param tentativo
	 * @return {@code true} se il tentativo è valido
	 */
	public boolean valoreValido(int tentativo) {
		return tentativo>=1 && tentativo<=this.NMAX.get() ;
	}
	
	
	
	public int getSegreto() {
		return this.segreto ;
	}

	public final IntegerProperty tentativiProperty() {
		return this.tentativi;
	}
	
	public final int getTentativi() {
		return this.tentativiProperty().get();
	}

	public final IntegerProperty NMAXProperty() {
		return this.NMAX;
	}
	

	public final int getNMAX() {
		return this.NMAXProperty().get();
	}
		

	public final IntegerProperty TMAXProperty() {
		return this.TMAX;
	}
	

	public final int getTMAX() {
		return this.TMAXProperty().get();
	}
	

	public final BooleanProperty inGameProperty() {
		return this.inGame;
	}
	

	public final boolean isInGame() {
		return this.inGameProperty().get();
	}
	

	public final void setInGame(final boolean inGame) {
		this.inGameProperty().set(inGame);
	}
	
	
	

}
