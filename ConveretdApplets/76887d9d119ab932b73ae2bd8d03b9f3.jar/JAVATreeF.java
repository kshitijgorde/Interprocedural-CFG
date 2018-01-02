import java.io.InputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.awt.image.ImageObserver;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.NoSuchElementException;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.List;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Panel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JAVATreeF extends Applet implements Runnable
{
    int xSwitch;
    Thread thJAVATree;
    String sDefMO;
    String sCopyright;
    String[] aHref;
    String[] aFrame;
    String[] aHref2;
    String[] aFrame2;
    String[] aExpand;
    String[] aMO;
    String[] aKeyWords;
    String[] aSearch;
    String[] aNodeText;
    int[] iLevel;
    String sText;
    String sHref;
    String sFrame;
    String sHref2;
    String sFrame2;
    String sExpand;
    String sSearch;
    String sKeyWords;
    String sNodeText;
    int[] iColor;
    String sWebSearch;
    String sLevel;
    String sPic1;
    String sPic2;
    String sMO;
    int iItemCnt;
    int iLoading;
    Font fFont;
    boolean bExpand;
    boolean bTREE_AUTOEXPAND;
    String dblClick;
    Image imgBG;
    private boolean m_fAllLoaded;
    private boolean bCopyright;
    private boolean allLoaded;
    int intWidth;
    int intHeight;
    String sMOUSEOVERSOUND;
    String sMOUSECLICKSOUND;
    String sPasswordError;
    Image ImgClosed;
    Image ImgOpen;
    Image ImgdLeaf;
    Image ImgdClosed;
    Image ImgdOpen;
    int iSBWidth;
    int iYRec;
    String sLine;
    TreeNode oldNode;
    TreeNode newNode;
    TreeNode oldMouseOverNode;
    Graphics g1;
    int[] iColorBlack;
    int[] iColorWhite;
    int[] iColorRed;
    int[] iColorBlue;
    int[] iColorYellow;
    int[] iColorGreen;
    int[] iColorGray;
    int[] iColorLightGray;
    String[] sColorParam;
    String[] sNormalParam;
    String[] sImageParam;
    Panel searchWeb;
    TextField searchSWText;
    Button btnSWClose;
    Label lblSW;
    Checkbox chkNL;
    Checkbox chkAV;
    Checkbox chkYH;
    Checkbox chkLY;
    Checkbox chkEX;
    Checkbox chkHO;
    Panel PasswordPanel;
    TextField PasswordText;
    Button btnPWDClose;
    Label PasswordLbl;
    Panel searchPanel;
    List list1;
    List list2;
    TextField searchText;
    Button btnCloseHelp;
    Button btnHelp;
    Button btnClose;
    Button btnHelpClose;
    Panel pnlSearchHelp;
    Checkbox chkMatchCase;
    Checkbox chkWholeWord;
    Checkbox chkSiteSearch;
    Checkbox chkWebSearch;
    Label lblHelp;
    Label lblHelp1;
    Label lblHelp2;
    TreeView treeView1;
    public TreeNode[] TN;
    public TreeNode[] TN1;
    
    public void executeLink(final String sLink, final String sTarget) {
        try {
            this.getAppletContext().showDocument(new URL(this.getCodeBase(), sLink), sTarget);
        }
        catch (MalformedURLException ex) {}
    }
    
    void treeView1_MouseEnter(final Event event) {
    }
    
    void treeView1_KeyPress(final Event event) {
    }
    
    void treeView1_MouseMove(final Event event) {
        try {
            if (!this.sMOUSEOVERSOUND.equals("none")) {
                this.play(this.getCodeBase(), this.sMOUSEOVERSOUND);
            }
        }
        catch (Exception ex) {}
        for (int z = 0; z < this.treeView1.getCount(); ++z) {
            if (this.TN[z] == this.treeView1.getMouseOverNode()) {
                this.sMO = this.aMO[z];
                this.sHref = this.aHref[z];
                this.newNode = this.TN[z];
                break;
            }
        }
        this.sMO = this.sMO.trim();
        if (this.sMO.toLowerCase().equals("none") || this.sMO.toLowerCase().equals("")) {
            this.sMO = this.sHref.trim();
        }
        if (this.newNode != this.oldNode) {
            this.showStatus(this.sMO);
            this.oldNode = this.newNode;
        }
    }
    
    void PasswordText_EnterHit(final Event event) {
        String sLinkTmp = "";
        sLinkTmp = this.decrypt(this.sHref.substring(3, this.sHref.length()), this.PasswordText.getText());
        if (!sLinkTmp.equals("ERROR")) {
            sLinkTmp = sLinkTmp.substring(2, sLinkTmp.length() - 4);
            try {
                this.getAppletContext().showDocument(new URL(this.getCodeBase(), sLinkTmp), this.sFrame);
            }
            catch (MalformedURLException ex) {}
            if (!this.sHref2.toLowerCase().equals("none")) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase(), this.sHref2), this.sFrame2);
                }
                catch (MalformedURLException ex2) {}
            }
            this.PasswordPanel.hide();
            this.treeView1.show();
            return;
        }
        if (this.sPasswordError != null) {
            try {
                this.getAppletContext().showDocument(new URL(this.getCodeBase(), this.sPasswordError), this.sFrame);
            }
            catch (MalformedURLException ex3) {}
        }
        this.PasswordLbl.setText("Incorrect password");
        this.PasswordText.setText("");
    }
    
    void btnPWDClose_Clicked(final Event event) {
        this.treeView1.show();
        this.PasswordPanel.hide();
    }
    
    void searchText_EnterHit(final Event event) {
        if (this.searchText.getText().length() > 0) {
            String sA = "";
            String sTmp = "";
            this.list1.clear();
            this.list2.clear();
            this.list1.addItem("searching ...");
            boolean bFound = false;
            String sTemp = "";
            final Vector subStrings = new Vector(5);
            int iWords = 0;
            if (!this.chkMatchCase.getState()) {
                sA = this.searchText.getText().toLowerCase();
            }
            else {
                sA = this.searchText.getText();
            }
            if (this.chkWholeWord.getState()) {
                sA = " " + sA + " ";
            }
            final StringTokenizer st = new StringTokenizer(sA);
            iWords = st.countTokens();
            subStrings.ensureCapacity(st.countTokens());
            while (st.hasMoreTokens()) {
                subStrings.addElement(st.nextToken());
            }
            try {
                for (int z = 0; z < this.treeView1.getCount(); ++z) {
                    if (!this.chkMatchCase.getState()) {
                        sTmp = " " + this.aNodeText[z].toLowerCase() + " " + this.aMO[z].toLowerCase() + " " + this.aKeyWords[z].toLowerCase() + " ";
                    }
                    else {
                        sTmp = " " + this.aNodeText[z] + " " + this.aMO[z].toLowerCase() + " " + this.aKeyWords[z].toLowerCase() + " ";
                    }
                    sTmp = sTmp.replace(',', ' ');
                    if (!this.aHref[z].trim().toLowerCase().equals("none") && !this.aHref[z].trim().equals("")) {
                        sTemp = this.aHref[z] + "|" + this.aFrame[z] + "|" + this.aHref2[z] + "|" + this.aFrame2[z] + "|" + this.aMO[z];
                        if (iWords > 1 && (this.searchText.getText().indexOf(" AND ") != -1 || this.searchText.getText().indexOf(" and ") != -1)) {
                            if (this.matchAND(sTmp, subStrings)) {
                                if (!bFound) {
                                    this.list1.clear();
                                }
                                this.list1.addItem(this.aNodeText[z]);
                                this.list2.addItem(sTemp);
                                bFound = true;
                            }
                        }
                        else if (iWords > 1 && (this.searchText.getText().indexOf(" OR ") != -1 || this.searchText.getText().indexOf(" or ") != -1)) {
                            if (this.matchOR(sTmp, subStrings)) {
                                if (!bFound) {
                                    this.list1.clear();
                                }
                                this.list1.addItem(this.aNodeText[z]);
                                this.list2.addItem(sTemp);
                                bFound = true;
                            }
                        }
                        else if (sTmp.indexOf(sA) != -1) {
                            if (!bFound) {
                                this.list1.clear();
                            }
                            this.list1.addItem(this.aNodeText[z]);
                            this.list2.addItem(sTemp);
                            bFound = true;
                        }
                    }
                }
            }
            catch (Exception ex) {}
            if (!bFound) {
                this.list1.clear();
                this.list1.addItem("no match");
            }
        }
    }
    
    protected boolean matchAND(final String content, final Vector subStrs) {
        final Enumeration eTemp = subStrs.elements();
        while (eTemp.hasMoreElements()) {
            final String sTemp = eTemp.nextElement();
            if (!sTemp.equalsIgnoreCase("and") && content.indexOf(sTemp) == -1) {
                return false;
            }
        }
        return true;
    }
    
    protected boolean matchOR(final String content, final Vector subStrs) {
        final Enumeration eTemp = subStrs.elements();
        while (eTemp.hasMoreElements()) {
            final String sTemp = eTemp.nextElement();
            if (!sTemp.equalsIgnoreCase("or") && content.indexOf(sTemp) != -1) {
                return true;
            }
        }
        return false;
    }
    
    void list1_MouseDown(final Event event) {
        String sMO = "";
        String sTemp = "";
        sTemp = this.list2.getItem(this.list1.getSelectedIndex());
        final StringTokenizer parser = new StringTokenizer(sTemp, "|");
        try {
            sTemp = parser.nextToken();
            sTemp = parser.nextToken();
            sTemp = parser.nextToken();
            sTemp = parser.nextToken();
            sMO = parser.nextToken();
        }
        catch (NoSuchElementException ex) {}
        this.showStatus(sMO);
    }
    
    void list1_DblClicked(final Event event) {
        String sLink = "";
        sLink = this.list2.getItem(this.list1.getSelectedIndex());
        final StringTokenizer parser = new StringTokenizer(sLink, "|");
        this.sHref = "";
        this.sHref2 = "";
        this.sFrame = "";
        this.sFrame2 = "";
        try {
            this.sHref = parser.nextToken().trim();
            this.sFrame = parser.nextToken().trim();
            this.sHref2 = parser.nextToken().trim();
            this.sFrame2 = parser.nextToken().trim();
        }
        catch (NoSuchElementException ex) {}
        if (!this.sHref.toLowerCase().equals("none") && this.isPasswordOK(this.sHref, this.sFrame)) {
            try {
                this.getAppletContext().showDocument(new URL(this.getCodeBase(), this.sHref), this.sFrame);
            }
            catch (MalformedURLException ex2) {}
            if (!this.sHref2.toLowerCase().equals("none")) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getCodeBase(), this.sHref2), this.sFrame2);
                }
                catch (MalformedURLException ex3) {}
            }
        }
    }
    
    void btnClose_Clicked(final Event event) {
        this.list1.clear();
        this.searchText.setText("");
        this.treeView1.show();
        this.searchPanel.hide();
        this.pnlSearchHelp.hide();
        this.list1.repaint();
    }
    
    void btnHelpClose_Clicked(final Event event) {
        this.searchPanel.show();
        this.pnlSearchHelp.hide();
        this.searchText.requestFocus();
    }
    
    void btnHelp_Clicked(final Event event) {
        this.pnlSearchHelp.show();
        this.searchPanel.hide();
    }
    
    void searchSWText_EnterHit(final Event event) {
        String sTemp = "";
        if (this.searchSWText.getText().length() > 0) {
            sTemp = this.searchSWText.getText().replace(' ', '+');
            if (this.chkNL.getState()) {
                this.executeLink("http://www.northernlight.com/nlquery.fcg?cb=0&qr=" + sTemp, "_blank");
            }
            if (this.chkAV.getState()) {
                this.executeLink("http://www.altavista.com/cgi-bin/query?q=" + sTemp, "_blank");
            }
            if (this.chkYH.getState()) {
                this.executeLink("http://search.yahoo.com/bin/search?p=" + sTemp, "_blank");
            }
            if (this.chkLY.getState()) {
                this.executeLink("http://www.lycos.com/srch/?lpv=1&loc=searchhp&query=" + sTemp, "_blank");
            }
            if (this.chkEX.getState()) {
                this.executeLink("http://search.excite.com/search.gw?search=" + sTemp, "_blank");
            }
            if (this.chkHO.getState()) {
                this.executeLink("http://hotbot.lycos.com/?MT=" + sTemp, "_blank");
            }
        }
    }
    
    void getSearchEngines() {
    }
    
    void btnSWClose_Clicked(final Event event) {
        this.treeView1.show();
        this.searchWeb.hide();
    }
    
    private void constructInternetSearch() {
        if (this.sWebSearch != null) {
            int iTop = 40;
            for (int y = 0; y < 6; ++y) {
                if (this.sWebSearch.charAt(y) == 'Y') {
                    if (y == 0) {
                        this.chkNL.reshape(2, iTop, 150, 20);
                    }
                    if (y == 1) {
                        this.chkAV.reshape(2, iTop, 150, 20);
                    }
                    if (y == 2) {
                        this.chkYH.reshape(2, iTop, 150, 20);
                    }
                    if (y == 3) {
                        this.chkLY.reshape(2, iTop, 150, 20);
                    }
                    if (y == 4) {
                        this.chkEX.reshape(2, iTop, 150, 20);
                    }
                    if (y == 5) {
                        this.chkHO.reshape(2, iTop, 150, 20);
                    }
                    iTop += 20;
                }
                else {
                    if (y == 0) {
                        this.chkNL.hide();
                    }
                    if (y == 1) {
                        this.chkAV.hide();
                    }
                    if (y == 2) {
                        this.chkYH.hide();
                    }
                    if (y == 3) {
                        this.chkLY.hide();
                    }
                    if (y == 4) {
                        this.chkEX.hide();
                    }
                    if (y == 5) {
                        this.chkHO.hide();
                    }
                }
            }
        }
    }
    
    void treeView1_MouseDown(final Event event) {
        try {
            if (!this.sMOUSECLICKSOUND.equals("none")) {
                this.play(this.getCodeBase(), this.sMOUSECLICKSOUND);
            }
        }
        catch (Exception ex) {}
        try {
            for (int z = 0; z < this.treeView1.getCount(); ++z) {
                if (this.TN[z] == this.treeView1.getSelectedNode()) {
                    this.sHref = this.aHref[z];
                    this.sFrame = this.aFrame[z];
                    this.sHref2 = this.aHref2[z];
                    this.sFrame2 = this.aFrame2[z];
                    this.sExpand = this.aExpand[z];
                    this.sSearch = this.aSearch[z];
                    this.sKeyWords = this.aKeyWords[z];
                    this.sNodeText = this.aNodeText[z];
                    this.sMO = this.aMO[z];
                    break;
                }
            }
            this.sHref = this.sHref.trim();
            this.sFrame = this.sFrame.trim();
            this.sHref2 = this.sHref2.trim();
            this.sFrame2 = this.sFrame2.trim();
            this.sExpand = this.sExpand.trim();
            this.sMO = this.sMO.trim();
            this.sSearch = this.sSearch.trim();
            this.sKeyWords = this.sKeyWords.trim();
            this.sNodeText = this.sNodeText.trim();
            if (this.sSearch.toLowerCase().equals("yes") && this.xSwitch != 2) {
                this.treeView1.hide();
                this.list1.clear();
                this.list2.clear();
                this.list1.addItem("Use the words 'AND' or 'OR'");
                this.list1.addItem("to refine your search");
                this.list1.addItem(" ");
                this.searchText.setText("");
                this.searchText.requestFocus();
                this.searchPanel.show();
            }
            if (this.sSearch.toLowerCase().equals("int") && this.sWebSearch != null) {
                this.treeView1.hide();
                this.searchSWText.setText("");
                this.searchSWText.requestFocus();
                this.searchWeb.show();
            }
            if (this.isPasswordOK(this.sHref, this.sFrame)) {
                if (!this.sHref.toLowerCase().equals("none")) {
                    try {
                        this.getAppletContext().showDocument(new URL(this.getCodeBase(), this.sHref), this.sFrame);
                    }
                    catch (MalformedURLException ex2) {}
                }
                if (!this.sHref2.toLowerCase().equals("none")) {
                    try {
                        this.getAppletContext().showDocument(new URL(this.getCodeBase(), this.sHref2), this.sFrame2);
                    }
                    catch (MalformedURLException ex3) {}
                }
            }
        }
        catch (Exception ex4) {}
    }
    
    public void start() {
        (this.thJAVATree = new Thread(this)).start();
    }
    
    public void run() {
        if (!this.allLoaded) {
            this.constructTree();
            this.constructInternetSearch();
            this.treeView1.show(true);
        }
    }
    
    public void stop() {
        this.thJAVATree.stop();
        this.thJAVATree = null;
    }
    
    public void paint(final Graphics g) {
        ++this.iLoading;
        g.drawString("eNavigator Suite V6.5", 10, 20);
        g.drawString("Loading: " + this.iLoading, 10, 40);
        g.fillRect(10, 60, this.iLoading * 5, 10);
    }
    
    public void init() {
        super.init();
        this.setLayout(null);
        try {
            final Dimension d = this.size();
            this.addNotify();
            this.intWidth = d.width;
            this.intHeight = d.height;
        }
        catch (Exception ex) {}
        if (this.intWidth < 70) {
            this.intWidth = 150;
        }
        if (this.intHeight < 100) {
            this.intHeight = 160;
        }
        (this.treeView1 = new TreeView()).reshape(0, 0, this.intWidth, this.intHeight);
        this.treeView1.setFont(new Font("Dialog", 0, 12));
        this.add(this.treeView1);
        this.treeView1.hide();
        (this.searchPanel = new Panel()).setLayout(null);
        this.searchPanel.reshape(0, 0, this.intWidth, this.intHeight);
        this.add(this.searchPanel);
        (this.btnClose = new Button("X")).reshape(0, 0, 15, 20);
        this.searchPanel.add(this.btnClose);
        (this.btnHelp = new Button("?")).reshape(15, 0, 15, 20);
        this.searchPanel.add(this.btnHelp);
        (this.searchText = new TextField()).reshape(30, 0, this.intWidth - 30, 20);
        this.searchPanel.add(this.searchText);
        this.list1 = new List(0, false);
        this.searchPanel.add(this.list1);
        this.list1.reshape(0, 20, this.intWidth, this.intHeight);
        this.list2 = new List(0, false);
        this.searchPanel.add(this.list2);
        this.list2.hide();
        (this.pnlSearchHelp = new Panel()).setLayout(null);
        this.pnlSearchHelp.reshape(0, 0, this.intWidth, this.intHeight);
        this.add(this.pnlSearchHelp);
        (this.btnHelpClose = new Button("X")).reshape(0, 0, 15, 20);
        this.pnlSearchHelp.add(this.btnHelpClose);
        (this.chkMatchCase = new Checkbox("Match Case")).reshape(2, 25, 200, 20);
        this.pnlSearchHelp.add(this.chkMatchCase);
        (this.chkWholeWord = new Checkbox("Whole Word")).reshape(2, 43, 200, 20);
        this.pnlSearchHelp.add(this.chkWholeWord);
        (this.lblHelp = new Label("HELP")).reshape(17, 0, 200, 20);
        this.pnlSearchHelp.add(this.lblHelp);
        (this.lblHelp1 = new Label("Use the words 'AND' or 'OR'")).reshape(2, 60, 200, 20);
        this.pnlSearchHelp.add(this.lblHelp1);
        (this.lblHelp2 = new Label("to refine your search")).reshape(2, 78, 200, 20);
        this.pnlSearchHelp.add(this.lblHelp2);
        (this.PasswordPanel = new Panel()).setLayout(null);
        this.PasswordPanel.reshape(0, 0, this.intWidth, this.intHeight);
        this.add(this.PasswordPanel);
        (this.btnPWDClose = new Button("X")).reshape(0, 0, 15, 20);
        this.PasswordPanel.add(this.btnPWDClose);
        (this.PasswordText = new TextField()).reshape(15, 0, this.intWidth - 15, 20);
        this.PasswordPanel.add(this.PasswordText);
        (this.PasswordLbl = new Label("Password required")).reshape(2, 20, 200, 20);
        this.PasswordPanel.add(this.PasswordLbl);
        (this.searchWeb = new Panel()).setLayout(null);
        this.searchWeb.reshape(0, 0, this.intWidth - 4, this.intHeight - 26);
        this.add(this.searchWeb);
        (this.btnSWClose = new Button("X")).reshape(0, 0, 15, 20);
        this.searchWeb.add(this.btnSWClose);
        (this.searchSWText = new TextField()).reshape(16, 0, this.intWidth - 15, 20);
        this.searchWeb.add(this.searchSWText);
        (this.lblSW = new Label("Internet Search")).reshape(2, 20, 200, 20);
        this.searchWeb.add(this.lblSW);
        (this.chkNL = new Checkbox("Northern Light")).reshape(2, 40, 150, 20);
        this.searchWeb.add(this.chkNL);
        this.chkNL.setState(true);
        (this.chkAV = new Checkbox("Alta Vista")).reshape(2, 60, 150, 20);
        this.searchWeb.add(this.chkAV);
        this.chkAV.setState(true);
        (this.chkYH = new Checkbox("Yahoo")).reshape(2, 80, 150, 20);
        this.searchWeb.add(this.chkYH);
        this.chkYH.setState(true);
        (this.chkLY = new Checkbox("Lycos")).reshape(2, 100, 150, 20);
        this.searchWeb.add(this.chkLY);
        this.chkLY.setState(true);
        (this.chkEX = new Checkbox("Exite")).reshape(2, 120, 150, 20);
        this.searchWeb.add(this.chkEX);
        this.chkEX.setState(true);
        (this.chkHO = new Checkbox("Hotbot")).reshape(2, 140, 150, 20);
        this.searchWeb.add(this.chkHO);
        this.chkHO.setState(true);
        this.searchPanel.hide();
        this.searchWeb.hide();
        this.PasswordPanel.hide();
        this.pnlSearchHelp.hide();
        this.showStatus("... eNavigator Suite 2000 [Tree] loading, please wait");
    }
    
    private boolean isPasswordOK(final String sHref, final String sFrame) {
        boolean bshow = false;
        if (this.xSwitch == 2) {
            bshow = true;
        }
        else if (sHref.startsWith("$$$", 0)) {
            this.PasswordPanel.show();
            this.PasswordText.setText("");
            this.PasswordLbl.setText("Password required");
            this.PasswordText.requestFocus();
            this.treeView1.hide();
            this.searchPanel.hide();
        }
        else {
            bshow = true;
        }
        return bshow;
    }
    
    public String decrypt(final String src1, String Key) {
        String sChkSum = "";
        String src2 = "";
        String dest = "";
        int KeyLen = 0;
        int chkSum = 0;
        int KeyPos = 0;
        int offset = 0;
        int SrcPos = 0;
        int SrcAsc = 0;
        int TmpSrcAsc = 0;
        int L = 0;
        if (!src1.equals("")) {
            dest = "ERROR";
            return dest;
        }
        final StringTokenizer parser = new StringTokenizer(src1, "-");
        try {
            src2 = parser.nextToken();
            sChkSum = parser.nextToken();
        }
        catch (NoSuchElementException ex) {}
        while (Key.length() < src2.length()) {
            Key += Key;
        }
        final char[] aKey = Key.toCharArray();
        KeyLen = Key.length();
        offset = Integer.parseInt("00" + src2.substring(0, 2), 16);
        for (SrcPos = 2; SrcPos < src2.length() - 1; SrcPos += 2) {
            try {
                SrcAsc = Integer.parseInt("00" + src2.substring(SrcPos, SrcPos + 2), 16);
            }
            catch (Exception ex2) {}
            if (KeyPos < KeyLen) {
                ++KeyPos;
            }
            else {
                KeyPos = 1;
            }
            L = aKey[KeyPos - 1];
            TmpSrcAsc = (SrcAsc ^ L);
            if (TmpSrcAsc <= offset) {
                TmpSrcAsc = 255 + TmpSrcAsc - offset;
            }
            else {
                TmpSrcAsc -= offset;
            }
            dest += (char)TmpSrcAsc;
            offset = SrcAsc;
            chkSum += TmpSrcAsc;
        }
        if (chkSum != Integer.parseInt(sChkSum)) {
            dest = "ERROR";
        }
        if (!dest.startsWith("OK") || !dest.endsWith("OKOK")) {
            dest = "ERROR";
        }
        return dest;
    }
    
    public void setTreeNode(final String sNode, final boolean bExecute) {
        try {
            if (sNode == null) {
                return;
            }
            this.treeView1.setNode(sNode);
            if (this.treeView1.getSelectedText().equals(sNode) && bExecute) {
                this.treeView1_MouseDown(null);
            }
        }
        catch (Exception ex) {}
    }
    
    public String getTreeNode() {
        if (this.treeView1.getSelectedText() == null) {
            return null;
        }
        return this.treeView1.getSelectedText();
    }
    
    public void addTreeNode(final String s1ParentNodeText, final String s1NodeText, final String s1Href1, final String s1Target1, final String s1Href2, final String s1Target2, final String s1Statusbar, final String s1Expand, final String s1KeyWords) {
        try {
            if (s1NodeText == null) {
                return;
            }
            this.aHref[this.iItemCnt] = "none";
            if (s1Href1 != null && !s1Href1.trim().equals("")) {
                this.aHref[this.iItemCnt] = s1Href1;
            }
            if (s1Target1 != null) {
                this.aFrame[this.iItemCnt] = s1Target1;
            }
            else {
                this.aFrame[this.iItemCnt] = "none";
            }
            this.aHref2[this.iItemCnt] = "none";
            if (s1Href2 != null && !s1Href2.trim().equals("")) {
                this.aHref2[this.iItemCnt] = s1Href2;
            }
            if (s1Target2 != null) {
                this.aFrame2[this.iItemCnt] = s1Target2;
            }
            else {
                this.aFrame2[this.iItemCnt] = "none";
            }
            this.aMO[this.iItemCnt] = "";
            if (s1Statusbar != null && !s1Statusbar.trim().equals("")) {
                this.aMO[this.iItemCnt] = s1Statusbar;
            }
            this.aExpand[this.iItemCnt] = "NO";
            if (s1Expand != null && s1Expand.trim().toLowerCase().equals("yes")) {
                this.aExpand[this.iItemCnt] = "YES";
            }
            this.aKeyWords[this.iItemCnt] = "none";
            if (s1KeyWords != null && !s1KeyWords.trim().equals("")) {
                this.aKeyWords[this.iItemCnt] = s1KeyWords;
            }
            this.aNodeText[this.iItemCnt] = "none";
            if (s1NodeText != null && !s1NodeText.trim().equals("")) {
                this.aNodeText[this.iItemCnt] = s1NodeText;
            }
            this.TN[this.iItemCnt] = new TreeNode(s1NodeText, this.ImgdLeaf, this.ImgdLeaf);
            this.bExpand = false;
            if (this.aExpand[this.iItemCnt].toLowerCase().equals("yes")) {
                this.bExpand = true;
            }
            if (this.bTREE_AUTOEXPAND) {
                this.bExpand = true;
            }
            if (s1ParentNodeText.trim().equals("")) {
                this.treeView1.append(this.TN[this.iItemCnt], this.bExpand);
            }
            else {
                this.treeView1.setNode(s1ParentNodeText);
                this.TN1[this.iItemCnt] = this.treeView1.getSelectedNode();
                if (this.TN1[this.iItemCnt] != null) {
                    this.TN1[this.iItemCnt].setCollapsedImage(this.ImgdClosed);
                    this.TN1[this.iItemCnt].setExpandedImage(this.ImgdOpen);
                }
                this.treeView1.insert(this.TN[this.iItemCnt], this.TN1[this.iItemCnt], 0, this.bExpand);
            }
            if (!this.bTREE_AUTOEXPAND) {
                if (this.aExpand[this.iItemCnt].trim().toUpperCase().equals("YES")) {
                    this.bExpand = true;
                }
                else {
                    this.bExpand = false;
                }
            }
            this.treeView1.setNode(s1NodeText);
        }
        catch (Exception ex) {}
        ++this.iItemCnt;
        this.treeView1.redraw();
    }
    
    public void delTreeNode(final String sNode) {
        try {
            if (!sNode.trim().equals("(c)1997-2000 Auscomp") && !sNode.trim().equals("Copyright parameter missing!") && !sNode.trim().equals("eNavigator Suite 2000") && !sNode.trim().equals("For evaluation only") && !sNode.trim().equals("Auscomp - America (West Coast)") && !sNode.trim().equals("Auscomp - America (East Coast)") && !sNode.trim().equals("Auscomp - Australia")) {
                this.treeView1.remove(sNode);
                this.treeView1.redraw();
            }
        }
        catch (Exception ex) {}
    }
    
    public void constructTree() {
        String strBG = "";
        this.iColor = new int[3];
        this.aHref = new String[3999];
        this.aFrame = new String[3999];
        this.aHref2 = new String[3999];
        this.aFrame2 = new String[3999];
        this.aExpand = new String[3999];
        this.aMO = new String[3999];
        this.aSearch = new String[3999];
        this.aKeyWords = new String[3999];
        this.aNodeText = new String[3999];
        this.iLevel = new int[3999];
        this.TN = new TreeNode[3999];
        this.TN1 = new TreeNode[3999];
        try {
            strBG = this.getParameter("COPYRIGHT");
            if (strBG.indexOf(this.sCopyright) > 0) {
                this.bCopyright = true;
            }
            else {
                this.bCopyright = false;
            }
        }
        catch (Exception ex) {}
        try {
            this.sPasswordError = this.getParameter("PASSWORDERROR");
        }
        catch (Exception ex2) {}
        for (int x = 0; x < 12; ++x) {
            switch (x) {
                case 0: {
                    this.iColor = this.iColorWhite;
                    break;
                }
                case 1: {
                    this.iColor = this.iColorBlack;
                    break;
                }
                case 2: {
                    this.iColor = this.iColorBlue;
                    break;
                }
                case 3: {
                    this.iColor = this.iColorWhite;
                    break;
                }
                case 4: {
                    this.iColor = this.iColorRed;
                    break;
                }
                case 5: {
                    this.iColor = this.iColorBlack;
                    break;
                }
                case 6: {
                    this.iColor = this.iColorBlack;
                    break;
                }
                case 7: {
                    this.iColor = this.iColorBlack;
                    break;
                }
                case 8: {
                    this.iColor = this.iColorBlack;
                    break;
                }
                case 9: {
                    this.iColor = this.iColorYellow;
                    break;
                }
                case 10: {
                    this.iColor = this.iColorBlack;
                    break;
                }
                case 11: {
                    this.iColor = this.iColorRed;
                    break;
                }
            }
            try {
                strBG = this.getParameter(this.sColorParam[x]);
                if (strBG != null) {
                    this.iColor[0] = this.getCOLOR(0, this.sColorParam[x], strBG);
                    this.iColor[1] = this.getCOLOR(1, this.sColorParam[x], strBG);
                    this.iColor[2] = this.getCOLOR(2, this.sColorParam[x], strBG);
                }
            }
            catch (Exception ex3) {}
            switch (x) {
                case 0: {
                    this.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.treeView1.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.list1.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.searchText.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.btnHelp.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.btnClose.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.pnlSearchHelp.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.btnHelpClose.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.lblHelp.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.lblHelp1.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.lblHelp2.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkMatchCase.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkWholeWord.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.PasswordPanel.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.PasswordText.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.btnPWDClose.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.PasswordLbl.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.searchWeb.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.lblSW.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkNL.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkAV.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkYH.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkLY.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkEX.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkHO.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.btnSWClose.setBackground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 1: {
                    this.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.treeView1.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.list1.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.searchText.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.btnHelp.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.btnClose.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.pnlSearchHelp.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.btnHelpClose.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.lblHelp2.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.lblHelp1.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.lblHelp2.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkMatchCase.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkWholeWord.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.PasswordPanel.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.PasswordText.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.btnPWDClose.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.PasswordLbl.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.searchSWText.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.btnSWClose.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.lblSW.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.searchWeb.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkNL.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkAV.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkYH.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkLY.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkEX.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    this.chkHO.setForeground(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 2: {
                    this.treeView1.setBgHilite(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 3: {
                    this.treeView1.setFgHilite(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 4: {
                    this.treeView1.setMOHilite(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 5: {
                    this.treeView1.setLineColor(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 6: {
                    this.treeView1.setFolderOColor(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 7: {
                    this.treeView1.setFolderCColor(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 8: {
                    this.treeView1.setLeafColor(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 9: {
                    this.treeView1.setToolTipBackColor(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 10: {
                    this.treeView1.setToolTipForeColor(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
                case 11: {
                    this.treeView1.setMouseOverFrameColor(new Color(this.iColor[0], this.iColor[1], this.iColor[2]));
                    break;
                }
            }
        }
        try {
            this.sMOUSEOVERSOUND = "none";
            strBG = this.getParameter("MOUSEOVERSOUND");
            if (strBG != null) {
                this.sMOUSEOVERSOUND = strBG;
            }
        }
        catch (Exception ex4) {}
        try {
            this.sMOUSECLICKSOUND = "none";
            strBG = this.getParameter("MOUSECLICKSOUND");
            if (strBG != null) {
                this.sMOUSECLICKSOUND = strBG;
            }
        }
        catch (Exception ex5) {}
        for (int x2 = 0; x2 < 8; ++x2) {
            try {
                strBG = this.getParameter(this.sNormalParam[x2]);
            }
            catch (Exception ex6) {}
            switch (x2) {
                case 0: {
                    if (strBG == null) {
                        strBG = "AXAA";
                    }
                    this.treeView1.setStyle(strBG);
                    break;
                }
                case 1: {
                    if (strBG == null) {
                        strBG = "none";
                    }
                    this.treeView1.setMouseOverStyle(strBG);
                    break;
                }
                case 2: {
                    try {
                        this.treeView1.setLineStep(Integer.parseInt(strBG));
                    }
                    catch (Exception ex7) {}
                    break;
                }
                case 3: {
                    try {
                        if (strBG == null) {
                            strBG = "Yes";
                        }
                        if (strBG.trim().toLowerCase().equals("no")) {
                            strBG = "No";
                        }
                    }
                    catch (Exception ex8) {}
                    this.treeView1.setToolTip(strBG);
                    break;
                }
                case 4: {
                    if (strBG == null) {
                        strBG = "No";
                    }
                    try {
                        if (strBG.trim().toLowerCase().equals("yes")) {
                            strBG = "Yes";
                        }
                    }
                    catch (Exception ex9) {}
                    this.treeView1.setBorder(strBG);
                    break;
                }
                case 5: {
                    if (strBG == null) {
                        strBG = "No";
                    }
                    try {
                        if (strBG.trim().toLowerCase().equals("yes")) {
                            strBG = "Yes";
                        }
                    }
                    catch (Exception ex10) {}
                    this.treeView1.setDblClick(strBG);
                    break;
                }
                case 6: {
                    this.bTREE_AUTOEXPAND = false;
                    try {
                        if (strBG.trim().toLowerCase().equals("yes")) {
                            this.bTREE_AUTOEXPAND = true;
                        }
                    }
                    catch (Exception ex11) {}
                    break;
                }
                case 7: {
                    try {
                        this.sWebSearch = strBG;
                    }
                    catch (Exception ex12) {}
                    break;
                }
            }
        }
        try {
            strBG = this.getParameter("FONT");
            String fName = "";
            String fStyle = "";
            String fSize = "";
            int iStyle = 0;
            if (strBG != null) {
                final StringTokenizer parser = new StringTokenizer(strBG, ";");
                try {
                    fName = parser.nextToken();
                    fStyle = parser.nextToken();
                    fSize = parser.nextToken();
                    if (fStyle.trim().toLowerCase().equals("bold")) {
                        iStyle = 1;
                    }
                    if (fStyle.trim().toLowerCase().equals("italic")) {
                        iStyle = 2;
                    }
                    this.fFont = new Font(fName, iStyle, Integer.parseInt(fSize.trim()));
                }
                catch (NoSuchElementException ex13) {
                    System.out.println(strBG + " Parameter Error(1)");
                }
                catch (Exception ex14) {
                    System.out.println(strBG + " Parameter Error(2)");
                }
            }
            this.treeView1.setFont(this.fFont);
            this.btnHelp.setFont(this.fFont);
            this.lblHelp1.setFont(this.fFont);
            this.lblHelp2.setFont(this.fFont);
            this.chkMatchCase.setFont(this.fFont);
            this.chkWholeWord.setFont(this.fFont);
            this.btnClose.setFont(this.fFont);
            this.list1.setFont(this.fFont);
            this.searchText.setFont(this.fFont);
            this.btnHelpClose.setFont(this.fFont);
            this.lblHelp1.setFont(this.fFont);
            this.lblHelp2.setFont(this.fFont);
            this.chkMatchCase.setFont(this.fFont);
            this.PasswordText.setFont(this.fFont);
            this.btnPWDClose.setFont(this.fFont);
            this.PasswordLbl.setFont(this.fFont);
            this.btnSWClose.setFont(this.fFont);
            this.searchSWText.setFont(this.fFont);
            this.lblSW.setFont(this.fFont);
            this.chkNL.setFont(this.fFont);
            this.chkAV.setFont(this.fFont);
            this.chkLY.setFont(this.fFont);
            this.chkEX.setFont(this.fFont);
            this.chkYH.setFont(this.fFont);
            this.chkHO.setFont(this.fFont);
        }
        catch (Exception ex15) {}
        for (int x3 = 0; x3 < 4; ++x3) {
            try {
                strBG = this.getParameter(this.sImageParam[x3]);
                this.imgBG = null;
                if (strBG != null) {
                    this.imgBG = this.getImage(this.getCodeBase(), strBG);
                    final MediaTracker m = new MediaTracker(this);
                    try {
                        m.addImage(this.imgBG, 0);
                        m.waitForAll();
                    }
                    catch (InterruptedException ex16) {}
                }
                switch (x3) {
                    case 0: {
                        if (this.imgBG.getHeight(this) > 0) {
                            this.ImgdOpen = this.imgBG;
                            break;
                        }
                        System.out.println("Error - couldn't find FOLDER OPEN IMAGE - please check image URL");
                        break;
                    }
                    case 1: {
                        if (this.imgBG.getHeight(this) > 0) {
                            this.ImgdClosed = this.imgBG;
                            break;
                        }
                        System.out.println("Error - couldn't find FOLDER CLOSED IMAGE - please check image URL");
                        break;
                    }
                    case 2: {
                        if (this.imgBG.getHeight(this) > 0) {
                            this.ImgdLeaf = this.imgBG;
                            break;
                        }
                        System.out.println("Error - couldn't find FOLDER CLOSED IMAGE - please check image URL");
                        break;
                    }
                    case 3: {
                        if (this.imgBG.getHeight(this) > 0) {
                            this.treeView1.setBGImage(this.imgBG);
                            this.treeView1.repaint();
                            break;
                        }
                        System.out.println("Error - couldn't find BACKGROUND IMAGE - please check image URL");
                        break;
                    }
                }
            }
            catch (Exception ex17) {}
        }
        try {
            strBG = null;
            strBG = this.getParameter("CONTENT");
            if (strBG == null) {
                this.getParameterContent();
            }
            else {
                this.getFileContent(strBG);
            }
        }
        catch (Exception ex18) {}
        this.allLoaded = true;
    }
    
    private void getParameterContent() {
        this.repaint();
        int i;
        for (i = 0; this.getParameter("I" + i) != null; ++i) {}
        String strBG = null;
        for (int x = 0; x < i; ++x) {
            strBG = this.getParameter("I" + x);
            if (strBG == null) {
                System.out.println("I" + x + " Parameter Error");
            }
            this.workParam("I" + x, strBG);
        }
        this.makeTree(this.iItemCnt);
        this.searchPanel.hide();
        this.PasswordPanel.hide();
        this.pnlSearchHelp.hide();
    }
    
    private void getFileContent(final String str1BG) {
        this.repaint();
        this.readFile(str1BG);
        this.makeTree(this.iItemCnt);
    }
    
    public boolean handleEvent(final Event event) {
        try {
            if (this.treeView1.getMouseOverNode() != null && event.target == this.treeView1 && event.id == 501) {
                this.treeView1_MouseDown(event);
                return true;
            }
        }
        catch (Exception ex) {}
        if (event.target == this.treeView1 && event.id == 504) {
            this.treeView1_MouseEnter(event);
            return true;
        }
        try {
            if (this.oldMouseOverNode != this.treeView1.getMouseOverNode() && this.treeView1.getMouseOverNode() != null && event.target == this.treeView1 && event.id == 503) {
                this.treeView1_MouseMove(event);
                this.oldMouseOverNode = this.treeView1.getMouseOverNode();
                return true;
            }
        }
        catch (Exception ex2) {}
        if (event.target == this.treeView1 && event.id == 401) {
            this.treeView1_KeyPress(event);
            return true;
        }
        if (event.target == this.btnClose && event.id == 1001) {
            this.btnClose_Clicked(event);
            return true;
        }
        if (event.target == this.btnHelp && event.id == 1001) {
            this.btnHelp_Clicked(event);
            return true;
        }
        if (event.target == this.btnHelpClose && event.id == 1001) {
            this.btnHelpClose_Clicked(event);
            return true;
        }
        if (event.target == this.searchText && event.id == 1001) {
            this.searchText_EnterHit(event);
            return true;
        }
        if (event.target == this.list1 && event.id == 1001) {
            this.list1_DblClicked(event);
            return true;
        }
        if (event.target == this.list1 && (event.id == 701 || event.id == 501)) {
            this.list1_MouseDown(event);
            return true;
        }
        if (event.target == this.btnPWDClose && event.id == 1001) {
            this.btnPWDClose_Clicked(event);
            return true;
        }
        if (event.target == this.PasswordText && event.id == 1001) {
            this.PasswordText_EnterHit(event);
            return true;
        }
        if (event.target == this.searchSWText && event.id == 1001) {
            this.searchSWText_EnterHit(event);
            return true;
        }
        if (event.target == this.btnSWClose && event.id == 1001) {
            this.btnSWClose_Clicked(event);
            return true;
        }
        return super.handleEvent(event);
    }
    
    private int getCOLOR(final int iColor, final String sWhat, final String sPBG) {
        int iRed = 255;
        int iBlue = 255;
        int iGreen = 255;
        final StringTokenizer parser = new StringTokenizer(sPBG, ",");
        try {
            final String sRed = parser.nextToken();
            final String sGreen = parser.nextToken();
            final String sBlue = parser.nextToken();
            iRed = Integer.parseInt(sRed);
            iGreen = Integer.parseInt(sGreen);
            iBlue = Integer.parseInt(sBlue);
        }
        catch (NoSuchElementException ex) {
            System.out.println(sWhat + " Parameter Error(1)");
        }
        catch (Exception ex2) {
            System.out.println(sWhat + " Parameter Error(2)");
        }
        switch (iColor) {
            case 0: {
                return iRed;
            }
            case 1: {
                return iGreen;
            }
            case 2: {
                return iBlue;
            }
            default: {
                return 255;
            }
        }
    }
    
    private void makeNode(final int iNCnt, final String sT, final String sP1, final String sP2) {
        this.ImgClosed = this.ImgdLeaf;
        this.ImgOpen = this.ImgdLeaf;
        if (!sP1.trim().toLowerCase().equals("none") && !sP1.trim().toLowerCase().equals("") && !sP1.trim().toLowerCase().equals("leaf")) {
            if (sP1.trim().toLowerCase().equals("folder")) {
                this.ImgClosed = this.ImgdClosed;
                this.ImgOpen = this.ImgdOpen;
            }
            else {
                this.ImgClosed = this.getImage(this.getCodeBase(), sP1);
                this.ImgOpen = this.getImage(this.getCodeBase(), sP2);
                final MediaTracker m = new MediaTracker(this);
                try {
                    m.addImage(this.ImgClosed, 0);
                    m.addImage(this.ImgOpen, 1);
                    m.waitForAll();
                    this.m_fAllLoaded = !m.isErrorAny();
                }
                catch (InterruptedException ex) {}
                if (!this.m_fAllLoaded) {
                    this.ImgClosed = this.ImgdLeaf;
                    this.ImgOpen = this.ImgdLeaf;
                }
            }
        }
        this.TN[iNCnt] = new TreeNode(sT, this.ImgClosed, this.ImgOpen);
    }
    
    private void makeTree(final int iNodeCnt) {
        this.bExpand = false;
        for (int y = 0; y < iNodeCnt; ++y) {
            if (this.bTREE_AUTOEXPAND) {
                this.bExpand = true;
            }
            if (this.iLevel[y] == 0) {
                this.treeView1.append(this.TN[y], this.bExpand);
            }
            else {
                this.treeView1.insert(this.TN[y], this.TN1[this.iLevel[y] - 1], 0, this.bExpand);
            }
            this.TN1[this.iLevel[y]] = this.TN[y];
            if (!this.bTREE_AUTOEXPAND) {
                if (this.aExpand[y].trim().toUpperCase().equals("YES")) {
                    this.bExpand = true;
                }
                else {
                    this.bExpand = false;
                }
            }
        }
        this.treeView1.redraw();
    }
    
    private void workParam(final String sItem, final String sP) {
        if (!this.bCopyright) {
            if (this.iItemCnt == 0) {
                this.makeNode(this.iItemCnt, "(c)1997-2000 Auscomp-WorldWide", "SLauscomp.gif", "SLauscomp.gif");
                this.iLevel[this.iItemCnt] = 0;
                this.aHref[this.iItemCnt] = "http://www.auscomp.com";
                this.aFrame[this.iItemCnt] = "_blank";
                this.aHref2[this.iItemCnt] = "none";
                this.aFrame2[this.iItemCnt] = "none";
                this.aExpand[this.iItemCnt] = "NO";
                this.aMO[this.iItemCnt] = "Auscomp eNavigator Suite 2000 is a product from Auscomp-WorldWide.";
                this.makeNode(++this.iItemCnt, "Copyright parameter missing!", "none", "none");
                this.iLevel[this.iItemCnt] = 0;
                this.aHref[this.iItemCnt] = "http://www.auscomp.com";
                this.aFrame[this.iItemCnt] = "main";
                this.aHref2[this.iItemCnt] = "none";
                this.aFrame2[this.iItemCnt] = "none";
                this.aExpand[this.iItemCnt] = "NO";
                this.aMO[this.iItemCnt] = "Copyright parameter missing!";
                ++this.iItemCnt;
            }
            return;
        }
        this.sHref2 = "None";
        this.sFrame2 = "None";
        this.sMO = "None";
        this.sExpand = "NO";
        this.sKeyWords = " ";
        this.sSearch = "NO";
        if (sP.substring(0, 3).equals("***")) {
            this.sHref = "None";
            this.sFrame = "None";
            this.sMO = this.sDefMO;
            return;
        }
        final StringTokenizer parser = new StringTokenizer(sP, ";");
        parser.countTokens();
        try {
            this.sText = parser.nextToken();
            this.sHref = parser.nextToken();
            this.sFrame = parser.nextToken();
            this.sLevel = parser.nextToken();
            this.sPic1 = parser.nextToken();
            this.sPic2 = parser.nextToken();
            this.sMO = parser.nextToken();
            this.sHref2 = parser.nextToken();
            this.sFrame2 = parser.nextToken();
            this.sExpand = parser.nextToken();
            this.sSearch = parser.nextToken();
            this.sKeyWords = parser.nextToken();
        }
        catch (NoSuchElementException ex) {}
        catch (Exception ex2) {
            System.out.println(sItem + " Parameter Error(3)");
        }
        if (this.iItemCnt == 0 && this.xSwitch == 0) {
            this.makeNode(this.iItemCnt, "(c)1997-2000 Auscomp", "SLauscomp.gif", "SLauscomp.gif");
            this.iLevel[this.iItemCnt] = 0;
            this.aHref[this.iItemCnt] = "http://www.auscomp.com";
            this.aFrame[this.iItemCnt] = "_blank";
            this.aHref2[this.iItemCnt] = "none";
            this.aFrame2[this.iItemCnt] = "none";
            this.aExpand[this.iItemCnt] = "NO";
            this.aMO[this.iItemCnt] = "Auscomp eNavigator Suite 2000 (c)1997-2000 Auscomp-WorldWide.";
            this.aSearch[this.iItemCnt] = "NO";
            this.aKeyWords[this.iItemCnt] = "Auscomp, Copyright, Evaluation, Order, www.auscomp.com";
            this.aNodeText[this.iItemCnt] = "(c)1997-2000 Auscomp";
            this.makeNode(++this.iItemCnt, "eNavigator Suite 2000", "none", "none");
            this.iLevel[this.iItemCnt] = 0;
            this.aHref[this.iItemCnt] = "http://www.auscomp.com";
            this.aFrame[this.iItemCnt] = "_blank";
            this.aHref2[this.iItemCnt] = "none";
            this.aFrame2[this.iItemCnt] = "none";
            this.aExpand[this.iItemCnt] = "NO";
            this.aMO[this.iItemCnt] = "Auscomp eNavigator Suite 2000 for evaluation purposes only.";
            this.aSearch[this.iItemCnt] = "NO";
            this.aKeyWords[this.iItemCnt] = "Auscomp, Copyright, Evaluation, Order, www.auscomp.com";
            this.aNodeText[this.iItemCnt] = "eNavigator 2000";
            this.makeNode(++this.iItemCnt, "For evaluation only", "none", "none");
            this.iLevel[this.iItemCnt] = 0;
            this.aHref[this.iItemCnt] = "http://www.auscomp.com/buy_online.html";
            this.aFrame[this.iItemCnt] = "_blank";
            this.aHref2[this.iItemCnt] = "none";
            this.aFrame2[this.iItemCnt] = "none";
            this.aExpand[this.iItemCnt] = "NO";
            this.aMO[this.iItemCnt] = "Click to buy on-line.";
            this.aSearch[this.iItemCnt] = "NO";
            this.aKeyWords[this.iItemCnt] = "Auscomp, Copyright, Evaluation, Order, www.auscomp.com";
            this.aNodeText[this.iItemCnt] = "For evaluation only";
            this.makeNode(++this.iItemCnt, "-------------------------------------", "none", "none");
            this.iLevel[this.iItemCnt] = 0;
            this.aHref[this.iItemCnt] = "http://www.auscomp.com/buy_online.html";
            this.aFrame[this.iItemCnt] = "_blank";
            this.aHref2[this.iItemCnt] = "none";
            this.aFrame2[this.iItemCnt] = "none";
            this.aExpand[this.iItemCnt] = "NO";
            this.aMO[this.iItemCnt] = "Click to buy on-line.";
            this.aSearch[this.iItemCnt] = "NO";
            this.aKeyWords[this.iItemCnt] = "";
            this.aNodeText[this.iItemCnt] = "";
            ++this.iItemCnt;
        }
        this.sLevel = this.sLevel.trim();
        this.iLevel[this.iItemCnt] = 0;
        try {
            this.iLevel[this.iItemCnt] = Integer.parseInt(this.sLevel);
        }
        catch (Exception ex3) {}
        this.aHref[this.iItemCnt] = this.sHref;
        this.aFrame[this.iItemCnt] = this.sFrame;
        this.aHref2[this.iItemCnt] = this.sHref2;
        this.aFrame2[this.iItemCnt] = this.sFrame2;
        this.aExpand[this.iItemCnt] = this.sExpand;
        this.aMO[this.iItemCnt] = this.sMO;
        this.aSearch[this.iItemCnt] = this.sSearch;
        this.aKeyWords[this.iItemCnt] = this.sKeyWords;
        this.aNodeText[this.iItemCnt] = this.sText;
        this.makeNode(this.iItemCnt, this.sText, this.sPic1, this.sPic2);
        ++this.iItemCnt;
    }
    
    public String getAppletInfo() {
        return "Auscomp eNavigator Suite 2000 [Tree] V6 (c)1997-2000 Auscomp-WorldWide (www.auscomp.com)";
    }
    
    private void readFile(final String tFile) {
        this.aHref = new String[3999];
        this.aFrame = new String[3999];
        this.aHref2 = new String[3999];
        this.aFrame2 = new String[3999];
        this.aExpand = new String[3999];
        this.aMO = new String[3999];
        this.iLevel = new int[3999];
        this.TN = new TreeNode[3999];
        this.TN1 = new TreeNode[3999];
        this.sLine = "";
        this.iYRec = 0;
        try {
            final URL u = new URL(this.getCodeBase(), tFile);
            u.openConnection();
            u.getContent();
            final InputStream in = u.openStream();
            final DataInputStream datainStream = new DataInputStream(in);
            while ((this.sLine = datainStream.readLine()) != null) {
                if (!this.sLine.trim().equalsIgnoreCase("")) {
                    this.workParam("Line: " + this.iYRec, this.sLine);
                    ++this.iYRec;
                }
            }
        }
        catch (MalformedURLException ex) {
            System.err.println("URL Error");
        }
        catch (IOException e) {
            System.out.println("Error - File not found (" + this.getCodeBase() + tFile + ")");
            e.printStackTrace();
        }
    }
    
    public JAVATreeF() {
        this.xSwitch = 2;
        this.sDefMO = "";
        this.sCopyright = "AUSCOMP (www.auscomp.com)";
        this.sText = "";
        this.sHref = "";
        this.sFrame = "";
        this.sHref2 = "";
        this.sFrame2 = "";
        this.sExpand = "";
        this.sSearch = "";
        this.sKeyWords = "";
        this.sNodeText = "";
        this.sWebSearch = "YNNNNN";
        this.sLevel = "";
        this.sPic1 = "";
        this.sPic2 = "";
        this.sMO = "";
        this.fFont = new Font("Dialog", 0, 12);
        this.bExpand = false;
        this.bTREE_AUTOEXPAND = false;
        this.dblClick = "no";
        this.m_fAllLoaded = false;
        this.bCopyright = false;
        this.allLoaded = false;
        this.intWidth = 200;
        this.intHeight = 250;
        this.sMOUSEOVERSOUND = "none";
        this.sMOUSECLICKSOUND = "none";
        this.sPasswordError = "";
        this.iSBWidth = 14;
        this.iColorBlack = new int[] { 0, 0, 0 };
        this.iColorWhite = new int[] { 255, 255, 255 };
        this.iColorRed = new int[] { 255, 0, 0 };
        this.iColorBlue = new int[] { 0, 0, 255 };
        this.iColorYellow = new int[] { 255, 255, 231 };
        this.iColorGreen = new int[] { 0, 255, 0 };
        this.iColorGray = new int[] { 192, 192, 192 };
        this.iColorLightGray = new int[] { 210, 210, 210 };
        this.sColorParam = new String[] { "BACKGROUND", "FOREGROUND", "SELBACKGROUND", "SELFOREGROUND", "MOUSEOVER", "LINECOLOR", "FOLDEROCOLOR", "FOLDERCCOLOR", "LEAFCOLOR", "TOOLTIPBACKCOLOR", "TOOLTIPFORECOLOR", "MOUSEOVERFRAMECOLOR" };
        this.sNormalParam = new String[] { "STYLE", "MOUSEOVERFRAME", "LINESTEP", "TOOLTIP", "BORDER", "DOUBLECLICK", "AUTOEXPAND", "WEBSEARCH" };
        this.sImageParam = new String[] { "FOLDER_OPEN", "FOLDER_CLOSED", "LEAF", "BACKGROUNDIMAGE" };
    }
}
