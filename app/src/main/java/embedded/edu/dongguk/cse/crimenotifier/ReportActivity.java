package embedded.edu.dongguk.cse.crimenotifier;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.perples.recosdk.RECOBeacon;
import com.perples.recosdk.RECOBeaconRegion;
import com.perples.recosdk.RECOErrorCode;
import com.perples.recosdk.RECORangingListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import util.BeaconDictionary;

public class ReportActivity extends RecoActivity implements RECORangingListener, View.OnClickListener {

    private static final int THRESHOLD = 0;

    private Button _submit;
    private TextView _location, _time;

    private boolean isAlreadySet = false;
    private String currentPos = "정보 없음";

    private ProgressDialog progressDialog;

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.report_apply:
                onUpload();
                break;
            default: break;
        }
    }

    public void onUpload(){

    }

    protected void init(){

        _submit = (Button)findViewById(R.id.report_apply);
        _submit.setOnClickListener(this);
        _location = (TextView)findViewById(R.id.report_location);
        _time = (TextView)findViewById(R.id.report_time);
        Date now = new Date();
        _time.setText(now.toString());
        isAlreadySet = false;
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.wait_message));
        progressDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        startService(new Intent(this, RecoBackgroundRangingService.class));

        init();

        mRecoManager.setRangingListener(this);
        mRecoManager.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.stop(mRegions);
        this.unbind();
    }

    private void unbind() {
        try {
            mRecoManager.unbind();
        } catch (RemoteException e) {
            Log.i("RECORangingActivity", "Remote Exception");
            e.printStackTrace();
        }
    }

    @Override
    public void onServiceConnect() {
        Log.i("RECORangingActivity", "onServiceConnect()");
        mRecoManager.setDiscontinuousScan(NotifyActivity.DISCONTINUOUS_SCAN);
        this.start(mRegions);
    }

    @Override
    public void didRangeBeaconsInRegion(Collection<RECOBeacon> recoBeacons, RECOBeaconRegion recoRegion) {
        Log.i("RECORangingActivity", "didRangeBeaconsInRegion() region: " + recoRegion.getUniqueIdentifier() + ", number of beacons ranged: " + recoBeacons.size());
        //!isAlreadySet
            if (recoBeacons.size() > THRESHOLD) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), recoBeacons.size() + "대의 Beacon으로부터 위치 수신됨", Toast.LENGTH_LONG).show();
                currentPos = BeaconDictionary.getBeaconName(new ArrayList<>(recoBeacons).get(0).getMinor());
                _location.setText(currentPos);
                isAlreadySet = true;
                stopService(new Intent(this, RecoBackgroundRangingService.class));
                this.stop(mRegions);
                this.unbind();

            }
    }

    @Override
    protected void start(ArrayList<RECOBeaconRegion> regions) {
        for(RECOBeaconRegion region : regions) {
            try {
                mRecoManager.startRangingBeaconsInRegion(region);
            } catch (RemoteException e) {
                Log.i("RECORangingActivity", "Remote Exception");
                e.printStackTrace();
            } catch (NullPointerException e) {
                Log.i("RECORangingActivity", "Null Pointer Exception");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void stop(ArrayList<RECOBeaconRegion> regions) {
        for(RECOBeaconRegion region : regions) {
            try {
                mRecoManager.stopRangingBeaconsInRegion(region);
            } catch (RemoteException e) {
                Log.i("RECORangingActivity", "Remote Exception");
                e.printStackTrace();
            } catch (NullPointerException e) {
                Log.i("RECORangingActivity", "Null Pointer Exception");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceFail(RECOErrorCode errorCode) {
        return;
    }

    @Override
    public void rangingBeaconsDidFailForRegion(RECOBeaconRegion region, RECOErrorCode errorCode) {
        Log.i("RECORangingActivity", "error code = " + errorCode);
        return;
    }
}
