// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import javax.swing.event.ChangeEvent;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.JSlider;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;
import java.text.DecimalFormat;
import javax.swing.JButton;
import java.awt.TextField;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import javax.swing.event.ChangeListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class ControlPanelN extends JPanel implements ActionListener, ItemListener, ChangeListener
{
    private static final long serialVersionUID = 6574260330614696731L;
    private static final String LOAD = "LoadImage";
    private static final String TEXTURE = "LoadTexture";
    private static final String CLEAR = "Clear";
    private static final String RIGHT = "RotateLeft";
    private static final String LEFT = "RotateRight";
    private static final String TLTDOWN = "TiltDown";
    private static final int SCALE_MIN = -100;
    private static final int SCALE_MAX = 100;
    private static final int SCALE_INIT = 0;
    private static final String APLZOOM = "Zoom";
    private static final String NEXT = "Next";
    private static final String BORDER = "Border";
    private static final String NEWSTART = "StartNew";
    private static final String DELETE = "Delete";
    private static final String OPEN = "Open";
    AreaCanvas areaCanvas;
    Checkbox drawMode;
    CheckboxGroup lineType;
    CheckboxGroup drawType;
    Checkbox area;
    Checkbox border;
    Checkbox straight;
    Checkbox curves;
    TextField scale;
    private JButton originalImage;
    private JButton opnButton;
    private JButton clrBtn;
    private DecimalFormat dfm;
    private Checkbox addSubtract;
    private Frame m_parent;
    private FileDialog m_fileDialog;
    private CheckboxGroup perspective;
    private Checkbox paving;
    private Checkbox walls;
    private Checkbox free;
    private Button loadBtn;
    private Button txtrBtn;
    private Button brdrBtn;
    private Button nxtBtn;
    private Button delBtn;
    private Panel subPanel;
    private Panel pavingTypePanel;
    private Panel lineTypePanel;
    private Panel zoomPanel;
    private Panel scalePanel;
    private Panel rotatePanel;
    private Panel tiltPanel;
    
    public ControlPanelN(final AreaCanvas areaCanvas) {
        this.originalImage = null;
        this.opnButton = null;
        this.clrBtn = null;
        this.dfm = new DecimalFormat("#.##");
        this.areaCanvas = areaCanvas;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        final Font smallFont = new Font("SansSerif", 0, 10);
        final Font mediumFont = new Font("SansSerif", 0, 12);
        final Panel innerPanel = new Panel();
        innerPanel.setLayout(new GridLayout(0, 1, 1, 2));
        (this.loadBtn = new Button("Load image")).setFont(mediumFont);
        this.loadBtn.setActionCommand("LoadImage");
        innerPanel.add(this.loadBtn);
        (this.txtrBtn = new Button("Apply texture")).setFont(mediumFont);
        this.txtrBtn.setActionCommand("LoadTexture");
        innerPanel.add(this.txtrBtn);
        (this.brdrBtn = new Button("Draw Border")).setFont(mediumFont);
        this.brdrBtn.setActionCommand("Border");
        (this.subPanel = new Panel()).setLayout(new GridLayout(1, 2));
        this.drawMode = new Checkbox("Drawing mode.", false);
        this.subPanel.add(this.drawMode);
        this.addSubtract = new Checkbox("Subtract new Area", false);
        this.subPanel.add(this.addSubtract);
        innerPanel.add(this.subPanel);
        (this.pavingTypePanel = new Panel()).setLayout(new GridLayout(1, 3));
        this.perspective = new CheckboxGroup();
        this.paving = new Checkbox("Paving", this.perspective, true);
        this.pavingTypePanel.add(this.paving);
        this.walls = new Checkbox("Walls", this.perspective, false);
        this.pavingTypePanel.add(this.walls);
        this.free = new Checkbox("Free", this.perspective, false);
        this.pavingTypePanel.add(this.free);
        innerPanel.add(this.pavingTypePanel);
        (this.lineTypePanel = new Panel()).setLayout(new GridLayout(1, 2));
        this.lineType = new CheckboxGroup();
        this.straight = new Checkbox("Straight", this.lineType, true);
        this.lineTypePanel.add(this.straight);
        this.curves = new Checkbox("Curved", this.lineType, false);
        this.lineTypePanel.add(this.curves);
        innerPanel.add(this.lineTypePanel);
        (this.zoomPanel = new Panel()).setLayout(new GridLayout(2, 1));
        final JLabel zoom = new JLabel("Zoom to: ");
        this.zoomPanel.add(zoom);
        final JSlider zoomScale = new JSlider(0, -100, 100, 0);
        zoomScale.addChangeListener(this);
        zoomScale.setMajorTickSpacing(10);
        zoomScale.setPaintTicks(true);
        final Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(new Integer(0), new JLabel("1"));
        labelTable.put(new Integer(-100), new JLabel("0.1"));
        labelTable.put(new Integer(100), new JLabel("10"));
        zoomScale.setLabelTable(labelTable);
        zoomScale.setPaintLabels(true);
        this.zoomPanel.add(zoomScale);
        innerPanel.add(this.zoomPanel);
        (this.scalePanel = new Panel()).setLayout(new FlowLayout(0, 5, 5));
        this.scalePanel.add(new JLabel("Scale", 2));
        this.scale = new TextField("1.0", 4);
        this.scalePanel.add(this.scale);
        final Button apZmBtn = new Button("Apply");
        apZmBtn.setFont(mediumFont);
        apZmBtn.setActionCommand("Zoom");
        this.scalePanel.add(apZmBtn);
        innerPanel.add(this.scalePanel);
        (this.rotatePanel = new Panel()).setLayout(new FlowLayout(0, 5, 5));
        final Button lftBtn = new Button("Rotate Left");
        lftBtn.setFont(mediumFont);
        lftBtn.setActionCommand("RotateRight");
        this.rotatePanel.add(lftBtn);
        final Button rgtBtn = new Button("Rotate Right");
        rgtBtn.setFont(mediumFont);
        rgtBtn.setActionCommand("RotateLeft");
        this.rotatePanel.add(rgtBtn);
        innerPanel.add(this.rotatePanel);
        (this.tiltPanel = new Panel()).setLayout(new FlowLayout(1, 5, 2));
        final Button tltDwnBtn = new Button("Tilt Down");
        tltDwnBtn.setFont(mediumFont);
        tltDwnBtn.setActionCommand("TiltDown");
        this.tiltPanel.add(tltDwnBtn);
        (this.nxtBtn = new Button("Next")).setFont(mediumFont);
        this.nxtBtn.setActionCommand("Next");
        innerPanel.add(this.nxtBtn);
        (this.opnButton = new JButton("Open")).setActionCommand("Open");
        innerPanel.add(this.opnButton);
        (this.clrBtn = new JButton("Clear")).setFont(mediumFont);
        this.clrBtn.setActionCommand("Clear");
        innerPanel.add(this.clrBtn);
        (this.originalImage = new JButton("Original Image")).setFont(mediumFont);
        this.originalImage.setActionCommand("Original");
        innerPanel.add(this.originalImage);
        (this.delBtn = new Button("Delete Point")).setFont(mediumFont);
        this.delBtn.setActionCommand("Delete");
        innerPanel.add(this.delBtn);
        this.add("North", innerPanel);
        apZmBtn.addActionListener(this);
        lftBtn.addActionListener(this);
        rgtBtn.addActionListener(this);
        tltDwnBtn.addActionListener(this);
        this.originalImage.addActionListener(this);
        this.loadBtn.addActionListener(this);
        this.txtrBtn.addActionListener(this);
        this.brdrBtn.addActionListener(this);
        this.nxtBtn.addActionListener(this);
        this.clrBtn.addActionListener(this);
        this.delBtn.addActionListener(this);
        this.opnButton.addActionListener(this);
        this.drawMode.addItemListener(this);
        this.addSubtract.addItemListener(this);
        this.paving.addItemListener(this);
        this.walls.addItemListener(this);
        this.free.addItemListener(this);
        this.straight.addItemListener(this);
        this.curves.addItemListener(this);
    }
    
    @Override
    public void actionPerformed(final ActionEvent aEvent) {
        final String action = aEvent.getActionCommand();
        System.out.println("Action action...." + action);
        if (action.equals("Clear")) {
            this.areaCanvas.clearDraw();
            this.getParent().getParent().setCursor(new Cursor(3));
        }
        else if (action.equals("Delete")) {
            ((ImageApplet)this.getParent().getParent()).previousStep();
        }
        else if (action.equals("Zoom")) {
            this.areaCanvas.zoomTexture(Double.parseDouble(this.scale.getText()));
        }
        else if (action.equals("RotateRight")) {
            ((ImageApplet)this.getParent().getParent()).cropImage();
        }
        else if (action.equals("RotateLeft")) {
            this.areaCanvas.tranformTexture(362);
        }
        else if (action.equals("Open")) {
            ((ImageApplet)this.getParent().getParent()).loadKerbFromFile();
        }
        else if (action.equals("Next")) {
            ((ImageApplet)this.getParent().getParent()).nextStep();
        }
        else if (action.equals("StartNew")) {
            this.areaCanvas.finishPolygon();
        }
        else if (action.equals("Original")) {
            System.out.println("inside Original...................");
            ((ImageApplet)this.getParent().getParent()).getOriginalImage("");
        }
        else if (action.equals("LoadImage")) {
            final ImageApplet iap = (ImageApplet)this.getParent().getParent();
            iap.loadImage();
        }
        else if (action.equals("LoadTexture")) {
            final String file = this.openDialog();
            if (file != null) {
                final File selectedFile = new File(file);
                try {
                    final BufferedImage image = ImageIO.read(selectedFile);
                    final AbstractCanvas current = ((ImageApplet)this.getParent().getParent()).getCurrent();
                    current.applyTexture(image);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public void itemStateChanged(final ItemEvent iev) {
        final Checkbox eventFrom = (Checkbox)iev.getSource();
        if (eventFrom.equals(this.drawMode)) {
            this.areaCanvas.setDrawMode(this.drawMode.getState());
        }
        else if (eventFrom.equals(this.addSubtract)) {
            this.areaCanvas.setToSubtractCurrent(this.addSubtract.getState());
        }
        else if (eventFrom.equals(this.curves)) {
            ((ImageApplet)this.getParent().getParent()).setCurveType();
        }
        else if (eventFrom.equals(this.straight)) {
            ((ImageApplet)this.getParent().getParent()).setLineType();
        }
        else if (eventFrom.equals(this.paving) || eventFrom.equals(this.walls) || eventFrom.equals(this.free)) {
            if (this.perspective.getSelectedCheckbox() == this.paving) {
                this.areaCanvas.setPerspectiveType((short)1);
            }
            else if (this.perspective.getSelectedCheckbox() == this.walls) {
                this.areaCanvas.setPerspectiveType((short)2);
            }
            else {
                this.areaCanvas.setPerspectiveType((short)3);
            }
        }
    }
    
    @Override
    public void stateChanged(final ChangeEvent evt) {
        final JSlider ref = (JSlider)evt.getSource();
        this.scale.setText(this.dfm.format(Math.pow(10.0, 0.01 * ref.getValue())));
    }
    
    public String openDialog() {
        if (this.m_parent == null) {
            this.m_parent = new Frame();
        }
        if (this.m_fileDialog == null) {
            this.m_fileDialog = new FileDialog(this.m_parent, "File Upload", 0);
        }
        this.m_fileDialog.setVisible(true);
        this.m_fileDialog.toFront();
        final String file = this.m_fileDialog.getFile();
        final String directory = this.m_fileDialog.getDirectory();
        this.m_fileDialog.setVisible(false);
        this.m_parent.setVisible(false);
        if (file == null || file.length() == 0) {
            return null;
        }
        return String.valueOf(directory) + File.separator + file;
    }
    
    public void setNewCanvas(final AreaCanvas areaCanvas) {
        this.areaCanvas = areaCanvas;
    }
    
    public void PrepareStep(final String action) {
        if (action == "Step2") {
            this.loadBtn.setVisible(true);
            this.txtrBtn.setVisible(false);
            this.lineTypePanel.setVisible(false);
            this.subPanel.setVisible(false);
            this.pavingTypePanel.setVisible(false);
            this.rotatePanel.setVisible(false);
            this.tiltPanel.setVisible(false);
            this.scalePanel.setVisible(false);
            this.zoomPanel.setVisible(false);
            this.nxtBtn.setVisible(false);
            this.opnButton.setVisible(false);
            this.delBtn.setVisible(false);
            this.originalImage.setVisible(false);
            this.clrBtn.setVisible(false);
        }
        else if (action == "Step3") {
            this.loadBtn.setVisible(false);
            this.txtrBtn.setVisible(true);
            this.lineTypePanel.setVisible(true);
            this.subPanel.setVisible(true);
            this.pavingTypePanel.setVisible(true);
            this.rotatePanel.setVisible(true);
            this.tiltPanel.setVisible(true);
            this.scalePanel.setVisible(true);
            this.zoomPanel.setVisible(true);
            ((ImageApplet)this.getParent().getParent()).nextStep();
        }
        else if (action == "Step4" || action == "Step5" || action != "Step6") {}
    }
}
