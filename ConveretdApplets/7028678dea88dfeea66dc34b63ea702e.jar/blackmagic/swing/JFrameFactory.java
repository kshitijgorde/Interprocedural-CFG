// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

public final class JFrameFactory
{
    public static final int BASIC = 0;
    public static final int USER_NAMEABLE = 1;
    static /* synthetic */ Class class$blackmagic$swing$JFrameFactory;
    
    public static JFrame getCloseableJFrame(final String s, final int n) {
        final JFrame baseFrame = getBaseFrame(s, n);
        baseFrame.setDefaultCloseOperation(2);
        return baseFrame;
    }
    
    public static JFrame getSystemExitJFrame(final String s, final int n) {
        final JFrame closeableJFrame = getCloseableJFrame(s, n);
        closeableJFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        return closeableJFrame;
    }
    
    private static JFrame getBaseFrame(final String s, final int n) {
        assert n >= 0 && n <= 1 : "Invalid Frame Type passed";
        JFrame frame = null;
        switch (n) {
            case 1: {
                final JUserNameableFrame userNameableFrame = new JUserNameableFrame();
                userNameableFrame.setBaseTitle(s);
                frame = userNameableFrame;
                break;
            }
            default: {
                frame = new JFrame();
                frame.setTitle(s);
                break;
            }
        }
        return frame;
    }
    
    public static void setMinSizeToCurrent(final JFrame frame) {
        frame.addComponentListener(new ComponentAdapter() {
            private final /* synthetic */ Dimension val$minimumSize = frame.getSize();
            
            public void componentResized(final ComponentEvent componentEvent) {
                frame.setSize(Math.max(this.val$minimumSize.width, frame.getSize().width), Math.max(this.val$minimumSize.height, frame.getSize().height));
            }
        });
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        $assertionsDisabled = !((JFrameFactory.class$blackmagic$swing$JFrameFactory == null) ? (JFrameFactory.class$blackmagic$swing$JFrameFactory = class$("blackmagic.swing.JFrameFactory")) : JFrameFactory.class$blackmagic$swing$JFrameFactory).desiredAssertionStatus();
    }
}
