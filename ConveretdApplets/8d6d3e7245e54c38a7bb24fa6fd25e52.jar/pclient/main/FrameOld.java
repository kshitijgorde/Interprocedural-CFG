// 
// Decompiled by Procyon v0.5.30
// 

package pclient.main;

import java.awt.TextArea;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import com.pchat.sc.WindowUtil;
import java.awt.Button;
import java.applet.Applet;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class FrameOld extends Frame implements ActionListener
{
    private String infoText;
    private Applet mainApp;
    private String downloadURL;
    private Button loadButton;
    private String buttonText;
    
    public FrameOld(final String title, final String infoText, final String buttonText, final Applet mainApp, final String downloadURL) {
        this.infoText = infoText;
        this.mainApp = mainApp;
        this.downloadURL = downloadURL;
        this.buttonText = buttonText;
        if (title != null) {
            this.setTitle(title);
        }
        if (this.infoText == null) {
            this.infoText = "";
        }
        this.prepare();
    }
    
    private void prepare() {
        this.setSize(320, 180);
        this.buildGUI();
        this.enableEvents(64L);
        this.pack();
        WindowUtil.center(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.loadButton) {
            WindowUtil.loadURL(this.mainApp, this.downloadURL);
        }
    }
    
    public void processWindowEvent(final WindowEvent windowEvent) {
        if (windowEvent.getID() == 201) {
            this.setVisible(false);
            this.dispose();
        }
    }
    
    private void buildGUI() {
        this.setLayout(new BorderLayout());
        (this.loadButton = new Button(this.buttonText)).addActionListener(this);
        final Panel panel = new Panel(new FlowLayout(1));
        panel.add(this.loadButton);
        final TextArea textArea = new TextArea(this.infoText, 10, 48, 3);
        textArea.setEditable(false);
        this.add(textArea, "Center");
        this.add(panel, "South");
    }
}
