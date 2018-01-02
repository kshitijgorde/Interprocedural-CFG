// 
// Decompiled by Procyon v0.5.30
// 

public class Com2meFWCT
{
    private static String text0;
    private static String text1;
    private static String text2;
    private static String text3;
    private static String text4;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    
    static {
        Com2meFWCT.text0 = "Com2meFWCT V0                                       ";
        Com2meFWCT.text1 = "more features in the 10 $ ShareWare version       ";
        Com2meFWCT.text2 = "contact: puzzle@ica-d.de                          ";
        Com2meFWCT.text3 = "http://www.ica-d.com/puzzle/puzzle.htm            ";
        Com2meFWCT.text4 = "(c) by ICA, Ltd. All rights reserved              ";
    }
    
    Com2meFWCT() {
        Com2meFWCT.text4 = new String(new char[] { '(', 'c', ')', ' ', 'b', 'y', ' ', 'I', 'C', 'A', ',', ' ', 'L', 't', 'd', '.', ' ', 'A', 'l', 'l', ' ', 'r', 'i', 'g', 'h', 't', 's', ' ', 'r', 'e', 's', 'e', 'r', 'v', 'e', 'd' });
        this.s1 = this.p1(Com2meFWCT.text1);
        this.s2 = this.p1(Com2meFWCT.text2);
        this.s3 = this.p1(Com2meFWCT.text3);
        this.s4 = this.p1(Com2meFWCT.text4);
    }
    
    public String getText1() {
        return this.s1;
    }
    
    public String getText2() {
        return this.s2;
    }
    
    public String getText3() {
        return this.s3;
    }
    
    public String getText4() {
        return this.s4;
    }
    
    private String p1(final String s) {
        return s.trim();
    }
}
