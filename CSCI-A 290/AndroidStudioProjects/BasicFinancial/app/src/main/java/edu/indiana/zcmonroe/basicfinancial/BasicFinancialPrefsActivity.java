/* BasicFinancialPrefsActivity.java
 *
 * Contains the java code for the settings activity
 *
 * Created by: Zac Monroe
 * Created on: 2/18/18
 * Last modified by: Zac Monroe
 * Last modified on: 3/2/18
 * Assignment/Project: A290 Android: Basic Financial
 * Refers to:
 *  - activity_basic_financial_prefs.xml
 *  - strings.xml
 *  - BasicFinancialMainActivity.java
 **/

package edu.indiana.zcmonroe.basicfinancial;

// Libraries added for this project
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class BasicFinancialPrefsActivity extends AppCompatActivity
{
  // String used for logging
  private final static String TAG = "PrefsActivity";

  /* myDrawerToggle and myDrawerLayout are for the navigation drawer. Toolbar is the action bar
   * at the top.
   */
  private ActionBarDrawerToggle myDrawerToggle;
  private DrawerLayout myDrawerLayout;
  public Toolbar toolbar;

  // This is the linear layout that contains the elements for the action of viewing the tags.
  public LinearLayout linViewTags;
  // This is the linear layout that contains the elements for viewing info about the app.
  public LinearLayout linAbout;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic_financial_prefs);

    // Solution's source: https://developer.android.com/training/appbar/setting-up.html
    // This is how I set up the title at the top of the screen.
    this.toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // Solution's source: https://developer.android.com/training/implementing-navigation/nav-drawer.html
    // From here until the next comment is how I set up the navigation drawer for use.
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);

    myDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

    myDrawerToggle = new ActionBarDrawerToggle(this,
            myDrawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
    myDrawerLayout.addDrawerListener(myDrawerToggle);
    myDrawerToggle.syncState();
    //
    // END OF NAVIGATION SETUP
    //

    /* This is where I make linDeleteTags and linAbout point to the layouts that they actually
     * control.
     */
    this.linViewTags = (LinearLayout) findViewById(R.id.lin_view_tags_main);
    this.linAbout = (LinearLayout) findViewById(R.id.lin_about_main);

    // This is where I set up what happens when the linear layouts are clicked on.
    /* linDeleteTags should take the user to a screen where they can delete any of the tags
     * that they've added to a transaction; deleting that tag should also
     */
    this.linViewTags.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onPrefSelected(view);
      }
    });
    this.linAbout.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onPrefSelected(view);
      }
    });
  }

  // // https://developer.android.com/training/implementing-navigation/nav-drawer.html
  /* onNavItemSelected handles when a navigation item from the navigation drawer is clicked.
   * If the item that is selected is the activity that was in action before opening the drawer,
   * onNavItemSelected merely closes the drawer. The default case is also to close the drawer.
   */
  public boolean onNavItemSelected(MenuItem item)
  {
    switch (item.getItemId())
    {
      case R.id.invest_item:
        startActivity(new Intent(this, BasicFinancialInvestActivity.class));
        return true;

      case R.id.settings_item:
        this.myDrawerLayout.closeDrawer(findViewById(R.id.navigation));
        // startActivity(new Intent(this, BasicFinancialPrefsActivity.class));
        return true;

      case R.id.budget_item:
        startActivity(new Intent(this, BasicFinancialMainActivity.class));
        return true;
    }
    return false;
  }

  // https://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog
  /* onPrefSelected is called when any of the preference items are clicked on. It performs an
   * action according to the ID of the item that was clicked on.
   */
  public void onPrefSelected(View v)
  {
    switch (v.getId())
    {
      // This ID case takes the user to the screen where they can view the tags that they've created
      case R.id.lin_view_tags_main:
        AlertDialog.Builder viewTagsBuilder = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_Light_Dialog_Alert);
        viewTagsBuilder.setTitle(R.string.all_tags);

        ArrayList<Week> weeks = BasicFinancialMainActivity.getWeeksData();
        ArrayList<String> tags = new ArrayList<>();

        // Each week
        for (Week w : weeks)
        {
          // Each week's transactions
          for (Transaction t : w.getTrans())
          {
            // Add the tag to our temporary array list of tags if it's not already in it
            if (!tags.contains(t.getTag()))
            {
              tags.add(t.getTag());
            }
          }
        }
        // Default string message for the view tags dialog
        String message = "You have no tags right now! why don't you go ahead and make some?";

        // Set up the message string for being placed into the dialog, if any strings are in the
        // array list
        if (tags.size() != 0)
        {
          message = "";
          for (String tag : tags)
          {
            message += (" - " + tag + "\n");
          }
        }

        // Further set up the tags dialog
        viewTagsBuilder.setMessage(message);
        viewTagsBuilder.show();

        Log.d(TAG, "Viewing tags!");
        break;

      // This ID case pops up a dialog that displays info about the app.
      case R.id.lin_about_main:
        AlertDialog.Builder abtBuilder = new AlertDialog.Builder(this,
                R.style.Theme_AppCompat_Light_Dialog_Alert);
        abtBuilder.setTitle(R.string.about_header);
        abtBuilder.setMessage(R.string.about_body);
        abtBuilder.show();

        Log.d(TAG, "Viewing app info!");
        break;

      default:
        break;
    }
  }

}
