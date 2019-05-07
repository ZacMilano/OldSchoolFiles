/* BasicFinancialMoneyOutActivity.java
 *
 * Contains the java code for the activity that appears
 * after pressing the button in BasicFinancialMoneyInOrOutActivity
 * that indicates that the transaction removes money from the budget.
 *
 * Created by: Zac Monroe
 * Created on: 2/19/18
 * Last modified by: Zac Monroe
 * Last modified on: 3/2/18
 * Assignment/Project: A290 Android: Basic Financial
 * Refers to:
 *  - activity_basic_financial_money_out.xml
 *  - BasicFinancialMainActivity.java
 *  - W
 **/

package edu.indiana.zcmonroe.basicfinancial;

// Libraries added for this project
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.Math;
import java.security.Key;

public class BasicFinancialMoneyOutActivity extends AppCompatActivity
{
  // String used for logging
  private static final String TAG = "MoneyOutActivity";

  // This is the "ADD TRANSACTION" button.
  public Button addTransactionBut;

  /* This is the "Bill?" checkbox.
   * I want it to make the "Loan?" checkbox visible when it is ticked.
   */
  public CheckBox billChkBox;

  /* This is the "Loan?" checkbox.
   * I want it to make the rest of the tools for calculating information about a loan visible
   * when it (the checkbox) is ticked.
   */
  public CheckBox loanChkBox;

  /* This is the linear layout that the "Loan?" checkbox is contained in. When the "Bill?" checkbox
   * is ticked, this layout comes in to view (with its only initial child, the "Loan?" checkbox),
   * and when the "Loan?" checkbox (within it) is ticked, more things (another button, a pseudo-
   * table) are pushed into it (below the "Loan?" checkbox).
   */
  public LinearLayout loanLinParent;

