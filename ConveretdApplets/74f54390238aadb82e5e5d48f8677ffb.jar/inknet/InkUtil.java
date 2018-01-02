// 
// Decompiled by Procyon v0.5.30
// 

package inknet;

import java.net.MalformedURLException;
import java.applet.Applet;
import java.net.UnknownHostException;
import java.net.InetAddress;
import java.util.Vector;
import java.net.URL;

public class InkUtil
{
    public static final int INVALID = 0;
    public static final int VALID_COBRAND = 1;
    public static final int VALID_COPYRIGHT = 2;
    public static final int VALID_PRIVATE = 4;
    
    private static final boolean checkLicense(final byte[] b, String str, int nStart, final long lDate) {
        if (str.length() < b.length * 4) {
            return false;
        }
        final char ch = str.charAt(0);
        if (ch >= '0' && ch <= '9') {
            final int nMonth = Integer.parseInt(str.substring(0, 2));
            final int nDay = Integer.parseInt(str.substring(2, 4));
            final int nYear = Integer.parseInt(str.substring(4, 8));
            final long lTime = getNumDays(nYear, nMonth, nDay);
            if (lDate > lTime * 86400000L) {
                System.out.println(loadString("Cflja|j/Jw\u007ff}jk5/") + lTime);
                return false;
            }
            nStart -= (int)(lTime % 10000L);
            str = str.substring(8);
        }
        int y = nStart;
        for (int i = 0; i < b.length; ++i) {
            int n1 = 0;
            int n2 = 1;
            for (int j = 0; j < 4; ++j) {
                n1 += (str.charAt(i * 4 + j) - 'a') * n2;
                n2 *= 26;
            }
            n2 = y % n1;
            n1 = b[i];
            if (n1 < 0) {
                n1 += 255;
            }
            if (n2 != n1) {
                return false;
            }
            y -= n2;
            if (y < 0) {
                y = nStart;
            }
        }
        return true;
    }
    
    private static final int checkURL(final URL url, final Vector vStrs, final String strGameType) {
        int nValid = 0;
        final long lDate = vStrs.elementAt(vStrs.size() - 1);
        try {
            String strServer = url.getHost();
            InetAddress inet = null;
            try {
                inet = InetAddress.getByName(strServer);
            }
            catch (UnknownHostException e) {
                e.printStackTrace();
            }
            catch (SecurityException e2) {
                e2.printStackTrace();
            }
            strServer = strServer.toLowerCase();
            for (int i = 0; i < vStrs.size() - 1; ++i) {
                final String str = (String)vStrs.elementAt(i);
                if (inet != null) {
                    nValid = checkValidLicense(inet, str, strGameType, lDate);
                    if (nValid != 0) {
                        return nValid;
                    }
                }
                String strSuperDomain = strServer;
                do {
                    nValid = checkValidLicense(strSuperDomain, str, strGameType, lDate);
                    if (nValid != 0) {
                        return nValid;
                    }
                    final int iDot = strSuperDomain.indexOf(46);
                    strSuperDomain = strSuperDomain.substring(iDot + 1);
                } while (strSuperDomain.indexOf(46) != -1);
            }
        }
        catch (NullPointerException e3) {
            vStrs.addElement("Exception Occurred: " + e3.toString());
            nValid = 0;
        }
        catch (SecurityException e4) {
            vStrs.addElement("Exception Occurred: " + e4.toString());
            nValid = 0;
        }
        return nValid;
    }
    
