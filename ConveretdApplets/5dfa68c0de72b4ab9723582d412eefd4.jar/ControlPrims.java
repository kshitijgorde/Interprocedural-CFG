// 
// Decompiled by Procyon v0.5.30
// 

class ControlPrims extends Primitives
{
    static String[] primlist;
    
    public String[] primlist() {
        return ControlPrims.primlist;
    }
    
    public Object dispatch(final int n, final Object[] array, final LContext lContext) {
        switch (n) {
            case 0: {
                return this.prim_repeat(array[0], array[1], lContext);
            }
            case 1: {
                return this.prim_if(array[0], array[1], lContext);
            }
            case 2: {
                return this.prim_ifelse(array[0], array[1], array[2], lContext);
            }
            case 3: {
                return this.prim_stop(lContext);
            }
            case 4: {
                return this.prim_output(array[0], lContext);
            }
            case 5: {
                return this.prim_dotimes(array[0], array[1], lContext);
            }
            case 6: {
                return this.prim_dolist(array[0], array[1], lContext);
            }
            case 7: {
                return this.prim_carefully(array[0], array[1], lContext);
            }
            case 8: {
                return lContext.errormessage;
            }
            case 9: {
                return this.prim_unwindprotect(array[0], array[1], lContext);
            }
            case 10: {
                return this.prim_error(array[0], lContext);
            }
            case 11: {
                return this.prim_dispatch(array[0], array[1], lContext);
            }
            case 12: {
                return this.prim_run(array[0], lContext);
            }
            case 13: {
                return this.prim_loop(array[0], lContext);
            }
            case 14: {
                return this.prim_loop(array[0], lContext);
            }
            case 15: {
                return this.prim_selectq(array[0], array[1], lContext);
            }
            case 16: {
                return this.prim_stopme(lContext);
            }
            default: {
                return null;
            }
        }
    }
    
    Object prim_repeat(final Object o, final Object o2, final LContext lContext) {
        final int anInt = Logo.anInt(o, lContext);
        final Object[] aList = Logo.aList(o2, lContext);
        for (int i = 0; i < anInt; ++i) {
            Logo.runCommand(aList, lContext);
            if (lContext.ufunresult != null) {
                return null;
            }
        }
        return null;
    }
    
    Object prim_if(final Object o, final Object o2, final LContext lContext) {
        if (Logo.aBoolean(o, lContext)) {
            Logo.runCommand(Logo.aList(o2, lContext), lContext);
        }
        return null;
    }
    
    Object prim_ifelse(final Object o, final Object o2, final Object o3, final LContext lContext) {
        final boolean aBoolean = Logo.aBoolean(o, lContext);
        final Object[] aList = Logo.aList(o2, lContext);
        final Object[] aList2 = Logo.aList(o3, lContext);
        return aBoolean ? Logo.runList(aList, lContext) : Logo.runList(aList2, lContext);
    }
    
    Object prim_stop(final LContext lContext) {
        lContext.ufunresult = lContext.juststop;
        return null;
    }
    
    Object prim_output(final Object ufunresult, final LContext lContext) {
        lContext.ufunresult = ufunresult;
        return null;
    }
    
    Object prim_dotimes(final Object o, final Object o2, final LContext lContext) {
        final MapList list = new MapList(Logo.aList(o, lContext));
        final Object[] aList = Logo.aList(o2, lContext);
        final Symbol aSymbol = Logo.aSymbol(list.next(), lContext);
        final int anInt = Logo.anInt(Logo.evalOneArg(list, lContext), lContext);
        Logo.checkListEmpty(list, lContext);
        final Object value = aSymbol.value;
        try {
            for (int i = 0; i < anInt; ++i) {
                aSymbol.value = new Double(i);
                Logo.runCommand(aList, lContext);
            }
            if (lContext.ufunresult != null) {
                return null;
            }
        }
        finally {
            aSymbol.value = value;
        }
        return null;
    }
    
    Object prim_dolist(final Object o, final Object o2, final LContext lContext) {
        final MapList list = new MapList(Logo.aList(o, lContext));
        final Object[] aList = Logo.aList(o2, lContext);
        final Symbol aSymbol = Logo.aSymbol(list.next(), lContext);
        final Object[] aList2 = Logo.aList(Logo.evalOneArg(list, lContext), lContext);
        Logo.checkListEmpty(list, lContext);
        final Object value = aSymbol.value;
        try {
            for (int i = 0; i < aList2.length; ++i) {
                aSymbol.value = aList2[i];
                Logo.runCommand(aList, lContext);
                if (lContext.ufunresult != null) {
                    return null;
                }
            }
        }
        finally {
            aSymbol.value = value;
        }
        return null;
    }
    
    Object prim_carefully(final Object o, final Object o2, final LContext lContext) {
        final Object[] aList = Logo.aList(o, lContext);
        final Object[] aList2 = Logo.aList(o2, lContext);
        try {
            return Logo.runList(aList, lContext);
        }
        catch (Exception ex) {
            lContext.errormessage = ex.getMessage();
            return Logo.runList(aList2, lContext);
        }
    }
    
    Object prim_unwindprotect(final Object o, final Object o2, final LContext lContext) {
        final Object[] aList = Logo.aList(o, lContext);
        final Object[] aList2 = Logo.aList(o2, lContext);
        try {
            Logo.runCommand(aList, lContext);
        }
        finally {
            Logo.runCommand(aList2, lContext);
        }
        return null;
    }
    
    Object prim_error(final Object o, final LContext lContext) {
        Logo.error(Logo.prs(o), lContext);
        return null;
    }
    
    Object prim_dispatch(final Object o, final Object o2, final LContext lContext) {
        return Logo.runList(Logo.aList(Logo.aList(o2, lContext)[Logo.anInt(o, lContext)], lContext), lContext);
    }
    
    Object prim_run(final Object o, final LContext lContext) {
        return Logo.runList(Logo.aList(o, lContext), lContext);
    }
    
    Object prim_loop(final Object o, final LContext lContext) {
        final Object[] aList = Logo.aList(o, lContext);
        do {
            Logo.runCommand(aList, lContext);
        } while (lContext.ufunresult == null);
        return null;
    }
    
    Object prim_selectq(final Object o, final Object o2, final LContext lContext) {
        final Object[] aList = Logo.aList(o2, lContext);
        for (int i = 0; i < aList.length; i += 2) {
            if (aList[i] instanceof DottedSymbol) {
                if (Logo.getValue(((DottedSymbol)aList[i]).sym, lContext).equals(o)) {
                    return Logo.runList((Object[])aList[i + 1], lContext);
                }
            }
            else if (aList[i].equals(o)) {
                return Logo.runList((Object[])aList[i + 1], lContext);
            }
        }
        return null;
    }
    
    Object prim_stopme(final LContext lContext) {
        Logo.error("", lContext);
        return null;
    }
    
    static {
        ControlPrims.primlist = new String[] { "repeat", "2", "if", "2", "ifelse", "3", "stop", "0", "output", "1", "dotimes", "2", "dolist", "2", "carefully", "2", "errormessage", "0", "unwind-protect", "2", "error", "1", "dispatch", "2", "run", "1", "loop", "1", "forever", "1", "selectq", "2", "stopme", "0" };
    }
}
