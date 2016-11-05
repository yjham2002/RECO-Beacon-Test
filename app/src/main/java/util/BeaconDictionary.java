package util;

import java.util.HashMap;

public class BeaconDictionary {
    public static String getBeaconName(int minor){
        String result = "정보없음";
        switch (minor){
            case 21225: result = BEACON_NAMES[0]; break;
            case 21222: result = BEACON_NAMES[1]; break;
            case 21226: result = BEACON_NAMES[2]; break;
            default: break;
        }
        return result;
    }
    public static final String[] BEACON_NAMES = {"임베디드 랩실", "신공학관 6층 화장실 좌측 복도", "임베디드 랩실 창가"};
}
