package com.example.charmyshah.parseexample;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by charmyshah on 5/6/15.
 */
public class DataActivity extends Activity {
    TextView showData;
    String methodData;
    String method;
    String problemName;
    String objectName;
  //  private List<Comment> comments;
  // private List<Comment> posts;
  //  String[] comment;
  List<String> comment = new ArrayList<String>();
    ListView lv;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datashow);

        showData = (TextView) findViewById(R.id.dataShowTextView);

        Intent intent = getIntent();
        methodData = intent.getStringExtra("DATA");
        method = intent.getStringExtra("METHOD");
        problemName = intent.getStringExtra("PR");
        objectName = problemName + "Comment";
        Log.i("MET", objectName);
        showData.setText(methodData);
        lv = (ListView) findViewById(R.id.listnew);



        arrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, comment );

        lv.setAdapter(arrayAdapter);

        refreshPostDataList();

        Button button = (Button) findViewById(R.id.saveComment);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(DataActivity.this, AddCommentActivity.class);
                i.putExtra("OBJNAME",objectName);
                i.putExtra("COLNAME",method);
                startActivity(i);

            }
        });


    }

    private void refreshPostDataList() {




            ParseQuery<ParseObject> query = ParseQuery.getQuery(objectName);
            Log.i("DATA", "function");
            query.findInBackground(new FindCallback<ParseObject>() {

                @Override
                public void done(List<ParseObject> postList, com.parse.ParseException e) {
                    Log.i("DATA", "done");
                    if (e == null) {
                        // If there are results, update the list of posts
                        // and notify the adapter
                        Log.i("DATA", "if");
                      comment.clear();
                      //  comment = new String[comment.length];
                        for (ParseObject post : postList) {
                           String newData = post.getString(method);
                            if(newData != null && !newData.isEmpty()){
                                Log.i("DATA", newData);
                                comment.add(newData);
                            }
                            else {
                                Log.i("DATA", "noting");
                            }
                        }
                        arrayAdapter.notifyDataSetChanged();
                    } else {
                        Log.i("DATA", "else");
                        Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                    }

                }
            });



    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {

            case R.id.action_refresh: {
                refreshPostDataList();
                break;
            }

            case R.id.action_settings: {
                // Do something when user selects Settings from Action Bar overlay
                break;
            }
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.data_menu, menu);
        return super.onCreateOptionsMenu(menu);
    } */

}
