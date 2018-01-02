import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class ColorDialogTot extends ColorDialog implements ItemListener, ActionListener
{
    Color[] addButton;
    Color[] addItem;
    int addItemListener;
    Choice adjustmentValueChanged;
    static String[] clone;
    
    public ColorDialogTot(final Frame frame) {
        super(frame);
        this.adjustmentValueChanged = new Choice();
        for (int i = 0; i < ColorDialogTot.clone.length; ++i) {
            this.adjustmentValueChanged.addItem(ColorDialogTot.clone[i]);
        }
        this.adjustmentValueChanged.addItemListener(this);
        this.adjustmentValueChanged.select("Numbers");
        this.addItemListener = 0;
        this.add("Applies to", this.adjustmentValueChanged);
        this.addButton("Default");
    }
    
    public final void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        super.adjustmentValueChanged(adjustmentEvent);
        this.addButton[CommonOmegaPanel.paramColorNumbers[this.addItemListener]] = super.color;
        super.I.repaint();
    }
    
    public final void itemStateChanged(final ItemEvent itemEvent) {
        if (itemEvent.getSource() == this.adjustmentValueChanged) {
            this.setColor(this.addItemListener = this.adjustmentValueChanged.getSelectedIndex());
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Ok")) {
            this.setVisible(false);
        }
        else if (actionCommand.equals("Cancel")) {
            if (super.I != null) {
                super.I.colors = this.addItem;
            }
            this.setColor(this.addItemListener);
            super.I.repaint();
            this.setVisible(false);
        }
        else if (actionCommand.equals("Default")) {
            super.I.colors = AbstractBox.default_colors.clone();
            this.addButton = super.I.colors;
            this.setColor(this.addItemListener);
            super.I.repaint();
        }
    }
    
    private final void setColor(final int n) {
        this.setColor(this.addButton[CommonOmegaPanel.paramColorNumbers[n]]);
    }
    
    public final void mostra(final Formula formula) {
        this.addButton = formula.colors;
        this.addItem = this.addButton.clone();
        this.setColor(this.adjustmentValueChanged.getSelectedIndex());
        super.mostra(formula);
    }
    
    static {
        ColorDialogTot.clone = new String[] { "Numbers", "Identifiers", "Symbols", "Error", "Warning", "Calculating", "Empty box", "Arrow", "Background", "Selection", "Comment" };
    }
}
