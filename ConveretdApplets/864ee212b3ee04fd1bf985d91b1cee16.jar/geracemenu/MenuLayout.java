// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Container;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Dimension;
import geracemenu.util.VArray;
import java.awt.LayoutManager;

public class MenuLayout implements LayoutManager
{
    public static final int FLAT = 1;
    public static final int DROPDOWN = 2;
    private static /* synthetic */ Class class$Ljava$awt$Component;
    private VArray components;
    private VRect background;
    private Dimension size;
    private int id;
    private boolean bkgProcessed;
    public Insets insets;
    
    public void addLayoutComponent(final String s, final Component component) {
        if (component instanceof VRect) {
            this.background = (VRect)component;
        }
        else {
            this.components.append(component);
        }
        if (this.background != null) {
            component.setBackground(this.background.getForeground());
        }
    }
    
    public void removeLayoutComponent(final Component component) {
        final int index = this.components.indexOf(component);
        if (index != -1) {
            this.components.remove(index, 1);
        }
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        return this.minimumLayoutSize(container);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        this.size.setSize(0, 0);
        Dimension dimension = null;
        final Insets insets = container.insets();
        switch (this.id) {
            case 1: {
                for (int i = 0; i < this.components.size(); ++i) {
                    final Dimension preferredSize = ((Component)this.components.get(i)).getPreferredSize();
                    final Dimension size = this.size;
                    size.width += preferredSize.width;
                    this.size.height = Math.max(this.size.height, this.insets.top + preferredSize.height + this.insets.bottom);
                }
                dimension = new Dimension(insets.left + this.insets.left + this.size.width + this.insets.right + insets.right, insets.top + this.size.height + insets.bottom);
                break;
            }
            case 2: {
                for (int j = 0; j < this.components.size(); ++j) {
                    final Dimension preferredSize2 = ((Component)this.components.get(j)).getPreferredSize();
                    final Dimension size2 = this.size;
                    size2.height += preferredSize2.height;
                    this.size.width = Math.max(this.size.width, this.insets.left + preferredSize2.width + this.insets.right);
                }
                dimension = new Dimension(insets.left + this.size.width + insets.right, insets.top + this.insets.top + this.size.height + this.insets.bottom + insets.bottom);
                break;
            }
        }
        return dimension;
    }
    
    public void layoutContainer(final Container container) {
        this.preferredLayoutSize(container);
        container.insets();
        final Dimension size = container.getSize();
        if (!this.bkgProcessed) {
            this.bkgProcessed = true;
            this.processComponents();
        }
        if (this.background != null) {
            this.background.setLocation(0, 0);
            this.background.setSize(size);
        }
        switch (this.id) {
            case 1: {
                Dimension preferredSize;
                for (int i = 0, n = (size.width - this.size.width) / 2; i < this.components.size(); ++i, n += preferredSize.width) {
                    final Component component = (Component)this.components.get(i);
                    preferredSize = component.getPreferredSize();
                    component.setSize(preferredSize);
                    component.setLocation(n, this.insets.top);
                }
                break;
            }
            case 2: {
                Dimension preferredSize2;
                for (int j = 0, top = this.insets.top; j < this.components.size(); ++j, top += preferredSize2.height) {
                    final Component component2 = (Component)this.components.get(j);
                    preferredSize2 = component2.getPreferredSize();
                    component2.setSize(Math.max(this.size.width - (this.insets.right + this.insets.left), preferredSize2.width), preferredSize2.height);
                    component2.setLocation(this.insets.left, top);
                }
                break;
            }
        }
    }
    
    private void processComponents() {
        if (this.background == null) {
            return;
        }
        for (int i = 0; i < this.components.size(); ++i) {
            ((Component)this.components.get(i)).setBackground(this.background.getForeground());
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public MenuLayout() {
        this(1, new Insets(0, 0, 0, 0));
    }
    
    public MenuLayout(final int n) {
        this(n, new Insets(0, 0, 0, 0));
    }
    
    public MenuLayout(final Insets insets) {
        this(1, insets);
    }
    
    public MenuLayout(final int id, final Insets insets) {
        this.components = new VArray((MenuLayout.class$Ljava$awt$Component != null) ? MenuLayout.class$Ljava$awt$Component : (MenuLayout.class$Ljava$awt$Component = class$("java.awt.Component")));
        this.background = null;
        this.bkgProcessed = false;
        this.size = new Dimension(0, 0);
        this.id = id;
        this.insets = insets;
    }
}
