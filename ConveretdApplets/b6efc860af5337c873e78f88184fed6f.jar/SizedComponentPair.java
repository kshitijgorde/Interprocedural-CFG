import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

class SizedComponentPair extends HelpPanel
{
    private static final int COMPONENT_1_Y = 0;
    private static final int COMPONENT_2_Y = 20;
    private static final int INSET = 2;
    private int width;
    private int height;
    
    public SizedComponentPair(final Component component1, final Component component2, final int width, final int height, final String helpMessage, final String alignment) {
        super(helpMessage, alignment);
        this.width = width;
        this.height = height;
        if (alignment.equals("East")) {
            this.setLayout(null);
            component1.setBounds(2, 0, width, height / 2);
            component2.setBounds(2, 20, width, height / 2);
            this.add(component1);
            this.add(component2);
        }
        else {
            this.setLayout(new BorderLayout());
            this.add("West", component1);
            this.add("East", component2);
        }
        this.setBackground(Color.lightGray);
        component1.addMouseListener(this);
        component2.addMouseListener(this);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.width, this.height);
    }
}
