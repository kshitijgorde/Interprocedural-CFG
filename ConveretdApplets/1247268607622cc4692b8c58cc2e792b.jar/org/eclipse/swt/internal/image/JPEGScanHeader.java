// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import org.eclipse.swt.SWT;

final class JPEGScanHeader extends JPEGVariableSizeSegment
{
    public int[][] componentParameters;
    
    public JPEGScanHeader(final byte[] array) {
        super(array);
    }
    
    public JPEGScanHeader(final LEDataInputStream leDataInputStream) {
        super(leDataInputStream);
        this.initializeComponentParameters();
    }
    
    public int getApproxBitPositionHigh() {
        return this.reference[2 * this.getNumberOfImageComponents() + 7] >> 4;
    }
    
    public int getApproxBitPositionLow() {
        return this.reference[2 * this.getNumberOfImageComponents() + 7] & 0xF;
    }
    
    public int getEndOfSpectralSelection() {
        return this.reference[2 * this.getNumberOfImageComponents() + 6];
    }
    
    public int getNumberOfImageComponents() {
        return this.reference[4];
    }
    
    public int getStartOfSpectralSelection() {
        return this.reference[2 * this.getNumberOfImageComponents() + 5];
    }
    
    void initializeComponentParameters() {
        final int numberOfImageComponents = this.getNumberOfImageComponents();
        this.componentParameters = new int[0][];
        for (int i = 0; i < numberOfImageComponents; ++i) {
            final int n = 5 + i * 2;
            final int n2 = this.reference[n] & 0xFF;
            final int n3 = (this.reference[n + 1] & 0xFF) >> 4;
            final byte b = (byte)(this.reference[n + 1] & 0xF);
            if (this.componentParameters.length <= n2) {
                final int[][] componentParameters = new int[n2 + 1][];
                System.arraycopy(this.componentParameters, 0, componentParameters, 0, this.componentParameters.length);
                this.componentParameters = componentParameters;
            }
            this.componentParameters[n2] = new int[] { n3, b };
        }
    }
    
    public void initializeContents() {
        final int numberOfImageComponents = this.getNumberOfImageComponents();
        final int[][] componentParameters = this.componentParameters;
        if (numberOfImageComponents == 0 || numberOfImageComponents != componentParameters.length) {
            SWT.error(40);
        }
        for (int i = 0; i < numberOfImageComponents; ++i) {
            final int n = i * 2 + 5;
            final int[] array = componentParameters[i];
            this.reference[n] = (byte)(i + 1);
            this.reference[n + 1] = (byte)(array[0] * 16 + array[1]);
        }
    }
    
    public void setEndOfSpectralSelection(final int n) {
        this.reference[2 * this.getNumberOfImageComponents() + 6] = (byte)n;
    }
    
    public void setNumberOfImageComponents(final int n) {
        this.reference[4] = (byte)(n & 0xFF);
    }
    
    public void setStartOfSpectralSelection(final int n) {
        this.reference[2 * this.getNumberOfImageComponents() + 5] = (byte)n;
    }
    
    public int signature() {
        return 65498;
    }
    
    public boolean verifyProgressiveScan() {
        final int startOfSpectralSelection = this.getStartOfSpectralSelection();
        final int endOfSpectralSelection = this.getEndOfSpectralSelection();
        final int approxBitPositionLow = this.getApproxBitPositionLow();
        final int approxBitPositionHigh = this.getApproxBitPositionHigh();
        final int numberOfImageComponents = this.getNumberOfImageComponents();
        return ((startOfSpectralSelection == 0 && endOfSpectralSelection == 0) || (startOfSpectralSelection <= endOfSpectralSelection && endOfSpectralSelection <= 63)) && approxBitPositionLow <= 13 && approxBitPositionHigh <= 13 && (approxBitPositionHigh == 0 || approxBitPositionHigh == approxBitPositionLow + 1) && (startOfSpectralSelection == 0 || (startOfSpectralSelection > 0 && numberOfImageComponents == 1));
    }
    
    public boolean isACProgressiveScan() {
        return this.getStartOfSpectralSelection() != 0 && this.getEndOfSpectralSelection() != 0;
    }
    
    public boolean isDCProgressiveScan() {
        return this.getStartOfSpectralSelection() == 0 && this.getEndOfSpectralSelection() == 0;
    }
    
    public boolean isFirstScan() {
        return this.getApproxBitPositionHigh() == 0;
    }
}
