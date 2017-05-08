import couleur.*;
import java.util.*;

public class Laby
{

   private int taille;
   private Dalle [][]board;
   private Pos posBeginning;
   private Pos posEnd;
   private Character pers;
   private Dessin dessin;

   private boolean map;

   public Laby(int taille)
   {

      this.taille = taille;
      if(taille < 3)
         this.taille = 3;
      this.board = new Dalle[this.taille][this.taille];<
   
      build();

   }


   private void build()
   {

      generateLaby();

      this.dessin = new Dessin(this, 500);

      int x = posBeginning.getX();
      int y = posBeginning.getY();

      pers = new Character(posBeginning, board[x][y].getOut()[0]);

      explore();
      dessin.draw();
   }  

 
   public void bouger(int k)
   {

      int didMove = 0;

      int x = pers.getX();
      int y = pers.getY();

      if(k == 37 || k == 39 || k == 81 || k == 68) // tourne
      {
         pers.turn(k);
         map = false;
      }
      if(k == 77) // Lance la map
      {
     	   if(!map)
         {
            dessin.drawMap();
            map = true;
         }
         else
         {
            dessin.draw();
            map = false;
         }
         return;
      }
      else if( k == 38 || k == 90 ) //forward
      { 

         if(pers.sameAs(posEnd))
         {
            build();
         }
         else if(board[x][y].wayExists(pers.getOri()))
         {
            pers.move(k);
            didMove++;
            map = false;
         }
      }
      else if( (k == 40 || k == 83) && board[x][y].wayExists(pers.getOriInvers()) ) // backward
      {
         pers.move(k);
         didMove++;
         map = false;
      } 

      maj();

      if(didMove == 2)
      {
         didMove = 0;
      }

   }



   private void maj()
   {

      explore();
      dessin.draw();

   }   


   public void explore()
   {

      String ori = pers.getOri();

      int x = pers.getX();
      int y = pers.getY();
      boolean flag = true;

      board[x][y].setVisite(true);

      do
      {

      if(ori == "up")
         x--;
      else if(ori == "down")
         x++;
      else if(ori == "left")
         y--;
      else if(ori == "right")
         y++;

      if(x < taille && y < taille && x > -1 && y > -1 && isVisible(x,y))
         board[x][y].setVisite(true);
      else
         flag = false;
      
      }while(flag);

   }


   public boolean isVisible(int xd, int yd) 
   {

      int x = pers.getX();
      int y = pers.getY();

      Dalle d = board[xd][yd];

      int diffX = x - xd;
      int diffY = y - yd;

      if(diffX <= -1)
        return d.wayExists("up");
      if(diffX >= 1)
        return d.wayExists("down");
      if(diffY >= 1)
        return d.wayExists("right");
      if(diffY <= -1)
        return d.wayExists("left");

      return false;
   }


   private void generateLaby()
   {

      for(int i = 0 ; i < taille ; i++)
      {
         for(int j = 0 ; j < taille ; j++)
         {

            boolean up = false;
            boolean down = false;
            boolean left = false;
            boolean right = false;
            double prob = 1-0.4;

            if(i != 0)
               up = board[i-1][j].getDown();

            if(j != 0)
               left = board[i][j-1].getRight();

            if(i != taille-1)
            {
               if(Math.random() > prob)
               {
                  down = true;
               }
            }

            if(j != taille-1)
            {
               if(Math.random() > prob)
               {
                  right = true;
               }
            }

            this.board[i][j] = new Dalle(up, down, left, right);

         }
      }

      if(!verifLaby())
         generateLaby();

   }   


   private boolean verifLaby()
   {

      LinkedList<Pos> list = new LinkedList<Pos>(); 
      LinkedList<Pos> tot = new LinkedList<Pos>();
      int [][]score = new int[taille][taille];

      for(int i = 0 ; i < taille ; i++)
         for(int j = 0 ; j < taille ; j++)
            score[i][j] = 0;


      for(int i = 0 ; i < taille ; i++)
      {
         for(int j = 0 ; j < taille ; j++)
         {
            
            if(!board[i][j].getExists())
            {
            
               Pos coord = new Pos(i,j);

               list.add(coord);

               while(list.size() != 0)
               {         

                  coord = list.remove(0); 
                  tot.add(coord);

                  board[coord.getX()][coord.getY()].setExists(true);

                  int x = coord.getX();
                  int y = coord.getY();

                  Dalle d = board[x][y];

                  boolean up = d.getUp();
                  boolean down = d.getDown();
                  boolean left = d.getLeft();
                  boolean right = d.getRight();

                  if(x != 0)
                  {
                     if(up == true && board[x-1][y].getExists() == false)
                     {
                        list.addFirst(new Pos(x-1, y));
                        board[x-1][y].setExists(true);
                     }
                  }

                  if(y != 0)
                  {
                     if(left == true && board[x][y-1].getExists() == false)
                     {
                        list.addFirst(new Pos(x, y-1));
                        board[x][y-1].setExists(true);
                     }
                  }
         
                  if(y != taille-1)
                  {
                     if(right == true && board[x][y+1].getExists() == false)
                     {
                        list.addFirst(new Pos(x, y+1));
                        board[x][y+1].setExists(true);
                     }
                  }

                  if(x != taille-1)
                  {
                     if(down == true && board[x+1][y].getExists() == false)
                     {
                        list.addFirst(new Pos(x+1, y));
                        board[x+1][y].setExists(true);
                     }
                  }

               } //fin while

               int s = tot.size();

               while(tot.size() != 0)
               {
                  Pos p = tot.remove(0);
                  score[p.getX()][p.getY()] = s;
               }

            } //fin if
         } //fin for
      }//fin for  

      int m = maxScore(score);

      for(int i = 0 ; i < taille ; i++)
         for(int j = 0 ; j < taille ; j++)
            if(score[i][j] < m)
               board[i][j].setExists(false);

      return placeBegEnd();

   }

  
   int maxScore(int [][]score)
   {
      int max = 0;

      for(int i = 0 ; i < taille ; i++)
         for(int j = 0 ; j < taille ; j++)
            if(score[i][j] > max)
               max = score[i][j];
      
      return max;
   }


   public boolean placeBegEnd()
   {

      LinkedList<Pos> list = new LinkedList<Pos>();

      for(int i = 0 ; i < taille ; i++)
         for(int j = 0 ; j < taille ; j++)
            if(board[i][j].nbOut() == 1 && board[i][j].getExists())
               list.add(new Pos(i, j));

      if(list.size() == 0)
         return false;

      int r = (int) (Math.random()*list.size());
      Pos p = list.remove(r);
      posBeginning = p;

            if(list.size() == 0)
         return false;

      r = (int) (Math.random()*list.size());
      p = list.remove(r);
      posEnd = p;

      return true;
   }

   public Dalle[][] getBoard()
   {
      return board;
   }
   public Pos getPosEnd()
   {
      return posEnd;
   }
   public Character getCharacter()
   {
      return pers;
   }
   public int getTaille()
   {
      return taille;
   }
}
