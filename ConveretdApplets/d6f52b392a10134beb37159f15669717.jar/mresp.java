import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

class mresp implements MouseListener, MouseMotionListener
{
    boolean gotone;
    Vector cl;
    int xstart;
    int ystart;
    jigsaw j;
    
    mresp(final jigsaw j) {
        this.gotone = false;
        this.xstart = 0;
        this.ystart = 0;
        this.j = j;
    }
    
    void setclus(final Vector cl) {
        this.cl = cl;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.j.paintclip(false);
        this.j.repaint();
        this.gotone = false;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.gotone) {
            final cluster cluster = this.cl.lastElement();
            cluster.posx += mouseEvent.getX() - this.xstart;
            final cluster cluster2 = this.cl.lastElement();
            cluster2.posy += mouseEvent.getY() - this.ystart;
            this.xstart = mouseEvent.getX();
            this.ystart = mouseEvent.getY();
            this.j.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        boolean b = false;
        this.j.requestFocus();
        this.j.ready = false;
        this.j.paintclip(false);
        this.gotone = false;
        for (int i = 0; i < this.cl.size() - 1; ++i) {
            if (this.closeenough(this.cl.lastElement(), (cluster)this.cl.elementAt(i))) {
                this.cl.setElementAt(this.cl.lastElement().join((cluster)this.cl.elementAt(i)), this.cl.size() - 1);
                this.cl.removeElementAt(i--);
                this.j.click.play();
                this.j.repaint();
                b = true;
            }
        }
        if (!b) {
            this.j.repaint();
        }
        if (this.cl.size() == 1) {
            if (this.j.o.thumb != null && this.j.o.thumb.isVisible()) {
                this.j.o.thumb.setVisible(false);
            }
            this.j.o.setState(0);
            this.j.o.requestFocus();
            if (!this.j.newpage.equals("")) {
                URL url = null;
                boolean b2 = true;
                try {
                    url = new URL(this.j.getCodeBase(), this.j.newpage);
                }
                catch (MalformedURLException ex) {
                    b2 = false;
                }
                if (b2) {
                    this.j.getAppletContext().showDocument(url);
                }
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.j.ready) {
            this.j.ready = false;
            this.xstart = mouseEvent.getX();
            this.ystart = mouseEvent.getY();
            int n = -1;
            this.gotone = false;
            for (int i = 0; i < this.cl.size(); ++i) {
                final cluster cluster = this.cl.elementAt(i);
                if (this.xstart >= cluster.posx && this.xstart < cluster.posx + cluster.b.getWidth() && this.ystart >= cluster.posy && this.ystart < cluster.posy + cluster.b.getHeight() && cluster.b.getRGB(this.xstart - cluster.posx, this.ystart - cluster.posy) >>> 24 != 0) {
                    n = i;
                }
            }
            if (n >= 0) {
                this.cl.addElement(this.cl.elementAt(n));
                this.cl.removeElementAt(n);
                this.gotone = true;
                this.j.setprev(this.cl.lastElement().posx, this.cl.lastElement().posy);
                this.j.paintclip(true);
                if (mouseEvent.isMetaDown() && cluster.ifrot) {
                    this.cl.lastElement().rotate(mouseEvent.isShiftDown());
                    this.j.repaint();
                }
            }
            else if (mouseEvent.isMetaDown()) {
                if (mouseEvent.isShiftDown()) {
                    this.j.o.toggleoptions();
                }
                else {
                    this.j.o.movethumb();
                }
            }
        }
        else {
            this.j.repaint();
        }
    }
    
    boolean closeenough(final cluster cluster, final cluster cluster2) {
        int n = 0;
        int n2 = 0;
        if (cluster.rotn != cluster2.rotn) {
            return false;
        }
        if (cluster.rotn == 0) {
            n = piece.pw * (cluster.lowx - cluster2.lowx) - (cluster.posx - cluster2.posx);
            n2 = piece.ph * (cluster.lowy - cluster2.lowy) - (cluster.posy - cluster2.posy);
        }
        if (cluster.rotn == 1) {
            n = piece.ph * (cluster.lowy - cluster2.lowy) - (cluster.posx - cluster2.posx);
            n2 = piece.pw * (cluster2.highx - cluster.highx) - (cluster.posy - cluster2.posy);
        }
        if (cluster.rotn == 2) {
            n = piece.pw * (cluster2.highx - cluster.highx) - (cluster.posx - cluster2.posx);
            n2 = piece.ph * (cluster2.highy - cluster.highy) - (cluster.posy - cluster2.posy);
        }
        if (cluster.rotn == 3) {
            n = piece.ph * (cluster2.highy - cluster.highy) - (cluster.posx - cluster2.posx);
            n2 = piece.pw * (cluster.lowx - cluster2.lowx) - (cluster.posy - cluster2.posy);
        }
        if (n * n + n2 * n2 < 20) {
            for (int i = 0; i < cluster.parr.length; ++i) {
                final piece piece = cluster.parr[i];
                for (int j = 0; j < cluster2.parr.length; ++j) {
                    final piece piece2 = cluster2.parr[j];
                    if ((piece.lowx == piece2.lowx && Math.abs(piece.lowy - piece2.lowy) == 1) || (Math.abs(piece.lowx - piece2.lowx) == 1 && piece.lowy == piece2.lowy)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