    private static final int checkValidLicense(final InetAddress inet, final String str, final String strGameType, final long lDate) {
        if (str.length() == 0) {
            return 0;
        }
        int nValid = 0;
        final char chCode = str.charAt(0);
        switch (chCode) {
            default: {
                if (checkLicense(inet.getAddress(), str, 456001, lDate)) {
                    nValid = 3;
                    break;
                }
                break;
            }
            case '0': {
                if (checkLicense(inet.getAddress(), str.substring(1), 456001, lDate)) {
                    nValid = 1;
                    break;
                }
                break;
            }
            case '1': {
                if (checkLicense(inet.getAddress(), str.substring(1), 53007, lDate)) {
                    nValid = 7;
                    break;
                }
                break;
            }
            case '2': {
                final byte[] b1 = getStringBytes(strGameType);
                final byte[] b2 = inet.getAddress();
                final byte[] b3 = new byte[b1.length + b2.length];
                System.arraycopy(b1, 0, b3, 0, b1.length);
                System.arraycopy(b2, 0, b3, b1.length, b2.length);
                if (checkLicense(b3, str.substring(1), 456001, lDate)) {
                    nValid = 1;
                }
                break;
            }
            case '3': {
                final byte[] b1 = getStringBytes(strGameType);
                final byte[] b2 = inet.getAddress();
                final byte[] b3 = new byte[b1.length + b2.length];
                System.arraycopy(b1, 0, b3, 0, b1.length);
                System.arraycopy(b2, 0, b3, b1.length, b2.length);
                if (checkLicense(b3, str.substring(1), 53007, lDate)) {
                    nValid = 7;
                }
                break;
            }
            case 'A': {
                if (checkLicense(inet.getAddress(), str.substring(1), 453007, lDate)) {
                    nValid = 3;
                    break;
                }
                break;
            }
            case 'B': {
                final byte[] b1 = getStringBytes(strGameType);
                final byte[] b2 = inet.getAddress();
                final byte[] b3 = new byte[b1.length + b2.length];
                System.arraycopy(b1, 0, b3, 0, b1.length);
                System.arraycopy(b2, 0, b3, b1.length, b2.length);
                if (checkLicense(b3, str.substring(1), 453007, lDate)) {
                    nValid = 3;
                }
                break;
            }
        }
        return nValid;
    }
    
    private static final int checkValidLicense(final String strDomain, final String str, final String strGameType, final long lDate) {
        if (str.length() == 0) {
            return 0;
        }
        int nValid = 0;
        final char chCode = str.charAt(0);
        switch (chCode) {
            default: {
                if (checkLicense(getStringBytes(strDomain), str, 453001, lDate)) {
                    nValid = 3;
                    break;
                }
                break;
            }
            case '0': {
                if (checkLicense(getStringBytes(strDomain), str.substring(1), 453001, lDate)) {
                    nValid = 1;
                    break;
                }
                break;
            }
            case 'A': {
                if (checkLicense(getStringBytes(strDomain), str.substring(1), 453007, lDate)) {
                    nValid = 3;
                    break;
                }
                break;
            }
            case '1': {
                if (checkLicense(getStringBytes(strDomain), str.substring(1), 53007, lDate)) {
                    nValid = 7;
                    break;
                }
                break;
            }
            case '2': {
                if (checkLicense(getStringBytes(strGameType + strDomain), str.substring(1), 453001, lDate)) {
                    nValid = 1;
                    break;
                }
                break;
            }
            case '3': {
                if (checkLicense(getStringBytes(strGameType + strDomain), str.substring(1), 53007, lDate)) {
                    nValid = 7;
                    break;
                }
                break;
            }
            case 'B': {
                if (checkLicense(getStringBytes(strGameType + strDomain), str.substring(1), 453007, lDate)) {
                    nValid = 3;
                    break;
                }
                break;
            }
        }
        return nValid;
    }
    
