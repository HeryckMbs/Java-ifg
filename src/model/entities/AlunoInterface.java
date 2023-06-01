package model.entities;

import java.util.List;

public interface AlunoInterface {
	void insert(Aluno obj);
	void update(Aluno obj);
	void deleteById(Integer id);
	Aluno findById(Integer id);
	List<Aluno> findAll();
}
