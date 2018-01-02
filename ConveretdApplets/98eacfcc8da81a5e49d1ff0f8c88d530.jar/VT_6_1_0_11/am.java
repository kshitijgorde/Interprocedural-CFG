// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.applet.Applet;
import java.awt.Component;
import java.util.ArrayList;
import java.io.IOException;
import com.hw.client.util.a;
import java.io.EOFException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.List;

public class am
{
    char a;
    int b;
    int c;
    byte[] d;
    int e;
    int f;
    int g;
    int h;
    List i;
    
    private am(final char a) {
        this.a = a;
    }
    
    static final am a(final InputStream inputStream) {
        final DataInputStream dataInputStream;
        final int read;
        if ((read = (dataInputStream = (DataInputStream)inputStream).read()) == -1) {
            throw new EOFException("AudioProxyCallback: Received r=-1");
        }
        final char c = (char)read;
        final am am = new am(c);
        if (c == '\u0085') {
            final short short1 = dataInputStream.readShort();
            dataInputStream.readFully(am.d = new byte[short1]);
            if (a.c()) {
                a.a("AudioProxyDoor: Received AGENT_CB_AUDIO len=" + short1);
            }
        }
        else if (c == '\u0087') {
            am.c = dataInputStream.readShort();
            if (am.c < 0) {
                am.c += 65536;
            }
            if (a.c()) {
                a.a("AudioProxyDoor: Received AGENT_CB_PLAYQUEUE playQueue=" + am.c);
            }
        }
        else if (c == '\u0081') {
            if (a.b()) {
                a.c("AudioProxyDoor: Received AGENT_CB_READY");
            }
        }
        else if (c == '\u0082') {
            am.b = dataInputStream.readInt();
            a.d("AudioProxyDoor: Received AGENT_CB_UNAVAILABLE cause=" + am.b);
        }
        else if (c == '\u0083') {
            if (a.b()) {
                a.c("AudioProxyDoor: Received AGENT_CB_STARTED");
            }
        }
        else if (c == '\u0084') {
            if (a.b()) {
                a.c("AudioProxyDoor: Received AGENT_CB_STOPPED");
            }
        }
        else if (c == '\u0086') {
            if (a.c()) {
                a.a("AudioProxyDoor: Received AGENT_CB_PONG");
            }
        }
        else if (c == '\u0088') {
            am.e = dataInputStream.readInt();
            am.f = dataInputStream.readInt();
            am.g = dataInputStream.readInt();
            am.h = dataInputStream.readInt();
            if (a.c()) {
                a.a("AudioProxyDoor: Received AGENT_CB_LEVELS in_pow=" + am.e + ", out_pow=" + am.f + ", in_lev=" + am.g + ", out_level=" + am.h);
            }
        }
        else if (c == '\u0089') {
            am.g = dataInputStream.readInt();
            if (a.c()) {
                a.a("AudioProxyDoor: Received AGENT_CB_INPUT_LEVEL_GET in_lev=" + am.g);
            }
        }
        else if (c == '\u008a') {
            am.h = dataInputStream.readInt();
            if (a.c()) {
                a.a("AudioProxyDoor: Received AGENT_CB_OUTPUT_LEVEL_GET out_lev=" + am.h);
            }
        }
        else {
            if (c != '\u008b') {
                a.e("AudioProxyDoor: unexpected callback r=" + read);
                throw new IOException("AudioProxyDoor: unexpected callback r=" + read + ", throwing IOException");
            }
            am.i = new ArrayList();
            final short short2 = dataInputStream.readShort();
            final short short3 = dataInputStream.readShort();
            final short short4 = dataInputStream.readShort();
            final short short5 = dataInputStream.readShort();
            if (a.c()) {
                a.a("AudioProxyDoor: Received AGENT_CB_GET_AUDIO_DEVICESnDevs=" + short2 + ", input=" + short3 + ", output=" + short4 + ", ring=" + short5);
            }
            for (short n = 0; n < short2; ++n) {
                final short short6 = dataInputStream.readShort();
                final short short7 = dataInputStream.readShort();
                final short short8;
                final byte[] array = new byte[short8 = dataInputStream.readShort()];
                dataInputStream.readFully(array);
                final String s = new String(array);
                if (a.c()) {
                    a.a("devID=" + short6 + ", caps=" + short7 + ", nameLength=" + short8 + ", name=" + s);
                }
                am.i.add(new p(s, short6, short3 == short6, short4 == short6, short5 == short6, short7));
            }
        }
        return am;
    }
    
    public am() {
    }
    
    public static Applet a(Component parent) {
        while (parent != null && !(parent instanceof Applet)) {
            parent = parent.getParent();
        }
        return (Applet)parent;
    }
}
