import java.util.Enumeration;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UI_TreeRepresentation extends JApplet
{
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final Color WINOW_COLOR;
    private final boolean _boolApplet;
    private boolean _boolLoaded;
    private JLabel _labelError;
    private JTextField _textInput;
    private JButton _buttonInput;
    private TreePanel _treePanel;
    private JScrollPane _scrollTree;
    private int _index;
    private String _parser;
    
    public UI_TreeRepresentation() {
        this(true);
    }
    
    public UI_TreeRepresentation(final boolean applet) {
        this._boolApplet = applet;
        if (this._boolApplet) {
            this.getRootPane().putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        }
    }
    
    public final void init() {
        this._boolLoaded = false;
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(UI_TreeRepresentation.WINOW_COLOR);
        this.pInitTitle("Tree Representation");
        final JLabel label = new JLabel("Enter expression: ");
        label.setBounds(30, 75, 150, 20);
        label.setForeground(Color.white);
        this.getContentPane().add(label);
        (this._textInput = new JTextField()).setBorder(new BorderUIResource.EtchedBorderUIResource(1, Color.orange, this.getContentPane().getBackground()));
        this._textInput.setFont(new Font("Dialog", 0, 12));
        this._textInput.setBounds(180, 75, 440, 20);
        this.getContentPane().add(this._textInput);
        (this._labelError = new JLabel("Error")).setBounds(this._textInput.getX(), 100, this._textInput.getWidth(), 20);
        this._labelError.setForeground(Color.red);
        this._labelError.setHorizontalTextPosition(0);
        this._labelError.setHorizontalAlignment(0);
        this._labelError.setVisible(false);
        this.getContentPane().add(this._labelError);
        (this._buttonInput = new JButton("Parse")).setBounds(670, 75, 100, 20);
        this._buttonInput.setEnabled(false);
        this.getContentPane().add(this._buttonInput);
        this._treePanel = new TreePanel(new BinaryTreeNode(), 720);
        (this._scrollTree = new JScrollPane(this._treePanel)).setHorizontalScrollBarPolicy(32);
        this._scrollTree.setVerticalScrollBarPolicy(22);
        this._scrollTree.setBounds(30, 125, 740, 430);
        this._scrollTree.setBorder(new LineBorder(Color.orange, 1));
        this.getContentPane().add(this._scrollTree);
        this._buttonInput.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent evt) {
                UI_TreeRepresentation.this.doEnterClick();
            }
        });
        this._textInput.addKeyListener(new KeyListener() {
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyChar() == '\n') {
                    UI_TreeRepresentation.this.doEnterClick();
                }
                else if (UI_TreeRepresentation.this._labelError.isVisible()) {
                    UI_TreeRepresentation.this._labelError.setVisible(false);
                }
            }
            
            public void keyReleased(final KeyEvent e) {
                if (!UI_TreeRepresentation.this._buttonInput.isEnabled() && UI_TreeRepresentation.this._textInput.getText().trim().length() > 0) {
                    UI_TreeRepresentation.this._buttonInput.setEnabled(true);
                }
                else if (UI_TreeRepresentation.this._buttonInput.isEnabled() && UI_TreeRepresentation.this._textInput.getText().trim().length() == 0) {
                    UI_TreeRepresentation.this._buttonInput.setEnabled(false);
                }
            }
            
            public void keyTyped(final KeyEvent e) {
            }
        });
        if (!this._boolLoaded) {
            this._boolLoaded = true;
        }
    }
    
    private void doEnterClick() {
        this._parser = this._textInput.getText().trim();
        this._parser = this._parser.replaceAll("\t", "");
        this._parser = this._parser.replaceAll("\n", "");
        this._parser = this._parser.replaceAll("\r", "");
        this._parser = this._parser.replaceAll(" ", "");
        this._parser = this._parser.replaceAll("()", "");
        System.out.println("Parser = " + this._parser);
        this._index = 0;
        this._scrollTree.setViewportView(null);
        this._treePanel = new TreePanel(new BinaryTreeNode(), this._scrollTree.getWidth() - 20);
        if (!this.isBalanced(this._parser)) {
            this._labelError.setText("Error! Parentheses don't match");
            this._labelError.setVisible(true);
        }
        else {
            final BinaryTreeNode root = this.expressionTree();
            if (!this._labelError.isVisible() && this._index < this._parser.length()) {
                this._labelError.setText("Error! Cannot parse data");
                this._labelError.setVisible(true);
            }
            final Enumeration enum1 = root.postorderEnumeration();
            while (enum1.hasMoreElements()) {
                final Object obj = enum1.nextElement();
                System.out.println("" + obj.toString());
            }
            System.out.println("Value = " + Math.round(root.toDouble() * 1000.0) / 1000.0);
            this._treePanel = new TreePanel(root, this._scrollTree.getWidth() - 20);
        }
        this._scrollTree.setViewportView(this._treePanel);
        this._scrollTree.repaint();
        this._treePanel.scrollRectToVisible(new Rectangle((this._treePanel.getWidth() - this._scrollTree.getWidth()) / 2, 0, this._scrollTree.getWidth(), this._scrollTree.getHeight()));
    }
    
    private int pParseNumber(final String parser, int index) {
        if (index >= parser.length()) {
            return index;
        }
        if (parser.charAt(index) == '+' || parser.charAt(index) == '-' || parser.charAt(index) == ' ') {
            ++index;
        }
        while (index < parser.length() && ((parser.charAt(index) >= '0' && parser.charAt(index) <= '9') || parser.charAt(index) == '.')) {
            ++index;
        }
        return index;
    }
    
    private BinaryTreeNode expressionTree() {
        BinaryTreeNode exp = this.termTree();
        while (this._index < this._parser.length() && (this._parser.charAt(this._index) == '+' || this._parser.charAt(this._index) == '-')) {
            final char op = this._parser.charAt(this._index++);
            final BinaryTreeNode nextTerm = this.termTree();
            exp = new BinaryTreeNode(new LabelNode("" + op), exp, nextTerm);
        }
        return exp;
    }
    
    private BinaryTreeNode termTree() {
        BinaryTreeNode term = this.factorTree();
        while (this._index < this._parser.length() && (this._parser.charAt(this._index) == '*' || this._parser.charAt(this._index) == '/')) {
            final char op = this._parser.charAt(this._index++);
            final BinaryTreeNode nextFactor = this.factorTree();
            term = new BinaryTreeNode(new LabelNode("" + op), term, nextFactor);
        }
        return term;
    }
    
    private boolean isBalanced(final String parser) {
        int lefts = 0;
        int rights = 0;
        for (int i = 0; i < parser.length(); ++i) {
            if (parser.charAt(i) == '(') {
                ++lefts;
            }
            else if (parser.charAt(i) == ')') {
                ++rights;
            }
        }
        return lefts == rights;
    }
    
    private BinaryTreeNode factorTree() {
        if (this._index >= this._parser.length()) {
            return new BinaryTreeNode(new LabelNode());
        }
        final char ch = this._parser.charAt(this._index);
        if (Character.isDigit(ch) || ch == '+' || ch == '-' || ch == '.') {
            final int intStart = this._index;
            final int intStop = this.pParseNumber(this._parser, this._index);
            if (intStop > intStart) {
                this._index = intStop;
                double dblValue;
                try {
                    dblValue = Double.parseDouble(this._parser.substring(intStart, intStop));
                    dblValue = Math.round(dblValue * 100.0) / 100.0;
                }
                catch (NumberFormatException e) {
                    this._labelError.setText("Error! Converting \"" + this._parser.substring(intStart, intStop) + "\" to a number");
                    this._labelError.setVisible(true);
                    return new BinaryTreeNode(new LabelNode());
                }
                System.out.println("Double = " + dblValue);
                return new BinaryTreeNode(new LabelNode("" + dblValue));
            }
        }
        else {
            if (ch == '(') {
                ++this._index;
                final BinaryTreeNode exp = this.expressionTree();
                if (this._index >= this._parser.length() || this._parser.charAt(this._index) != ')') {
                    this._labelError.setText("Error! Missing right parenthesis");
                    this._labelError.setVisible(true);
                }
                ++this._index;
                return exp;
            }
            if (ch == ')') {
                this._labelError.setText("Error! Extra right parenthesis");
                this._labelError.setVisible(true);
            }
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                this._labelError.setText("Error! Misplaced operator");
                this._labelError.setVisible(true);
            }
            else {
                this._labelError.setText("Error! Unexpected character \"" + ch + "\" encountered");
                this._labelError.setVisible(true);
            }
        }
        return new BinaryTreeNode(new LabelNode());
    }
    
    private void pInitTitle(String sTitle) {
        if (sTitle == null || sTitle.length() == 0) {
            sTitle = "User Interface";
        }
        final JLabel labelTitle = new JLabel(sTitle);
        labelTitle.setHorizontalTextPosition(0);
        labelTitle.setHorizontalAlignment(0);
        labelTitle.setFont(new Font("Dialog", 1, 32));
        labelTitle.setForeground(Color.orange);
        labelTitle.setBounds(0, 10, 800, 50);
        this.getContentPane().add(labelTitle);
    }
    
    static {
        WINOW_COLOR = new Color(123, 123, 123);
    }
}
