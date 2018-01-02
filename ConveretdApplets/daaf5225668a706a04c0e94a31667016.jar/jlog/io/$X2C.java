// 
// Decompiled by Procyon v0.5.30
// 

package jlog.io;

import java.io.Reader;
import java.util.Properties;
import java.util.Dictionary;

public class $X2C extends $OOD
{
    Dictionary $PI;
    
    public String $POD(final String s) {
        Object o = this.$PI.get(s.trim());
        if (o == null && this.$PI instanceof Properties) {
            o = ((Properties)this.$PI).getProperty(s.trim());
        }
        if (o == null) {
            return String.valueOf(super.$ROD) + s + super.$SOD;
        }
        return o.toString();
    }
    
    public $X2C(final Reader reader, final Dictionary $pi) {
        super(reader);
        this.$PI = $pi;
    }
}
