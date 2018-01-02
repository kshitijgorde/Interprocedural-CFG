import java.awt.Font;
import java.awt.Label;
import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.LayoutManager;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Runic extends Applet
{
    Thread canvasThread;
    RunicCanvas canvas1;
    
    public void init() {
        this.setLayout(null);
        this.setSize(474, 374);
        try {
            final String lowerCase = this.getDocumentBase().getProtocol().toLowerCase();
            try {
                this.getAppletContext().showDocument(new URL("http://www.4webgames.com/gemgame/popunder.php?host=" + URLEncoder.encode(this.getDocumentBase().toString())), "_new");
            }
            catch (Exception ex) {}
            if (!lowerCase.equals("file")) {
                (this.canvas1 = new RunicCanvas(this)).setBounds(0, 0, 474, 374);
                this.add(this.canvas1);
                (this.canvasThread = new Thread(this.canvas1)).start();
                return;
            }
            this.setBackground(Color.black);
            final Label label = new Label("This version not intended for desktop play.", 1);
            label.setBounds(0, 70, 388, 30);
            label.setFont(new Font("Serif", 1, 16));
            label.setForeground(new Color(16756655));
            label.setVisible(true);
            this.add(label);
            final Label label2 = new Label("A Windows version can be found at", 1);
            label2.setBounds(0, 100, 388, 30);
            label2.setFont(new Font("Serif", 1, 16));
            label2.setForeground(new Color(16756655));
            label2.setVisible(true);
            this.add(label2);
            final Label label3 = new Label("http://www.4webgames.com", 1);
            label3.setBounds(0, 150, 388, 30);
            label3.setFont(new Font("Serif", 1, 16));
            label3.setForeground(new Color(16756655));
            label3.setVisible(true);
            this.add(label3);
        }
        catch (Exception ex2) {}
    }
    
    public void destroy() {
        this.canvasThread.stop();
    }
}
