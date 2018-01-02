import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.Vector;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Choice;
import java.awt.List;
import java.awt.event.ItemListener;
import java.awt.ItemSelectable;

// 
// Decompiled by Procyon v0.5.30
// 

public class WList extends WPanel implements ItemSelectable, ItemListener
{
    public static int LIST;
    public static int CHOICE;
    protected ItemSelectable I;
    protected ItemListener Z;
    protected int C;
    protected boolean B;
    private List list;
    private Choice choice;
    
    public static final WList newList(final int n) {
        try {
            if (WPanel.lightweight) {
                return newWLightList(WList.LIST, n);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        return new WList(WList.LIST, n);
    }
    
    public static final WList newWLightList(final int n, final int n2) {
        return new WLightList(n, n2);
    }
    
    public static final WList newChoice() {
        try {
            if (WPanel.lightweight) {
                return newWLightList(WList.CHOICE, 0);
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        return new WList(WList.CHOICE, 0);
    }
    
    protected WList(final int c) {
        this.B = true;
        this.setLayout(new GridLayout(1, 1));
        this.C = c;
    }
    
    private WList(final int n, final int n2) {
        this(n);
        if (n == WList.LIST) {
            (this.list = new List(n2)).addItemListener(this);
            this.add(this.list);
            this.I = this.list;
        }
        else {
            (this.choice = new Choice()).addItemListener(this);
            this.add(this.choice);
            this.I = this.choice;
        }
    }
    
    public void add(final Vector vector) {
        if (this.C == WList.LIST) {
            for (int i = 0; i < vector.size(); ++i) {
                this.list.add(vector.elementAt(i));
            }
        }
        else {
            this.notImplementedError();
        }
    }
    
    public void add(final String s) {
        if (this.C == WList.LIST) {
            this.notImplementedError();
        }
        this.choice.add(s);
    }
    
    public int getSelectedIndex() {
        if (this.C == WList.LIST) {
            return this.list.getSelectedIndex();
        }
        return this.choice.getSelectedIndex();
    }
    
    public String getSelectedItem() {
        if (this.C == WList.LIST) {
            return this.list.getSelectedItem();
        }
        return this.choice.getSelectedItem();
    }
    
    public void setSelectedIndex(final int n, final boolean b) {
        if (this.C == WList.LIST) {
            this.list.select(n);
        }
        else {
            this.choice.select(n);
        }
    }
    
    public void setSelectedItem(final String s, final boolean b) {
        if (this.C == WList.LIST) {
            this.notImplementedError();
        }
        else {
            this.choice.select(s);
        }
    }
    
    public final void addItemListener(final ItemListener z) {
        if (this.Z != null) {
            throw new Error("Listener already present.");
        }
        this.Z = z;
    }
    
    public void removeAllItems() {
        if (this.C == WList.LIST) {
            this.list.removeAll();
        }
        else {
            this.choice.removeAll();
        }
    }
    
    public Object[] getSelectedObjects() {
        return this.I.getSelectedObjects();
    }
    
    public final void removeItemListener(final ItemListener itemListener) {
        this.Z = null;
    }
    
    private void notImplementedError() {
        throw new Error("Not implemented.");
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (this.Z != null && itemEvent.getStateChange() == 1) {
            if (!this.B) {
                this.B = true;
                return;
            }
            this.Z.itemStateChanged(new ItemEvent(this, 701, this.getSelectedItem(), 1));
        }
    }
    
    public Component getComponent() {
        if (this.C == WList.LIST) {
            return this.list;
        }
        return this.choice;
    }
    
    public void setFont(final Font font) {
        this.getComponent().setFont(font);
        super.setFont(font);
    }
    
    static {
        WList.LIST = 0;
        WList.CHOICE = 1;
    }
}
