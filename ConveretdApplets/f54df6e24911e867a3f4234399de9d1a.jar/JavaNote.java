// 
// Decompiled by Procyon v0.5.30
// 

class JavaNote
{
    public static void main(final String[] args) {
        final JavaGUI dial = new JavaGUI("Java NoteBook");
        dial.pack();
        dial.resize(640, 480);
        dial.setLocation(0, 30);
        dial.setResizable(false);
        dial.show();
    }
}
