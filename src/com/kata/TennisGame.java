package com.kata;

import java.util.Scanner;


public class TennisGame {
    
    static final String PLAYER_1 = "D";
    static final String PLAYER_2 = "F";
    
    
    public static void playExample() {
    	CalculScore board = new CalculScore(PLAYER_1, PLAYER_2);
    	
    	 board.updateScore("D");
    	 board.updateScore("D");
    	 board.updateScore("F");
    	 board.updateScore("D");
    	 board.updateScore("F");
    	 board.updateScore("F");
    	 board.updateScore("F");
    	 board.updateScore("F");
    	 board.displayScoreboard();
    	   	
    }

    public static  void main(String args[]) {        
        Scanner scan = new Scanner(System.in);
        
        System.out.println();
        System.out.println("*****  Pour jouer tapez 1  *****");
        System.out.println("*****  Pour voir un exemple tapez 2  *****");
        System.out.println("*****  sortir taper 0  *****");
       
        int choice = scan.nextInt();
        do{
        if(choice != 1 && choice != 2 && choice != 0 ){
        	   System.out.println( " Veuillez entré l'un des choix proposé : ");
        	   choice = scan.nextInt();
        	   } 

        switch (choice) {
            case 1:
          	
            	CalculScore board = new CalculScore(PLAYER_1, PLAYER_2);
            	
                 while(!board.gameWin) {
                 	 System.out.println();
                 	 System.out.println("Pour marquer un point pour le joueur 1 veuillez taper 1, pour marquer un point pour le joueur 2 veuillez taper 2, 0 pour sortir");
                 	 System.out.println();
                 	 
             int playerNum = scan.nextInt();
                    
                     do{
             	        if(playerNum != 1 && playerNum != 2 && playerNum != 0 ){
             	        	  playerNum = scan.nextInt();
             	        	   } 
                     if(playerNum == 1 || playerNum == 2) {
                    	 String player = playerNum == 1 ? "D" : "F";
                    	 board.updateScore(player);
                         System.out.println();
                         board.displayScoreboard();
                         System.out.println();
                         System.out.println();
                     } else if(playerNum == 0){
                    	 return;
                     }
                  
                 }while(playerNum!= 1 && playerNum != 2 && playerNum!= 3 );
                 }
           	
                break;
            case 2:
                System.out.println();
                playExample();
                break;
            case 0:
                break;
         
        }
        }while(choice!= 1 && choice != 2 && choice!= 0);
    }

}