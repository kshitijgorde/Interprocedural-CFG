// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import java.awt.Rectangle;

public abstract class CompositeView extends View
{
    private static View[] ZERO;
    private View[] children;
    private int nchildren;
    private short left;
    private short right;
    private short top;
    private short bottom;
    private Rectangle childAlloc;
    
    static {
        CompositeView.ZERO = new View[0];
    }
    
    public CompositeView(final Element element) {
        super(element);
        this.children = new View[1];
        this.nchildren = 0;
        this.childAlloc = new Rectangle();
    }
    
    public void append(final View view) {
        this.replace(this.nchildren, 0, new View[] { view });
    }
    
    public void changedUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        Object change = documentEvent.getChange(this.getElement());
        if (change != null && !this.updateChildren((DocumentEvent.ElementChange)change, documentEvent, viewFactory)) {
            change = null;
        }
        this.forwardUpdate((DocumentEvent.ElementChange)change, documentEvent, shape, viewFactory);
        this.updateLayout((DocumentEvent.ElementChange)change, documentEvent, shape);
    }
    
    protected abstract void childAllocation(final int p0, final Rectangle p1);
    
    protected boolean flipEastAndWestAtEnds(final int n, final Position.Bias bias) {
        return false;
    }
    
    void forwardUpdate(final DocumentEvent.ElementChange elementChange, final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        this.getElement();
        final int offset = documentEvent.getOffset();
        int n;
        int max = n = this.getViewIndexAtPosition(offset);
        final View view = (max >= 0) ? this.getView(max) : null;
        if (view != null && view.getStartOffset() == offset && offset > 0) {
            max = Math.max(max - 1, 0);
        }
        if (documentEvent.getType() != DocumentEvent.EventType.REMOVE) {
            n = this.getViewIndexAtPosition(offset + documentEvent.getLength());
            if (n < 0) {
                n = this.getViewCount() - 1;
            }
        }
        int n2;
        int index = n2 = n + 1;
        final Element[] array = (Element[])((elementChange != null) ? elementChange.getChildrenAdded() : null);
        if (array != null && array.length > 0) {
            index = elementChange.getIndex();
            n2 = index + array.length - 1;
        }
        for (int i = max; i <= n; ++i) {
            if (i < index || i > n2) {
                final View view2 = this.getView(i);
                if (view2 != null) {
                    this.forwardUpdateToView(view2, documentEvent, this.getChildAllocation(i, shape), viewFactory);
                }
            }
        }
    }
    
    void forwardUpdateToView(final View view, final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        final DocumentEvent.EventType type = documentEvent.getType();
        if (type == DocumentEvent.EventType.INSERT) {
            view.insertUpdate(documentEvent, shape, viewFactory);
        }
        else if (type == DocumentEvent.EventType.REMOVE) {
            view.removeUpdate(documentEvent, shape, viewFactory);
        }
        else {
            view.changedUpdate(documentEvent, shape, viewFactory);
        }
    }
    
    protected final short getBottomInset() {
        return this.bottom;
    }
    
    public Shape getChildAllocation(final int n, final Shape shape) {
        final Rectangle insideAllocation = this.getInsideAllocation(shape);
        this.childAllocation(n, insideAllocation);
        return insideAllocation;
    }
    
    protected Rectangle getInsideAllocation(final Shape shape) {
        if (shape != null) {
            Rectangle bounds;
            if (shape instanceof Rectangle) {
                bounds = (Rectangle)shape;
            }
            else {
                bounds = shape.getBounds();
            }
            this.childAlloc.setBounds(bounds);
            final Rectangle childAlloc = this.childAlloc;
            childAlloc.x += this.left;
            final Rectangle childAlloc2 = this.childAlloc;
            childAlloc2.y += this.top;
            final Rectangle childAlloc3 = this.childAlloc;
            childAlloc3.width -= this.left + this.right;
            final Rectangle childAlloc4 = this.childAlloc;
            childAlloc4.height -= this.top + this.bottom;
            return this.childAlloc;
        }
        return null;
    }
    
    protected final short getLeftInset() {
        return this.left;
    }
    
    protected int getNextEastWestVisualPositionFrom(final int n, final Position.Bias bias, final Shape shape, final int n2, final Position.Bias[] array) throws BadLocationException {
        final boolean b = n2 == 3;
        final Rectangle insideAllocation = this.getInsideAllocation(shape);
        int n3 = b ? 1 : -1;
        int n4;
        if (n == -1) {
            final View view = b ? this.getView(0) : this.getView(this.getViewCount() - 1);
            this.childAllocation(0, insideAllocation);
            n4 = view.getNextVisualPositionFrom(n, bias, insideAllocation, n2, array);
            if (n4 == -1 && b && this.getViewCount() > 1) {
                final View view2 = this.getView(1);
                final Rectangle insideAllocation2 = this.getInsideAllocation(shape);
                this.childAllocation(1, insideAllocation2);
                n4 = view2.getNextVisualPositionFrom(-1, array[0], insideAllocation2, n2, array);
            }
        }
        else {
            int n5;
            if (bias == Position.Bias.Backward) {
                n5 = this.getViewIndexAtPosition(Math.max(this.getStartOffset(), n - 1));
            }
            else {
                n5 = this.getViewIndexAtPosition(n);
            }
            final View view3 = this.getView(n5);
            this.childAllocation(n5, insideAllocation);
            n4 = view3.getNextVisualPositionFrom(n, bias, insideAllocation, n2, array);
            if (n4 == -1) {
                if (this.flipEastAndWestAtEnds(n, bias)) {
                    n3 *= -1;
                }
                final int n6 = n5 + n3;
                if (n6 >= 0 && n6 < this.getViewCount()) {
                    final View view4 = this.getView(n6);
                    final Rectangle insideAllocation3 = this.getInsideAllocation(shape);
                    this.childAllocation(n6, insideAllocation3);
                    n4 = view4.getNextVisualPositionFrom(-1, bias, insideAllocation3, n2, array);
                    if (n4 == n && n4 != -1 && array[0] != bias) {
                        final Rectangle insideAllocation4 = this.getInsideAllocation(shape);
                        this.childAllocation(n6, insideAllocation4);
                        n4 = view4.getNextVisualPositionFrom(n4, array[0], insideAllocation4, n2, array);
                    }
                }
            }
            else {
                if (this.flipEastAndWestAtEnds(n, bias)) {
                    n3 *= -1;
                }
                final int n7 = n5 + n3;
                if (array[0] != bias && ((n3 == 1 && view3.getEndOffset() == n4) || (n3 == -1 && view3.getStartOffset() == n4)) && n7 >= 0 && n7 < this.getViewCount()) {
                    final View view5 = this.getView(n7);
                    final Rectangle insideAllocation5 = this.getInsideAllocation(shape);
                    this.childAllocation(n7, insideAllocation5);
                    final Position.Bias bias2 = array[0];
                    final int nextVisualPosition = view5.getNextVisualPositionFrom(-1, bias, insideAllocation5, n2, array);
                    if (array[0] == bias) {
                        n4 = nextVisualPosition;
                    }
                    else {
                        array[0] = bias2;
                    }
                }
            }
        }
        return n4;
    }
    
    protected int getNextNorthSouthVisualPositionFrom(final int n, final Position.Bias bias, final Shape shape, final int n2, final Position.Bias[] array) throws BadLocationException {
        if (this.getViewCount() == 0) {
            return n;
        }
        final boolean b = n2 == 1;
        final Rectangle insideAllocation = this.getInsideAllocation(shape);
        int n3;
        if (n == -1) {
            final View view = b ? this.getView(this.getViewCount() - 1) : this.getView(0);
            this.childAllocation(0, insideAllocation);
            n3 = view.getNextVisualPositionFrom(n, bias, insideAllocation, n2, array);
        }
        else {
            int n4;
            if (bias == Position.Bias.Backward && n > 0) {
                n4 = this.getViewIndexAtPosition(n - 1);
            }
            else {
                n4 = this.getViewIndexAtPosition(n);
            }
            final View view2 = this.getView(n4);
            this.childAllocation(n4, insideAllocation);
            n3 = view2.getNextVisualPositionFrom(n, bias, insideAllocation, n2, array);
            if (n3 == -1 && ((b && --n4 >= 0) || (!b && ++n4 < this.getViewCount()))) {
                final View view3 = this.getView(n4);
                final Rectangle insideAllocation2 = this.getInsideAllocation(shape);
                this.childAllocation(n4, insideAllocation2);
                n3 = view3.getNextVisualPositionFrom(-1, bias, insideAllocation2, n2, array);
            }
        }
        return n3;
    }
    
    public int getNextVisualPositionFrom(final int n, final Position.Bias bias, final Shape shape, final int n2, final Position.Bias[] array) throws BadLocationException {
        this.getInsideAllocation(shape);
        switch (n2) {
            case 1: {
                return this.getNextNorthSouthVisualPositionFrom(n, bias, shape, n2, array);
            }
            case 5: {
                return this.getNextNorthSouthVisualPositionFrom(n, bias, shape, n2, array);
            }
            case 3: {
                return this.getNextEastWestVisualPositionFrom(n, bias, shape, n2, array);
            }
            case 7: {
                return this.getNextEastWestVisualPositionFrom(n, bias, shape, n2, array);
            }
            default: {
                throw new IllegalArgumentException("Bad direction: " + n2);
            }
        }
    }
    
    protected final short getRightInset() {
        return this.right;
    }
    
    protected final short getTopInset() {
        return this.top;
    }
    
    public View getView(final int n) {
        return this.children[n];
    }
    
    protected abstract View getViewAtPoint(final int p0, final int p1, final Rectangle p2);
    
    protected View getViewAtPosition(final int n, final Rectangle rectangle) {
        final Element element = this.getElement();
        final int elementIndex = element.getElementIndex(n);
        final Element element2 = element.getElement(elementIndex);
        if (element2 != null && elementIndex < this.getViewCount()) {
            final View view = this.getView(elementIndex);
            if (view.getElement() == element2) {
                if (rectangle != null) {
                    this.childAllocation(elementIndex, rectangle);
                }
                return view;
            }
        }
        return null;
    }
    
    public int getViewCount() {
        return this.nchildren;
    }
    
    protected int getViewIndexAtPosition(final int n) {
        return this.getElement().getElementIndex(n);
    }
    
    public void insert(final int n, final View view) {
        this.replace(n, 0, new View[] { view });
    }
    
    public void insertUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        Object change = documentEvent.getChange(this.getElement());
        if (change != null && !this.updateChildren((DocumentEvent.ElementChange)change, documentEvent, viewFactory)) {
            change = null;
        }
        this.forwardUpdate((DocumentEvent.ElementChange)change, documentEvent, shape, viewFactory);
        this.updateLayout((DocumentEvent.ElementChange)change, documentEvent, shape);
    }
    
    protected abstract boolean isAfter(final int p0, final int p1, final Rectangle p2);
    
    protected abstract boolean isBefore(final int p0, final int p1, final Rectangle p2);
    
    protected void loadChildren(final ViewFactory viewFactory) {
        final Element element = this.getElement();
        final int elementCount = element.getElementCount();
        if (elementCount > 0) {
            final View[] array = new View[elementCount];
            for (int i = 0; i < elementCount; ++i) {
                array[i] = viewFactory.create(element.getElement(i));
            }
            this.replace(0, 0, array);
        }
    }
    
    public Shape modelToView(final int n, final Shape shape, final Position.Bias bias) throws BadLocationException {
        final boolean b = bias == Position.Bias.Backward;
        final int n2 = b ? Math.max(0, n - 1) : n;
        if (b && n2 < this.getStartOffset()) {
            return null;
        }
        int viewIndexAtPosition = this.getViewIndexAtPosition(n2);
        if (viewIndexAtPosition != -1 && viewIndexAtPosition < this.getViewCount()) {
            final View view = this.getView(viewIndexAtPosition);
            if (view != null && n2 >= view.getStartOffset() && n2 < view.getEndOffset()) {
                Shape shape2 = view.modelToView(n, this.getChildAllocation(viewIndexAtPosition, shape), bias);
                if (shape2 == null && view.getEndOffset() == n && ++viewIndexAtPosition < this.getViewCount()) {
                    shape2 = this.getView(viewIndexAtPosition).modelToView(n, this.getChildAllocation(viewIndexAtPosition, shape), bias);
                }
                return shape2;
            }
        }
        throw new BadLocationException("Position not represented by view", n);
    }
    
    public Shape modelToView(final int n, final Position.Bias bias, final int n2, final Position.Bias bias2, final Shape shape) throws BadLocationException {
        if (n == this.getStartOffset() && n2 == this.getEndOffset()) {
            return shape;
        }
        final Rectangle insideAllocation = this.getInsideAllocation(shape);
        final Rectangle rectangle = new Rectangle(insideAllocation);
        final View viewAtPosition = this.getViewAtPosition((bias == Position.Bias.Backward) ? Math.max(0, n - 1) : n, rectangle);
        final Rectangle rectangle2 = new Rectangle(insideAllocation);
        final View viewAtPosition2 = this.getViewAtPosition((bias2 == Position.Bias.Backward) ? Math.max(0, n2 - 1) : n2, rectangle2);
        if (viewAtPosition != viewAtPosition2) {
            for (int viewCount = this.getViewCount(), i = 0; i < viewCount; ++i) {
                final View view;
                if ((view = this.getView(i)) == viewAtPosition || view == viewAtPosition2) {
                    final Rectangle rectangle3 = new Rectangle();
                    Rectangle rectangle4;
                    View view2;
                    if (view == viewAtPosition) {
                        rectangle4 = viewAtPosition.modelToView(n, bias, viewAtPosition.getEndOffset(), Position.Bias.Backward, rectangle).getBounds();
                        view2 = viewAtPosition2;
                    }
                    else {
                        rectangle4 = viewAtPosition2.modelToView(viewAtPosition2.getStartOffset(), Position.Bias.Forward, n2, bias2, rectangle2).getBounds();
                        view2 = viewAtPosition;
                    }
                    View view3;
                    while (++i < viewCount && (view3 = this.getView(i)) != view2) {
                        rectangle3.setBounds(insideAllocation);
                        this.childAllocation(i, rectangle3);
                        rectangle4.add(rectangle3);
                    }
                    if (view2 != null) {
                        Shape shape2;
                        if (view2 == viewAtPosition2) {
                            shape2 = viewAtPosition2.modelToView(viewAtPosition2.getStartOffset(), Position.Bias.Forward, n2, bias2, rectangle2);
                        }
                        else {
                            shape2 = viewAtPosition.modelToView(n, bias, viewAtPosition.getEndOffset(), Position.Bias.Backward, rectangle);
                        }
                        if (shape2 instanceof Rectangle) {
                            rectangle4.add((Rectangle)shape2);
                        }
                        else {
                            rectangle4.add(shape2.getBounds());
                        }
                    }
                    return rectangle4;
                }
            }
            throw new BadLocationException("Position not represented by view", n);
        }
        if (viewAtPosition == null) {
            return shape;
        }
        return viewAtPosition.modelToView(n, bias, n2, bias2, rectangle);
    }
    
    void remove(final int n) {
        this.replace(n, 1, CompositeView.ZERO);
    }
    
    public void removeAll() {
        this.replace(0, this.nchildren, CompositeView.ZERO);
    }
    
    public void removeUpdate(final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        Object change = documentEvent.getChange(this.getElement());
        if (change != null && !this.updateChildren((DocumentEvent.ElementChange)change, documentEvent, viewFactory)) {
            change = null;
        }
        this.forwardUpdate((DocumentEvent.ElementChange)change, documentEvent, shape, viewFactory);
        this.updateLayout((DocumentEvent.ElementChange)change, documentEvent, shape);
    }
    
    public void replace(final int n, final int n2, final View[] array) {
        for (int i = n; i < n + n2; ++i) {
            this.children[i].setParent(null);
            this.children[i] = null;
        }
        final int n3 = array.length - n2;
        final int n4 = n + n2;
        final int n5 = this.nchildren - n4;
        final int n6 = n4 + n3;
        if (this.nchildren + n3 >= this.children.length) {
            final View[] children = new View[Math.max(2 * this.children.length, this.nchildren + n3)];
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
        for (int j = 0; j < array.length; ++j) {
            array[j].setParent(this);
        }
    }
    
    protected final void setInsets(final short top, final short left, final short bottom, final short right) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }
    
    protected final void setParagraphInsets(final AttributeSet set) {
        this.top = (short)StyleConstants.getSpaceAbove(set);
        this.left = (short)StyleConstants.getLeftIndent(set);
        this.bottom = (short)StyleConstants.getSpaceBelow(set);
        this.right = (short)StyleConstants.getRightIndent(set);
    }
    
    public void setParent(final View parent) {
        super.setParent(parent);
        if (parent != null && this.nchildren == 0) {
            this.loadChildren(this.getViewFactory());
        }
    }
    
    boolean updateChildren(final DocumentEvent.ElementChange elementChange, final DocumentEvent documentEvent, final ViewFactory viewFactory) {
        final Element[] childrenRemoved = elementChange.getChildrenRemoved();
        final Element[] childrenAdded = elementChange.getChildrenAdded();
        final View[] array = new View[childrenAdded.length];
        for (int i = 0; i < childrenAdded.length; ++i) {
            array[i] = viewFactory.create(childrenAdded[i]);
        }
        this.replace(elementChange.getIndex(), childrenRemoved.length, array);
        return true;
    }
    
    void updateLayout(final DocumentEvent.ElementChange elementChange, final DocumentEvent documentEvent, final Shape shape) {
        if (elementChange != null && shape != null) {
            this.preferenceChanged(null, true, true);
            this.getContainer().repaint();
        }
    }
    
    public int viewToModel(final float n, final float n2, final Shape shape, final Position.Bias[] array) {
        final Rectangle insideAllocation = this.getInsideAllocation(shape);
        if (this.isBefore((int)n, (int)n2, insideAllocation)) {
            int n3 = -1;
            try {
                n3 = this.getNextVisualPositionFrom(-1, Position.Bias.Forward, shape, 3, array);
            }
            catch (BadLocationException ex) {}
            catch (IllegalArgumentException ex2) {}
            if (n3 == -1) {
                n3 = this.getStartOffset();
                array[0] = Position.Bias.Forward;
            }
            return n3;
        }
        if (this.isAfter((int)n, (int)n2, insideAllocation)) {
            int nextVisualPosition = -1;
            try {
                nextVisualPosition = this.getNextVisualPositionFrom(-1, Position.Bias.Forward, shape, 7, array);
            }
            catch (BadLocationException ex3) {}
            catch (IllegalArgumentException ex4) {}
            if (nextVisualPosition == -1) {
                nextVisualPosition = this.getEndOffset() - 1;
                array[0] = Position.Bias.Forward;
            }
            return nextVisualPosition;
        }
        final View viewAtPoint = this.getViewAtPoint((int)n, (int)n2, insideAllocation);
        if (viewAtPoint != null) {
            return viewAtPoint.viewToModel(n, n2, insideAllocation, array);
        }
        return -1;
    }
}
