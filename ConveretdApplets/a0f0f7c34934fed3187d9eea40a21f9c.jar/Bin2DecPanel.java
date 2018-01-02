import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Bin2DecPanel extends JPanel implements RadioEvent
{
    private static final int COLUMNS = 10;
    private static final int TITLE_HEIGHT = 40;
    private JLabel _labelError;
    private JTextField _textInput;
    private JButton _buttonHelp;
    private JButton _buttonNewNumber;
    private BitRadioButton[] _bitsBinary;
    private int _intNumber;
    private static RadioEvent _parent;
    private final int _seed;
    private final Random _random;
    
    public Bin2DecPanel(final RadioEvent parent) {
        this._seed = new Random().nextInt();
        this._random = new Random(this._seed);
        Bin2DecPanel._parent = parent;
        this.setLayout(new GridBagLayout());
        this.setBackground(Main.WINDOW_COLOR);
        final GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = 2;
        this._bitsBinary = new BitRadioButton[8];
        gridBag.gridy = 0;
        this.pTitle("Binary To Decimal", gridBag);
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
        this.pDecimal(gridBag);
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
                Bin2DecPanel.this.buttonHelp_Click();
            }
        });
        this.add(this._buttonHelp, gridBag);
        gridBag.gridx = 7;
        gridBag.gridwidth = 3;
        (this._buttonNewNumber = new JButton("New Number")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                Bin2DecPanel.this.buttonNewNumber_Click();
            }
        });
        this.add(this._buttonNewNumber, gridBag);
        gridBag.ipady = ipady;
        gridBag.gridwidth = gridwidth;
    }
    
    private void buttonHelp_Click() {
        String input = this._textInput.getText().trim();
        input = input.replaceAll("\\s", "");
        input = input.replaceAll("[)]", "");
        input = input.replaceAll("[(]", "");
        input = input.replaceAll("[=][0-9]+$", "");
        String temp = input.replaceAll("[^0-9+]", "");
        if (!temp.equals(input)) {
            System.out.println("Help: Removed non-chars");
            temp = temp.replaceAll("[+]", " + ");
            this._textInput.setText(temp);
        }
        String results = "";
        final String orignal = input;
        for (int i = this._bitsBinary.length - 1; i >= 0; --i) {
            final int value = (int)Math.pow(2.0, i);
            if (this._bitsBinary[i].isSelected()) {
                temp = input.replaceAll("^" + value + "[+]|[+]" + value + "|^" + value + "$", "");
                if (!temp.equals(input)) {
                    if (results.length() == 0) {
                        results = results + "" + value;
                    }
                    else {
                        results = results + "+" + value;
                    }
                    input = temp;
                }
            }
        }
        if (!orignal.equals(results)) {
            System.out.println("Help: Removed bad numbers");
            results = results.replaceAll("[+]", " + ");
            this._textInput.setText(results);
            return;
        }
        results = "";
        input = orignal;
        for (int i = this._bitsBinary.length - 1; i >= 0; --i) {
            if (this._bitsBinary[i].isSelected()) {
                final int value = (int)Math.pow(2.0, i);
                temp = input.replaceAll("^" + value + "[+]|[+]" + value + "[+]|[+]" + value + "$|^" + value + "$", "");
                if (temp.equals(input)) {
                    input = input + ((input.length() == 0) ? "" : "+") + value;
                    input = input.replaceAll("[+]", " + ");
                    this._textInput.setText(input);
                    return;
                }
            }
        }
        final ParseNode parser = new ParseNode(input);
        final BinaryTreeNode root = this.expressionTree(parser);
        input = input.replaceAll("[+]", " + ");
        this._textInput.setText(input + " = " + Math.round(root.toDouble()));
        this._textInput.setEditable(false);
        this._textInput.setForeground(Color.green);
        this._textInput.setBackground(Color.white);
        this._buttonHelp.setText("Correct");
        this._buttonHelp.setEnabled(false);
        this._buttonNewNumber.requestFocus();
    }
    
    private void buttonNewNumber_Click() {
        Bin2DecPanel._parent.update();
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
        for (int i = this._bitsBinary.length - 1; i >= 0; --i) {
            gridBag.gridx = this._bitsBinary.length - i;
            (this._bitsBinary[i] = new BitRadioButton(this, true, imgOff, imgOffPressed, imgOn, imgOnPressed)).setEnabled(false);
            this.add(this._bitsBinary[i], gridBag);
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
        gridBag.gridx = 1;
        gridBag.gridwidth = 8;
        gridBag.ipady = 8;
        (this._textInput = new JTextField()).setBorder(new BorderUIResource.EtchedBorderUIResource(1, Color.orange, this.getBackground()));
        this._textInput.setFont(new Font("Dialog", 1, 12));
        this.add(this._textInput, gridBag);
        this._textInput.addKeyListener(new KeyListener() {
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    Bin2DecPanel.this.doEnterClick();
                }
                else {
                    Bin2DecPanel.this._labelError.setText("");
                }
            }
            
            public void keyReleased(final KeyEvent e) {
            }
            
            public void keyTyped(final KeyEvent e) {
            }
        });
        final GridBagConstraints gridBagConstraints = gridBag;
        ++gridBagConstraints.gridy;
        (this._labelError = new JLabel("")).setBounds(this._textInput.getX(), 100, this._textInput.getWidth(), 20);
        this._labelError.setForeground(Color.red);
        this._labelError.setHorizontalTextPosition(0);
        this._labelError.setHorizontalAlignment(0);
        this.add(this._labelError, gridBag);
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
    }
    
    public void update(final int newNumber) {
        this._intNumber = newNumber;
        String strBits = "00000000" + Integer.toBinaryString(this._intNumber);
        strBits = strBits.substring(strBits.length() - 8);
        for (int i = this._bitsBinary.length - 1; i >= 0; --i) {
            if (strBits.charAt(strBits.length() - i - 1) == '1') {
                this._bitsBinary[i].setSelected(true);
            }
            else {
                this._bitsBinary[i].setSelected(false);
            }
        }
        this._buttonHelp.setText("Help");
        this._buttonHelp.setEnabled(true);
        this._textInput.setText("");
        this._textInput.setEditable(true);
        this._textInput.setForeground(Color.black);
        this._textInput.setBackground(Color.white);
    }
    
    private void doEnterClick() {
        String input = this._textInput.getText().trim();
        input = input.replaceAll("\\s", "");
        input = input.replaceAll("[)]", "");
        input = input.replaceAll("[(]", "");
        input = input.replaceAll("[=][0-9]+$", "");
        System.out.println("Parser = " + input);
        final ParseNode parser = new ParseNode(input);
        if (!this.isPlusAndDigits(input)) {
            this._labelError.setText("Error! Only '+' and integers allowed!");
        }
        else {
            final BinaryTreeNode root = this.expressionTree(parser);
            if (parser.notDone()) {
                this._labelError.setText("Error! Cannot parse data");
                return;
            }
            final int value = (int)Math.round(root.toDouble());
            System.out.println("Value = " + value);
            input = input.replaceAll("[+]", " + ");
            this._textInput.setText(input + " = " + value);
            if (this._intNumber == value) {
                this._textInput.setEditable(false);
                this._textInput.setForeground(Color.green);
                this._textInput.setBackground(Color.white);
                this._buttonHelp.setText("Correct");
                this._buttonHelp.setEnabled(false);
                this._buttonNewNumber.requestFocus();
            }
        }
    }
    
    private boolean isPlusAndDigits(final String input) {
        for (int i = 0; i < input.length(); ++i) {
            if (!Character.isDigit(input.charAt(i)) && input.charAt(i) != '+') {
                return false;
            }
        }
        return true;
    }
    
    private BinaryTreeNode expressionTree(final ParseNode parser) {
        BinaryTreeNode exp = this.termTree(parser);
        while (parser.notDone() && (parser.charAt() == '+' || parser.charAt() == '-')) {
            final char op = parser.charAt();
            parser.incr();
            final BinaryTreeNode nextTerm = this.termTree(parser);
            exp = new BinaryTreeNode(new String("" + op), exp, nextTerm);
        }
        return exp;
    }
    
    private BinaryTreeNode termTree(final ParseNode parser) {
        BinaryTreeNode term = this.factorTree(parser);
        while (parser.notDone() && (parser.charAt() == '*' || parser.charAt() == '/')) {
            final char op = parser.charAt();
            parser.incr();
            final BinaryTreeNode nextFactor = this.factorTree(parser);
            term = new BinaryTreeNode(new String("" + op), term, nextFactor);
        }
        return term;
    }
    
    private BinaryTreeNode factorTree(final ParseNode parser) {
        if (parser.isDone()) {
            return new BinaryTreeNode(new String());
        }
        final char ch = parser.charAt();
        if (Character.isDigit(ch) || ch == '+' || ch == '-') {
            final String strValue = this.parseNumber(parser);
            if (strValue.length() > 0) {
                int intValue;
                try {
                    intValue = Integer.parseInt(strValue);
                }
                catch (NumberFormatException e) {
                    this._labelError.setText("Error! Converting \"" + strValue + "\" to a number");
                    return new BinaryTreeNode(new String());
                }
                return new BinaryTreeNode(new String("" + intValue));
            }
        }
        else {
            if (ch == '(') {
                parser.incr();
                final BinaryTreeNode exp = this.expressionTree(parser);
                if (parser.notDone() && parser.charAt() == ')') {
                    parser.incr();
                }
                else {
                    this._labelError.setText("Error! Missing right parenthesis");
                }
                return exp;
            }
            if (ch == ')') {
                this._labelError.setText("Error! Extra right parenthesis");
            }
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                this._labelError.setText("Error! Misplaced operator");
            }
            else {
                this._labelError.setText("Error! Unexpected character \"" + ch + "\" encountered");
            }
        }
        return new BinaryTreeNode(new String());
    }
    
    private String parseNumber(final ParseNode parser) {
        final StringBuffer results = new StringBuffer("");
        if (parser.isDone()) {
            return "";
        }
        if (parser.charAt() == '+' || parser.charAt() == '-') {
            results.append(parser.charAt());
            parser.incr();
        }
        while (parser.notDone() && Character.isDigit(parser.charAt())) {
            results.append(parser.charAt());
            parser.incr();
        }
        return results.toString();
    }
}
