// 
// Decompiled by Procyon v0.5.30
// 

package com.dreamfabric.jac64;

import java.awt.event.KeyEvent;
import java.util.Locale;
import com.dreamfabric.c64utils.C64Script;

public class Keyboard
{
    public static final int IO_OFFSET = 12288;
    public static final int AUTO_SHIFT = 1024;
    public static final int AUTO_CTRL = 2048;
    public static final int AUTO_COMMODORE = 4096;
    public static final int MIN_AUTO = 1024;
    public static final int STICK_UPDOWN = 3;
    public static final int STICK_LEFTRIGHT = 12;
    public static final int STICK_UP = 1;
    public static final int STICK_DOWN = 2;
    public static final int STICK_LEFT = 4;
    public static final int STICK_RIGHT = 8;
    public static final int STICK_FIRE = 16;
    public static final char[][] KEYMAPS;
    boolean extendedKeyboardEmulation;
    boolean stickExits;
    int joystick1;
    boolean lastUp;
    boolean lastLeft;
    private int joy1;
    private int joy2;
    int stick;
    private int keyPressed;
    private int lastKey;
    public boolean ready;
    public int reads;
    private C64Script c64script;
    private String[] hotkeyScript;
    private Object[] hotkeyObject;
    int[] keyrow;
    int[] keycol;
    int[][] keytable;
    int keyShift;
    int[][] keytableDef;
    private int restoreKey;
    public static final int USER_UP = 0;
    public static final int USER_DOWN = 1;
    public static final int USER_LEFT = 2;
    public static final int USER_RIGHT = 3;
    public static final int USER_FIRE = 4;
    private static final int[] ARROWS;
    private int[] userDefinedStick;
    private int[] memory;
    private CIA cia;
    private C64Screen screen;
    
    private void remap(final char c, final char c2) {
        for (int i = 0; i < this.keytableDef.length; ++i) {
            if (this.keytableDef[i][0] == c) {
                this.keytableDef[i][0] = c2;
                System.out.println("Remapped: " + c + " => " + c2 + "  key " + (int)c + " => " + (int)c2);
                return;
            }
        }
    }
    
    private boolean doMap(final String s) {
        for (int i = 0; i < Keyboard.KEYMAPS.length; ++i) {
            final char[] array = Keyboard.KEYMAPS[i];
            final String string = "" + array[0] + array[1];
            System.out.println("Checking map for: " + string);
            if (s.equals(string)) {
                System.out.println("Found map - mapping...");
                for (int j = 2; j < array.length; j += 2) {
                    this.remap(array[j], array[j + 1]);
                }
                return true;
            }
        }
        return false;
    }
    
