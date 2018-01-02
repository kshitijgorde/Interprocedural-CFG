// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Graphics;
import java.awt.Point;
import javax.swing.event.DocumentEvent;
import javax.swing.SizeRequirements;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Vector;

public class ParagraphView extends BoxView implements TabExpander
{
    private int justification;
    private float lineSpacing;
    protected int firstLineIndent;
    private int tabBase;
    private int layoutSpan;
    private Vector layoutPool;
    static char[] tabChars;
    static char[] tabDecimalChars;
    
    static {
        (ParagraphView.tabChars = new char[1])[0] = '\t';
        (ParagraphView.tabDecimalChars = new char[2])[0] = '\t';
        ParagraphView.tabDecimalChars[1] = '.';
    }
    
    public ParagraphView(final Element element) {
        super(element, 1);
        this.layoutSpan = -1;
        this.setPropertiesFromAttributes();
    }
    
    protected void adjustRow(final Row row, final int n, final int n2) {
        final int viewCount = row.getViewCount();
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = -1;
        for (int i = 0; i < viewCount; ++i) {
            final View view = row.getView(i);
            final int breakWeight = view.getBreakWeight(0, n2 + n3, n - n3);
            if (breakWeight >= n4) {
                n4 = breakWeight;
                n6 = i;
                n5 = n3;
                if (breakWeight >= 3000) {
                    break;
                }
            }
            n3 += (int)view.getPreferredSpan(0);
        }
        if (n6 < 0) {
            return;
        }
        final int n7 = n - n5;
        final View view2 = row.getView(n6);
        row.replace(n6, viewCount - n6, new View[] { view2.breakView(0, view2.getStartOffset(), n2 + n5, n7) });
        for (int size = this.layoutPool.size(), j = 0; j < size; ++j) {
            final View view3 = this.layoutPool.elementAt(j);
            if (view3.getParent() == null) {
                view3.setParent(this);
            }
        }
    }
    
    public View breakView(final int n, final float n2, final Shape shape) {
        if (n == 1) {
            if (shape != null) {
                final Rectangle bounds = shape.getBounds();
                this.setSize(bounds.width, bounds.height);
            }
            return this;
        }
        return this;
    }
    
    protected SizeRequirements calculateMinorAxisRequirements(final int n, SizeRequirements sizeRequirements) {
        if (sizeRequirements == null) {
            sizeRequirements = new SizeRequirements();
        }
        float n2 = 0.0f;
        for (int size = this.layoutPool.size(), i = 0; i < size; ++i) {
            n2 += ((View)this.layoutPool.elementAt(i)).getPreferredSpan(n);
        }
        sizeRequirements.minimum = ((n == 0) ? (this.getLeftInset() + this.getRightInset()) : (this.getTopInset() + this.getBottomInset())) + 5;
        sizeRequirements.preferred = Math.max(sizeRequirements.minimum, (int)n2);
        sizeRequirements.maximum = 32767;
        sizeRequirements.alignment = 0.5f;
        return sizeRequirements;
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        this.setPropertiesFromAttributes();
        final Element element = this.getElement();
        final DocumentEvent.ElementChange change = documentEvent.getChange(element);
        if (change != null) {
            this.updateLogicalChildren(change, viewFactory);
        }
        final int offset = documentEvent.getOffset();
        final int n = offset + documentEvent.getLength();
        int elementIndex = element.getElementIndex(offset);
        int elementIndex2 = element.getElementIndex(n - 1);
        if (offset == n && elementIndex2 < elementIndex && elementIndex > 0) {
            elementIndex2 = --elementIndex + 1;
        }
        for (int i = elementIndex; i <= elementIndex2; ++i) {
            ((View)this.layoutPool.elementAt(i)).changedUpdate(documentEvent, null, viewFactory);
        }
        this.layoutSpan = Integer.MAX_VALUE;
        this.preferenceChanged(null, true, true);
        if (shape != null) {
            final Rectangle insideAllocation = this.getInsideAllocation(shape);
            this.layout(insideAllocation.width, insideAllocation.height);
            this.getContainer().repaint(insideAllocation.x, insideAllocation.y, insideAllocation.width, insideAllocation.height);
        }
    }
    
