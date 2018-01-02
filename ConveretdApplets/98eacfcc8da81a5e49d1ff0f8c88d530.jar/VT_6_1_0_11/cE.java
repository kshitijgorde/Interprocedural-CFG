// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.net.UnknownHostException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

final class cE
{
    private int a;
    private boolean b;
    private byte[] c;
    
    final Socket a(final String s, final int n) {
        return this.a(s, n, null, -1);
    }
    
    private Socket a(final String s, final int n, InetAddress inetAddress, final int ex) {
        inetAddress = null;
        try {
            new StringBuffer().append("Socks: contacting server on ").append((String)null).append(":").append(0).toString();
            final InputStream inputStream = ((Socket)(inetAddress = (InetAddress)b(null, 0, null, -1))).getInputStream();
            final OutputStream outputStream = ((Socket)inetAddress).getOutputStream();
            switch (this.a) {
                case 4: {
                    this.a(inputStream, outputStream, s, n);
                    break;
                }
                case 5: {
                    this.b(inputStream, outputStream, s, n);
                    break;
                }
                case -1: {
                    try {
                        this.a(inputStream, outputStream, s, n);
                        this.a = 4;
                    }
                    catch (dd dd) {
                        new StringBuffer().append("Socks: V4 request failed: ").append(dd.getMessage()).toString();
                        ((Socket)inetAddress).close();
                        this.b(((Socket)(inetAddress = (InetAddress)b(null, 0, null, -1))).getInputStream(), ((Socket)inetAddress).getOutputStream(), s, n);
                        this.a = 5;
                    }
                    break;
                }
                default: {
                    throw new Error("SocksClient internal error: unknown version " + this.a);
                }
            }
            return (Socket)inetAddress;
        }
        catch (IOException ex) {
            if (inetAddress != null) {
                try {
                    ((Socket)inetAddress).close();
                }
                catch (IOException ex2) {}
            }
            throw ex;
        }
    }
    
