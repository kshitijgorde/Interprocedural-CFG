import java.io.StringWriter;
import java.util.Vector;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.awt.Point;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.awt.Dimension;
import java.io.FileNotFoundException;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fn extends rp_dv
{
    public rp_fT a;
    rp_aV a;
    public String a;
    private double a;
    private double b;
    private String b;
    private int d;
    boolean a;
    public boolean b;
    private rp_bV[] a;
    public rp_as a;
    
    public rp_fn(String s, rp_fK rp_fK) {
        this.a = null;
        this.a = null;
        this.a = null;
        this.a = 1.0;
        this.b = 1.0;
        this.d = 0;
        this.a = false;
        this.b = false;
        this.a = null;
        this.a = new rp_as();
        final rp_fn rp_fn = this;
        final String s2 = s;
        rp_fK = rp_fK;
        s = s2;
        this = rp_fn;
        try {
            this.a(rp_fK.d(s));
        }
        catch (Exception ex) {
            throw new FileNotFoundException();
        }
    }
    
    public rp_fn(final int d, final String a, final rp_aV rp_aV, final int n, final int n2, final int a2) {
        this.a = null;
        this.a = null;
        this.a = null;
        this.a = 1.0;
        this.b = 1.0;
        this.d = 0;
        this.a = false;
        this.b = false;
        this.a = null;
        this.a = new rp_as();
        this.d = d;
        this.a = a;
        this.b = new Dimension(n, n2);
        this.a.width = 0;
        this.a.height = 0;
        if (rp_aV != null) {
            this.a(rp_aV);
        }
        if (this.d == 1) {
            this.a = new rp_eJ();
            ((rp_eJ)this.a).a = a2;
        }
        if (this.d == 2) {
            this.a = new rp_dK();
        }
        this.b(n, n2);
    }
    
    public rp_fn(final String a, final rp_aV rp_aV) {
        this.a = null;
        this.a = null;
        this.a = null;
        this.a = 1.0;
        this.b = 1.0;
        this.d = 0;
        this.a = false;
        this.b = false;
        this.a = null;
        this.a = new rp_as();
        this.d = 2;
        this.a = a;
        this.a(rp_aV);
        this.b();
    }
    
    public rp_fn(final int d) {
        this.a = null;
        this.a = null;
        this.a = null;
        this.a = 1.0;
        this.b = 1.0;
        this.d = 0;
        this.a = false;
        this.b = false;
        this.a = null;
        this.a = new rp_as();
        this.d = d;
        if (this.d == 1) {
            this.a = new rp_eJ();
        }
        if (this.d == 2) {
            this.a = new rp_dK();
        }
    }
    
    public rp_fn(final String s, int d) {
        this.a = null;
        this.a = null;
        this.a = null;
        this.a = 1.0;
        this.b = 1.0;
        this.d = 0;
        this.a = false;
        this.b = false;
        this.a = null;
        this.a = new rp_as();
        final rp_fn rp_fn = this;
        final BufferedReader bufferedReader = new BufferedReader(new StringReader(s));
        d = 1;
        final BufferedReader bufferedReader2 = bufferedReader;
        this = rp_fn;
        try {
            this.a(bufferedReader2);
        }
        catch (IOException ex) {
            System.out.println("IO: " + ex);
        }
        catch (rp_s rp_s) {
            System.out.println("Error parsing sep data: " + rp_s.getMessage());
            rp_s.printStackTrace();
        }
        catch (IllegalArgumentException ex2) {}
        finally {
            if (this.a == null) {
                this.d = d;
                if (this.d == 1) {
                    this.a = new rp_eJ();
                }
                if (this.d == 2) {
                    this.a = new rp_dK();
                }
            }
        }
    }
    
    public final void a(final rp_aV a) {
        this.a = a;
        this.a();
        this.a = a.b;
        this.a.width = a.c();
        this.a.height = a.d();
        this.b = 0 - this.a.width / 2;
        this.c = 0 - this.a.height / 2;
        if (this.b != null) {
            this.b(this.b.width, this.b.height);
        }
    }
    
    public final rp_fT a() {
        return this.a;
    }
    
    public final void a(final rp_fT a) {
        this.a = a;
    }
    
    public final boolean b() {
        return this.b;
    }
    
    public final String a() {
        return this.a;
    }
    
    public final boolean a() {
        return this.a != null || (this.b != null && this.b.length() != 0);
    }
    
    final rp_bV[] a(final rp_dC rp_dC) {
        if (this.a == null || this.a.length == 0) {
            return null;
        }
        final rp_bV[] array = new rp_bV[this.a.length];
        for (int i = 0; i < this.a.length; ++i) {
            array[i] = (rp_bV)this.a[i].clone();
            array[i].j = (int)(this.a[i].j * this.a + 0.5);
            array[i].k = (int)(this.a[i].k * this.b + 0.5);
            array[i].l = (int)(this.a[i].l * this.a + 0.5);
            array[i].m = (int)(this.a[i].m * this.b + 0.5);
            array[i].a(rp_dC);
        }
        return array;
    }
    
    public final void a(final rp_eS rp_eS, final rp_h rp_h) {
        if (!rp_eS.a()) {
            ((Graphics2D)rp_eS.a()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        rp_eS.a(Color.black);
        try {
            this.b(rp_eS, rp_h);
        }
        catch (IOException ex) {
            System.out.println("ItemDataSEP.draw : IOError parsing SEP image data");
        }
        catch (RuntimeException ex2) {
            System.out.println("ItemDataSEP.draw : Error parsing SEP image data");
        }
    }
    
    public final boolean b(final int width, final int height) {
        if (this.b != null) {
            this.b.width = width;
            this.b.height = height;
        }
        if (this.a.width == 0 || this.a.height == 0) {
            return false;
        }
        final double a = width / this.a.width;
        final double b = height / this.a.height;
        if (a == this.a && b == this.b) {
            return false;
        }
        this.a = a;
        this.b = b;
        return true;
    }
    
    public final int a() {
        if (this.b != null) {
            return this.b.width;
        }
        return (int)(this.a.width * this.a + 0.5);
    }
    
    public final int b() {
        if (this.b != null) {
            return this.b.height;
        }
        return (int)(this.a.height * this.b + 0.5);
    }
    
    public final int c() {
        if (this.a != null) {
            return this.a.a;
        }
        return 0;
    }
    
    public final int d() {
        if (this.a) {
            return this.c();
        }
        return this.b();
    }
    
    public final int e() {
        if (this.a) {
            return this.b();
        }
        return this.c();
    }
    
    final boolean a(final int n, final int n2) {
        return n >= this.b * this.a && n <= this.b * this.a + this.a() && n2 >= this.c * this.b && n2 <= this.c * this.b + this.b();
    }
    
    private void a(final rp_h rp_h) {
        rp_h.a(this.a, this.a(), this.b());
    }
    
    private void a() {
        if (this.a.c) {
            this.a(1, true);
        }
        this.a = this.a.a;
    }
    
    private void b() {
        final rp_dK a;
        (a = new rp_dK()).d = this.a.a;
        final String c;
        if ((c = this.a.c) != null) {
            this.a = rp_C.a(c);
        }
        a.a = this.a.a;
        a.f = this.a.b;
        final String d;
        if ((d = this.a.d) != null) {
            a.a(d);
        }
        this.a = a;
    }
    
    private void a(String s, final boolean b) {
        this.a = new rp_aV(s);
        try {
            if (b) {
                this.a();
                this.b();
            }
        }
        catch (Throwable t) {
            System.err.println("Error loading data from svg due to: " + t.getMessage());
            t.printStackTrace();
        }
        this.d = 2;
        s = ((this.a == null) ? "UnknownName" : this.a.d);
        rp_C.a(10, "Loading svg: " + s + " full svg mode: " + b);
        this.a.width = (int)(this.a.a() / this.a.a * 2540.0f);
        this.a.height = (int)(this.a.b() / this.a.a * 2540.0f);
        this.b = 0 - this.a.width / 2;
        this.c = 0 - this.a.height / 2;
    }
    
    private void b(final rp_eS rp_eS, final rp_h rp_h) {
        if (this.a != null && rp_h != null) {
            this.a(rp_h);
            return;
        }
        int n = 1;
        if (this.b == null || this.b.length() == 0) {
            final int n2 = this.a() / 2;
            final int n3 = this.b() / 2;
            final StringBuffer sb;
            (sb = new StringBuffer("")).append(-n2);
            sb.append(';');
            sb.append(-n3);
            sb.append(';');
            sb.append(n2);
            sb.append(';');
            sb.append(-n3);
            sb.append(';');
            sb.append(n2);
            sb.append(';');
            sb.append(n3);
            sb.append(';');
            sb.append(-n2);
            sb.append(';');
            sb.append(n3);
            sb.append(';');
            sb.append(-n2);
            sb.append(';');
            sb.append(-n3);
            this.b = "PNC:255;0;0\nPOL:5;" + sb.toString() + ";C;F;255;255;255\nPOL:5;" + sb.toString() + "\nLIN:" + -n2 + ';' + -n3 + ';' + n2 + ';' + n3 + "\nLIN:" + n2 + ';' + -n3 + ';' + -n2 + ';' + n3;
            this.b = 0 - this.a() / 2;
            this.c = 0 - this.b() / 2;
        }
        final BufferedReader bufferedReader = new BufferedReader(new StringReader(this.b));
        final boolean b = this.a != 1.0 || this.b != 1.0;
        String s = null;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (n != 0) {
                n = 0;
                if (line.toLowerCase().startsWith("issvg:true")) {
                    this.a(this.b.substring(this.b.indexOf("issvg:true") + "issvg:true\n".length()), false);
                    if (rp_h != null) {
                        this.a(rp_h);
                    }
                    return;
                }
                if (rp_h == null) {
                    return;
                }
            }
            if (s != null && !line.startsWith("POL:")) {
                s = null;
            }
            if (line.startsWith("PNC:") && rp_eS != null) {
                if (line.length() == 4) {
                    rp_eS.a(Color.black);
                }
                else {
                    final StringTokenizer stringTokenizer;
                    rp_eS.a(new Color(rp_C.a((stringTokenizer = new StringTokenizer(line.substring(4), ";")).nextToken()) % 256, rp_C.a(stringTokenizer.nextToken()) % 256, rp_C.a(stringTokenizer.nextToken()) % 256));
                }
            }
            else if (line.startsWith("PNW:") && rp_eS != null) {
                if (line.length() == 4) {
                    rp_eS.a(0);
                }
                else {
                    rp_eS.a(rp_C.a(line.substring(4)));
                }
            }
            else if (line.startsWith("LIN:")) {
                final StringTokenizer stringTokenizer2;
                final int a = rp_C.a((stringTokenizer2 = new StringTokenizer(line.substring(4), ";")).nextToken());
                final int a2 = rp_C.a(stringTokenizer2.nextToken());
                final int a3 = rp_C.a(stringTokenizer2.nextToken());
                final int a4 = rp_C.a(stringTokenizer2.nextToken());
                if (b) {
                    rp_h.a_((int)Math.round(a * this.a), -(int)Math.round(a2 * this.b));
                    rp_h.c((int)Math.round(a3 * this.a), -(int)Math.round(a4 * this.b));
                }
                else {
                    rp_h.a_(a, -a2);
                    rp_h.c(a3, -a4);
                }
            }
            else if (line.startsWith("GRD:")) {
                s = line;
            }
            else if (line.startsWith("POL:")) {
                final StringTokenizer stringTokenizer3;
                final int a5;
                final int[] array = new int[a5 = rp_C.a((stringTokenizer3 = new StringTokenizer(line.substring(4), ";")).nextToken())];
                final int[] array2 = new int[a5];
                for (int i = 0; i < a5; ++i) {
                    final int a6 = rp_C.a(stringTokenizer3.nextToken());
                    final int a7 = rp_C.a(stringTokenizer3.nextToken());
                    if (b) {
                        array[i] = (int)Math.round(a6 * this.a);
                        array2[i] = -(int)Math.round(a7 * this.b);
                    }
                    else {
                        array[i] = a6;
                        array2[i] = -a7;
                    }
                }
                boolean b2 = false;
                boolean b3 = false;
                Color color = null;
                while (stringTokenizer3.hasMoreTokens()) {
                    switch (stringTokenizer3.nextToken().charAt(0)) {
                        case 'C':
                        case 'c': {
                            b2 = true;
                            continue;
                        }
                        case 'F':
                        case 'f': {
                            color = new Color(rp_C.a(stringTokenizer3.nextToken()) % 256, rp_C.a(stringTokenizer3.nextToken()) % 256, rp_C.a(stringTokenizer3.nextToken()) % 256);
                            b3 = true;
                            b2 = true;
                            continue;
                        }
                    }
                }
                if (s != null) {
                    final StringTokenizer stringTokenizer4;
                    final String nextToken = (stringTokenizer4 = new StringTokenizer(s.substring(4), ";")).nextToken();
                    int n4 = -1;
                    if (nextToken.equalsIgnoreCase("LIN")) {
                        n4 = 1;
                    }
                    else if (nextToken.equalsIgnoreCase("SPH")) {
                        n4 = 3;
                    }
                    else if (nextToken.equalsIgnoreCase("CYL")) {
                        n4 = 2;
                    }
                    if (n4 != -1) {
                        int a8 = rp_C.a(stringTokenizer4.nextToken());
                        final int a9 = rp_C.a(stringTokenizer4.nextToken());
                        int a10 = rp_C.a(stringTokenizer4.nextToken());
                        final int a11 = rp_C.a(stringTokenizer4.nextToken());
                        int n5;
                        int n6;
                        if (b) {
                            a8 = (int)Math.round(a8 * this.a);
                            n5 = -(int)Math.round(a9 * this.b);
                            a10 = (int)Math.round(a10 * this.a);
                            n6 = -(int)Math.round(a11 * this.b);
                        }
                        else {
                            n5 = -a9;
                            n6 = -a11;
                        }
                        if (stringTokenizer4.nextToken().equalsIgnoreCase("C1")) {
                            final Color color2 = new Color(rp_C.a(stringTokenizer4.nextToken()) % 256, rp_C.a(stringTokenizer4.nextToken()) % 256, rp_C.a(stringTokenizer4.nextToken()) % 256);
                            if (stringTokenizer4.nextToken().equalsIgnoreCase("C2")) {
                                rp_h.a(array, array2, a5, n4, new Point(a8, n5), new Point(a10, n6), color2, new Color(rp_C.a(stringTokenizer4.nextToken()) % 256, rp_C.a(stringTokenizer4.nextToken()) % 256, rp_C.a(stringTokenizer4.nextToken()) % 256));
                                continue;
                            }
                        }
                    }
                }
                if (rp_h == null) {
                    continue;
                }
                if (b3 && color != null) {
                    rp_h.a(array, array2, a5, color);
                }
                else if (b2) {
                    rp_h.b(array, array2, a5);
                }
                else {
                    rp_h.a(array, array2, a5);
                }
            }
            else {
                if (!line.startsWith("ELL:")) {
                    continue;
                }
                final StringTokenizer stringTokenizer5;
                final int a12 = rp_C.a((stringTokenizer5 = new StringTokenizer(line.substring(4), ";")).nextToken());
                final int a13 = rp_C.a(stringTokenizer5.nextToken());
                final int a14 = rp_C.a(stringTokenizer5.nextToken());
                final int a15 = rp_C.a(stringTokenizer5.nextToken());
                final int a16 = rp_C.a(stringTokenizer5.nextToken());
                final double a17 = rp_C.a(stringTokenizer5.nextToken());
                final double a18 = rp_C.a(stringTokenizer5.nextToken());
                if (b) {
                    double n7;
                    if (a14 == 0) {
                        n7 = a16 * this.a;
                    }
                    else {
                        n7 = a16 * this.b;
                    }
                    rp_h.a((int)Math.round(a12 * this.a), -(int)Math.round(a13 * this.b), (int)Math.round(a14 * this.a), (int)Math.round(a15 * this.b), (int)Math.round(n7), a17, a18, 0.0);
                }
                else {
                    rp_h.a(a12, -a13, a14, a15, a16, a17, a18, 0.0);
                }
            }
        }
        bufferedReader.close();
    }
    
    private void a(final InputStream inputStream) {
        try {
            this.a(new BufferedReader(new InputStreamReader(inputStream)));
        }
        catch (Exception ex) {
            throw new FileNotFoundException();
        }
    }
    
    private void a(final BufferedReader bufferedReader) {
        boolean b = false;
        final String line;
        if ((line = bufferedReader.readLine()) == null || line.length() == 0) {
            throw new IllegalArgumentException("Empty SEP string");
        }
        if (line.startsWith("<?xml") || line.indexOf("<svg") != -1) {
            b = true;
        }
        else if (line == null || !line.startsWith("$HEADER")) {
            throw new rp_s("Missing HEADER section");
        }
        if (b) {
            final StringBuilder sb;
            (sb = new StringBuilder()).append(line).append("\n");
            String line2;
            while ((line2 = bufferedReader.readLine()) != null) {
                sb.append(line2).append("\n");
            }
            this.a(sb.toString(), true);
            return;
        }
        String s;
        if ((s = this.a(bufferedReader)) == null) {
            throw new rp_s("Missing DATA section");
        }
        if (s.startsWith("$INFO")) {
            if (this.d == 1) {
                s = this.b(bufferedReader);
            }
            if (this.d == 2) {
                s = this.c(bufferedReader);
            }
        }
        if (s == null || !s.startsWith("$DATA")) {
            throw new rp_s("Missing DATA section");
        }
        this.d(bufferedReader);
        try {
            this.b(null, null);
        }
        catch (RuntimeException ex) {
            throw new rp_s("unspecified parsing error");
        }
    }
    
    private String a(final BufferedReader bufferedReader) {
        final Vector<rp_bV> vector = new Vector<rp_bV>();
        String line;
        while ((line = bufferedReader.readLine()) != null && line.charAt(0) != '$') {
            final String substring = line.substring(0, 4);
            final String substring2 = line.substring(4);
            if (substring.equals("TYP:")) {
                this.d = ((substring2.toLowerCase().compareTo("sku") == 0) ? 1 : 2);
            }
            else if (substring.equals("X  :")) {
                this.b = rp_C.a(substring2);
            }
            else if (substring.equals("Y  :")) {
                this.c = rp_C.a(substring2);
            }
            else if (substring.equals("WID:")) {
                this.a.width = rp_C.a(substring2);
            }
            else if (substring.equals("DEP:")) {
                this.a.height = rp_C.a(substring2);
            }
            else if (substring.equals("HEI:")) {
                this.a.height = rp_C.a(substring2);
            }
            else if (substring.equals("PRP:")) {
                if (0 >= rp_C.a(substring2)) {
                    continue;
                }
                this.a(1, true);
            }
            else if (substring.equals("VEW:")) {
                this.a = "FRONT".equalsIgnoreCase(substring2);
            }
            else {
                if (!substring.equals("MGN:") && !substring.equals("RPL:")) {
                    continue;
                }
                final StringTokenizer stringTokenizer;
                final String nextToken = (stringTokenizer = new StringTokenizer(substring2, ";")).nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                final String nextToken3 = stringTokenizer.nextToken();
                final int a = rp_C.a(stringTokenizer.nextToken());
                final int a2 = rp_C.a(stringTokenizer.nextToken());
                final int a3 = rp_C.a(stringTokenizer.nextToken());
                final int a4 = rp_C.a(stringTokenizer.nextToken());
                final int b = rp_bV.b(nextToken2);
                final int a5 = rp_bV.a(nextToken3);
                if (b <= 0 || a5 <= 0) {
                    continue;
                }
                vector.addElement(new rp_bV(nextToken, substring.equals("MGN:") ? rp_dc.a : rp_dc.b, b, a5, a, -a2, a3, -a4));
            }
        }
        if (vector.size() > 0) {
            this.a = new rp_bV[vector.size()];
            for (int i = 0; i < vector.size(); ++i) {
                this.a[i] = vector.elementAt(i);
            }
        }
        return line;
    }
    
    private String b(final BufferedReader bufferedReader) {
        final rp_eJ a = new rp_eJ();
        String line;
        while ((line = bufferedReader.readLine()) != null && line.charAt(0) != '$') {
            if (line.startsWith("SKU:")) {
                a.d = line.substring(4);
            }
            else if (line.startsWith("LAY:")) {
                this.a = rp_C.a(line.substring(4));
            }
            else if (line.startsWith("HEI:")) {
                try {
                    a.a = rp_C.a(line.substring(4));
                }
                catch (NumberFormatException ex) {
                    a.a = 0;
                }
            }
            else if (line.startsWith("CAT:")) {
                a.a = line.substring(4);
            }
            else if (line.startsWith("STY:")) {
                a.f = line.substring(4);
            }
            else if (line.startsWith("MNF:")) {
                a.b = line.substring(4);
            }
            else if (line.startsWith("TXT:")) {
                a.a(line.substring(4));
            }
            else {
                if (!line.startsWith("GRP:")) {
                    continue;
                }
                a.e = line.substring(4);
            }
        }
        this.a = a;
        return line;
    }
    
    private String c(final BufferedReader bufferedReader) {
        final rp_dK a = new rp_dK();
        String line;
        while ((line = bufferedReader.readLine()) != null && line.charAt(0) != '$') {
            if (line.startsWith("SKU:")) {
                a.d = line.substring(4);
            }
            else if (line.startsWith("LAY:")) {
                this.a = rp_C.a(line.substring(4));
            }
            else if (line.startsWith("TXT:")) {
                a.a(line.substring(4));
            }
            else {
                if (!line.startsWith("GRP:")) {
                    continue;
                }
                a.e = line.substring(4);
            }
        }
        this.a = a;
        return line;
    }
    
    private String d(final BufferedReader bufferedReader) {
        final StringWriter stringWriter = new StringWriter();
        String line;
        while ((line = bufferedReader.readLine()) != null && (line.length() <= 0 || line.charAt(0) != '$')) {
            stringWriter.write(line + "\n");
        }
        this.b = stringWriter.toString();
        return line;
    }
    
    public final Object clone() {
        final rp_fn rp_fn;
        (rp_fn = (rp_fn)super.clone()).a = this.a;
        return rp_fn;
    }
    
    public final rp_as a() {
        return this.a;
    }
}
