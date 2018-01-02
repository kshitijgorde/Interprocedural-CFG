import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class CardPanel extends Panel
{
    public static final int CARDHEIGHT = 96;
    public static final int CARDWIDTH = 71;
    public static final int FRONT = 1;
    public static final int BACK = 2;
    public static final int HIDE = 3;
    private static final int rankLeft = 1;
    private static final int rankTop = 4;
    private static final int rankHeight = 14;
    private static final short[][] rankPixels;
    private static final int suitLeft = 1;
    private static final int suitTop = 18;
    private static final int suitHeight = 10;
    private static final short[][] suitPixels;
    private static final int faceWidth = 47;
    private static final int faceHeight = 37;
    private static final int faceLeft = 12;
    private static final int faceTop = 11;
    private static final byte[][] pipsLeft;
    private static final byte[][] pipsTop;
    private static final int pipHeight = 15;
    private static final int[][] pipPixels;
    private static final int wildMarkHeight = 35;
    private static final short[] wildMark;
    private static final int wildMarkXOffset = 54;
    private static final int wildMarkYOffset = 2;
    private Card card;
    private int cardWidth;
    private int cardHeight;
    private int status;
    private Image cardImage;
    private Graphics cardGraphics;
    private boolean wild;
    private static Image facesImage;
    private static Image cardBack;
    private static Image jokerImage;
    private boolean loaded;
    private Image sourceImg;
    private int destLeft;
    private int destTop;
    private int destWidth;
    private int destHeight;
    private int sourceLeft;
    private int sourceTop;
    private int sourceWidth;
    private int sourceHeight;
    
    public CardPanel(final int n, final int n2, final Card card, final int status) {
        this.wild = false;
        this.setLocation(n, n2);
        this.cardWidth = 71;
        this.cardHeight = 96;
        this.setSize(this.cardWidth, this.cardHeight);
        this.card = card;
        this.status = status;
        this.setVisible(false);
    }
    
    public void addNotify() {
        super.addNotify();
        this.cardImage = this.createImage(this.cardWidth, this.cardHeight);
        if (this.status == 3) {
            this.setVisible(false);
        }
        if (this.status == 1) {
            this.showFront();
        }
        if (this.status == 2) {
            this.showBack();
        }
    }
    
    public void removeNotify() {
        super.removeNotify();
        this.cardImage = null;
    }
    
    public static void setCardsImage(final Image facesImage, final Image cardBack, final Image jokerImage, final Component component) {
        CardPanel.facesImage = facesImage;
        CardPanel.cardBack = cardBack;
        CardPanel.jokerImage = jokerImage;
        final MediaTracker mediaTracker = new MediaTracker(component);
        mediaTracker.addImage(CardPanel.facesImage, 1);
        mediaTracker.addImage(CardPanel.cardBack, 2);
        mediaTracker.addImage(CardPanel.jokerImage, 3);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        if (mediaTracker.isErrorID(1)) {
            CardPanel.facesImage = null;
        }
        if (mediaTracker.isErrorID(2)) {
            CardPanel.cardBack = null;
        }
        if (mediaTracker.isErrorID(3)) {
            CardPanel.jokerImage = null;
        }
    }
    
    public Card getCard() {
        return this.card;
    }
    
    public void setCard(final Card card) {
        this.card.setCard(card.getRank(), card.getSuit());
        if (this.status == 3) {
            this.setVisible(false);
        }
        if (this.status == 1) {
            this.showFront();
        }
        if (this.status == 2) {
            this.showBack();
        }
    }
    
    public void showFront() {
        if (this.card.isJoker()) {
            this.drawJoker();
        }
        else {
            if (this.cardImage == null) {
                return;
            }
            this.cardGraphics = this.cardImage.getGraphics();
            this.drawBackground();
            this.drawBorder();
            this.drawRank();
            this.drawSuit();
            if (this.card.getRank() > 10) {
                this.drawFace();
            }
            else {
                this.drawPips();
            }
            if (this.wild) {
                this.drawWild();
            }
        }
        this.setVisible(true);
        this.status = 1;
        this.cardGraphics = null;
        this.update(this.getGraphics());
    }
    
    public void markWild() {
        this.wild = true;
        if (this.status == 1) {
            this.showFront();
        }
    }
    
    public void unMarkWild() {
        this.wild = false;
        if (this.status == 1) {
            this.showFront();
        }
    }
    
    private void drawBackground() {
        this.cardGraphics.setColor(Color.white);
        this.cardGraphics.fillRect(0, 0, this.cardWidth - 1, this.cardHeight - 1);
    }
    
    private void drawBorder() {
        this.cardGraphics.setColor(Color.black);
        this.cardGraphics.drawRect(0, 0, this.cardWidth - 1, this.cardHeight - 1);
    }
    
    private void drawRank() {
        this.cardGraphics.setColor((this.card.getSuit() == 4 || this.card.getSuit() == 1) ? Color.black : Color.red);
        for (int i = 0; i < 14; ++i) {
            for (int j = 15; j >= 0; --j) {
                if ((1 << j & CardPanel.rankPixels[this.card.getRank() - 1][i]) != 0x0) {
                    final int n = 16 - j;
                    final int n2 = 4 + i;
                    this.cardGraphics.drawLine(n, n2, n, n2);
                    final int n3 = 70 - n;
                    final int n4 = 95 - n2;
                    this.cardGraphics.drawLine(n3, n4, n3, n4);
                }
            }
        }
    }
    
    private void drawSuit() {
        this.cardGraphics.setColor((this.card.getSuit() == 4 || this.card.getSuit() == 1) ? Color.black : Color.red);
        for (int i = 0; i < 10; ++i) {
            for (int j = 15; j >= 0; --j) {
                if ((1 << j & CardPanel.suitPixels[this.card.getSuit() - 1][i]) != 0x0) {
                    final int n = 16 - j;
                    final int n2 = 18 + i;
                    this.cardGraphics.drawLine(n, n2, n, n2);
                    final int n3 = 70 - n;
                    final int n4 = 95 - n2;
                    this.cardGraphics.drawLine(n3, n4, n3, n4);
                }
            }
        }
    }
    
    private synchronized boolean drawCardImage() {
        if (this.sourceImg == null) {
            return false;
        }
        this.cardGraphics.drawImage(this.sourceImg, this.destLeft, this.destTop, this.destLeft + this.destWidth, this.destTop + this.destHeight, this.sourceLeft, this.sourceTop, this.sourceLeft + this.sourceWidth, this.sourceTop + this.sourceHeight, null);
        return true;
    }
    
    private void drawFace() {
        this.sourceImg = CardPanel.facesImage;
        this.sourceLeft = 47 * (this.card.getRank() - 11);
        this.sourceTop = 37 * (this.card.getSuit() - 1);
        this.sourceWidth = 47;
        this.sourceHeight = 37;
        this.destLeft = 12;
        this.destTop = 11;
        this.destWidth = 47;
        this.destHeight = 37;
        this.drawCardImage();
        this.sourceLeft += 47;
        this.sourceTop += 37;
        this.sourceWidth = -47;
        this.sourceHeight = -37;
        this.destTop = 48;
        this.drawCardImage();
    }
    
    private void drawPips() {
        for (int i = 0; i < 10; ++i) {
            if (CardPanel.pipsLeft[this.card.getRank() - 1][i] != 0) {
                if (i < 5) {
                    this.drawOnePip(CardPanel.pipsLeft[this.card.getRank() - 1][i], CardPanel.pipsTop[this.card.getRank() - 1][i]);
                }
                else {
                    this.drawOnePipUpsidedown(CardPanel.pipsLeft[this.card.getRank() - 1][i], CardPanel.pipsTop[this.card.getRank() - 1][i]);
                }
            }
        }
    }
    
    private void drawOnePip(final int n, final int n2) {
        this.cardGraphics.setColor((this.card.getSuit() == 4 || this.card.getSuit() == 1) ? Color.black : Color.red);
        for (int i = 0; i < 15; ++i) {
            for (int j = 15; j >= 0; --j) {
                if ((1 << j & CardPanel.pipPixels[this.card.getSuit() - 1][i]) != 0x0) {
                    final int n3 = n + 15 - j;
                    final int n4 = n2 + i;
                    this.cardGraphics.drawLine(n3, n4, n3, n4);
                }
            }
        }
    }
    
    private void drawOnePipUpsidedown(final int n, final int n2) {
        this.cardGraphics.setColor((this.card.getSuit() == 4 || this.card.getSuit() == 1) ? Color.black : Color.red);
        for (int i = 0; i < 15; ++i) {
            for (int j = 15; j >= 0; --j) {
                if ((1 << j & CardPanel.pipPixels[this.card.getSuit() - 1][i]) != 0x0) {
                    final int n3 = 71 - (n + 15 - j);
                    final int n4 = 96 - (n2 + i);
                    this.cardGraphics.drawLine(n3, n4, n3, n4);
                }
            }
        }
    }
    
    private void drawCardPixel(final int n, final int n2) {
        this.cardGraphics.drawLine(n, n2, n, n2);
    }
    
    private Color getCardColor() {
        if (this.card.getSuit() == 4 || this.card.getSuit() == 1) {
            return Color.black;
        }
        return Color.red;
    }
    
    private void drawWild() {
        this.cardGraphics.setColor(Color.red);
        for (int i = 0; i < 35; ++i) {
            for (int j = 15; j >= 0; --j) {
                if ((1 << j & CardPanel.wildMark[i]) != 0x0) {
                    final int n = 69 - j;
                    final int n2 = 2 + i;
                    this.cardGraphics.drawLine(n, n2, n, n2);
                    final int n3 = this.cardWidth - 1 - (69 - j);
                    final int n4 = this.cardHeight - 1 - (2 + i);
                    this.cardGraphics.drawLine(n3, n4, n3, n4);
                }
            }
        }
    }
    
    public void showBack() {
        if (this.cardImage == null) {
            return;
        }
        this.drawBack();
        this.setVisible(true);
        this.status = 2;
        this.update(this.getGraphics());
    }
    
    private void drawBack() {
        if (this.cardImage == null) {
            return;
        }
        this.cardGraphics = this.cardImage.getGraphics();
        this.sourceImg = CardPanel.cardBack;
        final boolean b = false;
        this.destLeft = (b ? 1 : 0);
        this.sourceLeft = (b ? 1 : 0);
        final boolean b2 = false;
        this.destTop = (b2 ? 1 : 0);
        this.sourceTop = (b2 ? 1 : 0);
        final int cardWidth = this.cardWidth;
        this.destWidth = cardWidth;
        this.sourceWidth = cardWidth;
        final int cardHeight = this.cardHeight;
        this.destHeight = cardHeight;
        this.sourceHeight = cardHeight;
        if (!this.drawCardImage()) {
            this.cardGraphics.setColor(new Color(128, 0, 0));
            this.cardGraphics.fillRect(0, 0, this.cardWidth, this.cardHeight);
            this.drawBorder();
        }
    }
    
    private void drawJoker() {
        if (this.cardImage == null) {
            return;
        }
        this.cardGraphics = this.cardImage.getGraphics();
        this.sourceImg = CardPanel.jokerImage;
        final boolean b = false;
        this.destLeft = (b ? 1 : 0);
        this.sourceLeft = (b ? 1 : 0);
        final boolean b2 = false;
        this.destTop = (b2 ? 1 : 0);
        this.sourceTop = (b2 ? 1 : 0);
        final int cardWidth = this.cardWidth;
        this.destWidth = cardWidth;
        this.sourceWidth = cardWidth;
        final int cardHeight = this.cardHeight;
        this.destHeight = cardHeight;
        this.sourceHeight = cardHeight;
        if (!this.drawCardImage()) {
            this.drawBackground();
            this.drawBorder();
            this.cardGraphics.drawString("Joker", 25, 50);
        }
    }
    
    public void hideCard() {
        this.cardImage = null;
        this.setVisible(false);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        graphics.drawImage(this.cardImage, 0, 0, this);
    }
    
    public void setX(final int n) {
        this.setLocation(n, this.getY());
    }
    
    public void setY(final int n) {
        this.setLocation(this.getX(), n);
    }
    
    public int getX() {
        return this.getLocation().x;
    }
    
    public int getY() {
        return this.getLocation().y;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.getGraphics().drawImage(this.cardImage, 0, 0, null);
            return false;
        }
        return true;
    }
    
    static {
        rankPixels = new short[][] { { 512, 512, 1792, 1792, 3456, 3456, 3456, 8128, 16352, 12384, 30960, 30960, 0, 0 }, { 3968, 8128, 6336, 192, 448, 896, 1792, 3584, 7360, 6336, 8128, 8128, 0, 0 }, { 8128, 8128, 6528, 768, 1536, 3968, 4032, 192, 192, 6336, 8128, 3968, 0, 0 }, { 384, 896, 1920, 3968, 7552, 14720, 16352, 16352, 384, 384, 960, 960, 0, 0 }, { 8128, 8128, 6144, 6144, 8064, 8128, 192, 192, 6336, 6336, 8128, 3968, 0, 0 }, { 1920, 3968, 7168, 6144, 8064, 8128, 6336, 6336, 6336, 6336, 8128, 3968, 0, 0 }, { 8128, 8128, 6336, 384, 384, 768, 768, 768, 1536, 1536, 1536, 1536, 0, 0 }, { 3968, 8128, 6336, 6336, 6336, 3968, 8128, 6336, 6336, 6336, 8128, 3968, 0, 0 }, { 3968, 8128, 6336, 6336, 6336, 6336, 8128, 4032, 192, 448, 3968, 3840, 0, 0 }, { 13248, 14304, 13920, 13920, 13920, 13920, 13920, 13920, 13920, 13920, 14304, 13248, 0, 0 }, { 1920, 1920, 768, 768, 768, 768, 768, 768, 13056, 13056, 16128, 7680, 0, 0 }, { 7936, 16256, 12672, 12672, 12672, 12672, 12672, 12672, 12672, 12672, 16256, 7936, 896, 384 }, { 31680, 31680, 13056, 13824, 15360, 14336, 15360, 13824, 13056, 12672, 31680, 31680, 0, 0 } };
        suitPixels = new short[][] { { 3584, 7936, 7936, 11904, 32704, 32704, 32704, 13696, 1024, 3584 }, { 1024, 3584, 7936, 16256, 16256, 16256, 7936, 3584, 1024, 0 }, { 0, 15232, 31680, 32704, 32704, 32704, 16256, 7936, 3584, 1024 }, { 1024, 3584, 7936, 16256, 32704, 32704, 32704, 13696, 3584, 3584 } };
        final byte[][] pipsLeft2 = new byte[10][];
        final int n = 0;
        final byte[] array = new byte[10];
        array[0] = 28;
        pipsLeft2[n] = array;
        pipsLeft2[1] = new byte[] { 28, 0, 0, 0, 0, 28, 0, 0, 0, 0 };
        pipsLeft2[2] = new byte[] { 28, 28, 0, 0, 0, 28, 0, 0, 0, 0 };
        pipsLeft2[3] = new byte[] { 16, 41, 0, 0, 0, 16, 41, 0, 0, 0 };
        pipsLeft2[4] = new byte[] { 16, 41, 28, 0, 0, 16, 41, 0, 0, 0 };
        pipsLeft2[5] = new byte[] { 16, 41, 16, 41, 0, 16, 41, 0, 0, 0 };
        pipsLeft2[6] = new byte[] { 16, 41, 16, 41, 28, 16, 41, 0, 0, 0 };
        pipsLeft2[7] = new byte[] { 16, 41, 16, 41, 0, 16, 41, 16, 41, 0 };
        pipsLeft2[8] = new byte[] { 16, 41, 16, 41, 28, 16, 41, 16, 41, 0 };
        pipsLeft2[9] = new byte[] { 16, 41, 16, 41, 28, 16, 41, 16, 41, 28 };
        pipsLeft = pipsLeft2;
        final byte[][] pipsTop2 = new byte[10][];
        final int n2 = 0;
        final byte[] array2 = new byte[10];
        array2[0] = 41;
        pipsTop2[n2] = array2;
        pipsTop2[1] = new byte[] { 10, 0, 0, 0, 0, 10, 0, 0, 0, 0 };
        pipsTop2[2] = new byte[] { 10, 41, 0, 0, 0, 10, 0, 0, 0, 0 };
        pipsTop2[3] = new byte[] { 10, 10, 0, 0, 0, 10, 10, 0, 0, 0 };
        pipsTop2[4] = new byte[] { 10, 10, 41, 0, 0, 10, 10, 0, 0, 0 };
        pipsTop2[5] = new byte[] { 10, 10, 41, 41, 0, 10, 10, 0, 0, 0 };
        pipsTop2[6] = new byte[] { 10, 10, 41, 41, 27, 10, 10, 0, 0, 0 };
        pipsTop2[7] = new byte[] { 10, 10, 31, 31, 0, 10, 10, 31, 31, 0 };
        pipsTop2[8] = new byte[] { 10, 10, 30, 30, 40, 10, 10, 30, 30, 0 };
        pipsTop2[9] = new byte[] { 10, 10, 31, 31, 21, 10, 10, 31, 31, 21 };
        pipsTop = pipsTop2;
        pipPixels = new int[][] { { 896, 1984, 4064, 4064, 4064, 1984, 15288, 32764, 65534, 65534, 65534, 32124, 14648, 896, 1984 }, { 256, 896, 1984, 4064, 8176, 8176, 16376, 32764, 16376, 8176, 8176, 4064, 1984, 896, 256 }, { 15480, 32508, 65278, 65534, 65534, 65534, 65534, 32764, 32764, 16376, 8176, 4064, 1984, 896, 256 }, { 256, 896, 1984, 4064, 8176, 16376, 32764, 32764, 65534, 65534, 65534, 64894, 31036, 896, 1984 } };
        wildMark = new short[] { 15934, 7218, 3698, 3708, 1980, 1848, 528, 528, 0, 992, 384, 384, 384, 384, 384, 384, 992, 0, 1984, 896, 896, 952, 932, 932, 908, 2040, 0, 2016, 920, 904, 900, 900, 900, 908, 2040 };
    }
}
