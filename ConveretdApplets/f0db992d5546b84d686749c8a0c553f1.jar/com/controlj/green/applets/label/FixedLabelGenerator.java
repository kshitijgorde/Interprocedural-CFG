// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.label;

public class FixedLabelGenerator extends LabelGenerator
{
    public FixedLabelGenerator(final String[] labels, final double labelStartValue, final double labelIncrement) {
        if (labels == null) {
            throw new IllegalArgumentException("The label array can't be null");
        }
        super.labels = labels;
        super.labelStart = labelStartValue;
        super.labelEnd = labelStartValue + labelIncrement * (labels.length - 1);
        super.increment = labelIncrement;
    }
    
    public String[] generateLabels(final double minValue, final double maxValue) {
        return super.labels;
    }
}
