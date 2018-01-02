import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class SizedComponent extends HelpPanel
{
    private int width;
    private int height;
    
    public SizedComponent(final Component component, final int width, final int height, final String helpMessage, final String alignment) {
        super(helpMessage, alignment);
        this.setLayout(new BorderLayout());
        this.add("Center", component);
        this.setBackground(Color.lightGray);
        this.width = width;
        this.height = height;
        component.addMouseListener(this);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
}
