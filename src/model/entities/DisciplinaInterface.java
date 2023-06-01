package model.entities;

import java.util.List;

public interface DisciplinaInterface {
	void insert(Disciplina obj);
	void update(Disciplina obj);
	void deleteById(Integer id);
	Disciplina findById(Integer id);
	List<Disciplina> findAll();
}
