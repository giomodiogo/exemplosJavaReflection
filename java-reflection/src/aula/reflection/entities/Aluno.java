package aula.reflection.entities;

import aula.reflection.annotations.MyDBFieldName;
import aula.reflection.annotations.MyInvoke;
import aula.reflection.annotations.MyNotNull;
import aula.reflection.interfaces.IContrato;
import aula.reflection.interfaces.IDever;
import aula.reflection.interfaces.IDireito;

public /* abstract */ class Aluno extends Pessoa implements IContrato, IDever, IDireito {

	@MyDBFieldName(name = "aluno_aprovado")
	@MyNotNull
	private boolean aprovado;
	@MyDBFieldName(name = "aluno_turma")
	@MyNotNull
	public String turma;

	public Aluno() {

	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public String getTurma() {
		return turma;
	}

	public void setTurma(String turma) {
		this.turma = turma;
	}

	public Aluno(boolean aprovado) {
		this.aprovado = aprovado;
	}

	@Override
	@MyInvoke
	public void assinarContrato() {
		System.out.println("Assinar contrato.");
	}

	public void mostrarNotas(String bimestre) {
		System.out.println("Mostrando notas do " + bimestre + "o Bimestre");
	}
}
