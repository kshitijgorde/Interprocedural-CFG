// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.a;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class dx
{
    private char a;
    private int b;
    private byte[] c;
    private int d;
    private short e;
    private String f;
    private String g;
    private String h;
    private int i;
    private int j;
    
    private dx(final char a) {
        this.a = a;
    }
    
    static final dx a(final boolean b, final boolean b2, final char c, final char c2, final int n) {
        final dx dx;
        (dx = new dx('\u0002')).b = ((b ? 0 : Integer.MIN_VALUE) | (b2 ? 0 : 1073741824) | c << 24 | c2 << 16 | n);
        return dx;
    }
    
    static final dx a() {
        return new dx('\u000f');
    }
    
    static final dx b() {
        return new dx('\u0004');
    }
    
    static final dx c() {
        return new dx('\u0005');
    }
    
    static final dx d() {
        return new dx('\u0006');
    }
    
    static final dx a(final byte[] c, final int d, final short e) {
        final dx dx;
        (dx = new dx('\u0003')).e = e;
        dx.c = c;
        dx.d = d;
        return dx;
    }
    
    static final dx e() {
        return new dx('\r');
    }
    
    static final dx a(final String s, final String s2, final String s3) {
        final dx dx;
        (dx = new dx('\u000e')).f = ((s != null) ? s : "");
        dx.g = ((s2 != null) ? s2 : "");
        dx.h = ((s3 != null) ? s3 : "");
        return dx;
    }
    
    static final dx f() {
        return new dx('\t');
    }
    
    static final dx g() {
        return new dx('\u000b');
    }
    
    static final dx a(final int i) {
        final dx dx;
        (dx = new dx('\f')).i = i;
        return dx;
    }
    
    static final dx b(final int j) {
        final dx dx;
        (dx = new dx('\u0007')).j = j;
        return dx;
    }
    
    static final void a(final OutputStream outputStream, final Object o, final boolean b) {
        if (!(o instanceof dx)) {
            throw new IOException("Can not write object: not instance of AudioProxyCommand");
        }
        final DataOutputStream dataOutputStream = (DataOutputStream)outputStream;
        final dx dx;
        if ((dx = (dx)o).a == '\u0003') {
            dataOutputStream.write(3);
            dataOutputStream.writeShort(dx.e);
            dataOutputStream.write(dx.c, dx.d, dx.e);
            if (a.c()) {
                a.a("Sending AGENT_CMD_PLAY nbbytes=" + dx.c.length);
            }
        }
        else if (dx.a == '\u0002') {
            dataOutputStream.write(2);
            dataOutputStream.writeInt(dx.b);
            if (a.c()) {
                a.a("Sending AGENT_CMD_START sample_rate=" + dx.b);
            }
        }
        else if (dx.a == '\u0004') {
            dataOutputStream.write(4);
            if (a.c()) {
                a.a("Sending AGENT_CMD_STOP");
            }
        }
        else if (dx.a == '\u0006') {
            dataOutputStream.write(6);
            if (a.c()) {
                a.a("Sending AGENT_CMD_PING");
            }
        }
        else if (dx.a == '\u0005') {
            dataOutputStream.write(5);
            if (a.c()) {
                a.a("Sending AGENT_CMD_QUIT");
            }
        }
        else if (dx.a == '\u0001') {
            dataOutputStream.write(1);
            if (a.c()) {
                a.a("Sending AGENT_CMD_INIT");
            }
        }
        else if (dx.a == '\r') {
            dataOutputStream.write(13);
            if (a.c()) {
                a.a("Sending AGENT_CMD_GET_AUDIO_DEVICES");
            }
        }
        else if (dx.a == '\u000e') {
            dataOutputStream.write(14);
            final byte[] bytes = dx.f.getBytes("US-ASCII");
            dataOutputStream.writeShort(bytes.length + 1);
            dataOutputStream.write(bytes);
            dataOutputStream.write(0);
            final byte[] bytes2 = dx.g.getBytes("US-ASCII");
            dataOutputStream.writeShort(bytes2.length + 1);
            dataOutputStream.write(bytes2);
            dataOutputStream.write(0);
            final byte[] bytes3 = dx.h.getBytes("US-ASCII");
            dataOutputStream.writeShort(bytes3.length + 1);
            dataOutputStream.write(bytes3);
            dataOutputStream.write(0);
            if (a.c()) {
                a.a("Sending AGENT_CMD_SET_AUDIO_DEVICES input=" + dx.f + ", output=" + dx.g + ", ring=" + dx.h);
            }
        }
        else if (dx.a == '\t') {
            dataOutputStream.write(9);
            if (a.c()) {
                a.a("Sending AGENT_CMD_GET_INPUT_LEVEL");
            }
        }
        else if (dx.a == '\n') {
            dataOutputStream.write(10);
            dataOutputStream.writeShort(0);
            if (a.c()) {
                a.a("Sending AGENT_CMD_SET_INPUT_LEVEL inputLevel=" + 0);
            }
        }
        else if (dx.a == '\u000b') {
            dataOutputStream.write(11);
            if (a.c()) {
                a.a("Sending AGENT_CMD_GET_OUTPUT_LEVEL");
            }
        }
        else if (dx.a == '\f') {
            dataOutputStream.write(12);
            dataOutputStream.writeShort(dx.i);
            if (a.c()) {
                a.a("Sending AGENT_CMD_SET_OUTPUT_LEVEL outputLevel=" + dx.i);
            }
        }
        else if (dx.a == '\u0007') {
            dataOutputStream.write(7);
            dataOutputStream.writeInt(dx.j);
        }
        else {
            if (dx.a != '\u000f') {
                throw new IOException("AudioProxyCommand has an invalid command");
            }
            dataOutputStream.write(15);
            if (a.c()) {
                a.a("Sending AGENT_CMD_START_RECORDING");
            }
        }
        if (b) {
            outputStream.flush();
        }
    }
}
