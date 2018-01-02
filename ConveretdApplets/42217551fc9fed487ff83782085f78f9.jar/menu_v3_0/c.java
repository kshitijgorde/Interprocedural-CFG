// 
// Decompiled by Procyon v0.5.30
// 

package menu_v3_0;

import java.awt.Image;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class c
{
    public static String a(final URL url, final String s) {
        if (s == null || url == null) {
            return null;
        }
        StringBuffer sb;
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(String.valueOf(url) + s).openConnection().getInputStream()));
            sb = new StringBuffer();
            int read;
            while ((read = bufferedReader.read()) != -1) {
                sb.append((char)read);
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            return null;
        }
        if (sb != null) {
            return sb.toString();
        }
        return null;
    }
    
    public static int a(final String s, final int n) {
        if (s == null || s.length() == 0) {
            return n;
        }
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static Color a(String string, final Color color) {
        if (string == null || string.length() < 3) {
            return color;
        }
        if (string.charAt(0) == '#') {
            string = "0x" + string.substring(1);
        }
        try {
            return Color.decode(string);
        }
        catch (NumberFormatException ex) {
            return color;
        }
    }
    
    public static boolean a(String upperCase, final boolean b) {
        if (upperCase == null || upperCase.length() == 0) {
            return b;
        }
        upperCase = upperCase.toUpperCase();
        return upperCase.equals("YES") || upperCase.equals("ON") || upperCase.equals("TRUE") || (!upperCase.equals("NO") && !upperCase.equals("OFF") && !upperCase.equals("FALSE") && b);
    }
    
    public static Vector a(final String[] array, final URL url, final boolean b) {
        return a(array, new int[] { 0 }, url, b);
    }
    
    public static Vector a(final String[] array) {
        final Vector try1 = try(array, new int[] { 0 });
        if (try1.size() == 0) {
            final a a = new a();
            a.do = new Font(a.a, a.int, a.case);
            try1.addElement(a);
        }
        return try1;
    }
    
    public static Vector a(final String[] array, final e else1, final Vector vector, final Vector vector2, final Image image, final Image image2, final Image image3, final Image image4, final Image image5, final Image image6, final String s, final String s2) {
        final byte[] array2 = { 117, 110, 114, 101, 103, 105, 115, 116, 101, 114, 101, 100 };
        final byte[] array3 = { 40, 99, 41, 32, 101, 120, 115, 121, 115, 32, 71, 98, 82, 32, 45, 32, 115, 111, 102, 116, 119, 97, 114, 101, 64, 101, 120, 115, 121, 115, 46, 110, 101, 116 };
        final byte[] array4 = { 95, 98, 108, 97, 110, 107 };
        final byte[] array5 = { 104, 116, 116, 112, 58, 47, 47, 119, 119, 119, 46, 101, 120, 115, 121, 115, 46, 110, 101, 116, 47, 106, 116, 109, 105, 110, 100, 101, 120, 46, 104, 116, 109 };
        final int[] array6 = { 0 };
        final Vector a = a(array, new int[] { 0 }, array6, else1, vector, vector2, image2, image, image3, image4, image5, image6, s, s2);
        if (a != null) {
            final e e = new e();
            final int[] array7 = array6;
            final int n = 0;
            ++array7[n];
            e.void = array6[0];
            (e.b = new Vector()).addElement(new String(array2));
            e.if = new String(array3);
            e.else = else1;
            e.g = new String(array4);
            e.f = new String(array5);
            a.addElement(e);
        }
        return a;
    }
    
    private static Vector a(final String[] array, final int[] array2, final int[] array3, final e e, final Vector vector, final Vector vector2, final Image image, final Image image2, final Image image3, final Image image4, final Image image5, final Image image6, final String s, final String s2) {
        final Vector<e> vector3 = new Vector<e>();
        e e2 = new e();
        String s3 = null;
        String s4 = null;
        String s5 = null;
        e2.void = array3[0];
        final int n = 0;
        ++array3[n];
        byte(array, array2);
        if (array[0] == null || array[0].length() <= array2[0] || array[0].charAt(array2[0]) != '{') {
            return vector3;
        }
        final int n2 = 0;
        ++array2[n2];
        if (e != null && e2.g == null) {
            e2.g = e.g;
        }
        if (e2.g == null) {
            e2.g = s;
        }
        if (e != null && e2.try == null) {
            e2.try = e.try;
        }
        if (e2.try == null) {
            e2.try = s2;
        }
        if (e != null && e2.long == null) {
            e2.long = e.long;
        }
        if (e != null && e2.a == null) {
            e2.a = e.a;
        }
        while (true) {
            byte(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return vector3;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == ';') {
                final int n3 = 0;
                ++array2[n3];
            }
            else if (char1 == '|') {
                final int n4 = 0;
                ++array2[n4];
                e2.else = e;
                if (e2.null == null) {
                    e2.goto = 30;
                    a(e2, vector, s3, s4, s5, image4, image5, image6);
                }
                else {
                    a(e2, vector, s3, s4, s5, image, image2, image3);
                }
                s3 = null;
                s4 = null;
                s5 = null;
                vector3.addElement(e2);
                e2 = new e();
                e2.void = array3[0];
                final int n5 = 0;
                ++array3[n5];
                if (e != null && e2.g == null) {
                    e2.g = e.g;
                }
                if (e2.g == null) {
                    e2.g = s;
                }
                if (e != null && e2.try == null) {
                    e2.try = e.try;
                }
                if (e2.try == null) {
                    e2.try = s2;
                }
                if (e != null && e2.long == null) {
                    e2.long = e.long;
                }
                if (e == null || e2.a != null) {
                    continue;
                }
                e2.a = e.a;
            }
            else if (char1 == '{') {
                e2.null = a(array, array2, array3, e2, vector, vector2, image, image2, image3, image4, image5, image6, s, s2);
            }
            else {
                if (char1 == '}') {
                    final int n6 = 0;
                    ++array2[n6];
                    e2.else = e;
                    if (e2.null == null) {
                        e2.goto = 30;
                        a(e2, vector, s3, s4, s5, image4, image5, image6);
                    }
                    else {
                        a(e2, vector, s3, s4, s5, image, image2, image3);
                    }
                    vector3.addElement(e2);
                    return vector3;
                }
                final String a = a(array, array2);
                if (a == null) {
                    array[0] = null;
                    return vector3;
                }
                if (a.equals("TOPIC")) {
                    final Vector do1 = do(array, array2);
                    if (do1 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.b = do1;
                }
                else if (a.equals("SYMBOL")) {
                    final String if1 = if(array, array2);
                    if (if1 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    s3 = if1;
                }
                else if (a.equals("OPENSYMBOL")) {
                    final String if2 = if(array, array2);
                    if (if2 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    s4 = if2;
                }
                else if (a.equals("BARSYMBOL")) {
                    final String if3 = if(array, array2);
                    if (if3 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    s5 = if3;
                }
                else if (a.equals("URL")) {
                    final String if4 = if(array, array2);
                    if (if4 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.f = if4;
                    if (e2.f.length() <= 4 || !if4.substring(0, 4).toUpperCase().equals("WWW.")) {
                        continue;
                    }
                    e2.f = "http://" + e2.f;
                }
                else if (a.equals("URL2")) {
                    final String if5 = if(array, array2);
                    if (if5 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.int = if5;
                    if (e2.int.length() <= 4 || !if5.substring(0, 4).toUpperCase().equals("WWW.")) {
                        continue;
                    }
                    e2.int = "http://" + e2.int;
                }
                else if (a.equals("TARGET")) {
                    final String if6 = if(array, array2);
                    if (if6 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.g = if6;
                }
                else if (a.equals("TARGET2")) {
                    final String if7 = if(array, array2);
                    if (if7 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.try = if7;
                }
                else if (a.equals("JSCALL")) {
                    final String if8 = if(array, array2);
                    if (if8 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.a = if8;
                }
                else if (a.equals("IMAGE")) {
                    final String if9 = if(array, array2);
                    if (if9 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.long = if9.toUpperCase();
                }
                else if (a.equals("FONT")) {
                    final String if10 = if(array, array2);
                    if (if10 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.case = a(vector2, if10);
                }
                else if (a.equals("COLOR")) {
                    final String if11 = if(array, array2);
                    if (if11 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.for = a(if11, e2.for);
                }
                else if (a.equals("SHADOWCOLOR")) {
                    final String if12 = if(array, array2);
                    if (if12 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.byte = a(if12, e2.byte);
                }
                else if (a.equals("COMMENT")) {
                    final String if13 = if(array, array2);
                    if (if13 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    e2.if = if13;
                }
                else {
                    if (!a.equals("STATUS")) {
                        array[0] = null;
                        return vector3;
                    }
                    final String if14 = if(array, array2);
                    if (if14 == null) {
                        array[0] = null;
                        return vector3;
                    }
                    final String replace = if14.replace('i', 'I');
                    if (replace.toUpperCase().equals("OPEN")) {
                        e2.goto = 10;
                    }
                    else if (replace.toUpperCase().equals("FIXOPEN")) {
                        e2.goto = 20;
                    }
                    else {
                        e2.goto = 30;
                    }
                }
            }
        }
    }
    
    private static Vector a(final String[] array, final int[] array2, final URL url, final boolean b) {
        final Vector<g> vector = new Vector<g>();
        g g = new g();
        byte(array, array2);
        if (array[0] == null || array[0].length() <= array2[0] || array[0].charAt(array2[0]) != '{') {
            return vector;
        }
        final int n = 0;
        ++array2[n];
        while (true) {
            byte(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return vector;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == ';') {
                final int n2 = 0;
                ++array2[n2];
            }
            else if (char1 == '|') {
                vector.addElement(g);
                g = new g();
                final int n3 = 0;
                ++array2[n3];
            }
            else {
                if (char1 == '}') {
                    vector.addElement(g);
                    final int n4 = 0;
                    ++array2[n4];
                    return vector;
                }
                final String a = a(array, array2);
                if (a == null) {
                    array[0] = null;
                    return vector;
                }
                if (a.equals("NAME")) {
                    final String if1 = if(array, array2);
                    if (if1 == null) {
                        array[0] = null;
                        return vector;
                    }
                    g.a = if1.toUpperCase();
                }
                else {
                    if (!a.equals("FILE") && !a.equals("IMAGE")) {
                        array[0] = null;
                        return vector;
                    }
                    final String if2 = if(array, array2);
                    if (if2 == null) {
                        array[0] = null;
                        return vector;
                    }
                    g.do = if2;
                }
            }
        }
    }
    
    private static Vector try(final String[] array, final int[] array2) {
        final Vector<a> vector = new Vector<a>();
        a a = new a();
        byte(array, array2);
        if (array[0] == null || array[0].length() <= array2[0] || array[0].charAt(array2[0]) != '{') {
            return vector;
        }
        final int n = 0;
        ++array2[n];
        while (true) {
            byte(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return vector;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == ';') {
                final int n2 = 0;
                ++array2[n2];
            }
            else if (char1 == '|') {
                a.do = new Font(a.a, a.int, a.case);
                vector.addElement(a);
                a = new a();
                final int n3 = 0;
                ++array2[n3];
            }
            else {
                if (char1 == '}') {
                    a.do = new Font(a.a, a.int, a.case);
                    vector.addElement(a);
                    final int n4 = 0;
                    ++array2[n4];
                    return vector;
                }
                final String a2 = a(array, array2);
                if (a2 == null) {
                    array[0] = null;
                    return vector;
                }
                if (a2.equals("NAME")) {
                    final String if1 = if(array, array2);
                    if (if1 == null) {
                        array[0] = null;
                        return vector;
                    }
                    a.if = if1.toUpperCase();
                }
                else if (a2.equals("FONT")) {
                    final String if2 = if(array, array2);
                    if (if2 == null) {
                        array[0] = null;
                        return vector;
                    }
                    a.a = if2;
                }
                else if (a2.equals("STYLE")) {
                    final int for1 = for(array, array2);
                    if (for1 < 0) {
                        array[0] = null;
                        return vector;
                    }
                    a.int = for1;
                }
                else if (a2.equals("SIZE")) {
                    final String if3 = if(array, array2);
                    if (if3 == null) {
                        array[0] = null;
                        return vector;
                    }
                    a.case = a(if3, a.case);
                }
                else if (a2.equals("SHIFTX")) {
                    final String if4 = if(array, array2);
                    if (if4 == null) {
                        array[0] = null;
                        return vector;
                    }
                    a.try = a(if4, a.try);
                }
                else if (a2.equals("SHIFTY")) {
                    final String if5 = if(array, array2);
                    if (if5 == null) {
                        array[0] = null;
                        return vector;
                    }
                    a.new = a(if5, a.new);
                }
                else if (a2.equals("SHADOW")) {
                    final String if6 = if(array, array2);
                    if (if6 == null) {
                        array[0] = null;
                        return vector;
                    }
                    a.byte = a(if6, a.byte);
                }
                else {
                    if (!a2.equals("PRESSDOWN")) {
                        array[0] = null;
                        return vector;
                    }
                    final String if7 = if(array, array2);
                    if (if7 == null) {
                        array[0] = null;
                        return vector;
                    }
                    a.for = a(if7, a.for);
                }
            }
        }
    }
    
    private static Vector do(final String[] array, final int[] array2) {
        final Vector<String> vector = new Vector<String>();
        while (true) {
            final String if1 = if(array, array2);
            if (if1 == null) {
                return null;
            }
            vector.addElement(if1);
            byte(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return null;
            }
            if (array[0].charAt(array2[0]) != '+') {
                return vector;
            }
            final int n = 0;
            ++array2[n];
        }
    }
    
    private static int for(final String[] array, final int[] array2) {
        int n = 0;
        while (true) {
            final String if1 = if(array, array2);
            if (if1 == null) {
                return -1;
            }
            final String replace = if1.replace('i', 'I');
            if (replace.toUpperCase().equals("PLAIN")) {
                n += 0;
            }
            else if (replace.toUpperCase().equals("BOLD")) {
                ++n;
            }
            else {
                if (!replace.toUpperCase().equals("ITALIC")) {
                    return -1;
                }
                n += 2;
            }
            byte(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return -1;
            }
            if (array[0].charAt(array2[0]) != '+') {
                return n;
            }
            final int n2 = 0;
            ++array2[n2];
        }
    }
    
    private static void a(final a a) {
        a.do = new Font(a.a, a.int, a.case);
    }
    
    private static boolean a(final char c) {
        return c == ' ' || c == '\b' || c == '\t' || c == '\n' || c == '\f' || c == '\r';
    }
    
    private static void new(final String[] array, final int[] array2) {
        if (array != null && array[0] != null) {
            for (int length = array[0].length(), i = array2[0]; i < length; ++i) {
                if (!a(array[0].charAt(i))) {
                    return;
                }
                final int n = 0;
                ++array2[n];
            }
        }
    }
    
    private static boolean int(final String[] array, final int[] array2) {
        return array != null && array[0] != null && array[0].length() > array2[0] + 1 && array[0].length() > 1 && array[0].charAt(array2[0]) == '/' && array[0].charAt(array2[0] + 1) == '/';
    }
    
    private static boolean case(final String[] array, final int[] array2) {
        if (int(array, array2)) {
            final int index = array[0].indexOf(10, array2[0]);
            if (index > 0) {
                array2[0] = index + 1;
                new(array, array2);
            }
            else {
                array[0] = "";
            }
            return true;
        }
        return false;
    }
    
    private static void byte(final String[] array, final int[] array2) {
        for (boolean case1 = true; case1; case1 = case(array, array2)) {
            new(array, array2);
        }
    }
    
    private static String a(final String[] array, final int[] array2) {
        String substring = new String();
        boolean b = true;
        byte(array, array2);
        final int n = array2[0];
        while (b) {
            if (array[0].length() <= array2[0]) {
                return "";
            }
            final char char1 = array[0].charAt(array2[0]);
            if (a(char1) || char1 == '=') {
                b = false;
            }
            else {
                final int n2 = 0;
                ++array2[n2];
            }
        }
        if (n < array2[0]) {
            substring = array[0].substring(n, array2[0]);
        }
        byte(array, array2);
        if (array[0].length() <= array2[0]) {
            return "";
        }
        if (array[0].charAt(array2[0]) != '=') {
            return "";
        }
        final int n3 = 0;
        ++array2[n3];
        byte(array, array2);
        if (array[0].length() <= array2[0]) {
            return "";
        }
        final char char2 = array[0].charAt(array2[0]);
        if (char2 != '{' && char2 != '\'') {
            return "";
        }
        return substring.replace('i', 'I').toUpperCase();
    }
    
    private static String if(final String[] array, final int[] array2) {
        byte(array, array2);
        final int length = array[0].length();
        if (length > array2[0] + 1 && array[0].charAt(array2[0]) == '\'') {
            final int index = array[0].indexOf(39, array2[0] + 1);
            if (index > 0 && index + 1 < length) {
                final String substring = array[0].substring(array2[0] + 1, index);
                array2[0] = index + 1;
                return substring;
            }
        }
        return null;
    }
    
    private static a a(final Vector vector, String upperCase) {
        if (vector != null && upperCase != null) {
            upperCase = upperCase.toUpperCase();
            for (int size = vector.size(), i = 0; i < size; ++i) {
                final a a = vector.elementAt(i);
                if (a.if.equals(upperCase)) {
                    return a;
                }
            }
        }
        return null;
    }
    
    private static void a(final e e, final Vector vector, final String s, final String s2, final String s3, final Image c, final Image new1, final Image e2) {
        if (s == null) {
            e.c = c;
        }
        else {
            e.c = if(vector, s);
        }
        if (s2 == null) {
            e.new = new1;
        }
        else {
            e.new = if(vector, s2);
        }
        if (s3 == null) {
            e.e = e2;
            return;
        }
        e.e = if(vector, s3);
    }
    
    private static Image if(final Vector vector, String upperCase) {
        if (vector != null && upperCase != null) {
            upperCase = upperCase.toUpperCase();
            for (int size = vector.size(), i = 0; i < size; ++i) {
                final g g = vector.elementAt(i);
                if (upperCase.equals(g.a)) {
                    return g.if;
                }
            }
        }
        return null;
    }
}
