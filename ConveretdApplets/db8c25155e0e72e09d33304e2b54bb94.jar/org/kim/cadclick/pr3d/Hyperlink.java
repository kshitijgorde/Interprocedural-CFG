// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;
import java.awt.Cursor;
import java.net.URL;
import java.awt.event.MouseListener;
import java.awt.Label;

public class Hyperlink extends Label implements MouseListener
{
    URL url;
    Cursor oldCursor;
    Applet parent;
    
    public Hyperlink(final String s, final String s2, final Font font, final Applet parent) {
        super(s2);
        this.parent = parent;
        super.setFont(font);
        super.setForeground(Color.blue);
        try {
            this.url = new URL(s);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        this.addMouseListener(this);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.oldCursor = this.getCursor();
        this.setCursor(new Cursor(12));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(this.oldCursor);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.parent.getAppletContext().showDocument(this.url, "_blank");
    }
}
