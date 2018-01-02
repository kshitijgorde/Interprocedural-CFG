import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Vector;
import java.awt.Scrollbar;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class ConsultImageScroller extends Panel
{
    ConsultCanvas consultCanvas;
    Scrollbar vs;
    Scrollbar hs;
    ConsultTreeTrial consultTree;
    ConsultFolder folder;
    int HscrollPercent;
    int VscrollPercent;
    
    ConsultImageScroller(final ConsultTreeTrial consultTree, final Vector folderVector) {
        this.consultTree = consultTree;
        this.setLayout(new BorderLayout());
        this.vs = new Scrollbar(1, 0, 10, 0, 100);
        this.add("Center", this.consultCanvas = new ConsultCanvas(consultTree, folderVector));
        this.add("East", this.vs);
        consultTree.consultCanvas = this.consultCanvas;
    }
    
    public boolean handleEvent(final Event e) {
        switch (e.id) {
            case 601:
            case 602:
            case 603:
            case 604:
            case 605: {
                this.scrollDown(this.consultCanvas.getGraphics());
                return super.handleEvent(e);
            }
            default: {
                return super.handleEvent(e);
            }
        }
    }
    
    void scrollDown(final Graphics g) {
        this.folder = this.consultTree.rootFolder;
        this.consultCanvas.offscreenImg.getWidth(this);
        final int offscreenHeight = this.consultCanvas.offscreenImg.getHeight(this);
        final float vp = offscreenHeight * (this.vs.getValue() / this.vs.getMaximum());
        this.consultCanvas.y = -(int)vp - this.consultCanvas.starty + 6;
        g.drawImage(this.consultCanvas.offscreenImg, this.consultCanvas.x, this.consultCanvas.y, this);
        g.setColor(Color.black);
    }
}
