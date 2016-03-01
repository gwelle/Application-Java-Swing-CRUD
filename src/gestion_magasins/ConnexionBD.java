/**
 * 
 */
package gestion_magasins;

/**
 * @author admin
 * 
 */
import java.sql.*;

public class ConnexionBD {

	private static String pilote = "org.postgresql.Driver";
	
	private static String url = "jdbc:postgresql://localhost:5432/gestion?useUnicode=yes&characterEncoding=UTF-8";
	private static String user = "postgres";
	private static String mdp = "postgres";
	
	private static Connection connexion = null ;
	
	/**
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ConnexionBD() throws ClassNotFoundException, SQLException{
	
		try{
			/**
			 * 	Chargement du pilote
			 */
			Class.forName(pilote);
			}
			
			catch(ClassNotFoundException e){
				e.printStackTrace();
				
			}
			
			try{
			/**
			 * Ouverture de la connexion
			 */
			connexion  = DriverManager.getConnection(url,user,mdp);
			}
			
			catch(SQLException e){
				e.printStackTrace();
				
			}
			
		}
		
		/**
		 * @return the connexion
		 * Design Pattern Singleton pour but d'avoir une connexion unique.
		 */
		public static Connection getConnexion() {
			
			if(connexion == null){
				 try {
					new ConnexionBD();
				} 
				 
				 catch (ClassNotFoundException e) {
					e.printStackTrace();
				} 
				 
				 catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return connexion;
		}
}