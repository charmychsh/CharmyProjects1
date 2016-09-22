package com.example.charmyshah.parseexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.SaveCallback;

/**
 * Created by charmyshah on 5/10/15.
 */
public class AddCommentActivity extends Activity {
    String objName;
    String colName;
    String commentData;

    private EditText commentEditText;
    private Button saveCommentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comment);


        Intent intents = getIntent();
        objName = intents.getStringExtra("OBJNAME");
        Log.i("MET", objName);
        colName = intents.getStringExtra("COLNAME");
        Log.i("MET", colName);

        commentEditText = (EditText) findViewById(R.id.commentContent);
        saveCommentButton = (Button)findViewById(R.id.saveNewComment);
        saveCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewComment();
            }
        });



    }

    private void saveNewComment() {

        commentData = commentEditText.getText().toString();
        if(!commentData.isEmpty()){

            ParseObject postComment = new ParseObject(objName);
            postComment.put(colName, commentData);
            postComment.saveInBackground(new SaveCallback() {

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
        else {
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("No Comment");
            alertDialog.setMessage("You have not added any comment");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.cancel();
                }
            });
            alertDialog.show();
        }

    }

}
