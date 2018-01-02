import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.Choice;
import java.awt.Component;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class DrawControls extends Panel implements ItemListener
{
    DrawPanel target;
    
    public DrawControls(final DrawPanel target) {
        this.target = target;
        this.setLayout(new FlowLayout());
        this.setBackground(Color.lightGray);
        target.setForeground(Color.red);
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        final Checkbox checkbox;
        this.add(checkbox = new Checkbox(null, checkboxGroup, true));
        checkbox.addItemListener(this);
        checkbox.setBackground(Color.black);
        checkbox.setForeground(Color.black);
        final Checkbox checkbox2;
        this.add(checkbox2 = new Checkbox(null, checkboxGroup, true));
        checkbox2.addItemListener(this);
        checkbox2.setBackground(Color.darkGray);
        checkbox2.setForeground(Color.darkGray);
        final Checkbox checkbox3;
        this.add(checkbox3 = new Checkbox(null, checkboxGroup, true));
        checkbox3.addItemListener(this);
        checkbox3.setBackground(Color.gray);
        checkbox3.setForeground(Color.gray);
        final Checkbox checkbox4;
        this.add(checkbox4 = new Checkbox(null, checkboxGroup, true));
        checkbox4.addItemListener(this);
        checkbox4.setBackground(Color.lightGray);
        checkbox4.setForeground(Color.lightGray);
        final Checkbox checkbox5;
        this.add(checkbox5 = new Checkbox(null, checkboxGroup, true));
        checkbox5.addItemListener(this);
        checkbox5.setBackground(Color.white);
        checkbox5.setForeground(Color.white);
        final Checkbox checkbox6;
        this.add(checkbox6 = new Checkbox(null, checkboxGroup, true));
        checkbox6.addItemListener(this);
        checkbox6.setBackground(Color.red);
        checkbox6.setForeground(Color.red);
        final Checkbox checkbox7;
        this.add(checkbox7 = new Checkbox(null, checkboxGroup, true));
        checkbox7.addItemListener(this);
        checkbox7.setBackground(Color.magenta);
        checkbox7.setForeground(Color.magenta);
        final Checkbox checkbox8;
        this.add(checkbox8 = new Checkbox(null, checkboxGroup, true));
        checkbox8.addItemListener(this);
        checkbox8.setBackground(Color.pink);
        checkbox8.setForeground(Color.pink);
        final Checkbox checkbox9;
        this.add(checkbox9 = new Checkbox(null, checkboxGroup, true));
        checkbox9.addItemListener(this);
        checkbox9.setBackground(Color.orange);
        checkbox9.setForeground(Color.orange);
        final Checkbox checkbox10;
        this.add(checkbox10 = new Checkbox(null, checkboxGroup, true));
        checkbox10.addItemListener(this);
        checkbox10.setBackground(Color.yellow);
        checkbox10.setForeground(Color.yellow);
        final Checkbox checkbox11;
        this.add(checkbox11 = new Checkbox(null, checkboxGroup, true));
        checkbox11.addItemListener(this);
        checkbox11.setBackground(Color.green);
        checkbox11.setForeground(Color.green);
        final Checkbox checkbox12;
        this.add(checkbox12 = new Checkbox(null, checkboxGroup, true));
        checkbox12.addItemListener(this);
        checkbox12.setBackground(Color.cyan);
        checkbox12.setForeground(Color.cyan);
        final Checkbox checkbox13;
        this.add(checkbox13 = new Checkbox(null, checkboxGroup, true));
        checkbox13.addItemListener(this);
        checkbox13.setBackground(Color.blue);
        checkbox13.setForeground(Color.blue);
        target.setForeground(checkbox13.getForeground());
        final Choice choice = new Choice();
        choice.addItemListener(this);
        choice.addItem("Line color");
        choice.addItem("Background");
        choice.setBackground(Color.magenta);
        choice.setForeground(Color.black);
        this.add(choice);
        final Choice choice2 = new Choice();
        choice2.addItemListener(this);
        choice2.addItem("Star mode");
        choice2.addItem("Fence mode");
        choice2.addItem("Stairs mode");
        choice2.addItem("Pencil");
        choice2.setBackground(Color.pink);
        choice.setForeground(Color.black);
        this.add(choice2);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() instanceof Checkbox) {
            if (this.target.FG_BG == 0) {
                this.target.setForeground(((Component)itemEvent.getSource()).getForeground());
            }
            else {
                this.target.setBackground(((Component)itemEvent.getSource()).getBackground());
            }
        }
        else if (itemEvent.getSource() instanceof Choice) {
            final String s = (String)itemEvent.getItem();
            if (s.equals("Line color")) {
                this.target.setDrawMode(0);
                this.target.setFG_BG(0);
            }
            else if (s.equals("Background")) {
                this.target.setFG_BG(1);
            }
            else if (s.equals("Star mode")) {
                this.target.setDrawMode(0);
                this.target.setStarMode(0);
            }
            else if (s.equals("Fence mode")) {
                this.target.setDrawMode(0);
                this.target.setStarMode(1);
            }
            else if (s.equals("Stairs mode")) {
                this.target.setDrawMode(0);
                this.target.setStarMode(2);
            }
            else if (s.equals("Pencil")) {
                this.target.setDrawMode(1);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        graphics.setColor(Color.lightGray);
        graphics.draw3DRect(0, 0, bounds.width, bounds.height, false);
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof Checkbox) {
                final Point location = component.getLocation();
                final Dimension size = component.getSize();
                graphics.setColor(Color.black);
                graphics.drawRect(location.x - 1, location.y - 1, size.width + 1, size.height + 1);
            }
        }
    }
}
