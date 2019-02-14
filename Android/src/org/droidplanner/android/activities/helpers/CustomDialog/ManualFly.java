package org.droidplanner.android.activities.helpers.CustomDialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;
import org.droidplanner.android.IndoorAutotakeoff;
import org.droidplanner.android.R;
import org.droidplanner.android.SocketDataReceiver;
import org.droidplanner.android.activities.helpers.JsonBuilder;
import org.droidplanner.android.activities.interfaces.ManualCallbackListener;
import org.droidplanner.android.constant.code.ConstantCode;
import org.droidplanner.android.singleton.SingletonRC;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Borhan Uddin on 7/18/2018.
 */
public class ManualFly extends Dialog {
    private IndoorAutotakeoff indoorAutotakeoff=new IndoorAutotakeoff();
    public Activity c;
    private Context context = getContext();
    private String TAG = "ferdib ManualFly";
    public Dialog d;
    public Button yes;
    public Spinner spinner;
    private static final String[] paths = {"LEFT", "RIGHT"};
    private ManualCallbackListener manualCallbackListener;
    private int leftorright = 0;
    private EditText pwm, time;
    //private int pitchBar = 0, pitchMax = 532, pitchMin = 307;
    private int pitchBar = 0, pitchMax = 532, pitchMin = 307;
    private int rollBar = 0, rollhMax = 532, rollMin = 307;
    private int throttleBar = 0, throttleMax = 532, throttleMin = 307;
    private int yawBar = 0, yawMax = 532, yawMin = 307, yawMiddle = 420;
    private int middleValue = 420;
    DiscreteSeekBar discreteSeekBarPitch, discreteSeekBarRoll, discreteSeekBarThrottle, discreteSeekBarYaw, discreteSeekBarRadio5, discreteSeekBarRadio6, discreteSeekBarRadio7, discreteSeekBarRadio8;
    private int channel_0 = 420, channel_1 = 420, channel_2 = 307, channel_3 = 420, channel_4 = 307, channel_5 = 307, channel_6 = 307, channel_7 = 307;
    private Button stabilize, guided, land, armed, disarmed, auto, procress50, objDetect, throttle_in, throttle_de, roll_inc, roll_dec, pitch_inc, pitch_dec, yaw_inc, yaw_dec,autofly,resetvalue;

    int count = 0, throttle_toggle_button_count = 0;
    int ultraCount = 0;

    int initialMiddleValuePitch = 420, initialMiddleValueRoll = 420, initialMiddleValueYaw = 420, initialValueThrottle;

