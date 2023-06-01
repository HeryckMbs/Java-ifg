package model.entities;

import java.util.List;

public interface CursoInterface {
	
	void insert(Curso obj);
	void update(Curso obj);
	void deleteById(Integer id);
	Curso findById(Integer id);
	List<Curso> findAll();

}
