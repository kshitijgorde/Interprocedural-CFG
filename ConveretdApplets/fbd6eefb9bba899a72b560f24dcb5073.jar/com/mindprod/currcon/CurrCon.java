// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.currcon;

import java.util.Locale;
import com.mindprod.fastcat.FastCat;
import java.awt.event.ItemEvent;
import javax.swing.BorderFactory;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Container;
import com.mindprod.common11.VersionCheck;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.HashMap;
import javax.swing.JApplet;

public final class CurrCon extends JApplet
{
    public static final String EMBEDDED_COPYRIGHT = "Copyright: (c) 2001-2011 Roedy Green, Canadian Mind Products, http://mindprod.com";
    private static final String RELEASE_DATE = "2011-05-18";
    public static final String VERSION_STRING = "4.4";
    private static HashMap<String, String> countryToCurr;
    private static Exch[] exchs;
    private final DecimalFormat[] df;
    private Font mainFont;
    private ItemListener theItemListener;
    private JComboBox currCodeSelect;
    private JLabel compactAmountDisplay;
    private JLabel currCodeDisplay;
    private JLabel currNameDisplay;
    private JLabel invalidDisplay;
    private JLabel preciseAmountDisplay;
    private String baseCurrAbbr;
    private String userCurrAbbr;
    private boolean ready;
    private boolean showSymbols;
    private double baseAmount;
    private int appletHeight;
    private int baseCurrIndex;
    private int currentCurrIndex;
    
    public CurrCon() {
        this.df = new DecimalFormat[] { new DecimalFormat("###,###,##0"), new DecimalFormat("#,###,##0.0"), new DecimalFormat("###,##0.00"), new DecimalFormat("###,##0.000") };
        this.ready = false;
        this.showSymbols = false;
        this.baseAmount = 0.0;
        this.baseCurrIndex = -1;
        this.currentCurrIndex = -1;
    }
    
    public void destroy() {
    }
    
    public void init() {
        if (!VersionCheck.isJavaVersionOK(1, 5, 0, this)) {
            return;
        }
        this.appletHeight = this.getHeight();
        this.mainFont = new Font("Dialog", 0, this.adjust(13));
        this.setVisible(false);
        final Container contentPane = this.getContentPane();
        contentPane.setBackground(Scheme.BACKGROUND_FOR_APPLET);
        this.setLayout(null);
        this.baseCurrAbbr = "USD";
        this.userCurrAbbr = "USD";
        if (!exchsOk()) {
            contentPane.add(new Label("err 2"));
            contentPane.setVisible(true);
            return;
        }
        final String whatToShow = this.getAppletParameters();
        this.determineUsersHomeCurrency();
        this.buildComponents(contentPane, whatToShow);
        this.validate();
        this.setVisible(true);
        this.ready = true;
    }
    
    private static boolean exchsOk() {
        if (CurrCon.exchs == null) {
            return false;
        }
        for (final Exch exch : CurrCon.exchs) {
            if (exch == null) {
                return false;
            }
        }
        return true;
    }
    
    private static void fetchCurrencyInfo() {
        try {
            final InputStream is = CurrCon.class.getResourceAsStream("exchs.ser");
            final GZIPInputStream gzis = new GZIPInputStream(is, 4096);
            final ObjectInputStream ois = new ObjectInputStream(gzis);
            final long wasResourceVersion = (long)ois.readObject();
            if (wasResourceVersion != Exch.getSerialVersionUID()) {
                throw new IllegalArgumentException("Wrong version of exchs.ser file.  Was:" + wasResourceVersion + " wanted:" + Exch.getSerialVersionUID());
            }
            if (Exch.getSerialVersionUID() != 5L) {
                throw new IllegalArgumentException("Inconsistent Exch and Geom code.  Exch:" + Exch.getSerialVersionUID() + " Geom:" + 5L);
            }
            final String[] country = (String[])ois.readObject();
            final String[] currency = (String[])ois.readObject();
            CurrCon.exchs = (Exch[])ois.readObject();
            ois.close();
            CurrCon.countryToCurr = new HashMap<String, String>(325);
            for (int i = 0; i < country.length; ++i) {
                CurrCon.countryToCurr.put(country[i], currency[i]);
            }
        }
        catch (Exception e) {
            System.err.println("Missing or damaged exchs.ser " + e + "\n");
        }
    }
    
