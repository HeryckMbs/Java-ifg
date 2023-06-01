package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.DisciplinaInterface;
import model.entities.Disciplina;

public class DisciplinaDAOImp implements DisciplinaInterface {

	private Connection conexao;
	
	public DisciplinaDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Disciplina obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO Disciplina (nomeDisciplina,cargahoraria) VALUES (?, ?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNomedisciplina());
			pst.setInt(2, obj.getCargahoraria());
			int linhas = pst.executeUpdate();
			
			if (linhas > 0) {
				rs = pst.getGeneratedKeys();
				rs.next();
				obj.setIddisciplina(rs.getInt(1));
				System.out.println(obj.toString() + " foi criado com sucesso !");
			}
			else {
				System.out.println("N�o foi poss�vel cadastrar o Disciplina !");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Disciplina obj) {
		String sql = "UPDATE Disciplina SET nomeDisciplina = ?, cargahoraria = ? where idDisciplina = ?";
		PreparedStatement statement = null;
		ResultSet resultado = null;
		
		try {
			statement = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, obj.getNomedisciplina());
			statement.setInt(2, obj.getCargahoraria());
			statement.setInt(3, obj.getIddisciplina());
			int linhas = statement.executeUpdate();
			
			if (linhas > 0) {
				resultado = statement.getGeneratedKeys();
				resultado.next();
				obj.setIddisciplina(resultado.getInt(1));
				System.out.println(obj.toString() + " foi atualizado com sucesso !");
			}
			else {
				System.out.println("N�o foi poss�vel atualizar o Disciplina !");
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM Disciplina where iddisciplina = ?";
		PreparedStatement statement = null;
		ResultSet resultado= null;
		
		try {
			statement = conexao.prepareStatement(sql);
			statement.setInt(1, id);
			int linhas = statement.executeUpdate();
			if(linhas > 0) {
				System.out.println("Disciplina excluido com sucesso");
			}else {
				System.out.println("N�o foi poss�vel excluir o Disciplina");
			}
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public Disciplina findById(Integer id) {
		
		return null;
	}

	@Override
	public List<Disciplina> findAll() {
		String sql = "SELECT * FROM Disciplina";
		PreparedStatement statement = null;
		ResultSet resultado = null;
		List<Disciplina> Disciplinas = new ArrayList<Disciplina>();
		try {
			statement = conexao.prepareStatement(sql);
			resultado = statement.executeQuery();
			
			while(resultado.next()) {
				if(resultado != null) {
					Disciplina disciplina = new Disciplina();
					disciplina.setIddisciplina(resultado.getInt("iddisciplina"));
					disciplina.setNomedisciplina(resultado.getString("nomedisciplina"));
					disciplina.setCargahoraria(Integer.toString(resultado.getInt("cargahoraria")));
					Disciplinas.add(disciplina);
				}
			}
			System.out.println(Disciplinas);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Disciplinas;
	}

}
