import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import javax.swing.border.Border;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.util.Random;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ExperimentPanel extends JPanel
{
    public static final int BUTTON_WIDTH = 10;
    public static final int INPUT_TEXT_SIZE = 4;
    public static final int INPUT_LABEL_SIZE = 8;
    protected CirclePanel circlePanel;
    protected Random r;
    protected boolean simpleVersion;
    protected JPanel dataPanel;
    protected JPanel colorPanel;
    protected JPanel labelsPanel;
    protected JButton changeDiagramButton;
    protected JButton drawDiagramButton;
    protected JButton redrawButton;
    protected JButton hcButton;
    protected JButton randomButton;
    protected JButton dataButton;
    protected JRadioButton colorRadioButton;
    protected JRadioButton monochromeRadioButton;
    protected JRadioButton defaultLabelsRadioButton;
    protected JRadioButton defineLabelsRadioButton;
    protected JRadioButton hideLabelsRadioButton;
    protected JRadioButton advancedLabelsRadioButton;
    protected JTextField[] circleXField;
    protected JTextField[] circleYField;
    protected JTextField[] circleRadiusField;
    protected JTextField[] popsField;
    protected JTextField[] labelsField;
    protected JLabel[] varianceLabel;
    protected JLabel messageLabel;
    
    public ExperimentPanel(final CirclePanel cp, final boolean simpleVersion, final int startingLabelsStatus) {
        this.r = new Random();
        this.circleXField = new JTextField[3];
        this.circleYField = new JTextField[3];
        this.circleRadiusField = new JTextField[3];
        this.popsField = new JTextField[8];
        this.labelsField = new JTextField[8];
        this.varianceLabel = new JLabel[8];
        this.circlePanel = cp;
        this.simpleVersion = simpleVersion;
        this.circlePanel.labelsStatus = startingLabelsStatus;
        this.dataPanel = new JPanel();
        final GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        this.addWidgets(this, gridbag);
        this.setVisible(true);
    }
    
    protected void addWidgets(final JPanel panel, final GridBagLayout gridbag) {
        final Border etchedBorder = BorderFactory.createEtchedBorder();
        final Border spaceBorder1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        final Border spaceBorder2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        final Border compoundBorder = BorderFactory.createCompoundBorder(etchedBorder, spaceBorder1);
        final Border panelBorder = BorderFactory.createCompoundBorder(spaceBorder2, compoundBorder);
        final CircleLayout[] circles = this.circlePanel.circles;
        final GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 5;
        c.ipady = 5;
        int yLevel = 0;
        c.gridy = yLevel;
        JLabel label = new JLabel("circle", 2);
        if (this.simpleVersion) {
            label.setVisible(false);
        }
        c.gridx = 0;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        label = new JLabel("x", 2);
        if (this.simpleVersion) {
            label.setVisible(false);
        }
        c.gridx = 1;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        label = new JLabel("y", 2);
        if (this.simpleVersion) {
            label.setVisible(false);
        }
        c.gridx = 2;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        label = new JLabel("radius", 2);
        if (this.simpleVersion) {
            label.setVisible(false);
        }
        c.gridx = 3;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        for (int circle = 0; circle < circles.length; ++circle) {
            ++yLevel;
            label = new JLabel(String.valueOf(Integer.toString(circle)) + " " + CircleAreaRunner.circleIndex[circle], 2);
            if (this.simpleVersion) {
                label.setVisible(false);
            }
            c.gridx = 0;
            c.gridy = yLevel;
            gridbag.setConstraints(label, c);
            panel.add(label);
            (this.circleXField[circle] = new JTextField(4)).setHorizontalAlignment(2);
            this.circleXField[circle].setText(Double.toString(circles[circle].center.x));
            this.circleXField[circle].setCaretPosition(0);
            if (this.simpleVersion) {
                this.circleXField[circle].setVisible(false);
            }
            c.gridx = 1;
            c.gridy = yLevel;
            gridbag.setConstraints(this.circleXField[circle], c);
            panel.add(this.circleXField[circle]);
            (this.circleYField[circle] = new JTextField(4)).setHorizontalAlignment(2);
            this.circleYField[circle].setText(Double.toString(circles[circle].center.y));
            this.circleYField[circle].setCaretPosition(0);
            if (this.simpleVersion) {
                this.circleYField[circle].setVisible(false);
            }
            c.gridx = 2;
            c.gridy = yLevel;
            gridbag.setConstraints(this.circleYField[circle], c);
            panel.add(this.circleYField[circle]);
            (this.circleRadiusField[circle] = new JTextField(4)).setHorizontalAlignment(2);
            this.circleRadiusField[circle].setText(Double.toString(circles[circle].radius));
            this.circleRadiusField[circle].setCaretPosition(0);
            if (this.simpleVersion) {
                this.circleRadiusField[circle].setVisible(false);
            }
            c.gridx = 3;
            c.gridy = yLevel;
            gridbag.setConstraints(this.circleRadiusField[circle], c);
            panel.add(this.circleRadiusField[circle]);
        }
        ++yLevel;
        label = new JLabel("zone", 2);
        c.gridx = 0;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        label = new JLabel("population", 2);
        c.gridx = 1;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        label = new JLabel("label", 2);
        c.gridx = 2;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        label = new JLabel("variance", 2);
        if (this.simpleVersion) {
            label.setVisible(false);
        }
        c.gridx = 3;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        for (int zone = 1; zone < 8; ++zone) {
            ++yLevel;
            if (zone == 3) {
                ++yLevel;
            }
            if (zone == 4) {
                yLevel -= 2;
            }
            if (zone == 5) {
                ++yLevel;
            }
            label = new JLabel(String.valueOf(Integer.toString(zone)) + " " + CircleAreaRunner.zoneIndex[zone], 2);
            if (this.simpleVersion) {
                label.setText(CircleAreaRunner.zoneIndex[zone]);
            }
            c.gridx = 0;
            c.gridy = yLevel;
            gridbag.setConstraints(label, c);
            panel.add(label);
            (this.popsField[zone] = new JTextField(4)).setHorizontalAlignment(2);
            this.popsField[zone].setText(Double.toString(this.circlePanel.pops[zone]));
            this.popsField[zone].setCaretPosition(0);
            c.gridx = 1;
            c.gridy = yLevel;
            gridbag.setConstraints(this.popsField[zone], c);
            panel.add(this.popsField[zone]);
            (this.labelsField[zone] = new JTextField(8)).setHorizontalAlignment(2);
            this.labelsField[zone].setText(this.circlePanel.zoneLabels[zone]);
            this.labelsField[zone].setCaretPosition(0);
            if (this.circlePanel.labelsStatus != 2) {
                this.labelsField[zone].setEnabled(false);
            }
            c.gridx = 2;
            c.gridy = yLevel;
            gridbag.setConstraints(this.labelsField[zone], c);
            panel.add(this.labelsField[zone]);
            (this.varianceLabel[zone] = new JLabel("", 2)).setText(Double.toString(CirclePanel.round(CircleAreaRunner.zoneVariance[zone] * 1000.0, 2)));
            if (this.simpleVersion) {
                this.varianceLabel[zone].setVisible(false);
            }
            c.gridx = 3;
            c.gridy = yLevel;
            gridbag.setConstraints(this.varianceLabel[zone], c);
            panel.add(this.varianceLabel[zone]);
        }
        ++yLevel;
        (this.labelsPanel = new JPanel()).setBorder(panelBorder);
        c.gridx = 0;
        c.gridy = yLevel;
        c.fill = 2;
        c.gridwidth = 6;
        c.anchor = 10;
        gridbag.setConstraints(this.labelsPanel, c);
        panel.add(this.labelsPanel);
        final GridBagLayout labelsPanelGridbag = new GridBagLayout();
        this.labelsPanel.setLayout(labelsPanelGridbag);
        final GridBagConstraints labelsPanelC = new GridBagConstraints();
        (this.defaultLabelsRadioButton = new JRadioButton("Default Labels")).setSelected(true);
        this.defaultLabelsRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.defaultLabelsRadioButton();
            }
        });
        labelsPanelC.gridx = 0;
        labelsPanelC.gridy = 0;
        labelsPanelC.anchor = 17;
        labelsPanelGridbag.setConstraints(this.defaultLabelsRadioButton, labelsPanelC);
        this.labelsPanel.add(this.defaultLabelsRadioButton);
        (this.defineLabelsRadioButton = new JRadioButton("Define Labels")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.defineLabelsRadioButton();
            }
        });
        labelsPanelC.gridx = 0;
        labelsPanelC.gridy = 1;
        labelsPanelC.anchor = 17;
        labelsPanelGridbag.setConstraints(this.defineLabelsRadioButton, labelsPanelC);
        this.labelsPanel.add(this.defineLabelsRadioButton);
        (this.hideLabelsRadioButton = new JRadioButton("Hide Labels")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.hideLabelsRadioButton();
            }
        });
        labelsPanelC.gridx = 0;
        labelsPanelC.gridy = 2;
        labelsPanelC.anchor = 17;
        labelsPanelGridbag.setConstraints(this.hideLabelsRadioButton, labelsPanelC);
        this.labelsPanel.add(this.hideLabelsRadioButton);
        this.advancedLabelsRadioButton = new JRadioButton("Advanced Labels");
        if (this.simpleVersion) {
            this.advancedLabelsRadioButton.setVisible(false);
        }
        this.advancedLabelsRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.advancedLabelsRadioButton();
            }
        });
        labelsPanelC.gridx = 0;
        labelsPanelC.gridy = 3;
        labelsPanelC.anchor = 17;
        labelsPanelGridbag.setConstraints(this.advancedLabelsRadioButton, labelsPanelC);
        this.labelsPanel.add(this.advancedLabelsRadioButton);
        final ButtonGroup labelsGroup = new ButtonGroup();
        labelsGroup.add(this.defaultLabelsRadioButton);
        labelsGroup.add(this.defineLabelsRadioButton);
        labelsGroup.add(this.hideLabelsRadioButton);
        labelsGroup.add(this.advancedLabelsRadioButton);
        if (this.circlePanel.labelsStatus == 1) {
            this.defaultLabelsRadioButton.setSelected(true);
        }
        else if (this.circlePanel.labelsStatus == 2) {
            this.defineLabelsRadioButton.setSelected(true);
        }
        else if (this.circlePanel.labelsStatus == 3) {
            this.hideLabelsRadioButton.setSelected(true);
        }
        else if (this.circlePanel.labelsStatus == 4) {
            this.advancedLabelsRadioButton.setSelected(true);
        }
        ++yLevel;
        (this.colorPanel = new JPanel()).setBorder(panelBorder);
        c.gridx = 0;
        c.gridy = yLevel;
        c.fill = 2;
        c.gridwidth = 6;
        c.anchor = 10;
        gridbag.setConstraints(this.colorPanel, c);
        panel.add(this.colorPanel);
        (this.colorRadioButton = new JRadioButton("Colour")).setSelected(true);
        this.colorRadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.colorRadioButton();
            }
        });
        this.colorPanel.add(this.colorRadioButton);
        ++yLevel;
        (this.monochromeRadioButton = new JRadioButton("Monochrome")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.monochromeRadioButton();
            }
        });
        this.colorPanel.add(this.monochromeRadioButton);
        final ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(this.colorRadioButton);
        colorGroup.add(this.monochromeRadioButton);
        ++yLevel;
        (this.drawDiagramButton = new JButton("Draw Diagram")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.drawDiagramButton();
            }
        });
        c.gridx = 0;
        c.gridy = yLevel;
        c.gridwidth = 6;
        c.fill = 2;
        c.anchor = 10;
        gridbag.setConstraints(this.drawDiagramButton, c);
        panel.add(this.drawDiagramButton);
        ++yLevel;
        this.changeDiagramButton = new JButton("Change Circles Geometry");
        if (this.simpleVersion) {
            this.changeDiagramButton.setVisible(false);
        }
        this.changeDiagramButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.changeDiagramButton();
            }
        });
        c.gridx = 0;
        c.gridy = yLevel;
        c.gridwidth = 6;
        c.fill = 2;
        c.anchor = 10;
        gridbag.setConstraints(this.changeDiagramButton, c);
        panel.add(this.changeDiagramButton);
        ++yLevel;
        this.redrawButton = new JButton("Initial Layout");
        if (this.simpleVersion) {
            this.redrawButton.setVisible(false);
        }
        this.redrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.redrawButton();
            }
        });
        c.gridx = 0;
        c.gridy = yLevel;
        c.gridwidth = 6;
        c.fill = 2;
        c.anchor = 10;
        gridbag.setConstraints(this.redrawButton, c);
        panel.add(this.redrawButton);
        ++yLevel;
        this.randomButton = new JButton("Random Diagram");
        if (this.simpleVersion) {
            this.randomButton.setVisible(false);
        }
        this.randomButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.randomButton();
            }
        });
        c.gridx = 0;
        c.gridy = yLevel;
        c.gridwidth = 6;
        c.fill = 2;
        c.anchor = 10;
        gridbag.setConstraints(this.randomButton, c);
        panel.add(this.randomButton);
        ++yLevel;
        this.dataButton = new JButton("Output Data");
        if (this.simpleVersion) {
            this.dataButton.setVisible(false);
        }
        this.dataButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.dataButton();
            }
        });
        c.gridx = 0;
        c.gridy = yLevel;
        c.gridwidth = 6;
        c.fill = 2;
        c.anchor = 10;
        gridbag.setConstraints(this.dataButton, c);
        panel.add(this.dataButton);
        ++yLevel;
        this.hcButton = new JButton("Run Hill Climber");
        if (this.simpleVersion) {
            this.hcButton.setVisible(false);
        }
        this.hcButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.hcButton();
            }
        });
        c.gridx = 0;
        c.gridy = yLevel;
        c.fill = 2;
        c.gridwidth = 6;
        c.anchor = 10;
        gridbag.setConstraints(this.hcButton, c);
        panel.add(this.hcButton);
        ++yLevel;
        this.messageLabel = new JLabel(" ");
        c.gridx = 0;
        c.gridy = yLevel;
        c.fill = 2;
        c.gridwidth = 6;
        c.anchor = 10;
        gridbag.setConstraints(this.messageLabel, c);
        panel.add(this.messageLabel);
    }
    
    protected void drawDiagramButton() {
        this.messageLabel.setText(" ");
        if (!this.updateCirclesFromFields()) {
            return;
        }
        this.circlePanel.circles = LayoutAlgorithms.computeThreeCircleLayout(this.circlePanel.pops);
        final Rectangle bbox = new Rectangle(80, 80, 400, 400);
        CircleGeometry.transformCircles(this.circlePanel.circles, bbox);
        this.updateFieldsFromCircles();
        this.circlePanel.runHillClimber(false);
        this.updateFieldsFromCircles();
        this.circlePanel.updatePanel(0);
    }
    
    protected void changeDiagramButton() {
        this.messageLabel.setText(" ");
        if (!this.updateCirclesFromFields()) {
            return;
        }
        this.circlePanel.updatePanel(0);
    }
    
    protected void redrawButton() {
        this.messageLabel.setText(" ");
        if (!this.updateCirclesFromFields()) {
            return;
        }
        this.circlePanel.circles = LayoutAlgorithms.computeThreeCircleLayout(this.circlePanel.pops);
        final Rectangle bbox = new Rectangle(80, 80, 400, 400);
        CircleGeometry.transformCircles(this.circlePanel.circles, bbox);
        this.circlePanel.updatePanel(0);
        this.updateFieldsFromCircles();
    }
    
    public static double safeParseDouble(final String doubleString) {
        double ret = 0.0;
        try {
            ret = Double.parseDouble(doubleString);
        }
        catch (Exception e) {
            System.out.println("Could not parse double from String: " + doubleString);
            return -1.0;
        }
        return ret;
    }
    
    protected boolean updateCirclesFromFields() {
        final double[] xValue = new double[3];
        final double[] yValue = new double[3];
        final double[] radiusValue = new double[3];
        for (int circle = 0; circle < this.circlePanel.circles.length; ++circle) {
            xValue[circle] = safeParseDouble(this.circleXField[circle].getText());
            if (xValue[circle] < 0.0) {
                this.messageLabel.setText("Invalid x for circle " + CircleAreaRunner.circleIndex[circle]);
                return false;
            }
            yValue[circle] = safeParseDouble(this.circleYField[circle].getText());
            if (yValue[circle] < 0.0) {
                this.messageLabel.setText("Invalid y for circle " + CircleAreaRunner.circleIndex[circle]);
                return false;
            }
            radiusValue[circle] = safeParseDouble(this.circleRadiusField[circle].getText());
            if (radiusValue[circle] < 0.0) {
                this.messageLabel.setText("Invalid radius for circle " + CircleAreaRunner.circleIndex[circle]);
                return false;
            }
        }
        final double[] popsValue = new double[8];
        for (int zone = 1; zone < 8; ++zone) {
            popsValue[zone] = safeParseDouble(this.popsField[zone].getText());
            if (popsValue[zone] < 0.0) {
                this.messageLabel.setText("Invalid population for zone " + CircleAreaRunner.zoneIndex[zone]);
                return false;
            }
        }
        for (int circle2 = 0; circle2 < this.circlePanel.circles.length; ++circle2) {
            this.circlePanel.circles[circle2].center.x = xValue[circle2];
            this.circlePanel.circles[circle2].center.y = yValue[circle2];
            this.circlePanel.circles[circle2].radius = radiusValue[circle2];
        }
        for (int zone = 1; zone < 8; ++zone) {
            this.circlePanel.pops[zone] = popsValue[zone];
            this.circlePanel.zoneLabels[zone] = this.labelsField[zone].getText();
        }
        return true;
    }
    
    protected void hcButton() {
        this.messageLabel.setText(" ");
        if (!this.updateCirclesFromFields()) {
            return;
        }
        this.circlePanel.updatePanel(0);
        this.updateFieldsFromCircles();
        this.circlePanel.runHillClimber(true);
        this.updateFieldsFromCircles();
    }
    
    protected void randomButton() {
        this.messageLabel.setText(" ");
        for (int zone = 1; zone < 8; ++zone) {
            this.circlePanel.pops[zone] = (int)(this.r.nextDouble() * 200.0 + 10.0);
        }
        this.circlePanel.circles = LayoutAlgorithms.computeThreeCircleLayout(this.circlePanel.pops);
        final Rectangle bbox = new Rectangle(80, 80, 400, 400);
        CircleGeometry.transformCircles(this.circlePanel.circles, bbox);
        CircleAreaRunner.computeFitness(this.circlePanel.pops, this.circlePanel.circles);
        this.circlePanel.updatePanel(0);
        this.updateFieldsFromCircles();
    }
    
    protected void resetLabels() {
        this.circlePanel.resetLabels();
        for (int zone = 1; zone < 8; ++zone) {
            this.labelsField[zone].setText(this.circlePanel.zoneLabels[zone]);
        }
    }
    
    protected void dataButton() {
        if (!this.updateCirclesFromFields()) {
            return;
        }
        this.circlePanel.updatePanel(0);
        final CircleLayout[] circles = this.circlePanel.circles;
        final double[] pops = this.circlePanel.pops;
        System.out.print("{0,");
        for (int zone = 1; zone < 8; ++zone) {
            System.out.print(pops[zone]);
            if (zone < 7) {
                System.out.print(",");
            }
        }
        System.out.println("}");
        CircleAreaRunner.outputPopsAndAreas(pops, circles);
        final double fitness = CircleAreaRunner.computeFitness(this.circlePanel.pops, this.circlePanel.circles);
        CircleAreaRunner.computeStructureCheck(this.circlePanel.pops, this.circlePanel.circles);
        System.out.println("Variance:");
        for (int zone2 = 1; zone2 < 8; ++zone2) {
            System.out.println(String.valueOf(zone2) + " " + CircleAreaRunner.zoneIndex[zone2] + " " + CircleAreaRunner.zoneVariance[zone2]);
        }
        System.out.println("Structure:");
        for (int zone2 = 1; zone2 < 8; ++zone2) {
            System.out.println(String.valueOf(zone2) + " " + CircleAreaRunner.zoneIndex[zone2] + " " + CircleAreaRunner.structureData[zone2]);
        }
        System.out.println("Area Data: range data then binary data");
        for (int i = 0; i < CircleAreaRunner.rangeData.length; ++i) {
            System.out.println(String.valueOf(CircleAreaRunner.zonePairString[i]) + " " + CircleAreaRunner.rangeData[i] + " " + CircleAreaRunner.binaryAreaData[i]);
        }
        System.out.println("Total Fitness " + fitness);
        this.updateFieldsFromCircles();
    }
    
    public void updateFieldsFromCircles() {
        final CircleLayout[] circles = this.circlePanel.circles;
        for (int circle = 0; circle < circles.length; ++circle) {
            this.circleXField[circle].setText(Double.toString(circles[circle].center.x));
            this.circleYField[circle].setText(Double.toString(circles[circle].center.y));
            this.circleRadiusField[circle].setText(Double.toString(circles[circle].radius));
            this.circleXField[circle].setCaretPosition(0);
            this.circleYField[circle].setCaretPosition(0);
            this.circleRadiusField[circle].setCaretPosition(0);
        }
        for (int zone = 1; zone < 8; ++zone) {
            this.popsField[zone].setText(Double.toString(this.circlePanel.pops[zone]));
            this.popsField[zone].setCaretPosition(0);
            this.varianceLabel[zone].setText(Double.toString(CirclePanel.round(CircleAreaRunner.zoneVariance[zone] * 1000.0, 2)));
        }
        if (this.circlePanel.labelsStatus != 2) {
            this.resetLabels();
        }
    }
    
    protected void colorRadioButton() {
        this.circlePanel.useColor = true;
        this.circlePanel.updatePanel(0);
    }
    
    protected void monochromeRadioButton() {
        this.circlePanel.useColor = false;
        this.circlePanel.updatePanel(0);
    }
    
    protected void defaultLabelsRadioButton() {
        for (int zone = 1; zone < 8; ++zone) {
            this.labelsField[zone].setEnabled(false);
        }
        this.circlePanel.labelsStatus = 1;
        this.circlePanel.updatePanel(0);
    }
    
    protected void defineLabelsRadioButton() {
        for (int zone = 1; zone < 8; ++zone) {
            this.labelsField[zone].setEnabled(true);
        }
        this.circlePanel.labelsStatus = 2;
        this.circlePanel.updatePanel(0);
    }
    
    protected void hideLabelsRadioButton() {
        for (int zone = 1; zone < 8; ++zone) {
            this.labelsField[zone].setEnabled(false);
        }
        this.circlePanel.labelsStatus = 3;
        this.circlePanel.updatePanel(0);
    }
    
    protected void advancedLabelsRadioButton() {
        for (int zone = 1; zone < 8; ++zone) {
            this.labelsField[zone].setEnabled(false);
        }
        this.circlePanel.labelsStatus = 4;
        this.circlePanel.updatePanel(0);
    }
}
