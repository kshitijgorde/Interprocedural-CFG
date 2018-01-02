// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import javax.swing.AbstractAction;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.util.Vector;
import java.util.Enumeration;
import java.text.BreakIterator;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import javax.swing.event.DocumentEvent;
import javax.accessibility.AccessibleText;
import java.io.Writer;
import java.awt.Point;
import javax.swing.UIManager;
import javax.swing.plaf.ComponentUI;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeListener;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.Reader;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.plaf.TextUI;
import javax.swing.JViewport;
import java.awt.Rectangle;
import java.awt.Dimension;
import javax.accessibility.AccessibleContext;
import javax.swing.event.CaretEvent;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.util.EventListener;
import javax.swing.event.CaretListener;
import java.awt.LayoutManager;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import javax.swing.KeyStroke;
import java.util.Hashtable;
import java.awt.datatransfer.ClipboardOwner;
import javax.swing.Action;
import java.awt.Insets;
import java.awt.Color;
import javax.accessibility.Accessible;
import javax.swing.Scrollable;
import javax.swing.JComponent;

public abstract class JTextComponent extends JComponent implements Scrollable, Accessible
{
    public static final String FOCUS_ACCELERATOR_KEY = "focusAcceleratorKey";
    private Document model;
    private transient Caret caret;
    private transient Highlighter highlighter;
    private transient Keymap keymap;
    private boolean opaque;
    private transient MutableCaretEvent caretEvent;
    private Color caretColor;
    private Color selectionColor;
    private Color selectedTextColor;
    private Color disabledTextColor;
    private boolean editable;
    private Insets margin;
    private char focusAccelerator;
    private Action focusAction;
    private static ClipboardOwner defaultClipboardOwner;
    private static Hashtable keymapTable;
    private JTextComponent editor;
    private static JTextComponent focusedComponent;
    public static final String DEFAULT_KEYMAP = "default";
    static final KeyBinding[] defaultBindings;
    static /* synthetic */ Class class$javax$swing$event$CaretListener;
    
    static {
        JTextComponent.defaultClipboardOwner = new ClipboardObserver();
        JTextComponent.keymapTable = null;
        defaultBindings = new KeyBinding[] { new KeyBinding(KeyStroke.getKeyStroke(8, 0), "delete-previous"), new KeyBinding(KeyStroke.getKeyStroke(127, 0), "delete-next"), new KeyBinding(KeyStroke.getKeyStroke(39, 0), "caret-forward"), new KeyBinding(KeyStroke.getKeyStroke(37, 0), "caret-backward") };
        try {
            JTextComponent.keymapTable = new Hashtable(17);
            final Keymap addKeymap = addKeymap("default", null);
            addKeymap.setDefaultAction(new DefaultEditorKit.DefaultKeyTypedAction());
            loadKeymap(addKeymap, JTextComponent.defaultBindings, new DefaultEditorKit().getActions());
        }
        catch (Throwable t) {
            t.printStackTrace();
            JTextComponent.keymapTable = new Hashtable(17);
        }
    }
    
    public JTextComponent() {
        this.focusAction = new FocusAction();
        this.enableEvents(8L);
        this.addMouseListener(this.caretEvent = new MutableCaretEvent(this));
        this.addFocusListener(this.caretEvent);
        this.setEditable(true);
        this.setLayout(null);
        this.updateUI();
    }
    
    static /* synthetic */ void access$2(final JTextComponent focusedComponent) {
        JTextComponent.focusedComponent = focusedComponent;
    }
    
    public void addCaretListener(final CaretListener caretListener) {
        super.listenerList.add((JTextComponent.class$javax$swing$event$CaretListener != null) ? JTextComponent.class$javax$swing$event$CaretListener : (JTextComponent.class$javax$swing$event$CaretListener = class$("javax.swing.event.CaretListener")), caretListener);
    }
    
