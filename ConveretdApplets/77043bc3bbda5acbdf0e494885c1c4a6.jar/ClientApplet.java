import java.awt.Component;
import teletext.TeletextPageCanvas;
import teletext.TeletextPage;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ClientApplet extends Applet
{
    TeletextPage teletextPage;
    TeletextPageCanvas teletextPageCanvas;
    TeletextInputField teletextInputField;
    TeletextClient teletextClient;
    
    public void init() {
        this.teletextPage = new TeletextPage();
        this.add(this.teletextPageCanvas = new TeletextPageCanvas(this.teletextPage));
        this.add(this.teletextInputField = new TeletextInputField(this.getCodeBase().getHost(), this.teletextPage, this.teletextPageCanvas));
        this.teletextPageCanvas.render();
        this.teletextPageCanvas.repaint();
    }
}
