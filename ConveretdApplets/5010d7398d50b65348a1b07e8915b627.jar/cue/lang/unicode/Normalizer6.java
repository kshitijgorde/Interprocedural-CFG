// 
// Decompiled by Procyon v0.5.30
// 

package cue.lang.unicode;

import java.text.Normalizer;

class Normalizer6 extends a
{
    public final String a(final String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFKD);
    }
}
