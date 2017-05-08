import couleur.*;

public class Dessin
{

   private Dalle [][]board;
   private Laby laby;
   private Pos end;
   private Character pers;

   private int taille;
   private int nCase;

   private boolean f;
   private boolean r;
   private boolean l;

   private int []baseCol;

   public Dessin(Laby laby, int taille)
   {
      this.laby = laby;
      this.board = laby.getBoard();
      this.pers = laby.getCharacter();
      this.taille = taille;
      this.nCase = laby.getTaille();

      chCol();

      init();
   }

   public void chCol()
   {
      int a = (int) (Math.random()*125+65);
      int b = (int) (Math.random()*125+65);
      int c = (int) (Math.random()*125+65);

      chBase(a,b,c);
   }

   public void chBase(int a,int b,int c)
   {
      this.baseCol = new int[] {a,b,c};
   }

   public void getPers()
   {
      this.pers = laby.getCharacter();
   }

   public void init()
   {
      Deug.startDrawings(taille,taille);
      Deug.setColor(255, 255, 255);
      Deug.fillRect(0,0,taille,taille);
   }


   public void setColor(int []col)
   {
      if(col.length == 3)
      Deug.setColor(col[0], col[1], col[2]);
   }


   public void setOri(int x, int y)
   {

      String ori = pers.getOri();

      Dalle d = board[x][y];

      if(ori == "up")
      {
         f = d.wayExists("up");
         r = d.wayExists("right");
         l = d.wayExists("left");
         if(f)
            end = new Pos(x-1, y);
      }
      else if(ori == "down")
      {
         f = d.wayExists("down");
         r = d.wayExists("left");
         l = d.wayExists("right");
         if(f) 
            end = new Pos(x+1, y);
      }
      else if(ori == "right")
      {
         f = d.wayExists("right");
         r = d.wayExists("down");
         l = d.wayExists("up");
         if(f)
            end = new Pos(x, y+1);
      }
      else 
      {
         f = d.wayExists("left");
         r = d.wayExists("up");
         l = d.wayExists("down");
         if(f)
            end = new Pos(x, y-1);
      }

   }

   public void draw()
   {
      getPers();
      draw(new Pos(pers.getX(), pers.getY()), 0, this.taille, 0);
   }

