// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.io.UTFDataFormatException;
import java.io.IOException;
import java.io.EOFException;
import java.io.DataInputStream;

public class UTFDISReader
{
    private DataInputStream dis;
    private int index;
    private char[] lineBuffer;
    private int pushBackChar;
    private boolean bPushBack;
    
    public UTFDISReader(final DataInputStream dis) {
        this.dis = dis;
        this.index = 0;
        this.bPushBack = false;
    }
    
    public String readUTFLine() throws IOException {
        char[] lineBuffer = this.lineBuffer;
        if (lineBuffer == null) {
            final char[] lineBuffer2 = new char[256];
            this.lineBuffer = lineBuffer2;
            lineBuffer = lineBuffer2;
        }
        int length = lineBuffer.length;
        int n = 0;
        try {
        Label_0204:
            while (true) {
                int n2;
                if (this.bPushBack) {
                    n2 = this.pushBackChar;
                    this.bPushBack = false;
                }
                else {
                    n2 = this.dis.readUnsignedByte();
                }
                ++this.index;
                switch (n2) {
                    case 10: {
                        break Label_0204;
                    }
                    case 13: {
                        final int unsignedByte = this.dis.readUnsignedByte();
                        if (unsignedByte != 10) {
                            this.pushBackChar = unsignedByte;
                            this.bPushBack = true;
                        }
                        else {
                            ++this.index;
                        }
                        if (n == 0) {
                            continue;
                        }
                        break Label_0204;
                    }
                    default: {
                        final int processChar = this.processChar(n2);
                        if (--length < 0) {
                            lineBuffer = new char[n + 256];
                            length = lineBuffer.length - n - 1;
                            System.arraycopy(this.lineBuffer, 0, lineBuffer, 0, n);
                            this.lineBuffer = lineBuffer;
                        }
                        lineBuffer[n++] = (char)processChar;
                        continue;
                    }
                }
            }
        }
        catch (EOFException ex) {
            return null;
        }
        return String.copyValueOf(lineBuffer, 0, n);
    }
    
    public String readUTF() throws IOException {
        char[] lineBuffer = this.lineBuffer;
        if (lineBuffer == null) {
            final char[] lineBuffer2 = new char[256];
            this.lineBuffer = lineBuffer2;
            lineBuffer = lineBuffer2;
        }
        int length = lineBuffer.length;
        int n = 0;
        String copyValue;
        try {
            while (true) {
                final int processChar = this.processChar(this.dis.readUnsignedByte());
                if (--length < 0) {
                    lineBuffer = new char[n + 256];
                    length = lineBuffer.length - n - 1;
                    System.arraycopy(this.lineBuffer, 0, lineBuffer, 0, n);
                    this.lineBuffer = lineBuffer;
                }
                lineBuffer[n++] = (char)processChar;
            }
        }
        catch (EOFException ex2) {
            copyValue = String.copyValueOf(lineBuffer, 0, n);
        }
        catch (IOException ex) {
            System.out.println("Error reading UTF Stream");
            throw ex;
        }
        return copyValue;
    }
    
    private int processChar(final int n) throws IOException {
        int n2 = 0;
        try {
            switch (n >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: {
                    n2 = (char)n;
                    break;
                }
                case 12:
                case 13: {
                    final int unsignedByte = this.dis.readUnsignedByte();
                    ++this.index;
                    if ((unsignedByte & 0xC0) != 0x80) {
                        throw new UTFDataFormatException();
                    }
                    n2 = (char)((n & 0x1F) << 6 | (unsignedByte & 0x3F));
                    break;
                }
                case 14: {
                    final int unsignedByte2 = this.dis.readUnsignedByte();
                    ++this.index;
                    if (unsignedByte2 == -1) {
                        throw new UTFDataFormatException();
                    }
                    final int unsignedByte3 = this.dis.readUnsignedByte();
                    ++this.index;
                    if ((unsignedByte2 & 0xC0) != 0x80 || (unsignedByte3 & 0xC0) != 0x80) {
                        throw new UTFDataFormatException();
                    }
                    n2 = (char)((n & 0xF) << 12 | (unsignedByte2 & 0x3F) << 6 | (unsignedByte3 & 0x3F) << 0);
                    break;
                }
            }
        }
        catch (EOFException ex) {
            throw ex;
        }
        return n2;
    }
}
