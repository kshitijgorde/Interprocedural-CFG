import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bin2OctPanel extends JPanel implements RadioEvent
{
    private static final int COLUMNS = 15;
    private static final int TITLE_HEIGHT = 40;
    private static final int MIDDLE_SPACE = 12;
    private JButton _buttonHelp;
    private JButton _buttonNewNumber;
    private JComboBox _comboHigh;
    private JComboBox _comboMid;
    private JComboBox _comboLow;
    private JLabel _labelHigh;
    private JLabel _labelMid;
    private JLabel _labelLow;
    private BitRadioButton[] _bitsHigh;
    private BitRadioButton[] _bitsMid;
    private BitRadioButton[] _bitsLow;
    private static RadioEvent _parent;
    private int _intHighNumber;
    private int _intMidNumber;
    private int _intLowNumber;
    private final int _seed;
    private final Random _random;
    
    public Bin2OctPanel(final RadioEvent parent) {
        this._seed = new Random().nextInt();
        this._random = new Random(this._seed);
        Bin2OctPanel._parent = parent;
        this.setLayout(new GridBagLayout());
        this.setBackground(Main.WINDOW_COLOR);
        final GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = 2;
        this._bitsHigh = new BitRadioButton[3];
        this._bitsMid = new BitRadioButton[3];
        this._bitsLow = new BitRadioButton[3];
        gridBag.gridy = 0;
        this.pTitle("Binary To Octal", gridBag);
        final GridBagConstraints gridBagConstraints = gridBag;
        ++gridBagConstraints.gridy;
        this.pBitLabels(gridBag);
        final GridBagConstraints gridBagConstraints2 = gridBag;
        ++gridBagConstraints2.gridy;
        this.pBits(gridBag);
        final GridBagConstraints gridBagConstraints3 = gridBag;
        ++gridBagConstraints3.gridy;
        this.pArrows(gridBag);
        final GridBagConstraints gridBagConstraints4 = gridBag;
        ++gridBagConstraints4.gridy;
        this.pOctal(gridBag);
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
                Bin2OctPanel.this.buttonHelp_Click();
            }
        });
        this.add(this._buttonHelp, gridBag);
        gridBag.gridx = 11;
        gridBag.gridwidth = 3;
        (this._buttonNewNumber = new JButton("New Number")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Bin2OctPanel.this.buttonNewNumber_Click();
            }
        });
        this.add(this._buttonNewNumber, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void buttonHelp_Click() {
        final String high = Integer.toOctalString(this._intHighNumber).toUpperCase();
        if (!this._comboHigh.getSelectedItem().toString().equals(high)) {
            this._comboHigh.setSelectedItem(high);
            this._comboHigh.setVisible(false);
            this._labelHigh.setVisible(true);
            this._labelHigh.setText(high);
            return;
        }
        final String mid = Integer.toOctalString(this._intMidNumber).toUpperCase();
        if (!this._comboMid.getSelectedItem().toString().equals(mid)) {
            this._comboMid.setSelectedItem(mid);
            this._comboMid.setVisible(false);
            this._labelMid.setVisible(true);
            this._labelMid.setText(mid);
            return;
        }
        final String low = Integer.toOctalString(this._intLowNumber).toUpperCase();
        if (!this._comboLow.getSelectedItem().toString().equals(low)) {
            this._comboLow.setSelectedItem(low);
            this._comboLow.setVisible(false);
            this._labelLow.setVisible(true);
            this._labelLow.setText(low);
            this._buttonHelp.setText("Correct");
            this._buttonHelp.setEnabled(false);
            this._buttonNewNumber.requestFocus();
        }
    }
    
    private void buttonNewNumber_Click() {
        Bin2OctPanel._parent.update();
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
            (this._bitsHigh[i] = new BitRadioButton(this, true, imgOff, imgOffPressed, imgOn, imgOnPressed)).setEnabled(false);
            this.add(this._bitsHigh[i], gridBag);
        }
        gridBag.gridx = 5;
        gridBag.ipadx = 12;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsMid.length - 1; i >= 0; --i) {
            gridBag.gridx = 8 - i;
            (this._bitsMid[i] = new BitRadioButton(this, true, imgOff, imgOffPressed, imgOn, imgOnPressed)).setEnabled(false);
            this.add(this._bitsMid[i], gridBag);
        }
        gridBag.gridx = 9;
        gridBag.ipadx = 12;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsLow.length - 1; i >= 0; --i) {
            gridBag.gridx = 12 - i;
            (this._bitsLow[i] = new BitRadioButton(this, true, imgOff, imgOffPressed, imgOn, imgOnPressed)).setEnabled(false);
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
        (this._comboHigh = new JComboBox((ComboBoxModel<E>)new DefaultComboBoxModel<Object>((E[])new String[] { "", "0", "1", "2", "3", "4", "5", "6", "7" }))).setAlignmentX(0.5f);
        this._comboHigh.setFont(new Font("Dialog", 1, 18));
        this._comboHigh.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Bin2OctPanel.this.doComboClick();
            }
        });
        this.add(this._comboHigh, gridBag);
        (this._labelHigh = new JLabel()).setFont(new Font("Dialog", 1, 24));
        this._labelHigh.setHorizontalAlignment(0);
        this._labelHigh.setHorizontalTextPosition(0);
        this._labelHigh.setForeground(Color.green);
        this._labelHigh.setVisible(false);
        this.add(this._labelHigh, gridBag);
        gridBag.gridx = 6;
        gridBag.gridwidth = 3;
        (this._comboMid = new JComboBox((ComboBoxModel<E>)new DefaultComboBoxModel<Object>((E[])new String[] { "", "0", "1", "2", "3", "4", "5", "6", "7" }))).setAlignmentX(0.5f);
        this._comboMid.setFont(new Font("Dialog", 1, 18));
        this._comboMid.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Bin2OctPanel.this.doComboClick();
            }
        });
        this.add(this._comboMid, gridBag);
        (this._labelMid = new JLabel()).setFont(new Font("Dialog", 1, 24));
        this._labelMid.setHorizontalAlignment(0);
        this._labelMid.setHorizontalTextPosition(0);
        this._labelMid.setForeground(Color.green);
        this._labelMid.setVisible(false);
        this.add(this._labelMid, gridBag);
        gridBag.gridx = 10;
        gridBag.gridwidth = 3;
        (this._comboLow = new JComboBox((ComboBoxModel<E>)new DefaultComboBoxModel<Object>((E[])new String[] { "", "0", "1", "2", "3", "4", "5", "6", "7" }))).setAlignmentX(0.5f);
        this._comboLow.setFont(new Font("Dialog", 1, 18));
        this._comboLow.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Bin2OctPanel.this.doComboClick();
            }
        });
        this.add(this._comboLow, gridBag);
        (this._labelLow = new JLabel()).setFont(new Font("Dialog", 1, 24));
        this._labelLow.setHorizontalAlignment(0);
        this._labelLow.setHorizontalTextPosition(0);
        this._labelLow.setForeground(Color.green);
        this._labelLow.setVisible(false);
        this.add(this._labelLow, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void doComboClick() {
        final String high = Integer.toOctalString(this._intHighNumber).toUpperCase();
        final boolean boolHigh = this._comboHigh.getSelectedItem().toString().equals(high);
        if (boolHigh) {
            this._comboHigh.setVisible(false);
            this._labelHigh.setVisible(true);
            this._labelHigh.setText(high);
        }
        final String mid = Integer.toOctalString(this._intMidNumber).toUpperCase();
        final boolean boolMid = this._comboMid.getSelectedItem().toString().equals(mid);
        if (boolMid) {
            this._comboMid.setVisible(false);
            this._labelMid.setVisible(true);
            this._labelMid.setText(mid);
        }
        final String low = Integer.toOctalString(this._intLowNumber).toUpperCase();
        final boolean boolLow = this._comboLow.getSelectedItem().toString().equals(low);
        if (boolLow) {
            this._comboLow.setVisible(false);
            this._labelLow.setVisible(true);
            this._labelLow.setText(low);
        }
        if (boolHigh && boolMid && boolLow) {
            this._buttonHelp.setText("Correct");
            this._buttonHelp.setEnabled(false);
            this._buttonNewNumber.requestFocus();
        }
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
    }
    
    public void update(final int newNumber) {
        this._intHighNumber = newNumber / 64;
        String strBits = "000" + Integer.toBinaryString(this._intHighNumber);
        strBits = strBits.substring(strBits.length() - 3);
        for (int i = strBits.length() - 1; i >= 0; --i) {
            if (strBits.charAt(strBits.length() - i - 1) == '1') {
                this._bitsHigh[i].setSelected(true);
            }
            else {
                this._bitsHigh[i].setSelected(false);
            }
        }
        this._comboHigh.setSelectedIndex(0);
        this._comboHigh.setVisible(true);
        this._labelHigh.setVisible(false);
        this._intMidNumber = newNumber / 8 % 8;
        strBits = "000" + Integer.toBinaryString(this._intMidNumber);
        strBits = strBits.substring(strBits.length() - 3);
        for (int i = strBits.length() - 1; i >= 0; --i) {
            if (strBits.charAt(strBits.length() - i - 1) == '1') {
                this._bitsMid[i].setSelected(true);
            }
            else {
                this._bitsMid[i].setSelected(false);
            }
        }
        this._comboMid.setSelectedIndex(0);
        this._comboMid.setVisible(true);
        this._labelMid.setVisible(false);
        this._intLowNumber = newNumber % 8;
        strBits = "000" + Integer.toBinaryString(this._intLowNumber);
        strBits = strBits.substring(strBits.length() - 3);
        for (int i = strBits.length() - 1; i >= 0; --i) {
            if (strBits.charAt(strBits.length() - i - 1) == '1') {
                this._bitsLow[i].setSelected(true);
            }
            else {
                this._bitsLow[i].setSelected(false);
            }
        }
        this._comboLow.setSelectedIndex(0);
        this._comboLow.setVisible(true);
        this._labelLow.setVisible(false);
        this._buttonHelp.setText("Help");
        this._buttonHelp.setEnabled(true);
    }
}
