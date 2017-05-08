package couleur;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * La classe <code>Deug</code> permet de réaliser:
 * <ul>
 * <li>des dessins,
 * </ul>
 * Elle permet de masquer certaines constructions de Java que nous avons
 * considérées comme trop avancées pour une initiation à la programmation.
 * D'autre part, une tentative d'uniformisation de nommage a été faite afin
 * de normaliser les incantations Java.
 *
 * <H3>Dessins</H3>
 *
 * <P>Un exemple de programme graphique typique est le suivant:
 * <PRE>
 * Deug.startDrawings(200,200);
 * for (i=0; i<200; i+=4) {
 *   Deug.setGray(i);
 *   Deug.drawLine(0,i,199,199-i);
 *   Deug.drawLine(i,0,199-i,199);
 * }
 * Deug.stopDrawings();
 * </PRE>
 * Pour obtenir:<BR>
 * <IMG SRC="Dessin.jpg">

 * @author Jean-Baptiste.Yunes@liafa.jussieu.fr
 * @author Fabien.Tarissan@pps.jussieu.fr
 * @version 0.15
 */
public final class Deug {
    
    /**
     * L'argument passé est invalide (ex: <CODE>==null</CODE>)
     **/
    public static final int ARG_ERROR=3;
    /**
     * Pas d'instanciation de la classe <code>Deug</code>
     */
    private Deug() {}

    private static JFrame theFrame = null;
    private static Drawable theDrawable = null;
    /**
     * Prépare une zone d'affichage de taille 200x200.
     */
    public static void startDrawings() {
	startDrawings(200,200);
    }
    /**
     * Prépare une zone d'affichage de taille arbitraire.
     * @param width La largeur de la zone d'affichage
     * @param height La hauteur de la zone d'affichage
     */
    public static void startDrawings(int width,int height) {
	if (theFrame!=null) return;
	theFrame = new JFrame("Dessin");
	theFrame.setResizable(false);
	theDrawable = new Drawable(width,height);
	theFrame.getContentPane().add(theDrawable);
	theFrame.pack();
	theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	theFrame.setVisible(true);
	try {
	    Thread.sleep(2000); // Should be synchronized!!!
	} catch(Exception e) {
	}
    }
    /**
     * Ferme la fenêtre graphique.
    */
    public static void stopDrawings() {
	if (theFrame==null) return;
	theFrame.dispose();
	theFrame=null;
    }
    /**
     * Dessine un point dans la zone d'affichage.
     * @param x L'abscisse du point.
     * @param y L'ordonnée du point.
     */
    public static void drawPoint(int x,int y) {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.drawLine(x,y,x,y);
    }
    /**
     * Modifie la couleur de l'encre des points à dessiner.
     * Les couleurs disponibles sont au nombre de 256 <em>niveaux</em> de gris,
     * de <code>0</code> pour coder le noir à <code>255</code> pour
     * coder le blanc. Un gris médian s'obtient donc avec <code>127</code>.
     * @param c La couleur.
     */
    public static void setGray(int c) {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.setColor(c,c,c);
    } 

    /**
     * Modifie la couleur de l'encre des points à dessiner.
     * Les couleurs sont codées suivant le système soustractif rgb (red, green, blue)
     * (255, 0, 0) représente le rouge, (255, 255, 0) fera du jaune
     * @param r quantité de rouge (entre 0 et 255)
     * @param g qunatité de vert (entre 0 et 255)
     * @param b quantité de bleu (entre 0 et 255)
     */
    public static void setColor(int r, int g, int b) {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.setColor(r,g,b);
    }

     /** Dessine un Polygone vide dans la zone d'affichage.
     * les sommets sont (xPoints[0], yPoints[0]) ... (xPoints[nPoints-1], yPoints[nPoints-1])
     * @param xPoints tableau contenat les abscisses des points  
     * @param yPoints tableau contenat les ordonnes des points  
     *  @param nPoints nombre de points
     */ 
    
    public static void drawPolygon(int[] xPoints, int[] yPoints, int nPoints){
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.drawPolygon(xPoints,yPoints,nPoints);
    } 

    /** Dessine un Polygone plein dans la zone d'affichage.
     * les sommets sont (xPoints[0], yPoints[0]) ... (xPoints[nPoints-1], yPoints[nPoints-1])
     * @param xPoints tableau contenat les abscisses des points  
     * @param yPoints tableau contenat les ordonnes des points  
     *  @param nPoints nombre de points
     */ 
    
