// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dialog;
import java.awt.Image;
import gjt.DialogClient;
import java.awt.Panel;

public class WPPanel extends Panel implements DialogClient
{
    Image paper;
    
    public void dialogDismissed(final Dialog d) {
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.paper, 0, 0, this);
        super.paint(g);
    }
    
    public void setPaper(final Image im) {
        this.paper = im;
    }
    
    public void showMessage(final String s) {
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
