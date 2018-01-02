import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.AdjustmentEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.util.ArrayList;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import java.awt.event.AdjustmentListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class NumberSpinner extends JPanel implements AdjustmentListener
{
    private JTextField textBox;
    private JScrollBar scrollBar;
    private String tail;
    ArrayList changeListeners;
    
    public NumberSpinner() {
        this.changeListeners = new ArrayList();
        this.init(1, 10, "");
    }
    
    public NumberSpinner(final int n, final int n2, final String s) {
        this.changeListeners = new ArrayList();
        this.init(n, n2, s);
    }
    
    private void init(final int n, final int n2, final String tail) {
        this.setLayout(new BoxLayout(this, 0));
        this.scrollBar = new JScrollBar(1, n, 0, n, n2);
        this.tail = tail;
        (this.textBox = new JTextField(n + this.tail)).setEditable(false);
        this.textBox.setFocusable(false);
        this.scrollBar.setBlockIncrement(1);
        this.add(this.textBox);
        this.add(this.scrollBar);
        this.scrollBar.setPreferredSize(new Dimension(this.scrollBar.getPreferredSize().width, this.textBox.getPreferredSize().height));
        this.scrollBar.addAdjustmentListener(this);
        this.setWidth();
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.update();
        this.fireChangePerformed();
    }
    
    public void setMinimum(final int minimum) {
        this.scrollBar.setMinimum(minimum);
        this.setWidth();
    }
    
    public void setMaximum(final int maximum) {
        this.scrollBar.setMaximum(maximum);
        this.setWidth();
    }
    
    public void setTail(final String tail) {
        this.tail = tail;
        this.setWidth();
        this.update();
    }
    
    public void setValue(final int value) {
        this.scrollBar.setValue(value);
        this.update();
    }
    
    public int getValue() {
        return this.scrollBar.getValue();
    }
    
    private void update() {
        this.textBox.setText(this.scrollBar.getValue() + this.tail);
    }
    
    private void setWidth() {
        int length = (this.scrollBar.getMinimum() + this.tail).length();
        final int length2 = (this.scrollBar.getMaximum() + this.tail).length();
        if (length < length2) {
            length = length2;
        }
        ++length;
        if (length != this.textBox.getColumns()) {
            this.textBox.setColumns(length);
            this.revalidate();
        }
    }
    
    public void setEnabled(final boolean enabled) {
        this.scrollBar.setEnabled(enabled);
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        if (!this.changeListeners.contains(changeListener)) {
            this.changeListeners.add(changeListener);
        }
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.changeListeners.remove(changeListener);
    }
    
    public ChangeListener[] getChangeListeners() {
        return (ChangeListener[])this.changeListeners.toArray();
    }
    
    protected void fireChangePerformed() {
        int i = this.changeListeners.size();
        if (i > 0) {
            final ChangeEvent changeEvent = new ChangeEvent(this);
            --i;
            while (i >= 0) {
                this.changeListeners.get(i).stateChanged(changeEvent);
                --i;
            }
        }
    }
}
