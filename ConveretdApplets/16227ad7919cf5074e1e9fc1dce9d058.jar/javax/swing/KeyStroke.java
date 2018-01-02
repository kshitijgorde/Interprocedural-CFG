// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.event.KeyEvent;
import java.util.StringTokenizer;
import java.io.Serializable;

public class KeyStroke implements Serializable
{
    private static final Object pressedCharCacheKey;
    private static final Object releasedCharCacheKey;
    private static final Object pressedCodeCacheKey;
    private static final Object releasedCodeCacheKey;
    char keyChar;
    int keyCode;
    int modifiers;
    boolean onKeyRelease;
    static final int MIN_ASCII_CACHE_INDEX = 10;
    static final int MAX_ASCII_CACHE_INDEX = 127;
    private static final Object classLock;
    private static ModifierKeyword[] modifierKeywords;
    static /* synthetic */ Class class$java$awt$event$KeyEvent;
    
    static {
        pressedCharCacheKey = new StringBuffer("KeyStroke.pressedCharCacheKey");
        releasedCharCacheKey = new StringBuffer("KeyStroke.releasedCharCacheKey");
        pressedCodeCacheKey = new StringBuffer("KeyStroke.pressedCodeCacheKey");
        releasedCodeCacheKey = new StringBuffer("KeyStroke.releasedCodeCacheKey");
        classLock = new Object();
        KeyStroke.modifierKeywords = new ModifierKeyword[] { new ModifierKeyword("shift", 1), new ModifierKeyword("control", 2), new ModifierKeyword("ctrl", 2), new ModifierKeyword("meta", 4), new ModifierKeyword("alt", 8), new ModifierKeyword("button1", 16), new ModifierKeyword("button2", 8), new ModifierKeyword("button3", 4) };
    }
    
    static void cacheKeyCharKeyStroke(final KeyStroke keyStroke, final boolean b) {
        if (keyStroke.keyChar >= '\n' && keyStroke.keyChar < '\u007f') {
            synchronized (KeyStroke.classLock) {
                if (b) {
                    KeyStroke[] array = (KeyStroke[])SwingUtilities.appContextGet(KeyStroke.releasedCharCacheKey);
                    if (array == null) {
                        array = new KeyStroke[117];
                        SwingUtilities.appContextPut(KeyStroke.releasedCharCacheKey, array);
                    }
                    array[keyStroke.keyChar - '\n'] = keyStroke;
                }
                else {
                    KeyStroke[] array2 = (KeyStroke[])SwingUtilities.appContextGet(KeyStroke.pressedCharCacheKey);
                    if (array2 == null) {
                        array2 = new KeyStroke[117];
                        SwingUtilities.appContextPut(KeyStroke.pressedCharCacheKey, array2);
                    }
                    array2[keyStroke.keyChar - '\n'] = keyStroke;
                }
            }
            // monitorexit(KeyStroke.classLock)
        }
    }
    
