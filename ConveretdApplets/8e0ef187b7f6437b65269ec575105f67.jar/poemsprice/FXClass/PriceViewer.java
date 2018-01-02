// 
// Decompiled by Procyon v0.5.30
// 

package poemsprice.FXClass;

import java.util.TimerTask;
import java.util.Timer;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import org.jdesktop.layout.GroupLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Color;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.Image;
import poemsprice.fxPriceApplet;
import poemsprice.fxPrice;
import java.awt.Label;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import poemsprice.CommonConstants;
import poemsprice.ColorConstants;
import javax.swing.JPanel;

public class PriceViewer extends JPanel implements ColorConstants, CommonConstants
{
    private JButton btnBuy;
    private JButton btnSell;
    private JComboBox drpQty;
    private JLabel lblBid;
    private JLabel lblBidPrice1;
    private JLabel lblBidPrice2;
    private ImgCanvas lblImg;
    private JLabel lblOffer;
    private JLabel lblOfferPrice1;
    private JLabel lblOfferPrice2;
    private JLabel lblQty;
    private JLabel lblQtyIndicator;
    private JLabel lblTitle;
    private JPanel pnlBidPad;
    private Label lblSeperator1;
    private Label lblSeperator2;
    private Label lblSeperator3;
    private JPanel pnlSeperator;
    private JPanel pnlBidPrice;
    private JPanel pnlOfferPad;
    private JPanel pnlOfferPrice;
    private JPanel pnlPriceViewer;
    private JPanel pnlQty;
    private JPanel pnlTitle;
    private JLabel lblHigh;
    private JLabel lblLow;
    private JLabel lblName;
    private boolean bEnableTitle;
    public String objName;
    public fxPrice myData;
    private String strSymbol;
    private int DefaultOrderType;
    private String DefaultQty;
    private fxPriceApplet objParentApplet;
    private String strSelectedQty;
    private int gblDebugMode;
    private boolean CallBackMode;
    private boolean SingleClickOrder;
    private int intSingleClickOrderType;
    private int intDefaultOrderType;
    private int intDisplayType;
    private String[] QtyList;
    private Reminder refreshTime;
    private int intDecimalDisp;
    private int intEnableMarketTrading;
    private boolean bFirstTime;
    private boolean isList;
    private boolean isBullion;
    private Image[] nImage;
    
    public PriceViewer(final int EnableMarketTrading, final Image[] images) throws MalformedURLException {
        this.SingleClickOrder = false;
        this.QtyList = new String[0];
        this.bFirstTime = true;
        this.isList = false;
        this.isBullion = false;
        this.bEnableTitle = false;
        this.DefaultQty = "0";
        this.CallBackMode = false;
        this.intSingleClickOrderType = -1;
        this.intDefaultOrderType = -1;
        this.intDisplayType = 0;
        this.refreshTime = new Reminder();
        this.intDecimalDisp = 4;
        this.intEnableMarketTrading = EnableMarketTrading;
        this.nImage = images;
        this.InitData();
        if (this.intDisplayType == 0) {
            this.initComponents();
            this.pnlPriceViewer.setVisible(true);
        }
        else {
            this.initListComponents(false);
        }
    }
    
    public PriceViewer(final boolean bTitle, final int EnableMarketTrading, final Image[] images) throws MalformedURLException {
        this.SingleClickOrder = false;
        this.QtyList = new String[0];
        this.bFirstTime = true;
        this.isList = false;
        this.isBullion = false;
        this.bEnableTitle = false;
        this.DefaultQty = "0";
        this.CallBackMode = false;
        this.intSingleClickOrderType = -1;
        this.intDefaultOrderType = -1;
        this.intDisplayType = 0;
        this.refreshTime = new Reminder();
        this.intDecimalDisp = 4;
        this.intEnableMarketTrading = EnableMarketTrading;
        this.intDisplayType = 1;
        this.nImage = images;
        this.InitData();
        this.initListComponents(this.bEnableTitle = bTitle);
    }
    
    public PriceViewer(final boolean bTitle, final boolean tradePage, final int EnableMarketTrading, final Image[] images) throws MalformedURLException {
        this.SingleClickOrder = false;
        this.QtyList = new String[0];
        this.bFirstTime = true;
        this.isList = false;
        this.isBullion = false;
        this.bEnableTitle = false;
        this.DefaultQty = "0";
        this.CallBackMode = false;
        this.intSingleClickOrderType = -1;
        this.intDefaultOrderType = -1;
        this.intDisplayType = 0;
        this.refreshTime = new Reminder();
        this.intDecimalDisp = 4;
        this.intEnableMarketTrading = EnableMarketTrading;
        this.intDisplayType = 1;
        if (tradePage) {
            this.intDisplayType = 2;
        }
        this.nImage = images;
        this.InitData();
        this.initListComponents(this.bEnableTitle = bTitle);
    }
    
    public PriceViewer(final boolean bTitle, final boolean tradePage, final int EnableMarketTrading, final boolean isListFormat, final Image[] images) throws MalformedURLException {
        this.SingleClickOrder = false;
        this.QtyList = new String[0];
        this.bFirstTime = true;
        this.isList = false;
        this.isBullion = false;
        this.bEnableTitle = false;
        this.DefaultQty = "0";
        this.CallBackMode = false;
        this.intSingleClickOrderType = -1;
        this.intDefaultOrderType = -1;
        this.intDisplayType = 0;
        this.refreshTime = new Reminder();
        this.intDecimalDisp = 4;
        this.intEnableMarketTrading = EnableMarketTrading;
        this.intDisplayType = 1;
        if (tradePage) {
            this.intDisplayType = 2;
        }
        this.nImage = images;
        this.InitData();
        this.initListComponents(this.bEnableTitle = bTitle);
        this.isList = isListFormat;
    }
    
    public void setSysmbol(final String nStrSymbol, final String ProdType) {
        this.strSymbol = nStrSymbol;
        this.myData.setContract(this.strSymbol);
        if (this.intDisplayType == 0) {
            this.lblQtyIndicator.setText("X " + this.getUnit(this.strSymbol, ProdType));
        }
    }
    
