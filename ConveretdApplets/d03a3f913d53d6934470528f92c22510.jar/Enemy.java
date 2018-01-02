import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

final class Enemy extends Ship
{
    private Data data;
    private int id;
    private int type;
    private String face1;
    private String face2;
    
    public Enemy(final Data data, final int n) {
        this.id = n;
        this.data = data;
        this.setDirection(1);
        this.setType(1);
        if (n * 50 < 210) {
            super.xco = n * 50;
            super.yco = 30;
            data.idOld = n + 1;
        }
        else if ((n - data.idOld) * 50 < 210 && !data.inOrder()) {
            super.xco = (n - data.idOld) * 50;
            super.yco = 90;
        }
        else {
            if (!data.inOrder()) {
                data.setInOrder(true);
                data.idOld = n;
            }
            super.xco = (n - data.idOld) * 50;
            super.yco = 140;
        }
    }
    
    public void calcMove() {
        int n;
        if (this.data.bossMode) {
            n = 40;
        }
        else {
            n = 0;
        }
        if (this.isAlive()) {
            if (super.direction > 0) {
                if (super.xco < 370 - n) {
                    if (this.data.enemyKilled < 8) {
                        ++super.xco;
                    }
                    else if (this.data.enemyKilled > 7) {
                        super.xco += 2;
                    }
                    if (this.data.getLevel() > 11) {
                        ++super.xco;
                    }
                    if (this.data.bossMode) {
                        super.xco += 2;
                    }
                }
                else {
                    this.changeDirectionAll();
                }
            }
            else if (super.direction < 0) {
                if (super.xco > 2) {
                    if (this.data.enemyKilled < 8) {
                        --super.xco;
                    }
                    else if (this.data.enemyKilled > 7) {
                        super.xco -= 2;
                    }
                    if (this.data.getLevel() > 11) {
                        --super.xco;
                    }
                    if (this.data.bossMode) {
                        super.xco -= 2;
                    }
                }
                else {
                    this.changeDirectionAll();
                }
            }
        }
        if (super.yco > 260 - n) {
            this.explode(10);
            this.data.player.explode(20);
            this.data.playerKilled();
            this.data.inv.explodeS.play();
            this.data.inv.field.gameOver();
        }
    }
    
    public void changeDirectionAll() {
        for (int i = 0; i < this.data.getEnemyNumber(); ++i) {
            this.data.enemies[i].drop();
            this.data.enemies[i].changeDirection();
        }
    }
    
    public Image getFace() {
        if (!super.exploding) {
            this.animate();
            if (super.defaultFace) {
                return this.data.getGraphic(this.face1);
            }
            return this.data.getGraphic(this.face2);
        }
        else {
            this.explode(10);
            if (super.explodeCount % 4 == 0) {
                return this.data.getGraphic("explode1G");
            }
            return this.data.getGraphic("explode2G");
        }
    }
    
    public int getType() {
        return this.type;
    }
    
    public void randAttack() {
        double n = 0.0;
        if (this.type == 3) {
            if (this.getXco() - this.data.player.getXco() < 5 && this.getXco() - this.data.player.getXco() > -5) {
                n = Math.random() * this.data.getRandomMod();
                if ((int)n % 5 == 0) {
                    n = 1.0;
                }
            }
        }
        else if (this.type == 4) {
            if (this.getXco() - this.data.player.getXco() < 90 && this.getXco() - this.data.player.getXco() > 80) {
                n = Math.random() * this.data.getRandomMod();
                if ((int)n % 5 == 0) {
                    n = 1.0;
                }
            }
            else if (this.getXco() - this.data.player.getXco() > -90 && this.getXco() - this.data.player.getXco() < -80) {
                n = Math.random() * this.data.getRandomMod();
                if ((int)n % 5 == 0) {
                    n = 1.0;
                }
            }
        }
        if (n != 1.0) {
            n = Math.random() * this.data.getRandomMod();
            if (this.type == 2 && n >= 2.0) {
                n /= 2.0;
            }
        }
        if ((int)n == 1) {
            for (int i = 0; i < this.data.getEnemyFire(); ++i) {
                if (!this.data.enemyFire[i].isAlive()) {
                    this.data.enemyFire[i].setXco(this.getXco() + 10);
                    this.data.enemyFire[i].setYco(this.getYco() + 10);
                    this.data.enemyFire[i].setDirection(1);
                    int n2 = 0;
                    if (this.data.bossMode) {
                        n2 = 2;
                    }
                    if (this.getType() == 1) {
                        this.data.enemyFire[i].setSpeed(3 + n2);
                    }
                    else if (this.getType() == 2) {
                        this.data.enemyFire[i].setSpeed(4 + n2);
                    }
                    else if (this.getType() == 3) {
                        this.data.enemyFire[i].setSpeed(5 + n2);
                    }
                    else if (this.getType() == 4) {
                        this.data.enemyFire[i].setSpeed(6 + n2);
                    }
                    this.data.enemyFire[i].enable();
                    break;
                }
            }
        }
    }
    
    public void setType(final int n) {
        if (n == 1) {
            this.face1 = "alien1aG";
            this.face2 = "alien1bG";
            this.type = 1;
        }
        else if (n == 2) {
            this.face1 = "alien2aG";
            this.face2 = "alien2bG";
            this.type = 2;
        }
        else if (n == 3) {
            this.face1 = "alien3aG";
            this.face2 = "alien3bG";
            this.type = 3;
        }
        else {
            this.face1 = "alien4aG";
            this.face2 = "alien4bG";
            this.type = 4;
        }
    }
}
