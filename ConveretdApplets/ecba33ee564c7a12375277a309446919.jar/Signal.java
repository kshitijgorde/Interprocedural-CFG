import java.awt.TextArea;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Signal
{
    int fSigLength;
    int fAmplitude;
    int[] x;
    int[] approxX;
    double[] mag;
    double[] phase;
    boolean fourier;
    FourierControls fFourierControls;
    MagPanel fMagPanel;
    int fCoefs;
    
    public Signal(final int fSigLength, final int n, final FourierControls fFourierControls, final MagPanel fMagPanel) {
        this.fourier = false;
        this.fSigLength = fSigLength;
        this.fAmplitude = n / 2;
        this.fFourierControls = fFourierControls;
        this.fMagPanel = fMagPanel;
        this.x = new int[this.fSigLength];
        this.approxX = new int[this.fSigLength];
        this.doPulse();
    }
    
    public void Drag(int n, int n2, int n3, int n4) {
        if (n3 == n) {
            return;
        }
        this.changed();
        if (n > n3) {
            final int n5 = n;
            n = n3;
            n3 = n5;
            final int n6 = n2;
            n2 = n4;
            n4 = n6;
        }
        for (int i = n; i <= n3; ++i) {
            this.x[i] = this.fAmplitude - (n2 + (n4 - n2) * (i - n) / (n3 - n));
        }
    }
    
    public void Draw(final Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.drawLine(0, this.fAmplitude, this.fSigLength, this.fAmplitude);
        graphics.drawLine(this.fSigLength / 2, 0, this.fSigLength / 2, this.fAmplitude * 2);
        graphics.setColor(Color.black);
        graphics.drawString("One period of x(t)", 3, 13);
        graphics.setColor(Color.blue);
        for (int i = 1; i < this.fSigLength; ++i) {
            graphics.drawLine(i - 1, this.fAmplitude - this.x[i - 1], i, this.fAmplitude - this.x[i]);
        }
        if (this.fourier) {
            graphics.setColor(Color.red);
            for (int j = 1; j < this.fSigLength; ++j) {
                graphics.drawLine(j - 1, this.fAmplitude - this.approxX[j - 1], j, this.fAmplitude - this.approxX[j]);
            }
        }
    }
    
    public void Modify(final int n, final int n2) {
        this.changed();
        this.x[n] = this.fAmplitude - n2;
    }
    
    public void changed() {
        this.fourier = false;
        this.fFourierControls.disablePlusMinus();
        this.fMagPanel.set(null, null, 0);
    }
    
    public void doDecay() {
        final double sqrt = Math.sqrt(this.fAmplitude);
        final double n = -Math.log(sqrt) * 2.0 / this.fSigLength;
        for (int i = 0; i < this.fSigLength; ++i) {
            this.x[i] = (int)(sqrt * Math.exp(n * (i - this.fSigLength / 2)));
        }
        this.changed();
    }
    
    public void doDoublePulse() {
        for (int i = 0; i < this.fSigLength; ++i) {
            if (i > 2 * this.fSigLength / 3 && i < this.fSigLength * 5 / 6) {
                this.x[i] = (int)(0.8 * this.fAmplitude);
            }
            else if (i > this.fSigLength / 6 && i < this.fSigLength / 3) {
                this.x[i] = (int)(-0.8 * this.fAmplitude);
            }
            else {
                this.x[i] = 0;
            }
        }
        this.changed();
    }
    
    public void doNoise() {
        for (int i = 0; i < this.fSigLength; ++i) {
            if (i % 8 == 0) {
                this.x[i] = (int)(Math.random() * 2.0 * this.fAmplitude - this.fAmplitude);
            }
            else {
                this.x[i] = this.x[i - 1] + (int)(Math.random() * 6.0 - 3.0);
            }
        }
        this.changed();
    }
    
    public void doPulse() {
        for (int i = 0; i < this.fSigLength; ++i) {
            if (i > 3 * this.fSigLength / 7 && i < this.fSigLength * 4 / 7) {
                this.x[i] = (int)(0.8 * this.fAmplitude);
            }
            else {
                this.x[i] = (int)(-0.8 * this.fAmplitude);
            }
        }
        this.changed();
    }
    
    public void doSawtooth() {
        for (int i = 0; i < this.fSigLength; ++i) {
            this.x[i] = (int)(0.8 * (this.fAmplitude * 2.0 / this.fSigLength * (i - this.fSigLength / 2)));
        }
        this.changed();
    }
    
    public void doTriangle() {
        for (int i = 0; i < this.fSigLength; ++i) {
            this.x[i] = (int)(0.8 * (this.fAmplitude - this.fAmplitude * 4.0 / this.fSigLength * Math.abs(i - this.fSigLength / 2)));
        }
        this.changed();
    }
    
    public void getCoefs(final TextArea textArea) {
        textArea.setText("    Magnitude:       Phase:\n");
        for (int i = 0; i <= this.fCoefs; ++i) {
            String s = String.valueOf(this.mag[i]);
            String s2 = String.valueOf(this.phase[i]);
            if (s.length() > 5) {
                s = s.substring(0, 6);
            }
            if (s2.length() > 5) {
                s2 = s2.substring(0, 5);
            }
            textArea.appendText(String.valueOf(i) + ".  " + s + "\t     " + s2 + "\n");
        }
    }
    
    public void go(final int fCoefs) {
        this.fCoefs = fCoefs;
        final double n = 6.283185307179586 / this.fSigLength;
        final double[] array = new double[fCoefs + 1];
        final double[] array2 = new double[fCoefs + 1];
        this.mag = new double[fCoefs + 1];
        this.phase = new double[fCoefs + 1];
        for (int i = 0; i < this.fSigLength; ++i) {
            final double[] array3 = array;
            final int n2 = 0;
            array3[n2] += this.x[i] * n;
        }
        final double[] array4 = array;
        final int n3 = 0;
        array4[n3] /= 6.283185307179586;
        array2[0] = 0.0;
        this.mag[0] = Math.abs(array[0]) / 2.0;
        this.phase[0] = ((array[0] >= 0.0 || Math.abs(array[0]) < 1.0E-5) ? 0.0 : 3.141592653589793);
        double n4 = this.mag[0];
        for (int j = 1; j <= fCoefs; ++j) {
            for (int k = 0; k < this.fSigLength; ++k) {
                final double n5 = j * (k - this.fSigLength / 2) * n;
                final double n6 = this.x[k] * n;
                final double[] array5 = array;
                final int n7 = j;
                array5[n7] += n6 * Math.cos(n5);
                final double[] array6 = array2;
                final int n8 = j;
                array6[n8] += n6 * Math.sin(n5);
            }
            final double[] array7 = array;
            final int n9 = j;
            array7[n9] /= 3.141592653589793;
            final double[] array8 = array2;
            final int n10 = j;
            array8[n10] /= 3.141592653589793;
            this.mag[j] = Math.sqrt(array[j] * array[j] + array2[j] * array2[j]);
            this.phase[j] = Math.atan((array2[j] - array2[j - 1]) / (array[j] - array[j - 1]));
            if (array[j] < 0.0) {
                this.phase[j] += 3.141592653589793;
            }
            if (this.phase[j] <= 0.1) {
                this.phase[j] = 0.0;
            }
            if (this.mag[j] > n4) {
                n4 = this.mag[j];
            }
        }
        final int[] array9 = new int[fCoefs + 1];
        final int[] array10 = new int[fCoefs + 1];
        for (int l = 0; l <= fCoefs; ++l) {
            if (this.mag.length == 1) {
                if (this.mag[l] <= 1.0) {
                    array9[l] = 0;
                }
                else {
                    array9[l] = (int)(this.mag[l] * (this.fMagPanel.getSize().height - 8) / (2.0 * n4));
                }
            }
            else {
                array9[l] = (int)(this.mag[l] * (this.fMagPanel.getSize().height - 8) / n4);
            }
            array10[l] = (int)(this.phase[l] * (this.fMagPanel.getSize().height - 8) / 6.283185307179586);
        }
        for (int n11 = 0; n11 < this.fSigLength; ++n11) {
            final double n12 = (n11 - this.fSigLength / 2) * n;
            double n13 = array[0];
            for (int n14 = 1; n14 <= fCoefs; ++n14) {
                final double n15 = n14 * n12;
                n13 += array[n14] * Math.cos(n15) + array2[n14] * Math.sin(n15);
            }
            this.approxX[n11] = (int)n13;
        }
        this.fMagPanel.set(array9, array10, fCoefs);
        this.fourier = true;
    }
    
    public void zero() {
        for (int i = 0; i < this.fSigLength; ++i) {
            this.x[i] = 0;
        }
        this.changed();
    }
}
