package model.dao;

import model.db.DB;

public class FactoryDAO {

	public static CursoDAOImp createCursoDAO() {
		return new CursoDAOImp(DB.getConexao());
	}
	
	public static AlunoDAOImp createAlunoDAO() {
		return new AlunoDAOImp(DB.getConexao());
	}
	public static DisciplinaDAOImp createDisciplinaDAO() {
		return new DisciplinaDAOImp(DB.getConexao());
	}
//	public static CursoDAOImp createCursoDAO(String entitydao) {
//		return new CursoDAOImp(DB.getConexao());
//	}
}
