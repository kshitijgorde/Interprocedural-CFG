// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import javax.swing.Action;

public class DefaultEditorKit extends EditorKit
{
    public static final String EndOfLineStringProperty = "__EndOfLine__";
    public static final String insertContentAction = "insert-content";
    public static final String insertBreakAction = "insert-break";
    public static final String insertTabAction = "insert-tab";
    public static final String deletePrevCharAction = "delete-previous";
    public static final String deleteNextCharAction = "delete-next";
    public static final String readOnlyAction = "set-read-only";
    public static final String writableAction = "set-writable";
    public static final String cutAction = "cut-to-clipboard";
    public static final String copyAction = "copy-to-clipboard";
    public static final String pasteAction = "paste-from-clipboard";
    public static final String beepAction = "beep";
    public static final String pageUpAction = "page-up";
    public static final String pageDownAction = "page-down";
    static final String selectionPageUpAction = "selection-page-up";
    static final String selectionPageDownAction = "selection-page-down";
    public static final String forwardAction = "caret-forward";
    public static final String backwardAction = "caret-backward";
    public static final String selectionForwardAction = "selection-forward";
    public static final String selectionBackwardAction = "selection-backward";
    public static final String upAction = "caret-up";
    public static final String downAction = "caret-down";
    public static final String selectionUpAction = "selection-up";
    public static final String selectionDownAction = "selection-down";
    public static final String beginWordAction = "caret-begin-word";
    public static final String endWordAction = "caret-end-word";
    public static final String selectionBeginWordAction = "selection-begin-word";
    public static final String selectionEndWordAction = "selection-end-word";
    public static final String previousWordAction = "caret-previous-word";
    public static final String nextWordAction = "caret-next-word";
    public static final String selectionPreviousWordAction = "selection-previous-word";
    public static final String selectionNextWordAction = "selection-next-word";
    public static final String beginLineAction = "caret-begin-line";
    public static final String endLineAction = "caret-end-line";
    public static final String selectionBeginLineAction = "selection-begin-line";
    public static final String selectionEndLineAction = "selection-end-line";
    public static final String beginParagraphAction = "caret-begin-paragraph";
    public static final String endParagraphAction = "caret-end-paragraph";
    public static final String selectionBeginParagraphAction = "selection-begin-paragraph";
    public static final String selectionEndParagraphAction = "selection-end-paragraph";
    public static final String beginAction = "caret-begin";
    public static final String endAction = "caret-end";
    public static final String selectionBeginAction = "selection-begin";
    public static final String selectionEndAction = "selection-end";
    public static final String selectWordAction = "select-word";
    public static final String selectLineAction = "select-line";
    public static final String selectParagraphAction = "select-paragraph";
    public static final String selectAllAction = "select-all";
    static final String unselectAction = "unselect";
    public static final String defaultKeyTypedAction = "default-typed";
    private static final Action[] defaultActions;
    
    static {
        defaultActions = new Action[] { new InsertContentAction(), new DeletePrevCharAction(), new DeleteNextCharAction(), new ReadOnlyAction(), new WritableAction(), new CutAction(), new CopyAction(), new PasteAction(), new PageUpAction("page-up", false), new PageDownAction("page-down", false), new PageUpAction("selection-page-up", true), new PageDownAction("selection-page-down", true), new InsertBreakAction(), new BeepAction(), new NextVisualPositionAction("caret-forward", false, 3), new NextVisualPositionAction("caret-backward", false, 7), new NextVisualPositionAction("selection-forward", true, 3), new NextVisualPositionAction("selection-backward", true, 7), new NextVisualPositionAction("caret-up", false, 1), new NextVisualPositionAction("caret-down", false, 5), new NextVisualPositionAction("selection-up", true, 1), new NextVisualPositionAction("selection-down", true, 5), new BeginWordAction("caret-begin-word", false), new EndWordAction("caret-end-word", false), new BeginWordAction("selection-begin-word", true), new EndWordAction("selection-end-word", true), new PreviousWordAction("caret-previous-word", false), new NextWordAction("caret-next-word", false), new PreviousWordAction("selection-previous-word", true), new NextWordAction("selection-next-word", true), new BeginLineAction("caret-begin-line", false), new EndLineAction("caret-end-line", false), new BeginLineAction("selection-begin-line", true), new EndLineAction("selection-end-line", true), new BeginParagraphAction("caret-begin-paragraph", false), new EndParagraphAction("caret-end-paragraph", false), new BeginParagraphAction("selection-begin-paragraph", true), new EndParagraphAction("selection-end-paragraph", true), new BeginAction("caret-begin", false), new EndAction("caret-end", false), new BeginAction("selection-begin", true), new EndAction("selection-end", true), new DefaultKeyTypedAction(), new InsertTabAction(), new SelectWordAction(), new SelectLineAction(), new SelectParagraphAction(), new SelectAllAction(), new UnselectAction(), new DumpModelAction() };
    }
    
