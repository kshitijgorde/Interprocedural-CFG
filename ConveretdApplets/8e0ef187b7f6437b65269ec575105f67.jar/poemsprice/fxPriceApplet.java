// 
// Decompiled by Procyon v0.5.30
// 

package poemsprice;

import java.awt.Container;
import org.jdesktop.layout.GroupLayout;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import netscape.javascript.JSObject;
import java.awt.Image;
import java.awt.Label;
import java.util.Hashtable;
import java.net.URL;
import javax.swing.JPanel;
import poemsprice.FXClass.PriceViewer;
import java.applet.Applet;

public class fxPriceApplet extends Applet implements FeederConstant, ColorConstants, CommonConstants, Runnable
{
    private String[] aCurrency;
    private String[] aNonCurrency;
    private String[] aFeederCurrency;
    private String strCurrencyList;
    private String strNonCurrencyList;
    private int NonCurrencyLength;
    private int CurrencyLength;
    private PriceViewer[] objCurrency;
    private PriceViewer objTitle;
    private JPanel pnlBar;
    private JPanel pnlListView;
    private JPanel pnlGridView;
    private JPanel pnlMain;
    private JPanel pnlPrice;
    private JPanel pnlPriceList;
    private String strURLCodeBase;
    private String strURLBase;
    private String strJarFilename;
    private String strURLFeeder;
    private String strServiceName;
    private URL urlCodeBase;
    private fxRequester objRequester;
    private Hashtable objTblPricePanel;
    private Hashtable objTblPriceObject;
    private Hashtable tblDecimalDisp;
    private String strPriceDelayTime;
    private int intWidth;
    private int intHeight;
    private int intNRows;
    private int intNColumns;
    private int index;
    private int intDefaultOrderType;
    private String strDefautQty;
    public int gblDebugMode;
    private int intSingleClickOrderType;
    private boolean EnableQuickTrade;
    public static String strImgLocation;
    private String temp;
    private boolean CallBackMode;
    private boolean bTradingPageListView;
    private boolean bBasicView;
    private int intQuickTradeEnableTime;
    String iProdType;
    String bTemplateNo;
    String strDecimalDisp;
    int intEnableMarketTrading;
    private boolean EnableMarketTrade;
    private Thread trd;
    private boolean AppletLoaded;
    private JPanel pnlMessage;
    private Label lblMessage;
    private String urlName;
    private String UpArrow;
    private String downArrow;
    private String NoChange;
    private String Normal;
    private Image imgUp;
    private Image imgDown;
    private Image imgNormal;
    private Image imgNoChange;
    private Image[] nImages;
    private URL url1;
    private URL url2;
    private URL url3;
    private URL url4;
    private boolean isValidHost;
    
    public fxPriceApplet() {
        this.EnableMarketTrade = false;
        this.AppletLoaded = false;
        this.isValidHost = false;
        this.strCurrencyList = "";
        this.strNonCurrencyList = "";
        this.NonCurrencyLength = 0;
        this.CurrencyLength = 0;
        this.index = 0;
        this.gblDebugMode = 0;
        this.intSingleClickOrderType = -1;
        this.EnableQuickTrade = false;
        this.CallBackMode = false;
        this.bTradingPageListView = false;
        this.bBasicView = false;
        this.intQuickTradeEnableTime = 30;
        this.iProdType = "1";
        this.bTemplateNo = "1";
        this.strDecimalDisp = "";
        this.intEnableMarketTrading = 0;
    }
    
