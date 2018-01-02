// 
// Decompiled by Procyon v0.5.30
// 

package weightapplet;

import java.awt.EventQueue;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.text.DefaultFormatterFactory;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Frame;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.util.Vector;
import java.awt.Component;
import java.awt.Color;
import javax.swing.JDialog;

public class DetailedActivityDialog extends JDialog
{
    private Color activecolor;
    private Color inactivecolor;
    private MainPanel mpanel;
    private int index;
    private double metmin_total;
    private int activityindex;
    private double weightunits;
    private double energyunits;
    private double actchangeperc;
    private double oldactchangeperc;
    private Object oldboxvalue;
    private boolean liveupdating;
    Component[] comboxlist;
    Object[] statelist;
    Vector combostatevector;
    Vector combovector;
    Vector<Boolean> checkstatevector;
    Vector checkvector;
    private JComboBox ActChange1;
    private JComboBox ActChange2;
    private JComboBox ActChange3;
    private JComboBox ActChange4;
    private JCheckBox ActCheck1;
    private JCheckBox ActCheck2;
    private JCheckBox ActCheck3;
    private JCheckBox ActCheck4;
    private JComboBox ActivityBox1;
    private JComboBox ActivityBox2;
    private JComboBox ActivityBox3;
    private JComboBox ActivityBox4;
    private JButton CancelButton;
    private JComboBox DayWeekBox1;
    private JComboBox DayWeekBox2;
    private JComboBox DayWeekBox3;
    private JComboBox DayWeekBox4;
    private JComboBox DurationBox1;
    private JComboBox DurationBox2;
    private JComboBox DurationBox3;
    private JComboBox DurationBox4;
    private JComboBox FrequencyBox1;
    private JComboBox FrequencyBox2;
    private JComboBox FrequencyBox3;
    private JComboBox FrequencyBox4;
    private JCheckBox LiveUpdatingCheckBox;
    private JLabel METlabel;
    private JFormattedTextField NewActChangePercField;
    private JButton OkButton;
    private JLabel SpaceLabel;
    private JLabel StatementLabel;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JTextField jTextField1;
    
    public DetailedActivityDialog(final Frame frame, final boolean b, final MainPanel mpanel, final String text, final int index) {
        super(frame, false);
        this.activecolor = Color.black;
        this.inactivecolor = Color.gray;
        this.activityindex = 4;
        this.weightunits = 1.0;
        this.energyunits = 1.0;
        this.liveupdating = false;
        this.initComponents();
        this.mpanel = mpanel;
        this.StatementLabel.setText(text);
        this.index = index;
        if (index == 3 || index == 4) {
            this.liveupdating = false;
            this.LiveUpdatingCheckBox.setSelected(false);
            this.LiveUpdatingCheckBox.setVisible(false);
        }
        this.boxvector();
        this.update();
        this.remember();
    }
    
    private void boxvector() {
        final Component[] components = this.getContentPane().getComponents();
        this.combovector = new Vector();
        this.checkvector = new Vector();
        for (int i = 0; i < components.length; ++i) {
            if (components[i].getClass().getName().contains("Combo")) {
                this.combovector.add(components[i]);
            }
            if (components[i].getClass().getName().contains("Check") && !components[i].equals(this.LiveUpdatingCheckBox)) {
                this.checkvector.add(components[i]);
            }
        }
    }
    
    private void remember() {
        this.combostatevector = new Vector();
        this.checkstatevector = new Vector<Boolean>();
        for (int i = 0; i < this.combovector.size(); ++i) {
            this.combostatevector.add(((JComboBox)this.combovector.elementAt(i)).getSelectedItem());
        }
        for (int j = 0; j < this.checkvector.size(); ++j) {
            this.checkstatevector.add(((JCheckBox)this.checkvector.elementAt(j)).isSelected());
        }
    }
    
