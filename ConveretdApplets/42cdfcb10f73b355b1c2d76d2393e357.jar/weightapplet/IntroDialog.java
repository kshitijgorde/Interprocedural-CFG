// 
// Decompiled by Procyon v0.5.30
// 

package weightapplet;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerListModel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Frame;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JDialog;

public class IntroDialog extends JDialog
{
    MainPanel mpanel;
    private double initweight;
    private double initheight;
    private boolean initmale;
    private JFormattedTextField AgeField;
    private JLabel AgeLabel;
    private JLabel BaselineLabel;
    private JRadioButton CentimetersRadio;
    private JButton EstimatePALButton;
    private JComboBox GenderBox;
    private JFormattedTextField HeightField;
    private JLabel HeightLabel;
    private JRadioButton InchesRadio;
    private JFormattedTextField InitialWeightField;
    private JLabel InitialWeightLabel;
    private JRadioButton KilogramsRadio;
    private JLabel PALLabel;
    private JSpinner PALSpinner;
    private JRadioButton PoundsRadio;
    private JButton QuickStartButton;
    private JButton jButton1;
    
    public IntroDialog(final Frame frame, final boolean b, final MainPanel mpanel) {
        super(frame, b);
        this.mpanel = mpanel;
        this.initComponents();
    }
    