    private int InitilizeParameterValue() {
        try {
            this.iProdType = "2";
            this.bTemplateNo = "2";
            fxPriceApplet.strImgLocation = "http://poems.cyberquote.com.sg/htm/Gifs/_1024_768/Template2";
            this.strURLFeeder = "http://wdsfut03.poems.com.sg:80/FX100KFeeder/ignore";
            this.strServiceName = "FX100KFeeder";
            this.gblDebugMode = 0;
            this.intNRows = 1;
            this.intNColumns = 6;
            this.bTradingPageListView = false;
            this.EnableQuickTrade = false;
            this.EnableMarketTrade = false;
            this.strDefautQty = "1";
            this.intDefaultOrderType = 2;
            this.intSingleClickOrderType = 1;
            this.intEnableMarketTrading = 0;
            this.bBasicView = true;
            this.CallBackMode = false;
            this.intQuickTradeEnableTime = 60;
            this.temp = "EURUSD[1];USDJPY[1];GBPUSD[1];USDCHF[1];AUDUSD[1];USDCAD[1];NZDUSD[1];EURJPY[1];EURGBP[1];EURCHF[1];GBPJPY[1];USDSGD[1];GLDUSD[1];";
            if (this.temp != null) {
                this.strNonCurrencyList = this.temp;
                if (this.strNonCurrencyList.trim().length() > 0) {
                    this.aNonCurrency = this.strNonCurrencyList.split(";");
                    this.NonCurrencyLength = this.aNonCurrency.length;
                }
                else {
                    this.NonCurrencyLength = 0;
                }
            }
            this.aFeederCurrency = new String[this.CurrencyLength + this.NonCurrencyLength];
            this.index = 0;
            while (this.index < this.CurrencyLength) {
                this.aFeederCurrency[this.index] = this.aCurrency[this.index];
                ++this.index;
            }
            for (int lp = 0; lp < this.NonCurrencyLength; ++lp) {
                this.aFeederCurrency[this.index] = this.aNonCurrency[lp];
                ++this.index;
            }
            this.index = 0;
            this.temp = "EURUSD[1]-4;USDJPY[1]-2;GBPUSD[1]-4;USDCHF[1]-4;AUDUSD[1]-4;USDCAD[1]-4;NZDUSD[1]-4;EURJPY[1]-2;EURGBP[1]-4;EURCHF[1]-4;GBPJPY[1]-2;USDSGD[1]-4;GLDUSD[1]-2;";
            if (this.temp != null && this.temp.length() > 0) {
                this.temp = this.temp.toString().trim() + "";
                this.strDecimalDisp = this.temp;
            }
            this.tblDecimalDisp = new Hashtable();
            final String[] tmp2 = this.strDecimalDisp.split(";");
            for (int j = 0; j < tmp2.length; ++j) {
                final String[] tmp3 = tmp2[j].split("-");
                if (tmp3.length == 2) {
                    this.tblDecimalDisp.put(tmp3[0].toString().trim(), tmp3[1].toString());
                }
            }
        }
        catch (Exception exp) {
            System.out.println("[InitilizeParameterValue][Error][" + exp.getMessage() + "]");
            exp.printStackTrace();
            return -1;
        }
        return 0;
    }
    
