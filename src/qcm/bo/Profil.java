package qcm.bo;

public class Profil {

	private String codeProfil;
	private String libelle;
	
	public Profil(String codeProfil, String libelle) {
		super();
		this.codeProfil = codeProfil;
		this.libelle = libelle;
	}
	
	public String getCodeProfil() {
		return codeProfil;
	}
	public void setCodeProfil(String codeProfil) {
		this.codeProfil = codeProfil;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
}
