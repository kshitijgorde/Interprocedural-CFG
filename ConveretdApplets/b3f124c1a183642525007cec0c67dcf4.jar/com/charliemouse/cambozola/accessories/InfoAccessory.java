// 
// Decompiled by Procyon v0.5.30
// 

package com.charliemouse.cambozola.accessories;

import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.charliemouse.cambozola.shared.AppID;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.Frame;
import java.awt.Point;
import com.charliemouse.cambozola.Accessory;

public class InfoAccessory extends Accessory
{
    protected InfoFrame m_infoFrame;
    protected Point m_frameLoc;
    
    public InfoAccessory() {
        this.m_infoFrame = null;
        this.m_frameLoc = new Point(20, 20);
    }
    
    public String getName() {
        return "Information";
    }
    
    public String getDescription() {
        return "Display information on the stream";
    }
    
    public String getIconLocation() {
        return "info.gif";
    }
    
    public void actionPerformed(final Point point) {
        if (this.m_infoFrame == null) {
            this.m_infoFrame = new InfoFrame();
        }
        else {
            this.m_infoFrame.close();
        }
    }
    
    class InfoFrame extends Frame implements ActionListener, Runnable
    {
        TextField fpsValue;
        
        public InfoFrame() {
            final AppID appID = AppID.getAppID();
            this.setTitle(appID.getAppName());
            this.setLayout(new BorderLayout());
            final Panel panel = new Panel();
            panel.setLayout(new GridLayout(3, 1));
            panel.add(new Label(appID.getAppName() + " " + appID.getFullVersion(), 1));
            panel.add(new Label(appID.getCopyright(), 1));
            panel.add(new Label(appID.getLocURL(), 1));
            this.add(panel, "North");
            final Panel panel2 = new Panel();
            panel2.setLayout(new GridLayout(2, 1));
            final Panel panel3 = new Panel();
            panel3.setLayout(new FlowLayout());
            panel3.add(new Label("URL:"));
            final TextField textField = new TextField("" + InfoAccessory.this.getViewerAttributes().getStream().getStreamURL(), 30);
            textField.setEditable(false);
            panel3.add(textField);
            panel2.add(panel3);
            final Panel panel4 = new Panel();
            panel4.add(new Label("FPS:"));
            panel4.setLayout(new FlowLayout());
            (this.fpsValue = new TextField(30)).setEditable(false);
            this.updateFPS();
            panel4.add(this.fpsValue);
            panel2.add(panel4);
            this.add(panel2, "Center");
            final Button button = new Button("Close");
            button.addActionListener(this);
            final Panel panel5 = new Panel();
            panel5.setLayout(new FlowLayout());
            panel5.add(button);
            this.add(panel5, "South");
            this.pack();
            this.validate();
            if (InfoAccessory.this.m_frameLoc != null) {
                this.setLocation(InfoAccessory.this.m_frameLoc);
            }
            this.setVisible(true);
            this.addWindowListener(new WindowAdapter() {
                private final /* synthetic */ InfoFrame this$1 = this$1;
                
                public void windowClosing(final WindowEvent windowEvent) {
                    this.this$1.close();
                }
            });
            new Thread(this).start();
        }
        
        public void run() {
            do {
                this.updateFPS();
                try {
                    Thread.sleep(10000L);
                }
                catch (InterruptedException ex) {}
            } while (this.isVisible());
        }
        
        private void updateFPS() {
            String format;
            if (InfoAccessory.this.getViewerAttributes().getStream() == null) {
                format = "No image stream yet.";
            }
            else {
                format = NumberFormat.getInstance().format(InfoAccessory.this.getViewerAttributes().getStream().getFPS());
            }
            this.fpsValue.setText(format);
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            this.close();
        }
        
        public void close() {
            InfoAccessory.this.m_frameLoc = this.getLocation();
            this.setVisible(false);
            InfoAccessory.this.m_infoFrame = null;
            this.dispose();
        }
    }
}