    public void init() {
        URL CurrentURL = null;
        this.isValidHost = false;
        String host = this.getCodeBase().getHost();
        final String TargetHost = "www.phillipfutures.com.sg";
        final JSObject jso = JSObject.getWindow(this);
        final Object locObj = jso.getMember("location");
        if (locObj == null) {
            System.out.println("locObj is null");
            return;
        }
        try {
            final Object hrefObj = ((JSObject)locObj).getMember("href");
            if (hrefObj == null) {
                System.out.println("hrefObj is null");
                return;
            }
            System.out.println("href=" + hrefObj.toString());
            CurrentURL = new URL(hrefObj.toString());
            host = CurrentURL.getHost();
        }
        catch (Exception e) {
            System.err.println("Couldn't get Current address: Unknown host");
        }
        System.out.println("Where is come from:" + host);
        final boolean equalsIgnoreCase = host.equalsIgnoreCase(TargetHost);
        this.isValidHost = equalsIgnoreCase;
        if (equalsIgnoreCase) {
            try {
                this.objTblPricePanel = new Hashtable();
                this.objTblPriceObject = new Hashtable();
                this.urlCodeBase = this.getCodeBase();
                this.strURLCodeBase = this.urlCodeBase.toString();
                this.trd = new Thread(this);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("Connection Error");
        }
        this.setBackground(ColorConstants.gblVarAppletbgColor);
        this.initComponents();
    }
    
    private void initComponents() {
        (this.lblMessage = new Label()).setText("Applet Initilizing");
        this.lblMessage.setAlignment(1);
        this.lblMessage.setSize(750, 75);
        this.lblMessage.setLocation(5, 20);
        this.lblMessage.setForeground(new Color(255, 0, 0));
        (this.pnlMessage = new JPanel()).setSize(800, 100);
        this.pnlMessage.setLocation(0, 0);
        this.pnlMessage.setLayout(new BorderLayout());
        this.pnlMessage.add(this.lblMessage, "Center");
        this.setLayout(new BorderLayout());
        this.add(this.pnlMessage, "Center");
    }
    
    public void start() {
        System.out.println("Starting applet....");
        if (this.isValidHost) {
            this.setVisible(true);
            this.lblMessage.setText("Applet Started");
            System.out.println("Applet Started");
            try {
                this.trd.start();
                this.AppletLoaded = true;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else {
            System.out.println("Connection error....");
            this.lblMessage.setText("Connection Error");
        }
    }
    
    public void run() {
        try {
            while (!this.AppletLoaded) {}
            this.lblMessage.setText("Initilizing parameter information...");
            if (this.InitilizeParameterValue() == 0) {
                this.lblMessage.setText("Parameter Initilized");
                final URL ServerURL = new URL(this.strURLFeeder);
                final String proxyURL = "";
                this.objRequester = new fxRequester(ServerURL, proxyURL, this.strServiceName, this.aFeederCurrency);
                this.lblMessage.setText("Data request from server");
                System.out.println("Data request from server");
                if (this.objRequester.start()) {
                    this.lblMessage.setText("Data request sucessful!");
                    System.out.println("Data request sucessful!");
                    if (1 <= this.gblDebugMode) {
                        System.out.println("Feeder Started");
                    }
                }
                else {
                    this.lblMessage.setText("Data request fail");
                    this.objRequester.stop();
                }
                this.lblMessage.setText("Image Loading ...");
                this.LoadImage();
                this.lblMessage.setText("Image Loaded");
                this.index = 0;
                this.lblMessage.setText("Loading grid format price panel...");
                this.doGridFormatDisplay();
                this.lblMessage.setText("Grid format price panel loaded");
                this.lblMessage.setText("Loading list format price panel...");
                this.doListFormatDisplay(this.bTradingPageListView);
                this.lblMessage.setText("List format price panel loaded");
                System.out.println("All price panel loaded...");
                this.objRequester.setPricePanelObject(this.objTblPricePanel);
                this.objRequester.setPriceObject(this.objTblPriceObject);
            }
            this.objRequester.gblDebugMode = this.gblDebugMode;
            this.remove(this.pnlMessage);
            this.initPriceComponents();
            if (this.objTitle != null) {
                this.objTitle.resetThisObjASTitle();
            }
            int iW = this.pnlPrice.getWidth();
            int iH = this.pnlPrice.getHeight();
            ++iW;
            ++iH;
            this.setSize(iW, iH);
            this.repaint();
            final Thread trd = this.trd;
            Thread.sleep(100L);
            this.trd.stop();
            this.trd.join();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void LoadImage() {
        this.UpArrow = "UPArrow.gif";
        this.downArrow = "downArrow.gif";
        this.NoChange = "NoChange.gif";
        this.Normal = "Normal.gif";
        String strImageLocation = "";
        if (fxPriceApplet.strImgLocation.trim().length() > 0) {
            strImageLocation = fxPriceApplet.strImgLocation.trim() + "/";
        }
        if (strImageLocation.trim().length() > 0) {
            try {
                this.url1 = new URL(strImageLocation + this.UpArrow);
                this.url2 = new URL(strImageLocation + this.downArrow);
                this.url3 = new URL(strImageLocation + this.NoChange);
                this.url4 = new URL(strImageLocation + this.Normal);
                this.imgUp = Toolkit.getDefaultToolkit().getImage(this.url1);
                this.imgDown = Toolkit.getDefaultToolkit().getImage(this.url2);
                this.imgNoChange = Toolkit.getDefaultToolkit().getImage(this.url3);
                this.imgNormal = Toolkit.getDefaultToolkit().getImage(this.url4);
                (this.nImages = new Image[4])[0] = this.imgUp;
                this.nImages[1] = this.imgDown;
                this.nImages[2] = this.imgNoChange;
                this.nImages[3] = this.imgNormal;
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private void doGridFormatDisplay() {
        try {
            (this.pnlPrice = new JPanel()).setLayout(new GridLayout(this.intNRows, this.intNColumns, 1, 0));
            this.pnlPrice.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            this.objCurrency = new PriceViewer[this.CurrencyLength + this.NonCurrencyLength];
            this.index = 0;
            while (this.index < this.CurrencyLength) {
                this.objCurrency[this.index] = new PriceViewer(this.intEnableMarketTrading, this.nImages);
                this.objCurrency[this.index].objName = this.aCurrency[this.index];
                this.objCurrency[this.index].setAppletSource(this);
                this.objCurrency[this.index].setDebugMode(this.gblDebugMode);
                this.objCurrency[this.index].setSingleClickOrder(this.EnableMarketTrade);
                this.objCurrency[this.index].setSingleClickOrderType(this.intSingleClickOrderType);
                this.objCurrency[this.index].setDefaultOrderType(this.intDefaultOrderType);
                this.objCurrency[this.index].setSysmbol(this.aCurrency[this.index].trim(), this.iProdType);
                this.objCurrency[this.index].setTitle(this.aCurrency[this.index].trim().substring(0, 6));
                this.objCurrency[this.index].setCallBackMode(this.CallBackMode);
                this.objCurrency[this.index].setDecimalDisp(Integer.parseInt(this.tblDecimalDisp.get(this.aCurrency[this.index]).toString()));
                this.objCurrency[this.index].setQtyList(this.aCurrency[this.index].trim(), this.iProdType, this.strDefautQty);
                this.objCurrency[this.index].SetBullion(this.aCurrency[this.index].trim());
                this.pnlPrice.add(this.objCurrency[this.index]);
                this.objTblPricePanel.put(this.aCurrency[this.index].trim(), this.objCurrency[this.index]);
                this.objCurrency[this.index].setColorTemplate(this.bTemplateNo, false, false);
                ++this.index;
            }
            if (!this.bBasicView) {
                for (int j = this.CurrencyLength; j < this.intNRows * this.intNColumns; ++j) {
                    this.pnlPrice.add(new JPanel());
                }
            }
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void doListFormatDisplay(final boolean bTradePage) {
        try {
            (this.pnlPriceList = new JPanel()).setVisible(true);
            if (this.NonCurrencyLength > 0) {
                if (this.bBasicView || this.bTradingPageListView) {
                    this.pnlPriceList.setLayout(new GridLayout(this.NonCurrencyLength + 1, 1, 0, 1));
                    this.pnlPriceList.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    (this.objTitle = new PriceViewer(true, bTradePage, this.intEnableMarketTrading, this.nImages)).setSysmbol("Name", this.iProdType);
                    this.objTitle.setBackground(this.objTitle.getColortemplateTitle(this.bTemplateNo));
                }
                for (int lp = 0; lp < this.NonCurrencyLength; ++lp) {
                    if (!this.bBasicView && !this.bTradingPageListView) {
                        final fxPrice priceObject = new fxPrice();
                        this.objTblPriceObject.put(this.aNonCurrency[lp].trim(), priceObject);
                    }
                    else {
                        this.objCurrency[this.index] = new PriceViewer(false, bTradePage, this.intEnableMarketTrading, true, this.nImages);
                        this.objCurrency[this.index].objName = this.aNonCurrency[lp];
                        this.objCurrency[this.index].setAppletSource(this);
                        this.objCurrency[this.index].setDebugMode(this.gblDebugMode);
                        this.objCurrency[this.index].setSingleClickOrder(this.EnableMarketTrade);
                        this.objCurrency[this.index].setSingleClickOrderType(this.intSingleClickOrderType);
                        this.objCurrency[this.index].setDefaultOrderType(this.intDefaultOrderType);
                        this.objCurrency[this.index].setDefaultQty(this.strDefautQty);
                        this.objCurrency[this.index].setSysmbol(this.aNonCurrency[lp].trim(), this.iProdType);
                        this.objCurrency[this.index].setTitle(this.aNonCurrency[lp].trim().substring(0, 6));
                        this.objCurrency[this.index].setCallBackMode(this.CallBackMode);
                        this.objCurrency[this.index].setDecimalDisp(Integer.parseInt(this.tblDecimalDisp.get(this.aNonCurrency[lp]).toString()));
                        this.objCurrency[this.index].SetBullion(this.aNonCurrency[lp].toString().trim());
                        if (lp == 0) {
                            this.pnlPriceList.add(this.objTitle);
                        }
                        this.pnlPriceList.add(this.objCurrency[this.index]);
                        this.objTblPricePanel.put(this.aNonCurrency[lp].trim(), this.objCurrency[this.index]);
                        if (lp % 2 != 0) {
                            this.objCurrency[this.index].setColorTemplate(this.bTemplateNo, true, false);
                        }
                        else {
                            this.objCurrency[this.index].setColorTemplate(this.bTemplateNo, true, true);
                        }
                        ++this.index;
                    }
                }
            }
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void initPriceComponents() {
        final GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.pnlPrice, -2, -1, -2).add((Component)this.pnlPriceList, -2, -1, -2).add((GroupLayout.Group)layout.createSequentialGroup()));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.pnlPrice, -2, -1, -2).add((Component)this.pnlPriceList, -2, -1, -2).addContainerGap()));
    }
    
    public void stop() {
        if (this.objRequester != null) {
            this.objRequester.bAppletClosing = true;
            this.objRequester.stop();
        }
    }
    
    public void setQuickTradeEnable(final boolean Value) {
        for (int i = 0; i < this.objCurrency.length; ++i) {
            this.objCurrency[i].setSingleClickOrder(Value);
        }
    }
    
    public String getFieldValue(final String keyID, final short keyfield) {
        if (this.objRequester != null) {
            return this.objRequester.getFieldValue(keyID.trim(), keyfield);
        }
        return "";
    }
    
    static {
        fxPriceApplet.strImgLocation = "http://10.30.2.70/htm/CSK/Gifs";
    }
}
