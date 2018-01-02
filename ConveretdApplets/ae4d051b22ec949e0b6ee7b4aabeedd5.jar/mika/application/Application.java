// 
// Decompiled by Procyon v0.5.30
// 

package mika.application;

import java.awt.Graphics;
import mika.system.S_Debug;
import java.awt.Component;
import mika.graphics.G_ImageTools;
import mika.system.S_TextReader;
import java.net.URL;
import mika.util.U_Banner;
import java.applet.Applet;
import java.awt.Color;
import mika.system.S_Timer;
import java.awt.Panel;

public abstract class Application extends Panel
{
    int m_iX;
    int m_iY;
    int m_iWidth;
    int m_iHeight;
    int m_iBannerHeight;
    boolean m_bImgLoaded;
    float m_fLoaded;
    static int sm_iLoadBarX;
    static int sm_iLoadBarY;
    static int sm_iLoadBarWidth;
    static int sm_iLoadBarHeight;
    String m_strLoading;
    S_Timer m_timer;
    float m_fTimerDelta;
    public Color m_clrLoadingBackground;
    Color m_clrLoadingText;
    Color m_clrLoadingBar;
    static Applet sm_thisApplet;
    String m_strConfigFile;
    static String sm_strRefreshURL;
    static String sm_strRefreshFrame;
    static String sm_strParameterWidth;
    static String sm_strParameterHeight;
    static String sm_strParameterConfigFile;
    static String sm_strParameterRefreshURL;
    static String sm_strParameterRefreshFrame;
    U_Banner m_Banner;
    static int sm_iProgress;
    static int sm_iMaxProgress;
    static Panel sm_thisPanel;
    
    public Application() {
        this.m_bImgLoaded = false;
        this.m_strLoading = "";
        this.m_timer = new S_Timer();
        this.m_fTimerDelta = 0.013f;
        this.m_clrLoadingBackground = Color.black;
        this.m_clrLoadingText = Color.gray;
        this.m_clrLoadingBar = Color.red;
        this.m_strConfigFile = "";
        Application.sm_iMaxProgress = 0;
    }
    
    public boolean isLoadingDone() {
        return this.m_bImgLoaded;
    }
    
    public static final void setApplet(final Applet sm_thisApplet) {
        Application.sm_thisApplet = sm_thisApplet;
    }
    
    public static final Applet getApplet() {
        return Application.sm_thisApplet;
    }
    
    public void readParameters() {
        final String parameter = Application.sm_thisApplet.getParameter(Application.sm_strParameterWidth);
        if (parameter != null) {
            this.m_iWidth = Integer.valueOf(parameter);
        }
        final String parameter2 = Application.sm_thisApplet.getParameter(Application.sm_strParameterHeight);
        if (parameter2 != null) {
            this.m_iHeight = Integer.valueOf(parameter2);
        }
        final String parameter3 = Application.sm_thisApplet.getParameter(Application.sm_strParameterConfigFile);
        if (parameter3 != null) {
            this.m_strConfigFile = parameter3;
        }
        else {
            System.out.println("Forgot to specify configuration file?");
        }
        final String parameter4 = Application.sm_thisApplet.getParameter(Application.sm_strParameterRefreshURL);
        if (parameter4 != null) {
            Application.sm_strRefreshURL = parameter4;
        }
        final String parameter5 = Application.sm_thisApplet.getParameter(Application.sm_strParameterRefreshFrame);
        if (parameter5 != null) {
            Application.sm_strRefreshFrame = parameter5;
        }
    }
    
