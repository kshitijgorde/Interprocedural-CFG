// 
// Decompiled by Procyon v0.5.30
// 

package weightapplet;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Frame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.util.HashMap;
import javax.swing.JDialog;

public class PAL_Dialog extends JDialog
{
    private MainPanel mpanel;
    private HashMap hash;
    private String leisure;
    private String work;
    private JButton CancelButton;
    private JButton EstimatePALButton;
    private JComboBox Leisure_Combo;
    private JLabel SpaceLabel;
    private JLabel SpaceLabel1;
    private JLabel SpaceLabel3;
    private JComboBox Work_Combo;
    private JLabel jLabel2;
    private JLabel jLabel6;
    
    public PAL_Dialog(final Frame frame, final boolean b, final MainPanel mpanel) {
        super(frame, b);
        this.leisure = "Moderate";
        this.work = "Moderate";
        this.mpanel = mpanel;
        this.inithash();
        this.initComponents();
    }
    
    private void inithash() {
        (this.hash = new HashMap()).put("Very light, Very light", 1.4);
        this.hash.put("Very light, Light", 1.5);
        this.hash.put("Very light, Moderate", 1.6);
        this.hash.put("Very light, Heavy", 1.7);
        this.hash.put("Light, Very light", 1.5);
        this.hash.put("Light, Light", 1.6);
        this.hash.put("Light, Moderate", 1.7);
        this.hash.put("Light, Heavy", 1.8);
        this.hash.put("Moderate, Very light", 1.6);
        this.hash.put("Moderate, Light", 1.7);
        this.hash.put("Moderate, Moderate", 1.8);
        this.hash.put("Moderate, Heavy", 1.9);
        this.hash.put("Active, Very light", 1.7);
        this.hash.put("Active, Light", 1.8);
        this.hash.put("Active, Moderate", 1.9);
        this.hash.put("Active, Heavy", 2.1);
        this.hash.put("Very active, Very light", 1.9);
        this.hash.put("Very active, Light", 2.0);
        this.hash.put("Very active, Moderate", 2.2);
        this.hash.put("Very active, Heavy", 2.3);
    }
    
    private void initComponents() {
        this.Work_Combo = new JComboBox();
        this.Leisure_Combo = new JComboBox();
        this.EstimatePALButton = new JButton();
        this.jLabel2 = new JLabel();
        this.SpaceLabel = new JLabel();
        this.jLabel6 = new JLabel();
        this.SpaceLabel1 = new JLabel();
        this.SpaceLabel3 = new JLabel();
        this.CancelButton = new JButton();
        this.setTitle("Estimate Your Physical Activity Level");
        this.getContentPane().setLayout(new GridBagLayout());
        this.Work_Combo.setModel(new DefaultComboBoxModel<String>(new String[] { "Very light, e.g. sitting at the computer most of the day or sitting at a desk", "Light, e.g. light industrial work, sales or office work that comprises light activities", "Moderate, e.g. cleaning,  kitchen staff, or delivering mail on foot or by bicycle", "Heavy, e.g. heavy industrial work, construction work or farming" }));
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = 17;
        this.getContentPane().add(this.Work_Combo, gridBagConstraints);
        this.Leisure_Combo.setModel(new DefaultComboBoxModel<String>(new String[] { "Very light: almost no activity at all", "Light, e.g. walking, nonstrenuous cycling or gardening approximately once a week", "Moderate: regular activity at least once a week, e.g., walking or bicycling (including to work), or gardening ", "Active: regular activities more than once a week, e.g., intense walking or bicycling or sports", "Very Active: strenuous activities several times a week" }));
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 5;
        gridBagConstraints2.gridwidth = 2;
        gridBagConstraints2.anchor = 17;
        this.getContentPane().add(this.Leisure_Combo, gridBagConstraints2);
        this.EstimatePALButton.setText("Estimate Initial Physical Activity Level");
        this.EstimatePALButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                PAL_Dialog.this.EstimatePALButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 7;
        gridBagConstraints3.anchor = 13;
        this.getContentPane().add(this.EstimatePALButton, gridBagConstraints3);
        this.jLabel2.setText("Describe your physical activity at leisure time (if the activities vary between summer and winter, try to give a mean estimate):");
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 0;
        gridBagConstraints4.gridy = 4;
        gridBagConstraints4.gridwidth = 2;
        gridBagConstraints4.anchor = 17;
        this.getContentPane().add(this.jLabel2, gridBagConstraints4);
        this.SpaceLabel.setFont(new Font("Lucida Grande", 0, 14));
        this.SpaceLabel.setText("     ");
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 6;
        gridBagConstraints5.gridwidth = 2;
        this.getContentPane().add(this.SpaceLabel, gridBagConstraints5);
        this.jLabel6.setText("Describe your physical activity at work (including work at home, sick leave and schoolwork):");
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 1;
        gridBagConstraints6.gridwidth = 2;
        gridBagConstraints6.anchor = 17;
        this.getContentPane().add(this.jLabel6, gridBagConstraints6);
        this.SpaceLabel1.setFont(new Font("Lucida Grande", 0, 14));
        this.SpaceLabel1.setText("     ");
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 0;
        gridBagConstraints7.gridwidth = 2;
        this.getContentPane().add(this.SpaceLabel1, gridBagConstraints7);
        this.SpaceLabel3.setFont(new Font("Lucida Grande", 0, 14));
        this.SpaceLabel3.setText("     ");
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        gridBagConstraints8.gridx = 0;
        gridBagConstraints8.gridy = 3;
        gridBagConstraints8.gridwidth = 2;
        this.getContentPane().add(this.SpaceLabel3, gridBagConstraints8);
        this.CancelButton.setText("Cancel");
        this.CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                PAL_Dialog.this.CancelButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        gridBagConstraints9.gridx = 1;
        gridBagConstraints9.gridy = 7;
        this.getContentPane().add(this.CancelButton, gridBagConstraints9);
        this.pack();
    }
    
    private void EstimatePALButtonActionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
        this.mpanel.getBaseline().setPAL(this.lookupPAL());
        this.mpanel.getBaseline().print();
        this.mpanel.syncPAL();
        this.mpanel.recalc();
    }
    
    private void CancelButtonActionPerformed(final ActionEvent actionEvent) {
        this.setVisible(false);
    }
    
    private double lookupPAL() {
        this.setleisure_string();
        this.setwork_string();
        final String string = this.leisure + ", " + this.work;
        double double1;
        try {
            double1 = Double.parseDouble(this.hash.get(string).toString());
        }
        catch (Exception ex) {
            double1 = 1.3;
        }
        return double1;
    }
    
    private void setleisure_string() {
        final int selectedIndex = this.Leisure_Combo.getSelectedIndex();
        if (selectedIndex == 0) {
            this.leisure = "Very light";
        }
        else if (selectedIndex == 1) {
            this.leisure = "Light";
        }
        else if (selectedIndex == 2) {
            this.leisure = "Moderate";
        }
        else if (selectedIndex == 3) {
            this.leisure = "Active";
        }
        else if (selectedIndex == 4) {
            this.leisure = "Very active";
        }
    }
    
    private void setwork_string() {
        final int selectedIndex = this.Work_Combo.getSelectedIndex();
        if (selectedIndex == 0) {
            this.work = "Very light";
        }
        else if (selectedIndex == 1) {
            this.work = "Light";
        }
        else if (selectedIndex == 2) {
            this.work = "Moderate";
        }
        else if (selectedIndex == 3) {
            this.work = "Heavy";
        }
    }
}
