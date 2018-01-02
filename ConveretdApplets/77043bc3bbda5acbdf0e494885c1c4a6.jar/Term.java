// 
// Decompiled by Procyon v0.5.30
// 

public class Term
{
    public static void main(final String[] array) {
        final TeletextPageFrame teletextPageFrame = new TeletextPageFrame(array[0]);
        teletextPageFrame.setVisible(true);
        teletextPageFrame.pack();
        teletextPageFrame.show();
        teletextPageFrame.repaint();
    }
}
