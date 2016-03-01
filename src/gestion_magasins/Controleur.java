package gestion_magasins;

import java.awt.event.*;
import java.util.Vector;
import javax.swing.*;

/**
 * @author admin
 *
 */
public class Controleur implements ActionListener, MouseListener {

	// LE MODELE
	// Modele des donnees de l application
	private Modele modele;
	
	
	// LES VUES
	private VuePrincipal vuePrincipal;
	private VueAjouterMagasin vueAjouter;
	private VueModifierMagasin vueModifier;

	
	// LA JTABLE DE LA VUE
	// une reference a la JTable de la vue VuePrincipal
	private JTable tbMagasins;

	// modele de des donnees de la JTable
	private MyTableModel tableModel;

	// Vecteur des données de la JTable
	private Vector donnees = new Vector();
	
	
	// LES MODIFICATIONS
	// Object qui contient la definition d'une modification
	private Modification mod;
	
	
	// LES VARS DE TRAVAIL

	// paramètres d'un Magasin
	private int numeroMagasin = 0;
	private String surnom = null;
	private String enseigne = null;
	private JComboBox<Integer> fidelite = new JComboBox<Integer>();
	private JComboBox<Integer> backOffice = new JComboBox<Integer>();
	private JComboBox<Integer> numeroContact = new JComboBox<Integer>();


	public Controleur() {

		modele = new Modele();

		vuePrincipal = new VuePrincipal();
		vueAjouter = new VueAjouterMagasin();
		vueModifier = new VueModifierMagasin();

		ecouterEvenementsBouttons();

		initJTable();

		// affichage vue principale
		vuePrincipal.pack();
		vuePrincipal.setVisible(true);
	}

	private void initJTable() {

		introduirelisteDesMagasinsDansLeVecteurDonnees();
		// instancie mon modele de JTable
		tableModel = new MyTableModel(donnees, modele.getTableColumnName());
		tbMagasins = vuePrincipal.getTableauMagasins();
		tbMagasins.setModel(tableModel);
		tbMagasins.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbMagasins.addMouseListener(this);
	}

	private void introduirelisteDesMagasinsDansLeVecteurDonnees() {

		donnees.addAll(modele.obtenirListeDesMagasins());
	}

	// Ecouter les évenements boutons

	private void ecouterEvenementsBouttons() {

		// VuePrincipale
		vuePrincipal.getBoutonAjouter().addActionListener(this);
		vuePrincipal.getBoutonSupprimer().addActionListener(this);
		vuePrincipal.getBoutonSauver().addActionListener(this);

		// VueAjouter
		this.vueAjouter.getBoutonValider().addActionListener(this);
		this.vueAjouter.getBoutonAnnuler().addActionListener(this);

		// VueModifier
		this.vueModifier.getBoutonValider().addActionListener(this);
		this.vueModifier.getBoutonAnnuler().addActionListener(this);
	}

	/**
	 * Effacer les champs de la vue Ajouter
	 */
	private void effacerChampsVueAjouter() {
		this.vueAjouter.getNumeroMagasinField().setText("");
		this.vueAjouter.getEnseigneField().setText("");
		this.vueAjouter.getSurnomField().setText("");
	}

