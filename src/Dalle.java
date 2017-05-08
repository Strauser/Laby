public class Dalle 
{

   private boolean up;
   private boolean down;
   private boolean left;
   private boolean right;

   private boolean exists;
   private boolean visite;

   private Sublaby[] sublaby;

   public Dalle(boolean up, boolean down, boolean left, boolean right)
   {

      this.up = up;
      this.down = down;
      this.left = left;
      this.right = right;

      this.visite = false;
      this. exists = false;

   }


   public void addSublaby(Sublaby s)
   {
    
      if(this.hasSublaby(s))
         return;

      if(sublaby == null)
      {
         this.sublaby = new Sublaby[1];
         sublaby[0] = s;
      }
      else
      {
         Sublaby []newSub = new Sublaby[sublaby.length+1];
         System.arraycopy(sublaby, 0, newSub, 0, sublaby.length);

         newSub[sublaby.length] = s;
         sublaby = newSub;
      }

   }

   //returns if the dalle belongs to Sublaby s
   public boolean hasSublaby(Sublaby s)
   {
      if(sublaby == null)
         return false;

      for(int i = 0; i < sublaby.length; i++)
         if(sublaby[i].sameAs(s))
            return true;
   
      return false; 
   }

   //returns array of sublaby the dalle belongs to.
   public Sublaby[] getSublabys()
   {
      return sublaby;
   }
   //returns the first sublaby
   public Sublaby getSublaby()
   {
      return getSublabys()[0];
   }


   //returns the number of way out.
   public int nbOut()
   {
      int nb = 0;
   
      if(this.up)
         nb++;

      if(this.down)
         nb++;
      
      if(this.left)
         nb++;

      if(this.right)
         nb++;

      return nb;
   }

   //returns String[] withs names of outs.
   public String[] getOut()
   {

      int taille = nbOut();
      int q = 0;

      String[] outs = new String[taille];

      if(this.up)
      {
         outs[q++] = "up";
      }
      if(this.down)
      {
         outs[q++] = "down";
      }
      if(this.left)
      {
         outs[q++] = "left";
      }
      if(this.right)
      {
         outs[q++] = "right";
      }

      return outs;
   }

   //returns if the way exists.
   public boolean wayExists(String ori)
   {
      if(ori == "up")
         return this.up;
      if(ori == "down")
         return this.down;
      if(ori == "left")
         return this.left;
      if(ori == "right")
         return this.right;

      return false;
   }

   public void setUp(boolean up)
   {
      this.up = up;
   }
   public void setDown(boolean down)
   {
      this.down = down;
   }
   public void setLeft(boolean left)
   {
      this.left = left;
   }
   public void setRight(boolean right)
   {
      this.right = right;
   }
   public void setExists(boolean e)
   {
      this.exists = e;
   }
   public void setVisite(boolean v)
   {
      this.visite = v;
   }


   public boolean getUp()
   {
      return this.up;
   }
   public boolean getDown()
   {
      return this.down;
   }
   public boolean getLeft()
   {
      return this.left;
   }
   public boolean getRight()
   {
      return this.right;
   }
   public boolean getExists()
   {
      return exists;
   }
   public boolean getVisite()
   {
      return visite;
   }

}
