import java.util.*;

public class checkers2
{
  public static void main(String []args)
  {
    //** SET UP ***
    pieces[][] grid = new pieces[9][9];
    
    for (int i = 0; i<= 8; i++)
    {
      for( int j = 0; j <= 8; j++ )
      {
        grid[i][j] = new pieces();
        
      }
    }
    
    //initial red postioning
    for (int i = 0; i < 3; i++)
    {
      if( i % 2 == 0)
      {
        for (int j = 1; j <= 7; j = j+2)
        {
          grid[i][j] = new pieces(i , j , false, "r");
        }
      }
      
      else
      {
        for (int j = 0; j <= 7; j = j+2)
        {
          grid[i][j] = new pieces(i , j , false, "r");
        }
      }
    }
    
    //initial white positioning
    for (int i = 5; i <= 7; i++)
    {
      if( i % 2 == 0)
      {
        for (int j = 1; j <= 7; j = j+2)
        {
          grid[i][j] = new pieces(i , j , false, "w");;
        }
      }
      
      else
      {
        for (int j = 0; j <= 7; j = j+2)
        {
          grid[i][j] = new pieces(i , j , false, "w");;
        }
      }
    }
    
    
    /*grid[3][2] = new pieces(3,2,false,"w");
    grid[6][5] = new pieces();*/
    

        
    System.out.println("Welcome to Checkers!");
    printBoard(grid);
    
/*---------------------------------------------------* RED'S MOVEMENT *--------------------------------------------------------------*/
    
    Scanner pencil = new Scanner(System.in);
    
    boolean progress;
    int choice = 2;
    
    while (progress = true)
    {
      //RED'S TURN
      
      if (choice % 2 == 0)
      {
        System.out.println("Red's turn!");
        
        System.out.println("x coordinate of intial piece (coordinate system is where (0,2) = [0][2] )");
        int x1 = pencil.nextInt();
        pencil.nextLine();
        
        System.out.println("y coordinate of intial piece");
        int y1 = pencil.nextInt();
        pencil.nextLine();
        
        System.out.println("x coordinate where to move");
        int x2 = pencil.nextInt();
        pencil.nextLine();
        
        System.out.println("y coordinate where to move");
        int y2 = pencil.nextInt();
        pencil.nextLine();
        
        //initial requirement check-ins 
        if ( x1 > 7 || y1 > 7 || x2 > 7 || y2 > 7 )
        {
          System.out.println("out of bounds move - please try again");
          choice = 1;
        }
        
        else if (!(grid[x1][y1].getColor() == "r" || grid[x1][y1].getColor() == "R"))
        {
          System.out.println("piece selected is not red - please try again");
          choice = 1;
        }
        
        
        else if  (grid[x2][y2].getColor() != " ")
        {
          System.out.println("space already taken - please try again");
          choice = 1;
        }
        
        //if the piece is not a king
        else if (!grid[x1][y1].kingStatus())
        {
          boolean redKill = false;
          //regular movement one up forward
          if ( (x2 - x1) == 1 && Math.abs(y2 - y1) == 1 && grid[x2][y2].getColor() == " ")
          {   
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
          }
          
          //kill to the right
          else if ((x2 - x1) == 2 && (y2 - y1) == 2 && grid[x1+1][y1+1].getColor() == "w")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1+1][y1+1]= new pieces();
            redKill = true;
            
            
          }
          
          //kill to the left
          else if ((x2 - x1) == 2 && (y2 - y1) == -2 && grid[x1+1][y1-1].getColor() == "w")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1+1][y1-1]= new pieces();
            redKill = true;
          }
          
          else 
          {
            System.out.println("invalid move - please try again");
            choice = 1;
          }
          
          //Make into King
          if (x2 == 7)
          {
            grid[x2][y2].makeKing(true);
            grid[x2][y2].setColor("R");
          }
          
