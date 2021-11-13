package br.atos.acelera.anotacao;

import java.util.Optional;

import br.atos.acelera.comunica.BasicaStrategy;
import br.atos.acelera.comunica.NenhumaStrategy;
import br.atos.acelera.comunica.OauthStrategy;
import br.atos.acelera.comunica.ProcessoStrategy;
import br.atos.acelera.comunica.X509Strategy;

public enum Seguranca {
	BASICA("basica",new BasicaStrategy()),
	OAUTH("oauth", new OauthStrategy()),
	X509("x509",new X509Strategy()),
	NENHUMA(new NenhumaStrategy());
	
	private ProcessoStrategy processo;
	private String integacao;
	
	Seguranca(String nm, ProcessoStrategy procStrategy){
		this(procStrategy);
		this.integacao = nm; 
	}
	
	Seguranca(ProcessoStrategy procStrategy){
		this.processo = procStrategy;
	}
	
	public ProcessoStrategy getProcesso() {
		return this.processo;
	}
	
	public String getIntegracao() {
		return this.integacao; 
	}

	public static Seguranca parse(String nomeIntegracao) {
		
		for(Seguranca seguranca : Seguranca.values()) {
			if (Optional
					.ofNullable(seguranca.getIntegracao())
					.orElseGet(()->"")					
					.equals(nomeIntegracao)) {
				return seguranca;
			}
		}
		
		return Seguranca.NENHUMA;
	}	
	
	

}
