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

public class Oct2BinPanel extends JPanel implements RadioEvent
{
    private static final int COLUMNS = 15;
    private static final int TITLE_HEIGHT = 40;
    private static final int MIDDLE_SPACE = 12;
    private JButton _buttonHelp;
    private JButton _buttonNewNumber;
    private JLabel _labelLow;
    private JLabel _labelMid;
    private JLabel _labelHigh;
    private BitRadioButton[] _bitsHigh;
    private BitRadioButton[] _bitsMid;
    private BitRadioButton[] _bitsLow;
    private int _intHighNumber;
    private int _intMidNumber;
    private int _intLowNumber;
    private final int _seed;
    private final Random _random;
    private static RadioEvent _parent;
    
    public Oct2BinPanel(final RadioEvent parent) {
        this._seed = new Random().nextInt();
        this._random = new Random(this._seed);
        Oct2BinPanel._parent = parent;
        this.setLayout(new GridBagLayout());
        this.setBackground(Main.WINDOW_COLOR);
        final GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = 2;
        this._bitsHigh = new BitRadioButton[3];
        this._bitsMid = new BitRadioButton[3];
        this._bitsLow = new BitRadioButton[3];
        gridBag.gridy = 0;
        this.pTitle("Octal to Binary", gridBag);
        final GridBagConstraints gridBagConstraints = gridBag;
        ++gridBagConstraints.gridy;
        this.pOctal(gridBag);
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
        gridBag.gridwidth = 3;
        (this._buttonHelp = new JButton("Help")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Oct2BinPanel.this.buttonHelp_Click();
            }
        });
        this.add(this._buttonHelp, gridBag);
        gridBag.gridx = 11;
        gridBag.gridwidth = 3;
        (this._buttonNewNumber = new JButton("New Number")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Oct2BinPanel.this.buttonNewNumber_Click();
            }
        });
        this.add(this._buttonNewNumber, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void buttonHelp_Click() {
        String highBits = "000" + Integer.toBinaryString(this._intHighNumber);
        highBits = highBits.substring(highBits.length() - 3);
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
        String midBits = "000" + Integer.toBinaryString(this._intMidNumber);
        midBits = midBits.substring(midBits.length() - 3);
        for (int j = this._bitsMid.length - 1; j >= 0; --j) {
            if (this._bitsMid[j].isSelected() && midBits.charAt(midBits.length() - j - 1) == '0') {
                this._bitsMid[j].doClick(200);
                return;
            }
            if (!this._bitsMid[j].isSelected() && midBits.charAt(midBits.length() - j - 1) == '1') {
                this._bitsMid[j].doClick(200);
                return;
            }
        }
        String lowBits = "000" + Integer.toBinaryString(this._intLowNumber);
        lowBits = lowBits.substring(lowBits.length() - 3);
        for (int k = this._bitsLow.length - 1; k >= 0; --k) {
            if (this._bitsLow[k].isSelected() && lowBits.charAt(lowBits.length() - k - 1) == '0') {
                this._bitsLow[k].doClick(200);
                return;
            }
            if (!this._bitsLow[k].isSelected() && lowBits.charAt(lowBits.length() - k - 1) == '1') {
                this._bitsLow[k].doClick(200);
                return;
            }
        }
    }
    
    private void buttonNewNumber_Click() {
        Oct2BinPanel._parent.update();
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
        gridBag.gridx = 2;
        gridBag.gridwidth = 3;
        BitRadioButton arrow = new BitRadioButton(this, false, imgArrow, imgArrow, imgArrow, imgArrow);
        arrow.setEnabled(false);
        this.add(arrow, gridBag);
        gridBag.gridx = 6;
        gridBag.gridwidth = 3;
        arrow = new BitRadioButton(this, false, imgArrow, imgArrow, imgArrow, imgArrow);
        arrow.setEnabled(false);
        this.add(arrow, gridBag);
        gridBag.gridx = 10;
        gridBag.gridwidth = 3;
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
            gridBag.gridx = 4 - i;
            (this._bitsHigh[i] = new BitRadioButton(this, true, imgOff, imgOffPressed, imgOn, imgOnPressed)).setEnabled(true);
            this.add(this._bitsHigh[i], gridBag);
        }
        gridBag.gridx = 5;
        gridBag.ipadx = 12;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsMid.length - 1; i >= 0; --i) {
            gridBag.gridx = 8 - i;
            (this._bitsMid[i] = new BitRadioButton(this, true, imgOff, imgOffPressed, imgOn, imgOnPressed)).setEnabled(true);
            this.add(this._bitsMid[i], gridBag);
        }
        gridBag.gridx = 9;
        gridBag.ipadx = 12;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsLow.length - 1; i >= 0; --i) {
            gridBag.gridx = 12 - i;
            (this._bitsLow[i] = new BitRadioButton(this, true, imgOff, imgOffPressed, imgOn, imgOnPressed)).setEnabled(true);
            this.add(this._bitsLow[i], gridBag);
        }
        gridBag.gridx = 13;
        gridBag.ipadx = 32;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        gridBag.gridx = 14;
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
            gridBag.gridx = 4 - i;
            final JLabel label = new JLabel("" + (int)Math.pow(2.0, i));
            label.setHorizontalTextPosition(0);
            label.setHorizontalAlignment(0);
            label.setFont(new Font("Dialog", 1, 12));
            label.setForeground(Color.white);
            this.add(label, gridBag);
        }
        gridBag.gridx = 5;
        gridBag.ipadx = 12;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsMid.length - 1; i >= 0; --i) {
            gridBag.gridx = 8 - i;
            final JLabel label = new JLabel("" + (int)Math.pow(2.0, i));
            label.setHorizontalTextPosition(0);
            label.setHorizontalAlignment(0);
            label.setFont(new Font("Dialog", 1, 12));
            label.setForeground(Color.white);
            this.add(label, gridBag);
        }
        gridBag.gridx = 9;
        gridBag.ipadx = 12;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsLow.length - 1; i >= 0; --i) {
            gridBag.gridx = 12 - i;
            final JLabel label = new JLabel("" + (int)Math.pow(2.0, i));
            label.setHorizontalTextPosition(0);
            label.setHorizontalAlignment(0);
            label.setFont(new Font("Dialog", 1, 12));
            label.setForeground(Color.white);
            this.add(label, gridBag);
        }
        gridBag.gridx = 13;
        gridBag.ipadx = 32;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        gridBag.gridx = 14;
        gridBag.ipadx = 92;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
    }
    
    private void pOctal(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        final int gridwidth = gridBag.gridwidth;
        gridBag.ipady = 0;
        gridBag.gridx = 2;
        gridBag.gridwidth = 3;
        (this._labelHigh = new JLabel()).setFont(new Font("Dialog", 1, 32));
        this._labelHigh.setHorizontalAlignment(0);
        this._labelHigh.setHorizontalTextPosition(0);
        this._labelHigh.setForeground(Color.white);
        this.add(this._labelHigh, gridBag);
        gridBag.gridx = 6;
        gridBag.gridwidth = 3;
        (this._labelMid = new JLabel()).setFont(new Font("Dialog", 1, 32));
        this._labelMid.setHorizontalAlignment(0);
        this._labelMid.setHorizontalTextPosition(0);
        this._labelMid.setForeground(Color.white);
        this.add(this._labelMid, gridBag);
        gridBag.gridx = 10;
        gridBag.gridwidth = 3;
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
        gridBag.gridwidth = 15;
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
        gridBag.gridwidth = 15;
        this.add(new JLabel(""), gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    public void update() {
        boolean boolHigh = false;
        boolean boolMid = false;
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
        int intMid = 0;
        for (int j = 0; j < this._bitsMid.length; ++j) {
            if (this._bitsMid[j].isSelected()) {
                intMid += (int)Math.pow(2.0, j);
            }
        }
        intMid = this._intMidNumber - intMid;
        if (intMid < 0) {
            this._labelMid.setForeground(Color.red);
        }
        else if (intMid > 0) {
            this._labelMid.setForeground(Color.white);
        }
        else {
            this._labelMid.setForeground(Color.green);
            for (int j = 0; j < this._bitsMid.length; ++j) {
                this._bitsMid[j].setEnabled(false);
            }
            boolMid = true;
        }
        int intLow = 0;
        for (int k = 0; k < this._bitsLow.length; ++k) {
            if (this._bitsLow[k].isSelected()) {
                intLow += (int)Math.pow(2.0, k);
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
            for (int k = 0; k < this._bitsLow.length; ++k) {
                this._bitsLow[k].setEnabled(false);
            }
            boolLow = true;
        }
        if (boolHigh && boolMid && boolLow) {
            this._buttonHelp.setText("Correct");
            this._buttonHelp.setEnabled(false);
            this._buttonNewNumber.requestFocus();
        }
    }
    
    public void update(final int newNumber) {
        this._intHighNumber = newNumber / 64;
        this._labelHigh.setText(Integer.toOctalString(this._intHighNumber).toUpperCase());
        for (int i = this._bitsHigh.length - 1; i >= 0; --i) {
            if (this._bitsHigh[i].isSelected()) {
                this._bitsHigh[i].setSelected(false);
            }
            this._bitsHigh[i].setEnabled(true);
        }
        this._intMidNumber = newNumber / 8 % 8;
        this._labelMid.setText(Integer.toOctalString(this._intMidNumber).toUpperCase());
        for (int i = this._bitsMid.length - 1; i >= 0; --i) {
            if (this._bitsMid[i].isSelected()) {
                this._bitsMid[i].setSelected(false);
            }
            this._bitsMid[i].setEnabled(true);
        }
        this._intLowNumber = newNumber % 8;
        this._labelLow.setText(Integer.toOctalString(this._intLowNumber).toUpperCase());
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
