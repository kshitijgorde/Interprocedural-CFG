import java.awt.geom.Point2D;
import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.border.Border;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.io.File;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.util.Random;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ExperimentPanel extends JPanel
{
    private static final long serialVersionUID = 1L;
    public static final int BUTTON_WIDTH = 10;
    public static final int INPUT_TEXT_SIZE = 4;
    public static final int INPUT_LABEL_SIZE = 8;
    public static final String SVG_FILE_NAME = "apDiagram.svg";
    protected PolygonPanel polygonPanel;
    protected Random r;
    protected int diagramType;
    protected boolean simpleVersion;
    protected boolean improveLayout;
    protected JPanel dataPanel;
    protected JPanel colorPanel;
    protected JPanel labelsPanel;
    protected JPanel improvePanel;
    protected JPanel typePanel;
    protected JButton drawDiagramButton;
    protected JButton svgButton;
    protected JCheckBox colorButton;
    protected JCheckBox shadingButton;
    protected JCheckBox dashedButton;
    protected JRadioButton labelsRadioButton;
    protected JRadioButton noLabelsRadioButton;
    protected JRadioButton improveRadioButton;
    protected JRadioButton noImproveRadioButton;
    protected JRadioButton type0RadioButton;
    protected JRadioButton type1RadioButton;
    protected JRadioButton type2RadioButton;
    protected JRadioButton type3RadioButton;
    protected JLabel[] areaCalcDiagramLabel;
    protected JLabel[] areaPolyDiagramLabel;
    protected JTextField[] popsField;
    protected JLabel messageLabel;
    double[] popsValue;
    protected File currentFile;
    
    public ExperimentPanel(final PolygonPanel polygonPanel, final int diagramType, final int labelsStatus, final boolean improveLayout, final boolean simpleVersion, final boolean appletVersion) {
        this.r = new Random();
        this.diagramType = 0;
        this.areaCalcDiagramLabel = new JLabel[8];
        this.areaPolyDiagramLabel = new JLabel[8];
        this.popsField = new JTextField[8];
        this.popsValue = new double[8];
        this.currentFile = null;
        this.init(polygonPanel, diagramType, labelsStatus, improveLayout, simpleVersion);
    }
    
    private void init(final PolygonPanel polygonPanel, final int diagramType, final int labelsStatus, final boolean improveLayout, final boolean simpleVersion) {
        this.polygonPanel = polygonPanel;
        this.simpleVersion = simpleVersion;
        this.improveLayout = improveLayout;
        this.diagramType = diagramType;
        this.dataPanel = new JPanel();
        final GridBagLayout gridbag = new GridBagLayout();
        this.setLayout(gridbag);
        this.addWidgets(this, gridbag);
        switch (diagramType) {
            case 0: {
                this.type0RadioButton.setSelected(true);
                break;
            }
            case 1: {
                this.type1RadioButton.setSelected(true);
                break;
            }
            case 2: {
                this.type2RadioButton.setSelected(true);
                break;
            }
            case 3: {
                this.type3RadioButton.setSelected(true);
                break;
            }
        }
        switch (labelsStatus) {
            case 3: {
                this.noLabelsRadioButton.setSelected(true);
                polygonPanel.labelsStatus = 3;
                break;
            }
            case 1: {
                this.labelsRadioButton.setSelected(true);
                polygonPanel.labelsStatus = 1;
                break;
            }
        }
        if (improveLayout) {
            this.improveRadioButton.setSelected(true);
        }
        else {
            this.noImproveRadioButton.setSelected(true);
        }
        this.drawDiagram();
        this.setVisible(true);
    }
    
    protected void addWidgets(final JPanel panel, final GridBagLayout gridbag) {
        final Border etchedBorder = BorderFactory.createEtchedBorder();
        final Border spaceBorder1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        final Border spaceBorder2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        final Border compoundBorder = BorderFactory.createCompoundBorder(etchedBorder, spaceBorder1);
        final Border panelBorder = BorderFactory.createCompoundBorder(spaceBorder2, compoundBorder);
        final GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 5;
        c.ipady = 5;
        int yLevel = 0;
        int xLevel = 0;
        c.gridy = yLevel;
        yLevel = 1;
        xLevel = 0;
        JLabel label = new JLabel("Region", 2);
        c.gridx = xLevel;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        ++xLevel;
        label = new JLabel("Population", 2);
        c.gridx = xLevel;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        ++xLevel;
        label = new JLabel("Measured Area", 2);
        c.gridx = xLevel;
        c.gridy = yLevel;
        gridbag.setConstraints(label, c);
        panel.add(label);
        for (int zone = 1; zone < 8; ++zone) {
            yLevel = zone + 1;
            if (zone == 3) {
                yLevel = 5;
            }
            if (zone == 4) {
                yLevel = 4;
            }
            label = new JLabel(Util.zoneIndex[zone], 2);
            xLevel = 0;
            c.gridx = xLevel;
            c.gridy = yLevel;
            gridbag.setConstraints(label, c);
            panel.add(label);
            ++xLevel;
            (this.popsField[zone] = new JTextField(4)).setHorizontalAlignment(2);
            this.popsField[zone].setText(Double.toString(this.polygonPanel.pops[zone]));
            this.popsField[zone].setSize(new Dimension(60, 20));
            this.popsField[zone].setMinimumSize(new Dimension(60, 20));
            this.popsField[zone].setCaretPosition(0);
            this.popsField[zone].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    ExperimentPanel.this.drawDiagram();
                }
            });
            c.gridx = xLevel;
            c.gridy = yLevel;
            gridbag.setConstraints(this.popsField[zone], c);
            panel.add(this.popsField[zone]);
            ++xLevel;
            (this.areaPolyDiagramLabel[zone] = new JLabel()).setHorizontalAlignment(2);
            this.areaPolyDiagramLabel[zone].setText("");
            c.gridx = xLevel;
            c.gridy = yLevel;
            gridbag.setConstraints(this.areaPolyDiagramLabel[zone], c);
            panel.add(this.areaPolyDiagramLabel[zone]);
        }
        ++yLevel;
        ++yLevel;
        (this.colorPanel = new JPanel()).setBorder(panelBorder);
        c.gridx = 0;
        c.gridy = yLevel;
        c.fill = 2;
        c.gridwidth = 3;
        c.anchor = 10;
        gridbag.setConstraints(this.colorPanel, c);
        panel.add(this.colorPanel);
        ++yLevel;
        final GridBagLayout gridbagColor = new GridBagLayout();
        this.colorPanel.setLayout(gridbagColor);
        final GridBagConstraints gbcColor = new GridBagConstraints();
        gbcColor.ipadx = 0;
        gbcColor.ipady = 0;
        (this.colorButton = new JCheckBox("Colour")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.drawDiagram();
            }
        });
        this.colorButton.setSelected(true);
        if (this.simpleVersion) {
            this.colorButton.setSelected(true);
        }
        gbcColor.gridx = 0;
        gbcColor.gridy = 0;
        gbcColor.fill = 2;
        gbcColor.anchor = 10;
        gridbagColor.setConstraints(this.colorButton, gbcColor);
        this.colorPanel.add(this.colorButton);
        (this.shadingButton = new JCheckBox("Shading")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.drawDiagram();
            }
        });
        this.shadingButton.setSelected(false);
        if (this.simpleVersion) {
            this.shadingButton.setSelected(false);
        }
        gbcColor.gridx = 1;
        gbcColor.gridy = 0;
        gbcColor.fill = 2;
        gbcColor.anchor = 10;
        gridbagColor.setConstraints(this.shadingButton, gbcColor);
        this.colorPanel.add(this.shadingButton);
        (this.dashedButton = new JCheckBox("Dashed")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.drawDiagram();
            }
        });
        this.dashedButton.setSelected(true);
        if (this.simpleVersion) {
            this.dashedButton.setSelected(false);
        }
        gbcColor.gridx = 2;
        gbcColor.gridy = 0;
        gbcColor.fill = 2;
        gbcColor.anchor = 10;
        gridbagColor.setConstraints(this.dashedButton, gbcColor);
        this.colorPanel.add(this.dashedButton);
        (this.labelsPanel = new JPanel()).setBorder(panelBorder);
        c.gridx = 0;
        c.gridy = yLevel;
        c.fill = 2;
        c.gridwidth = 3;
        c.anchor = 10;
        gridbag.setConstraints(this.labelsPanel, c);
        panel.add(this.labelsPanel);
        ++yLevel;
        final GridBagLayout gridbagLabels = new GridBagLayout();
        this.labelsPanel.setLayout(gridbagLabels);
        final GridBagConstraints gbcLabels = new GridBagConstraints();
        gbcLabels.ipadx = 0;
        gbcLabels.ipady = 0;
        (this.labelsRadioButton = new JRadioButton("Show Labels")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.labelsRadioButton();
            }
        });
        gbcLabels.gridx = 0;
        gbcLabels.gridy = 0;
        gbcLabels.fill = 2;
        gbcLabels.anchor = 10;
        gridbagLabels.setConstraints(this.labelsRadioButton, gbcLabels);
        this.labelsPanel.add(this.labelsRadioButton);
        (this.noLabelsRadioButton = new JRadioButton("No Labels")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.noLabelsRadioButton();
            }
        });
        gbcLabels.gridx = 1;
        gbcLabels.gridy = 0;
        gbcLabels.fill = 2;
        gbcLabels.anchor = 10;
        gridbagLabels.setConstraints(this.noLabelsRadioButton, gbcLabels);
        this.labelsPanel.add(this.noLabelsRadioButton);
        final ButtonGroup labelsGroup = new ButtonGroup();
        labelsGroup.add(this.labelsRadioButton);
        labelsGroup.add(this.noLabelsRadioButton);
        (this.improvePanel = new JPanel()).setBorder(panelBorder);
        c.gridx = 0;
        c.gridy = yLevel;
        c.fill = 2;
        c.gridwidth = 3;
        c.anchor = 10;
        gridbag.setConstraints(this.improvePanel, c);
        panel.add(this.improvePanel);
        ++yLevel;
        final GridBagLayout gridbagImprove = new GridBagLayout();
        this.improvePanel.setLayout(gridbagImprove);
        if (this.simpleVersion) {
            this.improvePanel.setVisible(false);
        }
        final GridBagConstraints gbcImprove = new GridBagConstraints();
        gbcImprove.ipadx = 0;
        gbcImprove.ipady = 0;
        (this.noImproveRadioButton = new JRadioButton("Unimproved Layout")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.noImproveRadioButton();
            }
        });
        gbcImprove.gridx = 0;
        gbcImprove.gridy = 0;
        gbcImprove.fill = 2;
        gbcImprove.anchor = 10;
        gridbagImprove.setConstraints(this.noImproveRadioButton, gbcImprove);
        this.improvePanel.add(this.noImproveRadioButton);
        (this.improveRadioButton = new JRadioButton("Improved Layout")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.improveRadioButton();
            }
        });
        gbcImprove.gridx = 1;
        gbcImprove.gridy = 0;
        gbcImprove.fill = 2;
        gbcImprove.anchor = 10;
        gridbagImprove.setConstraints(this.improveRadioButton, gbcImprove);
        this.improvePanel.add(this.improveRadioButton);
        final ButtonGroup improveGroup = new ButtonGroup();
        improveGroup.add(this.noImproveRadioButton);
        improveGroup.add(this.improveRadioButton);
        (this.typePanel = new JPanel()).setBorder(panelBorder);
        if (this.simpleVersion) {
            this.typePanel.setVisible(false);
        }
        c.gridx = 0;
        c.gridy = yLevel;
        c.fill = 2;
        c.gridwidth = 3;
        c.anchor = 10;
        gridbag.setConstraints(this.typePanel, c);
        panel.add(this.typePanel);
        ++yLevel;
        final GridBagLayout gridbagtype = new GridBagLayout();
        this.typePanel.setLayout(gridbagtype);
        final GridBagConstraints gbctype = new GridBagConstraints();
        gbctype.ipadx = 0;
        gbctype.ipady = 0;
        (this.type0RadioButton = new JRadioButton("Core-Triangular")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.type0RadioButton();
            }
        });
        gbctype.gridx = 0;
        gbctype.gridy = 0;
        gbctype.fill = 2;
        gbctype.anchor = 10;
        gridbagtype.setConstraints(this.type0RadioButton, gbctype);
        this.typePanel.add(this.type0RadioButton);
        (this.type1RadioButton = new JRadioButton("Triangular")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.type1RadioButton();
            }
        });
        gbctype.gridx = 1;
        gbctype.gridy = 0;
        gbctype.fill = 2;
        gbctype.anchor = 10;
        gridbagtype.setConstraints(this.type1RadioButton, gbctype);
        this.typePanel.add(this.type1RadioButton);
        (this.type2RadioButton = new JRadioButton("DT-Triangular")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.type2RadioButton();
            }
        });
        gbctype.gridx = 0;
        gbctype.gridy = 1;
        gbctype.fill = 2;
        gbctype.anchor = 10;
        gridbagtype.setConstraints(this.type2RadioButton, gbctype);
        this.typePanel.add(this.type2RadioButton);
        (this.type3RadioButton = new JRadioButton("CH-Triangular")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.type3RadioButton();
            }
        });
        gbctype.gridx = 1;
        gbctype.gridy = 1;
        gbctype.fill = 2;
        gbctype.anchor = 10;
        gridbagtype.setConstraints(this.type3RadioButton, gbctype);
        this.typePanel.add(this.type3RadioButton);
        final ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(this.type0RadioButton);
        typeGroup.add(this.type1RadioButton);
        typeGroup.add(this.type2RadioButton);
        typeGroup.add(this.type3RadioButton);
        this.type3RadioButton.setSelected(true);
        this.diagramType = 3;
        ++yLevel;
        (this.svgButton = new JButton("Save As SVG File")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.svgButton();
            }
        });
        c.gridx = 0;
        c.gridy = yLevel;
        c.gridwidth = 6;
        c.fill = 2;
        c.anchor = 10;
        gridbag.setConstraints(this.svgButton, c);
        panel.add(this.svgButton);
        if (this.simpleVersion) {
            this.svgButton.setVisible(false);
        }
        ++yLevel;
        (this.drawDiagramButton = new JButton("Draw Diagram")).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                ExperimentPanel.this.drawDiagram();
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
        this.messageLabel = new JLabel(" ");
        c.gridx = 0;
        c.gridy = yLevel;
        c.fill = 2;
        c.gridwidth = 6;
        c.anchor = 10;
        gridbag.setConstraints(this.messageLabel, c);
        panel.add(this.messageLabel);
    }
    
    protected void noLabelsRadioButton() {
        this.polygonPanel.labelsStatus = 3;
        this.drawDiagram();
    }
    
    protected void labelsRadioButton() {
        this.polygonPanel.labelsStatus = 1;
        this.drawDiagram();
    }
    
    protected void drawDiagram() {
        this.updatePolygonsFromFields(false);
        this.updateFields();
    }
    
    private void svgButton() {
        System.out.println(this.currentFile);
        try {
            JFileChooser chooser = null;
            if (this.currentFile == null) {
                chooser = new JFileChooser();
            }
            else {
                chooser = new JFileChooser(this.currentFile);
            }
            final int returnVal = chooser.showSaveDialog(this);
            if (returnVal == 0) {
                final File chosenFile = chooser.getSelectedFile();
                String fileString = chosenFile.toString();
                if (fileString.length() < 4) {
                    fileString = String.valueOf(fileString) + ".svg";
                }
                final String extension = fileString.substring(fileString.length() - 4, fileString.length());
                if (!extension.equals(".svg") && !extension.equals(".SVG")) {
                    fileString = String.valueOf(fileString) + ".svg";
                }
                this.polygonPanel.saveSVGFile(new File(fileString));
                this.messageLabel.setText("Saved file " + fileString);
                this.currentFile = new File(fileString);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            this.messageLabel.setText("Problems saving file");
        }
    }
    
    protected boolean updatePolygonsFromFields(final boolean useFallback) {
        this.polygonPanel.useColor = this.colorButton.isSelected();
        this.polygonPanel.useShading = this.shadingButton.isSelected();
        this.polygonPanel.useDashes = this.dashedButton.isSelected();
        for (int zone = 1; zone < 8; ++zone) {
            this.popsValue[zone] = Util.safeParseDouble(this.popsField[zone].getText());
            if (this.simpleVersion && this.popsValue[zone] < 0.0) {
                this.messageLabel.setText("Invalid population for zone " + Util.zoneIndex[zone]);
                return false;
            }
        }
        for (int zone = 1; zone < 8; ++zone) {
            this.polygonPanel.pops[zone] = this.popsValue[zone];
        }
        this.messageLabel.setText(" ");
        boolean ret = this.polygonPanel.updatePanel(0, this.diagramType, this.improveLayout, this.simpleVersion);
        if (!ret && useFallback) {
            this.messageLabel.setText("Failed to draw, switching to Core-Triangular");
            ret = this.polygonPanel.updatePanel(0, 0, this.improveLayout, false);
            if (!ret) {
                this.messageLabel.setText("Failed to draw, switching to CH-Triangular");
                ret = this.polygonPanel.updatePanel(0, 3, this.improveLayout, false);
            }
            if (!ret) {
                this.messageLabel.setText("Failed to draw, switching to DT-Triangular");
                ret = this.polygonPanel.updatePanel(0, 2, this.improveLayout, false);
            }
            if (!ret) {
                this.messageLabel.setText("Failed to draw, switching to Triangular");
                ret = this.polygonPanel.updatePanel(0, 1, this.improveLayout, false);
            }
            if (!ret) {
                this.messageLabel.setText("Failed to draw any diagram type");
            }
        }
        else if (!ret) {
            this.messageLabel.setText("Failed to draw requested diagram type");
        }
        if (this.polygonPanel.polygonDiagram != null && !this.simpleVersion) {
            if (!this.polygonPanel.polygonDiagram.convex) {
                this.messageLabel.setForeground(Color.red);
            }
            else {
                this.messageLabel.setForeground(Color.black);
            }
            this.messageLabel.setText("Convex ? " + this.polygonPanel.polygonDiagram.convex + ", checksum = " + Util.round(this.polygonPanel.diagramChecksum, 3));
        }
        return true;
    }
    
    public void updateFields() {
        final PolygonDiagram pd = this.polygonPanel.getPolygonDiagram();
        if (pd == null) {
            for (int zone = 1; zone < 8; ++zone) {
                this.areaPolyDiagramLabel[zone].setText("-");
            }
            return;
        }
        this.polygonPanel.centreDiagram();
        final Point2D.Double[][] polyZones = pd.findZonePolygonArrays();
        final double polyMiddleArea = Util.computePolygonArea(polyZones[7]);
        for (int zone2 = 1; zone2 < 8; ++zone2) {
            final double polyAreaOfZone = Util.computePolygonArea(polyZones[zone2]);
            double scaledAreaOfZone = polyAreaOfZone / polyMiddleArea;
            scaledAreaOfZone *= this.polygonPanel.pops[7];
            this.areaPolyDiagramLabel[zone2].setText(Double.toString(Util.round(scaledAreaOfZone, 3)));
        }
    }
    
    protected void noImproveRadioButton() {
        this.improveLayout = false;
        this.drawDiagram();
    }
    
    protected void improveRadioButton() {
        this.improveLayout = true;
        this.drawDiagram();
    }
    
    protected void type0RadioButton() {
        this.diagramType = 0;
        this.drawDiagram();
    }
    
    protected void type1RadioButton() {
        this.diagramType = 1;
        this.drawDiagram();
    }
    
    protected void type2RadioButton() {
        this.diagramType = 2;
        this.drawDiagram();
    }
    
    protected void type3RadioButton() {
        this.diagramType = 3;
        this.drawDiagram();
    }
}
