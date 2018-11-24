package sistema.crud.cerveja.service;

import  sistema.crud.cerveja.controller.CervejaController;
import sistema.crud.cerveja.sistema.loja.web.*;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.ws.rs.Consumes; // javax.ws.rs são interface de alto nivél de anotação
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


	//Codigo REST
	
	@Path("/teste")//URL de acesso aos recursos do servidor
	public class CervejaClientService {
			
		private EntityManager objEm = CervejaController.em(); 
		//criado objEM: objeto que ira  possibilitar o acesso a dados	

			
		@GET //este metodo so pode ser requisitado por uma requisicao do tipo get no HTTP
		@Path("/listar") // aqui recebe o nome do servidor que aparece no nome final da url que aponta pra recurso no servidor
		@Produces("appication/json") // serve pra informar ao Jersey q ele deve retornar ao cliente no formato json
		public java.util.List<Cerveja> Listar(){
			
			String comando = "SELECT cerveja FROM Cerveja cerveja";// query recebe a consulta a ser realizada no banco, porém aqui deve constar o nome da classe que esta sendo mapeada
			try {
				System.out.println("Vou passar");
				
				java.util.List<Cerveja> cervejas = objEm.createNativeQuery(comando, Cerveja.class).getResultList(); //o metodo createQuery faz a buscar no banco onde clientes recebe essa busca
				//sobre java.util = Contém as classes e as interfaces usadas para tratar coleções como tabelas, as listas vinculadas, pilhas e dicionários 
				return cervejas; // retorno da busca, que eh uma lista do banco
				
			} catch(RollbackException e) {
				System.out.println("Deu erro!");
				throw new WebApplicationException(500); //para recuperar possiveis erros durante a execucao
			}
		}
		
		@POST
		@Path("/cadastrar")
		public Response cadastrar (@FormParam("nome") String nome, @FormParam("codigo") int codigo){ //foi necessario incluir os parametros nome e codigo para ser visto pelo metodo
			
			System.out.println("entrando");
			
			Cerveja objCerveja = new Cerveja(); //instanciando o objeto para a classe Cerveja
			 
			try {
				System.out.println("Vou passar");
					 
				objCerveja.setCodigo (codigo); //leitura de entrada de dados (via teclado)
				objCerveja.setNome(nome);

					
				objEm.getTransaction().begin(); // inicia a transacao
				objEm.persist(objCerveja); //nova cerveja é persistido
				objEm.getTransaction().commit(); //para que o nova cerveja seja gravado permanentemente
				
				objEm.close();
				
				System.out.println("Adicionado!"); //retorna a informacao da acao
		
							
				return Response.status(200).entity("cadastro realizado com sucesso!").build(); //Isto informa que ao usuario foi cadastrado com sucesso
		
			} catch (RollbackException e) {
				throw new WebApplicationException(500);
			}
			
			
		}
			
			@DELETE
			@Path("excluir/{id}")
			public Response excluir (@FormParam("id")int id){ //informado como parâmetro o id do cliente a ser excluído da base de dados.Para que o serviço possa receber o parâmetro é necessário informar o ID do cliente no final da URL no index
				
				Cerveja cerveja = new Cerveja(); //instanciando o objeto  a classe Cerveja
							
				try {
					System.out.println("Vou passar");
								
					objEm.getTransaction().begin(); // inicia a transacao
					cerveja = objEm.find(Cerveja.class, id); //faz a busca no bando de dados do cliente a ser excluido
					objEm.remove(cerveja); //método remove() exclui o cliente
					objEm.getTransaction().commit(); //para que o usuario seja excluido permanentemente
					objEm.close();
					
					return Response.status(200).entity("cerveja excluido").build();
					
				} catch (RollbackException e) {
					throw new WebApplicationException(500);
					}
				}
			
		}