    public static int getGameCanvasType(final Applet applet, final String strGameType) {
        final URL urlDocument = applet.getDocumentBase();
        final Vector strs = getKeys(applet, urlDocument);
        final int nValid = checkURL(urlDocument, strs, strGameType) & checkURL(applet.getCodeBase(), strs, strGameType);
        if (nValid == 0 && applet != null && applet.getAppletContext() != null) {
            applet.showStatus(loadString("Zacflja|jk/|`i{xn}j!/l`a{nl{/|z\u007f\u007f`}{Ohnbjg`z|j!l`b"));
            try {
                if (strs != null && strs.size() > 0) {
                    final String strURL = strs.elementAt(0).toString();
                    if (strURL.startsWith("http")) {
                        final URL url = new URL(strURL);
                        applet.getAppletContext().showDocument(url, "_new");
                    }
                }
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return nValid;
    }
    
    private static final Vector getKeys(final Applet applet, final URL urlDocument) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: lconst_0       
        //     1: lstore_2        /* lDate */
        //     2: new             Ljava/util/Vector;
        //     5: dup            
        //     6: invokespecial   java/util/Vector.<init>:()V
        //     9: astore          vStrs
        //    11: aconst_null    
        //    12: astore          strLicenseURL
        //    14: aload_0         /* applet */
        //    15: ifnull          88
        //    18: aload_0         /* applet */
        //    19: invokevirtual   java/applet/Applet.getAppletContext:()Ljava/applet/AppletContext;
        //    22: ifnull          88
        //    25: iconst_0       
        //    26: istore          i
        //    28: aconst_null    
        //    29: astore          str
        //    31: aload_0         /* applet */
        //    32: new             Ljava/lang/StringBuffer;
        //    35: dup            
        //    36: invokespecial   java/lang/StringBuffer.<init>:()V
        //    39: ldc             "LICENSE"
        //    41: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    44: iload           i
        //    46: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //    49: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //    52: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    55: astore          str
        //    57: aload           str
        //    59: ifnull          70
        //    62: aload           vStrs
        //    64: aload           str
        //    66: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //    69: nop            
        //    70: iinc            i, 1
        //    73: nop            
        //    74: aload           str
        //    76: ifnonnull       31
        //    79: aload_0         /* applet */
        //    80: ldc             "MONITOR"
        //    82: invokevirtual   java/applet/Applet.getParameter:(Ljava/lang/String;)Ljava/lang/String;
        //    85: astore          strLicenseURL
        //    87: nop            
        //    88: aload           strLicenseURL
        //    90: ifnonnull       97
        //    93: ldc             "license.txt"
        //    95: astore          strLicenseURL
        //    97: aload           vStrs
        //    99: invokevirtual   java/util/Vector.size:()I
        //   102: ifne            288
        //   105: iconst_0       
        //   106: istore          bSuccess
        //   108: aload_1         /* urlDocument */
        //   109: invokevirtual   java/net/URL.getHost:()Ljava/lang/String;
        //   112: invokestatic    java/net/URLEncoder.encode:(Ljava/lang/String;)Ljava/lang/String;
        //   115: astore          strHost
        //   117: iconst_0       
        //   118: istore          i
        //   120: goto            276
        //   123: new             Ljava/net/URL;
        //   126: dup            
        //   127: aload_0         /* applet */
        //   128: invokevirtual   java/applet/Applet.getCodeBase:()Ljava/net/URL;
        //   131: aload           strLicenseURL
        //   133: invokespecial   java/net/URL.<init>:(Ljava/net/URL;Ljava/lang/String;)V
        //   136: astore          url
        //   138: aload           url
        //   140: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   143: astore          urlconnect
        //   145: aload           urlconnect
        //   147: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //   150: astore          is1
        //   152: new             Ljava/io/DataInputStream;
        //   155: dup            
        //   156: aload           is1
        //   158: invokespecial   java/io/DataInputStream.<init>:(Ljava/io/InputStream;)V
        //   161: astore          is
        //   163: goto            184
        //   166: aload           13
        //   168: ldc             "#"
        //   170: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   173: ifne            183
        //   176: aload           vStrs
        //   178: aload           13
        //   180: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   183: nop            
        //   184: aload           is
        //   186: invokevirtual   java/io/DataInputStream.readLine:()Ljava/lang/String;
        //   189: dup            
        //   190: astore          str
        //   192: ifnonnull       166
        //   195: iconst_1       
        //   196: istore          bSuccess
        //   198: aload           urlconnect
        //   200: invokevirtual   java/net/URLConnection.getDate:()J
        //   203: lstore_2        /* lDate */
        //   204: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   207: new             Ljava/lang/StringBuffer;
        //   210: dup            
        //   211: invokespecial   java/lang/StringBuffer.<init>:()V
        //   214: ldc             "Date Value "
        //   216: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   219: lload_2         /* lDate */
        //   220: invokevirtual   java/lang/StringBuffer.append:(J)Ljava/lang/StringBuffer;
        //   223: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   226: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   229: aload           is
        //   231: invokevirtual   java/io/FilterInputStream.close:()V
        //   234: nop            
        //   235: goto            272
        //   238: astore          e
        //   240: aload           vStrs
        //   242: new             Ljava/lang/StringBuffer;
        //   245: dup            
        //   246: invokespecial   java/lang/StringBuffer.<init>:()V
        //   249: ldc             "Error getting license.txt, "
        //   251: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   254: aload           e
        //   256: invokevirtual   java/lang/Throwable.toString:()Ljava/lang/String;
        //   259: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   262: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
        //   265: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   268: nop            
        //   269: goto            272
        //   272: nop            
        //   273: iinc            i, 1
        //   276: iload           i
        //   278: iconst_3       
        //   279: if_icmpge       287
        //   282: iload           bSuccess
        //   284: ifeq            123
        //   287: nop            
        //   288: aload           vStrs
        //   290: new             Ljava/lang/Long;
        //   293: dup            
        //   294: lload_2         /* lDate */
        //   295: invokespecial   java/lang/Long.<init>:(J)V
        //   298: invokevirtual   java/util/Vector.addElement:(Ljava/lang/Object;)V
        //   301: aload           vStrs
        //   303: areturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name           Signature
        //  -----  ------  ----  -------------  -------------------------
        //  0      304     0     applet         Ljava/applet/Applet;
        //  0      304     1     urlDocument    Ljava/net/URL;
        //  2      302     2     lDate          J
        //  11     293     4     vStrs          Ljava/util/Vector;
        //  14     290     5     strLicenseURL  Ljava/lang/String;
        //  28     60      6     i              I
        //  31     57      7     str            Ljava/lang/String;
        //  108    180     6     bSuccess       Z
        //  117    171     7     strHost        Ljava/lang/String;
        //  120    168     8     i              I
        //  152    83      9     is1            Ljava/io/InputStream;
        //  163    72      10    is             Ljava/io/DataInputStream;
        //  145    90      11    urlconnect     Ljava/net/URLConnection;
        //  138    97      12    url            Ljava/net/URL;
        //  192    43      13    str            Ljava/lang/String;
        //  240    33      9     e              Ljava/io/IOException;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  123    235    238    272    Ljava/io/IOException;
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
    
    private static final int getNumDays(final int nYear, final int nMonth, final int nDay) {
        final int[] nMonthDays = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
        int days = (nYear - 1970) * 365 + (nYear - 1970 + 1) / 4;
        if (nYear % 4 == 0 && nMonth > 2) {
            ++days;
        }
        days += nMonthDays[nMonth - 1];
        days = days + nDay - 1;
        return days;
    }
    
    private static byte[] getStringBytes(final String str) {
        byte[] bytes = null;
        try {
            bytes = str.getBytes();
            if (bytes.length != str.length()) {}
            return bytes;
        }
        catch (NoSuchMethodError e) {
            e.printStackTrace();
            bytes = new byte[str.length()];
            if (str.length() == 0) {
                return bytes;
            }
            str.getBytes(0, str.length() - 1, bytes, 0);
            return bytes;
        }
    }
    
    public static User getUser(final Applet applet, final String strServer, final int nValid) {
        User user = null;
        String strSession = null;
        String strUser = null;
        String strGame = null;
        int nPort = 80;
        if (applet != null && applet.getAppletContext() != null) {
            String strUserClass = "inknet.TempUser";
            try {
                strUser = applet.getParameter("USER");
                strSession = applet.getParameter("SESSIONID");
                strGame = applet.getParameter("GAMENAME");
                String str = applet.getParameter("PORT");
                if (str != null) {
                    try {
                        nPort = Integer.parseInt(str);
                    }
                    catch (NumberFormatException e2) {
                        nPort = 80;
                    }
                }
                str = applet.getParameter("USERCLASS");
                if (str != null && str.length() > 0) {
                    strUserClass = str;
                }
                user = (User)Class.forName(strUserClass).newInstance();
            }
            catch (ClassNotFoundException e) {
                System.out.println("Class not available " + strUserClass + " " + e.toString());
            }
            catch (InstantiationException e3) {
                System.out.println("Class cannot be created " + strUserClass);
            }
            catch (IllegalAccessException e4) {
                System.out.println("Class needs public constuctor " + strUserClass);
            }
        }
        if (user == null) {
            user = new TempUser();
        }
        user.init(applet, strServer, strSession, strUser, strGame, nPort, nValid);
        return user;
    }
    
    public static String loadString(final String str) {
        final StringBuffer strb = new StringBuffer(str);
        for (int i = 0; i < strb.length(); ++i) {
            char ch = strb.charAt(i);
            ch ^= '\u000f';
            strb.setCharAt(i, ch);
        }
        return strb.toString();
    }
}
