import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class NFCanvas extends Canvas
{
    NF4 nf4;
    NFControls controls;
    int a;
    int b;
    int xy;
    int[] angle;
    int total;
    int[][] values;
    int columns;
    int num;
    int X;
    int Y;
    String[] name;
    boolean submit;
    int kind;
    int witch;
    Font font;
    double max;
    double min;
    double decode;
    
    public void add(final NF4 nf4, final int[][] array, final int n, final int n2, final String[] array2) {
        this.columns = nf4.columns;
        this.num = nf4.num;
        this.values = new int[n2][n];
        this.name = new String[n2];
        for (int i = 0; i < n2; ++i) {
            this.name[i] = nf4.name[i];
            for (int j = 0; j < n; ++j) {
                this.values[i][j] = nf4.values[i][j];
            }
        }
        for (int k = 0; k < n2; ++k) {
            for (int l = 0; l < n; ++l) {
                if (array[k][l] > this.max) {
                    this.max = array[k][l];
                }
                else if (array[k][l] < this.min) {
                    this.min = array[k][l];
                }
            }
        }
        this.decode = 260.0 / (this.max - this.min);
    }
    
    public void paint(final Graphics graphics) {
        this.white(graphics);
    }
    
    public void update(final Graphics graphics) {
        if (!this.submit) {
            this.white(graphics);
            return;
        }
        if (this.kind == 0) {
            this.line(graphics, this.xy, this.witch);
            return;
        }
        if (this.kind == 1) {
            this.rect(graphics, this.xy, this.witch);
            return;
        }
        this.circle(graphics, this.xy, this.witch);
    }
    
    public void white(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillRect(10, 10, 500, 280);
        graphics.setColor(Color.black);
        graphics.drawRect(10, 10, 500, 280);
        graphics.drawRect(10, 300, 90 + 40 * this.columns, 30 * this.num);
        for (int i = 0; i < this.num; ++i) {
            this.a = 10;
            switch (i) {
                case 0: {
                    graphics.setColor(Color.red);
                    break;
                }
                case 1: {
                    graphics.setColor(Color.blue);
                    break;
                }
                case 2: {
                    graphics.setColor(Color.green);
                    break;
                }
                case 3: {
                    graphics.setColor(Color.orange);
                    break;
                }
                default: {
                    graphics.setColor(Color.yellow);
                    break;
                }
            }
            graphics.drawString(this.name[i], 10, 300 + (i + 1) * 30);
            graphics.setColor(Color.black);
            this.a += 80;
            graphics.drawLine(10, 300 + (i + 1) * 30, 90 + 40 * this.columns, 300 + (i + 1) * 30);
            for (int j = 0; j < this.columns; ++j) {
                graphics.drawString("" + this.values[i][j], this.a, 300 + (i + 1) * 30);
                graphics.drawLine(this.a - 3, 270 + (i + 1) * 30, this.a - 3, 300 + (i + 1) * 30);
                this.a += 40;
            }
        }
    }
    
    public void line(final Graphics graphics, final int n, final int n2) {
        if (n2 > this.num + 1) {
            graphics.drawString("Please Input Number under " + (this.num + 1), 100, 100);
            return;
        }
        this.a = 10;
        switch (n2) {
            case 0: {
                graphics.setColor(Color.red);
                break;
            }
            case 1: {
                graphics.setColor(Color.blue);
                break;
            }
            case 2: {
                graphics.setColor(Color.green);
                break;
            }
            case 3: {
                graphics.setColor(Color.orange);
                break;
            }
            default: {
                graphics.setColor(Color.yellow);
                break;
            }
        }
        for (int i = 0; i < this.columns; ++i) {
            if (i == this.columns - 1) {
                graphics.drawString("" + this.values[n2][i], this.a, 290 - (int)(this.values[n2][i] * this.decode));
                return;
            }
            graphics.drawLine(this.a, 290 - (int)(this.values[n2][i] * this.decode), this.a + 500 / (this.columns - 1), 290 - (int)(this.values[n2][i + 1] * this.decode));
            graphics.drawString("" + this.values[n2][i], this.a, 290 - (int)(this.values[n2][i] * this.decode));
            this.a += 500 / this.columns;
        }
    }
    
    public void rect(final Graphics graphics, final int n, final int n2) {
        this.a = 10;
        if (n2 > this.num + 1) {
            graphics.drawString("Please Input Number under " + (this.num + 1), 100, 100);
            return;
        }
        for (int i = 0; i < this.columns; ++i) {
            switch (n2) {
                case 0: {
                    graphics.setColor(Color.red);
                    break;
                }
                case 1: {
                    graphics.setColor(Color.blue);
                    break;
                }
                case 2: {
                    graphics.setColor(Color.green);
                    break;
                }
                case 3: {
                    graphics.setColor(Color.orange);
                    break;
                }
                default: {
                    graphics.setColor(Color.yellow);
                    break;
                }
            }
            graphics.fillRoundRect(this.a, 290 - (int)(this.values[n2][i] * this.decode), 15, (int)(this.values[n2][i] * this.decode), 5, 5);
            graphics.setColor(Color.black);
            graphics.drawString("" + this.values[n2][i], this.a + 5, 290 - (int)(this.values[n2][i] * this.decode));
            this.a += 20;
        }
    }
    
    public void circle(final Graphics graphics, final int n, final int n2) {
        this.a = 10;
        if (n2 > this.columns) {
            graphics.drawString("Please Input number under" + (this.columns + 1), 100, 100);
            return;
        }
        this.total = this.values[0][n2];
        for (int i = 1; i < this.num; ++i) {
            this.total += this.values[i][n2];
        }
        this.angle = new int[this.num];
        for (int j = 0; j < this.num; ++j) {
            this.angle[j] = 360 * this.values[j][n2] / this.total;
        }
        graphics.setFont(this.font);
        this.a = 0;
        this.b = 0;
        for (int k = 0; k < this.num; ++k) {
            if (k == 0) {
                graphics.setColor(Color.red);
            }
            else if (k == 1) {
                graphics.setColor(Color.blue);
            }
            else if (k == 2) {
                graphics.setColor(Color.green);
            }
            else if (k == 3) {
                graphics.setColor(Color.orange);
            }
            else {
                graphics.setColor(Color.yellow);
            }
            graphics.drawString("" + this.name[k], 40, this.b);
            graphics.fillArc(150, 60, 200, 200, this.a, this.angle[k]);
            this.a += this.angle[k];
            this.b += 20;
        }
    }
    
    public void getdata(final int kind) {
        this.kind = kind;
    }
    
    public void redraw(final boolean submit, final int n) {
        this.submit = submit;
        this.witch = n - 1;
        this.repaint();
    }
    
    NFCanvas() {
        this.a = 10;
        this.b = 40;
        this.submit = false;
        this.min = 1.410065408E9;
        this.decode = 1.0;
    }
}
