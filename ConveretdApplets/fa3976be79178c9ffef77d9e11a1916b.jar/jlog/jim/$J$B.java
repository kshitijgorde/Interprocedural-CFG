// 
// Decompiled by Procyon v0.5.30
// 

package jlog.jim;

import java.net.URL;
import java.io.InputStream;
import java.awt.Image;

class $J$B implements jlog.awt.$I$B.$J$B
{
    $H0B $SHC;
    
    public Image $PC(final String s) throws Exception {
        return this.$SHC.$PD.$PC(s);
    }
    
    $J$B(final $H0B $shc) {
        this.$SHC = $shc;
    }
    
    public String getProperty(final String s, final String s2) {
        return this.$SHC.$UF.getProperty(s, s2);
    }
    
    public InputStream getResourceAsStream(final String s) throws Exception {
        return this.$SHC.$PD.getResourceAsStream(s);
    }
    
    public void showDocument(final URL url, final String s) throws Exception {
        this.$SHC.showDocument(url, s);
    }
}
