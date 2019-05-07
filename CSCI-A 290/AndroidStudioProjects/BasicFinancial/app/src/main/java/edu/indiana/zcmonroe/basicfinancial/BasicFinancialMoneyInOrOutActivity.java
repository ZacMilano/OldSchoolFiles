/* BasicFinancialMoneyInOrOutActivity.java
 *
 * Contains the java code for the activity that appears
 * after pressing the floating action button in the main
 * activity.
 *
 * Created by: Zac Monroe
 * Created on: 2/19/18
 * Last modified by: Zac Monroe
 * Last modified on: 3/2/18
 * Assignment/Project: A290 Android: Basic Financial
 * Refers to:
 *  - activity_basic_financial_money_in_or_out.xml
 *  - BasicFinancialMoneyInActivity.java
 *  - BasicFinancialMoneyOutActivity.java
 **/

package edu.indiana.zcmonroe.basicfinancial;

// Libraries added for this project
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.support.v7.widget.Toolbar;
import android.widget.Button;


public class BasicFinancialMoneyInOrOutActivity extends AppCompatActivity
{
  // These buttons are the buttons that are displayed as the main attraction of the activity.
  Button moneyInButton;
  Button moneyOutButton;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic_financial_money_in_or_out);

    // Solution's source: https://developer.android.com/training/appbar/setting-up.html
    // This is how I set up the title at the top of the screen.
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    // Here I retrieve the buttons from the XML and set their onClick functionality.
    this.moneyInButton = findViewById(R.id.btn_money_in);
    this.moneyInButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        buttonsMethod(v);
      }
    });
    this.moneyOutButton = findViewById(R.id.btn_money_out);
    this.moneyOutButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        buttonsMethod(v);
      }
    });
  }

  /* buttonsMethod is what is called on a click of either button. The buttons,
   * using this method, each open an activity.
   */
  public void buttonsMethod(View v)
  {
    switch (v.getId())
    {
      case R.id.btn_money_in:
        Intent ia = new Intent(this, BasicFinancialMoneyInActivity.class);
        startActivity(ia);
        break;

      case R.id.btn_money_out:
        default:
          Intent ib = new Intent(this, BasicFinancialMoneyOutActivity.class);
          startActivity(ib);
          break;
    }
  }
}
