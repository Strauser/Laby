public class Pos
{

   private int x;
   private int y;

   public Pos(int x, int y)
   {  
      this.x = x;
      this.y = y;
   }

   public Pos(Pos p)
   {
      this(p.getX(), p.getY());
   }

   public int getX()
   {
      return this.x;
   }
   public int getY()
   {
      return this.y;
   }

   public void moveX(int dx)
   {
      this.x += dx;
   }
   public void moveY(int dy)
   {
      this.y += dy;
   }

   public boolean sameAs(Pos p)
   {
      if( (p.getX() == this.x) && (p.getY() == this.y) )
         return true;
      return false;
   }

}
