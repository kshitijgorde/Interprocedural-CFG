// 
// Decompiled by Procyon v0.5.30
// 

package microtex.TeletextOnWeb;

import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.MediaTracker;
import java.applet.Applet;

public class teletext extends Applet
{
    public final int RCSTR_CLEAN = 0;
    public final int RCSTR_PAGENOTFOUND = 1;
    public final int RCSTR_LOADINGPAGE = 2;
    public final int RCSTR_CTRLBACK = 3;
    public final int RCSTR_CTRLFORW = 4;
    public final int RCSTR_CTRLGO = 5;
    public final int RCSTR_CTRLPAGEPREV = 6;
    public final int RCSTR_CTRLPAGENEXT = 7;
    public final int RCSTR_CTRLROLLPREV = 8;
    public final int RCSTR_CTRLROLLNEXT = 9;
    public final int RCSTR_CTRLFONT = 10;
    public final int RCSTR_CTRLNOFONT = 11;
    public final String[] m_strUserMessages;
    public String m_nFontSize;
    public String m_strWelcomeStr;
    public String m_strStartPageStr;
    public String m_strDataPathStr;
    public String m_strIndexStr;
    private final String PARAM_FontSize = "FontSize";
    private final String PARAM_WelcomeStr = "WelcomeStr";
    private final String PARAM_StartPageStr = "StartingPage";
    private final String PARAM_DataPathStr = "PagesPath";
    private final String PARAM_IndexStr = "Index";
    public ControlPanel m_wndControl;
    public TextPanel m_pnlText;
    public boolean m_AllLoadedCtrl;
    public boolean m_EndPresentazione;
    public MediaTracker m_tracker;
    
    public String getAppletInfo() {
        return "Name: Teletext on Web 1.2\n" + "Authors:\n" + "         Marco Cortese  mcortese@hotmail.com  http://digilander.iol.it/mcortese\n" + "         Federico Munerotto  mune@ieee.org  http://max.dei.unipd.it/~mune\n" + "Copyright:\n" + "         (c)1998-1999 MicroTex \n" + "         via Orsato Marghera VE Italy\n" + "         http://www.microtex.it\n" + "";
    }
    
    public String[][] getParameterInfo() {
        final String[][] array = new String[5][];
        array[0] = new String[] { "FontSize", "String", "[0:2] The font size." };
        array[1] = new String[] { "WelcomeStr", "String", "Splash window's welcome message." };
        array[2] = new String[] { "StartingPage", "String", "Starting page number." };
        array[3] = new String[] { "PagesPath", "String", "Pages path." };
        array[4] = new String[] { "Index", "String", "Page index file name." };
        final String[][] info = array;
        return info;
    }
    
    public void BuildCostraints(final GridBagConstraints gbc, final int gx, final int gy, final int gw, final int gh, final int wx, final int wy) {
        gbc.gridx = gx;
        gbc.gridy = gy;
        gbc.gridwidth = gw;
        gbc.gridheight = gh;
        gbc.weightx = wx;
        gbc.weighty = wy;
    }
    
    public void init() {
        String param = this.getParameter("FontSize");
        if (param != null) {
            this.m_nFontSize = param;
        }
        param = this.getParameter("WelcomeStr");
        if (param != null) {
            this.m_strWelcomeStr = param;
        }
        param = this.getParameter("StartingPage");
        if (param != null) {
            this.m_strStartPageStr = param;
        }
        param = this.getParameter("PagesPath");
        if (param != null) {
            this.m_strDataPathStr = param;
        }
        param = this.getParameter("Index");
        if (param != null) {
            this.m_strIndexStr = param;
        }
        final FlowLayout layout = new FlowLayout(0, 0, 0);
        this.setLayout(layout);
        this.m_wndControl = new ControlPanel(this);
        this.m_pnlText = new TextPanel(this);
        this.add(this.m_wndControl);
        this.add(this.m_pnlText);
        this.m_pnlText.setWelcomeString(this.m_strWelcomeStr);
        this.m_pnlText.setFontSize(Integer.parseInt(this.m_nFontSize));
        this.m_wndControl.setPreferredSize(new Dimension(this.size().width - this.m_pnlText.getTVWidth(), this.m_pnlText.getTVHeight()));
        this.m_pnlText.setPreferredSize(new Dimension(this.m_pnlText.getTVWidth(), this.m_pnlText.getTVHeight()));
        this.m_tracker = new MediaTracker(this);
        for (int i = 0; i < this.m_wndControl.m_imgBgListText.length; ++i) {
            this.m_wndControl.m_imgBgListText[i] = this.getImage(this.getCodeBase(), "images/sfondo" + i + ".gif");
            this.m_tracker.addImage(this.m_wndControl.m_imgBgListText[i], i);
        }
        for (int i = 0; i < this.m_wndControl.m_imgNext.length; ++i) {
            this.m_wndControl.m_imgNext[i] = this.getImage(this.getCodeBase(), "images/plus" + i + ".gif");
            this.m_wndControl.m_imgPrev[i] = this.getImage(this.getCodeBase(), "images/minus" + i + ".gif");
            this.m_tracker.addImage(this.m_wndControl.m_imgNext[i], 2 + i);
            this.m_tracker.addImage(this.m_wndControl.m_imgPrev[i], 6 + i);
        }
        this.m_wndControl.setBackground(new Color(115, 115, 123));
        this.m_pnlText.setBackground(Color.black);
        this.m_pnlText.init();
        this.m_wndControl.init();
        layout.layoutContainer(this);
        this.validate();
        System.out.println(this.getAppletInfo());
    }
    
