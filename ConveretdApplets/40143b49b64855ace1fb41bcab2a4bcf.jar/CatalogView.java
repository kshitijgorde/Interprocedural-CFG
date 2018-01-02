import java.awt.Event;
import java.awt.Image;
import java.awt.Container;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.StringTokenizer;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CatalogView extends Applet implements Runnable
{
    PopUpWindow window;
    MyDialog PasswdDlg;
    MyDialog ProgressBar;
    MyDialog ExpiredDlg;
    Thread m_CatalogView;
    private boolean pleaseCreate;
    private boolean Created;
    public Field[] FIELDs;
    private String param;
    public String pWINDOW_TITLE;
    public String pSOURCE_FILE;
    private boolean Encoded;
    public String pHELP_DOC;
    public String pHELP_BASE;
    public String pHELP_TARGET;
    private String pMOUSE_MSG;
    private String pIMAGE_URL;
    public String pPASSWORD;
    public boolean pSEARCH_BUTTON;
    public boolean pSEARCHALL_BUTTON;
    public boolean pPRINT_BUTTON;
    public boolean pSHOWALL_BUTTON;
    public boolean pHELP_BUTTON;
    public boolean pSORT_CHOICE;
    public boolean pFILTER_CHOICE;
    public boolean pINDEX_CHOICE;
    public boolean pKEY_FIELD;
    public boolean pAUTO_LOAD;
    public int pVISIBLE_WIDTH;
    public int pVISIBLE_ROWS;
    public int pTOPVISIBLE_ROWS;
    public int pFONT_SIZE;
    public int pFONT_TYPE;
    public int pDATAFONT_SIZE;
    public int pDATAFONT_TYPE;
    public char pDELIMITER;
    public int pPAD_X;
    public int pPAD_Y;
    public int pSORT_ORDER;
    private Date expDate;
    private ImageCanvas imageButtom;
    public boolean Destroyed;
    private String pLICENSE;
    public String pRECORD_NUMBER;
    
    public void stop() {
        if (this.m_CatalogView != null) {
            this.m_CatalogView.stop();
            this.m_CatalogView = null;
        }
        if (this.window != null) {
            if (this.window.AboutDlg != null) {
                this.window.AboutDlg.dispose();
            }
            if (this.window.ImageDlg != null) {
                this.window.ImageDlg.dispose();
            }
            this.window.dispose();
            this.window = null;
            this.Created = false;
        }
        if (this.ProgressBar != null) {
            this.ProgressBar.dispose();
            this.ProgressBar = null;
        }
        if (this.PasswdDlg != null) {
            this.PasswdDlg.dispose();
            this.PasswdDlg = null;
        }
    }
    
    public CatalogView() {
        this.window = null;
        this.PasswdDlg = null;
        this.ProgressBar = null;
        this.ExpiredDlg = null;
        this.m_CatalogView = null;
        this.pleaseCreate = false;
        this.Created = false;
        this.param = null;
        this.pWINDOW_TITLE = "Untitled";
        this.pSOURCE_FILE = null;
        this.Encoded = false;
        this.pHELP_DOC = "www2.dk-online.dk/users/nkt/pages/help.htm";
        this.pHELP_BASE = "http://";
        this.pHELP_TARGET = "_new";
        this.pMOUSE_MSG = "Launch Catalog View";
        this.pIMAGE_URL = null;
        this.pPASSWORD = null;
        this.pSEARCH_BUTTON = true;
        this.pSEARCHALL_BUTTON = true;
        this.pPRINT_BUTTON = true;
        this.pSHOWALL_BUTTON = true;
        this.pHELP_BUTTON = true;
        this.pSORT_CHOICE = true;
        this.pFILTER_CHOICE = true;
        this.pINDEX_CHOICE = true;
        this.pKEY_FIELD = true;
        this.pAUTO_LOAD = false;
        this.pVISIBLE_WIDTH = 600;
        this.pVISIBLE_ROWS = 20;
        this.pTOPVISIBLE_ROWS = 4;
        this.pFONT_SIZE = 11;
        this.pFONT_TYPE = 1;
        this.pDATAFONT_SIZE = 11;
        this.pDATAFONT_TYPE = 0;
        this.pDELIMITER = '\"';
        this.pPAD_X = 4;
        this.pPAD_Y = 2;
        this.pSORT_ORDER = 0;
        this.Destroyed = false;
        this.pLICENSE = null;
    }
    
    public void start() {
        if (this.ProgressBar == null) {
            this.ProgressBar = new MyDialog(this, MyDialog.PROGRESS_DLG, this.pFONT_SIZE);
        }
        if (this.PasswdDlg == null) {
            this.PasswdDlg = new MyDialog(this, 0, this.pFONT_SIZE);
        }
        if (this.m_CatalogView == null) {
            (this.m_CatalogView = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: CatalogView\r\nAuthor: IST, DENMARK\r\nhttp://www.trading.dk/catview";
    }
    
    public void run() {
        if (this.pAUTO_LOAD) {
            this.ProgressBar.show();
        }
        if (this.window == null) {
            try {
                this.window = new PopUpWindow(this);
            }
            catch (Exception ex) {
                this.showStatus("Couldn't create instance of class PopUpWindow");
                return;
            }
            if (this.pleaseCreate) {
                if (this.pPASSWORD != null) {
                    this.PasswdDlg.show();
                }
                else {
                    this.window.show();
                }
            }
            this.ProgressBar.hide();
            this.Created = true;
            this.pleaseCreate = false;
        }
    }
    
    public void init() {
        this.param = this.getParameter("SOURCE_FILE");
        if (this.param != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.param, " ,;");
            this.pSOURCE_FILE = stringTokenizer.nextToken();
            if (stringTokenizer.hasMoreTokens()) {
                this.param = stringTokenizer.nextToken();
                if (this.param.equalsIgnoreCase("PROTECTED")) {}
                this.Encoded = true;
            }
        }
        this.param = this.getParameter("RECORD_NUMBER");
        if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
            this.pRECORD_NUMBER = this.param;
        }
        this.param = this.getParameter("AUTO_LOAD");
        if (this.param != null && this.param.equalsIgnoreCase("YES")) {
            this.pAUTO_LOAD = true;
            this.pleaseCreate = true;
        }
        this.param = this.getParameter("PASSWORD");
        if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
            this.pPASSWORD = this.param;
        }
        this.param = this.getParameter("IMAGE_URL");
        if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
            this.pIMAGE_URL = this.param;
        }
        this.param = this.getParameter("MOUSE_MSG");
        if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
            this.pMOUSE_MSG = this.param;
        }
        this.param = this.getParameter("WINDOW_TITLE");
        if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
            this.pWINDOW_TITLE = this.param;
        }
        this.param = this.getParameter("HELP_DOC");
        if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(this.param, " ,;");
            this.pHELP_DOC = stringTokenizer2.nextToken();
            if (stringTokenizer2.hasMoreTokens()) {
                this.pHELP_BASE = stringTokenizer2.nextToken();
            }
            if (stringTokenizer2.hasMoreTokens()) {
                this.pHELP_TARGET = stringTokenizer2.nextToken();
            }
        }
        this.param = this.getParameter("DELIMITER");
        if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
            if (this.param.equalsIgnoreCase("SEMICOLON")) {
                this.pDELIMITER = ';';
            }
            if (this.param.equalsIgnoreCase("COMMA")) {
                this.pDELIMITER = ',';
            }
            if (this.param.equalsIgnoreCase("Q_MARK")) {
                this.pDELIMITER = new String("'").charAt(0);
            }
            if (this.param.equalsIgnoreCase("DQ_MARK")) {
                this.pDELIMITER = '\"';
            }
            if (this.param.equalsIgnoreCase("TAB")) {
                this.pDELIMITER = '\t';
            }
        }
        try {
            this.param = this.getParameter("VISIBLE_WIDTH");
            if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
                this.pVISIBLE_WIDTH = Integer.parseInt(this.param);
            }
            this.param = this.getParameter("VISIBLE_ROWS");
            if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
                this.pVISIBLE_ROWS = Integer.parseInt(this.param);
            }
            this.param = this.getParameter("TOPVISIBLE_ROWS");
            if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
                this.pTOPVISIBLE_ROWS = Integer.parseInt(this.param);
            }
            this.param = this.getParameter("PAD_X");
            if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
                this.pPAD_X = Integer.parseInt(this.param);
            }
            this.param = this.getParameter("PAD_Y");
            if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
                this.pPAD_Y = Integer.parseInt(this.param);
            }
            this.param = this.getParameter("FONT");
            if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(this.param, " ,;");
                this.pFONT_SIZE = Integer.parseInt(stringTokenizer3.nextToken());
                if (stringTokenizer3.hasMoreTokens()) {
                    this.param = stringTokenizer3.nextToken();
                }
                if (this.param.equalsIgnoreCase("BOLD")) {
                    this.pFONT_TYPE = 1;
                }
            }
            this.param = this.getParameter("DATA_FONT");
            if (this.param != null && !this.param.equalsIgnoreCase("NO")) {
                final StringTokenizer stringTokenizer4 = new StringTokenizer(this.param, " ,;");
                this.pDATAFONT_SIZE = Integer.parseInt(stringTokenizer4.nextToken());
                if (stringTokenizer4.hasMoreTokens()) {
                    this.param = stringTokenizer4.nextToken();
                }
                if (this.param.equalsIgnoreCase("BOLD")) {
                    this.pDATAFONT_TYPE = 1;
                }
            }
            this.param = this.getParameter("SORT_CHOICE");
            if (this.param != null) {
                final StringTokenizer stringTokenizer5 = new StringTokenizer(this.param, " ,;");
                if (stringTokenizer5.nextToken().equalsIgnoreCase("NO")) {
                    this.pSORT_CHOICE = false;
                }
                if (stringTokenizer5.hasMoreTokens()) {
                    this.pSORT_ORDER = Integer.parseInt(stringTokenizer5.nextToken());
                }
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("One of the parameters did not contain a valid integer");
        }
        this.param = this.getParameter("SEARCH_BUTTON");
        if (this.param != null && this.param.equalsIgnoreCase("NO")) {
            this.pSEARCH_BUTTON = false;
        }
        this.param = this.getParameter("SEARCHALL_BUTTON");
        if (this.param != null && this.param.equalsIgnoreCase("NO")) {
            this.pSEARCHALL_BUTTON = false;
        }
        this.param = this.getParameter("SHOWALL_BUTTON");
        if (this.param != null && this.param.equalsIgnoreCase("NO")) {
            this.pSHOWALL_BUTTON = false;
        }
        this.param = this.getParameter("HELP_BUTTON");
        if (this.param != null && this.param.equalsIgnoreCase("NO")) {
            this.pHELP_BUTTON = false;
        }
        this.param = this.getParameter("FILTER_CHOICE");
        if (this.param != null && this.param.equalsIgnoreCase("NO")) {
            this.pFILTER_CHOICE = false;
        }
        this.param = this.getParameter("INDEX_CHOICE");
        if (this.param != null && this.param.equalsIgnoreCase("NO")) {
            this.pINDEX_CHOICE = false;
        }
        this.param = this.getParameter("KEY_FIELD");
        if (this.param != null && this.param.equalsIgnoreCase("NO")) {
            this.pKEY_FIELD = false;
            this.pSEARCHALL_BUTTON = false;
            this.pSEARCH_BUTTON = false;
        }
        int n;
        for (n = 0; this.getParameter("FIELD_" + Integer.toString(n)) != null; ++n) {}
        this.FIELDs = new Field[n];
        for (int i = 0; i < this.FIELDs.length; ++i) {
            this.FIELDs[i] = new Field();
        }
        for (int j = 0; j < this.FIELDs.length; ++j) {
            final StringTokenizer stringTokenizer6 = new StringTokenizer(this.getParameter("FIELD_" + Integer.toString(j)));
            if (stringTokenizer6.hasMoreTokens()) {
                this.FIELDs[j].Name = stringTokenizer6.nextToken();
            }
            if (stringTokenizer6.hasMoreTokens()) {
                try {
                    this.FIELDs[j].Width = Integer.parseInt(stringTokenizer6.nextToken());
                }
                catch (NumberFormatException ex2) {
                    System.out.println("Field " + j + " does not contain a valid WIDTH");
                }
            }
            while (stringTokenizer6.hasMoreTokens()) {
                final String nextToken = stringTokenizer6.nextToken();
                if (nextToken.equalsIgnoreCase("INDEXED") || nextToken.equalsIgnoreCase("Index")) {
                    this.FIELDs[j].INDEXED = true;
                }
                if (nextToken.equalsIgnoreCase("FILTER")) {
                    this.FIELDs[j].FILTER = true;
                    this.FIELDs[j].INDEXED = true;
                }
                if (nextToken.equalsIgnoreCase("NUMBER")) {
                    this.FIELDs[j].Type = 4;
                }
                if (nextToken.equalsIgnoreCase("INVISIBLE")) {
                    this.FIELDs[j].setInVisible();
                }
                if (nextToken.equalsIgnoreCase("SORT") || nextToken.equalsIgnoreCase("SORTED")) {
                    this.FIELDs[j].setSORTED();
                }
                if (nextToken.indexOf("URL") > -1) {
                    this.FIELDs[j].Type = Field.URL;
                    final StringTokenizer stringTokenizer7 = new StringTokenizer(nextToken, ",;");
                    stringTokenizer7.nextToken();
                    if (stringTokenizer7.hasMoreTokens()) {
                        this.FIELDs[j].BASE_URL = stringTokenizer7.nextToken();
                    }
                    if (stringTokenizer7.hasMoreTokens()) {
                        this.FIELDs[j].TARGET = stringTokenizer7.nextToken();
                    }
                }
                if (nextToken.indexOf("IMAGE") > -1) {
                    this.FIELDs[j].Type = Field.IMAGE;
                    final StringTokenizer stringTokenizer8 = new StringTokenizer(nextToken, ",;");
                    stringTokenizer8.nextToken();
                    if (stringTokenizer8.hasMoreTokens()) {
                        this.FIELDs[j].BASE_IMAGE = stringTokenizer8.nextToken();
                    }
                }
                if (nextToken.equalsIgnoreCase("MEMO")) {
                    this.FIELDs[j].setMemo();
                }
            }
        }
        this.setLayout(new GridLayout(1, 0));
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getCodeBase(), this.pIMAGE_URL);
        this.imageButtom = new ImageCanvas(this, 50, 50);
        if (image != null) {
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForID(0);
            }
            catch (InterruptedException ex3) {
                System.out.println("Image Load Failure");
            }
            this.imageButtom.setImage(image);
            this.add(this.imageButtom);
        }
        this.validate();
        this.ProgressBar = new MyDialog(this, 1, this.pFONT_SIZE);
        if (this.pRECORD_NUMBER != null) {
            this.ProgressBar.StatusLabel2.setText("Total Records " + this.pRECORD_NUMBER);
        }
        this.PasswdDlg = new MyDialog(this, 0, this.pFONT_SIZE);
        this.ExpiredDlg = new MyDialog(this, MyDialog.EXP_DLG, this.pFONT_SIZE);
        if (this.Encoded) {
            this.pSOURCE_FILE = this.ProgressBar.decode(this.pSOURCE_FILE, 1);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201 && this.window != null) {
            if (this.window.AboutDlg != null) {
                this.window.AboutDlg.dispose();
            }
            if (this.window.ImageDlg != null) {
                this.window.ImageDlg.dispose();
            }
            this.window.dispose();
        }
        if (event.id == 504 && event.target instanceof ImageCanvas) {
            this.showStatus(this.pMOUSE_MSG);
        }
        if (event.id == 505 && event.target instanceof ImageCanvas) {
            this.showStatus("");
        }
        if (event.id == 501 && event.target instanceof ImageCanvas) {
            if (this.Created) {
                if (this.pPASSWORD != null && !this.window.isVisible()) {
                    this.PasswdDlg.show();
                }
                else {
                    this.window.show();
                }
            }
            else {
                this.pleaseCreate = true;
                this.ProgressBar.show();
            }
        }
        return super.handleEvent(event);
    }
}
