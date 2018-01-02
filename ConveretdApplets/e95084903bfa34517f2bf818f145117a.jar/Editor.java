import java.awt.FileDialog;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class Editor extends Container implements KeyListener, Runnable, TextBufferListener
{
    private static final String CLASS_NAME = "Editor";
    private static final char BACKSPACE = '\b';
    private static final char CR = '\r';
    private static final char CTRL_A = '\u0001';
    private static final char CTRL_B = '\u0002';
    private static final char CTRL_C = '\u0003';
    private static final char CTRL_D = '\u0004';
    private static final char CTRL_E = '\u0005';
    private static final char CTRL_F = '\u0006';
    private static final char CTRL_G = '\u0007';
    private static final char CTRL_K = '\u000b';
    private static final char CTRL_L = '\f';
    private static final char CTRL_N = '\u000e';
    private static final char CTRL_O = '\u000f';
    private static final char CTRL_P = '\u0010';
    private static final char CTRL_Q = '\u0011';
    private static final char CTRL_R = '\u0012';
    private static final char CTRL_S = '\u0013';
    private static final char CTRL_U = '\u0015';
    private static final char CTRL_V = '\u0016';
    private static final char CTRL_X = '\u0018';
    private static final char CTRL_Z = '\u001a';
    private static final char EM = '\u0019';
    private static final char HT = '\t';
    private static final char LF = '\n';
    private static final char NULL = '\0';
    private static final char UNIX_ENTER = '\n';
    private static final char WINDOWS_ENTER = '\r';
    private static final Color BG_COLOR;
    private static final Color FG_COLOR;
    private static final Color COMMENT_COLOR;
    private static final Color MATCH_COLOR;
    private static final Color TITLE_END_COLOR;
    private static final int EXTRA_LINES = 2;
    private static final int HEIGHT = 140;
    private static final int MULTIKEY_DELAY = 2000;
    private static final int WIDTH = 640;
    private static final int PROMPT_SAVE = 1;
    private static final int PROMPT_SEARCH = 2;
    private static final String GLOBAL_PATTERN = "^ *[Gg][Ll][Oo][Bb][Aa][Ll] +";
    private static final String MAKE_PATTERN = "^ *[Mm][Aa][Kk][Ee] +";
    private static final String OPEN_FILE = "Open File";
    private static final String PROC_DEF_PATTERN = "^ *[Tt][Oo] +[A-Za-z]";
    private static final String PROC_END_LINE = "  end";
    private static final String PROC_END_PATTERN = "^ *end *$";
    private static final String SAVE_CHANGES = "Save changes to ";
    private static final String SAVE_FILE = "Save to File";
    private static final String SEARCH_FOR = "Search for string: ";
    private boolean doingFwdSearch;
    private boolean interpretAllFailed;
    private boolean promptAborted;
    private boolean textModified;
    private char[] cmdLeadChar;
    private int fontSize;
    private int whichPrompt;
    private long triggerTime;
    private RegExp globVarRegExp;
    private RegExp makeVarRegExp;
    private RegExp procDefRegExp;
    private RegExp procEndRegExp;
    private String curFile;
    private String nxtFile;
    private String lastSearch;
    private TEPromptBar promptBar;
    private Thread timerThread;
    private TextBuffer tb;
    private Vector procsToParse;
    
    public Editor(final int n, final int n2, final int n3) {
        this(null, n, n2, n3);
    }
    
    public Editor(final TextBuffer tb, int n, final int n2, final int fontSize) {
        this.setLayout(null);
        if (n == 0) {
            n = 640;
        }
        this.fontSize = fontSize;
        if (tb == null) {
            this.tb = new TextBuffer(n, n2, fontSize);
        }
        else {
            (this.tb = tb).setSize(n, n2);
        }
        this.add(this.tb);
        this.tb.addKeyListener(this);
        this.tb.addEventHandler(this);
        super.setSize(n, n2);
        (this.cmdLeadChar = new char[2])[0] = '\0';
        this.globVarRegExp = new RegExp("^ *[Gg][Ll][Oo][Bb][Aa][Ll] +");
        this.makeVarRegExp = new RegExp("^ *[Mm][Aa][Kk][Ee] +");
        this.procEndRegExp = new RegExp("^ *end *$");
        this.procDefRegExp = new RegExp("^ *[Tt][Oo] +[A-Za-z]");
        this.textModified = false;
        this.curFile = null;
        this.nxtFile = null;
        this.procsToParse = new Vector(10, 5);
        (this.timerThread = new Thread(this, "TEtimer")).setDaemon(true);
        this.timerThread.start();
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        this.parseProcs();
        this.tb.removeEventHandler(this);
        this.tb.removeKeyListener(this);
        this.remove(this.tb);
        this.timerThread = null;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 127: {
                this.textModified = true;
                this.tb.delCharCmd();
                break;
            }
            case 40: {
                this.tb.cursorDown();
                break;
            }
            case 35: {
                this.tb.gotoEndOfLine();
                break;
            }
            case 27: {
                synchronized (this.cmdLeadChar) {
                    this.triggerTime = System.currentTimeMillis() + 2000L;
                    this.cmdLeadChar[0] = '\u001b';
                    this.cmdLeadChar.notifyAll();
                    break;
                }
            }
            case 36: {
                this.tb.gotoBeginOfLine();
                break;
            }
            case 37: {
                this.tb.cursorLeft();
                break;
            }
            case 34: {
                this.tb.pageDownCmd(true);
                break;
            }
            case 33: {
                this.tb.pageUpCmd(true);
                break;
            }
            case 39: {
                this.tb.cursorRight();
                break;
            }
            case 38: {
                this.tb.cursorUp();
                break;
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    private void multiKeyCommand(final char c) {
        synchronized (this.cmdLeadChar) {
            Label_0306: {
                switch (this.cmdLeadChar[0]) {
                    case '\u0012': {
                        if (c != '\u0012') {
                            this.doPromptKey(c);
                            break;
                        }
                        if (this.promptBar != null) {
                            this.promptAborted = true;
                            this.donePrompt();
                        }
                        if (this.lastSearch == null) {
                            break;
                        }
                        this.tb.cursorLeft();
                        if (!this.findSearchStringBack(this.lastSearch)) {
                            this.tb.cursorRight();
                            Toolkit.getDefaultToolkit().beep();
                            break;
                        }
                        break;
                    }
                    case '\u0013': {
                        if (c != '\u0013') {
                            this.doPromptKey(c);
                            break;
                        }
                        if (this.promptBar != null) {
                            this.promptAborted = true;
                            this.donePrompt();
                        }
                        if (this.lastSearch == null) {
                            break;
                        }
                        this.tb.cursorRight();
                        if (!this.findSearchStringFwd(this.lastSearch)) {
                            this.tb.cursorLeft();
                            Toolkit.getDefaultToolkit().beep();
                            break;
                        }
                        break;
                    }
                    case '\u001b': {
                        switch (c) {
                            case '<': {
                                this.tb.setCurLinNumCmd(0);
                                this.tb.setColNum(0);
                                break Label_0306;
                            }
                            case '>': {
                                final int setCurLinNumCmd = this.tb.setCurLinNumCmd(Integer.MAX_VALUE);
                                this.tb.setColNum(0);
                                int topLineNum = setCurLinNumCmd - this.tb.getNumDispLines() * 2 / 3;
                                if (topLineNum < 0) {
                                    topLineNum = 0;
                                }
                                this.tb.setTopLineNum(topLineNum);
                                break Label_0306;
                            }
                            default: {
                                Toolkit.getDefaultToolkit().beep();
                                break Label_0306;
                            }
                        }
                        break;
                    }
                    default: {
                        Toolkit.getDefaultToolkit().beep();
                        break;
                    }
                }
            }
            this.cmdLeadChar[0] = '\0';
            this.triggerTime = 0L;
        }
    }
    
    private void doPromptKey(final char c) {
        if (c >= ' ' && c <= '~') {
            this.promptBar.addCharCmd(c);
        }
        else if (c == '\b') {
            this.promptBar.delPrevCharCmd();
        }
        else if (c == '\u001b') {
            this.promptAborted = true;
            this.donePrompt();
        }
        else if (c == '\n' || c == '\r') {
            switch (this.whichPrompt) {
                case 1: {
                    final String inputText = this.promptBar.getInputText();
                    if (inputText.charAt(0) == 'y' || inputText.charAt(0) == 'Y') {
                        this.save();
                    }
                    TG.reset();
                    if (this.nxtFile == null) {
                        break;
                    }
                    final char[] file = FileIO.readFile(this.nxtFile);
                    if (file != null) {
                        this.loadFile(file);
                        this.curFile = this.nxtFile;
                        this.nxtFile = null;
                        break;
                    }
                    break;
                }
                case 2: {
                    if (this.doingFwdSearch) {
                        this.doSearchFwd();
                        break;
                    }
                    this.doSearchBack();
                    break;
                }
                default: {
                    System.err.println("Editor.doPromptKey: unknown prompt");
                    break;
                }
            }
            this.donePrompt();
        }
        else {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        if (this.cmdLeadChar[0] != '\0') {
            this.multiKeyCommand(keyChar);
            return;
        }
        if (this.promptBar != null) {
            this.doPromptKey(keyChar);
            return;
        }
        switch (keyChar) {
            case '\b': {
                this.textModified = true;
                this.tb.delPrevCharCmd();
                break;
            }
            case '\u0001': {
                this.tb.gotoBeginOfLine();
                break;
            }
            case '\u0002': {
                this.tb.cursorLeft();
                break;
            }
            case '\u0003': {
                this.tb.copySelection();
                break;
            }
            case '\u0004': {
                this.textModified = true;
                this.tb.delCharCmd();
                break;
            }
            case '\u0005': {
                this.tb.gotoEndOfLine();
                break;
            }
            case '\u0006': {
                this.tb.cursorRight();
                break;
            }
            case '\u000b': {
                this.textModified = true;
                this.tb.delToEOLCmd();
                break;
            }
            case '\u000e': {
                this.tb.cursorDown();
                break;
            }
            case '\u000f': {
                this.textModified = true;
                this.openLine();
                break;
            }
            case '\u0010': {
                this.tb.cursorUp();
                break;
            }
            case '\u0012':
            case '\u0013': {
                synchronized (this.cmdLeadChar) {
                    this.cmdLeadChar[0] = keyChar;
                }
                this.doingFwdSearch = (keyChar == '\u0013');
                this.doPrompt(2, "Search for string: ", this.lastSearch, true);
                break;
            }
            case '\u0016': {
                this.textModified = true;
                this.tb.pasteSelection();
                break;
            }
            case '\u0018': {
                this.textModified = true;
                this.tb.cutSelection();
                break;
            }
            case '\t': {
                this.textModified = true;
                this.tb.addCharCmd(' ');
                this.tb.addCharCmd(' ');
                break;
            }
            case '\n':
            case '\r': {
                this.enterKey();
                break;
            }
            default: {
                if (keyChar >= ' ' && keyChar <= '~') {
                    this.textModified = true;
                    this.tb.addCharCmd(keyChar);
                    break;
                }
                break;
            }
        }
    }
    
    public void run() {
        while (Thread.currentThread() == this.timerThread) {
            if (this.triggerTime <= 0L) {
                synchronized (this.cmdLeadChar) {
                    try {
                        this.cmdLeadChar.wait();
                    }
                    catch (InterruptedException ex) {}
                    continue;
                }
                break;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis >= this.triggerTime) {
                synchronized (this.cmdLeadChar) {
                    switch (this.cmdLeadChar[0]) {
                        case '\u001b': {
                            Toolkit.getDefaultToolkit().beep();
                            break;
                        }
                    }
                    this.cmdLeadChar[0] = '\0';
                    this.triggerTime = 0L;
                    continue;
                }
            }
            try {
                Thread.sleep(this.triggerTime - currentTimeMillis);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    private void addProcToParse(final String s) {
        if (this.procsToParse.indexOf(s) < 0) {
            this.procsToParse.addElement(s);
        }
    }
    
    private void addEndLine(final int n) {
        final int topOfProcIntro = this.topOfProcIntro(n);
        this.tb.openEmptyLine(topOfProcIntro);
        for (int length = "  end".length(), i = 0; i < length; ++i) {
            this.tb.addChar(topOfProcIntro, i, "  end".charAt(i));
        }
        this.tb.openEmptyLine(topOfProcIntro);
    }
    
    private String chkIfInProcDef(final int n) {
        for (int i = n; i >= 0; --i) {
            final String line = this.tb.getLine(i);
            if (line != null) {
                if (this.procEndRegExp.findPattern(line) == 0 && i != n) {
                    break;
                }
                if (this.procDefRegExp.findPattern(line) == 0) {
                    return this.procIdent(line);
                }
            }
        }
        return null;
    }
    
    private ColoredText[] colorSearchMatches(final ColoredText[] array, final String s) {
        if (this.lastSearch == null) {
            return array;
        }
        final int length = this.lastSearch.length();
        if (length == 0) {
            return array;
        }
        final String lowerCase = s.toLowerCase();
        final String lowerCase2 = this.lastSearch.toLowerCase();
        int n = lowerCase.indexOf(lowerCase2);
        if (n < 0) {
            return array;
        }
        int n2 = 1;
        int n3;
        while ((n3 = n + length) < lowerCase.length() && (n = lowerCase.indexOf(lowerCase2, n3)) >= 0) {
            ++n2;
        }
        final ColoredText[] array2 = new ColoredText[2 * n2 + 1];
        int n4 = 0;
        int i = lowerCase.indexOf(lowerCase2);
        int n5 = 0;
        while (i >= 0) {
            final int n6 = i - n4;
            if (n6 > 0) {
                array2[n5] = new ColoredText(Editor.FG_COLOR, Editor.BG_COLOR, s.substring(n4, n4 + n6));
                ++n5;
            }
            array2[n5] = new ColoredText(Editor.FG_COLOR, Editor.MATCH_COLOR, s.substring(i, i + length));
            ++n5;
            n4 = i + length;
            i = lowerCase.indexOf(lowerCase2, n4);
        }
        if (n4 < lowerCase.length()) {
            array2[n5] = new ColoredText(Editor.FG_COLOR, Editor.BG_COLOR, s.substring(n4, s.length()));
        }
        if (array != null) {
            return this.mergeColoredText(array, array2);
        }
        return array2;
    }
    
    private int firstNonBlankChar(final String s) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1;
            if ((char1 = s.charAt(i)) != ' ') {
                return char1;
            }
        }
        return -1;
    }
    
    private ColoredText[] mergeColoredText(final ColoredText[] array, final ColoredText[] array2) {
        final ColoredText[] array3 = new ColoredText[array.length * 2 + array2.length];
        int n = 0;
        int n2 = 0;
        for (int n3 = 0; n3 < array2.length && array2[n3] != null; ++n3) {
            int i = array2[n3].text.length();
            if (array2[n3].bg == Editor.BG_COLOR && array2[n3].fg == Editor.FG_COLOR) {
                while (i > 0) {
                    final ColoredText coloredText = array[n2];
                    final int length = coloredText.text.length();
                    if (i >= length) {
                        array3[n++] = coloredText;
                        ++n2;
                        i -= length;
                    }
                    else {
                        array3[n++] = new ColoredText(coloredText.fg, coloredText.bg, coloredText.text.substring(0, i));
                        array[n2] = new ColoredText(coloredText.fg, coloredText.bg, coloredText.text.substring(i, length));
                        i = 0;
                    }
                }
            }
            else {
                array3[n++] = array2[n3];
                while (i > 0) {
                    final ColoredText coloredText2 = array[n2];
                    final int length2 = coloredText2.text.length();
                    if (i >= length2) {
                        i -= length2;
                        ++n2;
                    }
                    else {
                        array[n2] = new ColoredText(coloredText2.fg, coloredText2.bg, coloredText2.text.substring(i, length2));
                        i = 0;
                    }
                }
            }
        }
        return array3;
    }
    
    private void modifiedTitleLine(final int n, String line) {
        final int length = line.length();
        this.addProcToParse(this.procIdent(line));
        int n2 = 0;
        while (n2 < length && line.charAt(n2++) == ' ') {
            this.tb.delChar(n, 0);
        }
        int n3;
        for (n3 = n + 1; (line = this.tb.getLine(n3)) != null; ++n3) {
            if (this.procEndRegExp.findPattern(line) == 0) {
                return;
            }
            if (this.procDefRegExp.findPattern(line) == 0) {
                break;
            }
        }
        this.addEndLine(n3);
    }
    
    private String procIdent(final String s) {
        int n;
        for (n = 0; s.charAt(n) == ' '; ++n) {}
        for (n += 2; s.charAt(n) == ' '; ++n) {}
        int n2 = s.indexOf(32, n);
        if (n2 < 0) {
            n2 = s.length();
        }
        return s.substring(n, n2);
    }
    
    private void addRemSpaces(final int n) {
        final String line = this.tb.getLine(n);
        final int length = line.length();
        int i = -1;
        for (int j = 0; j < length; ++j) {
            if (line.charAt(j) != ' ') {
                i = j;
                break;
            }
        }
        while (i > 2) {
            this.tb.delChar(n, 0);
            --i;
        }
        while (i < 2) {
            this.tb.addChar(n, 0, ' ');
            ++i;
        }
        final String line2 = this.tb.getLine(n);
        for (int n2 = line2.length() - 1; n2 >= 2 && line2.charAt(n2) == ' '; --n2) {
            this.tb.delChar(n, n2);
        }
    }
    
    private int topOfProcIntro(final int n) {
        int i;
        for (i = n - 1; i >= 0; --i) {
            final int firstNonBlankChar = this.firstNonBlankChar(this.tb.getLine(i));
            if (firstNonBlankChar != -1 && firstNonBlankChar != 59) {
                break;
            }
        }
        return i + 1;
    }
    
    public ColoredText[] colorText(final String s) {
        final int lastIndex = s.lastIndexOf(";");
        if (lastIndex == 0) {
            return this.colorSearchMatches(new ColoredText[] { new ColoredText(Editor.COMMENT_COLOR, Editor.BG_COLOR, s) }, s);
        }
        if (lastIndex > 0) {
            if (s.charAt(lastIndex - 1) == '\"') {
                return this.colorSearchMatches(null, s);
            }
            final int lastIndex2 = s.lastIndexOf("[");
            final int lastIndex3 = s.lastIndexOf("]");
            if (lastIndex2 >= 0 && lastIndex2 < lastIndex && (lastIndex3 < 0 || lastIndex3 > lastIndex)) {
                return this.colorSearchMatches(null, s);
            }
            return this.colorSearchMatches(new ColoredText[] { new ColoredText(Editor.FG_COLOR, Editor.BG_COLOR, s.substring(0, lastIndex)), new ColoredText(Editor.COMMENT_COLOR, Editor.BG_COLOR, s.substring(lastIndex)) }, s);
        }
        else {
            if (s.startsWith("to ") || s.startsWith("  end")) {
                return this.colorSearchMatches(new ColoredText[] { new ColoredText(Editor.TITLE_END_COLOR, Editor.BG_COLOR, s) }, s);
            }
            return this.colorSearchMatches(null, s);
        }
    }
    
    public void curLineNumChanged(final int n) {
    }
    
    public void focusGained() {
    }
    
    public void focusLost() {
        this.parseProcs();
    }
    
    public void lineAdded(final int n) {
        final String chkIfInProcDef = this.chkIfInProcDef(n);
        if (chkIfInProcDef != null) {
            this.addProcToParse(chkIfInProcDef);
        }
    }
    
    public void lineDeleted(final int n) {
        final String chkIfInProcDef = this.chkIfInProcDef(n);
        if (chkIfInProcDef != null) {
            this.addProcToParse(chkIfInProcDef);
        }
    }
    
    public void lineModified(int n) {
        final String line = this.tb.getLine(n);
        if (line == null) {
            return;
        }
        final int length = line.length();
        if (this.procDefRegExp.findPattern(line) == 0) {
            this.modifiedTitleLine(n, line);
        }
        else {
            final String chkIfInProcDef = this.chkIfInProcDef(n);
            if (chkIfInProcDef != null) {
                this.addProcToParse(chkIfInProcDef);
                this.addRemSpaces(n);
                if (this.procEndRegExp.findPattern(line) == 0) {
                    String line2;
                    while ((line2 = this.tb.getLine(++n)) != null) {
                        if (this.procEndRegExp.findPattern(line2) == 0) {
                            this.tb.delLine(n);
                            break;
                        }
                        if (this.procDefRegExp.findPattern(line2) == 0) {
                            return;
                        }
                    }
                }
            }
            else {
                int n2 = 0;
                while (n2 < length && line.charAt(n2++) == ' ') {
                    this.tb.delChar(n, 0);
                }
                if (this.globVarRegExp.findPattern(line) == 0) {
                    TGDriver.parseJLogo(line);
                }
                else if (this.makeVarRegExp.findPattern(line) == 0) {
                    TGDriver.parseJLogo(line);
                }
            }
        }
    }
    
    public Dimension getMinimumSize() {
        final Dimension minimumSize = this.tb.getMinimumSize();
        return new Dimension(minimumSize.width, minimumSize.height);
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = this.tb.getPreferredSize();
        return new Dimension(preferredSize.width, preferredSize.height);
    }
    
    public void requestFocus() {
        this.tb.requestFocus();
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        if (this.promptBar != null) {
            final Dimension preferredSize = this.promptBar.getPreferredSize();
            this.tb.setSize(n3, n4 - preferredSize.height);
            this.promptBar.setBounds(0, n4 - preferredSize.height, n3, preferredSize.height);
        }
        else {
            this.tb.setSize(n3, n4);
        }
    }
    
    public void setSize(final int n, final int n2) {
        super.setSize(n, n2);
        if (this.promptBar != null) {
            final Dimension preferredSize = this.promptBar.getPreferredSize();
            this.tb.setSize(n, n2 - preferredSize.height);
            this.promptBar.setBounds(0, n2 - preferredSize.height, n, preferredSize.height);
        }
        else {
            this.tb.setSize(n, n2);
        }
    }
    
    private void clearText() {
        this.tb.clearText();
        this.textModified = false;
    }
    
    private void doPrompt(final int n, final String s) {
        this.doPrompt(n, s, null, false);
    }
    
    private void doPrompt(final int n, final String s, final String s2) {
        this.doPrompt(n, s, s2, false);
    }
    
    private void doPrompt(final int whichPrompt, final String s, final String s2, final boolean b) {
        if (this.promptBar != null) {
            if (this.whichPrompt == 1) {
                return;
            }
            this.remove(this.promptBar);
            this.promptBar.done();
        }
        this.whichPrompt = whichPrompt;
        final Rectangle bounds = this.tb.getBounds();
        this.promptBar = new TEPromptBar(bounds.width, this.fontSize, s, s2);
        if (b) {
            this.promptBar.setTextIsDefault();
        }
        this.promptBar.addKeyListener(this);
        this.add(this.promptBar);
        final Dimension preferredSize = this.promptBar.getPreferredSize();
        this.tb.setSize(bounds.width, bounds.height - preferredSize.height);
        this.promptBar.setBounds(0, bounds.height - preferredSize.height, bounds.width, preferredSize.height);
        this.promptBar.requestFocus();
        this.promptAborted = false;
    }
    
    private void donePrompt() {
        this.remove(this.promptBar);
        this.promptBar.done();
        final Rectangle bounds = this.promptBar.getBounds();
        this.promptBar = null;
        final Rectangle bounds2 = this.tb.getBounds();
        this.tb.setSize(bounds2.width, bounds2.height + bounds.height);
        this.tb.requestFocus();
    }
    
    private void doSearchBack() {
        final String inputText = this.promptBar.getInputText();
        if (!this.findSearchStringBack(inputText)) {
            Toolkit.getDefaultToolkit().beep();
        }
        this.lastSearch = inputText;
    }
    
    private void doSearchFwd() {
        final String inputText = this.promptBar.getInputText();
        if (!this.findSearchStringFwd(inputText)) {
            Toolkit.getDefaultToolkit().beep();
        }
        this.lastSearch = inputText;
    }
    
    private void enterKey() {
        this.tb.enterCmd();
        this.textModified = true;
        final int curLineNum = this.tb.getCurLineNum();
        if (this.procDefRegExp.findPattern(this.tb.getLine(curLineNum)) < 0 && this.chkIfInProcDef(curLineNum) != null) {
            this.addRemSpaces(curLineNum);
            this.tb.setColNum(2);
        }
    }
    
    private boolean findSearchStringBack(final String s) {
        final int searchBack = this.tb.searchBack(s);
        if (searchBack >= 0) {
            this.tb.setCurLinNumCmd(searchBack / 1024);
            this.tb.setColNum(searchBack % 1024);
            return true;
        }
        return false;
    }
    
    private boolean findSearchStringFwd(final String s) {
        final int searchFwd = this.tb.searchFwd(s);
        if (searchFwd >= 0) {
            this.tb.setCurLinNumCmd(searchFwd / 1024);
            this.tb.setColNum(searchFwd % 1024);
            return true;
        }
        return false;
    }
    
    private boolean isBlankLine(final String s) {
        int i = s.length();
        int n = 0;
        while (i > 0) {
            if (s.charAt(n++) != ' ') {
                return false;
            }
            --i;
        }
        return true;
    }
    
    private void loadFile(final char[] array) {
        int n = 0;
        final int length = array.length;
        int curLineNum = 0;
        int n2 = 0;
        this.interpretAllFailed = false;
        for (int i = 0; i < length; ++i) {
            final char c = array[i];
            if (c == '\t') {
                array[i] = ' ';
            }
            else if (c == '\n') {
                int n3 = i - n2;
                if (n3 > 0) {
                    if (array[i - 1] == '\r') {
                        --n3;
                    }
                    final StringBuffer sb = new StringBuffer(n3);
                    sb.append(array, n2, n3);
                    this.tb.insertLineOfChars(sb);
                    if (n == 0 && !TGDriver.parseJLogo(sb.toString())) {
                        n = 1;
                        curLineNum = this.tb.getCurLineNum();
                        TGDriver.abortProcDef();
                        this.interpretAllFailed = true;
                    }
                }
                else {
                    this.tb.openEmptyLine();
                }
                n2 = i + 1;
            }
        }
        if (n2 <= length) {
            final int n4 = length - n2;
            final StringBuffer sb2 = new StringBuffer(n4);
            sb2.append(array, n2, n4);
            this.tb.insertLineOfChars(sb2);
            this.tb.cursorLeft();
        }
        this.textModified = false;
        if (n != 0) {
            if (curLineNum != 0) {
                this.tb.setTopLineNum(curLineNum - 1);
            }
            this.tb.setCurLinNumCmd(curLineNum);
        }
        else {
            this.tb.setCurLinNumCmd(0);
        }
        this.tb.setColNum(0);
    }
    
    private void openLine() {
        this.tb.openLineCmd();
        final int curLineNum = this.tb.getCurLineNum();
        if (this.chkIfInProcDef(curLineNum) != null) {
            this.tb.addChar(curLineNum, 0, ' ');
            this.tb.addChar(curLineNum, 0, ' ');
            this.tb.gotoEndOfLine();
        }
    }
    
    private boolean parseProc(int n) {
        final StringBuffer sb = new StringBuffer(150);
        String line;
        while ((line = this.tb.getLine(n)) != null) {
            sb.setLength(0);
            sb.append(line);
            if (!TGDriver.parseJLogo(sb.toString())) {
                TGDriver.abortProcDef();
                this.tb.setTopLineNum((n == 0) ? 0 : (n - 1));
                return false;
            }
            if (this.procEndRegExp.findPattern(line) == 0) {
                break;
            }
            ++n;
        }
        return true;
    }
    
    private void setCurFile() {
        final FileDialog fileDialog = new FileDialog(TG.getFrame(), "Save to File", 1);
        fileDialog.show();
        String curFile;
        if ((curFile = fileDialog.getFile()) == null) {
            return;
        }
        final String directory;
        if ((directory = fileDialog.getDirectory()) != null) {
            curFile = directory + curFile;
        }
        TG.setEditorFileName(this.curFile = curFile);
    }
    
    public void copySelection() {
        this.tb.copySelection();
    }
    
    public void cutSelection() {
        this.textModified = true;
        this.tb.cutSelection();
    }
    
    public int getCurLineNum() {
        return this.tb.getCurLineNum();
    }
    
    public String getLine(final int n) {
        return this.tb.getLine(n);
    }
    
    public StringBuffer getText() {
        int n = 0;
        int i = 0;
        final String property = System.getProperty("line.separator");
        final StringBuffer sb = new StringBuffer(5000);
        for (String s = this.tb.getLine(n); s != null; s = this.tb.getLine(n)) {
            if (s.length() == 0) {
                ++i;
            }
            else {
                while (i > 0) {
                    sb.append(property);
                    --i;
                }
                sb.append(s);
                sb.append(property);
            }
            ++n;
        }
        return sb;
    }
    
    public TextBuffer getTextBuffer() {
        return this.tb;
    }
    
    public void interpretBuffer() {
        this.interpretAllFailed = false;
        final StringBuffer sb = new StringBuffer(150);
        for (int maxLineNum = this.tb.getMaxLineNum(), i = 0; i <= maxLineNum; ++i) {
            sb.setLength(0);
            sb.append(this.tb.getLine(i));
            if (!TGDriver.parseJLogo(sb.toString())) {
                TGDriver.abortProcDef();
                this.tb.setTopLineNum((i == 0) ? 0 : (i - 1));
                this.interpretAllFailed = true;
                break;
            }
        }
    }
    
    public int lineStartingWith(final String s) {
        return this.tb.lineStartingWith(s);
    }
    
    private void parseProcs() {
        if (this.interpretAllFailed) {
            this.interpretBuffer();
            this.procsToParse.removeAllElements();
        }
        else if (this.procsToParse != null && !this.procsToParse.isEmpty()) {
            for (int maxLineNum = this.tb.getMaxLineNum(), i = 0; i <= maxLineNum; ++i) {
                final String line = this.tb.getLine(i);
                if (line != null) {
                    if (this.procDefRegExp.findPattern(line) == 0) {
                        final int index = this.procsToParse.indexOf(this.procIdent(line));
                        if (index >= 0) {
                            if (!this.parseProc(i)) {
                                return;
                            }
                            this.procsToParse.removeElementAt(index);
                        }
                    }
                }
            }
        }
    }
    
    public void newFile() {
        if (this.textModified) {
            this.doPrompt(1, "Save changes to " + ((this.curFile != null) ? this.curFile : "NoName") + "? ");
            this.promptBar.setYesNoReply();
            this.nxtFile = null;
        }
        else {
            TG.reset();
            this.tb.requestFocus();
        }
    }
    
    public void openFile() {
        final FileDialog fileDialog = new FileDialog(TG.getFrame(), "Open File", 0);
        fileDialog.show();
        String s;
        if ((s = fileDialog.getFile()) == null) {
            return;
        }
        final String directory;
        if ((directory = fileDialog.getDirectory()) != null) {
            s = directory + s;
        }
        if (this.textModified) {
            this.doPrompt(1, "Save changes to " + ((this.curFile != null) ? this.curFile : "NoName") + "? ");
            this.promptBar.setYesNoReply();
            this.nxtFile = s;
        }
        else {
            TG.reset();
            final char[] file = FileIO.readFile(s);
            if (file != null) {
                this.loadFile(file);
                this.curFile = s;
            }
            TG.setEditorFileName((this.curFile != null) ? this.curFile : "NoName");
            this.tb.requestFocus();
        }
    }
    
    public void pasteSelection() {
        this.textModified = true;
        this.tb.pasteSelection();
    }
    
    public void reset() {
        this.clearText();
        this.curFile = null;
        TG.setEditorFileName("NoName");
    }
    
    public void save() {
        if (this.textModified) {
            if (this.curFile == null) {
                this.setCurFile();
            }
            if (this.curFile != null) {
                FileIO.saveToFile(this.curFile, this.getText().toString());
                this.textModified = false;
            }
        }
    }
    
    public void saveAs() {
        this.setCurFile();
        if (this.curFile != null) {
            FileIO.saveToFile(this.curFile, this.getText().toString());
        }
        this.textModified = false;
    }
    
    public int saveFileCompleted() {
        if (this.promptBar != null && this.whichPrompt == 1) {
            return 0;
        }
        if (this.promptAborted) {
            return -1;
        }
        return 1;
    }
    
    public void setTopLineNum(final int topLineNum) {
        this.tb.setTopLineNum(topLineNum);
    }
    
    public boolean waitForSave() {
        if (this.textModified) {
            this.doPrompt(1, "Save changes to " + ((this.curFile != null) ? this.curFile : "NoName") + "? ");
            this.promptBar.setYesNoReply();
            this.nxtFile = null;
            return true;
        }
        return false;
    }
    
    static {
        BG_COLOR = Color.white;
        FG_COLOR = Color.black;
        COMMENT_COLOR = Color.blue;
        MATCH_COLOR = Color.yellow;
        TITLE_END_COLOR = new Color(0, 150, 50);
    }
}
