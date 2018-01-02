import java.awt.event.WindowAdapter;
import com.wintertree.ssce.PropSpellingSession;
import java.net.URL;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;
import java.awt.event.ItemEvent;
import com.wintertree.util.MessageBox;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import com.wintertree.ssce.MemTextLexicon;
import com.wintertree.ssce.SuggestionSet;
import com.wintertree.ssce.Lexicon;
import java.awt.Cursor;
import com.wintertree.ssce.TypographicalComparator;
import com.wintertree.ssce.FileTextLexicon;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JFrame;
import java.util.Stack;
import com.wintertree.ssce.SpellingSession;
import com.wintertree.ssce.WordParser;
import com.wintertree.ssce.WordComparator;
import java.awt.List;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpellingDialogSwing extends JDialog
{
    public boolean debug;
    public boolean completed;
    boolean fComponentsAdjusted;
    JButton cancelButton;
    JButton changeButton;
    JButton changeAllButton;
    JTextField changeToField;
    JLabel label1;
    JTextArea contextArea;
    JButton ignoreButton;
    JButton ignoreAllButton;
    JTextField problemField;
    JLabel problemLabel;
    JButton suggestButton;
    JButton undoButton;
    List suggestionsList;
    JLabel label2;
    JLabel label3;
    public int minSuggestDepth;
    protected boolean canceled;
    protected WordComparator comparator;
    protected WordParser parser;
    protected SpellingSession speller;
    private boolean doubledWord;
    private int suggestSearchDepth;
    private String suggestWord;
    private Stack undoStack;
    
    protected SpellingDialogSwing(final JFrame frame, final boolean b) {
        super(frame, "Check spelling", b);
        this.debug = true;
        this.completed = false;
        this.fComponentsAdjusted = false;
        this.cancelButton = new JButton();
        this.changeButton = new JButton();
        this.changeAllButton = new JButton();
        this.changeToField = new JTextField(30);
        this.label1 = new JLabel();
        this.contextArea = new JTextArea("", 0, 0);
        this.ignoreButton = new JButton();
        this.ignoreAllButton = new JButton();
        this.problemField = new JTextField(30);
        this.problemLabel = new JLabel();
        this.suggestButton = new JButton();
        this.undoButton = new JButton();
        this.suggestionsList = new List(7);
        this.label2 = new JLabel();
        this.label3 = new JLabel();
        frame.setSize(521, 327);
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(521, 327);
        this.setVisible(false);
        this.ignoreButton.setText("Ignore");
        this.ignoreButton.setBackground(Color.lightGray);
        this.ignoreAllButton.setText("Ignore all");
        this.ignoreAllButton.setBackground(Color.lightGray);
        this.changeButton.setText("Change");
        this.changeButton.setBackground(Color.lightGray);
        this.changeAllButton.setText("Change all");
        this.changeAllButton.setBackground(Color.lightGray);
        this.suggestButton.setText("Suggest");
        this.suggestButton.setBackground(Color.lightGray);
        this.undoButton.setText("Undo");
        this.undoButton.setBackground(Color.lightGray);
        this.cancelButton.setText("Cancel");
        this.cancelButton.setBackground(Color.lightGray);
        final JPanel panel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        panel.setLayout(layout);
        this.addButtonRows(new JButton[] { this.ignoreButton, this.ignoreAllButton, this.changeButton, this.changeAllButton, this.suggestButton, this.undoButton, this.cancelButton }, layout, panel);
        this.problemLabel.setText("Not in dictionary2:");
        this.label1.setText("Change to:");
        this.label2.setText("Suggestions:");
        final JLabel[] array = { this.problemLabel, this.label1 };
        final JTextField[] array2 = { this.problemField, this.changeToField };
        final JPanel panel2 = new JPanel();
        final GridBagLayout layout2 = new GridBagLayout();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        panel2.setLayout(layout2);
        this.addLabelTextRows(array, array2, layout2, panel2);
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.weightx = 1.0;
        panel2.add(this.label2, gridBagConstraints2);
        gridBagConstraints2.gridwidth = 0;
        gridBagConstraints2.fill = 2;
        gridBagConstraints2.weightx = 1.0;
        panel2.add(this.suggestionsList, gridBagConstraints2);
        final JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(250, 200));
        panel3.add(panel2, "East");
        panel3.add(panel, "West");
        this.contextArea.setEditable(false);
        this.contextArea.setLineWrap(true);
        final JScrollPane scrollPane = new JScrollPane(this.contextArea);
        scrollPane.setVerticalScrollBarPolicy(22);
        scrollPane.setPreferredSize(new Dimension(250, 250));
        scrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(""), BorderFactory.createEmptyBorder(5, 5, 5, 5)), scrollPane.getBorder()));
        this.getContentPane().add(panel3, "North");
        this.getContentPane().add(scrollPane, "Center");
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
    
    public SpellingDialogSwing(final JFrame frame, final SpellingSession speller, final JTextArea textArea, final WordParser parser, WordComparator comparator, final FileTextLexicon[] array) {
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
    
    private void addButtonRows(final JButton[] array, final GridBagLayout gridBagLayout, final Container container) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 13;
        for (int length = array.length, i = 0; i < length; ++i) {
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            container.add(array[i], gridBagConstraints);
        }
    }
    
    private void addLabelTextRows(final JLabel[] array, final JTextField[] array2, final GridBagLayout gridBagLayout, final Container container) {
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = 13;
        for (int length = array.length, i = 0; i < length; ++i) {
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            container.add(array[i], gridBagConstraints);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.weightx = 1.0;
            container.add(array2[i], gridBagConstraints);
        }
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
        this.changeButton.setText("Change");
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
            this.changeButton.setText("Delete");
        }
        else {
            this.changeButton.setText("Change");
        }
        this.changeButton.setEnabled(true);
        this.changeAllButton.setEnabled(!this.doubledWord && this.getTempLex() != null);
        final JButton suggestButton = this.suggestButton;
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
        this.setBounds(450, 100, 461, 485);
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
        final UndoRecordSwing undoRecordSwing = new UndoRecordSwing();
        undoRecordSwing.offset = this.parser.getCursor();
        undoRecordSwing.origText = this.problemField.getText();
        undoRecordSwing.newText = this.changeToField.getText();
        this.undoStack.push(undoRecordSwing);
    }
    
    protected void deleteWord() {
        if (this.debug) {
            System.out.println("deleting " + this.parser.getWord());
        }
        final StringBuffer sb = new StringBuffer();
        final int deleteWord = this.parser.deleteWord(sb);
        this.contextArea.setText(this.parser.toString());
        final UndoRecordSwing undoRecordSwing = new UndoRecordSwing();
        undoRecordSwing.offset = deleteWord;
        undoRecordSwing.origText = sb.toString();
        undoRecordSwing.newText = "";
        this.undoStack.push(undoRecordSwing);
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
            this.suggestionsList.add(set.wordAt(i));
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
    
    public void highlight(int caretPosition) {
        final String text = this.contextArea.getText();
        final int n = 0;
        System.out.println("Positions (before) : " + n + "  pos " + caretPosition + " length" + text.length());
        if (caretPosition > text.length()) {
            caretPosition = text.length() - 1;
        }
        System.out.println("Positions (after) : " + n + "  pos " + caretPosition);
        System.out.println("Positions (after) : " + n + "  pos " + caretPosition);
        if (n < 0) {}
        this.removeHighlights(this.contextArea);
        try {
            final Highlighter highlighter = this.contextArea.getHighlighter();
            final Document document = this.contextArea.getDocument();
            document.getText(0, document.getLength());
            highlighter.addHighlight(caretPosition, caretPosition + this.parser.getWord().length(), new MyHighlightPainter(Color.LIGHT_GRAY));
            this.contextArea.setCaretPosition(caretPosition);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void removeHighlights(final JTextComponent textComponent) {
        final Highlighter highlighter = textComponent.getHighlighter();
        final Highlighter.Highlight[] highlights = highlighter.getHighlights();
        for (int i = 0; i < highlights.length; ++i) {
            if (highlights[i].getPainter() instanceof MyHighlightPainter) {
                highlighter.removeHighlight(highlights[i]);
            }
        }
    }
    
    public void display_text() {
        final String text = this.contextArea.getText();
        for (int i = 0; i <= text.length(); ++i) {
            System.out.println((int)text.charAt(i) + "   " + text.charAt(i));
        }
    }
    
    public void undo() {
        final UndoRecordSwing undoRecordSwing = this.undoStack.pop();
        this.parser.setCursor(undoRecordSwing.offset);
        if (undoRecordSwing.newText.length() > 0) {
            this.parser.deleteText(undoRecordSwing.newText.length());
        }
        if (undoRecordSwing.origText.length() > 0) {
            this.parser.insertText(undoRecordSwing.offset, undoRecordSwing.origText);
        }
        this.contextArea.setText(this.parser.toString());
        final MemTextLexicon tempLex = this.getTempLex();
        if (tempLex != null && tempLex.findWord(undoRecordSwing.origText, true, new StringBuffer()) == 65) {
            try {
                tempLex.deleteWord(undoRecordSwing.origText);
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
    
    public static void main(final String[] array) {
        try {
            final FileInputStream fileInputStream = new FileInputStream("C:\\CVS_Projects\\SpellChecker\\www\\ttemi.properties");
            final Properties properties = new Properties();
            properties.load(fileInputStream);
            final PropSpellingSession propSpellingSession = new PropSpellingSession(properties, new URL("file:\\C:\\CVS_Projects\\SpellChecker\\www\\"));
            propSpellingSession.getLexicons();
            final JTextArea textArea = new JTextArea("Tetra Tech EM Inc. (Tetra Tech) provided support through the Resource Efficiency Manager (REM) program to Elmendorf Air Force Base (AFB).  Mr. Francis Sheridan is the Tetra Tech REM assigned to Elmendorf AFB.");
            final SpellingDialogSwing spellingDialogSwing = new SpellingDialogSwing(new JFrame(), (SpellingSession)propSpellingSession, textArea, (WordParser)new TextAreaWordParser(textArea, propSpellingSession.getOption(4096), false), propSpellingSession.comparator, propSpellingSession.userLexicons);
            spellingDialogSwing.minSuggestDepth = propSpellingSession.minSuggestDepth;
            spellingDialogSwing.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    class SymItem implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (itemEvent.getSource() == SpellingDialogSwing.this.suggestionsList) {
                SpellingDialogSwing.this.suggestionsList_ItemStateChanged(itemEvent);
            }
        }
    }
    
    class SymAction implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            final Object source = actionEvent.getSource();
            if (source == SpellingDialogSwing.this.ignoreButton) {
                SpellingDialogSwing.this.ignoreButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialogSwing.this.ignoreAllButton) {
                SpellingDialogSwing.this.ignoreAllButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialogSwing.this.changeButton) {
                SpellingDialogSwing.this.changeButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialogSwing.this.changeAllButton) {
                SpellingDialogSwing.this.changeAllButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialogSwing.this.suggestButton) {
                SpellingDialogSwing.this.suggestButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialogSwing.this.suggestionsList) {
                SpellingDialogSwing.this.suggestionsList_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialogSwing.this.undoButton) {
                SpellingDialogSwing.this.undoButton_ActionPerformed(actionEvent);
            }
            else if (source == SpellingDialogSwing.this.cancelButton) {
                SpellingDialogSwing.this.cancelButton_ActionPerformed(actionEvent);
            }
        }
    }
    
    class SymWindow extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (windowEvent.getSource() == SpellingDialogSwing.this) {
                SpellingDialogSwing.this.SpellingDialog_WindowClosing(windowEvent);
            }
        }
    }
}
