import java.awt.Event;
import java.awt.event.FocusEvent;
import java.awt.datatransfer.Transferable;
import java.io.IOException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.util.StringTokenizer;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

class bb extends TextField implements KeyListener, FocusListener
{
    bi a;
    boolean b;
    int c;
    int d;
    private w e;
    private boolean f;
    boolean g;
    
    bb(final int n, final int n2) {
        super(n);
        this.f = false;
        this.a = new bi(n2 + 1);
        this.addFocusListener(this);
        if (irc.cB != null) {
            this.setText(irc.cB);
            this.selectAll();
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final boolean dx = bm.dX;
        Label_0029: {
            if (keyEvent.getKeyCode() == 9) {
                this.g = true;
                if (!dx) {
                    break Label_0029;
                }
            }
            this.g = false;
        }
        if (this.e != null && keyEvent.getKeyCode() != 27 && keyEvent.getKeyCode() != 9) {
            this.e.g();
        }
        if (keyEvent.getKeyCode() == 38) {
            this.setText(this.a.a());
            return;
        }
        if (keyEvent.getKeyCode() == 40) {
            this.setText(this.a.b());
            return;
        }
        if (this.e != null && (keyEvent.getKeyCode() == 27 || keyEvent.getKeyCode() == 9)) {
            final int caretPosition = this.getCaretPosition();
            if (caretPosition == 0) {
                return;
            }
            final String text = this.getText();
            int n = caretPosition - 1;
            int char1 = 0;
            Label_0179: {
                while (true) {
                    Label_0156: {
                        if (!dx) {
                            break Label_0156;
                        }
                        --n;
                    }
                    if (n != 0) {
                        final char c = (char)(char1 = text.charAt(n));
                        if (dx) {
                            break Label_0179;
                        }
                        if (c != ' ') {
                            continue;
                        }
                    }
                    break;
                }
                char1 = n;
            }
            if (char1 != 0) {
                ++n;
            }
            final String c2 = this.e.c(text.substring(n, caretPosition).toLowerCase());
            if (c2 != null) {
                this.b(text.substring(0, n) + c2);
            }
        }
        else {
            if (keyEvent.getKeyCode() == 112 && irc.bP[1] != null) {
                this.b(irc.bP[1]);
                return;
            }
            if (keyEvent.getKeyCode() == 113 && irc.bP[2] != null) {
                this.b(irc.bP[2]);
                return;
            }
            if (keyEvent.getKeyCode() == 114 && irc.bP[3] != null) {
                this.b(irc.bP[3]);
                return;
            }
            if (keyEvent.getKeyCode() == 115 && irc.bP[4] != null) {
                this.b(irc.bP[4]);
                return;
            }
            if (keyEvent.getKeyCode() == 116 && irc.bP[5] != null) {
                this.b(irc.bP[5]);
                return;
            }
            if (keyEvent.getKeyCode() == 117 && irc.bP[6] != null) {
                this.b(irc.bP[6]);
                return;
            }
            if (keyEvent.getKeyCode() == 118 && irc.bP[7] != null) {
                this.b(irc.bP[7]);
                return;
            }
            if (keyEvent.getKeyCode() == 119 && irc.bP[8] != null) {
                this.b(irc.bP[8]);
                return;
            }
            if (keyEvent.getKeyCode() == 120 && irc.bP[9] != null) {
                this.b(irc.bP[9]);
                return;
            }
            if (keyEvent.getKeyCode() == 121 && irc.bP[10] != null) {
                this.b(irc.bP[10]);
                return;
            }
            if (keyEvent.getKeyCode() == 122 && irc.bP[11] != null) {
                this.b(irc.bP[11]);
                return;
            }
            if (keyEvent.getKeyCode() == 123 && irc.bP[12] != null) {
                this.b(irc.bP[12]);
                return;
            }
            if (irc.bv && keyEvent.getModifiers() == 2 && keyEvent.getKeyCode() == 86) {
                this.f = true;
                if (irc.bv) {
                    try {
                        final Transferable contents = irc.i.getContents(null);
                        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                            final StringTokenizer stringTokenizer = new StringTokenizer((String)contents.getTransferData(DataFlavor.stringFlavor), "\n");
                            while (true) {
                                while (true) {
                                    Label_0693: {
                                        if (!dx) {
                                            break Label_0693;
                                        }
                                        this.setText(stringTokenizer.nextToken());
                                        this.processActionEvent(new ActionEvent(this, 0, ""));
                                    }
                                    if (stringTokenizer.hasMoreTokens()) {
                                        continue;
                                    }
                                    break;
                                }
                                if (!dx) {
                                    return;
                                }
                                continue;
                            }
                        }
                    }
                    catch (UnsupportedFlavorException ex) {}
                    catch (IOException ex2) {}
                }
            }
        }
    }
    
    public void a(final int c) {
        this.c = c;
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.b = false;
    }
    
    public String a() {
        final String text = this.getText();
        if (text != null) {
            this.a.a(text);
        }
        if (text != null && text.length() >= 1 && text.trim().charAt(0) != '/' && irc.bo && (this.c != 0 || this.d != 0)) {
            this.setCaretPosition(0);
            this.c(3);
            if (this.c != 0) {
                this.d(this.c - 1);
            }
            if (this.d != 0) {
                if (this.c == 0) {
                    this.d(0);
                }
                this.a(",");
                this.d(this.d - 1);
            }
        }
        return this.getText();
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (this.f) {
            this.setText("");
            this.f = false;
        }
    }
    
    public void b(final int d) {
        this.d = d;
    }
    
    public void c(final int n) {
        final int caretPosition = this.getCaretPosition();
        final String text = this.getText();
        String substring = "";
        if (caretPosition < text.length()) {
            substring = text.substring(caretPosition);
        }
        this.setText(text.substring(0, caretPosition) + String.valueOf((char)n) + substring);
        this.setCaretPosition(caretPosition + 1);
    }
    
    boolean b() {
        return this.b;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return (event.modifiers & 0x2) != 0x0 || (event.modifiers & 0x4) != 0x0;
    }
    
    public void a(final w e) {
        this.e = e;
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.b = true;
    }
    
    public void d(final int n) {
        final String string = new Integer(n).toString();
        if (n < 10) {
            this.c(48);
        }
        int n2 = 0;
        while (true) {
            Label_0044: {
                if (!bm.dX) {
                    break Label_0044;
                }
                this.c(string.charAt(n2));
                ++n2;
            }
            if (n2 == string.length()) {
                return;
            }
            continue;
        }
    }
    
    public void a(final String s) {
        int n = 0;
        while (true) {
            Label_0020: {
                if (!bm.dX) {
                    break Label_0020;
                }
                this.c(s.charAt(n));
                ++n;
            }
            if (n == s.length()) {
                return;
            }
            continue;
        }
    }
    
    private void b(final String text) {
        this.setText(text);
        this.setCaretPosition(text.length());
    }
}
