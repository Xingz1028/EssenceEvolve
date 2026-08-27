package org.com.xing_zi.essenceevolve.items.herb.tip;

import java.util.ArrayList;
import java.util.HashSet;

//统一管理注册的药物的词条类
//注册后，请在主类添加
public class HerbToolTipRegister {
    public static final ArrayList<HerbItemTipProvider> LIST = new ArrayList<>();

    public static void register(HerbItemTipProvider herbToolTip){
        LIST.add(herbToolTip);
    }
    public static ArrayList<HerbItemTipProvider> getList(){
        return LIST;
    }
}
