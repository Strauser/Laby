public class Inventory
{

   private Obj []inventory;
   private int []stack; 
   private Character owner;

   public Inventory(Character owner)
   {
      this.owner = owner;
      inventory = new Obj[0];
      stack = new int[0];
   }

   //Adds Obj to inventory
   public void add(Obj o, int i)
   {
      int j = getIndex(o);
      if(j == -1 || !o.isStackable())
         addNew(o);
      stack[i]+= i;
   }
   public void add(Obj o)
   {
      add(o, 1);
   }
      

   //Adds new Obj at last position.
   public void addNew(Obj o)
   {

      int len = inventory.length;
      Obj []newInv = new Obj[len+1];
      int []newStack = new int[len+1];

      System.arraycopy(inventory, 0, newInv, 0, len);
      System.arraycopy(stack, 0, newStack, 0, len);

      newInv[len] = o;
      newStack[len] = 0;

      inventory = newInv;
      stack = newStack;

   }

   //Remove one of Obj
   public void remove(int i)
   {
      if(i == -1)
      {
         System.out.println("You don't have that object");
         return;
      }

      if(stack[i] == 1)
         removeT(i);
      else
         stack[i]--;
   }
   public void remove(String name)
   {
      remove(getIndex(name));
   }
   public void remove(Obj o)
   {
      remove(getIndex(o));
   } 

   //Removes Obj at Index i.
   public void removeT(int i)
   {

      int len = inventory.length;
      Obj []newInv = new Obj[len-1];

      System.arraycopy(inventory, 0, newInv, 0, i);
      System.arraycopy(inventory, i+1, newInv, i, len-i-1);

      inventory = newInv;

   }
   public void removeT(String name)
   {
      remove(getIndex(name));
   }
   public void removeT(Obj o)
   {
      remove(getIndex(o));
   }

   //Returns index of Obj with name.
   public int getIndex(String name)
   {
      for(int i = 0 ; i < inventory.length; i++)
         if(inventory[i].getName() == name)
            return i;

      return -1;
   }

   //Returns index of Obj.
   public int getIndex(Obj o)
   {
      for(int i = 0 ; i < inventory.length; i++)
         if(o.sameAs(inventory[i]))
            return i;

      return -1;
   }

   //Uses Obj
   public void use(Obj o)
   {
      if(o instanceof Consumable)
      {
         ((Consumable)o).useOn(owner);
         remove(o);
      }
   }

   public String toString()
   {
      String a = "";
      String b = "Inventory :\n";
   
      for(int i = 0 ; i < inventory.length ; i++)
      {
         a = inventory[i].getName();
         b = b + a;
      }
      return b;
   }

}
