package br.atos.acelera.exceptions;

public class AulaExceptionUnChecked extends RuntimeException {
	
	/**
	 * SeiralUID
	 */
	private static final long serialVersionUID = 1L;

	public AulaExceptionUnChecked(String string){
		super(string);
	}

	public AulaExceptionUnChecked(String string, Exception e) {
		super(string, e);
	}
	

}