    public static final void refreshBanner() {
        try {
            if (Application.sm_strRefreshURL != null && Application.sm_strRefreshFrame != null) {
                getApplet().getAppletContext().showDocument(new URL(Application.sm_strRefreshURL), Application.sm_strRefreshFrame);
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void setOffset(final int ix, final int iy) {
        this.m_iX = ix;
        this.m_iY = iy;
    }
    
    public int getWidth() {
        return this.m_iWidth;
    }
    
    public int getHeight() {
        return this.m_iHeight;
    }
    
    public String getConfigurationFile() {
        return this.m_strConfigFile;
    }
    
    public void reshape() {
        System.out.println("reshape()");
        this.repositionElements();
    }
    
    public void readExtendedLoadingBar(final S_TextReader s_TextReader) {
        try {
            this.m_clrLoadingBackground = new Color(s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            this.m_clrLoadingText = new Color(s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            this.m_clrLoadingBar = new Color(s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue(), s_TextReader.readIntegerValue());
            Application.sm_iLoadBarX = s_TextReader.readIntegerValue();
            Application.sm_iLoadBarY = s_TextReader.readIntegerValue();
            Application.sm_iLoadBarWidth = s_TextReader.readIntegerValue();
            Application.sm_iLoadBarHeight = s_TextReader.readIntegerValue();
            this.m_strLoading = s_TextReader.readStringValue().replace('_', ' ');
            System.out.println(" Loading bar: position: " + Application.sm_iLoadBarX + ", " + Application.sm_iLoadBarY + "; size: " + Application.sm_iLoadBarWidth + "x" + Application.sm_iLoadBarHeight + "; text: " + this.m_strLoading);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void readLoadingBar(final S_TextReader s_TextReader) {
        try {
            Application.sm_iLoadBarX = s_TextReader.readIntegerValue();
            Application.sm_iLoadBarY = s_TextReader.readIntegerValue();
            Application.sm_iLoadBarWidth = s_TextReader.readIntegerValue();
            Application.sm_iLoadBarHeight = s_TextReader.readIntegerValue();
            this.m_strLoading = s_TextReader.readStringValue().replace('_', ' ');
            System.out.println(" Loading bar: position: " + Application.sm_iLoadBarX + ", " + Application.sm_iLoadBarY + "; size: " + Application.sm_iLoadBarWidth + "x" + Application.sm_iLoadBarHeight + "; text: " + this.m_strLoading);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int getBannerHeight() {
        return this.m_iBannerHeight;
    }
    
    public void setBannerHeight(final int iBannerHeight) {
        this.m_iBannerHeight = iBannerHeight;
    }
    
    public void readBanner(final S_TextReader s_TextReader) {
        try {
            (this.m_Banner = new U_Banner()).setApplet(getApplet());
            final String replace = s_TextReader.readStringValue().replace('_', ' ');
            final String replace2 = s_TextReader.readStringValue().replace('_', ' ');
            System.out.println("Banner link: " + replace);
            System.out.println("Link description: " + replace2);
            this.m_Banner.setLink(replace, replace2);
            for (int integerValue = s_TextReader.readIntegerValue(), i = 0; i < integerValue; ++i) {
                final String stringValue = s_TextReader.readStringValue();
                this.m_Banner.addImage(G_ImageTools.loadImage(this, new URL(getApplet().getCodeBase(), stringValue), stringValue), s_TextReader.readFloatValue());
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void destroy() {
        System.out.println("destroy()");
        try {
            for (int i = 0; i < this.getModeCount(); ++i) {
                this.getMode(i).destroy();
            }
            if (this.m_Banner != null) {
                this.m_Banner.stop();
                this.m_Banner = null;
            }
            S_Debug.closeWindow();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void createElements() {
        System.out.println("createElements()");
        try {
            for (int i = 0; i < this.getModeCount(); ++i) {
                this.add(this.getMode(i));
                this.getMode(i).show();
                this.getMode(i).hide();
                this.getMode(i).init();
            }
            if (this.m_Banner != null) {
                this.add(this.m_Banner);
                this.m_Banner.show();
                this.m_Banner.init();
            }
            this.repositionElements();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void repositionElements() {
        System.out.println("repositionElements()");
        try {
            if (this.getParent() != null && this.getParent().size().width > this.m_iWidth) {
                final int n = (this.getParent().size().width - this.m_iWidth) / 2;
                final int n2 = (this.getParent().size().height - this.m_iHeight) / 2;
                System.out.println("reposition to parent " + n + "  " + n2 + ";  " + this.m_iWidth + "  " + this.m_iHeight);
                this.reshape(n, n2, this.m_iWidth, this.m_iHeight);
            }
            else {
                this.reshape(this.m_iX, this.m_iY, this.m_iWidth, this.m_iHeight);
                System.out.println("panel " + this.m_iX + "  " + this.m_iY + ";  " + this.m_iWidth + "  " + this.m_iHeight);
            }
            for (int i = 0; i < this.getModeCount(); ++i) {
                final Mode mode = this.getMode(i);
                if (mode != null && mode.isInitDone()) {
                    mode.repositionElements();
                }
            }
            if (this.m_Banner != null) {
                this.m_Banner.reshape(0, 0, this.m_iWidth, this.getBannerHeight());
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        this.paintBackground(graphics);
    }
    
    public void paintBackground(final Graphics graphics) {
        if (!this.m_bImgLoaded) {
            graphics.setColor(this.m_clrLoadingBackground);
            graphics.fillRect(0, 0, this.m_iWidth, this.m_iHeight);
            graphics.setColor(this.m_clrLoadingText);
            graphics.fillRect(Application.sm_iLoadBarX - 1, Application.sm_iLoadBarY - 1, Application.sm_iLoadBarWidth + 1, Application.sm_iLoadBarHeight + 1);
            graphics.drawString(this.m_strLoading, Application.sm_iLoadBarX, Application.sm_iLoadBarY - 2);
            this.m_fLoaded = Application.sm_iProgress / Application.sm_iMaxProgress;
            graphics.setColor(this.m_clrLoadingBar);
            graphics.fillRect(Application.sm_iLoadBarX + 1, Application.sm_iLoadBarY + 1, (int)(Application.sm_iLoadBarWidth * this.m_fLoaded - 2.0f), Application.sm_iLoadBarHeight - 2);
            this.notifyAll();
        }
        else {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.m_iWidth, this.m_iHeight);
        }
    }
    
    public static void setMaxProgress(final int sm_iMaxProgress) {
        Application.sm_iMaxProgress = sm_iMaxProgress;
    }
    
    public static void progress() {
        ++Application.sm_iProgress;
        Application.sm_thisPanel.repaint(Application.sm_iLoadBarX - 1, Application.sm_iLoadBarY - 1, Application.sm_iLoadBarWidth + 1, Application.sm_iLoadBarHeight + 1);
    }
    
    public void prepareRunApplication() throws Exception {
        System.out.println("prepareRunApplication()");
        try {
            this.m_bImgLoaded = false;
            Application.sm_iProgress = 0;
            this.reshape(this.m_iX, this.m_iY, this.m_iWidth, this.m_iHeight);
            ((Application)(Application.sm_thisPanel = this)).load(getApplet().getCodeBase());
            this.createElements();
            if (this.m_Banner != null) {
                this.m_Banner.start();
            }
            this.m_bImgLoaded = true;
            this.repaint();
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public abstract void runApplication();
    
    public void requestFocus() {
        if (this.getActiveMode() != null) {
            this.getActiveMode().requestFocus();
        }
    }
    
    public abstract void load(final URL p0) throws Exception;
    
    public abstract int getModeCount();
    
    public abstract Mode getMode(final int p0);
    
    public abstract Mode getActiveMode();
    
    public abstract String getInformation();
    
    public abstract String getName();
    
    static {
        Application.sm_strRefreshURL = null;
        Application.sm_strRefreshFrame = null;
        Application.sm_strParameterWidth = "width";
        Application.sm_strParameterHeight = "height";
        Application.sm_strParameterConfigFile = "configFile";
        Application.sm_strParameterRefreshURL = "refreshURL";
        Application.sm_strParameterRefreshFrame = "refreshFrame";
    }
}
