/* FirstRealAppMainActivity.java
 *
 * Contains the Java code for the main menu activity
 *
 * Created by: Zac Monroe
 * Created on: 2/14/18
 * Last modified by: Zac Monroe
 * Last modified on: 2/17/18
 * Assignment/Project: A290 Android First Real App
 * Refers to:
 *  - content_first_real_app_main.xml
 *  - activity_first_real_app_main.xml
 *  - strings.xml
 *  - menu_first_real_app_settings.xml
 *  - icon_for_difficulty.png
 *  - arrays.xml
 **/

package edu.indiana.zcmonroe.a290firstrealapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

// Libraries I added for this project
import android.content.Intent;
import android.view.View.OnClickListener;
import android.view.MenuInflater;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Paint;
import android.graphics.LinearGradient;
import android.graphics.Shader;

public class FirstRealAppMainActivity extends AppCompatActivity implements OnClickListener {

  private static final String TAG = "StartMainActivity";

  /*
   * openNewGameDialog opens the dialog box where the difficulty of the game is selected.
   */

  private void openNewGameDialog()
  {
    new AlertDialog.Builder(this)
            .setTitle(R.string.NewGameTitle)
            .setIcon(R.drawable.icon_for_difficulty)
            .setItems(R.array.difficulty,
                    new android.content.DialogInterface.OnClickListener()
                    {
                      public void onClick(DialogInterface dialoginterface, int i)
                      {
                        startGame(i);
                      }
                    }
                  )
            .show();
  }

  /*
   * startGame opens the specified game from the array,
   * using the given integer as the index of the item.
   */

  private void startGame(int i)
  {
    Log.d(TAG, "Clicked on " + i);
    Intent intent = new Intent(this, FirstRealAppGameActivity.class);
    intent.putExtra(FirstRealAppGameActivity.KEY_DIFFICULTY, i);
    startActivity(intent);
  }

  public void onClick(View v)
  {
    switch (v.getId())
    {
      case R.id.btn_About:
        Intent i1 = new Intent(this, FirstRealAppAboutActivity.class);
        startActivity(i1);
        break;

      case R.id.btn_Continue:
        finish();
        break;

      case R.id.btn_New:
        openNewGameDialog();
        break;

      case R.id.btn_Exit:
        finish();
        break;

      default:
        Intent i5 = new Intent(this, FirstRealAppAboutActivity.class);
        startActivity(i5);
        break;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_real_app_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    View ContinueButton = findViewById(R.id.btn_Continue);
    ContinueButton.setOnClickListener(this);

    View NewButton = findViewById(R.id.btn_New);
    NewButton.setOnClickListener(this);

    View AboutButton = findViewById(R.id.btn_About);
    AboutButton.setOnClickListener(this);

    View ExitButton = findViewById(R.id.btn_Exit);
    ExitButton.setOnClickListener(this);

    // I removed the floating email button because it was unnecessary for this project.
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    super.onCreateOptionsMenu(menu);
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_first_real_app_settings, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.

    switch (item.getItemId())
    {
      case R.id.SettingsMenu:
        startActivity(new Intent(this, FirstRealAppPrefsActivity.class));
        return true;
    }
    return false;
  }
}
