import java.io.InputStream;
import netscape.javascript.JSObject;
import java.util.StringTokenizer;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.Frame;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Rosa2000 extends Applet
{
    private final String kstrTbSelectedButton = "TB_SELECTED_BUTTON";
    private final String kstrTbPositionName = "TB_POSITION";
    private final String kstrTbLocationName = "TB_LOCATION";
    private final String kstrTbAlignName = "TB_ALIGN";
    private final String kstrImgListName = "IMG_URL";
    private final String kstrXScaleName = "X_SCALE";
    private final String kstrYcaleName = "Y_SCALE";
    private final String kstrBkColorName = "BG_COLOR";
    private final String kstrButtonDefName = "TB_BUTTONS";
    private final String kstrDebugModeName = "DEBUG";
    private final String kstrFormParam = "INP_FORM_NAME";
    private final String kstrLoadingMsg = "LOADING_MSG";
    private final String kstrDefaultLoadingMsg;
    public int m_gBufferSizeForStream;
    public static boolean m_bIsDebug;
    private int m_nWidthOfLoadingWnd;
    private int m_nHeightOfLoadingWnd;
    ToolbarPanel m_toolbar;
    Map m_map;
    private String m_strLoadingString;
    boolean m_bLoad;
    Frame m_Frame;
    Label m_status;
    
    public Rosa2000() {
        this.kstrDefaultLoadingMsg = null;
        this.m_gBufferSizeForStream = 5000;
        this.m_nWidthOfLoadingWnd = 400;
        this.m_nHeightOfLoadingWnd = 100;
        this.m_toolbar = null;
        this.m_map = null;
        this.m_strLoadingString = null;
        this.m_bLoad = false;
        this.m_Frame = null;
        this.m_status = null;
    }
    
    public void init() {
        this.readDebugParam();
        this.m_strLoadingString = this.getParameter("LOADING_MSG");
        if (this.m_strLoadingString == null) {
            this.m_strLoadingString = "Loading map... Please wait.";
        }
        this.setLayout(null);
        this.loadBackground();
        this.setVisible(true);
        this.paint(this.getGraphics());
        this.setLoadingMsg("Loading Map...");
        this.loadMap(null);
    }
    
    public static boolean isDebugMode() {
        return Rosa2000.m_bIsDebug;
    }
    
    public ToolbarPanel getTB() {
        return this.m_toolbar;
    }
    
    public Map getMapObj() {
        return this.m_map;
    }
    
    public void stop() {
        this.m_map.destroyCommand();
        this.m_toolbar.setButtonPress(null);
        System.out.println("stop");
    }
    
    public void start() {
        this.setSelectedButton();
        System.out.println("start");
    }
    
    protected void loadBackground() {
        String strColorName = this.getParameter("BG_COLOR");
        int nColor = 16777215;
        if (strColorName != null) {
            try {
                if (strColorName.startsWith("#")) {
                    strColorName = strColorName.substring(1);
                }
                nColor = Integer.valueOf(strColorName, 16);
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid background color: " + strColorName);
                return;
            }
        }
        this.setBackground(new Color(nColor));
    }
    
    protected void readDebugParam() {
        final String strDebug = this.getParameter("DEBUG");
        if (strDebug != null) {
            try {
                Rosa2000.m_bIsDebug = (Integer.valueOf(strDebug, 10) == 1);
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid debug info: " + strDebug);
            }
        }
    }
    
    public void loadMap(final String strMap) {
        String strCurrentButtonName = null;
        if (this.m_toolbar != null && this.m_toolbar.getCurrentButton() != null) {
            strCurrentButtonName = this.m_toolbar.getCurrentButton().getButtonName();
        }
        this.m_bLoad = false;
        if (this.m_map != null) {
            this.remove(this.m_map);
        }
        this.m_map = null;
        if (this.m_toolbar != null) {
            this.remove(this.m_toolbar);
        }
        this.m_toolbar = null;
        this.paint(this.getGraphics());
        this.loadImage(strMap);
        this.setLoadingMsg("Loading toolbar...");
        this.readToolbarParam();
        this.add(this.m_map);
        this.add(this.m_toolbar);
        this.m_toolbar.automaticPlace();
        this.m_bLoad = true;
        this.destroyLoadingMsg();
        this.repaint();
        this.m_map.setVisible(true);
        this.m_toolbar.setVisible(true);
        if (strCurrentButtonName != null && this.m_toolbar != null) {
            this.setButtonPress(strCurrentButtonName);
        }
    }
    
    protected void loadImage(final String strMap) {
        if (isDebugMode()) {
            System.out.println("loadimage = " + strMap);
        }
        String strMapName = null;
        this.m_bLoad = false;
        if (strMap == null) {
            strMapName = this.getParameter("IMG_URL");
        }
        else {
            strMapName = strMap;
        }
        if (strMap != null) {
            this.repaint();
        }
        try {
            Image mapImg = null;
            URL url = null;
            try {
                url = new URL(strMapName);
                mapImg = this.getImage(url);
            }
            catch (MalformedURLException e) {
                url = this.getDocumentBase();
                mapImg = this.getImage(url, strMapName);
            }
            final MediaTracker theTracker = new MediaTracker(this);
            theTracker.addImage(mapImg, 0);
            theTracker.waitForAll();
            if (theTracker.isErrorAny()) {
                System.err.println("Cannot load the map " + strMapName + " URL Not found.");
            }
            if (this.m_map != null) {
                this.remove(this.m_map);
            }
            this.m_map = new Map(this, mapImg, mapImg.getWidth(this), mapImg.getHeight(this));
        }
        catch (Exception e2) {
            System.err.println("Error loading image.");
            System.exit(1);
        }
    }
    
    protected void readToolbarParam() {
        String strToolbarPosition = this.getParameter("TB_POSITION");
        if (strToolbarPosition == null) {
            strToolbarPosition = "right";
        }
        final String strToolbarAlignment = this.getParameter("TB_ALIGN");
        this.m_toolbar = new ToolbarPanel(this, this.m_map, strToolbarPosition, strToolbarAlignment);
        this.readButton();
    }
    
    protected int readButton() {
        int nRetButtonValue = 0;
        if (this.m_toolbar != null) {
            final String strButtonList = this.getParameter("TB_BUTTONS");
            String strButton = new String();
            if (strButtonList != null) {
                int nIndex = 0;
                int nPreviousIndex = 0;
                while ((nIndex = strButtonList.indexOf("|", nIndex++)) != -1) {
                    strButton = strButtonList.substring(nPreviousIndex, nIndex);
                    ++nRetButtonValue;
                    nPreviousIndex = ++nIndex;
                    this.m_toolbar.createButton(strButton);
                }
                if (nPreviousIndex < strButtonList.length() - 1) {
                    strButton = strButtonList.substring(nPreviousIndex);
                    ++nRetButtonValue;
                    this.m_toolbar.createButton(strButton);
                }
            }
        }
        return nRetButtonValue;
    }
    
    protected void setSelectedButton() {
        final String strDefaultButton = this.getParameter("TB_SELECTED_BUTTON");
        if (strDefaultButton != null && this.m_toolbar != null) {
            final ButtonCtrl defBut = this.m_toolbar.getButton(strDefaultButton);
            if (defBut != null) {
                defBut.setPressed(true);
                defBut.setCursorType();
                this.m_map.startAction(defBut, true);
            }
        }
    }
    
    public void showText(final String strText) {
        this.showStatus(strText);
    }
    
    public void paint(final Graphics g) {
        if (!this.m_bLoad && this.m_strLoadingString != null) {
            final Dimension appletDim = this.getSize();
            final int nX = (appletDim.width - g.getFontMetrics().stringWidth(this.m_strLoadingString)) / 2;
            g.setColor(new Color(255, 255, 255));
            g.fillRect(nX - 2, appletDim.height / 2 - g.getFontMetrics().getHeight(), g.getFontMetrics().stringWidth(this.m_strLoadingString) + 2, g.getFontMetrics().getHeight() + 4);
            g.setColor(new Color(0, 0, 0));
            g.drawString(this.m_strLoadingString, nX, appletDim.height / 2);
        }
        else {
            super.paint(g);
        }
    }
    
    void showLoadingMessage() {
        if (this.m_Frame == null && this.m_strLoadingString != null) {
            (this.m_Frame = new Frame()).setLayout(null);
            this.m_Frame.setSize(this.m_nWidthOfLoadingWnd, this.m_nHeightOfLoadingWnd);
            this.m_Frame.setTitle(this.m_strLoadingString);
            center(this.m_Frame);
            this.m_Frame.toFront();
            (this.m_status = new Label("")).setSize(this.m_nWidthOfLoadingWnd, this.m_nHeightOfLoadingWnd);
            this.m_status.setAlignment(1);
            this.m_Frame.add(this.m_status);
            this.m_Frame.setResizable(false);
            this.m_status.setForeground(Color.blue);
            this.setLoadingMsg("");
            this.m_Frame.setVisible(true);
        }
    }
    
    protected void setLoadingMsg(final String strMsg) {
        if (this.m_status != null) {
            this.m_status.setText(strMsg);
        }
    }
    
    protected void destroyLoadingMsg() {
        if (this.m_Frame != null) {
            this.m_Frame.setVisible(false);
            this.m_Frame.dispose();
            this.m_Frame = null;
            this.m_status = null;
        }
    }
    
    public static void center(final Window w) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension frameSize = w.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        w.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }
    
    public void SetMapInformations(final String strURL) {
        System.out.println("SetMapInformations " + strURL);
        final Object oObject = null;
        InputStream oStream = null;
        String retval = "";
        if (strURL != null) {
            try {
                URL url = null;
                final URL urlbase = this.getDocumentBase();
                try {
                    url = new URL(urlbase.getProtocol(), urlbase.getHost(), urlbase.getPort(), strURL);
                }
                catch (Exception e) {
                    System.err.println("Error in SetMapInformations : url could not be created : " + url.toString());
                    System.exit(1);
                }
                oStream = url.openStream();
            }
            catch (Exception e2) {
                System.err.println("Error in SetMapInformations");
                System.exit(1);
            }
        }
        if (oStream != null) {
            try {
                int nbytes = 0;
                final int nSizeofBuffer = this.m_gBufferSizeForStream;
                final byte[] buf = new byte[nSizeofBuffer];
                while ((nbytes = oStream.read(buf, 0, nSizeofBuffer)) != -1) {
                    retval += new String(buf);
                }
                oStream.close();
            }
            catch (Exception e2) {
                System.err.println("Error in SetMapInformations read");
                System.exit(1);
            }
            if (isDebugMode()) {
                System.out.println("SetMapInformations : stream value " + retval);
            }
            final StringTokenizer st = new StringTokenizer(retval, "\n");
            final int i = 0;
            boolean bFormParm = false;
            boolean bJavaScript = false;
            while (st.hasMoreTokens()) {
                final String sToken = st.nextToken();
                if (isDebugMode()) {
                    System.out.println("Token = " + sToken);
                }
                if (sToken.equalsIgnoreCase(new String("FORMPARAMS"))) {
                    bFormParm = true;
                }
                else if (sToken.equalsIgnoreCase(new String("JAVASCRIPT"))) {
                    bJavaScript = true;
                }
                else if (sToken.equalsIgnoreCase(new String("END"))) {
                    bFormParm = false;
                    bJavaScript = false;
                }
                else {
                    if (sToken.regionMatches(true, 0, new String("MAPURL"), 0, 6)) {
                        final StringTokenizer stTmp = new StringTokenizer(sToken, "=");
                        if (stTmp.countTokens() == 2) {
                            stTmp.nextToken();
                            final String strNewURL = stTmp.nextToken();
                            this.loadMap(strNewURL);
                            continue;
                        }
                    }
                    if (bFormParm) {
                        String strParam = null;
                        String strValue = null;
                        final StringTokenizer stForm = new StringTokenizer(sToken, "=");
                        if (stForm.countTokens() == 2) {
                            strParam = stForm.nextToken();
                            strValue = stForm.nextToken();
                            this.setFormParameter(strParam, strValue);
                        }
                    }
                    if (!bJavaScript) {
                        continue;
                    }
                    JSObject.getWindow((Applet)this).eval(sToken);
                }
            }
        }
    }
    
    protected void setFormParameter(final String sParamName, final String sValue) {
        if (isDebugMode()) {
            System.out.println("Setform param :" + sParamName + "=" + sValue);
        }
        if (sParamName != null && sValue != null) {
            try {
                final JSObject window = JSObject.getWindow((Applet)this);
                final JSObject doc = (JSObject)window.getMember("document");
                if (doc != null) {
                    final String strFormName = this.getParameter("INP_FORM_NAME");
                    if (strFormName != null) {
                        final JSObject form = (JSObject)doc.getMember(strFormName);
                        if (form != null) {
                            final JSObject jsParam = (JSObject)form.getMember(sParamName);
                            if (jsParam != null) {
                                jsParam.setMember("value", (Object)sValue);
                            }
                            else {
                                System.out.println("param not found");
                            }
                        }
                    }
                }
            }
            catch (ClassCastException ex) {
                System.err.println("A parameter is not found");
            }
        }
    }
    
    public void setButtonPress(final String strButton) {
        final ButtonCtrl oButton = this.getButton(strButton);
        if (oButton != null && this.m_toolbar != null) {
            this.m_toolbar.setButtonPress(oButton);
            this.m_map.startAction(oButton, true);
        }
    }
    
    public ButtonCtrl getButton(final String strButton) {
        if (this.m_toolbar != null) {
            return this.m_toolbar.getButton(strButton);
        }
        return null;
    }
    
    static {
        Rosa2000.m_bIsDebug = false;
    }
}
