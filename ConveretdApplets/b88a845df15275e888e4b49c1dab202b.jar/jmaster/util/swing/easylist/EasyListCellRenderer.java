// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.easylist;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.JList;
import jmaster.util.property.D;
import java.util.ArrayList;
import jmaster.util.log.B;
import java.util.List;
import jmaster.util.swing.GUIHelper;
import jmaster.util.log.A;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;

public class EasyListCellRenderer extends JLabel implements ListCellRenderer
{
    private static final long E = 4706225221855941778L;
    protected A C;
    private GUIHelper A;
    private String[] B;
    private List D;
    
    public EasyListCellRenderer() {
        this.C = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.A = GUIHelper.getInstance();
        this.D = new ArrayList();
    }
    
    public void init(final String s) {
        final D property = this.A.getProperty();
        this.A.injectProperties(this, s);
        this.B = property.H(s + ".componentNames");
        this.D.clear();
        for (int i = 0; i < this.B.length; ++i) {
            final String s2 = this.B[i];
            final String property2 = property.getProperty(s2 + ".class");
            try {
                final EasyListCellRendererComponent easyListCellRendererComponent = (EasyListCellRendererComponent)Class.forName(property2).newInstance();
                this.A.injectProperties(easyListCellRendererComponent, s2);
                easyListCellRendererComponent.setListCellRenderer(this);
                this.D.add(easyListCellRendererComponent);
            }
            catch (Exception ex) {
                this.C.E("Failed to instantiate component, name=" + s2 + ", class=" + property2, ex);
            }
        }
    }
    
    public int getComponentCount2() {
        return this.D.size();
    }
    
    public EasyListCellRendererComponent getComponentAt2(final int n) {
        return this.D.get(n);
    }
    
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean selected, final boolean focused) {
        final EasyList list2 = (EasyList)list;
        if (list != null) {
            this.setSize(list.getFixedCellWidth(), list.getFixedCellHeight());
        }
        for (int i = 0; i < this.D.size(); ++i) {
            final EasyListCellRendererComponent easyListCellRendererComponent = this.D.get(i);
            easyListCellRendererComponent.setListCellRenderer(this);
            easyListCellRendererComponent.setListCellRendererComponent(this);
            easyListCellRendererComponent.setList(list2);
            easyListCellRendererComponent.setValue(value);
            easyListCellRendererComponent.setIndex(index);
            easyListCellRendererComponent.setSelected(selected);
            easyListCellRendererComponent.setFocused(focused);
            easyListCellRendererComponent.prepare();
        }
        return this;
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.getComponentCount2(); ++i) {
            this.getComponentAt2(i).render(this, graphics);
        }
    }
    
    public void handleMouseEvent(final MouseEvent mouseEvent, final EasyList list, final int index, final Rectangle rectangle, final Point point) {
        EasyListCellRendererComponent easyListCellRendererComponent = null;
        Object o = null;
        this.setSize(rectangle.width, rectangle.height);
        final Object value = (index == -1) ? null : list.getModel().getElementAt(index);
        for (int i = 0; i < this.getComponentCount2(); ++i) {
            final EasyListCellRendererComponent componentAt2 = this.getComponentAt2(i);
            if (componentAt2.isVisible()) {
                componentAt2.setList(list);
                componentAt2.setValue(value);
                componentAt2.setIndex(index);
                componentAt2.setSelected(list.getSelectionModel().isSelectedIndex(index));
                componentAt2.setFocused(componentAt2.isSelected() && list.isFocusOwner());
                componentAt2.prepare();
                componentAt2.handleMouseEvent(mouseEvent, list, index, rectangle, point, this);
                if (componentAt2.isActionHover()) {
                    easyListCellRendererComponent = componentAt2;
                    o = value;
                }
            }
        }
        list.setActiveObject(easyListCellRendererComponent, o);
    }
}
