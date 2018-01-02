import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.applet.Applet;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.util.Vector;
import java.io.FileReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class h
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
    
    public static String a(final String s, String string) {
        if (string == null) {
            return null;
        }
        StringBuffer sb;
        try {
            if (s != null) {
                string = String.valueOf(s) + string;
            }
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(string));
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
    
    public static t a(final String[] array) {
        final t t = new t();
        final Vector<s> vector = new Vector<s>();
        final int[] array2 = { 0 };
        int else1 = -1;
        array2[0] = 0;
        if (array == null || array[0] == null) {
            return null;
        }
        while (true) {
            try(array, array2);
            if (array[0] == null) {
                return null;
            }
            if (array[0].length() <= array2[0]) {
                t.for = if(vector);
                return t;
            }
            if (array[0].charAt(array2[0]) == ';') {
                final int[] array3 = array2;
                final int n = 0;
                ++array3[n];
            }
            else {
                final String new1 = new(array, array2);
                if (new1.equals("LAYER")) {
                    final s s = new s();
                    final boolean a = a(array, array2, s);
                    if (s.new == null) {
                        array[0] = null;
                        t.for = if(vector);
                        return t;
                    }
                    ++else1;
                    if (a) {
                        t.else = else1;
                    }
                    vector.addElement(s);
                }
                else if (new1.equals("BGCOLOR")) {
                    t.b = a(a(array, array2, '='), t.b);
                }
                else if (new1.equals("DRAGMODE")) {
                    final String a2 = a(array, array2, '=');
                    if (a2 != null) {
                        t.try = a(a2, t.try);
                    }
                    else {
                        t.try = true;
                    }
                }
                else if (new1.equals("SCROLLBARACTIVE")) {
                    final String a3 = a(array, array2, '=');
                    if (a3 != null) {
                        t.goto = a(a3, t.goto);
                    }
                    else {
                        t.goto = true;
                    }
                }
                else if (new1.equals("SCROLLBARPOSITION")) {
                    final String a4 = a(array, array2, '=');
                    if (a4 != null) {
                        t.byte = a4.replace('i', 'I').toUpperCase();
                    }
                    else {
                        t.byte = null;
                    }
                }
                else if (new1.equals("SCROLLBARWIDTH")) {
                    t.null = a(a(array, array2, '='), t.null);
                }
                else if (new1.equals("SCROLLBARLEFTMARGIN")) {
                    t.long = a(a(array, array2, '='), t.long);
                }
                else if (new1.equals("SCROLLBARRIGHTMARGIN")) {
                    t.void = a(a(array, array2, '='), t.void);
                }
                else if (new1.equals("SCROLLBARMARGINCOLOR")) {
                    t.a = a(a(array, array2, '='), t.a);
                }
                else if (new1.equals("SCROLLBARBGCOLOR")) {
                    t.int = a(a(array, array2, '='), t.int);
                }
                else if (new1.equals("SCROLLBARBUTTONCOLOR")) {
                    t.do = a(a(array, array2, '='), t.do);
                }
                else if (new1.equals("SCROLLBARSLIDERCOLOR")) {
                    t.new = a(a(array, array2, '='), t.new);
                }
                else if (new1.equals("SCROLLBARLINECOLOR")) {
                    t.if = a(a(array, array2, '='), t.if);
                }
                else if (new1.equals("SCROLLBARARROWCOLOR")) {
                    t.char = a(a(array, array2, '='), t.char);
                }
                else {
                    if (!new1.equals("SCROLLBARACTIVEARROWCOLOR")) {
                        array[0] = null;
                        t.for = if(vector);
                        return t;
                    }
                    t.case = a(a(array, array2, '='), t.case);
                }
            }
        }
    }
    
    private static boolean a(final String[] array, final int[] array2, final s s) {
        boolean b = false;
        if (!for(array, array2)) {
            array[0] = null;
            return false;
        }
        while (true) {
            try(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return false;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == ';') {
                final int n = 0;
                ++array2[n];
            }
            else {
                if (char1 == '}') {
                    final int n2 = 0;
                    ++array2[n2];
                    return b;
                }
                final String new1 = new(array, array2);
                if (new1 == null) {
                    array[0] = null;
                    return false;
                }
                if (new1.equals("USERCONTROL")) {
                    b = true;
                }
                else if (new1.equals("NAME")) {
                    final String a = a(array, array2, '=');
                    if (a == null) {
                        array[0] = null;
                        return false;
                    }
                    s.new = a.replace('i', 'I').toUpperCase();
                }
                else {
                    if (!new1.equals("FILE")) {
                        array[0] = null;
                        return false;
                    }
                    final String a2 = a(array, array2, '=');
                    if (a2 == null) {
                        array[0] = null;
                        return false;
                    }
                    s.void = a2;
                }
            }
        }
    }
    
    public static void a(final URL url, final String[] array, final s s, final Component component) {
        final Vector<k> vector = new Vector<k>();
        final r goto1 = new r();
        final Vector b = new Vector();
        final Vector<g> vector2 = new Vector<g>();
        final Vector vector3 = new Vector();
        final g g = new g();
        final int[] array2 = { 0 };
        s.goto = goto1;
        s.b = b;
        if (array == null) {
            if (array != null) {
                array[0] = null;
            }
            return;
        }
        g.for = new Font(g.if, g.a, g.do);
        vector2.addElement(g);
        while (true) {
            try(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                s.long = int(vector);
                return;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == '{') {
                final int[] array3 = array2;
                final int n = 0;
                ++array3[n];
                final k k = new k();
                k.a = new f();
                vector.addElement(k);
                a(url, k, vector2, b, vector3, array, array2);
            }
            else if (char1 == ';') {
                final int[] array4 = array2;
                final int n2 = 0;
                ++array4[n2];
            }
            else {
                final String new1 = new(array, array2);
                if (new1.equals("SB") || new1.equals("SPACEBLOCK")) {
                    final k i = new k();
                    final u char2 = new u();
                    i.char = char2;
                    final String a = a(array, array2, '=');
                    if (a == null) {
                        array[0] = null;
                        s.long = int(vector);
                        return;
                    }
                    vector.addElement(i);
                    char2.L = 400;
                    final String upperCase = a.replace('i', 'I').toUpperCase();
                    if (upperCase.equals("TOP")) {
                        char2.byte = 3000;
                    }
                    else if (upperCase.equals("BOTTOM")) {
                        char2.byte = 4000;
                    }
                    else if (upperCase.equals("FIXBOTTOM")) {
                        char2.byte = 5000;
                    }
                    else if (upperCase.equals("CENTER")) {
                        char2.byte = 1000;
                    }
                    else if (upperCase.equals("FIXCENTER")) {
                        char2.byte = 2000;
                    }
                    else if (upperCase.equals("FULLSCREEN")) {
                        char2.byte = 6000;
                    }
                    else if (upperCase.equals("CLEARSCREEN")) {
                        char2.byte = 7000;
                    }
                    else {
                        char2.F = a(upperCase, 0);
                    }
                }
                else if (new1.equals("LABEL")) {
                    final k j = new k();
                    final u char3 = new u();
                    j.char = char3;
                    final String a2 = a(array, array2, '=');
                    if (a2 == null) {
                        array[0] = null;
                        s.long = int(vector);
                        return;
                    }
                    char3.L = 10;
                    char3.goto = a2.replace('i', 'I').toUpperCase();
                    vector.addElement(j);
                }
                else if (new1.equals("COMMANDS")) {
                    final u a3 = a(array, array2, vector3);
                    if (a3 == null) {
                        continue;
                    }
                    final k l = new k();
                    l.char = a3;
                    vector.addElement(l);
                }
                else if (new1.equals("INIT")) {
                    goto1.try = a(array, array2, vector3);
                }
                else if (new1.equals("MOUSEENTER")) {
                    goto1.a = a(array, array2, vector3);
                }
                else if (new1.equals("MOUSEEXIT")) {
                    goto1.c = a(array, array2, vector3);
                }
                else if (new1.equals("MOUSEONLINK")) {
                    goto1.byte = a(array, array2, vector3);
                }
                else if (new1.equals("MOUSEOFFLINK")) {
                    goto1.if = a(array, array2, vector3);
                }
                else if (new1.equals("MOUSEPRESSED")) {
                    goto1.case = a(array, array2, vector3);
                }
                else if (new1.equals("MOUSERELEASED")) {
                    goto1.void = a(array, array2, vector3);
                }
                else if (new1.equals("RELOADREQUEST")) {
                    goto1.goto = a(array, array2, vector3);
                }
                else if (new1.equals("RELOADINDICATION")) {
                    goto1.for = a(array, array2, vector3);
                }
                else if (new1.equals("SHOWLAYER")) {
                    goto1.int = a(array, array2, vector3);
                }
                else if (new1.equals("HIDELAYER")) {
                    goto1.null = a(array, array2, vector3);
                }
                else if (new1.equals("LAYERRESIZED")) {
                    goto1.new = a(array, array2, vector3);
                }
                else if (new1.equals("FONTS")) {
                    a(vector2, array, array2);
                }
                else if (new1.equals("IMAGES")) {
                    a(b, array, array2, component);
                }
                else if (new1.equals("MACROS")) {
                    if(vector3, array, array2);
                    s.else = for(vector3);
                }
                else if (new1.equals("SCROLLINGMODE")) {
                    final String a4 = a(array, array2, '=');
                    if (a4 == null || !a4.trim().toUpperCase().equals("NORMAL")) {
                        continue;
                    }
                    s.byte = 10;
                }
                else if (new1.equals("BASEWIDTH")) {
                    final String a5 = a(array, array2, '=');
                    if (a5 == null) {
                        continue;
                    }
                    final int a6 = a(a5, s.int);
                    if (a6 <= 0) {
                        continue;
                    }
                    s.int = a6;
                }
                else if (new1.equals("RELOADINTERVAL")) {
                    final String a7 = a(array, array2, '=');
                    if (a7 == null) {
                        continue;
                    }
                    s.do = a(a7, s.do);
                }
                else {
                    if (!new1.equals("RELOADFILE")) {
                        array[0] = null;
                        s.long = int(vector);
                        return;
                    }
                    s.try = a(array, array2, '=');
                }
            }
        }
    }
    
    public static boolean if(final String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
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
    
    public static s[] if(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            return null;
        }
        final s[] array = new s[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public static k[] int(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            return null;
        }
        final k[] array = new k[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public static u[] do(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            return null;
        }
        final u[] array = new u[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public static v[] a(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            return null;
        }
        final v[] array = new v[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public static m[] new(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            return null;
        }
        final m[] array = new m[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public static u[] for(final Vector vector) {
        if (vector == null || vector.size() == 0) {
            return null;
        }
        final u[] array = new u[vector.size()];
        for (int i = 0; i < vector.size(); ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    private static Image if(final Vector vector, final String s) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                final d d = vector.elementAt(i);
                if (d.if.equals(s)) {
                    return d.a;
                }
            }
            return null;
        }
        return null;
    }
    
    private static void a(final d d, final Component component) {
        final MediaTracker mediaTracker = new MediaTracker(component);
        Applet applet = null;
        if (component == null) {
            return;
        }
        if (component instanceof Applet) {
            applet = (Applet)component;
        }
        if (d.do != null && d.do.length() > 0) {
            if (applet != null) {
                mediaTracker.addImage(d.a = applet.getImage(applet.getCodeBase(), d.do), 0);
            }
            else {
                mediaTracker.addImage(d.a = Toolkit.getDefaultToolkit().getImage(d.do), 0);
            }
            component.prepareImage(d.a, component);
            try {
                mediaTracker.waitForID(0);
            }
            catch (Exception ex) {
                System.out.println("Error loading image");
            }
            mediaTracker.removeImage(d.a);
        }
    }
    
    private static void a(final Vector vector, final String[] array, final int[] array2, final Component component) {
        if (!for(array, array2)) {
            array[0] = null;
            return;
        }
        while (true) {
            try(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == '|' || char1 == ';') {
                final int n = 0;
                ++array2[n];
            }
            else {
                if (char1 == '}') {
                    final int n2 = 0;
                    ++array2[n2];
                    return;
                }
                final String new1 = new(array, array2);
                if (new1 == null || new1.length() <= 0) {
                    array[0] = null;
                    return;
                }
                final d d = new d();
                d.if = new1.replace('i', 'I').toUpperCase();
                final String a = a(array, array2, '=');
                if (a == null) {
                    array[0] = null;
                    return;
                }
                d.do = a;
                a(d, component);
                vector.addElement(d);
            }
        }
    }
    
    private static void if(final Vector vector, final String[] array, final int[] array2) {
        if (!for(array, array2)) {
            array[0] = null;
            return;
        }
        while (true) {
            try(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == ';' || char1 == '|') {
                final int n = 0;
                ++array2[n];
            }
            else {
                if (char1 == '}') {
                    final int n2 = 0;
                    ++array2[n2];
                    return;
                }
                final String new1 = new(array, array2);
                if (new1 == null || new1.length() <= 0) {
                    array[0] = null;
                    return;
                }
                final u u = new u();
                u.goto = new1.replace('i', 'I').toUpperCase();
                final u a = a(array, array2, vector);
                if (a != null) {
                    u.q = a.q;
                    u.a = a.a;
                }
                vector.addElement(u);
            }
        }
    }
    
    private static Font a(final Vector vector, final String s) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                final g g = vector.elementAt(i);
                if (g.int.equals(s)) {
                    return g.for;
                }
            }
            return vector.elementAt(0).for;
        }
        return null;
    }
    
    private static void a(final g g) {
        g.for = new Font(g.if, g.a, g.do);
    }
    
    private static int a(final String[] array, final int[] array2) {
        int n = 0;
        char c = '=';
        while (true) {
            final String a = a(array, array2, c);
            if (a == null) {
                return -1;
            }
            final String upperCase = a.replace('i', 'I').toUpperCase();
            if (upperCase.equals("PLAIN")) {
                n += 0;
            }
            else if (upperCase.equals("BOLD")) {
                ++n;
            }
            else {
                if (!upperCase.equals("ITALIC")) {
                    return -1;
                }
                n += 2;
            }
            try(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return -1;
            }
            if (array[0].charAt(array2[0]) != '+') {
                return n;
            }
            c = '+';
        }
    }
    
    private static void a(final Vector vector, final String[] array, final int[] array2) {
        g g = new g();
        if (!for(array, array2)) {
            array[0] = null;
            return;
        }
        while (true) {
            try(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == ';') {
                final int n = 0;
                ++array2[n];
            }
            else if (char1 == '|') {
                final int n2 = 0;
                ++array2[n2];
                g.for = new Font(g.if, g.a, g.do);
                vector.addElement(g);
                g = new g();
            }
            else {
                if (char1 == '}') {
                    final int n3 = 0;
                    ++array2[n3];
                    g.for = new Font(g.if, g.a, g.do);
                    vector.addElement(g);
                    return;
                }
                final String new1 = new(array, array2);
                if (new1 == null) {
                    array[0] = null;
                    return;
                }
                if (new1.equals("NAME")) {
                    final String a = a(array, array2, '=');
                    if (a == null) {
                        array[0] = null;
                        return;
                    }
                    g.int = a.replace('i', 'I').toUpperCase();
                }
                else if (new1.equals("FONT")) {
                    final String a2 = a(array, array2, '=');
                    if (a2 == null) {
                        array[0] = null;
                        return;
                    }
                    g.if = a2;
                }
                else if (new1.equals("STYLE")) {
                    final int a3 = a(array, array2);
                    if (a3 < 0) {
                        array[0] = null;
                        return;
                    }
                    g.a = a3;
                }
                else {
                    if (!new1.equals("SIZE")) {
                        array[0] = null;
                        return;
                    }
                    final String a4 = a(array, array2, '=');
                    if (a4 == null) {
                        array[0] = null;
                        return;
                    }
                    g.do = a(a4, g.do);
                }
            }
        }
    }
    
    private static boolean a(final char c) {
        return c == ' ' || c == '\b' || c == '\t' || c == '\n' || c == '\f' || c == '\r';
    }
    
    private static void byte(final String[] array, final int[] array2) {
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
    
    private static boolean do(final String[] array, final int[] array2) {
        return array != null && array[0] != null && array[0].length() > array2[0] + 1 && array[0].length() > 1 && array[0].charAt(array2[0]) == '/' && array[0].charAt(array2[0] + 1) == '/';
    }
    
    private static boolean if(final String[] array, final int[] array2) {
        if (do(array, array2)) {
            final int index = array[0].indexOf(10, array2[0]);
            if (index > 0) {
                array2[0] = index + 1;
                byte(array, array2);
            }
            else {
                array[0] = "";
            }
            return true;
        }
        return false;
    }
    
    private static void try(final String[] array, final int[] array2) {
        for (boolean if1 = true; if1; if1 = if(array, array2)) {
            byte(array, array2);
        }
    }
    
    public static u a(final String[] array, final int[] array2, final Vector vector) {
        final u u = new u();
        final Vector<v> vector2 = new Vector<v>();
        int try1 = -100;
        String s = "";
        if (!for(array, array2)) {
            final String a = a(array, array2, ' ');
            if (a != null) {
                u.a = a.replace('i', 'I').toUpperCase();
            }
            return u;
        }
        while (true) {
            try(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return null;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == ';') {
                final int n = 0;
                ++array2[n];
            }
            else {
                if (char1 == '}') {
                    final int n2 = 0;
                    ++array2[n2];
                    u.q = a(vector2);
                    return u;
                }
                final String new1 = new(array, array2);
                if (new1 == null) {
                    array[0] = null;
                    return null;
                }
                if (new1.equals("LAYER")) {
                    final String a2 = a(array, array2, '=');
                    if (a2 == null) {
                        continue;
                    }
                    final String upperCase = a2.replace('i', 'I').toUpperCase();
                    if (upperCase.equals("SELF") || upperCase.equals("_SELF")) {
                        s = "";
                        try1 = -100;
                    }
                    else if (upperCase.equals("ALL")) {
                        s = "";
                        try1 = -200;
                    }
                    else {
                        s = upperCase;
                        try1 = 1000;
                    }
                }
                else if (new1.equals("NAME")) {
                    final String a3 = a(array, array2, '=');
                    if (a3 != null) {
                        u.goto = a3.replace('i', 'I').toUpperCase();
                    }
                    else {
                        u.goto = null;
                    }
                }
                else if (new1.equals("TOP")) {
                    u.n = 3000;
                }
                else if (new1.equals("BOTTOM")) {
                    u.n = 4000;
                }
                else if (new1.equals("PAUSE")) {
                    final v v = new v();
                    final String a4 = a(array, array2, '=');
                    if (a4 == null) {
                        v.do = 200;
                    }
                    else {
                        v.do = 100;
                        v.for = a(a4, -1);
                        if (v.for < 0) {
                            array[0] = null;
                            return null;
                        }
                    }
                    v.try = try1;
                    v.a = s;
                    vector2.addElement(v);
                }
                else if (new1.equals("SPEED")) {
                    final String a5 = a(array, array2, '=');
                    final v v2 = new v();
                    v2.do = 600;
                    v2.for = a(a5, -1);
                    v2.try = try1;
                    v2.a = s;
                    if (v2.for < 0) {
                        array[0] = null;
                        return null;
                    }
                    vector2.addElement(v2);
                }
                else if (new1.equals("DIRECTION")) {
                    final String a6 = a(array, array2, '=');
                    if (a6 == null) {
                        continue;
                    }
                    final v v3 = new v();
                    v3.do = 500;
                    final String upperCase2 = a6.replace('i', 'I').toUpperCase();
                    if (upperCase2.equals("BACK")) {
                        v3.for = 60;
                    }
                    else {
                        if (!upperCase2.equals("FORWARD")) {
                            array[0] = null;
                            return null;
                        }
                        v3.for = 50;
                    }
                    v3.try = try1;
                    v3.a = s;
                    vector2.addElement(v3);
                }
                else if (new1.equals("JUMP")) {
                    String byte1 = a(array, array2, '=');
                    if (byte1 != null) {
                        byte1 = byte1.replace('i', 'I').toUpperCase();
                    }
                    final v v4 = new v();
                    v4.do = 20;
                    v4.byte = byte1;
                    v4.try = try1;
                    v4.a = s;
                    vector2.addElement(v4);
                }
                else if (new1.equals("START")) {
                    final String a7 = a(array, array2, '=');
                    final v v5 = new v();
                    v5.do = 700;
                    v5.try = try1;
                    v5.a = s;
                    if (a7 == null) {
                        v5.for = 0;
                    }
                    else {
                        v5.for = a(a7, -1);
                    }
                    if (v5.for < 0) {
                        array[0] = null;
                        return null;
                    }
                    vector2.addElement(v5);
                }
                else if (new1.equals("CONTINUE")) {
                    final v v6 = new v();
                    v6.do = 300;
                    v6.try = try1;
                    v6.a = s;
                    vector2.addElement(v6);
                }
                else if (new1.equals("STOP")) {
                    final String a8 = a(array, array2, '=');
                    final v v7 = new v();
                    v7.do = 800;
                    v7.try = try1;
                    v7.a = s;
                    if (a8 == null) {
                        v7.for = 0;
                    }
                    else {
                        v7.for = a(a8, -1);
                    }
                    if (v7.for < 0) {
                        array[0] = null;
                        return null;
                    }
                    vector2.addElement(v7);
                }
                else if (new1.equals("LOWERSTOPPRIORITY")) {
                    final String a9 = a(array, array2, '=');
                    final v v8 = new v();
                    v8.do = 900;
                    v8.try = try1;
                    v8.a = s;
                    if (a9 == null) {
                        v8.for = 0;
                    }
                    else {
                        v8.for = a(a9, -1);
                    }
                    if (v8.for < 0) {
                        array[0] = null;
                        return null;
                    }
                    vector2.addElement(v8);
                }
                else if (new1.equals("LOADPROJECT")) {
                    final String a10 = a(array, array2, '=');
                    final v v9 = new v();
                    v9.do = 2500;
                    v9.try = -200;
                    v9.byte = a10;
                    vector2.addElement(v9);
                }
                else if (new1.equals("RELOAD")) {
                    final v v10 = new v();
                    v10.do = 1000;
                    v10.try = try1;
                    v10.a = s;
                    vector2.addElement(v10);
                }
                else if (new1.equals("LOAD")) {
                    final String a11 = a(array, array2, '=');
                    final v v11 = new v();
                    v11.do = 1100;
                    v11.try = try1;
                    v11.a = s;
                    v11.byte = a11;
                    vector2.addElement(v11);
                }
                else if (new1.equals("HIDE")) {
                    final v v12 = new v();
                    v12.do = 1200;
                    v12.try = try1;
                    v12.a = s;
                    vector2.addElement(v12);
                }
                else if (new1.equals("SHOW")) {
                    final v v13 = new v();
                    v13.do = 1300;
                    v13.try = try1;
                    v13.a = s;
                    vector2.addElement(v13);
                }
                else if (new1.equals("HIDEINCREMENT")) {
                    final v v14 = new v();
                    v14.do = 1400;
                    v14.try = try1;
                    v14.a = s;
                    vector2.addElement(v14);
                }
                else if (new1.equals("SHOWINCREMENT")) {
                    final v v15 = new v();
                    v15.do = 1500;
                    v15.try = try1;
                    v15.a = s;
                    vector2.addElement(v15);
                }
                else if (new1.equals("SWITCHSHOWHIDE")) {
                    final v v16 = new v();
                    v16.do = 1600;
                    v16.try = try1;
                    v16.a = s;
                    vector2.addElement(v16);
                }
                else if (new1.equals("SETUSERCONTROL")) {
                    final v v17 = new v();
                    v17.do = 1700;
                    v17.try = 1000;
                    v17.a = s;
                    vector2.addElement(v17);
                }
                else if (new1.equals("SCROLLBAR")) {
                    final String a12 = a(array, array2, '=');
                    final v v18 = new v();
                    v18.do = 5100;
                    v18.try = -200;
                    if (a12 == null) {
                        v18.for = 1;
                    }
                    else {
                        final String upperCase3 = a12.replace('i', 'I').toUpperCase();
                        if (upperCase3.equals("HIDE")) {
                            v18.for = 300;
                        }
                        else if (upperCase3.equals("LEFT")) {
                            v18.for = 100;
                        }
                        else if (upperCase3.equals("RIGHT")) {
                            v18.for = 200;
                        }
                        else if (a(upperCase3, true)) {
                            v18.for = 1;
                        }
                        else {
                            v18.for = 0;
                        }
                    }
                    vector2.addElement(v18);
                }
                else if (new1.equals("DRAGMODE")) {
                    final String a13 = a(array, array2, '=');
                    final v v19 = new v();
                    v19.do = 5000;
                    v19.try = -200;
                    if (a13 == null) {
                        v19.for = 1;
                    }
                    else if (a(a13, true)) {
                        v19.for = 1;
                    }
                    else {
                        v19.for = 0;
                    }
                    vector2.addElement(v19);
                }
                else if (new1.equals("SET")) {
                    a(array, array2, 3000, null, -100, null, vector2, vector);
                }
                else if (new1.equals("INIT")) {
                    a(array, array2, 3200, null, try1, s, vector2, vector);
                }
                else if (new1.equals("MOUSEENTER")) {
                    a(array, array2, 3300, null, try1, s, vector2, vector);
                }
                else if (new1.equals("MOUSEEXIT")) {
                    a(array, array2, 3400, null, try1, s, vector2, vector);
                }
                else if (new1.equals("MOUSEONLINK")) {
                    a(array, array2, 3500, null, try1, s, vector2, vector);
                }
                else if (new1.equals("MOUSEOFFLINK")) {
                    a(array, array2, 3600, null, try1, s, vector2, vector);
                }
                else if (new1.equals("MOUSEPRESSED")) {
                    a(array, array2, 3700, null, try1, s, vector2, vector);
                }
                else if (new1.equals("MOUSERELEASED")) {
                    a(array, array2, 3800, null, try1, s, vector2, vector);
                }
                else if (new1.equals("RELOADREQUEST")) {
                    a(array, array2, 3900, null, try1, s, vector2, vector);
                }
                else if (new1.equals("RELOADINDICATION")) {
                    a(array, array2, 4000, null, try1, s, vector2, vector);
                }
                else if (new1.equals("SHOWLAYER")) {
                    a(array, array2, 4100, null, try1, s, vector2, vector);
                }
                else if (new1.equals("HIDELAYER")) {
                    a(array, array2, 4200, null, try1, s, vector2, vector);
                }
                else if (new1.equals("LAYERRESIZED")) {
                    a(array, array2, 4300, null, try1, s, vector2, vector);
                }
                else if (new1.equals("SETBGCOLOR")) {
                    final String a14 = a(array, array2, '=');
                    final v v20 = new v();
                    v20.do = 1800;
                    v20.try = -200;
                    if (a14 == null) {
                        array[0] = null;
                        return null;
                    }
                    v20.byte = a14;
                    vector2.addElement(v20);
                }
                else {
                    a(array, array2, 3100, new1, try1, s, vector2, vector);
                }
            }
        }
    }
    
    private static void a(final String[] array, final int[] array2, final int do1, final String new1, final int try1, final String a, final Vector vector, final Vector vector2) {
        final u a2 = a(array, array2, vector2);
        final v v = new v();
        v.do = do1;
        v.new = new1;
        v.try = try1;
        v.a = a;
        if (a2 != null) {
            v.byte = a2.a;
            v.if = a2.q;
        }
        vector.addElement(v);
    }
    
    private static void a(final URL url, final k k, final Vector vector, final Vector vector2, final Vector vector3, final String[] array, final int[] array2) {
        final Vector<k> vector4 = new Vector<k>();
        final f a = k.a;
        if (a.goto == null && vector != null && vector.size() < 0) {
            a.goto = vector.elementAt(0).for;
        }
        while (true) {
            try(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                a.else = int(vector4);
                return;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == ';') {
                final int n = 0;
                ++array2[n];
            }
            else {
                if (char1 == '}') {
                    final int n2 = 0;
                    ++array2[n2];
                    a.else = int(vector4);
                    return;
                }
                if (char1 == '{') {
                    final int n3 = 0;
                    ++array2[n3];
                    final k i = new k();
                    i.a = new f();
                    vector4.addElement(i);
                    a(k, i);
                    a(url, i, vector, vector2, vector3, array, array2);
                }
                else {
                    final String new1 = new(array, array2);
                    if (new1 == null) {
                        a.else = int(vector4);
                        array[0] = null;
                        return;
                    }
                    if (new1.equals("TB")) {
                        final k j = new k();
                        j.if = new l();
                        a(url, j, k, vector, vector2, vector3, array, array2);
                        vector4.addElement(j);
                    }
                    else if (new1.equals("IB")) {
                        final k l = new k();
                        l.new = new b();
                        vector4.addElement(l);
                        a(l, k, vector2, vector3, array, array2);
                    }
                    else if (new1.equals("COMMANDS")) {
                        a.null = a(array, array2, vector3);
                    }
                    else if (new1.equals("FONT")) {
                        String s = a(array, array2, '=');
                        if (s != null) {
                            s = s.replace('i', 'I').toUpperCase();
                        }
                        a.goto = a(vector, s);
                        if (a.goto == null || s == null) {
                            a.else = int(vector4);
                            array[0] = null;
                            return;
                        }
                        continue;
                    }
                    else if (new1.equals("COLOR")) {
                        a.int = a(a(array, array2, '='), (Color)null);
                        if (a.int == null) {
                            a.else = int(vector4);
                            array[0] = null;
                            return;
                        }
                        continue;
                    }
                    else if (new1.equals("OFFMOUSECOLOR")) {
                        a.byte = a(a(array, array2, '='), (Color)null);
                        if (a.byte == null) {
                            a.else = int(vector4);
                            array[0] = null;
                            return;
                        }
                        continue;
                    }
                    else if (new1.equals("ONMOUSECOLOR")) {
                        a.a = a(a(array, array2, '='), (Color)null);
                        if (a.a == null) {
                            a.else = int(vector4);
                            array[0] = null;
                            return;
                        }
                        continue;
                    }
                    else if (new1.equals("TARGET")) {
                        a.void = a(array, array2, '=');
                    }
                    else if (new1.equals("URL")) {
                        a.try = a(array, array2, '=');
                        if (a.try.length() <= 4 || !a.try.substring(0, 4).toUpperCase().equals("WWW.")) {
                            continue;
                        }
                        a.try = "http://" + a.try;
                    }
                    else if (new1.equals("JSCALL")) {
                        a.for = a(array, array2, '=');
                        if (a.for == null || !a.for.equals("")) {
                            continue;
                        }
                        a.for = null;
                    }
                    else if (new1.equals("COLUMNS")) {
                        a.case = 100;
                    }
                    else if (new1.equals("ROWS")) {
                        a.case = 10;
                    }
                    else if (new1.equals("CENTER") || new1.equals("TOP") || new1.equals("BOTTOM") || new1.equals("RIGHT") || new1.equals("LEFT")) {
                        k.null = a(new1);
                    }
                    else if (new1.equals("BGMARGIN")) {
                        final int a2 = a(a(array, array2, '='), 0);
                        a.char = a2;
                        a.do = a2;
                        a.d = a2;
                        a.if = a2;
                    }
                    else if (new1.equals("BGTOPMARGIN")) {
                        a.char = a(a(array, array2, '='), 0);
                    }
                    else if (new1.equals("BGBOTTOMMARGIN")) {
                        a.do = a(a(array, array2, '='), 0);
                    }
                    else if (new1.equals("BGLEFTMARGIN")) {
                        a.d = a(a(array, array2, '='), 0);
                    }
                    else if (new1.equals("BGRIGHTMARGIN")) {
                        a.if = a(a(array, array2, '='), 0);
                    }
                    else if (new1.equals("BGCOLOR")) {
                        a.c = a(a(array, array2, '='), (Color)null);
                        if (a.c == null) {
                            a.else = int(vector4);
                            array[0] = null;
                            return;
                        }
                        continue;
                    }
                    else {
                        if (!new1.equals("BGIMAGE")) {
                            a.else = int(vector4);
                            array[0] = null;
                            return;
                        }
                        final String a3 = a(array, array2, '=');
                        if (a3 == null) {
                            a.else = int(vector4);
                            array[0] = null;
                            return;
                        }
                        a.b = if(vector2, a3.replace('i', 'I').toUpperCase());
                    }
                }
            }
        }
    }
    
    private static void a(final URL url, final k k, final k i, final Vector vector, final Vector vector2, final Vector vector3, final String[] array, final int[] array2) {
        final Vector<m> vector4 = new Vector<m>();
        final l if1 = k.if;
        final f a = i.a;
        Color color = a.int;
        Color int1 = a.byte;
        Color if2 = a.a;
        String goto1 = null;
        String new1 = null;
        String a2 = null;
        u a3 = null;
        boolean a4 = false;
        int a5 = 0;
        int a6 = 0;
        Font a7 = null;
        Font byte1 = a.goto;
        if (a.void == null || a.try == null) {
            goto1 = a.void;
            new1 = a.try;
        }
        if (!for(array, array2)) {
            array[0] = null;
            return;
        }
        if (byte1 == null) {
            byte1 = a(vector, "DEFAULT");
        }
        while (true) {
            try(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                if1.if = new(vector4);
                return;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == ';') {
                final int n = 0;
                ++array2[n];
            }
            else {
                if (char1 == '}') {
                    final int n2 = 0;
                    ++array2[n2];
                    if1.if = new(vector4);
                    return;
                }
                final String new2 = new(array, array2);
                if (new2 == null) {
                    if1.if = new(vector4);
                    array[0] = null;
                    return;
                }
                if (new2.equals("TEXT")) {
                    final String a8 = a(array, array2, '=');
                    if (a8 == null) {
                        if1.if = new(vector4);
                        array[0] = null;
                        return;
                    }
                    final m m = new m();
                    m.byte = byte1;
                    m.int = color;
                    m.if = null;
                    m.for = null;
                    m.new = null;
                    m.goto = null;
                    m.long = a4;
                    m.try = a5;
                    m.case = a6;
                    m.char = a7;
                    m.do = a8;
                    if (m.do.equals("")) {
                        continue;
                    }
                    vector4.addElement(m);
                }
                else if (new2.equals("FILE")) {
                    final String a9 = a(array, array2, '=');
                    if (a9 == null) {
                        continue;
                    }
                    String do1;
                    if (url != null) {
                        do1 = a(url, a9);
                    }
                    else {
                        do1 = a((String)null, a9);
                    }
                    if (do1 == null || do1.equals("")) {
                        continue;
                    }
                    final m j = new m();
                    j.byte = byte1;
                    j.int = color;
                    j.if = null;
                    j.for = null;
                    j.new = null;
                    j.goto = null;
                    j.long = a4;
                    j.try = a5;
                    j.case = a6;
                    j.char = a7;
                    j.do = do1;
                    vector4.addElement(j);
                }
                else if (new2.equals("ACTIVETEXT")) {
                    final String a10 = a(array, array2, '=');
                    if (a10 == null) {
                        if1.if = new(vector4);
                        array[0] = null;
                        return;
                    }
                    final m l = new m();
                    l.byte = byte1;
                    l.int = int1;
                    l.if = if2;
                    l.for = a2;
                    l.new = new1;
                    l.goto = goto1;
                    l.else = a3;
                    l.long = a4;
                    l.try = a5;
                    l.case = a6;
                    l.char = a7;
                    l.do = a10;
                    if (l.do.equals("")) {
                        continue;
                    }
                    vector4.addElement(l);
                }
                else if (new2.equals("FONT")) {
                    String s = a(array, array2, '=');
                    if (s != null) {
                        s = s.replace('i', 'I').toUpperCase();
                    }
                    byte1 = a(vector, s);
                    if (byte1 == null || s == null) {
                        if1.if = new(vector4);
                        array[0] = null;
                        return;
                    }
                    continue;
                }
                else if (new2.equals("COLOR")) {
                    color = a(a(array, array2, '='), (Color)null);
                    if (color == null) {
                        if1.if = new(vector4);
                        array[0] = null;
                        return;
                    }
                    continue;
                }
                else if (new2.equals("OFFMOUSECOLOR")) {
                    int1 = a(a(array, array2, '='), (Color)null);
                    if (int1 == null) {
                        if1.if = new(vector4);
                        array[0] = null;
                        return;
                    }
                    continue;
                }
                else if (new2.equals("ONMOUSECOLOR")) {
                    if2 = a(a(array, array2, '='), (Color)null);
                    if (if2 == null) {
                        if1.if = new(vector4);
                        array[0] = null;
                        return;
                    }
                    continue;
                }
                else if (new2.equals("UNDERLINE")) {
                    a4 = a(a(array, array2, '='), false);
                }
                else if (new2.equals("UP")) {
                    final String a11 = a(array, array2, '=');
                    if (a11 == null) {}
                    a5 = -a(a11, 0);
                }
                else if (new2.equals("DOWN")) {
                    final String a12 = a(array, array2, '=');
                    if (a12 == null) {}
                    a5 = a(a12, 0);
                }
                else if (new2.equals("LINEHEIGHT")) {
                    a6 = 0;
                    a7 = null;
                    final String a13 = a(array, array2, '=');
                    if (a13 == null) {
                        continue;
                    }
                    if (if(a13)) {
                        a6 = a(a13, 0);
                    }
                    else {
                        a7 = a(vector, a13.replace('i', 'I').toUpperCase());
                    }
                }
                else if (new2.equals("TARGET")) {
                    goto1 = a(array, array2, '=');
                }
                else if (new2.equals("URL")) {
                    new1 = a(array, array2, '=');
                    if (new1.length() <= 4 || !new1.substring(0, 4).toUpperCase().equals("WWW.")) {
                        continue;
                    }
                    new1 = "http://" + new1;
                }
                else if (new2.equals("JSCALL")) {
                    a2 = a(array, array2, '=');
                    if (a2 == null || !a2.equals("")) {
                        continue;
                    }
                    a2 = null;
                }
                else if (new2.equals("COMMANDS")) {
                    a3 = a(array, array2, vector3);
                }
                else if (new2.equals("CENTER") || new2.equals("TOP") || new2.equals("BOTTOM") || new2.equals("RIGHT") || new2.equals("LEFT")) {
                    k.null = a(new2);
                }
                else if (new2.equals("TEXTALIGNMENT")) {
                    final String a14 = a(array, array2, '=');
                    if (a14 == null) {
                        if1.if = new(vector4);
                        array[0] = null;
                        return;
                    }
                    if1.case = a(a14);
                }
                else if (new2.equals("WIDTH")) {
                    if1.a = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("MARGIN")) {
                    final int a15 = a(a(array, array2, '='), 0);
                    if1.new = a15;
                    if1.goto = a15;
                    if1.null = a15;
                    if1.c = a15;
                }
                else if (new2.equals("TOPMARGIN")) {
                    if1.new = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("BOTTOMMARGIN")) {
                    if1.goto = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("LEFTMARGIN")) {
                    if1.null = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("RIGHTMARGIN")) {
                    if1.c = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("BGMARGIN")) {
                    final int a16 = a(a(array, array2, '='), 0);
                    if1.byte = a16;
                    if1.int = a16;
                    if1.d = a16;
                    if1.for = a16;
                }
                else if (new2.equals("BGTOPMARGIN")) {
                    if1.byte = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("BGBOTTOMMARGIN")) {
                    if1.int = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("BGLEFTMARGIN")) {
                    if1.d = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("BGRIGHTMARGIN")) {
                    if1.for = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("BGCOLOR")) {
                    if1.b = a(a(array, array2, '='), (Color)null);
                    if (if1.b == null) {
                        if1.if = new(vector4);
                        array[0] = null;
                        return;
                    }
                    continue;
                }
                else {
                    if (!new2.equals("BGIMAGE")) {
                        if1.if = new(vector4);
                        array[0] = null;
                        return;
                    }
                    final String a17 = a(array, array2, '=');
                    if (a17 == null) {
                        if1.if = new(vector4);
                        array[0] = null;
                        return;
                    }
                    if1.void = if(vector2, a17.replace('i', 'I').toUpperCase());
                }
            }
        }
    }
    
    private static void a(final k k, final k i, final Vector vector, final Vector vector2, final String[] array, final int[] array2) {
        final b new1 = k.new;
        final f a = i.a;
        if (!for(array, array2)) {
            array[0] = null;
            return;
        }
        if (a.void == null || a.try == null) {
            new1.c = a.void;
            new1.try = a.try;
        }
        while (true) {
            try(array, array2);
            if (array[0] == null || array[0].length() <= array2[0]) {
                return;
            }
            final char char1 = array[0].charAt(array2[0]);
            if (char1 == ';') {
                final int n = 0;
                ++array2[n];
            }
            else {
                if (char1 == '}') {
                    final int n2 = 0;
                    ++array2[n2];
                    return;
                }
                final String new2 = new(array, array2);
                if (new2 == null) {
                    array[0] = null;
                    return;
                }
                if (new2.equals("IMAGE")) {
                    final String a2 = a(array, array2, '=');
                    if (a2 == null) {
                        array[0] = null;
                        return;
                    }
                    new1.do = if(vector, a2.replace('i', 'I').toUpperCase());
                }
                else if (new2.equals("ACTIVEIMAGE")) {
                    final String a3 = a(array, array2, '=');
                    if (a3 == null) {
                        array[0] = null;
                        return;
                    }
                    new1.d = if(vector, a3.replace('i', 'I').toUpperCase());
                }
                else if (new2.equals("TARGET")) {
                    new1.c = a(array, array2, '=');
                }
                else if (new2.equals("URL")) {
                    new1.try = a(array, array2, '=');
                    if (new1.try.length() <= 4 || !new1.try.substring(0, 4).toUpperCase().equals("WWW.")) {
                        continue;
                    }
                    new1.try = "http://" + new1.try;
                }
                else if (new2.equals("JSCALL")) {
                    new1.new = a(array, array2, '=');
                    if (new1.new == null || !new1.new.equals("")) {
                        continue;
                    }
                    new1.new = null;
                }
                else if (new2.equals("COMMANDS")) {
                    new1.else = a(array, array2, vector2);
                }
                else if (new2.equals("CENTER") || new2.equals("TOP") || new2.equals("BOTTOM") || new2.equals("RIGHT") || new2.equals("LEFT")) {
                    k.null = a(new2);
                }
                else if (new2.equals("IMAGEALIGNMENT")) {
                    final String a4 = a(array, array2, '=');
                    if (a4 == null) {
                        array[0] = null;
                        return;
                    }
                    new1.for = a(a4);
                }
                else if (new2.equals("WIDTH")) {
                    new1.a = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("MARGIN")) {
                    final int a5 = a(a(array, array2, '='), 0);
                    new1.int = a5;
                    new1.b = a5;
                    new1.f = a5;
                    new1.g = a5;
                }
                else if (new2.equals("TOPMARGIN")) {
                    new1.int = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("BOTTOMMARGIN")) {
                    new1.b = a(a(array, array2, '='), 0);
                }
                else if (new2.equals("LEFTMARGIN")) {
                    new1.f = a(a(array, array2, '='), 0);
                }
                else {
                    if (!new2.equals("RIGHTMARGIN")) {
                        array[0] = null;
                        return;
                    }
                    new1.g = a(a(array, array2, '='), 0);
                }
            }
        }
    }
    
    private static int a(String upperCase) {
        upperCase = upperCase.replace('i', 'I').toUpperCase();
        if (upperCase.equals("CENTER")) {
            return 30;
        }
        if (upperCase.equals("TOP")) {
            return 10;
        }
        if (upperCase.equals("BOTTOM")) {
            return 20;
        }
        if (upperCase.equals("RIGHT")) {
            return 50;
        }
        return 40;
    }
    
    private static boolean for(final String[] array, final int[] array2) {
        try(array, array2);
        if (array[0] == null) {
            return false;
        }
        if (array[0].length() <= array2[0] || array[0].charAt(array2[0]) != '=') {
            return false;
        }
        final int n = 0;
        ++array2[n];
        try(array, array2);
        if (array[0].length() > array2[0] && array[0].charAt(array2[0]) == '{') {
            final int n2 = 0;
            ++array2[n2];
            return true;
        }
        return false;
    }
    
    private static String new(final String[] array, final int[] array2) {
        String substring = new String();
        boolean b = true;
        try(array, array2);
        final int n = array2[0];
        while (b) {
            if (array[0].length() <= array2[0]) {
                return "";
            }
            final char char1 = array[0].charAt(array2[0]);
            if (a(char1) || char1 == '=' || char1 == ';' || char1 == '}' || char1 == '{') {
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
        try(array, array2);
        return substring.replace('i', 'I').toUpperCase();
    }
    
    private static String int(final String[] array, final int[] array2) {
        return a(array, array2, '=');
    }
    
    private static String a(final String[] array, final int[] array2, final char c) {
        final String s = new String();
        try(array, array2);
        if (array[0].length() > array2[0] && (array[0].charAt(array2[0]) == c || c == ' ')) {
            if (c != ' ') {
                final int n = 0;
                ++array2[n];
                try(array, array2);
            }
            final int length = array[0].length();
            if (length > array2[0] + 1 && array[0].charAt(array2[0]) == '\'') {
                final int index = array[0].indexOf(39, array2[0] + 1);
                if (index > 0 && index < length) {
                    final String substring = array[0].substring(array2[0] + 1, index);
                    array2[0] = index + 1;
                    return substring;
                }
            }
        }
        return null;
    }
    
    private static void a(final k k, final k i) {
        i.a.goto = k.a.goto;
        i.a.int = k.a.int;
        i.a.byte = k.a.byte;
        i.a.a = k.a.a;
        if (k.a.void == null || k.a.try == null) {
            i.a.void = k.a.void;
            i.a.try = k.a.try;
        }
    }
}
