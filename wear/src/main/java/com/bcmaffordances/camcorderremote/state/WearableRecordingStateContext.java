package com.bcmaffordances.camcorderremote.state;

import com.bcmaffordances.camcorderremote.ActionFragment;
import com.bcmaffordances.wearableconnector.CamcorderRemoteConstants;
import com.bcmaffordances.wearableconnector.WearableConnector;

/**
 * Created by bmullins on 11/2/14.
 */
public class WearableRecordingStateContext {

    private WearableRecordingState mCurrentState;
    private WearableConnector mWearableConnector;

    public WearableRecordingStateContext(WearableConnector wearableConnector) {
        mWearableConnector = wearableConnector;
        mCurrentState = new UninitializedState();
    }

    public void changeState(WearableRecordingState newState) {
        mCurrentState = newState;
    }

    public void changeState(String message) {
        if (message.equals(CamcorderRemoteConstants.RESPONSE_RECORDING) ||
                message.equals(CamcorderRemoteConstants.RESPONSE_RESUMED))
        {
            mCurrentState = new RecordingState();
        }
        else if (message.equals(CamcorderRemoteConstants.RESPONSE_PAUSED)) {
            mCurrentState = new PausedState();
        }
        else if (message.equals(CamcorderRemoteConstants.RESPONSE_STOPPED)) {
            mCurrentState = new ReadyState();
        }
    }

    public ActionFragment getRecordActionFragment() {
        return mCurrentState.getRecordActionFragment(mWearableConnector);
    }

    public ActionFragment getStopActionFragment() {
        return mCurrentState.getStopActionFragment(mWearableConnector);
    }
}
