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
       
        
        int choice =-1;
        
        try {
        	choice	 = Integer.parseInt(scan.next());
        	 } catch (Throwable x) {
        	 }
        
        do{
        switch (choice) {
            case 1:
          	
            	CalculScore board = new CalculScore(PLAYER_1, PLAYER_2);
            	
                 while(!board.gameWin) {                 	
             int playerNum = -1;
                  
                     do{
                    	
                     if(playerNum == 1 || playerNum == 2) {
                    	 String player = playerNum == 1 ? "D" : "F";
                    	 board.updateScore(player);
                         System.out.println();
                         board.displayScoreboard();
                         System.out.println();
                         System.out.println();
                         try {
                			 playerNum	 =  Integer.parseInt(scan.next());
                	        	 } catch (Throwable x) {
                	        	 }
                     } else if(playerNum == 0){
                    	 return;
                     } else {
                    	 playerNum = -1;
                    	 System.out.println();
                     	 System.out.println("Pour marquer un point pour le joueur 1 veuillez taper 1, pour marquer un point pour le joueur 2 veuillez taper 2, 0 pour sortir");
                     	 System.out.println();
                     	 try {
                			 playerNum	 =  Integer.parseInt(scan.next());
                	        	 } catch (Throwable x) {
                	        	 }
                     }
                  
                 }while(playerNum!=0);
                 }
           	
                break;
            case 2:
                System.out.println();
                playExample();
                break;
            case 0:
                break;
                default:
                	System.out.println();
                    System.out.println("*****  Pour jouer tapez 1  *****");
                    System.out.println("*****  Pour voir un exemple tapez 2  *****");
                    System.out.println("*****  sortir taper 0  *****");
                    choice = -1;
                	try {
                   		choice	 = Integer.parseInt(scan.next());
                        	 } catch (Throwable x) {                       	
                        	 }                 	         
        }
        }while(choice!= 0);
    }

}