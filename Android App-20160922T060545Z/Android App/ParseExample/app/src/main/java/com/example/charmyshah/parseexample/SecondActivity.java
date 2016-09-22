package com.example.charmyshah.parseexample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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
public class SecondActivity extends ListActivity {
    TextView showData;
    String particularProblem;
    private List<Method> postList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
      //  showData = (TextView) findViewById(R.id.textView2);

        Intent i = getIntent();
        particularProblem = i.getStringExtra("PROB");

        postList = new ArrayList<Method>();
        ArrayAdapter<Method> adapter = new ArrayAdapter<Method>(this, R.layout.secondlist_item,R.id.text2, postList);
        setListAdapter(adapter);
        Log.i("NEW","oncreate");
        refreshPostNewList();

    }

    private void refreshPostNewList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(particularProblem);

        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> Objects, com.parse.ParseException e) {
                Log.i("NEW","done");
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    Log.i("NEW","if");
                    postList.clear();
                    for (ParseObject post : Objects) {
                        Method meth = new Method( post.getString("method"),post.getString("data"));
                        Log.i("NEW",post.getString("method"));
                        postList.add(meth);
                    }
                    ((ArrayAdapter<Method>) getListAdapter()).notifyDataSetChanged();
                } else {
                    Log.i("NEW","else");
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }

            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Method meth = postList.get(position);
        Intent intent = new Intent(this, DataActivity.class);
        intent.putExtra("DATA", meth.getData());
        intent.putExtra("PR",particularProblem );
        intent.putExtra("METHOD", meth.getMethod());
        startActivity(intent);

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {

            case R.id.action_refresh: {
                refreshPostNewList();
                break;
            }

            case R.id.action_new: {
                Intent i = new Intent(this, AddMethodActivity.class);
                i.putExtra("DATA",particularProblem);
                startActivity(i);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
