public class Character extends Facing
{

   private int life;
   private Inventory inventory;

   public Character(int x, int y, String ori)
   {
      super(x, y, ori);
      this.life = 10;
      this.inventory = new Inventory(this);
   }

   public Character(Pos p, String facing)
   {
      this(p.getX(), p.getY(), facing);
   }
   
   public boolean chLife(int i)
   {
      this.life += i;
      if(life >= 10)
         life = 10;
      if(life <= 0)
         return false;

      return true;
   }

   public Inventory getInv()
   {
      return inventory;
   }
}
