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
@XmlRootElement(name="étudiants")
public class EtudiantList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private List<Etudiant> etudiants;
	
	/**
     * Constructeur vide d'une liste d'étudiants.
     * 
     */
	
	public EtudiantList() {} 
	
	public EtudiantList(List<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	
	@XmlElement(name="étudiant")
    public List<Etudiant> getListe() {
        return etudiants;
    }

    public void setListe(List<Etudiant> liste) {
        this.etudiants = liste;
    }
	
	

}
