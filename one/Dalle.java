public class Dalle 
{

   private boolean up;
   private boolean down;
   private boolean left;
   private boolean right;

   private boolean exists;
   private boolean visite;

   public Dalle(boolean up, boolean down, boolean left, boolean right)
   {

      this.up = up;
      this.down = down;
      this.left = left;
      this.right = right;

      this.visite = false;
      this. exists = false;

   }


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
