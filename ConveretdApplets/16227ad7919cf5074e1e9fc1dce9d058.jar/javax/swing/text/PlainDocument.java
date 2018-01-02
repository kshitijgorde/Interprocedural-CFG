// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import javax.swing.undo.UndoableEdit;
import java.util.Vector;

public class PlainDocument extends AbstractDocument
{
    public static final String tabSizeAttribute = "tabSize";
    public static final String lineLimitAttribute = "lineLimit";
    private AbstractElement defaultRoot;
    private Vector added;
    private Vector removed;
    
    public PlainDocument() {
        this(new GapContent());
    }
    
    protected PlainDocument(final Content content) {
        super(content);
        this.added = new Vector();
        this.removed = new Vector();
        this.putProperty("tabSize", new Integer(8));
        this.defaultRoot = this.createDefaultRoot();
    }
    
    protected AbstractElement createDefaultRoot() {
        final BranchElement branchElement = (BranchElement)this.createBranchElement(null, null);
        branchElement.replace(0, 0, new Element[] { this.createLeafElement(branchElement, null, 0, 1) });
        return branchElement;
    }
    
    public Element getDefaultRootElement() {
        return this.defaultRoot;
    }
    
    public Element getParagraphElement(final int n) {
        final Element defaultRootElement = this.getDefaultRootElement();
        return defaultRootElement.getElement(defaultRootElement.getElementIndex(n));
    }
    
    private void insertComposedTextUpdate(final DefaultDocumentEvent defaultDocumentEvent, final AttributeSet set) {
        this.added.removeAllElements();
        final BranchElement branchElement = (BranchElement)this.getDefaultRootElement();
        final int offset = defaultDocumentEvent.getOffset();
        final int length = defaultDocumentEvent.getLength();
        final int elementIndex = branchElement.getElementIndex(offset);
        final Element element = branchElement.getElement(elementIndex);
        final int startOffset = element.getStartOffset();
        final int endOffset = element.getEndOffset();
        final BranchElement[] array = { (BranchElement)this.createBranchElement(branchElement, null) };
        final Element[] array2 = { element };
        if (startOffset != offset) {
            this.added.addElement(this.createLeafElement(array[0], null, startOffset, offset));
        }
        this.added.addElement(this.createLeafElement(array[0], set, offset, offset + length));
        if (endOffset != offset + length) {
            this.added.addElement(this.createLeafElement(array[0], null, offset + length, endOffset));
        }
        final Element[] array3 = new Element[this.added.size()];
        this.added.copyInto(array3);
        defaultDocumentEvent.addEdit(new ElementEdit(branchElement, elementIndex, array2, array));
        array[0].replace(0, 0, array3);
        branchElement.replace(elementIndex, 1, array);
    }
    
    protected void insertUpdate(final DefaultDocumentEvent defaultDocumentEvent, final AttributeSet set) {
        this.removed.removeAllElements();
        this.added.removeAllElements();
        final BranchElement branchElement = (BranchElement)this.getDefaultRootElement();
        int offset = defaultDocumentEvent.getOffset();
        int length = defaultDocumentEvent.getLength();
        if (offset > 0) {
            --offset;
            ++length;
        }
        final int elementIndex = branchElement.getElementIndex(offset);
        final Element element = branchElement.getElement(elementIndex);
        final int startOffset = element.getStartOffset();
        int n = element.getEndOffset();
        int n2 = startOffset;
        try {
            final String text = this.getText(offset, length);
            boolean b = false;
            for (int i = 0; i < length; ++i) {
                if (text.charAt(i) == '\n') {
                    final int n3 = offset + i + 1;
                    this.added.addElement(this.createLeafElement(branchElement, null, n2, n3));
                    n2 = n3;
                    b = true;
                }
            }
            if (b) {
                int n4 = 1;
                this.removed.addElement(element);
                if (offset + length == n && n2 != n && elementIndex + 1 < branchElement.getElementCount()) {
                    ++n4;
                    final Element element2 = branchElement.getElement(elementIndex + 1);
                    this.removed.addElement(element2);
                    n = element2.getEndOffset();
                }
                if (n2 < n) {
                    this.added.addElement(this.createLeafElement(branchElement, null, n2, n));
                }
                final Element[] array = new Element[this.added.size()];
                this.added.copyInto(array);
                final Element[] array2 = new Element[this.removed.size()];
                this.removed.copyInto(array2);
                defaultDocumentEvent.addEdit(new ElementEdit(branchElement, elementIndex, array2, array));
                branchElement.replace(elementIndex, array2.length, array);
            }
            if (Utilities.isComposedTextAttributeDefined(set)) {
                this.insertComposedTextUpdate(defaultDocumentEvent, set);
            }
        }
        catch (BadLocationException ex) {
            throw new Error("Internal error: " + ex.toString());
        }
    }
    
    protected void removeUpdate(final DefaultDocumentEvent defaultDocumentEvent) {
        this.removed.removeAllElements();
        final BranchElement branchElement = (BranchElement)this.getDefaultRootElement();
        final int offset = defaultDocumentEvent.getOffset();
        final int length = defaultDocumentEvent.getLength();
        final int elementIndex = branchElement.getElementIndex(offset);
        final int elementIndex2 = branchElement.getElementIndex(offset + length);
        if (elementIndex != elementIndex2) {
            for (int i = elementIndex; i <= elementIndex2; ++i) {
                this.removed.addElement(branchElement.getElement(i));
            }
            final Element[] array = { this.createLeafElement(branchElement, null, branchElement.getElement(elementIndex).getStartOffset(), branchElement.getElement(elementIndex2).getEndOffset()) };
            final Element[] array2 = new Element[this.removed.size()];
            this.removed.copyInto(array2);
            defaultDocumentEvent.addEdit(new ElementEdit(branchElement, elementIndex, array2, array));
            branchElement.replace(elementIndex, array2.length, array);
        }
        else {
            final Element element = branchElement.getElement(elementIndex);
            if (!element.isLeaf() && Utilities.isComposedTextElement(element.getElement(element.getElementIndex(offset)))) {
                final Element[] array3 = { this.createLeafElement(branchElement, null, element.getStartOffset(), element.getEndOffset()) };
                defaultDocumentEvent.addEdit(new ElementEdit(branchElement, elementIndex, new Element[] { element }, array3));
                branchElement.replace(elementIndex, 1, array3);
            }
        }
    }
}
