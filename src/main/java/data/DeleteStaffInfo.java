package data;

import java.util.HashMap;
import java.util.List;

public class DeleteStaffInfo {
    private HashMap<String, Integer[]> map = new HashMap<>();

    public DeleteStaffInfo(){
//        map.put("胡婷", new Integer[]{1});
//        map.put("谢辉",new Integer[]{1});
//        map.put("廖庆九",new Integer[]{1,2,3});
//        map.put("汪玲",new Integer[]{1,2,3});
//        map.put("袁平",new Integer[]{1,2,3});
//        map.put("姚家全",new Integer[]{1,2,3});
//        map.put("吴舞旌",new Integer[]{1,2,3});
//        map.put("王姗",new Integer[]{1,2,3});
//        map.put("陈飞",new Integer[]{1,2,3});
//        map.put("尹恒",new Integer[]{1,2,3});
//        map.put("陈家金",new Integer[]{1,2,3});
//        map.put("伍绍全",new Integer[]{1,2,3});
//        map.put("丰飙",new Integer[]{1,2,3});
//        map.put("任国光",new Integer[]{1,2,3});
//        map.put("王姗",new Integer[]{1,2,3});
        map.put("聂淑芬",new Integer[]{1,2,3});
        map.put("段俊军",new Integer[]{1,2,3});

    }

    public boolean ispass(String name,int month){
        if (map.containsKey(name)) {
            Integer[] integers = map.get(name);
            for (Integer integer : integers) {
                if(integer.equals(month)){
                    return true;
                }
            }
        }
        return false;
    }

    public HashMap<String, Integer[]> getMap() {
        return map;
    }
}
