package gestion_magasins;

/**
 * @author admin
 */

import java.sql.*;
import java.util.Vector;


public class Modele {
	
	// Vecteur des modifications en attente dans la JTable
	private Vector modifications = new Vector();	
	
	public Modele() {
	}

	/**
	 * @return the modifications
	 */
	public Vector getModifications() {
		return modifications;
	}


	/**
	 * @param modifications the modifications to set
	 */
	public void setModifications(Vector modifications) {
		this.modifications = modifications;
	}

	/**
	 * Récupérer le nom des colonnes du tableau des Magasins
	 * @return nomColonnes
	 */
	public Vector<String> getTableColumnName(){
		
	Vector<String> nomColonnes = new Vector<String>();
		
		nomColonnes.add("N°Magasin");
		nomColonnes.add("Enseigne");
		nomColonnes.add("Surnom");
		nomColonnes.add("Fidélité");
		nomColonnes.add("Back Office");
		nomColonnes.add("N°Contact");
		
		return nomColonnes;
	}
	
	/**
	 * Ajouter un Magasin à la base de Données
	 * @param unMagasin
	 */
	public void insererUnMagasinAlaBDD(Magasin unMagasin){
		Statement s = null;

		StringBuilder sb = new StringBuilder();
		Connection connexion = ConnexionBD.getConnexion();
		
		try {
			
			  s = connexion.createStatement();
		}
		
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		sb.append("INSERT INTO magasin(id_magasin,enseigne,surnom,");
		sb.append("fidelite,back_office,id_contact) ").append("VALUES(");
		sb.append(unMagasin.getNumeroMagasin()).append(",'");
		sb.append(unMagasin.getEnseigne()).append("','");
		sb.append(unMagasin.getSurnom()).append("',");
		sb.append(unMagasin.getFidelite()).append(",");
		sb.append(unMagasin.getBackOffice()).append(",");
		sb.append(unMagasin.getNumeroContact()).append(");");
		
		try {
			
			s.execute(sb.toString());
			System.out.println("Requête d'Insertion Stringbuilder : " + sb.toString()) ;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Modifier les valeurs des colonnes d'un Magasin à la base de Données
	 * @param unMagasin
	 */
	public void modifierUnMagasinAlaBDD(Magasin unMagasin){

		int resultat   ;
		String requete = null ;
		PreparedStatement pstmt = null ;
		
		Connection connexion = ConnexionBD.getConnexion();
		
		requete = "UPDATE magasin SET enseigne = ?, "
				+ "surnom = ?, "
				+ "fidelite = ?, "
				+ "back_office = ?, "
				+ "id_contact = ? "
				+ "WHERE id_magasin = ?;";
		
		try {
			pstmt = connexion.prepareStatement(requete);
			
			pstmt.setString(1, unMagasin.getEnseigne());
			pstmt.setString(2, unMagasin.getSurnom());
			pstmt.setInt(3, unMagasin.getFidelite());
			pstmt.setInt(4, unMagasin.getBackOffice());
			pstmt.setInt(5, unMagasin.getNumeroContact());
			pstmt.setInt(6, unMagasin.getNumeroMagasin());
			
			System.out.println("La colonne Surnom est rempli ? : " + unMagasin.getSurnom()) ;
			System.out.println("Requête de Modification : " + pstmt.toString()) ;
			
			resultat = pstmt.executeUpdate();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Supprimer un Magasin à la base de Données
	 * @param unMagasin
	 */
	
	public void supprimerUnMagasinAlaBDD(Magasin unMagasin){
		
		int resultat   ;
		String requete = null ;
		PreparedStatement pstmt = null ;
		
		Connection connexion = ConnexionBD.getConnexion();
		
		requete = "DELETE FROM magasin WHERE id_magasin = ?;";
		
		try {
			pstmt = connexion.prepareStatement(requete);
			
			pstmt.setInt(1, unMagasin.getNumeroMagasin());
			
			System.out.println("Requête de Suppression : " + pstmt.toString()) ;
			
			resultat = pstmt.executeUpdate();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Retourne la liste de tous les Magasins contenus dans la base de données
	 * @return the lesMagasins
	 */
	public Vector<Vector> obtenirListeDesMagasins() {

		Vector<Vector> lesMagasins = new Vector<Vector>();
		
		Connection connexion = null ;
		Statement stmt = null;
		try {
			connexion = ConnexionBD.getConnexion();
			stmt = connexion.createStatement();
		} 
		
		catch (SQLException erreur) {
			
			System.err.println("Erreur :"+ " " +erreur.getMessage());
		}

		ResultSet resultat = null ;
		
		try {
			
			String requete = "SELECT * FROM magasin;" ;
			
			resultat = stmt.executeQuery(requete);

			while(resultat.next()){
				
				Vector vecteur = new Vector();
				vecteur.add(0, resultat.getInt("id_magasin"));
				vecteur.add(1,resultat.getString("enseigne"));
				vecteur.add(2,resultat.getString("surnom"));
				vecteur.add(3, resultat.getInt("fidelite"));
				vecteur.add(4, resultat.getInt("back_office"));
				vecteur.add(5,resultat.getInt("id_contact"));
				
				lesMagasins.addElement(vecteur);
				
				System.out.println("Les Magasins : " + vecteur.toString());
			}
		} 
		
		catch (SQLException e) {
			
			System.err.println(e.getMessage());
			System.err.println(e.getErrorCode());
			System.err.println(e.getSQLState());
		}
		
		catch(java.lang.ArrayIndexOutOfBoundsException e1){
			System.err.println("Message d \'erreur" + e1.getMessage());
		}
		
		return lesMagasins;
	}
}