    private void initComponents() {
        this.InitialWeightLabel = new JLabel();
        this.PoundsRadio = new JRadioButton();
        this.KilogramsRadio = new JRadioButton();
        this.InitialWeightField = new JFormattedTextField();
        this.AgeLabel = new JLabel();
        this.AgeField = new JFormattedTextField();
        this.HeightLabel = new JLabel();
        this.HeightField = new JFormattedTextField();
        this.InchesRadio = new JRadioButton();
        this.CentimetersRadio = new JRadioButton();
        this.GenderBox = new JComboBox();
        this.PALLabel = new JLabel();
        this.EstimatePALButton = new JButton();
        this.BaselineLabel = new JLabel();
        this.QuickStartButton = new JButton();
        this.PALSpinner = new JSpinner();
        this.jButton1 = new JButton();
        this.setDefaultCloseOperation(2);
        this.setModal(true);
        this.getContentPane().setLayout(new GridBagLayout());
        this.InitialWeightLabel.setText("Initial Weight");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 3;
        gridBagConstraints.anchor = 17;
        this.getContentPane().add(this.InitialWeightLabel, gridBagConstraints);
        this.PoundsRadio.setText("Pounds");
        this.PoundsRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                IntroDialog.this.PoundsRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.anchor = 17;
        this.getContentPane().add(this.PoundsRadio, gridBagConstraints2);
        this.KilogramsRadio.setSelected(true);
        this.KilogramsRadio.setText("Kilograms");
        this.KilogramsRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                IntroDialog.this.KilogramsRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.anchor = 17;
        this.getContentPane().add(this.KilogramsRadio, gridBagConstraints3);
        this.InitialWeightField.setColumns(3);
        this.InitialWeightField.setValue(this.mpanel.getBaseline().getWeight_kgs());
        this.InitialWeightField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                IntroDialog.this.InitialWeightFieldActionPerformed(actionEvent);
            }
        });
        this.InitialWeightField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                IntroDialog.this.InitialWeightFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.anchor = 17;
        this.getContentPane().add(this.InitialWeightField, gridBagConstraints4);
        this.AgeLabel.setText("Age (years)");
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 4;
        gridBagConstraints5.anchor = 17;
        this.getContentPane().add(this.AgeLabel, gridBagConstraints5);
        this.AgeField.setColumns(3);
        this.AgeField.setValue(this.mpanel.getBaseline().getAge());
        this.AgeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                IntroDialog.this.AgeFieldActionPerformed(actionEvent);
            }
        });
        this.AgeField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                IntroDialog.this.AgeFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.gridx = 1;
        gridBagConstraints6.gridy = 4;
        gridBagConstraints6.anchor = 17;
        this.getContentPane().add(this.AgeField, gridBagConstraints6);
        this.HeightLabel.setText("Height");
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.gridx = 0;
        gridBagConstraints7.gridy = 5;
        gridBagConstraints7.anchor = 17;
        this.getContentPane().add(this.HeightLabel, gridBagConstraints7);
        this.HeightField.setColumns(3);
        this.HeightField.setValue(this.mpanel.getBaseline().getHeight_cms());
        this.HeightField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                IntroDialog.this.HeightFieldActionPerformed(actionEvent);
            }
        });
        this.HeightField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                IntroDialog.this.HeightFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        gridBagConstraints8.gridx = 1;
        gridBagConstraints8.gridy = 5;
        gridBagConstraints8.anchor = 17;
        this.getContentPane().add(this.HeightField, gridBagConstraints8);
        this.InchesRadio.setText("Inches");
        this.InchesRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                IntroDialog.this.InchesRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        gridBagConstraints9.gridx = 0;
        gridBagConstraints9.gridy = 6;
        gridBagConstraints9.anchor = 17;
        this.getContentPane().add(this.InchesRadio, gridBagConstraints9);
        this.CentimetersRadio.setSelected(true);
        this.CentimetersRadio.setText("Centimeters");
        this.CentimetersRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                IntroDialog.this.CentimetersRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
        gridBagConstraints10.gridx = 1;
        gridBagConstraints10.gridy = 6;
        gridBagConstraints10.anchor = 17;
        this.getContentPane().add(this.CentimetersRadio, gridBagConstraints10);
        this.GenderBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Male", "Female" }));
        this.GenderBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                IntroDialog.this.GenderBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        gridBagConstraints11.gridx = 0;
        gridBagConstraints11.gridy = 3;
        gridBagConstraints11.gridwidth = 2;
        this.getContentPane().add(this.GenderBox, gridBagConstraints11);
        this.PALLabel.setText("Physical Activity Level");
        final GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
        gridBagConstraints12.gridx = 0;
        gridBagConstraints12.gridy = 8;
        gridBagConstraints12.anchor = 17;
        this.getContentPane().add(this.PALLabel, gridBagConstraints12);
        this.EstimatePALButton.setText("Estimate Activity Level");
        this.EstimatePALButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                IntroDialog.this.EstimatePALButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        gridBagConstraints13.gridx = 0;
        gridBagConstraints13.gridy = 9;
        gridBagConstraints13.gridwidth = 2;
        this.getContentPane().add(this.EstimatePALButton, gridBagConstraints13);
        this.BaselineLabel.setFont(new Font("Lucida Grande", 1, 14));
        this.BaselineLabel.setHorizontalAlignment(0);
        this.BaselineLabel.setText("Enter Baseline Information");
        this.BaselineLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
        gridBagConstraints14.gridx = 0;
        gridBagConstraints14.gridy = 0;
        gridBagConstraints14.gridwidth = 2;
        gridBagConstraints14.ipadx = 20;
        this.getContentPane().add(this.BaselineLabel, gridBagConstraints14);
        this.QuickStartButton.setText("Quick Start");
        this.QuickStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                IntroDialog.this.QuickStartButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
        gridBagConstraints15.gridx = 0;
        gridBagConstraints15.gridy = 11;
        this.getContentPane().add(this.QuickStartButton, gridBagConstraints15);
        this.PALSpinner.setModel(new SpinnerListModel(new String[] { "1.3", "1.4", "1.5", "1.6", "1.7", "1.8", "1.9", "2.0", "2.1", "2.2", "2.3", "2.4" }));
        this.PALSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                IntroDialog.this.PALSpinnerStateChanged(changeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
        gridBagConstraints16.gridx = 1;
        gridBagConstraints16.gridy = 8;
        gridBagConstraints16.anchor = 17;
        this.getContentPane().add(this.PALSpinner, gridBagConstraints16);
        this.jButton1.setText("Guide Me");
        final GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
        gridBagConstraints17.gridx = 1;
        gridBagConstraints17.gridy = 11;
        this.getContentPane().add(this.jButton1, gridBagConstraints17);
        this.pack();
    }
    
    private void PoundsRadioActionPerformed(final ActionEvent actionEvent) {
    }
    
    private void KilogramsRadioActionPerformed(final ActionEvent actionEvent) {
    }
    
    private void InitialWeightFieldActionPerformed(final ActionEvent actionEvent) {
        System.out.println("ACTION EVENT NAME IS " + actionEvent.getActionCommand());
        this.InitialWeightFieldChange();
    }
    
    private void InitialWeightFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.InitialWeightFieldChange();
        }
    }
    
    private void AgeFieldActionPerformed(final ActionEvent actionEvent) {
    }
    
    private void AgeFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {}
    }
    
    private void HeightFieldActionPerformed(final ActionEvent actionEvent) {
    }
    
    private void HeightFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {}
    }
    
    private void InchesRadioActionPerformed(final ActionEvent actionEvent) {
    }
    
    private void CentimetersRadioActionPerformed(final ActionEvent actionEvent) {
    }
    
    private void GenderBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.GenderBox.getSelectedItem().toString().equals("Male")) {
            this.mpanel.getBaseline().setMale(true);
        }
        else {
            this.mpanel.getBaseline().setMale(false);
        }
    }
    
    private void EstimatePALButtonActionPerformed(final ActionEvent actionEvent) {
        this.mpanel.getPAL_Dialog().setVisible(true);
    }
    
    private void PALSpinnerStateChanged(final ChangeEvent changeEvent) {
        try {
            this.mpanel.getBaseline().setPAL(Double.parseDouble(this.PALSpinner.getValue().toString()));
        }
        catch (Exception ex) {
            System.out.println("Spinner failed to parse");
        }
    }
    
    private void QuickStartButtonActionPerformed(final ActionEvent actionEvent) {
        this.mpanel.getBaseline().print();
        this.remove(this.mpanel.getBaselinePanel());
        this.mpanel.add(this.mpanel.getBaselinePanel());
        this.setVisible(false);
    }
    
    private void InitialWeightFieldChange() {
        if (Double.parseDouble(this.InitialWeightField.getValue().toString()) > 0.0) {
            this.mpanel.getBaseline().setWeight_kgs(this.initweight);
            System.out.println("baseline weight changed");
        }
        else {
            this.InitialWeightField.setValue(this.mpanel.getBaseline().getWeight_kgs());
        }
    }
}
