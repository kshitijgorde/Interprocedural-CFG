// 
// Decompiled by Procyon v0.5.30
// 

package abcynth;

import java.io.InputStream;
import java.awt.Component;
import abc.parser.TuneBook;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JApplet;

public class PlayerApplet extends JApplet
{
    private static final String DEMO_FILE_NAME = "LGtunes.abc";
    private PlayerApp m_app;
    
    public PlayerApplet() {
        this.m_app = null;
        this.m_app = new PlayerApp();
        final JButton launchDemoButton = new JButton("Click here to run demo!");
        launchDemoButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                PlayerApplet.this.m_app.setLocation((screenSize.width - PlayerApplet.this.m_app.getWidth()) / 2, (screenSize.height - PlayerApplet.this.m_app.getHeight()) / 2);
                PlayerApplet.this.m_app.setSize(700, 550);
                PlayerApplet.this.m_app.setVisible(true);
            }
        });
        try {
            final InputStream is = this.getClass().getResourceAsStream("LGtunes.abc");
            System.out.println("get ressource " + is);
            if (is != null) {
                final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                this.m_app.getTuneBookEditor().setTuneBook(new TuneBook(reader));
            }
        }
        catch (Exception ex) {}
        this.getContentPane().add(launchDemoButton);
    }
    
    public void destroy() {
        this.m_app.getPlayer().stop();
    }
}
