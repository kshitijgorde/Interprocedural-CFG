// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import ji.util.d;

public class gm
{
    public int a;
    public String b;
    public String c;
    public String d;
    public int e;
    public String f;
    public boolean g;
    public boolean h;
    public int i;
    public String j;
    public String k;
    private String l;
    public boolean m;
    
    public gm(final int a, final String c, final String b) {
        this.a = a;
        this.c = c;
        this.b = b;
        this.i = 0;
        this.m = false;
    }
    
    public String a() {
        String s = this.b;
        if (this.b == null) {
            s = "name";
        }
        if (this.d != null && !this.b.endsWith(this.d)) {
            s = String.valueOf(String.valueOf(this.b)).concat(String.valueOf(String.valueOf(this.d)));
        }
        return ji.util.d.br(s);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.getClass().getName());
        sb.append(":  sid=").append(this.a).append("; id=".concat(String.valueOf(String.valueOf(this.j)))).append("; directoryName=").append(this.c).append("; filename=").append(this.b).append("; localFilename=").append(this.k).append("; fileExtension=").append(this.d).append("; dataDirectorySid=").append(this.e).append("; dataDirectoryName=").append(this.f).append("; binaryData=").append(this.g).append("; dataObject=").append(this.h).append("; encoding=").append(this.i).append("; mimeType=").append(this.l).append("; selected=").append(this.m);
        return sb.toString();
    }
}
