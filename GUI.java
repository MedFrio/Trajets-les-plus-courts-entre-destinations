import javax.swing.*;

import pkg.Pays;
import pkg.Voyage;

import java.awt.*;
import java.awt.event.*;

public class GUI {


    //Affichage graphique pour la creation de pays et voyages ou on peut add destination, ajouter pays, calculer trajet plus court, plus court chemin
    public static void main(String args[]) {
        Voyage v = new Voyage();
        JFrame frame = new JFrame("Voyage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(3, 2));

        JButton addPays = new JButton("Ajouter Pays");
        JButton addDestination = new JButton("Ajouter Destination");
        JButton calculerTrajetPlusCourt = new JButton("Calculer Trajet Plus Court");
        JButton Export_Voyage = new JButton("Export Voyage");
        JButton Dev_DUMP = new JButton("★DEV_DUMP");
        JButton Dev_Generate = new JButton("★DEV_GENERATE");
        JButton Dev_GRAPH = new JButton("★DEV_GRAPH");
        JButton Dev_STATS = new JButton("★DEV_STATS");
        JButton Quit = new JButton("Quitter");


        frame.add(addPays);
        frame.add(addDestination);
        frame.add(calculerTrajetPlusCourt);
        frame.add(Export_Voyage);
        frame.add(Dev_DUMP);
        frame.add(Dev_Generate);
        frame.add(Dev_GRAPH);
        frame.add(Dev_STATS);
        frame.add(Quit);
        //dev graphe inactif
        Dev_GRAPH.setEnabled(false);

        //Block frame dimension not resizable
        frame.setResizable(false);





        frame.setVisible(true);




        addPays.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pays=null;
                Pays p = null;
                while (pays == null || pays.equals("")) {
                    // if the user cancel or close the dialog, close
                    if (JOptionPane.showConfirmDialog(null, "Ajouter un pays?", "Ajouter Pays", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                        return;
                    }
                    pays = JOptionPane.showInputDialog("Nom du pays");
                    System.out.println(pays);
                    p = new Pays(pays);
                }
                //pays already exists
                if (v.getPays().toString().contains(pays)) {
                    JOptionPane.showMessageDialog(null, "Pays déjà existant.");
                }
                else
                if (p != null){
                v.ajouterPays(p);
                //Desactivate button
                Dev_Generate.setEnabled(false);
                }

        }
        });

