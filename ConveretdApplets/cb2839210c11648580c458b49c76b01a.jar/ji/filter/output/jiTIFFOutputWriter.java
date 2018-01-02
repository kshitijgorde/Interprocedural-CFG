// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.output;

import java.util.Properties;
import ji.v1event.af;
import ji.document.ad;
import ji.image.cy;
import ji.io.ac;

public class jiTIFFOutputWriter implements ga
{
    private hf a;
    
    public jiTIFFOutputWriter() {
        this.a = new hf();
    }
    
    public String getParentId() {
        return this.a.a();
    }
    
    public void setParentId(final String s) {
        this.a.a(s);
    }
    
    public boolean saveImage(final ac ac, final ac ac2, final String s, final String s2, final cy cy, final ad ad, final boolean b, final boolean b2, final int n, final String s3, final int n2, final int n3, final int n4, final int n5, final boolean b3, final int n6, final int n7, final af af, final boolean b4, final Object o, final boolean b5, final boolean b6, final int n8, final int n9) throws Exception {
        return this.a.a(ac, ac2, s, s2, cy, ad, b, b2, n, s3, n2, n3, n4, n5, b3, n6, n7, af, b4, o, b5, b6, n8, n9);
    }
    
    public void releaseResources() {
        this.a.b();
    }
    
    public String getSaveExtension() {
        return this.a.c();
    }
    
    public boolean saveEnd(final ac ac, final af af, final ad ad, final String s, final boolean[] array) throws Exception {
        return this.a.a(ac, af, ad, s, array);
    }
    
    public boolean saveStart(final int n, final boolean[] array) throws Exception {
        return this.a.a(n, array);
    }
    
    public boolean supportsMultiPage() {
        return this.a.d();
    }
    
    public void setImageOutputParams(final Properties properties) {
        this.a.a(properties);
    }
}
