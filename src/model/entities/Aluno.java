package model.entities;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Aluno {
	String nome;
	int idAluno;
	String sexo ;
    Date dt_nas;
    double nota;
    public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSexo() {
		return sexo;
	}
	public Aluno() {
	
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getDt_nas() {
		return dt_nas;
	}
	public void setDt_nas(String dt_nas) {
		Date date1 = java.sql.Date.valueOf(dt_nas);
		this.dt_nas = date1;
	}
	public double getNota() {
		return nota;
	}
	public void setNota(double nota) {
		this.nota = nota;
	}
	public int getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	
}
