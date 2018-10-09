package src.main.java.fr.eni.qcm.bo;

public class Utilisateur {

	private Integer idUtilisateur;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String type;
	
	public Utilisateur() {
		super();
	}
	
	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", password=" + password + ", type=" + type + "]";
	}
	
	
	
	
	
}
