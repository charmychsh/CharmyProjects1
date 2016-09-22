package com.example.charmyshah.parseexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.text.ParseException;

/**
 * Created by charmyshah on 5/7/15.
 */
public class AddMethodActivity extends Activity {

    private Button saveMethodButton;
    private EditText titleEditText;
    private EditText contentEditText;
    private String postMethodName;
    private String postContent;
    private Method method;
    String particularProb;
    String particularProbObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getActionBar().setDisplayHomeAsUpEnabled(true);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.add_method);

        Intent intent = getIntent();
        particularProb = intent.getStringExtra("DATA");
        particularProbObj = particularProb + "Comment";
        Log.i("MET", particularProbObj);


        titleEditText = (EditText) findViewById(R.id.methodTitle);
        contentEditText = (EditText) findViewById(R.id.methodContent);

        saveMethodButton = (Button)findViewById(R.id.saveMethod);
        saveMethodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMethod();
            }
        });


    }

    private void saveMethod() {

        postMethodName = titleEditText.getText().toString();
        postContent = contentEditText.getText().toString();

        if(!postMethodName.isEmpty() && !postContent.isEmpty()) {

            ParseObject post = new ParseObject(particularProb);
            post.put("method", postMethodName);
            post.put("data", postContent);
            post.saveInBackground(new SaveCallback() {

                @Override
                public void done(com.parse.ParseException e) {
                    if (e == null) {
                        // Saved successfully.
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                       // finish();
                    } else {
                        // The save failed.
                        Toast.makeText(getApplicationContext(), "Failed to Save", Toast.LENGTH_SHORT).show();
                        Log.d(getClass().getSimpleName(), "User update error: " + e);
                    }
                }
            });

            ParseObject postComment = new ParseObject(particularProbObj);
            postComment.put(postMethodName, "");
            Log.i("PARSE",postMethodName);
            post.saveInBackground(new SaveCallback() {

                @Override
                public void done(com.parse.ParseException e) {
                    if (e == null) {
                        // Saved successfully.
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        // The save failed.
                        Toast.makeText(getApplicationContext(), "Failed to Save", Toast.LENGTH_SHORT).show();
                        Log.d(getClass().getSimpleName(), "User update error: " + e);
                    }
                }
            });
        }
        else{

            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("No Data");
            alertDialog.setMessage("Methodname or content box is not filled with data");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.cancel();
                }
            });
            alertDialog.show();

        }

    }

}
