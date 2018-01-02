import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Component;
import teletext.TeletextPageCanvas;
import teletext.TeletextPage;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class TeletextPageFrame extends Frame
{
    TeletextPage teletextPage;
    String fileName;
    
    public TeletextPageFrame(final String fileName) {
        super("TeletextPageFrame");
        this.fileName = fileName;
        (this.teletextPage = new TeletextPage()).read(this.fileName);
        this.add(new TeletextPageCanvas(this.teletextPage));
        if (this == null) {
            throw null;
        }
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent windowEvent) {
                TeletextPageFrame.this.teletextPage.write(TeletextPageFrame.this.fileName);
                System.exit(0);
            }
            
            {
                this.constructor$0(TeletextPageFrame.this);
            }
            
            private final void constructor$0(final TeletextPageFrame teletextPageFrame) {
            }
        });
    }
}
