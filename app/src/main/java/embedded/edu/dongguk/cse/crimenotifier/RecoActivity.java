package embedded.edu.dongguk.cse.crimenotifier;

import android.app.Activity;
import android.os.Bundle;

import com.perples.recosdk.RECOBeaconManager;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECOServiceConnectListener;

import java.util.ArrayList;

public abstract class RecoActivity extends Activity implements RECOServiceConnectListener {
    protected RECOBeaconManager mRecoManager;
    protected ArrayList<RECOBeaconRegion> mRegions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecoManager = RECOBeaconManager.getInstance(getApplicationContext(), NotifyActivity.SCAN_RECO_ONLY, NotifyActivity.ENABLE_BACKGROUND_RANGING_TIMEOUT);
        mRegions = this.generateBeaconRegion();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private ArrayList<RECOBeaconRegion> generateBeaconRegion() {
        ArrayList<RECOBeaconRegion> regions = new ArrayList<RECOBeaconRegion>();

        RECOBeaconRegion recoRegion;
        recoRegion = new RECOBeaconRegion(NotifyActivity.RECO_UUID, "RECO Sample Region");
        regions.add(recoRegion);

        return regions;
    }

    protected abstract void start(ArrayList<RECOBeaconRegion> regions);
    protected abstract void stop(ArrayList<RECOBeaconRegion> regions);
}
