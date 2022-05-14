package br.com.hello.repository;

import java.util.List;

import br.com.hello.entity.Contato;

public interface IRepository<T> {

	public void save(T tipo); //Inserir dados
	public void delete (Integer id); //Excluir dados
	public List<T>findAll(); //Buscar todos
	public T findByld(Integer id); //Buscar por ID
	public void update(T tipo); //atualizar
	Contato findById(Integer id);
	

}