    public ManualFly(Activity a, ManualCallbackListener manualCallbackListener) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.manualCallbackListener = manualCallbackListener;
    }

    private JSONObject jsonobjectManuall() throws JSONException {
        JSONObject json = null;

        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.MANUAL_FLY + "\"" + ","
                + "\"" + ConstantCode.CHANNEL_0 + "\":" + "\"" + channel_0 + "\","
                + "\"" + ConstantCode.CHANNEL_1 + "\":" + "\"" + channel_1 + "\","
                + "\"" + ConstantCode.CHANNEL_2 + "\":" + "\"" + channel_2 + "\","
                + "\"" + ConstantCode.CHANNEL_3 + "\":" + "\"" + channel_3 + "\","
                + "\"" + ConstantCode.CHANNEL_4 + "\":" + "\"" + channel_4 + "\","
                + "\"" + ConstantCode.CHANNEL_5 + "\":" + "\"" + channel_5 + "\","
                + "\"" + ConstantCode.CHANNEL_6 + "\":" + "\"" + channel_6 + "\","
                + "\"" + ConstantCode.CHANNEL_7 + "\":" + "\"" + channel_7 + "\"" +
                "}");

        Log.d("ferdibt", String.valueOf(channel_0));

        return json;
    }

    private JSONObject jsonobjectManuallPitch() throws JSONException {
        JSONObject json = null;
        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.pitchSeek + "\"" + ","
                + "\"" + ConstantCode.CHANNEL_0 + "\":" + "\"" + channel_0 + "\"" +
                "}");
        return json;
    }

    private JSONObject jsonobjectManuallRoll() throws JSONException {
        JSONObject json = null;
        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.rollSeek + "\"" + ","
                + "\"" + ConstantCode.CHANNEL_1 + "\":" + "\"" + channel_1 + "\"" +
                "}");
        return json;
    }

    private JSONObject jsonobjectManuallThrottle() throws JSONException {
        JSONObject json = null;
        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.throttleSeek + "\"" + ","
                + "\"" + ConstantCode.CHANNEL_2 + "\":" + "\"" + channel_2 + "\"" +
                "}");
        return json;
    }

    private JSONObject jsonobjectManuallYaw() throws JSONException {
        JSONObject json = null;
        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.yawSeek + "\"" + ","
                + "\"" + ConstantCode.CHANNEL_3 + "\":" + "\"" + channel_3 + "\"" +
                "}");
        return json;
    }

    private JSONObject jsonobjectManuallFour() throws JSONException {
        JSONObject json = null;
        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.fourSeek + "\"" + ","
                + "\"" + ConstantCode.CHANNEL_4 + "\":" + "\"" + channel_4 + "\"" +
                "}");
        return json;
    }

    private JSONObject jsonobjectManuallFive() throws JSONException {
        JSONObject json = null;
        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.fiveSeek + "\"" + ","
                + "\"" + ConstantCode.CHANNEL_5 + "\":" + "\"" + channel_5 + "\"" +
                "}");
        return json;
    }

    private JSONObject jsonobjectManuallSix() throws JSONException {
        JSONObject json = null;
        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.sixSeek + "\"" + ","
                + "\"" + ConstantCode.CHANNEL_6 + "\":" + "\"" + channel_6 + "\"" +
                "}");
        return json;
    }

    private JSONObject jsonobjectManuallSeven() throws JSONException {
        JSONObject json = null;
        json = new JSONObject("{\"" + ConstantCode.USERNAME_KEY + "\":" + "\"" + ConstantCode.USER + "\"" + ","
                + "\"" + ConstantCode.ACTION_TYPE + "\":" + "\"" + ConstantCode.sevenSeek + "\"" + ","
                + "\"" + ConstantCode.CHANNEL_7 + "\":" + "\"" + channel_7 + "\"" +
                "}");
        return json;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.manual_oparate_dialog);
        discreteSeekBarPitch = (DiscreteSeekBar) findViewById(R.id.pitchBar);
        discreteSeekBarRoll = (DiscreteSeekBar) findViewById(R.id.rollBar);
        discreteSeekBarThrottle = (DiscreteSeekBar) findViewById(R.id.throttleBar);
        discreteSeekBarYaw = (DiscreteSeekBar) findViewById(R.id.yawBar);
        discreteSeekBarRadio5 = (DiscreteSeekBar) findViewById(R.id.simpleSeekBarRadio5);
        discreteSeekBarRadio6 = (DiscreteSeekBar) findViewById(R.id.simpleSeekBarRadio6);
        discreteSeekBarRadio7 = (DiscreteSeekBar) findViewById(R.id.simpleSeekBarRadio7);
        discreteSeekBarRadio8 = (DiscreteSeekBar) findViewById(R.id.simpleSeekBarRadio8);
        stabilize = (Button) findViewById(R.id.stabilize);
        guided = (Button) findViewById(R.id.guided);
        land = (Button) findViewById(R.id.land);
        armed = (Button) findViewById(R.id.armed);
        disarmed = (Button) findViewById(R.id.disarmed);
        auto = (Button) findViewById(R.id.auto);
        procress50 = (Button) findViewById(R.id.progress50);
        objDetect = (Button) findViewById(R.id.obj_en);
        throttle_in = (Button) findViewById(R.id.throttle_in);
        throttle_de = (Button) findViewById(R.id.throttle_de);

        roll_inc = (Button) findViewById(R.id.roll_inc);
        roll_dec = (Button) findViewById(R.id.roll_dec);
        pitch_inc = (Button) findViewById(R.id.pitch_inc);
        pitch_dec = (Button) findViewById(R.id.pitch_dec);
        yaw_inc = (Button) findViewById(R.id.yaw_inc);
        yaw_dec = (Button) findViewById(R.id.yaw_dec);
        autofly = (Button) findViewById(R.id.auto_fly);
        resetvalue = (Button) findViewById(R.id.reset_value);
        resetvalue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    SocketDataReceiver.attemptSend(indoorAutotakeoff.jsonSetDefaultValue().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        autofly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SocketDataReceiver.attemptSend(indoorAutotakeoff.jsonAutoTakeoffIndoor("1.2","10").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        pitch_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pitchInc = channel_0 + 1;
                initialMiddleValuePitch = pitchInc;
                Log.d(TAG, String.valueOf(initialMiddleValuePitch));
                discreteSeekBarPitch.setProgress(initialMiddleValuePitch);
                pitch_inc.setText("PI " + initialMiddleValuePitch);
                pitch_dec.setText("PD " + initialMiddleValuePitch);
            }
        });
        pitch_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pitchDec = channel_0 - 1;
                initialMiddleValuePitch = pitchDec;
                Log.d(TAG, String.valueOf(initialMiddleValuePitch));
                discreteSeekBarPitch.setProgress(initialMiddleValuePitch);
                pitch_inc.setText("PI " + initialMiddleValuePitch);
                pitch_dec.setText("PD " + initialMiddleValuePitch);
            }
        });

        roll_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rollInc = channel_1 + 1;
                initialMiddleValueRoll = rollInc;
                Log.d(TAG, String.valueOf(initialMiddleValueRoll));
                discreteSeekBarRoll.setProgress(initialMiddleValueRoll);
                roll_inc.setText("RI " + initialMiddleValueRoll);
                roll_dec.setText("RD " + initialMiddleValueRoll);
            }
        });
        roll_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rollDec = channel_1 - 1;
                initialMiddleValueRoll = rollDec;
                Log.d(TAG, String.valueOf(initialMiddleValueRoll));
                discreteSeekBarRoll.setProgress(initialMiddleValueRoll);
                roll_inc.setText("RI " + initialMiddleValueRoll);
                roll_dec.setText("RD " + initialMiddleValueRoll);
            }
        });

        yaw_inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int yawInc = channel_3 + 1;
                initialMiddleValueYaw = yawInc;
                Log.d(TAG, String.valueOf(initialMiddleValueYaw));
                discreteSeekBarYaw.setProgress(initialMiddleValueYaw);
                yaw_inc.setText("YI " + initialMiddleValueYaw);
                yaw_dec.setText("YD " + initialMiddleValueYaw);
            }
        });
        yaw_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int yawDec = channel_3 - 1;
                initialMiddleValueYaw = yawDec;
                Log.d(TAG, String.valueOf(initialMiddleValueYaw));
                discreteSeekBarYaw.setProgress(initialMiddleValueYaw);
                yaw_inc.setText("YI " + initialMiddleValueYaw);
                yaw_dec.setText("YD " + initialMiddleValueYaw);
            }
        });

        procress50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //discreteSeekBarThrottle.setProgress(60);
                String objCommand = "{\"u\":\"g\",\"135\":\"174\"}";
                SocketDataReceiver.attemptSend(objCommand);
                count = 1;

            }
        });

        objDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ultraCount == 0) {
                    String ultraSonicEnCmd = "{\"u\":\"g\",\"135\":\"" + ConstantCode.ultraSonicEnable + "\"}";
                    SocketDataReceiver.attemptSend(ultraSonicEnCmd);
                    ultraCount = 1;
                } else {
                    String ultraSonicDisCmd = "{\"u\":\"g\",\"135\":\"" + ConstantCode.ultraSonicDisable + "\"}";
                    SocketDataReceiver.attemptSend(ultraSonicDisCmd);
                    ultraCount = 0;
                }
            }
        });

        throttle_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String objCommand = "{\"u\":\"g\",\"135\":\"" + ConstantCode.THROTTLE_IN + "\"}";
                SocketDataReceiver.attemptSend(objCommand);
                throttle_toggle_button_count = 1;*/
                //String objCommand = "{\"u\":\"g\",\"135\":\"172\"}";
                //SocketDataReceiver.attemptSend(objCommand);

                int throttleIn = channel_2 + 1;
                initialValueThrottle = throttleIn;
                Log.d(TAG, String.valueOf(initialValueThrottle));
                discreteSeekBarThrottle.setProgress(initialValueThrottle);
                throttle_in.setText("TI " + initialValueThrottle);
                throttle_de.setText("TD " + initialValueThrottle);
            }
        });
        throttle_de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String objCommand = "{\"u\":\"g\",\"135\":\"" + ConstantCode.THROTTLE_DE + "\"}";
                SocketDataReceiver.attemptSend(objCommand);
                throttle_toggle_button_count = 1;*/
                //String objCommand2 = "{\"u\":\"g\",\"135\":\"173\"}";
                //SocketDataReceiver.attemptSend(objCommand2);
                //count = 0;

                int throttleDec = channel_2 - 1;
                initialValueThrottle = throttleDec;
                Log.d(TAG, String.valueOf(initialValueThrottle));
                discreteSeekBarThrottle.setProgress(initialValueThrottle);
                throttle_in.setText("TI " + initialValueThrottle);
                throttle_de.setText("TD " + initialValueThrottle);
            }
        });
        initComponents();

        channel_0 = SingletonRC.getInstance(context).getChannel_0();
        channel_1 = SingletonRC.getInstance(context).getChannel_1();
        channel_2 = SingletonRC.getInstance(context).getChannel_2();
        channel_3 = SingletonRC.getInstance(context).getChannel_3();
        channel_4 = SingletonRC.getInstance(context).getChannel_4();
        channel_5 = SingletonRC.getInstance(context).getChannel_5();
        channel_6 = SingletonRC.getInstance(context).getChannel_6();
        channel_7 = SingletonRC.getInstance(context).getChannel_7();

        int c0 = SingletonRC.getInstance(context).getChannel_0();
        int c1 = SingletonRC.getInstance(context).getChannel_1();
        int c2 = SingletonRC.getInstance(context).getChannel_2();
        int c3 = SingletonRC.getInstance(context).getChannel_3();
        int c4 = SingletonRC.getInstance(context).getChannel_4();
        int c5 = SingletonRC.getInstance(context).getChannel_5();
        int c6 = SingletonRC.getInstance(context).getChannel_6();
        int c7 = SingletonRC.getInstance(context).getChannel_7();

        stabilize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketDataReceiver.attemptSend(JsonBuilder.getModeChanger("STABILIZE").toString());
            }
        });
        guided.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketDataReceiver.attemptSend(JsonBuilder.getModeChanger("GUIDED").toString());
            }
        });
        land.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketDataReceiver.attemptSend(JsonBuilder.getModeChanger("LAND").toString());
            }
        });
        armed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketDataReceiver.attemptSend(JsonBuilder.armed_or_disarmed(ConstantCode.ACTION_ARMED).toString());
            }
        });
        disarmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketDataReceiver.attemptSend(JsonBuilder.armed_or_disarmed(ConstantCode.ACTION_DISARMED).toString());
            }
        });
        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SocketDataReceiver.attemptSend(JsonBuilder.getModeChanger("AUTO").toString());
                //String aiface = "{\"u\":\"g\",\"135\":\"130\",\"142\":\"s1\"}";
                //SocketDataReceiver.attemptSend(aiface);
                SocketDataReceiver.attemptSend(JsonBuilder.getModeChanger("ALT_HOLD").toString());
            }
        });
        discreteSeekBarPitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        discreteSeekBarPitch.setProgress(initialMiddleValuePitch);
                        break;
                }
                return false;
            }
        });
        discreteSeekBarRoll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        discreteSeekBarRoll.setProgress(initialMiddleValueRoll);
                        break;
                }
                return false;
            }
        });
        discreteSeekBarThrottle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        //discreteSeekBarThrottle.setProgress(middleValue);
                        break;
                }
                return false;
            }
        });
        discreteSeekBarYaw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        discreteSeekBarYaw.setProgress(initialMiddleValueYaw);
                        break;
                }
                return false;
            }
        });
        discreteSeekBarPitch.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                //channel_0 = (int) ((value * 4.5) + 150);
                channel_0 = value; //(int) ((value * 4.5) + 150);
                SingletonRC.getInstance(context).setChannel_0(value);
                try {

                    //SocketDataReceiver.attemptSend(jsonobjectManuall().toString());
                    SocketDataReceiver.attemptSend(jsonobjectManuallPitch().toString());
                    Log.d("ferdibc", String.valueOf(jsonobjectManuallPitch().toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return value;
            }
        });
        discreteSeekBarRoll.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                //channel_1 = (int) ((value * 4.5) + 150);
                channel_1 = value;
                SingletonRC.getInstance(context).setChannel_1(value);
                try {
                    //SocketDataReceiver.attemptSend(jsonobjectManuall().toString());
                    SocketDataReceiver.attemptSend(jsonobjectManuallRoll().toString());
                    Log.d("ferdibc", String.valueOf(jsonobjectManuallRoll().toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return value;
            }
        });
        discreteSeekBarThrottle.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                //channel_2 = (int) ((value * 4.5) + 150);
                channel_2 = value;
                SingletonRC.getInstance(context).setChannel_2(value);
                try {
                    //SocketDataReceiver.attemptSend(jsonobjectManuall().toString());
                    SocketDataReceiver.attemptSend(jsonobjectManuallThrottle().toString());
                    Log.d("ferdibc", String.valueOf(jsonobjectManuallThrottle().toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return value;
            }
        });
        discreteSeekBarYaw.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                //channel_3 = (int) ((value * 4.5) + 150);
                channel_3 = value;
                SingletonRC.getInstance(context).setChannel_3(value);
                try {
                    //SocketDataReceiver.attemptSend(jsonobjectManuall().toString());
                    SocketDataReceiver.attemptSend(jsonobjectManuallYaw().toString());
                    Log.d("ferdibc", String.valueOf(jsonobjectManuallYaw().toString()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return value;
            }
        });
        discreteSeekBarRadio5.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                //channel_4 = (int) ((value * 4.5) + 150);
                channel_4 = value;
                SingletonRC.getInstance(context).setChannel_4(value);
                try {
                    SocketDataReceiver.attemptSend(jsonobjectManuallFour().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return value;
            }
        });
        discreteSeekBarRadio6.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                //channel_5 = (int) ((value * 4.5) + 150);
                channel_5 = value;
                SingletonRC.getInstance(context).setChannel_5(value);
                try {
                    SocketDataReceiver.attemptSend(jsonobjectManuallFive().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return value;
            }
        });
        discreteSeekBarRadio7.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                //channel_6 = (int) ((value * 4.5) + 150);
                channel_6 = value;
                SingletonRC.getInstance(context).setChannel_6(value);
                try {
                    SocketDataReceiver.attemptSend(jsonobjectManuallSix().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return value;
            }
        });
        discreteSeekBarRadio8.setNumericTransformer(new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {

                //channel_7 = (int) ((value * 4.5) + 150);
                channel_7 = value;
                SingletonRC.getInstance(context).setChannel_7(value);
                try {
                    SocketDataReceiver.attemptSend(jsonobjectManuallSeven().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return value;
            }
        });

        discreteSeekBarPitch.setProgress(c0);
        discreteSeekBarRoll.setProgress(c1);
        discreteSeekBarThrottle.setProgress(c2);
        discreteSeekBarYaw.setProgress(c3);
        discreteSeekBarRadio5.setProgress(c4);
        discreteSeekBarRadio6.setProgress(c5);
        discreteSeekBarRadio7.setProgress(c6);
        discreteSeekBarRadio8.setProgress(c7);
    }

    public void initComponents() {
        //Max Min value
        discreteSeekBarPitch.setMax(pitchMax);
        discreteSeekBarPitch.setMin(pitchMin);
        discreteSeekBarRoll.setMax(rollhMax);
        discreteSeekBarRoll.setMin(rollMin);
        discreteSeekBarThrottle.setMax(throttleMax);
        discreteSeekBarThrottle.setMin(throttleMin);
        discreteSeekBarYaw.setMax(yawMax);
        discreteSeekBarYaw.setMin(yawMin);
        discreteSeekBarRadio5.setMax(yawMax);
        discreteSeekBarRadio5.setMin(yawMin);
        discreteSeekBarRadio6.setMax(yawMax);
        discreteSeekBarRadio6.setMin(yawMin);
        discreteSeekBarRadio7.setMax(yawMax);
        discreteSeekBarRadio7.setMin(yawMin);
        discreteSeekBarRadio8.setMax(yawMax);
        discreteSeekBarRadio8.setMin(yawMin);
        discreteSeekBarYaw.setMax(yawMax);
        discreteSeekBarYaw.setMin(yawMin);
        //set Default value progress


        discreteSeekBarPitch.setProgress(middleValue);
        discreteSeekBarRoll.setProgress(middleValue);
        discreteSeekBarThrottle.setProgress(throttleMin);
        discreteSeekBarYaw.setProgress(yawMiddle);
    }


}
