import java.awt.Color;
import java.awt.Graphics;
import edu.wise.utils.General;
import edu.wise.utils.FormatUtils;
import java.awt.Event;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.Component;
import java.awt.Choice;
import java.awt.LayoutManager;

// 
// Decompiled by Procyon v0.5.30
// 

class RightPanel extends DistPanel
{
    int com_base;
    String es_txt;
    boolean sampleDrawn;
    boolean pullValues;
    int buttonHeight;
    
    RightPanel() {
        this.com_base = 0;
        this.buttonHeight = 22;
    }
    
    RightPanel(final int n, final int n2) {
        super(n, n2);
        this.com_base = 0;
        this.buttonHeight = 22;
        this.com_base = 20;
        this.setLayout(null);
        this.setFont(DistPanel.smallFont);
        this.sampleDrawn = false;
        this.pullValues = false;
        (DistPanel.effect = new Choice()).addItem("Mean = 555");
        DistPanel.effect.addItem("Mean = 585");
        DistPanel.effect.addItem("Mean = 625");
        DistPanel.effect.addItem("Mean = 667");
        DistPanel.effect.addItem("Custom");
        this.add(DistPanel.effect);
        this.selectEffect();
        DistPanel.effect.reshape(this.com_base - 10, 23, 130, 22);
        this.add(DistPanel.effectLabel = new Label("Alternate mean = "));
        DistPanel.effectLabel.setFont(DistPanel.smallFont_BOLD);
        DistPanel.effectLabel.reshape(this.com_base - 10, 4, 110, 16);
        this.add(DistPanel.sampleButton = new Button("Draw a sample"));
        DistPanel.sampleButton.reshape(this.com_base - 5, 14 * (super.comp_ht + 8), super.regularFM.stringWidth(DistPanel.sampleButton.getLabel()) + 40, this.buttonHeight);
        this.add(DistPanel.setButton = new Button("Set"));
        DistPanel.setButton.reshape(this.com_base - 5, 16 * (super.comp_ht + 8) - 14, super.regularFM.stringWidth(DistPanel.sampleButton.getLabel()) - 30, this.buttonHeight);
        this.add(DistPanel.clear = new Button("Clear"));
        DistPanel.clear.reshape(this.com_base - 5 + super.regularFM.stringWidth(DistPanel.sampleButton.getLabel()) - 15, 16 * (super.comp_ht + 8) - 14, super.regularFM.stringWidth(DistPanel.sampleButton.getLabel()) - 30, this.buttonHeight);
        this.add(DistPanel.n_lab = new Label("N ="));
        DistPanel.n_lab.setFont(DistPanel.smallFont_BOLD);
        DistPanel.n_lab.reshape(this.com_base, 3 * (super.comp_ht + 8) - 4, 60, super.comp_ht + 8);
        this.add(DistPanel.mu_nod_lab = new Label("µ_0 ="));
        DistPanel.mu_nod_lab.setFont(DistPanel.smallFont_BOLD);
        DistPanel.mu_nod_lab.reshape(this.com_base, 4 * (super.comp_ht + 8) - 2, 60, super.smallFM.getHeight());
        this.add(DistPanel.mu_alt_lab = new Label("µ_1 ="));
        DistPanel.mu_alt_lab.setFont(DistPanel.smallFont_BOLD);
        DistPanel.mu_alt_lab.reshape(this.com_base, 5 * (super.comp_ht + 8), 60, super.smallFM.getHeight());
        this.add(DistPanel.sigma_lab = new Label("sigma ="));
        DistPanel.sigma_lab.setFont(DistPanel.smallFont_BOLD);
        DistPanel.sigma_lab.reshape(this.com_base, 6 * (super.comp_ht + 8) + 2, 60, super.comp_ht);
        this.add(DistPanel.alpha_lab = new Label("alpha ="));
        DistPanel.alpha_lab.setFont(DistPanel.smallFont_BOLD);
        DistPanel.alpha_lab.reshape(this.com_base, (int)(7.25 * (super.comp_ht + 8) + 2.0), 60, super.comp_ht + 8);
        this.add(DistPanel.n_tf = new TextField(String.valueOf(Values.n), 30));
        DistPanel.n_tf.reshape(this.com_base + 75, 3 * (8 + super.comp_ht) - 4, 50, 8 + super.comp_ht);
        this.add(DistPanel.mu_nod_tf = new TextField(String.valueOf(Distribution.get_mu()), 30));
        DistPanel.mu_nod_tf.reshape(this.com_base + 75, 4 * (8 + super.comp_ht) - 2, 50, 8 + super.comp_ht);
        this.add(DistPanel.mu_alt_tf = new TextField(String.valueOf(Distribution.alt_mu), 30));
        DistPanel.mu_alt_tf.reshape(this.com_base + 75, 5 * (8 + super.comp_ht), 50, 8 + super.comp_ht);
        this.add(DistPanel.sigma_tf = new TextField(String.valueOf(Distribution.get_sigma()), 30));
        DistPanel.sigma_tf.reshape(this.com_base + 75, 6 * (8 + super.comp_ht) + 2, 50, 8 + super.comp_ht);
        this.add(DistPanel.alpha_tf = new TextField(String.valueOf(Distribution.alpha), 30));
        DistPanel.alpha_tf.reshape(this.com_base + 75, 7 * (8 + super.comp_ht) + 4, 50, 8 + super.comp_ht);
    }
    
