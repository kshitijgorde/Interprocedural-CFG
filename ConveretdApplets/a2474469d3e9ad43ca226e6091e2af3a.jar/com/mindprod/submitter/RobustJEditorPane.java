// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.submitter;

import java.awt.Graphics;
import javax.swing.JEditorPane;

public class RobustJEditorPane extends JEditorPane
{
    protected void paintComponent(final Graphics g) {
        try {
            super.paintComponent(g);
        }
        catch (SecurityException ex) {}
    }
    
    protected void printComponent(final Graphics g) {
        try {
            super.printComponent(g);
        }
        catch (SecurityException ex) {}
    }
}
