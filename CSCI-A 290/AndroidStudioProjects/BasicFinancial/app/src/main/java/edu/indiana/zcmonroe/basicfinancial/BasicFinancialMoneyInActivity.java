/* BasicFinancialMoneyInActivity.java
 *
 * Contains the java code for the activity that appears
 * after pressing the button in BasicFinancialMoneyInOrOutActivity
 * that indicates that the transaction adds money to the budget.
 *
 * Created by: Zac Monroe
 * Created on: 2/19/18
 * Last modified by: Zac Monroe
 * Last modified on: 3/2/18
 * Assignment/Project: A290 Android: Basic Financial
 * Refers to:
 *  - activity_basic_financial_money_in.xml
 *  - BasicFinancialMainActivity.java
 **/

package edu.indiana.zcmonroe.basicfinancial;

// Libraries added for this project
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.View.OnKeyListener;

public class BasicFinancialMoneyInActivity extends AppCompatActivity
{
  // String used for logging
  private static final String TAG = "MoneyInActivity";

  // This is the "ADD TRANSACTION" button.
  Button addTransactionBut;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic_financial_money_in);

    Log.d(TAG, "Created!");

    // Here is where I assign which button the addTransactionBut actually refers to.
    this.addTransactionBut = findViewById(R.id.btn_add_money_in);
    // Here is where I assign the button's functionality.
    this.addTransactionBut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        addTrans(v);
      }
    });

    // Solution's source: https://developer.android.com/training/appbar/setting-up.html
    // This is how I set up the title at the top of the screen.
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  // addTrans gets the current week that was selected before starting this activity and places a
  // transaction object instance into it based on the text and checkbox fields.
  public void addTrans(View v)
  {
    Log.d(TAG, "Add transaction button clicked!");

    // https://alvinalexander.com/java/edu/pj/pj010018
    // How I learned the ternary operator ^
    EditText editLabel = (EditText) findViewById(R.id.edit_label);
    String label = (editLabel == null)? "" : editLabel.getText().toString();

    EditText editAmount = (EditText) findViewById(R.id.edit_amount);
    double amount = ((editAmount == null) || (editAmount.getText().toString().equals("")))? 0 :
            Double.valueOf(editAmount.getText().toString());

    CheckBox chkSpecial = (CheckBox) findViewById(R.id.chk_paycheck);
    String special = ((chkSpecial == null) || !chkSpecial.isChecked() )? "" : "Paycheck";

    EditText editTag = (EditText) findViewById(R.id.edit_tag);
    String tag = (editTag == null)? "" : editTag.getText().toString();

    Transaction t = new Transaction(true, amount, label, special, tag);

    Week weekAddedTo = BasicFinancialMainActivity.weeks
            .get(BasicFinancialMainActivity.currentWeekIndex);
    weekAddedTo.addTrans(t);
    BasicFinancialMainActivity.weeks.set(BasicFinancialMainActivity.currentWeekIndex, weekAddedTo);
    BasicFinancialMainActivity.saveWeeksData();

    startActivity(new Intent(this, BasicFinancialMainActivity.class));
  }
}
