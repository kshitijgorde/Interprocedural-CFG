// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import ch.randelshofer.gui.event.ChangeEvent;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Color;
import ch.randelshofer.gui.event.ChangeListener;
import java.awt.Canvas;

public class MovieSliderAWT extends Canvas implements ChangeListener
{
    private static final int THUMB_WIDTH = 8;
    private static final int THUMB_HEIGHT = 13;
    private static final int HALF_THUMB_WIDTH = 4;
    private BoundedRangeModel model_;
    protected int thumbPos_;
    private int progressPos_;
    private BoundedRangeModel progressModel_;
    
    public MovieSliderAWT() {
        this.model_ = new DefaultBoundedRangeModel();
        this.thumbPos_ = 0;
        this.progressModel_ = new DefaultBoundedRangeModel(1, 0, 0, 1);
        this.model_.addChangeListener(this);
        this.setBackground(Color.lightGray);
    }
    
    public synchronized void setModel(final BoundedRangeModel boundedRangeModel) {
        if (this.model_ != null) {
            this.model_.removeChangeListener(this);
        }
        this.model_ = ((boundedRangeModel == null) ? new DefaultBoundedRangeModel() : boundedRangeModel);
        if (this.model_ != null) {
            this.model_.addChangeListener(this);
        }
        this.repaint();
    }
    
    public BoundedRangeModel getModel() {
        return this.model_;
    }
    
    public Dimension preferredSize() {
        return new Dimension(50, 15);
    }
    
    public Dimension minimumSize() {
        return new Dimension(18, 15);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.isEnabled()) {
            this.moveThumb(n);
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.isEnabled()) {
            this.moveThumb(n);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.isEnabled()) {
            this.moveThumb(n);
        }
        return true;
    }
    
    protected void moveThumb(int n) {
        final int computeProgressPos = this.computeProgressPos();
        if (n <= 4) {
            n = 5;
        }
        if (n >= computeProgressPos - 4) {
            n = computeProgressPos - 4 - 1;
        }
        this.model_.setValue((n - 4 - 1 + computeProgressPos / Math.max(1, 2 * (this.model_.getMaximum() - this.model_.getMinimum()))) / (computeProgressPos - 10) * (this.model_.getMaximum() - this.model_.getMinimum()));
    }
    
    protected int computeThumbPos() {
        final BoundedRangeModel model_ = this.model_;
        if (model_ == null) {
            return 4;
        }
        return (int)((this.computeProgressPos() - 4 + 2) * Math.max(0.0f, model_.getValue() / (model_.getMaximum() - model_.getMinimum()))) + 4;
    }
    
    public synchronized void setProgressModel(final BoundedRangeModel progressModel_) {
        if (this.progressModel_ != null) {
            this.progressModel_.removeChangeListener(this);
        }
        this.progressModel_ = progressModel_;
        if (this.progressModel_ != null) {
            this.progressModel_.addChangeListener(this);
        }
    }
    
    public BoundedRangeModel getProgressModel() {
        return this.progressModel_;
    }
    
    public void paint(final Graphics graphics) {
        this.thumbPos_ = this.computeThumbPos();
        this.progressPos_ = this.computeProgressPos();
        this.paint(graphics, this.thumbPos_, this.progressPos_);
    }
    
    public void paint(final Graphics graphics, int min, final int n) {
        final Dimension size = this.size();
        final int width = size.width;
        final int height = size.height;
        min = Math.min(Math.max(min, 0), width);
        if (!this.isEnabled()) {
            graphics.setColor(Color.gray);
        }
        graphics.drawRect(0, 0, width - 1, height - 1);
        graphics.drawRect(4, 4, width - 8 - 1, height - 9);
        if (this.isEnabled()) {
            graphics.setColor(Color.white);
            graphics.drawLine(1, 1, width - 2, 1);
            graphics.drawLine(1, 2, 1, height - 2);
            graphics.drawLine(5, 5, width - 4 - 3, 5);
            graphics.drawLine(5, 6, 5, height - 6);
            if (n > 0) {
                graphics.setColor(Color.gray);
                graphics.fillRect(6, 6, n - 4, height - 11);
            }
            graphics.setColor(Color.white);
            graphics.drawRect(min - 4 + 1, 1, 6, height - 3);
            graphics.setColor(this.getForeground());
        }
        graphics.drawRect(min - 4, 0, 8, height - 1);
        graphics.drawRect(min - 4 + 2, 2, 4, height - 5);
    }
    
    protected int computeProgressPos() {
        final BoundedRangeModel progressModel_ = this.progressModel_;
        final int n = this.size().width - 8 - 3;
        if (progressModel_ == null) {
            return 6;
        }
        return n * (progressModel_.getValue() / (progressModel_.getMaximum() - progressModel_.getMinimum())) + 4;
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        if (this.computeProgressPos() != this.progressPos_ || this.computeThumbPos() != this.thumbPos_) {
            this.repaint();
        }
    }
    
    public void setEnabled(final boolean enabled) {
        if (enabled != this.isEnabled()) {
            super.setEnabled(enabled);
            this.repaint();
        }
    }
}
