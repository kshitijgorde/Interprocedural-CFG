// 
// Decompiled by Procyon v0.5.30
// 

package magnus;

import java.awt.Event;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.TextField;
import java.applet.Applet;

public class EadMolWeight extends Applet
{
    TextField if;
    TextField byte;
    TextField do;
    TextField try;
    TextField new;
    TextArea int;
    Button a;
    Button for;
    
    public void init() {
        System.out.println("Magnus Chemistry");
        System.out.println("================");
        System.out.println("(c) J M Goodman, 1999-2006");
        System.out.println("Cambridge University");
        System.out.println("All rights reserved");
        System.out.println("");
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.ipadx = 0;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 10;
        final Label label = new Label("Molecular Weight Calculation", 1);
        label.setFont(new Font("TimesRoman", 1, 20));
        layout.setConstraints(label, EadUtil.a(gridBagConstraints, 0, 0, 3, 1));
        this.add(label);
        layout.setConstraints(this.a = new Button("Calculate"), EadUtil.a(gridBagConstraints, 0, 2, 1, 1));
        this.add(this.a);
        final Label label2 = new Label("(c) J M Goodman, 2001", 1);
        label2.setFont(new Font("TimesRoman", 2, 12));
        layout.setConstraints(label2, EadUtil.a(gridBagConstraints, 1, 2, 1, 1));
        this.add(label2);
        layout.setConstraints(this.for = new Button("Clear"), EadUtil.a(gridBagConstraints, 2, 2, 1, 1));
        this.add(this.for);
        final Label label3 = new Label("Molecular Formula");
        label3.setFont(new Font("TimesRoman", 0, 16));
        layout.setConstraints(label3, EadUtil.a(gridBagConstraints, 0, 1, 1, 1));
        this.add(label3);
        layout.setConstraints(this.if = new TextField(25), EadUtil.a(gridBagConstraints, 1, 1, 2, 1));
        this.add(this.if);
        final Label label4 = new Label("HRMS weight: ");
        label4.setFont(new Font("TimesRoman", 0, 16));
        layout.setConstraints(label4, EadUtil.a(gridBagConstraints, 0, 3, 1, 1));
        this.add(label4);
        layout.setConstraints(this.byte = new TextField(25), EadUtil.a(gridBagConstraints, 1, 3, 2, 1));
        this.add(this.byte);
        final Label label5 = new Label("Molecular weight: ");
        label5.setFont(new Font("TimesRoman", 0, 16));
        layout.setConstraints(label5, EadUtil.a(gridBagConstraints, 0, 4, 1, 1));
        this.add(label5);
        layout.setConstraints(this.do = new TextField(25), EadUtil.a(gridBagConstraints, 1, 4, 2, 1));
        this.add(this.do);
        final Label label6 = new Label("Element %: ");
        label6.setFont(new Font("TimesRoman", 0, 16));
        layout.setConstraints(label6, EadUtil.a(gridBagConstraints, 0, 5, 1, 1));
        this.add(label6);
        layout.setConstraints(this.try = new TextField(35), EadUtil.a(gridBagConstraints, 1, 5, 2, 1));
        this.add(this.try);
        final Label label7 = new Label("Standardised molecular formula: ");
        label7.setFont(new Font("TimesRoman", 0, 16));
        layout.setConstraints(label7, EadUtil.a(gridBagConstraints, 0, 6, 3, 1));
        this.add(label7);
        layout.setConstraints(this.new = new TextField(25), EadUtil.a(gridBagConstraints, 0, 7, 3, 1));
        this.add(this.new);
        final Label label8 = new Label("Molecular ion isotope pattern");
        label8.setFont(new Font("TimesRoman", 0, 16));
        layout.setConstraints(label8, EadUtil.a(gridBagConstraints, 0, 8, 3, 1));
        this.add(label8);
        layout.setConstraints(this.int = new TextArea(10, 20), EadUtil.a(gridBagConstraints, 0, 9, 3, 2));
        this.add(this.int);
    }
    
    public boolean action(final Event event, final Object o) {
        final String s = (String)o;
        if (event.target instanceof Button) {
            if (s == "Calculate") {
                this.if();
            }
            if (s == "Clear") {
                this.a();
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (n == 10) {
            this.if();
            return true;
        }
        return false;
    }
    
    void if() {
        final EadStruct eadStruct = new EadStruct(this.if.getText());
        if (eadStruct.molWeight("MIon") < 0.0) {
            this.byte.setText("Data are unavailable");
            this.do.setText("Data are unavailable");
        }
        else {
            this.byte.setText(EadUtil.printNumber(eadStruct.molWeight("MIon") - 5.48579896E-4, 8));
            this.do.setText(EadUtil.printNumber(eadStruct.molWeight("Average"), 7));
            eadStruct.setupMolForm();
            this.try.setText(eadStruct.elePercentage());
            this.new.setText(eadStruct.char);
            this.a(eadStruct);
        }
    }
    
    void a(final EadStruct eadStruct) {
        this.int.setText("");
        String string = "Mass      %        fraction\n";
        double n = 0.0;
        final int if1 = eadStruct.if();
        final double[] array = new double[if1];
        final int a = eadStruct.a(array, if1);
        for (int i = 0; i < if1; ++i) {
            if (array[i] > n) {
                n = array[i];
            }
        }
        for (int j = 0; j < if1; ++j) {
            if (array[j] / n > 1.0E-5) {
                string = string + Integer.toString(a + j) + "        " + Integer.toString((int)(100.0 * array[j] / n)) + "           " + Double.toString(array[j]) + "\n";
            }
        }
        this.int.setText(string);
    }
    
    void a() {
        this.if.setText("");
        this.byte.setText("");
        this.do.setText("");
        this.try.setText("");
        this.new.setText("");
    }
}