    private static final Socket b(final String p0, final int p1, final InetAddress p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    java/net/InetAddress.getAllByName:(Ljava/lang/String;)[Ljava/net/InetAddress;
        //     4: astore_0       
        //     5: iconst_0       
        //     6: istore          4
        //     8: iload           4
        //    10: aload_0        
        //    11: arraylength    
        //    12: if_icmpge       67
        //    15: aload_2        
        //    16: ifnonnull       32
        //    19: new             Ljava/net/Socket;
        //    22: dup            
        //    23: aload_0        
        //    24: iload           4
        //    26: aaload         
        //    27: iload_1        
        //    28: invokespecial   java/net/Socket.<init>:(Ljava/net/InetAddress;I)V
        //    31: areturn        
        //    32: new             Ljava/net/Socket;
        //    35: dup            
        //    36: aload_0        
        //    37: iload           4
        //    39: aaload         
        //    40: iload_1        
        //    41: aload_2        
        //    42: iload_3        
        //    43: invokespecial   java/net/Socket.<init>:(Ljava/net/InetAddress;ILjava/net/InetAddress;I)V
        //    46: areturn        
        //    47: astore          5
        //    49: iload           4
        //    51: aload_0        
        //    52: arraylength    
        //    53: iconst_1       
        //    54: isub           
        //    55: if_icmplt       61
        //    58: aload           5
        //    60: athrow         
        //    61: iinc            4, 1
        //    64: goto            8
        //    67: aconst_null    
        //    68: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  15     31     47     67     Ljava/net/SocketException;
        //  32     46     47     67     Ljava/net/SocketException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void a(final InputStream inputStream, final OutputStream outputStream, final String s, int n) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
        new StringBuffer().append("Socks: Beginning V4 Protocol Exchange for host ").append(s).append(":").append(n).toString();
        byte[] address = { 0, 0, 0, 42 };
        if (!this.b) {
            try {
                address = InetAddress.getByName(s).getAddress();
            }
            catch (UnknownHostException ex) {
                this.b = true;
            }
            catch (SecurityException ex2) {
                this.b = true;
            }
        }
        if (this.c == null) {
            String property;
            try {
                property = System.getProperty("user.name", "");
            }
            catch (SecurityException ex3) {
                property = "";
            }
            final byte[] bytes = property.getBytes();
            System.arraycopy(bytes, 0, this.c = new byte[bytes.length + 1], 0, bytes.length);
            this.c[property.length()] = 0;
        }
        new StringBuffer().append("Socks: Sending connect request for user ").append(new String(this.c, 0, this.c.length - 1)).toString();
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(4);
        byteArrayOutputStream.write(1);
        byteArrayOutputStream.write(n >> 8 & 0xFF);
        byteArrayOutputStream.write(n & 0xFF);
        byteArrayOutputStream.write(address);
        byteArrayOutputStream.write(this.c);
        if (this.b) {
            byteArrayOutputStream.write(s.getBytes("8859_1"));
            byteArrayOutputStream.write(0);
        }
        byteArrayOutputStream.writeTo(outputStream);
        final int read;
        if ((read = inputStream.read()) == -1) {
            throw new dd("Connection refused by server");
        }
        if (read != 4 && read != 0) {
            throw new dd("Received invalid version: " + read + "; expected: 0");
        }
        final int read2 = inputStream.read();
        new StringBuffer().append("Socks: Received response; version: ").append(read).append("; status: ").append(read2).toString();
        switch (read2) {
            case 90: {
                byte[] array;
                int read3;
                for (array = new byte[6], n = 0; n < array.length && (read3 = inputStream.read(array, 0, array.length - n)) != -1; n += read3) {}
            }
            case 91: {
                throw new dd("Connection request rejected");
            }
            case 92: {
                throw new dd("Connection request rejected: can't connect to identd");
            }
            case 93: {
                throw new dd("Connection request rejected: identd reports different user-id from " + new String(this.c, 0, this.c.length - 1));
            }
            default: {
                throw new dd("Connection request rejected: unknown error " + read2);
            }
        }
    }
    
    private void b(final InputStream inputStream, final OutputStream outputStream, final String s, int n) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
        new StringBuffer().append("Socks: Beginning V5 Protocol Exchange for host ").append(s).append(":").append(n).toString();
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(5);
        byteArrayOutputStream.write(2);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(2);
        byteArrayOutputStream.writeTo(outputStream);
        final int read;
        if ((read = inputStream.read()) == -1) {
            throw new dd("Connection refused by server");
        }
        if (read != 5) {
            throw new dd("Received invalid version: " + read + "; expected: 5");
        }
        final int read2 = inputStream.read();
        new StringBuffer().append("Socks: Received response; version: ").append(read).append("; method: ").append(read2).toString();
        switch (read2) {
            case 0: {
                break;
            }
            case 1: {
                throw new dd("GSSAPI authentication protocol not implemented");
            }
            case 2: {
                aJ a;
                try {
                    a = aJ.a(null, 0, "SOCKS5", "USER/PASS", null, null, true);
                }
                catch (cL cl) {
                    a = null;
                }
                if (a == null) {
                    throw new dd("No Authorization info for SOCKS found (server requested username/password).");
                }
                final cU[] e;
                if ((e = a.e()) == null || e.length == 0) {
                    throw new dd("No Username/Password found in authorization info for SOCKS.");
                }
                final String a2 = e[0].a();
                final String b = e[0].b();
                new StringBuffer().append("Socks: Sending authorization request for user ").append(a2).toString();
                final byte[] bytes = a2.getBytes();
                final byte[] bytes2 = b.getBytes();
                final byte[] array;
                (array = new byte[2 + bytes.length + 1 + bytes2.length])[0] = 1;
                array[1] = (byte)bytes.length;
                System.arraycopy(bytes, 0, array, 2, bytes.length);
                array[2 + array[1]] = (byte)bytes2.length;
                System.arraycopy(bytes2, 0, array, 2 + array[1] + 1, bytes2.length);
                outputStream.write(array);
                final int read3;
                if ((read3 = inputStream.read()) != 1) {
                    throw new dd("Wrong version received in username/password subnegotiation response: " + read3 + "; expected: 1");
                }
                final int read4;
                if ((read4 = inputStream.read()) != 0) {
                    throw new dd("Username/Password authentication failed; status: " + read4);
                }
                new StringBuffer().append("Socks: Received response; version: ").append(read3).append("; status: ").append(read4).toString();
                break;
            }
            case -1: {
                throw new dd("Server unwilling to accept any standard authentication methods");
            }
            default: {
                throw new dd("Cannot handle authentication method " + read2);
            }
        }
        byteArrayOutputStream.reset();
        byteArrayOutputStream.write(5);
        byteArrayOutputStream.write(1);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(s.length() & 0xFF);
        byteArrayOutputStream.write(s.getBytes("8859_1"));
        byteArrayOutputStream.write(n >> 8 & 0xFF);
        byteArrayOutputStream.write(n & 0xFF);
        byteArrayOutputStream.writeTo(outputStream);
        final int read5;
        if ((read5 = inputStream.read()) != 5) {
            throw new dd("Received invalid version: " + read5 + "; expected: 5");
        }
        final int read6 = inputStream.read();
        new StringBuffer().append("Socks: Received response; version: ").append(read5).append("; status: ").append(read6).toString();
        switch (read6) {
            case 0: {
                inputStream.read();
                final int read7;
                int read8 = 0;
                switch (read7 = inputStream.read()) {
                    case 4: {
                        read8 = 16;
                        break;
                    }
                    case 1: {
                        read8 = 4;
                        break;
                    }
                    case 3: {
                        read8 = inputStream.read();
                        break;
                    }
                    default: {
                        throw new dd("Invalid address type received from server: " + read7);
                    }
                }
                byte[] array2;
                int read9;
                for (array2 = new byte[read8 + 2], n = 0; n < array2.length && (read9 = inputStream.read(array2, 0, array2.length - n)) != -1; n += read9) {}
            }
            case 1: {
                throw new dd("General SOCKS server failure");
            }
            case 2: {
                throw new dd("Connection not allowed");
            }
            case 3: {
                throw new dd("Network unreachable");
            }
            case 4: {
                throw new dd("Host unreachable");
            }
            case 5: {
                throw new dd("Connection refused");
            }
            case 6: {
                throw new dd("TTL expired");
            }
            case 7: {
                throw new dd("Command not supported");
            }
            case 8: {
                throw new dd("Address type not supported");
            }
            default: {
                throw new dd("Unknown reply received from server: " + read6);
            }
        }
    }
    
    public final String toString() {
        return this.getClass().getName() + "[" + (String)null + ":" + 0 + "]";
    }
}
