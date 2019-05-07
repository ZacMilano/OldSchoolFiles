/* Week.java
 *
 * Week object instances each display one week's transactions.
 *
 * Created by: Zac Monroe
 * Created on: 2/18/18
 * Last modified by: Zac Monroe
 * Last modified on: 3/2/18
 * Assignment/Project: A290 Android: Basic Financial
 * Refers to:
 *  - Transaction.java
 **/

package edu.indiana.zcmonroe.basicfinancial;

// Libraries added for this project
import android.content.Context;
import android.util.Log;
import java.util.ArrayList;
import com.google.gson.Gson;

public class Week
{
  // String used for logging
  private static final String TAG = "Week";

  private ArrayList<Transaction> transactions;

  public Week()
  {
    this.transactions = new ArrayList<Transaction>();
  }

  // addTrans adds a transaction to this week instance's array list of transactions
  public void addTrans(Transaction t)
  {
    // This and all other instances of gson:
    // https://sites.google.com/site/gson/gson-user-guide
    Gson gsonInstance = new Gson();
    this.transactions.add(t);
    Log.d(TAG, "This week's transactions: " + gsonInstance.toJson(this.transactions));
  }

  // getTrans is a getter method for the week's transactions
  public ArrayList<Transaction> getTrans()
  {
    return this.transactions;
  }
}