   public void draw(Pos p, int d, int taille, int count)
   {

      if(count > 4)
         return;

      int x = p.getX();
      int y = p.getY();

      int []cFloor = {baseCol[0]-30, baseCol[1]-30, baseCol[2]-30};
      int []cWall = {baseCol[0]+65, baseCol[1]+65, baseCol[2]+65};
      int []cCeil = {baseCol[0]+20, baseCol[1]+20, baseCol[2]+20};
      int []cEdge = {0, 0, 0};
      int []cDoor = {170, 170, 170};

      //int []cFloor = {15, 15, 15};
      //int []cWall = {65, 65, 65};
      //int []cCeil = {40, 40, 40};
      //int []cEdge = {0, 0, 0};
      //int []cDoor = {100, 100, 100};

      int []pointX;
      int []pointY;

      int d1 = taille/5;
      int d2 = d1 + (d1/2);
      int middle = this.taille/2;

      setOri(p.getX(), p.getY());

      //static ceiling
      setColor(cCeil);
      pointX = new int[] {d, taille+d, taille+d, d};
      pointY = new int[] {d, d, middle, middle};
      Deug.fillPolygon(pointX, pointY, 4);

      //static floor
      setColor(cFloor);
      pointX = new int[] {d, taille+d, taille+d, d};
      pointY = new int[] {middle, middle, taille+d, taille+d};
      Deug.fillPolygon(pointX, pointY, 4);

      if(!r)
      {
         //right closed wall
         setColor(cWall);
         pointX = new int[] {(taille-d1)+d, taille+d, taille+d, (taille-d1)+d};
         pointY = new int[] {d1+d, d, taille+d, (taille-d1)+d};
         Deug.fillPolygon(pointX, pointY, 4);

         //right edges
         setColor(cEdge);
         Deug.drawLine((taille-d1)+d, d1+d, taille+d, d);
         Deug.drawLine((taille-d1)+d, (taille-d1)+d, taille+d, taille+d);


      }
      else
      {

         //right open wall
         setColor(cWall);
         pointX = new int[] {(taille-d1)+d, taille+d, taille+d, (taille-d1)+d};
         pointY = new int[] {d1+d, d1+d, (taille-d1)+d, (taille-d1)+d};
         Deug.fillPolygon(pointX, pointY, 4);

         //right edges
         setColor(cEdge);
         Deug.drawLine(taille+d, d, taille+d, taille+d);
         Deug.drawLine((taille-d1)+d, d1+d, taille+d, d1+d);
         Deug.drawLine((taille-d1)+d, (taille-d1)+d, taille+d, (taille-d1)+d);

      }
 
      if(!l)
      {
         //left closed Wall
         setColor(cWall);
         pointX = new int[] {d, d1+d, d1+d, d};
         pointY = new int[] {d, d1+d, (taille-d1)+d, taille+d};
         Deug.fillPolygon(pointX, pointY, 4);

         //left edges
         setColor(cEdge);
         Deug.drawLine(d, d, d1+d, d1+d);
         Deug.drawLine(d, taille+d, d1+d, (taille-d1)+d);

      }
      else
      {

         //left open wall
         setColor(cWall);
         pointX = new int[] {d, d1+d, d1+d, d};
         pointY = new int[] {d1+d, d1+d, (taille-d1)+d, (taille-d1)+d};
         Deug.fillPolygon(pointX, pointY, 4);

         //left edges
         setColor(cEdge);
         Deug.drawLine(d, d, d, taille+d);
         Deug.drawLine(d, d1+d, d1+d, d1+d);
         Deug.drawLine(d, (taille-d1)+d, d1+d, (taille-d1)+d);

      }

      if(!f)
      {
         //front closed Wall
         setColor(cWall);
         pointX = new int[] {d1+d, (taille-d1)+d, (taille-d1)+d, d1+d};
         pointY = new int[] {d1+d, d1+d, (taille-d1)+d, (taille-d1)+d};
         Deug.fillPolygon(pointX, pointY, 4);

         //front edges
         setColor(cEdge);
         Deug.drawLine(d1+d, d1+d, (taille-d1)+d, d1+d);
         Deug.drawLine(d1+d, (taille-d1)+d, (taille-d1)+d, (taille-d1)+d);

            if(p.sameAs(laby.getPosEnd()))
            {
               setColor(cDoor);
               pointX = new int[] {2*d1+d, (taille-2*d1)+d, (taille-2*d1)+d, 2*d1+d};
               pointY = new int[] {2*d1+d, 2*d1+d, (taille-d1)+d, (taille-d1)+d};
               Deug.fillPolygon(pointX, pointY, 4);

               //edges
               setColor(cEdge);
               Deug.drawLine(2*d1+d, 2*d1+d, 2*d1+d, (taille-d1)+d);
               Deug.drawLine(2*d1+d, 2*d1+d, (taille-2*d1)+d, 2*d1+d);
               Deug.drawLine((taille-2*d1)+d, 2*d1+d, (taille-2*d1)+d, (taille-d1)+d);
               Deug.drawCircle((taille-(2*d1+d1/4))+d, (taille-(2*d1))+d, (d2-d1)/10);

            }
      }
      else
      {
         //front right static Wall
         setColor(cWall);
         pointX = new int[] {d1+d, d2+d, d2+d, d1+d};
         pointY = new int[] {d1+d, d2+d, (taille-d2)+d, (taille-d1)+d};
         Deug.fillPolygon(pointX, pointY, 4);

         //front left static Wall
         setColor(cWall);
         pointX = new int[] {(taille-d2)+d, (taille-d1)+d, (taille-d1)+d, (taille-d2)+d};
         pointY = new int[] {d2+d, d1+d, (taille-d1)+d, (taille-d2)+d};
         Deug.fillPolygon(pointX, pointY, 4);

         //static edges
         setColor(cEdge);
         Deug.drawLine(d1+d, d1+d, d2+d, d2+d);
         Deug.drawLine((taille-d2)+d, d2+d, (taille-d1)+d, d1+d);
         Deug.drawLine(d1+d, (taille-d1)+d, d2+d, (taille-d2)+d);
         Deug.drawLine((taille-d2)+d, (taille-d2)+d, (taille-d1)+d, (taille-d1)+d);
      } 


      /*-------*/
      /* EDGES */
      /*-------*/

      setColor(cEdge);

      if( (!l && !f) || (l && f) )
         Deug.drawLine(d1+d, d1+d, d1+d, (taille-d1)+d);

      if( (!r && !f) || (r && f) )
         Deug.drawLine((taille-d1)+d, d1+d, (taille-d1)+d, (taille-d1)+d);


      if(f)
      {
         //if we can move forward, then draw the next dalle.
         draw(end, d2+d, (2*taille/5), count+1);
      }

   }