    private static int findCurrencyInfo(final String currAbbr) {
        for (int i = 0; i < CurrCon.exchs.length; ++i) {
            if (CurrCon.exchs[i].getCurrAbbr().equals(currAbbr)) {
                return i;
            }
        }
        return -1;
    }
    
    private static String stripCommas(final String amount) {
        final int place = amount.indexOf(44);
        if (place < 0) {
            return amount;
        }
        final StringBuffer sb = new StringBuffer(amount.length() - 1);
        sb.append(amount.substring(0, place));
        for (int i = place + 1; i < amount.length(); ++i) {
            final char c = amount.charAt(i);
            if (c != ',') {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    private int adjust(final int size) {
        return size * this.appletHeight / 20;
    }
    
    private void buildComponents(final Container contentPane, final String whatToShow) {
        int x = 0;
        final char[] arr$;
        final char[] whatToShowLetters = arr$ = whatToShow.toCharArray();
        for (final char showCommandLetter : arr$) {
            switch (showCommandLetter) {
                case '$': {
                    this.showSymbols = true;
                    break;
                }
                case 'A': {
                    (this.compactAmountDisplay = new JLabel("", 4)).setBackground(Scheme.BACKGROUND_FOR_COMPACT_AMOUNT);
                    this.compactAmountDisplay.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));
                    this.compactAmountDisplay.setFont(this.mainFont);
                    this.compactAmountDisplay.setForeground(Scheme.FOREGROUND_FOR_COMPACT_AMOUNT);
                    this.compactAmountDisplay.setOpaque(true);
                    int width = 76;
                    if (this.showSymbols) {
                        width += 30;
                    }
                    width = this.adjust(width);
                    this.compactAmountDisplay.setBounds(x, 0, width, this.appletHeight);
                    x += width;
                    contentPane.add(this.compactAmountDisplay);
                    this.calcAmounts();
                    break;
                }
                case 'c': {
                    (this.currCodeSelect = new JComboBox()).setBackground(Scheme.BACKGROUND_FOR_CURRENCY_SELECTOR);
                    this.currCodeSelect.setEditable(false);
                    this.currCodeSelect.setForeground(Scheme.FOREGROUND_FOR_CURRENCY_SELECTOR);
                    this.currCodeSelect.setOpaque(true);
                    for (final Exch exch : CurrCon.exchs) {
                        if (exch.getExchangeRate() == 0.0) {
                            System.err.println("corrupt exchs file contains 0 exchange rate.");
                            System.exit(1);
                        }
                        this.currCodeSelect.addItem(exch.getCurrAbbr());
                    }
                    this.currCodeSelect.setSelectedIndex(this.currentCurrIndex);
                    final int width = this.adjust(54);
                    this.currCodeSelect.setBounds(x, 0, width, this.appletHeight);
                    x += width;
                    contentPane.add(this.currCodeSelect);
                    this.theItemListener = new ItemListener() {
                        public void itemStateChanged(final ItemEvent event) {
                            if (event.getStateChange() == 1) {
                                final int newCurrIndex = CurrCon.this.currCodeSelect.getSelectedIndex();
                                if (newCurrIndex >= 0 && newCurrIndex != CurrCon.this.currentCurrIndex) {
                                    CurrCon.this.currencyChangeListener(newCurrIndex);
                                    new Thread(new Broadcaster(CurrCon.this, newCurrIndex)).start();
                                }
                            }
                        }
                    };
                    this.currCodeSelect.addItemListener(this.theItemListener);
                    break;
                }
                case 'C': {
                    (this.currCodeDisplay = new JLabel(this.userCurrAbbr)).setBackground(Scheme.BACKGROUND_FOR_CURRENCY_CODE);
                    this.currCodeDisplay.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
                    this.currCodeDisplay.setForeground(Scheme.FOREGROUND_FOR_CURRENCY_CODE);
                    this.currCodeDisplay.setOpaque(true);
                    final int width = this.adjust(32);
                    this.currCodeDisplay.setBounds(x, 0, width, this.appletHeight);
                    x += width;
                    contentPane.add(this.currCodeDisplay);
                    break;
                }
                case 'N': {
                    (this.currNameDisplay = new JLabel(CurrCon.exchs[this.currentCurrIndex].getCurrName())).setBackground(Scheme.BACKGROUND_FOR_CURRENCY_NAME);
                    if (x > 0) {
                        this.currNameDisplay.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0));
                    }
                    this.currNameDisplay.setForeground(Scheme.FOREGROUND_FOR_CURRENCY_NAME);
                    this.currNameDisplay.setOpaque(true);
                    final int width = this.adjust(130);
                    this.currNameDisplay.setBounds(x, 0, width, this.appletHeight);
                    x += width;
                    contentPane.add(this.currNameDisplay);
                    break;
                }
                case 'P': {
                    (this.preciseAmountDisplay = new JLabel("", 4)).setBackground(Scheme.BACKGROUND_FOR_PRECISE_AMOUNT);
                    this.preciseAmountDisplay.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 2));
                    this.preciseAmountDisplay.setFont(this.mainFont);
                    this.preciseAmountDisplay.setForeground(Scheme.FOREGROUND_FOR_PRECISE_AMOUNT);
                    this.preciseAmountDisplay.setOpaque(true);
                    int width = 108;
                    if (this.showSymbols) {
                        width += 30;
                    }
                    width = this.adjust(width);
                    this.preciseAmountDisplay.setBounds(x, 0, width, this.appletHeight);
                    x += width;
                    contentPane.add(this.preciseAmountDisplay);
                    this.calcAmounts();
                    break;
                }
                default: {
                    System.err.println("invalid show parameter: " + whatToShow);
                    (this.invalidDisplay = new JLabel("err 1")).setBackground(Scheme.BACKGROUND_FOR_INVALID);
                    this.invalidDisplay.setForeground(Scheme.FOREGROUND_FOR_INVALID);
                    this.invalidDisplay.setOpaque(true);
                    final int width = this.adjust(30);
                    this.invalidDisplay.setBounds(x, 0, width, this.appletHeight);
                    x += width;
                    this.add(this.invalidDisplay);
                    break;
                }
            }
        }
    }
    
    private void calcAmounts() {
        if (this.preciseAmountDisplay == null && this.compactAmountDisplay == null) {
            return;
        }
        final Exch exch = CurrCon.exchs[this.currentCurrIndex];
        final double convertedAmount = this.baseAmount * CurrCon.exchs[this.baseCurrIndex].getExchangeRate() / exch.getExchangeRate();
        final int decimalPlaces = exch.getDecimalPlaces();
        final String symbol = exch.getSymbol();
        if (this.preciseAmountDisplay != null) {
            this.calcPreciseAmount(convertedAmount, decimalPlaces, symbol);
        }
        if (this.compactAmountDisplay != null) {
            this.calcCompactAmount(convertedAmount, decimalPlaces, symbol);
        }
    }
    
    private void calcCompactAmount(final double convertedAmount, final int decimalPlaces, final String symbol) {
        String convertedAmountText = this.df[decimalPlaces].format(convertedAmount);
        if (convertedAmountText.length() > 17) {
            convertedAmountText = this.df[1].format(convertedAmount / 1.0E12) + " T";
        }
        else if (convertedAmountText.length() > 14) {
            convertedAmountText = this.df[1].format(convertedAmount / 1.0E9) + " B";
        }
        else if (convertedAmountText.length() > 11) {
            convertedAmountText = this.df[1].format(convertedAmount / 1000000.0) + " M";
        }
        final FastCat sb = new FastCat(2);
        if (this.showSymbols) {
            sb.append(symbol);
        }
        sb.append(convertedAmountText);
        this.compactAmountDisplay.setText(sb.toString());
    }
    
    private void calcPreciseAmount(final double convertedAmount, final int decimalPlaces, final String symbol) {
        final String convertedAmountText = this.df[decimalPlaces].format(convertedAmount);
        final FastCat sb = new FastCat(2);
        if (this.showSymbols) {
            sb.append(symbol);
        }
        sb.append(convertedAmountText);
        this.preciseAmountDisplay.setText(sb.toString());
    }
    
    synchronized void currencyChangeListener(final int newCurrIndex) {
        if (!this.ready) {
            return;
        }
        if (this.currentCurrIndex == newCurrIndex) {
            return;
        }
        this.currentCurrIndex = newCurrIndex;
        if (this.currCodeSelect != null) {
            this.currCodeSelect.removeItemListener(this.theItemListener);
            this.currCodeSelect.setSelectedIndex(newCurrIndex);
            this.currCodeSelect.addItemListener(this.theItemListener);
        }
        if (this.currCodeDisplay != null) {
            this.currCodeDisplay.setText(CurrCon.exchs[newCurrIndex].getCurrAbbr());
        }
        if (this.currNameDisplay != null) {
            this.currNameDisplay.setText(CurrCon.exchs[newCurrIndex].getCurrName());
        }
        this.calcAmounts();
        Thread.yield();
    }
    
    private void determineUsersHomeCurrency() {
        try {
            final String country = Locale.getDefault().getCountry();
            if (country == null || country.length() != 2) {
                this.currentCurrIndex = -1;
                System.err.println("Warning OS failed to divulge the user's country: " + country);
            }
            else {
                this.userCurrAbbr = CurrCon.countryToCurr.get(country);
                this.currentCurrIndex = findCurrencyInfo(this.userCurrAbbr);
            }
        }
        catch (Exception e) {
            this.currentCurrIndex = -1;
            System.err.println("Warning OS failed to divulge the user's country");
        }
        if (this.currentCurrIndex < 0) {
            this.userCurrAbbr = "USD";
            this.currentCurrIndex = findCurrencyInfo(this.userCurrAbbr);
        }
    }
    
    private String getAppletParameters() {
        final String geom = this.getParameter("geom");
        if (geom == null) {
            System.err.println("Warning: missing geom version parameter");
        }
        else {
            final long websiteGeom = Long.parseLong(geom);
            if (websiteGeom != 5L) {
                System.err.println("Warning: macros were expanded with version " + websiteGeom + " instead of the expected " + 5L);
            }
        }
        String whatToShow = this.getParameter("show");
        if (whatToShow == null) {
            whatToShow = "I";
        }
        if (whatToShow.indexOf(65) >= 0 || whatToShow.indexOf(80) >= 0) {
            String amountValue = this.getParameter("amount");
            try {
                if (amountValue == null) {
                    throw new NumberFormatException();
                }
                amountValue = stripCommas(amountValue);
                this.baseAmount = Double.valueOf(amountValue);
            }
            catch (NumberFormatException e) {
                this.baseAmount = 0.0;
                System.err.println("invalid value for amount param: " + amountValue);
                whatToShow = "I";
            }
            this.baseCurrAbbr = this.getParameter("currency");
            if (this.baseCurrAbbr != null) {
                this.baseCurrAbbr = this.baseCurrAbbr.toUpperCase();
                if (this.baseCurrAbbr.equals("CDN")) {
                    this.baseCurrAbbr = "CAD";
                }
                this.baseCurrIndex = findCurrencyInfo(this.baseCurrAbbr);
            }
            else {
                this.baseCurrAbbr = "---";
                this.baseCurrIndex = -1;
            }
            if (this.baseCurrIndex < 0) {
                System.err.println("invalid or missing currency code " + this.baseCurrAbbr);
                whatToShow = "I";
            }
        }
        return whatToShow;
    }
    
    static {
        fetchCurrencyInfo();
    }
}
