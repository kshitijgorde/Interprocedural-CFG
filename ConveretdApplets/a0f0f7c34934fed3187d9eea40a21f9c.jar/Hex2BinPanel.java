import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Hex2BinPanel extends JPanel implements RadioEvent
{
    private static final int COLUMNS = 14;
    private static final int TITLE_HEIGHT = 40;
    private static final int MIDDLE_SPACE = 12;
    private JButton _buttonHelp;
    private JButton _buttonNewNumber;
    private JLabel _labelLow;
    private JLabel _labelHigh;
    private BitRadioButton[] _bitsHigh;
    private BitRadioButton[] _bitsLow;
    private int _intHighNumber;
    private int _intLowNumber;
    private final int _seed;
    private final Random _random;
    private static RadioEvent _parent;
    
    public Hex2BinPanel(final RadioEvent parent) {
        this._seed = new Random().nextInt();
        this._random = new Random(this._seed);
        Hex2BinPanel._parent = parent;
        this.setLayout(new GridBagLayout());
        this.setBackground(Main.WINDOW_COLOR);
        final GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = 2;
        this._bitsHigh = new BitRadioButton[4];
        this._bitsLow = new BitRadioButton[4];
        gridBag.gridy = 0;
        this.pTitle("Hexadecimal to Binary", gridBag);
        final GridBagConstraints gridBagConstraints = gridBag;
        ++gridBagConstraints.gridy;
        this.pHexadecimal(gridBag);
        final GridBagConstraints gridBagConstraints2 = gridBag;
        ++gridBagConstraints2.gridy;
        this.pArrows(gridBag);
        final GridBagConstraints gridBagConstraints3 = gridBag;
        ++gridBagConstraints3.gridy;
        this.pBits(gridBag);
        final GridBagConstraints gridBagConstraints4 = gridBag;
        ++gridBagConstraints4.gridy;
        this.pBitLabels(gridBag);
        final GridBagConstraints gridBagConstraints5 = gridBag;
        ++gridBagConstraints5.gridy;
        this.pRowSpacer(gridBag);
        final GridBagConstraints gridBagConstraints6 = gridBag;
        ++gridBagConstraints6.gridy;
        this.pRowSpacer(gridBag);
        final GridBagConstraints gridBagConstraints7 = gridBag;
        ++gridBagConstraints7.gridy;
        this.pButtons(gridBag);
        for (int i = 0; i < 3; ++i) {
            final GridBagConstraints gridBagConstraints8 = gridBag;
            ++gridBagConstraints8.gridy;
            this.pRowSpacer(gridBag);
        }
    }
    
    private void pButtons(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        final int gridwidth = gridBag.gridwidth;
        gridBag.ipady = 15;
        gridBag.gridx = 1;
        gridBag.gridwidth = 4;
        (this._buttonHelp = new JButton("Help")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Hex2BinPanel.this.buttonHelp_Click();
            }
        });
        this.add(this._buttonHelp, gridBag);
        gridBag.gridx = 10;
        gridBag.gridwidth = 3;
        (this._buttonNewNumber = new JButton("New Number")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Hex2BinPanel.this.buttonNewNumber_Click();
            }
        });
        this.add(this._buttonNewNumber, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void buttonHelp_Click() {
        String highBits = "0000" + Integer.toBinaryString(this._intHighNumber);
        highBits = highBits.substring(highBits.length() - 4);
        for (int i = this._bitsHigh.length - 1; i >= 0; --i) {
            if (this._bitsHigh[i].isSelected() && highBits.charAt(highBits.length() - i - 1) == '0') {
                this._bitsHigh[i].doClick(200);
                return;
            }
            if (!this._bitsHigh[i].isSelected() && highBits.charAt(highBits.length() - i - 1) == '1') {
                this._bitsHigh[i].doClick(200);
                return;
            }
        }
        String lowBits = "0000" + Integer.toBinaryString(this._intLowNumber);
        lowBits = lowBits.substring(lowBits.length() - 4);
        for (int j = this._bitsLow.length - 1; j >= 0; --j) {
            if (this._bitsLow[j].isSelected() && lowBits.charAt(lowBits.length() - j - 1) == '0') {
                this._bitsLow[j].doClick(200);
                return;
            }
            if (!this._bitsLow[j].isSelected() && lowBits.charAt(lowBits.length() - j - 1) == '1') {
                this._bitsLow[j].doClick(200);
                return;
            }
        }
    }
    
    private void buttonNewNumber_Click() {
        Hex2BinPanel._parent.update();
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
        gridBag.gridx = 4;
        gridBag.gridwidth = 2;
        BitRadioButton arrow = new BitRadioButton(this, false, imgArrow, imgArrow, imgArrow, imgArrow);
        arrow.setEnabled(false);
        this.add(arrow, gridBag);
        gridBag.gridx = 9;
        gridBag.gridwidth = 2;
        arrow = new BitRadioButton(this, false, imgArrow, imgArrow, imgArrow, imgArrow);
        arrow.setEnabled(false);
        this.add(arrow, gridBag);
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
        gridBag.ipadx = 92;
        this.add(new JLabel(" "), gridBag);
        gridBag.ipadx = ipadx;
        gridBag.gridx = 1;
        gridBag.ipadx = 32;
        this.add(new JLabel(" "), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsHigh.length - 1; i >= 0; --i) {
            gridBag.gridx = 6 - i;
            (this._bitsHigh[i] = new BitRadioButton(this, true, imgOff, imgOffPressed, imgOn, imgOnPressed)).setEnabled(true);
            this.add(this._bitsHigh[i], gridBag);
        }
        gridBag.gridx = 7;
        gridBag.ipadx = 12;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsLow.length - 1; i >= 0; --i) {
            gridBag.gridx = 11 - i;
            (this._bitsLow[i] = new BitRadioButton(this, true, imgOff, imgOffPressed, imgOn, imgOnPressed)).setEnabled(true);
            this.add(this._bitsLow[i], gridBag);
        }
        gridBag.gridx = 12;
        gridBag.ipadx = 32;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        gridBag.gridx = 13;
        gridBag.ipadx = 92;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
    }
    
    private void pBitLabels(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipadx = gridBag.ipadx;
        gridBag.gridx = 0;
        gridBag.ipadx = 92;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        gridBag.gridx = 1;
        gridBag.ipadx = 32;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsHigh.length - 1; i >= 0; --i) {
            gridBag.gridx = 6 - i;
            final JLabel label = new JLabel("" + (int)Math.pow(2.0, i));
            label.setHorizontalTextPosition(0);
            label.setHorizontalAlignment(0);
            label.setFont(new Font("Dialog", 1, 12));
            label.setForeground(Color.white);
            this.add(label, gridBag);
        }
        gridBag.gridx = 7;
        gridBag.ipadx = 12;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsLow.length - 1; i >= 0; --i) {
            gridBag.gridx = 11 - i;
            final JLabel label = new JLabel("" + (int)Math.pow(2.0, i));
            label.setHorizontalTextPosition(0);
            label.setHorizontalAlignment(0);
            label.setFont(new Font("Dialog", 1, 12));
            label.setForeground(Color.white);
            this.add(label, gridBag);
        }
        gridBag.gridx = 12;
        gridBag.ipadx = 32;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        gridBag.gridx = 13;
        gridBag.ipadx = 92;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
    }
    
    private void pHexadecimal(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        final int gridwidth = gridBag.gridwidth;
        gridBag.ipady = 0;
        gridBag.gridx = 4;
        gridBag.gridwidth = 2;
        (this._labelHigh = new JLabel()).setFont(new Font("Dialog", 1, 32));
        this._labelHigh.setHorizontalAlignment(0);
        this._labelHigh.setHorizontalTextPosition(0);
        this._labelHigh.setForeground(Color.white);
        this.add(this._labelHigh, gridBag);
        gridBag.gridx = 9;
        gridBag.gridwidth = 2;
        (this._labelLow = new JLabel()).setFont(new Font("Dialog", 1, 32));
        this._labelLow.setHorizontalAlignment(0);
        this._labelLow.setHorizontalTextPosition(0);
        this._labelLow.setForeground(Color.white);
        this.add(this._labelLow, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
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
        gridBag.gridwidth = 14;
        final JLabel labelTitle = new JLabel(sTitle);
        labelTitle.setHorizontalTextPosition(0);
        labelTitle.setHorizontalAlignment(0);
        labelTitle.setFont(new Font("Dialog", 1, 32));
        labelTitle.setForeground(Color.orange);
        this.add(labelTitle, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void pRowSpacer(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        final int gridwidth = gridBag.gridwidth;
        gridBag.gridx = 0;
        gridBag.ipady = 20;
        gridBag.gridwidth = 14;
        this.add(new JLabel(""), gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    public void update() {
        boolean boolHigh = false;
        boolean boolLow = false;
        int intHigh = 0;
        for (int i = 0; i < this._bitsHigh.length; ++i) {
            if (this._bitsHigh[i].isSelected()) {
                intHigh += (int)Math.pow(2.0, i);
            }
        }
        intHigh = this._intHighNumber - intHigh;
        if (intHigh < 0) {
            this._labelHigh.setForeground(Color.red);
        }
        else if (intHigh > 0) {
            this._labelHigh.setForeground(Color.white);
        }
        else {
            this._labelHigh.setForeground(Color.green);
            for (int i = 0; i < this._bitsHigh.length; ++i) {
                this._bitsHigh[i].setEnabled(false);
            }
            boolHigh = true;
        }
        int intLow = 0;
        for (int j = 0; j < this._bitsLow.length; ++j) {
            if (this._bitsLow[j].isSelected()) {
                intLow += (int)Math.pow(2.0, j);
            }
        }
        intLow = this._intLowNumber - intLow;
        if (intLow < 0) {
            this._labelLow.setForeground(Color.red);
        }
        else if (intLow > 0) {
            this._labelLow.setForeground(Color.white);
        }
        else {
            this._labelLow.setForeground(Color.green);
            for (int j = 0; j < this._bitsLow.length; ++j) {
                this._bitsLow[j].setEnabled(false);
            }
            boolLow = true;
        }
        if (boolHigh && boolLow) {
            this._buttonHelp.setText("Correct");
            this._buttonHelp.setEnabled(false);
            this._buttonNewNumber.requestFocus();
        }
    }
    
    public void update(final int newNumber) {
        this._intHighNumber = newNumber / 16;
        this._labelHigh.setText(Integer.toHexString(this._intHighNumber).toUpperCase());
        for (int i = this._bitsHigh.length - 1; i >= 0; --i) {
            if (this._bitsHigh[i].isSelected()) {
                this._bitsHigh[i].setSelected(false);
            }
            this._bitsHigh[i].setEnabled(true);
        }
        this._intLowNumber = newNumber % 16;
        this._labelLow.setText(Integer.toHexString(this._intLowNumber).toUpperCase());
        for (int i = this._bitsLow.length - 1; i >= 0; --i) {
            if (this._bitsLow[i].isSelected()) {
                this._bitsLow[i].setSelected(false);
            }
            this._bitsLow[i].setEnabled(true);
        }
        this._buttonHelp.setText("Help");
        this._buttonHelp.setEnabled(true);
    }
}
