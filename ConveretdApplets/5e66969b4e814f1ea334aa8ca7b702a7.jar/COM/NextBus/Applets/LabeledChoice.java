// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Applets;

import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.ItemListener;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Vector;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Choice;
import java.util.List;
import java.awt.Panel;

public class LabeledChoice extends Panel
{
    private static final long serialVersionUID = 5575498695725641192L;
    private List _itemListeners;
    private String _keyBuffer;
    private Choice _choice;
    private Label _label;
    
    public LabeledChoice(final String s) {
        this(s, 1);
    }
    
    public LabeledChoice(final String s, final int n) {
        super(new FlowLayout(1, 3, 3));
        this._itemListeners = new Vector();
        this._keyBuffer = "";
        this._choice = new LabeledChoice$PreferredSizeChoice(this);
        this.add(this._label = new Label(s, 2));
        this.add(this._choice);
        switch (n) {
            case 0: {
                this.setLayout(new BorderLayout());
                this._label.setAlignment(0);
                this.add(this._label, "North");
                this.add(this._choice, "Center");
                break;
            }
            case 1: {
                this.setLayout(new FlowLayout(1, 3, 3));
                this._label.setAlignment(2);
                this.add(this._label);
                this.add(this._choice);
                break;
            }
        }
        (this = this)._choice.addItemListener(new c(this));
        this._choice.addKeyListener(new b(this));
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this._label.setFont(font);
        this._choice.setFont(font);
    }
    
    public final void a(final ItemListener itemListener) {
        this._itemListeners.add(itemListener);
        this._choice.addItemListener(itemListener);
    }
    
    public final ItemListener[] a() {
        final ItemListener[] array = new ItemListener[this._itemListeners.size()];
        for (int i = 0; i < this._itemListeners.size(); ++i) {
            array[i] = (ItemListener)this._itemListeners.get(i);
        }
        return array;
    }
    
    public final void a(final String s) {
        this._choice.select(s);
    }
    
    public void removeAll() {
        this._choice.removeAll();
    }
    
    public final void b(final String s) {
        this._choice.add(s);
    }
    
    public final String b() {
        return this._choice.getSelectedItem();
    }
    
    public void requestFocus() {
        this._choice.requestFocus();
    }
    
    public final void c(final String s) {
        this._choice.select(s);
    }
}
