package gestion_magasins;

public class Modification {

	Magasin magasin ;
	String action ;

	/**
	 * @param magasin
	 * @param action
	 */
	public Modification(Magasin magasin, String action) {
		this.magasin = magasin;
		this.action = action;
	}



	/**
	 * @return the magasin
	 */
	public Magasin getMagasin() {
		return magasin;
	}


	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}


	/**
	 * @param magasin the magasin to set
	 */
	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}


	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Modifications [" + magasin.toString() + ", action=" + action + "]";
	}
}
