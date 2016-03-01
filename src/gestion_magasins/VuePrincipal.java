package gestion_magasins;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author admin
 *
 */
public class VuePrincipal extends JFrame {

	private JTable tableauMagasins ;
	private JButton boutonAjouter ;
	private JButton boutonSupprimer ;
	private JButton boutonSauver ;
	

	public VuePrincipal() {
		super("La liste des Magasins");
		this.creerInterfaceGraphique();
		this.desactiverBoutonSauver();
	}
	
		private void creerInterfaceGraphique(){
			
			//Instantiation du Tableau et des Boutons
			tableauMagasins = new JTable();
			boutonAjouter = new JButton("Ajouter");
			boutonAjouter.setActionCommand("BoutonAjouterMagasin");
			
			boutonSupprimer = new JButton("Supprimer");
			boutonSauver = new JButton("Sauver");
			
			//Définition de la taile du Tableau et des Boutons
			this.setPreferredSize(new Dimension(800,400));
			boutonAjouter.setPreferredSize(new Dimension(100,50));
			boutonSupprimer.setPreferredSize(new Dimension(100,50));
			boutonSauver.setPreferredSize(new Dimension(100,50));
			
			//Panneau d'ascenseur pour définir sa taille et intégrer le Tableau
			JScrollPane jsp = new JScrollPane();
			jsp.setPreferredSize(new Dimension(800,600));
			jsp.getViewport().add(this.tableauMagasins);
		
			/*Panneau principale en Border Layout qui nous indiquera 
			que dans le panneau d'ascenseur le tableau sera centré*/
			JPanel jp =(JPanel)this.getContentPane();
			jp.setLayout(new BorderLayout());
			jp.add(jsp,BorderLayout.CENTER);
			
			/*Panneau secondaire en FlowLayout dans lequel nous allons 
			 intégrer les noutons et les placés au sud*/
			JPanel jp1 = new JPanel();
			jp1.setLayout(new FlowLayout());
			jp1.add(boutonAjouter);
			jp1.add(boutonSupprimer);
			jp1.add(boutonSauver);
			jp.add(jp1,BorderLayout.SOUTH);
		}
		
		/**
		 * Activer le bouton Sauver
		 */
		public void activerBoutonSauver(){
			boutonSauver.setEnabled(true);
		}
		
		/**
		 * Désactiver le bouton Sauver
		 */
		public void desactiverBoutonSauver(){
			boutonSauver.setEnabled(false);
		}
		
	//Les Getters
	/**
	 * @return the tableauMagasins
	 */
	public JTable getTableauMagasins() {
		return tableauMagasins;
	}
	/**
	 * @return the boutonAjouter
	 */
	public JButton getBoutonAjouter() {
		return boutonAjouter;
	}

	/**
	 * @return the boutonSupprimer
	 */
	public JButton getBoutonSupprimer() {
		return boutonSupprimer;
	}
	/**
	 * @return the boutonSauver
	 */
	public JButton getBoutonSauver() {
		return boutonSauver;
	}
}