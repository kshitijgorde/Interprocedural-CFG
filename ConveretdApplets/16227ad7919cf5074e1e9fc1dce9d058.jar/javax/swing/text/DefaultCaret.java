// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.beans.PropertyChangeEvent;
import javax.swing.event.DocumentEvent;
import java.io.ObjectOutputStream;
import java.awt.event.ActionListener;
import javax.swing.plaf.TextUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import javax.swing.event.DocumentListener;
import java.beans.PropertyChangeListener;
import javax.swing.SwingUtilities;
import java.util.EventListener;
import javax.swing.event.ChangeListener;
import java.awt.Point;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.FocusListener;
import java.awt.Rectangle;

public class DefaultCaret extends Rectangle implements Caret, FocusListener, MouseListener, MouseMotionListener
{
    protected EventListenerList listenerList;
    protected transient ChangeEvent changeEvent;
    JTextComponent component;
    boolean async;
    boolean visible;
    int dot;
    int mark;
    Object selectionTag;
    Timer flasher;
    Point magicCaretPosition;
    transient Position.Bias dotBias;
    transient Position.Bias markBias;
    boolean dotLTR;
    boolean markLTR;
    transient UpdateHandler updateHandler;
    private transient int[] flagXPoints;
    private transient int[] flagYPoints;
    private transient FocusListener focusListener;
    static /* synthetic */ Class class$javax$swing$event$ChangeListener;
    