    private void selectEffect() {
        if (Distribution.alt_mu == 555.0) {
            DistPanel.effect.select("Mean = 555");
        }
        else if (Distribution.alt_mu == 585.0) {
            DistPanel.effect.select("Mean = 585");
        }
        else if (Distribution.alt_mu == 625.0) {
            DistPanel.effect.select("Mean = 625");
        }
        else if (Distribution.alt_mu == 667.0) {
            DistPanel.effect.select("Mean = 667");
        }
        else {
            DistPanel.effect.select("Custom");
        }
    }
    
    public void get_n_tf() {
        this.sampleDrawn = false;
        try {
            Values.dump_core();
            Values.n = Integer.parseInt(DistPanel.n_tf.getText());
            if (Values.n > 100) {
                Values.n = 100;
            }
            if (Values.n < 1) {
                Values.n = 1;
            }
            Distribution.n_size_change(Values.n);
            Distribution.parameter_change();
            if (!this.pullValues) {
                Z.DD_panel.repaint();
            }
        }
        catch (NumberFormatException ex) {}
        DistPanel.n_tf.setText(String.valueOf(Values.n));
    }
    
    public void get_mu_tf() {
        this.sampleDrawn = false;
        try {
            Values.dump_core();
            Distribution.mu = Double.valueOf(DistPanel.mu_nod_tf.getText());
            Distribution.full_parameter_change();
        }
        catch (NumberFormatException ex) {
            System.out.println("Not a valid parameter::" + ex.toString());
            DistPanel.mu_nod_tf.setText(String.valueOf(Distribution.get_mu()));
        }
    }
    
    public void get_mu_alt_tf() {
        this.sampleDrawn = false;
        try {
            Values.dump_core();
            Distribution.alt_mu = Double.valueOf(DistPanel.mu_alt_tf.getText());
            Distribution.parameter_change();
            this.selectEffect();
        }
        catch (NumberFormatException ex) {
            System.out.println("Not a valid parameter::" + ex.toString());
            DistPanel.mu_alt_tf.setText(String.valueOf(Distribution.get_alt_mean()));
        }
    }
    
    private void get_alpha_tf() {
        try {
            final double doubleValue = Double.valueOf(DistPanel.alpha_tf.getText());
            if (doubleValue > 0.0 && doubleValue < 0.5) {
                Distribution.alpha = doubleValue;
            }
            DistPanel.alpha_tf.setText(String.valueOf(Distribution.alpha));
        }
        catch (NumberFormatException ex) {
            System.out.println("Not a valid parameter::" + ex.toString());
            DistPanel.alpha_tf.setText(String.valueOf(Distribution.alpha));
        }
    }
    
