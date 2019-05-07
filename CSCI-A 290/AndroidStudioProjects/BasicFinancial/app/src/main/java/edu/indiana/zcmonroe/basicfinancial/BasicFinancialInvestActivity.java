/* BasicFinancialInvestActivity.java
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
 **/

package edu.indiana.zcmonroe.basicfinancial;

// Libraries added for this project
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.Math;
import java.util.Locale;

public class BasicFinancialInvestActivity extends AppCompatActivity {

  private final static String TAG = "InvestActivity";

  // This is the button whose purpose is to calculate the numbers for the inputted investment.
  private Button calculateButton;

  /* These are all the right-hand cells in the table-looking layout that displays the value
   * of the investment over time (barring the top, header cell). These will contain the value
   * of the investment after their amounts of time.
   */
  private TextView oneMonthBalance;
  private TextView sixMonthBalance;
  private TextView twelveMonthBalance;
  private TextView twentyFourMonthBalance;
  private TextView sixtyMonthBalance;
  private TextView oneTwentyMonthBalance;
  private TextView twoFortyMonthBalance;

  /* Here I declare the EditText instances from the layout. They are what the user inputted for the
   * principal, expected growth rate, and weekly contribution (respectively).
   */
  private EditText inputPrincipal;
  private EditText inputGrowth;
  private EditText inputContrib;

  // myDrawerToggle and myDrawerLayout are for the navigation drawer.
  private ActionBarDrawerToggle myDrawerToggle;
  private DrawerLayout myDrawerLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic_financial_invest);

    // Solution's source: https://developer.android.com/training/appbar/setting-up.html
    // This is how I set up the title at the top of the screen.
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

    // Here, I set up which button calculateButton actually refers to.
    this.calculateButton = (Button) findViewById(R.id.btn_calculate_investment);
    // Here, I set up the functionality of calculateButton.
    this.calculateButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        calculateInvestment();
      }
    });

    /* Here, I set up which TextViews each of the *MonthBalance instances actually refer to.
     * Their text values will be changed by pressing the "Calculate investment" button.
     */
    this.oneMonthBalance = (TextView) findViewById(R.id.txt_investment_value_1_mo);
    this.sixMonthBalance = (TextView) findViewById(R.id.txt_investment_value_6_mo);
    this.twelveMonthBalance = (TextView) findViewById(R.id.txt_investment_value_12_mo);
    this.twentyFourMonthBalance = (TextView) findViewById(R.id.txt_investment_value_24_mo);
    this.sixtyMonthBalance = (TextView) findViewById(R.id.txt_investment_value_60_mo);
    this.oneTwentyMonthBalance = (TextView) findViewById(R.id.txt_investment_value_120_mo);
    this.twoFortyMonthBalance = (TextView) findViewById(R.id.txt_investment_value_240_mo);

    /* Here, I set up which EditText instances each of the input* instances refer to in the layout
     * files.
     */
    this.inputPrincipal = (EditText) findViewById(R.id.edit_principal);
    this.inputGrowth = (EditText) findViewById(R.id.edit_growth_rate);
    this.inputContrib = (EditText) findViewById(R.id.edit_contribution);


    // Here, I set up the final edit text such that when the enter key is pressed (while this field
    // is active), it has the same effect as pressing the calculate investment button.
    EditText contrib = (EditText) findViewById(R.id.edit_contribution);
    // https://stackoverflow.com/questions/30783435/textviews-setonkeylistener-for-enter-key-is-not-working
    // https://stackoverflow.com/questions/8233586/android-execute-function-after-pressing-enter-for-edittext
    contrib.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if ((keyEvent.getAction() == KeyEvent.ACTION_UP) &&
                ((i == KeyEvent.KEYCODE_ENTER) || (i == KeyEvent.KEYCODE_NUMPAD_ENTER)))
        {
          calculateInvestment();
          return true;
        }
        return false;
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
        this.myDrawerLayout.closeDrawer(findViewById(R.id.navigation));
        // startActivity(new Intent(this, BasicFinancialInvestActivity.class));
        return true;

      case R.id.settings_item:
        startActivity(new Intent(this, BasicFinancialPrefsActivity.class));
        return true;

      case R.id.budget_item:
        startActivity(new Intent(this, BasicFinancialMainActivity.class));
        return true;
    }
    return false;
  }


  /* balanceAfterWeeks calculates the value of an investment after
   * a given amount of weeks based on the principal investment,
   * expected APR return, and a weekly contribution by the investor.
   */
  public double balanceAfterWeeks(double principal, double weeklyContrib,
                                  double apr, double amtWeeks)
  {
    double numNoInterest = principal + (weeklyContrib * amtWeeks);

    double interestFactor = Math.pow((1 + (apr / 100)), (amtWeeks / 52));

    return numNoInterest * interestFactor;
  }

  /* balanceAfterWeeksString returns a string and calls balanceAfterWeeks based on user input.
   * It works best when the user only inputs digits and (if needed) a decimal point.
   *
   * If the user does not enter anything in one of the fields, its value for the call to
   * balanceAfterWeeks defaults to zero.
   */
  public String balanceAfterWeeksString(double amtWeeks)
  {
    double principal;
    double weeklyContrib;
    double apr;

    // I could have used ternary operators for the following if else blocks, but this is more
    // legible because a lot of the inputs are very long in name.
    if (this.inputPrincipal.getText().toString().equals(""))
    {
      principal = 0.0;
    }
    else
    {
      principal = Double.parseDouble(this.inputPrincipal.getText().toString());
    }

    if (this.inputContrib.getText().toString().equals(""))
    {
      weeklyContrib = 0.0;
    }
    else
    {
      weeklyContrib = Double.parseDouble(this.inputContrib.getText().toString());
    }

    if (this.inputGrowth.getText().toString().equals(""))
    {
      apr = 0.0;
    }
    else
    {
      apr = Double.parseDouble(this.inputGrowth.getText().toString());
    }

    return String.format(Locale.getDefault(),"%.2f",
            this.balanceAfterWeeks(principal,weeklyContrib, apr, amtWeeks));
  }

  /* calculateInvestment is the onClick function for the button labeled "Calculate Investment."
   * It updates the text in each of the TextViews in the table that indicates an eventual monetary
   * value.
   */
  public void calculateInvestment()
  {
    Log.d(TAG, "Calculating investment...");

    this.oneMonthBalance.setText(balanceAfterWeeksString(4.0));
    this.sixMonthBalance.setText(balanceAfterWeeksString(26.0));
    this.twelveMonthBalance.setText(balanceAfterWeeksString(52.0));
    this.twentyFourMonthBalance.setText(balanceAfterWeeksString(2 * 52.0));
    this.sixtyMonthBalance.setText(balanceAfterWeeksString(5 * 52.0));
    this.oneTwentyMonthBalance.setText(balanceAfterWeeksString(10 * 52.0));
    this.twoFortyMonthBalance.setText(balanceAfterWeeksString(20 * 52.0));
  }
}
