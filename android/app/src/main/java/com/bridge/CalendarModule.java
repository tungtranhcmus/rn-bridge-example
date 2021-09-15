package com.bridge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;
import android.util.Log;
import com.facebook.react.bridge.Callback;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;


public class CalendarModule extends ReactContextBaseJavaModule {
    CalendarModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "CalendarModule";
    }

    @ReactMethod
    public void createCalendarEvent1(String name, String location, Callback callBack) {
        Log.d("CalendarModule", "Create event called with name: " + name
                + " and location: " + location);
        Integer eventId = 123;
        callBack.invoke(eventId);
    }

    @ReactMethod
    public void createCalendarEventCallback(String name, String location, Callback myFailureCallback, Callback mySuccessCallback) {
        try{
            Log.d("CalendarModule", "Create event called with name: " + name
                    + " and location: " + location);
            mySuccessCallback.invoke(1233);
        }catch (Exception e){
            myFailureCallback.invoke((e));
        }

    }

    @ReactMethod
    public void createCalendarEvent2(String name, String location, Promise promise) {
        Log.d("CalendarModule", "Create event called with name: " + name
                + " and location: " + location);
        try {

            WritableMap params = Arguments.createMap();
            params.putString("eventProperty", "someValue");
            sendEvent(getReactApplicationContext(), "EventA", params);


            Integer eventId = 123;
            promise.resolve(eventId);
        } catch(Exception e) {
            promise.reject("Create Event Error", e);
        }
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           @Nullable WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

}
