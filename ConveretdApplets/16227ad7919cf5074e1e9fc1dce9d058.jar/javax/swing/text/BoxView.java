// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

import java.awt.Graphics;
import java.awt.Container;
import java.awt.Shape;
import javax.swing.event.DocumentEvent;
import java.awt.Rectangle;
import javax.swing.SizeRequirements;

public class BoxView extends CompositeView
{
    int axis;
    int width;
    int height;
    boolean xValid;
    boolean yValid;
    SizeRequirements xRequest;
    SizeRequirements yRequest;
    boolean xAllocValid;
    int[] xOffsets;
    int[] xSpans;
    boolean yAllocValid;
    int[] yOffsets;
    int[] ySpans;
    
    public BoxView(final Element element, final int axis) {
        super(element);
        this.axis = axis;
        this.xOffsets = new int[0];
        this.xSpans = new int[0];
        this.xValid = false;
        this.xAllocValid = false;
        this.yOffsets = new int[0];
        this.ySpans = new int[0];
        this.yValid = false;
        this.yAllocValid = false;
    }
    
    protected void baselineLayout(final int n, final int n2, final int[] array, final int[] array2) {
        final int n3 = (int)(n * this.getAlignment(n2));
        final int n4 = n - n3;
        for (int viewCount = this.getViewCount(), i = 0; i < viewCount; ++i) {
            final View view = this.getView(i);
            final float alignment = view.getAlignment(n2);
            final int n5 = (int)view.getPreferredSpan(n2);
            int n6 = (int)(n5 * alignment);
            int n7 = n5 - n6;
            if (n5 > n) {
                if ((int)view.getMinimumSpan(n2) < n5) {
                    n6 = n3;
                    n7 = n4;
                }
                else if (view.getResizeWeight(n2) > 0 && view.getMaximumSpan(n2) != n5) {
                    throw new Error("should not happen: " + view.getClass());
                }
            }
            else if (n5 < n && (int)view.getMaximumSpan(n2) > n5) {
                n6 = n3;
                n7 = n4;
            }
            array[i] = n3 - n6;
            array2[i] = n6 + n7;
        }
    }
    
    protected SizeRequirements baselineRequirements(final int n, SizeRequirements sizeRequirements) {
        int max = 0;
        int max2 = 0;
        int n2 = 0;
        for (int viewCount = this.getViewCount(), i = 0; i < viewCount; ++i) {
            final View view = this.getView(i);
            final int n3 = (int)view.getPreferredSpan(n);
            final int n4 = (int)(view.getAlignment(n) * n3);
            max = Math.max(n3 - n4, max);
            max2 = Math.max(n4, max2);
            n2 += view.getResizeWeight(n);
        }
        if (sizeRequirements == null) {
            sizeRequirements = new SizeRequirements();
        }
        sizeRequirements.preferred = max + max2;
        if (n2 != 0) {
            sizeRequirements.maximum = Integer.MAX_VALUE;
            sizeRequirements.minimum = 0;
        }
        else {
            sizeRequirements.maximum = sizeRequirements.preferred;
            sizeRequirements.minimum = sizeRequirements.preferred;
        }
        if (sizeRequirements.preferred > 0) {
            sizeRequirements.alignment = max2 / sizeRequirements.preferred;
        }
        else {
            sizeRequirements.alignment = 0.5f;
        }
        return sizeRequirements;
    }
    
    protected SizeRequirements calculateMajorAxisRequirements(final int n, SizeRequirements sizeRequirements) {
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        for (int viewCount = this.getViewCount(), i = 0; i < viewCount; ++i) {
            final View view = this.getView(i);
            n2 += view.getMinimumSpan(n);
            n3 += view.getPreferredSpan(n);
            n4 += view.getMaximumSpan(n);
        }
        if (sizeRequirements == null) {
            sizeRequirements = new SizeRequirements();
        }
        sizeRequirements.alignment = 0.5f;
        sizeRequirements.minimum = (int)n2;
        sizeRequirements.preferred = (int)n3;
        sizeRequirements.maximum = (int)n4;
        return sizeRequirements;
    }
    
