import java.awt.Container;
import java.awt.Event;
import java.awt.Frame;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Component;
import java.beans.PropertyVetoException;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import symantec.itools.awt.BorderPanel;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ErrorHandler extends Dialog implements Runnable
{
    BorderPanel borderPnl;
    Panel centerPnl;
    Panel suggestPnl;
    List suggList;
    Label label_2;
    TextField wordField;
    Panel eastPnl;
    Panel buttonPnl;
    Button replace;
    Button replaceAll;
    Button ignore;
    Button ignoreAll;
    Button learnButton;
    Button stopButton;
    public TextInfo textInfo;
    public TextArea text;
    public String checkWord;
    public int errorPos;
    private int xPosition;
    private int yPosition;
    public Object arg;
    JMProps props;
    
    void layoutFields() {
        this.setLayout(new BorderLayout(0, 0));
        this.setVisible(false);
        this.setSize(this.insets().left + this.insets().right + 430, this.insets().top + this.insets().bottom + 270);
        this.borderPnl = new BorderPanel();
        try {
            this.borderPnl.setBevelStyle(3);
        }
        catch (PropertyVetoException ex) {}
        this.borderPnl.setLayout((LayoutManager)new BorderLayout(10, 5));
        this.borderPnl.setBounds(this.insets().left, this.insets().top, 430, 270);
        this.add("Center", (Component)this.borderPnl);
        (this.centerPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.centerPnl.setBounds(0, 0, 339, 244);
        ((Container)this.borderPnl).add("Center", this.centerPnl);
        (this.suggestPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.suggestPnl.setBounds(0, 24, 409, 244);
        this.centerPnl.add("Center", this.suggestPnl);
        (this.suggList = new List(0, false)).setBounds(0, 24, 409, 244);
        this.suggestPnl.add("Center", this.suggList);
        (this.label_2 = new Label(this.props.language.getProperty("spelling.suggestions", "Suggestions:"))).setBounds(0, 0, 409, 24);
        this.suggestPnl.add("North", this.label_2);
        (this.wordField = new TextField()).setBounds(0, 0, 389, 24);
        this.centerPnl.add("North", this.wordField);
        (this.eastPnl = new Panel()).setLayout(new BorderLayout(0, 0));
        this.eastPnl.setBounds(331, 0, 78, 244);
        ((Container)this.borderPnl).add("East", this.eastPnl);
        (this.buttonPnl = new Panel()).setLayout(new GridLayout(6, 1, 0, 5));
        this.buttonPnl.setBounds(0, 0, 60, 169);
        this.eastPnl.add("North", this.buttonPnl);
        (this.replace = new Button()).setActionCommand("button");
        this.replace.setLabel(this.props.language.getProperty("spelling.replace", "Replace"));
        this.replace.setBounds(0, 0, 60, 24);
        this.buttonPnl.add(this.replace);
        (this.replaceAll = new Button()).setActionCommand("button");
        this.replaceAll.setLabel(this.props.language.getProperty("spelling.replace_all", "Replace All"));
        this.replaceAll.setBounds(0, 29, 78, 24);
        this.buttonPnl.add(this.replaceAll);
        (this.ignore = new Button()).setActionCommand("button");
        this.ignore.setLabel(this.props.language.getProperty("spelling.ignore", "Ignore"));
        this.ignore.setBounds(0, 58, 78, 24);
        this.buttonPnl.add(this.ignore);
        (this.ignoreAll = new Button()).setActionCommand("button");
        this.ignoreAll.setLabel(this.props.language.getProperty("spelling.ignore_all", "Ignore All"));
        this.ignoreAll.setBounds(0, 87, 78, 24);
        this.buttonPnl.add(this.ignoreAll);
        (this.learnButton = new Button()).setActionCommand("button");
        this.learnButton.setLabel(this.props.language.getProperty("spelling.learn", "Learn"));
        this.learnButton.setVisible(false);
        this.learnButton.setBounds(0, 116, 78, 24);
        this.buttonPnl.add(this.learnButton);
        (this.stopButton = new Button()).setActionCommand("button");
        this.stopButton.setLabel(this.props.language.getProperty("spelling.stop", "Stop"));
        this.stopButton.setBounds(0, 145, 78, 24);
        this.buttonPnl.add(this.stopButton);
        this.borderPnl.setBackground(this.props.background);
        ((Component)this.borderPnl).setForeground(this.props.foreground);
        this.setFont(this.props.font);
        this.setForeground(this.props.foreground);
        this.wordField.setBackground(Color.white);
        this.wordField.setForeground(Color.black);
        this.suggList.setBackground(Color.white);
        this.suggList.setForeground(Color.black);
        this.setTitle(this.props.language.getProperty("title.spelling", "Spell Check"));
    }
    
    public ErrorHandler(final sendFrame sendFrame, final TextArea text, final WordBundle wordBundle, final String[] array, final TextInfo textInfo, final int n, final int n2) {
        super(sendFrame, "Spell Checker - Error Detected", true);
        this.props = sendFrame.props;
        this.layoutFields();
        this.text = text;
        this.checkWord = wordBundle.word;
        this.textInfo = textInfo;
        this.errorPos = wordBundle.position;
        this.wordField.setText(this.checkWord);
        if (array.length > 0) {
            for (int n3 = 0; n3 < array.length && array[n3] != null; ++n3) {
                this.suggList.addItem(array[n3]);
            }
        }
        if (n == 0 && n2 == 0) {
            j_util.centerDialog(this, sendFrame);
            SpellEngine.setErrPos(this.location());
        }
        else {
            this.move(n, n2);
        }
        this.xPosition = this.location().x;
        this.yPosition = this.location().y;
    }
    
    public void run() {
        this.text.select(this.errorPos, this.errorPos + this.checkWord.length());
        this.show();
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.ignore) {
                    this.text.select(this.errorPos, this.errorPos);
                    SpellEngine.resumeChecking(this.textInfo);
                    break;
                }
                if (event.target == this.suggList) {
                    this.wordField.setText(this.suggList.getSelectedItem());
                    this.textInfo.curPos = this.errorPos;
                    SpellEngine.replaceSingle(this.textInfo, this.checkWord, this.wordField.getText());
                    SpellEngine.resetChecker();
                    SpellEngine.resumeChecking(this.textInfo);
                    break;
                }
                if (event.target == this.ignoreAll) {
                    SpellEngine.addIgnore(this.checkWord);
                    SpellEngine.resumeChecking(this.textInfo);
                    break;
                }
                if (event.target == this.replace) {
                    this.textInfo.curPos = this.errorPos;
                    SpellEngine.replaceSingle(this.textInfo, this.checkWord, this.wordField.getText());
                    SpellEngine.resetChecker();
                    SpellEngine.resumeChecking(this.textInfo);
                    break;
                }
                if (event.target == this.replaceAll) {
                    this.textInfo.curPos = this.errorPos;
                    SpellEngine.replaceAll(this.textInfo, this.checkWord, this.wordField.getText());
                    SpellEngine.resetChecker();
                    SpellEngine.resumeChecking(this.textInfo);
                    break;
                }
                if (event.target == this.learnButton) {
                    SpellEngine.addLearned(this.checkWord);
                    SpellEngine.resumeChecking(this.textInfo);
                    break;
                }
                if (event.target == this.stopButton) {
                    SpellEngine.stopped = true;
                    SpellEngine.resumeChecking(this.textInfo);
                    break;
                }
                break;
            }
            case 701: {
                this.wordField.setText(this.suggList.getSelectedItem());
                break;
            }
            case 205: {
                SpellEngine.setErrPos(this.location());
                break;
            }
            default: {
                if (this.location().x != this.xPosition || this.location().y != this.yPosition) {
                    SpellEngine.setErrPos(this.location());
                    this.xPosition = this.location().x;
                    this.yPosition = this.location().y;
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
