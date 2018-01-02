import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class EvaluateStream
{
    Vector addElement;
    Vector append;
    final int charAt = 1;
    final int classification = 0;
    final int column = 2;
    String file;
    String flush;
    static boolean function;
    private int seed;
    static String getProperty;
    static int isEmpty;
    
    public EvaluateStream() {
        this.addElement = new Vector();
        this.append = new Vector();
        this.file = null;
        this.flush = null;
        this.seed = 0;
    }
    
    static final int addElement(final int n) {
        if (n >= 10) {
            return n + 65 - 10;
        }
        return n + 48;
    }
    
    static final int append(final int n) {
        if (n >= 48 && n <= 57) {
            return n - 48;
        }
        if (n >= 65 && n <= 90) {
            return n - 65 + 10;
        }
        if (n >= 97 && n <= 122) {
            return n - 97 + 10;
        }
        return 0;
    }
    
    public final String readString(final DataInputStream dataInputStream, final int n) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            final byte byte1 = dataInputStream.readByte();
            char c;
            if (byte1 < 0) {
                c = (char)(byte1 + 256);
            }
            else {
                c = (char)byte1;
            }
            sb.append(c);
        }
        return new String(sb);
    }
    
    public final void sendBegin(final DataOutputStream dataOutputStream) {
        this.addElement.removeAllElements();
        this.append.removeAllElements();
        dataOutputStream.writeInt(15);
        dataOutputStream.writeInt(4);
        dataOutputStream.writeByte(1);
        dataOutputStream.writeByte(0);
        dataOutputStream.writeShort(2);
        if (this.file != null) {
            dataOutputStream.writeInt(17);
            dataOutputStream.writeInt(this.file.length());
            dataOutputStream.writeBytes(this.file);
        }
        if (this.seed > 0) {
            dataOutputStream.writeInt(36);
            dataOutputStream.writeInt(4);
            dataOutputStream.writeInt(this.seed);
        }
        if (EvaluateStream.isEmpty >= 0) {
            dataOutputStream.writeInt(21);
            dataOutputStream.writeInt(4);
            dataOutputStream.writeInt(EvaluateStream.isEmpty);
        }
        else {
            if (this.flush != null) {
                dataOutputStream.writeInt(23);
                final String flush = this.flush;
                dataOutputStream.writeInt(flush.length());
                dataOutputStream.writeBytes(flush);
            }
            try {
                final String property = System.getProperty("java.version");
                dataOutputStream.writeInt(32);
                dataOutputStream.writeInt(property.length());
                dataOutputStream.writeBytes(property);
            }
            catch (Exception ex) {}
            try {
                final String string = System.getProperty("os.name") + "-" + System.getProperty("os.arch") + "-" + System.getProperty("os.version");
                dataOutputStream.writeInt(33);
                dataOutputStream.writeInt(string.length());
                dataOutputStream.writeBytes(string);
            }
            catch (Exception ex2) {}
        }
        dataOutputStream.writeInt(31);
        dataOutputStream.writeInt(EvaluateStream.getProperty.length());
        dataOutputStream.writeBytes(EvaluateStream.getProperty);
        dataOutputStream.writeInt(19);
        final String s = "secondary";
        dataOutputStream.writeInt(s.length());
        dataOutputStream.writeBytes(s);
    }
    
    public final void sendEnd(final DataOutputStream dataOutputStream) {
        dataOutputStream.writeInt(18);
        dataOutputStream.writeInt(0);
        dataOutputStream.flush();
    }
    
    public final void send(final String[] array, final int n, final DataOutputStream dataOutputStream) {
        this.sendBegin(dataOutputStream);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final boolean b = (n & 0x20000000) != 0x0;
        final OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, "ISO-8859-1");
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                outputStreamWriter.write(26);
            }
            final String s = array[i];
            for (int length = s.length(), j = 0; j < length; ++j) {
                final char char1 = s.charAt(j);
                if (!b && char1 > '\u00ff') {
                    outputStreamWriter.write(126);
                    outputStreamWriter.write(addElement((char1 & '\uf000') >> 12));
                    outputStreamWriter.write(addElement((char1 & '\u0f00') >> 8));
                    outputStreamWriter.write(addElement((char1 & '\u00f0') >> 4));
                    outputStreamWriter.write(addElement(char1 & '\u000f'));
                }
                else {
                    outputStreamWriter.write(s.charAt(j));
                }
            }
        }
        outputStreamWriter.flush();
        final int size = byteArrayOutputStream.size();
        dataOutputStream.writeInt(1);
        dataOutputStream.writeInt(size + 4);
        dataOutputStream.writeInt(n);
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        for (int k = 0; k < size; ++k) {
            dataOutputStream.writeByte(byteArray[k]);
        }
        this.sendEnd(dataOutputStream);
    }
    
    public final String[] receive(final int n, final DataInputStream dataInputStream, final int n2) {
        final String[] array = new String[n];
        final StringBuffer sb = new StringBuffer();
        final boolean b = (n2 & 0x20000000) != 0x0;
        int i;
        do {
            i = dataInputStream.readInt();
            if (i == -2147483632) {
                throw new OException("Out of memory.");
            }
            if (i == -2147483623) {
                throw new OException("Lack of time.");
            }
            if (i == -2147483621) {
                throw new OException("The answer is too big.");
            }
            if (i == -2147483622) {
                throw new OException("The questions is too big.");
            }
            if ((i & Integer.MIN_VALUE) != 0x0) {
                throw new OException("Kernel error.");
            }
            final int int1 = dataInputStream.readInt();
            switch (i) {
                case 1: {
                    sb.setLength(0);
                    int n3 = 0;
                    for (int j = 0; j < int1; ++j) {
                        int byte1 = dataInputStream.readByte();
                        if (byte1 == 126) {
                            if (j + 4 < int1) {
                                sb.append((char)((append(dataInputStream.readByte()) << 12) + (append(dataInputStream.readByte()) << 8) + (append(dataInputStream.readByte()) << 4) + append(dataInputStream.readByte())));
                                j += 4;
                            }
                            else {
                                sb.append(byte1);
                            }
                        }
                        else if (byte1 == 26) {
                            array[n3] = new String(sb);
                            sb.setLength(0);
                            ++n3;
                        }
                        else {
                            if (byte1 < 0) {
                                byte1 += 256;
                            }
                            sb.append((char)byte1);
                        }
                    }
                    array[n3] = new String(sb);
                    continue;
                }
                case 17: {
                    for (int k = 0; k < int1; ++k) {
                        dataInputStream.readByte();
                    }
                    continue;
                }
                case 19: {
                    final ErrorWarning errorWarning = new ErrorWarning();
                    errorWarning.classification = this.readString(dataInputStream, dataInputStream.readInt());
                    errorWarning.file = this.readString(dataInputStream, dataInputStream.readInt());
                    errorWarning.function = this.readString(dataInputStream, dataInputStream.readInt());
                    errorWarning.line = dataInputStream.readInt();
                    errorWarning.column = dataInputStream.readInt();
                    errorWarning.text = this.readString(dataInputStream, dataInputStream.readInt());
                    this.addElement.addElement(errorWarning);
                    continue;
                }
                case 24:
                case 35: {
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    for (int l = 0; l < int1; ++l) {
                        byteArrayOutputStream.write(dataInputStream.readByte());
                    }
                    this.append.addElement(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
                    byteArrayOutputStream.reset();
                    continue;
                }
                case 30: {
                    EvaluateStream.isEmpty = dataInputStream.readInt();
                    continue;
                }
                case 34: {
                    dataInputStream.readInt();
                    EvaluateStream.function = true;
                    continue;
                }
                case 18: {
                    continue;
                }
                default: {
                    for (int n4 = 0; n4 < int1; ++n4) {
                        dataInputStream.readByte();
                    }
                    continue;
                }
            }
        } while (i != 18);
        if (this.seed > 0) {
            this.seed += 17;
        }
        return array;
    }
    
    public final Vector getErrorsWarnings() {
        return this.addElement;
    }
    
    public final boolean testErrorsWarnings() {
        return !this.addElement.isEmpty();
    }
    
    public final Vector getGraphics() {
        return this.append;
    }
    
    public final boolean testGraphics() {
        return !this.append.isEmpty();
    }
    
    public final void setLanguage(final String file) {
        this.file = file;
    }
    
    public final void setDocumentBase(final String flush) {
        this.flush = flush;
    }
    
    public final void setSeed(final int seed) {
        this.seed = seed;
    }
    
    static {
        EvaluateStream.function = false;
        EvaluateStream.getProperty = "(unknown)";
        EvaluateStream.isEmpty = -1;
    }
}
