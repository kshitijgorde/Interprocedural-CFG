// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.A.C;

import java.util.HashMap;
import z.A.A.A.A;

public class B extends A
{
    public static final int \u02c0 = 512;
    public static final int \u038a = 632;
    public static final int \u02e1 = 634;
    public static final int \u02e0 = 617;
    public static final int \u02e2 = 552;
    public static final int \u038c = 592;
    public static final int \u037a = 597;
    public static final int \u038e = 622;
    public static final int \u02d1 = 627;
    public static final int \u0393 = 517;
    public static final int \u0396 = 567;
    public static final int \u0388 = 602;
    public static final int \u0386 = 607;
    public static final int \u02c1 = 613;
    public static final int \u0389 = 615;
    public static final int \u0395 = 527;
    public static final int \u0391 = 532;
    public static final int \u02d0 = 522;
    public static final int \u0390 = 537;
    public static final int \u02e3 = 628;
    public static final int \u038f = 542;
    public static final int \u02bf = 547;
    public static final int \u0392 = 572;
    public static final int \u0394 = 577;
    protected static final HashMap \u02e4;
    
    public B() {
        this.A(new C(this));
    }
    
    public String F() {
        return "Iptc";
    }
    
    protected HashMap D() {
        return B.\u02e4;
    }
    
    static {
        (\u02e4 = new HashMap()).put(new Integer(512), "Directory Version");
        B.\u02e4.put(new Integer(632), "Caption/Abstract");
        B.\u02e4.put(new Integer(634), "Writer/Editor");
        B.\u02e4.put(new Integer(617), "Headline");
        B.\u02e4.put(new Integer(552), "Special Instructions");
        B.\u02e4.put(new Integer(592), "By-line");
        B.\u02e4.put(new Integer(597), "By-line Title");
        B.\u02e4.put(new Integer(622), "Credit");
        B.\u02e4.put(new Integer(627), "Source");
        B.\u02e4.put(new Integer(517), "Object Name");
        B.\u02e4.put(new Integer(567), "Date Created");
        B.\u02e4.put(new Integer(602), "City");
        B.\u02e4.put(new Integer(607), "Province/State");
        B.\u02e4.put(new Integer(613), "Country/Primary Location");
        B.\u02e4.put(new Integer(615), "Original Transmission Reference");
        B.\u02e4.put(new Integer(527), "Category");
        B.\u02e4.put(new Integer(532), "Supplemental Category(s)");
        B.\u02e4.put(new Integer(522), "Urgency");
        B.\u02e4.put(new Integer(537), "Keywords");
        B.\u02e4.put(new Integer(628), "Copyright Notice");
        B.\u02e4.put(new Integer(542), "Release Date");
        B.\u02e4.put(new Integer(547), "Release Time");
        B.\u02e4.put(new Integer(572), "Time Created");
        B.\u02e4.put(new Integer(577), "Originating Program");
    }
}