    public Object clone() {
        return new DefaultEditorKit();
    }
    
    public Caret createCaret() {
        return null;
    }
    
    public Document createDefaultDocument() {
        return new PlainDocument();
    }
    
    public Action[] getActions() {
        return DefaultEditorKit.defaultActions;
    }
    
    public String getContentType() {
        return "text/plain";
    }
    
    public ViewFactory getViewFactory() {
        return null;
    }
    
    public void read(final InputStream inputStream, final Document document, final int n) throws IOException, BadLocationException {
        this.read(new InputStreamReader(inputStream), document, n);
    }
    
    public void read(final Reader reader, final Document document, int n) throws IOException, BadLocationException {
        final char[] array = new char[4096];
        int n2 = 0;
        boolean b = false;
        boolean b2 = false;
        final boolean b3 = document.getLength() == 0;
        int read;
        while ((read = reader.read(array, 0, array.length)) != -1) {
            int n3 = 0;
            for (int i = 0; i < read; ++i) {
                switch (array[i]) {
                    case '\r': {
                        if (n2 == 0) {
                            n2 = 1;
                            break;
                        }
                        b2 = true;
                        if (i == 0) {
                            document.insertString(n, "\n", null);
                            ++n;
                            break;
                        }
                        array[i - 1] = '\n';
                        break;
                    }
                    case '\n': {
                        if (n2 != 0) {
                            if (i > n3 + 1) {
                                document.insertString(n, new String(array, n3, i - n3 - 1), null);
                                n += i - n3 - 1;
                            }
                            n2 = 0;
                            n3 = i;
                            b = true;
                            break;
                        }
                        break;
                    }
                    default: {
                        if (n2 != 0) {
                            b2 = true;
                            if (i == 0) {
                                document.insertString(n, "\n", null);
                                ++n;
                            }
                            else {
                                array[i - 1] = '\n';
                            }
                            n2 = 0;
                            break;
                        }
                        break;
                    }
                }
            }
            if (n3 < read) {
                if (n2 != 0) {
                    if (n3 >= read - 1) {
                        continue;
                    }
                    document.insertString(n, new String(array, n3, read - n3 - 1), null);
                    n += read - n3 - 1;
                }
                else {
                    document.insertString(n, new String(array, n3, read - n3), null);
                    n += read - n3;
                }
            }
        }
        if (n2 != 0) {
            document.insertString(n, "\n", null);
            b2 = true;
        }
        if (b3) {
            if (b) {
                document.putProperty("__EndOfLine__", "\r\n");
            }
            else if (b2) {
                document.putProperty("__EndOfLine__", "\r");
            }
            else {
                document.putProperty("__EndOfLine__", "\n");
            }
        }
    }
    
    public void write(final OutputStream outputStream, final Document document, final int n, final int n2) throws IOException, BadLocationException {
        this.write(new OutputStreamWriter(outputStream), document, n, n2);
    }
    
