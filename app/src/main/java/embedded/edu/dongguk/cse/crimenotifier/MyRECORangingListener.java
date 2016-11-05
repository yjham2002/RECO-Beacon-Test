package embedded.edu.dongguk.cse.crimenotifier;

import com.perples.recosdk.RECOBeacon;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECOErrorCode;
import com.perples.recosdk.RECORangingListener;

import java.util.Collection;

/**
 * Created by HP on 2016-11-05.
 */
public class MyRECORangingListener implements RECORangingListener {

    public void didRangeBeaconsInRegion(Collection<RECOBeacon> recoBeacons, RECOBeaconRegion recoRegion) {
        //ranging 중인 region에서 1초 간격으로 감지된
        //RECOBeacon들 리스트와 함께 이 callback 메소드를 호출합니다.
        //recoRegion에서 감지된 RECOBeacon 리스트 수신 시 작성 코드
    }

    public void rangingBeaconsDidFailForRegion(RECOBeaconRegion recoRegion, RECOErrorCode errorCode) {
        //ranging을 정상적으로 시작하지 못했을 경우 이 callback 메소드가 호출됩니다.
        //RECOErrorCode는 "Error Code"를 확인하시기 바랍니다.
        //ranging 실패 시 코드 작성
    }
}