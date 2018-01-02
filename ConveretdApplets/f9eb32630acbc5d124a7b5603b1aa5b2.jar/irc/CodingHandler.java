// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.OutputStream;

public class CodingHandler extends IRCObject
{
    private MyPushbackStream _is;
    private OutputStream _os;
    private BufferedReader _reader;
    private BufferedWriter _writer;
    private int _coding;
    public static final int CODING_ASCII = 0;
    public static final int CODING_PUAP = 1;
    public static final int CODING_UTF_8 = 2;
    public static final int CODING_LOCAL_CHARSET = 3;
    
    public CodingHandler(final IRCConfiguration ircConfiguration, final InputStream inputStream, final OutputStream os) {
        super(ircConfiguration);
        this._coding = ircConfiguration.getI("coding");
        if (this._coding != 3) {
            this._is = new MyPushbackStream(inputStream);
            this._os = os;
            this._reader = null;
            this._writer = null;
        }
        else {
            this._is = null;
            this._os = null;
            this._reader = new BufferedReader(new InputStreamReader(inputStream));
            this._writer = new BufferedWriter(new OutputStreamWriter(os));
        }
    }
    
    public void close() throws IOException {
        if (this._is != null) {
            this._is.close();
        }
        if (this._os != null) {
            this._os.close();
        }
        if (this._reader != null) {
            this._reader.close();
        }
        if (this._writer != null) {
            this._writer.close();
        }
        this._is = null;
        this._os = null;
        this._reader = null;
        this._writer = null;
    }
    
    public String read() throws IOException {
        if (this._coding != 3) {
            return asciiToWide(this.readUTF());
        }
        return this._reader.readLine();
    }
    
    public void write(final String s) throws IOException {
        if (this._coding == 0) {
            this.writeASCII(s);
        }
        else if (this._coding == 1) {
            this.writePUAP(s);
        }
        else if (this._coding == 2) {
            this.writeUTF(s);
        }
        else if (this._coding == 3) {
            this.writeCHARSET(s);
        }
        else {
            this.writePUAP(s);
        }
        if (this._os != null) {
            this._os.flush();
        }
        if (this._writer != null) {
            this._writer.flush();
        }
    }
    
    private void writeCHARSET(final String s) throws IOException {
        this._writer.write(s, 0, s.length());
        this._writer.newLine();
    }
    
    private void writeASCII(final String s) throws IOException {
        for (int i = 0; i < s.length(); ++i) {
            this._os.write((byte)s.charAt(i));
        }
        this._os.write(13);
        this._os.write(10);
    }
    
    private void writePUAP(final String s) throws IOException {
        this.writeASCII(wideToAscii(s));
    }
    
    private String readUTF() throws IOException {
        String s = "";
        String string = "";
        int n = 1;
        char c = '\0';
        int n2 = 0;
        while (!false) {
            int n3 = this._is.read();
            if ((n3 == 10 || n3 == 13) && string.length() == 0) {
                continue;
            }
            if (n3 == -1) {
                if (string.length() == 0) {
                    throw new IOException("EOF reached");
                }
                if (n2 != 0) {
                    n = 0;
                }
                if (n != 0) {
                    return s;
                }
                return string;
            }
            else if (n3 == 10 || n3 == 13) {
                if (n2 != 0) {
                    n = 0;
                }
                if (this._is.available() >= 1) {
                    n3 = this._is.read();
                }
                if (n3 != 10 && n3 != 13) {
                    this._is.unread((byte)n3);
                }
                if (n != 0) {
                    return s;
                }
                return string;
            }
            else {
                string += (char)n3;
                if (n == 0) {
                    continue;
                }
                if (n3 < 128) {
                    if (n2 != 0) {
                        n = 0;
                    }
                    else {
                        s += (char)n3;
                    }
                }
                else if (n3 < 192) {
                    if (n2 == 0) {
                        n = 0;
                    }
                    else {
                        c = (char)(c << 6 | (n3 - 128 & 0x3F));
                        if (--n2 != 0) {
                            continue;
                        }
                        s += c;
                        c = '\0';
                    }
                }
                else if (n3 < 224) {
                    if (n2 != 0) {
                        n = 0;
                    }
                    else {
                        n2 = 1;
                        c = (char)(n3 - 192);
                    }
                }
                else if (n2 != 0) {
                    n = 0;
                }
                else {
                    n2 = 2;
                    c = (char)(n3 - 224);
                }
            }
        }
        return null;
    }
    
    private void writeUTF(final String s) throws IOException {
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 < '\u007f') {
                this._os.write((byte)char1);
            }
            else if (char1 < '\u07ff') {
                this._os.write((byte)('\u00c0' + (char1 >> 6)));
                this._os.write((byte)('\u0080' + (char1 & '?')));
            }
            else {
                this._os.write((byte)('\u00e0' + (char1 >> 12)));
                this._os.write((byte)('\u0080' + (char1 >> 6 & '?')));
                this._os.write((byte)('\u0080' + (char1 & '?')));
            }
        }
        this._os.write(10);
        this._os.write(13);
    }
    
    private static String asciiToWide(final String s) {
        try {
            String s2 = "";
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '\u001e') {
                    final String substring = s.substring(i + 1, i + 5);
                    i += 4;
                    s2 += (char)Integer.parseInt(substring, 16);
                }
                else {
                    s2 += s.charAt(i);
                }
            }
            return s2;
        }
        catch (Exception ex) {
            return s;
        }
    }
    
    private static String wideToAscii(final String s) {
        String s2 = "";
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 > '\u00ff') {
                final String string = s2 + '\u001e';
                String s3;
                for (s3 = Integer.toHexString(char1); s3.length() < 4; s3 = "0" + s3) {}
                s2 = string + s3;
            }
            else {
                s2 += char1;
            }
        }
        return s2;
    }
}
