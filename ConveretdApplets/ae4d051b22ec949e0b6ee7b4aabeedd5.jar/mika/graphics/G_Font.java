// 
// Decompiled by Procyon v0.5.30
// 

package mika.graphics;

import mika.system.S_Debug;
import mika.system.S_TextReader;
import java.awt.Font;

public class G_Font
{
    Font m_fntFont;
    boolean m_bDump;
    
    public G_Font() {
        this.m_bDump = true;
    }
    
    public G_Font(final G_Font g_Font) {
        this.m_bDump = true;
        this.m_fntFont = g_Font.m_fntFont;
        this.m_bDump = g_Font.m_bDump;
    }
    
    public G_Font(final S_TextReader s_TextReader) throws Exception {
        this.m_bDump = true;
        this.load(s_TextReader);
    }
    
    public void load(final S_TextReader s_TextReader) throws Exception {
        final String stringValue = s_TextReader.readStringValue();
        final int integerValue = s_TextReader.readIntegerValue();
        final String lowerCase = s_TextReader.readStringValue().toLowerCase();
        if (this.m_bDump) {
            S_Debug.print("G_Font: " + stringValue + ", " + integerValue + ", " + lowerCase);
        }
        int n = 0;
        if (lowerCase.indexOf(98) != -1) {
            ++n;
        }
        if (lowerCase.indexOf(105) != -1) {
            n += 2;
        }
        this.m_fntFont = new Font(stringValue, n, integerValue);
    }
    
    public Font getFont() {
        return this.m_fntFont;
    }
}
