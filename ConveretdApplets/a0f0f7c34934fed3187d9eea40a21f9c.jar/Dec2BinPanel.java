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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Dec2BinPanel extends JPanel implements RadioEvent
{
    private static final int COLUMNS = 10;
    private static final int TITLE_HEIGHT = 40;
    private JLabel _labelDecimal;
    private JButton _buttonHelp;
    private JButton _buttonNewNumber;
    private BitRadioButton[] _bitsBinary;
    private int _intNumber;
    private final int _seed;
    private final Random _random;
    private static RadioEvent _parent;
    
    public Dec2BinPanel(final RadioEvent parent) {
        this._seed = new Random().nextInt();
        this._random = new Random(this._seed);
        Dec2BinPanel._parent = parent;
        this.setLayout(new GridBagLayout());
        this.setBackground(Main.WINDOW_COLOR);
        final GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = 2;
        gridBag.gridy = 0;
        this.pTitle("Decimal To Binary", gridBag);
        final GridBagConstraints gridBagConstraints = gridBag;
        ++gridBagConstraints.gridy;
        this.pDecimal(gridBag);
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
        gridBag.gridx = 0;
        gridBag.gridwidth = 3;
        (this._buttonHelp = new JButton("Help")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Dec2BinPanel.this.buttonHelp_Click();
            }
        });
        this.add(this._buttonHelp, gridBag);
        gridBag.gridx = 7;
        gridBag.gridwidth = 3;
        (this._buttonNewNumber = new JButton("New Number")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Dec2BinPanel.this.buttonNewNumber_Click();
            }
        });
        this.add(this._buttonNewNumber, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void buttonHelp_Click() {
        String strBits = "00000000" + Integer.toBinaryString(this._intNumber);
        strBits = strBits.substring(strBits.length() - 8);
        for (int i = this._bitsBinary.length - 1; i >= 0; --i) {
            if (this._bitsBinary[i].isSelected() && strBits.charAt(strBits.length() - i - 1) == '0') {
                this._bitsBinary[i].doClick(200);
                break;
            }
            if (!this._bitsBinary[i].isSelected() && strBits.charAt(strBits.length() - i - 1) == '1') {
                this._bitsBinary[i].doClick(200);
                break;
            }
        }
    }
    
    private void buttonNewNumber_Click() {
        Dec2BinPanel._parent.update();
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
        final BitRadioButton arrowSign = new BitRadioButton(this, false, imgArrow, imgArrow, imgArrow, imgArrow);
        arrowSign.setEnabled(false);
        this.add(arrowSign, gridBag);
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
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        this._bitsBinary = new BitRadioButton[8];
        for (int i = this._bitsBinary.length - 1; i >= 0; --i) {
            gridBag.gridx = this._bitsBinary.length - i;
            this.add(this._bitsBinary[i] = new BitRadioButton(this, true, imgOff, imgOffPressed, imgOn, imgOnPressed), gridBag);
        }
        gridBag.gridx = 9;
        gridBag.ipadx = 32;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
    }
    
    private void pBitLabels(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipadx = gridBag.ipadx;
        gridBag.gridx = 0;
        gridBag.ipadx = 32;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
        for (int i = this._bitsBinary.length - 1; i >= 0; --i) {
            gridBag.gridx = this._bitsBinary.length - i;
            final JLabel label = new JLabel("" + (int)Math.pow(2.0, i));
            label.setHorizontalTextPosition(0);
            label.setHorizontalAlignment(0);
            label.setFont(new Font("Dialog", 1, 12));
            label.setForeground(Color.white);
            this.add(label, gridBag);
        }
        gridBag.gridx = 9;
        gridBag.ipadx = 32;
        this.add(new JLabel(""), gridBag);
        gridBag.ipadx = ipadx;
    }
    
    private void pDecimal(GridBagConstraints gridBag) {
        if (gridBag == null) {
            gridBag = new GridBagConstraints();
        }
        final int ipady = gridBag.ipady;
        final int gridwidth = gridBag.gridwidth;
        gridBag.gridx = 3;
        gridBag.gridwidth = 4;
        gridBag.ipady = 20;
        (this._labelDecimal = new JLabel("127")).setHorizontalTextPosition(0);
        this._labelDecimal.setHorizontalAlignment(0);
        this._labelDecimal.setFont(new Font("Dialog", 1, 32));
        this._labelDecimal.setForeground(Color.white);
        this.add(this._labelDecimal, gridBag);
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
        gridBag.gridwidth = 10;
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
        gridBag.gridx = 0;
        gridBag.ipady = 20;
        this.add(new JLabel(""), gridBag);
        gridBag.ipady = ipady;
    }
    
    public void update() {
        int value = 0;
        for (int i = 0; i < this._bitsBinary.length; ++i) {
            if (this._bitsBinary[i].isSelected()) {
                value += (int)Math.pow(2.0, i);
            }
        }
        value = this._intNumber - value;
        this._labelDecimal.setText("" + value);
        if (value < 0) {
            this._labelDecimal.setForeground(Color.red);
        }
        else if (value > 0) {
            this._labelDecimal.setForeground(Color.white);
        }
        else {
            this._labelDecimal.setForeground(Color.green);
            this._labelDecimal.setText("" + this._intNumber);
            for (int i = 0; i < this._bitsBinary.length; ++i) {
                this._bitsBinary[i].setEnabled(false);
            }
            this._buttonHelp.setText("Correct");
            this._buttonHelp.setEnabled(false);
            this._buttonNewNumber.requestFocus();
        }
    }
    
    public void update(final int newNumber) {
        this._intNumber = newNumber;
        for (int i = this._bitsBinary.length - 1; i >= 0; --i) {
            if (this._bitsBinary[i].isSelected()) {
                this._bitsBinary[i].setSelected(false);
            }
            this._bitsBinary[i].setEnabled(true);
        }
        this._labelDecimal.setText("" + this._intNumber);
        this._labelDecimal.setForeground(Color.white);
        this._buttonHelp.setText("Help");
        this._buttonHelp.setEnabled(true);
    }
}