    public Keyboard(final C64Screen screen, final CIA cia, final int[] memory) {
        this.extendedKeyboardEmulation = false;
        this.stickExits = false;
        this.joystick1 = 255;
        this.lastUp = false;
        this.lastLeft = false;
        this.joy1 = 255;
        this.joy2 = 255;
        this.stick = 68608;
        this.keyPressed = 0;
        this.ready = false;
        this.reads = 0;
        this.keyrow = new int[8];
        this.keycol = new int[8];
        this.keytable = new int[1024][3];
        this.keyShift = 0;
        this.keytableDef = new int[][] { { 65, 1, 2, 0 }, { 66, 3, 4, 0 }, { 67, 2, 4, 0 }, { 68, 2, 2, 0 }, { 69, 1, 6, 0 }, { 70, 2, 5, 0 }, { 71, 3, 2, 0 }, { 72, 3, 5, 0 }, { 73, 4, 1, 0 }, { 74, 4, 2, 0 }, { 75, 4, 5, 0 }, { 76, 5, 2, 0 }, { 77, 4, 4, 0 }, { 78, 4, 7, 0 }, { 79, 4, 6, 0 }, { 80, 5, 1, 0 }, { 81, 7, 6, 0 }, { 82, 2, 1, 0 }, { 83, 1, 5, 0 }, { 84, 2, 6, 0 }, { 85, 3, 6, 0 }, { 86, 3, 7, 0 }, { 87, 1, 1, 0 }, { 88, 2, 7, 0 }, { 89, 3, 1, 0 }, { 90, 1, 4, 0 }, { 48, 4, 3, 0 }, { 49, 7, 0, 0 }, { 50, 7, 3, 0 }, { 51, 1, 0, 0 }, { 52, 1, 3, 0 }, { 53, 2, 0, 0 }, { 54, 2, 3, 0 }, { 55, 3, 0, 0 }, { 56, 3, 3, 0 }, { 57, 4, 0, 0 }, { 10, 0, 1, 0 }, { 10, 0, 1, 0 }, { 32, 7, 4, 0 }, { 44, 5, 7, 0 }, { 46, 5, 4, 0 }, { 92, 6, 5, 0 }, { 59, 5, 5, 0 }, { 45, 5, 0, 0 }, { 61, 5, 3, 0 }, { 96, 7, 1, 0 }, { 39, 6, 2, 0 }, { 109, 5, 3, 0 }, { 91, 5, 6, 0 }, { 93, 6, 1, 0 }, { 27, 7, 1, 0 }, { 47, 6, 7, 0 }, { 111, 6, 7, 0 }, { 63, 6, 7, 1024 }, { 127, 0, 0, 0 }, { 8, 0, 0, 0 }, { 16, 1, 7, 0 }, { 20, 6, 4, 0 }, { 19, 7, 7, 0 }, { 27, 7, 7, 0 }, { 13, 0, 1, 0 }, { 17, 7, 5, 2 }, { 10, 0, 1, 0 }, { 40, 0, 7, 0 }, { 38, 0, 7, 1024 }, { 39, 0, 2, 0 }, { 37, 0, 2, 1024 }, { 112, 0, 4, 0 }, { 114, 0, 5, 0 }, { 116, 0, 6, 0 }, { 118, 0, 3, 0 }, { 36, 6, 3, 0 }, { 35, 6, 6, 0 }, { 155, 6, 0, 0 }, { 9, 7, 2, 0 } };
        this.restoreKey = 33;
        this.userDefinedStick = Keyboard.ARROWS;
        final Locale default1 = Locale.getDefault();
        System.out.println("Locale: " + default1);
        final String language = default1.getLanguage();
        if (!this.doMap(language)) {
            System.out.println("Could not find map for keyboard: " + language);
        }
        this.cia = cia;
        this.memory = memory;
        this.screen = screen;
        for (int i = 0; i < this.keytable.length; ++i) {
            this.keytable[i][0] = -1;
        }
        for (int j = 0; j < this.keytableDef.length; ++j) {
            final int n = this.keytableDef[j][0];
            this.keytable[n][0] = this.keytableDef[j][1];
            this.keytable[n][1] = this.keytableDef[j][2];
            this.keytable[n][2] = this.keytableDef[j][3];
        }
        this.reset();
    }
    
    public void registerHotKey(final int n, final String s, final Object o) {
        if (this.hotkeyScript == null) {
            this.c64script = new C64Script();
            this.hotkeyScript = new String[12];
            this.hotkeyObject = new Object[12];
        }
        if (n < this.hotkeyScript.length) {
            this.hotkeyScript[n] = s;
            this.hotkeyObject[n] = o;
        }
    }
    
    public void setStick(final boolean b) {
        if (b) {
            this.stick = 68609;
        }
        else {
            this.stick = 68608;
        }
    }
    
