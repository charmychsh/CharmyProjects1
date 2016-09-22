package com.example.charmyshah.parseexample;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.AbsListView.OnScrollListener;


public class MainActivity extends ListActivity {
    ProgressDialog mProgressDialog;
    private List<Problem> posts;
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        posts = new ArrayList<Problem>();
        ArrayAdapter<Problem> adapter = new ArrayAdapter<Problem>(this, R.layout.list_item,R.id.text1, posts);
        setListAdapter(adapter);
        Log.i("DATA","oncreate");
        lv = getListView();


        refreshPostList();




    }



    private void refreshPostList() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Problem");
        query.orderByAscending("problem");
        Log.i("DATA","function");
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> postList, com.parse.ParseException e) {
                Log.i("DATA","done");
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    Log.i("DATA","if");
                    posts.clear();
                    for (ParseObject post : postList) {
                        Problem note = new Problem( post.getString("problem"));
                        Log.i("DATA",post.getString("problem"));
                        posts.add(note);
                    }
                    ((ArrayAdapter<Problem>) getListAdapter()).notifyDataSetChanged();
                } else {
                    Log.i("DATA","else");
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }

            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Problem note = posts.get(position);
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("PROB", note.getProblem());
        startActivity(intent);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }



        return super.onOptionsItemSelected(item);
    }
}



