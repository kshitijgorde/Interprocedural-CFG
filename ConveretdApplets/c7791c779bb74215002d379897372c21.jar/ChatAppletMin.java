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

public class ChatAppletMin extends Applet
{
    static final int PORT = 53376;
    MinChatClient minchat;
    Button openMinClient;
    
    public void init() {
        (this.openMinClient = new Button("Start Mini BochiChatClient")).addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                ChatAppletMin.this.minchat = new MinChatClient(ChatAppletMin.this.getCodeBase().getHost(), 53376, ChatAppletMin.this);
                ChatAppletMin.this.openMinClient.setVisible(false);
            }
        });
        this.setLayout(new BorderLayout());
        this.add("Center", this.openMinClient);
    }
    
    public void stop() {
        this.minchat.closeChat(true);
    }
}
