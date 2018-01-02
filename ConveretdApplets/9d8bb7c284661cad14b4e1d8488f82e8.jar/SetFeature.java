import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class SetFeature implements Serializable
{
    private String name;
    private String[] variations;
    
    public SetFeature(final String name, final String[] variations) {
        this.name = name;
        this.variations = variations;
    }
    
    public SetFeature(final String s, final String s2, final String s3, final String s4) {
        this(s, new String[] { s2, s3, s4 });
    }
    
    public String getFeatureName() {
        return this.name;
    }
    
    public String[] getFeatureVariations() {
        return this.variations;
    }
    
    public String getFeatureVariation(final int n) {
        return this.variations[n - 1];
    }
}