    public void get_sigma_tf() {
        this.sampleDrawn = false;
        try {
            Values.dump_core();
            final double doubleValue = Double.valueOf(DistPanel.sigma_tf.getText());
            if (doubleValue <= 0.0) {
                System.out.println("Population variance must be greater than 0");
            }
            else {
                Distribution.sigma = doubleValue;
                Distribution.full_parameter_change();
            }
        }
        catch (NumberFormatException ex) {
            DistPanel.sigma_tf.setText(String.valueOf(Distribution.sigma));
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target.equals(DistPanel.mu_nod_tf)) {
            try {
                final char char1 = DistPanel.mu_nod_tf.getText().charAt(0);
                if (char1 == ' ' || char1 == '\u007f') {
                    DistPanel.mu_nod_tf.setText(String.valueOf(Distribution.get_mu()));
                }
                else {
                    this.get_mu_tf();
                    if (!this.pullValues) {
                        Z.DD_panel.repaint();
                    }
                }
            }
            catch (StringIndexOutOfBoundsException ex) {
                DistPanel.mu_nod_tf.setText(String.valueOf(Distribution.get_mu()));
            }
        }
        else if (event.id == 1001 && event.target.equals(DistPanel.mu_alt_tf)) {
            try {
                final char char2 = DistPanel.mu_alt_tf.getText().charAt(0);
                if (char2 == ' ' || char2 == '\u007f') {
                    DistPanel.mu_alt_tf.setText(String.valueOf(Distribution.get_alt_mean()));
                }
                else {
                    this.get_mu_alt_tf();
                    if (!this.pullValues) {
                        Z.DD_panel.repaint();
                    }
                }
            }
            catch (StringIndexOutOfBoundsException ex2) {
                DistPanel.mu_alt_tf.setText(String.valueOf(Distribution.get_alt_mean()));
            }
        }
        else if (event.id == 1001 && event.target.equals(DistPanel.sigma_tf)) {
            try {
                final char char3 = DistPanel.sigma_tf.getText().charAt(0);
                if (char3 == ' ' || char3 == '\u007f') {
                    DistPanel.sigma_tf.setText(String.valueOf(Distribution.get_sigma()));
                }
                else {
                    this.get_sigma_tf();
                    if (!this.pullValues) {
                        Z.DD_panel.repaint();
                    }
                }
            }
            catch (StringIndexOutOfBoundsException ex3) {
                DistPanel.sigma_tf.setText(String.valueOf(Distribution.get_sigma()));
            }
        }
        else if (event.id == 1001 && event.target.equals(DistPanel.alpha_tf)) {
            try {
                final char char4 = DistPanel.alpha_tf.getText().charAt(0);
                if (char4 == ' ' || char4 == '\u007f') {
                    DistPanel.alpha_tf.setText(String.valueOf(Distribution.get_sigma()));
                }
                else {
                    this.get_alpha_tf();
                    if (!this.pullValues) {
                        Z.DD_panel.repaint();
                    }
                }
            }
            catch (StringIndexOutOfBoundsException ex4) {
                DistPanel.alpha_tf.setText(String.valueOf(Distribution.alpha));
            }
        }
        else if (event.id == 1001 && event.target == DistPanel.effect) {
            Values.dump_core();
            if (DistPanel.effect.getSelectedIndex() == 0) {
                Distribution.alt_mu = 555.0;
            }
            else if (DistPanel.effect.getSelectedIndex() == 1) {
                Distribution.alt_mu = 585.0;
            }
            else if (DistPanel.effect.getSelectedIndex() == 2) {
                Distribution.alt_mu = 625.0;
            }
            else if (DistPanel.effect.getSelectedIndex() == 3) {
                Distribution.alt_mu = 667.0;
            }
            DistPanel.mu_alt_tf.setText(Double.toString(Distribution.alt_mu).substring(0, FormatUtils.word_length(Distribution.alt_mu, 5)));
            Distribution.parameter_change();
            Distribution.clear_means_arr(Z.val);
            DistPanel.draw_arrow = false;
            if (!this.pullValues) {
                Z.DD_panel.repaint();
            }
        }
        else if (event.id == 1001 && event.target.equals(DistPanel.n_tf)) {
            try {
                final char char5 = DistPanel.n_tf.getText().charAt(0);
                if (char5 == ' ' || char5 == '\u007f') {
                    DistPanel.n_tf.setText(String.valueOf(Values.n));
                }
                else {
                    this.get_n_tf();
                }
            }
            catch (StringIndexOutOfBoundsException ex5) {
                DistPanel.n_tf.setText(String.valueOf(Values.n));
            }
        }
        else if (event.id == 1001 && event.target.equals(DistPanel.sampleButton)) {
            this.sampleDrawn = true;
            General.clear_cases(Values.cases_arr);
            Z.samp.drawSample(Z.dist, Z.val);
            DistPanel.draw_arrow = true;
            if (!this.pullValues) {
                this.repaint();
                Z.DD_panel.repaint();
            }
        }
        else if (event.id == 1001 && event.target.equals(DistPanel.clear)) {
            this.sampleDrawn = false;
            Values.dump_core();
            if (!this.pullValues) {
                this.repaint();
                Z.DD_panel.repaint();
            }
        }
        else if (event.id == 1001 && event.target.equals(DistPanel.setButton)) {
            this.sampleDrawn = false;
            this.pullValues = true;
            Values.dump_core();
            this.handleEvent(new Event(DistPanel.n_tf, 1001, null));
            this.handleEvent(new Event(DistPanel.mu_nod_tf, 1001, null));
            this.handleEvent(new Event(DistPanel.mu_alt_tf, 1001, null));
            this.handleEvent(new Event(DistPanel.sigma_tf, 1001, null));
            this.handleEvent(new Event(DistPanel.alpha_tf, 1001, null));
            this.pullValues = false;
            this.repaint();
            Z.DD_panel.repaint();
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawLine(0, 0, super.WIDTH, 0);
        graphics.drawLine(0, super.HEIGHT - 1, super.WIDTH, super.HEIGHT - 1);
        graphics.drawLine(0, 0, 0, super.HEIGHT);
        graphics.drawLine(super.WIDTH - 1, 0, super.WIDTH - 1, super.HEIGHT);
        graphics.drawLine(0, 9 * (super.comp_ht + 7) + 5, super.WIDTH, 9 * (super.comp_ht + 7) + 5);
        graphics.drawLine(0, 14 * (super.comp_ht + 7) + 5, super.WIDTH, 14 * (super.comp_ht + 7) + 5);
        graphics.setFont(DistPanel.smallFont_BOLD);
        graphics.drawString("Mean = ", this.com_base, 10 * (super.comp_ht + 7));
        graphics.drawString("s =", this.com_base, 11 * (super.comp_ht + 7));
        graphics.drawString("z = ", this.com_base, 12 * (super.comp_ht + 7));
        graphics.drawString("1-tail p = ", this.com_base, 13 * (super.comp_ht + 7));
        graphics.drawString("2-tail p = ", this.com_base, 14 * (super.comp_ht + 7));
        graphics.setFont(DistPanel.smallFont);
        if (this.sampleDrawn) {
            graphics.setFont(DistPanel.smallFont);
            final double rounder = FormatUtils.rounder(Values.obt_mean, 3);
            final double rounder2 = FormatUtils.rounder(Values.s_dev, 3);
            final double rounder3 = FormatUtils.rounder(Values.z, 3);
            final double rounder4 = FormatUtils.rounder(Z.dist.get_onetail_p(Values.z), 3);
            final double rounder5 = FormatUtils.rounder(Z.dist.get_twotail_p(Values.z), 3);
            final int n = 10;
            final int n2 = super.WIDTH - super.smallFM.stringWidth(Double.toString(rounder)) - n;
            final int n3 = super.WIDTH - super.smallFM.stringWidth(Double.toString(rounder2)) - n;
            final int n4 = super.WIDTH - super.smallFM.stringWidth(Double.toString(rounder3)) - n;
            final int n5 = super.WIDTH - super.smallFM.stringWidth(Double.toString(rounder4)) - n;
            final int n6 = super.WIDTH - super.smallFM.stringWidth(Double.toString(rounder5)) - n;
            graphics.drawString(Double.toString(rounder), n2, 10 * (super.comp_ht + 7));
            if (Values.n > 1) {
                graphics.drawString(Double.toString(rounder2), n3, 11 * (super.comp_ht + 7));
            }
            graphics.drawString(Double.toString(rounder3), n4, 12 * (super.comp_ht + 7));
            if (rounder4 < Distribution.alpha || rounder4 > 1.0 - Distribution.alpha) {
                graphics.setColor(Color.red);
            }
            graphics.drawString(Double.toString(rounder4), n5, 13 * (super.comp_ht + 7));
            if (rounder5 < 0.025 || rounder5 > 0.975) {
                graphics.setColor(Color.red);
            }
            else {
                graphics.setColor(Color.black);
            }
            graphics.drawString(Double.toString(rounder5), n6, 14 * (super.comp_ht + 7));
            graphics.setColor(Color.black);
        }
    }
}
