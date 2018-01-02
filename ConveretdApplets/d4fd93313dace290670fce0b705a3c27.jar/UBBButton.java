import java.util.Vector;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public final class UBBButton extends UBBArea
{
    protected UBBTag[] subtags;
    protected boolean bypassInit;
    protected boolean enabled;
    protected boolean mouseInButton;
    protected boolean mousePressed;
    protected boolean buttonClick;
    protected boolean buttonOn;
    protected int buttonStick;
    protected Image buttonBaseImage;
    protected Image buttonEnterImage;
    protected Image buttonPressImage;
    protected Image buttonEnterPressImage;
    protected int animationFrameNbr;
    protected int animationTickCount;
    protected int animationNextFrameTickCount;
    protected Image[] currentAnimationFrames;
    protected Image[] buttonBaseImageFrames;
    protected Image[] buttonEnterImageFrames;
    protected Image[] buttonPressImageFrames;
    protected Image[] buttonEnterPressImageFrames;
    protected int[] currentAnimationImageTicks;
    protected int[] buttonBaseImageTicks;
    protected int[] buttonEnterImageTicks;
    protected int[] buttonPressImageTicks;
    protected int[] buttonEnterPressImageTicks;
    protected int buttonAnmLoop;
    protected int buttonAnmLoopCnt;
    protected boolean buttonAnmRev;
    protected boolean buttonAnmIsRev;
    
    public void stop() {
        if (!super.hidden && this.mouseInButton) {
            this.mouseEvent(2, new Point(0, 0));
        }
        super.stop();
    }
    
    private void renderButton(final Image image) {
        if (super.children != null && super.image != null && image != null) {
            final Graphics graphics = super.image.getGraphics();
            if (graphics != null) {
                graphics.drawImage(image, 0, 0, null);
                while (true) {
                    for (int i = 0; i < super.children.length; ++i) {
                        if (!super.children[i].isHidden()) {
                            while (i < super.children.length) {
                                try {
                                    super.children[i].paint(graphics);
                                }
                                catch (Exception ex) {
                                    super.error.notify(super.name, 0, "draw " + super.children[i].getName(), null);
                                }
                                ++i;
                            }
                            graphics.dispose();
                            return;
                        }
                    }
                    continue;
                }
            }
        }
        else {
            super.image = image;
        }
    }
    
    public int getState() {
        int n = 1;
        if (this.buttonOn) {
            n += 2;
        }
        return n + this.buttonStick;
    }
    
    public void drawBackground(final Graphics graphics, final Rectangle rectangle) {
        if (graphics != null) {
            if (this.buttonBaseImage != null) {
                graphics.drawImage(this.buttonBaseImage, -rectangle.x, -rectangle.y, null);
                return;
            }
            super.drawBackground(graphics, rectangle);
        }
    }
    
    public boolean setState(final String s, final int n) {
        if (s == null || (super.name != null && s.equals(super.name))) {
            if ((n & 0x1) != 0x0) {
                if (this.buttonOn) {
                    if (super.actions != null && this.buttonStick != 4 && super.actions[5] != null) {
                        super.host.ubbEvent(this, super.actions[5]);
                    }
                    this.buttonOn = false;
                    super.drawUpdatePending = true;
                }
            }
            else if ((n & 0x2) != 0x0 && !this.buttonOn) {
                if (super.actions != null && this.buttonStick != 4 && super.actions[4] != null) {
                    super.host.ubbEvent(this, super.actions[4]);
                }
                this.buttonOn = true;
                super.drawUpdatePending = true;
            }
        }
        if (s == null && super.setState(s, n)) {
            super.drawUpdatePending = true;
        }
        return super.drawUpdatePending;
    }
    
    public synchronized void draw() {
        Image image = this.buttonBaseImage;
        this.currentAnimationFrames = this.buttonBaseImageFrames;
        this.currentAnimationImageTicks = this.buttonBaseImageTicks;
        if (this.mouseInButton || this.buttonOn) {
            if (this.mousePressed) {
                if (this.buttonPressImage != null) {
                    image = this.buttonPressImage;
                    this.currentAnimationFrames = this.buttonPressImageFrames;
                    this.currentAnimationImageTicks = this.buttonPressImageTicks;
                }
            }
            else if ((this.buttonStick & 0x4) == 0x0 && this.buttonOn) {
                if (this.mouseInButton && this.buttonEnterPressImage != null) {
                    image = this.buttonEnterPressImage;
                    this.currentAnimationFrames = this.buttonEnterPressImageFrames;
                    this.currentAnimationImageTicks = this.buttonEnterPressImageTicks;
                }
                else if (this.buttonPressImage != null) {
                    image = this.buttonPressImage;
                    this.currentAnimationFrames = this.buttonPressImageFrames;
                    this.currentAnimationImageTicks = this.buttonPressImageTicks;
                }
            }
            else if (this.buttonEnterImage != null) {
                if (this.buttonClick && this.buttonAnmLoop > 0 && this.buttonEnterImageFrames != null) {
                    image = this.buttonEnterImageFrames[this.buttonEnterImageFrames.length - 1];
                    this.currentAnimationFrames = null;
                    this.currentAnimationImageTicks = null;
                }
                else {
                    image = this.buttonEnterImage;
                    this.currentAnimationFrames = this.buttonEnterImageFrames;
                    this.currentAnimationImageTicks = this.buttonEnterImageTicks;
                }
            }
        }
        if (this.currentAnimationFrames != null) {
            this.buttonAnmIsRev = false;
            final boolean animationFrameNbr = false;
            this.buttonAnmLoopCnt = (animationFrameNbr ? 1 : 0);
            this.animationTickCount = (animationFrameNbr ? 1 : 0);
            this.animationFrameNbr = (animationFrameNbr ? 1 : 0);
            this.animationNextFrameTickCount = this.currentAnimationImageTicks[0];
            image = this.currentAnimationFrames[0];
        }
        this.renderButton(image);
        super.drawUpdatePending = false;
    }
    
    public String[][] create() {
        if (super.attributes != null) {
            for (int i = 0; i < super.attributes.length; ++i) {
                if (super.attributes[i][0] != null && super.attributes[i][0].equals("inheritstates")) {
                    super.appendStates = (super.attributes[i][1].charAt(0) == 't');
                }
            }
        }
        final String[][] subtagAttributes = this.getSubtagAttributes(0, 0, false);
        if (subtagAttributes != null) {
            final String[][] attributes = new String[super.attributes.length + subtagAttributes.length][2];
            int n = 0;
            for (int j = 0; j < subtagAttributes.length; ++j) {
                attributes[n] = subtagAttributes[j];
                ++n;
            }
            for (int k = 0; k < super.attributes.length; ++k) {
                attributes[n] = super.attributes[k];
                ++n;
            }
            super.attributes = attributes;
        }
        super.attributes = super.create();
        int n2 = -1;
        final String[][] array = new String[(super.attributes != null) ? super.attributes.length : 1][2];
        String s = null;
        int n3 = 1;
        int n4 = 0;
        int n5 = 1;
        int n6 = 0;
        String s2 = "Dialog";
        int int1 = 14;
        int n7 = 0;
        Color color = Color.black;
        int n8 = 0;
        UBBImageProxy loadImage = null;
        int int2 = 0;
        int int3 = 0;
        int int4 = 0;
        int int5 = 0;
        int n9 = -1;
        int n10 = 0;
        int n11 = -1;
        int n12 = 0;
        int n13 = 0;
        String s3 = null;
        int buttonStick = 4;
        int int6 = 1;
        if (super.attributes != null) {
            for (int l = super.attributes.length - 1; l >= 0; --l) {
                final String s4 = super.attributes[l][0];
                String name = super.attributes[l][1];
                if (s4 != null && name != null) {
                    final int hashString = UBB.hashString(s4);
                    try {
                        if (hashString == 2718) {
                            if (name.toLowerCase().equals("none")) {
                                s = null;
                            }
                            else {
                                s = name;
                                if (super.name == null) {
                                    super.name = name;
                                }
                            }
                        }
                        else if (hashString == 16155) {
                            final int[] alignment = this.parseAlignment(false, name);
                            n3 = alignment[0];
                            n4 = alignment[1];
                        }
                        else if (hashString == 11100) {
                            final int[] alignment2 = this.parseAlignment(true, name);
                            n5 = alignment2[0];
                            n6 = alignment2[1];
                        }
                        else if (hashString == 16006) {
                            n8 = ((name.charAt(0) == 't') ? 1 : 0);
                        }
                        else if (hashString == 2533) {
                            s2 = name;
                        }
                        else if (hashString == 8488) {
                            int1 = Integer.parseInt(name);
                        }
                        else if (hashString == 10536) {
                            name = name.toLowerCase();
                            n7 = 0;
                            if (name.indexOf(112) == -1) {
                                if (name.indexOf(98) > -1) {
                                    n7 |= 0x1;
                                }
                                if (name.indexOf(105) > -1) {
                                    n7 |= 0x2;
                                }
                            }
                        }
                        else if (hashString == 10458) {
                            color = UBBImageFactory.parseColor(name);
                        }
                        else if (hashString == 3665) {
                            if (name.toLowerCase().equals("none")) {
                                loadImage = null;
                            }
                            else {
                                loadImage = super.imageFactory.loadImage(name);
                            }
                        }
                        else if (hashString == 5046) {
                            int2 = Integer.parseInt(name);
                        }
                        else if (hashString == 5048) {
                            int3 = Integer.parseInt(name);
                        }
                        else if (hashString == 5045) {
                            if (name.toLowerCase().charAt(0) == 'f') {
                                int4 = -1;
                            }
                            else {
                                int4 = Integer.parseInt(name);
                                if (int4 == 0) {
                                    int4 = -1;
                                }
                            }
                        }
                        else if (hashString == 5025) {
                            if (name.toLowerCase().charAt(0) == 'f') {
                                int5 = -1;
                            }
                            else {
                                int5 = Integer.parseInt(name);
                                if (int5 == 0) {
                                    int5 = -1;
                                }
                            }
                        }
                        else if (hashString == 10396) {
                            n13 = ((name.charAt(0) == 't') ? 1 : 0);
                        }
                        else if (hashString == 17699) {
                            final int[] alignment3 = this.parseAlignment(false, name);
                            n9 = alignment3[0];
                            n10 = alignment3[1];
                        }
                        else if (hashString == 12553) {
                            final int[] alignment4 = this.parseAlignment(true, name);
                            n11 = alignment4[0];
                            n12 = alignment4[1];
                        }
                        else if (hashString == 6418) {
                            if (name.toLowerCase().equals("none")) {
                                s3 = "";
                            }
                            else {
                                if (s3 == null) {
                                    s3 = "";
                                }
                                if (name.charAt(0) == '+') {
                                    s3 = s3 + " " + name.substring(1);
                                }
                                else if (name.charAt(name.length() - 1) == '+') {
                                    s3 = name.substring(0, name.length() - 1) + " " + s3;
                                }
                                else {
                                    s3 = name;
                                }
                            }
                        }
                        else if (hashString == 3903) {
                            int6 = Integer.parseInt(name);
                        }
                        else if (hashString == 14560) {
                            this.buttonAnmLoop = Integer.parseInt(name);
                        }
                        else if (hashString == 22832) {
                            this.buttonAnmRev = (name.charAt(0) == 't');
                        }
                        else if (hashString == 3950) {
                            name = name.toLowerCase();
                            char c = name.charAt(0);
                            if (c == '!') {
                                c = name.charAt(1);
                                this.buttonOn = true;
                            }
                            if (c == 'a') {
                                buttonStick = 8;
                            }
                            else if (c == 'p') {
                                buttonStick = 16;
                            }
                            else {
                                buttonStick = 4;
                                this.buttonOn = false;
                            }
                        }
                        else if (hashString == 6449) {
                            this.enabled = (name.charAt(0) == 't');
                        }
                        else {
                            array[++n2][0] = s4;
                            array[n2][1] = name;
                        }
                    }
                    catch (Exception ex) {
                        super.error.notify(super.name, 1, "BUTTON default attribute error " + s4 + "=" + name, ex);
                    }
                }
            }
        }
        try {
            if (super.image == null) {
                super.image = super.host.createImage(super.bounds.width, super.bounds.height);
            }
            final Graphics graphics = super.image.getGraphics();
            if (super.backgroundClear) {
                super.container.drawBackground(graphics, super.bounds);
            }
            else {
                graphics.setColor(super.backgroundColor);
                graphics.fillRect(0, 0, super.bounds.width, super.bounds.height);
            }
            if (super.backgroundImage != null && super.backgroundImage.isReady()) {
                if (super.bgTile) {
                    super.backgroundImage.tile(graphics, super.bgX, super.bgY, super.bounds.width, super.bounds.height, super.backgroundColor);
                }
                else {
                    graphics.drawImage(super.backgroundImage.getImage(), super.bgX, super.bgY, null);
                }
            }
            graphics.dispose();
        }
        catch (Exception ex2) {
            super.error.notify(super.name, 0, "Can't create default background", ex2);
        }
        boolean b = false;
        int n14 = 0;
        Image image = null;
        final Vector<Image> vector = new Vector<Image>(5);
        int n15 = 1;
        do {
            int n16 = 0;
            String s5 = s;
            int n17 = n3;
            int n18 = n4;
            int n19 = n5;
            int n20 = n6;
            int n21 = n8;
            String s6 = s2;
            int int7 = int1;
            Color color2 = color;
            int n22 = n7;
            UBBImageProxy ubbImageProxy = loadImage;
            int int8 = int2;
            int int9 = int3;
            int int10 = int4;
            int int11 = int5;
            int n23 = n13;
            int n24 = n9;
            int n25 = n10;
            int n26 = n11;
            int n27 = n12;
            String s7 = s3;
            int int12 = int6;
            boolean b2 = false;
            UBBImageProxy ubbImageProxy2 = super.backgroundImage;
            int n28 = super.bgX;
            int n29 = super.bgY;
            Color color3 = super.backgroundColor;
            boolean backgroundClear = super.backgroundClear;
            boolean bgTile = super.bgTile;
            this.buttonStick = buttonStick;
            UBBImageProxy ubbImageProxy3 = null;
            boolean b3 = false;
            String[][] array2 = this.getSubtagAttributes(n15, n16, false);
            if (n15 == 1 && array2 == null) {
                b3 = true;
                ubbImageProxy3 = super.imageFactory.manageImage(super.name, super.image, false, true);
            }
            do {
                if (n16 == 1) {
                    vector.addElement(image);
                    vector.addElement((Image)new Integer(int12));
                    if (n14 == 0) {
                        super.animation.addAnimatedComponent(this);
                        n14 = 1;
                    }
                }
                if (array2 != null) {
                    b = (b3 = true);
                    for (int n30 = 0; n30 < array2.length; ++n30) {
                        final String s8 = array2[n30][0];
                        String name2 = array2[n30][1];
                        if (s8 != null && name2 != null) {
                            final int hashString2 = UBB.hashString(s8);
                            try {
                                if (hashString2 == 2718) {
                                    if (name2.toLowerCase().equals("none")) {
                                        s5 = null;
                                    }
                                    else {
                                        s5 = name2;
                                        if (super.name == null) {
                                            super.name = name2;
                                        }
                                    }
                                }
                                else if (hashString2 == 16155) {
                                    final int[] alignment5 = this.parseAlignment(false, name2);
                                    n17 = alignment5[0];
                                    n18 = alignment5[1];
                                }
                                else if (hashString2 == 11100) {
                                    final int[] alignment6 = this.parseAlignment(true, name2);
                                    n19 = alignment6[0];
                                    n20 = alignment6[1];
                                }
                                else if (hashString2 == 16006) {
                                    n21 = ((name2.charAt(0) == 't') ? 1 : 0);
                                }
                                else if (hashString2 == 2533) {
                                    s6 = name2;
                                }
                                else if (hashString2 == 8488) {
                                    int7 = Integer.parseInt(name2);
                                }
                                else if (hashString2 == 10536) {
                                    name2 = name2.toLowerCase();
                                    n22 = 0;
                                    if (name2.indexOf(112) == -1) {
                                        if (name2.indexOf(98) > -1) {
                                            n22 |= 0x1;
                                        }
                                        if (name2.indexOf(105) > -1) {
                                            n22 |= 0x2;
                                        }
                                    }
                                }
                                else if (hashString2 == 10458) {
                                    color2 = UBBImageFactory.parseColor(name2);
                                }
                                else if (hashString2 == 3665) {
                                    if (name2.toLowerCase().equals("none")) {
                                        ubbImageProxy = null;
                                    }
                                    else {
                                        ubbImageProxy = super.imageFactory.loadImage(name2);
                                    }
                                }
                                else if (hashString2 == 5046) {
                                    int8 = Integer.parseInt(name2);
                                }
                                else if (hashString2 == 5048) {
                                    int9 = Integer.parseInt(name2);
                                }
                                else if (hashString2 == 5045) {
                                    if (name2.toLowerCase().charAt(0) == 'f') {
                                        int10 = -1;
                                    }
                                    else {
                                        int10 = Integer.parseInt(name2);
                                        if (int10 == 0) {
                                            int10 = -1;
                                        }
                                    }
                                }
                                else if (hashString2 == 5025) {
                                    if (name2.toLowerCase().charAt(0) == 'f') {
                                        int11 = -1;
                                    }
                                    else {
                                        int11 = Integer.parseInt(name2);
                                        if (int11 == 0) {
                                            int11 = -1;
                                        }
                                    }
                                }
                                else if (hashString2 == 10396) {
                                    n23 = ((name2.charAt(0) == 't') ? 1 : 0);
                                }
                                else if (hashString2 == 17699) {
                                    final int[] alignment7 = this.parseAlignment(false, name2);
                                    n24 = alignment7[0];
                                    n25 = alignment7[1];
                                }
                                else if (hashString2 == 12553) {
                                    final int[] alignment8 = this.parseAlignment(true, name2);
                                    n26 = alignment8[0];
                                    n27 = alignment8[1];
                                }
                                else if (hashString2 == 6418) {
                                    if (name2.toLowerCase().equals("none")) {
                                        s7 = "";
                                    }
                                    else {
                                        if (s7 == null) {
                                            s7 = "";
                                        }
                                        if (name2.charAt(0) == '+') {
                                            s7 = s7 + " " + name2.substring(1);
                                        }
                                        else if (name2.charAt(name2.length() - 1) == '+') {
                                            s7 = name2.substring(0, name2.length() - 1) + " " + s7;
                                        }
                                        else {
                                            s7 = name2;
                                        }
                                    }
                                }
                                else if (hashString2 == 6350) {
                                    if (name2.toLowerCase().equals("clear")) {
                                        backgroundClear = true;
                                    }
                                    else {
                                        backgroundClear = false;
                                        color3 = UBBImageFactory.parseColor(name2);
                                    }
                                    b2 = true;
                                }
                                else if (hashString2 == 6321) {
                                    if (name2.toLowerCase().equals("none")) {
                                        ubbImageProxy2 = null;
                                    }
                                    else {
                                        ubbImageProxy2 = super.imageFactory.loadImage(name2);
                                    }
                                    b2 = true;
                                }
                                else if (hashString2 == 1523) {
                                    n28 = Integer.parseInt(name2);
                                    b2 = true;
                                }
                                else if (hashString2 == 1525) {
                                    n29 = Integer.parseInt(name2);
                                    b2 = true;
                                }
                                else if (hashString2 == 4873) {
                                    bgTile = (name2.charAt(0) == 't');
                                    b2 = true;
                                }
                                else if (hashString2 == 3903) {
                                    int12 = Integer.parseInt(name2);
                                }
                                else if (hashString2 == 14560) {
                                    this.buttonAnmLoop = Integer.parseInt(name2);
                                }
                                else if (hashString2 == 22832) {
                                    this.buttonAnmRev = (name2.charAt(0) == 't');
                                }
                                else {
                                    if (hashString2 != 3950) {
                                        throw new IllegalArgumentException("Unknown attribute");
                                    }
                                    name2 = name2.toLowerCase();
                                    char c2 = name2.charAt(0);
                                    if (c2 == '!') {
                                        c2 = name2.charAt(1);
                                        this.buttonOn = true;
                                    }
                                    if (c2 == 'a') {
                                        buttonStick = 8;
                                    }
                                    else if (c2 == 'p') {
                                        buttonStick = 16;
                                    }
                                    else {
                                        buttonStick = 4;
                                        this.buttonOn = false;
                                    }
                                }
                            }
                            catch (Exception ex3) {
                                super.error.notify(super.name, 1, "BUTTON attribute error " + s8 + "=" + name2, ex3);
                            }
                        }
                    }
                    if (b2) {
                        try {
                            final Image image2 = super.host.createImage(super.bounds.width, super.bounds.height);
                            final Graphics graphics2 = image2.getGraphics();
                            if (backgroundClear) {
                                super.container.drawBackground(graphics2, super.bounds);
                            }
                            else {
                                graphics2.setColor(color3);
                                graphics2.fillRect(0, 0, super.bounds.width, super.bounds.height);
                            }
                            if (ubbImageProxy2 != null) {
                                if (bgTile) {
                                    ubbImageProxy2.tile(graphics2, n28, n29, super.bounds.width, super.bounds.height, color3);
                                }
                                else {
                                    graphics2.drawImage(ubbImageProxy2.getImage(), n28, n29, null);
                                }
                            }
                            graphics2.dispose();
                            ubbImageProxy3 = super.imageFactory.manageImage(super.name, image2, false, false);
                        }
                        catch (Exception ex4) {
                            super.error.notify(super.name, 0, "Creating button background", ex4);
                        }
                    }
                    else {
                        ubbImageProxy3 = super.imageFactory.manageImage(super.name, super.image, false, true);
                    }
                }
                else if (b) {
                    ubbImageProxy3 = super.imageFactory.manageImage(super.name, super.image, false, true);
                }
                if (b3 || (b && n15 != 4)) {
                    if (ubbImageProxy != null) {
                        try {
                            if (int10 > 0 || int11 > 0) {
                                ubbImageProxy = super.imageFactory.manageImage(super.name, ubbImageProxy.getImageCopy(int10, int11), false, false);
                            }
                            final Graphics imageGraphics = ubbImageProxy3.getImageGraphics();
                            if (n23 != 0) {
                                ubbImageProxy.tile(imageGraphics, int8, int9, super.bounds.width, super.bounds.height, null);
                            }
                            else {
                                int n31;
                                if (n24 < 0) {
                                    n31 = int8;
                                }
                                else {
                                    final int width = ubbImageProxy.getWidth();
                                    int n32;
                                    if (n24 == 0) {
                                        n32 = 0;
                                    }
                                    else if (n24 == 1) {
                                        n32 = (super.bounds.width - width) / 2;
                                    }
                                    else {
                                        n32 = super.bounds.width - width;
                                    }
                                    n31 = n32 + n25;
                                }
                                int n33;
                                if (n26 < 0) {
                                    n33 = int9;
                                }
                                else {
                                    final int height = ubbImageProxy.getHeight();
                                    int n34;
                                    if (n26 == 0) {
                                        n34 = 0;
                                    }
                                    else if (n26 == 1) {
                                        n34 = (super.bounds.height - height) / 2;
                                    }
                                    else {
                                        n34 = super.bounds.height - height;
                                    }
                                    n33 = n34 + n27;
                                }
                                if (int10 == -1 || int11 == -1) {
                                    final Image imageCopy = ubbImageProxy.getImageCopy((int10 == -1) ? (super.bounds.width - n31) : ubbImageProxy.getWidth(), (int11 == -1) ? (super.bounds.height - n33) : ubbImageProxy.getHeight());
                                    if (imageCopy != null) {
                                        imageGraphics.drawImage(imageCopy, n31, n33, null);
                                        imageCopy.flush();
                                    }
                                }
                                else {
                                    imageGraphics.drawImage(ubbImageProxy.getImage(), n31, n33, null);
                                }
                            }
                            imageGraphics.dispose();
                        }
                        catch (Exception ex5) {
                            super.error.notify(super.name, 0, "Creating button foreground", ex5);
                        }
                    }
                    if (n21 == 0) {
                        ubbImageProxy3.applyEffects(s7, false);
                    }
                    if (s5 != null) {
                        final UBBTextTools.FormattedText formatText = super.textTools.formatText(s5, 0, super.bounds.width, s6, int7, n22, color2);
                        formatText.setJustification(n17, n18);
                        formatText.setAlignment(n19, n20);
                        formatText.paint(ubbImageProxy3.getImage());
                    }
                    if (n21 != 0) {
                        ubbImageProxy3.applyEffects(s7, false);
                    }
                    image = ubbImageProxy3.getImage();
                    if (n16 == 0) {
                        if (n15 == 1) {
                            this.buttonBaseImage = image;
                        }
                        else if (n15 == 2) {
                            this.buttonEnterImage = image;
                        }
                        else if (n15 == 3) {
                            this.buttonPressImage = image;
                        }
                        else if (n15 == 4) {
                            this.buttonEnterPressImage = image;
                        }
                    }
                    else {
                        vector.addElement(image);
                        vector.addElement((Image)new Integer(int12));
                    }
                    ++n16;
                }
                else {
                    if (n15 == 2) {
                        this.buttonEnterImage = this.buttonBaseImage;
                    }
                    if (n15 == 3) {
                        this.buttonPressImage = this.buttonEnterImage;
                    }
                    if (n15 == 4) {
                        this.buttonEnterPressImage = this.buttonPressImage;
                    }
                }
                if (array2 != null) {
                    array2 = this.getSubtagAttributes(n15, n16, false);
                }
            } while (array2 != null);
            final int size = vector.size();
            if (size > 0) {
                final int n35 = size / 2;
                final Image[] array3 = new Image[n35];
                final int[] array4 = new int[n35];
                for (int n36 = 0; n36 < n35; ++n36) {
                    array3[n36] = vector.elementAt(n36 * 2);
                    array4[n36] = (int)vector.elementAt(n36 * 2 + 1);
                }
                if (n15 == 1) {
                    this.buttonBaseImageFrames = array3;
                    this.buttonBaseImageTicks = array4;
                }
                else if (n15 == 2) {
                    this.buttonEnterImageFrames = array3;
                    this.buttonEnterImageTicks = array4;
                }
                else if (n15 == 3) {
                    this.buttonPressImageFrames = array3;
                    this.buttonPressImageTicks = array4;
                }
                else if (n15 == 4) {
                    this.buttonEnterPressImageFrames = array3;
                    this.buttonEnterPressImageTicks = array4;
                }
                vector.removeAllElements();
            }
        } while (++n15 <= 4);
        super.drawUpdatePending = true;
        if (!this.bypassInit && super.children != null) {
            for (int n37 = 0; n37 < super.children.length; ++n37) {
                final String[][] create = super.children[n37].create();
                if (create != null) {
                    for (int n38 = 0; n38 < create.length; ++n38) {
                        if (create[n38][0] != null) {
                            super.error.notify(super.children[n37].getName(), 1, "Bad attribute " + create[n38][0] + "=" + create[n38][1], null);
                        }
                    }
                }
            }
        }
        if (n2 > -1) {
            return array;
        }
        return null;
    }
    
    public UBBButton() {
        this.enabled = true;
    }
    
    public void destroy() {
        super.destroy();
        if (this.buttonBaseImage != null) {
            this.buttonBaseImage.flush();
            this.buttonBaseImage = null;
        }
        if (this.buttonEnterImage != null) {
            this.buttonEnterImage.flush();
            this.buttonEnterImage = null;
        }
        if (this.buttonPressImage != null) {
            this.buttonPressImage.flush();
            this.buttonPressImage = null;
        }
        if (this.buttonEnterPressImage != null) {
            this.buttonEnterPressImage.flush();
            this.buttonEnterPressImage = null;
        }
        this.currentAnimationFrames = null;
        if (this.buttonBaseImageFrames != null) {
            for (int i = 0; i < this.buttonBaseImageFrames.length; ++i) {
                this.buttonBaseImageFrames[i].flush();
                this.buttonBaseImageFrames[i] = null;
            }
        }
        if (this.buttonEnterImageFrames != null) {
            for (int j = 0; j < this.buttonEnterImageFrames.length; ++j) {
                this.buttonEnterImageFrames[j].flush();
                this.buttonEnterImageFrames[j] = null;
            }
        }
        if (this.buttonPressImageFrames != null) {
            for (int k = 0; k < this.buttonPressImageFrames.length; ++k) {
                this.buttonPressImageFrames[k].flush();
                this.buttonPressImageFrames[k] = null;
            }
        }
        if (this.buttonEnterPressImageFrames != null) {
            for (int l = 0; l < this.buttonEnterPressImageFrames.length; ++l) {
                this.buttonEnterPressImageFrames[l].flush();
                this.buttonEnterPressImageFrames[l] = null;
            }
        }
    }
    
    public int[] parseAlignment(final boolean b, final String s) {
        final int[] array = new int[2];
        final char char1 = s.toLowerCase().charAt(0);
        if (b) {
            if (char1 == 'b') {
                array[0] = 2;
            }
            else if (char1 == 'm') {
                array[0] = 1;
            }
            else {
                array[0] = 0;
            }
        }
        else if (char1 == 'r') {
            array[0] = 2;
        }
        else if (char1 == 'c') {
            array[0] = 1;
        }
        else {
            array[0] = 0;
        }
        try {
            final int index;
            if ((index = s.indexOf(43)) > 0) {
                array[1] = Integer.parseInt(s.substring(index + 1));
            }
            else {
                final int index2;
                if ((index2 = s.indexOf(45)) > 0) {
                    array[1] = -Integer.parseInt(s.substring(index2 + 1));
                }
            }
        }
        catch (Exception ex) {
            super.error.notify(super.name, 0, "Bad " + (b ? "align" : "justify") + " value", ex);
        }
        return array;
    }
    
    public void start() {
        super.start();
        if (this.enabled) {
            super.host.regMouseListener(this, false);
        }
        this.subtags = null;
    }
    
    public void init(final UBB ubb, final String s, final int n, final int n2, final String[][] array, final String s2, final UBBTag[] subtags, final UBBImageFactory ubbImageFactory, final UBBTextTools ubbTextTools, final UBBAnimationTimer ubbAnimationTimer, final UBBErrorHandler ubbErrorHandler, final boolean bypassInit) {
        super.init(ubb, s, n, n2, array, s2, subtags, ubbImageFactory, ubbTextTools, ubbAnimationTimer, ubbErrorHandler, true);
        this.bypassInit = bypassInit;
        this.subtags = subtags;
    }
    
    public int mouseEvent(final int n, final Point point) {
        this.buttonClick = false;
        super.mouseEvent(n, point);
        final int n2 = n & 0x7F;
        switch (n2) {
            case 1: {
                this.mouseInButton = true;
                super.drawUpdatePending = true;
                break;
            }
            case 2: {
                this.mouseInButton = false;
                super.drawUpdatePending = true;
                break;
            }
            case 4: {
                if ((n & 0x80) == 0x0) {
                    this.mousePressed = true;
                    super.drawUpdatePending = true;
                    break;
                }
                break;
            }
            case 8: {
                if ((n & 0x80) == 0x0) {
                    if (this.mouseInButton && this.mousePressed) {
                        if ((this.buttonStick & 0x8) != 0x0) {
                            this.buttonOn = !this.buttonOn;
                        }
                        else if ((this.buttonStick & 0x10) != 0x0 && !this.buttonOn) {
                            this.buttonOn = true;
                        }
                        this.buttonClick = true;
                        if (super.container != null) {
                            super.container.childEvent(this, 1);
                        }
                    }
                    this.mousePressed = false;
                    super.drawUpdatePending = true;
                    break;
                }
                break;
            }
            case 32: {
                if (this.mousePressed) {
                    super.drawUpdatePending = true;
                    this.mousePressed = false;
                    break;
                }
                break;
            }
        }
        if (super.drawUpdatePending) {
            this.updateUBBListeners(false);
        }
        if (super.actions != null) {
            if (this.buttonClick) {
                if (super.actions[3] != null) {
                    super.host.ubbEvent(this, super.actions[3]);
                }
                if (this.buttonStick != 4) {
                    if (this.buttonOn && super.actions[4] != null) {
                        super.host.ubbEvent(this, super.actions[4]);
                    }
                    else if (!this.buttonOn && super.actions[5] != null) {
                        super.host.ubbEvent(this, super.actions[5]);
                    }
                }
            }
            else {
                switch (n2) {
                    case 1: {
                        if (super.actions[0] != null) {
                            super.host.ubbEvent(this, super.actions[0]);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (super.actions[1] != null) {
                            super.host.ubbEvent(this, super.actions[1]);
                            break;
                        }
                        break;
                    }
                    case 4: {
                        if ((n & 0x80) == 0x0 && super.actions[2] != null) {
                            super.host.ubbEvent(this, super.actions[2]);
                            break;
                        }
                        break;
                    }
                }
            }
        }
        return super.mouseModifier;
    }
    
    public synchronized boolean clockEvent(final boolean b) {
        if (this.currentAnimationFrames != null) {
            if (b) {
                final boolean b2 = false;
                this.animationNextFrameTickCount = (b2 ? 1 : 0);
                this.animationTickCount = (b2 ? 1 : 0);
                this.animationFrameNbr = this.currentAnimationFrames.length;
                this.buttonAnmLoopCnt = 0;
                this.buttonAnmIsRev = false;
            }
            if (!super.hidden && (this.buttonAnmLoop == 0 || this.buttonAnmLoopCnt < this.buttonAnmLoop) && this.animationTickCount++ == this.animationNextFrameTickCount) {
                Image image;
                if ((!this.buttonAnmIsRev && ++this.animationFrameNbr < this.currentAnimationFrames.length) || (this.buttonAnmIsRev && --this.animationFrameNbr >= 0)) {
                    this.animationNextFrameTickCount += this.currentAnimationImageTicks[this.animationFrameNbr];
                    image = this.currentAnimationFrames[this.animationFrameNbr];
                }
                else {
                    if ((!this.buttonAnmRev || this.buttonAnmIsRev) && this.buttonAnmLoop > 0) {
                        ++this.buttonAnmLoopCnt;
                    }
                    if (this.buttonAnmLoop > 0 && this.buttonAnmLoopCnt >= this.buttonAnmLoop) {
                        image = this.currentAnimationFrames[--this.animationFrameNbr];
                    }
                    else {
                        this.animationTickCount = 1;
                        if (this.buttonAnmRev && !this.buttonAnmIsRev) {
                            this.animationFrameNbr = this.currentAnimationFrames.length - 1;
                        }
                        else {
                            this.animationFrameNbr = 0;
                        }
                        if (this.buttonAnmRev) {
                            this.buttonAnmIsRev = !this.buttonAnmIsRev;
                            if (this.animationFrameNbr == 0) {
                                this.animationFrameNbr = 1;
                            }
                            else {
                                --this.animationFrameNbr;
                            }
                        }
                        this.animationNextFrameTickCount = this.currentAnimationImageTicks[this.animationFrameNbr];
                        image = this.currentAnimationFrames[this.animationFrameNbr];
                    }
                }
                this.renderButton(image);
                if (b) {
                    final boolean animationFrameNbr = false;
                    this.buttonAnmLoopCnt = (animationFrameNbr ? 1 : 0);
                    this.animationTickCount = (animationFrameNbr ? 1 : 0);
                    this.animationFrameNbr = (animationFrameNbr ? 1 : 0);
                    this.buttonAnmIsRev = false;
                }
                return super.animationFrameUpdate = true;
            }
        }
        return false;
    }
    
    public boolean setHidden(final String s, final boolean b) {
        if (b && (s == null || s.equals(super.name)) && !super.hidden && this.mouseInButton) {
            this.mouseEvent(2, new Point(0, 0));
        }
        return super.setHidden(s, b);
    }
}
