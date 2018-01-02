import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpaceBox extends AbstractBox
{
    private int type;
    private String sWidth;
    private char space;
    private int defaultWidth;
    
    public SpaceBox(final int type) {
        this.defaultWidth = 0;
        if (type < 0 || type >= 5) {
            throw new IllegalArgumentException();
        }
        this.type = type;
    }
    
    public SpaceBox(final char space) {
        this(4);
        this.space = space;
    }
    
    public final void enCalculRect(final BoxComponent boxComponent) {
        super.height = this.em();
        super.baseline = this.em(0.75f);
        if (this.type == 1) {
            super.width = 0;
        }
        else if (this.type == 2) {
            super.width = Length.toUserUnit(this.sWidth, this.em(), boxComponent.getDPI());
        }
        else if (this.type == 4) {
            if (this.space == '\u2007') {
                super.width = this.em("8");
            }
            else if (this.space == '\u2008') {
                super.width = this.em(",") + this.em(0.1f);
            }
            else if (this.space == '\u2009') {
                super.width = 1 * (this.em(",") + this.em(0.1f)) / 2;
            }
            else if (this.space == '\u200a') {
                super.width = (this.em(",") + this.em(0.1f)) / 4;
            }
            else if (this.space == '\ue000') {
                super.width = this.em(-0.2f);
            }
            else if (this.space >= '\u2061' && this.space <= '\u2064') {
                super.width = 0;
            }
        }
        else {
            super.width = this.em(" ");
        }
        this.defaultWidth = super.width;
    }
    
    public static final void restoreBoxWidth(final AbstractBox abstractBox) {
        if (abstractBox instanceof SpaceBox) {
            final SpaceBox spaceBox = (SpaceBox)abstractBox;
            spaceBox.width = spaceBox.defaultWidth;
        }
    }
    
    public static final int getBoxType(final AbstractBox abstractBox) {
        if (abstractBox instanceof SpaceBox) {
            return ((SpaceBox)abstractBox).type;
        }
        return 5;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1 && !this.isStyle(128)) {
            if (this.type == 0 || this.type == 4 || this.type == 1) {
                return "mo";
            }
            if (this.type == 2) {
                return "mspace";
            }
        }
        return null;
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            boxScripting.appendScript("\\space");
        }
        if (boxScripting.scriptMode == 1) {
            if (this.isStyle(128)) {
                if (this.type == 0) {
                    if (this.isNextBrotherASpace() || super.p_en_pare == 0) {
                        boxScripting.appendText(" ");
                    }
                    else {
                        boxScripting.appendText(" ");
                    }
                }
                else {
                    boxScripting.openTag("br");
                    boxScripting.closeTag();
                }
            }
            else {
                if (this.type == 0) {
                    boxScripting.appendText(" ");
                }
                if (this.type == 4) {
                    boxScripting.appendText("" + this.space);
                }
                if (this.type == 1) {
                    boxScripting.appendText("\u2028");
                }
                if (this.type == 2) {}
            }
        }
        if (boxScripting.scriptMode == 2) {
            if (this.type == 0) {
                boxScripting.appendText(" ");
            }
        }
        else if (boxScripting.scriptMode == -1 && this.type == 0) {
            boxScripting.appendText(" ");
        }
    }
    
    private boolean isNextBrotherASpace() {
        if (this.getParentBox() != null) {
            final int n = super.p_en_pare + 1;
            if (n < this.getParentBox().nfills) {
                return getBoxType(this.getParentBox().fill[n]) == 0;
            }
        }
        return false;
    }
    
    public static final SpaceBox createBox(final String s) {
        if (s.equals(" ") || s.equals(" ")) {
            return new SpaceBox(0);
        }
        if (s.equals("\u2028")) {
            return new SpaceBox(1);
        }
        if ("\u2007\u2008\u2009\u200a\u2061\u2062\u2063\u2064\u2028\ue000".indexOf(s) >= 0) {
            return new SpaceBox(s.charAt(0));
        }
        return null;
    }
    
    public final boolean hasProperties() {
        return true;
    }
    
    public final void exchangeProperties(final Hashtable hashtable, final int n) {
        if (this.type == 2) {
            this.sWidth = Attributes.exchangeString(hashtable, "width", n, this.sWidth, "0em");
        }
    }
}