	/**
	 * Ajouter un Magasin à la JTable
	 */
	private void ajouterMagasin() {

		surnom = this.vueAjouter.getSurnomField().getText();
		enseigne = this.vueAjouter.getEnseigneField().getText();
		numeroMagasin = getIntFromText(vueAjouter.getNumeroMagasinField().getText());
		
		fidelite = (JComboBox<Integer>) vueAjouter.getFideliteJComboBox();
		int fideliteCombo = Integer.parseInt(fidelite.getSelectedItem().toString());
		
		backOffice = (JComboBox<Integer>) vueAjouter.getBackOfficeJComboBox();
		int BOCombo = Integer.parseInt(backOffice.getSelectedItem().toString());
		
		numeroContact = (JComboBox<Integer>) vueAjouter.getBackOfficeJComboBox();
		int numeroContactCombo = Integer.parseInt(numeroContact.getSelectedItem().toString());

		Magasin unMagasin = new Magasin(numeroMagasin, enseigne, surnom, 
				fideliteCombo, BOCombo, numeroContactCombo);

		
		Vector row = new Vector(); 

		/*On ajoute les informations relatives à un Magasin 
		 * dans le vecteur de données "donnees"
		 */
		row.add(0, unMagasin.getNumeroMagasin());
		row.add(1, unMagasin.getEnseigne());
		row.add(2, unMagasin.getSurnom());
		row.add(3, unMagasin.getFidelite());
		row.add(4, unMagasin.getBackOffice());
		row.add(5, unMagasin.getNumeroContact());

		this.tableModel.addRow(row);

		JOptionPane.showMessageDialog(null,
				"Le Magasin " + unMagasin.getNumeroMagasin() + " " + " a bien été ajouter à la JTable",
				"Message de confirmation", JOptionPane.INFORMATION_MESSAGE);

		this.vueAjouter.fermerLaVue();

		effacerChampsVueAjouter();
		
		//Initialisation d'une Modification avec le Magasin et l'Action courante
		mod = new Modification(unMagasin, DefinitionsStatic.ACTION_AJOUTER);

		//On ajoute la modification courante au vecteur de modifications
		modele.getModifications().addElement(mod);

		//On active le bouton Sauver
		vuePrincipal.activerBoutonSauver();

		return;
	}

	
	//Modifier un Magasin depuis la JTable
	private void modifierMagasin() {

		surnom = this.vueModifier.getSurnomField().getText();
		enseigne = this.vueModifier.getEnseigneField().getText();
		numeroMagasin = getIntFromText(vueModifier.getNumeroMagasinField().getText());
		fidelite = (JComboBox<Integer>) vueModifier.getFideliteJComboBox();
		backOffice = (JComboBox<Integer>) vueModifier.getBackOfficeJComboBox();
		numeroContact = (JComboBox<Integer>) vueModifier.getNumeroContactJComboBox();

		Vector row = new Vector();

		int ligneSelectionnee = tbMagasins.getSelectedRow() ;
		
		int fideliteCombo = Integer.parseInt(fidelite.getSelectedItem().toString());
		int BOcombo = Integer.parseInt(backOffice.getSelectedItem().toString());
		int numeroContactCombo = Integer.parseInt(numeroContact.getSelectedItem().toString());


		row = (Vector) donnees.elementAt(ligneSelectionnee);
		row.setElementAt(numeroMagasin, 0);
		row.setElementAt(enseigne, 1);
		row.setElementAt(surnom, 2);
		row.setElementAt(fideliteCombo, 3);
		row.setElementAt(BOcombo, 4);
		row.setElementAt(numeroContactCombo, 5);
		


		//Mise à jour de la JTable
		tbMagasins.repaint();

		JOptionPane.showMessageDialog(null,
				"Le Magasin " + ligneSelectionnee + " " + "a bien été modifier dans la JTable",
				"Message de confirmation", JOptionPane.INFORMATION_MESSAGE);

		Magasin unMagasin = new Magasin();
		
		unMagasin.setNumeroMagasin((int) row.get(0));
		unMagasin.setEnseigne((String) row.get(1));
		unMagasin.setSurnom((String) row.get(2));
		unMagasin.setFidelite((int) row.get(3));
		unMagasin.setBackOffice((int) row.get(4));
		unMagasin.setNumeroContact((int) row.get(5));
		

		//Initialisation d'une Modification avec le Magasin et l'Action courante
		mod = new Modification(unMagasin, DefinitionsStatic.ACTION_MODIFIER);

		//On ajoute la modification courante au vecteur de modifications
		modele.getModifications().addElement(mod);

		//On active le bouton Sauver
		vuePrincipal.activerBoutonSauver();

		return;
	}

	//Supprimer un Magasin depuis la JTable
	private void supprimerMagasin() {

		Vector row = new Vector();
		
		int ligneSelectionnee = tbMagasins.getSelectedRow() ;

		row = (Vector) donnees.elementAt(ligneSelectionnee);

		Magasin unMagasin = new Magasin();

		unMagasin.setNumeroMagasin((int) row.get(0));
		unMagasin.setEnseigne((String) row.get(1));
		unMagasin.setSurnom((String) row.get(2));
		unMagasin.setFidelite((int) row.get(3));
		unMagasin.setBackOffice((int) row.get(4));
		unMagasin.setNumeroContact((int) row.get(5));

		this.tableModel.removeRow(tbMagasins.getSelectedRow());

		tbMagasins.repaint();

		//Initialisation d'une Modification avec le Magasin et l'Action courante
		mod = new Modification(unMagasin, DefinitionsStatic.ACTION_SUPPRIMER);

		//On ajoute la modification courante au vecteur de modifications
		modele.getModifications().addElement(mod);

		JOptionPane.showMessageDialog(null,
				"Le Magasin " + ligneSelectionnee + " " + "a bien été supprimer à la JTable", "Message de confirmation",
				JOptionPane.INFORMATION_MESSAGE);

		//On active le bouton Sauver
		vuePrincipal.activerBoutonSauver();

		return;
	}

