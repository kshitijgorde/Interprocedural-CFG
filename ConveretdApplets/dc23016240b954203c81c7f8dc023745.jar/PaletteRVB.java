import java.awt.Container;
import java.awt.Button;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import util102.gui.BorderPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class PaletteRVB extends Frame
{
    TextField Rouge;
    TextField Vert;
    TextField Bleu;
    Panel panel;
    public Color couleur;
    public boolean ouverte;
    JavaDraw parentJavaDraw;
    
    PaletteRVB(final String s, final JavaDraw parentJavaDraw) {
        super(s);
        this.couleur = new Color(100, 100, 100);
        this.ouverte = false;
        this.parentJavaDraw = parentJavaDraw;
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(0, 0));
        final BorderPanel borderPanel = new BorderPanel(5, 5, 5, 5, 3);
        ((Container)borderPanel).setLayout(new BorderLayout(5, 5));
        final BorderPanel borderPanel2 = new BorderPanel(5, 5, 5, 5, 7);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        ((Container)borderPanel2).setLayout(layout);
        (this.panel = new Panel()).setBackground(this.couleur);
        gridBagConstraints.ipadx = 40;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.panel, gridBagConstraints);
        ((Container)borderPanel2).add(this.panel);
        ((Container)borderPanel).add("West", (Component)borderPanel2);
        final BorderPanel borderPanel3 = new BorderPanel(10, 10, 10, 10, 7);
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        ((Container)borderPanel3).setLayout(layout2);
        final Label label = new Label("Red :", 0);
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridwidth = -1;
        layout2.setConstraints(label, gridBagConstraints2);
        ((Container)borderPanel3).add(label);
        this.Rouge = new TextField("100", 3);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridwidth = 0;
        layout2.setConstraints(this.Rouge, gridBagConstraints2);
        ((Container)borderPanel3).add(this.Rouge);
        final Label label2 = new Label("Green :", 0);
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridwidth = -1;
        layout2.setConstraints(label2, gridBagConstraints2);
        ((Container)borderPanel3).add(label2);
        this.Vert = new TextField("100", 3);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridwidth = 0;
        layout2.setConstraints(this.Vert, gridBagConstraints2);
        ((Container)borderPanel3).add(this.Vert);
        final Label label3 = new Label("Blue :", 0);
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridwidth = -1;
        layout2.setConstraints(label3, gridBagConstraints2);
        ((Container)borderPanel3).add(label3);
        this.Bleu = new TextField("100", 3);
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.weighty = 0.0;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints2.anchor = 17;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.gridwidth = 0;
        layout2.setConstraints(this.Bleu, gridBagConstraints2);
        ((Container)borderPanel3).add(this.Bleu);
        ((Container)borderPanel).add("Center", (Component)borderPanel3);
        this.add("Center", (Component)borderPanel);
        this.validate();
        this.pack();
    }
    
    public final void verifChamps() {
        int intValue;
        try {
            intValue = new Integer(this.Rouge.getText());
        }
        catch (Exception ex) {
            intValue = 100;
            this.Rouge.setText("100");
        }
        int intValue2;
        try {
            intValue2 = new Integer(this.Vert.getText());
        }
        catch (Exception ex2) {
            intValue2 = 100;
            this.Vert.setText("100");
        }
        int intValue3;
        try {
            intValue3 = new Integer(this.Bleu.getText());
        }
        catch (Exception ex3) {
            intValue3 = 100;
            this.Bleu.setText("100");
        }
        if (intValue > 255) {
            intValue = 100;
            this.Rouge.setText("100");
        }
        if (intValue2 > 255) {
            intValue2 = 100;
            this.Vert.setText("100");
        }
        if (intValue3 > 255) {
            intValue3 = 100;
            this.Bleu.setText("100");
        }
        this.couleur = new Color(intValue, intValue2, intValue3);
    }
    
    public final void miseAJour() {
        this.verifChamps();
        this.panel.setBackground(this.couleur);
        this.panel.repaint();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 9) {
                    this.verifChamps();
                    this.panel.setBackground(this.couleur);
                    this.panel.repaint();
                    this.parentJavaDraw.miseAJourCouleur();
                    this.parentJavaDraw.changeCouleur();
                    this.parentJavaDraw.dessinePlans();
                    this.parentJavaDraw.selectObjet();
                    this.parentJavaDraw.repaint();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.verifChamps();
            this.panel.setBackground(this.couleur);
            this.panel.repaint();
            this.parentJavaDraw.miseAJourCouleur();
            this.parentJavaDraw.changeCouleur();
            this.parentJavaDraw.dessinePlans();
            this.parentJavaDraw.selectObjet();
            this.parentJavaDraw.repaint();
            this.ouverte = false;
            this.hide();
            return true;
        }
        if (event.target instanceof TextField) {
            this.verifChamps();
            this.panel.setBackground(this.couleur);
            this.panel.repaint();
            this.parentJavaDraw.miseAJourCouleur();
            this.parentJavaDraw.changeCouleur();
            this.parentJavaDraw.dessinePlans();
            this.parentJavaDraw.selectObjet();
            this.parentJavaDraw.repaint();
            return true;
        }
        return false;
    }
}
