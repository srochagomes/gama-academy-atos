package br.atos.acelera.dados;

import java.util.Date;
import java.util.Objects;

public class Professor extends Funcionario {
	

	private static final int SALARIO_BONUS = 3000;
	private static final int IDADE_BONUS = 30;





	public Professor(String aNome, short aIdade, Date aNascimento, String materia, int identificador,
			int mediaNotasAlunos, int notaBimestre, double salario, int qtDiasTrabalhados, int qtMesesTrabalhados,
			int qtAnosTrabalhados) {
		super(aNome, aIdade, aNascimento);
		this.materia = materia;
		this.identificador = identificador;
		this.mediaNotasAlunos = mediaNotasAlunos;
		this.notaBimestre = notaBimestre;
		this.salario = salario;
		this.qtDiasTrabalhados = qtDiasTrabalhados;
		this.qtMesesTrabalhados = qtMesesTrabalhados;
		this.qtAnosTrabalhados = qtAnosTrabalhados;
	}

	private String materia;
	private int identificador;
	private int mediaNotasAlunos;
	private int notaBimestre;
	private double salario;
	private int qtDiasTrabalhados;
	private int qtMesesTrabalhados;
	private int qtAnosTrabalhados;	
	
	
	
	
	
	void lecionar() {		
		
	}

	public void dormir() {
		System.out.println("Professor dormindo 5hrs");
		
	}
	
	public static class ProfessorBuilder{
		private String nomeBuilder;
		private short idadeBuilder;
		private Date nascimentoBuilder;		
		private String materiaBuilder;
		private int identificadorBuilder;
		private int mediaNotasAlunosBuilder;
		private int notaBimestreBuilder;
		private double salarioBuilder;
		private int qtDiasTrabalhadosBuilder;
		private int qtMesesTrabalhadosBuilder;
		private int qtAnosTrabalhadosBuilder;
		
		
		public static ProfessorBuilder iniciar() {
			return new ProfessorBuilder();	
		}
		
		public Professor construir() {
			return new Professor(this.nomeBuilder, this.idadeBuilder, 
					this.nascimentoBuilder, this.materiaBuilder, this.identificadorBuilder,
					this.mediaNotasAlunosBuilder, this.notaBimestreBuilder, this.salarioBuilder, 
					this.qtDiasTrabalhadosBuilder, this.qtMesesTrabalhadosBuilder, this.qtAnosTrabalhadosBuilder);	
		}

		public ProfessorBuilder comQtAnosTrabalhados(int aQtAnosTrabalhados) {
			this.qtAnosTrabalhadosBuilder = aQtAnosTrabalhados;
			return this;
		}
		
		public ProfessorBuilder comQtMesesTrabalhados(int aQtMesesTrabalhados) {
			this.qtMesesTrabalhadosBuilder = aQtMesesTrabalhados;
			return this;
		}
		
		public ProfessorBuilder comQtDiasTrabalhados(int aQtDiasTrabalhados) {
			this.qtDiasTrabalhadosBuilder = aQtDiasTrabalhados;
			return this;
		}
		
		public ProfessorBuilder comSalario(double aSalario) {
			this.salarioBuilder = aSalario;
			return this;
		}

		public ProfessorBuilder comNotaBimestre(int aNotaBimestre) {
			this.notaBimestreBuilder = aNotaBimestre;
			return this;
		}
		
		public ProfessorBuilder comMediaNotasAlunos(int aMediaNotasAlunosBuilder) {
			this.mediaNotasAlunosBuilder = aMediaNotasAlunosBuilder;
			return this;
		}
		
		public ProfessorBuilder comIdentificador(int aIdentificador) {
			this.identificadorBuilder = aIdentificador;
			return this;
		}
		
		public ProfessorBuilder comNome(String aNome) {
			this.nomeBuilder = aNome;
			return this;
		}

		public ProfessorBuilder comIdade(short aIdade) {
			this.idadeBuilder = aIdade;
			return this;			
			
		}
		public ProfessorBuilder comNascimento(Date aDate) {
			this.nascimentoBuilder = aDate;
			return this;			
		}
		
		
		public ProfessorBuilder comMateria(String aMateria) {
			this.materiaBuilder = aMateria;
			return this;			
			
		}
		
		
				
	}
	
	

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public int getMediaNotasAlunos() {
		return mediaNotasAlunos;
	}

	public void setMediaNotasAlunos(int mediaNotasAlunos) {
		this.mediaNotasAlunos = mediaNotasAlunos;
	}

	public int getNotaBimestre() {
		return notaBimestre;
	}

	public void setNotaBimestre(int notaBimestre) {
		this.notaBimestre = notaBimestre;
	}

	public double getSalario() {
		System.out.println("pegou Salario");
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getQtDiasTrabalhados() {
		return qtDiasTrabalhados;
	}

	public void setQtDiasTrabalhados(int qtDiasTrabalhados) {
		this.qtDiasTrabalhados = qtDiasTrabalhados;
	}

	public int getQtMesesTrabalhados() {
		return qtMesesTrabalhados;
	}

	public void setQtMesesTrabalhados(int qtMesesTrabalhados) {
		this.qtMesesTrabalhados = qtMesesTrabalhados;
	}

	public int getQtAnosTrabalhados() {
		return qtAnosTrabalhados;
	}

	public void setQtAnosTrabalhados(int qtAnosTrabalhados) {
		this.qtAnosTrabalhados = qtAnosTrabalhados;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		
		return identificador == other.identificador;
	}

	public boolean temBonus() {		
		return this.getIdade() == IDADE_BONUS 
				&& this.getSalario()< SALARIO_BONUS;
	}

	public boolean deveTrabalhar() {		
		return this.getQtDiasTrabalhados() < 365;
	}

	public void adicionaDiasTrabalhados(int dias) {
		this.setQtDiasTrabalhados(
				this.getQtDiasTrabalhados()+10);
		
	}
	
	
	

}
