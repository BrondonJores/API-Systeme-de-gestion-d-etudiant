package ma.est.models;

import java.io.Serializable;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Représente un étudiant.
 * Contient son nom, prénom, téléphone et adresse.
 *
 * @author Brondon HOUAKEU
 * @version 1.0
 * @see EtudiantDAO
 */
@XmlRootElement
public class Etudiant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int ID;
	private String name;
	private String surname;
	private String telephone;
	private String adresse;
	
	
	/**
     * Constructeur vide d'un étudiant.
     * 
     */
	
	public Etudiant() {
		 
	}
	
	/**
     * Constructeur paramétré d'un étudiant
     * 
     * @param name Nom de l'étudiant
     * @param surname Prénom de l'étudiant
     * @param telephone Numéro de téléphone de l'étudiant
     * @param adresse Adresse de l'étudiant
     */
	public Etudiant(int iD, String name, String surname, String telephone, String adresse) {
		super();
		ID = iD;
		this.name = name;
		this.surname = surname;
		this.telephone = telephone;
		this.adresse = adresse;
	}
	
	
	/**
     * Retourne l'id de l'étudiant.
     * 
     * @return l'id
     */
	public int getID() {
		return ID;
	}
	
	/**
     * Modifie l'id de l'étudiant.
     * 
     * @param iD Id de l'étudiant
     */
	public void setID(int iD) {
		ID = iD;
	}
	
	/**
     * Retourne le nom de l'étudiant.
     * 
     * @return le nom
     */
	public String getName() {
		return name;
	}
	
	/**
     * Modifie le nom de l'étudiant
     * 
     * @param name Nom de l'étudiant
     */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
     * Retourne le prénom de l'étudiant.
     * 
     * @return le prénom
     */
	public String getSurname() {
		return surname;
	}
	
	/**
     * Modifie le prénom de l'étudiant
     * 
     * @param surname le prénom 
     */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
     * Retourne le numéro de téléphone de l'étudiant.
     * 
     * @return le numéro de téléphone
     */
	public String getTelephone() {
		return telephone;
	}
	
	/**
     * Modifie le numéro de téléphone de l'étudiant.
     * 
     * @param telephone
     */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
     * Retourne l'adresse de l'étudiant.
     * 
     * @return l'adresse
     */
	public String getAdresse() {
		return adresse;
	}
	
	/**
     * Modifie l'adresse de l'étudiant
     * 
     * @param adresse
     */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/**
     * Retourne l'id de sérialization de la classe étudiant.
     * 
     * @return l'id de sérialization
     */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
