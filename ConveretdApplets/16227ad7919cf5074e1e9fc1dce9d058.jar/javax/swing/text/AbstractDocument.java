// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CompoundEdit;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import javax.swing.tree.TreeNode;
import java.util.Vector;
import java.io.IOException;
import java.io.ObjectInputValidation;
import java.io.ObjectInputStream;
import javax.swing.undo.UndoableEdit;
import java.util.Hashtable;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.DocumentEvent;
import java.io.PrintStream;
import javax.swing.event.UndoableEditListener;
import java.util.EventListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.EventListenerList;
import java.util.Dictionary;
import java.io.Serializable;

public abstract class AbstractDocument implements Document, Serializable
{
    private transient int numReaders;
    private transient Thread currWriter;
    private Dictionary documentProperties;
    protected EventListenerList listenerList;
    private Content data;
    private AttributeContext context;
    private transient BranchElement bidiRoot;
    private static final String BAD_LOCK_STATE = "document lock failure";
    protected static final String BAD_LOCATION = "document location failure";
    public static final String ParagraphElementName = "paragraph";
    public static final String ContentElementName = "content";
    public static final String SectionElementName = "section";
    public static final String BidiElementName = "bidi level";
    public static final String ElementNameAttribute = "$ename";
    static final String I18NProperty = "i18n";
    static final String AsyncLoadPriority = "load priority";
    static /* synthetic */ Class class$javax$swing$event$DocumentListener;
    static /* synthetic */ Class class$javax$swing$event$UndoableEditListener;
    
    protected AbstractDocument(final Content content) {
        this(content, (AttributeContext)StyleContext.getDefaultStyleContext());
    }
    
    protected AbstractDocument(final Content data, final AttributeContext context) {
        this.documentProperties = null;
        this.listenerList = new EventListenerList();
        this.data = data;
        this.context = context;
        this.bidiRoot = (BranchElement)new BidiRootElement();
        this.putProperty("i18n", Boolean.FALSE);
        try {
            this.writeLock();
            this.bidiRoot.replace(0, 0, new Element[] { new BidiElement(this.bidiRoot, 0, 1, 0) });
        }
        finally {
            this.writeUnlock();
        }
    }
    
    public void addDocumentListener(final DocumentListener documentListener) {
        this.listenerList.add((AbstractDocument.class$javax$swing$event$DocumentListener != null) ? AbstractDocument.class$javax$swing$event$DocumentListener : (AbstractDocument.class$javax$swing$event$DocumentListener = class$("javax.swing.event.DocumentListener")), documentListener);
    }
    
    public void addUndoableEditListener(final UndoableEditListener undoableEditListener) {
        this.listenerList.add((AbstractDocument.class$javax$swing$event$UndoableEditListener != null) ? AbstractDocument.class$javax$swing$event$UndoableEditListener : (AbstractDocument.class$javax$swing$event$UndoableEditListener = class$("javax.swing.event.UndoableEditListener")), undoableEditListener);
    }
    
