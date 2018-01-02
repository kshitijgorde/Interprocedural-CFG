import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Gradient
{
    private int[] redGradient;
    private int[] greenGradient;
    private int[] blueGradient;
    private int totalSteps;
    private int currentStep;
    
    public int getSteps() {
        return this.totalSteps;
    }
    
    public int getCurrentStep() {
        return this.currentStep;
    }
    
    public int getRed(final int n) {
        return this.redGradient[n];
    }
    
    public int getGreen(final int n) {
        return this.greenGradient[n];
    }
    
    public int getBlue(final int n) {
        return this.blueGradient[n];
    }
    
    public Color getColor(final int n) {
        return new Color(this.redGradient[n], this.greenGradient[n], this.blueGradient[n]);
    }
    
    public Color getStartColor() {
        return new Color(this.redGradient[0], this.greenGradient[0], this.blueGradient[0]);
    }
    
    public Color getEndColor() {
        return new Color(this.redGradient[this.totalSteps], this.greenGradient[this.totalSteps], this.blueGradient[this.totalSteps]);
    }
    
    public Color getCurrentColor() {
        return new Color(this.redGradient[this.currentStep], this.greenGradient[this.currentStep], this.blueGradient[this.currentStep]);
    }
    
    public Color getNextColor() {
        ++this.currentStep;
        return new Color(this.redGradient[this.currentStep], this.greenGradient[this.currentStep], this.blueGradient[this.currentStep]);
    }
    
    public Color getPreviousColor() {
        --this.currentStep;
        return new Color(this.redGradient[this.currentStep], this.greenGradient[this.currentStep], this.blueGradient[this.currentStep]);
    }
    
    public void changeColors(final Color color, final Color color2) {
        final int n = (color.getRed() - color2.getRed()) / this.totalSteps;
        final int n2 = (color.getGreen() - color2.getGreen()) / this.totalSteps;
        final int n3 = (color.getBlue() - color2.getBlue()) / this.totalSteps;
        this.redGradient[0] = color.getRed();
        for (int i = 1; i < this.totalSteps; ++i) {
            this.redGradient[i] = this.redGradient[i - 1] - n;
        }
        this.greenGradient[0] = color.getGreen();
        for (int j = 1; j < this.totalSteps; ++j) {
            this.greenGradient[j] = this.greenGradient[j - 1] - n2;
        }
        this.blueGradient[0] = color.getBlue();
        for (int k = 1; k < this.totalSteps; ++k) {
            this.blueGradient[k] = this.blueGradient[k - 1] - n3;
        }
    }
    
    public void changeSteps(final int totalSteps) {
        final Color startColor = this.getStartColor();
        final Color endColor = this.getEndColor();
        final int n = (startColor.getRed() - endColor.getRed()) / totalSteps;
        final int n2 = (startColor.getGreen() - endColor.getGreen()) / totalSteps;
        final int n3 = (startColor.getBlue() - endColor.getBlue()) / totalSteps;
        this.redGradient[0] = startColor.getRed();
        for (int i = 1; i < totalSteps; ++i) {
            this.redGradient[i] = this.redGradient[i - 1] - n;
        }
        this.greenGradient[0] = startColor.getGreen();
        for (int j = 1; j < totalSteps; ++j) {
            this.greenGradient[j] = this.greenGradient[j - 1] - n2;
        }
        this.blueGradient[0] = startColor.getBlue();
        for (int k = 1; k < totalSteps; ++k) {
            this.blueGradient[k] = this.blueGradient[k - 1] - n3;
        }
        this.totalSteps = totalSteps;
    }
    
    public Gradient(final Color color, final Color color2, final int totalSteps) {
        this.redGradient = new int[totalSteps];
        this.greenGradient = new int[totalSteps];
        this.blueGradient = new int[totalSteps];
        this.totalSteps = totalSteps;
        final int n = (color.getRed() - color2.getRed()) / totalSteps;
        final int n2 = (color.getGreen() - color2.getGreen()) / totalSteps;
        final int n3 = (color.getBlue() - color2.getBlue()) / totalSteps;
        this.redGradient[0] = color.getRed();
        for (int i = 1; i < totalSteps; ++i) {
            this.redGradient[i] = this.redGradient[i - 1] - n;
        }
        this.greenGradient[0] = color.getGreen();
        for (int j = 1; j < totalSteps; ++j) {
            this.greenGradient[j] = this.greenGradient[j - 1] - n2;
        }
        this.blueGradient[0] = color.getBlue();
        for (int k = 1; k < totalSteps; ++k) {
            this.blueGradient[k] = this.blueGradient[k - 1] - n3;
        }
        this.currentStep = 0;
    }
}