    public void write(final Writer writer, final Document document, final int n, final int n2) throws IOException, BadLocationException {
        if (n < 0 || n + n2 > document.getLength()) {
            throw new BadLocationException("DefaultEditorKit.write", n);
        }
        final Segment segment = new Segment();
        int i = n2;
        int n3 = n;
        Object o = document.getProperty("__EndOfLine__");
        if (o == null) {
            try {
                o = System.getProperty("line.separator");
            }
            catch (SecurityException ex) {}
        }
        String s;
        if (o instanceof String) {
            s = (String)o;
        }
        else {
            s = null;
        }
        if (o != null && !s.equals("\n")) {
            while (i > 0) {
                final int min = Math.min(i, 4096);
                document.getText(n3, min, segment);
                int offset = segment.offset;
                final char[] array = segment.array;
                final int n4 = offset + segment.count;
                for (int j = offset; j < n4; ++j) {
                    if (array[j] == '\n') {
                        if (j > offset) {
                            writer.write(array, offset, j - offset);
                        }
                        writer.write(s);
                        offset = j + 1;
                    }
                }
                if (n4 > offset) {
                    writer.write(array, offset, n4 - offset);
                }
                n3 += min;
                i -= min;
            }
        }
        else {
            while (i > 0) {
                final int min2 = Math.min(i, 4096);
                document.getText(n3, min2, segment);
                writer.write(segment.array, segment.offset, segment.count);
                n3 += min2;
                i -= min2;
            }
        }
        writer.flush();
    }
    
