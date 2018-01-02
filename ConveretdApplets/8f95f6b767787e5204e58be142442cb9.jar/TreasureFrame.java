import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Label;
import java.awt.CheckboxGroup;
import java.awt.Panel;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class TreasureFrame extends Frame
{
    Treasure treasure;
    Panel panelNumDimensions;
    CheckboxGroup groupNumDim;
    Label labelGameNumber;
    TextField editGameNumber;
    Checkbox check2D;
    Checkbox check3D;
    Checkbox check4D;
    Label label1;
    Checkbox checkEuclidean;
    Button buttonOk;
    
    public TreasureFrame(final Treasure treasure) {
        super("TreasureFrame window");
        this.treasure = treasure;
        this.setLayout(new BorderLayout());
        this.resize(290, 276);
        (this.panelNumDimensions = new Panel()).setLayout(null);
        this.add(this.panelNumDimensions);
        this.panelNumDimensions.reshape(45, 112, 180, 48);
        this.groupNumDim = new CheckboxGroup();
        this.add(this.labelGameNumber = new Label("Game number"));
        this.labelGameNumber.reshape(18, 40, 117, 16);
        this.add(this.editGameNumber = new TextField(10));
        this.editGameNumber.reshape(153, 32, 108, 24);
        this.check2D = new Checkbox("2", this.groupNumDim, false);
        this.panelNumDimensions.add(this.check2D);
        this.check2D.reshape(18, 16, 36, 24);
        this.check3D = new Checkbox("3", this.groupNumDim, true);
        this.panelNumDimensions.add(this.check3D);
        this.check3D.reshape(72, 16, 36, 24);
        this.check4D = new Checkbox("4", this.groupNumDim, false);
        this.panelNumDimensions.add(this.check4D);
        this.check4D.reshape(126, 16, 36, 24);
        this.add(this.label1 = new Label("Number of Dimensions"));
        this.label1.reshape(45, 80, 180, 16);
        this.add(this.checkEuclidean = new Checkbox("Euclidean space"));
        this.checkEuclidean.reshape(45, 184, 144, 24);
        this.add(this.buttonOk = new Button("Ok"));
        this.buttonOk.reshape(108, 232, 63, 24);
        this.editGameNumber.setText("1");
        this.checkEuclidean.setState(true);
    }
    
    public synchronized void show() {
        this.move(50, 50);
        this.resize(290, 282);
        super.show();
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001 && event.target == this.buttonOk) {
            this.clickedButtonOk();
            return true;
        }
        if (event.id == 1001 && event.target == this.checkEuclidean) {
            this.clickedCheckEuclidean();
            return true;
        }
        if (event.id == 201) {
            if (this.check2D.getState()) {
                this.treasure.startGame(this.editGameNumber.getText(), this.checkEuclidean.getState(), 2);
            }
            else if (this.check3D.getState()) {
                this.treasure.startGame(this.editGameNumber.getText(), this.checkEuclidean.getState(), 3);
            }
            else {
                this.treasure.startGame(this.editGameNumber.getText(), this.checkEuclidean.getState(), 4);
            }
            this.hide();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void clickedCheckEuclidean() {
    }
    
    public void clickedButtonOk() {
        if (this.check2D.getState()) {
            this.treasure.startGame(this.editGameNumber.getText(), this.checkEuclidean.getState(), 2);
        }
        else if (this.check3D.getState()) {
            this.treasure.startGame(this.editGameNumber.getText(), this.checkEuclidean.getState(), 3);
        }
        else {
            this.treasure.startGame(this.editGameNumber.getText(), this.checkEuclidean.getState(), 4);
        }
        this.hide();
    }
}
