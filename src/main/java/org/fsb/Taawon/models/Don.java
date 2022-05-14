package org.fsb.Taawon.models;

import java.time.LocalDate;
import java.util.Objects;

public class Don {
    
    int num;
    String libelle;
    String categorie;
    String type;
    LocalDate date_don;
    int quantite;
    double montant;
    String mode_paiement;
    int num_compte;
    int num_cheque;
    int cin;


    public Don(){
        
    }

    public Don(int num, String libelle, String categorie, String type, LocalDate date_don, int quantite, double montant, String mode_paiement, int num_compte, int num_cheque) {
        this.num = num;
        this.libelle = libelle;
        this.categorie = categorie;
        this.type = type;
        this.date_don = date_don;
        this.quantite = quantite;
        this.montant = montant;
        this.mode_paiement = mode_paiement;
        this.num_compte = num_compte;
        this.num_cheque = num_cheque;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getCin() {
        return this.cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate_don() {
        return this.date_don;
    }

    public void setDate_don(LocalDate date_don) {
        this.date_don = date_don;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getMontant() {
        return this.montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getMode_paiement() {
        return this.mode_paiement;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

    public int getNum_compte() {
        return this.num_compte;
    }

    public void setNum_compte(int num_compte) {
        this.num_compte = num_compte;
    }

    public int getNum_cheque() {
        return this.num_cheque;
    }

    public void setNum_cheque(int num_cheque) {
        this.num_cheque = num_cheque;
    }

    public Don num(int num) {
        setNum(num);
        return this;
    }

    public Don libelle(String libelle) {
        setLibelle(libelle);
        return this;
    }

    public Don categorie(String categorie) {
        setCategorie(categorie);
        return this;
    }

    public Don type(String type) {
        setType(type);
        return this;
    }

    public Don date_don(LocalDate date_don) {
        setDate_don(date_don);
        return this;
    }

    public Don quantite(int quantite) {
        setQuantite(quantite);
        return this;
    }

    public Don montant(double montant) {
        setMontant(montant);
        return this;
    }

    public Don mode_paiement(String mode_paiement) {
        setMode_paiement(mode_paiement);
        return this;
    }

    public Don num_compte(int num_compte) {
        setNum_compte(num_compte);
        return this;
    }

    public Don num_cheque(int num_cheque) {
        setNum_cheque(num_cheque);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Don)) {
            return false;
        }
        Don don = (Don) o;
        return num == don.num && Objects.equals(libelle, don.libelle) && Objects.equals(categorie, don.categorie) && Objects.equals(type, don.type) && Objects.equals(date_don, don.date_don) && quantite == don.quantite && montant == don.montant && Objects.equals(mode_paiement, don.mode_paiement) && num_compte == don.num_compte && num_cheque == don.num_cheque;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, libelle, categorie, type, date_don, quantite, montant, mode_paiement, num_compte, num_cheque);
    }



    @Override
    public String toString() {
        return "{" +
            " num='" + getNum() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", categorie='" + getCategorie() + "'" +
            ", type='" + getType() + "'" +
            ", date_don='" + getDate_don() + "'" +
            ", quantite='" + getQuantite() + "'" +
            ", montant='" + getMontant() + "'" +
            ", mode_paiement='" + getMode_paiement() + "'" +
            ", num_compte='" + getNum_compte() + "'" +
            ", num_cheque='" + getNum_cheque() + "'" +
            ", cin='" + getCin() + "'" +
            "}";
    }


}
