import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ChatApplet extends Applet
{
    static final int PORT = 53376;
    Button openBigClient;
    ChatClient bochichat;
    
    public void init() {
        (this.openBigClient = new Button("Start BochiChatClient")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChatApplet.this.bochichat = new ChatClient(ChatApplet.this.getCodeBase().getHost(), 53376, ChatApplet.this);
                ChatApplet.this.openBigClient.setVisible(false);
            }
        });
        this.setLayout(new BorderLayout());
        this.add("Center", this.openBigClient);
    }
    
    public void stop() {
        this.bochichat.closeChat(true);
    }
}
