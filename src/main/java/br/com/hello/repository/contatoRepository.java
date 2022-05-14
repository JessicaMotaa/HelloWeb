package br.com.hello.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.hello.adapter.ConnectionFactory;
import br.com.hello.entity.Contato;

public class contatoRepository implements IRepository<Contato>{

	private Connection connection;
	
	
	//jdbc = Forma de guardar e manipular o banco de dados
	
	public contatoRepository() {
		try {
			connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void save(Contato contato) {
		
		String sql = "INSERT INTO pessoas(nome, email) values (?,?)"; // evitar ataques de SQL injection
		
		try {
		PreparedStatement stmt = connection.prepareStatement(sql);
		
		stmt.setString(1, contato.getNome());
		stmt.setString(2, contato.getEmail());
		
		stmt.execute();
		stmt.close();
		
		}catch(SQLException ex) {
			throw new RuntimeException("Erro 500");
		}
	}


	@Override
	public void delete(Integer id) {
		
		String sql = "DELETE from pessoas WHERE id =?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro 500");
		}
		
	}

	@Override
	public List<Contato> findAll() {
		List <Contato> contatos = new ArrayList<>();
		String sql = "SELECT * FROM pessoas";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql); //leva info pro DB
			ResultSet result = stmt.executeQuery(); // retorna info do banco
			
			while(result.next()) {
				Contato contato = new Contato();
				
				
				contato.setNome(result.getString("nome"));
				contato.setEmail(result.getString("email"));
				
				contatos.add(contato);
			}
				result.close();
			stmt.close();
				
		} catch (SQLException ex) {
			throw new RuntimeException("Erro 500");
		}
		
		return contatos;
	}


	@Override
	public void update(Contato contato) {
		String sql = "UPDATE pessoas SET nome=?, email=? where id=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			
			
			stmt.execute();
			stmt.close();

		} catch (SQLException ex) {
			throw new RuntimeException("Erro 500");
		}
	}

	@Override
	public Contato findByld(Integer id) {
		
		Contato contato = new Contato();
		String sql = "SELECT * FROM pessoas WHERE id = ?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				contato.setNome(result.getString("nome"));
				contato.setEmail(result.getString("email"));
			}
			result.close();
			stmt.close();

		} catch (SQLException ex) {
			throw new RuntimeException("Erro 500");
		}

		return contato;
	}


	@Override
	public Contato findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