          int r = 0;
          //TESTING MULTI KILL **must implement to the other multi kills!!)
          if (redKill = true && x2+2<8 && y2+2<8 && x2-2>0 && y2-2>0)
          {
            while(x2+2<8 && y2+2<8 && x2-2>0 && y2-2>0 && ((grid[x2+1][y2-1].getColor() == "w" && grid[x2+2][y2-2].getColor() == " ") || (grid[x2+1][y2+1].getColor() == "w" && grid[x2+2][y2+2].getColor() == " " )))
            {
              printBoard(grid);
              
              System.out.println("x coordinate where to kill again");
              int x3 = pencil.nextInt();
              pencil.nextLine();
              
              System.out.println("y coordinate where to kill again");
              int y3 = pencil.nextInt();
              pencil.nextLine();
              
              //kill to the right
              if (Math.abs(x2 - x3) == 2 && (y3 - y2) == 2 && grid[x2+1][y2+1].getColor() == "w")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2+1][y2+1]= new pieces();
                
              }
              
              //kill to the left
              else if (Math.abs(x3 - x2) == 2 && (y3 - y2) == -2 && grid[x2+1][y2-1].getColor() == "w")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2+1][y2-1]= new pieces();
               
              }
              
              else 
              {
                System.out.println("invalid move - please try again");
              }
              
              
              if (x3 == 0)
              {
                grid[x3][y3].makeKing(true);
                grid[x3][y3].setColor("R");
              }
              
              x2 = x3;
              y2 = y3;
              
            }
          
          }
        }
        
        
        /*------------------------------------------------------RED'S KING-----------------------------------------------------------*/
    
        
        // if the piece is a king
        else if (grid[x1][y1].kingStatus())
        {
          boolean redKill;
          if (Math.abs(x2-x1) == 1 && Math.abs(y2 - y1) == 1)
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
          }
          
          else if ((x2 - x1) == -2 && (y2 - y1) == 2 && grid[x1-1][y1+1].getColor() == "w")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1-1][y1+1]= new pieces();
            redKill = true;
            
          }
          
          //kill to the left up
          else if ((x2 - x1) == -2 && (y2 - y1) == -2 && grid[x1-1][y1-1].getColor() == "w")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1-1][y1-1]= new pieces();
            redKill = true;
          }
          
          
          //kill to the right down
          else if ((x2 - x1) == 2 && (y2 - y1) == 2 && grid[x1+1][y1+1].getColor() == "w")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1+1][y1+1]= new pieces();
            redKill = true;
          }
                      
            
          
          //kill to the left down 
          else if ((x2 - x1) == 2 && (y2 - y1) == -2 && grid[x1+1][y1-1].getColor() == "w")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1+1][y1-1]= new pieces();
            redKill = true;
          }
          
          else 
          {
            System.out.println("invalid move - please try again");
            choice = 1;
          }
          //King moves
          
          
          if (redKill = true && x2+2<8 && y2+2<8 && x2-2>0 && y2-2>0)
          {
            while(x2+2<8 && y2+2<8 && x2-2>0 && y2-2>0 && ((grid[x2+1][y2+1].getColor() == "w" && grid[x2+2][y2+2].getColor() == " ") || (grid[x2+1][y2-1].getColor() == "w" && grid[x2+2][y2-2].getColor() == " ") || (grid[x2-1][y2+1].getColor() == "w" && grid[x2-2][y2+2].getColor() == " ") || (grid[x2-1][y2-1].getColor() == "w" && grid[x2-2][y2-2].getColor() == " ")))
            {
              
              printBoard(grid);
              System.out.println("x coordinate where to kill again");
              int x3 = pencil.nextInt();
              pencil.nextLine();
              
              System.out.println("y coordinate where to kill again");
              int y3 = pencil.nextInt();
              pencil.nextLine();
              
              //kill to the right up 
              if ((x3 - x2) == -2 && (y3 - y2) == 2 && grid[x2-1][y2+1].getColor() == "w")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2-1][y2+1]= new pieces();
                
                
              }
              
              //kill to the left up
              else if ((x3 - x2) == -2 && (y3 - y2) == -2 && grid[x2-1][y2-1].getColor() == "w")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2-1][y2-1]= new pieces();
              }
              
              
              //kill to the right down
              else if ((x3 - x2) == 2 && (y3 - y2) == 2 && grid[x2+1][y2+1].getColor() == "w")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2+1][y2+1]= new pieces();
                
              }
              
              
              
              //kill to the left down 
              else if ((x3 - x2) == 2 && (y3 - y2) == -2 && grid[x2+1][y3-1].getColor() == "w")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2+1][y2-1]= new pieces();
                
              }            
              
              
              x2 = x3;
              y2 = y3;
            }
            
          }
        }
        
        
        
        
        //IMPLEMENT KING STATUS HERE
        
        printBoard(grid);
        choice++;
      }
      
    