    public DefaultCaret() {
        this.listenerList = new EventListenerList();
        this.changeEvent = null;
        this.updateHandler = new UpdateHandler();
        this.flagXPoints = new int[3];
        this.flagYPoints = new int[3];
        this.async = false;
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList.add((DefaultCaret.class$javax$swing$event$ChangeListener != null) ? DefaultCaret.class$javax$swing$event$ChangeListener : (DefaultCaret.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    protected void adjustVisibility(final Rectangle rectangle) {
        SwingUtilities.invokeLater(new SafeScroller(rectangle));
    }
    
    void changeCaretPosition(final int dot, final Position.Bias dotBias) {
        this.repaint();
        if (this.flasher != null && this.flasher.isRunning()) {
            this.visible = true;
            this.flasher.restart();
        }
        this.dot = dot;
        this.dotBias = dotBias;
        this.dotLTR = this.isPositionLTR(dot, dotBias);
        this.fireStateChanged();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DefaultCaret.this.repaintNewCaret();
            }
        });
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected synchronized void damage(final Rectangle rectangle) {
        if (rectangle != null) {
            super.x = rectangle.x - 4;
            super.y = rectangle.y;
            super.width = 10;
            super.height = rectangle.height;
            this.repaint();
        }
    }
    
    public void deinstall(final JTextComponent textComponent) {
        textComponent.removeMouseListener(this);
        textComponent.removeMouseMotionListener(this);
        if (this.focusListener != null) {
            textComponent.removeFocusListener(this.focusListener);
            this.focusListener = null;
        }
        textComponent.removePropertyChangeListener(this.updateHandler);
        final Document document = textComponent.getDocument();
        if (document != null) {
            document.removeDocumentListener(this.updateHandler);
        }
        synchronized (this) {
            this.component = null;
        }
        if (this.flasher != null) {
            this.flasher.stop();
        }
    }
    
    public boolean equals(final Object o) {
        return this == o;
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((DefaultCaret.class$javax$swing$event$ChangeListener != null) ? DefaultCaret.class$javax$swing$event$ChangeListener : (DefaultCaret.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")))) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (this.component.isEditable()) {
            this.setVisible(true);
            this.setSelectionVisible(true);
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.setVisible(false);
        this.setSelectionVisible(false);
    }
    
    boolean getAsynchronousMovement() {
        return this.async;
    }
    
    public int getBlinkRate() {
        return (this.flasher == null) ? 0 : this.flasher.getDelay();
    }
    
    protected final JTextComponent getComponent() {
        return this.component;
    }
    
    public int getDot() {
        return this.dot;
    }
    
    Position.Bias getDotBias() {
        return this.dotBias;
    }
    
    public Point getMagicCaretPosition() {
        return this.magicCaretPosition;
    }
    
    public int getMark() {
        return this.mark;
    }
    
    Position.Bias getMarkBias() {
        return this.markBias;
    }
    
    protected Highlighter.HighlightPainter getSelectionPainter() {
        return DefaultHighlighter.DefaultPainter;
    }
    
    Position.Bias guessBiasForOffset(final int n, Position.Bias bias, final boolean b) {
        if (b != this.isPositionLTR(n, bias)) {
            bias = Position.Bias.Backward;
        }
        else if (bias != Position.Bias.Backward && b != this.isPositionLTR(n, Position.Bias.Backward)) {
            bias = Position.Bias.Backward;
        }
        if (bias == Position.Bias.Backward && n > 0) {
            try {
                final String text = this.component.getDocument().getText(n - 1, 1);
                if (text.length() > 0 && text.charAt(0) == '\n') {
                    bias = Position.Bias.Forward;
                }
            }
            catch (BadLocationException ex) {}
        }
        return bias;
    }
    
    public void install(final JTextComponent component) {
        this.component = component;
        final Document document = component.getDocument();
        final boolean b = false;
        this.mark = (b ? 1 : 0);
        this.dot = (b ? 1 : 0);
        final boolean b2 = true;
        this.markLTR = b2;
        this.dotLTR = b2;
        final Position.Bias forward = Position.Bias.Forward;
        this.markBias = forward;
        this.dotBias = forward;
        if (document != null) {
            document.addDocumentListener(this.updateHandler);
        }
        component.addPropertyChangeListener(this.updateHandler);
        component.addFocusListener(this.focusListener = new FocusHandler(this));
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        if (this.component.hasFocus()) {
            this.focusGained(null);
        }
    }
    
    boolean isDotLeftToRight() {
        return this.dotLTR;
    }
    
    boolean isMarkLeftToRight() {
        return this.markLTR;
    }
    
    boolean isPositionLTR(int n, final Position.Bias bias) {
        final Document document = this.component.getDocument();
        if (document instanceof AbstractDocument) {
            if (bias == Position.Bias.Backward && --n < 0) {
                n = 0;
            }
            return ((AbstractDocument)document).isLeftToRight(n, n);
        }
        return true;
    }
    
    public boolean isSelectionVisible() {
        return this.selectionTag != null;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            if (mouseEvent.getClickCount() == 2) {
                new DefaultEditorKit.SelectWordAction().actionPerformed(null);
            }
            else if (mouseEvent.getClickCount() == 3) {
                new DefaultEditorKit.SelectLineAction().actionPerformed(null);
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            this.moveCaret(mouseEvent);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
            this.positionCaret(mouseEvent);
            if (this.component != null && this.component.isEnabled()) {
                this.component.requestFocus();
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    protected void moveCaret(final MouseEvent mouseEvent) {
        final Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
        final Position.Bias[] array = { null };
        final int viewToModel = this.component.getUI().viewToModel(this.component, point, array);
        if (array[0] == null) {
            array[0] = Position.Bias.Forward;
        }
        if (viewToModel >= 0) {
            this.moveDot(viewToModel, array[0]);
        }
    }
    
    public void moveDot(final int n) {
        this.moveDot(n, Position.Bias.Forward);
    }
    
    void moveDot(final int n, final Position.Bias bias) {
        if (!this.component.isEnabled()) {
            this.setDot(n, bias);
            return;
        }
        if (n != this.dot) {
            this.changeCaretPosition(n, bias);
            final Highlighter highlighter = this.component.getHighlighter();
            if (highlighter != null) {
                final int min = Math.min(n, this.mark);
                final int max = Math.max(n, this.mark);
                try {
                    if (this.selectionTag != null) {
                        highlighter.changeHighlight(this.selectionTag, min, max);
                    }
                    else {
                        this.selectionTag = highlighter.addHighlight(min, max, this.getSelectionPainter());
                    }
                }
                catch (BadLocationException ex) {
                    throw new StateInvariantError("Bad caret position");
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.isVisible()) {
            try {
                final Rectangle modelToView = this.component.getUI().modelToView(this.component, this.dot, this.dotBias);
                graphics.setColor(this.component.getCaretColor());
                graphics.drawLine(modelToView.x, modelToView.y, modelToView.x, modelToView.y + modelToView.height - 1);
                final Document document = this.component.getDocument();
                if (document instanceof AbstractDocument) {
                    final Element bidiRootElement = ((AbstractDocument)document).getBidiRootElement();
                    if (bidiRootElement != null && bidiRootElement.getElementCount() > 1) {
                        this.flagXPoints[0] = modelToView.x;
                        this.flagYPoints[0] = modelToView.y;
                        this.flagXPoints[1] = modelToView.x;
                        this.flagYPoints[1] = modelToView.y + 4;
                        this.flagYPoints[2] = modelToView.y;
                        this.flagXPoints[2] = (this.dotLTR ? (modelToView.x + 5) : (modelToView.x - 4));
                        graphics.fillPolygon(this.flagXPoints, this.flagYPoints, 3);
                    }
                }
            }
            catch (BadLocationException ex) {}
        }
    }
    
    protected void positionCaret(final MouseEvent mouseEvent) {
        final Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
        final Position.Bias[] array = { null };
        final int viewToModel = this.component.getUI().viewToModel(this.component, point, array);
        if (array[0] == null) {
            array[0] = Position.Bias.Forward;
        }
        if (viewToModel >= 0) {
            this.setDot(viewToModel, array[0]);
            this.setMagicCaretPosition(null);
        }
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.updateHandler = new UpdateHandler();
        if (!objectInputStream.readBoolean()) {
            this.dotBias = Position.Bias.Forward;
        }
        else {
            this.dotBias = Position.Bias.Backward;
        }
        if (!objectInputStream.readBoolean()) {
            this.markBias = Position.Bias.Forward;
        }
        else {
            this.markBias = Position.Bias.Backward;
        }
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList.remove((DefaultCaret.class$javax$swing$event$ChangeListener != null) ? DefaultCaret.class$javax$swing$event$ChangeListener : (DefaultCaret.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    protected final synchronized void repaint() {
        if (this.component != null) {
            this.component.repaint(super.x, super.y, super.width, super.height);
        }
    }
    
    void repaintNewCaret() {
        if (this.component != null) {
            final TextUI ui = this.component.getUI();
            final Document document = this.component.getDocument();
            if (ui != null && document != null) {
                Rectangle modelToView;
                try {
                    modelToView = ui.modelToView(this.component, this.dot, this.dotBias);
                }
                catch (BadLocationException ex) {
                    modelToView = null;
                }
                if (modelToView != null) {
                    this.adjustVisibility(modelToView);
                }
                this.damage(modelToView);
            }
        }
    }
    
    void setAsynchronousMovement(final boolean async) {
        this.async = async;
    }
    
    public void setBlinkRate(final int delay) {
        if (delay != 0) {
            if (this.flasher == null) {
                this.flasher = new Timer(delay, this.updateHandler);
            }
            this.flasher.setDelay(delay);
        }
        else if (this.flasher != null) {
            this.flasher.stop();
            this.flasher.removeActionListener(this.updateHandler);
            this.flasher = null;
        }
    }
    
    public void setDot(final int n) {
        this.setDot(n, Position.Bias.Forward);
    }
    
    void setDot(int mark, Position.Bias forward) {
        final Document document = this.component.getDocument();
        if (document != null) {
            mark = Math.min(mark, document.getLength());
        }
        mark = Math.max(mark, 0);
        if (mark == 0) {
            forward = Position.Bias.Forward;
        }
        this.mark = mark;
        if (this.dot != mark || this.dotBias != forward || this.selectionTag != null) {
            this.changeCaretPosition(mark, forward);
        }
        this.markBias = this.dotBias;
        this.markLTR = this.dotLTR;
        final Highlighter highlighter = this.component.getHighlighter();
        if (highlighter != null && this.selectionTag != null) {
            highlighter.removeHighlight(this.selectionTag);
            this.selectionTag = null;
        }
    }
    
    public void setMagicCaretPosition(final Point magicCaretPosition) {
        this.magicCaretPosition = magicCaretPosition;
    }
    
    public void setSelectionVisible(final boolean b) {
        if (b) {
            final Highlighter highlighter = this.component.getHighlighter();
            if (this.dot != this.mark && highlighter != null && this.selectionTag == null) {
                final int min = Math.min(this.dot, this.mark);
                final int max = Math.max(this.dot, this.mark);
                final Highlighter.HighlightPainter selectionPainter = this.getSelectionPainter();
                try {
                    this.selectionTag = highlighter.addHighlight(min, max, selectionPainter);
                }
                catch (BadLocationException ex) {
                    this.selectionTag = null;
                }
            }
        }
        else if (this.selectionTag != null) {
            this.component.getHighlighter().removeHighlight(this.selectionTag);
            this.selectionTag = null;
        }
    }
    
    public void setVisible(final boolean visible) {
        if (this.component != null) {
            if (this.visible != visible) {
                try {
                    this.damage(this.component.modelToView(this.dot));
                }
                catch (BadLocationException ex) {}
            }
            this.visible = visible;
        }
        if (this.flasher != null) {
            if (this.visible) {
                this.flasher.start();
            }
            else {
                this.flasher.stop();
            }
        }
    }
    
    public String toString() {
        return String.valueOf(new StringBuffer("Dot=(").append(this.dot).append(", ").append(this.dotBias).append(")").toString()) + " Mark=(" + this.mark + ", " + this.markBias + ")";
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeBoolean(this.dotBias == Position.Bias.Backward);
        objectOutputStream.writeBoolean(this.markBias == Position.Bias.Backward);
    }
    
    class SafeScroller implements Runnable
    {
        Rectangle r;
        
        SafeScroller(final Rectangle r) {
            this.r = r;
        }
        
        public void run() {
            if (DefaultCaret.this.component != null) {
                DefaultCaret.this.component.scrollRectToVisible(this.r);
            }
        }
    }
    
    private static class FocusHandler implements FocusListener
    {
        private transient FocusListener fl;
        
        FocusHandler(final FocusListener fl) {
            this.fl = fl;
        }
        
        public void focusGained(final FocusEvent focusEvent) {
            this.fl.focusGained(focusEvent);
        }
        
        public void focusLost(final FocusEvent focusEvent) {
            this.fl.focusLost(focusEvent);
        }
    }
    
    class UpdateHandler implements PropertyChangeListener, DocumentListener, ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            if (!DefaultCaret.this.visible && (DefaultCaret.this.width == 0 || DefaultCaret.this.height == 0)) {
                DefaultCaret.this.setVisible(true);
            }
            else {
                DefaultCaret.this.visible ^= true;
                DefaultCaret.this.repaint();
            }
        }
        
        public void changedUpdate(final DocumentEvent documentEvent) {
        }
        
        public void insertUpdate(final DocumentEvent documentEvent) {
            if (DefaultCaret.this.async || SwingUtilities.isEventDispatchThread()) {
                int n = 0;
                final int offset = documentEvent.getOffset();
                final int length = documentEvent.getLength();
                if (DefaultCaret.this.dot >= offset) {
                    n = length;
                }
                if (DefaultCaret.this.mark >= offset) {
                    final DefaultCaret this$0 = DefaultCaret.this;
                    this$0.mark += length;
                }
                if (n != 0) {
                    if (DefaultCaret.this.dot == offset) {
                        final Document document = DefaultCaret.this.component.getDocument();
                        final int n2 = DefaultCaret.this.dot + n;
                        boolean equals;
                        try {
                            equals = document.getText(n2 - 1, 1).equals("\n");
                        }
                        catch (BadLocationException ex) {
                            equals = false;
                        }
                        if (equals) {
                            DefaultCaret.this.changeCaretPosition(n2, Position.Bias.Forward);
                        }
                        else {
                            DefaultCaret.this.changeCaretPosition(n2, Position.Bias.Backward);
                        }
                    }
                    else {
                        DefaultCaret.this.changeCaretPosition(DefaultCaret.this.dot + n, DefaultCaret.this.dotBias);
                    }
                }
            }
        }
        
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final Object oldValue = propertyChangeEvent.getOldValue();
            final Object newValue = propertyChangeEvent.getNewValue();
            if (oldValue instanceof Document || newValue instanceof Document) {
                DefaultCaret.this.setDot(0);
                if (oldValue != null) {
                    ((Document)oldValue).removeDocumentListener(this);
                }
                if (newValue != null) {
                    ((Document)newValue).addDocumentListener(this);
                }
            }
        }
        
        public void removeUpdate(final DocumentEvent documentEvent) {
            if (DefaultCaret.this.async || SwingUtilities.isEventDispatchThread()) {
                int n = 0;
                final int offset = documentEvent.getOffset();
                final int n2 = offset + documentEvent.getLength();
                boolean b = false;
                if (DefaultCaret.this.dot >= n2) {
                    n = n2 - offset;
                    if (DefaultCaret.this.dot == n2) {
                        b = true;
                    }
                }
                else if (DefaultCaret.this.dot >= offset) {
                    n = DefaultCaret.this.dot - offset;
                    b = true;
                }
                boolean b2 = false;
                if (DefaultCaret.this.mark >= n2) {
                    final DefaultCaret this$0 = DefaultCaret.this;
                    this$0.mark -= n2 - offset;
                    if (DefaultCaret.this.mark == n2) {
                        b2 = true;
                    }
                }
                else if (DefaultCaret.this.mark >= offset) {
                    DefaultCaret.this.mark = offset;
                    b2 = true;
                }
                if (DefaultCaret.this.mark == DefaultCaret.this.dot - n) {
                    DefaultCaret.this.setDot(DefaultCaret.this.dot - n, DefaultCaret.this.guessBiasForOffset(DefaultCaret.this.dot - n, DefaultCaret.this.dotBias, DefaultCaret.this.dotLTR));
                }
                else {
                    if (b) {
                        DefaultCaret.this.dotBias = DefaultCaret.this.guessBiasForOffset(DefaultCaret.this.dot - n, DefaultCaret.this.dotBias, DefaultCaret.this.dotLTR);
                    }
                    if (b2) {
                        DefaultCaret.this.markBias = DefaultCaret.this.guessBiasForOffset(DefaultCaret.this.mark, DefaultCaret.this.markBias, DefaultCaret.this.markLTR);
                    }
                    DefaultCaret.this.changeCaretPosition(DefaultCaret.this.dot - n, DefaultCaret.this.dotBias);
                    DefaultCaret.this.markLTR = DefaultCaret.this.isPositionLTR(DefaultCaret.this.mark, DefaultCaret.this.markBias);
                }
            }
        }
    }
}
