// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;

final class JPEGFrameHeader extends JPEGVariableSizeSegment
{
    int maxVFactor;
    int maxHFactor;
    public int[] componentIdentifiers;
    public int[][] componentParameters;
    
    public JPEGFrameHeader(final byte[] array) {
        super(array);
    }
    
    public JPEGFrameHeader(final LEDataInputStream leDataInputStream) {
        super(leDataInputStream);
        this.initializeComponentParameters();
    }
    
    public int getSamplePrecision() {
        return this.reference[4] & 0xFF;
    }
    
    public int getNumberOfLines() {
        return (this.reference[5] & 0xFF) << 8 | (this.reference[6] & 0xFF);
    }
    
    public int getSamplesPerLine() {
        return (this.reference[7] & 0xFF) << 8 | (this.reference[8] & 0xFF);
    }
    
    public int getNumberOfImageComponents() {
        return this.reference[9] & 0xFF;
    }
    
    public void setSamplePrecision(final int n) {
        this.reference[4] = (byte)(n & 0xFF);
    }
    
    public void setNumberOfLines(final int n) {
        this.reference[5] = (byte)((n & 0xFF00) >> 8);
        this.reference[6] = (byte)(n & 0xFF);
    }
    
    public void setSamplesPerLine(final int n) {
        this.reference[7] = (byte)((n & 0xFF00) >> 8);
        this.reference[8] = (byte)(n & 0xFF);
    }
    
    public void setNumberOfImageComponents(final int n) {
        this.reference[9] = (byte)(n & 0xFF);
    }
    
    public int getMaxHFactor() {
        return this.maxHFactor;
    }
    
    public int getMaxVFactor() {
        return this.maxVFactor;
    }
    
    public void setMaxHFactor(final int maxHFactor) {
        this.maxHFactor = maxHFactor;
    }
    
    public void setMaxVFactor(final int maxVFactor) {
        this.maxVFactor = maxVFactor;
    }
    
    void initializeComponentParameters() {
        final int numberOfImageComponents = this.getNumberOfImageComponents();
        this.componentIdentifiers = new int[numberOfImageComponents];
        int[][] componentParameters = new int[0][];
        int maxHFactor = 1;
        byte maxVFactor = 1;
        for (int i = 0; i < numberOfImageComponents; ++i) {
            final int n = i * 3 + 10;
            final int n2 = this.reference[n] & 0xFF;
            this.componentIdentifiers[i] = n2;
            final int n3 = (this.reference[n + 1] & 0xFF) >> 4;
            final byte b = (byte)(this.reference[n + 1] & 0xF);
            final int n4 = this.reference[n + 2] & 0xFF;
            if (n3 > maxHFactor) {
                maxHFactor = n3;
            }
            if (b > maxVFactor) {
                maxVFactor = b;
            }
            final int[] array = { n4, n3, b, 0, 0 };
            if (componentParameters.length <= n2) {
                final int[][] array2 = new int[n2 + 1][];
                System.arraycopy(componentParameters, 0, array2, 0, componentParameters.length);
                componentParameters = array2;
            }
            componentParameters[n2] = array;
        }
        final int samplesPerLine = this.getSamplesPerLine();
        final int numberOfLines = this.getNumberOfLines();
        final int[] array3 = { 8, 16, 24, 32 };
        for (int j = 0; j < numberOfImageComponents; ++j) {
            final int[] array4 = componentParameters[this.componentIdentifiers[j]];
            final int n5 = array4[1];
            final int n6 = array4[2];
            final int n7 = (samplesPerLine * n5 + maxHFactor - 1) / maxHFactor;
            final int n8 = (numberOfLines * n6 + maxVFactor - 1) / maxVFactor;
            final int roundUpToMultiple = this.roundUpToMultiple(n7, array3[n5 - 1]);
            final int roundUpToMultiple2 = this.roundUpToMultiple(n8, array3[n6 - 1]);
            array4[3] = roundUpToMultiple;
            array4[4] = roundUpToMultiple2;
        }
        this.setMaxHFactor(maxHFactor);
        this.setMaxVFactor(maxVFactor);
        this.componentParameters = componentParameters;
    }
    
    public void initializeContents() {
        final int numberOfImageComponents = this.getNumberOfImageComponents();
        if (numberOfImageComponents == 0 || numberOfImageComponents != this.componentParameters.length) {
            SWT.error(40);
        }
        int maxHFactor = 0;
        int maxVFactor = 0;
        final int[][] componentParameters = this.componentParameters;
        for (int i = 0; i < numberOfImageComponents; ++i) {
            final int n = i * 3 + 10;
            final int[] array = componentParameters[this.componentIdentifiers[i]];
            final int n2 = array[1];
            final int n3 = array[2];
            if (n2 * n3 > 4) {
                SWT.error(40);
            }
            this.reference[n] = (byte)(i + 1);
            this.reference[n + 1] = (byte)(n2 * 16 + n3);
            this.reference[n + 2] = (byte)array[0];
            if (n2 > maxHFactor) {
                maxHFactor = n2;
            }
            if (n3 > maxVFactor) {
                maxVFactor = n3;
            }
        }
        final int samplesPerLine = this.getSamplesPerLine();
        final int numberOfLines = this.getNumberOfLines();
        final int[] array2 = { 8, 16, 24, 32 };
        for (int j = 0; j < numberOfImageComponents; ++j) {
            final int[] array3 = componentParameters[this.componentIdentifiers[j]];
            final int n4 = array3[1];
            final int n5 = array3[2];
            final int n6 = (samplesPerLine * n4 + maxHFactor - 1) / maxHFactor;
            final int n7 = (numberOfLines * n5 + maxVFactor - 1) / maxVFactor;
            final int roundUpToMultiple = this.roundUpToMultiple(n6, array2[n4 - 1]);
            final int roundUpToMultiple2 = this.roundUpToMultiple(n7, array2[n5 - 1]);
            array3[3] = roundUpToMultiple;
            array3[4] = roundUpToMultiple2;
        }
        this.setMaxHFactor(maxHFactor);
        this.setMaxVFactor(maxVFactor);
    }
    
    int roundUpToMultiple(final int n, final int n2) {
        final int n3 = n + n2 - 1;
        return n3 - n3 % n2;
    }
    
    public boolean verify() {
        final int segmentMarker = this.getSegmentMarker();
        return (segmentMarker >= 65472 && segmentMarker <= 65475) || (segmentMarker >= 65477 && segmentMarker <= 65479) || (segmentMarker >= 65481 && segmentMarker <= 65483) || (segmentMarker >= 65485 && segmentMarker <= 65487);
    }
    
    public boolean isProgressive() {
        final int segmentMarker = this.getSegmentMarker();
        return segmentMarker == 65474 || segmentMarker == 65478 || segmentMarker == 65482 || segmentMarker == 65486;
    }
    
    public boolean isArithmeticCoding() {
        return this.getSegmentMarker() >= 65481;
    }
}
