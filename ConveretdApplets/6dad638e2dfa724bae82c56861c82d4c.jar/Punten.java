import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.Serializable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Punten implements Serializable
{
    protected Punt[][] punt;
    
    public Punten(final int n, final int n2) {
        this.punt = new Punt[(n - 6) / 10 + 1][(n2 - 6) / 10 + 1];
        for (int i = 0; i < this.punt.length; ++i) {
            for (int j = 0; j < this.punt[0].length; ++j) {
                this.punt[i][j] = new Punt(5 + i * 10, 5 + j * 10);
            }
        }
    }
    
    public void paint(final Graphics graphics, final ImageObserver imageObserver) {
        for (int i = 0; i < this.punt.length; ++i) {
            for (int j = 0; j < this.punt[0].length; ++j) {
                this.punt[i][j].paint(graphics, imageObserver);
            }
        }
    }
    
    public void activeer() {
        for (int i = 0; i < this.punt.length; ++i) {
            for (int j = 0; j < this.punt[0].length; ++j) {
                if (this.punt[i][j].invoer instanceof Switch) {
                    this.punt[i][j].invoer.activeer(false, null);
                }
            }
        }
    }
    
    public void deActiveer() {
        for (int i = 0; i < this.punt.length; ++i) {
            for (int j = 0; j < this.punt[0].length; ++j) {
                if (this.punt[i][j].invoer instanceof Switch) {
                    this.punt[i][j].invoer.deActiveer();
                }
            }
        }
    }
    
    public int getMouseX(final int n, final int n2) {
        switch (n) {
            case 1:
            case 2: {
                return Math.max(5, Math.min(5 + n2 - n2 % 10, 5 + (this.punt.length - 1) * 10));
            }
            case 3: {
                return Math.max(15, Math.min(5 + n2 - n2 % 10, 5 + (this.punt.length - 2) * 10));
            }
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11: {
                return Math.max(25, Math.min(5 + n2 - n2 % 10, 5 + (this.punt.length - 3) * 10));
            }
            default: {
                return -1;
            }
        }
    }
    
    public int getMouseY(final int n, final int n2) {
        switch (n) {
            case 1:
            case 2: {
                return Math.max(5, Math.min(5 + n2 - n2 % 10, 5 + (this.punt[0].length - 1) * 10));
            }
            case 3: {
                return Math.max(15, Math.min(5 + n2 - n2 % 10, 5 + (this.punt[0].length - 2) * 10));
            }
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11: {
                return Math.max(25, Math.min(5 + n2 - n2 % 10, 5 + (this.punt[0].length - 3) * 10));
            }
            default: {
                return -1;
            }
        }
    }
    
    public boolean isSwitch(int n, int n2) {
        n /= 10;
        n2 /= 10;
        return this.punt[n][n2].richting[0] instanceof Switch;
    }
    
    public void setSwitch(int n, int n2) {
        n /= 10;
        n2 /= 10;
        if (this.punt[n][n2].richting[0] instanceof Switch) {
            this.punt[n][n2].richting[0].activeer(true, null);
        }
    }
    
    public String setLine(int n, int n2, int n3, int n4) {
        n = (this.getMouseX(2, n) - 5) / 10;
        n2 = (this.getMouseY(2, n2) - 5) / 10;
        n3 = (this.getMouseX(2, n3) - 5) / 10;
        n4 = (this.getMouseY(2, n4) - 5) / 10;
        if (Math.abs(n3 - n) <= Math.abs(n4 - n2)) {
            n3 = n;
        }
        else {
            n4 = n2;
        }
        if (n3 == n && n4 == n2) {
            return "Line needs length";
        }
        if (n3 == n) {
            if (n4 > n2) {
                for (int i = 1; i < n4 - n2; ++i) {
                    if (this.punt[n][n2 + i].hasLine3()) {
                        return "No room";
                    }
                }
                if (this.punt[n][n2].richting[0] != null || this.punt[n][n4].richting[2] != null || this.punt[n][n4].invoer != null) {
                    return "Not possible";
                }
            }
            else {
                for (int j = 1; j < n2 - n4; ++j) {
                    if (this.punt[n][n4 + j].hasLine3()) {
                        return "No room";
                    }
                }
                if (this.punt[n][n4].richting[0] != null || this.punt[n][n2].richting[2] != null || this.punt[n][n4].invoer != null) {
                    return "Not possible";
                }
            }
            if (this.punt[n][n2].hasLine2() || this.punt[n3][n4].hasLine2()) {
                return "no connecting to middle of lines";
            }
        }
        else {
            if (n3 > n) {
                for (int k = 1; k < n3 - n; ++k) {
                    if (this.punt[n + k][n2].hasLine4()) {
                        return "No room";
                    }
                }
                if (this.punt[n][n2].richting[1] != null || this.punt[n3][n2].richting[3] != null || this.punt[n3][n2].invoer != null) {
                    return "Not possible";
                }
            }
            else {
                for (int l = 1; l < n - n3; ++l) {
                    if (this.punt[n3 + l][n2].hasLine4()) {
                        return "No room";
                    }
                }
                if (this.punt[n3][n2].richting[1] != null || this.punt[n][n2].richting[3] != null || this.punt[n3][n2].invoer != null) {
                    return "Not possible";
                }
            }
            if (this.punt[n][n2].hasLine() || this.punt[n3][n4].hasLine()) {
                return "no connecting to middle of lines";
            }
        }
        final Lijn invoer = new Lijn(this.punt[n][n2], this.punt[n3][n4]);
        this.punt[n3][n4].invoer = invoer;
        if (this.punt[n][n2].lusControle(invoer)) {
            this.punt[n3][n4].invoer = null;
            return "No loops pls";
        }
        if (n3 == n) {
            if (n4 > n2) {
                for (int n5 = 1; n5 < n4 - n2; ++n5) {
                    this.punt[n][n2 + n5].richting[0] = invoer;
                    this.punt[n][n2 + n5].richting[2] = invoer;
                }
                this.punt[n][n2].richting[0] = invoer;
                this.punt[n][n4].richting[2] = invoer;
            }
            else {
                for (int n6 = 1; n6 < n2 - n4; ++n6) {
                    this.punt[n][n4 + n6].richting[0] = invoer;
                    this.punt[n][n4 + n6].richting[2] = invoer;
                }
                this.punt[n][n4].richting[0] = invoer;
                this.punt[n][n2].richting[2] = invoer;
            }
        }
        else if (n3 > n) {
            for (int n7 = 1; n7 < n3 - n; ++n7) {
                this.punt[n + n7][n2].richting[1] = invoer;
                this.punt[n + n7][n2].richting[3] = invoer;
            }
            this.punt[n][n2].richting[1] = invoer;
            this.punt[n3][n2].richting[3] = invoer;
        }
        else {
            for (int n8 = 1; n8 < n - n3; ++n8) {
                this.punt[n3 + n8][n2].richting[1] = invoer;
                this.punt[n3 + n8][n2].richting[3] = invoer;
            }
            this.punt[n3][n2].richting[1] = invoer;
            this.punt[n][n2].richting[3] = invoer;
        }
        return "Successfull";
    }
    
    public String setVerbinding(int n, int n2, final int n3) {
        n = (this.getMouseX(n3, n) - 5) / 10;
        n2 = (this.getMouseY(n3, n2) - 5) / 10;
        Label_0515: {
            switch (n3) {
                case 3: {
                    for (int i = 0; i < 4; ++i) {
                        if (this.punt[n][n2].richting[i] != null) {
                            return "No room";
                        }
                    }
                    if (this.punt[n - 1][n2].richting[1] != null || this.punt[n + 1][n2].richting[3] != null || this.punt[n + 1][n2].invoer != null || this.punt[n + 1][n2].hasLine() || this.punt[n - 1][n2].hasLine()) {
                        return "Not possible";
                    }
                    break;
                }
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11: {
                    for (int j = -1; j <= 1; ++j) {
                        for (int k = -1; k <= 1; ++k) {
                            for (int l = 0; l < 4; ++l) {
                                if (this.punt[n + j][n2 + k].richting[l] != null) {
                                    return "No room";
                                }
                            }
                        }
                    }
                    switch (n3) {
                        case 10: {
                            if (this.punt[n + 2][n2].richting[3] != null || this.punt[n + 2][n2].invoer != null || this.punt[n + 2][n2].hasLine()) {
                                return "Not possible";
                            }
                            break Label_0515;
                        }
                        case 11: {
                            if (this.punt[n - 2][n2].richting[1] != null || this.punt[n - 2][n2].hasLine()) {
                                return "Not possible";
                            }
                            break Label_0515;
                        }
                        default: {
                            if (this.punt[n - 2][n2 - 1].richting[1] != null || this.punt[n - 2][n2 + 1].richting[1] != null || this.punt[n + 2][n2].richting[3] != null || this.punt[n + 2][n2].invoer != null || this.punt[n - 2][n2 - 1].hasLine() || this.punt[n - 2][n2 + 1].hasLine() || this.punt[n + 2][n2].hasLine()) {
                                return "Not possible";
                            }
                            break Label_0515;
                        }
                    }
                    break;
                }
            }
        }
        Verbinding verbinding = null;
        switch (n3) {
            case 1: {
                for (int n4 = 0; n4 < 4; ++n4) {
                    if (this.punt[n][n2].richting[n4] != null) {
                        verbinding = this.punt[n][n2].richting[n4];
                        break;
                    }
                }
                if (verbinding == null) {
                    return "no object to delete";
                }
                for (int n5 = 0; n5 < this.punt.length; ++n5) {
                    for (int n6 = 0; n6 < this.punt[0].length; ++n6) {
                        for (int n7 = 0; n7 < 4; ++n7) {
                            if (this.punt[n5][n6].richting[n7] == verbinding) {
                                this.punt[n5][n6].richting[n7] = null;
                            }
                        }
                        if (this.punt[n5][n6].invoer == verbinding) {
                            this.punt[n5][n6].invoer = null;
                        }
                    }
                }
                return "object deleted";
            }
            case 3: {
                final LijnNot lijnNot = new LijnNot(this.punt[n - 1][n2], this.punt[n + 1][n2]);
                this.punt[n + 1][n2].invoer = lijnNot;
                if (this.punt[n - 1][n2].lusControle(lijnNot)) {
                    this.punt[n + 1][n2].invoer = null;
                    return "No loops pls";
                }
                this.punt[n - 1][n2].richting[1] = lijnNot;
                this.punt[n + 1][n2].richting[3] = lijnNot;
                this.punt[n + 1][n2].invoer = lijnNot;
                for (int n8 = 0; n8 < 4; ++n8) {
                    this.punt[n][n2].richting[n8] = lijnNot;
                }
                break;
            }
            case 10: {
                final Switch invoer = new Switch(this.punt[n + 2][n2]);
                this.punt[n + 2][n2].richting[3] = invoer;
                this.punt[n + 2][n2].invoer = invoer;
                for (int n9 = -1; n9 <= 1; ++n9) {
                    for (int n10 = -1; n10 <= 1; ++n10) {
                        for (int n11 = 0; n11 < 4; ++n11) {
                            this.punt[n + n9][n2 + n10].richting[n11] = invoer;
                        }
                    }
                }
                return "succesfull";
            }
            case 11: {
                final Lamp lamp = new Lamp(this.punt[n - 2][n2]);
                this.punt[n - 2][n2].richting[1] = lamp;
                for (int n12 = -1; n12 <= 1; ++n12) {
                    for (int n13 = -1; n13 <= 1; ++n13) {
                        for (int n14 = 0; n14 < 4; ++n14) {
                            this.punt[n + n12][n2 + n13].richting[n14] = lamp;
                        }
                    }
                }
                return "Succesfull";
            }
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9: {
                final Poort poort = new Poort(this.punt[n - 2][n2 - 1], this.punt[n - 2][n2 + 1], this.punt[n + 2][n2], n3 - 4);
                this.punt[n + 2][n2].invoer = poort;
                if (this.punt[n - 2][n2 - 1].lusControle(poort) || this.punt[n - 2][n2 + 1].lusControle(poort)) {
                    this.punt[n + 2][n2].invoer = null;
                    return "No loops pls";
                }
                this.punt[n - 2][n2 - 1].richting[1] = poort;
                this.punt[n - 2][n2 + 1].richting[1] = poort;
                this.punt[n + 2][n2].richting[3] = poort;
                this.punt[n + 2][n2].invoer = poort;
                for (int n15 = -1; n15 <= 1; ++n15) {
                    for (int n16 = -1; n16 <= 1; ++n16) {
                        for (int n17 = 0; n17 < 4; ++n17) {
                            this.punt[n + n15][n2 + n16].richting[n17] = poort;
                        }
                    }
                }
                break;
            }
        }
        return "Successfull";
    }
}
