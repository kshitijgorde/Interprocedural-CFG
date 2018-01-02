// 
// Decompiled by Procyon v0.5.30
// 

package rene.viewer;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.Dimension;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.PopupMenu;
import java.util.Vector;
import rene.util.list.ListElement;
import java.awt.ItemSelectable;
import java.awt.event.MouseListener;

public class Lister extends Viewer implements MouseListener, ItemSelectable
{
    ListElement Chosen;
    Vector AL;
    Vector IL;
    PopupMenu PM;
    
    public Lister(final boolean b, final boolean b2) {
        super(b, b2);
        this.AL = new Vector();
        this.IL = new Vector();
    }
    
    public Lister() {
        this(true, true);
    }
    
    public Lister(final String s) {
        super(s);
    }
    
    public String getSelectedItem() {
        if (this.Chosen == null) {
            return null;
        }
        return new String(((Line)this.Chosen.content()).a);
    }
    
    public void setText(final String text) {
        this.Chosen = null;
        super.setText(text);
    }
    
    public void add(final String s) {
        this.appendLine(s);
    }
    
    public void add(final String s, final Color color) {
        this.appendLine(s, color);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.AL.addElement(actionListener);
    }
    
    public void addItemListener(final ItemListener itemListener) {
        this.IL.addElement(itemListener);
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        this.IL.removeElement(itemListener);
    }
    
    public Object[] getSelectedObjects() {
        return new String[] { this.getSelectedItem() };
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final ListElement getline = super.TD.getline(mouseEvent.getY());
        if (getline == null) {
            return;
        }
        final Line line = (Line)getline.content();
        if (this.Chosen != null) {
            ((Line)this.Chosen.content()).chosen(false);
        }
        this.Chosen = getline;
        line.chosen(true);
        super.TD.repaint();
        if (mouseEvent.getClickCount() >= 2 || mouseEvent.isControlDown()) {
            if (mouseEvent.isControlDown()) {
                final Enumeration<ItemListener> elements = (Enumeration<ItemListener>)this.IL.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().itemStateChanged(new ItemEvent(this, 0, this.getSelectedItem(), 701));
                }
            }
            final Enumeration<ActionListener> elements2 = (Enumeration<ActionListener>)this.AL.elements();
            while (elements2.hasMoreElements()) {
                elements2.nextElement().actionPerformed(new ActionEvent(this, 0, this.getSelectedItem()));
            }
            return;
        }
        final Enumeration<ItemListener> elements3 = (Enumeration<ItemListener>)this.IL.elements();
        while (elements3.hasMoreElements()) {
            elements3.nextElement().itemStateChanged(new ItemEvent(this, 0, this.getSelectedItem(), 701));
        }
        if (mouseEvent.isPopupTrigger() || (mouseEvent.isMetaDown() && this.PM != null)) {
            this.PM.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void removeAll() {
        this.setText("");
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(150, 200);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(150, 200);
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame();
        frame.setLayout(new BorderLayout());
        final Lister lister = new Lister(true, false);
        frame.add("Center", lister);
        frame.setSize(300, 300);
        frame.setVisible(true);
        for (int i = 0; i < 1000; ++i) {
            lister.add("test");
        }
    }
    
    public void setPopupMenu(final PopupMenu pm) {
        this.add(this.PM = pm);
    }
}
