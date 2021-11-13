package br.atos.acelera.generico;

import br.atos.acelera.dados.Materia;
import br.atos.acelera.dados.Pessoa;

public class MaquinaTipo <T  extends Pessoa, Y extends Materia>{
	
	
	public void tratarHumano( T pess) {		
		System.out.println("Maquina tratando "+pess.getNome());
	}
	
	public void tratarEstudo( Y mat) {		
		System.out.println("Maquina tratando "+mat.getDescricao());	
	}


}