  /* This is the main linear layout of the content file for this activity. The loanLinParent is
   * pushed into this with the LayoutInflater.
   */
  public LinearLayout moneyOutLinParent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_basic_financial_money_out);

    // Solution's source: https://developer.android.com/training/appbar/setting-up.html
    // This is how I set up the title at the top of the screen.
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Log.d(TAG, "Created!");

    // Here is where I assign which button the addTransactionBut actually refers to.
    this.addTransactionBut = (Button) findViewById(R.id.btn_add_money_out);
    // Here is where I assign the button's functionality.
    this.addTransactionBut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        addTrans(v);
      }
    });

    // Here is where I assign which checkbox billChkBox actually refers to.
    this.billChkBox = (CheckBox) findViewById(R.id.chk_bill);
    // Here is where I set up billChkBox's functionality
    this.billChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        Log.d(TAG, "Bill? " + String.valueOf(b));
        addLoanChkBox(b);
      }
    });

    // Here is where I set up where moneyOutLinParent points to.
    this.moneyOutLinParent = (LinearLayout) findViewById(R.id.lin_money_out_main);
  }

  /* addLoanChkBox is called when the "Bill?" checkbox is ticked. If this transaction is a bill, the
   * loan interface is shown, and otherwise it is removed.
   */
  private void addLoanChkBox(boolean isBill)
  {
    if (isBill)
    {
      // Here is where I assign the layout that loanLinParent actually points to.
      this.loanLinParent = (LinearLayout) findViewById(R.id.lin_loan_main);

      // https://www.bignerdranch.com/blog/understanding-androids-layoutinflater-inflate/
      // This is a LayoutInflater instance. It is what I am using to add views to the layout.
      LayoutInflater layoutInflater = getLayoutInflater();

      /* I set the parent to null because I use the addView method of LinearLayouts below this.
       * I  am using the index such that the loan layout will be placed second-to-last in the main
       * view (just above the "add transaction" button).
       */
      this.loanLinParent = (LinearLayout) layoutInflater.inflate(
              R.layout.content_basic_financial_loan_checkbox, null);
      this.moneyOutLinParent.addView(this.loanLinParent,
              this.moneyOutLinParent.getChildCount() - 1);

      // Here is where I assign which checkbox loanChkBox actually refers to.
      this.loanChkBox = (CheckBox) findViewById(R.id.chk_loan);
      // Here is where I set up loanChkBox's functionality
      this.loanChkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
          Log.d(TAG, "Loan? " + String.valueOf(b));
          loanChkBoxTicked(b);
        }
      });
    }

    else
    {
      Log.d(TAG, "Attempting to remove the loan checkbox!");
      /* If the layout that holds the loan is not in view, this does not get used (to avoid null
       * reference calls).
       */
      if (!(this.loanLinParent == null))
      {
        this.moneyOutLinParent.removeView((View) this.loanLinParent);
        Log.d(TAG, "Success!");
      }
    }
  }


  /* loanChkBoxTicked is called when loanChkBox is ticked. If the given input is true, it adds the
   * additional input fields, pseudo-table, and button for the loan tools. Otherwise it removes them
   */
  private void loanChkBoxTicked(boolean isLoan)
  {
    if (isLoan)
    {
      // https://www.bignerdranch.com/blog/understanding-androids-layoutinflater-inflate/
      LinearLayout loanItems;

      LayoutInflater layoutInflater = getLayoutInflater();

      loanItems = (LinearLayout) layoutInflater.inflate(
              R.layout.content_basic_financial_loan_view, null);
      this.loanLinParent.addView(loanItems);
      setUpLoanBtn();

      // This makes it so that pressing the enter button from the interest rate field calculates
      // the loan.
      EditText interest = (EditText) findViewById(R.id.edit_loan_interest);
      interest.setOnKeyListener(new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
          if ((keyEvent.getAction() == KeyEvent.ACTION_UP) &&
                  ((i == KeyEvent.KEYCODE_ENTER) || (i == KeyEvent.KEYCODE_NUMPAD_ENTER)))
          {
            onCalcLoanBtnClicked();
            return true;
          }
          return false;
        }
      });
    }

    else
    {
      Log.d(TAG, "Attempting to remove the loan items!");
      /* If the layout that holds the loan is not in view, this does not get used (to avoid null
       * reference calls).
       */
      if (!(this.loanLinParent == null) && !(findViewById(R.id.lin_loan_main) == null))
      {
        this.moneyOutLinParent.removeView(findViewById(R.id.lin_loan_main));
        Log.d(TAG, "Success!");
        addLoanChkBox(true);
      }
    }
  }

  /* setUpLoanBtn sets the onClickListener for the "Calculate loan" button, which calls
   * onCalcLoanBtnClicked().
   */
  private void setUpLoanBtn() {
    if (findViewById(R.id.btn_calculate_loan) != null)
    {
      /* This is the "Calculate loan" button. Its functionality is to update the table values
       * below it.
       */
      Button calcLoanBtn = (Button) findViewById(R.id.btn_calculate_loan);
      calcLoanBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          onCalcLoanBtnClicked();
        }
      });
    }
  }

  /* onCalcLoanBtnClicked updates the bottom row's text values in lin_loan_table by using
   * the calculateMonths and monthlyPaymentFinishInYear methods.
   */
  private void onCalcLoanBtnClicked()
  {
    /* Here I declare the values that are to be passed into the public calculation methods of this
     * class (calculateMonths, monthlyPaymentFinishInYear), and if they are not filled in (or are
     * null) they become zero.
     */
    Log.d(TAG, "Calculate loan button clicked");
    double princ;
    double interest;
    double payment;

    if (findViewById(R.id.edit_loan_balance) != null) {
      EditText editPrincipal = (EditText)findViewById(R.id.edit_loan_balance);
      if ("".equals(editPrincipal.getText().toString()))
      {
        Log.d(TAG, "Defaulting principal amount to zero because empty field");
        princ = 0.0;
      }
      else
      {
        Log.d(TAG, "Setting principal amount to possibly non-zero value");
        princ = Double.valueOf(editPrincipal.getText().toString());
      }
    }
    else {
      Log.d(TAG, "Defaulting principal amount to zero because null EditText");
      princ = 0.0;
    }

    if (findViewById(R.id.edit_loan_interest) != null) {
      EditText editInterest = (EditText)findViewById(R.id.edit_loan_interest);
      if ("".equals(editInterest.getText().toString()))
      {
        Log.d(TAG, "Defaulting interest amount to zero because empty field");
        interest = 0.0;
      }
      else
      {
        Log.d(TAG, "Setting interest amount to possibly non-zero value");
        interest = Double.valueOf(editInterest.getText().toString());
      }
    }
    else {
      Log.d(TAG, "Defaulting interest amount to zero because null EditText");
      interest = 0.0;
    }

    if (findViewById(R.id.edit_amount) != null) {
      EditText editAmt = (EditText)findViewById(R.id.edit_amount);
      if ("".equals(editAmt.getText().toString()))
      {
        Log.d(TAG, "Defaulting payment amount to zero because empty field");
        payment = 0.0;
      }
      else
      {
        Log.d(TAG, "Setting payment amount to possibly non-zero value");
        payment = Double.valueOf(editAmt.getText().toString());
      }
    }
    else {
      Log.d(TAG, "Defaulting payment amount to zero because null EditText");
      payment = 0.0;
    }

    if (findViewById(R.id.txt_loan_how_many_months_value) != null)
    {
      TextView howManyMonths = findViewById(R.id.txt_loan_how_many_months_value);
      howManyMonths.setText(calculateMonths(princ, interest, payment));
    }

    if (findViewById(R.id.txt_loan_min_payment_year_value) != null)
    {
      TextView minPayment = findViewById(R.id.txt_loan_min_payment_year_value);
      minPayment.setText(monthlyPaymentFinishInYear(princ, interest));
    }
  }

  /* The following two methods are for when the "Loan?" checkbox is ticked; they are called when
   * the user presses the "Calculate loan" button.
   */

  /* calculateMonths calculates the amount of months it would take to
   * completely pay off the loan with the given principal amount,
   * APR interest rate, and monthly payment. Returns ">1200" if the
   * loan can never be paid off (i.e. not within 100 years).
   */
  public String calculateMonths(double princ, double interest, double payment)
  {
    interest /= 100.0;
    interest += 1.0;
    interest = Math.pow(interest, (1.0/12.0));

    double months = 0.0;

    while (princ > 0.0)
    {
      princ *= interest;
      princ -= payment;
      months++;

      if (months > 1200.0)
      {
        return ">100 years. Try a higher monthly payment.";
      }
    }

    return String.valueOf(Math.round(months));
  }

  /* monthlyPaymentFinishInYear calculates the monthly payment that one
   * would need to pay in order to finish off the loan within 12 months.
   */
  public String monthlyPaymentFinishInYear(double princ, double interest)
  {
    interest /= 100.0;
    interest += 1.0;

    double amount = princ;
    amount *= interest;
    amount /= 12.0;

    String amountAsString;
    amountAsString = String.format("%.2f", amount);
    return ("$" + amountAsString);
  }

  /* addTrans is called when the "Add transaction" button is clicked; it adds a new transaction,
   * based on the fields in this form, to the current selected week (the week that was visible)
   * before attempting to add a transaction).
   */
  public void addTrans(View v)
  {
    Log.d(TAG, "Add transaction button clicked!");

    EditText editLabel = (EditText) findViewById(R.id.edit_label);
    String label = (editLabel == null)? "" : editLabel.getText().toString();

    EditText editAmount = (EditText) findViewById(R.id.edit_amount);
    double amount = ((editAmount == null) || (editAmount.getText().toString().equals("")))? 0 :
            Double.valueOf(editAmount.getText().toString());

    CheckBox chkBill = (CheckBox) findViewById(R.id.chk_bill);
    CheckBox chkLoan = (CheckBox) findViewById(R.id.chk_loan);
    String special = "";
    special = (chkBill == null || !chkBill.isChecked())? special : "Bill";
    special = (chkLoan == null || !chkLoan.isChecked())? special : "Loan";


    EditText editTag = (EditText) findViewById(R.id.edit_tag);
    String tag = (editTag == null)? "" : editTag.getText().toString();

    Transaction t = new Transaction(false, amount, label, special, tag);

    Week weekAddedTo = BasicFinancialMainActivity.weeks
            .get(BasicFinancialMainActivity.currentWeekIndex);
    weekAddedTo.addTrans(t);
    BasicFinancialMainActivity.weeks.set(BasicFinancialMainActivity.currentWeekIndex, weekAddedTo);
    BasicFinancialMainActivity.saveWeeksData();

    startActivity(new Intent(this, BasicFinancialMainActivity.class));
  }
}
