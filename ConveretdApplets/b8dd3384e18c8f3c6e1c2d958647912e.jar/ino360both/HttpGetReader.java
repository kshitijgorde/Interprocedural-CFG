// 
// Decompiled by Procyon v0.5.30
// 

package ino360both;

import java.applet.Applet;

public class HttpGetReader
{
    private String host;
    private int port;
    private Applet ptv;
    
    public HttpGetReader(final Applet ptv) {
        this.ptv = ptv;
        this.port = ptv.getCodeBase().getPort();
        if (this.port < 0) {
            this.port = 80;
        }
        this.host = ptv.getCodeBase().getHost();
    }
    
    public byte[] doPartialGet(final String fileName, final int start, final int nBytes) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/net/Socket;
        //     3: dup            
        //     4: aload_0         /* this */
        //     5: getfield        ino360both/HttpGetReader.host:Ljava/lang/String;
        //     8: aload_0         /* this */
        //     9: getfield        ino360both/HttpGetReader.port:I
        //    12: invokespecial   java/net/Socket.<init>:(Ljava/lang/String;I)V
        //    15: astore          sock
        //    17: new             Ljava/io/DataInputStream;
        //    20: dup            
        //    21: aload           sock
        //    23: invokevirtual   java/net/Socket.getInputStream:()Ljava/io/InputStream;
        //    26: invokespecial   java/io/DataInputStream.<init>:(Ljava/io/InputStream;)V
        //    29: astore          inStream
        //    31: new             Ljava/io/DataOutputStream;
        //    34: dup            
        //    35: aload           sock
        //    37: invokevirtual   java/net/Socket.getOutputStream:()Ljava/io/OutputStream;
        //    40: invokespecial   java/io/DataOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    43: astore          outStream
        //    45: new             Ljava/net/URL;
        //    48: dup            
        //    49: aload_0         /* this */
        //    50: getfield        ino360both/HttpGetReader.ptv:Ljava/applet/Applet;
        //    53: invokevirtual   java/applet/Applet.getDocumentBase:()Ljava/net/URL;
        //    56: aload_1         /* fileName */
        //    57: invokespecial   java/net/URL.<init>:(Ljava/net/URL;Ljava/lang/String;)V
        //    60: astore          u
        //    62: new             Ljava/lang/StringBuffer;
        //    65: dup            
        //    66: invokespecial   java/lang/StringBuffer.<init>:()V
        //    69: ldc             "GET "
        //    71: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    74: aload           u
        //    76: invokevirtual   java/net/URL.getFile:()Ljava/lang/String;
        //    79: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    82: ldc             " HTTP/1.1\r\n"
        //    84: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    87: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    90: astore          header
        //    92: new             Ljava/lang/StringBuffer;
        //    95: dup            
        //    96: invokespecial   java/lang/StringBuffer.<init>:()V
        //    99: aload           header
        //   101: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   104: ldc             "User-Agent: PTViewer\r\n"
        //   106: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   109: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   112: astore          header
        //   114: new             Ljava/lang/StringBuffer;
        //   117: dup            
        //   118: invokespecial   java/lang/StringBuffer.<init>:()V
        //   121: ldc             "Range: bytes="
        //   123: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   126: iload_2         /* start */
        //   127: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   130: ldc             "-"
        //   132: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   135: iload_2         /* start */
        //   136: iload_3         /* nBytes */
        //   137: iadd           
        //   138: iconst_1       
        //   139: isub           
        //   140: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   143: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   146: astore          range
        //   148: new             Ljava/lang/StringBuffer;
        //   151: dup            
        //   152: invokespecial   java/lang/StringBuffer.<init>:()V
        //   155: aload           header
        //   157: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   160: aload           range
        //   162: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   165: ldc             "\r\n"
        //   167: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   170: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   173: astore          header
        //   175: new             Ljava/lang/StringBuffer;
        //   178: dup            
        //   179: invokespecial   java/lang/StringBuffer.<init>:()V
        //   182: aload           header
        //   184: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   187: ldc             "Connection: close\r\n"
        //   189: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   192: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   195: astore          header
        //   197: new             Ljava/lang/StringBuffer;
        //   200: dup            
        //   201: invokespecial   java/lang/StringBuffer.<init>:()V
        //   204: aload           header
        //   206: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   209: ldc             "Host: "
        //   211: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   214: aload_0         /* this */
        //   215: getfield        ino360both/HttpGetReader.host:Ljava/lang/String;
        //   218: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   221: ldc             ":"
        //   223: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   226: aload_0         /* this */
        //   227: getfield        ino360both/HttpGetReader.port:I
        //   230: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   233: ldc             "\r\n\r\n"
        //   235: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   238: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   241: astore          header
        //   243: aload           outStream
        //   245: aload           header
        //   247: invokevirtual   java/io/DataOutputStream.writeBytes:(Ljava/lang/String;)V
        //   250: iconst_m1      
        //   251: istore          len
        //   253: bipush          100
        //   255: istore          responseCode
        //   257: goto            345
        //   260: iconst_m1      
        //   261: istore          responseCode
        //   263: goto            333
        //   266: aload           4
        //   268: invokevirtual   java/lang/String.length:()I
        //   271: ifne            277
        //   274: goto            344
        //   277: iload           responseCode
        //   279: iconst_m1      
        //   280: if_icmpne       291
        //   283: aload_0         /* this */
        //   284: aload           4
        //   286: invokespecial   ino360both/HttpGetReader.getStatusCode:(Ljava/lang/String;)I
        //   289: istore          responseCode
        //   291: aload           4
        //   293: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //   296: ldc             "content-length:"
        //   298: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   301: ifeq            332
        //   304: aload           4
        //   306: bipush          15
        //   308: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   311: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   314: invokestatic    java/lang/Integer.valueOf:(Ljava/lang/String;)Ljava/lang/Integer;
        //   317: invokevirtual   java/lang/Integer.intValue:()I
        //   320: istore          len
        //   322: nop            
        //   323: goto            331
        //   326: astore          ex
        //   328: goto            331
        //   331: nop            
        //   332: nop            
        //   333: aload           inStream
        //   335: invokevirtual   java/io/DataInputStream.readLine:()Ljava/lang/String;
        //   338: dup            
        //   339: astore          line
        //   341: ifnonnull       266
        //   344: nop            
        //   345: iload           responseCode
        //   347: bipush          100
        //   349: if_icmpeq       260
        //   352: iload           responseCode
        //   354: sipush          206
        //   357: if_icmpeq       388
        //   360: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   363: new             Ljava/lang/StringBuffer;
        //   366: dup            
        //   367: invokespecial   java/lang/StringBuffer.<init>:()V
        //   370: ldc             "PTViewer: unexpected response code: "
        //   372: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   375: iload           responseCode
        //   377: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   380: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   383: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   386: aconst_null    
        //   387: areturn        
        //   388: iload           len
        //   390: iload_3         /* nBytes */
        //   391: if_icmpeq       431
        //   394: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   397: new             Ljava/lang/StringBuffer;
        //   400: dup            
        //   401: invokespecial   java/lang/StringBuffer.<init>:()V
        //   404: ldc             "PTViewer: number of returned bytes does not match. Requested: "
        //   406: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   409: iload_3         /* nBytes */
        //   410: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   413: ldc             "   Returned in header: "
        //   415: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   418: iload           len
        //   420: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   423: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   426: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   429: aconst_null    
        //   430: areturn        
        //   431: iload           len
        //   433: ifge            448
        //   436: new             Ljava/io/ByteArrayOutputStream;
        //   439: dup            
        //   440: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   443: astore          ba
        //   445: goto            459
        //   448: new             Ljava/io/ByteArrayOutputStream;
        //   451: dup            
        //   452: iload           len
        //   454: invokespecial   java/io/ByteArrayOutputStream.<init>:(I)V
        //   457: astore          ba
        //   459: sipush          4096
        //   462: newarray        B
        //   464: astore          tmpBuf
        //   466: goto            480
        //   469: aload           ba
        //   471: aload           tmpBuf
        //   473: iconst_0       
        //   474: iload           14
        //   476: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //   479: nop            
        //   480: aload           inStream
        //   482: aload           tmpBuf
        //   484: invokevirtual   java/io/DataInputStream.read:([B)I
        //   487: dup            
        //   488: istore          tmpLen
        //   490: ifge            469
        //   493: aload           ba
        //   495: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   498: aload           ba
        //   500: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   503: astore          retVal
        //   505: aload           retVal
        //   507: arraylength    
        //   508: istore          len
        //   510: iload           len
        //   512: iload_3         /* nBytes */
        //   513: if_icmpeq       553
        //   516: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   519: new             Ljava/lang/StringBuffer;
        //   522: dup            
        //   523: invokespecial   java/lang/StringBuffer.<init>:()V
        //   526: ldc             "PTViewer: number of returned bytes does not match. Requested: "
        //   528: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   531: iload_3         /* nBytes */
        //   532: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   535: ldc             "   Returned in body: "
        //   537: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   540: iload           len
        //   542: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   545: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   548: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   551: aconst_null    
        //   552: areturn        
        //   553: aload           retVal
        //   555: areturn        
        //   556: astore          ex
        //   558: aload           ex
        //   560: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   563: aconst_null    
        //   564: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name          Signature
        //  -----  ------  ----  ------------  -------------------------------
        //  0      565     0     this          Lino360both/HttpGetReader;
        //  0      565     1     fileName      Ljava/lang/String;
        //  0      565     2     start         I
        //  0      565     3     nBytes        I
        //  341    224     4     line          Ljava/lang/String;
        //  92     473     5     header        Ljava/lang/String;
        //  148    417     6     range         Ljava/lang/String;
        //  17     539     7     sock          Ljava/net/Socket;
        //  31     525     8     inStream      Ljava/io/DataInputStream;
        //  45     511     9     outStream     Ljava/io/DataOutputStream;
        //  62     494     10    u             Ljava/net/URL;
        //  253    303     11    len           I
        //  257    299     12    responseCode  I
        //  328    5       13    ex            Ljava/lang/Exception;
        //  445    111     13    ba            Ljava/io/ByteArrayOutputStream;
        //  490    66      14    tmpLen        I
        //  466    90      15    tmpBuf        [B
        //  505    51      16    retVal        [B
        //  558    7       7     ex            Ljava/lang/Exception;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  304    323    326    331    Ljava/lang/Exception;
        //  0      387    556    565    Ljava/lang/Exception;
        //  388    430    556    565    Ljava/lang/Exception;
        //  431    552    556    565    Ljava/lang/Exception;
        //  553    555    556    565    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:2985)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
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
    
    private int getStatusCode(final String line) {
        String code = line.substring(8).trim();
        code = code.substring(0, 3);
        return Integer.valueOf(code);
    }
}
