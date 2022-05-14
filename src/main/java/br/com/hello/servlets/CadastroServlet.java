package br.com.hello.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import br.com.hello.entity.Contato;
import br.com.hello.repository.contatoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/cadastro")
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		
		PrintWriter out = response.getWriter();
		
		Contato contato = new Contato(nome, email);
		
		contatoRepository contatoRepository = new contatoRepository();
		contatoRepository.save(contato);
		
		out.println("<html> <body> Nome: " + nome +" cadastrado(a) com sucesso!!! </body></html>");
		}
}

























