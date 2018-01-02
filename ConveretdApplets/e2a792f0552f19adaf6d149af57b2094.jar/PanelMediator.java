import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class PanelMediator
{
    public ButtonsPanel buttonsPanel;
    public ColorPanel colorPanel;
    public URL URL_STRING;
    
    public void setButtons(final ButtonsPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
    }
    
    public void setColor(final ColorPanel colorPanel) {
        this.colorPanel = colorPanel;
    }
}