	//Récupérer les valeurs saisies en vue de la modification de ses dernières
	private void recupererValeursSaisiesLigneSelectionnee(int idx) {

		Vector row = (Vector) tableModel.getDataVector().get(idx);

		//Numéro de magasin récupéré
		vueModifier.getNumeroMagasinField().setText(row.elementAt(0) + "");

		//Enseigne récupéré
		vueModifier.getEnseigneField().setText(row.elementAt(1) + "");

		//Surnom récupéré
		vueModifier.getSurnomField().setText(row.elementAt(2) + "");

		//Fidelite récupérée
		vueModifier.getFideliteJComboBox().setSelectedItem(row.elementAt(3) + "");

		//Back Office rcupéré
		vueModifier.getBackOfficeJComboBox().setSelectedItem(row.elementAt(4) + "");

		//Numéro de Contact récupéré
		vueModifier.getNumeroContactJComboBox().setSelectedItem(row.elementAt(5) + "");
	}

	/**
	 * Convertir des Entiers en Chaine de Caractères
	 * @param text
	 * @return
	 */
	private int getIntFromText(String text) {
		
		int n;

		try {

			n = Integer.parseInt(text);
		}

		catch (NumberFormatException erreur) {

			JOptionPane.showMessageDialog(null, "Attention ! j\'attends un entier", "Message d'erreur",
					JOptionPane.ERROR_MESSAGE);

			return -1;
		}
		return n;
	}
	
	/*
	 *  Fonction qui ecoute les evenements ActionEvent
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */

	@Override
	public void actionPerformed(ActionEvent e) {

		Object sourceEvenement = e.getSource();

		//Bouton Ajouter
		if (sourceEvenement == vuePrincipal.getBoutonAjouter()) {
			vueAjouter.afficherLaVue();
		}

		//Bouton Valider
		if (sourceEvenement == vueAjouter.getBoutonValider()) {
			ajouterMagasin();
			return;
		}

		//Bouton Annuler
		if (sourceEvenement == this.vueAjouter.getBoutonAnnuler()) {

			effacerChampsVueAjouter();

			this.vueAjouter.dispose();
		}

		//Bouton Supprimer
		if (sourceEvenement == this.vuePrincipal.getBoutonSupprimer()) {
			supprimerMagasin();
		}
		
		//Bouton Sauver
		if (sourceEvenement == this.vuePrincipal.getBoutonSauver()) {

			//On sauvegarde les Modifications(ajouter,modifier,supprimer) dans la BDD
			sauvegarderLesModificationsDanslaBDD();

			// On désactive le bouton Sauver
			vuePrincipal.desactiverBoutonSauver();

			//supprimer toute les modifications
			modele.getModifications().clear();
		}

		// Bouton Valider une Modification 
		if (e.getActionCommand().equalsIgnoreCase("BoutonModifierMagasin")) {

			modifierMagasin();

			this.vueModifier.fermerLaVue();
		}
		
		//Bouton Annuler une Modification
		if (e.getActionCommand().equalsIgnoreCase("BoutonAnnuler - ModifierMagasin")) {

			this.vueModifier.fermerLaVue();
		}
		
	}

	private void sauvegarderLesModificationsDanslaBDD() {
		
		for (int i = 0; i < modele.getModifications().size(); i++) {
			
			Modification m = (Modification) modele.getModifications().get(i);
			
			if (m.action.equalsIgnoreCase("ajouter")) {
				modele.insererUnMagasinAlaBDD(m.getMagasin());
			}

			if (m.action.equalsIgnoreCase("modifier")) {
				modele.modifierUnMagasinAlaBDD(m.getMagasin());
			}

			if (m.action.equalsIgnoreCase("supprimer")) {
				modele.supprimerUnMagasinAlaBDD(m.getMagasin());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {
			
			vueModifier.afficherLaVue();
			recupererValeursSaisiesLigneSelectionnee(tbMagasins.getSelectedRow());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Controleur();
	}
	
	
	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}