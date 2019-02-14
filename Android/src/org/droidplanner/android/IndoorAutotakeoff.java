package org.droidplanner.android;

import org.droidplanner.android.constant.code.ConstantCode;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Borhan Uddin on 17-Jan-19.
 */
public class IndoorAutotakeoff {

    public JSONObject jsonAutoTakeoffIndoor(String alt, String duration) throws JSONException {
        JSONObject json = null;
        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.ACTION_TAKEOFF + "\"" + ","
                + "\"" + ConstantCode.automatic_takeoff_time_duration + "\":" + "\"" + duration + "\"" + ","
                + "\"" + ConstantCode.DATA_STRING + "\":" + alt + "}");
        return json;
    }

    public JSONObject jsonSetDefaultValue() throws JSONException {
        JSONObject json = null;
        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.DEFAULT_RC_OVERRIDE + "\"" + "}");
        return json;
    }
}
