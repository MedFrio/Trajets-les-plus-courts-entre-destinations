// Fichier  : Pays.java


/** package & import */

package pkg;
import java.util.*;

/** Classe Pays du package sae de la SAE S2.02 exploitation algorithmique d'un probleme  */
public class Pays {
    
    /** nomPays est un String, cet argument vise à attribuer un nom à notre Pays */
    private String nomPays;
    
    /** courtTrajet est une Liste de Pays, cet argument vise à creer une liste de pays auquel il est lie */
    private List<Pays> courtTrajet = new LinkedList<>();
    
    /** quarantaine est un Int, cet argument represente la duree de quarantaine */
    private Integer quarantaine = Integer.MAX_VALUE;
    
    /** paysVoisins est une HashMap representant les pays voisins */
    Map<Pays, Integer> paysVoisisns = new HashMap<>();

    /** Constructeur de notre Class Pays qui prend un string en argument */
    public Pays(String nomPays) {
        this.nomPays = nomPays;
    }

    /** Methode addDestination qui prend un pays et un int en arguments qui rajoute ces arguments dans la Map */
    public void addDestination(Pays destination, int quarantaine) {
        paysVoisisns.put(destination, quarantaine);
    }

    /** Methode getPaysVoisins qui renvoie les pays voisins */
    public Map<Pays, Integer> getPaysVoisins(){

        return paysVoisisns;
        
    }
    
    /** Methode getQuarantaine represente le getter de quarantaine */
    public Integer getQuarantaine(){

        return quarantaine;

    }
    /** Methode toString qui renvoie le nom du pays */
    public String toString(){

        return nomPays;

    }
    
    /** Methode getCourtTrajet qui est le getter de courtTrajet - without brackets*/
    public List<Pays> getCourtTrajet(){
            
            return courtTrajet;

    }

    /** Methode getCourtTrajet qui est le getter de courtTrajet - without brackets*/
    public String getCourtTrajet_toString(){
        String courtTrajet_string=courtTrajet.toString();
        courtTrajet_string=courtTrajet_string.replace("[", "");
        courtTrajet_string=courtTrajet_string.replace("]", "");
        return courtTrajet_string;

}

    /** Methode setQuarantaine qui prend un int en argument qui modifie la duree de quarantaine */
    public void setQuarantaine(Integer d){

        quarantaine=d;

    }
    
    /** Methode setCourtTrajet qui prend une Liste de pays en argument qui modifie courtTrajet */
    public void setCourtTrajet(List<Pays> courtT){

        courtTrajet=courtT;

    }

    /** methode setNomPays qui change le nom d'un pays */
    public void setNomPays(String nomP){

        nomPays=nomP;

    }

    /** Methode qui affiche le plus court chemin entre le pays de depart et le pays d'arrivee */
    public static String plusCourtChemin(Pays p1,Pays p2){

        return ("Le plus court chemin entre : "+p1+" et "+p2+" est : "+p2.getCourtTrajet_toString()+" et "+p2.toString());

    }



}
