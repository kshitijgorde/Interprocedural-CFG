import java.awt.Color;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Button;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class Com2meFWg1 extends Panel
{
    private Button scrambleButton;
    private Button originalButton;
    private Button quitButton;
    private int pieces;
    private String imageName;
    private Com2meFWg2 gameCanvas;
    private Com2meFWO gameStatus;
    
    public Com2meFWg1(final Com2meFWO gameStatus, final Image image) {
        this.gameStatus = gameStatus;
        this.gameCanvas = new Com2meFWg2(gameStatus, image);
        this.setLayout(new BorderLayout());
        this.add("Center", this.gameCanvas);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.quitButton) {
            this.gameStatus.setObject("quit");
            return true;
        }
        if (event.target != this.scrambleButton) {
            return false;
        }
        this.gameStatus.setObject("scramble");
        return true;
    }
    
    public String getImageName() {
        return this.imageName;
    }
    
    public int getPieceNumber() {
        return this.pieces;
    }
    
    public int getPiecesReady() {
        return this.gameCanvas.getPiecesReady();
    }
    
    public void setImageName(final String imageName) {
        this.imageName = imageName;
    }
    
    public void setPictureground(final Color pictureground) {
        this.gameCanvas.setPictureground(pictureground);
    }
    
    public void setPieces(final String s) {
        this.pieces = Integer.parseInt(s);
        if (this.pieces <= 3) {
            this.pieces = 3;
        }
    }
    
    public void start() {
        this.gameStatus.setObject("scramble");
    }
    
    public void startNewGame(final Image image, final Com2meFWPP[] array) {
        this.gameCanvas.setNewPieces(image, array);
    }
}