    protected SizeRequirements calculateMinorAxisRequirements(final int n, SizeRequirements sizeRequirements) {
        int max = 0;
        long max2 = 0L;
        int max3 = Integer.MAX_VALUE;
        for (int viewCount = this.getViewCount(), i = 0; i < viewCount; ++i) {
            final View view = this.getView(i);
            max = Math.max((int)view.getMinimumSpan(n), max);
            max2 = Math.max((int)view.getPreferredSpan(n), max2);
            max3 = Math.max((int)view.getMaximumSpan(n), max3);
        }
        if (sizeRequirements == null) {
            sizeRequirements = new SizeRequirements();
            sizeRequirements.alignment = 0.5f;
        }
        sizeRequirements.preferred = (int)max2;
        sizeRequirements.minimum = max;
        sizeRequirements.maximum = max3;
        return sizeRequirements;
    }
    
    void checkRequests() {
        if (this.axis == 0) {
            if (!this.xValid) {
                this.xRequest = this.calculateMajorAxisRequirements(0, this.xRequest);
            }
            if (!this.yValid) {
                this.yRequest = this.calculateMinorAxisRequirements(1, this.yRequest);
            }
        }
        else {
            if (!this.xValid) {
                this.xRequest = this.calculateMinorAxisRequirements(0, this.xRequest);
            }
            if (!this.yValid) {
                this.yRequest = this.calculateMajorAxisRequirements(1, this.yRequest);
            }
        }
        this.yValid = true;
        this.xValid = true;
    }
    
    protected void childAllocation(final int n, final Rectangle rectangle) {
        rectangle.x += this.xOffsets[n];
        rectangle.y += this.yOffsets[n];
        rectangle.width = this.xSpans[n];
        rectangle.height = this.ySpans[n];
    }
    
    protected boolean flipEastAndWestAtEnds(final int n, final Position.Bias bias) {
        if (this.axis == 1) {
            final int viewIndexAtPosition = this.getViewIndexAtPosition((bias == Position.Bias.Backward) ? Math.max(0, n - 1) : n);
            if (viewIndexAtPosition != -1) {
                final View view = this.getView(viewIndexAtPosition);
                if (view != null && view instanceof CompositeView) {
                    return ((CompositeView)view).flipEastAndWestAtEnds(n, bias);
                }
            }
        }
        return false;
    }
    
    void forwardUpdate(final DocumentEvent.ElementChange elementChange, final DocumentEvent documentEvent, final Shape shape, final ViewFactory viewFactory) {
        final boolean allocationValid = this.isAllocationValid();
        super.forwardUpdate(elementChange, documentEvent, shape, viewFactory);
        if (allocationValid && !this.isAllocationValid()) {
            final Container container = this.getContainer();
            if (shape != null && container != null) {
                final int viewIndexAtPosition = this.getViewIndexAtPosition(documentEvent.getOffset());
                final Rectangle insideAllocation = this.getInsideAllocation(shape);
                if (this.axis == 0) {
                    final Rectangle rectangle = insideAllocation;
                    rectangle.x += this.xOffsets[viewIndexAtPosition];
                    final Rectangle rectangle2 = insideAllocation;
                    rectangle2.width -= this.xSpans[viewIndexAtPosition];
                }
                else {
                    final Rectangle rectangle3 = insideAllocation;
                    rectangle3.y += this.yOffsets[viewIndexAtPosition];
                    final Rectangle rectangle4 = insideAllocation;
                    rectangle4.height -= this.ySpans[viewIndexAtPosition];
                }
                container.repaint(insideAllocation.x, insideAllocation.y, insideAllocation.width, insideAllocation.height);
            }
        }
    }
    
