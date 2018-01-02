import java.awt.event.ActionEvent;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class Map extends Component implements ActionListener
{
    private final String kstrRect = "rect";
    private final String kstrPoint = "point";
    private final String kstrEllipse = "ellipse";
    private final String kstrPoly = "poly";
    private final String kstrLegend = "legend";
    private final String kstrDistance = "dist";
    private final String kstrSubmit = "submit";
    private final String kstrAutoMode = "auto_";
    private final String kstrFormParam = "INP_FORM_NAME";
    private final String kstrTypeParam = "INP_TYPE_NAME";
    private final String kstrCoordParam = "INP_COORD_NAME";
    private final String kstrMapCoordParam = "IMG_XY";
    private Image m_ImagMap;
    private DrawObj m_curDrawObj;
    private DrawObj m_RectDrawObj;
    private DrawObj m_PointDrawObj;
    private DrawObj m_EllipseDrawObj;
    private DrawObj m_PolyDrawObj;
    private DrawObj m_LegendDrawObj;
    private DrawObj m_DistDrawObj;
    private ButtonCtrl m_curButton;
    private Point m_coord;
    private boolean m_bAlreadyUsed;
    private String m_strFormName;
    private String m_strTypeName;
    private String m_strCoordName;
    private Rosa2000 m_parent;
    protected Image m_offScreenImage;
    protected Graphics m_offScreenGraphics;
    
    public Map(final Rosa2000 applet, final Image map, final int nWidth, final int nHeight) {
        this.m_ImagMap = null;
        this.m_curDrawObj = null;
        this.m_RectDrawObj = null;
        this.m_PointDrawObj = null;
        this.m_EllipseDrawObj = null;
        this.m_PolyDrawObj = null;
        this.m_LegendDrawObj = null;
        this.m_DistDrawObj = null;
        this.m_curButton = null;
        this.m_coord = null;
        this.m_bAlreadyUsed = false;
        this.m_strFormName = null;
        this.m_strTypeName = null;
        this.m_strCoordName = null;
        this.m_offScreenImage = null;
        this.m_offScreenGraphics = null;
        this.setVisible(false);
        this.m_ImagMap = map;
        this.m_parent = applet;
        this.setSize(nWidth, nHeight);
        this.readSubmitParams();
        this.readLocation();
        final Dimension appletDim = this.m_parent.getSize();
        this.m_offScreenImage = this.m_parent.createImage(appletDim.width, appletDim.height);
        this.m_offScreenGraphics = this.m_offScreenImage.getGraphics();
    }
    
    public void setObjectDrawer(final DrawObj drawObj) {
        this.m_curDrawObj = drawObj;
    }
    
    public DrawObj getObjectDrawer() {
        return this.m_curDrawObj;
    }
    
    public Image getImage() {
        return this.m_ImagMap;
    }
    
    public Rosa2000 getApplet() {
        return this.m_parent;
    }
    
    public boolean isAutoMode() {
        if (this.m_curButton != null) {
            final String strCmd = this.m_curButton.getActionCommand();
            if (strCmd != null) {
                return strCmd.startsWith("auto_");
            }
        }
        return false;
    }
    
    public Graphics getGraphics() {
        return this.m_offScreenGraphics;
    }
    
    public void paint(final Graphics g) {
        if (this.m_offScreenGraphics != null) {
            this.m_offScreenGraphics.drawImage(this.m_ImagMap, 0, 0, this);
            if (this.getObjectDrawer() != null) {
                this.getObjectDrawer().draw();
            }
            g.drawImage(this.m_offScreenImage, 0, 0, this);
        }
    }
    
    public void redraw() {
        final Graphics g = this.m_parent.getGraphics();
        if (g != null) {
            this.paint(g);
        }
    }
    
    public void commitOperation() {
        if (this.m_strTypeName != null && this.m_strCoordName != null && this.m_curButton.getButtonActionName() != null && this.m_curButton.getButtonActionValue() != null && this.m_curButton.getActionCommand() != null) {
            try {
                final JSObject window = JSObject.getWindow((Applet)this.m_parent);
                final JSObject doc = (JSObject)window.getMember("document");
                JSObject form = null;
                if (this.m_strFormName != null) {
                    form = (JSObject)doc.getMember(this.m_strFormName);
                }
                else {
                    form = (JSObject)doc.getMember("forms");
                    if (form != null) {
                        form = (JSObject)form.getSlot(0);
                    }
                }
                if (form != null) {
                    final JSObject jsInputType = (JSObject)form.getMember(this.m_strTypeName);
                    jsInputType.setMember("value", (Object)this.m_curButton.getActionCommand());
                    if (this.m_curDrawObj != null) {
                        final JSObject jsCoordinate = (JSObject)form.getMember(this.m_strCoordName);
                        jsCoordinate.setMember("value", (Object)this.m_curDrawObj.getSubmitValue());
                    }
                    final JSObject jsButtonAction = (JSObject)form.getMember(this.m_curButton.getButtonActionName());
                    jsButtonAction.setMember("value", (Object)this.m_curButton.getButtonActionValue());
                    if (this.isAutoMode() || this.m_curButton.getActionCommand().equals("submit")) {
                        if (this.m_curButton.getJSAction() != null && this.m_curButton.getJSAction().length() != 0) {
                            JSObject.getWindow((Applet)this.m_parent).eval(this.m_curButton.getJSAction());
                            return;
                        }
                        if (this.m_curButton.getTarget() != null && this.m_curButton.getTarget().length() != 0) {
                            form.setMember("target", (Object)this.m_curButton.getTarget());
                        }
                        this.submitData(form);
                    }
                    else {
                        this.m_curButton.setPressed(false);
                    }
                }
            }
            catch (ClassCastException ex) {
                System.err.println("A parameter is not found. Cannot submit to the server");
            }
            this.m_bAlreadyUsed = true;
            return;
        }
        this.m_bAlreadyUsed = true;
        this.startAction(this.m_curButton, true);
    }
    
    protected void destroyCommand() {
        if (this.getObjectDrawer() != null) {
            this.getObjectDrawer().destroyEvent();
        }
        this.m_curDrawObj = null;
        this.m_curButton = null;
    }
    
    public void submitData(final JSObject form) {
        final Object[] args = new Object[0];
        form.call("submit", args);
    }
    
    public void actionPerformed(final ActionEvent e) {
        this.startAction((ButtonCtrl)e.getSource(), false);
    }
    
    public void startAction(final ButtonCtrl buttonPress, final boolean bIgnoreSubmit) {
        if (this.getObjectDrawer() != null) {
            this.getObjectDrawer().disable_object();
        }
        if (this.m_bAlreadyUsed) {
            this.repaint();
        }
        if (buttonPress.isPressed()) {
            this.m_parent.getTB().setButtonPress(buttonPress);
            final String strName = buttonPress.toString();
            this.m_curButton = buttonPress;
            String strCmd = this.m_curButton.getActionCommand();
            String strValidCmd = new String();
            if (this.isAutoMode()) {
                strValidCmd = "auto_";
            }
            if (strCmd == null) {
                strCmd = "null";
            }
            if (strCmd.equals(strValidCmd + "rect")) {
                if (this.m_RectDrawObj == null) {
                    this.m_RectDrawObj = new RectObj(this);
                }
                else {
                    this.m_RectDrawObj.enable_object();
                }
                this.m_curDrawObj = this.m_RectDrawObj;
            }
            else if (strCmd.equals(strValidCmd + "point")) {
                if (this.m_PointDrawObj == null) {
                    this.m_PointDrawObj = new PointObj(this);
                }
                else {
                    this.m_PointDrawObj.enable_object();
                }
                this.m_curDrawObj = this.m_PointDrawObj;
            }
            else if (strCmd.equals(strValidCmd + "ellipse")) {
                if (this.m_EllipseDrawObj == null) {
                    this.m_EllipseDrawObj = new EllipseObj(this);
                }
                else {
                    this.m_EllipseDrawObj.enable_object();
                }
                this.m_curDrawObj = this.m_EllipseDrawObj;
            }
            else if (strCmd.equals(strValidCmd + "poly")) {
                if (this.m_PolyDrawObj == null) {
                    this.m_PolyDrawObj = new PolyObj(this, this.m_parent);
                }
                else {
                    this.m_PolyDrawObj.enable_object();
                }
                this.m_curDrawObj = this.m_PolyDrawObj;
            }
            else if (strCmd.equals("legend")) {
                if (this.m_LegendDrawObj == null) {
                    this.m_LegendDrawObj = new LegendObj(this, this.m_parent);
                }
                else {
                    this.m_LegendDrawObj.enable_object();
                }
                this.m_curDrawObj = this.m_LegendDrawObj;
            }
            else if (strCmd.equals("dist")) {
                if (this.m_DistDrawObj == null) {
                    this.m_DistDrawObj = new DistObj(this, this.m_parent);
                }
                else {
                    this.m_DistDrawObj.enable_object();
                }
                this.m_curDrawObj = this.m_DistDrawObj;
            }
            else if (strCmd.equals("submit")) {
                if (!bIgnoreSubmit) {
                    this.commitOperation();
                }
            }
            else {
                System.err.println("Command " + strCmd + " not supported");
            }
        }
    }
    
    protected void readLocation() {
        final String strLocation = this.m_parent.getParameter("IMG_XY");
        if (strLocation != null) {
            boolean bError = true;
            String strCoor = new String();
            int nX = 0;
            int nY = 0;
            int nIndex = 0;
            if ((nIndex = strLocation.indexOf(",", nIndex++)) != -1) {
                strCoor = strLocation.substring(0, nIndex);
                try {
                    nX = Integer.valueOf(strCoor, 10);
                    if (nIndex < strLocation.length() - 1) {
                        strCoor = strLocation.substring(nIndex + 1);
                        if (strCoor != null) {
                            nY = Integer.valueOf(strCoor, 10);
                            bError = false;
                        }
                    }
                }
                catch (NumberFormatException ex) {}
            }
            if (!bError) {
                this.setLocation(this.m_coord = new Point(nX, nY));
            }
            else {
                System.err.println("Invalid coordinate parameter: " + strLocation + " for the Map.");
            }
        }
        else {
            this.centerMap();
        }
    }
    
    protected void centerMap() {
        final Dimension mapSize = this.getSize();
        final Dimension parentSize = this.m_parent.getSize();
        this.setLocation(this.m_coord = new Point((parentSize.width - mapSize.width) / 2, (parentSize.height - mapSize.height) / 2));
    }
    
    protected void readSubmitParams() {
        this.m_strFormName = this.m_parent.getParameter("INP_FORM_NAME");
        this.m_strTypeName = this.m_parent.getParameter("INP_TYPE_NAME");
        if (this.m_strTypeName == null) {
            System.err.println("The parameter INP_TYPE_NAME is missing.  The submit command will be disabled.");
        }
        this.m_strCoordName = this.m_parent.getParameter("INP_COORD_NAME");
        if (this.m_strCoordName == null) {
            System.err.println("The parameter INP_COORD_NAME is missing.  The submit command will be disabled.");
        }
    }
}
