package com.kata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class CalculScore {
    private ArrayList<String> player1;
    private ArrayList<String> player2;
    public int player1Set = 0 ;
    public int player2Set = 0 ;
    public int player1Score = 0 ;
    public int player2Score = 0 ;
    private String player1name;
    private String player2name;
    public boolean gameWin = false;
    public boolean setPoint = false;
    private static HashMap<String, String> scores;
    
    private int scoreboardLastIndex;
    
    CalculScore(String player1name, String player2name){
        this.player1name = player1name;
        this.player2name = player2name;
        player1 = new ArrayList<String>();
        player2 = new ArrayList<String>();
        player1.add("0");
        player2.add("0");
        scoreboardLastIndex = player1.size() - 1;
        
        scores = new HashMap<String, String>();
        scores.put("0", "15");
        scores.put("15", "30");
        scores.put("30", "40");
        scores.put("40", "A");
        scores.put("A", "game");
    }
    
    public boolean isDuce(){
        return currentPlayerScore(player1name) == "40" && currentPlayerScore(player2name) == "40";
    }
    
    public boolean updateScore(String playerScored) {
        if ( gameHasEnded() ) {
        	
            player1 = new ArrayList<String>();
            player2 = new ArrayList<String>();
            player1.add("0");
            player2.add("0");
            scoreboardLastIndex = player1.size() - 1;
        }
        
        String player1_currentScore = currentPlayerScore(player1name);
        String player2_currentScore = currentPlayerScore(player2name);
        
        boolean player1_reached40 = reached40(player1name);
        boolean player2_reached40 = reached40(player2name);
        
        
        if(setPoint) {
        	
        
        if(playerScored == player1name) {
        	if(Integer.parseInt(player1.get(player1.size()-1))==9) {
        		player1.add("game");
        		player2.add(player2_currentScore);
        		setPoint=false;
        	} else {
        		player1.add(Integer.toString(Integer.parseInt(player1_currentScore)+1));
        		player2.add(player2_currentScore);
        	}
        	
        } else if(playerScored == player2name){
        	if(Integer.parseInt(player2.get(player2.size()-1))==9) {
        		player2.add("game");
        		player1.add(player1_currentScore);
        		setPoint = false;
        	} else {
        		player2.add(Integer.toString(Integer.parseInt(player2_currentScore)+1));
        		player1.add(player1_currentScore);
        	}
        
        	}
        	}else {
        
        if ( playerScored == player1name ) {
            if ( !player2_reached40 ) {
                if( player1_reached40 ) {
                    player1.add("game");
        
                    player2.add(player2_currentScore);
                }
                else {
                    player1.add(scores.get(player1_currentScore));
                    player2.add(player2_currentScore);
                }
            }
            else {
                if( player1_reached40 ) {
                    if ( player2_currentScore == "A" ) {
                        player1.add("40");
                        player2.add("40");
                    }
                    else {
                        player1.add(scores.get(player1_currentScore));
                        player2.add(player2_currentScore);
                    }
                }
                else {
                    if ( player2_currentScore == "A" ) {
                        player1.add(scores.get(player1_currentScore));
                        player2.add("40");
                    }
                    else {
                        player1.add(scores.get(player1_currentScore));
                        player2.add(player2_currentScore);
                    }
                }
            }
        }
        else {
            if ( !player1_reached40 ) {
                if( player2_reached40 ) {
                    player2.add("game");
                  
                    player1.add(player1_currentScore);
                }
                else {
                    player2.add(scores.get(player2_currentScore));
                    player1.add(player1_currentScore);
                }
            }
            else {
                if( player2_reached40 ) {
                    if ( player1_currentScore == "A" ) {
                        player2.add("40");
                        player1.add("40");
                    }
                    else {
                        player2.add(scores.get(player2_currentScore));
                        player1.add(player1_currentScore);
                    }
                }
                else {
                    if ( player1_currentScore == "A" ) {
                        player2.add(scores.get(player2_currentScore));
                        player1.add("40");
                    }
                    else {
                        player2.add(scores.get(player2_currentScore));
                        player1.add(player1_currentScore);
                    }
                }
            }           
        }       
        	}

        scoreboardLastIndex++;
        return true;
    }
        
    private boolean reached40(String player) {
        String currentScore = currentPlayerScore(player);
        return currentScore == "40" || currentScore == "A" || currentScore == "game";
    }
    
    public boolean gameHasEnded() {
        return player1.get(scoreboardLastIndex) == "game" ||
                player2.get(scoreboardLastIndex) == "game";
    }
    
    private String currentPlayerScore(String player) {
        if ( player == player1name ) {
            return player1.get(scoreboardLastIndex);
        }
        else {
            return player2.get(scoreboardLastIndex);
        }
    }
    
    public String currentScore() {
        return currentPlayerScore(player1name) + " - " + currentPlayerScore(player2name);
    }

    private int scoreBoardLength(){
        return scoreboardLastIndex + 1 ;
    }

    public void displayScoreboard(){
    	if(player1.get(scoreboardLastIndex) == "game" ) {
            player1Set = player1Set + 1;
            if(((player1Set == 6)&&(player2Set<5)) || (player1Set == 7) ) {
            	player1Set = 0;
            	player2Set = 0;
            	player1Score = player1Score+1;
            	  if(player1Score == 3){
            		  gameWin = true;
            	  }          	
            } else if (((player1Set == 6)&&(player2Set==6)) ) {
            	setPoint = true;
            }
        }
 if(player2.get(scoreboardLastIndex) == "game" ) {
	  player2Set = player2Set + 1;
	  if(((player2Set == 6)&&(player1Set<5)) || (player2Set == 7) ) {
		  player1Set = 0;
       	player2Set = 0;
		  player2Score = player2Score+1;
    	  if(player2Score == 3){
    		  gameWin = true;
    	  }          
      }else if (((player2Set == 6)&&(player1Set==6)) ) {
      	setPoint = true;
      }
        }
        displayScore(player1,player2);
        System.out.println();
        System.out.println();        
        String leftAlignFormat = "| %-15s | %-4s | %-4s  |  %-4s       |%n";
                                                        

		System.out.format("+-----------------+------+-------+-------------+%n");
		System.out.format("| Player Name     | Set  | Score | Match Score |%n");
		System.out.format("+-----------------+------+-------+-------------+%n");
		System.out.format(leftAlignFormat, "Player1" , player1.get(player1.size()-1), player1Set,player1Score);
		System.out.format(leftAlignFormat, "Player2" , player2.get(player2.size()-1), player2Set,player2Score);
		System.out.format("+-----------------+------+-------+-------------+%n");

    }

    private void displayScore(ArrayList<String> player,ArrayList<String> player1){
    	
    	String str = "";   	
    	String column = "";
    	String top = "";
    	
    	for (int i = 0; i < player.size(); i++) {
    		str = str + " %-4s    |";
    		column = column + " Point   |";
    		top = top +"---------+";    	   
    	}
    	
    	String leftAlignFormat = "| %-15s |"+str+"%n";
    	System.out.format("+-----------------+"+top+"%n");
    	System.out.format("| Column name     |"+column+"%n");
    	System.out.format("+-----------------+"+top+"%n");
    	
    	 switch (player.size()) {
         case 1:   System.out.format(leftAlignFormat, " Player1" , player.get(0));
                   System.out.format(leftAlignFormat, " Player2" , player1.get(0));
                  break;
         case 2:  System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1));
         		  System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1));
                  break;
         case 3:  System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2));
                  System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2));
                  break;
        
         case 4: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3));
                 System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3));
                  break;
         case 5: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4));
                 System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4));
                  break;
         case 6: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5));
                 System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5));
                 break;
         case 7: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6));
                 System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6));
                 break;
         case 8: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7));
                 System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7));
                 break;
         case 9: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8));
                 System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8));
                 break;
         case 10: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9));
                 System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9));
                 break;
         case 11: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10));
                 System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10));
                 break;
         case 12: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11));
                  System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11));
                  break;
         case 13: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11),player.get(12));
                  System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11),player1.get(12));
                  break;
         case 14: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11),player.get(12),player.get(13));
                 System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11),player1.get(12),player1.get(13));
                 break;
		 case 15: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11),player.get(12),player.get(13),player.get(14));
		          System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11),player1.get(12),player1.get(13),player1.get(14));
		          break;
		 case 16: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11),player.get(12),player.get(13),player.get(14),player.get(15));
		         System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11),player1.get(12),player1.get(13),player1.get(14),player1.get(15));
		         break;
		 case 17: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11),player.get(12),player.get(13),player.get(14),player.get(15),player.get(16));
		          System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11),player1.get(12),player1.get(13),player1.get(14),player1.get(15),player1.get(16));
		          break;
		 case 18: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11),player.get(12),player.get(13),player.get(14),player.get(15),player.get(16),player.get(17));
		          System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11),player1.get(12),player1.get(13),player1.get(14),player1.get(15),player1.get(16),player1.get(17));
		          break;
		 case 19: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11),player.get(12),player.get(13),player.get(14),player.get(15),player.get(16),player.get(17),player.get(18));
                  System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11),player1.get(12),player1.get(13),player1.get(14),player1.get(15),player1.get(16),player1.get(17),player1.get(18));
                  break;
         case 20: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11),player.get(12),player.get(13),player.get(14),player.get(15),player.get(16),player.get(17),player1.get(18),player1.get(19));
                  System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11),player1.get(12),player1.get(13),player1.get(14),player1.get(15),player1.get(16),player1.get(17),player1.get(18),player1.get(19));
                  break;
         case 21: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11),player.get(12),player.get(13),player.get(14),player.get(15),player.get(16),player.get(17),player.get(18),player1.get(19),player1.get(20));
                  System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11),player1.get(12),player1.get(13),player1.get(14),player1.get(15),player1.get(16),player1.get(17),player1.get(18),player1.get(19),player1.get(20));
                  break;
		 case 22: System.out.format(leftAlignFormat, " Player1" , player.get(0),player.get(1),player.get(2),player.get(3),player.get(4),player.get(5),player.get(6),player.get(7),player.get(8),player.get(9),player.get(10),player.get(11),player.get(12),player.get(13),player.get(14),player.get(15),player.get(16),player.get(17),player1.get(18),player1.get(19),player1.get(20),player1.get(21));
		         System.out.format(leftAlignFormat, " Player2" , player1.get(0),player1.get(1),player1.get(2),player1.get(3),player1.get(4),player1.get(5),player1.get(6),player1.get(7),player1.get(8),player1.get(9),player1.get(10),player1.get(11),player1.get(12),player1.get(13),player1.get(14),player1.get(15),player1.get(16),player1.get(17),player1.get(18),player1.get(19),player1.get(20),player1.get(21));
		         break;
         default:
                  break;
    	 }    	
    	 System.out.format("+-----------------+"+top+"%n");

    }
}