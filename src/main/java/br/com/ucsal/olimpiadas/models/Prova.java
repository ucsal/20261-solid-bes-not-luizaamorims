package br.com.ucsal.olimpiadas.models;

public class Prova {
    //classe de modelo, armazena apenas os dados da entidade, sem lógica de negócio.


    private long id;
	private String titulo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
