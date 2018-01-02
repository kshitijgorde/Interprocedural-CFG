// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.io.IOException;
import javax.swing.UIManager;

public class Matt
{
    private String unOrnamentedTuneFile;
    private String ornamentedTuneFile;
    private String learnedFile;
    private ODCFTranscriber transcriber;
    private MattGuiNB mattGui;
    private static Matt _instance;
    public static final String PATH = "c:\\Users\\Bryan Duggan\\Documents\\Projects\\PhD\\";
    
    public static Matt instance() {
        if (Matt._instance == null) {
            Matt._instance = new Matt();
        }
        return Matt._instance;
    }
    
    public Matt() {
        this.transcriber = new ODCFTranscriber();
        (this.mattGui = MattGuiNB.instance()).setTranscriber(this.transcriber);
    }
    
    public static void main(final String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println("Error setting native LAF: " + e);
        }
        final Matt matt = instance();
        final String test = "ab[AA]cd[]ef[BBB]g";
        System.out.println(test);
        if (MattProperties.getString("mode").equals("server")) {
            final MattServer ms = new MattServer();
            ms.start();
        }
        else {
            matt.showGui();
        }
    }
    
    public String getUnOrnamentedTuneFile() {
        return this.unOrnamentedTuneFile;
    }
    
    public void setUnOrnamentedTuneFile(final String unOrnamentedTuneFile) {
        this.unOrnamentedTuneFile = unOrnamentedTuneFile;
    }
    
    public String getOrnamentedTuneFile() {
        return this.ornamentedTuneFile;
    }
    
    public void setOrnamentedTuneFile(final String ornamentedTuneFile) {
        this.ornamentedTuneFile = ornamentedTuneFile;
    }
    
    public String getLearnedFile() {
        return this.learnedFile;
    }
    
    public void setLearnedFile(final String learnedFile) {
        this.learnedFile = learnedFile;
    }
    
    public void showGui() throws IOException {
        this.mattGui.setMatt(this);
        this.mattGui.setVisible(true);
        this.mattGui.setTranscriber(this.transcriber);
        this.transcriber.setGui(this.mattGui);
        this.getTranscriber().setInputFile("c:\\Users\\Bryan Duggan\\Documents\\Projects\\PhD\\audio\\test\\D.wav");
    }
    
    public ODCFTranscriber getTranscriber() {
        return this.transcriber;
    }
    
    public void setTranscriber(final ODCFTranscriber transcriber) {
        this.transcriber = transcriber;
    }
    
    static {
        Matt._instance = null;
    }
}
