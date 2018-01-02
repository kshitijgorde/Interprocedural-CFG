import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.Component;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.Button;
import java.awt.event.WindowListener;
import java.awt.event.ActionListener;
import java.awt.Dialog;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class MsgDlgAlarm extends Frame
{
    static final String BTN_STOP_ALARM = "Stop Alarm";
    static final String MSG_STOP_ALARM = "Press the \"Stop Alarm\" button to shut alarm";
    RCLoadAudio audio;
    
    MsgDlgAlarm(final RCLoadAudio audio) {
        this.audio = audio;
        new _MsgDlgAlarm(this);
    }
    
    class _MsgDlgAlarm extends Dialog implements ActionListener, WindowListener
    {
        private boolean activated;
        private Button btnClose;
        
        _MsgDlgAlarm(final Frame frame) {
            super(frame, "Jtimer", true);
            this.setVisible(true);
        }
        
        public void windowOpened(final WindowEvent windowEvent) {
            if (MsgDlgAlarm.this.audio != null) {
                MsgDlgAlarm.this.audio.loop();
            }
        }
        
        public void windowDeactivated(final WindowEvent windowEvent) {
            if (MsgDlgAlarm.this.audio != null) {
                MsgDlgAlarm.this.audio.stop();
            }
        }
        
        public void windowActivated(final WindowEvent windowEvent) {
            if (!this.activated) {
                this.activated = true;
                this.btnClose.requestFocus();
            }
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            this.setVisible(false);
            this.dispose();
        }
        
        public void windowClosed(final WindowEvent windowEvent) {
        }
        
        public void windowIconified(final WindowEvent windowEvent) {
        }
        
        public void windowDeiconified(final WindowEvent windowEvent) {
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (actionEvent.getSource() == this.btnClose) {
                this.setVisible(false);
            }
        }
        
        public void addNotify() {
            super.addNotify();
            final TextArea textArea = new TextArea("Press the \"Stop Alarm\" button to shut alarm", 3, "Press the \"Stop Alarm\" button to shut alarm".length());
            textArea.setEditable(false);
            this.add("Center", textArea);
            (this.btnClose = new Button("Stop Alarm")).addActionListener(this);
            final Panel panel = new Panel();
            panel.setBackground(Color.gray);
            panel.setLayout(new FlowLayout(1, 5, 10));
            panel.add(this.btnClose);
            this.add("South", panel);
            this.addWindowListener(this);
            this.pack();
            this.centerDlg();
        }
        
        void centerDlg() {
            final Dimension screenSize = this.getToolkit().getScreenSize();
            final Rectangle bounds = this.getBounds();
            this.setLocation((screenSize.width - bounds.width) / 2, (screenSize.height - bounds.height) / 2);
        }
    }
}
