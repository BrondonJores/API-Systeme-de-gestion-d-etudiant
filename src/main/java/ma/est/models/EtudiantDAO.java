package ma.est.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import ma.est.dao.Db;

/**
 * Représente un DAO étudiant.
 * Contient un db.
 *
 * @author Brondon HOUAKEU
 * @version 1.0
 * @see EtudiantDAO
 * @see Db
 * @see ma.est.controllers.EtudiantServlet
 */

public class EtudiantDAO {

    private Db db;
    
    /**
     * Constructeur vide de la classe EtudiantDAO.
     * 
     * @see EtudiantDAO#update(Etudiant, int)
     * @see EtudiantDAO#delete(Etudiant)
     * @see EtudiantDAO#insert(Etudiant)
     * @see EtudiantDAO#find(int)
     * @see EtudiantDAO#all()
     * @throws SQLException si une erreur SQL survient
     */
    

    public EtudiantDAO() throws ClassNotFoundException {
        db = new Db();
    }

    /**
     * Retourne la liste des étudiants de la base.
     * 
     * @return la liste des étudiants si présent, une liste vide sinon
     * @throws SQLException si une erreur SQL survient
     * @see EtudiantDAO#update(Etudiant, int)
     * @see EtudiantDAO#delete(Etudiant)
     * @see EtudiantDAO#insert(Etudiant)
     * @see EtudiantDAO#find(int)
     */
    
    public List<Etudiant> all() throws SQLException {
        List<Etudiant> liste = new ArrayList<>();
        String SQL = "SELECT * FROM etudiant";

        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Etudiant e = new Etudiant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("telephone"),
                        rs.getString("adresse")
                );
                liste.add(e);
            }
        }

        return liste;
    }
    
    /**
     * Recherche un étudiant dans la base.
     * 
     * @param id étudiant à rechercher
     * @return l'étudiant si présent, null sinon
     * @throws SQLException si une erreur SQL survient
     * @see EtudiantDAO#update(Etudiant, int)
     * @see EtudiantDAO#delete(Etudiant)
     * @see EtudiantDAO#insert(Etudiant)
     * @see EtudiantDAO#all()
     */

    public Etudiant find(int id) throws SQLException {
        Etudiant etudiant = null;
        String SQL = "SELECT * FROM etudiant WHERE id = ?";

        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    etudiant = new Etudiant(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("telephone"),
                            rs.getString("adresse")
                    );
                }
            }
        }

        return etudiant;
    }
    
    /**
     * Ajoute un étudiant dans la base.
     * 
     * @param e étudiant à ajouter
     * @return true si l'ajout a réussi, false sinon
     * @throws SQLException si une erreur SQL survient
     * @see EtudiantDAO#update(Etudiant, int)
     * @see EtudiantDAO#delete(Etudiant)
     * @see EtudiantDAO#find(int)
     * @see EtudiantDAO#all()
     */

    public boolean insert(Etudiant e) throws SQLException {
        String SQL = "INSERT INTO etudiant (name, surname, telephone, adresse) VALUES (?, ?, ?, ?)";

        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL)) {

            ps.setString(1, e.getName());
            ps.setString(2, e.getSurname());
            ps.setString(3, e.getTelephone());
            ps.setString(4, e.getAdresse());

            return ps.executeUpdate() == 1;
        }
    }
    
    
    /**
     * Modifie un étudiant de la base.
     * 
     * @param e étudiant à modifier
     * @param id id de l'étudiant à modifier
     * @return true si la modification a réussi, false sinon
     * @throws SQLException si une erreur SQL survient
     * @see EtudiantDAO#insert(Etudiant)
     * @see EtudiantDAO#delete(Etudiant)
     * @see EtudiantDAO#find(int)
     * @see EtudiantDAO#all()
     */
    
    public boolean update(Etudiant e, int id) throws SQLException {
        String SQL = "UPDATE etudiant SET name = ?, surname = ?, telephone = ?, adresse = ? WHERE id = ?";

        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL)) {

            ps.setString(1, e.getName());
            ps.setString(2, e.getSurname());
            ps.setString(3, e.getTelephone());
            ps.setString(4, e.getAdresse());
            ps.setInt(5, id);

            return ps.executeUpdate() == 1;
        }
    }

    
    /**
     * Supprime un étudiant de la base.
     * 
     * @param e étudiant à supprimer
     * @return true si la suppression a réussi, false sinon
     * @throws SQLException si une erreur SQL survient
     * @see EtudiantDAO#insert(Etudiant)
     * @see EtudiantDAO#update(Etudiant, int)
     * @see EtudiantDAO#find(int)
     * @see EtudiantDAO#all()
     */
    
    public boolean delete(Etudiant e) throws SQLException {
        String SQL = "DELETE FROM etudiant WHERE id = ?";

        try (Connection cn = db.getConnection();
             PreparedStatement ps = cn.prepareStatement(SQL)) {

            ps.setInt(1, e.getID());
            return ps.executeUpdate() == 1;
        }
    }
}
