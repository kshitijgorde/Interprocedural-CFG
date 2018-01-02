import java.awt.event.ItemEvent;
import javax.swing.ListCellRenderer;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.event.EventListenerList;
import java.awt.Frame;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_ch extends JComboBox implements ItemListener
{
    private static rp_dZ[] a;
    private boolean a;
    private int a;
    private Frame a;
    private EventListenerList a;
    
    public static boolean a(final Color color, final Color color2) {
        for (int i = 0; i < rp_ch.a.length; ++i) {
            if (rp_ch.a[i].a(color, color2)) {
                return true;
            }
        }
        return false;
    }
    
    public rp_ch(final Frame a, final Dimension dimension, final boolean b, final ArrayList list) {
        this.a = new EventListenerList();
        this.a = a;
        this.a = true;
        this.setMaximumRowCount(5);
        this.setEditable(false);
        super.addItemListener(this);
        final rp_a renderer;
        (renderer = new rp_a()).setPreferredSize(new Dimension(dimension.width - 15, dimension.height - 6));
        this.setRenderer(renderer);
        this.addItem(new rp_fW("icO"));
        this.addItem(new rp_fW("icC"));
        if (list != null) {
            for (int i = 0; i < list.size(); ++i) {
                this.addItem(new rp_fW(true, list.get(i)));
            }
        }
        for (int j = 0; j < rp_ch.a.length; ++j) {
            this.addItem(new rp_fW(false, rp_ch.a[j]));
        }
    }
    
    public final Color a() {
        return ((rp_fW)this.getSelectedItem()).a();
    }
    
    public final Color b() {
        return ((rp_fW)this.getSelectedItem()).b();
    }
    
    public final void a(final Color color, final Color color2) {
        int a;
        if ((a = this.a(color, color2)) == -1) {
            this.insertItemAt(new rp_fW(true, new rp_dZ(color, color2)), 2);
            a = 2;
        }
        if (a != -1) {
            this.setSelectedIndex(a);
        }
        this.a = a;
    }
    
    private int a(final Color color, final Color color2) {
        if (color == null || color2 == null) {
            return 0;
        }
        for (int i = 0; i < this.getItemCount(); ++i) {
            final rp_fW rp_fW;
            if (!(rp_fW = this.getItemAt(i)).a() && rp_fW.a.a(color, color2)) {
                return i;
            }
        }
        return -1;
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getStateChange() == 1) {
            if (this.a && this.getSelectedIndex() == 1) {
                final rp_fW rp_fW;
                final rp_bE rp_bE;
                (rp_bE = new rp_bE("bla", (rp_fW = this.getItemAt(this.a)).a() ? Color.BLACK : rp_fW.a(), rp_fW.a() ? Color.WHITE : rp_fW.b())).setVisible(true);
                if (!rp_bE.d()) {
                    this.setSelectedIndex(this.a);
                    return;
                }
                if (this.a(rp_bE.a, rp_bE.b) == -1) {
                    this.insertItemAt(new rp_fW(true, new rp_dZ(rp_bE.a, rp_bE.b)), 2);
                }
                this.setSelectedIndex(2);
            }
            Object[] listenerList;
            for (int i = (listenerList = this.a.getListenerList()).length - 2; i >= 0; i -= 2) {
                if (listenerList[i] == rp_fC.class) {
                    ((rp_fC)listenerList[i + 1]).a(this.a(), this.b());
                }
            }
            this.a = this.getSelectedIndex();
        }
    }
    
    public final void a(final rp_fC rp_fC) {
        this.a.add(rp_fC.class, rp_fC);
    }
    
    static {
        rp_ch.a = new rp_dZ[] { new rp_dZ(new Color(204, 204, 153), new Color(237, 237, 190)), new rp_dZ(new Color(176, 151, 39), new Color(204, 175, 29)), new rp_dZ(new Color(12, 61, 31), new Color(0, 102, 0)), new rp_dZ(new Color(204, 102, 51), new Color(255, 153, 102)), new rp_dZ(new Color(153, 102, 51), new Color(176, 142, 107)), new rp_dZ(new Color(117, 80, 33), new Color(173, 119, 26)), new rp_dZ(new Color(51, 102, 51), new Color(92, 133, 92)), new rp_dZ(new Color(117, 42, 25), new Color(153, 51, 0)), new rp_dZ(new Color(102, 51, 32), new Color(140, 72, 0)), new rp_dZ(new Color(69, 69, 69), new Color(184, 184, 184)), new rp_dZ(new Color(204, 153, 5), new Color(255, 204, 0)), new rp_dZ(new Color(71, 36, 36), new Color(102, 51, 51)), new rp_dZ(new Color(176, 142, 107), new Color(89, 45, 0)), new rp_dZ(new Color(51, 0, 0), new Color(69, 69, 69)), new rp_dZ(new Color(102, 153, 204), new Color(153, 204, 255)), new rp_dZ(new Color(0, 0, 102), new Color(0, 51, 153)) };
    }
}
