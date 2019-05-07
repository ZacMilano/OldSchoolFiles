/*MyFirstActivityMain.java
*
* Code for the main Activity of
* the Android Calling project
*
* Created by: Zac Monroe
* Created on: 1/25/18
* Last modified by: Zac Monroe
* Last modified on: 1/29/18
* Assignment/Project: A290 Android - Android Calling
* Refers to:
*  - my_first_activity_main.xml
* */



package edu.indiana.zcmonroe.androidcalling;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MyFirstActivityMain extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.my_first_activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }
  /*
  * I removed the floating email button from the bottom right
  * and the options menu from the top right.
  * They were unnecessary clutter within the app.
  * */
}