    public static class DefaultKeyTypedAction extends TextAction
    {
        public DefaultKeyTypedAction() {
            super("default-typed");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null && actionEvent != null) {
                if (!textComponent.isEditable() || !textComponent.isEnabled()) {
                    textComponent.getToolkit().beep();
                    return;
                }
                final String actionCommand = actionEvent.getActionCommand();
                final int modifiers = actionEvent.getModifiers();
                if (actionCommand != null && actionCommand.length() > 0 && (modifiers & 0x8) == 0x0) {
                    final char char1 = actionCommand.charAt(0);
                    if (char1 >= ' ' && char1 != '\u007f') {
                        textComponent.replaceSelection(actionCommand);
                    }
                }
            }
        }
    }
    
    public static class InsertContentAction extends TextAction
    {
        public InsertContentAction() {
            super("insert-content");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null && actionEvent != null) {
                if (!textComponent.isEditable() || !textComponent.isEnabled()) {
                    textComponent.getToolkit().beep();
                    return;
                }
                final String actionCommand = actionEvent.getActionCommand();
                if (actionCommand != null) {
                    textComponent.replaceSelection(actionCommand);
                }
                else {
                    textComponent.getToolkit().beep();
                }
            }
        }
    }
    
    public static class InsertBreakAction extends TextAction
    {
        public InsertBreakAction() {
            super("insert-break");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                if (!textComponent.isEditable() || !textComponent.isEnabled()) {
                    textComponent.getToolkit().beep();
                    return;
                }
                textComponent.replaceSelection("\n");
            }
        }
    }
    
    public static class InsertTabAction extends TextAction
    {
        public InsertTabAction() {
            super("insert-tab");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                if (!textComponent.isEditable() || !textComponent.isEnabled()) {
                    textComponent.getToolkit().beep();
                    return;
                }
                textComponent.replaceSelection("\t");
            }
        }
    }
    
    static class DeletePrevCharAction extends TextAction
    {
        DeletePrevCharAction() {
            super("delete-previous");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            boolean b = true;
            if (textComponent != null && textComponent.isEditable()) {
                try {
                    final Document document = textComponent.getDocument();
                    final Caret caret = textComponent.getCaret();
                    final int dot = caret.getDot();
                    final int mark = caret.getMark();
                    if (dot != mark) {
                        document.remove(Math.min(dot, mark), Math.abs(dot - mark));
                        b = false;
                    }
                    else if (dot > 0) {
                        document.remove(dot - 1, 1);
                        b = false;
                    }
                }
                catch (BadLocationException ex) {}
            }
            if (b) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
    
    static class DeleteNextCharAction extends TextAction
    {
        DeleteNextCharAction() {
            super("delete-next");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            boolean b = true;
            if (textComponent != null && textComponent.isEditable()) {
                try {
                    final Document document = textComponent.getDocument();
                    final Caret caret = textComponent.getCaret();
                    final int dot = caret.getDot();
                    final int mark = caret.getMark();
                    if (dot != mark) {
                        document.remove(Math.min(dot, mark), Math.abs(dot - mark));
                        b = false;
                    }
                    else if (dot < document.getLength()) {
                        document.remove(dot, 1);
                        b = false;
                    }
                }
                catch (BadLocationException ex) {}
            }
            if (b) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
    
    static class ReadOnlyAction extends TextAction
    {
        ReadOnlyAction() {
            super("set-read-only");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                textComponent.setEditable(false);
            }
        }
    }
    
    static class WritableAction extends TextAction
    {
        WritableAction() {
            super("set-writable");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                textComponent.setEditable(true);
            }
        }
    }
    
    public static class CutAction extends TextAction
    {
        public CutAction() {
            super("cut-to-clipboard");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                textComponent.cut();
            }
        }
    }
    
    public static class CopyAction extends TextAction
    {
        public CopyAction() {
            super("copy-to-clipboard");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                textComponent.copy();
            }
        }
    }
    
    public static class PasteAction extends TextAction
    {
        public PasteAction() {
            super("paste-from-clipboard");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                textComponent.paste();
            }
        }
    }
    
    public static class BeepAction extends TextAction
    {
        public BeepAction() {
            super("beep");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            Toolkit.getDefaultToolkit().beep();
        }
    }
    
    static class PageUpAction extends TextAction
    {
        private boolean select;
        
        public PageUpAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                final Rectangle rectangle = new Rectangle();
                textComponent.computeVisibleRect(rectangle);
                final int y = rectangle.y;
                final Rectangle rectangle2 = rectangle;
                rectangle2.y -= rectangle.height;
                if (rectangle.y < 0) {
                    rectangle.y = 0;
                }
                final int n = y - rectangle.y;
                textComponent.scrollRectToVisible(rectangle);
                final int caretPosition = textComponent.getCaretPosition();
                try {
                    if (caretPosition != -1) {
                        final Rectangle modelToView;
                        final Rectangle rectangle3 = modelToView = textComponent.modelToView(caretPosition);
                        modelToView.y -= n;
                        int viewToModel = textComponent.viewToModel(new Point(rectangle3.x, rectangle3.y));
                        final Document document = textComponent.getDocument();
                        if (viewToModel != 0 && viewToModel > document.getLength() - 1) {
                            viewToModel = document.getLength() - 1;
                        }
                        if (viewToModel < 0) {
                            viewToModel = 0;
                        }
                        if (this.select) {
                            textComponent.moveCaretPosition(viewToModel);
                        }
                        else {
                            textComponent.setCaretPosition(viewToModel);
                        }
                    }
                }
                catch (BadLocationException ex) {
                    textComponent.getToolkit().beep();
                }
            }
        }
    }
    
    static class PageDownAction extends TextAction
    {
        private boolean select;
        
        PageDownAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                final Rectangle rectangle = new Rectangle();
                textComponent.computeVisibleRect(rectangle);
                final int y = rectangle.y;
                final Rectangle rectangle2 = rectangle;
                rectangle2.y += rectangle.height;
                if (rectangle.y + rectangle.height > textComponent.getHeight()) {
                    rectangle.y = textComponent.getHeight() - rectangle.height;
                }
                final int n = rectangle.y - y;
                textComponent.scrollRectToVisible(rectangle);
                final int caretPosition = textComponent.getCaretPosition();
                try {
                    if (caretPosition != -1) {
                        final Rectangle modelToView;
                        final Rectangle rectangle3 = modelToView = textComponent.modelToView(caretPosition);
                        modelToView.y += n;
                        int viewToModel = textComponent.viewToModel(new Point(rectangle3.x, rectangle3.y));
                        final Document document = textComponent.getDocument();
                        if (viewToModel != 0 && viewToModel > document.getLength() - 1) {
                            viewToModel = document.getLength() - 1;
                        }
                        if (viewToModel < 0) {
                            viewToModel = 0;
                        }
                        if (this.select) {
                            textComponent.moveCaretPosition(viewToModel);
                        }
                        else {
                            textComponent.setCaretPosition(viewToModel);
                        }
                    }
                }
                catch (BadLocationException ex) {
                    textComponent.getToolkit().beep();
                }
            }
        }
    }
    
    static class DumpModelAction extends TextAction
    {
        DumpModelAction() {
            super("dump-model");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                final Document document = textComponent.getDocument();
                if (document instanceof AbstractDocument) {
                    ((AbstractDocument)document).dump(System.err);
                }
            }
        }
    }
    
    static class NextVisualPositionAction extends TextAction
    {
        private boolean select;
        private int direction;
        
        NextVisualPositionAction(final String s, final boolean select, final int direction) {
            super(s);
            this.select = select;
            this.direction = direction;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                final Caret caret = textComponent.getCaret();
                final DefaultCaret defaultCaret = (caret instanceof DefaultCaret) ? ((DefaultCaret)caret) : null;
                final int dot = caret.getDot();
                final Position.Bias[] array = { null };
                try {
                    if (caret != null && (this.direction == 1 || this.direction == 5) && caret.getMagicCaretPosition() == null) {
                        final Rectangle rectangle = (defaultCaret != null) ? textComponent.getUI().modelToView(textComponent, dot, defaultCaret.getDotBias()) : textComponent.modelToView(dot);
                        caret.setMagicCaretPosition(new Point(rectangle.x, rectangle.y));
                    }
                    final int nextVisualPosition = textComponent.getUI().getNextVisualPositionFrom(textComponent, dot, (Position.Bias)((defaultCaret != null) ? defaultCaret.getDotBias() : Position.Bias.Forward), this.direction, array);
                    if (array[0] == null) {
                        array[0] = Position.Bias.Forward;
                    }
                    if (defaultCaret != null) {
                        if (this.select) {
                            defaultCaret.moveDot(nextVisualPosition, array[0]);
                        }
                        else {
                            defaultCaret.setDot(nextVisualPosition, array[0]);
                        }
                    }
                    else if (this.select) {
                        caret.moveDot(nextVisualPosition);
                    }
                    else {
                        caret.setDot(nextVisualPosition);
                    }
                    if (this.direction == 3 || this.direction == 7) {
                        textComponent.getCaret().setMagicCaretPosition(null);
                    }
                }
                catch (BadLocationException ex) {}
            }
        }
    }
    
    static class BeginWordAction extends TextAction
    {
        private boolean select;
        
        BeginWordAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                try {
                    final int wordStart = Utilities.getWordStart(textComponent, textComponent.getCaretPosition());
                    if (this.select) {
                        textComponent.moveCaretPosition(wordStart);
                    }
                    else {
                        textComponent.setCaretPosition(wordStart);
                    }
                }
                catch (BadLocationException ex) {
                    textComponent.getToolkit().beep();
                }
            }
        }
    }
    
    static class EndWordAction extends TextAction
    {
        private boolean select;
        
        EndWordAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                try {
                    final int wordEnd = Utilities.getWordEnd(textComponent, textComponent.getCaretPosition());
                    if (this.select) {
                        textComponent.moveCaretPosition(wordEnd);
                    }
                    else {
                        textComponent.setCaretPosition(wordEnd);
                    }
                }
                catch (BadLocationException ex) {
                    textComponent.getToolkit().beep();
                }
            }
        }
    }
    
    static class PreviousWordAction extends TextAction
    {
        private boolean select;
        
        PreviousWordAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                try {
                    final int previousWord = Utilities.getPreviousWord(textComponent, textComponent.getCaretPosition());
                    if (this.select) {
                        textComponent.moveCaretPosition(previousWord);
                    }
                    else {
                        textComponent.setCaretPosition(previousWord);
                    }
                }
                catch (BadLocationException ex) {
                    textComponent.getToolkit().beep();
                }
            }
        }
    }
    
    static class NextWordAction extends TextAction
    {
        private boolean select;
        
        NextWordAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                try {
                    final int nextWord = Utilities.getNextWord(textComponent, textComponent.getCaretPosition());
                    if (this.select) {
                        textComponent.moveCaretPosition(nextWord);
                    }
                    else {
                        textComponent.setCaretPosition(nextWord);
                    }
                }
                catch (BadLocationException ex) {
                    textComponent.getToolkit().beep();
                }
            }
        }
    }
    
    static class BeginLineAction extends TextAction
    {
        private boolean select;
        
        BeginLineAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                try {
                    final int rowStart = Utilities.getRowStart(textComponent, textComponent.getCaretPosition());
                    if (this.select) {
                        textComponent.moveCaretPosition(rowStart);
                    }
                    else {
                        textComponent.setCaretPosition(rowStart);
                    }
                    textComponent.getCaret().setMagicCaretPosition(null);
                }
                catch (BadLocationException ex) {
                    textComponent.getToolkit().beep();
                }
            }
        }
    }
    
    static class EndLineAction extends TextAction
    {
        private boolean select;
        
        EndLineAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                try {
                    final int rowEnd = Utilities.getRowEnd(textComponent, textComponent.getCaretPosition());
                    if (this.select) {
                        textComponent.moveCaretPosition(rowEnd);
                    }
                    else {
                        textComponent.setCaretPosition(rowEnd);
                    }
                    textComponent.getCaret().setMagicCaretPosition(null);
                }
                catch (BadLocationException ex) {
                    textComponent.getToolkit().beep();
                }
            }
        }
    }
    
    static class BeginParagraphAction extends TextAction
    {
        private boolean select;
        
        BeginParagraphAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                final int startOffset = Utilities.getParagraphElement(textComponent, textComponent.getCaretPosition()).getStartOffset();
                if (this.select) {
                    textComponent.moveCaretPosition(startOffset);
                }
                else {
                    textComponent.setCaretPosition(startOffset);
                }
            }
        }
    }
    
    static class EndParagraphAction extends TextAction
    {
        private boolean select;
        
        EndParagraphAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                final int endOffset = Utilities.getParagraphElement(textComponent, textComponent.getCaretPosition()).getEndOffset();
                if (this.select) {
                    textComponent.moveCaretPosition(endOffset);
                }
                else {
                    textComponent.setCaretPosition(endOffset);
                }
            }
        }
    }
    
    static class BeginAction extends TextAction
    {
        private boolean select;
        
        BeginAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                if (this.select) {
                    textComponent.moveCaretPosition(0);
                }
                else {
                    textComponent.setCaretPosition(0);
                }
            }
        }
    }
    
    static class EndAction extends TextAction
    {
        private boolean select;
        
        EndAction(final String s, final boolean select) {
            super(s);
            this.select = select;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                final int length = textComponent.getDocument().getLength();
                if (this.select) {
                    textComponent.moveCaretPosition(length);
                }
                else {
                    textComponent.setCaretPosition(length);
                }
            }
        }
    }
    
    static class SelectWordAction extends TextAction
    {
        private Action start;
        private Action end;
        
        SelectWordAction() {
            super("select-word");
            this.start = new BeginWordAction("pigdog", false);
            this.end = new EndWordAction("pigdog", true);
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.start.actionPerformed(actionEvent);
            this.end.actionPerformed(actionEvent);
        }
    }
    
    static class SelectLineAction extends TextAction
    {
        private Action start;
        private Action end;
        
        SelectLineAction() {
            super("select-line");
            this.start = new BeginLineAction("pigdog", false);
            this.end = new EndLineAction("pigdog", true);
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.start.actionPerformed(actionEvent);
            this.end.actionPerformed(actionEvent);
        }
    }
    
    static class SelectParagraphAction extends TextAction
    {
        private Action start;
        private Action end;
        
        SelectParagraphAction() {
            super("select-paragraph");
            this.start = new BeginParagraphAction("pigdog", false);
            this.end = new EndParagraphAction("pigdog", true);
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.start.actionPerformed(actionEvent);
            this.end.actionPerformed(actionEvent);
        }
    }
    
    static class SelectAllAction extends TextAction
    {
        SelectAllAction() {
            super("select-all");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                final Document document = textComponent.getDocument();
                textComponent.setCaretPosition(0);
                textComponent.moveCaretPosition(document.getLength());
            }
        }
    }
    
    static class UnselectAction extends TextAction
    {
        UnselectAction() {
            super("unselect");
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            final JTextComponent textComponent = this.getTextComponent(actionEvent);
            if (textComponent != null) {
                textComponent.setCaretPosition(textComponent.getCaretPosition());
            }
        }
    }
}
