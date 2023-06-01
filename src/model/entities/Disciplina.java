package model.entities;

public class Disciplina {
	int iddisciplina; 
	String nomedisciplina;
	int cargahoraria;
	public int getIddisciplina() {
		return iddisciplina;
	}
	public void setIddisciplina(int iddisciplina) {
		this.iddisciplina = iddisciplina;
	}
	public String getNomedisciplina() {
		return nomedisciplina;
	}
	public void setNomedisciplina(String nomedisciplina) {
		this.nomedisciplina = nomedisciplina;
	}
	public int getCargahoraria() {
		return cargahoraria;
	}
	public void setCargahoraria(String cargahoraria) {
		this.cargahoraria = Integer.parseInt(cargahoraria);
	}
	public Disciplina() {
		
	}
	
}
