// 
// Decompiled by Procyon v0.5.30
// 

package microtex.TeletextOnWeb;

import java.util.BitSet;

public class PageObj
{
    public String m_textData;
    public byte[] m_textFore;
    public byte[] m_textBack;
    public BitSet m_textDouble;
    public BitSet m_textGrid;
    public int m_curPage;
    public int m_curRoll;
    public int m_curOfRoll;
    
    public PageObj(final int page, final int roll, final int ofRoll, final String data, final byte[] fore, final byte[] back, final BitSet dheight, final BitSet grid) {
        this.m_textFore = new byte[960];
        this.m_textBack = new byte[960];
        this.m_textDouble = new BitSet(960);
        this.m_textGrid = new BitSet(960);
        this.m_curPage = page;
        this.m_curRoll = roll;
        this.m_curOfRoll = ofRoll;
        this.m_textData = data;
        for (int k = 0; k < fore.length; ++k) {
            this.m_textFore[k] = fore[k];
            this.m_textBack[k] = back[k];
            if (dheight.get(k)) {
                this.m_textDouble.set(k);
            }
            else {
                this.m_textDouble.clear(k);
            }
            if (grid.get(k)) {
                this.m_textGrid.set(k);
            }
            else {
                this.m_textGrid.clear(k);
            }
        }
    }
}
