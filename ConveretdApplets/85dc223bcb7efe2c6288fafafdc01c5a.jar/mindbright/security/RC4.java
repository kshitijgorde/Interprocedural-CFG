// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.security;

public final class RC4 extends Cipher
{
    int x;
    int y;
    byte[] state;
    
    public RC4() {
        this.state = new byte[256];
    }
    
    final int arcfour_byte() {
        final int x = this.x + 1 & 0xFF;
        final int sx = this.state[x];
        final int y = sx + this.y & 0xFF;
        final int sy = this.state[y];
        this.x = x;
        this.y = y;
        this.state[y] = (byte)(sx & 0xFF);
        this.state[x] = (byte)(sy & 0xFF);
        return this.state[sx + sy & 0xFF];
    }
    
    public synchronized void encrypt(final byte[] src, final int srcOff, final byte[] dest, final int destOff, final int len) {
        for (int end = srcOff + len, si = srcOff, di = destOff; si < end; ++si, ++di) {
            dest[di] = (byte)((src[si] ^ this.arcfour_byte()) & 0xFF);
        }
    }
    
    public void decrypt(final byte[] src, final int srcOff, final byte[] dest, final int destOff, final int len) {
        this.encrypt(src, srcOff, dest, destOff, len);
    }
    
    public void setKey(final byte[] key) {
        for (int counter = 0; counter < 256; ++counter) {
            this.state[counter] = (byte)counter;
        }
        int keyindex = 0;
        int stateindex = 0;
        for (int counter = 0; counter < 256; ++counter) {
            final int t = this.state[counter];
            stateindex = (stateindex + key[keyindex] + t & 0xFF);
            final int u = this.state[stateindex];
            this.state[stateindex] = (byte)(t & 0xFF);
            this.state[counter] = (byte)(u & 0xFF);
            if (++keyindex >= key.length) {
                keyindex = 0;
            }
        }
    }
}
