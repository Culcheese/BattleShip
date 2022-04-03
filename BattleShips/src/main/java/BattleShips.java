package BattleShips;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.*;

public class BattleShips {
	public static int turn = -1;
    public static int numRows = 10;
    public static int numCols = 10;
    public static int playerShips;
    public static int computerShips;
    public static String[][] playerGrid = new String[numRows][numCols];
    public static String[][] cpuGrid = new String[numRows][numCols];

    public static void main(String[] args) throws IOException{
        System.out.println("**** Welcome to Battle Ships game ****");
        System.out.println("Right now, sea is empty\n");
        //display board
        Board board = new Board();
        
        for(int i = 0; i < playerGrid.length; i++) {
            for (int j = 0; j < playerGrid[i].length; j++) {
               playerGrid[i][j] = " ";
               cpuGrid[i][j] = " ";
            }
        }
                
        deployComputerShips();

        //Step 2 Deploy player ships
        deployPlayerShips();

        //Step 3 - Deploy computer's ships
        //deployComputerShips();
        turn = 0;
        //Step 4 Battle

        //Step 5 - Game over
        //gameOver();
    }

    public static void deployPlayerShips(){
        System.out.println("\nPlayer is deploying ships");
        //Deploying five ships for computer
        BattleShips.playerShips = 5;
        for (int i = 1; i <= BattleShips.playerShips; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (playerGrid[x][y] == " "))
            {
                playerGrid[x][y] =   "x";
                System.out.println(i + ". ship DEPLOYED");
                i++;
            }
        }
    }

    public static void deployComputerShips(){
        System.out.println("\nComputer is deploying ships");
        //Deploying five ships for computer
        BattleShips.computerShips = 5;
        for (int i = 1; i <= BattleShips.computerShips; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numRows) && (y >= 0 && y < numCols) && (cpuGrid[x][y] == " "))
            {
                cpuGrid[x][y] =   "x";
                System.out.println(i + ". ship DEPLOYED");
                i++;
            }
        }
    }

    public static void computerTurn(){
        System.out.println("\nCOMPUTER'S TURN");
        //Guess co-ordinates
        int x = -1, y = -1;
        boolean repeat = false;
        while((x < 0 || x >= numRows) || (y < 0 || y >= numCols) || repeat) {
        	repeat = false;
        	x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);

            if ((x >= 0 && x < numRows) && (y >= 0 && y < numCols)) //valid guess
            {
                if (playerGrid[x][y] == "x") //if player ship is already there; player loses ship
                {
                    System.out.println("The Computer sunk one of your ships!");
                    playerGrid[x][y] = "y";
                    Board.playerPanels[x][y].setBackground(Color.RED);
                    --BattleShips.playerShips;
					if(computerShips == 0)
						gameOver();
                    Board.playerText.setText("<html><h1><br></br><div style='text-align: center;'>Player</div>Battleships: " + playerShips + "</h1></html>");
                }
                else if (playerGrid[x][y] == " ") {
                    playerGrid[x][y] = "y";
                    Board.playerPanels[x][y].setBackground(Color.GRAY);
                    System.out.println("Computer missed");
                }
                else {
                	repeat = true;
                }
            }
        } 
    }

    public static void gameOver(){
        System.out.println("Your ships: " + BattleShips.playerShips + " | Computer ships: " + BattleShips.computerShips);
        if(BattleShips.playerShips > 0 && BattleShips.computerShips <= 0) {
            Board.playerText.setText("<html><h1><br></br><div style='text-align: center;'>Player</div>WINNER</h1></html>");
            Board.cpuText.setText("<html><h1><br></br><div style='text-align: center;'>CPU</div>LOSER</h1></html>");
        }
        else {
            Board.playerText.setText("<html><h1><br></br><div style='text-align: center;'>Player</div>LOSER</h1></html>");
            Board.cpuText.setText("<html><h1><br></br><div style='text-align: center;'>CPU</div>WINNER</h1></html>");
        }
   }
    
    public static class ConfirmListener extends MouseAdapter
    {
    	public void mouseClicked(MouseEvent me)
    	{
            if(Board.currentBox != null) {
            	for(int i = 0; i < 10; i++) {
            		for(int j = 0; j < 10; j++) {
            			if(Board.currentBox != null && Board.currentBox.equals(Board.cpuPanels[i][j])) {
            				if(cpuGrid[i][j] == "x") {
            					Board.currentBox.setBackground(Color.RED);
            					computerShips--;
            					if(computerShips == 0)
            						gameOver();
                                Board.cpuText.setText("<html><h1><br></br><div style='text-align: center;'>CPU</div>Battleships: " + computerShips + "</h1></html>");
            				}
            				else {
            					Board.currentBox.setBackground(Color.GRAY);
            				}
            				Board.currentBox.removeMouseListener(Board.clickers[i][j]);
            				Board.currentBox = null;
            				computerTurn();
            			}
            		}
            	}
            }
        }
    }
}

