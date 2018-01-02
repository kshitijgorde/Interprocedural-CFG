// 
// Decompiled by Procyon v0.5.30
// 

package courseware.util;

import courseware.jfcdep.MatchedButton;
import java.awt.Insets;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import courseware.jfcdep.ClearLabel;
import java.util.Vector;
import java.awt.LayoutManager2;

public class ModuleLayout implements LayoutManager2
{
    public static int BUTTONLIST;
    public static int BIG;
    public static String BIG_STRING;
    public static String BUTTONLIST_STRING;
    public static String SKIP_CONSTRAINT;
    private int numSlices;
    private Vector slice;
    private ClearLabel nullLabel;
    private Dimension prefsize;
    private int sliceSpace;
    private int insetX;
    private int insetY;
    public int minPad;
    private boolean vertButtons;
    private int allheight;
    
    public ModuleLayout(final int numSlices) {
        this.prefsize = new Dimension(0, 0);
        this.minPad = 6;
        this.vertButtons = false;
        this.allheight = 0;
        this.numSlices = numSlices;
        this.slice = new Vector(numSlices, 1);
        this.nullLabel = new ClearLabel();
        for (int i = 0; i < this.numSlices; ++i) {
            final Vector<ClearLabel> vector = new Vector<ClearLabel>(5, 5);
            this.slice.addElement(vector);
            vector.addElement(this.nullLabel);
        }
    }
    
    public int getLastHeight() {
        return this.insetY + this.allheight;
    }
    
    public ModuleLayout() {
        this(1);
    }
    
    public float getLayoutAlignmentX(final Container container) {
        return 0.5f;
    }
    
    public float getLayoutAlignmentY(final Container container) {
        return 0.5f;
    }
    
    public void invalidateLayout(final Container container) {
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
        if (o == null || o instanceof String) {
            this.addLayoutComponent((String)o, component);
            return;
        }
        throw new IllegalArgumentException("cannot add to layout: constraint must be a string (or null)");
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        if (s.equals(ModuleLayout.BIG_STRING)) {
            this.setConstraint(component, 0, ModuleLayout.BIG);
        }
        else if (s.equals(ModuleLayout.BUTTONLIST_STRING)) {
            this.setConstraint(component, 0, ModuleLayout.BUTTONLIST);
        }
    }
    
    public void setNumSlices(final int numSlices) {
        if (numSlices == this.numSlices) {
            return;
        }
        for (int i = this.numSlices; i < numSlices; ++i) {
            final Vector<ClearLabel> vector = new Vector<ClearLabel>(5, 5);
            vector.addElement(this.nullLabel);
            this.slice.addElement(vector);
        }
        this.numSlices = numSlices;
    }
    
    public int getInsetX() {
        return this.insetX;
    }
    
    public int getInsetY() {
        return this.insetY;
    }
    
    public void setInsetX(final int insetX) {
        this.insetX = insetX;
    }
    
    public void setInsetY(final int insetY) {
        this.insetY = insetY;
    }
    
    public void setOptions(final int sliceSpace, final int insetX, final int insetY, final boolean vertButtons) {
        this.sliceSpace = sliceSpace;
        this.insetX = insetX;
        this.insetY = insetY;
        this.vertButtons = vertButtons;
    }
    
    public void removeLayoutComponent(final Component component) {
        final Enumeration<Vector<ClearLabel>> elements = this.slice.elements();
        while (elements.hasMoreElements()) {
            final Vector<ClearLabel> vector = elements.nextElement();
            final int index = vector.indexOf(component);
            if (index > 0) {
                vector.removeElementAt(index);
            }
            else {
                if (index != 0) {
                    continue;
                }
                vector.setElementAt(this.nullLabel, index);
            }
        }
    }
    
