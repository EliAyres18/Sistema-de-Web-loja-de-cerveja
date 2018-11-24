package sistema.crud.cerveja.sistema.loja.web;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity //uma estrutura de mapeamento objeto relacional e corresponde a uma refernecia a uma tabela
@Table(name="cerveja") //corresponde a tabela do banco

public class Cerveja {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;	
	@Column(name="nome")//coluna na tabela do banco
	private String nome;
	
	@Column(name="marca")
	private String marca;
	
	@Column(name="codigo")
	private int codigo;
	
	@Column(name="tipo")
	private String tipo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
		
}
