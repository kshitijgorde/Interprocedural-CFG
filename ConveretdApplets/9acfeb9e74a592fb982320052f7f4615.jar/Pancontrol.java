import java.awt.event.AdjustmentEvent;
import java.awt.event.ItemEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Scrollbar;
import java.awt.Label;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Pancontrol extends Panel implements ItemListener, AdjustmentListener
{
    Difus padre;
    Label L1;
    Scrollbar sbar;
    Checkbox cbonof;
    Choice lista;
    int[] np;
    String[] listem;
    int codesb1;
    int codesb2;
    int codesb3;
    
    public Pancontrol(final Difus padre, final Color background, final int n, final int n2) {
        this.np = new int[] { 50, 100, 200, 400 };
        this.listem = new String[] { "50 particles", "100 particles", "200 particles", "400 particles" };
        this.padre = padre;
        this.L1 = new Label("Speed");
        this.sbar = new Scrollbar(0, 1, 1, 1, 5);
        (this.cbonof = new Checkbox("Trace ", true)).addItemListener(this);
        this.lista = new Choice();
        this.codesb1 = this.sbar.hashCode();
        this.codesb2 = this.cbonof.hashCode();
        this.codesb3 = this.lista.hashCode();
        for (int i = 0; i < 4; ++i) {
            this.lista.addItem(this.listem[i]);
        }
        this.setLayout(null);
        this.setBackground(background);
        this.sbar.addAdjustmentListener(this);
        this.lista.addItemListener(this);
        this.cbonof.setBounds(n / 16, 0, 8 * n / 32, n2);
        this.lista.setBounds(13 * n / 32, 0, 7 * n / 32, n2);
        this.L1.setBounds(21 * n / 32, 0, 2 * n / 16, n2);
        this.sbar.setBounds(25 * n / 32, 0, 7 * n / 32, n2);
        this.add(this.cbonof);
        this.add(this.lista);
        this.add(this.L1);
        this.add(this.sbar);
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource().hashCode() == this.codesb2) {
            this.padre.btrace = this.cbonof.getState();
            return;
        }
        if (itemEvent.getSource().hashCode() == this.codesb3) {
            this.padre.cambionumpart(this.np[this.lista.getSelectedIndex()]);
        }
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getSource().hashCode() == this.codesb1) {
            this.padre.tiempo_pausa = 200 / adjustmentEvent.getValue();
        }
    }
}
