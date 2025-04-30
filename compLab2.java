/* Devin Isaiah Duque 
[CS1101] Comprehensive Lab 2 
This work is to be done individually. 
It is not permitted to. share, reproduce, or alter any part of this assignment for any purpose. 
Students are not permitted to share code, upload this assignment online in any form, 
or view/receive/ modifying code written by anyone else. This assignment is part. 
of an academic course at The University of Texas at El Paso and a grade will be assigned for the work 
produced individually by the student. 
*/ 
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class compLab2{
	//GLOBAL VARIABLES: EXISTS EVERYWHERE
	public static String[][] gameText = new String[11][11];
   public static String[][] playerGame = new String[11][11];
   public static int shipCount = 0;
   public static int bombCount = 20;
    
	//MAIN METHOD
	public static void main(String[] args){
		getGame("game5.txt");
      Scanner userIn = new Scanner(System.in);
      System.out.println("Get ready for a game of Battleship!");
      
      while(shipCount != 0 && bombCount != 0){
      usersBattle(playerGame);
      int row;
      int col;
      try{
      System.out.print("Enter row (0-9): ");
         row = userIn.nextInt();
      while(row < 0 || row > 9){
         System.out.println("Invalid input!"+"\n"+"Enter row (0-9): ");
         row = userIn.nextInt();
      }
   }catch(InputMismatchException error) {
      System.out.println("Invalid input!"+"\n"+"Enter row (0-9): ");
      row = userIn.nextInt();
   }
      try{
      System.out.println("Enter column (0-9): ");
         col = userIn.nextInt();

      while(col < 0 || col > 9){
         System.out.println("Invalid input!"+"\n"+"Enter column (0-9): ");
         col = userIn.nextInt();
      }
   }catch(InputMismatchException error) {
         System.out.println("Invalid input!"+"\n"+"Enter column (0-9): ");
         col = userIn.nextInt();
   }

      checkTry(row,col,bombCount);
      isGameWon(shipCount);  

    }
    userIn.close();
}	

//METHODS

//GIVES GLOBAL ARRAYS VALUES OF GAME FILE AND PLAYER BOARD WITH SHIPS LOCATION HIDDEN. COUNTS SHIP AMOUNT.
   public static String[][] getGame(String fileName){
         try {
            File gameFile = new File(fileName);
            Scanner fileScan = new Scanner(gameFile);

            int row = 0; 
            
            while (fileScan.hasNextLine() && row < gameText.length) {
                String line = fileScan.nextLine();
                String[] values = line.split(" "); 

                for (int col = 0; col < values.length && col < gameText[row].length; col++){ 
                   gameText[row][col] = values[col]; 
                   if(values[col].equals("S")){
                    shipCount ++;
                    playerGame[row][col] = "-";
                   }else{
                    playerGame[row][col] = values[col];
                   }
                }

                row++; 
            }

            fileScan.close();

        } catch (FileNotFoundException error) {
            System.out.println("File Not Found!");
        }
        return gameText;
    }

   public static void usersBattle(String[][]arr){
      for(int row =0;row < arr.length; row++){
         for(int column=0;column<arr[row].length;column ++){
            System.out.print(arr[row][column]+" ");
         }
         System.out.println();
      }
   }

   public static void checkTry(int row, int col, int bombs){
      if(gameText[row+1][col+1].equals("S")){
         gameText[row+1][col+1] = "X";
         playerGame[row+1][col+1] = "X";
         shipCount--;
         bombs --;
         bombCount = bombs;
         System.out.println("Hit!"+"\n"+"You have "+bombCount+" bombs remaining");


      }else if(gameText[row+1][col+1].equals("X")||gameText[row+1][col+1].equals("O")){
         bombs --;
         bombCount = bombs;
         System.out.println("Already guessed there!"+"\n"+"You have "+bombCount+" bombs remaining");
      }else{
         gameText[row+1][col+1] = "O";
         playerGame[row+1][col+1] = "O";
         bombs --;
         bombCount = bombs;
         System.out.println("Miss!"+"\n"+"You have "+bombCount+" bombs remaining");

      }

   }

   public static boolean isGameWon(int tries){
      if(tries==0){
         System.out.println("You sunk all the ships!");
         printGame();
         return true;
      }
      if(bombCount==0){
         System.out.println("Out of tries! Game over!");
         printGame();
         return true;
      }
      return false;
   }


   public static void printGame(){
      usersBattle(gameText);

   }

}