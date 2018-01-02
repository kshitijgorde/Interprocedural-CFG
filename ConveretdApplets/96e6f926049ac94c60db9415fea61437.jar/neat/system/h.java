// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.awt.event.KeyEvent;

public class h extends g
{
    public long c;
    public int d;
    public int e;
    public char f;
    
    protected void a(final KeyEvent keyEvent) {
        this.c = keyEvent.getWhen();
        this.d = keyEvent.getModifiers();
        this.e = keyEvent.getKeyCode();
        this.f = keyEvent.getKeyChar();
        if (this.f == '@') {
            this.e = 305397760;
        }
        else if (this.f == '_') {
            this.e = 305397761;
        }
        else if (this.f == '.') {
            this.e = 46;
        }
        else if (this.f == ',') {
            this.e = 44;
        }
        else if (this.f == ';') {
            this.e = 59;
        }
        else if (this.f == ':') {
            this.e = 305397762;
        }
        else if (this.f == '?') {
            this.e = 305397763;
        }
        else if (this.f == '!') {
            this.e = 305397764;
        }
        else if (this.f == '-') {
            this.e = 109;
        }
        else if (this.f == '+') {
            this.e = 107;
        }
        else if (this.f == '*') {
            this.e = 106;
        }
        else if (this.f == '/') {
            this.e = 47;
        }
        else if (this.f == '\\') {
            this.e = 92;
        }
        else if (this.f == '&') {
            this.e = 305397765;
        }
        else if (this.f == '%') {
            this.e = 305397766;
        }
        else if (this.f == '~') {
            this.e = 305397767;
        }
        else if (this.f == '=') {
            this.e = 61;
        }
        else if (this.f == '(') {
            this.e = 91;
        }
        else if (this.f == ')') {
            this.e = 93;
        }
        else if (this.f == '#') {
            this.e = 305397768;
        }
        else if (this.f == '^') {
            this.e = 305397769;
        }
    }
}
