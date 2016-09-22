package com.example.charmyshah.parseexample;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by charmyshah on 5/5/15.
 */
public class NoteAppApplication extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "We0I8TzaPvixAG5g1Morcj6HzsXz59ux5Wfqh0FO", "GFV2UTv1NwujOU453OyvL1asWJsWnD3I1AbTR56T");


    }
}
