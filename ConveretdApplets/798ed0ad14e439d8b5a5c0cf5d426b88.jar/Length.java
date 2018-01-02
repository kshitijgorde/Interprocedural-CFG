// 
// Decompiled by Procyon v0.5.30
// 

public class Length
{
    private static final String[] unitStrings;
    private int unit;
    private double value;
    
    public Length() {
        this(0.0, 0);
    }
    
    public Length(final double value, final int unit) {
        this.unit = 0;
        if (unit >= Length.unitStrings.length || unit < 0) {
            throw new IllegalArgumentException("unit");
        }
        this.value = value;
        this.unit = unit;
    }
    
    public static final Length create(final int n, final int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("dpi");
        }
        return new Length(n / n2 * 72.0, 0);
    }
    
    public Length(String substring) {
        this.unit = 0;
        try {
            final int length = substring.length();
            boolean b = true;
            if (length > 2) {
                final String lowerCase = substring.substring(length - 2).toLowerCase();
                for (int i = 0; i < Length.unitStrings.length; ++i) {
                    if (Length.unitStrings[i].equals(lowerCase)) {
                        this.unit = i;
                        b = false;
                        break;
                    }
                }
                if (!b) {
                    substring = substring.substring(0, length - 2);
                }
            }
            this.value = Double.valueOf(substring);
            if (b && this.value != 0.0) {
                throw new IllegalArgumentException();
            }
        }
        catch (RuntimeException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    public final int getUnit() {
        return this.unit;
    }
    
    public final double getValue() {
        return this.value;
    }
    
    public final String toString() {
        return Double.toString(this.value) + Length.unitStrings[this.unit];
    }
    
    public final int toUserUnit(final int n, final int n2) {
        double n3 = 0.0;
        if (n2 <= 0) {
            throw new IllegalArgumentException("dpi");
        }
        switch (this.unit) {
            case 0: {
                n3 = this.value * n2 / 72.0;
                break;
            }
            case 1: {
                n3 = this.value * n;
                break;
            }
            case 3: {
                n3 = this.value / 2.5399999618530273 * n2;
                break;
            }
            case 2: {
                n3 = this.value / 25.399999618530273 * n2;
                break;
            }
            case 4: {
                n3 = this.value * n2;
                break;
            }
            case 5: {
                n3 = this.value / 72.0 * n2;
                break;
            }
            case 6: {
                n3 = this.value * n / 2.0;
                break;
            }
        }
        return (int)Math.round(n3);
    }
    
    public final int toUserUnit() {
        return this.toUserUnit(16, 72);
    }
    
    public static final int toUserUnit(final String s, final int n, final int n2) {
        return new Length(s).toUserUnit(n, n2);
    }
    
    static {
        unitStrings = new String[] { "px", "em", "mm", "cm", "in", "pt", "ex" };
    }
}
