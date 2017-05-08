public class PotionHeal extends Consumable
{

   public PotionHeal()
   {
      super("PotionHeal", true);
   }

   public void useOn(Character c)
   {
      c.chLife(3);
   }

   public void drawObj()
   {}
}
