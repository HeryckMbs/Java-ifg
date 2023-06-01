package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Aluno;
import model.entities.AlunoInterface;

public class AlunoDAOImp implements AlunoInterface {

	private Connection conexao;
	
	public AlunoDAOImp(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(Aluno obj) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String sql = "INSERT INTO Aluno (nome,sexo,dt_nasc,nota) VALUES (?,?,?,?)";
			pst = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, obj.getNome());
			pst.setString(2, obj.getSexo());
			pst.setDate(3, obj.getDt_nas());
			pst.setDouble(4, obj.getNota());


			int linhas = pst.executeUpdate();
			
			if (linhas > 0) {
				rs = pst.getGeneratedKeys();
				rs.next();
				obj.setIdAluno(rs.getInt(1));
				System.out.println(obj.toString() + " foi criado com sucesso !");
			}
			else {
				System.out.println("N�o foi poss�vel cadastrar o Aluno !");
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Aluno obj) {
		String sql = "UPDATE Aluno SET nome = ?, sexo = ? , dt_nasc = ?, nota = ? where idAluno = ?";
		PreparedStatement statement = null;
		ResultSet resultado = null;
		
		try {
			statement = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, obj.getNome());
			statement.setString(2, obj.getSexo());
			statement.setDate(3, obj.getDt_nas());
			statement.setDouble(4, obj.getNota());
			statement.setInt(5, obj.getIdAluno());
			int linhas = statement.executeUpdate();
			
			if (linhas > 0) {
				resultado = statement.getGeneratedKeys();
				resultado.next();
				obj.setIdAluno(resultado.getInt(1));
				System.out.println(obj.toString() + " foi atualizado com sucesso !");
			}
			else {
				System.out.println("N�o foi poss�vel atualizar o Aluno !");
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM Aluno where idAluno = ?";
		PreparedStatement statement = null;
		ResultSet resultado= null;
		
		try {
			statement = conexao.prepareStatement(sql);
			statement.setInt(1, id);
			int linhas = statement.executeUpdate();
			if(linhas > 0) {
				System.out.println("Aluno excluido com sucesso");
			}else {
				System.out.println("N�o foi poss�vel excluir o Aluno");
			}
		}catch(Exception e) {
			
		}
		
	}

	@Override
	public Aluno findById(Integer id) {
		
		return null;
	}

	@Override
	public List<Aluno> findAll() {
		String sql = "SELECT * FROM aluno";
		PreparedStatement statement = null;
		ResultSet resultado = null;
		List<Aluno> Alunos = new ArrayList<Aluno>();
		try {
			statement = conexao.prepareStatement(sql);
			resultado = statement.executeQuery();
			
			while(resultado.next()) {
				if(resultado != null) {
					Aluno aluno = new Aluno();
					aluno.setDt_nas(resultado.getDate("dt_nasc").toString());
					aluno.setIdAluno(resultado.getInt("idAluno"));
					aluno.setNome(resultado.getString("nome"));
					aluno.setSexo(resultado.getString("sexo"));
					aluno.setNota(resultado.getDouble("nota"));
					Alunos.add(aluno);
				}
			}
			System.out.println(Alunos);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Alunos;
	}

}