    private void initComponents() {
        this.pnlPriceViewer = new JPanel();
        this.pnlTitle = new JPanel();
        this.lblTitle = new JLabel();
        try {
            this.lblImg = new ImgCanvas(this.nImage[0], this.nImage[1], this.nImage[2], this.nImage[3]);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        this.pnlBidPad = new JPanel();
        this.lblBid = new JLabel();
        this.pnlBidPrice = new JPanel();
        this.lblBidPrice1 = new JLabel();
        this.lblBidPrice2 = new JLabel();
        this.btnSell = new JButton();
        this.pnlQty = new JPanel();
        this.lblQty = new JLabel();
        this.drpQty = new JComboBox();
        this.lblQtyIndicator = new JLabel();
        this.pnlOfferPad = new JPanel();
        this.lblOffer = new JLabel();
        this.pnlOfferPrice = new JPanel();
        this.lblOfferPrice1 = new JLabel();
        this.lblOfferPrice2 = new JLabel();
        this.btnBuy = new JButton();
        this.pnlSeperator = new JPanel();
        this.lblSeperator1 = new Label();
        this.lblSeperator2 = new Label();
        this.lblSeperator3 = new Label();
        this.setBackground(new Color(242, 242, 242));
        this.setAlignmentX(0.0f);
        this.setAlignmentY(0.0f);
        this.setMaximumSize(new Dimension(138, 115));
        this.pnlPriceViewer.setBackground(new Color(242, 242, 242));
        this.pnlPriceViewer.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 255)));
        this.pnlTitle.setBackground(new Color(180, 93, 0));
        this.lblTitle.setBackground(new Color(180, 93, 0));
        this.lblTitle.setFont(new Font("Arial", 1, 14));
        this.lblTitle.setHorizontalAlignment(0);
        this.lblTitle.setText("XXXXXX ");
        this.lblImg.setImageType(0);
        final GroupLayout pnlTitleLayout = new GroupLayout((Container)this.pnlTitle);
        this.pnlTitle.setLayout((LayoutManager)pnlTitleLayout);
        pnlTitleLayout.setHorizontalGroup((GroupLayout.Group)pnlTitleLayout.createParallelGroup(1).add(2, (GroupLayout.Group)pnlTitleLayout.createSequentialGroup().addContainerGap(36, 32767).add((Component)this.lblTitle, -2, 73, -2).addPreferredGap(0, 17, 32767).add((Component)this.lblImg, -2, -1, -2)));
        pnlTitleLayout.setVerticalGroup((GroupLayout.Group)pnlTitleLayout.createParallelGroup(1).add((Component)this.lblTitle).add((Component)this.lblImg, -2, -1, -2));
        this.pnlSeperator.setBackground(new Color(242, 242, 242));
        this.lblSeperator1.setBackground(new Color(242, 242, 242));
        this.lblSeperator1.setText("lblSeperator1");
        this.lblSeperator2.setBackground(new Color(153, 153, 153));
        this.lblSeperator2.setText("lblSeperator1");
        this.lblSeperator3.setBackground(new Color(242, 242, 242));
        this.lblSeperator3.setText("lblSeperator1");
        this.pnlBidPad.setBackground(new Color(242, 242, 242));
        this.pnlBidPad.setToolTipText("Click to Sell");
        this.pnlBidPad.setAlignmentX(0.9f);
        this.pnlBidPad.setAlignmentY(0.0f);
        this.lblBid.setFont(new Font("Arial", 1, 11));
        this.lblBid.setHorizontalAlignment(0);
        this.lblBid.setText("Bid");
        this.lblBid.setVerticalAlignment(1);
        this.pnlBidPrice.setBackground(new Color(242, 242, 242));
        this.lblBidPrice1.setFont(new Font("Arial", 1, 22));
        this.lblBidPrice1.setForeground(new Color(204, 204, 204));
        this.lblBidPrice1.setHorizontalAlignment(4);
        this.lblBidPrice1.setText("     -");
        this.lblBidPrice1.setVerticalAlignment(3);
        this.lblBidPrice1.setHorizontalTextPosition(2);
        this.lblBidPrice1.setVerticalTextPosition(3);
        this.lblBidPrice2.setFont(new Font("Arial", 1, 22));
        this.lblBidPrice2.setForeground(new Color(204, 204, 204));
        this.lblBidPrice2.setHorizontalAlignment(2);
        this.lblBidPrice2.setText("     ");
        this.lblBidPrice2.setVerticalAlignment(3);
        this.lblBidPrice2.setHorizontalTextPosition(2);
        this.lblBidPrice2.setVerticalTextPosition(3);
        final GroupLayout pnlBidPriceLayout = new GroupLayout((Container)this.pnlBidPrice);
        this.pnlBidPrice.setLayout((LayoutManager)pnlBidPriceLayout);
        pnlBidPriceLayout.setHorizontalGroup((GroupLayout.Group)pnlBidPriceLayout.createParallelGroup(1).add(2, (GroupLayout.Group)pnlBidPriceLayout.createSequentialGroup().add((Component)this.lblBidPrice1).add((Component)this.lblBidPrice2)));
        pnlBidPriceLayout.setVerticalGroup((GroupLayout.Group)pnlBidPriceLayout.createParallelGroup(1).add(2, (Component)this.lblBidPrice1, -2, 26, -2).add(2, (Component)this.lblBidPrice2, -2, 26, -2));
        this.btnSell.setBackground(new Color(254, 204, 255));
        this.btnSell.setFont(new Font("Arial", 1, 10));
        this.btnSell.setText("Sell");
        this.btnSell.setToolTipText("Click to Sell");
        this.btnSell.setAlignmentY(0.0f);
        this.btnSell.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        this.btnSell.setMargin(new Insets(0, 0, 0, 0));
        this.btnSell.setMaximumSize(new Dimension(25, 15));
        this.btnSell.setMinimumSize(new Dimension(25, 15));
        this.btnSell.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PriceViewer.this.btnSellActionPerformed(evt);
            }
        });
        final GroupLayout pnlSeperatorLayout = new GroupLayout((Container)this.pnlSeperator);
        this.pnlSeperator.setLayout((LayoutManager)pnlSeperatorLayout);
        pnlSeperatorLayout.setHorizontalGroup((GroupLayout.Group)pnlSeperatorLayout.createParallelGroup(1).add((GroupLayout.Group)pnlSeperatorLayout.createSequentialGroup().add((Component)this.lblSeperator1, -2, 2, -2).add((Component)this.lblSeperator2, -2, 2, -2).add((Component)this.lblSeperator3, -2, 2, -2)));
        pnlSeperatorLayout.setVerticalGroup((GroupLayout.Group)pnlSeperatorLayout.createParallelGroup(1).add((Component)this.lblSeperator1, -2, 47, -2).add((Component)this.lblSeperator2, -2, 47, -2).add((Component)this.lblSeperator3, -2, 47, -2));
        final GroupLayout pnlBidPadLayout = new GroupLayout((Container)this.pnlBidPad);
        this.pnlBidPad.setLayout((LayoutManager)pnlBidPadLayout);
        pnlBidPadLayout.setHorizontalGroup((GroupLayout.Group)pnlBidPadLayout.createParallelGroup(1).add(2, (GroupLayout.Group)pnlBidPadLayout.createSequentialGroup().add((GroupLayout.Group)pnlBidPadLayout.createParallelGroup(2).add(1, (Component)this.btnSell, -2, 68, -2).add(1, (Component)this.lblBid, -1, 68, 32767).add((Component)this.pnlBidPrice, -1, 68, 32767)).addContainerGap()));
        pnlBidPadLayout.setVerticalGroup((GroupLayout.Group)pnlBidPadLayout.createParallelGroup(1).add((GroupLayout.Group)pnlBidPadLayout.createSequentialGroup().add((Component)this.lblBid).add((Component)this.pnlBidPrice, -1, -1, 32767).addPreferredGap(0).add((Component)this.btnSell, -2, 15, -2)));
        this.pnlBidPad.getAccessibleContext().setAccessibleName("pnlBid");
        this.pnlQty.setBackground(new Color(242, 242, 242));
        this.lblQty.setFont(new Font("Arial", 1, 10));
        this.lblQty.setText(" Qty");
        this.drpQty.setEditable(true);
        this.drpQty.setFont(new Font("Arial", 1, 10));
        this.lblQtyIndicator.setFont(new Font("Arial", 1, 10));
        this.lblQtyIndicator.setText("X 5000 Oz");
        final GroupLayout pnlQtyLayout = new GroupLayout((Container)this.pnlQty);
        this.pnlQty.setLayout((LayoutManager)pnlQtyLayout);
        pnlQtyLayout.setHorizontalGroup((GroupLayout.Group)pnlQtyLayout.createParallelGroup(1).add((GroupLayout.Group)pnlQtyLayout.createSequentialGroup().addContainerGap().add((Component)this.lblQty).addPreferredGap(0, 25, 32767).add((Component)this.drpQty, -2, 45, -2).add((Component)this.lblQtyIndicator, -1, -1, 32767).addPreferredGap(0, 25, 32767)));
        pnlQtyLayout.setVerticalGroup((GroupLayout.Group)pnlQtyLayout.createParallelGroup(1).add((GroupLayout.Group)pnlQtyLayout.createParallelGroup(3).add((Component)this.lblQty, -2, 14, -2).add((Component)this.drpQty, -2, 14, -2).add((Component)this.lblQtyIndicator, -2, 14, -2)));
        this.pnlOfferPad.setBackground(new Color(242, 242, 242));
        this.pnlOfferPad.setToolTipText("Click to Buy");
        this.pnlOfferPad.setAlignmentX(0.0f);
        this.pnlOfferPad.setAlignmentY(0.0f);
        this.lblOffer.setFont(new Font("Arial", 1, 11));
        this.lblOffer.setHorizontalAlignment(0);
        this.lblOffer.setText("Offer");
        this.lblOffer.setVerticalAlignment(1);
        this.pnlOfferPrice.setBackground(new Color(242, 242, 242));
        this.lblOfferPrice1.setFont(new Font("Arial", 1, 22));
        this.lblOfferPrice1.setForeground(new Color(204, 204, 204));
        this.lblOfferPrice1.setHorizontalAlignment(4);
        this.lblOfferPrice1.setText("     -");
        this.lblOfferPrice1.setVerticalAlignment(3);
        this.lblOfferPrice1.setHorizontalTextPosition(2);
        this.lblOfferPrice1.setVerticalTextPosition(3);
        this.lblOfferPrice2.setFont(new Font("Arial", 1, 22));
        this.lblOfferPrice2.setForeground(new Color(204, 204, 204));
        this.lblOfferPrice2.setHorizontalAlignment(2);
        this.lblOfferPrice2.setText("     ");
        this.lblOfferPrice2.setVerticalAlignment(3);
        this.lblOfferPrice2.setHorizontalTextPosition(2);
        this.lblOfferPrice2.setVerticalTextPosition(3);
        final GroupLayout pnlOfferPriceLayout = new GroupLayout((Container)this.pnlOfferPrice);
        this.pnlOfferPrice.setLayout((LayoutManager)pnlOfferPriceLayout);
        pnlOfferPriceLayout.setHorizontalGroup((GroupLayout.Group)pnlOfferPriceLayout.createParallelGroup(1).add(2, (GroupLayout.Group)pnlOfferPriceLayout.createSequentialGroup().add((Component)this.lblOfferPrice1).add((Component)this.lblOfferPrice2)));
        pnlOfferPriceLayout.setVerticalGroup((GroupLayout.Group)pnlOfferPriceLayout.createParallelGroup(1).add(2, (Component)this.lblOfferPrice1, -2, 26, -2).add(2, (Component)this.lblOfferPrice2, -2, 26, -2));
        this.btnBuy.setBackground(new Color(153, 205, 255));
        this.btnBuy.setFont(new Font("Arial", 1, 10));
        this.btnBuy.setText("Buy");
        this.btnBuy.setToolTipText("Click to Buy");
        this.btnBuy.setAlignmentY(0.0f);
        this.btnBuy.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        this.btnBuy.setMargin(new Insets(0, 0, 0, 0));
        this.btnBuy.setMaximumSize(new Dimension(25, 15));
        this.btnBuy.setMinimumSize(new Dimension(25, 15));
        this.btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                PriceViewer.this.btnBuyActionPerformed(evt);
            }
        });
        this.btnBuy.getAccessibleContext().setAccessibleName("");
        final GroupLayout pnlOfferPadLayout = new GroupLayout((Container)this.pnlOfferPad);
        this.pnlOfferPad.setLayout((LayoutManager)pnlOfferPadLayout);
        pnlOfferPadLayout.setHorizontalGroup((GroupLayout.Group)pnlOfferPadLayout.createParallelGroup(1).add((GroupLayout.Group)pnlOfferPadLayout.createSequentialGroup().add((GroupLayout.Group)pnlOfferPadLayout.createParallelGroup(1).add((Component)this.lblOffer, -1, 68, 32767).add(2, (Component)this.pnlOfferPrice, -1, 68, 32767).add((Component)this.btnBuy, -2, 68, -2)).addContainerGap()));
        pnlOfferPadLayout.setVerticalGroup((GroupLayout.Group)pnlOfferPadLayout.createParallelGroup(1).add((GroupLayout.Group)pnlOfferPadLayout.createSequentialGroup().add((Component)this.lblOffer, -1, -1, 32767).add((Component)this.pnlOfferPrice, -1, -1, 32767).addPreferredGap(0).add((Component)this.btnBuy, -2, 15, -2)));
        final GroupLayout pnlPriceViewerLayout = new GroupLayout((Container)this.pnlPriceViewer);
        this.pnlPriceViewer.setLayout((LayoutManager)pnlPriceViewerLayout);
        pnlPriceViewerLayout.setHorizontalGroup((GroupLayout.Group)pnlPriceViewerLayout.createParallelGroup(1).add((Component)this.pnlQty, -2, 136, -2).add((Component)this.pnlTitle, 0, -1, 32767).add((GroupLayout.Group)pnlPriceViewerLayout.createSequentialGroup().add((Component)this.pnlBidPad, -2, 68, -2).add((Component)this.pnlSeperator, -2, 6, -2).add((Component)this.pnlOfferPad, -2, 68, -2)));
        pnlPriceViewerLayout.setVerticalGroup((GroupLayout.Group)pnlPriceViewerLayout.createParallelGroup(1).add((GroupLayout.Group)pnlPriceViewerLayout.createSequentialGroup().add((Component)this.pnlTitle, -2, -1, -2).add((GroupLayout.Group)pnlPriceViewerLayout.createParallelGroup(1).add((Component)this.pnlBidPad, -2, -1, -2).add((Component)this.pnlSeperator, -2, -1, -2).add((Component)this.pnlOfferPad, -2, -1, -2)).add((Component)this.pnlQty, -2, -1, -2)));
        final GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.pnlPriceViewer, -2, -1, -2));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((Component)this.pnlPriceViewer, -2, 100, -2));
    }
    
    public void setEnableAlternaticeBackColor(final boolean value) {
        if (value) {
            this.setBackground(new Color(204, 238, 255));
        }
        else {
            this.setBackground(new Color(255, 255, 255));
        }
    }
    
    public void resetThisObjASTitle() {
        this.lblName.setText("Name");
        this.lblBidPrice1.setText("Rates");
        this.lblOfferPrice1.setText("---");
        this.lblHigh.setText("----");
        this.lblLow.setText("----");
        this.lblTitle.setText("Currency");
        this.lblImg.setImageType(0);
        this.resetToDefautColor();
    }
    
    private void initListComponents(final boolean bTitle) {
        this.lblHigh = new JLabel();
        this.lblLow = new JLabel();
        this.lblOfferPrice1 = new JLabel();
        this.lblBidPrice1 = new JLabel();
        this.lblTitle = new JLabel();
        this.lblName = new JLabel();
        try {
            this.lblImg = new ImgCanvas(this.nImage[0], this.nImage[1], this.nImage[2], this.nImage[3]);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        if (bTitle) {
            this.setBackground(new Color(240, 240, 240));
        }
        else {
            this.setBackground(new Color(255, 255, 255));
        }
        this.setAlignmentX(0.0f);
        this.setAlignmentY(0.0f);
        this.setMaximumSize(new Dimension(138, 115));
        this.lblHigh.setBackground(new Color(0, 0, 255));
        this.lblHigh.setFont(new Font("Arial", 1, 13));
        this.lblHigh.setHorizontalAlignment(4);
        this.lblHigh.setText("-");
        this.lblHigh.setVerticalAlignment(1);
        this.lblLow.setBackground(new Color(0, 0, 255));
        this.lblLow.setFont(new Font("Arial", 1, 13));
        this.lblLow.setHorizontalAlignment(4);
        this.lblLow.setText("-");
        this.lblLow.setVerticalAlignment(1);
        this.lblOfferPrice1.setBackground(new Color(0, 0, 255));
        this.lblOfferPrice1.setFont(new Font("Arial", 1, 13));
        this.lblOfferPrice1.setHorizontalAlignment(0);
        this.lblOfferPrice1.setText("-");
        this.lblOfferPrice1.setVerticalAlignment(1);
        this.lblOfferPrice1.setHorizontalTextPosition(0);
        this.lblOfferPrice1.setVerticalTextPosition(1);
        this.lblBidPrice1.setBackground(new Color(0, 0, 255));
        this.lblBidPrice1.setFont(new Font("Arial", 1, 13));
        this.lblBidPrice1.setForeground(new Color(51, 51, 51));
        this.lblBidPrice1.setHorizontalAlignment(0);
        this.lblBidPrice1.setText("-");
        this.lblBidPrice1.setVerticalAlignment(1);
        this.lblBidPrice1.setHorizontalTextPosition(0);
        this.lblBidPrice1.setVerticalTextPosition(1);
        this.lblTitle.setBackground(new Color(0, 0, 255));
        this.lblTitle.setFont(new Font("Arial", 1, 13));
        this.lblTitle.setHorizontalAlignment(10);
        this.lblTitle.setHorizontalTextPosition(10);
        this.lblTitle.setText("-");
        this.lblTitle.setVerticalAlignment(1);
        this.lblName.setBackground(new Color(0, 0, 255));
        this.lblName.setFont(new Font("Arial", 1, 13));
        if (this.bEnableTitle) {
            this.lblName.setHorizontalAlignment(0);
        }
        else {
            this.lblName.setHorizontalAlignment(2);
        }
        this.lblName.setText("-");
        this.lblName.setVerticalAlignment(1);
        this.lblImg.setImageType(0);
        final GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        if (this.intDisplayType != 2) {
            layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add((Component)this.lblTitle, 100, 120, 120).addPreferredGap(0).add((Component)this.lblBidPrice1, 60, 80, 80).addPreferredGap(0)));
            layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.lblTitle, -2, 16, -2)).add((GroupLayout.Group)layout.createParallelGroup(3).add((Component)this.lblBidPrice1, -2, 16, -2)));
        }
    }
    
    private void btnSellActionPerformed(final ActionEvent evt) {
        String actionCommand;
        if (this.intDefaultOrderType == 2) {
            actionCommand = "BID2";
        }
        else {
            actionCommand = "BID3";
        }
        this.actionOperation(actionCommand);
    }
    
    private void btnBuyActionPerformed(final ActionEvent evt) {
        String actionCommand;
        if (this.intDefaultOrderType == 2) {
            actionCommand = "ASK2";
        }
        else {
            actionCommand = "ASK3";
        }
        this.actionOperation(actionCommand);
    }
    
    public void singleClickActionOperation(final int intBidOrAsk) {
        if (this.myData.getAppTradablePrice() == 1 && this.myData.getTradablePrice() == 1) {
            if (!this.chkQuantity()) {
                if (this.objParentApplet != null) {
                    if (this.gblDebugMode > 0) {
                        System.out.println("Log:Before JS fn Calling");
                    }
                    final JSObject MainWindow = JSObject.getWindow(this.objParentApplet);
                    MainWindow.call("doQtyInvalid", null);
                    if (this.gblDebugMode > 0) {
                        System.out.println("Log:Alter JS fn Calling");
                    }
                }
                else {
                    System.out.println("Log:Applet Object Null");
                }
                return;
            }
            String strOrderType = "";
            String strBuy = "";
            final String strScript = "";
            if (intBidOrAsk == 1) {
                strOrderType = String.valueOf(this.intSingleClickOrderType);
                strBuy = String.valueOf(1);
            }
            else if (intBidOrAsk == 2) {
                strOrderType = String.valueOf(this.intSingleClickOrderType);
                strBuy = String.valueOf(2);
            }
            if (this.intDisplayType == 1 || this.intDisplayType == 2) {
                this.strSelectedQty = this.DefaultQty;
            }
            else {
                this.strSelectedQty = this.drpQty.getEditor().getItem().toString().trim();
                this.drpQty.getEditor().setItem(this.strSelectedQty);
            }
            try {
                final String[] strArgument = { this.objName, this.myData.getContract(), strOrderType, strBuy, this.myData.getBidPrice(), this.myData.getAskPrice(), String.valueOf(this.myData.getTradablePrice()), String.valueOf(this.myData.getAppTradablePrice()), this.strSelectedQty, this.myData.getTimeStamp() };
                if (this.objParentApplet != null) {
                    if (2 <= this.gblDebugMode) {
                        System.out.println("Log:Before JS fn Calling");
                    }
                    final JSObject MainWindow2 = JSObject.getWindow(this.objParentApplet);
                    MainWindow2.call("doSingleClickTrade", strArgument);
                    if (2 <= this.gblDebugMode) {
                        System.out.println("Log:Alter JS fn Calling");
                    }
                }
                else {
                    System.out.println("Log:Applet Object Null");
                }
            }
            catch (Exception exp) {
                exp.printStackTrace();
                System.out.println("Error:" + exp);
            }
        }
        else {
            try {
                if (this.SingleClickOrder) {
                    if (this.objParentApplet != null) {
                        if (2 <= this.gblDebugMode) {
                            System.out.println("Log:Non Tradable Alert");
                        }
                        final JSObject MainWindow = JSObject.getWindow(this.objParentApplet);
                        MainWindow.call("doNotAllowMarketOrder", null);
                        if (2 <= this.gblDebugMode) {
                            System.out.println("Log:Alter JS fn Calling");
                        }
                    }
                    else {
                        System.out.println("Log:Applet Object Null");
                    }
                }
            }
            catch (Exception exp2) {
                exp2.printStackTrace();
                System.out.println("Error:" + exp2);
            }
        }
    }
    
    public void actionOperation(final String actionCommand) {
        if (!this.chkQuantity()) {
            if (this.objParentApplet != null) {
                if (this.gblDebugMode > 0) {
                    System.out.println("Log:Before JS fn Calling");
                }
                final JSObject MainWindow = JSObject.getWindow(this.objParentApplet);
                MainWindow.call("doQtyInvalid", null);
                if (this.gblDebugMode > 0) {
                    System.out.println("Log:Alter JS fn Calling");
                }
            }
            else {
                System.out.println("Log:Applet Object Null");
            }
            return;
        }
        String strOrderType = "";
        String strBuy = "";
        final String strScript = "";
        if (actionCommand.compareTo("BID1") == 0) {
            strOrderType = String.valueOf(1);
            strBuy = String.valueOf(1);
        }
        else if (actionCommand.compareTo("BID2") == 0) {
            strOrderType = String.valueOf(2);
            strBuy = String.valueOf(1);
        }
        else if (actionCommand.compareTo("BID3") == 0) {
            strOrderType = String.valueOf(3);
            strBuy = String.valueOf(1);
        }
        else if (actionCommand.compareTo("ASK1") == 0) {
            strOrderType = String.valueOf(1);
            strBuy = String.valueOf(2);
        }
        else if (actionCommand.compareTo("ASK2") == 0) {
            strOrderType = String.valueOf(2);
            strBuy = String.valueOf(2);
        }
        else if (actionCommand.compareTo("ASK3") == 0) {
            strOrderType = String.valueOf(3);
            strBuy = String.valueOf(2);
        }
        if (this.intDisplayType == 1 || this.intDisplayType == 2) {
            this.strSelectedQty = this.DefaultQty;
        }
        else {
            this.strSelectedQty = this.drpQty.getEditor().getItem().toString().trim();
            this.drpQty.getEditor().setItem(this.strSelectedQty);
        }
        try {
            final String[] strArgument = { this.objName, this.myData.getContract(), strOrderType, strBuy, this.myData.getBidPrice(), this.myData.getAskPrice(), String.valueOf(this.myData.getTradablePrice()), String.valueOf(this.myData.getAppTradablePrice()), this.strSelectedQty, this.myData.getTimeStamp() };
            if (this.objParentApplet != null) {
                if (2 <= this.gblDebugMode) {
                    System.out.println("Log:Before JS fn Calling");
                }
                final JSObject MainWindow2 = JSObject.getWindow(this.objParentApplet);
                MainWindow2.call("doTrade", strArgument);
                if (2 <= this.gblDebugMode) {
                    System.out.println("Log:Alter JS fn Calling");
                }
            }
            else {
                System.out.println("Log:Applet Object Null");
            }
        }
        catch (Exception exp) {
            exp.printStackTrace();
            System.out.println("Error:" + exp);
        }
    }
    
    private boolean chkQuantity() {
        if (this.intDisplayType != 0) {
            return true;
        }
        String strSelectedQty = "";
        strSelectedQty = this.drpQty.getEditor().getItem().toString().trim();
        return strSelectedQty.length() > 0 && (this.isInteger(strSelectedQty) || (!this.isBullion && ((this.isNumeric(strSelectedQty) && strSelectedQty.equalsIgnoreCase("2.5")) || strSelectedQty.equalsIgnoreCase("7.5"))));
    }
    
    public void InitData() {
        this.objName = "";
        (this.myData = new fxPrice()).setAskPrice("0.0000");
        this.myData.setBidPrice("0.0000");
    }
    
    public String[] splitDecimal(final String price) {
        final String[] newPrice = new String[2];
        if (price.indexOf(".") > 0) {
            newPrice[0] = price.substring(0, price.indexOf("."));
            newPrice[1] = price.substring(price.indexOf(".") + 1);
            newPrice[0] += ".";
            String temp = newPrice[1];
            temp = temp.trim();
            if (temp.length() > 2) {
                if (temp.length() > this.intDecimalDisp) {
                    temp = temp.substring(0, this.intDecimalDisp);
                }
                newPrice[1] = temp.substring(temp.length() - 2);
                newPrice[0] += temp.substring(0, temp.length() - 2);
            }
        }
        else {
            newPrice[0] = price;
            newPrice[1] = "";
        }
        return newPrice;
    }
    
    public void setTitle(final String Value) {
        if (Value != null) {
            String t = Value;
            if (Value.trim().length() > 3) {
                t = Value.substring(0, 3) + "/" + Value.substring(3);
            }
            this.lblTitle.setText(t);
        }
        this.myData.setContract(Value);
    }
    
    public void setAppletSource(final fxPriceApplet objApp) {
        this.objParentApplet = objApp;
    }
    
    public void setPriceHigh(final String Value) {
    }
    
    public void setPriceLow(final String Value) {
    }
    
    public void setPriceOpen(final String Value) {
        this.myData.setOpenPrice(Value);
    }
    
    public void setName(final String Value) {
        this.myData.setName(Value);
        if (this.intDisplayType == 1 || this.intDisplayType == 2) {
            this.lblName.setText(Value);
        }
    }
    
    public void setPriceClose(final String Value) {
        this.myData.setClosePrice(Value);
    }
    
    public void setQtyList(final String ObjectID, final String prodtype, final String Value) {
        String myFeedCode = "";
        final String[] QtyList100K = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "15", "20" };
        final String[] QtyList10K = { "1", "2", "2.5", "3", "4", "5", "6", "7", "7.5", "8", "9", "10", "15", "20" };
        myFeedCode = ObjectID.substring(ObjectID.indexOf("]") + 1);
        myFeedCode = myFeedCode.trim();
        if (myFeedCode.length() == 0) {
            myFeedCode = ObjectID.substring(0, ObjectID.indexOf("["));
            myFeedCode = myFeedCode.trim();
        }
        if (myFeedCode.equalsIgnoreCase("USDGL") || myFeedCode.equalsIgnoreCase("GLDUSD") || myFeedCode.equalsIgnoreCase("USDSL") || myFeedCode.equalsIgnoreCase("SLVUSD")) {
            this.QtyList = QtyList100K;
        }
        else if (prodtype.equalsIgnoreCase("1")) {
            this.QtyList = QtyList100K;
        }
        else if (prodtype.equalsIgnoreCase("2")) {
            this.QtyList = QtyList10K;
        }
        else {
            this.QtyList = QtyList100K;
        }
        this.drpQty.setModel(new DefaultComboBoxModel<String>(this.QtyList));
        this.DefaultQty = Value;
        if (this.intDisplayType == 0) {
            for (int k = 0; k < this.QtyList.length; ++k) {
                if (this.DefaultQty.trim().equalsIgnoreCase(this.QtyList[k].trim())) {
                    this.drpQty.setSelectedIndex(k);
                }
            }
        }
    }
    
    public void setDefaultQty(final String Value) {
        this.DefaultQty = Value;
        if (this.intDisplayType == 0) {
            for (int k = 0; k < this.QtyList.length; ++k) {
                if (this.DefaultQty.trim().equalsIgnoreCase(this.QtyList[k].trim())) {
                    this.drpQty.setSelectedIndex(k);
                }
            }
        }
    }
    
    public void setDebugMode(final int Value) {
        this.gblDebugMode = Value;
    }
    
    public void setCallBackMode(final boolean Value) {
        this.CallBackMode = Value;
    }
    
    public void setSingleClickOrder(final boolean Value) {
        this.SingleClickOrder = Value;
    }
    
    public void setSingleClickOrderType(final int Value) {
        this.intSingleClickOrderType = Value;
    }
    
    public void setDefaultOrderType(final int Value) {
        this.intDefaultOrderType = Value;
    }
    
    public void setDecimalDisp(final int Value) {
        this.intDecimalDisp = Value;
    }
    
    public void refreshPrice(String Value) {
        String[] sData = null;
        final String[] newValue = null;
        Value = Value.replace('|', ';');
        sData = Value.split(";");
        String strLog = "";
        if (sData.length < 6) {
            return;
        }
        this.setTitle(sData[0]);
        strLog = strLog + " Contract=" + this.strSymbol;
        strLog = strLog + " Old RData=" + this.myData.getReceivedData();
        this.myData.setReceivedData(Value);
        strLog = strLog + " New RData=" + this.myData.getReceivedData();
        if (this.bFirstTime) {
            this.myData.setAppTradablePrice(2);
        }
        else {
            this.myData.setAppTradablePrice(1);
        }
        float oldBidPrice = 0.0f;
        float newBidPrice = 0.0f;
        float oldAskPrice = 0.0f;
        float newAskPrice = 0.0f;
        if (sData[4] == null) {
            return;
        }
        if (sData[4].trim().length() == 0) {
            return;
        }
        if (Integer.parseInt(sData[4]) > 0) {
            if (Double.parseDouble(sData[2]) > 0.0 && Double.parseDouble(sData[3]) > 0.0) {
                this.setFontsBack();
                if (this.isNumeric(this.myData.getBidPrice())) {
                    oldBidPrice = Float.parseFloat(this.myData.getBidPrice());
                }
                if (this.isNumeric(this.myData.getAskPrice())) {
                    oldAskPrice = Float.parseFloat(this.myData.getAskPrice());
                }
                this.myData.setBidPrice(sData[2]);
                this.myData.setAskPrice(sData[3]);
                this.myData.setTimeStamp(sData[5]);
                if (this.isNumeric(this.myData.getBidPrice())) {
                    newBidPrice = Float.parseFloat(this.myData.getBidPrice());
                }
                if (this.isNumeric(this.myData.getAskPrice())) {
                    newAskPrice = Float.parseFloat(this.myData.getAskPrice());
                }
                strLog = strLog + " Old Bid=" + oldBidPrice;
                strLog = strLog + " New Bid=" + newBidPrice;
                strLog = strLog + " Old Ask=" + oldAskPrice;
                strLog = strLog + " New Ask=" + newAskPrice;
                if (this.bFirstTime) {
                    this.myData.setTradablePrice(2);
                }
                else {
                    this.myData.setTradablePrice(Integer.parseInt(sData[4]));
                }
                this.displayBidPrice(sData[2]);
                this.displayAskPrice(sData[3]);
                this.displayTimeStamp(sData[5]);
                if (Integer.parseInt(sData[4]) == 1 && !this.bFirstTime) {
                    if (newBidPrice > oldBidPrice) {
                        this.setPriceUp();
                        strLog += " Result = Price Up";
                    }
                    else if (newBidPrice < oldBidPrice) {
                        this.setPriceDown();
                        strLog += " Result = Price Down";
                    }
                    else if (newAskPrice > oldAskPrice) {
                        this.setPriceUp();
                        strLog += " Result = Price Up";
                    }
                    else if (newAskPrice < oldAskPrice) {
                        this.setPriceDown();
                        strLog += " Result = Price Down";
                    }
                    else {
                        this.setPriceNoChage();
                        strLog += " Result = Price Same";
                    }
                }
                else {
                    this.setPriceNonTradable();
                    strLog += " Result = Price Non Tradable";
                    this.bFirstTime = false;
                }
            }
            else {
                this.setFontsToDash();
                this.bFirstTime = false;
            }
        }
        if (4 <= this.gblDebugMode) {
            System.out.println("PCheck Log:" + strLog);
        }
    }
    
    public void refreshAppletBadPrice(final String sValue) {
        if (sValue == null) {
            return;
        }
        if (sValue.trim().length() == 0) {
            return;
        }
        if (Integer.parseInt(sValue) == 1) {
            this.myData.setTradablePrice(1);
        }
        else {
            this.myData.setTradablePrice(2);
            this.setPriceNonTradable();
        }
    }
    
    private void setFontsBack() {
        if (!this.isList) {
            this.lblBidPrice1.setFont(new Font("Arial", 1, 12));
            this.lblOfferPrice1.setFont(new Font("Arial", 1, 12));
            this.lblBidPrice2.setFont(new Font("Arial", 1, 22));
            this.lblOfferPrice2.setFont(new Font("Arial", 1, 22));
        }
    }
    
    private void setFontsToDash() {
        if (!this.isList) {
            this.lblBidPrice1.setFont(new Font("Arial", 1, 22));
            this.lblOfferPrice1.setFont(new Font("Arial", 1, 22));
            this.lblBidPrice2.setFont(new Font("Arial", 1, 22));
            this.lblOfferPrice2.setFont(new Font("Arial", 1, 22));
            this.lblBidPrice1.setText("     -");
            this.lblOfferPrice1.setText("     -");
            this.lblBidPrice2.setText("     ");
            this.lblOfferPrice2.setText("     ");
        }
    }
    
    private void displayBidPrice(final String Value) {
        String[] newValue = null;
        if (Value == null) {
            return;
        }
        if (this.intDisplayType == 0) {
            newValue = this.splitDecimal(Value);
            if (newValue == null) {
                return;
            }
            if (newValue.length < 2) {
                return;
            }
            this.lblBidPrice1.setText(newValue[0]);
            this.lblBidPrice2.setText(newValue[1]);
        }
        else {
            this.lblBidPrice1.setText(Value);
        }
    }
    
    private void displayAskPrice(final String Value) {
        String[] newValue = null;
        if (Value == null) {
            return;
        }
        if (this.intDisplayType == 0) {
            newValue = this.splitDecimal(Value);
            if (newValue == null) {
                return;
            }
            if (newValue.length < 2) {
                return;
            }
            this.lblOfferPrice1.setText(newValue[0]);
            this.lblOfferPrice2.setText(newValue[1]);
        }
        else {
            this.lblOfferPrice1.setText(Value);
        }
    }
    
    private void displayTimeStamp(final String Value) {
        String newValue = null;
        if (Value == null) {
            return;
        }
        if (Value.length() < 12) {
            return;
        }
        newValue = Value.substring(11, 16);
    }
    
    public void setColorTemplate(final String templateNo, final boolean isListFormat, final boolean isAlternateColor) {
        switch (Integer.parseInt(templateNo)) {
            case 1: {
                this.set100kBlue(isListFormat, isAlternateColor);
                break;
            }
            case 2: {
                this.set10kGreen(isListFormat, isAlternateColor);
                break;
            }
        }
    }
    
    public Color getColortemplateTitle(final String bTemplateNo) {
        Color ListFormatTitlesColor = new Color(0, 0, 0);
        if (this.intDisplayType == 2) {
            ListFormatTitlesColor = new Color(255, 255, 255);
        }
        this.lblTitle.setForeground(ListFormatTitlesColor);
        this.lblName.setForeground(ListFormatTitlesColor);
        this.lblBidPrice1.setForeground(ListFormatTitlesColor);
        this.lblOfferPrice1.setForeground(ListFormatTitlesColor);
        this.lblHigh.setForeground(ListFormatTitlesColor);
        this.lblLow.setForeground(ListFormatTitlesColor);
        switch (Integer.parseInt(bTemplateNo)) {
            case 1: {
                if (this.intDisplayType != 2) {
                    return new Color(55, 87, 188);
                }
                return new Color(41, 73, 136);
            }
            case 2: {
                if (this.intDisplayType != 2) {
                    return new Color(180, 93, 0);
                }
                return new Color(44, 114, 252);
            }
            default: {
                return new Color(55, 87, 188);
            }
        }
    }
    
    private void setFontColors(final Color ColorOne, final Color ColorTwo, final Color grayColor, final boolean isListFormat, final boolean isAlternate) {
        if (isListFormat) {
            if (isAlternate) {
                this.lblTitle.setForeground(ColorOne);
                this.lblTitle.setFont(new Font("Arial", 1, 12));
                this.lblName.setForeground(ColorOne);
                this.lblName.setFont(new Font("Arial", 1, 12));
                this.lblBidPrice1.setForeground(grayColor);
                this.lblBidPrice1.setFont(new Font("Arial", 1, 12));
                this.lblOfferPrice1.setForeground(grayColor);
                this.lblOfferPrice1.setFont(new Font("Arial", 1, 12));
                this.lblHigh.setForeground(ColorOne);
                this.lblHigh.setFont(new Font("Arial", 1, 12));
                this.lblLow.setForeground(ColorOne);
                this.lblLow.setFont(new Font("Arial", 1, 12));
            }
            else {
                this.lblTitle.setForeground(ColorOne);
                this.lblTitle.setFont(new Font("Arial", 1, 12));
                this.lblName.setForeground(ColorOne);
                this.lblName.setFont(new Font("Arial", 1, 12));
                this.lblBidPrice1.setForeground(grayColor);
                this.lblBidPrice1.setFont(new Font("Arial", 1, 12));
                this.lblOfferPrice1.setForeground(grayColor);
                this.lblOfferPrice1.setFont(new Font("Arial", 1, 12));
                this.lblHigh.setForeground(ColorOne);
                this.lblHigh.setFont(new Font("Arial", 1, 12));
                this.lblLow.setForeground(ColorOne);
                this.lblLow.setFont(new Font("Arial", 1, 12));
            }
        }
        else {
            this.lblTitle.setForeground(ColorOne);
            this.btnBuy.setForeground(ColorOne);
            this.btnSell.setForeground(ColorOne);
            this.lblBid.setForeground(ColorTwo);
            this.lblOffer.setForeground(ColorTwo);
            this.lblBidPrice1.setForeground(grayColor);
            this.lblBidPrice2.setForeground(grayColor);
            this.lblOfferPrice1.setForeground(grayColor);
            this.lblOfferPrice2.setForeground(grayColor);
            this.lblQty.setForeground(ColorTwo);
            this.lblQtyIndicator.setForeground(ColorTwo);
            this.drpQty.setForeground(ColorTwo);
        }
    }
    
    private void set100kBlue(final boolean isListFormat, final boolean isAlternateColor) {
        final Color lblColors = new Color(242, 242, 242);
        Color tltColors = new Color(41, 73, 136);
        final Color buyColors = new Color(20, 10, 99);
        final Color sellColors = new Color(191, 11, 33);
        final Color fontColors = new Color(255, 255, 255);
        final Color altFontColors = new Color(0, 0, 0);
        final Color grayColor = new Color(93, 93, 93);
        if (isListFormat) {
            if (this.intDisplayType != 2) {
                tltColors = new Color(55, 87, 188);
            }
            if (isAlternateColor) {
                this.setBackground(lblColors);
                this.setFontColors(altFontColors, fontColors, grayColor, true, true);
            }
            else {
                this.setBackground(tltColors);
                this.setFontColors(altFontColors, fontColors, grayColor, true, false);
            }
        }
        else {
            this.pnlTitle.setBackground(tltColors);
            this.lblImg.setBackground(tltColors);
            this.pnlPriceViewer.setBorder(BorderFactory.createLineBorder(tltColors));
            this.btnBuy.setBackground(buyColors);
            this.btnSell.setBackground(sellColors);
            this.drpQty.setBackground(lblColors);
            this.pnlQty.setBackground(lblColors);
            this.pnlBidPad.setBackground(lblColors);
            this.pnlOfferPad.setBackground(lblColors);
            this.pnlBidPrice.setBackground(lblColors);
            this.pnlOfferPrice.setBackground(lblColors);
            this.pnlPriceViewer.setBackground(lblColors);
            this.pnlSeperator.setBackground(lblColors);
            this.lblSeperator1.setBackground(lblColors);
            this.lblSeperator3.setBackground(lblColors);
            this.setFontColors(fontColors, altFontColors, grayColor, isListFormat, false);
        }
    }
    
    private void set10kGreen(final boolean isListFormat, final boolean isAlternateColor) {
        final Color RowBGColor = new Color(255, 255, 255);
        final Color AlternateRowBGColor = new Color(247, 238, 229);
        final Color lblColors = new Color(242, 242, 242);
        Color tltColors = new Color(44, 114, 253);
        final Color buyColors = new Color(44, 114, 253);
        final Color sellColors = new Color(235, 82, 101);
        final Color fontColors = new Color(255, 255, 255);
        final Color altFontColors = new Color(0, 0, 0);
        final Color grayColor = new Color(93, 93, 93);
        if (isListFormat) {
            if (this.intDisplayType != 2) {
                tltColors = new Color(0, 0, 0);
            }
            if (isAlternateColor) {
                this.setBackground(AlternateRowBGColor);
                this.setFontColors(altFontColors, fontColors, grayColor, true, true);
            }
            else {
                this.setBackground(RowBGColor);
                this.setFontColors(altFontColors, fontColors, grayColor, true, false);
            }
        }
        else {
            this.pnlTitle.setBackground(tltColors);
            this.lblImg.setBackground(tltColors);
            this.pnlPriceViewer.setBorder(BorderFactory.createLineBorder(tltColors));
            this.btnBuy.setBackground(buyColors);
            this.btnSell.setBackground(sellColors);
            this.drpQty.setBackground(lblColors);
            this.pnlQty.setBackground(lblColors);
            this.pnlBidPad.setBackground(lblColors);
            this.pnlOfferPad.setBackground(lblColors);
            this.pnlBidPrice.setBackground(lblColors);
            this.pnlOfferPrice.setBackground(lblColors);
            this.pnlPriceViewer.setBackground(lblColors);
            this.pnlSeperator.setBackground(lblColors);
            this.lblSeperator1.setBackground(lblColors);
            this.lblSeperator3.setBackground(lblColors);
            this.setFontColors(fontColors, altFontColors, grayColor, isListFormat, false);
        }
    }
    
    private void setPriceNonTradable() {
        this.refreshTime.killTimer();
        this.lblBidPrice1.setForeground(ColorConstants.gblVarPriceGrayOutForeColor);
        this.lblOfferPrice1.setForeground(ColorConstants.gblVarPriceGrayOutForeColor);
        if (this.intDisplayType == 0) {
            this.lblBidPrice2.setForeground(ColorConstants.gblVarPriceGrayOutForeColor);
            this.lblOfferPrice2.setForeground(ColorConstants.gblVarPriceGrayOutForeColor);
        }
        this.repaint();
    }
    
    private void setPriceNoChage() {
        if (this.intDisplayType != 2) {
            this.lblImg.setImageType(0);
        }
        this.lblBidPrice1.setForeground(ColorConstants.gblVarPriceNoChageForeColor);
        this.lblOfferPrice1.setForeground(ColorConstants.gblVarPriceNoChageForeColor);
        if (this.intDisplayType == 0) {
            this.lblOfferPrice2.setForeground(ColorConstants.gblVarPriceNoChageForeColor);
            this.lblBidPrice2.setForeground(ColorConstants.gblVarPriceNoChageForeColor);
        }
        this.repaint();
        this.refreshTime.killTimer();
        this.refreshTime.startTimer();
    }
    
    private void setPriceUp() {
        if (this.intDisplayType != 2) {
            this.lblImg.setImageType(1);
        }
        this.lblBidPrice1.setForeground(ColorConstants.gblVarPriceHighForeColor);
        this.lblOfferPrice1.setForeground(ColorConstants.gblVarPriceHighForeColor);
        if (this.intDisplayType == 0) {
            this.lblBidPrice2.setForeground(ColorConstants.gblVarPriceHighForeColor);
            this.lblOfferPrice2.setForeground(ColorConstants.gblVarPriceHighForeColor);
        }
        this.repaint();
        this.refreshTime.killTimer();
        this.refreshTime.startTimer();
    }
    
    private void setPriceDown() {
        if (this.intDisplayType != 2) {
            this.lblImg.setImageType(-1);
        }
        this.lblBidPrice1.setForeground(ColorConstants.gblVarPriceLowForeColor);
        this.lblOfferPrice1.setForeground(ColorConstants.gblVarPriceLowForeColor);
        if (this.intDisplayType == 0) {
            this.lblOfferPrice2.setForeground(ColorConstants.gblVarPriceLowForeColor);
            this.lblBidPrice2.setForeground(ColorConstants.gblVarPriceLowForeColor);
        }
        this.repaint();
        this.refreshTime.killTimer();
        this.refreshTime.startTimer();
    }
    
    private void resetToDefautColor() {
        this.refreshTime.killTimer();
        this.lblBidPrice1.setForeground(ColorConstants.gblVarDefaultPriceForeColor);
        this.lblOfferPrice1.setForeground(ColorConstants.gblVarDefaultPriceForeColor);
        if (this.intDisplayType == 0) {
            this.lblBidPrice2.setForeground(ColorConstants.gblVarDefaultPriceForeColor);
            this.lblOfferPrice2.setForeground(ColorConstants.gblVarDefaultPriceForeColor);
        }
        this.repaint();
    }
    
    public String getUnit(final String ObjectID, final String iProdType) {
        String myFeedCode = "";
        myFeedCode = ObjectID.substring(ObjectID.indexOf("]") + 1);
        myFeedCode = myFeedCode.trim();
        if (myFeedCode.length() == 0) {
            myFeedCode = ObjectID.substring(0, ObjectID.indexOf("["));
            myFeedCode = myFeedCode.trim();
        }
        switch (Integer.parseInt(iProdType)) {
            case 1: {
                if (myFeedCode.equalsIgnoreCase("USDGL") || myFeedCode.equalsIgnoreCase("GLDUSD")) {
                    return "100 Oz";
                }
                if (myFeedCode.equalsIgnoreCase("USDSL") || myFeedCode.equalsIgnoreCase("SLVUSD")) {
                    return "5000 Oz";
                }
                return "100K";
            }
            case 2: {
                if (myFeedCode.equalsIgnoreCase("USDGL") || myFeedCode.equalsIgnoreCase("GLDUSD")) {
                    return "1 KG";
                }
                if (myFeedCode.equalsIgnoreCase("USDSL") || myFeedCode.equalsIgnoreCase("SLVUSD")) {
                    return "1000 Oz";
                }
                return "10K";
            }
            default: {
                if (myFeedCode.equalsIgnoreCase("USDGL") || myFeedCode.equalsIgnoreCase("GLDUSD")) {
                    return "100 Oz";
                }
                if (myFeedCode.equalsIgnoreCase("USDSL") || myFeedCode.equalsIgnoreCase("SLVUSD")) {
                    return "5000 Oz";
                }
                return "100K";
            }
        }
    }
    
    public boolean isSliver(final String ObjectID) {
        String myFeedCode = "";
        myFeedCode = ObjectID.substring(ObjectID.indexOf("]") + 1);
        myFeedCode = myFeedCode.trim();
        if (myFeedCode.length() == 0) {
            myFeedCode = ObjectID.substring(0, ObjectID.indexOf("["));
            myFeedCode = myFeedCode.trim();
        }
        return !myFeedCode.equalsIgnoreCase("USDGL") && !myFeedCode.equalsIgnoreCase("GLDUSD") && (myFeedCode.equalsIgnoreCase("USDSL") || myFeedCode.equalsIgnoreCase("SLVUSD"));
    }
    
    public void SetBullion(final String ObjectID) {
        String myFeedCode = "";
        myFeedCode = ObjectID.substring(ObjectID.indexOf("]") + 1);
        myFeedCode = myFeedCode.trim();
        if (myFeedCode.length() == 0) {
            myFeedCode = ObjectID.substring(0, ObjectID.indexOf("["));
            myFeedCode = myFeedCode.trim();
        }
        if (myFeedCode.equalsIgnoreCase("USDGL") || myFeedCode.equalsIgnoreCase("GLDUSD") || myFeedCode.equalsIgnoreCase("USDSL") || myFeedCode.equalsIgnoreCase("SLVUSD")) {
            this.isBullion = true;
        }
        else {
            this.isBullion = false;
        }
    }
    
    public boolean isInteger(final String str) {
        try {
            final int i = Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException exp) {
            return false;
        }
    }
    
    public boolean isNumeric(final String str) {
        try {
            final double i = Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException exp) {
            return false;
        }
    }
    
    public void callback_Tradable(final String key, final String Fid, final String Value) {
        if (this.CallBackMode) {
            final String strScript = "";
            try {
                final String[] strArgument = { key, String.valueOf(Fid), Value };
                if (this.objParentApplet != null) {
                    if (2 <= this.gblDebugMode) {
                        System.out.println("Log:Before JS fn Calling");
                    }
                    final JSObject MainWindow = JSObject.getWindow(this.objParentApplet);
                    if (MainWindow != null) {
                        MainWindow.call("callback_Tradable", strArgument);
                    }
                    else {
                        System.out.println("Log:Window Object Null");
                    }
                }
                else {
                    System.out.println("Log:Applet Object Null");
                }
            }
            catch (Exception exp) {
                exp.printStackTrace();
                System.out.println("Error:" + exp);
            }
        }
    }
    
    public void PriceClickOperation(final int intBidOrAsk) {
        final String strOrderType = "";
        String strBuy = "";
        final String strScript = "";
        if (intBidOrAsk == 1) {
            strBuy = String.valueOf(1);
        }
        else if (intBidOrAsk == 2) {
            strBuy = String.valueOf(2);
        }
        try {
            final String[] strArgument = { this.objName, this.myData.getContract(), strBuy, this.myData.getBidPrice(), this.myData.getAskPrice(), String.valueOf(this.myData.getTradablePrice()), String.valueOf(this.myData.getAppTradablePrice()), this.myData.getTimeStamp() };
            if (this.objParentApplet != null) {
                if (2 <= this.gblDebugMode) {
                    System.out.println("Log:Before JS fn Calling");
                }
                final JSObject MainWindow = JSObject.getWindow(this.objParentApplet);
                MainWindow.call("doPriceClick", strArgument);
                if (2 <= this.gblDebugMode) {
                    System.out.println("Log:Alter JS fn Calling");
                }
            }
            else {
                System.out.println("Log:Applet Object Null");
            }
        }
        catch (Exception exp) {
            exp.printStackTrace();
            System.out.println("Error:" + exp);
        }
    }
    
    public class Reminder implements CommonConstants
    {
        Timer timer;
        
        public void startTimer() {
            (this.timer = new Timer()).schedule(new RemindTask(), 1000L);
        }
        
        public void killTimer() {
            if (this.timer != null) {
                this.timer.cancel();
            }
        }
        
        class RemindTask extends TimerTask
        {
            public void run() {
                if (this != null) {
                    PriceViewer.this.resetToDefautColor();
                }
                if (Reminder.this.timer != null) {
                    Reminder.this.timer.cancel();
                }
            }
        }
    }
}