    public float getAlignment(final int n) {
        this.checkRequests();
        switch (n) {
            case 0: {
                return this.xRequest.alignment;
            }
            case 1: {
                return this.yRequest.alignment;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    int getAxis() {
        return this.axis;
    }
    
    public Shape getChildAllocation(final int n, final Shape shape) {
        if (shape != null) {
            final Shape childAllocation = super.getChildAllocation(n, shape);
            if (childAllocation != null && !this.isAllocationValid()) {
                final Rectangle rectangle = (Rectangle)((childAllocation instanceof Rectangle) ? childAllocation : childAllocation.getBounds());
                if (rectangle.width == 0 && rectangle.height == 0) {
                    return null;
                }
            }
            return childAllocation;
        }
        return null;
    }
    
    SizeRequirements[] getChildRequests(final int n) {
        final int viewCount = this.getViewCount();
        final SizeRequirements[] array = new SizeRequirements[viewCount];
        for (int i = 0; i < viewCount; ++i) {
            final View view = this.getView(i);
            array[i] = new SizeRequirements((int)view.getMinimumSpan(n), (int)view.getPreferredSpan(n), (int)view.getMaximumSpan(n), view.getAlignment(n));
        }
        return array;
    }
    
    public final int getHeight() {
        return this.height;
    }
    
    public float getMaximumSpan(final int n) {
        this.checkRequests();
        switch (n) {
            case 0: {
                return this.xRequest.maximum + this.getLeftInset() + this.getRightInset();
            }
            case 1: {
                return this.yRequest.maximum + this.getTopInset() + this.getBottomInset();
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    public float getMinimumSpan(final int n) {
        this.checkRequests();
        switch (n) {
            case 0: {
                return this.xRequest.minimum + this.getLeftInset() + this.getRightInset();
            }
            case 1: {
                return this.yRequest.minimum + this.getTopInset() + this.getBottomInset();
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    protected final int getOffset(final int n, final int n2) {
        return ((n == 0) ? this.xOffsets : this.yOffsets)[n2];
    }
    
    public float getPreferredSpan(final int n) {
        this.checkRequests();
        switch (n) {
            case 0: {
                return this.xRequest.preferred + this.getLeftInset() + this.getRightInset();
            }
            case 1: {
                return this.yRequest.preferred + this.getTopInset() + this.getBottomInset();
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    public int getResizeWeight(final int n) {
        this.checkRequests();
        switch (n) {
            case 0: {
                if (this.xRequest.preferred != this.xRequest.minimum && this.xRequest.preferred != this.xRequest.maximum) {
                    return 1;
                }
                return 0;
            }
            case 1: {
                if (this.yRequest.preferred != this.yRequest.minimum && this.yRequest.preferred != this.yRequest.maximum) {
                    return 1;
                }
                return 0;
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + n);
            }
        }
    }
    
    protected final int getSpan(final int n, final int n2) {
        return ((n == 0) ? this.xSpans : this.ySpans)[n2];
    }
    
    protected View getViewAtPoint(final int n, final int n2, final Rectangle rectangle) {
        final int viewCount = this.getViewCount();
        if (this.axis == 0) {
            if (n < rectangle.x + this.xOffsets[0]) {
                this.childAllocation(0, rectangle);
                return this.getView(0);
            }
            for (int i = 0; i < viewCount; ++i) {
                if (n < rectangle.x + this.xOffsets[i]) {
                    this.childAllocation(i - 1, rectangle);
                    return this.getView(i - 1);
                }
            }
            this.childAllocation(viewCount - 1, rectangle);
            return this.getView(viewCount - 1);
        }
        else {
            if (n2 < rectangle.y + this.yOffsets[0]) {
                this.childAllocation(0, rectangle);
                return this.getView(0);
            }
            for (int j = 0; j < viewCount; ++j) {
                if (n2 < rectangle.y + this.yOffsets[j]) {
                    this.childAllocation(j - 1, rectangle);
                    return this.getView(j - 1);
                }
            }
            this.childAllocation(viewCount - 1, rectangle);
            return this.getView(viewCount - 1);
        }
    }
    
    public final int getWidth() {
        return this.width;
    }
    
    protected boolean isAfter(final int n, final int n2, final Rectangle rectangle) {
        if (this.axis == 0) {
            return n > rectangle.width + rectangle.x;
        }
        return n2 > rectangle.height + rectangle.y;
    }
    
    protected boolean isAllocationValid() {
        return this.xAllocValid && this.yAllocValid;
    }
    
    protected boolean isBefore(final int n, final int n2, final Rectangle rectangle) {
        if (this.axis == 0) {
            return n < rectangle.x;
        }
        return n2 < rectangle.y;
    }
    
    protected void layout(final int n, final int n2) {
        this.checkRequests();
        if (this.axis == 0) {
            if (!this.xAllocValid) {
                this.layoutMajorAxis(n, 0, this.xOffsets, this.xSpans);
            }
            if (!this.yAllocValid) {
                this.layoutMinorAxis(n2, 1, this.yOffsets, this.ySpans);
            }
        }
        else {
            if (!this.xAllocValid) {
                this.layoutMinorAxis(n, 0, this.xOffsets, this.xSpans);
            }
            if (!this.yAllocValid) {
                this.layoutMajorAxis(n2, 1, this.yOffsets, this.ySpans);
            }
        }
        this.xAllocValid = true;
        this.yAllocValid = true;
        for (int viewCount = this.getViewCount(), i = 0; i < viewCount; ++i) {
            this.getView(i).setSize(this.xSpans[i], this.ySpans[i]);
        }
    }
    
    void layoutChanged(final int n) {
        if (n == 0) {
            this.xAllocValid = false;
        }
        else {
            this.yAllocValid = false;
        }
    }
    
    protected void layoutMajorAxis(final int n, final int n2, final int[] array, final int[] array2) {
        SizeRequirements.calculateTiledPositions(n, null, this.getChildRequests(n2), array, array2);
    }
    
    protected void layoutMinorAxis(final int n, final int n2, final int[] array, final int[] array2) {
        for (int viewCount = this.getViewCount(), i = 0; i < viewCount; ++i) {
            final View view = this.getView(i);
            final int n3 = (int)view.getMinimumSpan(n2);
            final int n4 = (int)view.getMaximumSpan(n2);
            if (n4 < n) {
                array[i] = (int)((n - n4) * view.getAlignment(n2));
                array2[i] = n4;
            }
            else {
                array[i] = 0;
                array2[i] = Math.max(n3, n);
            }
        }
    }
    
    public Shape modelToView(final int n, final Shape shape, final Position.Bias bias) throws BadLocationException {
        if (!this.isAllocationValid()) {
            final Rectangle bounds = shape.getBounds();
            this.setSize(bounds.width, bounds.height);
        }
        return super.modelToView(n, shape, bias);
    }
    
    public void paint(final Graphics graphics, final Shape shape) {
        final Rectangle bounds = shape.getBounds();
        this.setSize(bounds.width, bounds.height);
        final int viewCount = this.getViewCount();
        final int n = bounds.x + this.getLeftInset();
        final int n2 = bounds.y + this.getTopInset();
        final Rectangle clipBounds = graphics.getClipBounds();
        for (int i = 0; i < viewCount; ++i) {
            bounds.x = n + this.xOffsets[i];
            bounds.y = n2 + this.yOffsets[i];
            bounds.width = this.xSpans[i];
            bounds.height = this.ySpans[i];
            if (bounds.intersects(clipBounds)) {
                this.paintChild(graphics, bounds, i);
            }
        }
    }
    
    protected void paintChild(final Graphics graphics, final Rectangle rectangle, final int n) {
        this.getView(n).paint(graphics, rectangle);
    }
    
    public void preferenceChanged(final View view, final boolean b, final boolean b2) {
        if (b) {
            this.xValid = false;
            this.xAllocValid = false;
        }
        if (b2) {
            this.yValid = false;
            this.yAllocValid = false;
        }
        super.preferenceChanged(view, b, b2);
    }
    
    public void replace(final int n, final int n2, final View[] array) {
        super.replace(n, n2, array);
        final int length = array.length;
        this.xOffsets = this.updateLayoutArray(this.xOffsets, n, length);
        this.xSpans = this.updateLayoutArray(this.xSpans, n, length);
        this.xValid = false;
        this.xAllocValid = false;
        this.yOffsets = this.updateLayoutArray(this.xOffsets, n, length);
        this.ySpans = this.updateLayoutArray(this.xSpans, n, length);
        this.yValid = false;
        this.yAllocValid = false;
    }
    
    void setAxis(final int axis) {
        this.axis = axis;
        this.preferenceChanged(null, true, true);
    }
    
    public void setSize(final float n, final float n2) {
        if ((int)n != this.width) {
            this.xAllocValid = false;
        }
        if ((int)n2 != this.height) {
            this.yAllocValid = false;
        }
        if (!this.xAllocValid || !this.yAllocValid) {
            this.width = (int)n;
            this.height = (int)n2;
            this.layout(this.width - this.getLeftInset() - this.getRightInset(), this.height - this.getTopInset() - this.getBottomInset());
        }
    }
    
    int[] updateLayoutArray(final int[] array, final int n, final int n2) {
        final int viewCount = this.getViewCount();
        final int[] array2 = new int[viewCount];
        System.arraycopy(array, 0, array2, 0, n);
        System.arraycopy(array, n, array2, n + n2, viewCount - n2 - n);
        return array2;
    }
    
    public int viewToModel(final float n, final float n2, final Shape shape, final Position.Bias[] array) {
        if (!this.isAllocationValid()) {
            final Rectangle bounds = shape.getBounds();
            this.setSize(bounds.width, bounds.height);
        }
        return super.viewToModel(n, n2, shape, array);
    }
}
