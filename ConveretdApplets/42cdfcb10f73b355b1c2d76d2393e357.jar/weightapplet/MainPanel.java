// 
// Decompiled by Procyon v0.5.30
// 

package weightapplet;

import java.io.PrintStream;
import javax.swing.JFileChooser;
import java.awt.Insets;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.text.DefaultFormatterFactory;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.border.Border;
import javax.swing.Icon;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import javax.swing.JOptionPane;
import java.awt.Frame;
import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;

public class MainPanel extends JPanel
{
    private Baseline Baseline;
    private boolean init_Male;
    private double init_Age;
    private double init_Height;
    private double init_Weight;
    private double init_Bfp;
    private double init_RMR;
    private double init_PAL;
    private Intervention Intervention1;
    private Intervention Intervention2;
    private Intervention GoalIntervention;
    private Intervention GoalMaintenanceIntervention;
    private double GoalWeight;
    private double GoalBfp;
    private double TimetoGoal;
    private double WeightatGoal;
    private double BfpAtGoal;
    private double GoalCals;
    private double GoalMaintCals;
    private double OldWeight;
    private double OldPAL;
    private double OldRMR;
    private double OldTimetoGoal;
    private double OldGoalWeight;
    private double OldGoalActChange;
    private double OldGoalMaintActChange;
    private boolean OldMale;
    private String change;
    private double SimulationLength;
    private double FinalWeight;
    private double FinalBfp;
    private double FinalBMI;
    private double FinalRMR;
    private double weightunits;
    private double heightunits;
    private double energyunits;
    private String energystring;
    private boolean simpleversion;
    private boolean liveupdating;
    private boolean goalsim;
    private boolean calcflag;
    private double MinCals;
    private static double eps;
    private boolean warningson;
    private boolean init;
    private double check;
    private Color black;
    private Color grey;
    private Color highlightgreen;
    private Color highlightyellow;
    private Color highlightblue;
    private Color overviewhighlight;
    private JFrame Mainframe;
    private GraphPanel gpanel;
    private WeightApplet applet;
    private JFrame gframe;
    private double timestart;
    private double timezoom;
    private double vertical_zoom;
    private int[] graphparams;
    private boolean graphattached;
    private double spread_percent;
    private PAL_Dialog pdialog;
    private DetailedActivityDialog inter1actdialog;
    private DetailedActivityDialog inter2actdialog;
    private DetailedActivityDialog goalactdialog;
    private DetailedActivityDialog goalmaintactdialog;
    private JCheckBox AdvancedControlsCheckBox;
    private JFormattedTextField AgeField;
    private JLabel AgeLabel;
    private JLabel BaselineLabel;
    private JPanel BaselinePanel;
    private ButtonGroup BfpRadioGroup;
    private JSeparator BfpSeparator;
    private JRadioButton CaloriesRadio;
    private JRadioButton CentimetersRadio;
    private JTabbedPane ChoicePanel;
    private JPanel ComparePanel;
    private JButton DefaultButton;
    private JCheckBox DefaultColorsCheckBox;
    private ButtonGroup EnergyUnitsRadioGroup;
    private JButton EstimatePALButton;
    private JFormattedTextField FinalBMIField;
    private JLabel FinalBMILabel;
    private JFormattedTextField FinalBfpField;
    private JLabel FinalBfpLabel;
    private JFormattedTextField FinalWeightField;
    private JLabel FinalWeightLabel;
    private JComboBox GenderBox;
    private JLabel GenderLabel;
    private ButtonGroup GenderRadioGroup;
    private JComboBox GoalActChangeBox;
    private JLabel GoalActivityLabel;
    private JLabel GoalCaloriesLabel;
    private JFormattedTextField GoalCalsChangeField;
    private JLabel GoalCalsChangeLabel;
    private JFormattedTextField GoalCalsField;
    private JCheckBox GoalCheckBox;
    private JLabel GoalDaysLabel;
    private JLabel GoalInterventionLabel;
    private JFormattedTextField GoalMaintCalsChangeField;
    private JLabel GoalMaintCalsChangeLabel;
    private JFormattedTextField GoalMaintCalsField;
    private JLabel GoalMaintCalsLabel;
    private JComboBox GoalMaintenanceActChangeBox;
    private JLabel GoalMaintenanceActivityLabel;
    private JLabel GoalMaintenanceInterventionLabel;
    private JPanel GoalPanel;
    private JLabel GoalSpaceLabel;
    private JPanel GoalStatementPanel;
    private JFormattedTextField GoalTimeField;
    private JLabel GoalUnitsLabel;
    private JFormattedTextField GoalWeightField;
    private JLabel GoalWeightLabel;
    private JCheckBox GraphAttachedCheckBox;
    private JCheckBox GridCheckBox;
    private JFormattedTextField HeightField;
    private JLabel HeightLabel;
    private ButtonGroup HeightUnitsGroup;
    private JCheckBox HighlightCheckBox;
    private JRadioButton InchesRadio;
    private JFormattedTextField InitialBMIField;
    private JLabel InitialBMILabel;
    private JRadioButton InitialBfpCalculatedRadio;
    private JFormattedTextField InitialBfpField;
    private JFormattedTextField InitialBfpField2;
    private JRadioButton InitialBfpInputRadio;
    private JLabel InitialBfpLabel;
    private JLabel InitialBfpLabel2;
    private JFormattedTextField InitialCarbInPercField;
    private JLabel InitialCarbInPercLabel;
    private JFormattedTextField InitialPALField;
    private JFormattedTextField InitialRMRField;
    private JLabel InitialRMRLabel;
    private JFormattedTextField InitialSodiumField;
    private JLabel InitialSodiumLabel;
    private JFormattedTextField InitialWeightField;
    private JFormattedTextField InitialWeightField2;
    private JLabel InitialWeightLabel;
    private JLabel InitialWeightLabel2;
    private JComboBox Intervention1ActChangeBox;
    private JLabel Intervention1ActivityLabel;
    private JFormattedTextField Intervention1CaloriesField;
    private JLabel Intervention1CaloriesLabel;
    private JFormattedTextField Intervention1CarbInPercField;
    private JLabel Intervention1CarbInPercLabel;
    private JCheckBox Intervention1CheckBox;
    private JFormattedTextField Intervention1DayField;
    private JLabel Intervention1DayLabel;
    private JLabel Intervention1Label;
    private JCheckBox Intervention1RampCheckBox;
    private JRadioButton Intervention1SodiumCalculatedRadio;
    private JFormattedTextField Intervention1SodiumField;
    private JRadioButton Intervention1SodiumInputRadio;
    private JLabel Intervention1SodiumLabel;
    private ButtonGroup Intervention1SodiumRadioGroup;
    private JSeparator Intervention1SodiumSeparator;
    private JComboBox Intervention2ActChangeBox;
    private JLabel Intervention2ActivityLabel;
    private JFormattedTextField Intervention2CaloriesField;
    private JLabel Intervention2CaloriesLabel;
    private JFormattedTextField Intervention2CarbInPercField;
    private JLabel Intervention2CarbInPercLabel;
    private JCheckBox Intervention2CheckBox;
    private JFormattedTextField Intervention2DayField;
    private JLabel Intervention2DayLabel;
    private JLabel Intervention2Label;
    private JCheckBox Intervention2RampCheckBox;
    private JRadioButton Intervention2SodiumCalculatedRadio;
    private JFormattedTextField Intervention2SodiumField;
    private JRadioButton Intervention2SodiumInputRadio;
    private JLabel Intervention2SodiumLabel;
    private ButtonGroup Intervention2SodiumRadioGroup;
    private JSeparator Intervention2SodiumSeparator;
    private JPanel InterventionPanel;
    private JRadioButton KilogramsRadio;
    private JRadioButton KilojoulesRadio;
    private JFormattedTextField MaintCalsField;
    private JLabel MaintCalsLabel;
    private JPanel OptionsPanel;
    private JButton OverviewButton;
    private JLabel PALLabel;
    private JRadioButton PoundsRadio;
    private JRadioButton RMRCalculatedRadio;
    private JRadioButton RMRInputRadio;
    private ButtonGroup RMRRadioGroup;
    private JSeparator RMRSeparator;
    private JButton RunGoalButton;
    private JButton RunInterventionButton;
    private JButton SaveButton;
    private JCheckBox ShowLegendCheckBox;
    private JFormattedTextField SimulationLengthField;
    private JLabel SimulationLengthLabel;
    private JLabel SpaceLabel;
    private JLabel StatusLabel;
    private JLabel TimeStartLabel;
    private JLabel TimeStartMaxLabel;
    private JLabel TimeStartMinLabel;
    private JSlider TimeStartSlider;
    private JLabel TimeZoomLabel;
    private JLabel TimeZoomMaxLabel;
    private JLabel TimeZoomMinLabel;
    private JSlider TimeZoomSlider;
    private JComboBox UncertaintyBox;
    private JCheckBox UncertaintyCheckBox;
    private JLabel UncertaintyLabel;
    private JLabel VerticalZoomLabel;
    private JLabel VerticalZoomMaxLabel;
    private JLabel VerticalZoomMinLabel;
    private JSlider VerticalZoomSlider;
    private ButtonGroup WeightUnitsRadioGroup;
    private JPanel ZoomPanel;
    private JPanel jPanel2;
    private JSeparator jSeparator1;
    private JSeparator jSeparator2;
    private JSeparator jSeparator3;
    private JSeparator jSeparator4;
    