/*------------------------------------------------------WHITE'S MOVEMENT-----------------------------------------------------------*/
    
      
      if (choice % 2 == 1)
      {
        boolean whiteKill = false;
        //WHITE'S TURN
        System.out.println("White's turn!");
        
        System.out.println("x coordinate of intial piece (coordinate system is where (0,2) = [0][2] )");
        int x1 = pencil.nextInt();
        pencil.nextLine();
        
        System.out.println("y coordinate of intial piece");
        int y1 = pencil.nextInt();
        pencil.nextLine();
        
        System.out.println("x coordinate where to move");
        int x2 = pencil.nextInt();
        pencil.nextLine();
        
        System.out.println("y coordinate where to move");
        int y2 = pencil.nextInt();
        pencil.nextLine();
        
        //initial requirement check-ins 
        
        if ( x1 > 7 || y1 > 7 || x2 > 7 || y2 > 7 )
        {
          System.out.println("out of bounds move - please try again");
          choice = 2;
        }
        
        else if (!(grid[x1][y1].getColor() == "w" || grid[x1][y1].getColor() == "W"))
        {
            System.out.println("piece selected is not white - please try again");
            choice = 2;
        }
        
        
        else if  (grid[x2][y2].getColor() != " ")
        {
          System.out.println("space already taken - please try again");
          choice = 2;
        }
        
        //if the piece is not a king
        else if (!grid[x1][y1].kingStatus())
        {
          //regular movement one up forward
          if ( (x2 - x1) == -1 && Math.abs(y2 - y1) == 1 )
          {   
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            whiteKill = false;
          }
          
          //kill to the right
          else if (Math.abs(x1 - x2) == 2 && (y2 - y1) == 2 && grid[x1-1][y1+1].getColor() == "r")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1-1][y1+1]= new pieces();
            whiteKill = true;
            
          }
          
          //kill to the left
          else if (Math.abs(x1 - x2) == 2 && (y2 - y1) == -2 && grid[x1-1][y1-1].getColor() == "r")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1-1][y1-1]= new pieces();
            whiteKill = true;
          }
          
          else 
          {
            System.out.println("invalid move - please try again");
            choice = 2;
          }
          
          //Make into King
          if (x2 == 0)
          {
            grid[x2][y2].makeKing(true);
            grid[x2][y2].setColor("W");
          }
          
          int c = 0;
          
          //multi kill
          if(whiteKill = true && x2-2>0 && y2-2>0 && x2+2<8 && y2+2<8)
          {
            while(x2-2>0 && y2-2>0 && x2+2<8 && y2+2<8 && ((grid[x2-1][y2-1].getColor() == "r" && grid[x2-2][y2-2].getColor() == " ") || (grid[x2-1][y2+1].getColor() == "r" && grid[x2-2][y2+2].getColor() == " ")))
            {
              printBoard(grid);
              
              System.out.println("x coordinate where to kill again");
              int x3 = pencil.nextInt();
              pencil.nextLine();
              
              System.out.println("y coordinate where to kill again");
              int y3 = pencil.nextInt();
              pencil.nextLine();
              
              //kill to the right
              if (Math.abs(x2 - x3) == 2 && (y3 - y2) == 2 && grid[x2-1][y2+1].getColor() == "r")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2-1][y2+1]= new pieces();
                
              }
              
              //kill to the left
              else if (Math.abs(x3 - x2) == 2 && (y3 - y2) == -2 && grid[x2-1][y2-1].getColor() == "r")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2-1][y2-1]= new pieces();
               
              }
              
              else 
              {
                System.out.println("invalid move - please try again");
              }
              
              
              if (x3 == 0)
              {
                grid[x3][y3].makeKing(true);
                grid[x3][y3].setColor("W");
              }
              
              x2 = x3;
              y2 = y3;
              
              
            }
          }
          
          
        }
        
        
        /*------------------------------------------------------WHITE'S KING-----------------------------------------------------------*/
    
        //if the piece is a king
        else if(grid[x1][y1].kingStatus())
        {
          
          
          //regular movement one up forward
          if ( Math.abs(x2 - x1) == 1 && Math.abs(y2 - y1) == 1)
          {   
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            whiteKill = false;
          }
          
          //kill to the right up 
          else if ((x2 - x1) == -2 && (y2 - y1) == 2 && grid[x1-1][y1+1].getColor() == "r")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1-1][y1+1]= new pieces();
            whiteKill = true;
            
          }
          
          //kill to the left up
          else if ((x2 - x1) == -2 && (y2 - y1) == -2 && grid[x1-1][y1-1].getColor() == "r")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1-1][y1-1]= new pieces();
            whiteKill = true;
          }
          
          
          //kill to the right down
          else if ((x2 - x1) == 2 && (y2 - y1) == 2 && grid[x1+1][y1+1].getColor() == "r")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1+1][y1+1]= new pieces();
            whiteKill = true;
          }
                      
            
          
          //kill to the left down 
          else if ((x2 - x1) == 2 && (y2 - y1) == -2 && grid[x1+1][y1-1].getColor() == "r")
          {
            grid[x1][y1].setX(x2);
            grid[x1][y1].setY(y2);
            grid[x2][y2] = grid[x1][y1];
            grid[x1][y1] = new pieces();
            grid[x1+1][y1-1]= new pieces();
            whiteKill = true;
          }
          
          else 
          {
            System.out.println("invalid move - please try again");
            choice = 2;
          }
          
          
