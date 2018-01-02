// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.message;

import anon.util.ByteArrayUtil;
import anon.mixminion.fec.FECCodeFactory;

public class FragmentContainer
{
    private byte[] m_id;
    private int FRAGSIZE;
    private byte[][] m_fragments;
    private boolean m_readytoreassemble;
    private int m_counter;
    private int[] m_indizes;
    private int m_numberoffrags;
    private boolean[] m_add;
    
    public FragmentContainer(final byte[] id, final int numberoffrags) {
        this.m_id = null;
        this.FRAGSIZE = 28625;
        this.m_readytoreassemble = false;
        this.m_id = id;
        this.m_numberoffrags = numberoffrags;
        this.m_fragments = new byte[numberoffrags][this.FRAGSIZE];
        this.m_counter = numberoffrags - 1;
        this.m_indizes = new int[numberoffrags];
        this.m_add = new boolean[(int)Math.ceil(1.3333333333333333 * (int)Math.min(16.0, Math.pow(2.0, Math.ceil(Math.log(this.m_numberoffrags) / Math.log(2.0)))))];
    }
    
    public boolean addFragment(final byte[] array, final int n) {
        if (this.m_readytoreassemble) {
            return true;
        }
        if (!this.m_add[n]) {
            this.m_add[n] = true;
            this.m_indizes[this.m_counter] = n;
            this.m_fragments[this.m_counter] = array;
            --this.m_counter;
        }
        return this.m_counter == -1 && (this.m_readytoreassemble = true);
    }
    
    public byte[] getID() {
        return this.m_id;
    }
    
    public byte[] reassembleMessage() {
        final byte[] array = null;
        if (this.m_readytoreassemble) {
            byte[] conc = new byte[0];
            final double n = 1.3333333333333333;
            final int n2 = (int)Math.min(16.0, Math.pow(2.0, Math.ceil(Math.log(this.m_numberoffrags) / Math.log(2.0))));
            FECCodeFactory.getDefault().createFECCode(n2, (int)Math.ceil(n * n2)).decode(this.m_fragments, new int[this.m_numberoffrags], this.m_indizes, 28625, false);
            for (int i = 0; i < n2; ++i) {
                conc = ByteArrayUtil.conc(conc, this.m_fragments[i]);
            }
            return conc;
        }
        return array;
    }
}
