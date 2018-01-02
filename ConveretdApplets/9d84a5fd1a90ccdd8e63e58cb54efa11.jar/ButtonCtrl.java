import java.awt.Cursor;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.AWTEventMulticaster;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class ButtonCtrl extends Component
{
    protected static final String kstrPrefixParam = "TB_BUT_";
    protected final String kstrHintParam = "_HINT";
    protected final String kstrCursorTypeParam = "_CURSOR_TYPE";
    protected final String kstrBorderParam = "_BORDER";
    protected final String kstrXYLocParam = "_XY";
    protected final String kstrInputParam = "_INPUT";
    protected final String kstrNameParam = "_NAME";
    protected final String kstrValueParam = "_VALUE";
    protected final String kstrTargetParam = "_TARGET";
    protected final String kstrJSActionParam = "_JSACTION";
    protected String m_strName;
    protected int m_nXSize;
    protected int m_nYSize;
    protected boolean m_bPressed;
    protected Image m_Img;
    protected ActionListener m_Listener;
    protected String m_strCmd;
    protected String m_strHint;
    protected String m_strCursorType;
    protected int m_borderColor;
    protected String m_strNameParam;
    protected String m_strValueParam;
    protected String m_strTarget;
    protected String m_strJSAction;
    protected Rosa2000 m_applet;
    protected boolean m_bIsAutoPlace;
    private ToolTipButton m_ToolTip;
    
    public ButtonCtrl(final String strName, final boolean bAutoPress, final Rosa2000 applet) {
        this(strName, 0, 0, bAutoPress, applet);
    }
    
    public ButtonCtrl(final String strName, final int nXSize, final int nYSize, final boolean bAutoPress, final Rosa2000 applet) {
        this.m_strName = null;
        this.m_strHint = null;
        this.m_strCursorType = null;
        this.m_borderColor = 0;
        this.m_bIsAutoPlace = true;
        this.m_ToolTip = null;
        this.m_nXSize = nXSize;
        this.m_nYSize = nYSize;
        this.m_strName = strName;
        this.m_bPressed = false;
        this.addMouseListener(new ButtonHandler(this, bAutoPress));
        this.addMouseListener(this.m_ToolTip = new ToolTipButton(applet, this));
        this.m_strCmd = "";
        this.m_applet = applet;
    }
    
    public abstract void paint(final Graphics p0);
    
    public String getButtonName() {
        return this.m_strName;
    }
    
    public boolean isAutoPlacement() {
        return this.m_bIsAutoPlace;
    }
    
    public void setAutoPlacement(final boolean bAuto) {
        this.m_bIsAutoPlace = bAuto;
    }
    
    public String getButtonActionName() {
        return this.m_strNameParam;
    }
    
    public String getButtonActionValue() {
        return this.m_strValueParam;
    }
    
    public String getHint() {
        return this.m_strHint;
    }
    
    public static String getPrefixButtonParam() {
        return "TB_BUT_";
    }
    
    public void setPressed(final boolean bPressed) {
        this.m_bPressed = bPressed;
        this.repaint();
    }
    
    public boolean isPressed() {
        return this.m_bPressed;
    }
    
    public ActionListener getListener() {
        return this.m_Listener;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.m_nXSize, this.m_nYSize);
    }
    
    public void setActionCommand(final String s) {
        this.m_strCmd = s;
    }
    
    public String getActionCommand() {
        return this.m_strCmd;
    }
    
    public String getTarget() {
        return this.m_strTarget;
    }
    
    public String getJSAction() {
        return this.m_strJSAction;
    }
    
    public void addActionListener(final ActionListener l) {
        this.m_Listener = AWTEventMulticaster.add(this.m_Listener, l);
    }
    
    public void removeActionListener(final ActionListener l) {
        this.m_Listener = AWTEventMulticaster.remove(this.m_Listener, l);
    }
    
    protected void init() {
        try {
            if (this.m_strName != null) {
                final String strDefBut = "TB_BUT_" + this.m_strName;
                if (this.m_nXSize == 0) {
                    this.m_nXSize = this.m_Img.getWidth(this.m_applet);
                }
                if (this.m_nYSize == 0) {
                    this.m_nYSize = this.m_Img.getHeight(this.m_applet);
                }
                this.setSize(this.m_nXSize, this.m_nYSize);
                this.m_strHint = this.m_applet.getParameter(strDefBut + "_HINT");
                this.m_strCursorType = this.m_applet.getParameter(strDefBut + "_CURSOR_TYPE");
                this.readBorder();
                this.readLocation();
                this.m_strCmd = this.m_applet.getParameter(strDefBut + "_INPUT");
                if (this.m_strCmd == null) {
                    System.err.println("Error - The parameter " + strDefBut + "_INPUT" + " is missing." + "This button will not be attached to any form paramter.");
                }
                this.m_strNameParam = this.m_applet.getParameter(strDefBut + "_NAME");
                if (this.m_strNameParam == null) {
                    System.err.println("Warning - The parameter " + strDefBut + "_NAME" + " is missing. " + "This button will not be attached to any form paramter.");
                }
                this.m_strValueParam = this.m_applet.getParameter(strDefBut + "_VALUE");
                if (this.m_strValueParam == null) {
                    System.err.println("The parameter " + strDefBut + "_VALUE" + " is missing." + " No operation available.");
                }
                this.m_strTarget = this.m_applet.getParameter(strDefBut + "_TARGET");
                this.m_strJSAction = this.m_applet.getParameter(strDefBut + "_JSACTION");
            }
            else {
                System.err.println("Error, cannot initialize the current button. The name is missing.");
                System.exit(1);
            }
        }
        catch (Exception e) {
            System.err.println("Error loading image.");
        }
    }
    
    protected void readBorder() {
        final String strDefBut = "TB_BUT_" + this.m_strName;
        final String strColorName = this.m_applet.getParameter(strDefBut + "_BORDER");
        if (strColorName != null) {
            try {
                this.m_borderColor = Integer.valueOf(strColorName, 10);
                if (Rosa2000.isDebugMode()) {
                    System.out.println(strDefBut + "_BORDER" + " = " + strColorName);
                }
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid value in the parameter " + strDefBut + "_BORDER" + ". The value \n" + "will be set to " + this.m_borderColor);
            }
        }
    }
    
    protected void readLocation() {
        if (Rosa2000.isDebugMode()) {
            System.out.println("ButtonCtrl::ReadLocation");
        }
        final String strLocation = this.m_applet.getParameter("TB_BUT_" + this.m_strName + "_XY");
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
                this.m_bIsAutoPlace = false;
                this.setLocation(nX, nY);
            }
            else {
                System.err.println("Invalid coordinate parameter: " + strLocation + " for the button " + "TB_BUT_" + this.m_strName + "_XY");
                this.m_bIsAutoPlace = true;
            }
            if (Rosa2000.isDebugMode()) {
                System.out.println("ButtonCtrl::ReadLocation = " + nX + " " + nY);
            }
        }
    }
    
    public Point getHintPosition() {
        final Point oPos = new Point(0, 0);
        if (((ToolbarPanel)this.getParent()).IsVertical()) {
            if (((ToolbarPanel)this.getParent()).IsLeft()) {
                oPos.x = this.getSize().width + 3;
                oPos.y = this.getParent().getLocation().y + this.getLocation().y + this.getSize().height / 4;
            }
            else {
                oPos.x = this.getParent().getLocation().x - this.m_ToolTip.getSize().width - 3;
                oPos.y = this.getParent().getLocation().y + this.getLocation().y + this.getSize().height / 4;
            }
        }
        else if (((ToolbarPanel)this.getParent()).IsTop()) {
            oPos.x = this.getParent().getLocation().x + this.getLocation().x + this.getSize().width / 4;
            if (oPos.x + this.m_ToolTip.getSize().width > this.m_applet.getBounds().width) {
                oPos.x = this.m_applet.getBounds().width - this.m_ToolTip.getSize().width;
            }
            oPos.y = this.getHeight() + 3;
        }
        else {
            oPos.x = this.getParent().getLocation().x + this.getLocation().x + this.getSize().width / 4;
            if (oPos.x + this.m_ToolTip.getSize().width > this.m_applet.getBounds().width) {
                oPos.x = this.m_applet.getBounds().width - this.m_ToolTip.getSize().width;
            }
            oPos.y = this.getParent().getLocation().y - this.m_ToolTip.getSize().height - 3;
        }
        return oPos;
    }
    
    public void setCursorType() {
        if (this.m_strCursorType != null) {
            if (this.m_strCursorType.compareTo(new String("CROSSHAIR")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(1));
            }
            else if (this.m_strCursorType.compareTo(new String("WAIT")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(3));
            }
            else if (this.m_strCursorType.compareTo(new String("HAND")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(12));
            }
            else if (this.m_strCursorType.compareTo(new String("MOVE")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(13));
            }
            else if (this.m_strCursorType.compareTo(new String("TEXT")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(2));
            }
            else if (this.m_strCursorType.compareTo(new String("E_RESIZE")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(11));
            }
            else if (this.m_strCursorType.compareTo(new String("N_RESIZE")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(8));
            }
            else if (this.m_strCursorType.compareTo(new String("NE_RESIZE")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(7));
            }
            else if (this.m_strCursorType.compareTo(new String("NW_RESIZE")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(6));
            }
            else if (this.m_strCursorType.compareTo(new String("S_RESIZE")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(9));
            }
            else if (this.m_strCursorType.compareTo(new String("SE_RESIZE")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(5));
            }
            else if (this.m_strCursorType.compareTo(new String("SW_RESIZE")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(4));
            }
            else if (this.m_strCursorType.compareTo(new String("W_RESIZE")) == 0) {
                this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(10));
            }
        }
        else {
            this.setCursorToDefault();
        }
    }
    
    public void setCursorToDefault() {
        this.m_applet.getMapObj().setCursor(Cursor.getPredefinedCursor(0));
    }
}
