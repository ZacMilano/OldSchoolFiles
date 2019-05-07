/* FirstTestActivityMainActivity.java
*
* Contains the java code for the first test activity
*
* Created by: Zac Monroe
* Created on: 2/7/18
* Last modified by: Zac Monroe
* Last modified on: 2/7/18
* Assignment/Project: My First Activity/HW Project 2
* Part of: content_first_test_activity_main.xml
* */

package edu.indiana.zcmonroe.myfirstactivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

// Libraries I added for this project
import android.util.Log;
import android.view.Window;

public class FirstTestActivityMainActivity extends AppCompatActivity {

  String tag = "Lifecycle";
  /* Called when the activity is first created. */

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_test_main);

    // Log call I added for when an instance is created.
    Log.d(tag, "In the onCreate() event");

    // Removed the toolbar and the floating action button
  }

  /*
  * The following 6 methods add Log calls to different points
  * in the lifecycle of the app.
  **/

  public void onStart()
  {
    super.onStart();
    Log.d(tag, "In the onStart() event");
  }

  public void onRestart()
  {
    super.onRestart();
    Log.d(tag, "In the onRestart() event");
  }

  public void onResume()
  {
    super.onResume();
    Log.d(tag, "In the onResume() event");
  }

  public void onPause()
  {
    super.onPause();
    Log.d(tag, "In the onPause() event");
  }

  public void onStop()
  {
    super.onStop();
    Log.d(tag, "In the onStop() event");
  }

  public void onDestroy()
  {
    super.onDestroy();
    Log.d(tag, "In the onDestroy() event");
  }
  // I deleted the other two auto-generated method because they were useless to the project.


}