    static void cacheKeyStroke(final KeyStroke keyStroke) {
        final int subIndexForModifier;
        if (keyStroke.keyCode >= 10 && keyStroke.keyCode < 127 && (subIndexForModifier = subIndexForModifier(keyStroke.modifiers)) != -1) {
            synchronized (KeyStroke.classLock) {
                KeyStroke[][] array2;
                if (keyStroke.onKeyRelease) {
                    KeyStroke[][] array = (KeyStroke[][])SwingUtilities.appContextGet(KeyStroke.pressedCodeCacheKey);
                    if (array == null) {
                        array = new KeyStroke[4][117];
                        SwingUtilities.appContextPut(KeyStroke.pressedCodeCacheKey, array);
                    }
                    array2 = array;
                }
                else {
                    KeyStroke[][] array3 = (KeyStroke[][])SwingUtilities.appContextGet(KeyStroke.releasedCodeCacheKey);
                    if (array3 == null) {
                        array3 = new KeyStroke[4][117];
                        SwingUtilities.appContextPut(KeyStroke.releasedCodeCacheKey, array3);
                    }
                    array2 = array3;
                }
                array2[subIndexForModifier][keyStroke.keyCode - 10] = keyStroke;
            }
            // monitorexit(KeyStroke.classLock)
        }
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public boolean equals(final Object o) {
        if (o instanceof KeyStroke) {
            final KeyStroke keyStroke = (KeyStroke)o;
            if (keyStroke.keyChar == this.keyChar && keyStroke.keyCode == this.keyCode && keyStroke.onKeyRelease == this.onKeyRelease && keyStroke.modifiers == this.modifiers) {
                return true;
            }
        }
        return false;
    }
    
    static KeyStroke getCachedKeyCharKeyStroke(final char c, final boolean b) {
        KeyStroke keyStroke = null;
        if (c >= '\n' && c < '\u007f') {
            synchronized (KeyStroke.classLock) {
                KeyStroke[] array;
                if (b) {
                    array = (KeyStroke[])SwingUtilities.appContextGet(KeyStroke.releasedCharCacheKey);
                }
                else {
                    array = (KeyStroke[])SwingUtilities.appContextGet(KeyStroke.pressedCharCacheKey);
                }
                if (array != null) {
                    keyStroke = array[c - '\n'];
                }
            }
            // monitorexit(KeyStroke.classLock)
        }
        return keyStroke;
    }
    
    static KeyStroke getCachedKeyStroke(final int n, final int n2, final boolean b) {
        KeyStroke keyStroke = null;
        final int subIndexForModifier;
        if (n >= 10 && n < 127 && (subIndexForModifier = subIndexForModifier(n2)) != -1) {
            synchronized (KeyStroke.classLock) {
                KeyStroke[][] array;
                if (b) {
                    array = (KeyStroke[][])SwingUtilities.appContextGet(KeyStroke.pressedCodeCacheKey);
                }
                else {
                    array = (KeyStroke[][])SwingUtilities.appContextGet(KeyStroke.releasedCodeCacheKey);
                }
                if (array != null) {
                    keyStroke = array[subIndexForModifier][n - 10];
                }
            }
            // monitorexit(KeyStroke.classLock)
        }
        return keyStroke;
    }
    
    public char getKeyChar() {
        return this.keyChar;
    }
    
    public int getKeyCode() {
        return this.keyCode;
    }
    
    public static KeyStroke getKeyStroke(final char c) {
        return getKeyStroke(c, false);
    }
    
    public static KeyStroke getKeyStroke(final char keyChar, final boolean onKeyRelease) {
        KeyStroke cachedKeyCharKeyStroke = getCachedKeyCharKeyStroke(keyChar, onKeyRelease);
        if (cachedKeyCharKeyStroke == null) {
            cachedKeyCharKeyStroke = new KeyStroke();
            cachedKeyCharKeyStroke.keyChar = keyChar;
            cachedKeyCharKeyStroke.modifiers = 0;
            cacheKeyCharKeyStroke(cachedKeyCharKeyStroke, cachedKeyCharKeyStroke.onKeyRelease = onKeyRelease);
        }
        return cachedKeyCharKeyStroke;
    }
    
    public static KeyStroke getKeyStroke(final int n, final int n2) {
        return getKeyStroke(n, n2, false);
    }
    
    public static KeyStroke getKeyStroke(final int keyCode, final int modifiers, final boolean onKeyRelease) {
        KeyStroke cachedKeyStroke = getCachedKeyStroke(keyCode, modifiers, onKeyRelease);
        if (cachedKeyStroke == null) {
            cachedKeyStroke = new KeyStroke();
            cachedKeyStroke.keyCode = keyCode;
            cachedKeyStroke.modifiers = modifiers;
            cachedKeyStroke.onKeyRelease = onKeyRelease;
            cacheKeyStroke(cachedKeyStroke);
        }
        return cachedKeyStroke;
    }
    
    public static KeyStroke getKeyStroke(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        int n = 0;
        String nextToken;
        while ((nextToken = stringTokenizer.nextToken()) != null) {
            int modifierMask = 0;
            for (int n2 = 0; modifierMask == 0 && n2 < KeyStroke.modifierKeywords.length; modifierMask = KeyStroke.modifierKeywords[n2].getModifierMask(nextToken), ++n2) {}
            if (modifierMask == 0) {
                final String versionMap = versionMap("VK_" + nextToken);
                int int1;
                try {
                    int1 = ((KeyStroke.class$java$awt$event$KeyEvent != null) ? KeyStroke.class$java$awt$event$KeyEvent : (KeyStroke.class$java$awt$event$KeyEvent = class$("java.awt.event.KeyEvent"))).getField(versionMap).getInt((KeyStroke.class$java$awt$event$KeyEvent != null) ? KeyStroke.class$java$awt$event$KeyEvent : (KeyStroke.class$java$awt$event$KeyEvent = class$("java.awt.event.KeyEvent")));
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    throw new Error("Unrecognized keycode name: " + versionMap);
                }
                return getKeyStroke(int1, n);
            }
            n |= modifierMask;
        }
        throw new Error("Can't parse KeyStroke: \"" + s + "\"");
    }
    
    public static KeyStroke getKeyStrokeForEvent(final KeyEvent keyEvent) {
        KeyStroke keyStroke = null;
        switch (keyEvent.getID()) {
            case 401: {
                keyStroke = getKeyStroke(keyEvent.getKeyCode(), keyEvent.getModifiers(), false);
                break;
            }
            case 402: {
                keyStroke = getKeyStroke(keyEvent.getKeyCode(), keyEvent.getModifiers(), true);
                break;
            }
            case 400: {
                keyStroke = getKeyStroke(keyEvent.getKeyChar());
                break;
            }
        }
        return keyStroke;
    }
    
    public int getModifiers() {
        return this.modifiers;
    }
    
    private static String getStringRepresentation(final char c, final int n, final boolean b) {
        return "keyChar " + KeyEvent.getKeyModifiersText(n) + c + (b ? "-R" : "-P");
    }
    
    private static String getStringRepresentation(final int n, final int n2, final boolean b) {
        return "keyCode " + KeyEvent.getKeyModifiersText(n2) + KeyEvent.getKeyText(n) + (b ? "-R" : "-P");
    }
    
    public int hashCode() {
        return (this.keyChar + '\u0001') * (2 * (this.keyCode + 1)) * (this.modifiers + 1) + (this.onKeyRelease ? 1 : 2);
    }
    
    public boolean isOnKeyRelease() {
        return this.onKeyRelease;
    }
    
    static int subIndexForModifier(final int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 8) {
            return 3;
        }
        return -1;
    }
    
    public String toString() {
        if (this.keyChar == '\0') {
            return getStringRepresentation(this.keyCode, this.modifiers, this.onKeyRelease);
        }
        return getStringRepresentation(this.keyChar, 0, this.onKeyRelease);
    }
    
    private static String versionMap(String s) {
        if (!SwingUtilities.is1dot2) {
            if (s.equals("VK_KP_UP")) {
                s = "VK_UP";
            }
            if (s.equals("VK_KP_DOWN")) {
                s = "VK_DOWN";
            }
            if (s.equals("VK_KP_LEFT")) {
                s = "VK_LEFT";
            }
            if (s.equals("VK_KP_RIGHT")) {
                s = "VK_RIGHT";
            }
        }
        return s;
    }
    
    private static class ModifierKeyword
    {
        final String keyword;
        final int mask;
        
        ModifierKeyword(final String keyword, final int mask) {
            this.keyword = keyword;
            this.mask = mask;
        }
        
        int getModifierMask(final String s) {
            return s.equals(this.keyword) ? this.mask : 0;
        }
    }
}