    private byte[] calculateBidiLevels(final int n, final int n2) {
        final byte[] array = new byte[n2 - n];
        int n3 = 0;
        Element paragraphElement;
        for (int i = n; i < n2; i = paragraphElement.getEndOffset()) {
            paragraphElement = this.getParagraphElement(i);
            final int startOffset = paragraphElement.getStartOffset();
            final int endOffset = paragraphElement.getEndOffset();
            String text;
            try {
                text = this.getText(startOffset, endOffset - startOffset);
            }
            catch (BadLocationException ex) {
                throw new Error("Internal error: " + ex.toString());
            }
            final byte[] levels = new Bidi(text.toCharArray()).getLevels();
            System.arraycopy(levels, 0, array, n3, levels.length);
            n3 += levels.length;
        }
        if (n3 != array.length) {
            throw new Error("levelsEnd assertion failed.");
        }
        return array;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected Element createBranchElement(final Element element, final AttributeSet set) {
        return new BranchElement(element, set);
    }
    
    protected Element createLeafElement(final Element element, final AttributeSet set, final int n, final int n2) {
        return new LeafElement(element, set, n, n2);
    }
    
    public synchronized Position createPosition(final int n) throws BadLocationException {
        return this.data.createPosition(n);
    }
    
    public void dump(final PrintStream printStream) {
        final Element defaultRootElement = this.getDefaultRootElement();
        if (defaultRootElement instanceof AbstractElement) {
            ((AbstractElement)defaultRootElement).dump(printStream, 0);
        }
        ((AbstractElement)this.bidiRoot).dump(printStream, 0);
    }
    
    protected void fireChangedUpdate(final DocumentEvent documentEvent) {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractDocument.class$javax$swing$event$DocumentListener != null) ? AbstractDocument.class$javax$swing$event$DocumentListener : (AbstractDocument.class$javax$swing$event$DocumentListener = class$("javax.swing.event.DocumentListener")))) {
                ((DocumentListener)listenerList[i + 1]).changedUpdate(documentEvent);
            }
        }
    }
    
    protected void fireInsertUpdate(final DocumentEvent documentEvent) {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractDocument.class$javax$swing$event$DocumentListener != null) ? AbstractDocument.class$javax$swing$event$DocumentListener : (AbstractDocument.class$javax$swing$event$DocumentListener = class$("javax.swing.event.DocumentListener")))) {
                ((DocumentListener)listenerList[i + 1]).insertUpdate(documentEvent);
            }
        }
    }
    
    protected void fireRemoveUpdate(final DocumentEvent documentEvent) {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractDocument.class$javax$swing$event$DocumentListener != null) ? AbstractDocument.class$javax$swing$event$DocumentListener : (AbstractDocument.class$javax$swing$event$DocumentListener = class$("javax.swing.event.DocumentListener")))) {
                ((DocumentListener)listenerList[i + 1]).removeUpdate(documentEvent);
            }
        }
    }
    
    protected void fireUndoableEditUpdate(final UndoableEditEvent undoableEditEvent) {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractDocument.class$javax$swing$event$UndoableEditListener != null) ? AbstractDocument.class$javax$swing$event$UndoableEditListener : (AbstractDocument.class$javax$swing$event$UndoableEditListener = class$("javax.swing.event.UndoableEditListener")))) {
                ((UndoableEditListener)listenerList[i + 1]).undoableEditHappened(undoableEditEvent);
            }
        }
    }
    
    public int getAsynchronousLoadPriority() {
        final Integer n = (Integer)this.getProperty("load priority");
        if (n != null) {
            return n;
        }
        return -1;
    }
    
    protected final AttributeContext getAttributeContext() {
        return this.context;
    }
    
    public Element getBidiRootElement() {
        return this.bidiRoot;
    }
    
    protected final Content getContent() {
        return this.data;
    }
    
    protected final synchronized Thread getCurrentWriter() {
        return this.currWriter;
    }
    
    public abstract Element getDefaultRootElement();
    
    public Dictionary getDocumentProperties() {
        if (this.documentProperties == null) {
            this.documentProperties = new Hashtable(2);
        }
        return this.documentProperties;
    }
    
    public final Position getEndPosition() {
        Position position;
        try {
            position = this.createPosition(this.data.length());
        }
        catch (BadLocationException ex) {
            position = null;
        }
        return position;
    }
    
    public int getLength() {
        return this.data.length() - 1;
    }
    
    public abstract Element getParagraphElement(final int p0);
    
    public final Object getProperty(final Object o) {
        return this.getDocumentProperties().get(o);
    }
    
    public Element[] getRootElements() {
        return new Element[] { this.getDefaultRootElement(), this.getBidiRootElement() };
    }
    
    public final Position getStartPosition() {
        Position position;
        try {
            position = this.createPosition(0);
        }
        catch (BadLocationException ex) {
            position = null;
        }
        return position;
    }
    
    public String getText(final int n, final int n2) throws BadLocationException {
        return this.data.getString(n, n2);
    }
    
    public void getText(final int n, final int n2, final Segment segment) throws BadLocationException {
        this.data.getChars(n, n2, segment);
    }
    
    public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
        if (s == null || s.length() == 0) {
            return;
        }
        try {
            this.writeLock();
            final UndoableEdit insertString = this.data.insertString(n, s);
            final DefaultDocumentEvent defaultDocumentEvent = new DefaultDocumentEvent(n, s.length(), DocumentEvent.EventType.INSERT);
            if (insertString != null) {
                defaultDocumentEvent.addEdit(insertString);
            }
            this.insertUpdate(defaultDocumentEvent, set);
            defaultDocumentEvent.end();
            this.fireInsertUpdate(defaultDocumentEvent);
            if (insertString != null && (set == null || !set.isDefined(StyleConstants.ComposedTextAttribute))) {
                this.fireUndoableEditUpdate(new UndoableEditEvent(this, defaultDocumentEvent));
            }
        }
        finally {
            this.writeUnlock();
        }
    }
    
    protected void insertUpdate(final DefaultDocumentEvent defaultDocumentEvent, final AttributeSet set) {
        if (this.getProperty("i18n").equals(Boolean.TRUE)) {
            this.updateBidi(defaultDocumentEvent);
        }
    }
    
    boolean isLeftToRight(final int n, final int n2) {
        final Element bidiRootElement = this.getBidiRootElement();
        final Element element = bidiRootElement.getElement(bidiRootElement.getElementIndex(n));
        return element.getEndOffset() < n2 || StyleConstants.getBidiLevel(element.getAttributes()) % 2 == 0;
    }
    
    protected void postRemoveUpdate(final DefaultDocumentEvent defaultDocumentEvent) {
        if (this.getProperty("i18n").equals(Boolean.TRUE)) {
            this.updateBidi(defaultDocumentEvent);
        }
    }
    
    public final void putProperty(final Object o, final Object o2) {
        if (o2 != null) {
            this.getDocumentProperties().put(o, o2);
        }
        else {
            this.getDocumentProperties().remove(o);
        }
    }
    
    public final synchronized void readLock() {
        try {
            while (this.currWriter != null) {
                if (this.currWriter == Thread.currentThread()) {
                    return;
                }
                this.wait();
            }
            ++this.numReaders;
        }
        catch (InterruptedException ex) {}
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.listenerList = new EventListenerList();
        this.bidiRoot = (BranchElement)new BidiRootElement();
        try {
            this.writeLock();
            this.bidiRoot.replace(0, 0, new Element[] { new BidiElement(this.bidiRoot, 0, 1, 0) });
        }
        finally {
            this.writeUnlock();
        }
        objectInputStream.registerValidation(new ObjectInputValidation() {
            public void validateObject() {
                try {
                    AbstractDocument.this.writeLock();
                    AbstractDocument.this.updateBidi(new DefaultDocumentEvent(0, AbstractDocument.this.getLength(), DocumentEvent.EventType.INSERT));
                }
                finally {
                    AbstractDocument.this.writeUnlock();
                }
            }
        }, 0);
    }
    
    public final synchronized void readUnlock() {
        if (this.currWriter == Thread.currentThread()) {
            return;
        }
        if (this.numReaders <= 0) {
            throw new StateInvariantError("document lock failure");
        }
        --this.numReaders;
        this.notify();
    }
    
    public void remove(final int n, final int n2) throws BadLocationException {
        if (n2 > 0) {
            try {
                this.writeLock();
                final DefaultDocumentEvent defaultDocumentEvent = new DefaultDocumentEvent(n, n2, DocumentEvent.EventType.REMOVE);
                boolean composedTextElement = false;
                if (Utilities.is1dot2) {
                    Element element;
                    for (element = this.getDefaultRootElement(); !element.isLeaf(); element = element.getElement(element.getElementIndex(n))) {}
                    composedTextElement = Utilities.isComposedTextElement(element);
                }
                this.removeUpdate(defaultDocumentEvent);
                final UndoableEdit remove = this.data.remove(n, n2);
                if (remove != null) {
                    defaultDocumentEvent.addEdit(remove);
                }
                this.postRemoveUpdate(defaultDocumentEvent);
                defaultDocumentEvent.end();
                this.fireRemoveUpdate(defaultDocumentEvent);
                if (remove != null && !composedTextElement) {
                    this.fireUndoableEditUpdate(new UndoableEditEvent(this, defaultDocumentEvent));
                }
            }
            finally {
                this.writeUnlock();
            }
        }
    }
    
    public void removeDocumentListener(final DocumentListener documentListener) {
        this.listenerList.remove((AbstractDocument.class$javax$swing$event$DocumentListener != null) ? AbstractDocument.class$javax$swing$event$DocumentListener : (AbstractDocument.class$javax$swing$event$DocumentListener = class$("javax.swing.event.DocumentListener")), documentListener);
    }
    
    public void removeUndoableEditListener(final UndoableEditListener undoableEditListener) {
        this.listenerList.remove((AbstractDocument.class$javax$swing$event$UndoableEditListener != null) ? AbstractDocument.class$javax$swing$event$UndoableEditListener : (AbstractDocument.class$javax$swing$event$UndoableEditListener = class$("javax.swing.event.UndoableEditListener")), undoableEditListener);
    }
    
    protected void removeUpdate(final DefaultDocumentEvent defaultDocumentEvent) {
    }
    
    public void render(final Runnable runnable) {
        try {
            this.readLock();
            runnable.run();
        }
        finally {
            this.readUnlock();
        }
    }
    
    public void setAsynchronousLoadPriority(final int n) {
        this.putProperty("load priority", (n >= 0) ? new Integer(n) : null);
    }
    
    public void setDocumentProperties(final Dictionary documentProperties) {
        this.documentProperties = documentProperties;
    }
    
    private void updateBidi(final DefaultDocumentEvent defaultDocumentEvent) {
        int n2;
        int n3;
        if (defaultDocumentEvent.type == DocumentEvent.EventType.INSERT) {
            final int offset = defaultDocumentEvent.getOffset();
            final int n = offset + defaultDocumentEvent.getLength();
            n2 = this.getParagraphElement(offset).getStartOffset();
            n3 = this.getParagraphElement(n).getEndOffset();
        }
        else {
            if (defaultDocumentEvent.type != DocumentEvent.EventType.REMOVE) {
                throw new Error("Internal error: unknown event type.");
            }
            final Element paragraphElement = this.getParagraphElement(defaultDocumentEvent.getOffset());
            n2 = paragraphElement.getStartOffset();
            n3 = paragraphElement.getEndOffset();
        }
        final byte[] calculateBidiLevels = this.calculateBidiLevels(n2, n3);
        final Vector vector = new Vector<BidiElement>();
        int startOffset = n2;
        int elementIndex = 0;
        if (startOffset > 0) {
            final Element element = this.bidiRoot.getElement(elementIndex = this.bidiRoot.getElementIndex(n2 - 1));
            final int bidiLevel = StyleConstants.getBidiLevel(element.getAttributes());
            if (bidiLevel == calculateBidiLevels[0]) {
                startOffset = element.getStartOffset();
            }
            else if (element.getEndOffset() > n2) {
                vector.addElement(new BidiElement(this.bidiRoot, element.getStartOffset(), n2, bidiLevel));
            }
            else {
                ++elementIndex;
            }
        }
        int n4;
        for (n4 = 0; n4 < calculateBidiLevels.length && calculateBidiLevels[n4] == calculateBidiLevels[0]; ++n4) {}
        int endOffset = n3;
        BidiElement bidiElement = null;
        int elementIndex2 = this.bidiRoot.getElementCount() - 1;
        if (endOffset <= this.getLength()) {
            final Element element2 = this.bidiRoot.getElement(elementIndex2 = this.bidiRoot.getElementIndex(n3));
            final int bidiLevel2 = StyleConstants.getBidiLevel(element2.getAttributes());
            if (bidiLevel2 == calculateBidiLevels[calculateBidiLevels.length - 1]) {
                endOffset = element2.getEndOffset();
            }
            else if (element2.getStartOffset() < n3) {
                bidiElement = new BidiElement(this.bidiRoot, n3, element2.getEndOffset(), bidiLevel2);
            }
            else {
                --elementIndex2;
            }
        }
        int length;
        for (length = calculateBidiLevels.length; length > n4 && calculateBidiLevels[length - 1] == calculateBidiLevels[calculateBidiLevels.length - 1]; --length) {}
        if (n4 == length && calculateBidiLevels[0] == calculateBidiLevels[calculateBidiLevels.length - 1]) {
            vector.addElement(new BidiElement(this.bidiRoot, startOffset, endOffset, calculateBidiLevels[0]));
        }
        else {
            vector.addElement(new BidiElement(this.bidiRoot, startOffset, n4 + n2, calculateBidiLevels[0]));
            int n5;
            for (int i = n4; i < length; i = n5) {
                for (n5 = i; n5 < calculateBidiLevels.length && calculateBidiLevels[n5] == calculateBidiLevels[i]; ++n5) {}
                vector.addElement(new BidiElement(this.bidiRoot, n2 + i, n2 + n5, calculateBidiLevels[i]));
            }
            vector.addElement(new BidiElement(this.bidiRoot, length + n2, endOffset, calculateBidiLevels[calculateBidiLevels.length - 1]));
        }
        if (bidiElement != null) {
            vector.addElement(bidiElement);
        }
        int n6 = 0;
        if (this.bidiRoot.getElementCount() > 0) {
            n6 = elementIndex2 - elementIndex + 1;
        }
        final Element[] array = new Element[n6];
        for (int j = 0; j < n6; ++j) {
            array[j] = this.bidiRoot.getElement(elementIndex + j);
        }
        final Element[] array2 = new Element[vector.size()];
        vector.copyInto(array2);
        defaultDocumentEvent.addEdit(new ElementEdit(this.bidiRoot, elementIndex, array, array2));
        this.bidiRoot.replace(elementIndex, array.length, array2);
    }
    
    protected final synchronized void writeLock() {
        try {
            while (this.numReaders > 0 || this.currWriter != null) {
                if (Thread.currentThread() == this.currWriter) {
                    throw new IllegalStateException("Attempt to mutate in notification");
                }
                this.wait();
            }
            this.currWriter = Thread.currentThread();
        }
        catch (InterruptedException ex) {}
    }
    
    protected final synchronized void writeUnlock() {
        this.currWriter = null;
        this.notify();
    }
    
    public abstract class AbstractElement implements Element, MutableAttributeSet, Serializable, TreeNode
    {
        private Element parent;
        private transient AttributeSet attributes;
        
        public AbstractElement(final Element parent, final AttributeSet set) {
            this.parent = parent;
            this.attributes = AbstractDocument.this.getAttributeContext().getEmptySet();
            if (set != null) {
                this.addAttributes(set);
            }
        }
        
        public void addAttribute(final Object o, final Object o2) {
            this.checkForIllegalCast();
            this.attributes = AbstractDocument.this.getAttributeContext().addAttribute(this.attributes, o, o2);
        }
        
        public void addAttributes(final AttributeSet set) {
            this.checkForIllegalCast();
            this.attributes = AbstractDocument.this.getAttributeContext().addAttributes(this.attributes, set);
        }
        
        private final void checkForIllegalCast() {
            final Thread currentWriter = AbstractDocument.this.getCurrentWriter();
            if (currentWriter == null || currentWriter != Thread.currentThread()) {
                throw new StateInvariantError("Illegal cast to MutableAttributeSet");
            }
        }
        
        public abstract Enumeration children();
        
        public boolean containsAttribute(final Object o, final Object o2) {
            return this.attributes.containsAttribute(o, o2);
        }
        
        public boolean containsAttributes(final AttributeSet set) {
            return this.attributes.containsAttributes(set);
        }
        
        public AttributeSet copyAttributes() {
            return this.attributes.copyAttributes();
        }
        
        public void dump(final PrintStream printStream, final int n) {
            PrintWriter printWriter;
            try {
                printWriter = new PrintWriter(new OutputStreamWriter(printStream, "JavaEsc"), true);
            }
            catch (UnsupportedEncodingException ex) {
                printWriter = new PrintWriter(printStream, true);
            }
            this.indent(printWriter, n);
            if (this.getName() == null) {
                printWriter.print("<??");
            }
            else {
                printWriter.print("<" + this.getName());
            }
            if (this.getAttributeCount() > 0) {
                printWriter.println("");
                final Enumeration attributeNames = this.attributes.getAttributeNames();
                while (attributeNames.hasMoreElements()) {
                    final Object nextElement = attributeNames.nextElement();
                    this.indent(printWriter, n + 1);
                    printWriter.println(String.valueOf(String.valueOf(nextElement)) + "=" + this.getAttribute(nextElement));
                }
                this.indent(printWriter, n);
            }
            printWriter.println(">");
            if (this.isLeaf()) {
                this.indent(printWriter, n + 1);
                printWriter.print("[" + this.getStartOffset() + "," + this.getEndOffset() + "]");
                final Content content = AbstractDocument.this.getContent();
                try {
                    String s = content.getString(this.getStartOffset(), this.getEndOffset() - this.getStartOffset());
                    if (s.length() > 40) {
                        s = String.valueOf(s.substring(0, 40)) + "...";
                    }
                    printWriter.println("[" + s + "]");
                }
                catch (BadLocationException ex2) {}
            }
            else {
                for (int elementCount = this.getElementCount(), i = 0; i < elementCount; ++i) {
                    ((AbstractElement)this.getElement(i)).dump(printStream, n + 1);
                }
            }
        }
        
        protected void finalize() throws Throwable {
            AbstractDocument.this.getAttributeContext().reclaim(this.attributes);
        }
        
        public abstract boolean getAllowsChildren();
        
        public Object getAttribute(final Object o) {
            Object o2 = this.attributes.getAttribute(o);
            if (o2 == null) {
                final AttributeSet set = (this.parent != null) ? this.parent.getAttributes() : null;
                if (set != null) {
                    o2 = set.getAttribute(o);
                }
            }
            return o2;
        }
        
        public int getAttributeCount() {
            return this.attributes.getAttributeCount();
        }
        
        public Enumeration getAttributeNames() {
            return this.attributes.getAttributeNames();
        }
        
        public AttributeSet getAttributes() {
            return this;
        }
        
        public TreeNode getChildAt(final int n) {
            return (TreeNode)this.getElement(n);
        }
        
        public int getChildCount() {
            return this.getElementCount();
        }
        
        public Document getDocument() {
            return AbstractDocument.this;
        }
        
        public abstract Element getElement(final int p0);
        
        public abstract int getElementCount();
        
        public abstract int getElementIndex(final int p0);
        
        public abstract int getEndOffset();
        
        public int getIndex(final TreeNode treeNode) {
            for (int i = this.getChildCount() - 1; i >= 0; --i) {
                if (this.getChildAt(i) == treeNode) {
                    return i;
                }
            }
            return -1;
        }
        
        public String getName() {
            if (this.attributes.isDefined("$ename")) {
                return (String)this.attributes.getAttribute("$ename");
            }
            return null;
        }
        
        public TreeNode getParent() {
            return (TreeNode)this.getParentElement();
        }
        
        public Element getParentElement() {
            return this.parent;
        }
        
        public AttributeSet getResolveParent() {
            AttributeSet set = this.attributes.getResolveParent();
            if (set == null && this.parent != null) {
                set = this.parent.getAttributes();
            }
            return set;
        }
        
        public abstract int getStartOffset();
        
        private final void indent(final PrintWriter printWriter, final int n) {
            for (int i = 0; i < n; ++i) {
                printWriter.print("  ");
            }
        }
        
        public boolean isDefined(final Object o) {
            return this.attributes.isDefined(o);
        }
        
        public boolean isEqual(final AttributeSet set) {
            return this.attributes.isEqual(set);
        }
        
        public abstract boolean isLeaf();
        
        private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            final SimpleAttributeSet set = new SimpleAttributeSet();
            StyleContext.readAttributeSet(objectInputStream, set);
            this.attributes = AbstractDocument.this.getAttributeContext().addAttributes(SimpleAttributeSet.EMPTY, set);
        }
        
        public void removeAttribute(final Object o) {
            this.checkForIllegalCast();
            this.attributes = AbstractDocument.this.getAttributeContext().removeAttribute(this.attributes, o);
        }
        
        public void removeAttributes(final Enumeration enumeration) {
            this.checkForIllegalCast();
            this.attributes = AbstractDocument.this.getAttributeContext().removeAttributes(this.attributes, enumeration);
        }
        
        public void removeAttributes(final AttributeSet set) {
            this.checkForIllegalCast();
            final AttributeContext attributeContext = AbstractDocument.this.getAttributeContext();
            if (set == this) {
                this.attributes = attributeContext.getEmptySet();
            }
            else {
                this.attributes = attributeContext.removeAttributes(this.attributes, set);
            }
        }
        
        public void setResolveParent(final AttributeSet set) {
            this.checkForIllegalCast();
            final AttributeContext attributeContext = AbstractDocument.this.getAttributeContext();
            if (set != null) {
                this.attributes = attributeContext.addAttribute(this.attributes, StyleConstants.ResolveAttribute, set);
            }
            else {
                this.attributes = attributeContext.removeAttribute(this.attributes, StyleConstants.ResolveAttribute);
            }
        }
        
        private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            StyleContext.writeAttributeSet(objectOutputStream, this.attributes);
        }
    }
    
    public class BranchElement extends AbstractElement
    {
        private AbstractElement[] children;
        private int nchildren;
        private int lastIndex;
        
        public BranchElement(final Element element, final AttributeSet set) {
            super(element, set);
            this.children = new AbstractElement[1];
            this.nchildren = 0;
            this.lastIndex = -1;
        }
        
        public Enumeration children() {
            if (this.nchildren == 0) {
                return null;
            }
            final Vector<AbstractElement> vector = new Vector<AbstractElement>(this.nchildren);
            for (int i = 0; i < this.nchildren; ++i) {
                vector.addElement(this.children[i]);
            }
            return vector.elements();
        }
        
        public boolean getAllowsChildren() {
            return true;
        }
        
        public Element getElement(final int n) {
            if (n < this.nchildren) {
                return this.children[n];
            }
            return null;
        }
        
        public int getElementCount() {
            return this.nchildren;
        }
        
        public int getElementIndex(final int n) {
            int i = 0;
            int lastIndex = this.nchildren - 1;
            int lastIndex2 = 0;
            int n2 = this.getStartOffset();
            if (this.nchildren == 0) {
                return 0;
            }
            if (n >= this.getEndOffset()) {
                return this.nchildren - 1;
            }
            if (this.lastIndex >= i && this.lastIndex <= lastIndex) {
                final AbstractElement abstractElement = this.children[this.lastIndex];
                n2 = abstractElement.getStartOffset();
                final int endOffset = abstractElement.getEndOffset();
                if (n >= n2 && n < endOffset) {
                    return this.lastIndex;
                }
                if (n < n2) {
                    lastIndex = this.lastIndex;
                }
                else {
                    i = this.lastIndex;
                }
            }
            while (i <= lastIndex) {
                lastIndex2 = i + (lastIndex - i) / 2;
                final AbstractElement abstractElement2 = this.children[lastIndex2];
                n2 = abstractElement2.getStartOffset();
                final int endOffset2 = abstractElement2.getEndOffset();
                if (n >= n2 && n < endOffset2) {
                    return this.lastIndex = lastIndex2;
                }
                if (n < n2) {
                    lastIndex = lastIndex2 - 1;
                }
                else {
                    i = lastIndex2 + 1;
                }
            }
            int lastIndex3;
            if (n < n2) {
                lastIndex3 = lastIndex2;
            }
            else {
                lastIndex3 = lastIndex2 + 1;
            }
            return this.lastIndex = lastIndex3;
        }
        
        public int getEndOffset() {
            return this.children[this.nchildren - 1].getEndOffset();
        }
        
        public String getName() {
            String name = super.getName();
            if (name == null) {
                name = "paragraph";
            }
            return name;
        }
        
        public int getStartOffset() {
            return this.children[0].getStartOffset();
        }
        
        public boolean isLeaf() {
            return false;
        }
        
        public Element positionToElement(final int n) {
            final AbstractElement abstractElement = this.children[this.getElementIndex(n)];
            final int startOffset = abstractElement.getStartOffset();
            final int endOffset = abstractElement.getEndOffset();
            if (n >= startOffset && n < endOffset) {
                return abstractElement;
            }
            return null;
        }
        
        public void replace(final int n, final int n2, final Element[] array) {
            final int n3 = array.length - n2;
            final int n4 = n + n2;
            final int n5 = this.nchildren - n4;
            final int n6 = n4 + n3;
            if (this.nchildren + n3 >= this.children.length) {
                final AbstractElement[] children = new AbstractElement[Math.max(2 * this.children.length, this.nchildren + n3)];
                System.arraycopy(this.children, 0, children, 0, n);
                System.arraycopy(array, 0, children, n, array.length);
                System.arraycopy(this.children, n4, children, n6, n5);
                this.children = children;
            }
            else {
                System.arraycopy(this.children, n4, this.children, n6, n5);
                System.arraycopy(array, 0, this.children, n, array.length);
            }
            this.nchildren += n3;
        }
        
        public String toString() {
            return "BranchElement(" + this.getName() + ") " + this.getStartOffset() + "," + this.getEndOffset() + "\n";
        }
    }
    
    public class LeafElement extends AbstractElement
    {
        private transient Position p0;
        private transient Position p1;
        
        public LeafElement(final Element element, final AttributeSet set, final int n, final int n2) {
            super(element, set);
            try {
                this.p0 = AbstractDocument.this.createPosition(n);
                this.p1 = AbstractDocument.this.createPosition(n2);
            }
            catch (BadLocationException ex) {
                this.p0 = null;
                this.p1 = null;
                throw new StateInvariantError("Can't create Position references");
            }
        }
        
        public Enumeration children() {
            return null;
        }
        
        public boolean getAllowsChildren() {
            return false;
        }
        
        public Element getElement(final int n) {
            return null;
        }
        
        public int getElementCount() {
            return 0;
        }
        
        public int getElementIndex(final int n) {
            return -1;
        }
        
        public int getEndOffset() {
            return this.p1.getOffset();
        }
        
        public String getName() {
            String name = super.getName();
            if (name == null) {
                name = "content";
            }
            return name;
        }
        
        public int getStartOffset() {
            return this.p0.getOffset();
        }
        
        public boolean isLeaf() {
            return true;
        }
        
        private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            final int int1 = objectInputStream.readInt();
            final int int2 = objectInputStream.readInt();
            try {
                this.p0 = AbstractDocument.this.createPosition(int1);
                this.p1 = AbstractDocument.this.createPosition(int2);
            }
            catch (BadLocationException ex) {
                this.p0 = null;
                this.p1 = null;
                throw new IOException("Can't restore Position references");
            }
        }
        
        public String toString() {
            return "LeafElement(" + this.getName() + ") " + this.p0 + "," + this.p1 + "\n";
        }
        
        private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeInt(this.p0.getOffset());
            objectOutputStream.writeInt(this.p1.getOffset());
        }
    }
    
    class BidiRootElement extends BranchElement
    {
        BidiRootElement() {
            super(null, null);
        }
        
        public String getName() {
            return "bidi root";
        }
    }
    
    class BidiElement extends LeafElement
    {
        BidiElement(final Element element, final int n, final int n2, final int n3) {
            super(element, new SimpleAttributeSet(), n, n2);
            ((AbstractElement)this).addAttribute(StyleConstants.BidiLevel, new Integer(n3));
        }
        
        int getLevel() {
            final Integer n = (Integer)((AbstractElement)this).getAttribute(StyleConstants.BidiLevel);
            if (n != null) {
                return n;
            }
            return 0;
        }
        
        public String getName() {
            return "bidi level";
        }
        
        boolean isLeftToRight() {
            return this.getLevel() % 2 == 0;
        }
    }
    
    public class DefaultDocumentEvent extends CompoundEdit implements DocumentEvent
    {
        private int offset;
        private int length;
        private Hashtable changeLookup;
        private EventType type;
        
        public DefaultDocumentEvent(final int offset, final int length, final EventType type) {
            this.offset = offset;
            this.length = length;
            this.type = type;
        }
        
        public boolean addEdit(final UndoableEdit undoableEdit) {
            if (this.changeLookup == null && super.edits.size() > 10) {
                this.changeLookup = new Hashtable();
                for (int size = super.edits.size(), i = 0; i < size; ++i) {
                    final Object element = super.edits.elementAt(i);
                    if (element instanceof ElementChange) {
                        final ElementChange elementChange = (ElementChange)element;
                        this.changeLookup.put(elementChange.getElement(), elementChange);
                    }
                }
            }
            if (this.changeLookup != null && undoableEdit instanceof ElementChange) {
                final ElementChange elementChange2 = (ElementChange)undoableEdit;
                this.changeLookup.put(elementChange2.getElement(), elementChange2);
            }
            return super.addEdit(undoableEdit);
        }
        
        public ElementChange getChange(final Element element) {
            if (this.changeLookup != null) {
                return this.changeLookup.get(element);
            }
            for (int size = super.edits.size(), i = 0; i < size; ++i) {
                final Object element2 = super.edits.elementAt(i);
                if (element2 instanceof ElementChange) {
                    final ElementChange elementChange = (ElementChange)element2;
                    if (elementChange.getElement() == element) {
                        return elementChange;
                    }
                }
            }
            return null;
        }
        
        public Document getDocument() {
            return AbstractDocument.this;
        }
        
        public int getLength() {
            return this.length;
        }
        
        public int getOffset() {
            return this.offset;
        }
        
        public String getPresentationName() {
            final EventType type = this.getType();
            if (type == EventType.INSERT) {
                return "addition";
            }
            if (type == EventType.REMOVE) {
                return "deletion";
            }
            return "style change";
        }
        
        public String getRedoPresentationName() {
            return "Redo " + this.getPresentationName();
        }
        
        public EventType getType() {
            return this.type;
        }
        
        public String getUndoPresentationName() {
            return "Undo " + this.getPresentationName();
        }
        
        public boolean isSignificant() {
            return true;
        }
        
        public void redo() throws CannotRedoException {
            AbstractDocument.this.writeLock();
            try {
                super.redo();
                if (this.type == EventType.INSERT) {
                    AbstractDocument.this.fireInsertUpdate(this);
                }
                else if (this.type == EventType.REMOVE) {
                    AbstractDocument.this.fireRemoveUpdate(this);
                }
                else {
                    AbstractDocument.this.fireChangedUpdate(this);
                }
            }
            finally {
                AbstractDocument.this.writeUnlock();
            }
        }
        
        public String toString() {
            return super.edits.toString();
        }
        
        public void undo() throws CannotUndoException {
            AbstractDocument.this.writeLock();
            try {
                super.undo();
                if (this.type == EventType.REMOVE) {
                    AbstractDocument.this.fireInsertUpdate(this);
                }
                else if (this.type == EventType.INSERT) {
                    AbstractDocument.this.fireRemoveUpdate(this);
                }
                else {
                    AbstractDocument.this.fireChangedUpdate(this);
                }
            }
            finally {
                AbstractDocument.this.writeUnlock();
            }
        }
    }
    
    public static class ElementEdit extends AbstractUndoableEdit implements ElementChange
    {
        private Element e;
        private int index;
        private Element[] removed;
        private Element[] added;
        
        public ElementEdit(final Element e, final int index, final Element[] removed, final Element[] added) {
            this.e = e;
            this.index = index;
            this.removed = removed;
            this.added = added;
        }
        
        public Element[] getChildrenAdded() {
            return this.added;
        }
        
        public Element[] getChildrenRemoved() {
            return this.removed;
        }
        
        public Element getElement() {
            return this.e;
        }
        
        public int getIndex() {
            return this.index;
        }
        
        public void redo() throws CannotRedoException {
            super.redo();
            final Element[] removed = this.removed;
            this.removed = this.added;
            this.added = removed;
            ((BranchElement)this.e).replace(this.index, this.removed.length, this.added);
        }
        
        public void undo() throws CannotUndoException {
            super.undo();
            ((BranchElement)this.e).replace(this.index, this.added.length, this.removed);
            final Element[] removed = this.removed;
            this.removed = this.added;
            this.added = removed;
        }
    }
    
    public interface Content
    {
        Position createPosition(final int p0) throws BadLocationException;
        
        void getChars(final int p0, final int p1, final Segment p2) throws BadLocationException;
        
        String getString(final int p0, final int p1) throws BadLocationException;
        
        UndoableEdit insertString(final int p0, final String p1) throws BadLocationException;
        
        int length();
        
        UndoableEdit remove(final int p0, final int p1) throws BadLocationException;
    }
    
    public interface AttributeContext
    {
        AttributeSet addAttribute(final AttributeSet p0, final Object p1, final Object p2);
        
        AttributeSet addAttributes(final AttributeSet p0, final AttributeSet p1);
        
        AttributeSet getEmptySet();
        
        void reclaim(final AttributeSet p0);
        
        AttributeSet removeAttribute(final AttributeSet p0, final Object p1);
        
        AttributeSet removeAttributes(final AttributeSet p0, final Enumeration p1);
        
        AttributeSet removeAttributes(final AttributeSet p0, final AttributeSet p1);
    }
}
