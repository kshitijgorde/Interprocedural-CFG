// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.zxspectrum;

import java.awt.event.KeyEvent;
import java.util.Hashtable;
import java.awt.event.KeyListener;
import com.tn.zx.ZxKeyboardHandler;

public class ZxSpectrumKeyboardHandlerImpl implements ZxKeyboardHandler, KeyListener
{
    private Hashtable ivKeysPressed;
    private int[] ivZXKeyCounts;
    private int[] ivZXKeyRows;
    private int[] ivZXKeyRowsCurrent;
    private static final int ZX_CAPS = 0;
    private static final int ZX_Z = 1;
    private static final int ZX_X = 2;
    private static final int ZX_C = 3;
    private static final int ZX_V = 4;
    private static final int ZX_A = 16;
    private static final int ZX_S = 17;
    private static final int ZX_D = 18;
    private static final int ZX_F = 19;
    private static final int ZX_G = 20;
    private static final int ZX_Q = 32;
    private static final int ZX_W = 33;
    private static final int ZX_E = 34;
    private static final int ZX_R = 35;
    private static final int ZX_T = 36;
    private static final int ZX_1 = 48;
    private static final int ZX_2 = 49;
    private static final int ZX_3 = 50;
    private static final int ZX_4 = 51;
    private static final int ZX_5 = 52;
    private static final int ZX_0 = 64;
    private static final int ZX_9 = 65;
    private static final int ZX_8 = 66;
    private static final int ZX_7 = 67;
    private static final int ZX_6 = 68;
    private static final int ZX_P = 80;
    private static final int ZX_O = 81;
    private static final int ZX_I = 82;
    private static final int ZX_U = 83;
    private static final int ZX_Y = 84;
    private static final int ZX_ENTER = 96;
    private static final int ZX_L = 97;
    private static final int ZX_K = 98;
    private static final int ZX_J = 99;
    private static final int ZX_H = 100;
    private static final int ZX_SPACE = 112;
    private static final int ZX_SYMBOL = 113;
    private static final int ZX_M = 114;
    private static final int ZX_N = 115;
    private static final int ZX_B = 116;
    
    public ZxSpectrumKeyboardHandlerImpl() {
        this.ivKeysPressed = new Hashtable();
        this.ivZXKeyCounts = new int[256];
        this.ivZXKeyRows = new int[8];
        this.ivZXKeyRowsCurrent = new int[8];
    }
    
    @Override
    public synchronized void convertKeyboard() {
        for (int i = 0; i < this.ivZXKeyRows.length; ++i) {
            this.ivZXKeyRowsCurrent[i] = this.ivZXKeyRows[i];
        }
    }
    
    @Override
    public int getKeyMask(final int pPort254HiByte) {
        int result = 0;
        for (int i = 0; i < 8; ++i) {
            if ((pPort254HiByte & 1 << i) == 0x0) {
                result |= this.ivZXKeyRowsCurrent[i];
            }
        }
        return result ^ 0xFF;
    }
    
