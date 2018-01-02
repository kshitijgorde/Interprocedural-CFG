import java.awt.Event;
import java.awt.Component;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class MazeControls extends Panel
{
    Checkbox hexagonalRoomsCheckbox;
    Maze3D maze3D;
    Checkbox squareRoomsCheckbox;
    
    public MazeControls(final Maze3D maze3D) {
        this.maze3D = maze3D;
        this.add(new Button("New"));
        this.add(new Button("Solve"));
        this.add(new Button("Clear"));
        (this.hexagonalRoomsCheckbox = new Checkbox("Hexagonal rooms")).setState(maze3D.hexagonalRooms);
        this.add("South", this.hexagonalRoomsCheckbox);
        (this.squareRoomsCheckbox = new Checkbox("Square rooms")).setState(!maze3D.hexagonalRooms);
        this.add("South", this.squareRoomsCheckbox);
        this.add(new Button("About"));
    }
    
    public boolean keyDown(final Event event, final int n) {
        boolean b;
        if (this.maze3D.mazeCanvas.p.alreadyPainting) {
            b = false;
        }
        else if (this.maze3D.solutionDisplayed || this.maze3D.mazeCanvas.p.userHasSolved) {
            this.maze3D.message.setText("");
            b = false;
        }
        else {
            if (this.maze3D.hexagonalRooms) {
                this.maze3D.message.setText("Use Home, Up Arrow, PgUp, End, Down Arrow, or PgDn to solve.");
            }
            else {
                this.maze3D.message.setText("Use the arrow keys to solve.");
            }
            if (this.maze3D.hexagonalRooms) {
                switch (n) {
                    case 1006: {
                        b = true;
                        break;
                    }
                    case 52: {
                        b = true;
                        break;
                    }
                    case 1007: {
                        b = true;
                        break;
                    }
                    case 54: {
                        b = true;
                        break;
                    }
                    case 1004: {
                        this.maze3D.mazeCanvas.p.hexKey(2);
                        b = true;
                        break;
                    }
                    case 56: {
                        this.maze3D.mazeCanvas.p.hexKey(2);
                        b = true;
                        break;
                    }
                    case 1005: {
                        this.maze3D.mazeCanvas.p.hexKey(3);
                        b = true;
                        break;
                    }
                    case 50: {
                        this.maze3D.mazeCanvas.p.hexKey(3);
                        b = true;
                        break;
                    }
                    case 1000: {
                        this.maze3D.mazeCanvas.p.hexKey(0);
                        b = true;
                        break;
                    }
                    case 55: {
                        this.maze3D.mazeCanvas.p.hexKey(0);
                        b = true;
                        break;
                    }
                    case 1001: {
                        this.maze3D.mazeCanvas.p.hexKey(1);
                        b = true;
                        break;
                    }
                    case 49: {
                        this.maze3D.mazeCanvas.p.hexKey(1);
                        b = true;
                        break;
                    }
                    case 1002: {
                        this.maze3D.mazeCanvas.p.hexKey(4);
                        b = true;
                        break;
                    }
                    case 57: {
                        this.maze3D.mazeCanvas.p.hexKey(4);
                        b = true;
                        break;
                    }
                    case 1003: {
                        this.maze3D.mazeCanvas.p.hexKey(5);
                        b = true;
                        break;
                    }
                    case 51: {
                        this.maze3D.mazeCanvas.p.hexKey(5);
                        b = true;
                        break;
                    }
                    default: {
                        b = false;
                        break;
                    }
                }
            }
            else {
                switch (n) {
                    case 1006: {
                        this.maze3D.mazeCanvas.p.sqrKey(0);
                        b = true;
                        break;
                    }
                    case 52: {
                        this.maze3D.mazeCanvas.p.sqrKey(0);
                        b = true;
                        break;
                    }
                    case 1007: {
                        this.maze3D.mazeCanvas.p.sqrKey(2);
                        b = true;
                        break;
                    }
                    case 54: {
                        this.maze3D.mazeCanvas.p.sqrKey(2);
                        b = true;
                        break;
                    }
                    case 1004: {
                        this.maze3D.mazeCanvas.p.sqrKey(3);
                        b = true;
                        break;
                    }
                    case 56: {
                        this.maze3D.mazeCanvas.p.sqrKey(3);
                        b = true;
                        break;
                    }
                    case 1005: {
                        this.maze3D.mazeCanvas.p.sqrKey(1);
                        b = true;
                        break;
                    }
                    case 50: {
                        this.maze3D.mazeCanvas.p.sqrKey(1);
                        b = true;
                        break;
                    }
                    case 1000: {
                        b = true;
                        break;
                    }
                    case 55: {
                        b = true;
                        break;
                    }
                    case 1001: {
                        b = true;
                        break;
                    }
                    case 49: {
                        b = true;
                        break;
                    }
                    case 1002: {
                        b = true;
                        break;
                    }
                    case 57: {
                        b = true;
                        break;
                    }
                    case 1003: {
                        b = true;
                        break;
                    }
                    case 51: {
                        b = true;
                        break;
                    }
                    default: {
                        b = false;
                        break;
                    }
                }
            }
            if (this.maze3D.mazeCanvas.p.userHasSolved) {
                this.maze3D.message.setText("Congratulations!");
            }
        }
        return b;
    }
    
    public boolean action(final Event event, final Object o) {
        boolean b;
        if (event.target instanceof Button) {
            final String s = (String)o;
            if (s.equals("New")) {
                this.maze3D.solutionDisplayed = false;
                this.maze3D.mazeCanvas.previousHeight = 0;
                this.maze3D.mazeCanvas.previousWidth = 0;
                this.maze3D.mazeCanvas.paint(this.maze3D.mazeCanvas.getGraphics());
            }
            else if (s.equals("Solve")) {
                this.maze3D.message.setText("");
                this.maze3D.solutionDisplayed = true;
                if (this.maze3D.mazeCanvas.p.alreadyPainting) {
                    this.maze3D.mazeCanvas.paint(this.maze3D.mazeCanvas.getGraphics());
                }
                else if (this.maze3D.hexagonalRooms) {
                    this.maze3D.mazeCanvas.p.hexDisplaySolution();
                }
                else {
                    this.maze3D.mazeCanvas.p.sqrDisplaySolution();
                }
            }
            else if (s.equals("Clear")) {
                if (this.maze3D.hexagonalRooms) {
                    this.maze3D.message.setText("Use Home, Up Arrow, PgUp, End, Down Arrow, or PgDn to solve.");
                }
                else {
                    this.maze3D.message.setText("Use the arrow keys to solve.");
                }
                this.maze3D.solutionDisplayed = false;
                this.maze3D.clearUserAttempts = true;
                this.maze3D.mazeCanvas.paint(this.maze3D.mazeCanvas.getGraphics());
            }
            else if (s.equals("About")) {
                this.maze3D.message.setText("Copyright (c) 1996 James L. Dean.  This program may be distributed or used without payment to its author.");
            }
            b = true;
        }
        else if (event.target == this.hexagonalRoomsCheckbox) {
            this.squareRoomsCheckbox.setState(false);
            this.hexagonalRoomsCheckbox.setState(true);
            if (!this.maze3D.hexagonalRooms) {
                this.maze3D.hexagonalRooms = true;
                this.maze3D.solutionDisplayed = false;
                this.maze3D.mazeCanvas.previousHeight = 0;
                this.maze3D.mazeCanvas.previousWidth = 0;
                this.maze3D.mazeCanvas.paint(this.maze3D.mazeCanvas.getGraphics());
            }
            b = true;
        }
        else if (event.target == this.squareRoomsCheckbox) {
            this.squareRoomsCheckbox.setState(true);
            this.hexagonalRoomsCheckbox.setState(false);
            if (this.maze3D.hexagonalRooms) {
                this.maze3D.hexagonalRooms = false;
                this.maze3D.solutionDisplayed = false;
                this.maze3D.mazeCanvas.previousHeight = 0;
                this.maze3D.mazeCanvas.previousWidth = 0;
                this.maze3D.mazeCanvas.paint(this.maze3D.mazeCanvas.getGraphics());
            }
            b = true;
        }
        else {
            b = false;
        }
        return b;
    }
}
