import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_FloatingPoint extends JApplet implements RadioEvent
{
    boolean _boolApplet;
    boolean _boolLoaded;
    boolean _boolRollOver;
    JLabel _labelSign;
    JLabel _labelExponent;
    JLabel _labelMantissa;
    JLabel _labelResult;
    JTextArea _textMessages;
    JScrollPane _scrollMessages;
    BitRadioButton _bitSign;
    BitRadioButton[] _bitsExponent;
    BitRadioButton[] _bitsMantissa;
    
    public UI_FloatingPoint() {
        this(true);
    }
    
    public UI_FloatingPoint(final boolean applet) {
        this._boolApplet = applet;
        if (applet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    public void init() {
        this._boolLoaded = false;
        this._boolRollOver = true;
        this.getContentPane().setBackground(new Color(123, 123, 123));
        this.pInitComponents();
        this._boolLoaded = true;
        this.update();
    }
    
    private void pInitComponents() {
        this.getContentPane().setLayout(new GridBagLayout());
        final GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = 2;
        gridBag.gridy = 0;
        this.pTitle("Floating-Point Notation", gridBag);
        final GridBagConstraints gridBagConstraints = gridBag;
        ++gridBagConstraints.gridy;
        this.pHeaders(gridBag);
        final GridBagConstraints gridBagConstraints2 = gridBag;
        ++gridBagConstraints2.gridy;
        this.pBits(gridBag);
        final GridBagConstraints gridBagConstraints3 = gridBag;
        ++gridBagConstraints3.gridy;
        this.pArrows(gridBag);
        final GridBagConstraints gridBagConstraints4 = gridBag;
        ++gridBagConstraints4.gridy;
        this.pBitLabels(gridBag);
        final GridBagConstraints gridBagConstraints5 = gridBag;
        ++gridBagConstraints5.gridy;
        this.pResultArrow(gridBag);
        final GridBagConstraints gridBagConstraints6 = gridBag;
        ++gridBagConstraints6.gridy;
        this.pResult(gridBag);
        final GridBagConstraints gridBagConstraints7 = gridBag;
        ++gridBagConstraints7.gridy;
        this.pRowSpacer(gridBag);
        final GridBagConstraints gridBagConstraints8 = gridBag;
        ++gridBagConstraints8.gridy;
        this.pMessages(gridBag);
    }
    
    private void pTitle(String sTitle, GridBagConstraints gridBag) {
        if (sTitle == null) {
            sTitle = "User Interface";
        }
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        final int gridwidth = gridBag.gridwidth;
        gridBag.gridx = 0;
        gridBag.ipady = 40;
        gridBag.gridwidth = 13;
        final JLabel labelTitle = new JLabel(sTitle);
        labelTitle.setHorizontalTextPosition(0);
        labelTitle.setHorizontalAlignment(0);
        labelTitle.setFont(new Font("Dialog", 1, 32));
        labelTitle.setForeground(Color.orange);
        this.getContentPane().add(labelTitle, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void pHeaders(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        final int gridwidth = gridBag.gridwidth;
        gridBag.ipady = 20;
        gridBag.gridx = 1;
        final JLabel labelSign = new JLabel("Sign");
        labelSign.setHorizontalTextPosition(0);
        labelSign.setHorizontalAlignment(0);
        labelSign.setFont(new Font("Dialog", 1, 18));
        labelSign.setForeground(Color.white);
        this.getContentPane().add(labelSign, gridBag);
        gridBag.gridx = 3;
        gridBag.gridwidth = 3;
        final JLabel labelExponent = new JLabel("Exponent");
        labelExponent.setHorizontalTextPosition(0);
        labelExponent.setHorizontalAlignment(0);
        labelExponent.setFont(new Font("Dialog", 1, 18));
        labelExponent.setForeground(Color.white);
        this.getContentPane().add(labelExponent, gridBag);
        gridBag.gridx = 7;
        gridBag.gridwidth = 4;
        final JLabel labelMantissa = new JLabel("Mantissa");
        labelMantissa.setHorizontalTextPosition(0);
        labelMantissa.setHorizontalAlignment(0);
        labelMantissa.setFont(new Font("Dialog", 1, 18));
        labelMantissa.setForeground(Color.white);
        this.getContentPane().add(labelMantissa, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void pBits(GridBagConstraints gridBag) {
        final ImageLoader iLoader = new ImageLoader();
        final ImageIcon imgOff = iLoader.getImageIcon("images/offImage.gif");
        final ImageIcon imgOffPressed = iLoader.getImageIcon("images/offImagePressed.gif");
        final ImageIcon imgOn = iLoader.getImageIcon("images/onImage.gif");
        final ImageIcon imgOnPressed = iLoader.getImageIcon("images/onImagePressed.gif");
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipadx = gridBag.ipadx;
        gridBag.gridx = 0;
        gridBag.ipadx = 32;
        this.getContentPane().add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        gridBag.gridx = 1;
        this._bitSign = new BitRadioButton(this, this._boolRollOver, imgOff, imgOffPressed, imgOn, imgOnPressed);
        this.getContentPane().add(this._bitSign, gridBag);
        gridBag.gridx = 2;
        gridBag.ipadx = 32;
        this.getContentPane().add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        this._bitsExponent = new BitRadioButton[3];
        for (int i = 0; i < 3; ++i) {
            gridBag.gridx = 3 + i;
            this._bitsExponent[i] = new BitRadioButton(this, this._boolRollOver, imgOff, imgOffPressed, imgOn, imgOnPressed);
            this.getContentPane().add(this._bitsExponent[i], gridBag);
        }
        this._bitsExponent[0].setValue(1);
        gridBag.gridx = 6;
        gridBag.ipadx = 32;
        this.getContentPane().add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        this._bitsMantissa = new BitRadioButton[4];
        for (int i = 0; i < 4; ++i) {
            gridBag.gridx = 7 + i;
            this._bitsMantissa[i] = new BitRadioButton(this, this._boolRollOver, imgOff, imgOffPressed, imgOn, imgOnPressed);
            this.getContentPane().add(this._bitsMantissa[i], gridBag);
        }
        gridBag.gridx = 11;
        gridBag.ipadx = 32;
        this.getContentPane().add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        gridBag.gridx = 12;
        this.getContentPane().add(new JLabel(" "), gridBag);
    }
    
    private void pArrows(GridBagConstraints gridBag) {
        final ImageLoader iLoader = new ImageLoader();
        final ImageIcon imgArrow = iLoader.getImageIcon("images/arrowDown.gif");
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        final int gridwidth = gridBag.gridwidth;
        gridBag.ipady = 20;
        gridBag.gridx = 1;
        final BitRadioButton arrowSign = new BitRadioButton(this, false, imgArrow, imgArrow, imgArrow, imgArrow);
        arrowSign.setEnabled(false);
        this.getContentPane().add(arrowSign, gridBag);
        gridBag.gridx = 4;
        final BitRadioButton arrowExponent = new BitRadioButton(this, false, imgArrow, imgArrow, imgArrow, imgArrow);
        arrowExponent.setEnabled(false);
        this.getContentPane().add(arrowExponent, gridBag);
        gridBag.gridx = 8;
        gridBag.gridwidth = 2;
        final BitRadioButton arrowMantissa = new BitRadioButton(this, false, imgArrow, imgArrow, imgArrow, imgArrow);
        arrowMantissa.setEnabled(false);
        this.getContentPane().add(arrowMantissa, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void pBitLabels(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int gridwidth = gridBag.gridwidth;
        gridBag.gridx = 1;
        (this._labelSign = new JLabel("+")).setHorizontalTextPosition(0);
        this._labelSign.setHorizontalAlignment(0);
        this._labelSign.setFont(new Font("DialogInput", 1, 24));
        this._labelSign.setForeground(Color.white);
        this.getContentPane().add(this._labelSign, gridBag);
        gridBag.gridx = 3;
        gridBag.gridwidth = 3;
        (this._labelExponent = new JLabel("0")).setHorizontalTextPosition(0);
        this._labelExponent.setHorizontalAlignment(0);
        this._labelExponent.setFont(new Font("DialogInput", 1, 24));
        this._labelExponent.setForeground(Color.white);
        this.getContentPane().add(this._labelExponent, gridBag);
        gridBag.gridx = 6;
        gridBag.gridwidth = 6;
        (this._labelMantissa = new JLabel("0.0000")).setHorizontalTextPosition(0);
        this._labelMantissa.setHorizontalAlignment(0);
        this._labelMantissa.setFont(new Font("DialogInput", 1, 24));
        this._labelMantissa.setForeground(Color.white);
        this.getContentPane().add(this._labelMantissa, gridBag);
        gridBag.gridwidth = gridwidth;
    }
    
    private void pResultArrow(GridBagConstraints gridBag) {
        final ImageLoader iLoader = new ImageLoader();
        final ImageIcon imgArrow = iLoader.getImageIcon("images/arrowDown.gif");
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        final int gridwidth = gridBag.gridwidth;
        gridBag.ipady = 20;
        gridBag.gridx = 8;
        gridBag.gridwidth = 2;
        final BitRadioButton arrowResult = new BitRadioButton(this, false, imgArrow, imgArrow, imgArrow, imgArrow);
        arrowResult.setEnabled(false);
        this.getContentPane().add(arrowResult, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void pResult(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int gridwidth = gridBag.gridwidth;
        gridBag.gridx = 6;
        gridBag.gridwidth = 6;
        (this._labelResult = new JLabel("0.0")).setHorizontalTextPosition(0);
        this._labelResult.setHorizontalAlignment(0);
        this._labelResult.setFont(new Font("DialogInput", 1, 24));
        this._labelResult.setForeground(Color.white);
        this.getContentPane().add(this._labelResult, gridBag);
        gridBag.gridwidth = gridwidth;
    }
    
    private void pRowSpacer(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        gridBag.gridx = 0;
        gridBag.ipady = 20;
        this.getContentPane().add(new JLabel(""), gridBag);
        gridBag.ipady = ipady;
    }
    
    private void pMessages(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        final int gridwidth = gridBag.gridwidth;
        gridBag.gridx = 0;
        gridBag.gridwidth = 13;
        gridBag.ipady = 75;
        (this._textMessages = new JTextArea()).setBackground(this.getContentPane().getBackground());
        this._textMessages.setFont(new Font("Dialog", 0, 12));
        this._textMessages.setForeground(Color.orange);
        this._textMessages.setEditable(false);
        this._textMessages.setTabSize(2);
        this._textMessages.setText("");
        this._textMessages.setCaretPosition(0);
        this._textMessages.setMargin(new Insets(5, 10, 0, 10));
        (this._scrollMessages = new JScrollPane()).setBorder(new BorderUIResource.EtchedBorderUIResource(1, Color.orange, this.getContentPane().getBackground()));
        this._scrollMessages.setViewportView(this._textMessages);
        this._scrollMessages.setPreferredSize(new Dimension(100, 75));
        this.getContentPane().add(this._scrollMessages, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    public void update() {
        String sExponent = "";
        String sMantissa = "";
        String sCalculations = "";
        String sConvert2Dec = "";
        int intExponent = 0;
        final int intValue = 0;
        double dblValue = 0.0;
        if (!this._boolLoaded) {
            return;
        }
        if (this._bitSign.isSelected()) {
            sCalculations += "Step 1 - Sign\n\tSign bit equals 1 therefore sign is negative (-).\n\n";
            this._labelSign.setText("-");
        }
        else {
            sCalculations = "Step 1 - Sign\n\tSign bit equals 0 therefore sign is positive (+).\n\n";
            this._labelSign.setText("+");
        }
        for (int i = 0; i < 3; ++i) {
            sExponent = sExponent + "" + this._bitsExponent[i].getValue();
        }
        intExponent = Integer.parseInt(sExponent, 2) - (int)Math.pow(2.0, 2.0);
        this._labelExponent.setText("" + intExponent);
        sCalculations = sCalculations + "Step 2 - Exponent\n\tLet n = number of exponent bits (n=3)\n\tExponent = Binary Value - 2^(n-1)\n\tExponent = \"" + sExponent + "\" - 2^2\n" + "\tExponent = " + Integer.parseInt(sExponent, 2) + " - 4\n" + "\tExponent = " + intExponent + "\n\n";
        sCalculations += "Step 3 - Apply Exponent on Mantissa\n";
        sMantissa = "0.";
        for (int i = 0; i < 4; ++i) {
            sMantissa = sMantissa + "" + this._bitsMantissa[i].getValue();
        }
        if (intExponent < 0) {
            sCalculations = sCalculations + "\tExponent is negative therefore move decimal to the left.\n\tMantissa = Mantissa * 2^(Exponent)\n\tMantissa = " + sMantissa + " * 2^(" + intExponent + ")\n";
            sMantissa = "0.";
            for (int i = intExponent; i < 0; ++i) {
                sMantissa += "0";
            }
            for (int i = 0; i < 4; ++i) {
                sMantissa = sMantissa + "" + this._bitsMantissa[i].getValue();
                if (this._bitsMantissa[i].isSelected()) {
                    dblValue += Math.pow(2.0, intExponent - 1 - i);
                    sConvert2Dec = sConvert2Dec + "2^(" + (intExponent - 1 - i) + ") + ";
                }
            }
        }
        else {
            sCalculations = sCalculations + "\tExponent is positive therefore move decimal to the right.\n\tMantissa = Mantissa * 2^(Exponent)\n\tMantissa = " + sMantissa + " * 2^" + intExponent + "\n";
            sMantissa = "";
            for (int i = 0; i < intExponent; ++i) {
                sMantissa = sMantissa + "" + this._bitsMantissa[i].getValue();
                if (this._bitsMantissa[i].isSelected()) {
                    dblValue += Math.pow(2.0, intExponent - 1 - i);
                    if (intExponent - 1 - i == 0) {
                        sConvert2Dec += "1 + ";
                    }
                    else if (intExponent - 1 - i == 1) {
                        sConvert2Dec += "2 + ";
                    }
                    else if (intExponent - 1 - i < 0) {
                        sConvert2Dec = sConvert2Dec + "2^(" + (intExponent - 1 - i) + ") + ";
                    }
                    else {
                        sConvert2Dec = sConvert2Dec + "2^" + (intExponent - 1 - i) + " + ";
                    }
                }
            }
            sMantissa += ((intExponent == 0) ? "0." : ".");
            for (int i = intExponent; i < 4; ++i) {
                sMantissa = sMantissa + "" + this._bitsMantissa[i].getValue();
                if (this._bitsMantissa[i].isSelected()) {
                    dblValue += Math.pow(2.0, intExponent - 1 - i);
                    if (intExponent - 1 - i == 0) {
                        sConvert2Dec += "1 + ";
                    }
                    else if (intExponent - 1 - i == 1) {
                        sConvert2Dec += "2 + ";
                    }
                    else if (intExponent - 1 - i < 0) {
                        sConvert2Dec = sConvert2Dec + "2^(" + (intExponent - 1 - i) + ") + ";
                    }
                    else {
                        sConvert2Dec = sConvert2Dec + "2^" + (intExponent - 1 - i) + " + ";
                    }
                }
            }
        }
        if (sConvert2Dec.length() > 3) {
            sConvert2Dec = sConvert2Dec.substring(0, sConvert2Dec.length() - 3);
        }
        else {
            sConvert2Dec = "0";
        }
        this._labelMantissa.setText(sMantissa);
        sCalculations = sCalculations + "\tMantissa = " + sMantissa + "\n\n";
        if (this._bitSign.isSelected()) {
            dblValue *= -1.0;
        }
        this._labelResult.setText("" + dblValue);
        sCalculations += "Step 4 - Convert to Decimal\n\tAdd up all 'on' bits raised to their digit's place\n";
        if (sConvert2Dec.equals("0")) {
            sCalculations = sCalculations + "\tDecimal = " + (this._bitSign.isSelected() ? "-0.0" : "0.0") + "\n";
        }
        else {
            sCalculations = sCalculations + "\tDecimal = " + (this._bitSign.isSelected() ? "(-1) * " : "") + sConvert2Dec + "\n" + "\tDecimal = " + dblValue + "\n";
        }
        this._textMessages.setText(sCalculations);
        this._textMessages.setCaretPosition(0);
    }
    
    private String pGetParameter(final String key, final String def) {
        if (this._boolApplet) {
            final String param = this.getParameter(key);
            return (param == null) ? def : param;
        }
        return System.getProperty(key, def);
    }
}
