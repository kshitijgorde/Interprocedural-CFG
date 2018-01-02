import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import mika.graphics.G_ImageTools;
import mika.system.S_Debug;
import mika.system.S_TextReader;
import java.net.URL;
import mika.application.Mode;
import java.awt.Image;
import mika.application.Application;

// 
// Decompiled by Procyon v0.5.30
// 

public class Crossword extends Application
{
    Image m_imgBackground;
    static final int VERSION_HIGH = 1;
    static final int VERSION_LOW = 1;
    static final String INFO_STRING = "Crossword Applet v1.1\r\nCopyright (C) 2002 Mika Tammenkoski\r\nE-Mail: mika@sumea.com\r\n";
    C_Game m_Game;
    
    public Crossword() {
        Application.setMaxProgress(10);
        this.m_Game = new C_Game();
    }
    
    public String getInformation() {
        return "Crossword Applet v1.1\r\nCopyright (C) 2002 Mika Tammenkoski\r\nE-Mail: mika@sumea.com\r\n";
    }
    
    public String getName() {
        return "Crossword";
    }
    
    public int getModeCount() {
        return 1;
    }
    
    public Mode getMode(final int n) {
        return this.m_Game;
    }
    
    public Mode getActiveMode() {
        return this.m_Game;
    }
    
    public void load(final URL url) throws Exception {
        try {
            final S_TextReader s_TextReader = new S_TextReader(new URL(url, this.getConfigurationFile()));
            if (s_TextReader == null) {
                S_Debug.print("Error: Couldn't open configuration file(" + this.getConfigurationFile() + ")");
                return;
            }
            this.readLoadingBar(s_TextReader);
            final String stringValue = s_TextReader.readStringValue();
            S_Debug.print("Background image: " + stringValue);
            this.m_imgBackground = G_ImageTools.loadImage(Application.getApplet(), new URL(Application.getApplet().getCodeBase(), stringValue), stringValue);
            this.m_Game.setMainBackground(this.m_imgBackground);
            this.m_Game.load(s_TextReader);
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    public void paintBackground(final Graphics graphics) {
        if (!this.isLoadingDone()) {
            super.paintBackground(graphics);
        }
        else {
            graphics.drawImage(this.m_imgBackground, 0, 0, this);
        }
    }
    
    public void runApplication() {
        System.out.println("runApplication()");
        try {
            while (true) {
                this.m_Game.requestFocus();
                this.m_Game.run();
            }
        }
        catch (Exception ex) {
            S_Debug.print(ex);
        }
    }
}
