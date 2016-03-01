/**
 * 
 */
package gestion_magasins;

/**
 * @author admin
 *
 */
public class Magasin {


	private int numeroMagasin ;
	private String enseigne ;
	private String surnom ;
	private int fidelite ;
	private int backOffice ;
	private int numeroContact ;
	
	
	public Magasin() {
	}

	
	/**
	 * @param numeroMagasin
	 * @param enseigne
	 * @param surnom
	 * @param fidelite
	 * @param backOffice
	 * @param numeroContact
	 */
	public Magasin(int numeroMagasin, String enseigne, String surnom, 
			int fidelite, int backOffice, int numeroContact) {
		this.numeroMagasin = numeroMagasin;
		this.enseigne = enseigne;
		this.surnom = surnom;
		this.fidelite = fidelite;
		this.backOffice = backOffice;
		this.numeroContact = numeroContact;
	}


	/**
	 * @return the numeroMagasin
	 */
	public int getNumeroMagasin() {
		return numeroMagasin;
	}

	/**
	 * @return the enseigne
	 */
	public String getEnseigne() {
		return enseigne;
	}

	/**
	 * @return the surnom
	 */
	public String getSurnom() {
		return surnom;
	}

	/**
	 * @return the fidelite
	 */
	public int getFidelite() {
		return fidelite;
	}

	/**
	 * @return the backOffice
	 */
	public int getBackOffice() {
		return backOffice;
	}

	/**
	 * @return the numeroContact
	 */
	public int getNumeroContact() {
		return numeroContact;
	}

	/**
	 * @param numeroMagasin the numeroMagasin to set
	 */
	public void setNumeroMagasin(int numeroMagasin) {
		this.numeroMagasin = numeroMagasin;
	}

	/**
	 * @param enseigne the enseigne to set
	 */
	public void setEnseigne(String enseigne) {
		this.enseigne = enseigne;
	}

	/**
	 * @param surnom the surnom to set
	 */
	public void setSurnom(String surnom) {
		this.surnom = surnom;
	}

	/**
	 * @param fidelite the fidelite to set
	 */
	public void setFidelite(int fidelite) {
		this.fidelite = fidelite;
	}

	/**
	 * @param backOffice the backOffice to set
	 */
	public void setBackOffice(int backOffice) {
		this.backOffice = backOffice;
	}

	/**
	 * @param numeroContact the numeroContact to set
	 */
	public void setNumeroContact(int numeroContact) {
		this.numeroContact = numeroContact;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Magasin [numeroMagasin=" + numeroMagasin + ", enseigne=" + enseigne + ", surnom=" + surnom
				+ ", fidelite=" + fidelite + ", backOffice=" + backOffice + ", numeroContact=" + numeroContact + "]";
	}
}
