public class Facing extends Pos
{

   private String ori;

   public Facing(int x, int y, String ori)
   {
      super(x, y);

      if(ori == "up" || ori == "down" || ori == "left" || ori == "right")
         this.ori = ori;
      else
         this.ori = "up";

   }


   public void move(int c)
   {

      String orient = getOri();

      if(c == 40 || c == 83)
         orient = getOriInvers(); 
         
      if(orient == "up")
         this.moveX(-1);
      if(orient == "down")
         this.moveX(1);
      if(orient == "left")
         this.moveY(-1);
      if(orient == "right")
         this.moveY(1);

   }


   public void turn(int c)
   {

      if(c == 81 || c == 37) //turn left
      {
         if(ori == "up")
            ori = "left";
         else if(ori == "left")
            ori = "down";
         else if(ori == "down")
            ori = "right";
         else if(ori == "right")
            ori = "up";
      }
      else //turn right
      {
         if(ori == "up")
            ori = "right";
         else if(ori == "right")
            ori = "down";
         else if(ori == "down")
            ori = "left";
         else if(ori == "left")
            ori = "up";
      }

   }

   public String getOri()
   {
      return ori;
   }
   public String getOriInvers()
   {
      if(ori == "up")
         return "down";
      if(ori == "down")
         return "up";
      if(ori == "left")
         return "right";
      if(ori == "right")
         return "left";

      return "...";
   }

}
