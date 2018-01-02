import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class sSkib extends dynamisk
{
    private Image char;
    
    sSkib(final int a, final int int1, final Image do1, final Image char1, final int if1, final int for1) {
        super.a = a;
        super.int = int1;
        super.if = if1;
        super.for = for1;
        super.do = do1;
        this.char = char1;
    }
    
    public Image a() {
        Image image;
        if (super.try == 0) {
            image = super.do;
        }
        else {
            image = this.char;
        }
        return image;
    }
}
