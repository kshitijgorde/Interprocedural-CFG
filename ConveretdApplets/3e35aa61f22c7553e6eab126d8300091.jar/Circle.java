// 
// Decompiled by Procyon v0.5.30
// 

class Circle
{
    public int x;
    public int y;
    public int r;
    public int dx;
    public int dy;
    public int ddx;
    public int ddy;
    public float chaseSpeed;
    public float skill;
    public int changeTime;
    public int attackRng;
    public boolean squishing;
    
    public Circle(final int x, final int y, final int r, final int n, final int n2, final float chaseSpeed, final float skill, final int attackRng) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = n;
        this.dy = n2;
        this.ddx = n;
        this.ddy = n2;
        this.chaseSpeed = chaseSpeed;
        this.skill = skill;
        this.changeTime = 10;
        this.attackRng = attackRng;
        this.squishing = false;
    }
    
    public void initialize(final int x, final int y, final int r, final int n, final int n2, final int n3, final int n4, final int attackRng) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = n;
        this.dy = n2;
        this.ddx = n;
        this.ddy = n2;
        this.chaseSpeed = n3;
        this.skill = n4;
        this.changeTime = 10;
        this.attackRng = attackRng;
        this.squishing = false;
    }
    
    public void initialize(final int n, final int n2, final int n3, final int n4) {
        this.dx = n;
        this.dy = n2;
        this.ddx = n;
        this.ddy = n2;
        this.chaseSpeed = n3;
        this.skill = n4;
    }
}
