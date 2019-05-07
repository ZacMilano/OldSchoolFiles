/* MainDialogActivity.java
* Primary java file for this activity
* Created by: Zac Monroe
* Created on: 2/7/18
* Last modified by: Zac Monroe
* Last modified on: 2/7/18
* Assignment/Project: My First Dialog
* Part of: content_main_dialog.xml
**/

package edu.indiana.zcmonroe.myfirstdialog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/* Added these libraries for the dialog */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
// "import android.view.View;" was already in this file.
import android.widget.Toast;

public class MainDialogActivity extends AppCompatActivity {

  /* This sets up our "list" to be viewed in the dialog and tracks which is selected */

  CharSequence[] items = { "Google", "Apple", "Microsoft" };
  boolean[] itemsChecked = new boolean[items.length];

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_dialog);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
      }
    });
  }

  /* This is deprecated */
  @SuppressWarnings("deprecation")
  protected void onClick(View v)
  {
    /* Once again, this is deprecated but we suppress the warning */
    showDialog(0);
  }

  protected Dialog onCreateDialog(int id)
  {
    switch (id)
    {
      /* switch-case is better than if-else, in this case */
      case 0:
        return new AlertDialog.Builder(this)
                // This image is property of "Bandot" on Google Play
                .setIcon(R.drawable.ic_launcher)
                /* Creates basic dialog with "OK" and "Cancel" buttons and a title
                 */
                .setTitle("This is a dialog with some simple text . . .")
                /* Every button click/action requires a ClickListener so we must prepare one
                to react to user input
                 */
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int whichButton)
                          {
                            /* Use toast to briefly display that "OK" was clicked */
                            Toast.makeText(getBaseContext(),
                                    "OK clicked!", Toast.LENGTH_SHORT).show();
                          }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                          public void onClick(DialogInterface dialog, int whichButton)
                          {
                            /* Use toast to briefly display that "Cancel" was clicked */
                            Toast.makeText(getBaseContext(),
                                    "Cancel clicked!", Toast.LENGTH_SHORT).show();
                          }
                        }
                )
                .setMultiChoiceItems(items, itemsChecked,
                        new DialogInterface.OnMultiChoiceClickListener()
                        {
                          public void onClick(DialogInterface dialog,
                                              int which, boolean isChecked)
                          {
                            /* Using our boolean to confirm the state of each of our options
                             * in our item list and the using Toast to briefly display that
                             * each one has been "(un)checked"*/
                            Toast.makeText(getBaseContext(),
                                    items[which] + (isChecked ? " checked!":" unchecked!"),
                                    Toast.LENGTH_SHORT).show();

                          }
                        }
                )
                /* We have to actually create the "case" when the button is clicked */
                .create();
    }

    // No return value expected --> null
    return null;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main_dialog, menu);
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
