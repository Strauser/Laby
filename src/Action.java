import java.awt.event.*;
import couleur.*;

public class Action implements KeyListener, WindowListener
{

   private Laby l;

   public Action(Laby l)
   {
      this.l = l;
      Deug.setFocus(this);
   }

   public void windowDeactivated(WindowEvent e) {}
   public void windowIconified(WindowEvent e) {}
   public void windowClosed(WindowEvent e) {}
   public void windowClosing(WindowEvent e) {}
   public void windowGainedFocus(WindowEvent e) {}
   public void windowLostFocus(WindowEvent e) {}
   public void windowStateChanged(WindowEvent e) {}
   public void windowOpened(WindowEvent e) {}
   public void windowActivated(WindowEvent e) 
   {
      Deug.getFocus(this);
   }
   public void windowDeiconified(WindowEvent e)
   {
      Deug.getFocus(this);
   }

   public void keyReleased(KeyEvent e) {}
   public void keyTyped(KeyEvent e) {}
   public void keyPressed(KeyEvent e)
   {
      l.bouger(e.getKeyCode());
   }

      

}
