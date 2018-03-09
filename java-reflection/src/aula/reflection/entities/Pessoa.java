package aula.reflection.entities;

public class Pessoa {
	public String nome;
	private int idade;

	public Pessoa() {

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void mostrarRG() {
		System.out.println("Mostrar RG");
	}
}
