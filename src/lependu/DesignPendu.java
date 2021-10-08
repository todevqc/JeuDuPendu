/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lependu;

/**
 *
 * @author DevComputer
 */
public class DesignPendu {
    
    public static String[] penduTabComplete(String tabAffichePendu[], int limiteTentative) {

        switch (limiteTentative) {
            case (8) -> {
                tabAffichePendu[5] = "";
                tabAffichePendu[4] = "";
                tabAffichePendu[3] = "";
                tabAffichePendu[2] = "";
                tabAffichePendu[1] = "";
                tabAffichePendu[0] = "";
            }
            case (7) -> {
                tabAffichePendu[0] = "____";
            }
            case (6) -> {
                tabAffichePendu[4] = " |";
                tabAffichePendu[3] = " |";
                tabAffichePendu[2] = " |";
                tabAffichePendu[1] = " |";
                tabAffichePendu[0] = "_|__";
            }
            case (5) -> {
                tabAffichePendu[5] = " ______";
            }
            case (4) -> {
                tabAffichePendu[4] = " | /";
                tabAffichePendu[3] = " |/";
            }
            case (3) -> {
                tabAffichePendu[4] = " | /   |";
                tabAffichePendu[3] = " |/    |";
            }
            case (2) -> {
                tabAffichePendu[1] = " |    ___";
                tabAffichePendu[0] = "_|__ |///|";
            }
            case (1) -> {
                tabAffichePendu[3] = " |/    O";
                tabAffichePendu[2] = " |    /|\\";
                tabAffichePendu[1] = " |    /_\\";
            }
        }
        return tabAffichePendu;
    }
    
    public static void afficheLePendu(String tabAffichePendu[]) {
        for (int i = tabAffichePendu.length - 1; i >= 0; i--) {
            System.out.println(tabAffichePendu[i]);
        }        
    }
    
    
}
