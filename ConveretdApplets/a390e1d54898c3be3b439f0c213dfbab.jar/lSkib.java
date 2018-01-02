import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class lSkib extends dynamisk
{
    private Image case;
    
    lSkib(final int a, final int int1, final Image do1, final Image case1, final int if1, final int for1) {
        super.a = a;
        super.int = int1;
        super.if = if1;
        super.for = for1;
        super.do = do1;
        this.case = case1;
    }
    
    public Image a() {
        Image image;
        if (super.try == 0) {
            image = super.do;
        }
        else {
            image = this.case;
        }
        return image;
    }
}