    public void destroy() {
        this.m_pnlText.cleanUp();
        System.gc();
    }
    
    public void paint(final Graphics g) {
    }
    
    public void start() {
    }
    
    public void stop() {
        this.m_wndControl.resetScroll();
    }
    
    public String pageURL(final String page, final String rolling) {
        final String st = this.getCodeBase() + this.m_strDataPathStr + "/" + page.charAt(0) + "/" + page.charAt(1) + "/" + page + "-" + rolling + ".cti";
        return st;
    }
    
    public String ghostURL(final String page, final String rolling) {
        final String st = this.getCodeBase() + this.m_strDataPathStr + "/" + page.charAt(0) + "/" + page.charAt(1) + "/" + page + ".ght";
        return st;
    }
    
    public void pageRequest(final String page, String rolling) {
        final StringBuffer data = new StringBuffer();
        URL hp = null;
        URLConnection hpCon = null;
        if (Integer.parseInt(rolling) < 10) {
            rolling = "0" + rolling;
        }
        this.showMessage(2, page + "-" + rolling + ".");
        final String ghostURL = this.ghostURL(page, rolling);
        final byte[] rollingVec = new byte[100];
        int ofRoll = 1;
        if (rolling.compareTo("00") == 0) {
            try {
                hp = new URL(ghostURL);
            }
            catch (MalformedURLException e3) {
                System.out.println(ghostURL);
            }
            try {
                hpCon = hp.openConnection();
                final int len = hpCon.getContentLength();
                if (len > 1) {
                    final InputStream input = hpCon.getInputStream();
                    for (int i = len, j = 0, c = ofRoll = input.read(); (c = input.read()) != -1 && --i > 0 && j < 100; rollingVec[j++] = (byte)c) {}
                    input.close();
                }
                if (rollingVec[0] < 10) {
                    rolling = "0" + rollingVec[0];
                }
                else {
                    rolling = "" + rollingVec[0];
                }
                this.m_wndControl.pageChange(Integer.parseInt(page), Integer.parseInt(rolling), ofRoll, rollingVec);
            }
            catch (IOException e) {
                System.out.println("IO Error: " + e.getMessage());
            }
        }
        else {
            ofRoll = this.getRollingOf();
        }
        final String pageURL = this.pageURL(page, rolling);
        try {
            hp = new URL(pageURL);
        }
        catch (MalformedURLException e4) {
            System.out.println(pageURL);
        }
        try {
            hpCon = hp.openConnection();
            final int len2 = hpCon.getContentLength();
            if (len2 > 3) {
                final InputStream input2 = hpCon.getInputStream();
                int k = len2;
                int c;
                while ((c = input2.read()) != -1 && --k > 0) {
                    data.append((char)c);
                }
                input2.close();
                this.m_pnlText.setTextData(Integer.parseInt(page), Integer.parseInt(rolling), ofRoll, data.toString());
                this.m_pnlText.repaint();
            }
            else {
                this.showMessage(1, page + "-" + rolling + ".");
            }
        }
        catch (IOException e2) {
            System.out.println("IO Error: " + e2.getMessage());
        }
        this.m_wndControl.setControls(Integer.parseInt(page), Integer.parseInt(rolling), ofRoll, rollingVec);
    }
    
    public void showMessage(final int nMesg, final String strAppend) {
        this.showStatus(this.m_strUserMessages[nMesg] + " " + strAppend);
    }
    
    public void nextRolling() {
        this.pageRequest("" + this.m_pnlText.getPage(), "" + (this.m_pnlText.getRolling() + 1));
    }
    
    public void prevRolling() {
        this.pageRequest("" + this.m_pnlText.getPage(), "" + (this.m_pnlText.getRolling() - 1));
    }
    
    public int getPage() {
        return this.m_pnlText.getPage();
    }
    
    public int getRolling() {
        return this.m_pnlText.getRolling();
    }
    
    public int getRollingOf() {
        return this.m_pnlText.getRollingOf();
    }
    
    public teletext() {
        this.m_strUserMessages = new String[] { " ", "Pagina inesistente:", "Caricamento pagina:", "Pagina memorizzata precedente.", "Pagina memorizzata successiva.", "Carica la pagina selezionata.", "Pagina precedente.", "Pagina successiva.", "Sottopagina precedente.", "Sottopagina successiva.", "Imposta dimensioni.", "Dimensione non applicabile." };
        this.m_nFontSize = "0";
        this.m_strWelcomeStr = "Welcome on Teletext";
        this.m_strStartPageStr = "100";
        this.m_strDataPathStr = "data";
        this.m_strIndexStr = "TeletextOnWeb.ini";
        this.m_AllLoadedCtrl = false;
        this.m_EndPresentazione = false;
    }
}
