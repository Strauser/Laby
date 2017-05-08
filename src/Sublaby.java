import java.util.LinkedList;

public class Sublaby
{

   private static int num = 0;
   private int id;
   private int nb;
   private int taille;

   private Dalle [][]laby;

   public Sublaby(int taille)
   {
      this.id = this.num++;
      laby = new Dalle[taille][taille];
      this.taille = taille;
   }


   public void add(Dalle d, int x, int y)
   {
      if(laby[x][y] == null)
      {
         laby[x][y] = d;
         d.addSublaby(this);
         nb++;
      }
      else
         System.out.println("Trying to add Dalle to sublaby already taken");
   }

   //creates a sublaby from the Pos (x,y) and adds all Dalles to the sublaby.
   public void fill(Dalle[][] board, int x, int y)
   {

      LinkedList<Pos> list = new LinkedList<Pos>();

      Pos coord = new Pos(x,y);

      list.add(coord);

      while(list.size() != 0)
      {

         coord = list.remove(0);
         nb++;         

         board[coord.getX()][coord.getY()].setExists(true);

         x = coord.getX();
         y = coord.getY();

         Dalle d = board[x][y];
         add(d, x, y);

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

      } // end while

   }

   public boolean sameAs(Sublaby s)
   {
      if(id == s.getId())
         return true;
      return false;
   }

   public int getId()
   {
      return id;
   }
   public int nbDalle()
   {
      return nb;
   }
}
