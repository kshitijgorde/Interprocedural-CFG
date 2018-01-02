import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Sprite
{
    public static final int X = 0;
    public static final int Y = 1;
    public static final int WIDTH = 2;
    public static final int HEIGHT = 3;
    public static final int XFACE = 4;
    public static final int YFACE = 5;
    public static final int ACTIVE = 6;
    public static final int VISIBLE = 7;
    public static final int CONSTRAIN = 8;
    public static final int XANCHOR = 10;
    public static final int YANCHOR = 11;
    public static final Integer ZERO;
    public static final Integer ONE;
    public static final Integer TWO;
    public static final Integer THREE;
    public static final Integer RIGHT;
    public static final Integer LEFT;
    public static final Integer UP;
    public static final Integer DOWN;
    public static final Integer CENTER;
    public static final Integer WEST;
    public static final Integer EAST;
    public static final Integer NORTH;
    public static final Integer SOUTH;
    public static final Boolean TRUE;
    public static final Boolean FALSE;
    public int x;
    public int y;
    public int width;
    public int height;
    public int xFace;
    public int yFace;
    public int xAnchor;
    public int yAnchor;
    public boolean active;
    public boolean visible;
    public boolean constrain;
    
    public Sprite() {
        this.set(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11 }, new Object[] { Sprite.ZERO, Sprite.ZERO, Sprite.ZERO, Sprite.ZERO, Sprite.RIGHT, Sprite.UP, Sprite.FALSE, Sprite.FALSE, Sprite.FALSE, Sprite.CENTER, Sprite.CENTER });
    }
    
    public void set(final int n, final Object o) {
        switch (n) {
            case 0: {
                this.x = (int)o;
            }
            case 1: {
                this.y = (int)o;
            }
            case 2: {
                this.width = (int)o;
            }
            case 3: {
                this.height = (int)o;
            }
            case 4: {
                this.xFace = (int)o;
            }
            case 5: {
                this.yFace = (int)o;
            }
            case 6: {
                this.active = (boolean)o;
            }
            case 7: {
                this.visible = (boolean)o;
            }
            case 8: {
                this.constrain = (boolean)o;
            }
            case 10: {
                this.xAnchor = (int)o;
            }
            case 11: {
                this.yAnchor = (int)o;
            }
            default: {}
        }
    }
    
    public void set(final int[] array, final Object[] array2) {
        for (int i = 0; i < array.length; ++i) {
            this.set(array[i], array2[i]);
        }
    }
    
    public void activate() {
        this.active = true;
        this.visible = true;
    }
    
    public void deactivate() {
        this.active = false;
        this.visible = false;
    }
    
    public void draw(final Graphics graphics) {
    }
    
    static {
        ZERO = new Integer(0);
        ONE = new Integer(1);
        TWO = new Integer(2);
        THREE = new Integer(3);
        RIGHT = Sprite.ZERO;
        LEFT = Sprite.ONE;
        UP = Sprite.ZERO;
        DOWN = Sprite.ONE;
        CENTER = Sprite.ZERO;
        WEST = Sprite.ONE;
        EAST = Sprite.TWO;
        NORTH = Sprite.ONE;
        SOUTH = Sprite.TWO;
        TRUE = new Boolean(true);
        FALSE = new Boolean(false);
    }
}