    private void restore() {
        for (int i = 0; i < this.combovector.size(); ++i) {
            ((JComboBox)this.combovector.elementAt(i)).setSelectedItem(this.combostatevector.elementAt(i));
        }
        for (int j = 0; j < this.checkvector.size(); ++j) {
            System.out.println("Check vector index is " + j);
            ((JCheckBox)this.checkvector.elementAt(j)).setSelected(this.checkstatevector.elementAt(j));
        }
    }
    
    public double act2met(final String s) {
        double n = 0.0;
        if (s.matches("Light Walking")) {
            n = 2.5;
        }
        else if (s.matches("Medium Walking")) {
            n = 3.8;
        }
        else if (s.matches("Intense Walking")) {
            n = 6.3;
        }
        else if (s.matches("Light Running")) {
            n = 8.0;
        }
        else if (s.matches("Medium Running")) {
            n = 13.0;
        }
        else if (s.matches("Intense Running")) {
            n = 16.0;
        }
        else if (s.matches("Light Cycling")) {
            n = 6.0;
        }
        else if (s.matches("Medium Cycling")) {
            n = 8.0;
        }
        else if (s.matches("Intense Cycling")) {
            n = 10.0;
        }
        else if (s.matches("Swimming")) {
            n = 8.0;
        }
        return n;
    }
    
    public double changesign(final String s) {
        if (s.matches("Removing")) {
            return -1.0;
        }
        return 1.0;
    }
    
    public double dayweek(final String s) {
        if (s.matches("week")) {
            return 0.14285714285714285;
        }
        return 1.0;
    }
    
    public void setoldactchange(final double oldactchangeperc) {
        this.oldactchangeperc = oldactchangeperc;
    }
    
    public void setoldboxvalue(final Object oldboxvalue) {
        this.oldboxvalue = oldboxvalue;
    }
    
    public Object getoldboxvalue() {
        return this.oldboxvalue;
    }
    
    public double getactchangeperc() {
        return this.actchangeperc;
    }
    
    private Intervention getintervention() throws Exception {
        if (this.index == 1) {
            return this.mpanel.getIntervention1();
        }
        if (this.index == 2) {
            return this.mpanel.getIntervention2();
        }
        if (this.index == 3) {
            return this.mpanel.getGoalIntervention();
        }
        if (this.index == 4) {
            return this.mpanel.getGoalMaintenanceIntervention();
        }
        throw new Exception();
    }
    
    public JComboBox getbox() throws Exception {
        if (this.index == 1) {
            return this.mpanel.getIntervention1ActChangeBox();
        }
        if (this.index == 2) {
            return this.mpanel.getIntervention2ActChangeBox();
        }
        if (this.index == 3) {
            return this.mpanel.getGoalActChangeBox();
        }
        if (this.index == 4) {
            return this.mpanel.getGoalMaintActChangeBox();
        }
        throw new Exception();
    }
    
