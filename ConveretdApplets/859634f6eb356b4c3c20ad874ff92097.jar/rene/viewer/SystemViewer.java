// 
// Decompiled by Procyon v0.5.30
// 

package rene.viewer;

import java.io.PrintWriter;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;

public class SystemViewer extends Viewer
{
    TextArea T;
    
    public SystemViewer() {
        super("dummy");
        this.setLayout(new BorderLayout());
        this.add("Center", this.T = new TextArea());
    }
    
    public void appendLine(final String s) {
        this.T.append(String.valueOf(s) + "\n");
    }
    
    public void appendLine(final String s, final Color color) {
        this.appendLine(s);
    }
    
    public void append(final String s) {
        this.T.append(s);
    }
    
    public void append(final String s, final Color color) {
        this.append(s);
    }
    
    public void setText(final String text) {
        this.T.setText(text);
    }
    
    public void doUpdate(final boolean b) {
        this.T.repaint();
    }
    
    public void setFont(final Font font) {
        this.T.setFont(font);
    }
    
    public void save(final PrintWriter printWriter) {
        printWriter.print(this.T.getText());
        printWriter.flush();
    }
}