    private int[] keyCodeToZXKeyCodes(final int pKeyCode) {
        int key1 = -1;
        int key2 = -1;
        switch (pKeyCode) {
            case 48: {
                key1 = 64;
                break;
            }
            case 49: {
                key1 = 48;
                break;
            }
            case 50: {
                key1 = 49;
                break;
            }
            case 51: {
                key1 = 50;
                break;
            }
            case 52: {
                key1 = 51;
                break;
            }
            case 53: {
                key1 = 52;
                break;
            }
            case 54: {
                key1 = 68;
                break;
            }
            case 55: {
                key1 = 67;
                break;
            }
            case 56: {
                key1 = 66;
                break;
            }
            case 57: {
                key1 = 65;
                break;
            }
            case 65: {
                key1 = 16;
                break;
            }
            case 66: {
                key1 = 116;
                break;
            }
            case 67: {
                key1 = 3;
                break;
            }
            case 68: {
                key1 = 18;
                break;
            }
            case 69: {
                key1 = 34;
                break;
            }
            case 70: {
                key1 = 19;
                break;
            }
            case 71: {
                key1 = 20;
                break;
            }
            case 72: {
                key1 = 100;
                break;
            }
            case 73: {
                key1 = 82;
                break;
            }
            case 74: {
                key1 = 99;
                break;
            }
            case 75: {
                key1 = 98;
                break;
            }
            case 76: {
                key1 = 97;
                break;
            }
            case 77: {
                key1 = 114;
                break;
            }
            case 78: {
                key1 = 115;
                break;
            }
            case 79: {
                key1 = 81;
                break;
            }
            case 80: {
                key1 = 80;
                break;
            }
            case 81: {
                key1 = 32;
                break;
            }
            case 82: {
                key1 = 35;
                break;
            }
            case 83: {
                key1 = 17;
                break;
            }
            case 84: {
                key1 = 36;
                break;
            }
            case 85: {
                key1 = 83;
                break;
            }
            case 86: {
                key1 = 4;
                break;
            }
            case 87: {
                key1 = 33;
                break;
            }
            case 88: {
                key1 = 2;
                break;
            }
            case 89: {
                key1 = 84;
                break;
            }
            case 90: {
                key1 = 1;
                break;
            }
            case 10: {
                key1 = 96;
                break;
            }
            case 32: {
                key1 = 112;
                break;
            }
            case 16: {
                key1 = 0;
                break;
            }
            case 17: {
                key1 = 113;
                break;
            }
            case 39: {
                key1 = 0;
                key2 = 66;
                break;
            }
            case 37: {
                key1 = 0;
                key2 = 52;
                break;
            }
            case 38: {
                key1 = 0;
                key2 = 67;
                break;
            }
            case 40: {
                key1 = 0;
                key2 = 68;
                break;
            }
            case 8: {
                key1 = 0;
                key2 = 64;
                break;
            }
            case 44: {
                key1 = 113;
                key2 = 115;
                break;
            }
            case 46: {
                key1 = 113;
                key2 = 114;
                break;
            }
            case 222: {
                key1 = 113;
                key2 = 80;
                break;
            }
        }
        if (key2 != -1) {
            return new int[] { key1, key2 };
        }
        if (key1 != -1) {
            return new int[] { key1 };
        }
        return new int[0];
    }
    
    @Override
    public synchronized void keyPressed(final KeyEvent e) {
        final int keyCode = e.getKeyCode();
        final Integer i = new Integer(keyCode);
        if (this.ivKeysPressed.containsKey(i)) {
            return;
        }
        this.ivKeysPressed.put(i, i);
        final int[] keys = this.keyCodeToZXKeyCodes(keyCode);
        for (int j = 0; j < keys.length; ++j) {
            this.registerZXKeyPress(keys[j]);
        }
    }
    
    @Override
    public synchronized void keyReleased(final KeyEvent e) {
        final int keyCode = e.getKeyCode();
        final Integer i = new Integer(keyCode);
        this.ivKeysPressed.remove(i);
        final int[] keys = this.keyCodeToZXKeyCodes(keyCode);
        for (int j = 0; j < keys.length; ++j) {
            this.registerZXKeyRelease(keys[j]);
        }
    }
    
    @Override
    public void keyTyped(final KeyEvent e) {
    }
    
    private void registerZXKeyPress(final int pZXKeyCode) {
        final int[] ivZXKeyCounts = this.ivZXKeyCounts;
        ++ivZXKeyCounts[pZXKeyCode];
        final int[] ivZXKeyRows = this.ivZXKeyRows;
        final int n = pZXKeyCode >> 4;
        ivZXKeyRows[n] |= 1 << (pZXKeyCode & 0xF);
    }
    
    private void registerZXKeyRelease(final int pZXKeyCode) {
        final int count = this.ivZXKeyCounts[pZXKeyCode]--;
        if (count <= 1) {
            final int[] ivZXKeyRows = this.ivZXKeyRows;
            final int n = pZXKeyCode >> 4;
            ivZXKeyRows[n] &= (1 << (pZXKeyCode & 0xF) ^ 0xFF);
            this.ivZXKeyCounts[pZXKeyCode] = 0;
        }
    }
    
    public void reset() {
        this.ivKeysPressed.clear();
        for (int i = 0; i < this.ivZXKeyCounts.length; ++i) {
            this.ivZXKeyCounts[i] = 0;
        }
        for (int i = 0; i < this.ivZXKeyRows.length; ++i) {
            this.ivZXKeyRows[i] = 0;
            this.ivZXKeyRowsCurrent[i] = 0;
        }
    }
    
    public void terminate() {
    }
}
