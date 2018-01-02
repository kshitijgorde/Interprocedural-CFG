import java.awt.Event;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Component;
import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.CheckboxGroup;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ControlPanelGIF extends Panel
{
    Font f;
    DiceGIF applet;
    GridBagLayout gbl;
    public CheckboxGroup nrolls_cbg;
    public CheckboxGroup ndice_cbg;
    int ndice;
    
    public ControlPanelGIF(final DiceGIF applet) {
        this.f = new Font("TimesRoman", 1, 12);
        this.gbl = new GridBagLayout();
        this.nrolls_cbg = new CheckboxGroup();
        this.ndice_cbg = new CheckboxGroup();
        this.applet = applet;
        this.setBackground(new Color(230, 230, 230));
        this.setLayout(this.gbl);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        final Button button = new Button("Roll'em");
        this.gbl.setConstraints(button, gridBagConstraints);
        this.add(button);
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 1;
        gridBagConstraints2.gridy = 0;
        gridBagConstraints2.gridwidth = 1;
        gridBagConstraints2.gridheight = 1;
        final Button button2 = new Button("Clear");
        this.gbl.setConstraints(button2, gridBagConstraints2);
        this.add(button2);
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.anchor = 17;
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.gridwidth = 1;
        gridBagConstraints3.gridheight = 1;
        final Label label = new Label("# Rolls");
        this.gbl.setConstraints(label, gridBagConstraints3);
        this.add(label);
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.anchor = 17;
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 2;
        gridBagConstraints4.gridwidth = 1;
        gridBagConstraints4.gridheight = 1;
        final Checkbox checkbox = new Checkbox("1", this.nrolls_cbg, true);
        this.gbl.setConstraints(checkbox, gridBagConstraints4);
        this.add(checkbox);
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.anchor = 17;
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 3;
        gridBagConstraints5.gridwidth = 1;
        gridBagConstraints5.gridheight = 1;
        final Checkbox checkbox2 = new Checkbox("10", this.nrolls_cbg, false);
        this.gbl.setConstraints(checkbox2, gridBagConstraints5);
        this.add(checkbox2);
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.anchor = 17;
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 4;
        gridBagConstraints6.gridwidth = 1;
        gridBagConstraints6.gridheight = 1;
        final Checkbox checkbox3 = new Checkbox("20", this.nrolls_cbg, false);
        this.gbl.setConstraints(checkbox3, gridBagConstraints6);
        this.add(checkbox3);
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.anchor = 17;
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 5;
        gridBagConstraints7.gridwidth = 1;
        gridBagConstraints7.gridheight = 1;
        final Checkbox checkbox4 = new Checkbox("100", this.nrolls_cbg, false);
        this.gbl.setConstraints(checkbox4, gridBagConstraints7);
        this.add(checkbox4);
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        gridBagConstraints8.gridx = 1;
        gridBagConstraints8.gridy = 1;
        gridBagConstraints8.gridwidth = 1;
        gridBagConstraints8.gridheight = 1;
        final Label label2 = new Label("# Dice");
        this.gbl.setConstraints(label2, gridBagConstraints8);
        this.add(label2);
        final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        gridBagConstraints9.gridx = 1;
        gridBagConstraints9.gridy = 2;
        gridBagConstraints9.gridwidth = 1;
        gridBagConstraints9.gridheight = 1;
        final Checkbox checkbox5 = new Checkbox("1", this.ndice_cbg, true);
        this.gbl.setConstraints(checkbox5, gridBagConstraints9);
        this.add(checkbox5);
        final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
        gridBagConstraints10.gridx = 1;
        gridBagConstraints10.gridy = 3;
        gridBagConstraints10.gridwidth = 1;
        gridBagConstraints10.gridheight = 1;
        final Checkbox checkbox6 = new Checkbox("2", this.ndice_cbg, false);
        this.gbl.setConstraints(checkbox6, gridBagConstraints10);
        this.add(checkbox6);
        final GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        gridBagConstraints11.gridx = 1;
        gridBagConstraints11.gridy = 4;
        gridBagConstraints11.gridwidth = 1;
        gridBagConstraints11.gridheight = 1;
        final Checkbox checkbox7 = new Checkbox("6", this.ndice_cbg, false);
        this.gbl.setConstraints(checkbox7, gridBagConstraints11);
        this.add(checkbox7);
        final GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
        gridBagConstraints12.gridx = 1;
        gridBagConstraints12.gridy = 5;
        gridBagConstraints12.gridwidth = 1;
        gridBagConstraints12.gridheight = 1;
        final Checkbox checkbox8 = new Checkbox("9", this.ndice_cbg, false);
        this.gbl.setConstraints(checkbox8, gridBagConstraints12);
        this.add(checkbox8);
    }
    
    @Override
    public boolean action(final Event event, final Object o) {
        final int intValue = Integer.valueOf(this.nrolls_cbg.getCurrent().getLabel());
        if (event.target instanceof Button) {
            if (((Button)event.target).getLabel().equals("Clear")) {
                this.applet.clear();
            }
            else {
                this.applet.rollem(intValue);
            }
        }
        else if (event.target instanceof Checkbox && ((Checkbox)event.target).getCheckboxGroup().equals(this.ndice_cbg)) {
            this.applet.clear();
            this.applet.ndice = Integer.valueOf(this.ndice_cbg.getCurrent().getLabel());
        }
        return true;
    }
    
    public void update() {
        this.repaint();
    }
}
