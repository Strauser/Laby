public abstract class Consumable extends Obj
{

   public Consumable(String name, boolean b)
   {
      super("Consumable "+name, b);
   }

   public abstract void useOn(Character c);

}
