// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.Frame;
import jagoclient.Global;
import jagoclient.dialogs.Question;

class CloseQuestion extends Question
{
    GoFrame GF;
    boolean Result;
    
    public CloseQuestion(final GoFrame gf) {
        super(gf, Global.resourceString("Really_trash_this_board_"), Global.resourceString("Close_Board"), gf, true);
        this.Result = false;
        this.GF = gf;
        this.show();
    }
    
    public void tell(final Question question, final Object o, final boolean result) {
        question.setVisible(false);
        question.dispose();
        this.Result = result;
    }
}
