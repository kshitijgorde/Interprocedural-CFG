// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import javax.swing.event.ChangeEvent;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.AbstractUndoableEdit;
import java.util.Stack;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Enumeration;
import java.awt.Font;
import java.awt.Color;
import javax.swing.undo.UndoableEdit;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeListener;
import java.util.Vector;

public class DefaultStyledDocument extends AbstractDocument implements StyledDocument
{
    public static final int BUFFER_SIZE_DEFAULT = 4096;
    protected ElementBuffer buffer;
    private transient Vector listeningStyles;
    private transient ChangeListener styleChangeListener;
    private transient ChangeListener styleContextChangeListener;
    static /* synthetic */ Class class$javax$swing$event$DocumentListener;
    
    public DefaultStyledDocument() {
        this(new GapContent(4096), new StyleContext());
    }
    
    public DefaultStyledDocument(final Content content, final StyleContext styleContext) {
        super(content, (AttributeContext)styleContext);
        this.listeningStyles = new Vector();
        this.buffer = new ElementBuffer(this.createDefaultRoot());
        this.setLogicalStyle(0, styleContext.getStyle("default"));
    }
    
    public DefaultStyledDocument(final StyleContext styleContext) {
        this(new GapContent(4096), styleContext);
    }
    
    public void addDocumentListener(final DocumentListener documentListener) {
        synchronized (this.listeningStyles) {
            final int listenerCount = super.listenerList.getListenerCount((DefaultStyledDocument.class$javax$swing$event$DocumentListener != null) ? DefaultStyledDocument.class$javax$swing$event$DocumentListener : (DefaultStyledDocument.class$javax$swing$event$DocumentListener = class$("javax.swing.event.DocumentListener")));
            super.addDocumentListener(documentListener);
            if (listenerCount == 0) {
                if (this.styleContextChangeListener == null) {
                    this.styleContextChangeListener = this.createStyleContextChangeListener();
                }
                if (this.styleContextChangeListener != null) {
                    ((StyleContext)this.getAttributeContext()).addChangeListener(this.styleContextChangeListener);
                }
                this.updateStylesListeningTo();
            }
        }
        // monitorexit(this.listeningStyles)
    }
    