/*-------------------------------------------------------WHITE MULTI KILL----------------------------------------------------------*/
          if(whiteKill = true && x2+2<8 && y2+2<8 && x2-2>0 && y2-2>0)
          {
          
            while(x2+2<8 && y2+2<8 && x2-2>0 && y2-2>0 && ((grid[x2+1][y2+1].getColor() == "r" && grid[x2+2][y2+2].getColor() == " ")  || (grid[x2+1][y2-1].getColor() == "r" && grid[x2+2][y2-2].getColor() == " ")|| (grid[x2-1][y2+1].getColor() == "r" && grid[x2-2][y2+2].getColor() == " ")  || (grid[x2-1][y2-1].getColor() == "r" && grid[x2-2][y2-2].getColor() == " " )))
            {
              printBoard(grid);
              System.out.println("x coordinate where to kill again");
              int x3 = pencil.nextInt();
              pencil.nextLine();
              
              System.out.println("y coordinate where to kill again");
              int y3 = pencil.nextInt();
              pencil.nextLine();
              
              //kill to the right up 
              if ((x3 - x2) == -2 && (y3 - y2) == 2 && grid[x2-1][y2+1].getColor() == "r")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2-1][y2+1]= new pieces();
                whiteKill = true;
                
              }
              
              //kill to the left up
              else if ((x3 - x2) == -2 && (y3 - y2) == -2 && grid[x2-1][y2-1].getColor() == "r")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2-1][y2-1]= new pieces();
                whiteKill = true;
              }
              
              
              //kill to the right down
              else if ((x3 - x2) == 2 && (y3 - y2) == 2 && grid[x2+1][y2+1].getColor() == "r")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2+1][y2+1]= new pieces();
                whiteKill = true;
              }
              
              
              
              //kill to the left down 
              else if ((x3 - x2) == 2 && (y3 - y2) == -2 && grid[x2+1][y3-1].getColor() == "r")
              {
                grid[x2][y2].setX(x3);
                grid[x2][y2].setY(y3);
                grid[x3][y3] = grid[x2][y2];
                grid[x2][y2] = new pieces();
                grid[x2+1][y2-1]= new pieces();
                whiteKill = true;
              }            
              
              
              x2 = x3;
              y2 = y3;
              
              
            }
          }


          
        }
        
        
        printBoard(grid);
        choice++;
      
      }
      
