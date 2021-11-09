package teste;

import java.util.ArrayList;
import java.util.List;

public class Teste {
	private String texto;
	
	public Teste (String t){
		this.texto = t;
	}
	
	public static void main(String[] args) {
		
		Teste t = new Teste("teste");
		List<Teste> lista = new ArrayList<Teste>();
		while (true) {
			 
			t = new Teste("teste".concat(t.texto));
			lista.add(t);
			
			try {
				if (lista.size()%500==0) {
					Thread.sleep(1000l);	
				}
			
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
