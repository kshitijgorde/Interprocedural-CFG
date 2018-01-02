// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Window;
import jagoclient.gui.DoActionListener;
import jagoclient.gui.ButtonAction;
import jagoclient.gui.FormTextField;
import java.awt.Component;
import jagoclient.gui.MyLabel;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import jagoclient.gui.MyPanel;
import java.awt.Frame;
import jagoclient.Global;
import java.awt.TextField;
import jagoclient.gui.CloseDialog;

class EditInformation extends CloseDialog
{
    Node N;
    TextField Black;
    TextField White;
    TextField BlackRank;
    TextField WhiteRank;
    TextField Date;
    TextField Time;
    TextField Komi;
    TextField Result;
    TextField Handicap;
    TextField GameName;
    GoFrame F;
    
    public EditInformation(final GoFrame f, final Node n) {
        super(f, Global.resourceString("Game_Information"), false);
        this.N = n;
        this.F = f;
        final MyPanel myPanel = new MyPanel();
        myPanel.setLayout(new GridLayout(0, 2));
        myPanel.add(new MyLabel(Global.resourceString("Game_Name")));
        myPanel.add(this.GameName = new FormTextField(n.getaction("GN")));
        myPanel.add(new MyLabel(Global.resourceString("Date")));
        myPanel.add(this.Date = new FormTextField(n.getaction("DT")));
        myPanel.add(new MyLabel(Global.resourceString("Black")));
        myPanel.add(this.Black = new FormTextField(n.getaction("PB")));
        myPanel.add(new MyLabel(Global.resourceString("Black_Rank")));
        myPanel.add(this.BlackRank = new FormTextField(n.getaction("BR")));
        myPanel.add(new MyLabel(Global.resourceString("White")));
        myPanel.add(this.White = new FormTextField(n.getaction("PW")));
        myPanel.add(new MyLabel(Global.resourceString("White_Rank")));
        myPanel.add(this.WhiteRank = new FormTextField(n.getaction("WR")));
        myPanel.add(new MyLabel(Global.resourceString("Result")));
        myPanel.add(this.Result = new FormTextField(n.getaction("RE")));
        myPanel.add(new MyLabel(Global.resourceString("Time")));
        myPanel.add(this.Time = new FormTextField(n.getaction("TM")));
        myPanel.add(new MyLabel(Global.resourceString("Komi")));
        myPanel.add(this.Komi = new FormTextField(n.getaction("KM")));
        myPanel.add(new MyLabel(Global.resourceString("Handicap")));
        myPanel.add(this.Handicap = new FormTextField(n.getaction("HA")));
        this.add("Center", myPanel);
        final MyPanel myPanel2 = new MyPanel();
        myPanel2.add(new ButtonAction(this, Global.resourceString("OK")));
        myPanel2.add(new ButtonAction(this, Global.resourceString("Cancel")));
        this.add("South", myPanel2);
        Global.setpacked(this, "editinformation", 350, 450);
        this.show();
    }
    
    public void doAction(final String s) {
        Global.notewindow(this, "editinformation");
        if (Global.resourceString("OK").equals(s)) {
            this.N.setaction("GN", this.GameName.getText());
            this.N.setaction("PB", this.Black.getText());
            this.N.setaction("PW", this.White.getText());
            this.N.setaction("BR", this.BlackRank.getText());
            this.N.setaction("WR", this.WhiteRank.getText());
            this.N.setaction("DT", this.Date.getText());
            this.N.setaction("TM", this.Time.getText());
            this.N.setaction("KM", this.Komi.getText());
            this.N.setaction("RE", this.Result.getText());
            this.N.setaction("HA", this.Handicap.getText());
            if (!this.GameName.getText().equals("")) {
                this.F.setTitle(this.GameName.getText());
            }
        }
        this.setVisible(false);
        this.dispose();
    }
}
