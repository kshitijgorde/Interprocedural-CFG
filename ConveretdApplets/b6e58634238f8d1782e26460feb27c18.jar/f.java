import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.PopupMenu;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.util.Date;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.text.ParseException;
import java.awt.Component;
import java.util.Properties;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    public static final String[] a;
    public Number b;
    public d c;
    public String d;
    public static /* synthetic */ Class e;
    public static /* synthetic */ Class f;
    public static /* synthetic */ Class g;
    public static /* synthetic */ Class h;
    public static /* synthetic */ Class i;
    
    public void a(final Properties properties) {
        this.d = properties.getProperty("ImageTunnel");
        final String property;
        if ((property = properties.getProperty("MinStringWidth")) != null) {
            try {
                this.a(this.c.d().i(property));
            }
            catch (ParseException ex) {
                this.c.c().a((Component)this.c.s, 4, new Object[] { property, "MinStringWidth", ex.getMessage() });
            }
        }
    }
    
    public static Vector a(final String s, final String s2) {
        final Vector<String> vector = new Vector<String>(4);
        if (s.length() > 0) {
            int n = 0;
            for (int i = s.indexOf(s2); i >= 0; i = s.indexOf(s2, n)) {
                vector.addElement(s.substring(n, i));
                n = i + s2.length();
            }
            vector.addElement(s.substring(n));
        }
        vector.trimToSize();
        return vector;
    }
    
    public URL a(final String s) throws MalformedURLException {
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex2) {
            try {
                url = new URL(this.c.r, s);
            }
            catch (MalformedURLException ex3) {
                final MalformedURLException ex = new MalformedURLException(ex2.getMessage() + "; " + ex3.getMessage());
                this.c.c().a((Component)this.c.s, 2, new Object[] { s, (f.e == null) ? (f.e = class$("java.net.URL")) : f.e, ex.getMessage() });
                throw ex;
            }
        }
        return url;
    }
    
    public URL b(final String s) throws MalformedURLException, SecurityException {
        URL a = this.a(s);
        if (!a.getHost().equalsIgnoreCase(this.c.r.getHost())) {
            if (this.d == null) {
                this.c.c().a((Component)this.c.s, 3, new Object[] { "null", "property", "unspecified" });
                System.getSecurityManager().checkConnect(a.getHost(), a.getPort());
            }
            if (!a.getProtocol().equalsIgnoreCase("http")) {
                this.c.c().a((Component)this.c.s, 3, new Object[] { this.d, "protocol", a.getProtocol() });
                System.getSecurityManager().checkConnect(a.getHost(), a.getPort());
            }
            if (a.getPort() >= 0 && a.getPort() != 80) {
                this.c.c().a((Component)this.c.s, 3, new Object[] { this.d, "port", new Integer(a.getPort()) });
                System.getSecurityManager().checkConnect(a.getHost(), a.getPort());
            }
            try {
                a = new URL("http://" + this.c.r.getHost() + this.d + "image" + "?" + ("server=" + a.getHost() + "&path=" + a.getFile()));
            }
            catch (MalformedURLException ex) {
                this.c.c().a((Component)this.c.s, 3, new Object[] { this.d, "url", ex.getMessage() });
            }
        }
        return a;
    }
    
    public Integer c(final String s) throws Exception {
        return new Integer(this.i(s).intValue());
    }
    
    public Double d(final String s) throws ParseException {
        return new Double(this.i(s).doubleValue());
    }
    
    public Boolean e(String trim) throws Exception {
        trim = trim.trim();
        Boolean b;
        if (trim.equalsIgnoreCase("true") || trim.equalsIgnoreCase("yes") || trim.equalsIgnoreCase("y") || trim.equals("1")) {
            b = Boolean.TRUE;
        }
        else {
            if (!trim.equalsIgnoreCase("false") && !trim.equalsIgnoreCase("no") && !trim.equalsIgnoreCase("n") && !trim.equals("0")) {
                this.c.c().a((Component)this.c.s, 2, new Object[] { trim, (f.f == null) ? (f.f = class$("java.lang.Boolean")) : f.f, "invalid" });
                throw new ParseException(trim, 0);
            }
            b = Boolean.FALSE;
        }
        return b;
    }
    
    public Color f(final String s) throws ParseException {
        Integer n = null;
        Integer n2 = null;
        Integer n3 = null;
        try {
            final String trim = s.trim();
            if (trim.startsWith("#")) {
                final String substring = trim.substring(1);
                if (substring.length() >= 2) {
                    n = this.c("0x" + substring.substring(0, 2));
                }
                if (substring.length() >= 4) {
                    n2 = this.c("0x" + substring.substring(2, 4));
                }
                if (substring.length() >= 6) {
                    n3 = this.c("0x" + substring.substring(4, 6));
                }
            }
            else {
                final StringTokenizer stringTokenizer = new StringTokenizer(trim, ",");
                if (stringTokenizer.hasMoreTokens()) {
                    n = this.c(stringTokenizer.nextToken());
                }
                if (stringTokenizer.hasMoreTokens()) {
                    n2 = this.c(stringTokenizer.nextToken());
                }
                if (stringTokenizer.hasMoreTokens()) {
                    n3 = this.c(stringTokenizer.nextToken());
                }
            }
            if (n != null && n2 != null && n3 != null) {
                return new Color(n, n2, n3);
            }
            this.c.c().a((Component)this.c.s, 2, new Object[] { s, (f.g == null) ? (f.g = class$("java.awt.Color")) : f.g, "invalid" });
            throw new ParseException(s, 0);
        }
        catch (Exception ex) {
            if (ex instanceof ParseException) {
                throw (ParseException)ex;
            }
            throw new ParseException(ex.getMessage(), 0);
        }
    }
    
    public Font g(final String s) throws ParseException {
        String nextToken = null;
        Integer h = null;
        Integer c = null;
        final StringTokenizer stringTokenizer = new StringTokenizer(s.trim(), ",");
        if (stringTokenizer.hasMoreTokens()) {
            int n;
            for (nextToken = stringTokenizer.nextToken(), n = 0; n < f.a.length && !f.a[n].equalsIgnoreCase(nextToken); ++n) {}
            if (n >= f.a.length) {
                this.c.c().a((Component)this.c.s, 2, new Object[] { s, (f.h == null) ? (f.h = class$("java.awt.Font")) : f.h, nextToken });
                throw new ParseException(s, 0);
            }
        }
        if (stringTokenizer.hasMoreTokens()) {
            h = this.h(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            try {
                c = this.c(stringTokenizer.nextToken());
            }
            catch (Exception ex) {
                if (ex instanceof ParseException) {
                    throw (ParseException)ex;
                }
                throw new ParseException(ex.getMessage(), 0);
            }
            if (c <= 0) {
                this.c.c().a((Component)this.c.s, 2, new Object[] { s, (f.h == null) ? (f.h = class$("java.awt.Font")) : f.h, c });
                throw new ParseException(s, 0);
            }
        }
        if (nextToken != null && h != null && c != null) {
            return new Font(nextToken, h, c);
        }
        this.c.c().a((Component)this.c.s, 2, new Object[] { s, (f.h == null) ? (f.h = class$("java.awt.Font")) : f.h, "invalid" });
        throw new ParseException(s, 0);
    }
    
    public Integer h(final String s) throws ParseException {
        final String trim = s.trim();
        Integer n;
        if (trim.equalsIgnoreCase("PLAIN")) {
            n = new Integer(0);
        }
        else if (trim.equalsIgnoreCase("ITALIC")) {
            n = new Integer(2);
        }
        else {
            if (!trim.equalsIgnoreCase("BOLD")) {
                this.c.c().a((Component)this.c.s, 2, new Object[] { s, "font style", trim });
                throw new ParseException(s, 0);
            }
            n = new Integer(1);
        }
        return n;
    }
    
    public Number i(String substring) throws ParseException {
        String s = substring.trim();
        if (s.startsWith("+")) {
            s = s.substring(1);
        }
        Number n;
        try {
            if (s.startsWith("0x") || s.startsWith("0X")) {
                n = Integer.valueOf(s.substring(2), 16);
            }
            else if (s.startsWith("0") && s.length() > 1 && s.indexOf(".") < 0) {
                n = Integer.valueOf(s.substring(1), 8);
            }
            else {
                Double n2 = null;
                if (substring.endsWith("%")) {
                    substring = substring.substring(0, substring.length() - 1);
                    n2 = new Double(0.01);
                }
                try {
                    n = Integer.valueOf(substring, 10);
                }
                catch (NumberFormatException ex2) {
                    n = Double.valueOf(substring);
                }
                if (n2 != null) {
                    n = new Double(n2 * n.doubleValue());
                }
            }
            if (n == null) {
                this.c.c().a((Component)this.c.s, 2, new Object[] { substring, (f.i == null) ? (f.i = class$("java.lang.Number")) : f.i, "overflow, etc." });
                throw new ParseException(substring, 0);
            }
        }
        catch (NumberFormatException ex) {
            this.c.c().a((Component)this.c.s, 2, new Object[] { substring, (f.i == null) ? (f.i = class$("java.lang.Number")) : f.i, ex.getMessage() });
            throw new ParseException(ex.getMessage(), 0);
        }
        return n;
    }
    
    public Date j(final String s) throws ParseException {
        try {
            return new Date(Date.parse(s));
        }
        catch (IllegalArgumentException ex) {
            throw new ParseException(ex.getMessage(), 0);
        }
    }
    
    public void a(final Number b) {
        this.b = b;
    }
    
    public Number a() {
        return this.b;
    }
    
    public String a(final String s, final int n, final FontMetrics fontMetrics, final Number n2) {
        final int length = "...".length();
        final int stringWidth = fontMetrics.stringWidth("...");
        final boolean b = n2 instanceof Integer;
        int intValue = 0;
        double doubleValue = 0.0;
        final StringBuffer sb = new StringBuffer(s.length() + length);
        sb.append(s);
        if (b) {
            intValue = n2.intValue();
        }
        else {
            doubleValue = n2.doubleValue();
        }
        final int stringWidth2 = fontMetrics.stringWidth(sb.toString());
        if (stringWidth2 <= n) {
            return sb.toString();
        }
        do {
            sb.setLength(sb.length() - 1);
            if (b && sb.length() < intValue) {
                sb.setLength(0);
            }
            else {
                final int stringWidth3 = fontMetrics.stringWidth(sb.toString());
                if (!b && doubleValue * stringWidth2 > stringWidth3) {
                    sb.setLength(0);
                }
                else {
                    final int n3 = stringWidth3 + stringWidth;
                    if (n3 <= n) {
                        sb.append("...");
                        break;
                    }
                    if (n3 <= stringWidth) {
                        sb.setLength(0);
                        break;
                    }
                    continue;
                }
            }
        } while (sb.length() > 0);
        return (sb.length() == 0) ? null : sb.toString();
    }
    
    public String a(final String s, final String s2, final String s3) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length * s3.length());
        int n = 0;
        for (int i = s.indexOf(s2, n); i != -1; i = s.indexOf(s2, n)) {
            sb.append(s.substring(n, i));
            sb.append(s3);
            n = i + s2.length();
        }
        sb.append(s.substring(n, length));
        return sb.toString();
    }
    
    public Point a(final Rectangle rectangle, Rectangle intersection, final Dimension size, final int n) {
        final Rectangle rectangle2 = new Rectangle();
        final Rectangle rectangle3 = new Rectangle();
        intersection = intersection.intersection(rectangle);
        final int width = intersection.x - rectangle.x;
        final int width2 = rectangle.width - intersection.width - width;
        final boolean b = width > width2;
        if (b) {
            rectangle2.x = rectangle.x;
            rectangle2.width = width;
            rectangle3.x = intersection.x - size.width;
        }
        else {
            rectangle2.x = intersection.x + intersection.width;
            rectangle2.width = width2;
            rectangle3.x = rectangle2.x;
        }
        final int height = intersection.y - rectangle.y;
        final int height2 = rectangle.height - intersection.height - height;
        final boolean b2 = height > height2;
        if (b2) {
            rectangle2.y = rectangle.y;
            rectangle2.height = height;
            rectangle3.y = intersection.y - size.height;
        }
        else {
            rectangle2.y = intersection.y + intersection.height;
            rectangle2.height = height2;
            rectangle3.y = rectangle2.y;
        }
        rectangle3.setSize(size);
        int n2 = rectangle2.width - rectangle3.width;
        int n3 = rectangle2.height - rectangle3.height;
        Label_0611: {
            if (n2 < 0 || n3 < 0) {
                while (n2 >= 0 || n3 < 0) {
                    if (n2 >= 0 && n3 < 0) {
                        final Rectangle rectangle4 = rectangle3;
                        rectangle4.y += (b2 ? -1 : 1) * n3;
                        final int n4 = rectangle.y - rectangle3.y;
                        final int n5 = rectangle3.y + rectangle3.height - (rectangle.y + rectangle.height);
                        if (n4 > 0) {
                            final Rectangle rectangle5 = rectangle3;
                            rectangle5.y += n4;
                            final Rectangle rectangle6 = rectangle3;
                            rectangle6.height -= n4;
                            break Label_0611;
                        }
                        if (n5 > 0) {
                            final Rectangle rectangle7 = rectangle3;
                            rectangle7.height -= n5;
                        }
                        break Label_0611;
                    }
                    else if (n2 >= n3) {
                        final Rectangle rectangle8 = rectangle3;
                        rectangle8.width += n2;
                        final Rectangle rectangle9 = rectangle3;
                        rectangle9.x += (b ? -1 : 0) * n2;
                        n2 = 0;
                    }
                    else {
                        final Rectangle rectangle10 = rectangle3;
                        rectangle10.height += n3;
                        final Rectangle rectangle11 = rectangle3;
                        rectangle11.y += (b2 ? -1 : 0) * n3;
                        n3 = 0;
                    }
                }
                final Rectangle rectangle12 = rectangle3;
                rectangle12.x += (b ? -1 : 1) * n2;
                final int n6 = rectangle.x - rectangle3.x;
                final int n7 = rectangle3.x + rectangle3.width - (rectangle.x + rectangle.width);
                if (n6 > 0) {
                    final Rectangle rectangle13 = rectangle3;
                    rectangle13.x += n6;
                    final Rectangle rectangle14 = rectangle3;
                    rectangle14.width -= n6;
                }
                else if (n7 > 0) {
                    final Rectangle rectangle15 = rectangle3;
                    rectangle15.width -= n7;
                }
            }
        }
        size.setSize(rectangle3.getSize());
        return rectangle3.getLocation();
    }
    
    public Object a(final s s, final String s2, final aa aa) {
        return this.a(s, s2, aa, new Object[1], null);
    }
    
    public Object a(final s s, String trim, final aa aa, final Object[] array, final String s2) {
        int n = 0;
        String s3 = null;
        array[0] = null;
        if (trim != null) {
            trim = trim.trim();
            int n2 = 0;
            String s4 = null;
            for (int n3 = 0; n == 0 && n3 < trim.length() + 1; ++n3) {
                char char1;
                if (n3 < trim.length()) {
                    char1 = trim.charAt(n3);
                }
                else {
                    char1 = ' ';
                }
                if (char1 != ' ' || s4 != null) {
                    switch (n2) {
                        case 0: {
                            if (char1 == '\"') {
                                s4 = "";
                                n2 = 1;
                                break;
                            }
                            s4 = "" + char1;
                            n2 = 9;
                            break;
                        }
                        case 1:
                        case 7: {
                            if (char1 == '\"') {
                                if (s3 == null || s3 instanceof String) {
                                    if (s3 == null) {
                                        s3 = "";
                                    }
                                    s3 += s4;
                                    if (array[0] == null) {
                                        array[0] = new Object(this.c, trim.length()) {
                                            public d a;
                                            public Vector b;
                                            public Vector c;
                                            
                                            {
                                                this.a = a;
                                                this.b = new Vector(n);
                                                this.c = new Vector(n);
                                            }
                                            
                                            public final void a(final Color color, final int y) {
                                                if (this.b.size() == 0) {
                                                    if (y > 0) {
                                                        this.b.addElement(new Point(0, y));
                                                        this.c.addElement(color);
                                                    }
                                                }
                                                else {
                                                    final Point point = this.b.elementAt(this.b.size() - 1);
                                                    final Color color2 = this.c.elementAt(this.c.size() - 1);
                                                    if (y > point.y) {
                                                        if ((color2 == null && color == null) || (color2 != null && color2.equals(color))) {
                                                            point.y = y;
                                                        }
                                                        else {
                                                            this.b.addElement(new Point(point.y, y));
                                                            this.c.addElement(color);
                                                        }
                                                    }
                                                }
                                            }
                                            
                                            public final int a() {
                                                return this.b.size();
                                            }
                                            
                                            public final Point a(final int n) {
                                                return this.b.elementAt(n);
                                            }
                                            
                                            public final Color b(final int n) {
                                                return this.c.elementAt(n);
                                            }
                                        };
                                    }
                                    ((am.ah)array[0]).a(null, s3.length());
                                    s4 = null;
                                    n2 = 8;
                                    break;
                                }
                                this.c.c().a((Component)this.c.s, 18, new Object[] { trim, trim.substring(n3), new Integer(n2) });
                                n = 1;
                                break;
                            }
                            else {
                                if (char1 == '\\') {
                                    n2 = 2;
                                    break;
                                }
                                s4 += char1;
                                n2 = 1;
                                break;
                            }
                            break;
                        }
                        case 2: {
                            if (Character.toLowerCase(char1) == 'u') {
                                n2 = 3;
                                break;
                            }
                            s4 += char1;
                            n2 = 7;
                            break;
                        }
                        case 3: {
                            if (trim.length() >= n3 + 4) {
                                try {
                                    s4 += (char)Integer.parseInt(trim.substring(n3, n3 + 4), 16);
                                }
                                catch (NumberFormatException ex) {
                                    this.c.c().a((Component)this.c.s, 18, new Object[] { trim, ex.getMessage(), new Integer(n2) });
                                    n = 1;
                                    break;
                                }
                                n3 += 3;
                                n2 = 7;
                                break;
                            }
                            this.c.c().a((Component)this.c.s, 18, new Object[] { trim, trim.substring(n3), new Integer(n2) });
                            n = 1;
                            break;
                        }
                        case 8: {
                            if (char1 == '+') {
                                n2 = 0;
                                break;
                            }
                            this.c.c().a((Component)this.c.s, 18, new Object[] { trim, trim.substring(n3), new Integer(n2) });
                            n = 1;
                            break;
                        }
                        case 9: {
                            if (char1 == ' ' || char1 == '+') {
                                Object o = null;
                                y y = null;
                                if (s4.endsWith("()") && !s4.equals("colorValue()")) {
                                    s4 = s4.substring(0, s4.length() - "()".length());
                                    if (s4.equals("initToken")) {
                                        o = s.b();
                                    }
                                    else if (s4.equals("key")) {
                                        if (aa != null) {
                                            o = aa.b;
                                        }
                                    }
                                    else if (s4.equals("gKey")) {
                                        if (aa != null) {
                                            o = aa.b;
                                            for (int i = 0; i < s.q(); ++i) {
                                                if (s.a(s.f(i).b, aa.b) && !s.f(i).b.equals(aa.b)) {
                                                    o = o + "," + s.f(i).b;
                                                }
                                            }
                                        }
                                    }
                                    else if (s4.equals("sKey")) {
                                        o = "";
                                        for (int j = 0; j < s.q(); ++j) {
                                            if (((String)o).length() > 0) {
                                                o += ",";
                                            }
                                            o += s.f(j).b;
                                        }
                                    }
                                    else if (s4.equals("hKey")) {
                                        s d;
                                        for (d = s; d.d != null; d = d.d) {}
                                        o = "";
                                        for (int k = 0; k < d.f(); ++k) {
                                            if (k > 0) {
                                                o += ";";
                                            }
                                            for (int l = 0; l < d.b(k).q(); ++l) {
                                                if (l > 0) {
                                                    o += ",";
                                                }
                                                o += d.b(k).f(l).b;
                                            }
                                        }
                                    }
                                    else {
                                        this.c.c().a((Component)this.c.s, 18, new Object[] { trim, trim.substring(n3), new Integer(n2) });
                                        o = null;
                                    }
                                    if (o == null) {
                                        n = 1;
                                        break;
                                    }
                                }
                                else {
                                    if ((y = s.c(s4)) == null && !s4.equals("colorValue()")) {
                                        this.c.c().a((Component)this.c.s, 18, new Object[] { trim, trim.substring(n3), new Integer(n2) });
                                        n = 1;
                                        break;
                                    }
                                    if (s4.equals("colorValue()") && (y = s.f.b()) != null) {
                                        s4 = y.f;
                                    }
                                    if (aa == null) {
                                        n = 1;
                                        break;
                                    }
                                    if (y != null) {
                                        if (s2 != null && s2.equals(s4)) {
                                            o = y.h.a(aa.c(s4));
                                        }
                                        else {
                                            o = y.h.a(aa.b(s4));
                                        }
                                    }
                                    else {
                                        o = null;
                                    }
                                }
                                if (o instanceof URL && s3 == null) {
                                    s3 = (String)o;
                                    array[0] = ((y.h.c == 'U') ? Boolean.TRUE : Boolean.FALSE);
                                }
                                else {
                                    if (s3 != null && !(s3 instanceof String)) {
                                        this.c.c().a((Component)this.c.s, 18, new Object[] { trim, trim.substring(n3), new Integer(n2) });
                                        n = 1;
                                        break;
                                    }
                                    if (s3 == null) {
                                        s3 = "";
                                    }
                                    if (o == null) {
                                        o = s.c().a;
                                    }
                                    s3 += o;
                                    if (array[0] == null) {
                                        array[0] = new Object(this.c, trim.length()) {
                                            public d a;
                                            public Vector b;
                                            public Vector c;
                                            
                                            {
                                                this.a = a;
                                                this.b = new Vector(n);
                                                this.c = new Vector(n);
                                            }
                                            
                                            public final void a(final Color color, final int y) {
                                                if (this.b.size() == 0) {
                                                    if (y > 0) {
                                                        this.b.addElement(new Point(0, y));
                                                        this.c.addElement(color);
                                                    }
                                                }
                                                else {
                                                    final Point point = this.b.elementAt(this.b.size() - 1);
                                                    final Color color2 = this.c.elementAt(this.c.size() - 1);
                                                    if (y > point.y) {
                                                        if ((color2 == null && color == null) || (color2 != null && color2.equals(color))) {
                                                            point.y = y;
                                                        }
                                                        else {
                                                            this.b.addElement(new Point(point.y, y));
                                                            this.c.addElement(color);
                                                        }
                                                    }
                                                }
                                            }
                                            
                                            public final int a() {
                                                return this.b.size();
                                            }
                                            
                                            public final Point a(final int n) {
                                                return this.b.elementAt(n);
                                            }
                                            
                                            public final Color b(final int n) {
                                                return this.c.elementAt(n);
                                            }
                                        };
                                    }
                                    ((am.ah)array[0]).a((y != null) ? y.h.a(aa.b(s4), s.f) : null, s3.length());
                                }
                                s4 = null;
                                n2 = 8;
                                break;
                            }
                            s4 += char1;
                            n2 = 9;
                            break;
                        }
                        default: {
                            this.c.c().a((Component)this.c.s, 18, new Object[] { trim, "" + trim.substring(n3), new Integer(n2) });
                            n = 1;
                            break;
                        }
                    }
                }
            }
        }
        if (n != 0) {
            s3 = null;
        }
        return s3;
    }
    
    public Vector a(final s s, final Vector vector, final Vector vector2, final aa aa) {
        Vector<ak> vector3 = null;
        if (vector != null && vector.size() > 0) {
            vector3 = new Vector<ak>(vector.size());
            final Object[] array = { null };
            for (int i = 0; i < vector.size(); ++i) {
                Object a = this.a(s, vector.elementAt(i), aa, array, null);
                if (a instanceof URL) {
                    vector3.addElement(new ak(this.c, vector2.elementAt(i), (URL)a, (boolean)array[0]));
                }
                else {
                    if (a == null) {
                        a = "";
                        array[0] = new Object(this.c, 0) {
                            public d a = a;
                            public Vector b = new Vector(n);
                            public Vector c = new Vector(n);
                            
                            public final void a(final Color color, final int y) {
                                if (this.b.size() == 0) {
                                    if (y > 0) {
                                        this.b.addElement(new Point(0, y));
                                        this.c.addElement(color);
                                    }
                                }
                                else {
                                    final Point point = this.b.elementAt(this.b.size() - 1);
                                    final Color color2 = this.c.elementAt(this.c.size() - 1);
                                    if (y > point.y) {
                                        if ((color2 == null && color == null) || (color2 != null && color2.equals(color))) {
                                            point.y = y;
                                        }
                                        else {
                                            this.b.addElement(new Point(point.y, y));
                                            this.c.addElement(color);
                                        }
                                    }
                                }
                            }
                            
                            public final int a() {
                                return this.b.size();
                            }
                            
                            public final Point a(final int n) {
                                return this.b.elementAt(n);
                            }
                            
                            public final Color b(final int n) {
                                return this.c.elementAt(n);
                            }
                        };
                    }
                    vector3.addElement((ak)new am(this.c, vector2.elementAt(i), (String)a, array[0]));
                }
            }
        }
        return vector3;
    }
    
    public Vector a(final s s, final Vector vector, final Vector vector2, final aa aa, final Vector vector3, final String s2) {
        Vector<ak> vector4 = null;
        if (vector != null && vector.size() > 0) {
            vector4 = new Vector<ak>(vector.size());
            final Object[] array = { null };
            for (int i = 0; i < vector.size(); ++i) {
                Object a = this.a(s, vector.elementAt(i), aa, array, s2);
                if (a instanceof URL) {
                    vector4.addElement(new ak(this.c, vector2.elementAt(i), (URL)a, (boolean)array[0]));
                }
                else {
                    if (a == null) {
                        a = "";
                        array[0] = this.c.i().o;
                    }
                    else {
                        array[0] = vector3.elementAt(i);
                    }
                    vector4.addElement((ak)new am(this.c, vector2.elementAt(i), (String)a, array[0]));
                }
            }
        }
        return vector4;
    }
    
    public Vector a(Vector vector, final s s, final Vector vector2, final aa aa) {
        if (vector == null) {
            vector = new Vector<ab>(4 + ((vector2 == null) ? 0 : vector2.size()));
        }
        vector.removeAllElements();
        if (vector2 != null) {
            for (int i = 0; i < vector2.size(); ++i) {
                final ab ab = vector2.elementAt(i);
                if (ab.a()) {
                    if (vector.size() > 0 && vector.elementAt(vector.size() - 1) != ab.a) {
                        vector.addElement(ab.a);
                    }
                }
                else {
                    final Object a = this.a(s, ab.b, aa);
                    if (a != null && !(a instanceof String)) {
                        this.c.c().a((Component)this.c.s, 18, new Object[] { ab.b, a, "?" });
                    }
                    else {
                        final String s2;
                        if ((s2 = (String)a) != null) {
                            if (!ab.b()) {
                                vector.addElement(new ab(s2, null, null));
                            }
                            else {
                                final Object a2 = this.a(s, ab.c, aa);
                                if (a2 != null && !(a2 instanceof String)) {
                                    this.c.c().a((Component)this.c.s, 18, new Object[] { ab.c, a2, "?" });
                                }
                                else {
                                    final String s3;
                                    if ((s3 = (String)a2) != null) {
                                        String s4 = null;
                                        if (ab.d != null) {
                                            final Object a3 = this.a(s, ab.d, aa);
                                            if (a3 != null && !(a3 instanceof String)) {
                                                this.c.c().a((Component)this.c.s, 18, new Object[] { ab.d, a3, "?" });
                                                continue;
                                            }
                                            if ((s4 = (String)a3) == null) {
                                                continue;
                                            }
                                        }
                                        vector.addElement(new ab(s2, s3, s4));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        final Vector<ab> vector3 = new Vector<ab>(4);
        if (vector2 != null && vector2.size() > 0) {
            final aa ab2 = s.ab();
            if (aa != null) {
                if (ab2 == null || !ab2.equals(aa) || !s.x()) {
                    vector3.addElement(new ab("Set Reference Cell (Color and Value)", "hmc://refabs:" + (aa.c + 1) + "/" + aa.b, null));
                }
                if (ab2 == null || !ab2.equals(aa) || !s.y()) {
                    vector3.addElement(new ab("Set Reference Cell (Color)", "hmc://refrel:" + (aa.c + 1) + "/" + aa.b, null));
                }
            }
            if (ab2 != null) {
                vector3.addElement(new ab("Set Reference Cell Off", "hmc://refoff", null));
            }
        }
        if (s.af().b()) {
            if (vector3.size() > 0) {
                vector3.addElement(ab.a);
            }
            if (s.af().c()) {
                vector3.addElement(new ab("Back", "hmc://backward", null));
            }
            if (s.af().d()) {
                vector3.addElement(new ab("Forward", "hmc://forward", null));
            }
        }
        if (vector3.size() > 0) {
            if (vector.size() > 0 && vector.elementAt(vector.size() - 1) != ab.a) {
                vector.addElement(ab.a);
            }
            for (int j = 0; j < vector3.size(); ++j) {
                vector.addElement(vector3.elementAt(j));
            }
        }
        return vector;
    }
    
    public static Vector k(final String s) {
        String substring = null;
        String s2 = null;
        String substring2 = null;
        String substring3 = null;
        final Vector<String> vector = new Vector<String>(4);
        if (s != null) {
            substring = s;
            final int index;
            if ((index = substring.indexOf("://")) >= 0) {
                s2 = substring.substring(index + "://".length());
                substring = substring.substring(0, index);
                final int index2;
                if ((index2 = s2.indexOf("/")) >= 0) {
                    substring3 = s2.substring(index2 + "/".length());
                    s2 = s2.substring(0, index2);
                }
                final int index3;
                if ((index3 = s2.indexOf(":")) >= 0) {
                    substring2 = s2.substring(index3 + ":".length());
                    s2 = s2.substring(0, index3);
                }
            }
        }
        vector.addElement(substring);
        vector.addElement(s2);
        vector.addElement(substring2);
        vector.addElement(substring3);
        return vector;
    }
    
    public static boolean a(final MouseEvent mouseEvent, final boolean b) {
        return !(mouseEvent.getSource() instanceof PopupMenu) && ((!b && (mouseEvent.isPopupTrigger() || (mouseEvent.getID() == 502 && mouseEvent.isAltDown()))) || (b && mouseEvent.getID() == 501 && (mouseEvent.getModifiers() & 0xC) == 0x0));
    }
    
    public static synchronized void b() {
        System.gc();
    }
    
    public static Rectangle c() {
        return new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    }
    
    public f(final d c) {
        this.b = new Integer(2);
        this.c = c;
    }
    
    public Frame a(final Component component) {
        Component parent;
        for (parent = component; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        return (Frame)((parent == null) ? new Frame() : parent);
    }
    
    public static void a(final Component component, final Component component2, final boolean b) {
        boolean b2 = false;
        Rectangle rectangle = component2.getBounds();
        if (component.isShowing()) {
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            final Rectangle rectangle2 = new Rectangle(component.getLocationOnScreen(), component.getSize());
            rectangle = component2.getBounds();
            b2 = false;
            rectangle.x = rectangle2.x + (b ? rectangle2.width : 0) + 50;
            if (rectangle.x + rectangle.width > screenSize.width - 50 && (rectangle.x = rectangle2.x - (b ? rectangle.width : 0) - 50) < 0) {
                rectangle.x = (b ? rectangle2.x : 0);
            }
            if (b) {
                if (rectangle.x == rectangle2.x) {
                    rectangle.y = rectangle2.y + rectangle2.height + 50;
                    if (rectangle.y + rectangle.height > screenSize.height - 50 && (rectangle.y = rectangle2.y - rectangle.height - 50) < 0) {
                        b2 = true;
                    }
                }
                else {
                    rectangle.y = rectangle2.y;
                }
            }
            else if (rectangle.x == 0) {
                rectangle.y = 0;
            }
            else {
                rectangle.y = rectangle2.y + 50 * ((rectangle.x >= rectangle2.x) ? 1 : -1);
                if (rectangle.y + rectangle.height > screenSize.height - 50 || rectangle.y < 0) {
                    final Rectangle rectangle3 = rectangle;
                    final Rectangle rectangle4 = rectangle;
                    final boolean b3 = false;
                    rectangle4.y = (b3 ? 1 : 0);
                    rectangle3.x = (b3 ? 1 : 0);
                }
            }
        }
        else if (b) {
            b2 = true;
        }
        else {
            final Rectangle rectangle5 = rectangle;
            final Rectangle rectangle6 = rectangle;
            final boolean b4 = false;
            rectangle6.y = (b4 ? 1 : 0);
            rectangle5.x = (b4 ? 1 : 0);
        }
        if (b2) {
            a(component, component2, false);
        }
        else {
            component2.setLocation(rectangle.x, rectangle.y);
        }
    }
    
    public static final Color a(final Color color) {
        Color color2;
        if (color != null) {
            color2 = ((0.212671 * color.getRed() + 0.71516 * color.getGreen() + 0.072169 * color.getBlue() < 130.0) ? Color.white : Color.black);
        }
        else {
            color2 = null;
        }
        return color2;
    }
    
    public void d() {
        this.d = null;
        this.c = null;
    }
    
    public String a(final String s, final String s2, final int n) throws Exception {
        return this.a(true, s, s2, n);
    }
    
    public String b(final String s, final String s2, final int n) throws Exception {
        return this.a(false, s, s2, n);
    }
    
    private String a(final boolean b, final String s, final String s2, final int n) throws Exception {
        final int length = s2.length();
        String s3 = b ? s : "";
        String s4 = b ? "" : s;
        if (b) {
            final int length2 = s3.length();
            int n2;
            if ((n2 = length2) > 0 && length2 % 3 > 0) {
                n2 = length2 + 3 - length2 % 3;
            }
            for (int i = 0; i < 4 * n2 / 3; ++i) {
                if (i > 4 * length2 / 3) {
                    s4 += '!';
                }
                else {
                    switch (i % 4) {
                        case 0: {
                            s4 += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(this.a(length2, ' ', 95, s2, length, n, s3, i) / '\u0004');
                            break;
                        }
                        case 1: {
                            s4 += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt('\u0010' * (this.a(length2, ' ', 95, s2, length, n, s3, i) % '\u0004') + this.a(length2, ' ', 95, s2, length, n, s3, i + 1) / '\u0010');
                            break;
                        }
                        case 2: {
                            s4 += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt('\u0004' * (this.a(length2, ' ', 95, s2, length, n, s3, i) % '\u0010') + this.a(length2, ' ', 95, s2, length, n, s3, i + 1) / '@');
                            break;
                        }
                        default: {
                            s4 += "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".charAt(this.a(length2, ' ', 95, s2, length, n, s3, i) % '@');
                            break;
                        }
                    }
                }
            }
        }
        else {
            final int length3 = s4.length();
            int n3 = 0;
            for (int n4 = length3 - 1; n4 >= 0 && s4.charAt(n4) == '!'; --n4, ++n3) {}
            for (int n5 = 3 * (length3 - n3) / 4, j = 0; j < n5; ++j) {
                switch (j % 3) {
                    case 0: {
                        s3 += this.a(' ', 95, s2, length, n, (char)(4 * "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".indexOf(s4.charAt(4 * j / 3)) + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".indexOf(s4.charAt(4 * j / 3 + 1)) / 16), j);
                        break;
                    }
                    case 1: {
                        s3 += this.a(' ', 95, s2, length, n, (char)(16 * ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".indexOf(s4.charAt(4 * j / 3)) % 16) + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".indexOf(s4.charAt(4 * j / 3 + 1)) / 4), j);
                        break;
                    }
                    default: {
                        s3 += this.a(' ', 95, s2, length, n, (char)(64 * ("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".indexOf(s4.charAt(4 * j / 3)) % 4) + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-".indexOf(s4.charAt(4 * j / 3 + 1))), j);
                        break;
                    }
                }
            }
        }
        return b ? s4 : s3;
    }
    
    private char a(final int n, final char c, final int n2, final String s, final int n3, final int n4, final String s2, final int n5) {
        char c2;
        if (3 * n5 / 4 >= n) {
            c2 = '\0';
        }
        else {
            c2 = (char)(c + (s2.charAt(3 * n5 / 4) - c + n4 * (s.charAt(3 * n5 / 4 % n3) - c)) % n2);
        }
        return c2;
    }
    
    private char a(final char c, final int n, final String s, final int n2, final int n3, final char c2, final int n4) {
        return (char)(c + (n3 * n + (c2 - c) - n3 * (s.charAt(n4 % n2) - c)) % n);
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        a = Toolkit.getDefaultToolkit().getFontList();
    }
}
