package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Curso;

public class CursoDAOImp implements CursoDAO {

	private Connection conexao;
	
	public CursoDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Curso obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO curso (nomecurso) VALUES (?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomeCurso());
			int linhas = pst.executeUpdate();
			
			if (linhas > 0) {
				rs = pst.getGeneratedKeys();
				rs.next();
				obj.setIdcurso(rs.getInt(1));
				System.out.println(obj.toString() + " foi criado com sucesso !");
			}
			else {
				System.out.println("Não foi possível cadastrar o curso !");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Curso obj) {
		String sql = "UPDATE curso SET nomeCurso = ? where idcurso = ?";
		PreparedStatement statement = null;
		ResultSet resultado = null;
		
		try {
			statement = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, obj.getNomeCurso());
			statement.setInt(2, obj.getIdcurso());
			int linhas = statement.executeUpdate();
			
			if (linhas > 0) {
				resultado = statement.getGeneratedKeys();
				resultado.next();
				obj.setIdcurso(resultado.getInt(1));
				System.out.println(obj.toString() + " foi atualizado com sucesso !");
			}
			else {
				System.out.println("Não foi possível atualizar o curso !");
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM curso where idcurso = ?";
		PreparedStatement statement = null;
		ResultSet resultado= null;
		
		try {
			statement = conexao.prepareStatement(sql);
			statement.setInt(1, id);
			int linhas = statement.executeUpdate();
			if(linhas > 0) {
				System.out.println("Curso excluido com sucesso");
			}else {
				System.out.println("Não foi possível excluir o curso");
			}
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public Curso findById(Integer id) {
		
		return null;
	}

	@Override
	public List<Curso> findAll() {
		String sql = "SELECT * FROM curso";
		PreparedStatement statement = null;
		ResultSet resultado = null;
		List<Curso> cursos = new ArrayList<Curso>();
		try {
			statement = conexao.prepareStatement(sql);
			resultado = statement.executeQuery();
			
			while(resultado.next()) {
				if(resultado != null) {
					Curso curso = new Curso(resultado.getInt("idcurso"),resultado.getString("nomeCurso"));
					System.out.println(curso.toString());
						cursos.add(curso);
					
				}
			}
			System.out.println(cursos);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cursos;
	}

}
