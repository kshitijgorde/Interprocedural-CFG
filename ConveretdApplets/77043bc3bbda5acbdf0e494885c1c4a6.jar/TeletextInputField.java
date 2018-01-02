import teletext.TeletextReply;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import teletext.TeletextPageCanvas;
import teletext.TeletextPage;
import java.awt.TextField;

// 
// Decompiled by Procyon v0.5.30
// 

class TeletextInputField extends TextField
{
    TeletextPage teletextPage;
    TeletextPageCanvas teletextPageCanvas;
    String hostname;
    TeletextClient teletextClient;
    
    public TeletextInputField(final String hostname, final TeletextPage teletextPage, final TeletextPageCanvas teletextPageCanvas) {
        super(40);
        this.hostname = hostname;
        this.teletextPage = teletextPage;
        this.teletextPageCanvas = teletextPageCanvas;
        this.teletextClient = new TeletextClient();
        try {
            System.out.println("Connect: " + this.hostname);
            this.teletextClient.connect(this.hostname, 23000);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(final KeyEvent keyEvent) {
                TeletextInputField.this.processKeyPressedEvent(keyEvent);
            }
            
            public void keyTyped(final KeyEvent keyEvent) {
                TeletextInputField.this.processKeyTypedEvent(keyEvent);
            }
        });
    }
    
    public void processKeyPressedEvent(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.teletextPage.asciiToCustom();
            try {
                final TeletextReply execute = this.teletextClient.execute(this.getText(), this.teletextPage.getContents());
                if (execute.hasContents()) {
                    this.teletextPage.setContents(execute.getContents());
                }
                this.setText(this.getText() + " <" + execute.getReply());
                this.selectAll();
            }
            catch (IOException ex) {}
            this.teletextPage.customToAscii();
            this.teletextPageCanvas.render();
            this.teletextPageCanvas.repaint();
        }
    }
    
    public void processKeyTypedEvent(final KeyEvent keyEvent) {
    }
}
