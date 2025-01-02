// Fichier  : VoyageTest.java


/** package & import */

import pkg.Pays;
import pkg.Voyage;     

/** Classe VoyageTest du package pkg exploitation algorithmique d'un probleme */
public class VoyageTest{


    public static void main(String args[]) {
        
        //Creation des pays

        Pays paysA = new Pays("A");
        Pays paysB = new Pays("B");
        Pays paysC = new Pays("C");
        Pays paysD = new Pays("D");
        Pays paysE = new Pays("E");
        Pays paysF = new Pays("F");
        Pays paysG = new Pays("G");
        Pays paysH = new Pays("H");
        
        //Creation des arretes entre les pays en precisant la duree de quarantaine

        paysA.addDestination(paysB,0);
        paysB.addDestination(paysC,0);
        paysB.addDestination(paysD,0);
        paysD.addDestination(paysE,0);
        paysE.addDestination(paysF,0);
        paysF.addDestination(paysH,0);
        paysH.addDestination(paysG,0);
        paysG.addDestination(paysF,0);
        paysA.addDestination(paysG,0);
        //Creation du graphe (voyage)

        Voyage v = new Voyage();
        
        //Ajout des pays dans notre graphe (voyage)

        v.ajouterPays(paysA);
        v.ajouterPays(paysB);
        v.ajouterPays(paysC);
        v.ajouterPays(paysD);
        v.ajouterPays(paysE);
        v.ajouterPays(paysF);
        v.ajouterPays(paysG);

        //Pays de départ
        Pays pays_dep=paysA;

        //Pays d'arrivée
        Pays pays_arr=paysG;

        //Appliquation de l'algorithme de Dijstra
        v = Voyage.calculerTrajetPlusCourt(v, pays_dep);
        
        //Affichage du plus court chemin entre le pays de depart et le pays d'arrivee
        System.out.println(Pays.plusCourtChemin(pays_dep,pays_arr));} 

        //Affichage graphique pour la creation de pays et voyages ou on peut add destination, ajouter pays, calculer trajet plus court, plus court chemin

}
