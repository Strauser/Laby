public abstract class Obj
{

   private String name;
   private boolean stackable;
   
   public Obj(String name, boolean stackable)
   {
      this.name = name;
      this.stackable = stackable;
   }

   public String getName()
   {
      return name;
   }

   public boolean isStackable()
   {
      return stackable;
   }

   public boolean sameAs(Obj o)
   {
System.out.println(""+this.name+" - "+o.getName()+"!");
      if(this.name.equals(o.getName()))
         return true;
      return false;
   }


   public abstract void drawObj();
}
