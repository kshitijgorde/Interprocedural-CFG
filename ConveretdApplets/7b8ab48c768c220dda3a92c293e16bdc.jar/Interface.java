import java.awt.Color;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Interface
{
    final int WIDTH = 400;
    final int HEIGHT = 400;
    final int COINDIM = 64;
    final int SLOT_WIDTH = 64;
    final int SLOT_HEIGHT = 100;
    final int[] COINSLOT_X;
    final int[] COINSLOT_Y;
    final int COIN_OFFSET = 2;
    final int TABLE_X = 0;
    final int TABLE_Y = 250;
    final int TABLE_WIDTH = 400;
    final int TABLE_HEIGHT = 150;
    final int SRCSTACK = 1;
    final int SRCTABLE = 2;
    int[] CoinsD;
    int[] CoinsX;
    int[] CoinsY;
    final int EASY = 1;
    final int MEDIUM = 2;
    final int HARD = 3;
    int Mode;
    int Character;
    int Difficulty;
    int Goal;
    int Sum;
    boolean Complete;
    int Progress;
    int[] StockCoins;
    int[] Values;
    int TalkFor;
    String Say;
    int SpeechPos;
    Image imgBack;
    Graphics gFront;
    Graphics gBack;
    Image[] imgCoin;
    Image[] imgTalk;
    Image[] imgCharacters;
    Image[] imgCharactersTalk;
    Image imgCounter;
    Image imgBubble;
    Image imgD;
    int Select;
    int SelectX;
    int SelectY;
    int SelectSRC;
    Font BigFont;
    Font SmallFont;
    Applet pApplet;
    
    Interface(final Applet pApplet) {
        this.COINSLOT_X = new int[] { 0, 0, 64, 128, 192 };
        this.COINSLOT_Y = new int[] { 0, 100, 100, 100, 100 };
        this.CoinsD = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        this.CoinsX = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        this.CoinsY = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        this.Mode = 0;
        this.Character = 1;
        this.Difficulty = 0;
        this.Goal = 0;
        this.Sum = 0;
        this.Complete = false;
        this.Progress = 0;
        this.StockCoins = new int[] { 0, 0, 0, 0, 0 };
        this.Values = new int[] { 0, 1, 5, 10, 25 };
        this.TalkFor = 0;
        this.Say = "";
        this.SpeechPos = 0;
        this.imgBack = null;
        this.gFront = null;
        this.gBack = null;
        this.imgCoin = new Image[] { null, null, null, null, null };
        this.imgTalk = new Image[] { null, null };
        this.imgCharacters = new Image[] { null, null, null, null };
        this.imgCharactersTalk = new Image[] { null, null, null, null };
        this.imgCounter = null;
        this.imgBubble = null;
        this.imgD = null;
        this.Select = 0;
        this.SelectX = 0;
        this.SelectY = 0;
        this.SelectSRC = 0;
        this.BigFont = null;
        this.SmallFont = null;
        this.pApplet = pApplet;
        this.gFront = this.pApplet.getGraphics();
        this.Character = Integer.valueOf(this.pApplet.getParameter("char"));
        this.Difficulty = Integer.valueOf(this.pApplet.getParameter("diff"));
        this.BigFont = new Font("Dialog", 1, 20);
        this.SmallFont = new Font("Dialog", 1, 14);
    }
    
    public boolean Talking() {
        return this.TalkFor > 0;
    }
    
    public void LoadImages() {
        this.imgCharacters[1] = this.pApplet.getImage(this.pApplet.getDocumentBase(), "talk10.gif");
        while (this.imgCharacters[1].getHeight(null) == -1) {}
        this.imgCharacters[2] = this.pApplet.getImage(this.pApplet.getDocumentBase(), "talk20.gif");
        while (this.imgCharacters[2].getHeight(null) == -1) {}
        this.imgCharacters[3] = this.pApplet.getImage(this.pApplet.getDocumentBase(), "talk30.gif");
        while (this.imgCharacters[3].getHeight(null) == -1) {}
        this.imgCharactersTalk[1] = this.pApplet.getImage(this.pApplet.getDocumentBase(), "talk11.gif");
        while (this.imgCharactersTalk[1].getHeight(null) == -1) {}
        this.imgCharactersTalk[2] = this.pApplet.getImage(this.pApplet.getDocumentBase(), "talk21.gif");
        while (this.imgCharactersTalk[2].getHeight(null) == -1) {}
        this.imgCharactersTalk[3] = this.pApplet.getImage(this.pApplet.getDocumentBase(), "talk31.gif");
        while (this.imgCharactersTalk[3].getHeight(null) == -1) {}
        this.imgBubble = this.pApplet.getImage(this.pApplet.getDocumentBase(), "bubble.gif");
        while (this.imgBubble.getHeight(null) == -1) {}
        this.imgCounter = this.pApplet.getImage(this.pApplet.getDocumentBase(), "table.gif");
        while (this.imgCounter.getHeight(null) == -1) {}
        this.imgD = this.pApplet.getImage(this.pApplet.getDocumentBase(), "enh.gif");
        while (this.imgD.getHeight(null) == -1) {}
        for (int i = 1; i <= 4; ++i) {
            this.imgCoin[i] = this.pApplet.getImage(this.pApplet.getDocumentBase(), "c" + i + ".gif");
            while (this.imgCoin[i].getHeight(null) == -1) {}
        }
    }
    
    public void Check() {
        this.Sum = 0;
        for (int i = 1; i <= 40; ++i) {
            if (this.CoinsD[i] > 0) {
                this.Sum += this.Values[this.CoinsD[i]];
            }
        }
        if (this.Sum == this.Goal) {
            this.Complete = true;
            if (this.Progress == 10) {
                this.Talk("Nice Work! You Win!");
                this.Mode = 5;
            }
            else {
                this.Talk("Good Job!");
            }
        }
        else {
            this.Complete = false;
            this.Talk("Please try again.");
        }
    }
    
    public void CreateProblem() {
        for (boolean b = true; b; b = false) {
            this.Goal = (int)Math.round(Math.random() * 50.0 + 15 * this.Progress);
            if (this.Goal > 0) {}
        }
        if (this.Difficulty == 1 || this.Difficulty == 2) {
            for (int i = 1; i <= 4; ++i) {
                this.StockCoins[i] = 8;
            }
        }
        else {
            for (boolean b2 = true; b2; b2 = false) {
                for (int j = 1; j <= 4; ++j) {
                    this.StockCoins[j] = (int)Math.round(Math.random() * 8.0);
                }
                if (this.Possible()) {}
            }
        }
        if (this.Progress < 10) {
            ++this.Progress;
        }
        this.Talk("You need " + this.Format(this.Goal) + ".");
    }
    
    void Reset() {
        this.Complete = false;
        for (int i = 1; i <= 4; ++i) {
            this.StockCoins[i] = 0;
        }
        for (int j = 0; j <= 40; ++j) {
            this.CoinsD[j] = 0;
        }
    }
    
    boolean Possible() {
        for (int i = 0; i <= this.StockCoins[1]; ++i) {
            for (int j = 0; j <= this.StockCoins[2]; ++j) {
                for (int k = 0; k <= this.StockCoins[3]; ++k) {
                    for (int l = 0; l <= this.StockCoins[4]; ++l) {
                        if (i * this.Values[1] + j * this.Values[2] + k * this.Values[3] + l * this.Values[4] == this.Goal) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    void PlaceCoin(final int n, int n2, int n3) {
        for (int i = 1; i <= 40; ++i) {
            if (this.CoinsD[i] == 0) {
                this.CoinsD[i] = n;
                if (n2 < 32) {
                    n2 = 32;
                }
                if (n2 > 368) {
                    n2 = 368;
                }
                if (n3 < 282) {
                    n3 = 282;
                }
                if (n3 > 368) {
                    n3 = 368;
                }
                this.CoinsX[i] = n2;
                this.CoinsY[i] = n3;
                break;
            }
        }
    }
    
    void PlaceCoin(final int n) {
        for (int i = 1; i <= 40; ++i) {
            if (this.CoinsD[i] == 0) {
                this.CoinsD[i] = n;
                break;
            }
        }
    }
    
    void Talk(final String say) {
        this.Say = say;
        this.TalkFor = 10;
    }
    
    void GetCoin(final int n, final int n2) {
        int n3 = 64;
        int n4 = 0;
        for (int i = 1; i <= 40; ++i) {
            if (this.CoinsD[i] > 0) {
                final int n5 = Math.abs(this.CoinsX[i] - n) + Math.abs(this.CoinsY[i] - n2);
                if (n5 < n3) {
                    n3 = n5;
                    n4 = i;
                }
            }
        }
        this.Select = this.CoinsD[n4];
        this.CoinsD[n4] = 0;
        final int n6 = this.CoinsX[n4];
        final int n7 = this.CoinsY[n4];
        int n8;
        for (n8 = n4 + 1; this.CoinsD[n8] > 0; ++n8) {
            this.CoinsD[n8 - 1] = this.CoinsD[n8];
            this.CoinsX[n8 - 1] = this.CoinsX[n8];
            this.CoinsY[n8 - 1] = this.CoinsY[n8];
        }
        this.CoinsD[n8 - 1] = 0;
        this.CoinsX[n8 - 1] = n6;
        this.CoinsY[n8 - 1] = n7;
    }
    
    public void MoveMouse(final int selectX, final int selectY) {
        this.SelectX = selectX;
        this.SelectY = selectY;
    }
    
    public void ReleaseMouse(final int n, final int n2) {
        if (this.Mode == 4) {
            if (this.Select > 0) {
                if (n > 0 && n < 400 && n2 > 250 && n2 < 400) {
                    this.PlaceCoin(this.Select, n, n2);
                    this.Select = 0;
                }
                if (n > this.COINSLOT_X[1] && n < 256 && n2 > this.COINSLOT_Y[1] && n2 < this.COINSLOT_Y[1] + 100) {
                    final int[] stockCoins = this.StockCoins;
                    final int select = this.Select;
                    ++stockCoins[select];
                    this.Select = 0;
                }
                else {
                    if (this.SelectSRC == 2) {
                        this.PlaceCoin(this.Select);
                        this.Select = 0;
                    }
                    if (this.SelectSRC == 1) {
                        final int[] stockCoins2 = this.StockCoins;
                        final int select2 = this.Select;
                        ++stockCoins2[select2];
                        this.Select = 0;
                    }
                }
            }
            this.Update();
            if (n > 336 && n < 400 && n2 < 96 && n2 > 0) {
                this.Check();
            }
        }
    }
    
    public void PressMouse(final int n, final int n2) {
        if (this.Mode == 5) {
            this.Reset();
            this.Progress = 0;
            this.SpeechPos = 0;
            this.Mode = 1;
        }
        if (this.Mode == 4) {
            for (int i = 1; i < 5; ++i) {
                if (n > this.COINSLOT_X[i] && n < this.COINSLOT_X[i] + 64 && n2 > this.COINSLOT_Y[i] && n2 < this.COINSLOT_Y[i] + 100 && this.StockCoins[i] > 0) {
                    this.SelectSRC = 1;
                    this.Select = i;
                    final int[] stockCoins = this.StockCoins;
                    final int select = this.Select;
                    --stockCoins[select];
                }
            }
            if (n > 0 && n < 400 && n2 > 250 && n2 < 400) {
                this.SelectSRC = 2;
                this.GetCoin(n, n2);
            }
            this.Update();
            if (n > 336 && n < 400 && n2 < 96 && n2 > 0) {
                this.Check();
            }
        }
        if (this.Mode == 2) {
            boolean b = false;
            if (n2 > 128 && n2 < 224) {
                if (n > 64 && n < 128) {
                    this.Character = 1;
                    b = true;
                }
                if (n > 160 && n < 224) {
                    this.Character = 2;
                    b = true;
                }
                if (n > 256 && n < 320) {
                    this.Character = 3;
                    b = true;
                }
                if (b) {
                    this.imgTalk[0] = this.imgCharacters[this.Character];
                    this.imgTalk[1] = this.imgCharactersTalk[this.Character];
                    this.Mode = 3;
                }
            }
        }
        if (this.Mode == 1 && n > 128 && n < 256) {
            if (n2 > 150 && n2 < 175) {
                this.Difficulty = 1;
                this.Mode = 2;
            }
            if (n2 > 185 && n2 < 210) {
                this.Difficulty = 2;
                this.Mode = 2;
            }
            if (n2 > 225 && n2 < 250) {
                this.Difficulty = 3;
                this.Mode = 2;
            }
        }
    }
    
    public void ClickMouse(final int n, final int n2) {
    }
    
    public void DragMouse(final int selectX, final int selectY) {
        this.SelectX = selectX;
        this.SelectY = selectY;
    }
    
    public void Paint() {
        if (this.imgBack == null) {
            this.imgBack = this.pApplet.createImage(400, 400);
            (this.gBack = this.imgBack.getGraphics()).setFont(this.SmallFont);
        }
        if (this.Mode == 5) {
            this.gBack.setColor(Color.white);
            this.gBack.fillRect(0, 0, 400, 400);
            this.gBack.drawImage(this.imgBubble, 0, 0, null);
            if (this.TalkFor / 2.0f == this.TalkFor / 2) {
                this.gBack.drawImage(this.imgTalk[0], 336, 0, null);
            }
            else {
                this.gBack.drawImage(this.imgTalk[1], 336, 0, null);
            }
            this.gBack.setColor(Color.black);
            if (this.TalkFor > 0) {
                --this.TalkFor;
            }
            this.gBack.setFont(this.SmallFont);
            this.gBack.drawString(this.Say, 32, 32);
        }
        if (this.Mode == 3) {
            this.gBack.setColor(Color.white);
            this.gBack.fillRect(0, 0, 400, 400);
            this.gBack.drawImage(this.imgBubble, 0, 0, null);
            if (this.TalkFor / 2.0f == this.TalkFor / 2) {
                this.gBack.drawImage(this.imgTalk[0], 336, 0, null);
            }
            else {
                this.gBack.drawImage(this.imgTalk[1], 336, 0, null);
            }
            this.gBack.setColor(Color.black);
            if (this.TalkFor > 0) {
                --this.TalkFor;
            }
            if (this.TalkFor == 0) {
                switch (++this.SpeechPos) {
                    case 1: {
                        this.Talk("Welcome to Money Desk!");
                        break;
                    }
                    case 2: {
                        this.Talk("Your job is to help me count money.");
                        break;
                    }
                    case 3: {
                        this.Talk("Just drag the coins onto the desk.");
                        break;
                    }
                    case 4: {
                        this.Talk("When you think you have the right amount...");
                        break;
                    }
                    case 5: {
                        this.Talk("...Click on me!");
                        break;
                    }
                    case 6: {
                        this.Talk("Ready? Let's begin!");
                        break;
                    }
                    case 7: {
                        this.CreateProblem();
                        this.Mode = 4;
                        break;
                    }
                }
            }
            this.gBack.setFont(this.SmallFont);
            this.gBack.drawString(this.Say, 32, 32);
        }
        if (this.Mode == 4) {
            this.gBack.setColor(Color.white);
            this.gBack.fillRect(0, 0, 400, 400);
            this.gBack.drawImage(this.imgBubble, 0, 0, null);
            this.gBack.drawImage(this.imgCounter, 0, 250, null);
            for (int i = 1; i <= 40; ++i) {
                if (this.CoinsD[i] > 0) {
                    this.gBack.drawImage(this.imgCoin[this.CoinsD[i]], this.CoinsX[i] - 32, this.CoinsY[i] - 32, null);
                }
            }
            for (int j = 1; j < 5; ++j) {
                for (int k = 1; k <= this.StockCoins[j]; ++k) {
                    this.gBack.drawImage(this.imgCoin[j], this.COINSLOT_X[j] + 32 - 32, this.COINSLOT_Y[j] + 100 - 64 - k * 2, null);
                }
            }
            if (this.TalkFor / 2.0f == this.TalkFor / 2) {
                this.gBack.drawImage(this.imgTalk[0], 336, 0, null);
            }
            else {
                this.gBack.drawImage(this.imgTalk[1], 336, 0, null);
            }
            this.gBack.setColor(Color.black);
            if (this.TalkFor > 0) {
                --this.TalkFor;
            }
            if (this.Complete && this.TalkFor == 0) {
                this.Reset();
                this.CreateProblem();
            }
            this.gBack.setFont(this.SmallFont);
            this.gBack.drawString(this.Say, 32, 32);
            if (this.Select > 0) {
                this.gBack.drawImage(this.imgCoin[this.Select], this.SelectX - 32, this.SelectY - 32, null);
            }
        }
        if (this.Mode == 2) {
            this.gBack.setColor(Color.white);
            this.gBack.fillRect(0, 0, 400, 400);
            this.gBack.setColor(Color.black);
            this.gBack.setFont(this.SmallFont);
            this.gBack.drawImage(this.imgCharacters[1], 64, 128, null);
            this.gBack.drawImage(this.imgCharacters[2], 160, 128, null);
            this.gBack.drawImage(this.imgCharacters[3], 256, 128, null);
            this.gBack.drawString("Please choose a helper character:", 16, 100);
            this.gBack.setFont(this.BigFont);
            this.gBack.drawString("Money Desk", 128, 32);
            this.gBack.setFont(this.SmallFont);
            this.gBack.drawString("Copyright© 2000 Peter Baldwin", 16, 375);
        }
        if (this.Mode == 1) {
            this.gBack.setColor(Color.white);
            this.gBack.fillRect(0, 0, 400, 400);
            this.gBack.setColor(Color.black);
            this.gBack.setFont(this.SmallFont);
            this.gBack.drawImage(this.imgD, 136, 150, null);
            this.gBack.drawString("Please choose a difficulty level:", 16, 100);
            this.gBack.setFont(this.BigFont);
            this.gBack.drawString("Money Desk", 128, 32);
            this.gBack.setFont(this.SmallFont);
            this.gBack.drawString("Copyright© 2000 Peter Baldwin", 16, 375);
        }
        this.gFront.drawImage(this.imgBack, 0, 0, null);
    }
    
    public void setCoinImg(final Image image, final int n) {
        this.imgCoin[n] = image;
    }
    
    public void setCounterImg() {
    }
    
    public void Update() {
        if (!this.Complete) {
            this.Sum = 0;
            for (int i = 1; i <= 40; ++i) {
                if (this.CoinsD[i] > 0) {
                    this.Sum += this.Values[this.CoinsD[i]];
                }
            }
            if (this.Difficulty == 1) {
                this.Talk("You need " + this.Format(this.Goal) + ".  You have " + this.Format(this.Sum) + ".");
            }
            else {
                this.Say = "You need " + this.Format(this.Goal) + ".";
            }
        }
    }
    
    public String Format(final int n) {
        if (n < 100) {
            return n + " cents";
        }
        final String value = String.valueOf(n / 100.0f);
        if (value.length() != 4) {
            return "$" + value + "0";
        }
        return "$" + value;
    }
    
    public void Start() {
        this.Mode = 1;
    }
}
