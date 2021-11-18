package br.atos.acelera.exceptions;

public class AulaExceptionChecked extends Exception {
	
	/**
	 * SerialUID
	 */
	private static final long serialVersionUID = 1L;

	public AulaExceptionChecked(String string){
		super(string);
	}

	public AulaExceptionChecked(String string, Exception e) {
		super(string, e);
	}
	

}
