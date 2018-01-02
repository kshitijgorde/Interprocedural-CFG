import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class poolTable
{
    private int m_height;
    private int m_width;
    private Color m_colour;
    private int m_numberOfBalls;
    private poolBall[] m_poolBalls;
    private poolBall[] m_storedBalls;
    private int m_numberOfPockets;
    private pocket[] m_pockets;
    private int m_numberOfCushions;
    private cushion[] m_cushions;
    private xyVector[] m_replacements;
    poolCue m_cue;
    
    public void restore() {
        for (int i = 0; i < this.m_numberOfBalls; ++i) {
            this.m_poolBalls[i].reset(this.m_storedBalls[i]);
        }
    }
    
    public void store() {
        this.m_storedBalls = new poolBall[this.m_numberOfBalls];
        for (int i = 0; i < this.m_numberOfBalls; ++i) {
            this.m_storedBalls[i] = new poolBall(this.m_poolBalls[i]);
        }
    }
    
    void doCollision(final int n) {
        final poolBall poolBall = this.m_poolBalls[n];
        for (int i = 0; i < this.m_numberOfCushions; ++i) {
            this.m_cushions[i].bounce(poolBall);
        }
        for (int j = 0; j < this.m_numberOfBalls; ++j) {
            if (this.m_poolBalls[j] != poolBall && poolBall.testCollision(this.m_poolBalls[j])) {
                poolBall.Collide(this.m_poolBalls[j]);
            }
        }
        for (int k = 0; k < this.m_numberOfPockets; ++k) {
            if (this.m_pockets[k].testPot(poolBall)) {
                poolBall.m_velocity = new xyVector(0.0, 0.0);
                poolBall.m_spin = new xyVector(0.0, 0.0);
                poolBall.m_position = new xyVector(-100.0, -100.0);
                if (n > 6) {
                    --this.m_numberOfBalls;
                    this.m_poolBalls[n] = this.m_poolBalls[this.m_numberOfBalls];
                    javaTest.m_bRepaintAll = true;
                }
                else if (n < this.m_numberOfBalls - 1) {
                    poolBall.m_position = new xyVector(this.m_replacements[n]);
                }
                else {
                    javaTest.m_bRepaintAll = true;
                    --this.m_numberOfBalls;
                    if (this.m_numberOfBalls <= 0) {
                        javaTest.reset();
                    }
                }
            }
        }
    }
    
    poolTable(final int width, final int height) {
        this.m_height = height;
        this.m_width = width;
        this.m_colour = Color.green;
        final int n = 11;
        this.m_numberOfBalls = 17;
        this.m_poolBalls = new poolBall[this.m_numberOfBalls];
        (this.m_replacements = new xyVector[7])[0] = new xyVector(width * 3 / 4, height / 2 + 3 * n);
        this.m_poolBalls[0] = new poolBall(new xyVector(this.m_replacements[0]), n, Color.white);
        this.m_replacements[6] = new xyVector(width * 3 / 4, height / 4);
        this.m_poolBalls[6] = new poolBall(new xyVector(this.m_replacements[6]), n, Color.yellow);
        final Color color = new Color(0, 170, 0);
        this.m_replacements[5] = new xyVector(width * 3 / 4, height * 3 / 4);
        this.m_poolBalls[5] = new poolBall(new xyVector(this.m_replacements[5]), n, color);
        final Color color2 = new Color(190, 150, 0);
        this.m_replacements[4] = new xyVector(width * 3 / 4, height / 2);
        this.m_poolBalls[4] = new poolBall(new xyVector(this.m_replacements[4]), n, color2);
        this.m_replacements[3] = new xyVector(width / 2, height / 2);
        this.m_poolBalls[3] = new poolBall(new xyVector(this.m_replacements[3]), n, Color.blue);
        this.m_replacements[2] = new xyVector(width * 3 / 8, height / 2);
        this.m_poolBalls[2] = new poolBall(new xyVector(this.m_replacements[2]), n, Color.pink);
        this.m_replacements[1] = new xyVector(width / 8, height / 2);
        this.m_poolBalls[1] = new poolBall(new xyVector(this.m_replacements[1]), n, Color.black);
        final double n2 = Math.sqrt(3.0) * n;
        int n3 = 7;
        for (int i = 0; i < 4; ++i) {
            final double n4 = width * 3 / 8 - 2 * n - i * n2;
            double n5 = height / 2 - n * i;
            for (int j = 0; j <= i; ++j) {
                this.m_poolBalls[n3++] = new poolBall(new xyVector(n4, n5), n, Color.red);
                n5 += n * 2;
            }
        }
        this.m_numberOfPockets = 6;
        (this.m_pockets = new pocket[this.m_numberOfPockets])[0] = new pocket(0, 0, 24);
        this.m_pockets[1] = new pocket(0, this.m_height, 24);
        this.m_pockets[2] = new pocket(this.m_width, 0, 24);
        this.m_pockets[3] = new pocket(this.m_width, this.m_height, 24);
        this.m_pockets[4] = new pocket(this.m_width / 2, 0, 20);
        this.m_pockets[5] = new pocket(this.m_width / 2, this.m_height, 20);
        this.m_numberOfCushions = 6;
        (this.m_cushions = new cushion[this.m_numberOfCushions])[0] = new cushion(this.m_pockets[0], this.m_pockets[1], 17);
        this.m_cushions[1] = new cushion(this.m_pockets[1], this.m_pockets[5], -17);
        this.m_cushions[2] = new cushion(this.m_pockets[5], this.m_pockets[3], -17);
        this.m_cushions[3] = new cushion(this.m_pockets[3], this.m_pockets[2], -17);
        this.m_cushions[4] = new cushion(this.m_pockets[2], this.m_pockets[4], 17);
        this.m_cushions[5] = new cushion(this.m_pockets[4], this.m_pockets[0], 17);
        this.m_cue = new poolCue(this.m_poolBalls[0]);
    }
    
    void paintAll(final Graphics graphics) {
        graphics.setColor(this.m_colour);
        graphics.fillRect(0, 0, this.m_width, this.m_height);
        this.paint(graphics);
    }
    
    void paint(final Graphics graphics) {
        graphics.clipRect(0, 0, this.m_width, this.m_height);
        this.m_cue.unPaint(graphics);
        this.m_poolBalls[0].paint(graphics);
        this.m_cue.paint(graphics);
        for (int i = 1; i < this.m_numberOfBalls; ++i) {
            this.m_poolBalls[i].paint(graphics);
        }
        for (int j = 0; j < this.m_numberOfPockets; ++j) {
            this.m_pockets[j].paint(graphics);
        }
        for (int k = 0; k < this.m_numberOfCushions; ++k) {
            this.m_cushions[k].paint(graphics);
        }
        this.m_cue.paint(graphics);
    }
    
    public void Animate() {
        for (int i = 0; i < this.m_numberOfBalls; ++i) {
            if (this.m_poolBalls[i] != null && this.m_poolBalls[i].Move()) {
                this.doCollision(i);
            }
        }
    }
}
