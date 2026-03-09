package ma.est.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ma.est.models.Etudiant;
import ma.est.models.EtudiantDAO;

/**
 * Représente une classe de connexion.
 * Contient un url, un user et un password.
 *
 * @author Brondon HOUAKEU
 * @version 1.0
 * @see EtudiantDAO
 * @see Db
 * @see Etudiant
 */

public class Db {

    private String url;
    private String user;
    private String password;
    
    /**
     * Constructeur vide de la connexion.
     * 
     * @throws ClassNotFoundException si impossible de charger la classe du driver
     */

    public Db() throws ClassNotFoundException {
    	  String host = "localhost";
	      int port = 3306;
	      String dbName = "etudiants";
	      String user = "root";
	      String password = "";

        
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + dbName
                +"";
        this.user = user;
        this.password = password;
    }

    
    /**
     * Créé une connexion avec la base de données.
     * 
     * @return une connection
     * @throws SQLException si une erreur SQL survient
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
