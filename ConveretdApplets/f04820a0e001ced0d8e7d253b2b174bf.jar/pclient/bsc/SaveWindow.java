// 
// Decompiled by Procyon v0.5.30
// 

package pclient.bsc;

import java.awt.TextArea;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.Frame;

public class SaveWindow extends Frame
{
    private BaseChat pChat;
    private String chatContent;
    
    public SaveWindow(final BaseChat pChat, final String chatContent) {
        this.pChat = pChat;
        this.chatContent = chatContent;
        this.buildUI();
        this.resize(500, 300);
        this.enableEvents(64L);
        this.setTitle(this.pChat.paraConf.title());
    }
    
    public void processWindowEvent(final WindowEvent windowEvent) {
        if (windowEvent.getID() == 201) {
            this.closeWindow();
        }
        super.processWindowEvent(windowEvent);
    }
    
    private void buildUI() {
        final Panel buildTextPanel = this.buildTextPanel();
        this.setLayout(new BorderLayout());
        this.add("Center", buildTextPanel);
    }
    
    private Panel buildTextPanel() {
        final Panel panel = new Panel();
        final Label label = new Label(" ");
        final TextArea textArea = new TextArea(this.chatContent, 5, 49, 1);
        textArea.setEditable(false);
        panel.setLayout(new BorderLayout());
        panel.add("North", label);
        panel.add("Center", textArea);
        return panel;
    }
    
    private void closeWindow() {
        this.hide();
        this.dispose();
    }
}
