package actedeces.utils;

public interface Formulaire {
    public String getNomDefunt();
    public String getSexeDefunt();
    public String getDateNaissanceDefunt();
    public String getLieuNaissanceDefunt();
    public String getVilleResidenceDefunt();
    public String getFonctionDefunt();
    public String getNomPereDefunt();
    public boolean isPereDefuntVivant();
    public String getNomMereDefunt();
    public boolean isMereDefuntVivante();
    public String getNomDeclarant();
    public String getFonctionDeclarant();
    public String getVilleResidenceDeclarant();
    public String getDateDeces();
    public String getHeureDeces();
    public String getVilleDeces();
    public String getLieuDeces();
    public String getRegionDeclaration();
    public String getCommuneDeclaration();
    public String getOfficierEtatCivil();
    public String getDateDeclaration();
    public void afficherMessage(String message, String titre, int typeMessage);
}
