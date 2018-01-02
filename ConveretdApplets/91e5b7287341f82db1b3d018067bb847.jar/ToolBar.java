import java.awt.Dimension;
import java.awt.Image;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.applet.Applet;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ToolBar extends Panel
{
    ImageButton fButton;
    ImageButton bButton;
    FancyLabel fLabel;
    int width;
    int height;
    
    public ToolBar(final Applet applet, final int width, final String s, final String s2, final String s3, final int n) {
        final int n2 = (int)(width * 0.125);
        final int width2 = width - 2 * n2;
        this.width = width;
        this.height = n;
        final Image image = applet.getImage(applet.getDocumentBase(), s);
        final Image image2 = applet.getImage(applet.getDocumentBase(), s2);
        final Image image3 = applet.getImage(applet.getDocumentBase(), s3);
        this.fButton = new ImageButton(image, n2, n, 1002);
        this.bButton = new ImageButton(image2, n2, n, 1001);
        this.fLabel = new FancyLabel(this, image3, width2, n);
        this.setLayout(new FlowLayout(0, 0, 0));
        this.setBackground(Color.black);
        this.add(this.fLabel);
        this.add(this.fButton);
        this.add(this.bButton);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
}