    public static void fillPolygon(int[] xPoints, int[] yPoints, int nPoints){
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.fillPolygon(xPoints,yPoints,nPoints);
    }
    /**
     * Dessine un segment de droite dans la zone d'affichage.
     * @param x0 L'abscisse du premier point.
     * @param y0 L'ordonnée du premier point.
     * @param x1 L'abscisse du second point.
     * @param y1 L'ordonnée du second point.
     */
    public static void drawLine(int x0,int y0,int x1, int y1) {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.drawLine(x0,y0,x1,y1);
    }


    /**
     * Dessine un rectangle vide dans la zone d'affichage.
     * <p>
     * Les droites délimitant le rectangle à gauche et à droite sont
     * situées aux abscisses <code>x</code> et <code>x+l</code>.
     * Celles délimitant le rectangle en haut et en bas sont situées
     * aux ordonnées <code>y</code> et <code>y+h</code>.
     * @param x L'abscisse du coin nord-ouest du rectangle.
     * @param y L'ordonnée du coin nord-ouest du rectangle.
     * @param l La largeur du rectangle.
     * @param h La hauteur du rectangle.
     */
    public static void drawRect(int x,int y,int l, int h) {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.drawRect(x,y,l,h);
    }

    /**
     * Dessine un rectangle plein dans la zone d'affichage.
     * <p>
     * Les droites délimitant le rectangle à gauche et à droite sont
     * situées aux abscisses <code>x</code> et <code>x+l</code>.
     * Celles délimitant le rectangle en haut et en bas sont situées
     * aux ordonnées <code>y</code> et <code>y+h</code>.
     * @param x L'abscisse du coin nord-ouest du rectangle.
     * @param y L'ordonnée du coin nord-ouest du rectangle.
     * @param l La largeur du rectangle.
     * @param h La hauteur du rectangle.
     */
    public static void fillRect(int x,int y,int l, int h) {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.fillRect(x,y,l,h);
    }

    /**
     * Dessine un cercle vide dans la zone d'affichage.
     * @param x L'abscisse du centre du cercle.
     * @param y L'ordonnée du centre du cercle.
     * @param r Le rayon du cercle.
     */
    public static void drawCircle(int x,int y,int r) {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.drawOval(x-r, y-r, 2*r, 2*r);
    }

    /**
     * Dessine un cercle plein dans la zone d'affichage.
     * @param x L'abscisse du centre du cercle.
     * @param y L'ordonnée du centre du cercle.
     * @param r Le rayon du cercle.
     */
    public static void fillCircle(int x,int y,int r) {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.fillOval(x-r, y-r, 2*r, 2*r);
    }


    /**
     * Dessine un arc de cercle vide dans la zone d'affichage.
     * <p>
     * La valeur des angles est spécifiée en degré.
     * Le sens de rotation est celui des cercles trigonométriques.
     * L'angle de départ est <code>angleInit</code>
     * et l'angle de fin <code>angleInit + angleCouvert</code>
     * @param x L'abscisse du centre du cercle.
     * @param y L'ordonnée du centre du cercle.
     * @param r Le rayon du cercle.
     * @param angleInit Angle de départ.
     * @param angleCouvert Angle de rotation.
     */
    public static void drawArc(int x,int y,int r, int angleInit, int angleCouvert) {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.drawArc(x-r, y-r, 2*r, 2*r, angleInit, angleCouvert);
    }

    /**
     * Dessine un arc de cercle plein dans la zone d'affichage.
     * <p>
     * La valeur des angles est spécifiée en degré.
     * Le sens de rotation est celui des cercles trigonométriques.
     * L'angle de départ est <code>angleInit</code>
     * et l'angle de fin <code>angleInit + angleCouvert</code>
     * @param x L'abscisse du centre du cercle.
     * @param y L'ordonnée du centre du cercle.
     * @param r Le rayon du cercle.
     * @param angleInit L'angle de départ.
     * @param angleCouvert L'angle de rotation.
     */
    public static void fillArc(int x,int y,int r, int angleInit, int angleCouvert) {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.fillArc(x-r, y-r, 2*r, 2*r, angleInit, angleCouvert);
    }

