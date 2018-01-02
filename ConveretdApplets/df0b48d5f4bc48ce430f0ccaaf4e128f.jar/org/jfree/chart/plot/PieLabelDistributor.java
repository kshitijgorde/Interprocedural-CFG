// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.plot;

import java.util.List;
import java.util.Collections;

public class PieLabelDistributor extends AbstractPieLabelDistributor
{
    private double minGap;
    
    public PieLabelDistributor(final int labelCount) {
        this.minGap = 4.0;
    }
    
    public void distributeLabels(final double minY, final double height) {
        this.sort();
        if (this.isOverlap()) {
            this.adjustInwards();
        }
        if (this.isOverlap()) {
            this.adjustDownwards(minY, height);
        }
        if (this.isOverlap()) {
            this.adjustUpwards(minY, height);
        }
        if (this.isOverlap()) {
            this.spreadEvenly(minY, height);
        }
    }
    
    private boolean isOverlap() {
        double y = 0.0;
        for (int i = 0; i < this.labels.size(); ++i) {
            final PieLabelRecord plr = this.getPieLabelRecord(i);
            if (y > plr.getLowerY()) {
                return true;
            }
            y = plr.getUpperY();
        }
        return false;
    }
    
    protected void adjustInwards() {
        for (int lower = 0, upper = this.labels.size() - 1; upper > lower; ++lower, --upper) {
            if (lower < upper - 1) {
                final PieLabelRecord r0 = this.getPieLabelRecord(lower);
                final PieLabelRecord r2 = this.getPieLabelRecord(lower + 1);
                if (r2.getLowerY() < r0.getUpperY()) {
                    final double adjust = r0.getUpperY() - r2.getLowerY() + this.minGap;
                    r2.setAllocatedY(r2.getAllocatedY() + adjust);
                }
            }
            final PieLabelRecord r3 = this.getPieLabelRecord(upper - 1);
            final PieLabelRecord r4 = this.getPieLabelRecord(upper);
            if (r3.getUpperY() > r4.getLowerY()) {
                final double adjust = r3.getUpperY() - r4.getLowerY() + this.minGap;
                r4.setAllocatedY(r4.getAllocatedY() + adjust);
            }
        }
    }
    
    protected void adjustDownwards(final double minY, final double height) {
        for (int i = 0; i < this.labels.size() - 1; ++i) {
            final PieLabelRecord record0 = this.getPieLabelRecord(i);
            final PieLabelRecord record2 = this.getPieLabelRecord(i + 1);
            if (record2.getLowerY() < record0.getUpperY()) {
                record2.setAllocatedY(Math.min(minY + height, record0.getUpperY() + this.minGap + record2.getLabelHeight() / 2.0));
            }
        }
    }
    
    protected void adjustUpwards(final double minY, final double height) {
        for (int i = this.labels.size() - 1; i > 0; --i) {
            final PieLabelRecord record0 = this.getPieLabelRecord(i);
            final PieLabelRecord record2 = this.getPieLabelRecord(i - 1);
            if (record2.getUpperY() > record0.getLowerY()) {
                record2.setAllocatedY(Math.max(minY, record0.getLowerY() - this.minGap - record2.getLabelHeight() / 2.0));
            }
        }
    }
    
    protected void spreadEvenly(final double minY, final double height) {
        double y = minY;
        double sumOfLabelHeights = 0.0;
        for (int i = 0; i < this.labels.size(); ++i) {
            sumOfLabelHeights += this.getPieLabelRecord(i).getLabelHeight();
        }
        double gap = Math.max(0.0, height - sumOfLabelHeights);
        if (this.labels.size() > 1) {
            gap /= this.labels.size() - 1;
        }
        for (int j = 0; j < this.labels.size(); ++j) {
            final PieLabelRecord record = this.getPieLabelRecord(j);
            y += record.getLabelHeight() / 2.0;
            record.setAllocatedY(y);
            y = y + record.getLabelHeight() / 2.0 + gap;
        }
    }
    
    public void sort() {
        Collections.sort((List<Comparable>)this.labels);
    }
    
    public String toString() {
        final StringBuffer result = new StringBuffer();
        for (int i = 0; i < this.labels.size(); ++i) {
            result.append(this.getPieLabelRecord(i).toString()).append("\n");
        }
        return result.toString();
    }
}