    public Style addStyle(final String s, final Style style) {
        return ((StyleContext)this.getAttributeContext()).addStyle(s, style);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected void create(final ElementSpec[] array) {
        try {
            if (this.getLength() != 0) {
                this.remove(0, this.getLength());
            }
            this.writeLock();
            final Content content = this.getContent();
            final int length = array.length;
            final StringBuffer sb = new StringBuffer();
            for (final ElementSpec elementSpec : array) {
                if (elementSpec.getLength() > 0) {
                    sb.append(elementSpec.getArray(), elementSpec.getOffset(), elementSpec.getLength());
                }
            }
            final UndoableEdit insertString = content.insertString(0, sb.toString());
            final int length2 = sb.length();
            this.getClass();
            final DefaultDocumentEvent defaultDocumentEvent = new DefaultDocumentEvent(this, 0, length2, DocumentEvent.EventType.INSERT);
            defaultDocumentEvent.addEdit(insertString);
            this.buffer.create(length2, array, defaultDocumentEvent);
            super.insertUpdate(defaultDocumentEvent, null);
            defaultDocumentEvent.end();
            this.fireInsertUpdate(defaultDocumentEvent);
            this.fireUndoableEditUpdate(new UndoableEditEvent(this, defaultDocumentEvent));
        }
        catch (BadLocationException ex) {
            throw new StateInvariantError("problem initializing");
        }
        finally {
            this.writeUnlock();
        }
    }
    
    protected AbstractElement createDefaultRoot() {
        this.writeLock();
        final SectionElement sectionElement = new SectionElement();
        final BranchElement branchElement = new BranchElement(this, sectionElement, null);
        final Element[] array = { new LeafElement(this, branchElement, null, 0, 1) };
        branchElement.replace(0, 0, array);
        array[0] = branchElement;
        ((BranchElement)sectionElement).replace(0, 0, array);
        this.writeUnlock();
        return sectionElement;
    }
    
    short createSpecsForInsertAfterNewline(final Element element, final Element element2, final AttributeSet set, final Vector vector, final int n, final int n2) {
        if (element.getParentElement() == element2.getParentElement()) {
            vector.addElement(new ElementSpec(set, (short)2));
            vector.addElement(new ElementSpec(set, (short)1));
            if (element2.getEndOffset() != n2) {
                return 7;
            }
            final Element parentElement = element2.getParentElement();
            if (parentElement.getElementIndex(n) + 1 < parentElement.getElementCount()) {
                return 5;
            }
        }
        else {
            final Vector vector2 = new Vector<Element>();
            final Vector vector3 = new Vector<Element>();
            for (Element parentElement2 = element2; parentElement2 != null; parentElement2 = parentElement2.getParentElement()) {
                vector2.addElement(parentElement2);
            }
            Element parentElement3 = element;
            int index = -1;
            while (parentElement3 != null && (index = vector2.indexOf(parentElement3)) == -1) {
                vector3.addElement(parentElement3);
                parentElement3 = parentElement3.getParentElement();
            }
            if (parentElement3 != null) {
                for (int i = 0; i < index; ++i) {
                    vector.addElement(new ElementSpec(null, (short)2));
                }
                for (int j = vector3.size() - 1; j >= 0; --j) {
                    final ElementSpec elementSpec = new ElementSpec(vector3.elementAt(j).getAttributes(), (short)1);
                    if (j > 0) {
                        elementSpec.setDirection((short)5);
                    }
                    vector.addElement(elementSpec);
                }
                if (vector3.size() > 0) {
                    return 5;
                }
                return 7;
            }
        }
        return 6;
    }
    
    ChangeListener createStyleChangeListener() {
        return new StyleChangeHandler();
    }
    
    ChangeListener createStyleContextChangeListener() {
        return new StyleContextChangeHandler();
    }
    
    public Color getBackground(final AttributeSet set) {
        return ((StyleContext)this.getAttributeContext()).getBackground(set);
    }
    
    public Element getCharacterElement(final int n) {
        Element element;
        for (element = this.getDefaultRootElement(); !element.isLeaf(); element = element.getElement(element.getElementIndex(n))) {}
        return element;
    }
    
    public Element getDefaultRootElement() {
        return this.buffer.getRootElement();
    }
    
    public Font getFont(final AttributeSet set) {
        return ((StyleContext)this.getAttributeContext()).getFont(set);
    }
    
    public Color getForeground(final AttributeSet set) {
        return ((StyleContext)this.getAttributeContext()).getForeground(set);
    }
    
    public Style getLogicalStyle(final int n) {
        Style style = null;
        final Element paragraphElement = this.getParagraphElement(n);
        if (paragraphElement != null) {
            style = (Style)paragraphElement.getAttributes().getResolveParent();
        }
        return style;
    }
    
    public Element getParagraphElement(final int n) {
        Element element;
        for (element = this.getDefaultRootElement(); !element.isLeaf(); element = element.getElement(element.getElementIndex(n))) {}
        if (element != null) {
            return element.getParentElement();
        }
        return element;
    }
    
    public Style getStyle(final String s) {
        return ((StyleContext)this.getAttributeContext()).getStyle(s);
    }
    
    public Enumeration getStyleNames() {
        return ((StyleContext)this.getAttributeContext()).getStyleNames();
    }
    
    protected void insert(final int n, final ElementSpec[] array) throws BadLocationException {
        if (array == null || array.length == 0) {
            return;
        }
        try {
            this.writeLock();
            final Content content = this.getContent();
            final int length = array.length;
            final StringBuffer sb = new StringBuffer();
            for (final ElementSpec elementSpec : array) {
                if (elementSpec.getLength() > 0) {
                    sb.append(elementSpec.getArray(), elementSpec.getOffset(), elementSpec.getLength());
                }
            }
            final UndoableEdit insertString = content.insertString(n, sb.toString());
            final int length2 = sb.length();
            this.getClass();
            final DefaultDocumentEvent defaultDocumentEvent = new DefaultDocumentEvent(this, n, length2, DocumentEvent.EventType.INSERT);
            defaultDocumentEvent.addEdit(insertString);
            this.buffer.insert(n, length2, array, defaultDocumentEvent);
            super.insertUpdate(defaultDocumentEvent, null);
            defaultDocumentEvent.end();
            this.fireInsertUpdate(defaultDocumentEvent);
            this.fireUndoableEditUpdate(new UndoableEditEvent(this, defaultDocumentEvent));
        }
        finally {
            this.writeUnlock();
        }
    }
    
    protected void insertUpdate(final DefaultDocumentEvent defaultDocumentEvent, AttributeSet empty) {
        final int offset = defaultDocumentEvent.getOffset();
        final int length = defaultDocumentEvent.getLength();
        if (empty == null) {
            empty = SimpleAttributeSet.EMPTY;
        }
        final Element paragraphElement = this.getParagraphElement(offset + length);
        AttributeSet set = paragraphElement.getAttributes();
        final Element paragraphElement2 = this.getParagraphElement(offset);
        final Element element = paragraphElement2.getElement(paragraphElement2.getElementIndex(offset));
        final int n = offset + length;
        final boolean b = element.getEndOffset() == n;
        final AttributeSet attributes = element.getAttributes();
        try {
            final Segment segment = new Segment();
            final Vector<ElementSpec> vector = new Vector<ElementSpec>();
            ElementSpec elementSpec = null;
            boolean b2 = false;
            short specsForInsertAfterNewline = 6;
            if (offset > 0) {
                this.getText(offset - 1, 1, segment);
                if (segment.array[segment.offset] == '\n') {
                    b2 = true;
                    specsForInsertAfterNewline = this.createSpecsForInsertAfterNewline(paragraphElement, paragraphElement2, set, vector, offset, n);
                    for (int i = vector.size() - 1; i >= 0; --i) {
                        final ElementSpec elementSpec2 = vector.elementAt(i);
                        if (elementSpec2.getType() == 1) {
                            elementSpec = elementSpec2;
                            break;
                        }
                    }
                }
            }
            if (!b2) {
                set = paragraphElement2.getAttributes();
            }
            this.getText(offset, length, segment);
            final char[] array = segment.array;
            final int n2 = segment.offset + segment.count;
            int offset2 = segment.offset;
            for (int j = segment.offset; j < n2; ++j) {
                if (array[j] == '\n') {
                    final int n3 = j + 1;
                    vector.addElement(new ElementSpec(empty, (short)3, n3 - offset2));
                    vector.addElement(new ElementSpec(null, (short)2));
                    elementSpec = new ElementSpec(set, (short)1);
                    vector.addElement(elementSpec);
                    offset2 = n3;
                }
            }
            if (offset2 < n2) {
                vector.addElement(new ElementSpec(empty, (short)3, n2 - offset2));
            }
            final ElementSpec elementSpec3 = vector.firstElement();
            final int length2 = this.getLength();
            if (elementSpec3.getType() == 3 && attributes.isEqual(empty)) {
                elementSpec3.setDirection((short)4);
            }
            if (elementSpec != null) {
                if (b2) {
                    elementSpec.setDirection(specsForInsertAfterNewline);
                }
                else if (paragraphElement2.getEndOffset() != n) {
                    elementSpec.setDirection((short)7);
                }
                else {
                    final Element parentElement = paragraphElement2.getParentElement();
                    final int elementIndex = parentElement.getElementIndex(offset);
                    if (elementIndex + 1 < parentElement.getElementCount() && !parentElement.getElement(elementIndex + 1).isLeaf()) {
                        elementSpec.setDirection((short)5);
                    }
                }
            }
            if (b && n < length2) {
                final ElementSpec elementSpec4 = vector.lastElement();
                if (elementSpec4.getType() == 3 && elementSpec4.getDirection() != 4 && ((elementSpec == null && (paragraphElement == paragraphElement2 || b2)) || (elementSpec != null && elementSpec.getDirection() != 6))) {
                    final Element element2 = paragraphElement.getElement(paragraphElement.getElementIndex(n));
                    if (element2.isLeaf() && empty.isEqual(element2.getAttributes())) {
                        elementSpec4.setDirection((short)5);
                    }
                }
            }
            else if (!b && elementSpec != null && elementSpec.getDirection() == 7) {
                final ElementSpec elementSpec5 = vector.lastElement();
                if (elementSpec5.getType() == 3 && elementSpec5.getDirection() != 4 && empty.isEqual(attributes)) {
                    elementSpec5.setDirection((short)5);
                }
            }
            if (Utilities.isComposedTextAttributeDefined(empty)) {
                ((MutableAttributeSet)empty).addAttributes(attributes);
                ((MutableAttributeSet)empty).addAttribute("$ename", "content");
            }
            final ElementSpec[] array2 = new ElementSpec[vector.size()];
            vector.copyInto(array2);
            this.buffer.insert(offset, length, array2, defaultDocumentEvent);
        }
        catch (BadLocationException ex) {}
        super.insertUpdate(defaultDocumentEvent, empty);
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        this.listeningStyles = new Vector();
        objectInputStream.defaultReadObject();
        if (this.styleContextChangeListener == null && super.listenerList.getListenerCount((DefaultStyledDocument.class$javax$swing$event$DocumentListener != null) ? DefaultStyledDocument.class$javax$swing$event$DocumentListener : (DefaultStyledDocument.class$javax$swing$event$DocumentListener = class$("javax.swing.event.DocumentListener"))) > 0) {
            this.styleContextChangeListener = this.createStyleContextChangeListener();
            if (this.styleContextChangeListener != null) {
                ((StyleContext)this.getAttributeContext()).addChangeListener(this.styleContextChangeListener);
            }
            this.updateStylesListeningTo();
        }
    }
    
    public void removeDocumentListener(final DocumentListener documentListener) {
        synchronized (this.listeningStyles) {
            super.removeDocumentListener(documentListener);
            if (super.listenerList.getListenerCount((DefaultStyledDocument.class$javax$swing$event$DocumentListener != null) ? DefaultStyledDocument.class$javax$swing$event$DocumentListener : (DefaultStyledDocument.class$javax$swing$event$DocumentListener = class$("javax.swing.event.DocumentListener"))) == 0) {
                for (int i = this.listeningStyles.size() - 1; i >= 0; --i) {
                    ((Style)this.listeningStyles.elementAt(i)).removeChangeListener(this.styleChangeListener);
                }
                this.listeningStyles.removeAllElements();
                if (this.styleContextChangeListener != null) {
                    ((StyleContext)this.getAttributeContext()).removeChangeListener(this.styleContextChangeListener);
                }
            }
        }
        // monitorexit(this.listeningStyles)
    }
    
    public void removeStyle(final String s) {
        ((StyleContext)this.getAttributeContext()).removeStyle(s);
    }
    
    protected void removeUpdate(final DefaultDocumentEvent defaultDocumentEvent) {
        super.removeUpdate(defaultDocumentEvent);
        this.buffer.remove(defaultDocumentEvent.getOffset(), defaultDocumentEvent.getLength(), defaultDocumentEvent);
    }
    
    public void setCharacterAttributes(final int n, final int n2, final AttributeSet set, final boolean b) {
        try {
            this.writeLock();
            this.getClass();
            final DefaultDocumentEvent defaultDocumentEvent = new DefaultDocumentEvent(this, n, n2, DocumentEvent.EventType.CHANGE);
            this.buffer.change(n, n2, defaultDocumentEvent);
            final AttributeSet copyAttributes = set.copyAttributes();
            int endOffset;
            for (int i = n; i < n + n2; i = endOffset) {
                final Element characterElement = this.getCharacterElement(i);
                endOffset = characterElement.getEndOffset();
                final MutableAttributeSet set2 = (MutableAttributeSet)characterElement.getAttributes();
                defaultDocumentEvent.addEdit(new AttributeUndoableEdit(characterElement, copyAttributes, b));
                if (b) {
                    set2.removeAttributes(set2);
                }
                set2.addAttributes(set);
            }
            defaultDocumentEvent.end();
            this.fireChangedUpdate(defaultDocumentEvent);
            this.fireUndoableEditUpdate(new UndoableEditEvent(this, defaultDocumentEvent));
        }
        finally {
            this.writeUnlock();
        }
    }
    
    public void setLogicalStyle(final int n, final Style resolveParent) {
        final Element paragraphElement = this.getParagraphElement(n);
        if (paragraphElement != null && paragraphElement instanceof AbstractElement) {
            try {
                this.writeLock();
                final StyleChangeUndoableEdit styleChangeUndoableEdit = new StyleChangeUndoableEdit((AbstractElement)paragraphElement, resolveParent);
                ((AbstractElement)paragraphElement).setResolveParent(resolveParent);
                final int startOffset = paragraphElement.getStartOffset();
                final int endOffset = paragraphElement.getEndOffset();
                this.getClass();
                final DefaultDocumentEvent defaultDocumentEvent = new DefaultDocumentEvent(this, startOffset, endOffset - startOffset, DocumentEvent.EventType.CHANGE);
                defaultDocumentEvent.addEdit(styleChangeUndoableEdit);
                defaultDocumentEvent.end();
                this.fireChangedUpdate(defaultDocumentEvent);
                this.fireUndoableEditUpdate(new UndoableEditEvent(this, defaultDocumentEvent));
            }
            finally {
                this.writeUnlock();
            }
        }
    }
    
    public void setParagraphAttributes(final int n, final int n2, final AttributeSet set, final boolean b) {
        try {
            this.writeLock();
            this.getClass();
            final DefaultDocumentEvent defaultDocumentEvent = new DefaultDocumentEvent(this, n, n2, DocumentEvent.EventType.CHANGE);
            final AttributeSet copyAttributes = set.copyAttributes();
            final Element defaultRootElement = this.getDefaultRootElement();
            final int elementIndex = defaultRootElement.getElementIndex(n);
            for (int elementIndex2 = defaultRootElement.getElementIndex(n + ((n2 > 0) ? (n2 - 1) : 0)), i = elementIndex; i <= elementIndex2; ++i) {
                final Element element = defaultRootElement.getElement(i);
                final MutableAttributeSet set2 = (MutableAttributeSet)element.getAttributes();
                defaultDocumentEvent.addEdit(new AttributeUndoableEdit(element, copyAttributes, b));
                if (b) {
                    set2.removeAttributes(set2);
                }
                set2.addAttributes(set);
            }
            defaultDocumentEvent.end();
            this.fireChangedUpdate(defaultDocumentEvent);
            this.fireUndoableEditUpdate(new UndoableEditEvent(this, defaultDocumentEvent));
        }
        finally {
            this.writeUnlock();
        }
    }
    
    protected void styleChanged(final Style style) {
        final DefaultDocumentEvent defaultDocumentEvent = new DefaultDocumentEvent(this, 0, this.getLength(), DocumentEvent.EventType.CHANGE);
        defaultDocumentEvent.end();
        this.fireChangedUpdate(defaultDocumentEvent);
    }
    
    void updateStylesListeningTo() {
        synchronized (this.listeningStyles) {
            final StyleContext styleContext = (StyleContext)this.getAttributeContext();
            if (this.styleChangeListener == null) {
                this.styleChangeListener = this.createStyleChangeListener();
            }
            if (this.styleChangeListener != null && styleContext != null) {
                final Enumeration styleNames = styleContext.getStyleNames();
                final Vector vector = (Vector)this.listeningStyles.clone();
                this.listeningStyles.removeAllElements();
                while (styleNames.hasMoreElements()) {
                    final Style style = styleContext.getStyle(styleNames.nextElement());
                    final int index = vector.indexOf(style);
                    this.listeningStyles.addElement(style);
                    if (index == -1) {
                        style.addChangeListener(this.styleChangeListener);
                    }
                    else {
                        vector.removeElementAt(index);
                    }
                }
                for (int i = vector.size() - 1; i >= 0; --i) {
                    vector.elementAt(i).removeChangeListener(this.styleChangeListener);
                }
                if (this.listeningStyles.size() == 0) {
                    this.styleChangeListener = null;
                }
            }
        }
        // monitorexit(this.listeningStyles)
    }
    
    protected class SectionElement extends BranchElement
    {
        public SectionElement() {
            super(null, null);
        }
        
        public String getName() {
            return "section";
        }
    }
    
    public static class ElementSpec
    {
        public static final short StartTagType = 1;
        public static final short EndTagType = 2;
        public static final short ContentType = 3;
        public static final short JoinPreviousDirection = 4;
        public static final short JoinNextDirection = 5;
        public static final short OriginateDirection = 6;
        public static final short JoinFractureDirection = 7;
        private AttributeSet attr;
        private int len;
        private short type;
        private short direction;
        private int offs;
        private char[] data;
        
        public ElementSpec(final AttributeSet set, final short n) {
            this(set, n, null, 0, 0);
        }
        
        public ElementSpec(final AttributeSet set, final short n, final int n2) {
            this(set, n, null, 0, n2);
        }
        
        public ElementSpec(final AttributeSet attr, final short type, final char[] data, final int offs, final int len) {
            this.attr = attr;
            this.type = type;
            this.data = data;
            this.offs = offs;
            this.len = len;
            this.direction = 6;
        }
        
        public char[] getArray() {
            return this.data;
        }
        
        public AttributeSet getAttributes() {
            return this.attr;
        }
        
        public short getDirection() {
            return this.direction;
        }
        
        public int getLength() {
            return this.len;
        }
        
        public int getOffset() {
            return this.offs;
        }
        
        public short getType() {
            return this.type;
        }
        
        public void setDirection(final short direction) {
            this.direction = direction;
        }
        
        public void setType(final short type) {
            this.type = type;
        }
        
        public String toString() {
            String s = "??";
            String s2 = "??";
            switch (this.type) {
                case 1: {
                    s = "StartTag";
                    break;
                }
                case 3: {
                    s = "Content";
                    break;
                }
                case 2: {
                    s = "EndTag";
                    break;
                }
            }
            switch (this.direction) {
                case 4: {
                    s2 = "JoinPrevious";
                    break;
                }
                case 5: {
                    s2 = "JoinNext";
                    break;
                }
                case 6: {
                    s2 = "Originate";
                    break;
                }
                case 7: {
                    s2 = "Fracture";
                    break;
                }
            }
            return String.valueOf(s) + ":" + s2 + ":" + this.getLength();
        }
    }
    
    public class ElementBuffer implements Serializable
    {
        Element root;
        transient int pos;
        transient int offset;
        transient int length;
        transient int endOffset;
        transient Vector changes;
        transient Stack path;
        transient boolean insertOp;
        transient boolean recreateLeafs;
        transient ElemChanges[] insertPath;
        transient boolean createdFracture;
        transient Element fracturedParent;
        transient Element fracturedChild;
        transient boolean offsetLastIndex;
        transient boolean offsetLastIndexOnReplace;
        
        public ElementBuffer(final Element root) {
            this.root = root;
            this.changes = new Vector();
            this.path = new Stack();
        }
        
        void advance(final int n) {
            this.pos += n;
        }
        
        void beginEdits(final int n, final int length) {
            this.offset = n;
            this.length = length;
            this.endOffset = n + length;
            this.pos = n;
            if (this.changes == null) {
                this.changes = new Vector();
            }
            else {
                this.changes.removeAllElements();
            }
            if (this.path == null) {
                this.path = new Stack();
            }
            else {
                this.path.removeAllElements();
            }
            this.fracturedParent = null;
            this.fracturedChild = null;
            final boolean b = false;
            this.offsetLastIndexOnReplace = b;
            this.offsetLastIndex = b;
        }
        
        boolean canJoin(final Element element, final Element element2) {
            return element != null && element2 != null && element.isLeaf() == element2.isLeaf() && ((element.getName().equals("paragraph") && element2.getName().equals("paragraph")) || element.getAttributes().isEqual(element2.getAttributes()));
        }
        
        public void change(final int n, final int n2, final DefaultDocumentEvent defaultDocumentEvent) {
            this.beginEdits(n, n2);
            this.changeUpdate();
            this.endEdits(defaultDocumentEvent);
        }
        
        protected void changeUpdate() {
            if (!this.split(this.offset, this.length)) {
                while (this.path.size() != 0) {
                    this.pop();
                }
                this.split(this.offset + this.length, 0);
            }
            while (this.path.size() != 0) {
                this.pop();
            }
        }
        
        public Element clone(final Element element, final Element element2) {
            if (element2.isLeaf()) {
                return DefaultStyledDocument.this.createLeafElement(element, element2.getAttributes(), element2.getStartOffset(), element2.getEndOffset());
            }
            final Element branchElement = DefaultStyledDocument.this.createBranchElement(element, element2.getAttributes());
            final int elementCount = element2.getElementCount();
            final Element[] array = new Element[elementCount];
            for (int i = 0; i < elementCount; ++i) {
                array[i] = this.clone(branchElement, element2.getElement(i));
            }
            ((BranchElement)branchElement).replace(0, 0, array);
            return branchElement;
        }
        
        void create(final int n, final ElementSpec[] array, final DefaultDocumentEvent defaultDocumentEvent) {
            this.insertOp = true;
            this.beginEdits(this.offset, n);
            Element root = this.root;
            int n2 = root.getElementIndex(0);
            while (!root.isLeaf()) {
                final Element element = root.getElement(n2);
                this.push(root, n2);
                root = element;
                n2 = root.getElementIndex(0);
            }
            final ElemChanges elemChanges = this.path.peek();
            final Element element2 = elemChanges.parent.getElement(elemChanges.index);
            elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element2.getAttributes(), DefaultStyledDocument.this.getLength(), element2.getEndOffset()));
            elemChanges.removed.addElement(element2);
            while (this.path.size() > 1) {
                this.pop();
            }
            for (int length = array.length, i = 1; i < length; ++i) {
                this.insertElement(array[i]);
            }
            while (this.path.size() != 0) {
                this.pop();
            }
            this.endEdits(defaultDocumentEvent);
            this.insertOp = false;
        }
        
