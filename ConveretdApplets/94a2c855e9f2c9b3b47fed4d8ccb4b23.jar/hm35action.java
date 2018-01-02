import java.awt.Polygon;
import java.net.URL;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class hm35action extends hm35player
{
    public int vu;
    public int vs;
    public int vr;
    public int vq;
    public Vector vp;
    public Vector vo;
    public int vn;
    public int vm;
    public int vl;
    public boolean vk;
    public int af;
    public int vj;
    public int vi;
    public Vector vh;
    public Vector vg;
    public Vector vf;
    public Vector ve;
    public Object vd;
    public Object vc;
    public Vector vb;
    public Vector va;
    public int u9;
    public int[][] u8;
    public boolean u7;
    public Hashtable u6;
    public int u5;
    public int u4;
    public byte[] u3;
    public boolean u2;
    public boolean u1;
    public boolean u0;
    public boolean u_;
    public int uz;
    
    public final int af() {
        if (this.u3 != null) {
            this.u1 = false;
            if (this.u5 >= this.u3.length && this.u_) {
                this.u5 = 0;
            }
            if (this.u5 < this.u3.length) {
                this.u4 = this.u5;
                return this.u3[this.u5++] & 0xFF;
            }
        }
        this.u1 = true;
        return -1;
    }
    
    public final synchronized int af(final byte[] array, final int n, final int n2) {
        int i = 0;
        if (this.u3 != null) {
            while (i < n2) {
                final int af = this.af();
                array[n + i] = (byte)af;
                if (af == -1) {
                    break;
                }
                ++i;
            }
        }
        return i;
    }
    
    public final void runRun() {
        if (this.u1) {
            this.u3 = null;
            this.u5 = 0;
            super.setThreadState((byte)1);
        }
        if (this.u2) {
            super.setThreadState((byte)1);
            return;
        }
        if (this.u3 != null) {
            if (this.u4 == this.u5) {
                this.uz += 100;
            }
            else {
                this.uz = 0;
                this.u4 = this.u5;
            }
            if (this.uz > 500) {
                this.u0 = true;
            }
            if (this.uz > 2000) {
                this.u2 = true;
                this.g5(this.vd);
                this.g5(this.vc);
                System.out.println("No audio device available. Free audio device and restart the browser.");
                super.masterObject.ii.showStatus("No audio device available. Free audio device and restart the browser.");
                super.ae("noAudioDevice");
            }
        }
        super.sleep(100L);
    }
    
    public final Object getDisplayRecord(int n) {
        if (((int[])this.va.elementAt(n))[5] != -1) {
            n = ((int[])this.va.elementAt(n))[5];
        }
        return this.vb.elementAt(n);
    }
    
    private final Object g4(final byte b, final InputStream inputStream) {
        final Object w = super.w(inputStream, (byte)1, b, (byte)0, 0.0f, 0, 0, 0, 0.0f, true);
        super.v(w);
        return w;
    }
    
    private final void g5(final Object o) {
        if (this.u_) {
            this.u1 = true;
        }
        super.t(o);
        if (this.ve != null && !this.ve.isEmpty()) {
            final Enumeration<Object> elements = this.ve.elements();
            while (elements.hasMoreElements()) {
                super.t(elements.nextElement());
            }
            this.ve.removeAllElements();
        }
    }
    
    public final void stop() {
        this.g5(this.vd);
        this.g5(this.vc);
    }
    
    public final void destroy() {
        super.destroyThread();
        this.vh = null;
        this.vb = null;
        this.vg = null;
        this.vf = null;
        this.va = null;
        this.u6 = null;
        System.gc();
    }
    
    public final byte[] getActionData(final int n) {
        return this.vb.elementAt(n);
    }
    
    public final int getRangeIndexForAction(final int n) {
        return ((int[])this.va.elementAt(n))[6];
    }
    
    public final int getActionDataLength(final int n) {
        return ((int[])this.va.elementAt(n))[3];
    }
    
    public final int getActionSubType(final int n) {
        return ((int[])this.va.elementAt(n))[1];
    }
    
    public final int getActionType(final int n) {
        return ((int[])this.va.elementAt(n))[0];
    }
    
    public final Vector getSpatialVector(final int n, final int n2) {
        if (n2 == 0) {
            return this.vg.elementAt(((int[])this.va.elementAt(n))[6]);
        }
        if (n2 == 1) {
            return this.vf.elementAt(((int[])this.va.elementAt(n))[6]);
        }
        return null;
    }
    
    public final int getSpatialNumNodes(final int n) {
        return ((int[])this.vh.elementAt(((int[])this.va.elementAt(n))[6]))[5];
    }
    
    public final int handleAction(int n) {
        if (((int[])this.va.elementAt(n))[5] != -1) {
            n = ((int[])this.va.elementAt(n))[5];
        }
        final int[] array = this.va.elementAt(n);
        final DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(this.vb.elementAt(n)));
        String s = null;
        try {
            final int n2 = array[0];
            final int n3 = array[1];
            int n4 = -1;
            int n5 = 0;
            if ((array[7] & 0x80) != 0x0) {
                for (n4 = 0; n4 < this.u9 && this.u8[n4][0] != ((int[])this.vh.elementAt(array[6]))[2]; ++n4) {}
                if (n4 == this.u9) {
                    return -1;
                }
                if (n2 == 0) {
                    n5 = n3 + 2;
                }
                else {
                    n5 = n2 + n2 + n3 - 9;
                }
                if (this.u8[n4][n5] == n) {
                    return -1;
                }
                this.u8[n4][n5] = n;
                if (this.u8[n4][1] == -1) {
                    this.u8[n4][1] = array[6];
                }
            }
            switch (n2) {
                case 0: {
                    URL url = null;
                    int n6 = array[3];
                    if (n3 == 2) {
                        final short short1 = dataInputStream.readShort();
                        final byte[] array2 = new byte[short1];
                        dataInputStream.readFully(array2);
                        s = super.ac(array2);
                        n6 -= short1 + 2;
                    }
                    final byte[] array3 = new byte[n6];
                    dataInputStream.readFully(array3);
                    final String ac = super.ac(array3);
                    if (ac.length() == 0) {
                        return 1;
                    }
                    if (n3 == 2 && super.masterObject.ie != null) {
                        String s2 = null;
                        switch (super.masterObject.ic) {
                            case 'p': {
                                s2 = super.masterObject.ie.concat(ac);
                                break;
                            }
                            case 'a': {
                                s2 = ac.concat(super.masterObject.ie);
                                break;
                            }
                            default: {
                                s2 = new String(super.masterObject.ie);
                                break;
                            }
                        }
                        if (s2.lastIndexOf("://") == -1) {
                            url = new URL(super.masterObject.ii.getCodeBase().getProtocol(), super.masterObject.ii.getCodeBase().getHost(), super.masterObject.ii.getCodeBase().getPort(), s2);
                        }
                        else {
                            try {
                                url = new URL(s2);
                            }
                            catch (Exception ex) {}
                        }
                        s = super.masterObject.ib;
                    }
                    else {
                        try {
                            url = new URL(ac);
                        }
                        catch (Exception ex2) {
                            url = new URL(super.masterObject.ig, super.masterObject.if.concat(ac));
                        }
                    }
                    super.masterObject.bo(n3, url, s);
                    break;
                }
                case 7: {
                    if (this.u2) {
                        break;
                    }
                    final byte byte1 = dataInputStream.readByte();
                    if ((byte1 & 0x8) != 0x0) {
                        dataInputStream.readFully(new byte[dataInputStream.readShort()]);
                    }
                    byte byte2 = dataInputStream.readByte();
                    dataInputStream.readShort();
                    final byte[] array4 = new byte[dataInputStream.readUnsignedShort()];
                    dataInputStream.readFully(array4);
                    if ((byte1 & 0x1) == 0x0) {
                        byte2 = 1;
                    }
                    if ((byte1 & 0x3) != 0x0) {
                        byte2 = -1;
                    }
                    if (n == -1) {
                        break;
                    }
                    if (byte2 != -1) {
                        if (this.u6.containsKey(new Integer(n)) && System.currentTimeMillis() < this.u6.get(new Integer(n))) {
                            break;
                        }
                        this.u6.remove(new Integer(n));
                        this.u6.put(new Integer(n), new Long((array4.length - ((array4[4] << 24) + (array4[5] << 16) + (array4[6] << 8) + array4[7])) / 8 + System.currentTimeMillis()));
                    }
                    Object g4 = null;
                    if (!this.u0) {
                        g4 = this.g4(byte2, new ByteArrayInputStream(array4));
                    }
                    if (g4 == null) {
                        break;
                    }
                    final int n7 = ((int[])this.vh.elementAt(array[6]))[1];
                    if (n7 == 3) {
                        this.vd = g4;
                        break;
                    }
                    if (n7 == 4) {
                        this.vc = g4;
                        break;
                    }
                    this.ve.addElement(g4);
                    this.vj = ((int[])this.vh.elementAt(array[6]))[2];
                    break;
                }
                case 8: {
                    if (super.masterObject.hz) {
                        String ac2 = null;
                        String s3 = null;
                        for (short n8 = 0; n8 < dataInputStream.readShort(); ++n8) {
                            final byte byte3 = dataInputStream.readByte();
                            final byte byte4 = dataInputStream.readByte();
                            if ((byte4 & 0x1) != 0x0) {
                                final short short2 = dataInputStream.readShort();
                                if ((byte4 & 0x2) != 0x0) {
                                    final byte[] array5 = new byte[short2];
                                    dataInputStream.readFully(array5);
                                    ac2 = super.ac(array5);
                                }
                            }
                            switch (byte3) {
                                case 0: {
                                    s3 = super.masterObject.ii.getParameter(ac2);
                                    if (s3 == null) {
                                        return -1;
                                    }
                                    break;
                                }
                                case 2: {
                                    s = "TARGETMVR";
                                    break;
                                }
                                case 3: {
                                    s = "TARGETURL";
                                    break;
                                }
                            }
                            if (byte3 != 0) {
                                s = super.masterObject.ii.getParameter(s);
                                if (s != null) {
                                    s3 = s3.concat("&" + s + "=" + ac2);
                                }
                            }
                        }
                        super.masterObject.a9(s3);
                        break;
                    }
                    break;
                }
                case 9: {
                    if (n3 != 0) {
                        break;
                    }
                    final short short3 = dataInputStream.readShort();
                    final short short4 = dataInputStream.readShort();
                    String ac3;
                    if ((short3 & 0x1) != 0x0) {
                        final byte[] array6 = new byte[short4];
                        dataInputStream.readFully(array6);
                        ac3 = super.ac(array6);
                    }
                    else {
                        ac3 = new String(Integer.toString(short4));
                    }
                    final byte[] array7 = new byte[dataInputStream.readShort()];
                    dataInputStream.readFully(array7);
                    final Object[] array8 = { new Integer(2), ac3, super.ac(array7) };
                    super.masterObject.equals(array8);
                    if (array8[1] instanceof Boolean && !(boolean)array8[1]) {
                        this.u8[n4][1] = -1;
                        this.u8[n4][n5] = -1;
                        break;
                    }
                    break;
                }
                case 10: {
                    if (n3 == 0) {
                        dataInputStream.readInt();
                        final byte[] array9 = new byte[dataInputStream.readShort()];
                        dataInputStream.readFully(array9);
                        super.ae(super.ac(array9));
                        break;
                    }
                    break;
                }
                case 11: {
                    String string = null;
                    String s4 = null;
                    for (short n9 = 0; n9 < dataInputStream.readShort(); ++n9) {
                        final byte byte5 = dataInputStream.readByte();
                        final short short5 = dataInputStream.readShort();
                        final byte[] array10 = new byte[dataInputStream.readShort()];
                        dataInputStream.readFully(array10);
                        String s5 = super.ac(array10);
                        if ((byte5 & 0x1) == 0x0) {
                            s5 = super.masterObject.ii.getParameter(s5);
                        }
                        if ((byte5 & 0xFFFE) == 0x0) {
                            switch (short5) {
                                case 0: {
                                    string = s5;
                                    break;
                                }
                                case 1: {
                                    s = s5;
                                    break;
                                }
                                case 2: {
                                    s4 = s5;
                                    break;
                                }
                            }
                        }
                    }
                    if (string != null && string.length() > 0) {
                        if (s == null || s.length() == 0) {
                            s = "_blank";
                        }
                        if (s4 != null && s4.length() > 0) {
                            string = String.valueOf(string) + s4;
                        }
                        URL url2;
                        try {
                            url2 = new URL(string);
                        }
                        catch (Exception ex3) {
                            url2 = new URL(super.masterObject.ig, super.masterObject.if.concat(string));
                        }
                        super.masterObject.bo(2, url2, s);
                        break;
                    }
                    break;
                }
            }
        }
        catch (Exception ex4) {}
        catch (Error error) {}
        return 1;
    }
    
    public final boolean g6(final int n, final Vector vector, final Vector vector2, final int n2) {
        final Vector<Integer> vector3 = this.vg.elementAt(n2);
        final Vector<Integer> vector4 = this.vf.elementAt(n2);
        for (int n3 = ((int[])this.vh.elementAt(n2))[5], i = 0; i < n3; ++i) {
            if (!this.g8(vector3.elementAt(i), vector4.elementAt(i), n, vector, vector2)) {
                return false;
            }
        }
        return true;
    }
    
    public final boolean g7(final int n, final Vector vector, final Vector vector2, final int n2) {
        final Vector<Integer> vector3 = this.vg.elementAt(n2);
        final Vector<Integer> vector4 = this.vf.elementAt(n2);
        final int n3 = ((int[])this.vh.elementAt(n2))[5];
        int n4 = 0;
        for (int i = 0; i < n3; ++i) {
            if (!this.g8(vector3.elementAt(i), vector4.elementAt(i), n, vector, vector2)) {
                ++n4;
            }
        }
        return n4 != 0 && n4 != n3;
    }
    
    public final boolean g8(final int n, final int n2, final int n3, final Vector vector, final Vector vector2) {
        final int[] array = new int[n3];
        final int[] array2 = new int[n3];
        for (int i = 0; i < n3; ++i) {
            array[i] = vector.elementAt(i);
            array2[i] = vector2.elementAt(i);
        }
        return new Polygon(array, array2, n3).inside(n, n2);
    }
    
    public final boolean g9(final int n, final int n2, final int n3) {
        return this.g8(n, n2, ((int[])this.vh.elementAt(n3))[5], this.vg.elementAt(n3), this.vf.elementAt(n3));
    }
    
    public final Vector handleMediaEvent(final hm35player hm35player, final byte b, final byte b2, final int n, final int vl, final Vector vp, final Vector vo) {
        boolean b3 = false;
        if ((this.vj == -1 || b == this.vj) && (this.vi == -1 || this.vi != n)) {
            if (this.vi != -1) {
                this.g5(null);
            }
            this.vi = n;
        }
        final Vector<Integer> vector = new Vector<Integer>();
        final Vector<Integer> vector2 = new Vector<Integer>();
        int n2 = 0;
        if (this.vu == 0) {
            return vector;
        }
        for (int i = 0; i < this.vu; ++i) {
            final int[] array = this.vh.elementAt(i);
            if (array[2] == b && (array[1] == 4 || ((b2 == 127 || b2 == 6 || b2 == 7 || b2 == 0) && array[2] == n) || (array[3] <= n && array[4] >= n))) {
                vector2.addElement(new Integer(i));
                ++n2;
            }
        }
        if (b2 == 21) {
            this.u7 = true;
        }
        int n3;
        for (n3 = 0; n3 < this.u9 && this.u8[n3][0] != b; ++n3) {}
        if (n3 == this.u9) {
            this.u8[n3][0] = b;
            final int[][] u8 = this.u8;
            final int n4 = n3;
            final int[][] array2 = u8;
            for (int j = 1; j < 12; ++j) {
                array2[n4][j] = -1;
            }
            ++this.u9;
        }
        else if (b2 != 30 && this.u8[n3][1] != -1) {
            int n5;
            for (n5 = 0; n5 < n2 && vector2.elementAt(n5) != this.u8[n3][1]; ++n5) {}
            if (n5 == n2) {
                final int[][] u9 = this.u8;
                final int n6 = n3;
                final int[][] array3 = u9;
                for (int k = 1; k < 12; ++k) {
                    array3[n6][k] = -1;
                }
            }
        }
        if (n2 != 0 && this.vq != 0) {
            for (int l = 0; l < n2; ++l) {
                final int intValue = vector2.elementAt(l);
                for (int n7 = 0; n7 < this.vq; ++n7) {
                    final int[] array4 = this.va.elementAt(n7);
                    if (intValue == array4[6]) {
                        final int n8 = array4[2];
                        boolean g9 = false;
                        boolean g10 = false;
                        if ((b2 == 127 || b2 == 100 || b2 == 4 || b2 == 101 || b2 == 12) && vp != null && vo != null) {
                            g9 = this.g9(vp.elementAt(0), vo.elementAt(0), intValue);
                            g10 = this.g9(this.vn, this.vm, intValue);
                        }
                        final Integer n9 = new Integer(n7);
                        switch (b2) {
                            case 100: {
                                if (this.vk) {
                                    if (n8 == 6 && g9 && !g10) {
                                        vector.addElement(n9);
                                    }
                                    if (g10 && !g9) {
                                        if (n8 == 7) {
                                            vector.addElement(n9);
                                        }
                                        this.g5(null);
                                    }
                                }
                                if (n8 == 11 && g9) {
                                    vector.addElement(n9);
                                    b3 = true;
                                    break;
                                }
                                break;
                            }
                            case 4: {
                                if ((n8 == 11 || n8 == 4 || n8 == 6) && g9) {
                                    vector.addElement(n9);
                                    break;
                                }
                                break;
                            }
                            case 5: {
                                if ((n8 == 5 || n8 == 7) && this.vk && g10) {
                                    vector.addElement(n9);
                                    break;
                                }
                                break;
                            }
                            case 101: {
                                if (vl != 0) {
                                    if (n == this.af) {
                                        if (n8 == 8 && vl != 0 && this.g7(vl, vp, vo, intValue) && !this.g7(this.vl, this.vp, this.vo, intValue)) {
                                            vector.addElement(n9);
                                        }
                                        if (n8 == 2 && this.g6(vl, vp, vo, intValue) && !this.g6(this.vl, this.vp, this.vo, intValue)) {
                                            vector.addElement(n9);
                                        }
                                        if (n8 == 1 && !this.g7(vl, vp, vo, intValue) && this.g7(this.vl, this.vp, this.vo, intValue)) {
                                            vector.addElement(n9);
                                        }
                                        if (n8 == 3 && !this.g6(vl, vp, vo, intValue) && this.g6(this.vl, this.vp, this.vo, intValue)) {
                                            vector.addElement(n9);
                                        }
                                    }
                                    else {
                                        if (n8 == 8 && this.g7(vl, vp, vo, intValue)) {
                                            vector.addElement(n9);
                                        }
                                        if (n8 == 2 && this.g6(vl, vp, vo, intValue)) {
                                            vector.addElement(n9);
                                        }
                                        if (n8 == 6 && this.vk && g10) {
                                            vector.addElement(n9);
                                        }
                                    }
                                    if (n8 == 11 && g10) {
                                        vector.addElement(n9);
                                        break;
                                    }
                                    break;
                                }
                                else {
                                    if ((vp == null || vo == null) && n8 == 101) {
                                        vector.addElement(n9);
                                        break;
                                    }
                                    break;
                                }
                                break;
                            }
                            case 12:
                            case 13:
                            case 14: {
                                if ((n8 == 0 || n8 == 9 || n8 == 10 || n8 == 11) && this.vk && g9) {
                                    vector.addElement(n9);
                                    break;
                                }
                                break;
                            }
                            case 21: {
                                this.u7 = true;
                            }
                            case 20: {
                                if (b2 == n8) {
                                    vector.addElement(n9);
                                    break;
                                }
                                break;
                            }
                            case 30: {
                                if (n8 == 30) {
                                    vector.addElement(n9);
                                    break;
                                }
                                break;
                            }
                            case 31: {
                                if (n8 == 31) {
                                    vector.addElement(n9);
                                    break;
                                }
                                break;
                            }
                            case Byte.MAX_VALUE: {
                                if (array4[0] == 6 && g9) {
                                    vector.addElement(n9);
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        switch (b2) {
            case 4: {
                this.vk = true;
                this.vn = vp.elementAt(0);
                this.vm = vo.elementAt(0);
                break;
            }
            case 12:
            case 13:
            case 14:
            case 100: {
                if (b3) {
                    this.vk = true;
                }
                this.vn = vp.elementAt(0);
                this.vm = vo.elementAt(0);
                break;
            }
            case 5: {
                this.vk = false;
                this.g5(this.vd);
                this.g5(this.vc);
                break;
            }
            case 101: {
                this.af = n;
                this.vl = vl;
                this.vp = vp;
                this.vo = vo;
                break;
            }
        }
        return vector;
    }
    
    public final int consumeFrame(final byte b, final int n, final byte b2, final short n2, final int n3, final int n4, final DataInputStream dataInputStream) {
        if (this.vr > 0) {
            --this.vr;
            return 0;
        }
        ++this.vs;
        int int1 = -999999;
        int int2 = 999999;
        try {
            if (b2 == 4) {
                for (short n5 = 0; n5 < n2; ++n5) {
                    final int[] array = new int[6];
                    this.vh.addElement(array);
                    final byte byte1 = dataInputStream.readByte();
                    array[1] = byte1;
                    final byte byte2 = dataInputStream.readByte();
                    array[5] = ((byte1 != 5) ? 4 : (byte2 * 2));
                    dataInputStream.readUnsignedShort();
                    array[2] = b;
                    array[0] = dataInputStream.readInt();
                    final Vector<Integer> vector = new Vector<Integer>();
                    final Vector<Integer> vector2 = new Vector<Integer>();
                    for (byte b3 = 0; b3 < byte2; ++b3) {
                        if (((0x1 & dataInputStream.readShort()) == 0x0 && byte1 != 2) || (byte1 != 5 && byte1 == 2 && b3 == 0)) {
                            int1 = dataInputStream.readInt();
                            int2 = dataInputStream.readInt();
                            if (byte1 == 3 || byte1 == 4) {
                                int1 = -999999;
                                int2 = 999999;
                            }
                        }
                        else {
                            short short1 = dataInputStream.readShort();
                            int short2 = dataInputStream.readShort();
                            short short3 = dataInputStream.readShort();
                            int short4 = dataInputStream.readShort();
                            if (byte1 == 3 || byte1 == 4) {
                                short2 = (short1 = (short)(-999999));
                                short4 = (short3 = (short)999999);
                            }
                            if (byte1 == 2 || byte1 == 3 || byte1 == 4) {
                                vector.addElement(new Integer(short1));
                                vector2.addElement(new Integer(short2));
                                vector.addElement(new Integer(short3));
                                vector2.addElement(new Integer(short2));
                                vector.addElement(new Integer(short3));
                                vector2.addElement(new Integer(short4));
                                vector.addElement(new Integer(short1));
                                vector2.addElement(new Integer(short4));
                            }
                            if (byte1 == 5) {
                                vector.addElement(new Integer(short1));
                                vector.addElement(new Integer(short3));
                                vector2.addElement(new Integer(short2));
                                vector2.addElement(new Integer(short4));
                            }
                        }
                    }
                    this.vg.addElement(vector);
                    this.vf.addElement(vector2);
                    array[3] = int1;
                    array[4] = int2;
                    ++this.vu;
                }
            }
            else if (b2 == 3) {
                for (short n6 = 0; n6 < n2; ++n6) {
                    final int[] array2 = new int[8];
                    this.va.addElement(array2);
                    array2[0] = dataInputStream.readByte();
                    array2[1] = dataInputStream.readByte();
                    final byte byte3 = dataInputStream.readByte();
                    array2[2] = byte3;
                    final byte byte4 = dataInputStream.readByte();
                    array2[7] = byte4;
                    final int unsignedShort = dataInputStream.readUnsignedShort();
                    int int3;
                    int n7;
                    for (int3 = dataInputStream.readInt(), n7 = this.vu - 1; n7 > -1 && ((int[])this.vh.elementAt(n7))[0] != int3; --n7) {}
                    array2[6] = n7;
                    if (unsignedShort > 10) {
                        byte[] array3;
                        if ((byte4 & 0x10) == 0x0) {
                            dataInputStream.readShort();
                            final int n8 = ((byte4 & 0x8) != 0x0) ? (unsignedShort - 16) : (unsignedShort - 12);
                            array2[3] = n8;
                            array3 = new byte[n8];
                            dataInputStream.readFully(array3);
                        }
                        else {
                            array3 = new byte[0];
                        }
                        this.vb.addElement(array3);
                        if ((byte4 & 0x8) != 0x0) {
                            final int int4 = dataInputStream.readInt();
                            if ((byte4 & 0x10) == 0x0) {
                                array2[4] = int4;
                                array2[5] = -1;
                            }
                            else {
                                array2[4] = -1;
                                for (int i = 0; i < this.vq; ++i) {
                                    if (((int[])this.va.elementAt(i))[4] == int4) {
                                        array2[5] = i;
                                        break;
                                    }
                                }
                            }
                        }
                        else {
                            array2[5] = (array2[4] = -1);
                        }
                    }
                    else {
                        array2[3] = 0;
                        this.vb.addElement(new byte[1]);
                    }
                    if (this.u7 && byte3 == 21) {
                        this.handleAction(this.vq);
                    }
                    ++this.vq;
                }
            }
            else {
                dataInputStream.readFully(new byte[n3]);
            }
        }
        catch (Exception ex) {
            return -1;
        }
        return n3;
    }
    
    public final boolean endOfData(final byte b) {
        return true;
    }
    
    public final int startOfData(final byte b, final byte b2, final byte b3, final byte b4, final int n, final int n2, final int n3) {
        this.vr = this.vs;
        return super.startOfData(b, b2, b3, b4, n, n2, n3);
    }
    
    public final int init(final hm35master hm35master) {
        super.init(hm35master);
        return 1;
    }
    
    public hm35action() {
        this.vj = -1;
        this.vi = -1;
        this.vh = new Vector();
        this.vg = new Vector();
        this.vf = new Vector();
        this.vb = new Vector();
        this.va = new Vector();
        this.u8 = new int[5][12];
        this.u9 = 0;
        super.x();
        this.ve = new Vector();
        this.u6 = new Hashtable();
        super.startThread("audioMonitor");
    }
}
