package actedeces.model;

import java.sql.Date;
import java.sql.Time;

public class ActeDeces {
	private int id;
    private String nomDefunt; 
    private String sexeDefunt;
    private Date dateNaissanceDefunt;
    private String lieuNaissanceDefunt;
    private String villeResidenceDefunt;
    private String fonctionDefunt;
    private String nomPereDefunt;
    private boolean pereDefuntVivant;
    private String nomMereDefunt;
    private boolean mereDefuntVivante;
    private String nomDeclarant;
    private String fonctionDeclarant;
    private String villeResidenceDeclarant;
    private Date dateDeces;
    private Time heureDeces;
    private String villeDeces;
    private String lieuDeces;
    private String regionDeclaration;
    private String communeDeclaration;
    private String officierEtatCivil;
    private Date dateDeclaration;

    // Constructeur par défaut
    public ActeDeces() {}

    // Constructeur avec tous les attributs
    public ActeDeces(int id, String nomDefunt, String sexeDefunt, Date dateNaissanceDefunt, String lieuNaissanceDefunt,
                     String villeResidenceDefunt, String fonctionDefunt, String nomPereDefunt, boolean pereDefuntVivant,
                     String nomMereDefunt, boolean mereDefuntVivante, String nomDeclarant, String fonctionDeclarant,
                     String villeResidenceDeclarant, Date dateDeces, Time heureDeces, String villeDeces, String lieuDeces,
                     String regionDeclaration, String communeDeclaration, String officierEtatCivil, Date dateDeclaration) {
        this.id = id;
        this.nomDefunt = nomDefunt;
        this.sexeDefunt = sexeDefunt;
        this.dateNaissanceDefunt = dateNaissanceDefunt;
        this.lieuNaissanceDefunt = lieuNaissanceDefunt;
        this.villeResidenceDefunt = villeResidenceDefunt;
        this.fonctionDefunt = fonctionDefunt;
        this.nomPereDefunt = nomPereDefunt;
        this.pereDefuntVivant = pereDefuntVivant;
        this.nomMereDefunt = nomMereDefunt;
        this.mereDefuntVivante = mereDefuntVivante;
        this.nomDeclarant = nomDeclarant; 
        this.fonctionDeclarant = fonctionDeclarant;
        this.villeResidenceDeclarant = villeResidenceDeclarant;
        this.dateDeces = dateDeces;
        this.heureDeces = heureDeces;
        this.villeDeces = villeDeces;
        this.lieuDeces = lieuDeces;
        this.regionDeclaration = regionDeclaration;
        this.communeDeclaration = communeDeclaration;
        this.officierEtatCivil = officierEtatCivil;
        this.dateDeclaration = dateDeclaration;
    }

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNomDefunt() {
		return nomDefunt;
	}

	public void setNomDefunt(String nomDefunt) {
		this.nomDefunt = nomDefunt;
	}

	public String getSexeDefunt() {
		return sexeDefunt;
	}

	public void setSexeDefunt(String sexeDefunt) {
		this.sexeDefunt = sexeDefunt;
	}

	public Date getDateNaissanceDefunt() {
		return dateNaissanceDefunt;
	}

	public void setDateNaissanceDefunt(Date dateNaissanceDefunt) {
		this.dateNaissanceDefunt = dateNaissanceDefunt;
	}

	public String getLieuNaissanceDefunt() {
		return lieuNaissanceDefunt;
	}

	public void setLieuNaissanceDefunt(String lieuNaissanceDefunt) {
		this.lieuNaissanceDefunt = lieuNaissanceDefunt;
	}

	public String getVilleResidenceDefunt() {
		return villeResidenceDefunt;
	}

	public void setVilleResidenceDefunt(String villeResidenceDefunt) {
		this.villeResidenceDefunt = villeResidenceDefunt;
	}

	public String getFonctionDefunt() {
		return fonctionDefunt;
	}

	public void setFonctionDefunt(String fonctionDefunt) {
		this.fonctionDefunt = fonctionDefunt;
	}

	public String getNomPereDefunt() {
		return nomPereDefunt;
	}

	public void setNomPereDefunt(String nomPereDefunt) {
		this.nomPereDefunt = nomPereDefunt;
	}

	public boolean isPereDefuntVivant() {
		return pereDefuntVivant;
	}

	public void setPereDefuntVivant(boolean pereDefuntVivant) {
		this.pereDefuntVivant = pereDefuntVivant;
	}

	public String getNomMereDefunt() {
		return nomMereDefunt;
	}

	public void setNomMereDefunt(String nomMereDefunt) {
		this.nomMereDefunt = nomMereDefunt;
	}

	public boolean isMereDefuntVivante() {
		return mereDefuntVivante;
	}

	public void setMereDefuntVivante(boolean mereDefuntVivante) {
		this.mereDefuntVivante = mereDefuntVivante;
	}

	public String getNomDeclarant() {
		return nomDeclarant;
	}

	public void setNomDeclarant(String nomDeclarant) {
		this.nomDeclarant = nomDeclarant;
	}

	public String getFonctionDeclarant() {
		return fonctionDeclarant;
	}

	public void setFonctionDeclarant(String fonctionDeclarant) {
		this.fonctionDeclarant = fonctionDeclarant;
	}

	public String getVilleResidenceDeclarant() {
		return villeResidenceDeclarant;
	}

	public void setVilleResidenceDeclarant(String villeResidenceDeclarant) {
		this.villeResidenceDeclarant = villeResidenceDeclarant;
	}

	public Date getDateDeces() {
		return dateDeces;
	}

	public void setDateDeces(Date dateDeces) {
		this.dateDeces = dateDeces;
	}

	public Time getHeureDeces() {
		return heureDeces;
	}

	public void setHeureDeces(Time heureDeces) {
		this.heureDeces = heureDeces;
	}

	public String getVilleDeces() {
		return villeDeces;
	}

	public void setVilleDeces(String villeDeces) {
		this.villeDeces = villeDeces;
	}

	public String getLieuDeces() {
		return lieuDeces;
	}

	public void setLieuDeces(String lieuDeces) {
		this.lieuDeces = lieuDeces;
	}

	public String getRegionDeclaration() {
		return regionDeclaration;
	}

	public void setRegionDeclaration(String regionDeclaration) {
		this.regionDeclaration = regionDeclaration;
	}

	public String getCommuneDeclaration() {
		return communeDeclaration;
	}

	public void setCommuneDeclaration(String communeDeclaration) {
		this.communeDeclaration = communeDeclaration;
	}

	public String getOfficierEtatCivil() {
		return officierEtatCivil;
	}

	public void setOfficierEtatCivil(String officierEtatCivil) {
		this.officierEtatCivil = officierEtatCivil;
	}

	public Date getDateDeclaration() {
		return dateDeclaration;
	}

	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}
    
    
	//Pour le debogage
	@Override
    public String toString() {
        return "ActeDeces{" +
               "id=" + id +
               ", nomDefunt='" + nomDefunt + '\'' +
               ", sexeDefunt='" + sexeDefunt + '\'' +
               ", dateDeces=" + dateDeces +
               '}';
    }

}