    public static Keymap addKeymap(final String s, final Keymap keymap) {
        final DefaultKeymap defaultKeymap = new DefaultKeymap(s, keymap);
        if (s != null) {
            JTextComponent.keymapTable.put(s, defaultKeymap);
        }
        return defaultKeymap;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public void copy() {
        try {
            final Clipboard systemClipboard = this.getToolkit().getSystemClipboard();
            final int min = Math.min(this.caret.getDot(), this.caret.getMark());
            final int max = Math.max(this.caret.getDot(), this.caret.getMark());
            if (min != max) {
                systemClipboard.setContents(new StringSelection(this.getDocument().getText(min, max - min)), JTextComponent.defaultClipboardOwner);
            }
        }
        catch (BadLocationException ex) {}
    }
    
    public void cut() {
        if (this.isEditable() && this.isEnabled()) {
            try {
                final Clipboard systemClipboard = this.getToolkit().getSystemClipboard();
                final int min = Math.min(this.caret.getDot(), this.caret.getMark());
                final int max = Math.max(this.caret.getDot(), this.caret.getMark());
                if (min != max) {
                    final Document document = this.getDocument();
                    systemClipboard.setContents(new StringSelection(document.getText(min, max - min)), JTextComponent.defaultClipboardOwner);
                    document.remove(min, max - min);
                }
            }
            catch (BadLocationException ex) {}
        }
        else {
            this.getToolkit().beep();
        }
    }
    
    protected void fireCaretUpdate(final CaretEvent caretEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JTextComponent.class$javax$swing$event$CaretListener != null) ? JTextComponent.class$javax$swing$event$CaretListener : (JTextComponent.class$javax$swing$event$CaretListener = class$("javax.swing.event.CaretListener")))) {
                ((CaretListener)listenerList[i + 1]).caretUpdate(caretEvent);
            }
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJTextComponent();
        }
        return super.accessibleContext;
    }
    
    public Action[] getActions() {
        return this.getUI().getEditorKit(this).getActions();
    }
    
    public Caret getCaret() {
        return this.caret;
    }
    
    public Color getCaretColor() {
        return this.caretColor;
    }
    
    public int getCaretPosition() {
        return this.caret.getDot();
    }
    
    public Color getDisabledTextColor() {
        return this.disabledTextColor;
    }
    
    public Document getDocument() {
        return this.model;
    }
    
    public char getFocusAccelerator() {
        return this.focusAccelerator;
    }
    
    static final JTextComponent getFocusedComponent() {
        return JTextComponent.focusedComponent;
    }
    
    public Highlighter getHighlighter() {
        return this.highlighter;
    }
    
    public Keymap getKeymap() {
        return this.keymap;
    }
    
    public static Keymap getKeymap(final String s) {
        return JTextComponent.keymapTable.get(s);
    }
    
    public Insets getMargin() {
        return this.margin;
    }
    
    public Dimension getPreferredScrollableViewportSize() {
        return this.getPreferredSize();
    }
    
    public int getScrollableBlockIncrement(final Rectangle rectangle, final int n, final int n2) {
        switch (n) {
            case 1: {
                return rectangle.height;
            }
            case 0: {
                return rectangle.width;
            }
            default: {
                throw new IllegalArgumentException("Invalid orientation: " + n);
            }
        }
    }
    
    public boolean getScrollableTracksViewportHeight() {
        return this.getParent() instanceof JViewport && ((JViewport)this.getParent()).getHeight() > this.getPreferredSize().height;
    }
    
    public boolean getScrollableTracksViewportWidth() {
        return this.getParent() instanceof JViewport && ((JViewport)this.getParent()).getWidth() > this.getPreferredSize().width;
    }
    
    public int getScrollableUnitIncrement(final Rectangle rectangle, final int n, final int n2) {
        switch (n) {
            case 1: {
                return rectangle.height / 10;
            }
            case 0: {
                return rectangle.width / 10;
            }
            default: {
                throw new IllegalArgumentException("Invalid orientation: " + n);
            }
        }
    }
    
    public String getSelectedText() {
        String text = null;
        final int min = Math.min(this.caret.getDot(), this.caret.getMark());
        final int max = Math.max(this.caret.getDot(), this.caret.getMark());
        if (min != max) {
            try {
                text = this.getDocument().getText(min, max - min);
            }
            catch (BadLocationException ex) {
                throw new IllegalArgumentException(ex.getMessage());
            }
        }
        return text;
    }
    
    public Color getSelectedTextColor() {
        return this.selectedTextColor;
    }
    
    public Color getSelectionColor() {
        return this.selectionColor;
    }
    
    public int getSelectionEnd() {
        return Math.max(this.caret.getDot(), this.caret.getMark());
    }
    
    int getSelectionEnd(final Position.Bias[] array) {
        final DefaultCaret defaultCaret = (DefaultCaret)this.caret;
        if (defaultCaret.getDot() > defaultCaret.getMark()) {
            array[0] = defaultCaret.getDotBias();
            return defaultCaret.getDot();
        }
        array[0] = defaultCaret.getMarkBias();
        return defaultCaret.getMark();
    }
    
    public int getSelectionStart() {
        return Math.min(this.caret.getDot(), this.caret.getMark());
    }
    
    int getSelectionStart(final Position.Bias[] array) {
        final DefaultCaret defaultCaret = (DefaultCaret)this.caret;
        if (defaultCaret.getDot() < defaultCaret.getMark()) {
            array[0] = defaultCaret.getDotBias();
            return defaultCaret.getDot();
        }
        array[0] = defaultCaret.getMarkBias();
        return defaultCaret.getMark();
    }
    
    public String getText() {
        final Document document = this.getDocument();
        String text;
        try {
            text = document.getText(0, document.getLength());
        }
        catch (BadLocationException ex) {
            text = null;
        }
        return text;
    }
    
    public String getText(final int n, final int n2) throws BadLocationException {
        return this.getDocument().getText(n, n2);
    }
    
    public TextUI getUI() {
        return (TextUI)super.ui;
    }
    
    public boolean isEditable() {
        return this.editable;
    }
    
    public boolean isFocusTraversable() {
        return this.isEnabled();
    }
    
    public boolean isOpaque() {
        return this.opaque;
    }
    
    public static void loadKeymap(final Keymap keymap, final KeyBinding[] array, final Action[] array2) {
        final Hashtable<String, Action> hashtable = new Hashtable<String, Action>();
        for (int i = 0; i < array2.length; ++i) {
            final Action action = array2[i];
            final String s = (String)action.getValue("Name");
            hashtable.put((s != null) ? s : "", action);
        }
        for (int j = 0; j < array.length; ++j) {
            final Action action2 = hashtable.get(array[j].actionName);
            if (action2 != null) {
                keymap.addActionForKeyStroke(array[j].key, action2);
            }
        }
    }
    
    private final boolean mapEventToAction(final KeyEvent keyEvent) {
        final Keymap keymap = this.getKeymap();
        if (keymap != null) {
            final Action action = keymap.getAction(KeyStroke.getKeyStrokeForEvent(keyEvent));
            if (action != null) {
                String value = null;
                if (keyEvent.getKeyChar() != '\0') {
                    value = String.valueOf(keyEvent.getKeyChar());
                }
                action.actionPerformed(new ActionEvent(this, 1001, value, keyEvent.getModifiers()));
                keyEvent.consume();
                return true;
            }
        }
        return false;
    }
    
    public Rectangle modelToView(final int n) throws BadLocationException {
        return this.getUI().modelToView(this, n);
    }
    
    public void moveCaretPosition(final int n) {
        this.caret.moveDot(n);
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",caretColor=" + ((this.caretColor != null) ? this.caretColor.toString() : "") + ",disabledTextColor=" + ((this.disabledTextColor != null) ? this.disabledTextColor.toString() : "") + ",editable=" + (this.editable ? "true" : "false") + ",margin=" + ((this.margin != null) ? this.margin.toString() : "") + ",opaque=" + (this.opaque ? "true" : "false") + ",selectedTextColor=" + ((this.selectedTextColor != null) ? this.selectedTextColor.toString() : "") + ",selectionColor=" + ((this.selectionColor != null) ? this.selectionColor.toString() : "");
    }
    
    public void paste() {
        final Transferable contents = this.getToolkit().getSystemClipboard().getContents(this);
        if (contents != null) {
            try {
                this.replaceSelection((String)contents.getTransferData(DataFlavor.stringFlavor));
            }
            catch (Exception ex) {
                this.getToolkit().beep();
            }
        }
    }
    
    protected void processComponentKeyEvent(final KeyEvent keyEvent) {
        switch (keyEvent.getID()) {
            case 400: {
                if (this.mapEventToAction(keyEvent)) {
                    break;
                }
                final Keymap keymap = this.getKeymap();
                if (keymap == null) {
                    break;
                }
                final Action defaultAction = keymap.getDefaultAction();
                if (defaultAction != null) {
                    defaultAction.actionPerformed(new ActionEvent(this, 1001, String.valueOf(keyEvent.getKeyChar()), keyEvent.getModifiers()));
                    keyEvent.consume();
                    break;
                }
                break;
            }
            case 401: {
                this.mapEventToAction(keyEvent);
                break;
            }
            case 402: {
                this.mapEventToAction(keyEvent);
                break;
            }
        }
    }
    
    public void read(final Reader reader, final Object o) throws IOException {
        final EditorKit editorKit = this.getUI().getEditorKit(this);
        final Document defaultDocument = editorKit.createDefaultDocument();
        if (o != null) {
            defaultDocument.putProperty("stream", o);
        }
        try {
            editorKit.read(reader, defaultDocument, 0);
            this.setDocument(defaultDocument);
        }
        catch (BadLocationException ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.addMouseListener(this.caretEvent = new MutableCaretEvent(this));
        this.addFocusListener(this.caretEvent);
    }
    
    public void removeCaretListener(final CaretListener caretListener) {
        super.listenerList.remove((JTextComponent.class$javax$swing$event$CaretListener != null) ? JTextComponent.class$javax$swing$event$CaretListener : (JTextComponent.class$javax$swing$event$CaretListener = class$("javax.swing.event.CaretListener")), caretListener);
    }
    
    public static Keymap removeKeymap(final String s) {
        return JTextComponent.keymapTable.remove(s);
    }
    
    public void removeNotify() {
        super.removeNotify();
        if (JTextComponent.focusedComponent == this) {
            JTextComponent.focusedComponent = null;
        }
    }
    
    public void replaceSelection(final String s) {
        final Document document = this.getDocument();
        if (document != null) {
            try {
                final int min = Math.min(this.caret.getDot(), this.caret.getMark());
                final int max = Math.max(this.caret.getDot(), this.caret.getMark());
                if (min != max) {
                    document.remove(min, max - min);
                }
                if (s != null && s.length() > 0) {
                    document.insertString(min, s, null);
                }
            }
            catch (BadLocationException ex) {
                this.getToolkit().beep();
            }
        }
    }
    
    public void select(int caretPosition, int length) {
        if (caretPosition < 0) {
            caretPosition = 0;
        }
        if (length > this.getDocument().getLength()) {
            length = this.getDocument().getLength();
        }
        if (length < caretPosition) {
            length = caretPosition;
        }
        if (caretPosition > length) {
            caretPosition = length;
        }
        this.setCaretPosition(caretPosition);
        this.moveCaretPosition(length);
    }
    
    public void selectAll() {
        final Document document = this.getDocument();
        if (document != null) {
            this.setCaretPosition(0);
            this.moveCaretPosition(document.getLength());
        }
    }
    
    public void setCaret(final Caret caret) {
        if (this.caret != null) {
            this.caret.removeChangeListener(this.caretEvent);
            this.caret.deinstall(this);
        }
        final Caret caret2 = this.caret;
        this.caret = caret;
        if (this.caret != null) {
            this.caret.install(this);
            this.caret.addChangeListener(this.caretEvent);
        }
        this.firePropertyChange("caret", caret2, this.caret);
    }
    
    public void setCaretColor(final Color caretColor) {
        this.firePropertyChange("caretColor", this.caretColor, this.caretColor = caretColor);
    }
    
    public void setCaretPosition(final int dot) {
        final Document document = this.getDocument();
        if (document != null) {
            if (dot > document.getLength() || dot < 0) {
                throw new IllegalArgumentException("bad position: " + dot);
            }
            this.caret.setDot(dot);
        }
    }
    
    public void setDisabledTextColor(final Color disabledTextColor) {
        this.firePropertyChange("disabledTextColor", this.disabledTextColor, this.disabledTextColor = disabledTextColor);
    }
    
    public void setDocument(final Document model) {
        if (super.accessibleContext != null) {
            this.model.removeDocumentListener((DocumentListener)super.accessibleContext);
        }
        this.firePropertyChange("document", this.model, this.model = model);
        this.revalidate();
        this.repaint();
        if (super.accessibleContext != null) {
            this.model.addDocumentListener((DocumentListener)super.accessibleContext);
        }
    }
    
    public void setEditable(final boolean editable) {
        if (editable != this.editable) {
            final boolean editable2 = this.editable;
            this.editable = editable;
            this.firePropertyChange("editable", new Boolean(editable2), new Boolean(this.editable));
            this.repaint();
        }
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        this.repaint();
    }
    
    public void setFocusAccelerator(final char c) {
        final char upperCase = Character.toUpperCase(c);
        final KeyStroke[] registeredKeyStrokes = this.getRegisteredKeyStrokes();
        int i = 0;
        while (i < registeredKeyStrokes.length) {
            if (this.getActionForKeyStroke(registeredKeyStrokes[i]) == this.focusAction) {
                if (registeredKeyStrokes[i].getKeyChar() == upperCase) {
                    return;
                }
                this.unregisterKeyboardAction(registeredKeyStrokes[i]);
                break;
            }
            else {
                ++i;
            }
        }
        if (upperCase != '\0') {
            this.registerKeyboardAction(this.focusAction, KeyStroke.getKeyStroke(upperCase, 8), 2);
        }
        this.firePropertyChange("focusAcceleratorKey", this.focusAccelerator, this.focusAccelerator = upperCase);
    }
    
    public void setHighlighter(final Highlighter highlighter) {
        if (this.highlighter != null) {
            this.highlighter.deinstall(this);
        }
        final Highlighter highlighter2 = this.highlighter;
        this.highlighter = highlighter;
        if (this.highlighter != null) {
            this.highlighter.install(this);
        }
        this.firePropertyChange("highlighter", highlighter2, highlighter);
    }
    
    public void setKeymap(final Keymap keymap) {
        this.firePropertyChange("keymap", this.keymap, this.keymap = keymap);
    }
    
    public void setMargin(final Insets margin) {
        this.firePropertyChange("margin", this.margin, this.margin = margin);
        this.invalidate();
    }
    
    public void setOpaque(final boolean opaque) {
        this.opaque = opaque;
    }
    
    public void setSelectedTextColor(final Color selectedTextColor) {
        this.firePropertyChange("selectedTextColor", this.selectedTextColor, this.selectedTextColor = selectedTextColor);
    }
    
    public void setSelectionColor(final Color selectionColor) {
        this.firePropertyChange("selectionColor", this.selectionColor, this.selectionColor = selectionColor);
    }
    
    public void setSelectionEnd(final int n) {
        this.select(this.getSelectionStart(), n);
    }
    
    public void setSelectionStart(final int n) {
        this.select(n, this.getSelectionEnd());
    }
    
    public void setText(final String s) {
        try {
            final Document document = this.getDocument();
            document.remove(0, document.getLength());
            document.insertString(0, s, null);
        }
        catch (BadLocationException ex) {
            this.getToolkit().beep();
        }
    }
    
    public void setUI(final TextUI ui) {
        super.setUI(ui);
    }
    
    public void updateUI() {
        this.setUI((TextUI)UIManager.getUI(this));
        this.invalidate();
    }
    
    public int viewToModel(final Point point) {
        return this.getUI().viewToModel(this, point);
    }
    
    public void write(final Writer writer) throws IOException {
        final Document document = this.getDocument();
        try {
            this.getUI().getEditorKit(this).write(writer, document, 0, document.getLength());
        }
        catch (BadLocationException ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    public static class KeyBinding
    {
        public KeyStroke key;
        public String actionName;
        
        public KeyBinding(final KeyStroke key, final String actionName) {
            this.key = key;
            this.actionName = actionName;
        }
    }
    
    public class AccessibleJTextComponent extends AccessibleJComponent implements AccessibleText, CaretListener, DocumentListener
    {
        int caretPos;
        
        public AccessibleJTextComponent() {
            final Document document = JTextComponent.this.getDocument();
            if (document != null) {
                document.addDocumentListener(this);
            }
            JTextComponent.this.addCaretListener(this);
            this.caretPos = this.getCaretPosition();
        }
        
        public void caretUpdate(final CaretEvent caretEvent) {
            final int dot = caretEvent.getDot();
            final int mark = caretEvent.getMark();
            if (this.caretPos != dot) {
                this.firePropertyChange("AccessibleCaret", new Integer(this.caretPos), new Integer(dot));
                this.caretPos = dot;
            }
            if (mark != dot) {
                this.firePropertyChange("AccessibleSelection", null, this.getSelectedText());
            }
        }
        
        public void changedUpdate(final DocumentEvent documentEvent) {
            this.firePropertyChange("AccessibleText", null, new Integer(JTextComponent.this.getCaret().getDot()));
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.TEXT;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = super.getAccessibleStateSet();
            if (JTextComponent.this.isEditable()) {
                accessibleStateSet.add(AccessibleState.EDITABLE);
            }
            return accessibleStateSet;
        }
        
        public AccessibleText getAccessibleText() {
            return this;
        }
        
        public String getAfterIndex(final int n, final int n2) {
            if (n2 < 0 || n2 >= JTextComponent.this.model.getLength()) {
                return null;
            }
            switch (n) {
                case 1: {
                    if (n2 + 1 >= JTextComponent.this.model.getLength()) {
                        return null;
                    }
                    try {
                        return JTextComponent.this.model.getText(n2 + 1, 1);
                    }
                    catch (BadLocationException ex) {
                        return null;
                    }
                }
                case 2: {
                    try {
                        final String text = JTextComponent.this.model.getText(0, JTextComponent.this.model.getLength());
                        final BreakIterator wordInstance = BreakIterator.getWordInstance();
                        wordInstance.setText(text);
                        final int following = wordInstance.following(n2);
                        if (following == -1 || following >= text.length()) {
                            return null;
                        }
                        final int following2 = wordInstance.following(following);
                        if (following2 == -1 || following2 >= text.length()) {
                            return null;
                        }
                        return text.substring(following, following2);
                    }
                    catch (BadLocationException ex2) {
                        return null;
                    }
                }
                case 3: {
                    try {
                        final String text2 = JTextComponent.this.model.getText(0, JTextComponent.this.model.getLength());
                        final BreakIterator sentenceInstance = BreakIterator.getSentenceInstance();
                        sentenceInstance.setText(text2);
                        final int following3 = sentenceInstance.following(n2);
                        if (following3 == -1 || following3 >= text2.length()) {
                            return null;
                        }
                        final int following4 = sentenceInstance.following(following3);
                        if (following4 == -1 || following4 >= text2.length()) {
                            return null;
                        }
                        return text2.substring(following3, following4);
                    }
                    catch (BadLocationException ex3) {
                        return null;
                    }
                    break;
                }
            }
            return null;
        }
        
        public String getAtIndex(final int n, final int n2) {
            if (n2 < 0 || n2 >= JTextComponent.this.model.getLength()) {
                return null;
            }
            switch (n) {
                case 1: {
                    try {
                        return JTextComponent.this.model.getText(n2, 1);
                    }
                    catch (BadLocationException ex) {
                        return null;
                    }
                }
                case 2: {
                    try {
                        final String text = JTextComponent.this.model.getText(0, JTextComponent.this.model.getLength());
                        final BreakIterator wordInstance = BreakIterator.getWordInstance();
                        wordInstance.setText(text);
                        return text.substring(wordInstance.previous(), wordInstance.following(n2));
                    }
                    catch (BadLocationException ex2) {
                        return null;
                    }
                }
                case 3: {
                    try {
                        final String text2 = JTextComponent.this.model.getText(0, JTextComponent.this.model.getLength());
                        final BreakIterator sentenceInstance = BreakIterator.getSentenceInstance();
                        sentenceInstance.setText(text2);
                        return text2.substring(sentenceInstance.previous(), sentenceInstance.following(n2));
                    }
                    catch (BadLocationException ex3) {
                        return null;
                    }
                    break;
                }
            }
            return null;
        }
        
        public String getBeforeIndex(final int n, final int n2) {
            if (n2 < 0 || n2 > JTextComponent.this.model.getLength() - 1) {
                return null;
            }
            switch (n) {
                case 1: {
                    if (n2 == 0) {
                        return null;
                    }
                    try {
                        return JTextComponent.this.model.getText(n2 - 1, 1);
                    }
                    catch (BadLocationException ex) {
                        return null;
                    }
                }
                case 2: {
                    try {
                        final String text = JTextComponent.this.model.getText(0, JTextComponent.this.model.getLength());
                        final BreakIterator wordInstance = BreakIterator.getWordInstance();
                        wordInstance.setText(text);
                        wordInstance.following(n2);
                        final int previous = wordInstance.previous();
                        final int previous2 = wordInstance.previous();
                        if (previous2 == -1) {
                            return null;
                        }
                        return text.substring(previous2, previous);
                    }
                    catch (BadLocationException ex2) {
                        return null;
                    }
                }
                case 3: {
                    try {
                        final String text2 = JTextComponent.this.model.getText(0, JTextComponent.this.model.getLength());
                        final BreakIterator sentenceInstance = BreakIterator.getSentenceInstance();
                        sentenceInstance.setText(text2);
                        sentenceInstance.following(n2);
                        final int previous3 = sentenceInstance.previous();
                        final int previous4 = sentenceInstance.previous();
                        if (previous4 == -1) {
                            return null;
                        }
                        return text2.substring(previous4, previous3);
                    }
                    catch (BadLocationException ex3) {
                        return null;
                    }
                    break;
                }
            }
            return null;
        }
        
        public int getCaretPosition() {
            return JTextComponent.this.getCaretPosition();
        }
        
        public int getCharCount() {
            return JTextComponent.this.model.getLength();
        }
        
        public AttributeSet getCharacterAttribute(final int n) {
            Element element;
            for (element = JTextComponent.this.model.getDefaultRootElement(); !element.isLeaf(); element = element.getElement(element.getElementIndex(n))) {}
            return element.getAttributes();
        }
        
        public Rectangle getCharacterBounds(final int n) {
            if (n < 0 || n > JTextComponent.this.model.getLength() - 1) {
                return null;
            }
            Rectangle modelToView;
            try {
                modelToView = JTextComponent.this.modelToView(n);
            }
            catch (BadLocationException ex) {
                modelToView = null;
            }
            return modelToView;
        }
        
        public int getIndexAtPoint(final Point point) {
            if (point == null) {
                return -1;
            }
            return JTextComponent.this.viewToModel(point);
        }
        
        public String getSelectedText() {
            return JTextComponent.this.getSelectedText();
        }
        
        public int getSelectionEnd() {
            return JTextComponent.this.getSelectionEnd();
        }
        
        public int getSelectionStart() {
            return JTextComponent.this.getSelectionStart();
        }
        
        public void insertUpdate(final DocumentEvent documentEvent) {
            this.firePropertyChange("AccessibleText", null, new Integer(JTextComponent.this.getCaret().getDot()));
        }
        
        public void removeUpdate(final DocumentEvent documentEvent) {
            this.firePropertyChange("AccessibleText", null, new Integer(JTextComponent.this.getCaret().getDot()));
        }
    }
    
    static class ClipboardObserver implements ClipboardOwner
    {
        public void lostOwnership(final Clipboard clipboard, final Transferable transferable) {
        }
    }
    
    static class DefaultKeymap implements Keymap
    {
        String nm;
        Keymap parent;
        Hashtable bindings;
        Action defaultAction;
        
        DefaultKeymap(final String nm, final Keymap parent) {
            this.nm = nm;
            this.parent = parent;
            this.bindings = new Hashtable();
        }
        
        public void addActionForKeyStroke(final KeyStroke keyStroke, final Action action) {
            this.bindings.put(keyStroke, action);
        }
        
        public Action getAction(final KeyStroke keyStroke) {
            Action action = this.bindings.get(keyStroke);
            if (action == null && this.parent != null) {
                action = this.parent.getAction(keyStroke);
            }
            return action;
        }
        
        public Action[] getBoundActions() {
            final Action[] array = new Action[this.bindings.size()];
            int n = 0;
            final Enumeration<Action> elements = this.bindings.elements();
            while (elements.hasMoreElements()) {
                array[n++] = elements.nextElement();
            }
            return array;
        }
        
        public KeyStroke[] getBoundKeyStrokes() {
            final KeyStroke[] array = new KeyStroke[this.bindings.size()];
            int n = 0;
            final Enumeration<KeyStroke> keys = this.bindings.keys();
            while (keys.hasMoreElements()) {
                array[n++] = keys.nextElement();
            }
            return array;
        }
        
        public Action getDefaultAction() {
            if (this.defaultAction != null) {
                return this.defaultAction;
            }
            return (this.parent != null) ? this.parent.getDefaultAction() : null;
        }
        
        public KeyStroke[] getKeyStrokesForAction(final Action action) {
            if (action == null) {
                return null;
            }
            Object[] array = null;
            Vector vector = null;
            final Enumeration<Object> keys = this.bindings.keys();
            while (keys.hasMoreElements()) {
                final Object nextElement = keys.nextElement();
                if (this.bindings.get(nextElement) == action) {
                    if (vector == null) {
                        vector = new Vector<KeyStroke>();
                    }
                    vector.addElement(nextElement);
                }
            }
            if (this.parent != null) {
                final KeyStroke[] keyStrokesForAction = this.parent.getKeyStrokesForAction(action);
                if (keyStrokesForAction != null) {
                    int n = 0;
                    for (int i = keyStrokesForAction.length - 1; i >= 0; --i) {
                        if (this.isLocallyDefined(keyStrokesForAction[i])) {
                            keyStrokesForAction[i] = null;
                            ++n;
                        }
                    }
                    if (n > 0 && n < keyStrokesForAction.length) {
                        if (vector == null) {
                            vector = new Vector<Object>();
                        }
                        for (int j = keyStrokesForAction.length - 1; j >= 0; --j) {
                            if (keyStrokesForAction[j] != null) {
                                vector.addElement(keyStrokesForAction[j]);
                            }
                        }
                    }
                    else if (n == 0) {
                        if (vector == null) {
                            array = keyStrokesForAction;
                        }
                        else {
                            array = new KeyStroke[vector.size() + keyStrokesForAction.length];
                            vector.copyInto(array);
                            System.arraycopy(keyStrokesForAction, 0, array, vector.size(), keyStrokesForAction.length);
                            vector = null;
                        }
                    }
                }
            }
            if (vector != null) {
                array = new KeyStroke[vector.size()];
                vector.copyInto(array);
            }
            return (KeyStroke[])array;
        }
        
        public String getName() {
            return this.nm;
        }
        
        public Keymap getResolveParent() {
            return this.parent;
        }
        
        public boolean isLocallyDefined(final KeyStroke keyStroke) {
            return this.bindings.containsKey(keyStroke);
        }
        
        public void removeBindings() {
            this.bindings.clear();
        }
        
        public void removeKeyStrokeBinding(final KeyStroke keyStroke) {
            this.bindings.remove(keyStroke);
        }
        
        public void setDefaultAction(final Action defaultAction) {
            this.defaultAction = defaultAction;
        }
        
        public void setResolveParent(final Keymap parent) {
            this.parent = parent;
        }
        
        public String toString() {
            return "Keymap[" + this.nm + "]" + this.bindings;
        }
    }
    
    static class MutableCaretEvent extends CaretEvent implements ChangeListener, MouseListener, FocusListener
    {
        private boolean dragActive;
        private int dot;
        private int mark;
        
        MutableCaretEvent(final JTextComponent textComponent) {
            super(textComponent);
        }
        
        final void fire() {
            final JTextComponent textComponent = (JTextComponent)this.getSource();
            if (textComponent != null) {
                final Caret caret = textComponent.getCaret();
                this.dot = caret.getDot();
                this.mark = caret.getMark();
                textComponent.fireCaretUpdate(this);
            }
        }
        
        public void focusGained(final FocusEvent focusEvent) {
            JTextComponent.access$2((JTextComponent)this.getSource());
        }
        
        public void focusLost(final FocusEvent focusEvent) {
        }
        
        public final int getDot() {
            return this.dot;
        }
        
        public final int getMark() {
            return this.mark;
        }
        
        public final void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public final void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public final void mouseExited(final MouseEvent mouseEvent) {
        }
        
        public final void mousePressed(final MouseEvent mouseEvent) {
            this.dragActive = true;
        }
        
        public final void mouseReleased(final MouseEvent mouseEvent) {
            this.dragActive = false;
            this.fire();
        }
        
        public final void stateChanged(final ChangeEvent changeEvent) {
            if (!this.dragActive) {
                this.fire();
            }
        }
        
        public final String toString() {
            return "dot=" + this.dot + "," + "mark=" + this.mark;
        }
    }
    
    class FocusAction extends AbstractAction
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            JTextComponent.this.requestFocus();
        }
        
        public boolean isEnabled() {
            return JTextComponent.this.isEditable();
        }
    }
}