    View createView(final int n) {
        final View view = this.layoutPool.elementAt(this.getElement().getElementIndex(n));
        int n2 = view.getEndOffset();
        final AbstractDocument abstractDocument = (AbstractDocument)this.getDocument();
        if (abstractDocument.getProperty("i18n").equals(Boolean.TRUE)) {
            final Element bidiRootElement = abstractDocument.getBidiRootElement();
            if (bidiRootElement.getElementCount() > 1) {
                n2 = Math.min(bidiRootElement.getElement(bidiRootElement.getElementIndex(n)).getEndOffset(), n2);
            }
        }
        if (n == view.getStartOffset() && n2 == view.getEndOffset()) {
            return view;
        }
        return view.createFragment(n, n2);
    }
    
    protected int findOffsetToCharactersInString(final char[] array, final int n) {
        final int length = array.length;
        final int endOffset = this.getEndOffset();
        final Segment segment = new Segment();
        try {
            this.getDocument().getText(n, endOffset - n, segment);
        }
        catch (BadLocationException ex) {
            return -1;
        }
        for (int i = segment.offset; i < segment.offset + segment.count; ++i) {
            final char c = segment.array[i];
            for (int j = 0; j < length; ++j) {
                if (c == array[j]) {
                    return i - segment.offset + n;
                }
            }
        }
        return -1;
    }
    
    protected boolean flipEastAndWestAtEnds(final int n, final Position.Bias bias) {
        final Document document = this.getDocument();
        return document instanceof AbstractDocument && !((AbstractDocument)document).isLeftToRight(this.getStartOffset(), this.getStartOffset() + 1);
    }
    
