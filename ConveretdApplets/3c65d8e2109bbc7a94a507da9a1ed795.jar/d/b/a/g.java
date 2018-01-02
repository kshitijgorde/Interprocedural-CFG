// 
// Decompiled by Procyon v0.5.30
// 

package d.b.a;

import java.io.StreamCorruptedException;

public class g
{
    private w a;
    
    public g(final w a) {
        this.a = a;
    }
    
    public void a(final ad ad) throws StreamCorruptedException {
        switch (ad.a(4)) {
            case 0: {
                ad.if(1);
                break;
            }
            case 1: {
                ad.if(1);
                break;
            }
            case 2: {
                ad.if(4);
                break;
            }
            case 3: {
                ad.if(4);
                break;
            }
            case 4: {
                ad.if(4);
                break;
            }
            case 5: {
                ad.if(4);
                break;
            }
            case 6: {
                ad.if(4);
                break;
            }
            case 7: {
                ad.if(4);
                break;
            }
            case 8: {
                ad.if(8);
                break;
            }
            case 9: {
                this.a.a(ad);
                break;
            }
            case 10: {
                ad.if(16);
                break;
            }
            case 11: {
                ad.if(16);
                break;
            }
            case 12: {
                ad.if(32);
                break;
            }
            case 13: {
                ad.if(32);
                break;
            }
            case 14: {
                ad.if(64);
                break;
            }
            case 15: {
                ad.if(64);
                break;
            }
        }
    }
    
    public void if(final ad ad) throws StreamCorruptedException {
        ad.if(5 + 8 * ad.a(4));
    }
}