    public static void main(final String[] array) {
        System.out.println("Main method of main panel");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final MainPanel mainPanel = new MainPanel();
            }
        });
    }
    
    public GraphPanel getgraphpanel() {
        return this.gpanel;
    }
    
    public MainPanel() {
        this((WeightApplet)null);
    }
    
    public boolean isApplet() {
        return this.applet != null;
    }
    
    private void createFrame() {
        this.Mainframe = new JFrame();
        if (!this.isApplet()) {
            this.Mainframe.setDefaultCloseOperation(3);
        }
        else {
            this.Mainframe.setDefaultCloseOperation(2);
        }
        this.Mainframe.setTitle("Human Weight Simulator");
        this.Mainframe.getContentPane().add(this);
        this.Mainframe.pack();
        System.out.println("MainPanel visible");
        this.Mainframe.setVisible(true);
    }
    
    public MainPanel(final WeightApplet applet) {
        this.init_Male = true;
        this.init_Age = 23.0;
        this.init_Height = 180.0;
        this.init_Weight = 70.0;
        this.init_Bfp = 18.0;
        this.init_RMR = 1709.0;
        this.init_PAL = 1.5;
        this.weightunits = 1.0;
        this.heightunits = 1.0;
        this.energyunits = 1.0;
        this.energystring = "Calories";
        this.simpleversion = false;
        this.goalsim = true;
        this.calcflag = false;
        this.MinCals = 0.0;
        this.warningson = true;
        this.black = new Color(0, 0, 0);
        this.grey = new Color(102, 102, 102);
        this.highlightgreen = new Color(51, 251, 51);
        this.highlightyellow = new Color(255, 255, 10);
        this.highlightblue = new Color(51, 255, 255);
        this.overviewhighlight = new Color(255, 0, 255);
        this.graphparams = new int[] { 0, 0, 0 };
        this.graphattached = true;
        this.spread_percent = 10.0;
        this.init = true;
        this.applet = applet;
        this.Baseline = new Baseline();
        this.initComponents();
        this.remove(this.jPanel2);
        this.initValues();
        this.initGraph();
        this.pdialog = new PAL_Dialog(null, false, this);
        (this.inter1actdialog = new DetailedActivityDialog(null, false, this, "In the first phase, I will change my activity by:", 1)).setVisible(false);
        (this.inter2actdialog = new DetailedActivityDialog(null, false, this, "In the second phase, I will change my activity by:", 2)).setVisible(false);
        (this.goalactdialog = new DetailedActivityDialog(null, false, this, "In my weight change phase, I will change my activity by:", 3)).setVisible(false);
        (this.goalmaintactdialog = new DetailedActivityDialog(null, false, this, "In my weight maintenance phase, I will change my activity by:", 4)).setVisible(false);
        System.out.println("Things made, now going to listen");
        System.out.println("Listening, now going to recalc");
        this.init = false;
        this.recalc();
        this.ChoicePanel.setSelectedIndex(0);
        this.HeightUnitsChange();
        this.EnergyUnitsChange();
        this.AdvancedControlsCheckBox.setSelected(false);
        this.simplify();
        this.createFrame();
        System.out.println("Thing calculated, should be smooth from here");
        if (JOptionPane.showConfirmDialog(this.Mainframe, "Welcome to the Human Weight Simulator developed at the Lab of Biological Modeling, NIDDK.\nWould you like an overview of how to use the simulator?", "Welcome", 0) == 0) {
            this.walkthrough();
        }
        if (this.isApplet()) {
            this.OptionsPanel.remove(this.SaveButton);
        }
        this.OptionsPanel.repaint();
    }
    
    public void initGraph() {
        if (this.init) {
            this.gframe = new JFrame();
            (this.gpanel = new GraphPanel(this, this.Baseline, this.Intervention1, this.Intervention2, this.SimulationLength, this.graphparams)).setPreferredSize(new Dimension(800, 250));
        }
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        if (this.graphattached) {
            System.out.print("ATTACHING graph");
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 3;
            gridBagConstraints.gridwidth = 3;
            gridBagConstraints.fill = 1;
            gridBagConstraints.ipady = 150;
            if (!this.init) {
                this.add(this.jPanel2, gridBagConstraints);
            }
            this.add(this.gpanel, gridBagConstraints);
            if (this.Mainframe != null) {
                this.Mainframe.pack();
            }
        }
        else if (!this.graphattached) {
            this.gpanel.setSize(this.jPanel2.getPreferredSize());
            this.gframe.getContentPane().add(this.gpanel);
            this.gframe.pack();
        }
        if (this.init) {
            this.WeightUnitsChange();
        }
    }
    
    public void setgraphparams() {
        this.timestart = this.TimeStartSlider.getValue() / this.TimeStartSlider.getMaximum();
        this.timezoom = this.TimeZoomSlider.getValue() / this.TimeZoomSlider.getMaximum();
        this.vertical_zoom = this.VerticalZoomSlider.getValue() / this.VerticalZoomSlider.getMaximum();
    }
    
    public PAL_Dialog getPAL_Dialog() {
        return this.pdialog;
    }
    
    public void initValues() {
        this.calcflag = true;
        this.Baseline = new Baseline(this.init_Male, this.init_Age, this.init_Height, this.init_Weight, this.init_Bfp, this.init_RMR, this.init_PAL);
        this.InitialWeightField.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
        this.InitialWeightField2.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
        this.InitialBfpField.setValue(this.Baseline.getBfp());
        this.InitialBfpField2.setValue(this.Baseline.getBfp());
        this.HeightField.setValue(this.Baseline.getHeight_cms() * this.heightunits);
        this.AgeField.setValue(this.Baseline.getAge());
        this.GenderBox.setSelectedItem(this.Baseline.getMale() ? "Male" : "Female");
        this.InitialPALField.setValue(this.Baseline.getPAL());
        this.InitialCarbInPercField.setValue(this.Baseline.getCarbInPercent());
        this.InitialSodiumField.setValue(this.Baseline.getSodium());
        this.InitialRMRField.setValue(this.Baseline.getRMR() * this.energyunits);
        this.GoalWeight = 70.0;
        this.GoalWeightField.setValue(this.GoalWeight * this.weightunits);
        this.GoalBfp = 25.0;
        this.GoalMaintCals = new BodyModel(this.GoalWeight * this.GoalBfp / 100.0, this.GoalWeight * (100.0 - this.GoalBfp) / 100.0, 0.0, 0.5, 0.24 * this.Baseline.getMaintCals()).cals4balance(this.Baseline, this.Baseline.getActParam());
        this.TimetoGoal = 180.0;
        this.GoalTimeField.setValue(this.TimetoGoal);
        this.SimulationLength = 365.0;
        this.SimulationLengthField.setValue(this.SimulationLength);
        this.WeightUnitsChange();
        this.goalsim = true;
        (this.Intervention1 = new Intervention(90, 2000.0, 50.0, 0.0, 4000.0)).setproportionalsodium(this.Baseline);
        this.Intervention1.setTitle("Intervention1");
        this.Intervention1DayField.setValue(this.Intervention1.getday());
        this.Intervention1SodiumField.setValue(this.Intervention1.getsodium());
        this.Intervention1CarbInPercField.setValue(this.Intervention1.getcarbinpercent());
        this.Intervention1CaloriesField.setValue(this.Intervention1.getcalories() * this.energyunits);
        this.Intervention1ActChangeBox.setSelectedItem(Double.toString(this.Intervention1.getactchangepercent()));
        (this.Intervention2 = new Intervention(180, 2400.0, 50.0, 0.0, 4000.0)).setproportionalsodium(this.Baseline);
        this.Intervention2.setTitle("Intervention2");
        this.Intervention2DayField.setValue(this.Intervention2.getday());
        this.Intervention2SodiumField.setValue(this.Intervention2.getsodium());
        this.Intervention2CarbInPercField.setValue(this.Intervention1.getcarbinpercent());
        this.Intervention2CaloriesField.setValue(this.Intervention2.getcalories() * this.energyunits);
        this.Intervention2ActChangeBox.setSelectedItem(Double.toString(this.Intervention2.getactchangepercent()));
        (this.GoalIntervention = new Intervention(1, 2000.0, this.Baseline.getCarbInPercent(), 0.0, this.Baseline.getSodium() * 2000.0 / this.Baseline.getMaintCals())).setTitle("Goal Intervention");
        (this.GoalMaintenanceIntervention = new Intervention((int)this.TimetoGoal + 1, this.GoalMaintCals, this.Baseline.getCarbInPercent(), 0.0, this.Baseline.getSodium() * this.GoalMaintCals / this.Baseline.getMaintCals())).setTitle("Goal Maintenance Intervention");
        System.out.println("Values entered");
        this.calcflag = false;
    }
    
    public void walkthrough() {
        final Object[] array = { "Continue", "Cancel" };
        this.BaselinePanel.setBorder(BorderFactory.createLineBorder(this.overviewhighlight, 6));
        final int showOptionDialog = JOptionPane.showOptionDialog(this, "First enter the information needed to set the starting or \"baseline\" conditions such as your initial weight,\nsex, age, height, and physical activity level. \n\nIf you're not sure of your Physical Activity Level, click the \"Estimate Activity Level\" button\nand answer the two questions.\n\nThe Simulator will automatically calculate your \"Baseline Diet\" that specifies the daily energy intake\nrequired to maintain your initial weight.\n\nYou can also use this panel to change the displayed units for weight, height, and energy.", "Setting the Baseline", 2, 1, null, array, "Continue");
        this.BaselinePanel.setBorder(BorderFactory.createEtchedBorder());
        if (showOptionDialog == 1) {
            return;
        }
        this.ChoicePanel.setSelectedIndex(0);
        this.GoalPanel.setBorder(BorderFactory.createLineBorder(this.overviewhighlight, 6));
        final int showOptionDialog2 = JOptionPane.showOptionDialog(this, "Click the \"Set Your Goal Weight\" tab to simulate how to achieve your goal weight.\n\nStart by entering a goal body weight and how many days you plan to take to achieve that weight.\n\nThe Simulator will calculate the diet required to reach your goal weight in the specified number of days\nas well as the permanent diet required to maintain that goal weight. There may be some difference in the\nfinal maintained weight due to changes in body water.\n \nNote that these calculations take into account the specified changes of physical activity for both diet phases.\n\nIf your goal is not achievable in the specified time period, a warning message will be provided\nand your last change will be reverted.\n   \nBy selected \"Detailed\" in the activity boxes, you can specify a detailed change in your activity regimen", "Setting a Goal", 2, 1, null, array, "Continue");
        this.GoalPanel.setBorder(null);
        if (showOptionDialog2 == 1) {
            return;
        }
        this.ChoicePanel.setSelectedIndex(1);
        this.InterventionPanel.setBorder(BorderFactory.createLineBorder(this.overviewhighlight, 6));
        final int showOptionDialog3 = JOptionPane.showOptionDialog(this, "You can also enter planned changes in your diet and activity and calculate how much these would change your weight\nby clicking the \"Specify Lifestyle Change\" tab.\n \nYou can enter up to two different changes of diet and physical activity beginning at the specified times.\nThese changes can be turned on and off using the check boxes.", "Entering Lifestyle Changes", 2, 1, null, array, "Continue");
        this.InterventionPanel.setBorder(null);
        this.ChoicePanel.setSelectedIndex(0);
        if (showOptionDialog3 == 1) {
            return;
        }
        this.gpanel.setBorder(BorderFactory.createLineBorder(this.overviewhighlight, 6));
        final int showOptionDialog4 = JOptionPane.showOptionDialog(this, "By clicking the \"Run Simulation\" button simulation results will display in the bottom panel.\n\nThe first tab displays a graph of the Body Weight versus Time. The second tab displays a graph of the \nBody Fat percentage versus Time. The third tab displays Energy Intake and Expenditure versus Time.\nThe fourth tab displays the model outputs in tabulated form.\n\nYour initial and final weight, percent body fat, and BMI (Body Mass Index) are given above the graph.", "Display Simulation Outputs", 2, 1, null, array, "Continue");
        this.gpanel.setBorder(null);
        if (showOptionDialog4 == 1) {
            return;
        }
        this.ZoomPanel.setBorder(BorderFactory.createLineBorder(this.overviewhighlight, 6));
        final int showOptionDialog5 = JOptionPane.showOptionDialog(this.ChoicePanel, "Use the central panel to enter the length of the simulation and control the graphical display.\n\nChange the number of days in the \"Length of Simulation\" Field to change how many days of the simulation\nare calculated.\n\nThe \"" + this.TimeStartLabel.getText() + "\" slider changes the time when the graph begins plotting the data.\n" + "\n" + "The \"" + this.TimeZoomLabel.getText() + "\" slider allows you to change the horizontal scale of the graph.\n" + "\n" + "The \"" + this.VerticalZoomLabel.getText() + "\" slider allows you to change the vertical scale of the graph.", "Graph Controls", 2, 1, null, array, "Continue");
        this.ZoomPanel.setBorder(BorderFactory.createEtchedBorder());
        if (showOptionDialog5 == 1) {
            return;
        }
        this.OptionsPanel.setBorder(BorderFactory.createLineBorder(this.overviewhighlight, 6));
        final int showOptionDialog6 = JOptionPane.showOptionDialog(this.ChoicePanel, "The options panel on the right allows for various advanced options, restoring default values, and can be used to repeat this overview.\n\nEnabling \"Advanced Controls\" provides the ability to enter baseline information about resting metabolic rate (RMR) and body fat as well\nas specify more detailed diet changes such as dietary carbohydrate and sodium. Gradual lifestyle changes can also be implemented using the advanced controls.\n\nHighlights draw attention to the Simulator's inputs and outputs. Standard inputs are in green, advanced control inputs are in blue\nand outputs are in yellow\n\nGraph options include the ability to show an upper and lower weight trajectories around the main model prediction;\nthese reflect the uncertainty in the initial state of energy balance.\n\nYou can also display a grid or detach the graph from the main window, as well as change the color scheme.", "Options", 2, 1, null, array, "Continue");
        this.OptionsPanel.setBorder(BorderFactory.createEtchedBorder());
        if (showOptionDialog6 == 1) {
            return;
        }
        JOptionPane.showMessageDialog(this, "This concludes the Simulator overview. You can repeat it by clicking the \"Help\" button in the options panel.\nPlease send your comments and questions to Kevin Hall at kevinh@niddk.nih.gov ", "Have Fun!", 1);
    }
    
    public void syncbase() {
        this.InitialWeightField.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
    }
    
    public JComboBox getIntervention1ActChangeBox() {
        return this.Intervention1ActChangeBox;
    }
    
    public Intervention getIntervention1() {
        return this.Intervention1;
    }
    
    public JComboBox getIntervention2ActChangeBox() {
        return this.Intervention2ActChangeBox;
    }
    
    public Intervention getIntervention2() {
        return this.Intervention2;
    }
    
    public boolean isgoalsim() {
        return this.goalsim;
    }
    
    public boolean defaultcolors() {
        return this.DefaultColorsCheckBox.isSelected();
    }
    
    public Intervention getGoalIntervention() {
        return this.GoalIntervention;
    }
    
    public Intervention getGoalMaintenanceIntervention() {
        return this.GoalMaintenanceIntervention;
    }
    
    public JComboBox getGoalActChangeBox() {
        return this.GoalActChangeBox;
    }
    
    public JComboBox getGoalMaintActChangeBox() {
        return this.GoalMaintenanceActChangeBox;
    }
    
    public JPanel getBaselinePanel() {
        return this.BaselinePanel;
    }
    
    protected void WeightUnitsChange() {
        if (this.KilogramsRadio.isSelected()) {
            this.weightunits = 1.0;
            this.GoalUnitsLabel.setText("kg in");
        }
        else if (this.PoundsRadio.isSelected()) {
            this.weightunits = 2.20462;
            this.GoalUnitsLabel.setText("lbs in");
        }
        if (!this.init) {
            this.gpanel.setweightunits(this.weightunits);
        }
        this.calcflag = true;
        this.InitialWeightField.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
        this.InitialWeightField2.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
        this.GoalWeightField.setValue(this.GoalWeight * this.weightunits);
        this.FinalWeightField.setValue(this.FinalWeight * this.weightunits);
        this.calcflag = false;
    }
    
    private void HeightUnitsChange() {
        System.out.println("Changing heightunits");
        if (this.CentimetersRadio.isSelected()) {
            this.heightunits = 1.0;
        }
        else if (this.InchesRadio.isSelected()) {
            this.heightunits = 0.3937;
        }
        this.calcflag = true;
        this.HeightField.setValue(this.Baseline.getHeight_cms() * this.heightunits);
        this.calcflag = false;
    }
    
    private void BfpRadioChange() {
        if (this.InitialBfpInputRadio.isSelected()) {
            this.Baseline.setcalculatedBfp(false);
            this.InitialBfpField.setEditable(true);
            this.InitialBfpField.setForeground(this.black);
            if (this.HighlightCheckBox.isSelected()) {
                this.InitialBfpField.setBackground(this.highlightblue);
            }
        }
        else if (this.InitialBfpCalculatedRadio.isSelected()) {
            this.Baseline.setcalculatedBfp(true);
            this.InitialBfpField.setEditable(false);
            this.InitialBfpField.setForeground(this.grey);
            this.InitialBfpField.setBackground(Color.white);
            if (!this.InitialBfpField.getValue().equals(this.Baseline.getBfp())) {
                this.recalc();
            }
        }
    }
    
    private void RMRRadioChange() {
        if (this.RMRCalculatedRadio.isSelected()) {
            this.Baseline.setcalculatedRMR(true);
            this.InitialRMRField.setEditable(false);
            this.InitialRMRField.setForeground(this.grey);
            this.InitialRMRField.setBackground(Color.white);
            if (!this.InitialRMRField.getValue().equals(this.Baseline.getRMR() * this.energyunits)) {
                this.recalc();
            }
        }
        else if (this.RMRInputRadio.isSelected()) {
            this.Baseline.setcalculatedRMR(false);
            this.InitialRMRField.setEditable(true);
            this.InitialRMRField.setForeground(this.black);
            if (this.HighlightCheckBox.isSelected()) {
                this.InitialRMRField.setBackground(this.highlightblue);
            }
        }
    }
    
    private void Intervention1SodiumRadioChange() {
        if (this.Intervention1SodiumCalculatedRadio.isSelected()) {
            this.Intervention1.setproportionalsodium(this.Baseline);
            this.Intervention1SodiumField.setValue(this.Intervention1.getsodium());
            this.Intervention1SodiumField.setEditable(false);
            this.Intervention1SodiumField.setForeground(this.grey);
            this.Intervention1SodiumField.setBackground(Color.white);
            if (!this.goalsim && this.Intervention1.ison()) {
                this.recalc();
            }
        }
        else if (this.Intervention1SodiumInputRadio.isSelected()) {
            this.Intervention1SodiumField.setEditable(true);
            this.Intervention1SodiumField.setForeground(this.black);
            if (this.HighlightCheckBox.isSelected()) {
                this.Intervention1SodiumField.setBackground(this.highlightblue);
            }
        }
    }
    
    private void Intervention2SodiumRadioChange() {
        if (this.Intervention2SodiumCalculatedRadio.isSelected()) {
            this.Intervention2.setproportionalsodium(this.Baseline);
            this.Intervention2SodiumField.setEditable(false);
            this.Intervention2SodiumField.setForeground(this.grey);
            this.Intervention2SodiumField.setBackground(Color.white);
            this.Intervention2SodiumField.setValue(this.Intervention2.getsodium());
            if (!this.goalsim && this.Intervention2.ison()) {
                this.recalc();
            }
        }
        else if (this.Intervention2SodiumInputRadio.isSelected()) {
            this.Intervention2SodiumField.setEditable(true);
            this.Intervention2SodiumField.setForeground(this.black);
            if (this.HighlightCheckBox.isSelected()) {
                this.Intervention2SodiumField.setBackground(this.highlightblue);
            }
        }
    }
    
    public void syncPAL() {
        System.out.println("baseline PAL=" + this.Baseline.getPAL());
        this.InitialPALField.setValue(this.Baseline.getPAL());
    }
    
    public void interventionupdate() {
        if (this.Intervention1SodiumCalculatedRadio.isSelected()) {
            this.Intervention1.setproportionalsodium(this.Baseline);
            System.out.println("inter1 set prop in recalc, =" + this.Intervention1.getsodium());
            this.Intervention1SodiumField.setValue(this.Intervention1.getsodium());
        }
        if (this.Intervention2SodiumCalculatedRadio.isSelected()) {
            this.Intervention2.setproportionalsodium(this.Baseline);
            this.Intervention2SodiumField.setValue(this.Intervention2.getsodium());
        }
    }
    
    public void recalc() {
        this.calcflag = true;
        this.InitialBfpField.setValue(this.Baseline.getBfp());
        this.InitialBfpField2.setValue(this.Baseline.getBfp());
        this.MaintCalsField.setValue(this.Baseline.getMaintCals() * this.energyunits);
        this.InitialBMIField.setValue(this.Baseline.getBMI());
        this.InitialRMRField.setValue(this.Baseline.getRMR() * this.energyunits);
        this.gpanel.setcalspread(this.Baseline.getMaintCals() * this.spread_percent / 100.0);
        if (!this.init) {
            this.Baseline.getFatWt();
            this.Baseline.getLeanWt();
            this.Baseline.getActParam();
            this.Baseline.getCarbInPercent();
            if (this.GoalCheckBox.isSelected()) {
                try {
                    this.GoalIntervention = Intervention.forgoal(this.Baseline, this.GoalWeight, this.TimetoGoal, this.GoalIntervention.getactchangepercent(), this.MinCals, MainPanel.eps);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    this.redo();
                    this.recalc();
                    return;
                }
                this.GoalCals = this.GoalIntervention.getcalories();
                this.GoalCalsField.setValue(this.GoalIntervention.getcalories() * this.energyunits);
                this.GoalCalsChangeField.setValue((this.GoalIntervention.getcalories() - this.Baseline.getMaintCals()) * this.energyunits);
                final BodyModel projectFromBaseline = BodyModel.projectFromBaseline(this.Baseline, this.GoalIntervention, this.TimetoGoal + 1.0);
                this.WeightatGoal = this.Baseline.getnewWeight(projectFromBaseline);
                this.BfpAtGoal = projectFromBaseline.getFatPercent(this.Baseline);
                if (this.GoalWeight == this.Baseline.getWeight_kgs()) {
                    this.GoalMaintCals = this.Baseline.getMaintCals();
                }
                else {
                    this.GoalMaintCals = projectFromBaseline.cals4balance(this.Baseline, this.GoalMaintenanceIntervention.getAct(this.Baseline));
                    System.out.printf("Goal therm=%f, bad approx gives stabletherm=%f\n", projectFromBaseline.getTherm(), 0.13999999999999999 * this.GoalMaintCals);
                }
                if (this.GoalMaintCals < 0.0) {
                    this.redo();
                }
                this.GoalMaintenanceIntervention.setday((int)this.TimetoGoal + 1);
                this.GoalMaintenanceIntervention.setcalories(this.GoalMaintCals);
                this.GoalMaintenanceIntervention.setcarbinpercent(this.Baseline.getCarbInPercent());
                this.GoalMaintenanceIntervention.setproportionalsodium(this.Baseline);
                final DailyParams dailyParams = new DailyParams(this.GoalMaintenanceIntervention, this.Baseline);
                System.out.println("Check goal maintenance intervention");
                System.out.println("fc=" + projectFromBaseline.dfdt(this.Baseline, dailyParams) + ", lc=" + projectFromBaseline.dldt(this.Baseline, dailyParams) + ", glyc=" + projectFromBaseline.dgdt(this.Baseline, dailyParams) + ", decwc=" + projectFromBaseline.dDecwdt(this.Baseline, dailyParams));
                this.GoalMaintCalsField.setValue(this.GoalMaintenanceIntervention.getcalories() * this.energyunits);
                this.GoalMaintCalsChangeField.setValue((this.GoalMaintenanceIntervention.getcalories() - this.Baseline.getMaintCals()) * this.energyunits);
            }
            else if (!this.GoalCheckBox.isSelected()) {
                this.GoalIntervention.seton(false);
                this.GoalMaintenanceIntervention.seton(false);
            }
            if (this.Intervention1SodiumCalculatedRadio.isSelected()) {
                this.Intervention1.setproportionalsodium(this.Baseline);
                this.Intervention1SodiumField.setValue(this.Intervention1.getsodium());
            }
            if (this.Intervention2SodiumCalculatedRadio.isSelected()) {
                this.Intervention2.setproportionalsodium(this.Baseline);
                this.Intervention2SodiumField.setValue(this.Intervention2.getsodium());
            }
            if (this.goalsim) {
                try {
                    System.out.println("Setting goal mode with Goalscals=" + this.GoalCals);
                    this.gpanel.calculate(this.Baseline, this.GoalIntervention, this.GoalMaintenanceIntervention, this.SimulationLength);
                    this.StatusLabel.setText("Goal Simulation Displayed");
                }
                catch (Exception ex2) {
                    System.out.println("GoalView Failed");
                    ex2.printStackTrace();
                    this.StatusLabel.setText("Error running goal simulahtion");
                }
            }
            else {
                try {
                    System.out.println("Setting intervention view with ecw on");
                    this.gpanel.calculate(this.Baseline, this.Intervention1, this.Intervention2, this.SimulationLength);
                    this.StatusLabel.setText("Lifestyle Simulation Displayed");
                }
                catch (Exception ex3) {
                    this.StatusLabel.setText("Error running intervention simulation");
                }
            }
            this.setgraphparams();
            this.gpanel.setparams(this.timestart, this.timezoom, this.vertical_zoom);
            this.gpanel.repaint();
            final BodyModel getfinalbc = this.gpanel.getfinalbc();
            this.FinalWeight = getfinalbc.getWeight(this.Baseline);
            this.FinalWeightField.setValue(this.FinalWeight * this.weightunits);
            this.FinalBfp = getfinalbc.getFatPercent(this.Baseline);
            this.FinalBfpField.setValue(this.FinalBfp);
            this.FinalBMI = getfinalbc.getBMI(this.Baseline);
            this.FinalBMIField.setValue(this.FinalBMI);
            final double getnewBMI = this.Baseline.getnewBMI(this.gpanel.getminweight_kgs());
            if (getnewBMI < 19.0 && Math.abs(getnewBMI - this.Baseline.getBMI()) > 0.1 && this.warningson) {
                System.out.println("low bmi warning");
                JOptionPane.showMessageDialog(this, "Warning: This simulation resulted in an unhealthy low Body Mass Index (BMI).", "Low Body Mass Index Reached", 2);
            }
            System.out.println("END of recalc");
            this.calcflag = false;
        }
    }
    
    public void redo() {
        this.ChoicePanel.setSelectedIndex(0);
        if (!this.change.equals("weight")) {
            JOptionPane.showMessageDialog(this, "You can't achieve " + this.GoalWeight * this.weightunits + " " + this.gpanel.getweightstring() + " in " + this.TimetoGoal + " days with " + this.GoalActChangeBox.getSelectedItem().toString() + " change in activity" + "\nThe last change you made has been reset so that you can enter something different." + "\nTry giving yourself more time to achieve your goal, increasing your activity level, or setting a different goal" + "\n" + "\nYou can disable checking your goal by deselecting the check box in the \"Set Your Goal Weight\" Panel", "Unachievable Goal", 2);
        }
        if (this.change.equals("goalweight")) {
            this.change = null;
            System.out.println("Goal weight change");
            this.GoalWeight = this.OldGoalWeight;
            this.GoalWeightField.setValue(this.GoalWeight * this.weightunits);
            this.calcflag = false;
            this.recalc();
            return;
        }
        if (this.change.equals("goaltime")) {
            this.change = null;
            System.out.println("Goal time change");
            System.out.println("OldTimetogoal=" + this.OldTimetoGoal);
            this.TimetoGoal = this.OldTimetoGoal;
            System.out.println("Timetogoal=" + this.TimetoGoal);
            this.GoalTimeField.setValue(this.TimetoGoal);
            this.recalc();
            return;
        }
        if (this.change.equals("weight")) {
            this.change = null;
            this.GoalWeight = this.Baseline.getWeight_kgs();
            this.GoalWeightField.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
            this.recalc();
            return;
        }
        if (this.change.equals("PAL")) {
            this.change = null;
            this.Baseline.setPAL(this.OldPAL);
            this.InitialPALField.setValue(this.Baseline.getPAL());
            this.calcflag = false;
            this.recalc();
            return;
        }
        if (this.change.equals("RMR")) {
            this.change = null;
            this.Baseline.setRMR(this.OldRMR);
            if (this.RMRInputRadio.isSelected()) {
                this.calcflag = false;
                this.recalc();
            }
            return;
        }
        if (this.change.equals("goalact")) {
            this.change = null;
            this.GoalIntervention.setactchangepercent(this.OldGoalActChange);
            this.GoalActChangeBox.setSelectedItem(String.valueOf((int)this.OldGoalActChange) + "%");
            this.recalc();
            return;
        }
        if (this.change.equals("goalact")) {
            this.change = null;
            this.GoalMaintenanceIntervention.setactchangepercent(this.OldGoalActChange);
            this.GoalMaintenanceActChangeBox.setSelectedItem(String.valueOf((int)this.OldGoalMaintActChange) + "%");
            this.recalc();
            return;
        }
        if (this.change.equals("gender")) {
            this.change = null;
            this.Baseline.setMale(this.OldMale);
            this.GenderBox.setSelectedItem(this.OldMale ? "Male" : "Female");
            this.recalc();
        }
    }
    
    public Baseline getBaseline() {
        return this.Baseline;
    }
    
    public String getenergystring() {
        return this.energystring;
    }
    
    public double getweightunits() {
        return this.weightunits;
    }
    
    public void highlightIntervention1() {
        this.Intervention1DayField.setBackground(this.highlightgreen);
        this.Intervention1CaloriesField.setBackground(this.highlightgreen);
        this.Intervention1ActChangeBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
        if (this.Intervention1SodiumInputRadio.isSelected()) {
            this.Intervention1SodiumField.setBackground(this.highlightblue);
        }
        this.Intervention1CarbInPercField.setBackground(this.highlightblue);
    }
    
    public void unhighlightIntervention1() {
        this.Intervention1DayField.setBackground(Color.white);
        this.Intervention1CaloriesField.setBackground(Color.white);
        this.Intervention1ActChangeBox.setBorder(null);
        this.Intervention1SodiumField.setBackground(Color.white);
        this.Intervention1CarbInPercField.setBackground(Color.white);
    }
    
    public void highlightIntervention2() {
        this.Intervention2DayField.setBackground(this.highlightgreen);
        this.Intervention2CaloriesField.setBackground(this.highlightgreen);
        this.Intervention2CarbInPercField.setBackground(this.highlightblue);
        this.Intervention2ActChangeBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
        if (this.Intervention2SodiumInputRadio.isSelected()) {
            this.Intervention2SodiumField.setBackground(this.highlightblue);
        }
    }
    
    public void unhighlightIntervention2() {
        this.Intervention2DayField.setBackground(Color.white);
        this.Intervention2CaloriesField.setBackground(Color.white);
        this.Intervention2ActChangeBox.setBorder(null);
        this.Intervention2SodiumField.setBackground(Color.white);
        this.Intervention2CarbInPercField.setBackground(Color.white);
    }
    
    public void highlightgoal() {
        this.GoalWeightField.setBackground(this.highlightgreen);
        this.GoalTimeField.setBackground(this.highlightgreen);
        this.GoalActChangeBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
        this.GoalMaintenanceActChangeBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
        this.GoalCalsField.setBackground(this.highlightyellow);
        this.GoalMaintCalsField.setBackground(this.highlightyellow);
    }
    
    public void unhighlightgoal() {
        this.GoalWeightField.setBackground(Color.white);
        this.GoalTimeField.setBackground(Color.white);
        this.GoalActChangeBox.setBorder(null);
        this.GoalMaintenanceActChangeBox.setBorder(null);
        this.GoalCalsField.setBackground(Color.white);
        this.GoalMaintCalsField.setBackground(Color.white);
    }
    
    private void InitialWeightFieldChange() {
        try {
            this.check = Double.parseDouble(this.InitialWeightField.getValue().toString()) / this.weightunits;
            if (this.check > 0.0 && this.check != this.Baseline.getWeight_kgs() && !this.calcflag) {
                this.calcflag = true;
                this.change = "weight";
                this.OldWeight = this.Baseline.getWeight_kgs();
                this.Baseline.setWeight_kgs(this.check);
                this.InitialWeightField.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
                this.InitialWeightField2.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
                this.recalc();
            }
            else if (this.check <= 0.0) {
                System.out.println("negative initial weight");
                this.calcflag = true;
                this.InitialWeightField.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
                this.InitialWeightField2.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("stupid initial weight");
            this.calcflag = true;
            this.InitialWeightField.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
            this.InitialWeightField2.setValue(this.Baseline.getWeight_kgs() * this.weightunits);
            this.calcflag = false;
        }
    }
    
    private void InitialBfpChange() {
        try {
            if (this.InitialBfpInputRadio.isSelected()) {
                this.check = Double.parseDouble(this.InitialBfpField.getValue().toString());
                if (this.check >= 0.0 && this.check <= 100.0 && this.check != this.Baseline.getBfp() && !this.calcflag) {
                    this.Baseline.setcalculatedBfp(false);
                    this.Baseline.setBfp(this.check);
                    this.InitialBfpField2.setValue(this.Baseline.getBfp());
                    if (!this.calcflag) {
                        this.recalc();
                    }
                }
                else if (this.check < 0.0 || this.check > 100.0) {
                    this.calcflag = true;
                    this.InitialBfpField.setValue(this.Baseline.getBfp());
                    this.InitialBfpField2.setValue(this.Baseline.getBfp());
                    this.calcflag = false;
                }
            }
            else if (this.InitialBfpCalculatedRadio.isSelected()) {
                this.Baseline.setcalculatedBfp(true);
                this.InitialBfpField.setValue(this.Baseline.getBfp());
                this.InitialBfpField2.setValue(this.Baseline.getBfp());
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.InitialBfpField.setValue(this.Baseline.getBfp());
            this.InitialBfpField2.setValue(this.Baseline.getBfp());
            this.calcflag = false;
        }
    }
    
    private void CarbInPercChange() {
        try {
            this.check = Double.parseDouble(this.InitialCarbInPercField.getValue().toString());
            if (this.check > 0.0 && this.check <= 100.0 && this.check != this.Baseline.getCarbInPercent()) {
                this.calcflag = true;
                this.Baseline.setCarbInPercent(this.check);
                this.InitialCarbInPercField.setValue(this.Baseline.getCarbInPercent());
                this.recalc();
            }
            else {
                this.calcflag = true;
                this.InitialCarbInPercField.setValue(this.Baseline.getCarbInPercent());
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.InitialCarbInPercField.setValue(this.Baseline.getCarbInPercent());
            this.calcflag = false;
        }
    }
    
    private void InitialSodiumChange() {
        try {
            this.check = Double.parseDouble(this.InitialSodiumField.getValue().toString());
            if (this.check >= 0.0 && this.check <= 50000.0 && this.check != this.Baseline.getSodium() && !this.calcflag) {
                this.Baseline.setSodium(this.check);
                this.recalc();
            }
            else if (this.check < 0.0) {
                this.calcflag = true;
                this.InitialSodiumField.setValue(this.Baseline.getSodium());
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.InitialSodiumField.setValue(this.Baseline.getSodium());
            this.calcflag = false;
        }
    }
    
    private void InitialRMRChange() {
        if (this.RMRInputRadio.isSelected()) {
            try {
                this.check = Double.parseDouble(this.InitialRMRField.getValue().toString()) / this.energyunits;
                if (this.check > 0.0 && this.check != this.Baseline.getRMR() && !this.calcflag) {
                    this.OldRMR = this.Baseline.getRMR();
                    this.change = "RMR";
                    this.Baseline.setcalculatedRMR(false);
                    this.Baseline.setRMR(this.check);
                    this.recalc();
                }
            }
            catch (NumberFormatException ex) {
                this.calcflag = true;
                this.InitialRMRField.setValue(this.Baseline.getRMR() * this.energyunits);
                this.calcflag = false;
            }
        }
        else if (!this.RMRInputRadio.isSelected() && !this.calcflag) {
            this.Baseline.setcalculatedRMR(true);
            this.InitialRMRField.setValue(this.Baseline.getRMR() * this.energyunits);
            this.recalc();
        }
    }
    
    private void AgeChange() {
        try {
            this.check = Double.parseDouble(this.AgeField.getValue().toString());
            if (this.check >= 18.0 && this.check != this.Baseline.getAge() && !this.calcflag) {
                this.Baseline.setAge(this.check);
                this.recalc();
            }
            else if (this.check < 18.0 && this.check != this.Baseline.getAge()) {
                this.calcflag = true;
                if (this.warningson) {
                    JOptionPane.showMessageDialog(this, "This applet is designed to simulate the weight of persons 18 years or older", "Age too low", 2);
                }
                this.AgeField.setValue(this.Baseline.getAge());
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.AgeField.setValue(this.Baseline.getAge());
            this.calcflag = false;
        }
    }
    
    private void HeightChange() {
        try {
            this.check = Double.parseDouble(this.HeightField.getValue().toString()) / this.heightunits;
            if (this.check > 0.0 && this.check != this.Baseline.getHeight_cms() && !this.calcflag) {
                this.Baseline.setHeight(this.check);
                this.recalc();
            }
            else if (this.check <= 0.0) {
                this.calcflag = true;
                this.HeightField.setValue(this.Baseline.getHeight_cms() * this.heightunits);
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.HeightField.setValue(this.Baseline.getHeight_cms() * this.heightunits);
            this.calcflag = false;
        }
    }
    
    private void initPALChange() {
        try {
            this.check = Double.parseDouble(this.InitialPALField.getValue().toString());
            if (this.check != this.Baseline.getPAL()) {
                if (this.check >= 1.0 && this.check <= 3.0 && !this.calcflag) {
                    this.Baseline.setPAL(this.check);
                    this.recalc();
                }
                else if (this.check < 1.0 || this.check > 3.0) {
                    this.calcflag = true;
                    this.InitialPALField.setValue(this.Baseline.getPAL());
                    this.calcflag = false;
                }
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.InitialPALField.setValue(this.Baseline.getPAL());
            this.calcflag = false;
        }
    }
    
    private void SimulationLengthChange() {
        try {
            this.check = Double.parseDouble(this.SimulationLengthField.getValue().toString());
            if (this.check >= 1.0 && this.check != this.SimulationLength && !this.calcflag) {
                this.SimulationLength = this.check + 1.0;
                this.recalc();
            }
            else if (this.check < 1.0) {
                this.calcflag = true;
                this.SimulationLengthField.setValue(this.SimulationLength);
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.SimulationLengthField.setValue(this.SimulationLength);
            this.calcflag = false;
        }
    }
    
    private void GoalWeightChange() {
        try {
            this.check = Double.parseDouble(this.GoalWeightField.getValue().toString()) / this.weightunits;
            if (this.check > 0.0 && this.check != this.GoalWeight && !this.calcflag) {
                this.change = "goalweight";
                this.OldGoalWeight = this.GoalWeight;
                this.GoalWeight = this.check;
                this.recalc();
            }
            else if (this.check <= 0.0) {
                this.calcflag = true;
                this.GoalWeightField.setValue(this.GoalWeight * this.weightunits);
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.GoalWeightField.setValue(this.GoalWeight * this.weightunits);
            this.calcflag = false;
        }
    }
    
    private void GoalTimeChange() {
        try {
            this.check = Double.parseDouble(this.GoalTimeField.getValue().toString());
            if (this.check > 0.0 && this.check != this.TimetoGoal && !this.calcflag) {
                System.out.println("Goaltimechange");
                this.change = "goaltime";
                this.OldTimetoGoal = this.TimetoGoal;
                this.TimetoGoal = this.check;
                System.out.println("in change: OldTimetogoal=" + this.OldTimetoGoal);
                System.out.println("in change: Timetogoal=" + this.TimetoGoal);
                this.recalc();
            }
            else if (this.check <= 0.0) {
                this.calcflag = true;
                this.GoalTimeField.setValue(this.GoalMaintenanceIntervention.getday());
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.GoalTimeField.setValue(this.GoalMaintenanceIntervention.getday());
            this.calcflag = false;
        }
    }
    
    private void Intervention1DayChange() {
        try {
            this.check = Double.parseDouble(this.Intervention1DayField.getValue().toString());
            if (this.check > 0.0 && this.check != this.Intervention1.getday() && !this.calcflag) {
                System.out.println("inter1 day change");
                this.Intervention1.setday((int)this.check);
                if (!this.goalsim) {
                    this.recalc();
                }
            }
            else if (this.check <= 0.0) {
                this.calcflag = true;
                this.Intervention1DayField.setValue(this.Intervention1.getday());
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.GoalTimeField.setValue(this.Intervention1.getday());
            this.calcflag = false;
        }
    }
    
    private void Intervention1CaloriesChange() {
        try {
            this.check = Double.parseDouble(this.Intervention1CaloriesField.getValue().toString()) / this.energyunits;
            if (this.check >= 0.0 && this.check != this.Intervention1.getcalories() && !this.calcflag) {
                this.Intervention1.setcalories(this.check);
                if (this.Intervention1SodiumCalculatedRadio.isSelected()) {
                    this.Intervention1.setproportionalsodium(this.Baseline);
                    this.Intervention1SodiumField.setValue(this.Intervention1.getsodium());
                }
                if (this.Intervention1.ison() && !this.goalsim) {
                    this.recalc();
                }
            }
            else if (this.check < 0.0) {
                this.calcflag = true;
                this.Intervention1CaloriesField.setValue(this.Intervention1.getcalories() * this.energyunits);
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.Intervention1CaloriesField.setValue(this.Intervention1.getcalories() * this.energyunits);
            this.calcflag = false;
        }
    }
    
    private void Intervention1CarbInPercChange() {
        try {
            this.check = Double.parseDouble(this.Intervention1CarbInPercField.getValue().toString());
            if (this.check >= 0.0 && this.check <= 100.0 && this.check != this.Intervention1.getcarbinpercent() && !this.calcflag) {
                this.Intervention1.setcarbinpercent(this.check);
                if (this.Intervention1.ison() && !this.goalsim) {
                    this.recalc();
                }
            }
            else if (this.check < 0.0 || this.check > 100.0) {
                this.calcflag = true;
                this.Intervention1CarbInPercField.setValue(this.Intervention2.getcarbinpercent());
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.Intervention1CarbInPercField.setValue(this.Intervention1.getcarbinpercent());
            this.calcflag = false;
        }
    }
    
    private void Intervention1SodiumChange() {
        try {
            if (this.Intervention1SodiumInputRadio.isSelected()) {
                this.check = Double.parseDouble(this.Intervention1SodiumField.getValue().toString());
                if (this.check >= 0.0 && this.check != this.Intervention1.getsodium() && !this.calcflag) {
                    this.Intervention1.setsodium(this.check);
                    if (!this.goalsim && this.Intervention1.ison()) {
                        this.recalc();
                    }
                }
                else if (this.check < 0.0) {
                    this.calcflag = true;
                    this.calcflag = false;
                }
            }
            else if (this.Intervention1SodiumCalculatedRadio.isSelected()) {
                this.calcflag = true;
                this.Intervention1.setproportionalsodium(this.Baseline);
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.calcflag = false;
        }
    }
    
    private void Intervention2DayChange() {
        try {
            this.check = Double.parseDouble(this.Intervention2DayField.getValue().toString());
            if (this.check > 0.0 && this.check != this.Intervention2.getday() && !this.calcflag) {
                this.Intervention2.setday((int)this.check);
                if (this.Intervention2.ison() && !this.goalsim) {
                    this.recalc();
                }
            }
            else if (this.check <= 0.0) {
                this.calcflag = true;
                this.Intervention2DayField.setValue(this.Intervention2.getday());
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.Intervention2DayField.setValue(this.Intervention2.getday());
            this.calcflag = false;
        }
    }
    
    private void Intervention2CaloriesChange() {
        try {
            this.check = Double.parseDouble(this.Intervention2CaloriesField.getValue().toString()) / this.energyunits;
            if (this.check >= 0.0 && this.check != this.Intervention2.getcalories() && !this.calcflag) {
                this.Intervention2.setcalories(this.check);
                if (this.Intervention2SodiumCalculatedRadio.isSelected()) {
                    this.Intervention2.setproportionalsodium(this.Baseline);
                    this.Intervention2SodiumField.setValue(this.Intervention2.getsodium());
                }
                if (this.Intervention2.ison() && !this.goalsim) {
                    this.recalc();
                }
            }
            else if (this.check < 0.0) {
                this.calcflag = true;
                this.Intervention2CaloriesField.setValue(this.Intervention2.getcalories() * this.energyunits);
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.Intervention2CaloriesField.setValue(this.Intervention2.getcalories() * this.energyunits);
            this.calcflag = false;
        }
    }
    
    private void Intervention2CarbInPercChange() {
        try {
            this.check = Double.parseDouble(this.Intervention2CarbInPercField.getValue().toString());
            if (this.check >= 0.0 && this.check <= 100.0 && this.check != this.Intervention2.getcarbinpercent() && !this.calcflag) {
                this.Intervention2.setcarbinpercent(this.check);
                if (this.Intervention2.ison() && !this.goalsim) {
                    this.recalc();
                }
            }
            else if (this.check <= 0.0 || this.check > 100.0) {
                this.calcflag = true;
                this.Intervention2CarbInPercField.setValue(this.Intervention2.getcarbinpercent());
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.Intervention2CarbInPercField.setValue(this.Intervention2.getcarbinpercent());
            this.calcflag = false;
        }
    }
    
    private void Intervention2SodiumChange() {
        try {
            if (this.Intervention2SodiumInputRadio.isSelected()) {
                this.check = Double.parseDouble(this.Intervention2SodiumField.getValue().toString());
                if (this.check >= 0.0 && this.check != this.Intervention2.getsodium() && !this.calcflag) {
                    this.Intervention2.setsodium(this.check);
                    if (!this.goalsim && this.Intervention2.ison()) {
                        this.recalc();
                    }
                }
                else if (this.check < 0.0) {
                    this.calcflag = true;
                    this.Intervention2SodiumField.setValue(this.Intervention2.getsodium());
                    this.calcflag = false;
                }
            }
            else if (this.Intervention2SodiumCalculatedRadio.isSelected()) {
                this.calcflag = true;
                this.Intervention2.setproportionalsodium(this.Baseline);
                this.Intervention2SodiumField.setValue(this.Intervention2.getsodium());
                this.calcflag = false;
            }
        }
        catch (NumberFormatException ex) {
            this.calcflag = true;
            this.Intervention2SodiumField.setValue(this.Intervention2.getsodium());
            this.calcflag = false;
        }
    }
    
    private void initComponents() {
        this.WeightUnitsRadioGroup = new ButtonGroup();
        this.BfpRadioGroup = new ButtonGroup();
        this.RMRRadioGroup = new ButtonGroup();
        this.GenderRadioGroup = new ButtonGroup();
        this.HeightUnitsGroup = new ButtonGroup();
        this.Intervention1SodiumRadioGroup = new ButtonGroup();
        this.Intervention2SodiumRadioGroup = new ButtonGroup();
        this.EnergyUnitsRadioGroup = new ButtonGroup();
        this.BaselinePanel = new JPanel();
        this.InitialWeightLabel = new JLabel();
        this.PoundsRadio = new JRadioButton();
        this.KilogramsRadio = new JRadioButton();
        this.InitialWeightField = new JFormattedTextField();
        this.BaselineLabel = new JLabel();
        this.InitialBfpLabel = new JLabel();
        this.InitialBfpField = new JFormattedTextField();
        this.InitialBfpCalculatedRadio = new JRadioButton();
        this.InitialBfpInputRadio = new JRadioButton();
        this.jSeparator1 = new JSeparator();
        this.MaintCalsLabel = new JLabel();
        this.MaintCalsField = new JFormattedTextField();
        this.InitialCarbInPercLabel = new JLabel();
        this.InitialCarbInPercField = new JFormattedTextField();
        this.InitialSodiumLabel = new JLabel();
        this.InitialSodiumField = new JFormattedTextField();
        this.jSeparator2 = new JSeparator();
        this.InitialRMRLabel = new JLabel();
        this.InitialRMRField = new JFormattedTextField();
        this.RMRCalculatedRadio = new JRadioButton();
        this.RMRInputRadio = new JRadioButton();
        this.PALLabel = new JLabel();
        this.EstimatePALButton = new JButton();
        this.AgeLabel = new JLabel();
        this.AgeField = new JFormattedTextField();
        this.HeightLabel = new JLabel();
        this.HeightField = new JFormattedTextField();
        this.InchesRadio = new JRadioButton();
        this.CentimetersRadio = new JRadioButton();
        this.RMRSeparator = new JSeparator();
        this.BfpSeparator = new JSeparator();
        this.GenderBox = new JComboBox();
        this.CaloriesRadio = new JRadioButton();
        this.KilojoulesRadio = new JRadioButton();
        this.UncertaintyBox = new JComboBox();
        this.UncertaintyLabel = new JLabel();
        this.GenderLabel = new JLabel();
        this.InitialPALField = new JFormattedTextField();
        this.ChoicePanel = new JTabbedPane();
        this.GoalPanel = new JPanel();
        this.jSeparator4 = new JSeparator();
        this.RunGoalButton = new JButton();
        this.GoalMaintenanceInterventionLabel = new JLabel();
        this.GoalStatementPanel = new JPanel();
        this.GoalCheckBox = new JCheckBox();
        this.GoalWeightLabel = new JLabel();
        this.GoalWeightField = new JFormattedTextField();
        this.GoalUnitsLabel = new JLabel();
        this.GoalTimeField = new JFormattedTextField();
        this.GoalDaysLabel = new JLabel();
        this.GoalCalsChangeField = new JFormattedTextField();
        this.GoalInterventionLabel = new JLabel();
        this.GoalMaintCalsChangeLabel = new JLabel();
        this.GoalMaintCalsChangeField = new JFormattedTextField();
        this.GoalActivityLabel = new JLabel();
        this.GoalActChangeBox = new JComboBox();
        this.GoalCalsField = new JFormattedTextField();
        this.GoalCaloriesLabel = new JLabel();
        this.GoalMaintenanceActivityLabel = new JLabel();
        this.GoalMaintenanceActChangeBox = new JComboBox();
        this.GoalMaintCalsLabel = new JLabel();
        this.GoalCalsChangeLabel = new JLabel();
        this.GoalSpaceLabel = new JLabel();
        this.GoalMaintCalsField = new JFormattedTextField();
        this.InterventionPanel = new JPanel();
        this.Intervention1Label = new JLabel();
        this.Intervention1CheckBox = new JCheckBox();
        this.Intervention1DayLabel = new JLabel();
        this.Intervention2DayField = new JFormattedTextField();
        this.Intervention1CaloriesLabel = new JLabel();
        this.Intervention2CaloriesField = new JFormattedTextField();
        this.Intervention1CarbInPercLabel = new JLabel();
        this.Intervention2CarbInPercField = new JFormattedTextField();
        this.Intervention1ActivityLabel = new JLabel();
        this.Intervention2ActChangeBox = new JComboBox();
        this.Intervention1SodiumLabel = new JLabel();
        this.SpaceLabel = new JLabel();
        this.Intervention1SodiumCalculatedRadio = new JRadioButton();
        this.Intervention1SodiumInputRadio = new JRadioButton();
        this.Intervention1SodiumField = new JFormattedTextField();
        this.Intervention2Label = new JLabel();
        this.Intervention2CheckBox = new JCheckBox();
        this.Intervention2DayLabel = new JLabel();
        this.Intervention1DayField = new JFormattedTextField();
        this.Intervention2CaloriesLabel = new JLabel();
        this.Intervention1CaloriesField = new JFormattedTextField();
        this.Intervention2CarbInPercLabel = new JLabel();
        this.Intervention1CarbInPercField = new JFormattedTextField();
        this.Intervention2ActivityLabel = new JLabel();
        this.Intervention1ActChangeBox = new JComboBox();
        this.Intervention2SodiumLabel = new JLabel();
        this.Intervention2SodiumCalculatedRadio = new JRadioButton();
        this.Intervention2SodiumInputRadio = new JRadioButton();
        this.Intervention2SodiumField = new JFormattedTextField();
        this.RunInterventionButton = new JButton();
        this.Intervention1SodiumSeparator = new JSeparator();
        this.Intervention2SodiumSeparator = new JSeparator();
        this.Intervention1RampCheckBox = new JCheckBox();
        this.Intervention2RampCheckBox = new JCheckBox();
        this.ComparePanel = new JPanel();
        this.InitialWeightLabel2 = new JLabel();
        this.InitialWeightField2 = new JFormattedTextField();
        this.InitialBfpLabel2 = new JLabel();
        this.InitialBMILabel = new JLabel();
        this.FinalWeightLabel = new JLabel();
        this.FinalBMILabel = new JLabel();
        this.FinalBMIField = new JFormattedTextField();
        this.FinalBfpLabel = new JLabel();
        this.FinalBfpField = new JFormattedTextField();
        this.FinalWeightField = new JFormattedTextField();
        this.InitialBfpField2 = new JFormattedTextField();
        this.InitialBMIField = new JFormattedTextField();
        this.ZoomPanel = new JPanel();
        this.VerticalZoomMinLabel = new JLabel();
        this.SimulationLengthLabel = new JLabel();
        this.SimulationLengthField = new JFormattedTextField();
        this.TimeStartLabel = new JLabel();
        this.StatusLabel = new JLabel();
        this.VerticalZoomSlider = new JSlider();
        this.VerticalZoomMaxLabel = new JLabel();
        this.TimeZoomMinLabel = new JLabel();
        this.TimeZoomSlider = new JSlider();
        this.TimeZoomMaxLabel = new JLabel();
        this.TimeStartMinLabel = new JLabel();
        this.TimeStartSlider = new JSlider();
        this.TimeStartMaxLabel = new JLabel();
        this.TimeZoomLabel = new JLabel();
        this.VerticalZoomLabel = new JLabel();
        this.jPanel2 = new JPanel();
        this.OptionsPanel = new JPanel();
        this.GraphAttachedCheckBox = new JCheckBox();
        this.DefaultButton = new JButton();
        this.GridCheckBox = new JCheckBox();
        this.ShowLegendCheckBox = new JCheckBox();
        this.UncertaintyCheckBox = new JCheckBox();
        this.HighlightCheckBox = new JCheckBox();
        this.AdvancedControlsCheckBox = new JCheckBox();
        this.jSeparator3 = new JSeparator();
        this.OverviewButton = new JButton();
        this.DefaultColorsCheckBox = new JCheckBox();
        this.SaveButton = new JButton();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(final KeyEvent keyEvent) {
                MainPanel.this.formKeyPressed(keyEvent);
            }
        });
        this.setLayout(new GridBagLayout());
        this.BaselinePanel.setBorder(BorderFactory.createEtchedBorder());
        this.BaselinePanel.setMinimumSize(new Dimension(275, 393));
        this.BaselinePanel.setLayout(new GridBagLayout());
        this.InitialWeightLabel.setText("Initial Weight");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 3;
        gridBagConstraints.anchor = 17;
        this.BaselinePanel.add(this.InitialWeightLabel, gridBagConstraints);
        this.WeightUnitsRadioGroup.add(this.PoundsRadio);
        this.PoundsRadio.setText("Pounds");
        this.PoundsRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.PoundsRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 0;
        gridBagConstraints2.gridy = 2;
        gridBagConstraints2.anchor = 17;
        this.BaselinePanel.add(this.PoundsRadio, gridBagConstraints2);
        this.WeightUnitsRadioGroup.add(this.KilogramsRadio);
        this.KilogramsRadio.setSelected(true);
        this.KilogramsRadio.setText("Kilograms");
        this.KilogramsRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.KilogramsRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.gridx = 1;
        gridBagConstraints3.gridy = 2;
        gridBagConstraints3.anchor = 17;
        this.BaselinePanel.add(this.KilogramsRadio, gridBagConstraints3);
        this.InitialWeightField.setBackground(this.highlightgreen);
        this.InitialWeightField.setColumns(3);
        this.InitialWeightField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        this.InitialWeightField.setMinimumSize(new Dimension(56, 28));
        this.InitialWeightField.setValue(this.Baseline.getWeight_kgs());
        this.InitialWeightField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.InitialWeightFieldActionPerformed(actionEvent);
            }
        });
        this.InitialWeightField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.InitialWeightFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
        gridBagConstraints4.gridx = 1;
        gridBagConstraints4.gridy = 1;
        gridBagConstraints4.anchor = 17;
        this.BaselinePanel.add(this.InitialWeightField, gridBagConstraints4);
        this.BaselineLabel.setFont(new Font("Lucida Grande", 1, 14));
        this.BaselineLabel.setHorizontalAlignment(0);
        this.BaselineLabel.setText("Enter Baseline Information");
        this.BaselineLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        final GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
        gridBagConstraints5.gridx = 0;
        gridBagConstraints5.gridy = 0;
        gridBagConstraints5.gridwidth = 2;
        gridBagConstraints5.ipadx = 20;
        this.BaselinePanel.add(this.BaselineLabel, gridBagConstraints5);
        this.InitialBfpLabel.setText("Initial Body Fat %");
        final GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
        gridBagConstraints6.gridx = 0;
        gridBagConstraints6.gridy = 20;
        gridBagConstraints6.anchor = 17;
        this.BaselinePanel.add(this.InitialBfpLabel, gridBagConstraints6);
        this.InitialBfpField.setColumns(3);
        this.InitialBfpField.setEditable(false);
        this.InitialBfpField.setForeground(new Color(102, 102, 102));
        this.InitialBfpField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        this.InitialBfpField.setMinimumSize(new Dimension(56, 28));
        this.InitialBfpField.setValue(this.Baseline.getBfp());
        this.InitialBfpField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.InitialBfpFieldActionPerformed(actionEvent);
            }
        });
        this.InitialBfpField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.InitialBfpFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
        gridBagConstraints7.gridx = 1;
        gridBagConstraints7.gridy = 20;
        gridBagConstraints7.anchor = 17;
        this.BaselinePanel.add(this.InitialBfpField, gridBagConstraints7);
        this.BfpRadioGroup.add(this.InitialBfpCalculatedRadio);
        this.InitialBfpCalculatedRadio.setSelected(true);
        this.InitialBfpCalculatedRadio.setText("Automatic");
        this.InitialBfpCalculatedRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.InitialBfpCalculatedRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        gridBagConstraints8.gridx = 0;
        gridBagConstraints8.gridy = 21;
        gridBagConstraints8.anchor = 17;
        this.BaselinePanel.add(this.InitialBfpCalculatedRadio, gridBagConstraints8);
        this.BfpRadioGroup.add(this.InitialBfpInputRadio);
        this.InitialBfpInputRadio.setText("Input");
        this.InitialBfpInputRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.InitialBfpInputRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
        gridBagConstraints9.gridx = 1;
        gridBagConstraints9.gridy = 21;
        gridBagConstraints9.anchor = 17;
        this.BaselinePanel.add(this.InitialBfpInputRadio, gridBagConstraints9);
        final GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
        gridBagConstraints10.gridx = 0;
        gridBagConstraints10.gridy = 7;
        gridBagConstraints10.gridwidth = 2;
        gridBagConstraints10.fill = 2;
        this.BaselinePanel.add(this.jSeparator1, gridBagConstraints10);
        this.MaintCalsLabel.setText("Baseline Diet");
        final GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        gridBagConstraints11.gridx = 0;
        gridBagConstraints11.gridy = 13;
        gridBagConstraints11.anchor = 17;
        this.BaselinePanel.add(this.MaintCalsLabel, gridBagConstraints11);
        this.MaintCalsField.setBackground(this.highlightyellow);
        this.MaintCalsField.setColumns(4);
        this.MaintCalsField.setEditable(false);
        this.MaintCalsField.setForeground(this.grey);
        this.MaintCalsField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.MaintCalsField.setToolTipText("This is what you should eat daily in order to maintain your current weight");
        this.MaintCalsField.setMinimumSize(new Dimension(56, 28));
        this.MaintCalsField.setValue(this.Baseline.getMaintCals());
        final GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
        gridBagConstraints12.gridx = 1;
        gridBagConstraints12.gridy = 13;
        gridBagConstraints12.anchor = 17;
        this.BaselinePanel.add(this.MaintCalsField, gridBagConstraints12);
        this.InitialCarbInPercLabel.setText("% Calories from Carbs");
        final GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        gridBagConstraints13.gridx = 0;
        gridBagConstraints13.gridy = 17;
        gridBagConstraints13.anchor = 17;
        this.BaselinePanel.add(this.InitialCarbInPercLabel, gridBagConstraints13);
        this.InitialCarbInPercField.setBackground(this.highlightblue);
        this.InitialCarbInPercField.setColumns(3);
        this.InitialCarbInPercField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.InitialCarbInPercField.setMinimumSize(new Dimension(56, 28));
        this.InitialCarbInPercField.setValue(this.Baseline.getCarbInPercent());
        this.InitialCarbInPercField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.InitialCarbInPercFieldActionPerformed(actionEvent);
            }
        });
        this.InitialCarbInPercField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.InitialCarbInPercFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
        gridBagConstraints14.gridx = 1;
        gridBagConstraints14.gridy = 17;
        gridBagConstraints14.anchor = 17;
        this.BaselinePanel.add(this.InitialCarbInPercField, gridBagConstraints14);
        this.InitialSodiumLabel.setText("Initial Sodium (mg/day)");
        final GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
        gridBagConstraints15.gridx = 0;
        gridBagConstraints15.gridy = 18;
        gridBagConstraints15.anchor = 17;
        this.BaselinePanel.add(this.InitialSodiumLabel, gridBagConstraints15);
        this.InitialSodiumField.setBackground(this.highlightblue);
        this.InitialSodiumField.setColumns(3);
        this.InitialSodiumField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.InitialSodiumField.setMinimumSize(new Dimension(56, 28));
        this.InitialSodiumField.setValue(this.Baseline.getSodium());
        this.InitialSodiumField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.InitialSodiumFieldActionPerformed(actionEvent);
            }
        });
        this.InitialSodiumField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.InitialSodiumFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints16 = new GridBagConstraints();
        gridBagConstraints16.gridx = 1;
        gridBagConstraints16.gridy = 18;
        gridBagConstraints16.anchor = 17;
        this.BaselinePanel.add(this.InitialSodiumField, gridBagConstraints16);
        final GridBagConstraints gridBagConstraints17 = new GridBagConstraints();
        gridBagConstraints17.gridx = 0;
        gridBagConstraints17.gridy = 12;
        gridBagConstraints17.gridwidth = 2;
        gridBagConstraints17.fill = 2;
        this.BaselinePanel.add(this.jSeparator2, gridBagConstraints17);
        this.InitialRMRLabel.setText("Initial RMR");
        final GridBagConstraints gridBagConstraints18 = new GridBagConstraints();
        gridBagConstraints18.gridx = 0;
        gridBagConstraints18.gridy = 10;
        gridBagConstraints18.anchor = 17;
        this.BaselinePanel.add(this.InitialRMRLabel, gridBagConstraints18);
        this.InitialRMRField.setColumns(3);
        this.InitialRMRField.setEditable(false);
        this.InitialRMRField.setForeground(new Color(102, 102, 102));
        this.InitialRMRField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.InitialRMRField.setMinimumSize(new Dimension(56, 28));
        this.InitialRMRField.setValue(this.Baseline.getRMR());
        this.InitialRMRField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.InitialRMRFieldActionPerformed(actionEvent);
            }
        });
        this.InitialRMRField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.InitialRMRFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints19 = new GridBagConstraints();
        gridBagConstraints19.gridx = 1;
        gridBagConstraints19.gridy = 10;
        gridBagConstraints19.anchor = 17;
        this.BaselinePanel.add(this.InitialRMRField, gridBagConstraints19);
        this.RMRRadioGroup.add(this.RMRCalculatedRadio);
        this.RMRCalculatedRadio.setSelected(true);
        this.RMRCalculatedRadio.setText("Automatic");
        this.RMRCalculatedRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.RMRCalculatedRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints20 = new GridBagConstraints();
        gridBagConstraints20.gridx = 0;
        gridBagConstraints20.gridy = 11;
        gridBagConstraints20.anchor = 17;
        this.BaselinePanel.add(this.RMRCalculatedRadio, gridBagConstraints20);
        this.RMRRadioGroup.add(this.RMRInputRadio);
        this.RMRInputRadio.setText("Input");
        this.RMRInputRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.RMRInputRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
        gridBagConstraints21.gridx = 1;
        gridBagConstraints21.gridy = 11;
        gridBagConstraints21.anchor = 17;
        this.BaselinePanel.add(this.RMRInputRadio, gridBagConstraints21);
        this.PALLabel.setText("Physical Activity Level");
        final GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
        gridBagConstraints22.gridx = 0;
        gridBagConstraints22.gridy = 8;
        gridBagConstraints22.anchor = 17;
        this.BaselinePanel.add(this.PALLabel, gridBagConstraints22);
        this.EstimatePALButton.setText("Estimate Activity Level");
        this.EstimatePALButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.EstimatePALButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints23 = new GridBagConstraints();
        gridBagConstraints23.gridx = 0;
        gridBagConstraints23.gridy = 9;
        gridBagConstraints23.gridwidth = 2;
        this.BaselinePanel.add(this.EstimatePALButton, gridBagConstraints23);
        this.AgeLabel.setText("Age (years)");
        final GridBagConstraints gridBagConstraints24 = new GridBagConstraints();
        gridBagConstraints24.gridx = 0;
        gridBagConstraints24.gridy = 4;
        gridBagConstraints24.anchor = 17;
        this.BaselinePanel.add(this.AgeLabel, gridBagConstraints24);
        this.AgeField.setBackground(this.highlightgreen);
        this.AgeField.setColumns(3);
        this.AgeField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        this.AgeField.setMinimumSize(new Dimension(56, 28));
        this.AgeField.setValue(this.Baseline.getAge());
        this.AgeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.AgeFieldActionPerformed(actionEvent);
            }
        });
        this.AgeField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.AgeFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints25 = new GridBagConstraints();
        gridBagConstraints25.gridx = 1;
        gridBagConstraints25.gridy = 4;
        gridBagConstraints25.anchor = 17;
        this.BaselinePanel.add(this.AgeField, gridBagConstraints25);
        this.HeightLabel.setText("Height");
        final GridBagConstraints gridBagConstraints26 = new GridBagConstraints();
        gridBagConstraints26.gridx = 0;
        gridBagConstraints26.gridy = 5;
        gridBagConstraints26.anchor = 17;
        this.BaselinePanel.add(this.HeightLabel, gridBagConstraints26);
        this.HeightField.setBackground(this.highlightgreen);
        this.HeightField.setColumns(3);
        this.HeightField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        this.HeightField.setMinimumSize(new Dimension(56, 28));
        this.HeightField.setValue(this.Baseline.getHeight_cms());
        this.HeightField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.HeightFieldActionPerformed(actionEvent);
            }
        });
        this.HeightField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.HeightFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints27 = new GridBagConstraints();
        gridBagConstraints27.gridx = 1;
        gridBagConstraints27.gridy = 5;
        gridBagConstraints27.anchor = 17;
        this.BaselinePanel.add(this.HeightField, gridBagConstraints27);
        this.HeightUnitsGroup.add(this.InchesRadio);
        this.InchesRadio.setText("Inches");
        this.InchesRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.InchesRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints28 = new GridBagConstraints();
        gridBagConstraints28.gridx = 0;
        gridBagConstraints28.gridy = 6;
        gridBagConstraints28.anchor = 17;
        this.BaselinePanel.add(this.InchesRadio, gridBagConstraints28);
        this.HeightUnitsGroup.add(this.CentimetersRadio);
        this.CentimetersRadio.setSelected(true);
        this.CentimetersRadio.setText("Centimeters");
        this.CentimetersRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.CentimetersRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints29 = new GridBagConstraints();
        gridBagConstraints29.gridx = 1;
        gridBagConstraints29.gridy = 6;
        gridBagConstraints29.anchor = 17;
        this.BaselinePanel.add(this.CentimetersRadio, gridBagConstraints29);
        final GridBagConstraints gridBagConstraints30 = new GridBagConstraints();
        gridBagConstraints30.gridx = 0;
        gridBagConstraints30.gridy = 16;
        gridBagConstraints30.gridwidth = 2;
        gridBagConstraints30.ipadx = 100;
        this.BaselinePanel.add(this.RMRSeparator, gridBagConstraints30);
        final GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
        gridBagConstraints31.gridx = 0;
        gridBagConstraints31.gridy = 19;
        gridBagConstraints31.gridwidth = 2;
        gridBagConstraints31.ipadx = 100;
        this.BaselinePanel.add(this.BfpSeparator, gridBagConstraints31);
        this.GenderBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Male", "Female" }));
        this.GenderBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
        this.GenderBox.setNextFocusableComponent(this.AgeField);
        this.GenderBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.GenderBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints32 = new GridBagConstraints();
        gridBagConstraints32.gridx = 0;
        gridBagConstraints32.gridy = 3;
        gridBagConstraints32.gridwidth = 2;
        this.BaselinePanel.add(this.GenderBox, gridBagConstraints32);
        this.EnergyUnitsRadioGroup.add(this.CaloriesRadio);
        this.CaloriesRadio.setText("Calories/day");
        this.CaloriesRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.CaloriesRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints33 = new GridBagConstraints();
        gridBagConstraints33.gridx = 0;
        gridBagConstraints33.gridy = 14;
        gridBagConstraints33.anchor = 17;
        this.BaselinePanel.add(this.CaloriesRadio, gridBagConstraints33);
        this.EnergyUnitsRadioGroup.add(this.KilojoulesRadio);
        this.KilojoulesRadio.setSelected(true);
        this.KilojoulesRadio.setText("Kilojoules/day");
        this.KilojoulesRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.KilojoulesRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints34 = new GridBagConstraints();
        gridBagConstraints34.gridx = 1;
        gridBagConstraints34.gridy = 14;
        gridBagConstraints34.anchor = 17;
        this.BaselinePanel.add(this.KilojoulesRadio, gridBagConstraints34);
        this.UncertaintyBox.setModel(new DefaultComboBoxModel<String>(new String[] { "5%", "10%", "15%", "20%", "25%" }));
        this.UncertaintyBox.setSelectedIndex(1);
        this.UncertaintyBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.UncertaintyBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints35 = new GridBagConstraints();
        gridBagConstraints35.gridx = 1;
        gridBagConstraints35.gridy = 15;
        gridBagConstraints35.anchor = 17;
        this.BaselinePanel.add(this.UncertaintyBox, gridBagConstraints35);
        this.UncertaintyLabel.setText("Uncertainty Range");
        final GridBagConstraints gridBagConstraints36 = new GridBagConstraints();
        gridBagConstraints36.gridx = 0;
        gridBagConstraints36.gridy = 15;
        gridBagConstraints36.anchor = 17;
        this.BaselinePanel.add(this.UncertaintyLabel, gridBagConstraints36);
        final GridBagConstraints gridBagConstraints37 = new GridBagConstraints();
        gridBagConstraints37.gridx = 1;
        gridBagConstraints37.gridy = 3;
        gridBagConstraints37.anchor = 13;
        this.BaselinePanel.add(this.GenderLabel, gridBagConstraints37);
        this.GenderLabel.setVisible(false);
        this.InitialPALField.setBackground(this.highlightgreen);
        this.InitialPALField.setColumns(3);
        this.InitialPALField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.00"))));
        this.InitialPALField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.InitialPALFieldActionPerformed(actionEvent);
            }
        });
        this.InitialPALField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.InitialPALFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints38 = new GridBagConstraints();
        gridBagConstraints38.gridx = 1;
        gridBagConstraints38.gridy = 8;
        gridBagConstraints38.anchor = 17;
        this.BaselinePanel.add(this.InitialPALField, gridBagConstraints38);
        final GridBagConstraints gridBagConstraints39 = new GridBagConstraints();
        gridBagConstraints39.gridheight = 3;
        gridBagConstraints39.fill = 1;
        gridBagConstraints39.anchor = 11;
        gridBagConstraints39.weightx = 0.3;
        this.add(this.BaselinePanel, gridBagConstraints39);
        this.ChoicePanel.setBorder(BorderFactory.createEtchedBorder());
        this.ChoicePanel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.ChoicePanelPropertyChange(propertyChangeEvent);
            }
        });
        this.GoalPanel.setMinimumSize(new Dimension(594, 229));
        this.GoalPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints40 = new GridBagConstraints();
        gridBagConstraints40.gridx = 0;
        gridBagConstraints40.gridy = 1;
        gridBagConstraints40.gridwidth = 5;
        gridBagConstraints40.fill = 2;
        this.GoalPanel.add(this.jSeparator4, gridBagConstraints40);
        this.RunGoalButton.setText("Run Simulation");
        this.RunGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.RunGoalButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
        gridBagConstraints41.gridx = 0;
        gridBagConstraints41.gridy = 6;
        gridBagConstraints41.gridwidth = 5;
        this.GoalPanel.add(this.RunGoalButton, gridBagConstraints41);
        this.GoalMaintenanceInterventionLabel.setFont(new Font("Lucida Grande", 0, 14));
        this.GoalMaintenanceInterventionLabel.setText("Goal Maintenance Phase");
        final GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
        gridBagConstraints42.gridx = 3;
        gridBagConstraints42.gridy = 2;
        gridBagConstraints42.gridwidth = 2;
        this.GoalPanel.add(this.GoalMaintenanceInterventionLabel, gridBagConstraints42);
        this.GoalCheckBox.setSelected(true);
        this.GoalCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.GoalCheckBoxActionPerformed(actionEvent);
            }
        });
        this.GoalStatementPanel.add(this.GoalCheckBox);
        this.GoalWeightLabel.setText("My goal is to weigh");
        this.GoalStatementPanel.add(this.GoalWeightLabel);
        this.GoalWeightField.setBackground(this.highlightgreen);
        this.GoalWeightField.setColumns(3);
        this.GoalWeightField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        this.GoalWeightField.setMinimumSize(new Dimension(56, 28));
        this.GoalWeightField.setValue(60);
        this.GoalWeightField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.GoalWeightFieldActionPerformed(actionEvent);
            }
        });
        this.GoalWeightField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.GoalWeightFieldPropertyChange(propertyChangeEvent);
            }
        });
        this.GoalStatementPanel.add(this.GoalWeightField);
        this.GoalUnitsLabel.setText("kg in");
        this.GoalStatementPanel.add(this.GoalUnitsLabel);
        this.GoalTimeField.setBackground(this.highlightgreen);
        this.GoalTimeField.setColumns(3);
        this.GoalTimeField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.GoalTimeField.setMinimumSize(new Dimension(56, 28));
        this.GoalTimeField.setValue(365);
        this.GoalTimeField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.GoalTimeFieldActionPerformed(actionEvent);
            }
        });
        this.GoalTimeField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.GoalTimeFieldPropertyChange(propertyChangeEvent);
            }
        });
        this.GoalStatementPanel.add(this.GoalTimeField);
        this.GoalDaysLabel.setText("days");
        this.GoalStatementPanel.add(this.GoalDaysLabel);
        final GridBagConstraints gridBagConstraints43 = new GridBagConstraints();
        gridBagConstraints43.gridx = 0;
        gridBagConstraints43.gridy = 0;
        gridBagConstraints43.gridwidth = 5;
        gridBagConstraints43.fill = 1;
        this.GoalPanel.add(this.GoalStatementPanel, gridBagConstraints43);
        this.GoalCalsChangeField.setColumns(4);
        this.GoalCalsChangeField.setEditable(false);
        this.GoalCalsChangeField.setForeground(this.grey);
        this.GoalCalsChangeField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.GoalCalsChangeField.setMinimumSize(new Dimension(56, 28));
        this.GoalCalsChangeField.setNextFocusableComponent(this.GoalMaintenanceActChangeBox);
        final GridBagConstraints gridBagConstraints44 = new GridBagConstraints();
        gridBagConstraints44.gridx = 1;
        gridBagConstraints44.gridy = 5;
        gridBagConstraints44.anchor = 17;
        this.GoalPanel.add(this.GoalCalsChangeField, gridBagConstraints44);
        this.GoalInterventionLabel.setFont(new Font("Lucida Grande", 0, 14));
        this.GoalInterventionLabel.setText("Weight Change Phase");
        final GridBagConstraints gridBagConstraints45 = new GridBagConstraints();
        gridBagConstraints45.gridx = 0;
        gridBagConstraints45.gridy = 2;
        gridBagConstraints45.gridwidth = 2;
        this.GoalPanel.add(this.GoalInterventionLabel, gridBagConstraints45);
        this.GoalMaintCalsChangeLabel.setText("Calories, which is a change of");
        final GridBagConstraints gridBagConstraints46 = new GridBagConstraints();
        gridBagConstraints46.gridx = 3;
        gridBagConstraints46.gridy = 5;
        gridBagConstraints46.anchor = 17;
        this.GoalPanel.add(this.GoalMaintCalsChangeLabel, gridBagConstraints46);
        this.GoalMaintCalsChangeField.setColumns(4);
        this.GoalMaintCalsChangeField.setEditable(false);
        this.GoalMaintCalsChangeField.setForeground(this.grey);
        this.GoalMaintCalsChangeField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.GoalMaintCalsChangeField.setMinimumSize(new Dimension(56, 28));
        this.GoalMaintCalsChangeField.setNextFocusableComponent(this.RunGoalButton);
        final GridBagConstraints gridBagConstraints47 = new GridBagConstraints();
        gridBagConstraints47.gridx = 4;
        gridBagConstraints47.gridy = 5;
        gridBagConstraints47.anchor = 17;
        this.GoalPanel.add(this.GoalMaintCalsChangeField, gridBagConstraints47);
        this.GoalActivityLabel.setText("If you change your physical activity by");
        final GridBagConstraints gridBagConstraints48 = new GridBagConstraints();
        gridBagConstraints48.gridx = 0;
        gridBagConstraints48.gridy = 3;
        gridBagConstraints48.anchor = 17;
        this.GoalPanel.add(this.GoalActivityLabel, gridBagConstraints48);
        this.GoalActChangeBox.setModel(new DefaultComboBoxModel<String>(new String[] { "-100%", "-80%", "-60%", "-40%", "-20%", "0%", "Detailed...", "20%", "40%", "60%", "80%", "100%", "120%", "140%", "160%", "180%", "200%", "220%", "240%", "260%", "280%", "300%", "320%", "340%", "360%", "380%", "400%" }));
        this.GoalActChangeBox.setSelectedIndex(5);
        this.GoalActChangeBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
        this.GoalActChangeBox.setNextFocusableComponent(this.GoalCalsField);
        this.GoalActChangeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.GoalActChangeBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints49 = new GridBagConstraints();
        gridBagConstraints49.gridx = 1;
        gridBagConstraints49.gridy = 3;
        this.GoalPanel.add(this.GoalActChangeBox, gridBagConstraints49);
        this.GoalCalsField.setBackground(this.highlightyellow);
        this.GoalCalsField.setColumns(4);
        this.GoalCalsField.setEditable(false);
        this.GoalCalsField.setForeground(this.grey);
        this.GoalCalsField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.GoalCalsField.setMinimumSize(new Dimension(56, 28));
        this.GoalCalsField.setNextFocusableComponent(this.GoalCalsChangeField);
        final GridBagConstraints gridBagConstraints50 = new GridBagConstraints();
        gridBagConstraints50.gridx = 1;
        gridBagConstraints50.gridy = 4;
        gridBagConstraints50.anchor = 17;
        this.GoalPanel.add(this.GoalCalsField, gridBagConstraints50);
        this.GoalCaloriesLabel.setText("you can meet your goal by eating");
        final GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
        gridBagConstraints51.gridx = 0;
        gridBagConstraints51.gridy = 4;
        gridBagConstraints51.anchor = 17;
        this.GoalPanel.add(this.GoalCaloriesLabel, gridBagConstraints51);
        this.GoalMaintenanceActivityLabel.setText("If you permanently change your activity by ");
        final GridBagConstraints gridBagConstraints52 = new GridBagConstraints();
        gridBagConstraints52.gridx = 3;
        gridBagConstraints52.gridy = 3;
        gridBagConstraints52.anchor = 17;
        this.GoalPanel.add(this.GoalMaintenanceActivityLabel, gridBagConstraints52);
        this.GoalMaintenanceActChangeBox.setModel(new DefaultComboBoxModel<String>(new String[] { "-100%", "-80%", "-60%", "-40%", "-20%", "0%", "Detailed...", "20%", "40%", "60%", "80%", "100%", "120%", "140%", "160%", "180%", "200%", "220%", "240%", "260%", "280%", "300%", "320%", "340%", "360%", "380%", "400%" }));
        this.GoalMaintenanceActChangeBox.setSelectedIndex(5);
        this.GoalMaintenanceActChangeBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
        this.GoalMaintenanceActChangeBox.setNextFocusableComponent(this.GoalMaintCalsField);
        this.GoalMaintenanceActChangeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.GoalMaintenanceActChangeBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints53 = new GridBagConstraints();
        gridBagConstraints53.gridx = 4;
        gridBagConstraints53.gridy = 3;
        this.GoalPanel.add(this.GoalMaintenanceActChangeBox, gridBagConstraints53);
        this.GoalMaintCalsLabel.setText("you can maintain your goal by eating");
        final GridBagConstraints gridBagConstraints54 = new GridBagConstraints();
        gridBagConstraints54.gridx = 3;
        gridBagConstraints54.gridy = 4;
        gridBagConstraints54.anchor = 17;
        this.GoalPanel.add(this.GoalMaintCalsLabel, gridBagConstraints54);
        this.GoalCalsChangeLabel.setText("Calories, which is a change of");
        final GridBagConstraints gridBagConstraints55 = new GridBagConstraints();
        gridBagConstraints55.gridx = 0;
        gridBagConstraints55.gridy = 5;
        gridBagConstraints55.anchor = 17;
        this.GoalPanel.add(this.GoalCalsChangeLabel, gridBagConstraints55);
        this.GoalSpaceLabel.setText("     ");
        final GridBagConstraints gridBagConstraints56 = new GridBagConstraints();
        gridBagConstraints56.gridx = 2;
        gridBagConstraints56.gridy = 2;
        this.GoalPanel.add(this.GoalSpaceLabel, gridBagConstraints56);
        this.GoalMaintCalsField.setBackground(this.highlightyellow);
        this.GoalMaintCalsField.setColumns(4);
        this.GoalMaintCalsField.setEditable(false);
        this.GoalMaintCalsField.setForeground(this.grey);
        this.GoalMaintCalsField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.GoalMaintCalsField.setMinimumSize(new Dimension(56, 28));
        this.GoalMaintCalsField.setNextFocusableComponent(this.GoalMaintCalsChangeField);
        final GridBagConstraints gridBagConstraints57 = new GridBagConstraints();
        gridBagConstraints57.gridx = 4;
        gridBagConstraints57.gridy = 4;
        gridBagConstraints57.anchor = 17;
        this.GoalPanel.add(this.GoalMaintCalsField, gridBagConstraints57);
        this.ChoicePanel.addTab("Set Your Goal Weight", this.GoalPanel);
        this.InterventionPanel.setLayout(new GridBagLayout());
        this.Intervention1Label.setText("First Change");
        this.InterventionPanel.add(this.Intervention1Label, new GridBagConstraints());
        this.Intervention1CheckBox.setSelected(true);
        this.Intervention1CheckBox.setText("On");
        this.Intervention1CheckBox.setNextFocusableComponent(this.Intervention1DayField);
        this.Intervention1CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention1CheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints58 = new GridBagConstraints();
        gridBagConstraints58.anchor = 17;
        this.InterventionPanel.add(this.Intervention1CheckBox, gridBagConstraints58);
        this.Intervention1DayLabel.setText("Start Change on Day:");
        final GridBagConstraints gridBagConstraints59 = new GridBagConstraints();
        gridBagConstraints59.gridx = 0;
        gridBagConstraints59.gridy = 1;
        gridBagConstraints59.anchor = 17;
        this.InterventionPanel.add(this.Intervention1DayLabel, gridBagConstraints59);
        this.Intervention2DayField.setBackground(this.highlightgreen);
        this.Intervention2DayField.setColumns(3);
        this.Intervention2DayField.setMinimumSize(new Dimension(56, 28));
        this.Intervention2DayField.setNextFocusableComponent(this.Intervention2CaloriesField);
        this.Intervention2DayField.setValue(300);
        this.Intervention2DayField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention2DayFieldActionPerformed(actionEvent);
            }
        });
        this.Intervention2DayField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.Intervention2DayFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints60 = new GridBagConstraints();
        gridBagConstraints60.gridx = 4;
        gridBagConstraints60.gridy = 1;
        gridBagConstraints60.anchor = 17;
        this.InterventionPanel.add(this.Intervention2DayField, gridBagConstraints60);
        this.Intervention1CaloriesLabel.setText("New Calories");
        final GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
        gridBagConstraints61.gridx = 0;
        gridBagConstraints61.gridy = 3;
        gridBagConstraints61.anchor = 17;
        this.InterventionPanel.add(this.Intervention1CaloriesLabel, gridBagConstraints61);
        this.Intervention2CaloriesField.setBackground(this.highlightgreen);
        this.Intervention2CaloriesField.setColumns(4);
        this.Intervention2CaloriesField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.Intervention2CaloriesField.setMinimumSize(new Dimension(56, 28));
        this.Intervention2CaloriesField.setNextFocusableComponent(this.Intervention2CarbInPercField);
        this.Intervention2CaloriesField.setValue(2500);
        this.Intervention2CaloriesField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention2CaloriesFieldActionPerformed(actionEvent);
            }
        });
        this.Intervention2CaloriesField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.Intervention2CaloriesFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints62 = new GridBagConstraints();
        gridBagConstraints62.gridx = 4;
        gridBagConstraints62.gridy = 3;
        gridBagConstraints62.anchor = 17;
        this.InterventionPanel.add(this.Intervention2CaloriesField, gridBagConstraints62);
        this.Intervention1CarbInPercLabel.setText("New Diet Carb %");
        final GridBagConstraints gridBagConstraints63 = new GridBagConstraints();
        gridBagConstraints63.gridx = 0;
        gridBagConstraints63.gridy = 5;
        gridBagConstraints63.anchor = 17;
        this.InterventionPanel.add(this.Intervention1CarbInPercLabel, gridBagConstraints63);
        this.Intervention2CarbInPercField.setBackground(this.highlightblue);
        this.Intervention2CarbInPercField.setColumns(3);
        this.Intervention2CarbInPercField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.Intervention2CarbInPercField.setMinimumSize(new Dimension(56, 28));
        this.Intervention2CarbInPercField.setNextFocusableComponent(this.Intervention2ActChangeBox);
        this.Intervention2CarbInPercField.setValue(25);
        this.Intervention2CarbInPercField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention2CarbInPercFieldActionPerformed(actionEvent);
            }
        });
        this.Intervention2CarbInPercField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.Intervention2CarbInPercFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints64 = new GridBagConstraints();
        gridBagConstraints64.gridx = 4;
        gridBagConstraints64.gridy = 5;
        gridBagConstraints64.anchor = 17;
        this.InterventionPanel.add(this.Intervention2CarbInPercField, gridBagConstraints64);
        this.Intervention1ActivityLabel.setText("% Change in Physical Activity");
        final GridBagConstraints gridBagConstraints65 = new GridBagConstraints();
        gridBagConstraints65.gridx = 0;
        gridBagConstraints65.gridy = 4;
        gridBagConstraints65.anchor = 17;
        this.InterventionPanel.add(this.Intervention1ActivityLabel, gridBagConstraints65);
        this.Intervention2ActChangeBox.setModel(new DefaultComboBoxModel<String>(new String[] { "-100", "-80", "-60", "-40", "-20", "0", "Detailed...", "20", "40", "60", "80", "100", "120", "140", "160", "180", "200", "220", "240", "260", "280", "300", "320", "340", "360", "380", "400" }));
        this.Intervention2ActChangeBox.setSelectedIndex(5);
        this.Intervention2ActChangeBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
        this.Intervention2ActChangeBox.setNextFocusableComponent(this.Intervention2SodiumField);
        this.Intervention2ActChangeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention2ActChangeBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints66 = new GridBagConstraints();
        gridBagConstraints66.gridx = 4;
        gridBagConstraints66.gridy = 4;
        gridBagConstraints66.anchor = 17;
        this.InterventionPanel.add(this.Intervention2ActChangeBox, gridBagConstraints66);
        this.Intervention1SodiumLabel.setText("New Sodium (mg/day)");
        final GridBagConstraints gridBagConstraints67 = new GridBagConstraints();
        gridBagConstraints67.gridx = 0;
        gridBagConstraints67.gridy = 7;
        gridBagConstraints67.anchor = 17;
        this.InterventionPanel.add(this.Intervention1SodiumLabel, gridBagConstraints67);
        this.SpaceLabel.setText("       ");
        this.InterventionPanel.add(this.SpaceLabel, new GridBagConstraints());
        this.Intervention1SodiumRadioGroup.add(this.Intervention1SodiumCalculatedRadio);
        this.Intervention1SodiumCalculatedRadio.setSelected(true);
        this.Intervention1SodiumCalculatedRadio.setText("Automatic");
        this.Intervention1SodiumCalculatedRadio.setNextFocusableComponent(this.Intervention1SodiumInputRadio);
        this.Intervention1SodiumCalculatedRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention1SodiumCalculatedRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints68 = new GridBagConstraints();
        gridBagConstraints68.gridx = 0;
        gridBagConstraints68.gridy = 8;
        gridBagConstraints68.anchor = 17;
        this.InterventionPanel.add(this.Intervention1SodiumCalculatedRadio, gridBagConstraints68);
        this.Intervention1SodiumRadioGroup.add(this.Intervention1SodiumInputRadio);
        this.Intervention1SodiumInputRadio.setText("Input");
        this.Intervention1SodiumInputRadio.setNextFocusableComponent(this.Intervention2CheckBox);
        this.Intervention1SodiumInputRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention1SodiumInputRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints69 = new GridBagConstraints();
        gridBagConstraints69.gridx = 1;
        gridBagConstraints69.gridy = 8;
        gridBagConstraints69.anchor = 17;
        this.InterventionPanel.add(this.Intervention1SodiumInputRadio, gridBagConstraints69);
        this.Intervention1SodiumField.setColumns(3);
        this.Intervention1SodiumField.setEditable(false);
        this.Intervention1SodiumField.setForeground(new Color(102, 102, 102));
        this.Intervention1SodiumField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.Intervention1SodiumField.setMinimumSize(new Dimension(56, 28));
        this.Intervention1SodiumField.setNextFocusableComponent(this.Intervention1SodiumCalculatedRadio);
        this.Intervention1SodiumField.setValue(4000);
        this.Intervention1SodiumField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention1SodiumFieldActionPerformed(actionEvent);
            }
        });
        this.Intervention1SodiumField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.Intervention1SodiumFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints70 = new GridBagConstraints();
        gridBagConstraints70.gridx = 1;
        gridBagConstraints70.gridy = 7;
        gridBagConstraints70.anchor = 17;
        this.InterventionPanel.add(this.Intervention1SodiumField, gridBagConstraints70);
        this.Intervention2Label.setText("Second Change");
        this.InterventionPanel.add(this.Intervention2Label, new GridBagConstraints());
        this.Intervention2CheckBox.setSelected(true);
        this.Intervention2CheckBox.setText("On");
        this.Intervention2CheckBox.setNextFocusableComponent(this.Intervention2DayField);
        this.Intervention2CheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention2CheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
        gridBagConstraints71.anchor = 17;
        this.InterventionPanel.add(this.Intervention2CheckBox, gridBagConstraints71);
        this.Intervention2DayLabel.setText("Start Change on Day:");
        final GridBagConstraints gridBagConstraints72 = new GridBagConstraints();
        gridBagConstraints72.gridx = 3;
        gridBagConstraints72.gridy = 1;
        gridBagConstraints72.anchor = 17;
        this.InterventionPanel.add(this.Intervention2DayLabel, gridBagConstraints72);
        this.Intervention1DayField.setBackground(this.highlightgreen);
        this.Intervention1DayField.setColumns(3);
        this.Intervention1DayField.setMinimumSize(new Dimension(56, 28));
        this.Intervention1DayField.setNextFocusableComponent(this.Intervention1CaloriesField);
        this.Intervention1DayField.setValue(100);
        this.Intervention1DayField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention1DayFieldActionPerformed(actionEvent);
            }
        });
        this.Intervention1DayField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.Intervention1DayFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints73 = new GridBagConstraints();
        gridBagConstraints73.gridx = 1;
        gridBagConstraints73.gridy = 1;
        gridBagConstraints73.anchor = 17;
        this.InterventionPanel.add(this.Intervention1DayField, gridBagConstraints73);
        this.Intervention2CaloriesLabel.setText("New Calories");
        final GridBagConstraints gridBagConstraints74 = new GridBagConstraints();
        gridBagConstraints74.gridx = 3;
        gridBagConstraints74.gridy = 3;
        gridBagConstraints74.anchor = 17;
        this.InterventionPanel.add(this.Intervention2CaloriesLabel, gridBagConstraints74);
        this.Intervention1CaloriesField.setBackground(this.highlightgreen);
        this.Intervention1CaloriesField.setColumns(4);
        this.Intervention1CaloriesField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.Intervention1CaloriesField.setMinimumSize(new Dimension(56, 28));
        this.Intervention1CaloriesField.setNextFocusableComponent(this.Intervention1CarbInPercField);
        this.Intervention1CaloriesField.setValue(2000);
        this.Intervention1CaloriesField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention1CaloriesFieldActionPerformed(actionEvent);
            }
        });
        this.Intervention1CaloriesField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.Intervention1CaloriesFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints75 = new GridBagConstraints();
        gridBagConstraints75.gridx = 1;
        gridBagConstraints75.gridy = 3;
        gridBagConstraints75.anchor = 17;
        this.InterventionPanel.add(this.Intervention1CaloriesField, gridBagConstraints75);
        this.Intervention2CarbInPercLabel.setText("New Diet Carb %");
        final GridBagConstraints gridBagConstraints76 = new GridBagConstraints();
        gridBagConstraints76.gridx = 3;
        gridBagConstraints76.gridy = 5;
        gridBagConstraints76.anchor = 17;
        this.InterventionPanel.add(this.Intervention2CarbInPercLabel, gridBagConstraints76);
        this.Intervention1CarbInPercField.setBackground(this.highlightblue);
        this.Intervention1CarbInPercField.setColumns(3);
        this.Intervention1CarbInPercField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.Intervention1CarbInPercField.setMinimumSize(new Dimension(56, 28));
        this.Intervention1CarbInPercField.setNextFocusableComponent(this.Intervention1ActChangeBox);
        this.Intervention1CarbInPercField.setValue(50);
        this.Intervention1CarbInPercField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention1CarbInPercFieldActionPerformed(actionEvent);
            }
        });
        this.Intervention1CarbInPercField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.Intervention1CarbInPercFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints77 = new GridBagConstraints();
        gridBagConstraints77.gridx = 1;
        gridBagConstraints77.gridy = 5;
        gridBagConstraints77.anchor = 17;
        this.InterventionPanel.add(this.Intervention1CarbInPercField, gridBagConstraints77);
        this.Intervention2ActivityLabel.setText("% Change in Physical Activity");
        final GridBagConstraints gridBagConstraints78 = new GridBagConstraints();
        gridBagConstraints78.gridx = 3;
        gridBagConstraints78.gridy = 4;
        gridBagConstraints78.anchor = 17;
        this.InterventionPanel.add(this.Intervention2ActivityLabel, gridBagConstraints78);
        this.Intervention1ActChangeBox.setModel(new DefaultComboBoxModel<String>(new String[] { "-100", "-80", "-60", "-40", "-20", "0", "Detailed...", "20", "40", "60", "80", "100", "120", "140", "160", "180", "200", "220", "240", "260", "280", "300", "320", "340", "360", "380", "400" }));
        this.Intervention1ActChangeBox.setSelectedIndex(5);
        this.Intervention1ActChangeBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
        this.Intervention1ActChangeBox.setNextFocusableComponent(this.Intervention1SodiumField);
        this.Intervention1ActChangeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention1ActChangeBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints79 = new GridBagConstraints();
        gridBagConstraints79.gridx = 1;
        gridBagConstraints79.gridy = 4;
        gridBagConstraints79.anchor = 17;
        this.InterventionPanel.add(this.Intervention1ActChangeBox, gridBagConstraints79);
        this.Intervention2SodiumLabel.setText("New Sodium (mg/day)");
        final GridBagConstraints gridBagConstraints80 = new GridBagConstraints();
        gridBagConstraints80.gridx = 3;
        gridBagConstraints80.gridy = 7;
        gridBagConstraints80.anchor = 17;
        this.InterventionPanel.add(this.Intervention2SodiumLabel, gridBagConstraints80);
        this.Intervention2SodiumRadioGroup.add(this.Intervention2SodiumCalculatedRadio);
        this.Intervention2SodiumCalculatedRadio.setSelected(true);
        this.Intervention2SodiumCalculatedRadio.setText("Automatic");
        this.Intervention2SodiumCalculatedRadio.setNextFocusableComponent(this.Intervention2SodiumInputRadio);
        this.Intervention2SodiumCalculatedRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention2SodiumCalculatedRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
        gridBagConstraints81.gridx = 3;
        gridBagConstraints81.gridy = 8;
        gridBagConstraints81.anchor = 17;
        this.InterventionPanel.add(this.Intervention2SodiumCalculatedRadio, gridBagConstraints81);
        this.Intervention2SodiumRadioGroup.add(this.Intervention2SodiumInputRadio);
        this.Intervention2SodiumInputRadio.setText("Input");
        this.Intervention2SodiumInputRadio.setNextFocusableComponent(this.RunInterventionButton);
        this.Intervention2SodiumInputRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention2SodiumInputRadioActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints82 = new GridBagConstraints();
        gridBagConstraints82.gridx = 4;
        gridBagConstraints82.gridy = 8;
        gridBagConstraints82.anchor = 17;
        this.InterventionPanel.add(this.Intervention2SodiumInputRadio, gridBagConstraints82);
        this.Intervention2SodiumField.setColumns(3);
        this.Intervention2SodiumField.setEditable(false);
        this.Intervention2SodiumField.setForeground(new Color(102, 102, 102));
        this.Intervention2SodiumField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.Intervention2SodiumField.setMinimumSize(new Dimension(56, 28));
        this.Intervention2SodiumField.setNextFocusableComponent(this.Intervention2SodiumCalculatedRadio);
        this.Intervention2SodiumField.setValue(4000);
        this.Intervention2SodiumField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention2SodiumFieldActionPerformed(actionEvent);
            }
        });
        this.Intervention2SodiumField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.Intervention2SodiumFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints83 = new GridBagConstraints();
        gridBagConstraints83.gridx = 4;
        gridBagConstraints83.gridy = 7;
        gridBagConstraints83.anchor = 17;
        this.InterventionPanel.add(this.Intervention2SodiumField, gridBagConstraints83);
        this.RunInterventionButton.setText("Run  Simulation");
        this.RunInterventionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.RunInterventionButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints84 = new GridBagConstraints();
        gridBagConstraints84.gridx = 0;
        gridBagConstraints84.gridy = 9;
        gridBagConstraints84.gridwidth = 5;
        this.InterventionPanel.add(this.RunInterventionButton, gridBagConstraints84);
        final GridBagConstraints gridBagConstraints85 = new GridBagConstraints();
        gridBagConstraints85.gridx = 0;
        gridBagConstraints85.gridy = 6;
        gridBagConstraints85.gridwidth = 2;
        gridBagConstraints85.ipadx = 100;
        this.InterventionPanel.add(this.Intervention1SodiumSeparator, gridBagConstraints85);
        final GridBagConstraints gridBagConstraints86 = new GridBagConstraints();
        gridBagConstraints86.gridx = 3;
        gridBagConstraints86.gridy = 6;
        gridBagConstraints86.gridwidth = 2;
        gridBagConstraints86.ipadx = 100;
        this.InterventionPanel.add(this.Intervention2SodiumSeparator, gridBagConstraints86);
        this.Intervention1RampCheckBox.setText("Gradually Ramp Changes");
        this.Intervention1RampCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention1RampCheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints87 = new GridBagConstraints();
        gridBagConstraints87.gridx = 0;
        gridBagConstraints87.gridy = 2;
        gridBagConstraints87.gridwidth = 2;
        gridBagConstraints87.anchor = 17;
        this.InterventionPanel.add(this.Intervention1RampCheckBox, gridBagConstraints87);
        this.Intervention2RampCheckBox.setText("Gradually Ramp Changes");
        this.Intervention2RampCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.Intervention2RampCheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints88 = new GridBagConstraints();
        gridBagConstraints88.gridx = 3;
        gridBagConstraints88.gridy = 2;
        gridBagConstraints88.gridwidth = 2;
        gridBagConstraints88.anchor = 17;
        this.InterventionPanel.add(this.Intervention2RampCheckBox, gridBagConstraints88);
        this.ChoicePanel.addTab("...or Specify a Lifestyle Change", this.InterventionPanel);
        final GridBagConstraints gridBagConstraints89 = new GridBagConstraints();
        gridBagConstraints89.gridx = 1;
        gridBagConstraints89.gridy = 0;
        gridBagConstraints89.gridwidth = 2;
        gridBagConstraints89.fill = 1;
        gridBagConstraints89.ipadx = 125;
        gridBagConstraints89.ipady = 20;
        this.add(this.ChoicePanel, gridBagConstraints89);
        this.ComparePanel.setBorder(BorderFactory.createEtchedBorder());
        this.ComparePanel.setLayout(new GridBagLayout());
        this.InitialWeightLabel2.setText("Initial Weight");
        this.ComparePanel.add(this.InitialWeightLabel2, new GridBagConstraints());
        this.InitialWeightField2.setColumns(3);
        this.InitialWeightField2.setEditable(false);
        this.InitialWeightField2.setForeground(new Color(102, 102, 102));
        this.InitialWeightField2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        final GridBagConstraints gridBagConstraints90 = new GridBagConstraints();
        gridBagConstraints90.gridx = 1;
        gridBagConstraints90.gridy = 0;
        this.ComparePanel.add(this.InitialWeightField2, gridBagConstraints90);
        this.InitialBfpLabel2.setText("Initial Fat %");
        final GridBagConstraints gridBagConstraints91 = new GridBagConstraints();
        gridBagConstraints91.gridx = 2;
        gridBagConstraints91.gridy = 0;
        this.ComparePanel.add(this.InitialBfpLabel2, gridBagConstraints91);
        this.InitialBMILabel.setText("Initial BMI");
        final GridBagConstraints gridBagConstraints92 = new GridBagConstraints();
        gridBagConstraints92.gridx = 4;
        gridBagConstraints92.gridy = 0;
        this.ComparePanel.add(this.InitialBMILabel, gridBagConstraints92);
        this.FinalWeightLabel.setText("Final Weight");
        final GridBagConstraints gridBagConstraints93 = new GridBagConstraints();
        gridBagConstraints93.gridx = 0;
        gridBagConstraints93.gridy = 1;
        this.ComparePanel.add(this.FinalWeightLabel, gridBagConstraints93);
        this.FinalBMILabel.setText("Final BMI");
        final GridBagConstraints gridBagConstraints94 = new GridBagConstraints();
        gridBagConstraints94.gridx = 4;
        gridBagConstraints94.gridy = 1;
        this.ComparePanel.add(this.FinalBMILabel, gridBagConstraints94);
        this.FinalBMIField.setBackground(this.highlightyellow);
        this.FinalBMIField.setColumns(3);
        this.FinalBMIField.setEditable(false);
        this.FinalBMIField.setForeground(new Color(102, 102, 102));
        this.FinalBMIField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        final GridBagConstraints gridBagConstraints95 = new GridBagConstraints();
        gridBagConstraints95.gridx = 5;
        gridBagConstraints95.gridy = 1;
        this.ComparePanel.add(this.FinalBMIField, gridBagConstraints95);
        this.FinalBfpLabel.setText("Final Fat %");
        final GridBagConstraints gridBagConstraints96 = new GridBagConstraints();
        gridBagConstraints96.gridx = 2;
        gridBagConstraints96.gridy = 1;
        this.ComparePanel.add(this.FinalBfpLabel, gridBagConstraints96);
        this.FinalBfpField.setBackground(this.highlightyellow);
        this.FinalBfpField.setColumns(3);
        this.FinalBfpField.setEditable(false);
        this.FinalBfpField.setForeground(new Color(102, 102, 102));
        this.FinalBfpField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        final GridBagConstraints gridBagConstraints97 = new GridBagConstraints();
        gridBagConstraints97.gridx = 3;
        gridBagConstraints97.gridy = 1;
        this.ComparePanel.add(this.FinalBfpField, gridBagConstraints97);
        this.FinalWeightField.setBackground(this.highlightyellow);
        this.FinalWeightField.setColumns(3);
        this.FinalWeightField.setEditable(false);
        this.FinalWeightField.setForeground(new Color(102, 102, 102));
        this.FinalWeightField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        final GridBagConstraints gridBagConstraints98 = new GridBagConstraints();
        gridBagConstraints98.gridx = 1;
        gridBagConstraints98.gridy = 1;
        this.ComparePanel.add(this.FinalWeightField, gridBagConstraints98);
        this.InitialBfpField2.setColumns(3);
        this.InitialBfpField2.setEditable(false);
        this.InitialBfpField2.setForeground(new Color(102, 102, 102));
        this.InitialBfpField2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        final GridBagConstraints gridBagConstraints99 = new GridBagConstraints();
        gridBagConstraints99.gridx = 3;
        gridBagConstraints99.gridy = 0;
        this.ComparePanel.add(this.InitialBfpField2, gridBagConstraints99);
        this.InitialBMIField.setColumns(3);
        this.InitialBMIField.setEditable(false);
        this.InitialBMIField.setForeground(new Color(102, 102, 102));
        this.InitialBMIField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0.0"))));
        final GridBagConstraints gridBagConstraints100 = new GridBagConstraints();
        gridBagConstraints100.gridx = 5;
        gridBagConstraints100.gridy = 0;
        this.ComparePanel.add(this.InitialBMIField, gridBagConstraints100);
        final GridBagConstraints gridBagConstraints101 = new GridBagConstraints();
        gridBagConstraints101.gridx = 1;
        gridBagConstraints101.gridy = 2;
        gridBagConstraints101.fill = 1;
        this.add(this.ComparePanel, gridBagConstraints101);
        this.ZoomPanel.setBorder(BorderFactory.createEtchedBorder());
        this.ZoomPanel.setLayout(new GridBagLayout());
        this.VerticalZoomMinLabel.setText("Zoomed Out");
        final GridBagConstraints gridBagConstraints102 = new GridBagConstraints();
        gridBagConstraints102.gridx = 0;
        gridBagConstraints102.gridy = 7;
        gridBagConstraints102.anchor = 13;
        this.ZoomPanel.add(this.VerticalZoomMinLabel, gridBagConstraints102);
        this.SimulationLengthLabel.setText("Length of Simulation (days)");
        final GridBagConstraints gridBagConstraints103 = new GridBagConstraints();
        gridBagConstraints103.gridx = 1;
        gridBagConstraints103.gridy = 1;
        this.ZoomPanel.add(this.SimulationLengthLabel, gridBagConstraints103);
        this.SimulationLengthField.setBackground(this.highlightgreen);
        this.SimulationLengthField.setColumns(4);
        this.SimulationLengthField.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(new DecimalFormat("#0"))));
        this.SimulationLengthField.setValue(400);
        this.SimulationLengthField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.SimulationLengthFieldActionPerformed(actionEvent);
            }
        });
        this.SimulationLengthField.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
                MainPanel.this.SimulationLengthFieldPropertyChange(propertyChangeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints104 = new GridBagConstraints();
        gridBagConstraints104.gridx = 2;
        gridBagConstraints104.gridy = 1;
        this.ZoomPanel.add(this.SimulationLengthField, gridBagConstraints104);
        this.TimeStartLabel.setText("First Day Graphed");
        final GridBagConstraints gridBagConstraints105 = new GridBagConstraints();
        gridBagConstraints105.gridx = 1;
        gridBagConstraints105.gridy = 2;
        this.ZoomPanel.add(this.TimeStartLabel, gridBagConstraints105);
        this.StatusLabel.setFont(new Font("Lucida Grande", 0, 14));
        this.StatusLabel.setForeground(new Color(0, 153, 0));
        this.StatusLabel.setText("Goal Simulation Displayed");
        final GridBagConstraints gridBagConstraints106 = new GridBagConstraints();
        gridBagConstraints106.gridx = 1;
        gridBagConstraints106.gridy = 0;
        this.ZoomPanel.add(this.StatusLabel, gridBagConstraints106);
        this.VerticalZoomSlider.setMinorTickSpacing(10);
        this.VerticalZoomSlider.setPaintTicks(true);
        this.VerticalZoomSlider.setValue(0);
        this.VerticalZoomSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                MainPanel.this.VerticalZoomSliderStateChanged(changeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints107 = new GridBagConstraints();
        gridBagConstraints107.gridx = 1;
        gridBagConstraints107.gridy = 7;
        this.ZoomPanel.add(this.VerticalZoomSlider, gridBagConstraints107);
        this.VerticalZoomMaxLabel.setText("Zoomed In");
        final GridBagConstraints gridBagConstraints108 = new GridBagConstraints();
        gridBagConstraints108.gridx = 2;
        gridBagConstraints108.gridy = 7;
        gridBagConstraints108.anchor = 17;
        this.ZoomPanel.add(this.VerticalZoomMaxLabel, gridBagConstraints108);
        this.TimeZoomMinLabel.setText("Zoomed Out");
        final GridBagConstraints gridBagConstraints109 = new GridBagConstraints();
        gridBagConstraints109.gridx = 0;
        gridBagConstraints109.gridy = 5;
        gridBagConstraints109.anchor = 13;
        this.ZoomPanel.add(this.TimeZoomMinLabel, gridBagConstraints109);
        this.TimeZoomSlider.setMinorTickSpacing(10);
        this.TimeZoomSlider.setPaintTicks(true);
        this.TimeZoomSlider.setValue(0);
        this.TimeZoomSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                MainPanel.this.TimeZoomSliderStateChanged(changeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints110 = new GridBagConstraints();
        gridBagConstraints110.gridx = 1;
        gridBagConstraints110.gridy = 5;
        this.ZoomPanel.add(this.TimeZoomSlider, gridBagConstraints110);
        this.TimeZoomMaxLabel.setText("Zoomed In");
        final GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
        gridBagConstraints111.gridx = 2;
        gridBagConstraints111.gridy = 5;
        gridBagConstraints111.anchor = 17;
        this.ZoomPanel.add(this.TimeZoomMaxLabel, gridBagConstraints111);
        this.TimeStartMinLabel.setText("Min");
        final GridBagConstraints gridBagConstraints112 = new GridBagConstraints();
        gridBagConstraints112.gridx = 0;
        gridBagConstraints112.gridy = 3;
        gridBagConstraints112.anchor = 13;
        this.ZoomPanel.add(this.TimeStartMinLabel, gridBagConstraints112);
        this.TimeStartSlider.setMinorTickSpacing(10);
        this.TimeStartSlider.setPaintTicks(true);
        this.TimeStartSlider.setValue(0);
        this.TimeStartSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent changeEvent) {
                MainPanel.this.TimeStartSliderStateChanged(changeEvent);
            }
        });
        final GridBagConstraints gridBagConstraints113 = new GridBagConstraints();
        gridBagConstraints113.gridx = 1;
        gridBagConstraints113.gridy = 3;
        this.ZoomPanel.add(this.TimeStartSlider, gridBagConstraints113);
        this.TimeStartMaxLabel.setText("Max");
        final GridBagConstraints gridBagConstraints114 = new GridBagConstraints();
        gridBagConstraints114.gridx = 2;
        gridBagConstraints114.gridy = 3;
        gridBagConstraints114.anchor = 17;
        this.ZoomPanel.add(this.TimeStartMaxLabel, gridBagConstraints114);
        this.TimeZoomLabel.setText("Horizontal Zoom");
        final GridBagConstraints gridBagConstraints115 = new GridBagConstraints();
        gridBagConstraints115.gridx = 1;
        gridBagConstraints115.gridy = 4;
        this.ZoomPanel.add(this.TimeZoomLabel, gridBagConstraints115);
        this.VerticalZoomLabel.setText("Vertical Zoom");
        final GridBagConstraints gridBagConstraints116 = new GridBagConstraints();
        gridBagConstraints116.gridx = 1;
        gridBagConstraints116.gridy = 6;
        this.ZoomPanel.add(this.VerticalZoomLabel, gridBagConstraints116);
        final GridBagConstraints gridBagConstraints117 = new GridBagConstraints();
        gridBagConstraints117.gridx = 1;
        gridBagConstraints117.gridy = 1;
        gridBagConstraints117.fill = 1;
        gridBagConstraints117.weightx = 0.75;
        this.add(this.ZoomPanel, gridBagConstraints117);
        this.jPanel2.setMinimumSize(new Dimension(20, 300));
        this.jPanel2.setOpaque(false);
        this.jPanel2.setPreferredSize(new Dimension(400, 250));
        this.jPanel2.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints118 = new GridBagConstraints();
        gridBagConstraints118.gridx = 0;
        gridBagConstraints118.gridy = 3;
        gridBagConstraints118.gridwidth = 3;
        gridBagConstraints118.fill = 1;
        this.add(this.jPanel2, gridBagConstraints118);
        this.OptionsPanel.setBorder(BorderFactory.createEtchedBorder());
        this.OptionsPanel.setLayout(new GridBagLayout());
        this.GraphAttachedCheckBox.setSelected(true);
        this.GraphAttachedCheckBox.setText("Graph Attached");
        this.GraphAttachedCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.GraphAttachedCheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints119 = new GridBagConstraints();
        gridBagConstraints119.gridx = 0;
        gridBagConstraints119.gridy = 8;
        gridBagConstraints119.gridwidth = 2;
        gridBagConstraints119.anchor = 17;
        this.OptionsPanel.add(this.GraphAttachedCheckBox, gridBagConstraints119);
        this.DefaultButton.setText("Restore Defaults");
        this.DefaultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.DefaultButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints120 = new GridBagConstraints();
        gridBagConstraints120.gridx = 0;
        gridBagConstraints120.gridy = 0;
        gridBagConstraints120.gridwidth = 2;
        gridBagConstraints120.insets = new Insets(0, 1, 0, 1);
        this.OptionsPanel.add(this.DefaultButton, gridBagConstraints120);
        this.GridCheckBox.setText("Show Grid");
        this.GridCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.GridCheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints121 = new GridBagConstraints();
        gridBagConstraints121.gridx = 0;
        gridBagConstraints121.gridy = 7;
        gridBagConstraints121.gridwidth = 2;
        gridBagConstraints121.anchor = 17;
        this.OptionsPanel.add(this.GridCheckBox, gridBagConstraints121);
        this.ShowLegendCheckBox.setSelected(true);
        this.ShowLegendCheckBox.setText("Show Legend");
        this.ShowLegendCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.ShowLegendCheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints122 = new GridBagConstraints();
        gridBagConstraints122.gridx = 0;
        gridBagConstraints122.gridy = 6;
        gridBagConstraints122.gridwidth = 2;
        gridBagConstraints122.anchor = 17;
        this.OptionsPanel.add(this.ShowLegendCheckBox, gridBagConstraints122);
        this.UncertaintyCheckBox.setText("Show Weight Range");
        this.UncertaintyCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.UncertaintyCheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints123 = new GridBagConstraints();
        gridBagConstraints123.gridx = 0;
        gridBagConstraints123.gridy = 5;
        gridBagConstraints123.gridwidth = 2;
        gridBagConstraints123.anchor = 17;
        this.OptionsPanel.add(this.UncertaintyCheckBox, gridBagConstraints123);
        this.HighlightCheckBox.setSelected(true);
        this.HighlightCheckBox.setText("Highlights On");
        this.HighlightCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.HighlightCheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints124 = new GridBagConstraints();
        gridBagConstraints124.gridx = 0;
        gridBagConstraints124.gridy = 3;
        gridBagConstraints124.gridwidth = 2;
        gridBagConstraints124.anchor = 17;
        this.OptionsPanel.add(this.HighlightCheckBox, gridBagConstraints124);
        this.AdvancedControlsCheckBox.setSelected(true);
        this.AdvancedControlsCheckBox.setText("Advanced Controls");
        this.AdvancedControlsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.AdvancedControlsCheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints125 = new GridBagConstraints();
        gridBagConstraints125.gridx = 0;
        gridBagConstraints125.gridy = 2;
        gridBagConstraints125.gridwidth = 2;
        gridBagConstraints125.anchor = 17;
        this.OptionsPanel.add(this.AdvancedControlsCheckBox, gridBagConstraints125);
        final GridBagConstraints gridBagConstraints126 = new GridBagConstraints();
        gridBagConstraints126.gridx = 0;
        gridBagConstraints126.gridy = 4;
        gridBagConstraints126.gridwidth = 2;
        gridBagConstraints126.fill = 2;
        this.OptionsPanel.add(this.jSeparator3, gridBagConstraints126);
        this.OverviewButton.setText("Help");
        this.OverviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.OverviewButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints127 = new GridBagConstraints();
        gridBagConstraints127.gridx = 0;
        gridBagConstraints127.gridy = 1;
        this.OptionsPanel.add(this.OverviewButton, gridBagConstraints127);
        this.DefaultColorsCheckBox.setSelected(true);
        this.DefaultColorsCheckBox.setText("Default Colors");
        this.DefaultColorsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.DefaultColorsCheckBoxActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints128 = new GridBagConstraints();
        gridBagConstraints128.gridx = 0;
        gridBagConstraints128.gridy = 9;
        gridBagConstraints128.gridwidth = 2;
        gridBagConstraints128.anchor = 17;
        this.OptionsPanel.add(this.DefaultColorsCheckBox, gridBagConstraints128);
        this.SaveButton.setText("Save...");
        this.SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                MainPanel.this.SaveButtonActionPerformed(actionEvent);
            }
        });
        final GridBagConstraints gridBagConstraints129 = new GridBagConstraints();
        gridBagConstraints129.gridx = 1;
        gridBagConstraints129.gridy = 1;
        this.OptionsPanel.add(this.SaveButton, gridBagConstraints129);
        final GridBagConstraints gridBagConstraints130 = new GridBagConstraints();
        gridBagConstraints130.gridx = 2;
        gridBagConstraints130.gridy = 1;
        gridBagConstraints130.gridheight = 2;
        gridBagConstraints130.fill = 1;
        this.add(this.OptionsPanel, gridBagConstraints130);
    }
    
    private void HeightFieldActionPerformed(final ActionEvent actionEvent) {
        this.HeightChange();
    }
    
    private void InitialBfpFieldActionPerformed(final ActionEvent actionEvent) {
        this.InitialBfpChange();
    }
    
    private void InitialCarbInPercFieldActionPerformed(final ActionEvent actionEvent) {
        this.CarbInPercChange();
    }
    
    private void InitialSodiumFieldActionPerformed(final ActionEvent actionEvent) {
        this.InitialSodiumChange();
    }
    
    private void InitialRMRFieldActionPerformed(final ActionEvent actionEvent) {
        this.InitialRMRChange();
    }
    
    private void KilogramsRadioActionPerformed(final ActionEvent actionEvent) {
        this.WeightUnitsChange();
    }
    
    private void PoundsRadioActionPerformed(final ActionEvent actionEvent) {
        this.WeightUnitsChange();
    }
    
    private void InitialBfpCalculatedRadioActionPerformed(final ActionEvent actionEvent) {
        this.BfpRadioChange();
    }
    
    private void InitialBfpInputRadioActionPerformed(final ActionEvent actionEvent) {
        this.BfpRadioChange();
    }
    
    private void RMRCalculatedRadioActionPerformed(final ActionEvent actionEvent) {
        this.RMRRadioChange();
    }
    
    private void RMRInputRadioActionPerformed(final ActionEvent actionEvent) {
        this.RMRRadioChange();
    }
    
    private void AgeFieldActionPerformed(final ActionEvent actionEvent) {
        this.AgeChange();
    }
    
    private void InchesRadioActionPerformed(final ActionEvent actionEvent) {
        this.HeightUnitsChange();
    }
    
    private void CentimetersRadioActionPerformed(final ActionEvent actionEvent) {
        this.HeightUnitsChange();
    }
    
    private void Intervention1SodiumCalculatedRadioActionPerformed(final ActionEvent actionEvent) {
        this.Intervention1SodiumRadioChange();
    }
    
    private void Intervention2SodiumCalculatedRadioActionPerformed(final ActionEvent actionEvent) {
        this.Intervention2SodiumRadioChange();
    }
    
    private void Intervention1CheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.Intervention1CheckBox.isSelected()) {
            this.Intervention1.seton(true);
            this.Intervention1DayField.setEnabled(true);
            this.Intervention1CaloriesField.setEnabled(true);
            this.Intervention1SodiumField.setEnabled(true);
            this.Intervention1CarbInPercField.setEnabled(true);
            this.Intervention1ActChangeBox.setEnabled(true);
            this.Intervention1SodiumCalculatedRadio.setEnabled(true);
            this.Intervention1SodiumInputRadio.setEnabled(true);
            if (this.HighlightCheckBox.isSelected()) {
                this.highlightIntervention1();
            }
        }
        else {
            this.Intervention1.seton(false);
            this.Intervention1DayField.setEnabled(false);
            this.Intervention1CaloriesField.setEnabled(false);
            this.Intervention1SodiumField.setEnabled(false);
            this.Intervention1CarbInPercField.setEnabled(false);
            this.Intervention1ActChangeBox.setEnabled(false);
            this.Intervention1SodiumCalculatedRadio.setEnabled(false);
            this.Intervention1SodiumInputRadio.setEnabled(false);
            this.unhighlightIntervention1();
        }
        if (!this.goalsim) {
            this.recalc();
        }
    }
    
    private void Intervention2CheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.Intervention2CheckBox.isSelected()) {
            this.Intervention2.seton(true);
            this.Intervention2DayField.setEnabled(true);
            this.Intervention2CaloriesField.setEnabled(true);
            this.Intervention2SodiumField.setEnabled(true);
            this.Intervention2CarbInPercField.setEnabled(true);
            this.Intervention2ActChangeBox.setEnabled(true);
            this.Intervention2SodiumCalculatedRadio.setEnabled(true);
            this.Intervention2SodiumInputRadio.setEnabled(true);
            if (this.HighlightCheckBox.isSelected()) {
                this.highlightIntervention2();
            }
        }
        else {
            this.Intervention2.seton(false);
            this.Intervention2DayField.setEnabled(false);
            this.Intervention2CaloriesField.setEnabled(false);
            this.Intervention2SodiumField.setEnabled(false);
            this.Intervention2CarbInPercField.setEnabled(false);
            this.Intervention2ActChangeBox.setEnabled(false);
            this.Intervention2SodiumCalculatedRadio.setEnabled(false);
            this.Intervention2SodiumInputRadio.setEnabled(false);
            this.unhighlightIntervention2();
        }
        if (!this.goalsim) {
            this.recalc();
        }
    }
    
    private void Intervention2CaloriesFieldActionPerformed(final ActionEvent actionEvent) {
        this.Intervention2CaloriesChange();
    }
    
    private void Intervention2CarbInPercFieldActionPerformed(final ActionEvent actionEvent) {
        this.Intervention2CarbInPercChange();
    }
    
    private void Intervention1DayFieldActionPerformed(final ActionEvent actionEvent) {
        this.Intervention1DayChange();
    }
    
    private void Intervention2DayFieldActionPerformed(final ActionEvent actionEvent) {
        this.Intervention2DayChange();
    }
    
    private void GoalWeightFieldActionPerformed(final ActionEvent actionEvent) {
        this.GoalWeightChange();
    }
    
    private void GoalTimeFieldActionPerformed(final ActionEvent actionEvent) {
        this.GoalTimeChange();
    }
    
    private void SimulationLengthFieldActionPerformed(final ActionEvent actionEvent) {
        this.SimulationLengthChange();
    }
    
    private void TimeStartSliderStateChanged(final ChangeEvent changeEvent) {
        this.setgraphparams();
        this.gpanel.setparams(this.timestart, this.timezoom, this.vertical_zoom);
    }
    
    private void TimeZoomSliderStateChanged(final ChangeEvent changeEvent) {
        this.setgraphparams();
        this.gpanel.setparams(this.timestart, this.timezoom, this.vertical_zoom);
    }
    
    private void VerticalZoomSliderStateChanged(final ChangeEvent changeEvent) {
        this.setgraphparams();
        this.gpanel.setparams(this.timestart, this.timezoom, this.vertical_zoom);
    }
    
    private void InitialWeightFieldActionPerformed(final ActionEvent actionEvent) {
        System.out.println("ACTION EVENT NAME IS " + actionEvent.getActionCommand());
        this.InitialWeightFieldChange();
    }
    
    private void Intervention1SodiumInputRadioActionPerformed(final ActionEvent actionEvent) {
        this.Intervention1SodiumRadioChange();
    }
    
    private void RunGoalButtonActionPerformed(final ActionEvent actionEvent) {
        this.goalsim = true;
        this.inter1actdialog.setVisible(false);
        this.inter2actdialog.setVisible(false);
        if (this.GoalActChangeBox.getSelectedItem().toString().contains("Detail")) {
            this.GoalIntervention.setactchangepercent(this.goalactdialog.getactchangeperc());
            System.out.print("GOAL ACTIVITY WINDOW PARSED");
        }
        if (this.GoalMaintenanceActChangeBox.getSelectedItem().toString().contains("Detailed")) {
            this.GoalMaintenanceIntervention.setactchangepercent(this.goalmaintactdialog.getactchangeperc());
        }
        this.recalc();
    }
    
    private void RunInterventionButtonActionPerformed(final ActionEvent actionEvent) {
        this.goalsim = false;
        this.goalactdialog.setVisible(false);
        this.goalmaintactdialog.setVisible(false);
        if (this.Intervention1ActChangeBox.getSelectedItem().toString().contains("Detailed")) {
            this.Intervention1.setactchangepercent(this.inter1actdialog.getactchangeperc());
        }
        if (this.Intervention2ActChangeBox.getSelectedItem().toString().contains("Detailed")) {
            this.Intervention2.setactchangepercent(this.inter2actdialog.getactchangeperc());
        }
        this.recalc();
    }
    
    private void Intervention2SodiumInputRadioActionPerformed(final ActionEvent actionEvent) {
        this.Intervention2SodiumRadioChange();
    }
    
    private void Intervention1SodiumFieldActionPerformed(final ActionEvent actionEvent) {
        this.Intervention1SodiumChange();
    }
    
    private void Intervention2SodiumFieldActionPerformed(final ActionEvent actionEvent) {
        this.Intervention2SodiumChange();
    }
    
    private void GoalMaintenanceActChangeBoxActionPerformed(final ActionEvent actionEvent) {
        try {
            this.OldGoalMaintActChange = this.GoalMaintenanceIntervention.getactchangepercent();
            this.goalmaintactdialog.setoldactchange(this.OldGoalMaintActChange);
            this.change = "goalmaintact";
            this.GoalMaintenanceIntervention.setactchangepercent(Double.parseDouble(this.GoalMaintenanceActChangeBox.getSelectedItem().toString().replaceAll("%", "")));
            this.GoalMaintenanceIntervention.setdetailed(false);
            this.recalc();
        }
        catch (NumberFormatException ex) {
            this.goalmaintactdialog.setoldactchange(this.GoalMaintenanceIntervention.getactchangepercent());
            if (this.GoalMaintenanceIntervention.isdetailed()) {
                this.goalmaintactdialog.setoldboxvalue("Detailed...");
            }
            else {
                this.goalmaintactdialog.setoldboxvalue(String.valueOf((int)this.OldGoalMaintActChange) + "%");
            }
            this.GoalMaintenanceIntervention.setdetailed(true);
            this.goalmaintactdialog.setVisible(true);
        }
    }
    
    private void Intervention2ActChangeBoxActionPerformed(final ActionEvent actionEvent) {
        try {
            this.inter2actdialog.setoldactchange(this.Intervention2.getactchangepercent());
            this.Intervention2.setactchangepercent(Double.parseDouble(this.Intervention2ActChangeBox.getSelectedItem().toString()));
            this.Intervention2.setdetailed(false);
            if (!this.goalsim) {
                this.recalc();
            }
        }
        catch (NumberFormatException ex) {
            if (this.Intervention2.isdetailed()) {
                this.inter2actdialog.setoldboxvalue("Detailed...");
            }
            else {
                this.inter2actdialog.setoldboxvalue(String.valueOf((int)this.Intervention2.getactchangepercent()));
            }
            this.Intervention2.setdetailed(true);
            this.inter2actdialog.setVisible(true);
        }
    }
    
    private void Intervention1CaloriesFieldActionPerformed(final ActionEvent actionEvent) {
        this.Intervention1CaloriesChange();
    }
    
    private void Intervention1CarbInPercFieldActionPerformed(final ActionEvent actionEvent) {
        this.Intervention1CarbInPercChange();
    }
    
    private void InitialWeightFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.InitialWeightFieldChange();
        }
    }
    
    private void InitialBfpFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.InitialBfpChange();
        }
    }
    
    private void InitialSodiumFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.InitialSodiumChange();
        }
    }
    
    private void InitialRMRFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.InitialRMRChange();
        }
    }
    
    private void AgeFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.AgeChange();
        }
    }
    
    private void HeightFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.HeightChange();
        }
    }
    
    private void GoalWeightFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.GoalWeightChange();
        }
    }
    
    private void GoalTimeFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.GoalTimeChange();
        }
    }
    
    private void Intervention1DayFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.Intervention1DayChange();
        }
    }
    
    private void Intervention1CaloriesFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.Intervention1CaloriesChange();
        }
    }
    
    private void Intervention1CarbInPercFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.Intervention1CarbInPercChange();
        }
    }
    
    private void Intervention1SodiumFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.Intervention1SodiumChange();
        }
    }
    
    private void Intervention2DayFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.Intervention2DayChange();
        }
    }
    
    private void Intervention2CaloriesFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.Intervention2CaloriesChange();
        }
    }
    
    private void Intervention2CarbInPercFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.Intervention2CarbInPercChange();
        }
    }
    
    private void Intervention2SodiumFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.Intervention2SodiumChange();
        }
    }
    
    private void SimulationLengthFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.SimulationLengthChange();
        }
    }
    
    private void EstimatePALButtonActionPerformed(final ActionEvent actionEvent) {
        this.pdialog.setVisible(true);
    }
    
    private void GoalActChangeBoxActionPerformed(final ActionEvent actionEvent) {
        try {
            this.OldGoalActChange = this.GoalIntervention.getactchangepercent();
            this.goalactdialog.setoldactchange(this.OldGoalActChange);
            this.change = "goalact";
            this.GoalIntervention.setactchangepercent(Double.parseDouble(this.GoalActChangeBox.getSelectedItem().toString().replaceAll("%", "")));
            this.GoalIntervention.setdetailed(false);
            this.recalc();
        }
        catch (NumberFormatException ex) {
            this.goalactdialog.setoldactchange(this.GoalIntervention.getactchangepercent());
            if (this.GoalIntervention.isdetailed()) {
                this.goalactdialog.setoldboxvalue("Detailed...");
            }
            else {
                this.goalactdialog.setoldboxvalue(String.valueOf((int)this.GoalIntervention.getactchangepercent()) + "%");
            }
            this.GoalIntervention.setdetailed(true);
            this.goalactdialog.setVisible(true);
        }
    }
    
    private void InitialCarbInPercFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.CarbInPercChange();
        }
    }
    
    private void UncertaintyCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.UncertaintyCheckBox.isSelected()) {
            this.gpanel.setspread(true);
        }
        else if (!this.UncertaintyCheckBox.isSelected()) {
            this.gpanel.setspread(false);
        }
    }
    
    private void GridCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.GridCheckBox.isSelected()) {
            this.gpanel.setgrid(true);
        }
        else if (!this.GridCheckBox.isSelected()) {
            this.gpanel.setgrid(false);
        }
    }
    
    private void ShowLegendCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.ShowLegendCheckBox.isSelected()) {
            this.gpanel.setlegend(true);
        }
        else if (!this.ShowLegendCheckBox.isSelected()) {
            this.gpanel.setlegend(false);
        }
    }
    
    private void DefaultButtonActionPerformed(final ActionEvent actionEvent) {
        this.GoalCheckBox.setSelected(true);
        this.GoalCheckBoxActionPerformed(null);
        this.initValues();
        this.recalc();
        this.TimeStartSlider.setValue(0);
        this.TimeZoomSlider.setValue(0);
        this.VerticalZoomSlider.setValue(0);
        this.Intervention1CheckBox.isSelected();
        this.Intervention2CheckBox.isSelected();
        this.Intervention1ActChangeBox.setSelectedItem("0");
        this.Intervention2ActChangeBox.setSelectedItem("0");
        this.Intervention1SodiumCalculatedRadio.setSelected(true);
        this.Intervention2SodiumCalculatedRadio.setSelected(true);
        this.GoalActChangeBox.setSelectedItem("0");
        this.GoalMaintenanceActChangeBox.setSelectedItem("0");
    }
    
    private void GraphAttachedCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (!this.GraphAttachedCheckBox.isSelected()) {
            this.graphattached = false;
            this.remove(this.jPanel2);
            this.remove(this.gpanel);
            this.gframe.getContentPane().add(this.gpanel);
            this.gframe.pack();
            this.gframe.setVisible(true);
            this.repaint();
            ((JFrame)SwingUtilities.getRoot(this)).pack();
        }
        else if (this.GraphAttachedCheckBox.isSelected()) {
            this.graphattached = true;
            this.gframe.setVisible(false);
            this.initGraph();
            this.repaint();
        }
    }
    
    private void AdvancedControlsCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (!this.AdvancedControlsCheckBox.isSelected()) {
            this.simpleversion = true;
            this.simplify();
        }
        else if (this.AdvancedControlsCheckBox.isSelected()) {
            this.simpleversion = false;
            this.advanced();
        }
    }
    
    private void HighlightCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (!this.HighlightCheckBox.isSelected()) {
            this.SimulationLengthField.setBackground(Color.white);
            this.InitialWeightField.setBackground(Color.white);
            this.AgeField.setBackground(Color.white);
            this.GenderBox.setBorder(null);
            this.HeightField.setBackground(Color.white);
            this.InitialPALField.setBackground(Color.white);
            this.GoalWeightField.setBackground(Color.white);
            this.GoalTimeField.setBackground(Color.white);
            this.GoalActChangeBox.setBorder(null);
            this.GoalMaintenanceActChangeBox.setBorder(null);
            this.MaintCalsField.setBackground(Color.white);
            this.InitialSodiumField.setBackground(Color.white);
            this.InitialCarbInPercField.setBackground(Color.white);
            this.InitialBfpField.setBackground(Color.white);
            this.InitialRMRField.setBackground(Color.white);
            this.FinalWeightField.setBackground(Color.white);
            this.FinalBfpField.setBackground(Color.white);
            this.FinalBMIField.setBackground(Color.white);
            this.GoalCalsField.setBackground(Color.white);
            this.GoalMaintCalsField.setBackground(Color.white);
            this.unhighlightIntervention1();
            this.unhighlightIntervention2();
        }
        else if (this.HighlightCheckBox.isSelected()) {
            this.SimulationLengthField.setBackground(this.highlightgreen);
            this.InitialWeightField.setBackground(this.highlightgreen);
            this.AgeField.setBackground(this.highlightgreen);
            this.HeightField.setBackground(this.highlightgreen);
            this.InitialPALField.setBackground(this.highlightgreen);
            this.GenderBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
            this.GoalWeightField.setBackground(this.highlightgreen);
            this.GoalTimeField.setBackground(this.highlightgreen);
            this.GoalActChangeBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
            this.GoalMaintenanceActChangeBox.setBorder(BorderFactory.createLineBorder(this.highlightgreen, 3));
            this.MaintCalsField.setBackground(this.highlightblue);
            this.InitialSodiumField.setBackground(this.highlightblue);
            this.InitialCarbInPercField.setBackground(this.highlightblue);
            if (this.RMRInputRadio.isSelected()) {
                this.InitialRMRField.setBackground(this.highlightblue);
            }
            if (this.InitialBfpInputRadio.isSelected()) {
                this.InitialRMRField.setBackground(this.highlightblue);
            }
            this.MaintCalsField.setBackground(this.highlightyellow);
            this.FinalWeightField.setBackground(this.highlightyellow);
            this.FinalBfpField.setBackground(this.highlightyellow);
            this.FinalBMIField.setBackground(this.highlightyellow);
            this.GoalCalsField.setBackground(this.highlightyellow);
            this.GoalMaintCalsField.setBackground(this.highlightyellow);
            if (this.Intervention1.ison()) {
                this.highlightIntervention1();
            }
            if (this.Intervention2.ison()) {
                this.highlightIntervention2();
            }
        }
    }
    
    private void GenderBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.GenderBox.getSelectedItem().toString().equals("Male")) {
            this.OldMale = this.Baseline.getMale();
            this.Baseline.setMale(true);
            this.GenderLabel.setText("Male");
        }
        else {
            this.OldMale = this.Baseline.getMale();
            this.Baseline.setMale(false);
            this.GenderLabel.setText("Female");
        }
        if (this.OldMale != this.Baseline.getMale()) {
            this.change = "gender";
            this.recalc();
        }
    }
    
    private void CaloriesRadioActionPerformed(final ActionEvent actionEvent) {
        this.EnergyUnitsChange();
    }
    
    private void KilojoulesRadioActionPerformed(final ActionEvent actionEvent) {
        this.EnergyUnitsChange();
    }
    
    private void Intervention1RampCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.Intervention1RampCheckBox.isSelected()) {
            this.Intervention1.setrampon(true);
            this.Intervention1DayLabel.setText("Complete Change on Day");
        }
        else if (!this.Intervention1RampCheckBox.isSelected()) {
            this.Intervention1.setrampon(false);
            this.Intervention1DayLabel.setText("Start Change on Day");
        }
        if (!this.goalsim) {
            this.recalc();
        }
    }
    
    private void Intervention2RampCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.Intervention2RampCheckBox.isSelected()) {
            this.Intervention2.setrampon(true);
            this.Intervention2DayLabel.setText("Complete Change on Day");
        }
        else if (!this.Intervention2RampCheckBox.isSelected()) {
            this.Intervention2.setrampon(false);
            this.Intervention2DayLabel.setText("Start Change on Day");
        }
        if (!this.goalsim) {
            this.recalc();
        }
    }
    
    private void OverviewButtonActionPerformed(final ActionEvent actionEvent) {
        this.walkthrough();
    }
    
    private void formKeyPressed(final KeyEvent keyEvent) {
    }
    
    private void ChoicePanelPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (propertyChangeEvent.getPropertyName().contains("Selected")) {
            System.out.println("SELECTION CHANGE");
            if (this.ChoicePanel.getSelectedIndex() == 0) {
                this.goalsim = true;
            }
            else if (this.ChoicePanel.getSelectedIndex() == 1) {
                this.goalsim = false;
            }
            this.recalc();
        }
    }
    
    private void UncertaintyBoxActionPerformed(final ActionEvent actionEvent) {
        try {
            this.spread_percent = Double.parseDouble(this.UncertaintyBox.getSelectedItem().toString().replaceAll("%", ""));
            this.recalc();
        }
        catch (NumberFormatException ex) {
            System.out.print("Goal act parse problem");
        }
    }
    
    private void GoalCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (!this.GoalCheckBox.isSelected()) {
            this.GoalIntervention.seton(false);
            this.GoalMaintenanceIntervention.seton(false);
            this.GoalWeightField.setEnabled(false);
            this.GoalTimeField.setEnabled(false);
            this.GoalCalsField.setEnabled(false);
            this.GoalCalsChangeField.setEnabled(false);
            this.GoalMaintCalsField.setEnabled(false);
            this.GoalMaintCalsChangeField.setEnabled(false);
            this.GoalActChangeBox.setEnabled(false);
            this.GoalMaintenanceActChangeBox.setEnabled(false);
            this.unhighlightgoal();
            if (this.goalsim) {
                this.recalc();
            }
        }
        else if (this.GoalCheckBox.isSelected()) {
            this.GoalIntervention.seton(true);
            this.GoalMaintenanceIntervention.seton(true);
            this.GoalWeightField.setEnabled(true);
            this.GoalTimeField.setEnabled(true);
            this.GoalCalsField.setEnabled(true);
            this.GoalCalsChangeField.setEnabled(true);
            this.GoalMaintCalsField.setEnabled(true);
            this.GoalMaintCalsChangeField.setEnabled(true);
            this.GoalActChangeBox.setEnabled(true);
            this.GoalMaintenanceActChangeBox.setEnabled(true);
            if (this.HighlightCheckBox.isSelected()) {
                this.highlightgoal();
            }
            if (this.goalsim) {
                this.recalc();
            }
        }
    }
    
    private void DefaultColorsCheckBoxActionPerformed(final ActionEvent actionEvent) {
        if (this.DefaultColorsCheckBox.isSelected()) {
            this.gpanel.setdefaultcolors(true);
        }
        else {
            this.gpanel.setdefaultcolors(false);
        }
    }
    
    private void Intervention1ActChangeBoxActionPerformed(final ActionEvent actionEvent) {
        try {
            this.inter1actdialog.setoldactchange(this.Intervention1.getactchangepercent());
            this.Intervention1.setactchangepercent(Double.parseDouble(this.Intervention1ActChangeBox.getSelectedItem().toString()));
            this.Intervention1.setdetailed(false);
            if (!this.goalsim) {
                this.recalc();
            }
        }
        catch (NumberFormatException ex) {
            if (this.Intervention1.isdetailed()) {
                this.inter1actdialog.setoldboxvalue("Detailed...");
            }
            else {
                this.inter1actdialog.setoldboxvalue(String.valueOf((int)this.Intervention1.getactchangepercent()));
            }
            this.Intervention1.setdetailed(true);
            this.inter1actdialog.setVisible(true);
        }
    }
    
    private void InitialPALFieldActionPerformed(final ActionEvent actionEvent) {
        this.initPALChange();
    }
    
    private void InitialPALFieldPropertyChange(final PropertyChangeEvent propertyChangeEvent) {
        if (!propertyChangeEvent.getPropertyName().equals("Frame.active")) {
            this.initPALChange();
        }
    }
    
    private void SaveButtonActionPerformed(final ActionEvent actionEvent) {
        try {
            final JFileChooser fileChooser = new JFileChooser();
            final int showSaveDialog = fileChooser.showSaveDialog(this);
            final PrintStream out = System.out;
            if (showSaveDialog == 0) {
                System.setOut(new PrintStream(fileChooser.getSelectedFile()));
                this.Baseline.print();
                System.out.println();
                if (this.goalsim) {
                    if (!this.GoalIntervention.ison()) {
                        System.out.println("No intervention");
                    }
                    else {
                        this.GoalIntervention.print();
                        System.out.println();
                        this.GoalMaintenanceIntervention.print();
                        System.out.println();
                    }
                }
                else if (!this.Intervention1.ison() && !this.Intervention2.ison()) {
                    System.out.println("No intervention");
                    System.out.println();
                }
                else {
                    if (this.Intervention1.ison()) {
                        this.Intervention1.print();
                    }
                    System.out.println();
                    if (this.Intervention1.ison()) {
                        this.Intervention2.print();
                    }
                    System.out.println();
                }
                final String[] getcolumnnames = this.gpanel.getcolumnnames();
                for (int i = 0; i < getcolumnnames.length; ++i) {
                    System.out.print(getcolumnnames[i] + "\t");
                }
                System.out.println();
                final Object[][] gettabledata = this.gpanel.gettabledata();
                for (int n = 0; n < this.SimulationLength; ++n) {
                    for (int j = 0; j < gettabledata[n].length; ++j) {
                        System.out.print(gettabledata[n][j]);
                        System.out.print("\t");
                    }
                    System.out.print("\n");
                }
                System.out.close();
                System.setOut(out);
            }
        }
        catch (Exception ex) {}
    }
    
    private void EnergyUnitsChange() {
        if (this.CaloriesRadio.isSelected()) {
            this.energyunits = 1.0;
            this.energystring = "Calories";
        }
        else if (this.KilojoulesRadio.isSelected()) {
            this.energyunits = 4.184;
            this.energystring = "kJ";
        }
        this.calcflag = true;
        this.MaintCalsField.setValue(this.Baseline.getMaintCals() * this.energyunits);
        this.InitialRMRLabel.setText("Initial RMR (" + this.energystring + "/day)");
        this.InitialRMRField.setValue(this.Baseline.getRMR() * this.energyunits);
        this.Intervention1CaloriesLabel.setText("New Diet (" + this.energystring + "/day)");
        this.Intervention2CaloriesLabel.setText("New Diet (" + this.energystring + "/day)");
        this.Intervention1CaloriesField.setValue(this.Intervention1.getcalories() * this.energyunits);
        this.Intervention2CaloriesField.setValue(this.Intervention2.getcalories() * this.energyunits);
        this.GoalCalsField.setValue(this.GoalIntervention.getcalories() * this.energyunits);
        this.GoalMaintCalsField.setValue(this.GoalMaintenanceIntervention.getcalories() * this.energyunits);
        this.GoalCalsChangeField.setValue((this.GoalIntervention.getcalories() - this.Baseline.getMaintCals()) * this.energyunits);
        this.GoalCalsChangeLabel.setText(this.energystring + "/day, which is a change of");
        this.GoalMaintCalsChangeField.setValue((this.GoalMaintenanceIntervention.getcalories() - this.Baseline.getMaintCals()) * this.energyunits);
        this.GoalMaintCalsChangeLabel.setText(this.energystring + "/day, which is a change of");
        this.calcflag = false;
        this.gpanel.setenergyunits(this.energyunits);
    }
    
    private void simplify() {
        this.InitialBfpField.setVisible(false);
        this.InitialBfpLabel.setVisible(false);
        this.InitialBfpCalculatedRadio.setVisible(false);
        this.InitialBfpInputRadio.setVisible(false);
        this.BfpSeparator.setVisible(false);
        this.InitialRMRField.setVisible(false);
        this.InitialRMRLabel.setVisible(false);
        this.RMRCalculatedRadio.setVisible(false);
        this.RMRInputRadio.setVisible(false);
        this.RMRSeparator.setVisible(false);
        this.UncertaintyLabel.setVisible(false);
        this.UncertaintyBox.setVisible(false);
        this.InitialSodiumField.setVisible(false);
        this.InitialSodiumLabel.setVisible(false);
        this.InitialCarbInPercField.setVisible(false);
        this.InitialCarbInPercLabel.setVisible(false);
        this.Intervention1SodiumField.setVisible(false);
        this.Intervention1SodiumLabel.setVisible(false);
        this.Intervention1SodiumCalculatedRadio.setVisible(false);
        this.Intervention1SodiumInputRadio.setVisible(false);
        this.Intervention1SodiumSeparator.setVisible(false);
        this.Intervention1CarbInPercField.setVisible(false);
        this.Intervention1CarbInPercLabel.setVisible(false);
        this.Intervention1RampCheckBox.setVisible(false);
        this.Intervention2SodiumField.setVisible(false);
        this.Intervention2SodiumLabel.setVisible(false);
        this.Intervention2SodiumCalculatedRadio.setVisible(false);
        this.Intervention2SodiumInputRadio.setVisible(false);
        this.Intervention2SodiumSeparator.setVisible(false);
        this.Intervention2CarbInPercField.setVisible(false);
        this.Intervention2CarbInPercLabel.setVisible(false);
        this.Intervention2RampCheckBox.setVisible(false);
        this.RMRCalculatedRadio.setSelected(true);
        this.InitialRMRField.setBackground(Color.white);
        this.InitialBfpCalculatedRadio.setSelected(true);
        this.InitialBfpField.setBackground(Color.white);
        this.Intervention1SodiumField.setBackground(Color.white);
        this.Intervention2SodiumField.setBackground(Color.white);
        this.Intervention1SodiumCalculatedRadio.setSelected(true);
        this.Intervention2SodiumCalculatedRadio.setSelected(true);
        this.Intervention1RampCheckBox.setSelected(false);
        this.Intervention2RampCheckBox.setSelected(false);
        this.Intervention1DayLabel.setText("Start Change on Day");
        this.Intervention2DayLabel.setText("Start Change on Day");
        this.Baseline.setcalculatedRMR(true);
        this.Baseline.setcalculatedBfp(true);
        this.Intervention1.setrampon(false);
        this.Intervention2.setrampon(false);
        this.recalc();
        if (this.Mainframe != null && this.Mainframe.getExtendedState() != 6) {
            this.Mainframe.pack();
        }
        this.repaint();
    }
    
    private void advanced() {
        this.InitialWeightField.setBounds(this.InitialWeightField.getX(), this.InitialWeightField.getY(), this.InitialWeightField.getWidth() * 10, this.InitialWeightField.getHeight());
        this.InitialBfpField.setVisible(true);
        this.InitialBfpLabel.setVisible(true);
        this.InitialBfpCalculatedRadio.setVisible(true);
        this.InitialBfpInputRadio.setVisible(true);
        this.BfpSeparator.setVisible(true);
        this.InitialRMRField.setVisible(true);
        this.InitialRMRLabel.setVisible(true);
        this.RMRCalculatedRadio.setVisible(true);
        this.RMRInputRadio.setVisible(true);
        this.RMRSeparator.setVisible(true);
        this.UncertaintyLabel.setVisible(true);
        this.UncertaintyBox.setVisible(true);
        this.InitialSodiumField.setVisible(true);
        this.InitialSodiumLabel.setVisible(true);
        this.InitialCarbInPercField.setVisible(true);
        this.InitialCarbInPercLabel.setVisible(true);
        this.Intervention1SodiumField.setVisible(true);
        this.Intervention1SodiumLabel.setVisible(true);
        this.Intervention1SodiumCalculatedRadio.setVisible(true);
        this.Intervention1SodiumInputRadio.setVisible(true);
        this.Intervention1SodiumSeparator.setVisible(true);
        this.Intervention1CarbInPercField.setVisible(true);
        this.Intervention1CarbInPercLabel.setVisible(true);
        this.Intervention1RampCheckBox.setVisible(true);
        this.Intervention2SodiumField.setVisible(true);
        this.Intervention2SodiumLabel.setVisible(true);
        this.Intervention2SodiumCalculatedRadio.setVisible(true);
        this.Intervention2SodiumInputRadio.setVisible(true);
        this.Intervention2SodiumSeparator.setVisible(true);
        this.Intervention2CarbInPercField.setVisible(true);
        this.Intervention2CarbInPercLabel.setVisible(true);
        this.Intervention2RampCheckBox.setVisible(true);
        if (this.Mainframe != null && this.Mainframe.getExtendedState() != 6) {
            this.Mainframe.pack();
        }
        this.setBounds(this.getX(), this.getY(), this.getWidth() + 150, this.getHeight() + 150);
        this.repaint();
    }
    
    static {
        MainPanel.eps = 0.001;
    }
}