    private int getUserStick(final int n, final int n2) {
        if (this.userDefinedStick != null) {
            for (int i = 0; i < this.userDefinedStick.length; i += 2) {
                if (n == this.userDefinedStick[i] && (this.userDefinedStick[i + 1] == 0 || n2 == this.userDefinedStick[i + 1])) {
                    return i / 2;
                }
            }
        }
        return -1;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final char keyChar = keyEvent.getKeyChar();
        int lastKey = keyEvent.getKeyCode();
        final int keyLocation = keyEvent.getKeyLocation();
        if (this.hotkeyScript != null && keyEvent.isAltDown()) {
            final int n = 112 - lastKey;
            if (n < 12) {
                System.out.println("HotKey: " + n);
                if (this.hotkeyScript[n] != null) {
                    this.c64script.interpretCall(this.hotkeyScript[n], this.hotkeyObject[n]);
                }
            }
        }
        if (lastKey == 0) {
            System.out.println("KeyZero ???");
            lastKey = Character.toLowerCase(keyChar);
        }
        if (lastKey == this.restoreKey) {
            this.screen.restoreKey(true);
        }
        if (lastKey != this.lastKey) {
            ++this.keyPressed;
        }
        this.lastKey = lastKey;
        int n2 = this.getUserStick(lastKey, keyLocation);
        if (this.extendedKeyboardEmulation) {
            n2 = -1;
        }
        if (n2 == -1) {
            n2 = this.getNormalStick(lastKey);
        }
        switch (n2) {
            case 0: {
                this.joystick1 &= 0xFE;
                this.lastUp = true;
                if (this.stickExits) {
                    this.updateKeyboard();
                    return;
                }
                break;
            }
            case 1: {
                this.joystick1 &= 0xFD;
                this.lastUp = false;
                if (this.stickExits) {
                    this.updateKeyboard();
                    return;
                }
                break;
            }
            case 2: {
                this.joystick1 &= 0xFB;
                this.lastLeft = true;
                if (this.stickExits) {
                    this.updateKeyboard();
                    return;
                }
                break;
            }
            case 3: {
                this.joystick1 &= 0xF7;
                this.lastLeft = false;
                if (this.stickExits) {
                    this.updateKeyboard();
                    return;
                }
                break;
            }
            case 4: {
                this.joystick1 &= 0xEF;
                if (this.stickExits) {
                    this.updateKeyboard();
                    return;
                }
                break;
            }
        }
        switch (lastKey) {
            case 121: {
                this.setStick(this.stick == 68608);
                break;
            }
            case 120: {
                System.out.println("F9");
                this.extendedKeyboardEmulation = !this.extendedKeyboardEmulation;
                this.stickExits = !this.extendedKeyboardEmulation;
                break;
            }
        }
        if (this.extendedKeyboardEmulation) {
            if ((this.keytable[lastKey][2] & 0x400) != 0x0) {
                ++this.keyShift;
                if (this.keyShift == 1) {
                    this.handleKeyPress(16, keyLocation);
                }
            }
            else if (lastKey == 16) {
                ++this.keyShift;
            }
            this.handleKeyPress(lastKey, keyLocation);
            this.updateKeyboard();
        }
        else if (this.keytable[lastKey][2] < 1024) {
            this.handleKeyPress(lastKey, keyLocation);
            this.updateKeyboard();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        int n = keyEvent.getKeyCode();
        final char keyChar = keyEvent.getKeyChar();
        final int keyLocation = keyEvent.getKeyLocation();
        if (n == 0) {
            System.out.println("KeyZero???");
            n = Character.toLowerCase(keyChar);
        }
        if (n == this.restoreKey) {
            this.screen.restoreKey(false);
        }
        --this.keyPressed;
        this.lastKey = 0;
        if (this.keyPressed < 0) {
            this.keyPressed = 0;
        }
        int n2 = this.getUserStick(n, keyLocation);
        if (n2 == -1) {
            n2 = this.getNormalStick(n);
        }
        switch (n2) {
            case 0: {
                this.joystick1 |= 0x1;
                if (this.stickExits) {
                    this.updateKeyboard();
                    return;
                }
                break;
            }
            case 1: {
                this.joystick1 |= 0x2;
                if (this.stickExits) {
                    this.updateKeyboard();
                    return;
                }
                break;
            }
            case 2: {
                this.joystick1 |= 0x4;
                if (this.stickExits) {
                    this.updateKeyboard();
                    return;
                }
                break;
            }
            case 3: {
                this.joystick1 |= 0x8;
                if (this.stickExits) {
                    this.updateKeyboard();
                    return;
                }
                break;
            }
            case 4: {
                this.joystick1 |= 0x10;
                if (this.stickExits) {
                    this.updateKeyboard();
                    return;
                }
                break;
            }
        }
        if (this.extendedKeyboardEmulation) {
            if ((this.keytable[n][2] & 0x400) != 0x0) {
                --this.keyShift;
                if (this.keyShift == 0) {
                    this.handleKeyRelease(16, keyLocation);
                }
            }
            else if (n == 16) {
                --this.keyShift;
                if (this.keyShift > 0) {
                    return;
                }
            }
            this.handleKeyRelease(n, keyLocation);
        }
        else if (this.keytable[n][2] < 1024) {
            this.handleKeyRelease(n, keyLocation);
        }
    }
    
    private int getNormalStick(final int n) {
        switch (n) {
            case 104: {
                return 0;
            }
            case 98:
            case 101: {
                return 1;
            }
            case 100: {
                return 2;
            }
            case 102: {
                return 3;
            }
            case 96: {
                return 4;
            }
            default: {
                return -1;
            }
        }
    }
    
    private void handleKeyPress(final int n, final int n2) {
        final int n3 = this.keytable[n][0];
        final int n4 = this.keytable[n][1];
        if (n3 != -1 && n4 != -1) {
            this.keyrow[n3] &= 255 - (1 << this.keytable[n][1]);
            this.keycol[n4] &= 255 - (1 << this.keytable[n][0]);
        }
    }
    
    private void handleKeyRelease(final int n, final int n2) {
        final int n3 = this.keytable[n][0];
        final int n4 = this.keytable[n][1];
        if (n3 != -1 && n4 != -1) {
            this.keyrow[n3] |= 1 << this.keytable[n][1];
            this.keycol[n4] |= 1 << this.keytable[n][0];
        }
        this.updateKeyboard();
    }
    
    int readDC00(final int n) {
        int n2 = 255;
        final int n3 = (this.cia.ciaMemory[1] | ~this.cia.ciaMemory[3]) & ((n < 65536) ? this.joy1 : 255);
        int n4 = 1;
        for (int i = 0; i < 8; ++i) {
            if ((n3 & n4) == 0x0) {
                n2 &= this.keycol[i];
            }
            n4 <<= 1;
        }
        return n2 & (this.cia.ciaMemory[0] | ~this.cia.ciaMemory[2]) & ((n < 65536) ? this.joy2 : 255);
    }
    
    int readDC01(final int n) {
        int n2 = 255;
        final int n3 = (this.cia.ciaMemory[0] | ~this.cia.ciaMemory[2]) & ((n < 65536) ? this.joy2 : 255);
        int n4 = 1;
        for (int i = 0; i < 8; ++i) {
            if ((n3 & n4) == 0x0) {
                n2 &= this.keyrow[i];
            }
            n4 <<= 1;
        }
        return n2 & (this.cia.ciaMemory[1] | ~this.cia.ciaMemory[3]) & ((n < 65536) ? this.joy1 : 255);
    }
    
    void updateKeyboard() {
        int joystick1 = this.joystick1;
        if ((joystick1 & 0x3) == 0x0) {
            joystick1 = ((joystick1 | 0x3) & 255 - (this.lastUp ? 1 : 2));
        }
        if ((joystick1 & 0xC) == 0x0) {
            joystick1 = ((joystick1 | 0xC) & 255 - (this.lastLeft ? 4 : 8));
        }
        this.joy2 = ((this.stick == 68608) ? 255 : joystick1);
        this.joy1 = ((this.stick == 68608) ? joystick1 : 255);
        if (!this.ready && this.reads++ > 20) {
            this.ready = true;
            this.reads = 0;
        }
    }
    
    public void reset() {
        this.lastKey = 0;
        this.keyPressed = 0;
        this.keyShift = 0;
        this.joystick1 = 255;
        this.reads = 0;
        this.ready = false;
        for (int i = 0; i < 8; ++i) {
            this.keyrow[i] = 255;
            this.keycol[i] = 255;
        }
    }
    
    static {
        KEYMAPS = new char[][] { { 's', 'v', ';', '\u00f6', '\'', '\u00e4', '[', '\u00e5', '`', '§', '\\', '\u00de', '/', '-', ']', '\u0087', '-', '\u0209', '=', '\u0081' }, { 'd', 'e', ';', '\u00f6', '\'', '\u00e4', '[', '\u00fc', '`', '^', '\\', '\u0208', '/', '-', ']', '\u0209', '-', '\u00df', '=', '\u0081' } };
        ARROWS = new int[] { 38, 0, 40, 0, 37, 0, 39, 0, 17, 3 };
    }
}
