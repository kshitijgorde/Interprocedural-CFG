// 
// Decompiled by Procyon v0.5.30
// 

package abc.ui.swing;

import scanner.Token;
import abc.parser.InvalidTokenEvent;
import scanner.InvalidCharacterEvent;
import abc.parser.AbcTokenType;
import java.util.EventObject;
import scanner.TokenEvent;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import scanner.TokenType;
import java.util.Vector;
import abc.parser.TuneParserListenerInterface;
import javax.swing.event.DocumentListener;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Dimension;
import scanner.CharStreamPosition;
import scanner.PositionableInCharStream;
import javax.swing.text.AttributeSet;
import java.awt.event.ActionListener;
import javax.swing.KeyStroke;
import javax.swing.text.DefaultStyledDocument;
import java.awt.Font;
import abc.parser.TuneParser;
import javax.swing.text.EditorKit;
import abc.notation.Tune;
import javax.swing.text.Style;
import java.awt.Color;
import javax.swing.JTextPane;

public class TuneEditorPane extends JTextPane
{
    private static Color BACKGROUND_COLOR;
    private static Color FIELDS_COLOR;
    private static Color GRACING_FOREGROUND_COLOR;
    private static Color GRACING_BACKGROUND_COLOR;
    private static Color TUPLET_FOREGROUND_COLOR;
    private static Color SELECTION_FOREGROUND_COLOR;
    private static Color SELECTION_BACKGROUND_COLOR;
    private static Color BAR_COLOR;
    private static final String COPY_ACTION = "Copy";
    private static final String PASTE_ACTION = "Paste";
    private static final String ERROR_STYLE = "error";
    private static final String BARS_STYLE = "bars";
    private static final String TEXT_STYLE = "text";
    private static final String COMMENT_STYLE = "comment";
    private static final String NOTE_STYLE = "note";
    private static final String GRACING_STYLE = "note";
    private static final String FIELD_STYLE = "field";
    private static final String RHYTHM_STYLE = "rhythm";
    private static final String DEFAULT_STYLE = "rhythm";
    public static final String REFRESHER_THREAD_NAME = "ABC-TunePaneRefresh";
    private boolean m_forceRefresh;
    private Style m_barStyle;
    private Style m_textStyle;
    private Style m_errorStyle;
    private Style m_fieldStyle;
    private Style m_rhythmStyle;
    private Style m_defaultStyle;
    private Style m_baseNoteStyle;
    private Style m_commentStyle;
    private Style m_gracingStyle;
    private static final int IDLE_TIME_BEFORE_REFRESH = 200;
    private ParsingRefresh m_refresher;
    private Tune m_tune;
    private int m_idleTimeBeforeRefresh;
    private boolean m_enableColoring;
    private EditorKit m_editorKit;
    
    public TuneEditorPane() {
        this(new TuneParser());
    }
    
    public TuneEditorPane(final TuneParser parser) {
        this(parser, 200);
    }
    
    public TuneEditorPane(final int idleTimeBeforeRefresh) {
        this(new TuneParser(), idleTimeBeforeRefresh);
    }
    
