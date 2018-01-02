import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class MsgBox
{
    public MsgBox(final String message) {
        this(message, "Message Box");
    }
    
    public MsgBox(final String message, final String title) {
        int width = message.length() * 6;
        if (width < 200) {
            width = 200;
        }
        Frame frame = new Frame();
        final messageBox mb = new messageBox(frame, message, title);
        mb.resize(width, 100);
        mb.show();
        frame = null;
    }
}
