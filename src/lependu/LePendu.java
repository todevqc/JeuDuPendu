/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lependu;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author DevComputer
 */
public class LePendu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final String VERT = "\033[0;32m";
        //****  tableau servant a prendre le design du pendu
        String afficheLePendu[] = new String[6];

        boolean rejouer;
        /*
            -scanner sc variable de lecture ecrant 
            -random pour avoir des mot a chercher aleatoire
         */
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        /*
            -declaration d'un tableau de string qui contient tous les code a trouver
            qu'il recupere a partir d'une classe "ListeCodes.aTrouver()"
            il sont recuperer dans un string
            avant d'etre spliter en fonction du caractere retour chariot "\n"
         */
        String fichier = ListeCodes.aTrouver().toUpperCase();
        String[] tableDeCodes = new String[fichier.split("\n").length];
        for (int i = 0; i < fichier.split("\n").length; i++) {
            tableDeCodes[i] = fichier.split("\n")[i];
        }

        /*
            -choisir aleatoirement jusqu'a (n-1) avec nextint,
            un code a trouvé depuit le tableaux des code de(n element)
            en utilisant la methode randome sur le nombre d'element du tableau
         */
        String codeATouver;
        //nombre de tentatives autorisé
        int limiteTentative, idexTableRepetition;
        /**
         * ** nombre de lettre trouvé pour savoir si en a gagner ou perdue **
         * ** bouleen qu'en met a true si en gagne ** bouleen pour savoir si
         * bonne lettre trouver et decrementer ensuite les tentatives
         */
        int nbrLettreTrouver;
        boolean gagner, bonneLettreTrouver;
        // lettre saisie par l'utilisateur
        char lettre;
        /*    initialisation des tableaux d'une longueur egale au code s trouver    */
        char[] tableSolution, tableARemplire, tableDesRepetitions;

        do {
            /*
            -choisir aleatoirement jusqu'a (n-1) avec nextint,
            un code a trouvé depuit le tableaux des code de(n element)
            en utilisant la methode randome sur le nombre d'element du tableau
             */
            codeATouver = tableDeCodes[random.nextInt(tableDeCodes.length)];
            //nombre de tentatives autorisé
            limiteTentative = 8;
            idexTableRepetition = 0;
            /**
             * ** nombre de lettre trouvé pour savoir si en a gagner ou perdue
             * ** ** bouleen qu'en met a true si en gagne ** bouleen pour savoir
             * si bonne lettre trouver et decrementer ensuite les tentatives
             */
            nbrLettreTrouver = 0;
            gagner = false;
            bonneLettreTrouver = false;
            /*    initialisation des tableaux d'une longueur egale au code s trouver    */
            tableSolution = new char[codeATouver.length()];
            tableARemplire = new char[codeATouver.length()];
            tableDesRepetitions = new char[codeATouver.length()];

            /*   Remplissage Des Tables : solution et a remplire, avec le code a trouver */
            remplissageDesTables(tableSolution, tableARemplire, codeATouver);

            while ((limiteTentative > 0) && (!gagner)) {
                System.out.println("\n\n\n");
                //  *****   remplire le tableau d'affichage du pendu
                DesignPendu.penduTabComplete(afficheLePendu, limiteTentative);
                if (limiteTentative < 8) {
                    //  *****   Afficher le Pendu a partire du tableau à rebour
                    DesignPendu.afficheLePendu(afficheLePendu);
                }

                System.out.println("Il vous reste " + limiteTentative + " tentatives :");
                //  ****  affichage du tableau avec mis a jour
                afficheTableAJour(tableARemplire);

                /*  recuper le premier caractere saisie, le convertie en majuscule  */
                System.out.print("\nNouvelle tentative :");
                lettre = Character.toUpperCase(sc.nextLine().charAt(0));
                /*
                tester si la lettre est presente dans le tableau et l'afficher
                si la lettre correspond, bonneLettreTrouver est a true
                 */
                if (!siLettreExiste(tableDesRepetitions, lettre)) {
                    bonneLettreTrouver = false;
                    for (int i = 0; i < tableSolution.length; i++) {
                        if (tableSolution[i] == lettre) {
                            tableARemplire[i] = lettre;
                            bonneLettreTrouver = true;
                            nbrLettreTrouver += 1;
                        }
                    }
                    if (bonneLettreTrouver) {
                        tableDesRepetitions[idexTableRepetition] = lettre;
                        idexTableRepetition++;
                    }
                }
                if (nbrLettreTrouver == codeATouver.length()) {
                    gagner = true;
                }
                /*   
                decremente les tentatives si lettre n'est pas bonne   
                si limite a 0, ajouter deriner affichage du pendu
                 */
                if (!bonneLettreTrouver) {
                    limiteTentative--;
                    if (limiteTentative == 0) {
                        afficheLePendu[1] = " |    / \\";
                        afficheLePendu[0] = "_|__";
                    }
                }
            }
            if (gagner) {
                System.out.println("\n\n\n\n\n\n\n");
                System.out.println(VERT + "Vous avez gagner");
                System.out.println("Le code recherché est : ");

                afficheTableAJour(tableARemplire);
                System.out.println("\n\n");
            } else {
                perdu(afficheLePendu);
            }

            /*  recuper le premier caractere saisie, le convertie en majuscule  */
            System.out.print("\nFaire une nouvelle partie :");
            lettre = Character.toUpperCase(sc.nextLine().charAt(0));
            if (lettre == 'O') {
                rejouer = true;
            } else {
                rejouer = false;
            }
        } while (rejouer);
    }

    private static void remplissageDesTables(char[] tableSolution, char[] tableARemplire, String codeATouver) {
        for (int i = 0; i < codeATouver.length(); i++) {
            tableSolution[i] += codeATouver.charAt(i);
            tableARemplire[i] += '_';
        }
    }

    private static void afficheTableAJour(char[] tableARemplire) {
        for (int i = 0; i < tableARemplire.length; i++) {
            System.out.print(" " + tableARemplire[i]);
        }
    }

    private static boolean siLettreExiste(char[] tableDesRepetitions, char lettre) {
        boolean existOrNot = false;
        for (int i = 0; i < tableDesRepetitions.length; i++) {
            if (tableDesRepetitions[i] == lettre) {
                existOrNot = true;
            }
        }
        return existOrNot;
    }

    private static void perdu(String[] afficheLePendu) {
        final String ROUGE = "\033[0;31m";
        System.out.println("\n\n\n\n");
        //  *****   Afficher le Pendu a partire du tableau à rebour
        DesignPendu.afficheLePendu(afficheLePendu);
        System.out.println(ROUGE + "Vous avez perdue\n\n");
    }
    
}