/*-------------------------------------------------------CHECK FOR WIN----------------------------------------------------------*/      
      
      
      int a = 0;
      int b = 0;
      for(int i = 0; i <=8; i++)
      {
        for( int j = 0; j <= 8; j++ )
        {
          if (grid[i][j].getColor() == "r")
          {
            a++;
          }
          
          if (grid[i][j].getColor() == "w")
          {
            b++;
          }
          
        }
      }
      
      if (a == 0 || b == 0)
      {
        progress = false;
        System.out.println( "End of game! Thanks for playing :))");
      }

    }
      
 
    
  }
  

/*-------------------------------------------------------METHOD TO PRINT OUT BOARD----------------------------------------------------------*/
  
  public static void printBoard( pieces[][] grid)
  {
    System.out.println(" y  0   1   2   3   4   5   6   7 ");
    System.out.println("x  --- --- --- --- --- --- --- --- ");
    System.out.println( "0 | " + grid[0][0].getColor() + " | " + grid[0][1].getColor() + " | " + grid[0][2].getColor() + " | " + grid[0][3].getColor() + " | " + grid[0][4].getColor() + " | " + grid[0][5].getColor() + " | " + grid[0][6].getColor() + " | " + grid[0][7].getColor() + " | ");
    System.out.println("   --- --- --- --- --- --- --- --- ");
    System.out.println( "1 | " + grid[1][0].getColor() + " | " + grid[1][1].getColor() + " | " + grid[1][2].getColor() + " | " + grid[1][3].getColor() + " | " + grid[1][4].getColor() + " | " + grid[1][5].getColor() + " | " + grid[1][6].getColor() + " | " + grid[1][7].getColor() + " | ");
    System.out.println("   --- --- --- --- --- --- --- --- ");
    System.out.println( "2 | " + grid[2][0].getColor() + " | " + grid[2][1].getColor() + " | " + grid[2][2].getColor() + " | " + grid[2][3].getColor() + " | " + grid[2][4].getColor() + " | " + grid[2][5].getColor() + " | " + grid[2][6].getColor() + " | " + grid[2][7].getColor() + " | ");
    System.out.println("   --- --- --- --- --- --- --- --- ");
    System.out.println( "3 | " + grid[3][0].getColor() + " | " + grid[3][1].getColor() + " | " + grid[3][2].getColor() + " | " + grid[3][3].getColor() + " | " + grid[3][4].getColor() + " | " + grid[3][5].getColor() + " | " + grid[3][6].getColor() + " | " + grid[3][7].getColor() + " | ");
    System.out.println("   --- --- --- --- --- --- --- --- ");
    System.out.println( "4 | " + grid[4][0].getColor() + " | " + grid[4][1].getColor() + " | " + grid[4][2].getColor() + " | " + grid[4][3].getColor() + " | " + grid[4][4].getColor() + " | " + grid[4][5].getColor() + " | " + grid[4][6].getColor() + " | " + grid[4][7].getColor() + " | ");
    System.out.println("   --- --- --- --- --- --- --- --- ");
    System.out.println( "5 | " + grid[5][0].getColor() + " | " + grid[5][1].getColor() + " | " + grid[5][2].getColor() + " | " + grid[5][3].getColor() + " | " + grid[5][4].getColor() + " | " + grid[5][5].getColor() + " | " + grid[5][6].getColor() + " | " + grid[5][7].getColor() + " | ");
    System.out.println("   --- --- --- --- --- --- --- --- ");
    System.out.println( "6 | " + grid[6][0].getColor() + " | " + grid[6][1].getColor() + " | " + grid[6][2].getColor() + " | " + grid[6][3].getColor() + " | " + grid[6][4].getColor() + " | " + grid[6][5].getColor() + " | " + grid[6][6].getColor() + " | " + grid[6][7].getColor() + " | ");
    System.out.println("   --- --- --- --- --- --- --- --- ");
    System.out.println( "7 | " + grid[7][0].getColor() + " | " + grid[7][1].getColor() + " | " + grid[7][2].getColor() + " | " + grid[7][3].getColor() + " | " + grid[7][4].getColor() + " | " + grid[7][5].getColor() + " | " + grid[7][6].getColor() + " | " + grid[7][7].getColor() + " | ");
    System.out.println("   --- --- --- --- --- --- --- --- ");
  }
}
 
/*Checklist 
 *
 * check if king implementation works
 * check for win --- test 
 * multiple kill
 */