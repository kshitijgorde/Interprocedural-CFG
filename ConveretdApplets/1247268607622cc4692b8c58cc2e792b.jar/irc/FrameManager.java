// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import irc.channels.textarea.NewTextDocument;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import irc.channels.wholist.NoticeInfos;
import java.awt.event.MouseListener;
import javax.swing.JDialog;

public class FrameManager extends JDialog implements MouseListener
{
    private int nbframe;
    private int click;
    private int posx;
    private int posy;
    private EIRC eirc;
    private NoticeInfos no;
    private int time;
    private boolean affichierforc;
    
    public FrameManager(final EIRC eirc) {
        this.nbframe = 0;
        this.click = 0;
        this.time = 0;
        this.affichierforc = false;
        this.eirc = eirc;
        final Rectangle maximumWindowBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.posx = maximumWindowBounds.width;
        this.posy = maximumWindowBounds.height;
        this.setUndecorated(true);
        this.no = new NoticeInfos(this.eirc);
        this.no.textzone.addMouseListener(this);
        this.getContentPane().add(this.no);
        this.setVisible(false);
        this.setSize(1, 400);
        final Point location = eirc.getAccueil().Application.getLocation();
        this.setLocation((int)(location.x + eirc.getAccueil().Application.getSize().getWidth()), location.y);
        new Timer().schedule(new Reduire(this), 0L, 1000L);
        this.setFocusable(true);
    }
    
    public NewTextDocument Affichedoc() {
        this.affichier();
        return this.no.getDoc();
    }
    
    public NewTextDocument Affichedocforc() {
        this.affichierforc();
        return this.no.getDoc();
    }
    
    public void affichier() {
        if (this.eirc == null || this.eirc.getAccueil() == null || !this.eirc.getAccueil().Application.isActive()) {
            return;
        }
        if (this.affichierforc) {
            return;
        }
        this.affichierforc = true;
        this.time = 90;
        final Point location = this.eirc.getAccueil().Application.getLocation();
        this.setLocation(location.x - 250, location.y);
        if (this.getSize().width == 1) {
            this.setVisible(true);
            this.setSize(250, 400);
            this.no.scrollDown1();
        }
    }
    
    public void affichierforc() {
        if (this.eirc == null || this.eirc.getAccueil() == null || !this.eirc.getAccueil().Application.isActive()) {
            return;
        }
        if (this.affichierforc) {
            return;
        }
        this.affichierforc = true;
        this.time = 90;
        final Point location = this.eirc.getAccueil().Application.getLocation();
        this.setLocation(location.x - 250, location.y);
        this.setVisible(true);
        this.setSize(250, 400);
        this.no.scrollDown1();
    }
    
    public int getClick() {
        return this.click;
    }
    
    public int getNbframe() {
        return this.nbframe;
    }
    
    public int getPosx() {
        return this.posx;
    }
    
    public int getPosy() {
        return this.posy;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public void masquer() {
        this.affichierforc = false;
        this.setSize(1, 400);
        this.setVisible(false);
    }
    
    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    @Override
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    @Override
    public void mousePressed(final MouseEvent mouseEvent) {
        this.eirc.getAccueil().Application.toFront();
    }
    
    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void setAffichierforc(final boolean affichierforc) {
        this.affichierforc = affichierforc;
    }
    
    public void setClick(final int click) {
        this.click = click;
    }
    
    public void setNbframe(final int nbframe) {
        this.nbframe = nbframe;
    }
    
    public void setPosx(final int posx) {
        this.posx = posx;
    }
    
    public void setPosy(final int posy) {
        this.posy = posy;
    }
    
    public void setTime(final int time) {
        this.time = time;
    }
    
    public void vider() {
        this.no.vider();
        this.affichierforc = false;
    }
    
    public class Reduire extends TimerTask
    {
        FrameManager fm;
        
        public Reduire(final FrameManager fm) {
            this.fm = fm;
        }
        
        @Override
        public void run() {
            if (FrameManager.this.time == 0) {
                FrameManager.this.setSize(1, 400);
                FrameManager.this.setVisible(false);
                FrameManager.this.affichierforc = false;
            }
            else if (FrameManager.this.time > 0) {
                FrameManager.this.time--;
            }
        }
    }
}
