/* BasicFinancialMainActivity.java
 *
 * Contains the primary java code for the main activity
 *
 * Created by: Zac Monroe
 * Created on: 2/18/18
 * Last modified by: Zac Monroe
 * Last modified on: 3/2/18
 * Assignment/Project: A290 Android: Basic Financial
 * Refers to:
 *  - activity_basic_financial_main.xml
 *  - menu_basic_financial_navigation.xml
 *  - strings.xml
 *  - BasicFinancialInvestActivity.java
 *  - BasicFinancialPrefsActivity.java
 *  - Transaction.java
 *  - Week.java
 *  - content_basic_financial_transaction_view.xml
 **/

package edu.indiana.zcmonroe.basicfinancial;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

// Libraries added for this project
import android.content.ContentValues;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class BasicFinancialMainActivity extends AppCompatActivity
{
  // String used for logging
  private static final String TAG = "Main Activity";

  // This is the index of the week that is currently being viewed.
  // I set it to static so that the week that was being viewed will
  protected static int currentWeekIndex;

  /* This is the data that stores a json version of the actual week objects for the app (when not
   * in use).
   */
  public static SharedPreferences weeksData;

  // This is the array list that stores the Week object instances.
  public static ArrayList<Week> weeks;

  // These are the buttons and the text that go at the top of the activity (below action bar).
  Button prevBut, nextBut;
  TextView weekLabel;

  // myDrawerToggle and myDrawerLayout are for the navigation drawer.
  private ActionBarDrawerToggle myDrawerToggle;
  private DrawerLayout myDrawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic_financial_main);

    // Solution's source: https://developer.android.com/training/appbar/setting-up.html
    // This is how I set up the title at the top of the screen.
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    /* Solution's source: https://developer.android.com/training/implementing-navigation/nav-drawer.html
     * From here until the next comment is how I set up the navigation drawer for use.
     * It is generally pretty straightforward. I use .syncState() to make sure that the
     * hamburger button shows up (for ease of use/so the user can recognize that there is
     * indeed a navigation drawer).
     */
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);

    this.myDrawerLayout = findViewById(R.id.drawer_layout);

    this.myDrawerToggle =
            new ActionBarDrawerToggle(this, this.myDrawerLayout,
                                      toolbar, R.string.open_nav, R.string.close_nav);
    this.myDrawerLayout.addDrawerListener(this.myDrawerToggle);
    this.myDrawerToggle.syncState();
    //END OF NAVIGATION SETUP

    // https://stackoverflow.com/questions/34118086/getsupportactionbar-settitle-vs-toolbar-settitle/34118675
    // Here I set the activity's title to what it's supposed to be; I changed it in the manifest to
    // the app's name because the name that appeared below the app icon on my home screen was the
    // name of main activity (this one). So I later (below) have to set the title back to what it is
    // supposed to be.
    getSupportActionBar().setTitle(R.string.budgeting_title);

    /* I kept the floating action button but modified it so that it calls
     * a method (beginTransaction) that opens the MoneyInOrOutActivity (two big buttons).
     */
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        beginTransaction();
      }
    });

    // Here is where I set up the identity of the weekLabel.
    this.weekLabel = findViewById(R.id.txt_week_label);

    /* Here is where I set up the functionality of the Next and Prev buttons at the top.
     * They use the chooseWeekIndex() method.
     */
    this.prevBut = findViewById(R.id.btn_prev);
    this.nextBut = findViewById(R.id.btn_next);
    this.prevBut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v)
      {
        chooseWeekIndex(-1);
      }
    });
    this.nextBut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        chooseWeekIndex(1);
      }
    });

    // https://developer.android.com/reference/android/content/SharedPreferences.html
    weeksData = getPreferences(MODE_PRIVATE);
    weeks = (getWeeksData() != null)? getWeeksData() : new ArrayList<Week>();

    // Adding a week if there are none already
    getMaxWeekIndex();

    // Setting the label at the top ("Week" + index of current viewed week)
    setWeekLabel(currentWeekIndex);
    // Draw all the transactions for the current week.
    drawTransactions(currentWeekIndex);
  }


  // The onPause method will save the user's weeks data.
  @Override
  protected void onPause()
  {
    super.onPause();
    saveWeeksData();
  }

  // The onStop method will save the user's weeks data.
  @Override
  protected void onStop()
  {
    super.onStop();
    saveWeeksData();
  }

  // The onDestroy method will save the user's weeks data.
  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    saveWeeksData();
  }

  // drawTransactions draws the transactions for the week in the array list of weeks whose index
  // in said list is the given integer, weekIndex. It also updates the text below the transactions
  // (the total money remaining, etc).
  public void drawTransactions(int weekIndex)
  {
    ArrayList<Transaction> transactions = weeks.get(weekIndex).getTrans();

    LinearLayout transactionView;

    // This is the part of the main activity that the views are pushed into.
    LinearLayout destination = findViewById(R.id.lin_transactions_container);
    destination.removeAllViews();

    // This is for the total money remaining for the week.
    double totalAmt = 0;

    for (int i = 0; i < transactions.size(); i++)
    {
      // This is final because the delete button's listener required it to be so.
      final Transaction ithTransaction = transactions.get(i);

      // Calculate the total remaining money for the week
      if (ithTransaction.isPositive())
      {
        totalAmt += ithTransaction.getAmount();
      }
      else
      {
        totalAmt -= ithTransaction.getAmount();
      }

      // https://www.bignerdranch.com/blog/understanding-androids-layoutinflater-inflate/
      // How I add a transaction into view
      LayoutInflater inflater = getLayoutInflater();

      // Here I initialize the transaction as the default blank transaction.
      transactionView = (LinearLayout) inflater.inflate(
              R.layout.content_basic_financial_transaction_view, null);

      // Some of the children of the blank transaction. The children not listed are purely for
      // decoration.
      TextView sign = (TextView) transactionView.getChildAt(0);
      TextView amt = (TextView) transactionView.getChildAt(1);
      TextView label = (TextView) transactionView.getChildAt(3);
      TextView special = (TextView) transactionView.getChildAt(5);
      TextView tag = (TextView) transactionView.getChildAt(7);
      Button delBut = (Button) transactionView.getChildAt(8);

      // If the transaction was intended to add money, its sign value is "+" and its color is green,
      // otherwise "-" and red respectively.
      sign.setText((ithTransaction.isPositive())? "+" : "-");
      https://stackoverflow.com/questions/5271387/get-color-int-from-color-resource
      sign.setTextColor((ithTransaction.isPositive())? getResources().getColor(R.color.money_in) :
              getResources().getColor(R.color.money_out));
      // Display the amount as a dollar amount
      amt.setText(String.format("$%.2f", ithTransaction.getAmount()));
      https://stackoverflow.com/questions/5271387/get-color-int-from-color-resource
      amt.setTextColor((ithTransaction.isPositive())? getResources().getColor(R.color.money_in) :
              getResources().getColor(R.color.money_out));

      // Set the label column value
      label.setText(ithTransaction.getLabel());

      // Set the special column value
      special.setText(ithTransaction.getSpecial());

      // Set the tag column value
      tag.setText(ithTransaction.getTag());

      // Set up the delete button to delete the transaction itself from the week
      delBut.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          weeks.get(currentWeekIndex).getTrans().remove(ithTransaction);
          drawTransactions(currentWeekIndex);
        }
      });

      // Add the view we just set up into the main view
      destination.addView(transactionView);
    }

    // The following three TextViews are for the total money remaining amount and the other two
    // tips; they are set up the same way as above except I don't have to add them to view, just
    // change their text values.
    TextView totalRemainingView = findViewById(R.id.txt_total_remaining_money);
    totalRemainingView.setText(String.format("$%.2f", totalAmt));
    totalRemainingView.setTextColor((totalAmt >= 0)? getResources().getColor(R.color.money_in) :
            getResources().getColor(R.color.money_out));

    TextView purchasesRemainingView = findViewById(R.id.txt_20_purchases_remaining);
    purchasesRemainingView.setText(String.format("%d", (int) totalAmt/20));
    purchasesRemainingView.setTextColor((totalAmt >= 0)? getResources().getColor(R.color.money_in) :
            getResources().getColor(R.color.money_out));

    TextView maxPurchaseView = findViewById(R.id.txt_cost_of_10_remaining_purchases);
    maxPurchaseView.setText(String.format("$%.2f", totalAmt/10));
    maxPurchaseView.setTextColor((totalAmt >= 0)? getResources().getColor(R.color.money_in) :
            getResources().getColor(R.color.money_out));

  }

  /* chooseWeekIndex is a method that handles functionality for the arrows
   * near the top of the screen. A choice of -1 indicates a press of
   * the left arrow, and a choice of +1 indicates a press of the right
   * arrow. In the left-most Week, nothing happens when pressing the
   * left arrow, and in the right-most Week, a press of the right
   * arrow adds a week to the database.
   *
   * The input integer ("choice") will be either -1 or 1.
   **/
  public void chooseWeekIndex(int choice)
  {
    if ((choice + currentWeekIndex) <= 0)
    {
      currentWeekIndex = 0;
    }
    else if (this.getMaxWeekIndex() < currentWeekIndex + choice)
    {
      addWeek();
      currentWeekIndex += choice;
    }
    else
    {
      currentWeekIndex += choice;
    }
    drawTransactions(currentWeekIndex);
    setWeekLabel(currentWeekIndex);
  }

  /* setWeekLabel changes the value of the text between the prev and next buttons.
   * It is a helper function for chooseWeekIndex.
   */
  private void setWeekLabel(int newLabel)
  {
    Log.d(TAG, "Now viewing week #" + String.valueOf(currentWeekIndex));

    /* Setting the text of the weekLabel while also concatenating strings did not work well,
     * so when I found out that there was an append method of TextViews, I decided to set
     * the text as below.
     */
    this.weekLabel.setText(R.string.week_label);
    this.weekLabel.append(String.format(" %d", currentWeekIndex));
  }

  /* getMaxWeekIndex returns the index of the last week in the weeks ArrayList.
   * If the ArrayList is empty, it adds a week.
   */
  private int getMaxWeekIndex()
  {
    if (weeks.size() == 0) // if there are no weeks in the weeks structure
    {
      addWeek();
    }
    // return the amount of weeks
    return weeks.size() - 1;
  }

  /* addWeek is a helper function for chooseWeekIndex and getMaxWeekIndex.
   * It adds a blank week to weeks.
   */
  private void addWeek()
  {
    Log.d(TAG, "Adding week to the ArrayList");
    weeks.add(new Week());
  }

  // https://developer.android.com/training/data-storage/shared-preferences.html
  // https://developer.android.com/reference/android/content/SharedPreferences.html
  // saveWeeksData uses SharedPreferences to save the state of the main activity for later use.
  // This uses Gson to convert the weeks array list to a .json-formatted string. That string is then
  // what is saved.
  public static void saveWeeksData()
  {
    SharedPreferences.Editor weeksDataEditor = weeksData.edit();
    // This and all other instances of gson:
    // https://sites.google.com/site/gson/gson-user-guide
    Gson gsonInstance = new Gson();
    String weeksAsJson = gsonInstance.toJson(weeks);
    Log.d(TAG, weeksAsJson);

    weeksDataEditor.putString("Weeks", weeksAsJson);
    weeksDataEditor.apply();
  }

  // https://developer.android.com/training/data-storage/shared-preferences.html
  // https://developer.android.com/reference/android/content/SharedPreferences.html
  // getWeeksData returns the data of the weeks array list by accessing the .json-formatted string
  // in the Shared Preferences. It returns as a proper array list of week object instances.
  public static ArrayList<Week> getWeeksData()
  {
    // This and all other instances of gson:
    // https://sites.google.com/site/gson/gson-user-guide
    Gson gsonInstance = new Gson();
    String weeksAsJson = weeksData.getString("Weeks", "");
    Log.d(TAG, weeksAsJson);
    return gsonInstance.fromJson(weeksAsJson, new TypeToken<ArrayList<Week>>(){}.getType());
  }

  /* beginTransaction opens an instance of BasicFinancialMoneyInOrOutActivity; it is the first
   * step in creating a transaction.
   */
  private void beginTransaction()
  {
    // Save user's data (because leaving the activity)
    saveWeeksData();
    startActivity(new Intent(this, BasicFinancialMoneyInOrOutActivity.class));
    Log.d(TAG, String.valueOf(this.getMaxWeekIndex()));
  }

  // https://developer.android.com/training/implementing-navigation/nav-drawer.html
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
        startActivity(new Intent(this, BasicFinancialPrefsActivity.class));
        return true;

      case R.id.budget_item:
        this.myDrawerLayout.closeDrawer(findViewById(R.id.navigation));
        // startActivity(new Intent(this, BasicFinancialMainActivity.class));
        return true;

      default:
        this.myDrawerLayout.closeDrawer(findViewById(R.id.navigation));
        return false;
    }
  }
}
