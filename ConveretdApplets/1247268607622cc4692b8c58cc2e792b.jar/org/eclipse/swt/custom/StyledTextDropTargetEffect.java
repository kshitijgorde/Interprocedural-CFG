// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.dnd.DropTargetEffect;

public class StyledTextDropTargetEffect extends DropTargetEffect
{
    static final int CARET_WIDTH = 2;
    static final int SCROLL_HYSTERESIS = 100;
    static final int SCROLL_TOLERANCE = 20;
    int currentOffset;
    long scrollBeginTime;
    int scrollX;
    int scrollY;
    Listener paintListener;
    
    public StyledTextDropTargetEffect(final StyledText styledText) {
        super(styledText);
        this.currentOffset = -1;
        this.scrollX = -1;
        this.scrollY = -1;
        this.paintListener = new Listener() {
            public void handleEvent(final Event event) {
                if (StyledTextDropTargetEffect.this.currentOffset != -1) {
                    final StyledText styledText = (StyledText)StyledTextDropTargetEffect.this.getControl();
                    final Point locationAtOffset = styledText.getLocationAtOffset(StyledTextDropTargetEffect.this.currentOffset);
                    final int lineHeight = styledText.getLineHeight(StyledTextDropTargetEffect.this.currentOffset);
                    event.gc.setBackground(event.display.getSystemColor(2));
                    event.gc.fillRectangle(locationAtOffset.x, locationAtOffset.y, 2, lineHeight);
                }
            }
        };
    }
    
    public void dragEnter(final DropTargetEvent dropTargetEvent) {
        this.currentOffset = -1;
        this.scrollBeginTime = 0L;
        this.scrollX = -1;
        this.scrollY = -1;
        this.getControl().removeListener(9, this.paintListener);
        this.getControl().addListener(9, this.paintListener);
    }
    
    public void dragLeave(final DropTargetEvent dropTargetEvent) {
        final StyledText styledText = (StyledText)this.getControl();
        if (this.currentOffset != -1) {
            this.refreshCaret(styledText, this.currentOffset, -1);
        }
        styledText.removeListener(9, this.paintListener);
        this.scrollBeginTime = 0L;
        this.scrollX = -1;
        this.scrollY = -1;
    }
    
    public void dragOver(final DropTargetEvent dropTargetEvent) {
        final int feedback = dropTargetEvent.feedback;
        final StyledText styledText = (StyledText)this.getControl();
        final Point map = styledText.getDisplay().map(null, styledText, dropTargetEvent.x, dropTargetEvent.y);
        if ((feedback & 0x8) == 0x0) {
            this.scrollBeginTime = 0L;
            final int n = -1;
            this.scrollY = n;
            this.scrollX = n;
        }
        else if (styledText.getCharCount() == 0) {
            this.scrollBeginTime = 0L;
            final int n2 = -1;
            this.scrollY = n2;
            this.scrollX = n2;
        }
        else if (this.scrollX != -1 && this.scrollY != -1 && this.scrollBeginTime != 0L && ((map.x >= this.scrollX && map.x <= this.scrollX + 20) || (map.y >= this.scrollY && map.y <= this.scrollY + 20))) {
            if (System.currentTimeMillis() >= this.scrollBeginTime) {
                final Rectangle clientArea = styledText.getClientArea();
                final GC gc = new GC(styledText);
                final FontMetrics fontMetrics = gc.getFontMetrics();
                gc.dispose();
                final int averageCharWidth = fontMetrics.getAverageCharWidth();
                final int n3 = 10 * averageCharWidth;
                if (map.x < clientArea.x + 3 * averageCharWidth) {
                    styledText.setHorizontalPixel(styledText.getHorizontalPixel() - n3);
                }
                if (map.x > clientArea.width - 3 * averageCharWidth) {
                    styledText.setHorizontalPixel(styledText.getHorizontalPixel() + n3);
                }
                final int lineHeight = styledText.getLineHeight();
                if (map.y < clientArea.y + lineHeight) {
                    styledText.setTopPixel(styledText.getTopPixel() - lineHeight);
                }
                if (map.y > clientArea.height - lineHeight) {
                    styledText.setTopPixel(styledText.getTopPixel() + lineHeight);
                }
                this.scrollBeginTime = 0L;
                final int n4 = -1;
                this.scrollY = n4;
                this.scrollX = n4;
            }
        }
        else {
            this.scrollBeginTime = System.currentTimeMillis() + 100L;
            this.scrollX = map.x;
            this.scrollY = map.y;
        }
        if ((feedback & 0x1) != 0x0) {
            final int[] array = { 0 };
            final int currentOffset = styledText.getOffsetAtPoint(map.x, map.y, array, false) + array[0];
            if (currentOffset != this.currentOffset) {
                this.refreshCaret(styledText, this.currentOffset, currentOffset);
                this.currentOffset = currentOffset;
            }
        }
    }
    
    void refreshCaret(final StyledText styledText, final int n, final int n2) {
        if (n != n2) {
            if (n != -1) {
                final Point locationAtOffset = styledText.getLocationAtOffset(n);
                styledText.redraw(locationAtOffset.x, locationAtOffset.y, 2, styledText.getLineHeight(n), false);
            }
            if (n2 != -1) {
                final Point locationAtOffset2 = styledText.getLocationAtOffset(n2);
                styledText.redraw(locationAtOffset2.x, locationAtOffset2.y, 2, styledText.getLineHeight(n2), false);
            }
        }
    }
    
    public void dropAccept(final DropTargetEvent dropTargetEvent) {
        if (this.currentOffset != -1) {
            ((StyledText)this.getControl()).setSelection(this.currentOffset);
            this.currentOffset = -1;
        }
    }
}
