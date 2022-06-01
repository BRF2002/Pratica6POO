package Correto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConexaoPostgre {

		 private final String url = "jdbc:postgresql://localhost/BDlivrariaUniversitaria";
		 private final String user = "postgres";
		 private final String password ="123456";
		 Connection conn = null;

		 
		 private static final String QUERY = "select * from autor where id_autor =?";
		 private static final String SELECT_ALL_QUERY = "select * from livro";
		
		 
		 public Connection connect() {
		        
		        try {
		            conn = DriverManager.getConnection(url, user, password);

		            if (conn != null) {
		                System.out.println("Connected to the PostgreSQL server successfully.");
		            } else {
		                System.out.println("Failed to make connection!");
		            }
		            //versão do postgreeSQL
		            Statement statement = conn.createStatement();
		            ResultSet resultSet = statement.executeQuery("SELECT VERSION()");
		            if (resultSet.next()) {
		            	System.out.println(resultSet.getString(1));
		            }
		        } catch (SQLException e) {
		            System.out.println(e.getMessage());
		        }

		        return conn;
		        //conn.close();
		    }
		 
		    public void getLivro() {
		        // Step 1: Establishing a Connection
		        try {
		            // Step 2:Create a statement using connection object
		            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_QUERY);
		            System.out.println(preparedStatement);
		            // Step 3: Execute the query or update query
		            ResultSet rs = preparedStatement.executeQuery();

		            // Step 4: Process the ResultSet object.
		            while (rs.next()) {
		                int id_isbn = rs.getInt("id_isbn");
		                String nm_livro = rs.getString("nm_livro");
		                System.out.println(id_isbn + " - " + nm_livro);
		            }
		        } catch (SQLException e) {
		            printSQLException(e);
		        }
		    }

		    public void getUserById() {
		    	// Step 1: Establishing a Connection
		        try {
		            // Step 2:Create a statement using connection object
		            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
		            // Step 3: Execute the query or update query
		            preparedStatement.setInt(1, 5);
		            System.out.println(preparedStatement);
		            ResultSet rs = preparedStatement.executeQuery();
		            // Step 4: Process the ResultSet object.
		            // Step 4: Process the ResultSet object.
		            while (rs.next()) {
		                int id = rs.getInt("id_autor");
		                String name_autor = rs.getString("nm_autor");
		                System.out.println(id + " - " + name_autor);
		            }
		        } catch (SQLException e) {
		            printSQLException(e);
		        }
		    }
		    
	    public static void printSQLException(SQLException ex) {
			            for (Throwable e: ex) {
			                if (e instanceof SQLException) {
			                    e.printStackTrace(System.err);
			                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			                    System.err.println("Message: " + e.getMessage());
			                    Throwable t = ex.getCause();
			                    while (t != null) {
			                        System.out.println("Cause: " + t);
			                        t = t.getCause();
			                    }
			                }
			            }
			        }
			    /**
			     * @param args the command line arguments
			     */
			    public static void main(String[] args) {

			    	Scanner entrada = new Scanner(System.in);
	
			    	ConexaoPostgre app = new ConexaoPostgre();
			        app.connect();
			        int opcao = 0;

			        
			        do{
			            
				        System.out.println("Digite o Número de sua opção!");
				        System.out.println("1- Pesquisar Livro");
				        System.out.println("2- Pesquisar Cliente");
				        System.out.println("3- Pesquisar os Pedidos de um Cliente");
				        System.out.println("4- Sair");
				        	
				        opcao = entrada.nextInt();
			            
				        switch(opcao){
			            case 1:  
					        System.out.println("\nPesquisando livro");
					        app.getLivro();
					        break;
			            case 2:   
					        System.out.println("\nRealizando o select na tabela Autor pelo ID");
					        app.getUserById();
					        break;
			            case 3:
			            	break;
			            case 4:
			            	break;
			            
			            default:
			                System.out.println("Opção inválida.");
			            }
			        } while(opcao != 4);
			    }

				private static void menu() {
					// TODO Auto-generated method stub
					
				}

			    }
		

