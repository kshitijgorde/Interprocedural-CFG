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
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
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

public class ControlPanel extends JPanel implements ActionListener, ItemListener, ChangeListener
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
    private static final String CROP = "Crop";
    private static final String DELETEPOINT = "Delete Point";
    private static final String CHBORDERSIDE = "Inside";
    private static final String REPEATBORDER = "Repeat Border";
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
    private DecimalFormat dfm;
    private Checkbox addSubtract;
    private Frame m_parent;
    private FileDialog m_fileDialog;
    private CheckboxGroup perspective;
    private Checkbox paving;
    private Checkbox walls;
    private Checkbox free;
    
    public ControlPanel(final AreaCanvas areaCanvas) {
        this.originalImage = null;
        this.dfm = new DecimalFormat("#.##");
        this.areaCanvas = areaCanvas;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        final Panel innerPanel = new Panel();
        innerPanel.setLayout(new GridLayout(0, 1, 1, 2));
        final Font smallFont = new Font("SansSerif", 0, 10);
        final Font mediumFont = new Font("SansSerif", 0, 12);
        innerPanel.setFont(smallFont);
        final Button loadBtn = new Button("Load image");
        loadBtn.setFont(mediumFont);
        loadBtn.setActionCommand("LoadImage");
        innerPanel.add(loadBtn);
        final Button txtrBtn = new Button("Apply texture");
        txtrBtn.setFont(mediumFont);
        txtrBtn.setActionCommand("LoadTexture");
        innerPanel.add(txtrBtn);
        final Button brdrBtn = new Button("Draw Border");
        brdrBtn.setFont(mediumFont);
        brdrBtn.setActionCommand("Border");
        Panel subPanel = new Panel();
        subPanel.setLayout(new GridLayout(1, 2));
        subPanel.add(this.drawMode = new Checkbox("Drawing mode.", false));
        subPanel.add(this.addSubtract = new Checkbox("Subtract new Area", false));
        innerPanel.add(subPanel);
        subPanel = new Panel();
        subPanel.setLayout(new GridLayout(1, 3));
        this.perspective = new CheckboxGroup();
        subPanel.add(this.paving = new Checkbox("Paving", this.perspective, true));
        subPanel.add(this.walls = new Checkbox("Walls", this.perspective, false));
        subPanel.add(this.free = new Checkbox("Free", this.perspective, false));
        innerPanel.add(subPanel);
        subPanel = new Panel();
        subPanel.setLayout(new GridLayout(1, 2));
        this.lineType = new CheckboxGroup();
        subPanel.add(this.straight = new Checkbox("Straight", this.lineType, true));
        subPanel.add(this.curves = new Checkbox("Curved", this.lineType, false));
        innerPanel.add(subPanel);
        final JLabel zoom = new JLabel("Zoom to: ");
        innerPanel.add(zoom);
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
        subPanel = new Panel();
        subPanel.setLayout(new FlowLayout(0, 5, 5));
        subPanel.add(new JLabel("Scale", 2));
        subPanel.add(this.scale = new TextField("1.0", 4));
        final Button apZmBtn = new Button("Apply");
        apZmBtn.setFont(mediumFont);
        apZmBtn.setActionCommand("Zoom");
        subPanel.add(apZmBtn);
        innerPanel.add(subPanel);
        subPanel = new Panel();
        subPanel.setLayout(new FlowLayout(0, 5, 5));
        final Button lftBtn = new Button("Rotate Left");
        lftBtn.setFont(mediumFont);
        lftBtn.setActionCommand("RotateRight");
        subPanel.add(lftBtn);
        final Button rgtBtn = new Button("Rotate Right");
        rgtBtn.setFont(mediumFont);
        rgtBtn.setActionCommand("RotateLeft");
        subPanel.add(rgtBtn);
        innerPanel.add(subPanel);
        subPanel = new Panel();
        subPanel.setLayout(new FlowLayout(1, 5, 2));
        final Button tltDwnBtn = new Button("Tilt Down");
        tltDwnBtn.setFont(mediumFont);
        tltDwnBtn.setActionCommand("TiltDown");
        subPanel.add(tltDwnBtn);
        final Button nxtBtn = new Button("Next");
        nxtBtn.setFont(mediumFont);
        nxtBtn.setActionCommand("Next");
        innerPanel.add(nxtBtn);
        final JButton opnButton = new JButton("Open");
        opnButton.setActionCommand("Open");
        innerPanel.add(opnButton);
        final JButton clrBtn = new JButton("Clear");
        clrBtn.setFont(mediumFont);
        clrBtn.setActionCommand("Clear");
        innerPanel.add(clrBtn);
        (this.originalImage = new JButton("Original Image")).setFont(mediumFont);
        this.originalImage.setActionCommand("Original");
        innerPanel.add(this.originalImage);
        final Button delBtn = new Button("Previous");
        delBtn.setFont(mediumFont);
        delBtn.setActionCommand("Delete");
        innerPanel.add(delBtn);
        final Button cropBtn = new Button("Crop image");
        cropBtn.setFont(mediumFont);
        cropBtn.setActionCommand("Crop");
        innerPanel.add(cropBtn);
        final Button deletePtBtn = new Button("Delete Point");
        deletePtBtn.setFont(mediumFont);
        deletePtBtn.setActionCommand("Delete Point");
        innerPanel.add(deletePtBtn);
        final Button chDrawMode = new Button("Outside");
        chDrawMode.setFont(mediumFont);
        chDrawMode.setActionCommand("Inside");
        innerPanel.add(chDrawMode);
        final Button repeatBorder = new Button("Repeat Border");
        repeatBorder.setFont(mediumFont);
        repeatBorder.setActionCommand("Repeat Border");
        innerPanel.add(repeatBorder);
        this.add("North", innerPanel);
        apZmBtn.addActionListener(this);
        lftBtn.addActionListener(this);
        rgtBtn.addActionListener(this);
        tltDwnBtn.addActionListener(this);
        this.originalImage.addActionListener(this);
        loadBtn.addActionListener(this);
        txtrBtn.addActionListener(this);
        brdrBtn.addActionListener(this);
        nxtBtn.addActionListener(this);
        clrBtn.addActionListener(this);
        delBtn.addActionListener(this);
        opnButton.addActionListener(this);
        cropBtn.addActionListener(this);
        deletePtBtn.addActionListener(this);
        chDrawMode.addActionListener(this);
        repeatBorder.addActionListener(this);
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
            this.getParent().getParent().setCursor(new Cursor(3));
            ((ImageApplet)this.getParent().getParent()).clear();
            this.getParent().getParent().setCursor(new Cursor(0));
        }
        else if (action.equals("Crop")) {
            ((ImageApplet)this.getParent().getParent()).cropImage();
        }
        else if (action.equals("Delete")) {
            ((ImageApplet)this.getParent().getParent()).previousStep();
        }
        else if (action.equals("Delete Point")) {
            ((ImageApplet)this.getParent().getParent()).undoBorderPoint();
        }
        else if (action.equals("Inside")) {
            final Button btn = (Button)aEvent.getSource();
            if (btn.getLabel().equals("Inside")) {
                ((ImageApplet)this.getParent().getParent()).drawBorder("INSIDE");
                btn.setLabel("Outside");
            }
            else {
                ((ImageApplet)this.getParent().getParent()).drawBorder("OUTSIDE");
                btn.setLabel("Inside");
            }
        }
        else if (action.equals("Zoom")) {
            this.areaCanvas.zoomTexture(Double.parseDouble(this.scale.getText()));
        }
        else if (action.equals("RotateRight")) {
            ((ImageApplet)this.getParent().getParent()).tranformTexture("Left");
        }
        else if (action.equals("RotateLeft")) {
            ((ImageApplet)this.getParent().getParent()).tranformTexture("Right");
        }
        else if (action.equals("Open")) {
            ((ImageApplet)this.getParent().getParent()).loadKerbFromFile();
        }
        else if (action.equals("Next")) {
            ((ImageApplet)this.getParent().getParent()).nextStep();
        }
        else if (action.equals("Repeat Border")) {
            ((ImageApplet)this.getParent().getParent()).repeatBorder();
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
}