   /*---------*/
   /*   MAP   */
   /*---------*/


   public void drawAll()
   {
   
      for(int i = 0 ; i < taille ; i++) 
         for(int j = 0 ; j < taille ; j++)
            drawDalle(i,j);
      drawPers();
      drawEnd();
   }


   public void drawMap()
   {

      for(int i = 0 ; i < nCase ; i++)
         for(int j = 0 ; j < nCase ; j++)
            if(board[i][j].getVisite())
               drawDalle(i,j);

      drawPers();

      if(board[laby.getPosEnd().getX()][laby.getPosEnd().getY()].getVisite())
         drawEnd();
   }

 
   private void drawPers()
   {
      Deug.setColor(0, 0, 255);

      int x = pers.getX();
      int y = pers.getY();
      int tCase = taille/nCase;      
      int xC = x * tCase;
      int yC = y * tCase;

      Deug.fillCircle(yC+tCase/2, xC+tCase/2, tCase/6); // personnage

      String ori = pers.getOri();

      if(ori == "up")
      {
         Deug.drawLine(yC+tCase/2, xC+tCase/2, yC+tCase/2, xC+tCase/2-tCase/3);
      }
      if(ori == "down")
      {
         Deug.drawLine(yC+tCase/2, xC+tCase/2, yC+tCase/2, xC+tCase/2+tCase/3);
      }
      if(ori == "left")
      {
         Deug.drawLine(yC+tCase/2, xC+tCase/2, yC+tCase/2-tCase/3, xC+tCase/2);
      }
      if(ori == "right")
      {
         Deug.drawLine(yC+tCase/2, xC+tCase/2, yC+tCase/2+tCase/3, xC+tCase/2);
      }
   }


   public void drawDalle(int i, int j)
   {

      Dalle d = board[i][j];

      if(!board[i][j].getExists())
         return;

      boolean up = d.getUp();
      boolean down = d.getDown();
      boolean left = d.getLeft();
      boolean right = d.getRight();

      int tCase = taille/nCase;
      int lC = i*tCase;
      int rC = (i+1)*tCase;
      int uC = j*tCase;
      int dC = (j+1)*tCase;
      int dx = tCase/6;

      int []cWay = new int[] {255, 255, 255};
      int []cWall = new int[] {150, 150, 150};

      //middle

      setColor(cWay);

      int []pointX = new int[] {uC+dx, dC-dx, dC-dx, uC+dx};
      int []pointY = new int[] {lC+dx, lC+dx, rC-dx, rC-dx};
      Deug.fillPolygon(pointX, pointY, 4);

      //sides

      if(down)
         setColor(cWay);
      else
         setColor(cWall);
   
      pointX = new int[] {uC+dx, dC-dx, dC-dx, uC+dx};
      pointY = new int[] {rC-dx, rC-dx, rC, rC};
      Deug.fillPolygon(pointX, pointY, 4);


      if(up)
         setColor(cWay);
      else
         setColor(cWall);

      pointX = new int[] {uC+dx, dC-dx, dC-dx, uC+dx};
      pointY = new int[] {lC, lC, lC+dx, lC+dx};
      Deug.fillPolygon(pointX, pointY, 4);

      if(right)
         setColor(cWay);
      else
         setColor(cWall);

      pointX = new int[] {dC-dx, dC, dC, dC-dx};
      pointY = new int[] {lC+dx, lC+dx, rC-dx, rC-dx};
      Deug.fillPolygon(pointX, pointY, 4);

      if(left)
         setColor(cWay);
      else
         setColor(cWall);

      pointX = new int[] {uC, uC+dx, uC+dx, uC};
      pointY = new int[] {lC+dx, lC+dx, rC-dx, rC-dx};
      Deug.fillPolygon(pointX, pointY, 4);

      //corners

      setColor(cWall);

      pointX = new int[] {uC, uC+dx, uC+dx, uC};
      pointY = new int[] {lC, lC, lC+dx, lC+dx};
      Deug.fillPolygon(pointX, pointY, 4);

      pointX = new int[] {uC, uC+dx, uC+dx, uC};
      pointY = new int[] {rC-dx, rC-dx, rC, rC};
      Deug.fillPolygon(pointX, pointY, 4);

      pointX = new int[] {dC-dx, dC, dC, dC-dx};
      pointY = new int[] {lC, lC, lC+dx, lC+dx};
      Deug.fillPolygon(pointX, pointY, 4);

      pointX = new int[] {dC-dx, dC, dC, dC-dx};
      pointY = new int[] {rC-dx, rC-dx, rC, rC};
      Deug.fillPolygon(pointX, pointY, 4);


   }


