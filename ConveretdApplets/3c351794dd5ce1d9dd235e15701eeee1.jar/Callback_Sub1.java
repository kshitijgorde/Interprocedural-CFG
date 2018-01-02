import com.ms.dll.Root;
import com.ms.awt.WComponentPeer;
import java.awt.Component;
import com.ms.win32.User32;
import com.ms.dll.Callback;

// 
// Decompiled by Procyon v0.5.30
// 

final class Callback_Sub1 extends Callback
{
    private volatile boolean aBoolean3596;
    private volatile int anInt3597;
    private int anInt3598;
    private boolean aBoolean3599;
    private volatile int anInt3600;
    
    Callback_Sub1() {
        this.aBoolean3596 = true;
    }
    
    final void method356(final int n, final int n2, final int n3) {
        try {
            User32.SetCursorPos(n3, n2);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final synchronized int method357(final int n, final int n2, final int n3, final int n4) {
        try {
            if (~n != ~this.anInt3597) {
                return User32.CallWindowProc(User32.GetWindowLong(n, -4), n, n2, n3, n4);
            }
            if (~n2 == 0xFFFFFFDF && (n4 & 0xFFFF) == 0x1) {
                User32.SetCursor(this.aBoolean3596 ? this.anInt3598 : 0);
                return 0;
            }
            if (n2 == 101024) {
                User32.SetCursor(this.aBoolean3596 ? this.anInt3598 : 0);
                return 0;
            }
            if (n2 == 1) {
                this.anInt3597 = 0;
                this.aBoolean3596 = true;
            }
            return User32.CallWindowProc(this.anInt3600, n, n2, n3, n4);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    final void method358(final boolean aBoolean3596, final Component component, final byte b) {
        try {
            final WComponentPeer wComponentPeer = (WComponentPeer)component.getPeer();
            if (b >= -65) {
                this.method356(-61, -37, -29);
            }
            final int topHwnd = wComponentPeer.getTopHwnd();
            if (this.anInt3597 != topHwnd || !this.aBoolean3596 != !aBoolean3596) {
                if (!this.aBoolean3599) {
                    this.anInt3598 = User32.LoadCursor(0, 32512);
                    Root.alloc(this);
                    this.aBoolean3599 = true;
                }
                if (this.anInt3597 != topHwnd) {
                    if (~this.anInt3597 != -1) {
                        this.aBoolean3596 = true;
                        User32.SendMessage(topHwnd, 101024, 0, 0);
                        synchronized (this) {
                            User32.SetWindowLong(this.anInt3597, -4, this.anInt3600);
                        }
                    }
                    synchronized (this) {
                        this.anInt3597 = topHwnd;
                        this.anInt3600 = User32.SetWindowLong(this.anInt3597, -4, this);
                    }
                }
                this.aBoolean3596 = aBoolean3596;
                User32.SendMessage(topHwnd, 101024, 0, 0);
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
