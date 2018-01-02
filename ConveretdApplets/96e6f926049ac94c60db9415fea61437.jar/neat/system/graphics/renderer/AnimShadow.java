// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics.renderer;

import neat.kb;
import neat.system.f;
import neat.bb;

public class AnimShadow extends bb
{
    private static f recycler;
    public int width;
    public int height;
    public int frameTime;
    public kb frames;
    public int transparencyColor;
    public boolean hasTransparency;
    public boolean isRAWAnim;
    public int frameNumber;
    public int frameFirst;
    public int keepingResource;
    int[] intArrayFrames;
    private static /* synthetic */ Class class$Lneat$system$graphics$renderer$AnimShadow;
    private static String[] z;
    
    void a(final int n, final int n2) {
        if (this.frames != null) {
            int n3 = 0;
            int i = 0;
            int n4 = 1;
            while (i < this.frames.d()) {
                final char a = this.a(i++);
                if (n4 != 0) {
                    if (a == ' ') {
                        continue;
                    }
                    ++n3;
                    n4 = 0;
                }
                else {
                    if (a != ' ') {
                        continue;
                    }
                    n4 = 1;
                }
            }
            int n5 = 0;
            this.intArrayFrames = new int[n3];
            int n6 = 0;
            int j = 0;
            int n7 = 1;
            while (j < this.frames.d()) {
                final char a2 = this.a(j++);
                if (n7 != 0) {
                    if (a2 == ' ') {
                        continue;
                    }
                    n6 = a2 - '0';
                    n7 = 0;
                }
                else if (a2 != ' ') {
                    n6 = n6 * 10 + (a2 - '0');
                }
                else {
                    this.intArrayFrames[n5++] = n6;
                    n7 = 1;
                }
            }
            if (n7 == 0) {
                this.intArrayFrames[n5++] = n6;
            }
            return;
        }
        if (this.width == 0 || this.height == 0) {
            throw new RuntimeException(AnimShadow.z[1] + this.width + "," + this.height);
        }
        if (n == 0 || n2 == 0) {
            throw new RuntimeException(AnimShadow.z[0] + n + "," + n2);
        }
        this.intArrayFrames = new int[n / this.width * (n2 / this.height)];
        for (int k = 0; k < this.intArrayFrames.length; ++k) {
            this.intArrayFrames[k] = k;
        }
    }
    
    private char a(final int n) {
        final char b = this.frames.b(n);
        if ((b >= '0' && b <= '9') || b == ' ') {
            return b;
        }
        throw new RuntimeException(AnimShadow.z[2] + b);
    }
    
    public void b() {
        AnimShadow.recycler.a(this);
    }
    
    public static bb newShadow() {
        return (bb)AnimShadow.recycler.a();
    }
    
    public static AnimShadow a() {
        return (AnimShadow)AnimShadow.recycler.a();
    }
    
    public void g() {
        super.g();
        this.width = -1;
        this.height = -1;
        this.frameTime = -1;
        this.frames = null;
        this.hasTransparency = false;
        this.transparencyColor = -1;
        this.intArrayFrames = null;
        this.isRAWAnim = false;
        this.frameNumber = 0;
        this.frameFirst = 0;
        this.keepingResource = -1;
    }
    
    public void h() {
        if (this.frames != null) {
            this.frames.f();
            this.frames = null;
        }
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "R\u0012MA'R\u0018\u001bI&Z\u001b^\u0000/R\u0011^N8R\u0013USv".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = ';';
                            break;
                        }
                        case 1: {
                            c2 = '|';
                            break;
                        }
                        case 2: {
                            c2 = ';';
                            break;
                        }
                        case 3: {
                            c2 = ' ';
                            break;
                        }
                        default: {
                            c2 = 'K';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "R\u0012MA'R\u0018\u001bA%R\u0011\u001bC.W\u0010\u001bD\"V\u0019US\"T\u0012H\u001d".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = ';';
                            break;
                        }
                        case 1: {
                            c4 = '|';
                            break;
                        }
                        case 2: {
                            c4 = ';';
                            break;
                        }
                        case 3: {
                            c4 = ' ';
                            break;
                        }
                        default: {
                            c4 = 'K';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "R\u0012MA'R\u0018\u001bC#Z\u000eZC?^\u000e\u001bI%\u001b\u001aIA&^\u0010RS?\u0006".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = ';';
                            break;
                        }
                        case 1: {
                            c6 = '|';
                            break;
                        }
                        case 2: {
                            c6 = ';';
                            break;
                        }
                        case 3: {
                            c6 = ' ';
                            break;
                        }
                        default: {
                            c6 = 'K';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "U\u0019ZTeH\u0005HT.VR\\R*K\u0014RC8\u0015\u000e^N/^\u000e^Rez\u0012RM\u0018S\u001d_O<".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = ';';
                            break;
                        }
                        case 1: {
                            c8 = '|';
                            break;
                        }
                        case 2: {
                            c8 = ';';
                            break;
                        }
                        case 3: {
                            c8 = ' ';
                            break;
                        }
                        default: {
                            c8 = 'K';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                AnimShadow.z = z;
                AnimShadow.recycler = new f((AnimShadow.class$Lneat$system$graphics$renderer$AnimShadow != null) ? AnimShadow.class$Lneat$system$graphics$renderer$AnimShadow : (AnimShadow.class$Lneat$system$graphics$renderer$AnimShadow = a(AnimShadow.z[3])));
                return;
            }
            continue;
        }
    }
}
