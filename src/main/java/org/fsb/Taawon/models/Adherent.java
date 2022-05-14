package org.fsb.Taawon.models;

import java.time.LocalDate;


public class Adherent {

	int num;
	int cin;
	String nom;
	String prenom;
	String sexe;
	String profession;
	int tel;
	LocalDate date_naissance;
	LocalDate date_inscription;
	String email;
	String domain_interet;
	String motpass;
	
	
	
	@Override
	public String toString() {
		return "Adherent [num=" + num + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe
				+ ", profession=" + profession + ", tel=" + tel + ", date_naissance=" + date_naissance
				+ ", date_inscription=" + date_inscription + ", email=" + email + ", domain_interet=" + domain_interet
				+ ", motpass=" + motpass + ", role=" + role + "]";
	}
	
	public Adherent(int num, int cin, String nom, String prenom, String sexe, String profession, int tel,
			LocalDate date_naissance, LocalDate date_inscription, String email, String domain_interet, String motpass,
			String role) {
		super();
		this.num = num;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.profession = profession;
		this.tel = tel;
		this.date_naissance = date_naissance;
		this.date_inscription = date_inscription;
		this.email = email;
		this.domain_interet = domain_interet;
		this.motpass = motpass;
		this.role = role;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
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
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public LocalDate getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(LocalDate date_naissance) {
		this.date_naissance = date_naissance;
	}
	public LocalDate getDate_inscription() {
		return date_inscription;
	}
	public void setDate_inscription(LocalDate date_inscription) {
		this.date_inscription = date_inscription;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDomain_interet() {
		return domain_interet;
	}
	public void setDomain_interet(String domain_interet) {
		this.domain_interet = domain_interet;
	}
	public String getMotpass() {
		return motpass;
	}
	public void setMotpass(String motpass) {
		this.motpass = motpass;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	String role;
	
	
}