    private void initComponents() {
        this.jLabel3 = new JLabel();
        this.jTextField1 = new JTextField();
        this.METlabel = new JLabel();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.StatementLabel = new JLabel();
        this.ActivityBox1 = new JComboBox();
        this.ActCheck4 = new JCheckBox();
        this.ActCheck2 = new JCheckBox();
        this.ActChange4 = new JComboBox();
        this.ActChange1 = new JComboBox();
        this.ActChange2 = new JComboBox();
        this.ActChange3 = new JComboBox();
        this.ActCheck3 = new JCheckBox();
        this.ActCheck1 = new JCheckBox();
        this.ActivityBox2 = new JComboBox();
        this.ActivityBox3 = new JComboBox();
        this.ActivityBox4 = new JComboBox();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.jLabel7 = new JLabel();
        this.jLabel8 = new JLabel();
        this.jLabel9 = new JLabel();
        this.jLabel10 = new JLabel();
        this.DurationBox4 = new JComboBox();
        this.DurationBox1 = new JComboBox();
        this.DurationBox2 = new JComboBox();
        this.DurationBox3 = new JComboBox();
        this.FrequencyBox4 = new JComboBox();
        this.FrequencyBox3 = new JComboBox();
        this.FrequencyBox1 = new JComboBox();
        this.FrequencyBox2 = new JComboBox();
        this.SpaceLabel = new JLabel();
        this.jLabel11 = new JLabel();
        this.jLabel12 = new JLabel();
        this.jLabel13 = new JLabel();
        this.jLabel14 = new JLabel();
        this.OkButton = new JButton();
        this.CancelButton = new JButton();
        this.DayWeekBox1 = new JComboBox();
        this.DayWeekBox2 = new JComboBox();
        this.DayWeekBox3 = new JComboBox();
        this.DayWeekBox4 = new JComboBox();
        this.jLabel4 = new JLabel();
        this.jLabel15 = new JLabel();
        this.LiveUpdatingCheckBox = new JCheckBox();
        this.NewActChangePercField = new JFormattedTextField();
        this.jLabel3.setText("These changes will result in a net change of ");
        this.jTextField1.setColumns(2);
        this.jTextField1.setEditable(false);
        this.METlabel.setText("METS (kJ/kg)");
        this.setTitle("Enter A Physical Activity Regimen");
        this.getContentPane().setLayout(new GridBagLayout());
        this.jLabel1.setText("for");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        this.getContentPane().add(this.jLabel1, gridBagConstraints);
        this.jLabel2.setText("minutes, ");
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 5;
        gridBagConstraints2.gridy = 5;
        this.getContentPane().add(this.jLabel2, gridBagConstraints2);
        this.StatementLabel.setText("I will change my physical activities by:");
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 0;
        gridBagConstraints3.gridy = 0;
        gridBagConstraints3.gridwidth = 9;
        this.getContentPane().add(this.StatementLabel, gridBagConstraints3);
        this.ActivityBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "Light Walking", "Medium Walking", "Intense Walking", "Light Running", "Medium Running", "Intense Running", "Light Cycling", "Medium Cycling", "Intense Cycling" }));
        this.ActivityBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.ActivityBox1ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 2;
        gridBagConstraints4.gridy = 2;
        this.getContentPane().add(this.ActivityBox1, gridBagConstraints4);
        this.ActCheck4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                DetailedActivityDialog.this.ActCheck4StateChanged(changeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 5;
        this.getContentPane().add(this.ActCheck4, gridBagConstraints5);
        this.ActCheck2.setSelected(true);
        this.ActCheck2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                DetailedActivityDialog.this.ActCheck2StateChanged(changeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 3;
        this.getContentPane().add(this.ActCheck2, gridBagConstraints6);
        this.ActChange4.setForeground(new Color(153, 153, 153));
        this.ActChange4.setModel(new DefaultComboBoxModel<String>(new String[] { "Adding", "Removing" }));
        this.ActChange4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.ActChange4ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.gridx = 1;
        gridBagConstraints7.gridy = 5;
        this.getContentPane().add(this.ActChange4, gridBagConstraints7);
        this.ActChange1.setModel(new DefaultComboBoxModel<String>(new String[] { "Adding", "Removing" }));
        this.ActChange1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.ActChange1ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        gridBagConstraints8.gridx = 1;
        gridBagConstraints8.gridy = 2;
        this.getContentPane().add(this.ActChange1, gridBagConstraints8);
        this.ActChange2.setModel(new DefaultComboBoxModel<String>(new String[] { "Adding", "Removing" }));
        this.ActChange2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.ActChange2ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        gridBagConstraints9.gridx = 1;
        gridBagConstraints9.gridy = 3;
        this.getContentPane().add(this.ActChange2, gridBagConstraints9);
        this.ActChange3.setForeground(new Color(153, 153, 153));
        this.ActChange3.setModel(new DefaultComboBoxModel<String>(new String[] { "Adding", "Removing" }));
        this.ActChange3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.ActChange3ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
        gridBagConstraints10.gridx = 1;
        gridBagConstraints10.gridy = 4;
        this.getContentPane().add(this.ActChange3, gridBagConstraints10);
        this.ActCheck3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                DetailedActivityDialog.this.ActCheck3StateChanged(changeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        gridBagConstraints11.gridx = 0;
        gridBagConstraints11.gridy = 4;
        this.getContentPane().add(this.ActCheck3, gridBagConstraints11);
        this.ActCheck1.setSelected(true);
        this.ActCheck1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(final ItemEvent itemEvent) {
                DetailedActivityDialog.this.ActCheck1ItemStateChanged(itemEvent);
            }
        });
        final GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
        gridBagConstraints12.gridx = 0;
        gridBagConstraints12.gridy = 2;
        this.getContentPane().add(this.ActCheck1, gridBagConstraints12);
        this.ActivityBox2.setModel(new DefaultComboBoxModel<String>(new String[] { "Light Walking", "Medium Walking", "Intense Walking", "Light Running", "Medium Running", "Intense Running", "Light Cycling", "Medium Cycling", "Intense Cycling" }));
        this.ActivityBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.ActivityBox2ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        gridBagConstraints13.gridx = 2;
        gridBagConstraints13.gridy = 3;
        this.getContentPane().add(this.ActivityBox2, gridBagConstraints13);
        this.ActivityBox3.setForeground(new Color(153, 153, 153));
        this.ActivityBox3.setModel(new DefaultComboBoxModel<String>(new String[] { "Light Walking", "Medium Walking", "Intense Walking", "Light Running", "Medium Running", "Intense Running", "Light Cycling", "Medium Cycling", "Intense Cycling" }));
        this.ActivityBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.ActivityBox3ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
        gridBagConstraints14.gridx = 2;
        gridBagConstraints14.gridy = 4;
        this.getContentPane().add(this.ActivityBox3, gridBagConstraints14);
        this.ActivityBox4.setForeground(new Color(153, 153, 153));
        this.ActivityBox4.setModel(new DefaultComboBoxModel<String>(new String[] { "Light Walking", "Medium Walking", "Intense Walking", "Light Running", "Medium Running", "Intense Running", "Light Cycling", "Medium Cycling", "Intense Cycling" }));
        this.ActivityBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.ActivityBox4ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
        gridBagConstraints15.gridx = 2;
        gridBagConstraints15.gridy = 5;
        this.getContentPane().add(this.ActivityBox4, gridBagConstraints15);
        this.jLabel5.setText("for");
        final GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
        gridBagConstraints16.gridx = 3;
        gridBagConstraints16.gridy = 2;
        this.getContentPane().add(this.jLabel5, gridBagConstraints16);
        this.jLabel6.setText("for");
        final GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
        gridBagConstraints17.gridx = 3;
        gridBagConstraints17.gridy = 4;
        this.getContentPane().add(this.jLabel6, gridBagConstraints17);
        this.jLabel7.setText("for");
        final GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
        gridBagConstraints18.gridx = 3;
        gridBagConstraints18.gridy = 3;
        this.getContentPane().add(this.jLabel7, gridBagConstraints18);
        this.jLabel8.setText("minutes, ");
        final GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
        gridBagConstraints19.gridx = 5;
        gridBagConstraints19.gridy = 2;
        this.getContentPane().add(this.jLabel8, gridBagConstraints19);
        this.jLabel9.setText("minutes, ");
        final GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
        gridBagConstraints20.gridx = 5;
        gridBagConstraints20.gridy = 3;
        this.getContentPane().add(this.jLabel9, gridBagConstraints20);
        this.jLabel10.setText("minutes, ");
        final GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
        gridBagConstraints21.gridx = 5;
        gridBagConstraints21.gridy = 4;
        this.getContentPane().add(this.jLabel10, gridBagConstraints21);
        this.DurationBox4.setForeground(new Color(153, 153, 153));
        this.DurationBox4.setModel(new DefaultComboBoxModel<String>(new String[] { "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "75", "90", "120" }));
        this.DurationBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.DurationBox4ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
        gridBagConstraints22.gridx = 4;
        gridBagConstraints22.gridy = 5;
        this.getContentPane().add(this.DurationBox4, gridBagConstraints22);
        this.DurationBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "75", "90", "120" }));
        this.DurationBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.DurationBox1ActionPerformed(actionEvent);
            }
        });
        this.DurationBox1.addVetoableChangeListener(new VetoableChangeListener() {
            @Override
            public void vetoableChange(final PropertyChangeEvent propertyChangeEvent) throws PropertyVetoException {
                DetailedActivityDialog.this.DurationBox1VetoableChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
        gridBagConstraints23.gridx = 4;
        gridBagConstraints23.gridy = 2;
        this.getContentPane().add(this.DurationBox1, gridBagConstraints23);
        this.DurationBox2.setModel(new DefaultComboBoxModel<String>(new String[] { "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "75", "90", "120" }));
        this.DurationBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.DurationBox2ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
        gridBagConstraints24.gridx = 4;
        gridBagConstraints24.gridy = 3;
        this.getContentPane().add(this.DurationBox2, gridBagConstraints24);
        this.DurationBox3.setForeground(new Color(153, 153, 153));
        this.DurationBox3.setModel(new DefaultComboBoxModel<String>(new String[] { "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "75", "90", "120" }));
        this.DurationBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.DurationBox3ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
        gridBagConstraints25.gridx = 4;
        gridBagConstraints25.gridy = 4;
        this.getContentPane().add(this.DurationBox3, gridBagConstraints25);
        this.FrequencyBox4.setForeground(new Color(153, 153, 153));
        this.FrequencyBox4.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        this.FrequencyBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.FrequencyBox4ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
        gridBagConstraints26.gridx = 6;
        gridBagConstraints26.gridy = 5;
        this.getContentPane().add(this.FrequencyBox4, gridBagConstraints26);
        this.FrequencyBox3.setForeground(new Color(153, 153, 153));
        this.FrequencyBox3.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        this.FrequencyBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.FrequencyBox3ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
        gridBagConstraints27.gridx = 6;
        gridBagConstraints27.gridy = 4;
        this.getContentPane().add(this.FrequencyBox3, gridBagConstraints27);
        this.FrequencyBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        this.FrequencyBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.FrequencyBox1ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
        gridBagConstraints28.gridx = 6;
        gridBagConstraints28.gridy = 2;
        this.getContentPane().add(this.FrequencyBox1, gridBagConstraints28);
        this.FrequencyBox2.setModel(new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        this.FrequencyBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.FrequencyBox2ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
        gridBagConstraints29.gridx = 6;
        gridBagConstraints29.gridy = 3;
        this.getContentPane().add(this.FrequencyBox2, gridBagConstraints29);
        this.SpaceLabel.setText("     ");
        final GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
        gridBagConstraints30.gridx = 0;
        gridBagConstraints30.gridy = 1;
        gridBagConstraints30.gridwidth = 9;
        this.getContentPane().add(this.SpaceLabel, gridBagConstraints30);
        this.jLabel11.setText("time(s) per ");
        final GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
        gridBagConstraints31.gridx = 7;
        gridBagConstraints31.gridy = 2;
        this.getContentPane().add(this.jLabel11, gridBagConstraints31);
        this.jLabel12.setText("time(s) per ");
        final GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
        gridBagConstraints32.gridx = 7;
        gridBagConstraints32.gridy = 5;
        this.getContentPane().add(this.jLabel12, gridBagConstraints32);
        this.jLabel13.setText("time(s) per ");
        final GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
        gridBagConstraints33.gridx = 7;
        gridBagConstraints33.gridy = 3;
        this.getContentPane().add(this.jLabel13, gridBagConstraints33);
        this.jLabel14.setText("time(s) per ");
        final GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
        gridBagConstraints34.gridx = 7;
        gridBagConstraints34.gridy = 4;
        this.getContentPane().add(this.jLabel14, gridBagConstraints34);
        this.OkButton.setText("OK");
        this.OkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.OkButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
        gridBagConstraints35.gridx = 2;
        gridBagConstraints35.gridy = 7;
        gridBagConstraints35.gridwidth = 2;
        this.getContentPane().add(this.OkButton, gridBagConstraints35);
        this.CancelButton.setText("Cancel");
        this.CancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.CancelButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints36 = new GridBagConstraints();
        gridBagConstraints36.gridx = 5;
        gridBagConstraints36.gridy = 7;
        gridBagConstraints36.gridwidth = 2;
        this.getContentPane().add(this.CancelButton, gridBagConstraints36);
        this.DayWeekBox1.setModel(new DefaultComboBoxModel<String>(new String[] { "day", "week" }));
        this.DayWeekBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.DayWeekBox1ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
        gridBagConstraints37.gridx = 8;
        gridBagConstraints37.gridy = 2;
        this.getContentPane().add(this.DayWeekBox1, gridBagConstraints37);
        this.DayWeekBox2.setModel(new DefaultComboBoxModel<String>(new String[] { "day", "week" }));
        this.DayWeekBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.DayWeekBox2ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints38 = new GridBagConstraints();
        gridBagConstraints38.gridx = 8;
        gridBagConstraints38.gridy = 3;
        this.getContentPane().add(this.DayWeekBox2, gridBagConstraints38);
        this.DayWeekBox3.setForeground(new Color(153, 153, 153));
        this.DayWeekBox3.setModel(new DefaultComboBoxModel<String>(new String[] { "day", "week" }));
        this.DayWeekBox3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.DayWeekBox3ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints39 = new GridBagConstraints();
        gridBagConstraints39.gridx = 8;
        gridBagConstraints39.gridy = 4;
        this.getContentPane().add(this.DayWeekBox3, gridBagConstraints39);
        this.DayWeekBox4.setForeground(new Color(153, 153, 153));
        this.DayWeekBox4.setModel(new DefaultComboBoxModel<String>(new String[] { "day", "week" }));
        this.DayWeekBox4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.DayWeekBox4ActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
        gridBagConstraints40.gridx = 8;
        gridBagConstraints40.gridy = 5;
        this.getContentPane().add(this.DayWeekBox4, gridBagConstraints40);
        this.jLabel4.setText("This will be a change of");
        final GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
        gridBagConstraints41.gridx = 0;
        gridBagConstraints41.gridy = 6;
        gridBagConstraints41.gridwidth = 4;
        gridBagConstraints41.anchor = 13;
        this.getContentPane().add(this.jLabel4, gridBagConstraints41);
        this.jLabel15.setText("percent of your baseline activity level");
        final GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
        gridBagConstraints42.gridx = 5;
        gridBagConstraints42.gridy = 6;
        gridBagConstraints42.gridwidth = 4;
        gridBagConstraints42.anchor = 17;
        this.getContentPane().add(this.jLabel15, gridBagConstraints42);
        this.LiveUpdatingCheckBox.setText("Live Updating");
        this.LiveUpdatingCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                DetailedActivityDialog.this.LiveUpdatingCheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints43 = new GridBagConstraints();
        gridBagConstraints43.gridx = 0;
        gridBagConstraints43.gridy = 0;
        gridBagConstraints43.gridwidth = 2;
        this.getContentPane().add(this.LiveUpdatingCheckBox, gridBagConstraints43);
        this.NewActChangePercField.setColumns(4);
        this.NewActChangePercField.setEditable(false);
        this.NewActChangePercField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        final GridBagConstraints gridBagConstraints44 = new GridBagConstraints();
        gridBagConstraints44.gridx = 4;
        gridBagConstraints44.gridy = 6;
        this.getContentPane().add(this.NewActChangePercField, gridBagConstraints44);
        this.pack();
    }
    
    private void CancelButtonActionPerformed(final ActionEvent actionEvent) {
        try {
            this.getintervention().setactchangepercent(this.oldactchangeperc);
            this.getbox().setSelectedItem(this.oldboxvalue);
            this.setVisible(false);
            this.mpanel.recalc();
        }
        catch (Exception ex) {}
    }
    
    private void OkButtonActionPerformed(final ActionEvent actionEvent) {
        try {
            this.remember();
            this.getintervention().setdetailed(true);
            this.update();
            this.getintervention().setactchangepercent(this.actchangeperc);
            this.mpanel.recalc();
            this.setVisible(false);
        }
        catch (Exception ex) {}
    }
    
    private void ActChange1ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void ActivityBox1ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void DurationBox1ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void FrequencyBox1ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void DayWeekBox1ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void ActChange2ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void ActivityBox2ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void DurationBox2ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void FrequencyBox2ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void DayWeekBox2ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void ActChange3ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void ActivityBox3ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void DurationBox3ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void FrequencyBox3ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void DayWeekBox3ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void ActChange4ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void ActivityBox4ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void DurationBox4ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void FrequencyBox4ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void DayWeekBox4ActionPerformed(final ActionEvent actionEvent) {
        this.update();
    }
    
    private void LiveUpdatingCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.LiveUpdatingCheckBox.isSelected()) {
            this.liveupdating = true;
            this.update();
        }
        else {
            this.liveupdating = false;
        }
    }
    
    private void DurationBox1VetoableChange(final PropertyChangeEvent propertyChangeEvent) throws PropertyVetoException {
        if (propertyChangeEvent.getPropertyName().equalsIgnoreCase("value")) {
            try {
                Double.parseDouble(propertyChangeEvent.getNewValue().toString());
            }
            catch (NumberFormatException ex) {
                throw new PropertyVetoException("Non-number", propertyChangeEvent);
            }
        }
    }
    
    private void ActCheck1ItemStateChanged(final ItemEvent itemEvent) {
        if (this.ActCheck1.isSelected()) {
            this.ActChange1.setForeground(this.activecolor);
            this.ActivityBox1.setForeground(this.activecolor);
            this.DurationBox1.setForeground(this.activecolor);
            this.FrequencyBox1.setForeground(this.activecolor);
            this.DayWeekBox1.setForeground(this.activecolor);
        }
        else {
            this.ActChange1.setForeground(this.inactivecolor);
            this.ActivityBox1.setForeground(this.inactivecolor);
            this.DurationBox1.setForeground(this.inactivecolor);
            this.FrequencyBox1.setForeground(this.inactivecolor);
            this.DayWeekBox1.setForeground(this.inactivecolor);
        }
        this.update();
    }
    
    private void ActCheck2StateChanged(final ChangeEvent changeEvent) {
        if (this.ActCheck2.isSelected()) {
            this.ActChange2.setForeground(this.activecolor);
            this.ActivityBox2.setForeground(this.activecolor);
            this.DurationBox2.setForeground(this.activecolor);
            this.FrequencyBox2.setForeground(this.activecolor);
            this.DayWeekBox2.setForeground(this.activecolor);
        }
        else {
            this.ActChange2.setForeground(this.inactivecolor);
            this.ActivityBox2.setForeground(this.inactivecolor);
            this.DurationBox2.setForeground(this.inactivecolor);
            this.FrequencyBox2.setForeground(this.inactivecolor);
            this.DayWeekBox2.setForeground(this.inactivecolor);
        }
        this.update();
    }
    
    private void ActCheck3StateChanged(final ChangeEvent changeEvent) {
        if (this.ActCheck3.isSelected()) {
            this.ActChange3.setForeground(this.activecolor);
            this.ActivityBox3.setForeground(this.activecolor);
            this.DurationBox3.setForeground(this.activecolor);
            this.FrequencyBox3.setForeground(this.activecolor);
            this.DayWeekBox3.setForeground(this.activecolor);
        }
        else {
            this.ActChange3.setForeground(this.inactivecolor);
            this.ActivityBox3.setForeground(this.inactivecolor);
            this.DurationBox3.setForeground(this.inactivecolor);
            this.FrequencyBox3.setForeground(this.inactivecolor);
            this.DayWeekBox3.setForeground(this.inactivecolor);
        }
        this.update();
    }
    
    private void ActCheck4StateChanged(final ChangeEvent changeEvent) {
        if (this.ActCheck4.isSelected()) {
            this.ActChange4.setForeground(this.activecolor);
            this.ActivityBox4.setForeground(this.activecolor);
            this.DurationBox4.setForeground(this.activecolor);
            this.FrequencyBox4.setForeground(this.activecolor);
            this.DayWeekBox4.setForeground(this.activecolor);
        }
        else {
            this.ActChange4.setForeground(this.inactivecolor);
            this.ActivityBox4.setForeground(this.inactivecolor);
            this.DurationBox4.setForeground(this.inactivecolor);
            this.FrequencyBox4.setForeground(this.inactivecolor);
            this.DayWeekBox4.setForeground(this.inactivecolor);
        }
        this.update();
    }
    
    public void update() {
        final double[] array = new double[this.activityindex];
        if (this.ActCheck1.isSelected()) {
            array[0] = this.changesign(this.ActChange1.getSelectedItem().toString()) * this.act2met(this.ActivityBox1.getSelectedItem().toString()) * Double.parseDouble(this.DurationBox1.getSelectedItem().toString()) / 60.0 * Double.parseDouble(this.FrequencyBox1.getSelectedItem().toString()) * this.dayweek(this.DayWeekBox1.getSelectedItem().toString());
        }
        if (this.ActCheck2.isSelected()) {
            array[1] = this.changesign(this.ActChange2.getSelectedItem().toString()) * this.act2met(this.ActivityBox2.getSelectedItem().toString()) * Double.parseDouble(this.DurationBox2.getSelectedItem().toString()) / 60.0 * Double.parseDouble(this.FrequencyBox2.getSelectedItem().toString()) * this.dayweek(this.DayWeekBox2.getSelectedItem().toString());
        }
        if (this.ActCheck3.isSelected()) {
            array[2] = this.changesign(this.ActChange3.getSelectedItem().toString()) * this.act2met(this.ActivityBox3.getSelectedItem().toString()) * Double.parseDouble(this.DurationBox3.getSelectedItem().toString()) / 60.0 * Double.parseDouble(this.FrequencyBox3.getSelectedItem().toString()) * this.dayweek(this.DayWeekBox3.getSelectedItem().toString());
        }
        if (this.ActCheck4.isSelected()) {
            array[3] = this.changesign(this.ActChange4.getSelectedItem().toString()) * this.act2met(this.ActivityBox4.getSelectedItem().toString()) * Double.parseDouble(this.DurationBox4.getSelectedItem().toString()) / 60.0 * Double.parseDouble(this.FrequencyBox4.getSelectedItem().toString()) * this.dayweek(this.DayWeekBox4.getSelectedItem().toString());
        }
        this.metmin_total = 0.0;
        for (int i = 0; i < array.length; ++i) {
            this.metmin_total += array[i];
        }
        this.actchangeperc = this.metmin_total / this.mpanel.getBaseline().getActParam() * 100.0;
        if (this.actchangeperc < -100.0) {
            this.actchangeperc = -100.0;
        }
        this.NewActChangePercField.setText(Double.toString((int)this.actchangeperc));
        if (this.liveupdating && !this.mpanel.isgoalsim()) {
            if (this.index == 1 && this.mpanel.getIntervention1().isdetailed()) {
                this.mpanel.getIntervention1().setactchangepercent(this.actchangeperc);
                this.mpanel.recalc();
            }
            if (this.index == 2 && this.mpanel.getIntervention2().isdetailed()) {
                this.mpanel.getIntervention2().setactchangepercent(this.actchangeperc);
                this.mpanel.recalc();
            }
        }
    }
    
    public static void main(final String[] array) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final DetailedActivityDialog detailedActivityDialog = new DetailedActivityDialog(new JFrame(), true, new MainPanel(), null, 0);
                detailedActivityDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(final WindowEvent windowEvent) {
                        System.exit(0);
                    }
                });
                detailedActivityDialog.setVisible(true);
            }
        });
    }
}