    /**
     * Efface intégralement le contenu de la fenêtre graphique.
    */
    public static void clearArea() {
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.clearArea();
    } 
    /**
     * Écrit une chaîne de caractères à l'endroit indiqué
     * @param str chaîne de caractères à écrire
     * @param x L'abscisse du coin en bas à gauche de ce qui est écrit
     * @param y L'ordonnée  du coin en bas à gauche de ce qui est écrit
     */
    public static void drawString(String str,int x, int y){	
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.drawString(str,x,y);
    }
    /**
     * Modifie la taille de la fonte (valeurs classiques entre 8 et 72)
     * @param taille taille de fonte choisie
     */
    public static void setFontSize(int taille){
	if (theFrame==null)
	    throw new RuntimeException("il manque un appel à startDrawings()");
	theDrawable.setFontSize(taille);
    }

    public static void setFocus(EventListener k){
   theDrawable.setFocusable(true);
   theDrawable.addKeyListener((KeyListener)k);
    }

    public static void getFocus(WindowListener w){
   theDrawable.requestFocus();
    }
	
}

class Drawable extends Canvas {
    private int width, height;
    private Image backingStore;
    private Graphics backingGraphics;
    public Drawable(int width,int height) {
	setSize(width,height);
    }
    public void setSize(int width,int height) {
	super.setSize(width,height);
	this.width = width;
	this.height = height;
    }
    public Dimension getPreferredSize() {
	return new Dimension(width,height);
    }
    public void paint(Graphics g) {
	if (backingStore==null) {
	    backingStore = createImage(Drawable.this.width,
				       Drawable.this.height);
	    backingGraphics = backingStore.getGraphics();
	}
	g.drawImage(backingStore,0,0,this);
    }
    public void update(Graphics g) {
	paint(g);
    }
    public void drawLine(int x0,int y0,int x1, int y1) {
	if (backingGraphics==null) return;
	backingGraphics.drawLine(x0,y0,x1,y1);
	repaint();
    }
    
    public void drawRect(int x, int y, int l, int h) {
	if(backingGraphics==null) return;
	backingGraphics.drawRect(x,y,l,h);
	repaint();
    }

    public void fillRect(int x, int y, int l, int h) {
	if(backingGraphics==null) return;
	backingGraphics.fillRect(x,y,l,h);
	repaint();
    }

    public void drawOval(int x, int y, int l, int h) {
	if(backingGraphics==null) return;
	backingGraphics.drawOval(x,y,l,h);
	repaint();
    }

    public void fillOval(int x, int y, int l, int h) {
	if(backingGraphics==null) return;
	backingGraphics.fillOval(x,y,l,h);
	repaint();
    }

    public void drawArc(int x, int y, int l, int h, int angleInit, int angleCouvert) {
	if(backingGraphics==null) return;
	backingGraphics.drawArc(x,y,l,h, angleInit, angleCouvert);
	repaint();
    }

    public void fillArc(int x, int y, int l, int h, int angleInit, int angleCouvert) {
	if(backingGraphics==null) return;
	backingGraphics.fillArc(x,y,l,h, angleInit, angleCouvert);
	repaint();
    }

    public void setColor(int c1,int c2,int c3) {
	if (backingGraphics==null) return;
	backingGraphics.setColor(new Color(c1,c2,c3));
    }
    public void clearArea() {
	if (backingGraphics==null) return;
	backingGraphics.clearRect(0,0,width,height);
	repaint();
    }
    public void sleep(int secondes) {
        try {
            Thread.sleep(secondes*1000);
        } catch(InterruptedException ie) {}
    }

    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints){
	if(backingGraphics==null) return;
	backingGraphics.drawPolygon(xPoints, yPoints, nPoints);
	repaint();	
    } 
    public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints){
	if(backingGraphics==null) return;
	backingGraphics.fillPolygon(xPoints, yPoints, nPoints);
	repaint();	
    }

    public void drawString(String str,int x, int y){
	if(backingGraphics==null) return;
	backingGraphics.drawString(str,x,y);
	repaint();	
    }

    public void setFontSize(int taille){	
	if(backingGraphics==null) return;
	backingGraphics.setFont(new Font(Font.SERIF, Font.PLAIN, taille));
    }
    
}

