import java.awt.Container;
import java.awt.Event;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import util102.gui.BorderPanel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public final class PaletteText extends Frame
{
    Choice policeText;
    Choice styleText;
    TextField tailleText;
    TextField texte;
    int taille;
    boolean ouverte;
    
    PaletteText(final String s) {
        super(s);
        this.ouverte = false;
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout(0, 0));
        final BorderPanel borderPanel = new BorderPanel(10, 10, 10, 10, 3);
        ((Container)borderPanel).setLayout(new BorderLayout(5, 5));
        final BorderPanel borderPanel2 = new BorderPanel(7, 5, 5, 5, 7);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        ((Container)borderPanel2).setLayout(layout);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.weightx = 0.2;
        final Label label = new Label("Font :");
        layout.setConstraints(label, gridBagConstraints);
        ((Container)borderPanel2).add(label);
        (this.policeText = new Choice()).addItem("Courier");
        this.policeText.addItem("Helvetica");
        this.policeText.addItem("TimesRoman");
        this.policeText.addItem("Symbol");
        this.policeText.addItem("Dialog");
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.policeText, gridBagConstraints);
        ((Container)borderPanel2).add(this.policeText);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(0, 0, 5, 0);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.weightx = 0.2;
        final Label label2 = new Label("Style :");
        layout.setConstraints(label2, gridBagConstraints);
        ((Container)borderPanel2).add(label2);
        (this.styleText = new Choice()).addItem("Normal");
        this.styleText.addItem("Bold");
        this.styleText.addItem("Italic");
        this.styleText.addItem("Bold-italic");
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.styleText, gridBagConstraints);
        ((Container)borderPanel2).add(this.styleText);
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 0.8;
        gridBagConstraints.weightx = 0.2;
        final Label label3 = new Label("Size :");
        layout.setConstraints(label3, gridBagConstraints);
        ((Container)borderPanel2).add(label3);
        (this.tailleText = new TextField("10", 3)).setBackground(Color.white);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.tailleText, gridBagConstraints);
        ((Container)borderPanel2).add(this.tailleText);
        ((Container)borderPanel).add("North", (Component)borderPanel2);
        final BorderPanel borderPanel3 = new BorderPanel(7, 5, 5, 5, 7);
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        ((Container)borderPanel3).setLayout(layout2);
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.gridheight = 1;
        gridBagConstraints2.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.weighty = 0.8;
        gridBagConstraints2.weightx = 0.2;
        final Label label4 = new Label("Text :");
        layout2.setConstraints(label4, gridBagConstraints2);
        ((Container)borderPanel3).add(label4);
        (this.texte = new TextField("Here your text...", 20)).setBackground(Color.white);
        gridBagConstraints2.weightx = 0.8;
        gridBagConstraints2.gridwidth = 0;
        layout2.setConstraints(this.texte, gridBagConstraints2);
        ((Container)borderPanel3).add(this.texte);
        ((Container)borderPanel).add("Center", (Component)borderPanel3);
        final BorderPanel borderPanel4 = new BorderPanel(5, 5, 5, 5, 7);
        ((Container)borderPanel4).setLayout(new GridLayout(1, 1, 0, 0));
        ((Container)borderPanel4).add(new Button(" Ok "));
        ((Container)borderPanel).add("South", (Component)borderPanel4);
        this.add("Center", (Component)borderPanel);
        this.validate();
        this.pack();
    }
    
    public final void verifChamps() {
        try {
            this.taille = new Integer(this.tailleText.getText());
        }
        catch (Exception ex) {
            this.taille = 10;
            this.tailleText.setText("10");
        }
        if (this.taille > 300) {
            this.taille = 300;
            this.tailleText.setText("300");
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.verifChamps();
            this.ouverte = false;
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            this.verifChamps();
            this.ouverte = false;
            this.hide();
            return true;
        }
        if (event.target instanceof TextField) {
            this.verifChamps();
            return true;
        }
        return false;
    }
}