    public void makeFirstButton(final Component component) {
        final Enumeration<Vector<Component>> elements = this.slice.elements();
        while (elements.hasMoreElements()) {
            final Vector<Component> vector = elements.nextElement();
            final int index = vector.indexOf(component);
            if (index > 1) {
                vector.removeElementAt(index);
                vector.insertElementAt(component, 1);
            }
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.prefsize = new Dimension(container.getSize());
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return this.prefsize = new Dimension(container.getSize());
    }
    
    public void setConstraint(final Component component, final int n, final int n2) {
        final Vector<Component> vector = this.slice.elementAt(n);
        if (n2 == ModuleLayout.BIG) {
            vector.setElementAt(component, 0);
        }
        else if (!vector.contains(component)) {
            vector.addElement(component);
        }
    }
    
    public void setConstraint(final Component component, final int n, final int n2, final int n3) {
        final Vector<Component> vector = this.slice.elementAt(n);
        if (n2 == ModuleLayout.BIG) {
            vector.setElementAt(component, 0);
        }
        else if (vector.size() > n3) {
            vector.setElementAt(component, n3);
        }
        else {
            vector.addElement(component);
        }
    }
    
    public void layoutContainer(final Container container) {
        if (this.vertButtons) {
            this.layoutVertButtons(container);
        }
        else {
            this.layoutHorizButtons(container);
        }
    }
    
    Insets getInsets(final Container container) {
        final Insets insets = container.getInsets();
        if (insets.right < this.insetX) {
            insets.right = this.insetX;
        }
        if (insets.left < this.insetX) {
            insets.left = this.insetX;
        }
        if (insets.top < this.insetY) {
            insets.top = this.insetY;
        }
        if (insets.bottom < this.insetY) {
            insets.bottom = this.insetY;
        }
        if (insets.bottom < this.minPad) {
            insets.bottom = this.minPad;
        }
        if (insets.left < this.minPad) {
            insets.left = this.minPad;
        }
        return insets;
    }
    
    private void layoutVertButtons(final Container container) {
        this.prefsize = new Dimension(container.getSize());
        final Insets insets = this.getInsets(container);
        final Dimension dimension = new Dimension(this.prefsize.width - insets.left - insets.right, this.prefsize.height - insets.top - insets.bottom);
        if (this.numSlices > 1) {
            final Dimension dimension2 = dimension;
            dimension2.height /= this.numSlices;
        }
        final Dimension dimension3 = new Dimension(0, 0);
        for (int i = 0; i < this.numSlices; ++i) {
            final Vector<Component> vector = this.slice.elementAt(i);
            for (int j = 1; j < vector.size(); ++j) {
                final Dimension preferredSize = vector.elementAt(j).getPreferredSize();
                if (preferredSize.width > dimension3.width) {
                    dimension3.width = preferredSize.width;
                }
                if (preferredSize.height > dimension3.height) {
                    dimension3.height = preferredSize.height;
                }
            }
        }
        int top;
        int n = top = insets.top;
        for (int k = 0; k < this.numSlices; ++k) {
            final Vector<Component> vector2 = this.slice.elementAt(k);
            final Component component = vector2.elementAt(0);
            component.setVisible(false);
            component.setBounds(insets.left + dimension3.width + 2 * this.insetY, n, dimension.width - dimension3.width - 2 * this.insetY, dimension.height);
            if (component instanceof MatchedButton) {
                ((MatchedButton)component).updateColor();
            }
            component.setVisible(true);
            for (int l = 1; l < vector2.size(); ++l) {
                final Component component2 = vector2.elementAt(l);
                component2.setVisible(false);
                component2.setBounds(insets.left, top, dimension3.width, dimension3.height);
                if (component2 instanceof MatchedButton) {
                    ((MatchedButton)component2).updateColor();
                }
                component2.setVisible(true);
                top += dimension3.height + this.insetY;
                if (top > n + dimension.height) {
                    break;
                }
            }
            n = (top = n + dimension.height);
        }
    }
    
    private void layoutHorizButtons(final Container container) {
        this.allheight = 0;
        this.prefsize = new Dimension(container.getSize());
        final Insets insets = this.getInsets(container);
        final Dimension dimension = new Dimension(this.prefsize.width - insets.left - insets.right, this.prefsize.height - insets.top - insets.bottom);
        for (int i = 0; i < this.numSlices; ++i) {
            final Vector<Component> vector = this.slice.elementAt(i);
            for (int j = 1; j < vector.size(); ++j) {
                final Dimension preferredSize = vector.elementAt(j).getPreferredSize();
                if (preferredSize.height > this.allheight) {
                    this.allheight = preferredSize.height;
                }
            }
        }
        for (int k = 0; k < this.numSlices; ++k) {
            if (((Vector)this.slice.elementAt(k)).size() > 1) {
                final Dimension dimension2 = dimension;
                dimension2.height -= this.allheight;
            }
            else {
                final Dimension dimension3 = dimension;
                dimension3.height -= this.allheight / 2;
            }
            final Dimension dimension4 = dimension;
            dimension4.height -= this.minPad * 2;
        }
        if (this.numSlices > 1) {
            final Dimension dimension5 = dimension;
            dimension5.height /= this.numSlices;
        }
        int top = insets.top;
        int n = insets.left;
        for (int l = 0; l < this.numSlices; ++l) {
            final Vector<Component> vector2 = this.slice.elementAt(l);
            for (int n2 = 1; n2 < vector2.size(); ++n2) {
                final Component component = vector2.elementAt(n2);
                component.setVisible(false);
                final Dimension preferredSize2 = component.getPreferredSize();
                component.setBounds(n, top, preferredSize2.width, this.allheight);
                if (component instanceof MatchedButton) {
                    ((MatchedButton)component).updateColor();
                }
                component.setVisible(true);
                n += preferredSize2.width + this.insetX;
                if (n > insets.left + dimension.width) {
                    break;
                }
            }
            final Component component2 = vector2.elementAt(0);
            component2.setVisible(false);
            final int n3 = top + (this.minPad + ((vector2.size() == 1) ? (this.allheight / 2) : this.allheight));
            component2.setBounds(insets.left, n3, dimension.width, dimension.height);
            if (component2 instanceof MatchedButton) {
                ((MatchedButton)component2).updateColor();
            }
            component2.setVisible(true);
            top = n3 + dimension.height;
            n = insets.left;
        }
    }
    
    static {
        ModuleLayout.BUTTONLIST = 0;
        ModuleLayout.BIG = 1;
        ModuleLayout.BIG_STRING = "BIG";
        ModuleLayout.BUTTONLIST_STRING = "BUTTONLIST";
        ModuleLayout.SKIP_CONSTRAINT = "SKIP_CONSTRAINT";
    }
}
