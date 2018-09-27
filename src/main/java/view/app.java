/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import Dao.PublicacaoDao;
import Dao.UsuarioDao;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.Publicacao;
import model.Usuario;
import org.neo4j.driver.v1.Driver;

/**
 *
 * @author Cliente
 */
public class app {
    
    public static void main(String[] args) {
   
    UsuarioDao dao = new UsuarioDao();
    PublicacaoDao daoP = new PublicacaoDao();

/* Salvar usuario   
System.out.println(dao.salvar(new Usuario("rafa@gmail.com","123","Rafaela","Cajazeiras",LocalDate.now())));
System.out.println(dao.salvar(new Usuario("camila@gmail.com","123","Camila","São Paulo",LocalDate.now())));
System.out.println(dao.salvar(new Usuario("dani@gmail.com","123","Daniela","Cajazeiras",LocalDate.now())));
System.out.println(dao.salvar(new Usuario("ale@gmail.com","123","Alessandra","Rio de Janeiro",LocalDate.now())));
System.out.println(dao.salvar(new Usuario("Joao@gmail.com","123","João","São joão",LocalDate.now())));
System.out.println(dao.salvar(new Usuario("Enzo@gmail.com","123","Enzo","Salvador",LocalDate.now())));  */
   

/*
Atualizar usuário pelo email
System.out.print(dao.Atualizar(new Usuario("Joao@gmail.com","123456","Joao Silva","Patos",LocalDate.now())));
*/


/*
Buscar usuario pelo email
System.out.print(dao.BuscarPorEmail("Joao@gmail.com"));  */


/*Seguir usuario 
System.out.println(dao.SeguirUsuario("Joao@gmail.com","camila@gmail.com"));
System.out.println(dao.SeguirUsuario("camila@gmail.com","ale@gmail.com"));
System.out.println(dao.SeguirUsuario("Joao@gmail.com","Enzo@gmail.com"));
System.out.println(dao.SeguirUsuario("Enzo@gmail.com","rafa@gmail.com")); */



/* Listar usuarios que  um determinado usuario segue está seguindo 
System.out.print(dao.SeguidoresDeSeguidores("Joao@gmail.com")); */




  /*Cria publicação  

System.out.println(daoP.criarPublicacao("Joao@gmail.com", (new Publicacao(1,"Feliz natal",LocalDateTime.now()))));
System.out.println(daoP.criarPublicacao("Joao@gmail.com", (new Publicacao(2,"Feliz Ano novo",LocalDateTime.now()))));
System.out.println(daoP.criarPublicacao("Joao@gmail.com", (new Publicacao(3,"Feliz Pascoa ",LocalDateTime.now()))));
System.out.println(daoP.criarPublicacao("camila@gmail.com", (new Publicacao(4,"Feliz dia das mães",LocalDateTime.now()))));
System.out.println(daoP.criarPublicacao("Enzo@gmail.com", (new Publicacao(5,"Mensagens de motivação",LocalDateTime.now())))); 
*/


/*Listar todas as publicações a partir do email de um usuario 
System.out.println(daoP.ListarPublicacao("Joao@gmail.com")); */


/*Deletar usuario 
dao.Deletar("dani@gmail.com");
dao.Deletar("ale@gmail.com");
dao.Deletar("Joao@gmail.com");
dao.Deletar("rafa@gmail.com");
dao.Deletar("Enzo@gmail.com");
dao.Deletar("camila@gmail.com"); */

   /*Deletar publicações pelo id 
   daoP.Deletar(1); */
  

       
    try{
        dao.close();
    }
    catch(Exception ex){
        ex.printStackTrace();
    }
        }
    }
  

