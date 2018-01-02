import java.awt.event.WindowAdapter;
import java.awt.event.ItemEvent;
import com.wintertree.util.MessageBox;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import com.wintertree.ssce.MemTextLexicon;
import com.wintertree.ssce.SuggestionSet;
import com.wintertree.ssce.Lexicon;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Dimension;
import com.wintertree.ssce.TypographicalComparator;
import com.wintertree.ssce.FileTextLexicon;
import java.awt.TextArea;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.util.Stack;
import com.wintertree.ssce.SpellingSession;
import com.wintertree.ssce.WordParser;
import com.wintertree.ssce.WordComparator;
import java.awt.List;
import javax.swing.JTextArea;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpellingDialog extends Dialog
{
    public boolean debug;
    public boolean completed;
    boolean fComponentsAdjusted;
    Button cancelButton;
    Button changeButton;
    Button changeAllButton;
    TextField changeToField;
    Label label1;
    JTextArea contextArea;
    Button ignoreButton;
    Button ignoreAllButton;
    TextField problemField;
    Label problemLabel;
    Button suggestButton;
    Button undoButton;
    List suggestionsList;
    Label label2;
    Label label3;
    public int minSuggestDepth;
    protected boolean canceled;
    protected WordComparator comparator;
    protected WordParser parser;
    protected SpellingSession speller;
    private boolean doubledWord;
    private int suggestSearchDepth;
    private String suggestWord;
    private Stack undoStack;
    
    protected SpellingDialog(final Frame frame, final boolean b) {
        super(frame, "Check spelling", b);
        this.debug = true;
        this.completed = false;
        this.fComponentsAdjusted = false;
        this.cancelButton = new Button();
        this.changeButton = new Button();
        this.changeAllButton = new Button();
        this.changeToField = new TextField();
        this.label1 = new Label();
        this.contextArea = new JTextArea("", 0, 0);
        this.ignoreButton = new Button();
        this.ignoreAllButton = new Button();
        this.problemField = new TextField();
        this.problemLabel = new Label();
        this.suggestButton = new Button();
        this.undoButton = new Button();
        this.suggestionsList = new List(4);
        this.label2 = new Label();
        this.label3 = new Label();
        frame.setSize(521, 327);
        this.setLayout(null);
        this.setSize(521, 327);
        this.setVisible(false);
        this.cancelButton.setLabel("Cancel");
        this.add(this.cancelButton);
        this.cancelButton.setBackground(Color.lightGray);
        this.cancelButton.setBounds(352, 181, 98, 22);
        this.changeButton.setLabel("Change");
        this.add(this.changeButton);
        this.changeButton.setBackground(Color.lightGray);
        this.changeButton.setBounds(352, 66, 98, 22);
        this.changeAllButton.setLabel("Change all");
        this.add(this.changeAllButton);
        this.changeAllButton.setBackground(Color.lightGray);
        this.changeAllButton.setBounds(352, 95, 98, 22);
        this.add(this.changeToField);
        this.changeToField.setBounds(16, 72, 312, 25);
        this.label1.setText("Change to:");
        this.add(this.label1);
        this.label1.setBounds(16, 56, 312, 16);
        this.contextArea.setEditable(false);
        this.add(this.contextArea);
        this.contextArea.setBounds(16, 208, 440, 225);
        this.ignoreButton.setLabel("Ignore");
        this.add(this.ignoreButton);
        this.ignoreButton.setBackground(Color.lightGray);
        this.ignoreButton.setBounds(352, 8, 98, 22);
        this.ignoreAllButton.setLabel("Ignore all");
        this.add(this.ignoreAllButton);
        this.ignoreAllButton.setBackground(Color.lightGray);
        this.ignoreAllButton.setBounds(352, 37, 98, 22);
        this.add(this.problemField);
        this.problemField.setBounds(16, 24, 312, 23);
        this.problemLabel.setText("Not in dictionary2:");
        this.add(this.problemLabel);
        this.problemLabel.setBounds(16, 8, 312, 16);
        this.suggestButton.setLabel("Suggest");
        this.add(this.suggestButton);
        this.suggestButton.setBackground(Color.lightGray);
        this.suggestButton.setBounds(352, 124, 98, 22);
        this.undoButton.setLabel("Undo");
        this.add(this.undoButton);
        this.undoButton.setBackground(Color.lightGray);
        this.undoButton.setBounds(352, 152, 98, 22);
        this.add(this.suggestionsList);
        this.suggestionsList.setBounds(16, 120, 312, 77);
        this.label2.setText("Suggestions:");
        this.add(this.label2);
        this.label2.setBounds(16, 104, 312, 16);
        this.setResizable(true);
        this.addWindowListener(new SymWindow());
        final SymAction symAction = new SymAction();
        this.ignoreButton.addActionListener(symAction);
        this.ignoreAllButton.addActionListener(symAction);
        this.changeButton.addActionListener(symAction);
        this.changeAllButton.addActionListener(symAction);
        this.suggestButton.addActionListener(symAction);
        this.suggestionsList.addActionListener(symAction);
        this.undoButton.addActionListener(symAction);
        this.cancelButton.addActionListener(symAction);
        this.suggestionsList.addItemListener(new SymItem());
    }
    
    public SpellingDialog(final Frame frame, final SpellingSession speller, final TextArea textArea, final WordParser parser, WordComparator comparator, final FileTextLexicon[] array) {
        this(frame, false);
        this.canceled = false;
        this.completed = false;
        this.speller = speller;
        if (comparator == null) {
            comparator = (WordComparator)new TypographicalComparator();
        }
        this.comparator = comparator;
        this.doubledWord = false;
        this.minSuggestDepth = 10;
        this.suggestSearchDepth = 0;
        this.suggestWord = "";
        this.undoStack = new Stack();
        this.contextArea.setText(textArea.getText());
        this.parser = parser;
    }
    
    public SpellingDialog(final Frame frame, final SpellingSession speller, final JTextArea textArea, final WordParser parser, WordComparator comparator, final FileTextLexicon[] array) {
        this(frame, false);
        this.canceled = false;
        this.completed = false;
        this.speller = speller;
        if (comparator == null) {
            comparator = (WordComparator)new TypographicalComparator();
        }
        this.comparator = comparator;
        this.doubledWord = false;
        this.minSuggestDepth = 10;
        this.suggestSearchDepth = 0;
        this.suggestWord = "";
        this.undoStack = new Stack();
        this.contextArea.setText(textArea.getText());
        this.parser = parser;
    }
    
    public void setVisible(final boolean b) {
        if (b) {
            if (this.runChecker()) {
                super.setVisible(b);
            }
        }
        else {
            super.setVisible(b);
        }
    }
    
    protected boolean runChecker() {
        this.suggestionsList.removeAll();
        this.problemField.setText("");
        this.changeToField.setText("");
        this.changeButton.setLabel("Change");
        this.doubledWord = false;
        this.suggestWord = "";
        this.updateUI();
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        final int n2 = 5;
        int n3 = -1;
        int length = 0;
        int n4;
        int check;
        do {
            this.busy(true);
            check = this.speller.check(this.parser, sb);
            this.busy(false);
            if (this.debug) {
                System.out.println("runChecker: check result: " + check + " word: " + (this.parser.hasMoreElements() ? this.parser.getWord() : "(none)") + " replWord: " + sb.toString());
            }
            final int n5 = check;
            final SpellingSession speller = this.speller;
            if ((n5 & 0x1) != 0x0) {
                final int cursor = this.parser.getCursor();
                if (cursor >= n3 && cursor < n3 + length) {
                    ++n;
                }
                else {
                    n = 0;
                }
                if (n >= n2) {
                    this.parser.nextWord();
                }
                else {
                    this.problemField.setText(this.parser.getWord());
                    this.changeToField.setText(sb.toString());
                    this.changeWord();
                    n3 = cursor;
                    length = sb.length();
                }
            }
            if (this.debug) {
                System.out.println("End of While Loop");
            }
            n4 = check;
            final SpellingSession speller2 = this.speller;
        } while ((n4 & 0x1) != 0x0);
        final int n6 = check;
        final SpellingSession speller3 = this.speller;
        if ((n6 & 0x8) != 0x0) {
            this.setVisible(false);
            this.dispose();
            this.completed = true;
            return false;
        }
        this.parser.highlightWord();
        System.out.println("cursor position :" + this.parser.getCursor());
        this.highlight(this.parser.getCursor());
        final int n7 = check;
        final SpellingSession speller4 = this.speller;
        if ((n7 & 0x10) != 0x0) {
            this.problemLabel.setText("Not in dictionary:");
            this.problemField.setText(this.parser.getWord());
            this.changeToField.setText(sb.toString());
            this.suggestSearchDepth = this.minSuggestDepth;
            if (this.getSuggestions() > 0) {
                this.changeToField.setText(this.suggestionsList.getItem(0));
            }
            this.updateUI();
        }
        else {
            final int n8 = check;
            final SpellingSession speller5 = this.speller;
            if ((n8 & 0x2) != 0x0) {
                this.problemLabel.setText("Consider changing:");
                this.problemField.setText(this.parser.getWord());
                this.changeToField.setText(sb.toString());
                this.suggestSearchDepth = this.minSuggestDepth;
                this.getSuggestions();
                this.updateUI();
            }
            else {
                final int n9 = check;
                final SpellingSession speller6 = this.speller;
                if ((n9 & 0x80) != 0x0) {
                    this.problemLabel.setText("Capitalization:");
                    this.problemField.setText(this.parser.getWord());
                    this.changeToField.setText(sb.toString());
                    this.updateUI();
                }
                else {
                    final int n10 = check;
                    final SpellingSession speller7 = this.speller;
                    if ((n10 & 0x4) != 0x0) {
                        this.problemLabel.setText("Doubled word:");
                        this.problemField.setText(this.parser.getWord());
                        this.changeToField.setText("");
                        this.doubledWord = true;
                        this.updateUI();
                    }
                    else {
                        final int n11 = check;
                        final SpellingSession speller8 = this.speller;
                        if ((n11 & 0x20) != 0x0) {
                            this.problemLabel.setText("Mixed case:");
                            this.problemField.setText(this.parser.getWord());
                            this.changeToField.setText(sb.toString());
                            this.updateUI();
                        }
                        else {
                            final int n12 = check;
                            final SpellingSession speller9 = this.speller;
                            if ((n12 & 0x40) != 0x0) {
                                this.problemLabel.setText("Contains digits:");
                                this.problemField.setText(this.parser.getWord());
                                this.changeToField.setText(sb.toString());
                                this.updateUI();
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    
    protected void updateUI() {
        if (!this.problemField.getText().equals(this.suggestWord)) {
            this.suggestSearchDepth = this.minSuggestDepth;
        }
        this.changeToField.setEnabled(!this.doubledWord);
        this.ignoreButton.setEnabled(true);
        this.ignoreAllButton.setEnabled(!this.doubledWord && this.getTempLex() != null);
        if (this.doubledWord) {
            this.changeButton.setLabel("Delete");
        }
        else {
            this.changeButton.setLabel("Change");
        }
        this.changeButton.setEnabled(true);
        this.changeAllButton.setEnabled(!this.doubledWord && this.getTempLex() != null);
        final Button suggestButton = this.suggestButton;
        boolean enabled = false;
        Label_0183: {
            if (!this.doubledWord) {
                final int suggestSearchDepth = this.suggestSearchDepth;
                final SpellingSession speller = this.speller;
                if (suggestSearchDepth < 100 && this.problemField.getText().length() > 0) {
                    enabled = true;
                    break Label_0183;
                }
            }
            enabled = false;
        }
        suggestButton.setEnabled(enabled);
        this.cancelButton.setEnabled(true);
        this.undoButton.setEnabled(!this.undoStack.empty());
        this.pack();
        this.setBounds(450, 100, 461, 485);
    }
    
    public void addNotify() {
        final Dimension size = this.getSize();
        super.addNotify();
        if (this.fComponentsAdjusted) {
            return;
        }
        final Insets insets = this.getInsets();
        this.setSize(insets.left + insets.right + size.width, insets.top + insets.bottom + size.height);
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Point location = components[i].getLocation();
            location.translate(insets.left, insets.top);
            components[i].setLocation(location);
        }
        this.fComponentsAdjusted = true;
    }
    
    public int getNumWordsChanged() {
        return this.parser.getNumReplacements();
    }
    
    public int getNumWordsChecked() {
        return this.parser.getNumWords();
    }
    
    public String getText() {
        if (this.contextArea != null) {
            return this.contextArea.getText();
        }
        return null;
    }
    
    public boolean userCanceled() {
        return this.canceled;
    }
    
    protected void busy(final boolean b) {
        if (b) {
            this.setCursor(Cursor.getPredefinedCursor(3));
        }
        else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
    
    protected void changeWord() {
        if (this.debug) {
            System.out.println("replacing " + this.parser.getWord() + " with " + this.changeToField.getText());
        }
        this.parser.replaceWord(this.changeToField.getText());
        this.contextArea.setText(this.parser.toString());
        final UndoRecord undoRecord = new UndoRecord();
        undoRecord.offset = this.parser.getCursor();
        undoRecord.origText = this.problemField.getText();
        undoRecord.newText = this.changeToField.getText();
        this.undoStack.push(undoRecord);
    }
    
    protected void deleteWord() {
        if (this.debug) {
            System.out.println("deleting " + this.parser.getWord());
        }
        final StringBuffer sb = new StringBuffer();
        final int deleteWord = this.parser.deleteWord(sb);
        this.contextArea.setText(this.parser.toString());
        final UndoRecord undoRecord = new UndoRecord();
        undoRecord.offset = deleteWord;
        undoRecord.origText = sb.toString();
        undoRecord.newText = "";
        this.undoStack.push(undoRecord);
    }
    
    protected FileTextLexicon findLex(final String s) {
        final Lexicon[] lexicons = this.speller.getLexicons();
        if (lexicons == null) {
            return null;
        }
        for (int i = 0; i < lexicons.length; ++i) {
            if (lexicons[i] instanceof FileTextLexicon) {
                final FileTextLexicon fileTextLexicon = (FileTextLexicon)lexicons[i];
                if (fileTextLexicon.getFileName().equals(s)) {
                    return fileTextLexicon;
                }
            }
        }
        return null;
    }
    
    protected int getSuggestions() {
        final SuggestionSet set = new SuggestionSet(16);
        this.busy(true);
        this.speller.suggest(this.problemField.getText(), this.suggestSearchDepth, this.comparator, set);
        this.suggestionsList.removeAll();
        int i;
        for (i = 0; i < set.size(); ++i) {
            this.suggestionsList.addItem(set.wordAt(i));
        }
        this.busy(false);
        return i;
    }
    
    protected MemTextLexicon getTempLex() {
        final Lexicon[] lexicons = this.speller.getLexicons();
        if (lexicons == null) {
            return null;
        }
        for (int i = 0; i < lexicons.length; ++i) {
            if (lexicons[i] instanceof MemTextLexicon) {
                return (MemTextLexicon)lexicons[i];
            }
        }
        return null;
    }
    
    public void highlight(int n) {
        final String text = this.contextArea.getText();
        int n2 = 0;
        System.out.println("Positions (before) : " + n2 + "  pos " + n + " length" + text.length());
        if (n > text.length()) {
            n = text.length() - 1;
        }
        System.out.println("Positions (after) : " + n2 + "  pos " + n);
        System.out.println("Positions (after) : " + n2 + "  pos " + n);
        if (n2 < 0) {
            n2 = 0;
        }
        this.contextArea.select(this.parser.getCursor() - n2, this.parser.getCursor() - n2 + this.parser.getWord().length());
        System.out.println("Repainting 2 : getSelectedText :" + this.contextArea.getSelectedText());
        this.contextArea.repaint();
    }
    
    public void display_text() {
        final String text = this.contextArea.getText();
        for (int i = 0; i <= text.length(); ++i) {
            System.out.println((int)text.charAt(i) + "   " + text.charAt(i));
        }
    }
    
    public void undo() {
        final UndoRecord undoRecord = this.undoStack.pop();
        this.parser.setCursor(undoRecord.offset);
        if (undoRecord.newText.length() > 0) {
            this.parser.deleteText(undoRecord.newText.length());
        }
        if (undoRecord.origText.length() > 0) {
            this.parser.insertText(undoRecord.offset, undoRecord.origText);
        }
        this.contextArea.setText(this.parser.toString());
        final MemTextLexicon tempLex = this.getTempLex();
        if (tempLex != null && tempLex.findWord(undoRecord.origText, true, new StringBuffer()) == 65) {
            try {
                tempLex.deleteWord(undoRecord.origText);
            }
            catch (Exception ex) {}
        }
    }
    
    void SpellingDialog_WindowClosing(final WindowEvent windowEvent) {
        this.setVisible(false);
    }
    
    void ignoreButton_ActionPerformed(final ActionEvent actionEvent) {
        this.parser.nextWord();
        this.runChecker();
    }
    
    void ignoreAllButton_ActionPerformed(final ActionEvent actionEvent) {
        final MemTextLexicon tempLex = this.getTempLex();
        String s = this.problemField.getText();
        final SpellingSession speller = this.speller;
        final SpellingSession speller2 = this.speller;
        if (speller.getOption(16384)) {
            final SpellingSession speller3 = this.speller;
            s = SpellingSession.stripPossessives(s);
        }
        try {
            tempLex.addWord(s);
        }
        catch (Exception ex) {}
        this.runChecker();
    }
    
    void changeButton_ActionPerformed(final ActionEvent actionEvent) {
        if (this.doubledWord) {
            this.deleteWord();
        }
        else {
            this.changeWord();
        }
        this.runChecker();
    }
    
    void changeAllButton_ActionPerformed(final ActionEvent actionEvent) {
        if (!this.problemField.getText().equals(this.changeToField.getText())) {
            final MemTextLexicon tempLex = this.getTempLex();
            if (tempLex != null) {
                String s = this.problemField.getText();
                String s2 = this.changeToField.getText();
                final SpellingSession speller = this.speller;
                final SpellingSession speller2 = this.speller;
                if (speller.getOption(16384)) {
                    final SpellingSession speller3 = this.speller;
                    s = SpellingSession.stripPossessives(s);
                    final SpellingSession speller4 = this.speller;
                    s2 = SpellingSession.stripPossessives(s2);
                }
                try {
                    tempLex.addWord(s, 65, s2);
                }
                catch (Exception ex) {
                    MessageBox.createMessageBox(this.getTitle(), "Error saving word: " + ex);
                }
            }
        }
        this.runChecker();
    }
    
    void suggestButton_ActionPerformed(final ActionEvent actionEvent) {
        this.suggestWord = this.problemField.getText();
        if (this.getSuggestions() > 0) {
            this.changeToField.setText(this.suggestionsList.getItem(0));
        }
        this.suggestSearchDepth += 10;
        this.updateUI();
    }
    
    void suggestionsList_ActionPerformed(final ActionEvent actionEvent) {
        this.changeWord();
        this.runChecker();
    }
    
    void undoButton_ActionPerformed(final ActionEvent actionEvent) {
        this.undo();
        this.runChecker();
    }
    
    void cancelButton_ActionPerformed(final ActionEvent actionEvent) {
        this.canceled = true;
        this.completed = true;
        this.setVisible(false);
        this.dispose();
    }
    
    void suggestionsList_ItemStateChanged(final ItemEvent itemEvent) {
        final int selectedIndex = this.suggestionsList.getSelectedIndex();
        if (selectedIndex >= 0) {
            this.changeToField.setText(this.suggestionsList.getItem(selectedIndex));
        }
    }
    
    class SymItem implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (itemEvent.getSource() == SpellingDialog.this.suggestionsList) {
                SpellingDialog.this.suggestionsList_ItemStateChanged(itemEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == SpellingDialog.this.ignoreButton) {
                SpellingDialog.this.ignoreButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialog.this.ignoreAllButton) {
                SpellingDialog.this.ignoreAllButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialog.this.changeButton) {
                SpellingDialog.this.changeButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialog.this.changeAllButton) {
                SpellingDialog.this.changeAllButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialog.this.suggestButton) {
                SpellingDialog.this.suggestButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialog.this.suggestionsList) {
                SpellingDialog.this.suggestionsList_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialog.this.undoButton) {
                SpellingDialog.this.undoButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialog.this.cancelButton) {
                SpellingDialog.this.cancelButton_ActionPerformed(actionEvent);
            }
        }
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == SpellingDialog.this) {
                SpellingDialog.this.SpellingDialog_WindowClosing(windowEvent);
            }
        }
    }
}