        void endEdits(final DefaultDocumentEvent defaultDocumentEvent) {
            for (int size = this.changes.size(), i = 0; i < size; ++i) {
                final ElemChanges elemChanges = this.changes.elementAt(i);
                final Element[] array = new Element[elemChanges.removed.size()];
                elemChanges.removed.copyInto(array);
                final Element[] array2 = new Element[elemChanges.added.size()];
                elemChanges.added.copyInto(array2);
                final int index = elemChanges.index;
                ((BranchElement)elemChanges.parent).replace(index, array.length, array2);
                defaultDocumentEvent.addEdit(new ElementEdit(elemChanges.parent, index, array, array2));
            }
        }
        
        void fracture(final int n) {
            final int length = this.insertPath.length;
            int n2 = -1;
            int recreateLeafs = this.recreateLeafs ? 1 : 0;
            final ElemChanges elemChanges = this.insertPath[length - 1];
            int n3 = (elemChanges.index + 1 < elemChanges.parent.getElementCount()) ? 1 : 0;
            int n4 = (recreateLeafs != 0) ? length : -1;
            int n5 = length - 1;
            this.createdFracture = true;
            for (int i = length - 2; i >= 0; --i) {
                final ElemChanges elemChanges2 = this.insertPath[i];
                if (elemChanges2.added.size() > 0 || i == n) {
                    n2 = i;
                    if (recreateLeafs == 0 && n3 != 0) {
                        recreateLeafs = 1;
                        if (n4 == -1) {
                            n4 = n5 + 1;
                        }
                    }
                }
                if (n3 == 0 && elemChanges2.index < elemChanges2.parent.getElementCount()) {
                    n3 = 1;
                    n5 = i;
                }
            }
            if (recreateLeafs != 0) {
                if (n2 == -1) {
                    n2 = length - 1;
                }
                this.fractureFrom(this.insertPath, n2, n4);
            }
        }
        
