public class Character extends Facing
{

   public Character(int x, int y, String ori)
   {
      super(x, y, ori);
   }

   public Character(Pos p, String facing)
   {
      this(p.getX(), p.getY(), facing);
   }
      
}
