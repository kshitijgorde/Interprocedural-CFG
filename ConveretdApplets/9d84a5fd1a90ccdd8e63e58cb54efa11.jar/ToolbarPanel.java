import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class ToolbarPanel extends Panel
{
    public final String kstrTop = "top";
    public final String kstrBottom = "bottom";
    public final String kstrLeft = "left";
    public final String kstrRight = "right";
    protected final String kstrDefImageParam = "_IMG";
    protected final String kstrImagePressParam = "_IMG_PR";
    private final String kstrTopAling = "top";
    private final String kstrBottomAling = "bottom";
    private final String kstrMiddleAling = "middle";
    private final String kstrCenterAling = "center";
    private final String kstrLeftAling = "left";
    private final String kstrRightAling = "right";
    private int m_nNbrButton;
    private boolean m_bIsVertical;
    private String m_strAlign;
    private String m_strLocation;
    private Map m_buttonListener;
    private Rosa2000 m_parent;
    private Point m_ptLocation;
    
    ToolbarPanel(final Rosa2000 parent, final Map listener, final String strLocation, final String strAlignment) {
        this.m_nNbrButton = 0;
        this.m_bIsVertical = false;
        this.m_strAlign = null;
        this.m_strLocation = null;
        this.m_buttonListener = null;
        this.m_parent = null;
        this.setVisible(false);
        this.m_buttonListener = listener;
        this.m_parent = parent;
        this.m_ptLocation = new Point(0, 0);
        this.setLocation(strLocation);
        this.setAlignment(strAlignment);
        this.setLayout(null);
        if (Rosa2000.isDebugMode()) {
            System.out.println("ToolbarPanel Constructor param Location =  " + strLocation + "Alignment = " + strAlignment);
        }
    }
    
    public void setLocation(final String strLocation) {
        boolean bError = false;
        this.m_strLocation = strLocation;
        if (strLocation != null) {
            if (strLocation.equals("top") || strLocation.equals("bottom")) {
                this.m_bIsVertical = false;
            }
            else if (strLocation.equals("left") || strLocation.equals("right")) {
                this.m_bIsVertical = true;
            }
            else {
                bError = true;
            }
        }
        else {
            bError = true;
        }
        if (bError) {
            System.err.println("Invalid toolbar Location: " + strLocation);
            this.m_strLocation = "top";
        }
    }
    
    public String getAligment() {
        return this.m_strAlign;
    }
    
    public String getTbLocation() {
        return this.m_strLocation;
    }
    
    public void setButtonPress(final ButtonCtrl button) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            final ButtonCtrl buttonItem = (ButtonCtrl)this.getComponent(i);
            buttonItem.setPressed(buttonItem == button);
        }
    }
    
    public void setAlignment(final String strAlignment) {
        boolean bError = false;
        this.m_strAlign = strAlignment;
        if (this.m_bIsVertical) {
            if (strAlignment == null || (!strAlignment.equals("top") && !strAlignment.equals("bottom") && !strAlignment.equals("middle"))) {
                bError = true;
                this.m_strAlign = "top";
            }
        }
        else if (strAlignment == null || (!strAlignment.equals("right") && !strAlignment.equals("center") && !strAlignment.equals("left"))) {
            bError = true;
            this.m_strAlign = "left";
        }
        if (bError) {
            System.err.println("Invalid toolbar alignment " + strAlignment + ". The default value " + this.m_strAlign + " will be assing. ");
        }
    }
    
    public boolean createButton(final String strButtonName) {
        if (Rosa2000.isDebugMode()) {
            System.out.println("ToolbarPanel::createButton( " + strButtonName + " ) ");
        }
        if (strButtonName.startsWith("space_")) {
            final SpacingButton button = new SpacingButton(strButtonName, this.m_bIsVertical);
            this.addButton(button);
            return true;
        }
        final String strImageParam = ButtonCtrl.getPrefixButtonParam() + strButtonName;
        final String strImageDef = strImageParam + "_IMG";
        final String strImagePress = strImageParam + "_IMG_PR";
        final Image buttonDefImage = this.readButtonImage(strImageDef);
        final Image buttonPressImage = this.readButtonImage(strImagePress);
        if (buttonDefImage != null) {
            if (buttonPressImage == null) {
                final RectButton button2 = new RectButton(strButtonName, buttonDefImage, false, this.m_parent);
                this.addButton(button2);
            }
            else {
                final RoundButton button3 = new RoundButton(strButtonName, buttonDefImage, buttonPressImage, false, this.m_parent);
                this.addButton(button3);
            }
            return true;
        }
        return false;
    }
    
    private Image readButtonImage(final String strNameParam) {
        if (Rosa2000.isDebugMode()) {
            System.out.println("ToolbarPanel::readButtonImage( String strNameParam )");
        }
        final String strDefaultValue = this.m_parent.getParameter(strNameParam);
        if (strDefaultValue != null) {
            final MediaTracker tracker = new MediaTracker(this);
            final Image buttonImage = this.m_parent.getImage(this.m_parent.getDocumentBase(), strDefaultValue);
            tracker.addImage(buttonImage, 0);
            try {
                tracker.waitForID(0);
            }
            catch (Exception e) {
                System.err.println("Exception: " + e.getMessage());
                return null;
            }
            if (buttonImage == null) {
                System.err.println("ToolbarPanel::readButtonImage error reading image");
            }
            return buttonImage;
        }
        return null;
    }
    
    public void addButton(final ButtonCtrl button) {
        if (Rosa2000.isDebugMode()) {
            System.out.println("ButtonCtrl button");
        }
        if (button.isAutoPlacement()) {
            this.autoPlace(this.m_nNbrButton, button);
            ++this.m_nNbrButton;
            this.add(button);
            final Dimension tbSize = this.getCurrentSize();
            this.setSize(tbSize.width, tbSize.height);
        }
        else {
            this.m_parent.add(button);
        }
        button.addActionListener(this.m_buttonListener);
    }
    
    public Dimension getCurrentSize() {
        if (this.m_bIsVertical) {
            int nXMax = 0;
            int nYSize = 0;
            final Component[] aComponent = this.getComponents();
            for (int i = 0; i < this.getComponentCount(); ++i) {
                final Dimension dim = aComponent[i].getPreferredSize();
                if (dim.width > nXMax) {
                    nXMax = dim.width;
                }
                nYSize += dim.height;
            }
            return new Dimension(nXMax, nYSize);
        }
        int nYMax = 0;
        int nXSize = 0;
        final Component[] aComponent = this.getComponents();
        for (int i = 0; i < this.getComponentCount(); ++i) {
            final Dimension dim = aComponent[i].getPreferredSize();
            if (dim.height > nYMax) {
                nYMax = dim.width;
            }
            nXSize += dim.width;
        }
        return new Dimension(nXSize, nYMax);
    }
    
    protected ButtonCtrl getButton(final String strButtonName) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            if (this.getComponent(i).getClass().getSuperclass().getName().equals("ButtonCtrl")) {
                final ButtonCtrl currentButton = (ButtonCtrl)this.getComponent(i);
                if (currentButton.getButtonName() != null && currentButton.getButtonName().equals(strButtonName)) {
                    return currentButton;
                }
            }
        }
        return null;
    }
    
    public ButtonCtrl getCurrentButton() {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            final ButtonCtrl buttonItem = (ButtonCtrl)this.getComponent(i);
            if (buttonItem.isPressed()) {
                return buttonItem;
            }
        }
        return null;
    }
    
    protected void autoPlace(final int nIndex, final ButtonCtrl button) {
        if (Rosa2000.isDebugMode()) {
            System.out.println("autoPlace( " + nIndex + " )");
        }
        if (button.isAutoPlacement()) {
            ButtonCtrl prevButton = null;
            if (nIndex > 0 && nIndex <= this.getComponentCount()) {
                prevButton = (ButtonCtrl)this.getComponent(nIndex - 1);
            }
            Point ptCurLoc = new Point(0, 0);
            if (prevButton != null) {
                if (this.m_bIsVertical) {
                    final Point location;
                    ptCurLoc = (location = prevButton.getLocation());
                    location.y += prevButton.getPreferredSize().height;
                }
                else {
                    final Point location2;
                    ptCurLoc = (location2 = prevButton.getLocation());
                    location2.x += prevButton.getPreferredSize().width;
                }
            }
            if (Rosa2000.isDebugMode()) {
                System.out.println("Button location at: " + ptCurLoc.x + ", " + ptCurLoc.y);
            }
            button.setLocation(ptCurLoc);
        }
    }
    
    public void moveTB(final int nX, final int nY) {
        this.setLocation(this.m_ptLocation = new Point(nX, nY));
    }
    
    public void automaticPlace() {
        if (this.m_bIsVertical) {
            this.placeTbVertical();
        }
        else {
            this.placeTbHorizontal();
        }
    }
    
    protected void placeTbVertical() {
        if (Rosa2000.isDebugMode()) {
            System.out.println("ToolbarPanel::placeTbVertical()");
        }
        final Dimension mapDim = this.m_buttonListener.getSize();
        final Point mapLocation = this.m_buttonListener.getLocation();
        final Dimension tbDim = this.getSize();
        int nYPosition = mapLocation.y;
        if (this.m_strAlign.equals("middle")) {
            nYPosition += (mapDim.height - tbDim.height) / 2;
        }
        else if (this.m_strAlign.equals("bottom")) {
            nYPosition += mapDim.height - tbDim.height;
        }
        int nXPosition = 0;
        if (this.m_strLocation.equals("left")) {
            nXPosition = mapLocation.x - tbDim.width;
        }
        else {
            nXPosition = mapLocation.x + mapDim.width;
        }
        if (nXPosition < 0) {
            nXPosition = 0;
        }
        else if (nXPosition + tbDim.width > mapDim.width) {
            nXPosition = mapDim.width - tbDim.width;
        }
        this.setLocation(nXPosition, nYPosition);
    }
    
    protected void placeTbHorizontal() {
        if (Rosa2000.isDebugMode()) {
            System.out.println("ToolbarPanel::placeTbHorizontal()");
        }
        final Dimension mapDim = this.m_buttonListener.getSize();
        final Point mapLocation = this.m_buttonListener.getLocation();
        final Dimension tbDim = this.getSize();
        int nXPosition = mapLocation.x;
        if (this.m_strAlign.equals("center")) {
            nXPosition += (mapDim.width - tbDim.width) / 2;
        }
        else if (this.m_strAlign.equals("right")) {
            nXPosition += mapDim.width - tbDim.width;
        }
        int nYPosition = 0;
        if (this.m_strLocation.equals("top")) {
            nYPosition = mapLocation.y - tbDim.height;
        }
        else {
            nYPosition = mapLocation.y + mapDim.height;
        }
        if (nYPosition < 0) {
            nYPosition = 0;
        }
        else if (nYPosition + tbDim.height > mapDim.height) {
            nYPosition = mapDim.height - tbDim.height;
        }
        this.setLocation(nXPosition, nYPosition);
    }
    
    public boolean IsVertical() {
        return this.m_bIsVertical;
    }
    
    public boolean IsTop() {
        return this.m_strLocation.equals("top");
    }
    
    public boolean IsLeft() {
        return this.m_strLocation.equals("left");
    }
}