        void fractureDeepestLeaf(final ElementSpec[] array) {
            final ElemChanges elemChanges = this.path.peek();
            final Element element = elemChanges.parent.getElement(elemChanges.index);
            if (this.offset != 0) {
                elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element.getAttributes(), element.getStartOffset(), this.offset));
            }
            elemChanges.removed.addElement(element);
            if (element.getEndOffset() != this.endOffset) {
                this.recreateLeafs = true;
            }
            else {
                this.offsetLastIndex = true;
            }
        }
        
        void fractureFrom(final ElemChanges[] array, int n, final int n2) {
            final ElemChanges elemChanges = array[n];
            final int length = array.length;
            Element element;
            if (n + 1 == length) {
                element = elemChanges.parent.getElement(elemChanges.index);
            }
            else {
                element = elemChanges.parent.getElement(elemChanges.index - 1);
            }
            Element fracturedChild;
            if (element.isLeaf()) {
                fracturedChild = DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element.getAttributes(), Math.max(this.endOffset, element.getStartOffset()), element.getEndOffset());
            }
            else {
                fracturedChild = DefaultStyledDocument.this.createBranchElement(elemChanges.parent, element.getAttributes());
            }
            this.fracturedParent = elemChanges.parent;
            this.fracturedChild = fracturedChild;
            Element element2 = fracturedChild;
            while (++n < n2) {
                final boolean b = n + 1 == n2;
                final boolean b2 = n + 1 == length;
                final ElemChanges elemChanges2 = array[n];
                Element element3;
                if (b) {
                    if (this.offsetLastIndex || !b2) {
                        element3 = null;
                    }
                    else {
                        element3 = elemChanges2.parent.getElement(elemChanges2.index);
                    }
                }
                else {
                    element3 = elemChanges2.parent.getElement(elemChanges2.index - 1);
                }
                Element element4;
                if (element3 != null) {
                    if (element3.isLeaf()) {
                        element4 = DefaultStyledDocument.this.createLeafElement(element2, element3.getAttributes(), Math.max(this.endOffset, element3.getStartOffset()), element3.getEndOffset());
                    }
                    else {
                        element4 = DefaultStyledDocument.this.createBranchElement(element2, element3.getAttributes());
                    }
                }
                else {
                    element4 = null;
                }
                int n3 = elemChanges2.parent.getElementCount() - elemChanges2.index;
                int n4 = 1;
                int n5;
                Element[] array2;
                if (element4 == null) {
                    if (b2) {
                        --n3;
                        n5 = elemChanges2.index + 1;
                    }
                    else {
                        n5 = elemChanges2.index;
                    }
                    n4 = 0;
                    array2 = new Element[n3];
                }
                else {
                    if (!b) {
                        ++n3;
                        n5 = elemChanges2.index;
                    }
                    else {
                        n5 = elemChanges2.index + 1;
                    }
                    array2 = new Element[n3];
                    array2[0] = element4;
                }
                for (int i = n4; i < n3; ++i) {
                    final Element element5 = elemChanges2.parent.getElement(n5++);
                    array2[i] = this.recreateFracturedElement(element2, element5);
                    elemChanges2.removed.addElement(element5);
                }
                ((BranchElement)element2).replace(0, 0, array2);
                element2 = element4;
            }
        }
        
        public Element getRootElement() {
            return this.root;
        }
        
        public void insert(final int n, final int n2, final ElementSpec[] array, final DefaultDocumentEvent defaultDocumentEvent) {
            this.insertOp = true;
            this.beginEdits(n, n2);
            this.insertUpdate(array);
            this.endEdits(defaultDocumentEvent);
            this.insertOp = false;
        }
        
        void insertElement(final ElementSpec elementSpec) {
            final ElemChanges elemChanges = this.path.peek();
            Label_0532: {
                switch (elementSpec.getType()) {
                    case 1: {
                        switch (elementSpec.getDirection()) {
                            case 5: {
                                Element element = elemChanges.parent.getElement(elemChanges.index);
                                if (element.isLeaf()) {
                                    if (elemChanges.index + 1 >= elemChanges.parent.getElementCount()) {
                                        throw new StateInvariantError("Join next to leaf");
                                    }
                                    element = elemChanges.parent.getElement(elemChanges.index + 1);
                                }
                                this.push(element, 0, true);
                                break Label_0532;
                            }
                            case 7: {
                                if (!this.createdFracture) {
                                    this.fracture(this.path.size() - 1);
                                }
                                if (!elemChanges.isFracture) {
                                    this.push(this.fracturedChild, 0, true);
                                    break Label_0532;
                                }
                                this.push(elemChanges.parent.getElement(0), 0, true);
                                break Label_0532;
                            }
                            default: {
                                final Element branchElement = DefaultStyledDocument.this.createBranchElement(elemChanges.parent, elementSpec.getAttributes());
                                elemChanges.added.addElement(branchElement);
                                this.push(branchElement, 0);
                                break Label_0532;
                            }
                        }
                        break;
                    }
                    case 2: {
                        this.pop();
                        break;
                    }
                    case 3: {
                        final int length = elementSpec.getLength();
                        if (elementSpec.getDirection() != 5) {
                            elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, elementSpec.getAttributes(), this.pos, this.pos + length));
                        }
                        else if (!elemChanges.isFracture) {
                            Element element2 = null;
                            if (this.insertPath != null) {
                                int i = this.insertPath.length - 1;
                                while (i >= 0) {
                                    if (this.insertPath[i] == elemChanges) {
                                        if (i != this.insertPath.length - 1) {
                                            element2 = elemChanges.parent.getElement(elemChanges.index);
                                            break;
                                        }
                                        break;
                                    }
                                    else {
                                        --i;
                                    }
                                }
                            }
                            if (element2 == null) {
                                element2 = elemChanges.parent.getElement(elemChanges.index + 1);
                            }
                            elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element2.getAttributes(), this.pos, element2.getEndOffset()));
                            elemChanges.removed.addElement(element2);
                        }
                        else {
                            final Element element3 = elemChanges.parent.getElement(0);
                            elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element3.getAttributes(), this.pos, element3.getEndOffset()));
                            elemChanges.removed.addElement(element3);
                        }
                        this.pos += length;
                        break;
                    }
                }
            }
        }
        
        void insertFirstContent(final ElementSpec[] array) {
            final ElementSpec elementSpec = array[0];
            final ElemChanges elemChanges = this.path.peek();
            final Element element = elemChanges.parent.getElement(elemChanges.index);
            final int n = this.offset + elementSpec.getLength();
            final boolean b = array.length == 1;
            switch (elementSpec.getDirection()) {
                case 4: {
                    if (element.getEndOffset() == n || b) {
                        this.offsetLastIndex = true;
                        this.offsetLastIndexOnReplace = true;
                        break;
                    }
                    elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element.getAttributes(), element.getStartOffset(), n));
                    elemChanges.removed.addElement(element);
                    if (element.getEndOffset() != this.endOffset) {
                        this.recreateLeafs = true;
                        break;
                    }
                    this.offsetLastIndex = true;
                    break;
                }
                case 5: {
                    if (this.offset != 0) {
                        elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element.getAttributes(), element.getStartOffset(), this.offset));
                        final Element element2 = elemChanges.parent.getElement(elemChanges.index + 1);
                        Element element3;
                        if (b) {
                            element3 = DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element2.getAttributes(), this.offset, element2.getEndOffset());
                        }
                        else {
                            element3 = DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element2.getAttributes(), this.offset, n);
                        }
                        elemChanges.added.addElement(element3);
                        elemChanges.removed.addElement(element);
                        elemChanges.removed.addElement(element2);
                        break;
                    }
                    break;
                }
                default: {
                    if (element.getStartOffset() != this.offset) {
                        elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element.getAttributes(), element.getStartOffset(), this.offset));
                    }
                    elemChanges.removed.addElement(element);
                    elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, elementSpec.getAttributes(), this.offset, n));
                    if (element.getEndOffset() != this.endOffset) {
                        this.recreateLeafs = true;
                        break;
                    }
                    this.offsetLastIndex = true;
                    break;
                }
            }
        }
        
        protected void insertUpdate(final ElementSpec[] array) {
            Element root = this.root;
            int n = root.getElementIndex(this.offset);
            while (!root.isLeaf()) {
                final Element element = root.getElement(n);
                this.push(root, element.isLeaf() ? n : (n + 1));
                root = element;
                n = root.getElementIndex(this.offset);
            }
            this.insertPath = new ElemChanges[this.path.size()];
            this.path.copyInto(this.insertPath);
            this.createdFracture = false;
            this.recreateLeafs = false;
            int i;
            if (array[0].getType() == 3) {
                this.insertFirstContent(array);
                this.pos += array[0].getLength();
                i = 1;
            }
            else {
                this.fractureDeepestLeaf(array);
                i = 0;
            }
            while (i < array.length) {
                this.insertElement(array[i]);
                ++i;
            }
            if (!this.createdFracture) {
                this.fracture(-1);
            }
            while (this.path.size() != 0) {
                this.pop();
            }
            if (this.offsetLastIndex && this.offsetLastIndexOnReplace) {
                final ElemChanges elemChanges = this.insertPath[this.insertPath.length - 1];
                ++elemChanges.index;
            }
            for (int j = this.insertPath.length - 1; j >= 0; --j) {
                final ElemChanges elemChanges2 = this.insertPath[j];
                if (elemChanges2.parent == this.fracturedParent) {
                    elemChanges2.added.addElement(this.fracturedChild);
                }
                if ((elemChanges2.added.size() > 0 || elemChanges2.removed.size() > 0) && !this.changes.contains(elemChanges2)) {
                    this.changes.addElement(elemChanges2);
                }
            }
            if (this.offset == 0 && this.fracturedParent != null && array[0].getType() == 2) {
                int n2;
                for (n2 = 0; n2 < array.length && array[n2].getType() == 2; ++n2) {}
                final ElemChanges elemChanges3 = this.insertPath[this.insertPath.length - n2 - 1];
                final Vector removed = elemChanges3.removed;
                final Element parent = elemChanges3.parent;
                final ElemChanges elemChanges4 = elemChanges3;
                final int index = elemChanges4.index - 1;
                elemChanges4.index = index;
                removed.insertElementAt(parent.getElement(index), 0);
            }
        }
        
        Element join(final Element element, final Element element2, final Element element3, final int n, final int n2) {
            if (element2.isLeaf() && element3.isLeaf()) {
                return DefaultStyledDocument.this.createLeafElement(element, element2.getAttributes(), element2.getStartOffset(), element3.getEndOffset());
            }
            if (!element2.isLeaf() && !element3.isLeaf()) {
                final Element branchElement = DefaultStyledDocument.this.createBranchElement(element, element2.getAttributes());
                final int elementIndex = element2.getElementIndex(n);
                final int elementIndex2 = element3.getElementIndex(n2);
                Element element4 = element2.getElement(elementIndex);
                if (element4.getStartOffset() == n) {
                    element4 = null;
                }
                Element element5 = element3.getElement(elementIndex2);
                if (element5.getStartOffset() == n2) {
                    element5 = null;
                }
                final Vector vector = new Vector<Element>();
                for (int i = 0; i < elementIndex; ++i) {
                    vector.addElement(this.clone(branchElement, element2.getElement(i)));
                }
                if (this.canJoin(element4, element5)) {
                    vector.addElement(this.join(branchElement, element4, element5, n, n2));
                }
                else {
                    if (element4 != null) {
                        vector.addElement(this.clone(branchElement, element4));
                    }
                    if (element5 != null) {
                        vector.addElement(this.clone(branchElement, element5));
                    }
                }
                for (int elementCount = element3.getElementCount(), j = (element5 == null) ? elementIndex2 : (elementIndex2 + 1); j < elementCount; ++j) {
                    vector.addElement(this.clone(branchElement, element3.getElement(j)));
                }
                final Element[] array = new Element[vector.size()];
                vector.copyInto(array);
                ((BranchElement)branchElement).replace(0, 0, array);
                return branchElement;
            }
            throw new StateInvariantError("No support to join leaf element with non-leaf element");
        }
        
        void pop() {
            final ElemChanges elemChanges = this.path.peek();
            this.path.pop();
            if (elemChanges.added.size() > 0 || elemChanges.removed.size() > 0) {
                this.changes.addElement(elemChanges);
            }
            else if (!this.path.isEmpty()) {
                final Element parent = elemChanges.parent;
                if (parent.getElementCount() == 0) {
                    this.path.peek().added.removeElement(parent);
                }
            }
        }
        
        void push(final Element element, final int n) {
            this.push(element, n, false);
        }
        
        void push(final Element element, final int n, final boolean b) {
            this.path.push(new ElemChanges(element, n, b));
        }
        
        Element recreateFracturedElement(final Element element, final Element element2) {
            if (element2.isLeaf()) {
                return DefaultStyledDocument.this.createLeafElement(element, element2.getAttributes(), Math.max(element2.getStartOffset(), this.endOffset), element2.getEndOffset());
            }
            final Element branchElement = DefaultStyledDocument.this.createBranchElement(element, element2.getAttributes());
            final int elementCount = element2.getElementCount();
            final Element[] array = new Element[elementCount];
            for (int i = 0; i < elementCount; ++i) {
                array[i] = this.recreateFracturedElement(branchElement, element2.getElement(i));
            }
            ((BranchElement)branchElement).replace(0, 0, array);
            return branchElement;
        }
        
        public void remove(final int n, final int n2, final DefaultDocumentEvent defaultDocumentEvent) {
            this.beginEdits(n, n2);
            this.removeUpdate();
            this.endEdits(defaultDocumentEvent);
        }
        
        boolean removeElements(final Element element, final int n, final int n2) {
            if (!element.isLeaf()) {
                final int elementIndex = element.getElementIndex(n);
                final int elementIndex2 = element.getElementIndex(n2);
                this.push(element, elementIndex);
                final ElemChanges elemChanges = this.path.peek();
                if (elementIndex == elementIndex2) {
                    final Element element2 = element.getElement(elementIndex);
                    if (n <= element2.getStartOffset() && n2 >= element2.getEndOffset()) {
                        elemChanges.removed.addElement(element2);
                    }
                    else if (this.removeElements(element2, n, n2)) {
                        elemChanges.removed.addElement(element2);
                    }
                }
                else {
                    Element element3 = element.getElement(elementIndex);
                    Element element4 = element.getElement(elementIndex2);
                    final boolean b = n2 < element.getEndOffset();
                    if (b && this.canJoin(element3, element4)) {
                        for (int i = elementIndex; i <= elementIndex2; ++i) {
                            elemChanges.removed.addElement(element.getElement(i));
                        }
                        elemChanges.added.addElement(this.join(element, element3, element4, n, n2));
                    }
                    else {
                        int index = elementIndex + 1;
                        int n3 = elementIndex2 - 1;
                        if (element3.getStartOffset() == n || (elementIndex == 0 && element3.getStartOffset() > n && element3.getEndOffset() <= n2)) {
                            element3 = null;
                            index = elementIndex;
                        }
                        if (!b) {
                            element4 = null;
                            ++n3;
                        }
                        else if (element4.getStartOffset() == n2) {
                            element4 = null;
                        }
                        if (index <= n3) {
                            elemChanges.index = index;
                        }
                        for (int j = index; j <= n3; ++j) {
                            elemChanges.removed.addElement(element.getElement(j));
                        }
                        if (element3 != null && this.removeElements(element3, n, n2)) {
                            elemChanges.removed.insertElementAt(element3, 0);
                            elemChanges.index = elementIndex;
                        }
                        if (element4 != null && this.removeElements(element4, n, n2)) {
                            elemChanges.removed.addElement(element4);
                        }
                    }
                }
                this.pop();
                if (element.getElementCount() == elemChanges.removed.size() - elemChanges.added.size()) {
                    return true;
                }
            }
            return false;
        }
        
        protected void removeUpdate() {
            this.removeElements(this.root, this.offset, this.offset + this.length);
        }
        
        boolean split(final int pos, final int n) {
            boolean b = false;
            Element element = this.root;
            for (int n2 = element.getElementIndex(pos); !element.isLeaf(); element = element.getElement(n2), n2 = element.getElementIndex(pos)) {
                this.push(element, n2);
            }
            final ElemChanges elemChanges = this.path.peek();
            final Element element2 = elemChanges.parent.getElement(elemChanges.index);
            if (element2.getStartOffset() != pos) {
                int n4;
                final int n3 = n4 = elemChanges.index;
                if (pos + n < elemChanges.parent.getEndOffset() && n != 0) {
                    n4 = elemChanges.parent.getElementIndex(pos + n);
                    if (n4 == n3) {
                        elemChanges.removed.addElement(element2);
                        elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element2.getAttributes(), element2.getStartOffset(), pos));
                        elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element2.getAttributes(), pos, pos + n));
                        elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element2.getAttributes(), pos + n, element2.getEndOffset()));
                        return true;
                    }
                    if (pos + n == elemChanges.parent.getElement(n4).getStartOffset()) {
                        n4 = n3;
                    }
                    b = true;
                }
                this.pos = pos;
                final Element element3 = elemChanges.parent.getElement(n3);
                elemChanges.removed.addElement(element3);
                elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element3.getAttributes(), element3.getStartOffset(), this.pos));
                elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element3.getAttributes(), this.pos, element3.getEndOffset()));
                for (int i = n3 + 1; i < n4; ++i) {
                    final Element element4 = elemChanges.parent.getElement(i);
                    elemChanges.removed.addElement(element4);
                    elemChanges.added.addElement(element4);
                }
                if (n4 != n3) {
                    final Element element5 = elemChanges.parent.getElement(n4);
                    this.pos = pos + n;
                    elemChanges.removed.addElement(element5);
                    elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element5.getAttributes(), element5.getStartOffset(), this.pos));
                    elemChanges.added.addElement(DefaultStyledDocument.this.createLeafElement(elemChanges.parent, element5.getAttributes(), this.pos, element5.getEndOffset()));
                }
            }
            return b;
        }
        
        class ElemChanges
        {
            Element parent;
            int index;
            Vector added;
            Vector removed;
            boolean isFracture;
            
            ElemChanges(final Element parent, final int index, final boolean isFracture) {
                this.parent = parent;
                this.index = index;
                this.isFracture = isFracture;
                this.added = new Vector();
                this.removed = new Vector();
            }
            
            public String toString() {
                return "added: " + this.added + "\nremoved: " + this.removed + "\n";
            }
        }
    }
    
    public static class AttributeUndoableEdit extends AbstractUndoableEdit
    {
        protected AttributeSet newAttributes;
        protected AttributeSet copy;
        protected boolean isReplacing;
        protected Element element;
        
        public AttributeUndoableEdit(final Element element, final AttributeSet newAttributes, final boolean isReplacing) {
            this.element = element;
            this.newAttributes = newAttributes;
            this.isReplacing = isReplacing;
            this.copy = element.getAttributes().copyAttributes();
        }
        
        public void redo() throws CannotRedoException {
            super.redo();
            final MutableAttributeSet set = (MutableAttributeSet)this.element.getAttributes();
            if (this.isReplacing) {
                set.removeAttributes(set);
            }
            set.addAttributes(this.newAttributes);
        }
        
        public void undo() throws CannotUndoException {
            super.undo();
            final MutableAttributeSet set = (MutableAttributeSet)this.element.getAttributes();
            set.removeAttributes(set);
            set.addAttributes(this.copy);
        }
    }
    
    static class StyleChangeUndoableEdit extends AbstractUndoableEdit
    {
        protected AbstractElement element;
        protected Style newStyle;
        protected AttributeSet oldStyle;
        
        public StyleChangeUndoableEdit(final AbstractElement element, final Style newStyle) {
            this.element = element;
            this.newStyle = newStyle;
            this.oldStyle = element.getResolveParent();
        }
        
        public void redo() throws CannotRedoException {
            super.redo();
            this.element.setResolveParent(this.newStyle);
        }
        
        public void undo() throws CannotUndoException {
            super.undo();
            this.element.setResolveParent(this.oldStyle);
        }
    }
    
    class StyleChangeHandler implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            final Object source = changeEvent.getSource();
            if (source != null && source instanceof Style) {
                DefaultStyledDocument.this.styleChanged((Style)source);
            }
            else {
                DefaultStyledDocument.this.styleChanged(null);
            }
        }
    }
    
    class StyleContextChangeHandler implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            DefaultStyledDocument.this.updateStylesListeningTo();
        }
    }
}
