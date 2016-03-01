package gestion_magasins;

import java.awt.Dimension;

/**
 * @author admin
 *
 */

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueAjouterMagasin extends JFrame{

	//Les champs de saisie
	private JFormattedTextField numeroMagasinField ;
	private JTextField enseigneField ;
	private JTextField surnomField ;
	private JComboBox<Integer> fideliteJComboBox = new JComboBox<Integer>();
	private JComboBox<Integer> backOfficeJComboBox = new JComboBox<Integer>();
	private JComboBox<Integer> numeroContactJComboBox = new JComboBox<Integer>();
	
	
	//Les labels
	private JLabel numeroMagasinLabel ;
	private JLabel enseigneLabel ;
	private JLabel surnomLabel ;
	private JLabel fideliteLabel ;
	private JLabel backOfficeLabel ;
	private JLabel numeroContactLabel ;
	
	
	//Les boutons
	private JButton boutonValider ;
	private JButton boutonAnnuler ;
	
	public VueAjouterMagasin() {
		super("Formulaire d'insertion des valeurs") ;
		this.formulaireAjouterUnMagasin();
	}

	/**
	 * Formulaire d'insertion d'un magasin
	 */
	private void formulaireAjouterUnMagasin() {
		
		JPanel panneau = new JPanel() ;	
		
		//Associer le panneau au formulaire
		this.getContentPane().add(panneau);;
				
		//Numéro du Magasin
		numeroMagasinField = new JFormattedTextField() ;
		numeroMagasinLabel = new JLabel("Num\u00e9ro de Magasin: ") ;
		numeroMagasinLabel.setLabelFor(numeroMagasinField) ;
		
	
		//Nom de l'Ensegine
		enseigneField = new JTextField() ;
		enseigneLabel = new JLabel("Enseigne : ") ;
		enseigneLabel.setLabelFor(enseigneField) ;
		
		//Surnom
		surnomField = new JTextField() ;
		surnomLabel = new JLabel("Surnom : ") ;
		surnomLabel.setLabelFor(surnomField) ;
		
		//Fidélité
		fideliteLabel = new JLabel("Fid\u00e9lit\u00e9 : ") ;
		fideliteLabel.setLabelFor(fideliteJComboBox) ;
		
		fideliteJComboBox.addItem(1);
		fideliteJComboBox.addItem(2);
		fideliteJComboBox.addItem(3);

		//Back Office
		backOfficeLabel = new JLabel("Back Office : ") ;
		backOfficeLabel.setLabelFor(backOfficeJComboBox) ;
	
		backOfficeJComboBox.addItem(1);
		backOfficeJComboBox.addItem(2);
		backOfficeJComboBox.addItem(3);
		
		//Numéro de contact
		numeroContactLabel = new JLabel("Num\u00e9ro de Contact : ") ;
		numeroContactLabel.setLabelFor(numeroContactJComboBox) ;
		
		numeroContactJComboBox.addItem(1);
		numeroContactJComboBox.addItem(2);
		numeroContactJComboBox.addItem(3);
	
		//Bouton Valider et Annuler
		boutonValider = new JButton("Valider");
		boutonValider.setActionCommand("BoutonModifierMagasin");

		boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.setActionCommand("BoutonAnnuler - ModifierMagasin");

		//Ajout des composants à notre panneau
		panneau.add(numeroMagasinLabel) ;
		panneau.add(numeroMagasinField) ;
		panneau.add(enseigneLabel) ;
		panneau.add(enseigneField) ;
		panneau.add(surnomLabel) ;
		panneau.add(surnomField) ;
		panneau.add(fideliteLabel);
		panneau.add(fideliteJComboBox);
		panneau.add(backOfficeLabel);
		panneau.add(backOfficeJComboBox);
		panneau.add(numeroContactLabel);
		panneau.add(numeroContactJComboBox);
		panneau.add(boutonValider);
		panneau.add(boutonAnnuler);
		
		panneau.setLayout(new GridLayout(0,2));
		panneau.setPreferredSize(new Dimension(500,250));
	}
	
	//Afficher la vue des Mises à jour à partir du Controleur
	public void afficherLaVue(){
		pack();
		setLocationRelativeTo(null) ;
		setVisible(true) ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
	}
	
	//Afficher la vue des Mises à jour à partir du Controleur
	public void fermerLaVue(){
		dispose();
	}
	
	//Les Getters
	/**
	 * @return the numeroMagasinField
	 */
	public JFormattedTextField getNumeroMagasinField() {
		return numeroMagasinField;
	}

	/**
	 * @return the enseigneField
	 */
	public JTextField getEnseigneField() {
		return enseigneField;
	}

	/**
	 * @return the surnomField
	 */
	public JTextField getSurnomField() {
		return surnomField;
	}

	/**
	 * @return the backOfficeJComboBox
	 */
	public JComboBox<Integer> getBackOfficeJComboBox() {
		return backOfficeJComboBox;
	}

	
	/**
	 * @return the fideliteJComboBox
	 */
	public JComboBox<Integer> getFideliteJComboBox() {
		return fideliteJComboBox;
	}

	/**
	 * @return the numeroContactJComboBox
	 */
	public JComboBox<Integer> getNumeroContactJComboBox() {
		return numeroContactJComboBox;
	}

	/**
	 * @return the numeroMagasinLabel
	 */
	public JLabel getNumeroMagasinLabel() {
		return numeroMagasinLabel;
	}

	/**
	 * @return the enseigneLabel
	 */
	public JLabel getEnseigneLabel() {
		return enseigneLabel;
	}

	/**
	 * @return the surnomLabel
	 */
	public JLabel getSurnomLabel() {
		return surnomLabel;
	}

	/**
	 * @return the fideliteLabel
	 */
	public JLabel getFideliteLabel() {
		return fideliteLabel;
	}

	/**
	 * @return the backOfficeLabel
	 */
	public JLabel getBackOfficeLabel() {
		return backOfficeLabel;
	}

	/**
	 * @return the numeroContactLabel
	 */
	public JLabel getNumeroContactLabel() {
		return numeroContactLabel;
	}

	/**
	 * @return the boutonValider
	 */
	public JButton getBoutonValider() {
		return boutonValider;
	}

	/**
	 * @return the boutonAnnuler
	 */
	public JButton getBoutonAnnuler() {
		return boutonAnnuler;
	}
}