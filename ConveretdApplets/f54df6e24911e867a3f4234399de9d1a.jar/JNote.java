import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JNote extends Applet
{
    public void init() {
        final JavaGUI javaGUI = new JavaGUI("Java NoteBook");
        javaGUI.pack();
        javaGUI.resize(640, 480);
        javaGUI.setLocation(0, 30);
        javaGUI.setResizable(false);
        javaGUI.show();
    }
}