   public void drawEnd()
   {

      int tCase = taille/nCase;

      Deug.setColor(255, 0, 0);
      Deug.fillCircle(laby.getPosEnd().getY()*tCase+tCase/2, laby.getPosEnd().getX()*tCase+tCase/2, tCase/6);
   }


   public boolean isVisible(int xd, int yd) 
   {

      int x = pers.getX();
      int y = pers.getY();

      Dalle d = board[x][y];

      int diffX = x - xd;
      int diffY = y - yd;

      if(diffX == -1)
        return d.wayExists("down");
      if(diffX == 1)
        return d.wayExists("up");
      if(diffY == 1)
        return d.wayExists("left");
      if(diffY == -1)
        return d.wayExists("right");

      return false;
   }
 


   /*
      Debug Mode
   */

   public void drawDebug()
   {

      int tCase = taille/nCase;

      Deug.setColor(255, 255, 255);
      Deug.fillRect(0,0, taille, taille);

      drawPers();
      drawEnd();

      Deug.setColor(0, 0, 0);      

      for(int i = 0 ; i < nCase ; i++)
      {
         for(int j = 0 ; j < nCase ; j++)
         {

            Dalle d = board[i][j];

            boolean up = d.getUp();
            boolean down = d.getDown();
            boolean left = d.getLeft();
            boolean right = d.getRight();
            int lC = i*tCase;
            int rC = (i+1)*tCase;
            int uC = j*tCase;
            int dC = (j+1)*tCase;
            int dx = tCase/6;

            if(!down)
            {
               Deug.drawLine(uC+dx, rC-dx, dC-dx, rC-dx);
            }
            else
            {
               Deug.drawLine(dC-dx, rC-dx, dC-dx, rC); //right-down
               Deug.drawLine(uC+dx, rC-dx, uC+dx, rC); //left-down
            }


            if(!right)
            {
               Deug.drawLine(dC-dx, lC+dx, dC-dx, rC-dx);
            }
            else
            {
               Deug.drawLine(dC-dx, rC-dx, dC, rC-dx); //down-right
               Deug.drawLine(dC-dx, lC+dx, dC, lC+dx); //up-right
            }


            if(!up)
            {
               Deug.drawLine(uC+dx, lC+dx, dC-dx, lC+dx);
            }
            else
            {
               Deug.drawLine(dC-dx, lC, dC-dx, lC+dx); //right-up
               Deug.drawLine(uC+dx, lC, uC+dx, lC+dx); //left-up
            }


            if(!left)
            {
               Deug.drawLine(uC+dx, lC+dx, uC+dx, rC-dx);
            }
            else
            {
               Deug.drawLine(uC, rC-dx, uC+dx, rC-dx); //down-left
               Deug.drawLine(uC, lC+dx, uC+dx, lC+dx); //up-left
            }
         }
      }
   } 


}
