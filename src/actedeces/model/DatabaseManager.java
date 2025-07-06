package actedeces.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/acte_deces"; 
    private static final String DB_USER = "ghost"; 
    private static final String DB_PASSWORD = "Ghost@mysql1";
    
    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur de chargement du driver JDBC MySQL : " + e.getMessage());
        }
    }
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
    
    public boolean ajouterActeDeces(ActeDeces acte) throws SQLException {
    	String sql = "INSERT INTO Acte_deces (nom_defunt, sexe_defunt, date_naissance_defunt, "
				 + " lieu_naissance_defunt, ville_residence_defunt, fonction_defunt, "
				 + " nom_pere_defunt, pere_defunt_vivant, nom_mere_defunt, mere_defunt_vivante,"
				 + " nom_declarant, fonction_declarant, ville_residence_declarant, date_deces, "
				 + " heure_deces, ville_deces, lieu_deces, region_declaration, commune_declaration, "
				 + " officier_etat_civil, date_declaration) "
				 + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, acte.getNomDefunt());
            preparedStatement.setString(2, acte.getSexeDefunt());
            
            if (acte.getDateNaissanceDefunt() != null) preparedStatement.setDate(3, acte.getDateNaissanceDefunt()); else preparedStatement.setNull(3, Types.DATE);
            preparedStatement.setString(4, acte.getLieuNaissanceDefunt());
            preparedStatement.setString(5, acte.getVilleResidenceDefunt());
            preparedStatement.setString(6, acte.getFonctionDefunt());
            preparedStatement.setString(7, acte.getNomPereDefunt());
            preparedStatement.setBoolean(8, acte.isPereDefuntVivant());
            preparedStatement.setString(9, acte.getNomMereDefunt());
            preparedStatement.setBoolean(10, acte.isMereDefuntVivante());
            preparedStatement.setString(11, acte.getNomDeclarant()); 
            preparedStatement.setString(12, acte.getFonctionDeclarant());
            preparedStatement.setString(13, acte.getVilleResidenceDeclarant());
            if (acte.getDateDeces() != null) preparedStatement.setDate(14, acte.getDateDeces()); else preparedStatement.setNull(14, Types.DATE);
            if (acte.getHeureDeces() != null) preparedStatement.setTime(15, acte.getHeureDeces()); else preparedStatement.setNull(15, Types.TIME);
            preparedStatement.setString(16, acte.getVilleDeces());
            preparedStatement.setString(17, acte.getLieuDeces());
            preparedStatement.setString(18, acte.getRegionDeclaration());
            preparedStatement.setString(19, acte.getCommuneDeclaration());
            preparedStatement.setString(20, acte.getOfficierEtatCivil());
            if (acte.getDateDeclaration() != null) preparedStatement.setDate(21, acte.getDateDeclaration()); else preparedStatement.setNull(21, Types.DATE);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        acte.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
            return false;
        }
    }
    
    
    public ActeDeces getActeDecesById(int id) throws SQLException {
        String sql = "SELECT * FROM Acte_deces WHERE id = ?";
        ActeDeces acte = null;

        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        	preparedStatement.setInt(1, id);
            try (ResultSet result = preparedStatement.executeQuery()) {
                if (result.next()) {
                    acte = mapResultSetToActeDeces(result);
                }
            }
        }
        return acte;
    }
    
    
    public List<ActeDeces> getAllActeDeces() throws SQLException {
        String sql = "SELECT * FROM Acte_deces ORDER BY date_deces DESC";
        List<ActeDeces> actes = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet result = stmt.executeQuery(sql)) {
            while (result.next()) {
                actes.add(mapResultSetToActeDeces(result));
            }
        }
        return actes;
    }
    
    
    public boolean modifierActeDeces(ActeDeces acte) throws SQLException {
        String sql = "UPDATE Acte_deces SET nom_defunt = ?, sexe_defunt = ?, date_naissance_defunt = ?, "
                 + " lieu_naissance_defunt = ?, ville_residence_defunt = ?, fonction_defunt = ?, nom_pere_defunt = ?, " 
                 + " pere_defunt_vivant = ?, nom_mere_defunt = ?, mere_defunt_vivante = ?, nom_declarant = ?, " 
                 + " fonction_declarant = ?, ville_residence_declarant = ?, date_deces = ?, heure_deces = ?, " 
                 + " ville_deces = ?, lieu_deces = ?, region_declaration = ?, commune_declaration = ?, " 
                 + " officier_etat_civil = ?, date_declaration = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, acte.getNomDefunt());
            preparedStatement.setString(2, acte.getSexeDefunt());
            if (acte.getDateNaissanceDefunt() != null) preparedStatement.setDate(3, acte.getDateNaissanceDefunt()); else preparedStatement.setNull(3, Types.DATE);
            preparedStatement.setString(4, acte.getLieuNaissanceDefunt());
            preparedStatement.setString(5, acte.getVilleResidenceDefunt());
            preparedStatement.setString(6, acte.getFonctionDefunt());
            preparedStatement.setString(7, acte.getNomPereDefunt());
            preparedStatement.setBoolean(8, acte.isPereDefuntVivant());
            preparedStatement.setString(9, acte.getNomMereDefunt());
            preparedStatement.setBoolean(10, acte.isMereDefuntVivante());
            preparedStatement.setString(11, acte.getNomDeclarant());
            preparedStatement.setString(12, acte.getFonctionDeclarant());
            preparedStatement.setString(13, acte.getVilleResidenceDeclarant());
            if (acte.getDateDeces() != null) preparedStatement.setDate(14, acte.getDateDeces()); else preparedStatement.setNull(14, Types.DATE);
            if (acte.getHeureDeces() != null) preparedStatement.setTime(15, acte.getHeureDeces()); else preparedStatement.setNull(15, Types.TIME);
            preparedStatement.setString(16, acte.getVilleDeces());
            preparedStatement.setString(17, acte.getLieuDeces());
            preparedStatement.setString(18, acte.getRegionDeclaration());
            preparedStatement.setString(19, acte.getCommuneDeclaration());
            preparedStatement.setString(20, acte.getOfficierEtatCivil());
            if (acte.getDateDeclaration() != null) preparedStatement.setDate(21, acte.getDateDeclaration()); else preparedStatement.setNull(21, Types.DATE);
            preparedStatement.setInt(22, acte.getId());

            return preparedStatement.executeUpdate() > 0;
        }
    }

    
    public boolean supprimerActeDeces(int id) throws SQLException {
        String sql = "DELETE FROM Acte_deces WHERE id = ?";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }

    
    private ActeDeces mapResultSetToActeDeces(ResultSet result) throws SQLException {
        ActeDeces acte = new ActeDeces();
        acte.setId(result.getInt("id"));
        acte.setNomDefunt(result.getString("nom_defunt"));
        acte.setSexeDefunt(result.getString("sexe_defunt"));
        acte.setDateNaissanceDefunt(result.getDate("date_naissance_defunt"));
        acte.setLieuNaissanceDefunt(result.getString("lieu_naissance_defunt"));
        acte.setVilleResidenceDefunt(result.getString("ville_residence_defunt"));
        acte.setFonctionDefunt(result.getString("fonction_defunt"));
        acte.setNomPereDefunt(result.getString("nom_pere_defunt"));
        acte.setPereDefuntVivant(result.getBoolean("pere_defunt_vivant"));
        acte.setNomMereDefunt(result.getString("nom_mere_defunt"));
        acte.setMereDefuntVivante(result.getBoolean("mere_defunt_vivante"));
        acte.setNomDeclarant(result.getString("nom_declarant"));
        acte.setFonctionDeclarant(result.getString("fonction_declarant"));
        acte.setVilleResidenceDeclarant(result.getString("ville_residence_declarant"));
        acte.setDateDeces(result.getDate("date_deces"));
        acte.setHeureDeces(result.getTime("heure_deces"));
        acte.setVilleDeces(result.getString("ville_deces"));
        acte.setLieuDeces(result.getString("lieu_deces"));
        acte.setRegionDeclaration(result.getString("region_declaration"));
        acte.setCommuneDeclaration(result.getString("commune_declaration"));
        acte.setOfficierEtatCivil(result.getString("officier_etat_civil"));
        acte.setDateDeclaration(result.getDate("date_declaration"));
        return acte;
    }
    

}