        addDestination.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Choix uniquement de pays existant dans voyages en utilisant getPays
                JComboBox<Pays> pays1 = new JComboBox<Pays>();
                for (Pays p : v.getPays()) {
                    pays1.addItem(p);
                }
                JComboBox<Pays> pays2 = new JComboBox<Pays>();
                for (Pays p : v.getPays()) {
                    pays2.addItem(p);
                }
                JTextField duree = new JTextField();
                Object[] message = {
                    "Pays départ:", pays1,
                    "Pays arrivée:", pays2,
                    "Durée (0 MIN - 1000 MAX):", duree
                };
            try {
                int option = JOptionPane.showConfirmDialog(null, message, "Ajouter Destination", JOptionPane.OK_CANCEL_OPTION);
                //verify d is a number
                if (option == JOptionPane.OK_OPTION && !duree.getText().equals("") && !duree.getText().equals(null) && !pays1.getSelectedItem().equals(pays2.getSelectedItem()) && !pays1.getSelectedItem().equals(null) && !pays2.getSelectedItem().equals(null) && Integer.parseInt(duree.getText())>=0 && Integer.parseInt(duree.getText())<=1000 && !pays1.getSelectedItem().equals("") && !pays2.getSelectedItem().equals("")) {
                    Pays p1 = (Pays) pays1.getSelectedItem();
                    Pays p2 = (Pays) pays2.getSelectedItem();
                    int d = Integer.parseInt(duree.getText());
                    p1.addDestination(p2, d);

                    

                }
                else if (JOptionPane.CANCEL_OPTION == option || JOptionPane.CLOSED_OPTION == option) {
                    System.out.println("Cancelled");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Erreur dans les données entrées. Veuillez réessayer.");
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Erreur dans les données entrées. Veuillez réessayer.");
            }
            catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Erreur dans les données entrées. Veuillez réessayer.");
            }}});
        calculerTrajetPlusCourt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox<Pays> pays1 = new JComboBox<Pays>();
                for (Pays p1 : v.getPays()) {
                    pays1.addItem(p1);
                }
                JComboBox<Pays> pays2 = new JComboBox<Pays>();
                for (Pays p2 : v.getPays()) {
                    pays2.addItem(p2);
                }
                Object[] message = {
                    "Pays départ:", pays1,
                    "Pays arrivée:", pays2
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Calculer Trajet Plus Court", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION && pays1.getSelectedItem()!=pays2.getSelectedItem() && !pays1.getSelectedItem().equals(null) && !pays2.getSelectedItem().equals(null)){
                    Pays p1 = (Pays) pays1.getSelectedItem();
                    Pays p2 = (Pays) pays2.getSelectedItem();
                    if (!p1.getPaysVoisins().isEmpty() || !p2.getPaysVoisins().isEmpty()){
                        System.out.println(p1);
                        System.out.println(p2);
                        Voyage.calculerTrajetPlusCourt(v, p1);
                        System.out.println(Pays.plusCourtChemin(p1, p2));
                        //frame that shows the shortest path
                        JFrame frame2 = new JFrame("Plus court chemin");
                        frame2.setSize(400, 400);
                        frame2.setLayout(new GridLayout(1, 1));
                        JLabel label = new JLabel(Pays.plusCourtChemin(p1, p2));
                        frame2.add(label);
                        frame2.setVisible(true); 
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Calcul impossible, veuillez ajouter au moins une destinations aux pays sélectionnés.");
                    }

                }
                else if (JOptionPane.CANCEL_OPTION == option || JOptionPane.CLOSED_OPTION == option) {
                    System.out.println("Cancelled");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Erreur dans les données entrées. Veuillez réessayer.");
                }
            }});
        Export_Voyage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (v.getPays().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Voyage non exportable, veuillez ajouter des pays.");
                    
                }
                else{

                    v.exportVoyage();
                    JOptionPane.showMessageDialog(null, "Voyage exporté avec succès.\n Attention : Ce fichier contient uniquement les pays et leur duree de quarantaine et non les destinations");
                }
       


            }
        });
        Dev_DUMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                v.viderPays();
                JOptionPane.showMessageDialog(null, "Voyage vidé avec succès.");
                //Activate button
                Dev_Generate.setEnabled(true);
                //activate ajouter pays
                addPays.setEnabled(true);
            }
        });
        Dev_Generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               //Ask for number of countries
                int number_of_countries = 0;
                while (number_of_countries == 0) {
                    // if the user cancel or close the dialog, close
                    if (JOptionPane.showConfirmDialog(null, "Générer un nombre de pays?", "Générer Pays", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                        return;
                    }
                    String number_of_countries_string = JOptionPane.showInputDialog("Nombre de pays à générer");
                    try {
                        number_of_countries = Integer.parseInt(number_of_countries_string);
                    } catch (NumberFormatException nfe) {
                        JOptionPane.showMessageDialog(null, "Erreur dans les données entrées. Veuillez réessayer.");
                    }
                }
                
                //Generate countries
                for (int i = 0; i < number_of_countries; i++) {
                    Pays p = new Pays("Pays" + i);
                    v.ajouterPays(p);
                    if (v.getPays().size() >1){
                        //int d >=0 && d<=1000;
                        Integer d=-1;
                        while (d <= 0 || d >= 1000) {
                            d = (int) (Math.random() * 1000);
                        }
                        Pays[] listes_pays = v.getPays().toArray(new Pays[v.getPays().size()]);
                        int random_pays= (int) (Math.random() * v.getPays().size());
                        while (listes_pays[random_pays]==p){
                            random_pays = (int) (Math.random() * v.getPays().size()-1);
                        }
                        
                        listes_pays[random_pays].addDestination(p, (Integer) d);
                        System.out.println(listes_pays[random_pays] + " " + p + " " + (Integer) d);
                        
                    }
                    
                }
                if (v.getPays().size() == 0){
                    JOptionPane.showMessageDialog(null, "Nombre de pays fournies negatif ou nul. Veuillez réessayer.");
                }
                else
                JOptionPane.showMessageDialog(null, "Pays générés avec succès.");
                //Deactivate button
                Dev_Generate.setEnabled(false);
                //deactivate ajouter pays
                addPays.setEnabled(false);
                //Set <Pays> : find pays named Pays0
                Pays pays_depart = null;
                Pays pays_arrivee = null;
                for (Pays p : v.getPays()) {
                    if (p.toString().equals("Pays0")) {
                    pays_depart = p;
                    }
                }
                //get size of pays
                int size = v.getPays().size();
                //with size find last pays
                for (Pays p : v.getPays()) {
                    if (p.toString().equals("Pays" + (size - 1))) {
                        pays_arrivee = p;
                    }
                }
                System.out.println("Pays de départ : " + pays_depart);
                Voyage.calculerTrajetPlusCourt(v, pays_depart);
                System.out.println(Pays.plusCourtChemin(pays_depart, pays_arrivee));

            }

        });

        Dev_GRAPH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Draw graph - GUI (of countries)
                JFrame frame3 = new JFrame("Graph");
                frame3.setSize(400, 400);
                frame3.setLayout(new GridLayout(1, 1));
                JLabel label = new JLabel("Graph");
                frame3.add(label);
                frame3.setVisible(true);
                
                        
                        


            }

        });
        Dev_STATS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Show stats
                if (v.getPays().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Aucun pays dans le voyage.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nombre de pays : " + v.getPays().size());
                    //STATISTICS
                    int max = 0;
                    int min = 1000;
                    int sum = 0;
                    for (Pays p : v.getPays()) {
                        if (p.getQuarantaine() > max) {
                            max = p.getQuarantaine();
                        }
                        if (p.getQuarantaine() < min) {
                            min = p.getQuarantaine();
                        }
                        sum += p.getQuarantaine();
                    }
                    JOptionPane.showMessageDialog(null, "Durée de quarantaine maximale : " + max + "\nDurée de quarantaine minimale : " + min + "\nDurée de quarantaine moyenne : " + sum / v.getPays().size());


                }
            }

        });
        Quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });

    }
   
}