    public float getAlignment(final int n) {
        switch (n) {
            case 1: {
                float n2 = 0.5f;
                if (this.getViewCount() != 0) {
                    final int n3 = (int)this.getPreferredSpan(1);
                    final int n4 = (int)this.getView(0).getPreferredSpan(1);
                    n2 = ((n3 != 0) ? (n4 / 2 / n3) : 0.0f);
                }
                return n2;
            }
            case 0: {
                return 0.5f;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    public int getBreakWeight(final int n, final float n2) {
        if (n == 1) {
            return 0;
        }
        return 0;
    }
    
    protected int getClosestPositionTo(final int n, final Position.Bias bias, final Shape shape, final int n2, final Position.Bias[] array, final int n3, final int n4) throws BadLocationException {
        final JTextComponent textComponent = (JTextComponent)this.getContainer();
        final Document document = this.getDocument();
        final AbstractDocument abstractDocument = (document instanceof AbstractDocument) ? ((AbstractDocument)document) : null;
        final View view = this.getView(n3);
        int i = -1;
        array[0] = Position.Bias.Forward;
        for (int j = 0; j < view.getViewCount(); ++j) {
            final View view2 = view.getView(j);
            final int startOffset = view2.getStartOffset();
            if (abstractDocument == null || abstractDocument.isLeftToRight(startOffset, startOffset + 1)) {
                for (i = startOffset; i < view2.getEndOffset(); ++i) {
                    if (textComponent.modelToView(i).getBounds().x >= n4) {
                        return i;
                    }
                }
                --i;
            }
            else {
                for (i = view2.getEndOffset() - 1; i >= startOffset; --i) {
                    if (textComponent.modelToView(i).getBounds().x >= n4) {
                        return i;
                    }
                }
                ++i;
            }
        }
        if (i == -1) {
            return this.getStartOffset();
        }
        return i;
    }
    
    protected View getLayoutView(final int n) {
        return this.layoutPool.elementAt(n);
    }
    
    protected int getLayoutViewCount() {
        return this.layoutPool.size();
    }
    
    protected int getNextNorthSouthVisualPositionFrom(final int n, final Position.Bias bias, final Shape shape, final int n2, final Position.Bias[] array) throws BadLocationException {
        int n3;
        if (n == -1) {
            n3 = ((n2 == 1) ? (this.getViewCount() - 1) : 0);
        }
        else {
            if (bias == Position.Bias.Backward && n > 0) {
                n3 = this.getViewIndexAtPosition(n - 1);
            }
            else {
                n3 = this.getViewIndexAtPosition(n);
            }
            if (n2 == 1) {
                if (n3 == 0) {
                    return -1;
                }
                --n3;
            }
            else if (++n3 >= this.getViewCount()) {
                return -1;
            }
        }
        final JTextComponent textComponent = (JTextComponent)this.getContainer();
        final Caret caret = textComponent.getCaret();
        final Point point = (caret != null) ? caret.getMagicCaretPosition() : null;
        int n4;
        if (point == null) {
            final Rectangle modelToView = textComponent.getUI().modelToView(textComponent, n, bias);
            if (modelToView == null) {
                n4 = 0;
            }
            else {
                n4 = modelToView.getBounds().x;
            }
        }
        else {
            n4 = point.x;
        }
        return this.getClosestPositionTo(n, bias, shape, n2, array, n3, n4);
    }
    
    protected float getPartialSize(int n, final int n2) {
        float n3 = 0.0f;
        this.getViewCount();
        int endOffset;
        for (int elementIndex = this.getElement().getElementIndex(n), size = this.layoutPool.size(); n < n2 && elementIndex < size; n = endOffset) {
            final View view = this.layoutPool.elementAt(elementIndex++);
            endOffset = view.getEndOffset();
            final int min = Math.min(n2, endOffset);
            if (view instanceof TabableView) {
                n3 += ((TabableView)view).getPartialSpan(n, min);
            }
            else {
                if (n != view.getStartOffset() || min != view.getEndOffset()) {
                    return 0.0f;
                }
                n3 += view.getPreferredSpan(0);
            }
        }
        return n3;
    }
    
    protected float getTabBase() {
        return this.tabBase;
    }
    
    protected TabSet getTabSet() {
        return StyleConstants.getTabSet(this.getElement().getAttributes());
    }
    
    protected View getViewAtPosition(final int n, final Rectangle rectangle) {
        final int viewCount = this.getViewCount();
        for (int i = 0; i < viewCount; ++i) {
            final View view = this.getView(i);
            final int startOffset = view.getStartOffset();
            final int endOffset = view.getEndOffset();
            if (n >= startOffset && n < endOffset) {
                if (rectangle != null) {
                    this.childAllocation(i, rectangle);
                }
                return view;
            }
        }
        if (n == this.getEndOffset()) {
            final View view2 = this.getView(viewCount - 1);
            if (rectangle != null) {
                this.childAllocation(viewCount - 1, rectangle);
            }
            return view2;
        }
        return null;
    }
    
    protected int getViewIndexAtPosition(final int n) {
        if (n < this.getStartOffset() || n >= this.getEndOffset()) {
            return -1;
        }
        for (int i = this.getViewCount() - 1; i >= 0; --i) {
            final View view = this.getView(i);
            if (n >= view.getStartOffset() && n < view.getEndOffset()) {
                return i;
            }
        }
        return -1;
    }
    
    public void insertUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        final Element element = this.getElement();
        final DocumentEvent.ElementChange change = documentEvent.getChange(element);
        if (change != null) {
            this.updateLogicalChildren(change, viewFactory);
        }
        if (change != null && change.getChildrenAdded().length > 0) {
            final int index = change.getIndex();
            final int offset = documentEvent.getOffset();
            if (index > 0 && element.getElement(index - 1).getEndOffset() >= offset) {
                ((View)this.layoutPool.elementAt(index - 1)).insertUpdate(documentEvent, null, viewFactory);
            }
            final int n = index + change.getChildrenAdded().length;
            if (n < this.layoutPool.size()) {
                final int startOffset = element.getElement(n).getStartOffset();
                if (startOffset >= offset && startOffset <= offset + documentEvent.getLength()) {
                    ((View)this.layoutPool.elementAt(n)).insertUpdate(documentEvent, null, viewFactory);
                }
            }
        }
        else {
            final int offset2 = documentEvent.getOffset();
            final int elementIndex = element.getElementIndex(offset2);
            final View view = this.layoutPool.elementAt(elementIndex);
            view.insertUpdate(documentEvent, null, viewFactory);
            if (elementIndex > 0 && view.getStartOffset() == offset2) {
                ((View)this.layoutPool.elementAt(elementIndex - 1)).insertUpdate(documentEvent, null, viewFactory);
            }
        }
        this.layoutSpan = Integer.MAX_VALUE;
        this.preferenceChanged(null, true, true);
        final Rectangle insideAllocation = this.getInsideAllocation(shape);
        if (insideAllocation != null) {
            this.layout(insideAllocation.width, insideAllocation.height);
            this.getContainer().repaint(insideAllocation.x, insideAllocation.y, insideAllocation.width, insideAllocation.height);
        }
    }
    
    protected void layout(final int n, final int n2) {
        if (this.layoutSpan != n) {
            this.rebuildRows(n);
            if (n2 != (int)this.getPreferredSpan(1)) {
                this.getParent().preferenceChanged(this, false, true);
            }
        }
        super.layout(n, n2);
    }
    
    void layoutRow(final Row row, int endOffset) {
        final int n = this.tabBase + this.getLeftInset();
        final int layoutSpan = this.layoutSpan;
        final int endOffset2 = this.getEndOffset();
        final int n2 = n;
        int n3 = n + row.getLeftInset();
        final int n5;
        int n4 = n5 = layoutSpan - (n3 - n2);
        final int n6 = n3;
        boolean b = false;
        while (endOffset < endOffset2 && n4 > 0) {
            View view = this.createView(endOffset);
            int n7;
            if (view instanceof TabableView) {
                n7 = (int)((TabableView)view).getTabbedSpan(n3, this);
            }
            else {
                n7 = (int)view.getPreferredSpan(0);
            }
            if (view.getBreakWeight(0, endOffset, n4) >= 3000) {
                if (row.getViewCount() > 0) {
                    view = view.breakView(0, endOffset, n3, n4);
                    if (view != null) {
                        if (view instanceof TabableView) {
                            n7 = (int)((TabableView)view).getTabbedSpan(n3, this);
                        }
                        else {
                            n7 = (int)view.getPreferredSpan(0);
                        }
                    }
                    else {
                        n7 = 0;
                    }
                }
                b = true;
            }
            n4 -= n7;
            n3 += n7;
            if (view != null) {
                row.append(view);
                endOffset = view.getEndOffset();
            }
            if (b) {
                break;
            }
        }
        if (n4 < 0) {
            this.adjustRow(row, n5, n6);
        }
        else if (row.getViewCount() == 0) {
            row.append(this.createView(endOffset));
        }
        if (this.lineSpacing > 1.0f) {
            final float preferredSpan = row.getPreferredSpan(1);
            final float n8 = preferredSpan * this.lineSpacing - preferredSpan;
            if (n8 > 0.0f) {
                row.setInsets(row.getTopInset(), row.getLeftInset(), (short)n8, row.getRightInset());
            }
        }
    }
    
    protected void loadChildren(final ViewFactory viewFactory) {
        this.layoutPool = new Vector();
        final Element element = this.getElement();
        for (int elementCount = element.getElementCount(), i = 0; i < elementCount; ++i) {
            final View create = viewFactory.create(element.getElement(i));
            create.setParent(this);
            this.layoutPool.addElement(create);
        }
    }
    
    public float nextTabStop(float n, final int n2) {
        if (this.justification != 0) {
            return n + 10.0f;
        }
        n -= this.tabBase;
        final TabSet tabSet = this.getTabSet();
        if (tabSet == null) {
            return this.tabBase + ((int)n / 72 + 1) * 72;
        }
        final TabStop tabAfter = tabSet.getTabAfter(n + 0.01f);
        if (tabAfter == null) {
            return this.tabBase + n + 5.0f;
        }
        final int alignment = tabAfter.getAlignment();
        int n3 = 0;
        switch (alignment) {
            default: {
                return this.tabBase + tabAfter.getPosition();
            }
            case 5: {
                return this.tabBase + tabAfter.getPosition();
            }
            case 1:
            case 2: {
                n3 = this.findOffsetToCharactersInString(ParagraphView.tabChars, n2 + 1);
                break;
            }
            case 4: {
                n3 = this.findOffsetToCharactersInString(ParagraphView.tabDecimalChars, n2 + 1);
                break;
            }
        }
        if (n3 == -1) {
            n3 = this.getEndOffset();
        }
        final float partialSize = this.getPartialSize(n2 + 1, n3);
        switch (alignment) {
            case 1:
            case 4: {
                return this.tabBase + Math.max(n, tabAfter.getPosition() - partialSize);
            }
            case 2: {
                return this.tabBase + Math.max(n, tabAfter.getPosition() - partialSize / 2.0f);
            }
            default: {
                return n;
            }
        }
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        this.tabBase = shape.getBounds().x;
        super.paint(graphics, shape);
    }
    
    void rebuildRows(final int layoutSpan) {
        this.layoutSpan = layoutSpan;
        int i = this.getStartOffset();
        final int endOffset = this.getEndOffset();
        this.removeAll();
        for (int size = this.layoutPool.size(), j = 0; j < size; ++j) {
            ((View)this.layoutPool.elementAt(j)).setParent(this);
        }
        int n = 1;
        while (i < endOffset) {
            final int n2 = i;
            final Row row = new Row(this.getElement());
            if (n != 0) {
                row.setInsets((short)0, (short)Math.min(layoutSpan - 5, this.firstLineIndent), (short)0, (short)0);
                n = 0;
            }
            this.append(row);
            this.layoutRow(row, i);
            i = row.getEndOffset();
            if (i <= n2) {
                throw new StateInvariantError("infinite loop in formatting");
            }
        }
    }
    
    public void removeUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        final Element element = this.getElement();
        final DocumentEvent.ElementChange change = documentEvent.getChange(element);
        if (change != null) {
            this.updateLogicalChildren(change, viewFactory);
        }
        if (change == null || change.getChildrenAdded().length == 0) {
            final int offset = documentEvent.getOffset();
            final int elementIndex = element.getElementIndex(offset);
            ((View)this.layoutPool.elementAt(elementIndex)).removeUpdate(documentEvent, null, viewFactory);
            if (elementIndex > 0 && element.getElement(elementIndex).getStartOffset() == offset) {
                ((View)this.layoutPool.elementAt(elementIndex - 1)).removeUpdate(documentEvent, null, viewFactory);
            }
        }
        this.layoutSpan = Integer.MAX_VALUE;
        this.preferenceChanged(null, true, true);
        if (shape != null) {
            final Rectangle insideAllocation = this.getInsideAllocation(shape);
            this.layout(insideAllocation.width, insideAllocation.height);
            this.getContainer().repaint(insideAllocation.x, insideAllocation.y, insideAllocation.width, insideAllocation.height);
        }
    }
    
    protected void setFirstLineIndent(final float n) {
        this.firstLineIndent = (int)n;
    }
    
    protected void setJustification(final int justification) {
        this.justification = justification;
    }
    
    protected void setLineSpacing(final float lineSpacing) {
        this.lineSpacing = lineSpacing;
    }
    
    protected void setPropertiesFromAttributes() {
        final AttributeSet attributes = this.getAttributes();
        if (attributes != null) {
            this.setParagraphInsets(attributes);
            this.setJustification(StyleConstants.getAlignment(attributes));
            this.lineSpacing = StyleConstants.getLineSpacing(attributes);
            this.firstLineIndent = (int)StyleConstants.getFirstLineIndent(attributes);
        }
    }
    
    void updateLogicalChildren(final DocumentEvent.ElementChange elementChange, final ViewFactory viewFactory) {
        final int index = elementChange.getIndex();
        final Element[] childrenRemoved = elementChange.getChildrenRemoved();
        for (int i = 0; i < childrenRemoved.length; ++i) {
            ((View)this.layoutPool.elementAt(index)).setParent(null);
            this.layoutPool.removeElementAt(index);
        }
        final Element[] childrenAdded = elementChange.getChildrenAdded();
        for (int j = 0; j < childrenAdded.length; ++j) {
            final View create = viewFactory.create(childrenAdded[j]);
            create.setParent(this);
            this.layoutPool.insertElementAt(create, index + j);
        }
    }
    
    class Row extends BoxView
    {
        Row(final Element element) {
            super(element, 0);
        }
        
        protected SizeRequirements calculateMinorAxisRequirements(final int n, final SizeRequirements sizeRequirements) {
            return this.baselineRequirements(n, sizeRequirements);
        }
        
        public float getAlignment(final int n) {
            if (n == 0) {
                switch (ParagraphView.this.justification) {
                    case 0: {
                        return 0.0f;
                    }
                    case 2: {
                        return 1.0f;
                    }
                    case 1:
                    case 3: {
                        return 0.5f;
                    }
                }
            }
            return super.getAlignment(n);
        }
        
        public AttributeSet getAttributes() {
            return ParagraphView.this.getAttributes();
        }
        
        public int getEndOffset() {
            int max = 0;
            for (int viewCount = this.getViewCount(), i = 0; i < viewCount; ++i) {
                max = Math.max(max, this.getView(i).getEndOffset());
            }
            return max;
        }
        
        public int getStartOffset() {
            int min = Integer.MAX_VALUE;
            for (int viewCount = this.getViewCount(), i = 0; i < viewCount; ++i) {
                min = Math.min(min, this.getView(i).getStartOffset());
            }
            return min;
        }
        
        protected View getViewAtPosition(final int n, final Rectangle rectangle) {
            final int viewCount = this.getViewCount();
            for (int i = 0; i < viewCount; ++i) {
                final View view = this.getView(i);
                final int startOffset = view.getStartOffset();
                final int endOffset = view.getEndOffset();
                if (n >= startOffset && n < endOffset) {
                    if (rectangle != null) {
                        this.childAllocation(i, rectangle);
                    }
                    return view;
                }
            }
            if (n == this.getEndOffset()) {
                final View view2 = this.getView(viewCount - 1);
                if (rectangle != null) {
                    this.childAllocation(viewCount - 1, rectangle);
                }
                return view2;
            }
            return null;
        }
        
        protected int getViewIndexAtPosition(final int n) {
            if (n < this.getStartOffset() || n >= this.getEndOffset()) {
                return -1;
            }
            for (int i = this.getViewCount() - 1; i >= 0; --i) {
                final View view = this.getView(i);
                if (n >= view.getStartOffset() && n < view.getEndOffset()) {
                    return i;
                }
            }
            return -1;
        }
        
        protected void layout(final int n, final int n2) {
            if (this.getDocument().getProperty("i18n").equals(Boolean.TRUE)) {
                final int viewCount = this.getViewCount();
                if (viewCount > 1) {
                    final AbstractDocument abstractDocument = (AbstractDocument)this.getDocument();
                    final Element bidiRootElement = ((AbstractDocument)this.getElement().getDocument()).getBidiRootElement();
                    final byte[] array = new byte[viewCount];
                    final View[] array2 = new View[viewCount];
                    for (int i = 0; i < viewCount; ++i) {
                        final View view = this.getView(i);
                        array[i] = (byte)StyleConstants.getBidiLevel(bidiRootElement.getElement(bidiRootElement.getElementIndex(view.getStartOffset())).getAttributes());
                        array2[i] = view;
                    }
                    Bidi.reorderVisually(array, array2);
                    this.replace(0, viewCount, array2);
                }
            }
            super.layout(n, n2);
        }
        
        protected void layoutMinorAxis(final int n, final int n2, final int[] array, final int[] array2) {
            this.baselineLayout(n, n2, array, array2);
        }
        
        protected void loadChildren(final ViewFactory viewFactory) {
        }
        
        public Shape modelToView(final int n, final Shape shape, final Position.Bias bias) throws BadLocationException {
            final View viewAtPosition = this.getViewAtPosition(n, shape.getBounds());
            if (viewAtPosition != null && !viewAtPosition.getElement().isLeaf()) {
                return super.modelToView(n, shape, bias);
            }
            final Rectangle bounds = shape.getBounds();
            final int height = bounds.height;
            final int y = bounds.y;
            final Rectangle bounds2 = super.modelToView(n, shape, bias).getBounds();
            bounds2.height = height;
            bounds2.y = y;
            return bounds2;
        }
    }
}
