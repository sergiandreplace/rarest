package com.sergiandreplace.rarest.engine;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * User: A511218
 * Date: 7/10/13
 * Time: 2:02 PM
 */

public class ResponseParcelable extends Response implements Parcelable {

    public ResponseParcelable(Response response) {
        super();
        setStatus(response.getStatus());
        setErrorType(response.getErrorType());
        setErrorMessage(response.getErrorMessage());
        setBody(response.getBody());
        setHeaders(response.getHeaders());
    }

    public ResponseParcelable(Parcel in) {
        super();
        readFromParcel(in);
    }

    public static final Creator<ResponseParcelable> CREATOR
            = new Creator<ResponseParcelable>() {
        public ResponseParcelable createFromParcel(Parcel in) {
            return new ResponseParcelable(in);
        }

        public ResponseParcelable[] newArray(int size) {
            return new ResponseParcelable[size];
        }
    };

    private void readFromParcel(Parcel in) {
        setBody(in.readString());
        setErrorMessage(in.readString());
        setErrorType(in.readString());
        setStatus(in.readInt());
        setHeaders(bundleToMap(in.readBundle()));
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getBody());
        dest.writeString(getErrorMessage());
        dest.writeString(getErrorType());
        dest.writeInt(getStatus());
        dest.writeBundle(mapToBundle(getHeaders()));
    }

    @Override
    public int describeContents() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private Map bundleToMap(Bundle bundle) {
        Map<String, String> headers = new HashMap<String, String>();
        for (String key : bundle.keySet()) {
            String value = (String) bundle.get(key);
            headers.put(key, value);
        }
        return headers;
    }

    private Bundle mapToBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (String key : map.keySet()) {
            String value = map.get(key);
            bundle.putString(key, value);
        }
        return bundle;
    }

}
