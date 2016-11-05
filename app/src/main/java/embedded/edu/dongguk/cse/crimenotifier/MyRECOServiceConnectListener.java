package embedded.edu.dongguk.cse.crimenotifier;

import com.perples.recosdk.RECOBeaconManager;
import com.perples.recosdk.RECOErrorCode;
import com.perples.recosdk.RECOServiceConnectListener;


public class MyRECOServiceConnectListener implements RECOServiceConnectListener {

    public void onServiceConnect() {
        //RECOBeaconService와 연결 시 코드 작성
    }

    public void onServiceFail(RECOErrorCode errorCode) {
        //RECOBeaconService와 연결 되지 않았을 시 코드 작성
    }
}