package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

public class Model {
	
	private int NMAX = 100 ;
	private int TMAX = 7 ;
	
	private int segreto ; // numero da indovinare
	private int tentativi ; // tentativi già fatti
	
	private boolean inGame = false ;


	public Model() {
		this.inGame = false ;
	}
	
	/**
	 * Avvia una nuova partita, generando un nuovo numero segreto.
	 */
	public void newGame() {	
    	this.segreto = (int)(Math.random()*NMAX)+1 ;
    	
    	this.tentativi = 0 ;
    	this.inGame = true ;
	}
	
	/**
	 * Fai un tentativo di indovinare il numero segreto
	 * @param t valore numerico del tentativo
	 * @return 0 se è indovinato, +1 se è troppo grande, -1 se è troppo piccolo
	 */
	public int tentativo(int t) {
		
		if(!inGame) {
			throw new IllegalStateException("Partita non attiva") ;
		}
		if(t<1 || t>this.NMAX) {
			throw new InvalidParameterException("Tentativo fuori range") ;
		}
		
		this.tentativi++ ;
		
		if(t==this.segreto) 
			return 0 ;
		if(t<this.segreto)
			return -1 ;
		return +1 ;
		
	}

	public boolean isInGame() {
		return inGame;
	}
	
	public int getTentativi() {
		return this.tentativi ;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

	
}