    public TuneEditorPane(final TuneParser parser, final int idleTimeBeforeRefresh) {
        this.m_forceRefresh = false;
        this.m_gracingStyle = null;
        this.m_refresher = null;
        this.m_tune = null;
        this.m_idleTimeBeforeRefresh = 200;
        this.m_enableColoring = false;
        this.m_editorKit = null;
        this.setBackground(TuneEditorPane.BACKGROUND_COLOR);
        this.setSelectedTextColor(TuneEditorPane.SELECTION_FOREGROUND_COLOR);
        this.setSelectionColor(TuneEditorPane.SELECTION_BACKGROUND_COLOR);
        this.setFont(new Font("Courier", 0, 12));
        this.m_refresher = new ParsingRefresh((DefaultStyledDocument)this.getDocument(), parser);
        final KeyStroke copy = KeyStroke.getKeyStroke(67, 2, false);
        final KeyStroke paste = KeyStroke.getKeyStroke(86, 2, false);
        final MyActionListener myAL = new MyActionListener();
        this.registerKeyboardAction(myAL, "Copy", copy, 0);
        this.registerKeyboardAction(myAL, "Paste", paste, 0);
        this.createStyles();
        this.setCharacterAttributes(this.m_defaultStyle, true);
    }
    
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }
    
    public TuneParser getParser() {
        return this.m_refresher.getParser();
    }
    
    public boolean isColoringEnabled() {
        return this.m_enableColoring;
    }
    
    public void setColoringEnable(final boolean coloring) {
        this.m_enableColoring = coloring;
        if (this.m_enableColoring) {
            this.m_refresher.redrawTune();
        }
        else {
            ((DefaultStyledDocument)this.getDocument()).setCharacterAttributes(0, this.getDocument().getEndPosition().getOffset(), this.m_defaultStyle, true);
        }
    }
    
    public void setSelectedItem(final PositionableInCharStream elmnt) {
        final CharStreamPosition pos = elmnt.getPosition();
        final int begin = pos.getCharactersOffset();
        final int end = begin + elmnt.getLength();
        try {
            this.setCaretPosition(end);
            this.moveCaretPosition(begin);
            this.getCaret().setSelectionVisible(true);
            this.repaint();
        }
        catch (IllegalArgumentException ex) {}
    }
    
    public void setSize(final Dimension d) {
        if (d.width < this.getParent().getSize().width) {
            d.width = this.getParent().getSize().width;
        }
        super.setSize(d);
    }
    
    public Tune getTune() {
        return this.m_tune;
    }
    
    public void setText(final String text) {
        try {
            this.m_refresher.stopIt();
            this.getDocument().remove(0, this.getDocument().getLength() - 1);
            this.getDocument().insertString(0, text, this.m_defaultStyle);
            this.m_refresher.startIt();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createStyles() {
        StyleConstants.setFontFamily(this.m_defaultStyle = this.addStyle("rhythm", null), "Courier");
        StyleConstants.setBackground(this.m_errorStyle = this.addStyle("error", this.m_defaultStyle), Color.red);
        StyleConstants.setForeground(this.m_errorStyle, Color.yellow);
        StyleConstants.setBold(this.m_errorStyle, true);
        StyleConstants.setForeground(this.m_baseNoteStyle = this.addStyle("note", this.m_defaultStyle), Color.red);
        StyleConstants.setBackground(this.m_gracingStyle = this.addStyle("note", this.m_defaultStyle), TuneEditorPane.GRACING_BACKGROUND_COLOR);
        StyleConstants.setForeground(this.m_gracingStyle, TuneEditorPane.GRACING_FOREGROUND_COLOR);
        StyleConstants.setForeground(this.m_textStyle = this.addStyle("text", this.m_defaultStyle), Color.blue);
        StyleConstants.setForeground(this.m_commentStyle = this.addStyle("comment", this.m_defaultStyle), Color.white);
        StyleConstants.setBackground(this.m_commentStyle, Color.gray);
        StyleConstants.setForeground(this.m_barStyle = this.addStyle("bars", this.m_defaultStyle), TuneEditorPane.BAR_COLOR);
        StyleConstants.setBold(this.m_barStyle, true);
        StyleConstants.setForeground(this.m_fieldStyle = this.addStyle("field", this.m_defaultStyle), TuneEditorPane.FIELDS_COLOR);
        StyleConstants.setBold(this.m_fieldStyle, true);
        StyleConstants.setBold(this.m_fieldStyle, true);
        StyleConstants.setForeground(this.m_rhythmStyle = this.addStyle("rhythm", this.m_defaultStyle), TuneEditorPane.TUPLET_FOREGROUND_COLOR);
        StyleConstants.setBold(this.m_rhythmStyle, true);
    }
    
    static {
        TuneEditorPane.BACKGROUND_COLOR = new Color(249, 234, 202);
        TuneEditorPane.FIELDS_COLOR = new Color(0, 128, 0);
        TuneEditorPane.GRACING_FOREGROUND_COLOR = Color.green;
        TuneEditorPane.GRACING_BACKGROUND_COLOR = TuneEditorPane.FIELDS_COLOR;
        TuneEditorPane.TUPLET_FOREGROUND_COLOR = new Color(128, 0, 255);
        TuneEditorPane.SELECTION_FOREGROUND_COLOR = Color.white;
        TuneEditorPane.SELECTION_BACKGROUND_COLOR = new Color(10, 36, 106);
        TuneEditorPane.BAR_COLOR = new Color(0, 0, 128);
    }
    
    private class MyActionListener implements ActionListener
    {
        public void actionPerformed(final ActionEvent e) {
            if (e.getActionCommand().compareTo("Copy") == 0) {
                final StringBuffer sbf = new StringBuffer();
                final StringSelection stsel = new StringSelection(TuneEditorPane.this.getSelectedText());
                final Clipboard system = Toolkit.getDefaultToolkit().getSystemClipboard();
                system.setContents(stsel, stsel);
            }
            if (e.getActionCommand().compareTo("Paste") == 0) {
                final Clipboard system2 = Toolkit.getDefaultToolkit().getSystemClipboard();
                try {
                    final String selectedText = TuneEditorPane.this.getSelectedText();
                    final String trstring = (String)system2.getContents(this).getTransferData(DataFlavor.stringFlavor);
                    if (selectedText != null) {
                        TuneEditorPane.this.getDocument().remove(TuneEditorPane.this.getCaretPosition(), selectedText.length());
                    }
                    TuneEditorPane.this.getDocument().insertString(TuneEditorPane.this.getCaretPosition(), trstring, TuneEditorPane.this.m_defaultStyle);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    private class ParsingRefresh extends Thread implements DocumentListener, TuneParserListenerInterface
    {
        private int m_elapsedTimeSinceLastParsing;
        private DefaultStyledDocument m_document;
        private TuneParser m_parser;
        private int m_idleTime;
        private Object m_mutex;
        private Vector m_parsingEvents;
        private TokenType m_contextForText;
        private TokenType m_contextForNote;
        
        public ParsingRefresh(final DefaultStyledDocument document, final TuneParser parser) {
            super("ABC-TunePaneRefresh");
            this.m_elapsedTimeSinceLastParsing = 0;
            this.m_document = null;
            this.m_parser = null;
            this.m_idleTime = 0;
            this.m_mutex = new Object();
            this.m_parsingEvents = null;
            this.m_contextForText = null;
            this.m_contextForNote = null;
            this.start();
            this.m_parser = parser;
            this.m_parsingEvents = new Vector();
            this.m_parser.addListener(this);
            this.m_document = document;
            this.startIt();
        }
        
        public void startIt() {
            this.m_document.addDocumentListener(this);
            TuneEditorPane.this.m_forceRefresh = true;
            synchronized (this.m_mutex) {
                this.m_mutex.notify();
            }
        }
        
        public void stopIt() {
            this.m_document.removeDocumentListener(this);
        }
        
        public TuneParser getParser() {
            return this.m_parser;
        }
        
        public void setDocument(final DefaultStyledDocument doc) {
            try {
                System.out.println(this.getClass().getName() + " - setDocument(" + doc + ")");
                this.m_document.removeDocumentListener(this);
                this.m_document = doc;
                TuneEditorPane.this.m_tune = this.m_parser.parse(doc.getText(0, doc.getLength()));
                this.m_document.addDocumentListener(this);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        public void run() {
            while (true) {
                try {
                    while (true) {
                        synchronized (this.m_mutex) {
                            this.m_mutex.wait();
                            if (!TuneEditorPane.this.m_forceRefresh) {
                                do {
                                    this.m_mutex.wait(10L);
                                    this.m_idleTime += 10;
                                } while (this.m_idleTime <= TuneEditorPane.this.m_idleTimeBeforeRefresh);
                            }
                            try {
                                final String tuneNotation = TuneEditorPane.this.getDocument().getText(0, TuneEditorPane.this.getDocument().getLength());
                                if (tuneNotation.equals("")) {
                                    continue;
                                }
                                if (TuneEditorPane.this.m_forceRefresh) {
                                    TuneEditorPane.this.m_forceRefresh = false;
                                }
                                TuneEditorPane.this.m_tune = this.m_parser.parse(tuneNotation);
                            }
                            catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                catch (InterruptedException e2) {
                    e2.printStackTrace();
                    continue;
                }
                break;
            }
        }
        
        public void changedUpdate(final DocumentEvent e) {
        }
        
        public void insertUpdate(final DocumentEvent e) {
            synchronized (this.m_mutex) {
                this.m_mutex.notify();
                this.m_idleTime = 0;
            }
        }
        
        public void removeUpdate(final DocumentEvent e) {
            synchronized (this.m_mutex) {
                this.m_mutex.notify();
                this.m_idleTime = 0;
            }
        }
        
        public void tuneBegin() {
            this.m_parsingEvents.removeAllElements();
        }
        
        public void tuneEnd(final Tune tune) {
            if (TuneEditorPane.this.m_enableColoring) {
                try {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            ParsingRefresh.this.redrawTune();
                        }
                    });
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        public void validToken(final TokenEvent evt) {
            this.m_parsingEvents.addElement(evt);
        }
        
        private void redrawTune() {
            EventObject event = null;
            for (int i = 0; i < this.m_parsingEvents.size(); ++i) {
                event = this.m_parsingEvents.elementAt(i);
                if (event instanceof TokenEvent) {
                    final Token token = ((TokenEvent)event).getToken();
                    if (token != null) {
                        try {
                            Style att = TuneEditorPane.this.m_defaultStyle;
                            final AbcTokenType tokenType = (AbcTokenType)token.getType();
                            if (tokenType.isField()) {
                                att = TuneEditorPane.this.m_fieldStyle;
                            }
                            else if (tokenType.equals(AbcTokenType.TUPLET_SPEC) || tokenType.equals(AbcTokenType.BROKEN_RHYTHM)) {
                                att = TuneEditorPane.this.m_rhythmStyle;
                            }
                            else if (tokenType.equals(AbcTokenType.BARLINE) || tokenType.equals(AbcTokenType.REPEAT_OPEN) || tokenType.equals(AbcTokenType.REPEAT_CLOSE) || tokenType.equals(AbcTokenType.NTH_REPEAT)) {
                                att = TuneEditorPane.this.m_barStyle;
                            }
                            else if (token.getType().equals(AbcTokenType.COMMENT)) {
                                att = TuneEditorPane.this.m_commentStyle;
                            }
                            else if (token.getType().equals(AbcTokenType.TEXT)) {
                                if (this.m_contextForText.equals(AbcTokenType.COMMENT)) {
                                    att = TuneEditorPane.this.m_commentStyle;
                                }
                                else {
                                    att = TuneEditorPane.this.m_textStyle;
                                }
                            }
                            else if (token.getType().equals(AbcTokenType.BASE_NOTE)) {
                                if (AbcTokenType.GRACING_BEGIN.equals(this.m_contextForNote)) {
                                    att = TuneEditorPane.this.m_gracingStyle;
                                }
                                else {
                                    att = TuneEditorPane.this.m_baseNoteStyle;
                                }
                            }
                            else if (token.getType().equals(AbcTokenType.GRACING_BEGIN)) {
                                this.m_contextForNote = AbcTokenType.GRACING_BEGIN;
                                att = TuneEditorPane.this.m_gracingStyle;
                            }
                            else if (token.getType().equals(AbcTokenType.GRACING_END)) {
                                this.m_contextForNote = null;
                                att = TuneEditorPane.this.m_gracingStyle;
                            }
                            this.m_document.setCharacterAttributes(token.getPosition().getCharactersOffset(), token.getValue().length(), att, true);
                            this.m_contextForText = token.getType();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if (event instanceof InvalidCharacterEvent) {
                    final InvalidCharacterEvent evt = (InvalidCharacterEvent)event;
                    this.m_document.setCharacterAttributes(evt.getPosition().getCharactersOffset(), 1, TuneEditorPane.this.m_errorStyle, true);
                }
                else if (event instanceof InvalidTokenEvent) {
                    final InvalidTokenEvent evt2 = (InvalidTokenEvent)event;
                    this.m_document.setCharacterAttributes(evt2.getPosition().getCharactersOffset(), 1, TuneEditorPane.this.m_errorStyle, true);
                }
            }
        }
        
        public void invalidToken(final InvalidTokenEvent evt) {
            this.m_parsingEvents.addElement(evt);
        }
        
        public void invalidCharacter(final InvalidCharacterEvent evt) {
            this.m_parsingEvents.addElement(evt);
        }
    }
}
