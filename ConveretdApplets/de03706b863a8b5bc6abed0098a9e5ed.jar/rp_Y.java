import java.awt.event.ComponentEvent;
import javax.swing.JLabel;
import java.awt.event.ItemEvent;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.event.EventListenerList;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.event.ItemListener;
import java.awt.event.ComponentListener;
import java.awt.event.ActionListener;
import java.awt.ItemSelectable;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_Y extends JPanel implements ItemSelectable, ActionListener, ComponentListener, ItemListener
{
    JComponent a;
    JButton a;
    private JComponent b;
    private JComponent c;
    private JPopupMenu a;
    private EventListenerList a;
    
    public rp_Y(final JComponent b, final JComponent c) {
        this.a = null;
        this.a = null;
        this.a = null;
        this.a = new EventListenerList();
        this.b = b;
        this.c = c;
        if (c instanceof ItemSelectable) {
            ((ItemSelectable)c).addItemListener(this);
        }
        c.addComponentListener(this);
        this.setLayout(new BorderLayout());
        (this.a = new JPopupMenu()).setBorderPainted(false);
        this.a.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.a.add(b);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (this.a.isVisible()) {
            this.a.setVisible(false);
            return;
        }
        final Dimension size = this.a.getSize();
        final Dimension preferredSize = this.b.getPreferredSize();
        if (!preferredSize.equals(this.a.getSize())) {
            this.a.setPopupSize(size.width, preferredSize.height);
        }
        this.a.show(this, 0, this.a.getSize().height);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            this.a.setVisible(false);
            final String string = itemEvent.getItem().toString();
            if (this.a != null) {
                if (this.a instanceof JLabel) {
                    ((JLabel)this.a).setText(string);
                }
                if (this.a instanceof JButton) {
                    ((JButton)this.a).setText(string);
                }
            }
            final int id = itemEvent.getID();
            final Object item = itemEvent.getItem();
            final int stateChange = itemEvent.getStateChange();
            final Object o = item;
            final int n = id;
            final Object[] listenerList = this.a.getListenerList();
            ItemEvent itemEvent2 = null;
            for (int i = listenerList.length - 2; i >= 0; i -= 2) {
                if (listenerList[i] == ItemListener.class) {
                    if (itemEvent2 == null) {
                        itemEvent2 = new ItemEvent(this, n, o, stateChange);
                    }
                    ((ItemListener)listenerList[i + 1]).itemStateChanged(itemEvent2);
                }
            }
        }
    }
    
    public void addItemListener(final ItemListener itemListener) {
        this.a.add(ItemListener.class, itemListener);
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        this.a.remove(ItemListener.class, itemListener);
    }
    
    public Object[] getSelectedObjects() {
        Object o = null;
        if (this.c != null && this.c instanceof ItemSelectable) {
            o = ((ItemSelectable)this.c).getSelectedObjects()[0];
        }
        if (o == null) {
            return new Object[0];
        }
        return new Object[] { o };
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        if (this.a != null && this.a.isVisible()) {
            final Dimension preferredSize = this.b.getPreferredSize();
            preferredSize.width = this.a.getSize().width;
            if (!preferredSize.equals(this.a.getSize())) {
                this.a.setPopupSize(preferredSize);
            }
        }
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
}
