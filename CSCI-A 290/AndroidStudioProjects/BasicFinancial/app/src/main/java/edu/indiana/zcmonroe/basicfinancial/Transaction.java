/* Transaction.java
 *
 * Transaction instance objects are the primary components of the budget
 *
 * Created by: Zac Monroe
 * Created on: 2/18/18
 * Last modified by: Zac Monroe
 * Last modified on: 3/2/18
 * Assignment/Project: A290 Android: Basic Financial
 * Refers to:
 *  - [none]
 **/

package edu.indiana.zcmonroe.basicfinancial;

public class Transaction
{
  // isPositive is true if the transaction adds money, false if the transaction deducts money.
  // isPositive also determines the color of the amount/plus or minus sign text.
  private boolean isPositive;
  // amount is the quantity of money added or deducted because of the transaction.
  private double amount;
  // label is whatever the user wants to call it.
  private String label;
  // special is one of Paycheck, Bill, or Loan.
  private String special;
  // tag is whatever the user wants to call it; any of the tags can be deleted from the settings
  // activity.
  private String tag;

  public Transaction(boolean isPositive_, double amount_, String label_, String special_, String tag_)
  {
    this.isPositive = isPositive_;
    this.amount = amount_;
    this.label = label_;
    this.special = special_;
    this.tag = tag_;
  }
  
  /* These are the "getter" methods for this class's fields. I have no use for setter methods
   * because an instance of Transaction does not change beyond being deleted. All the fields
   * because an instance of Transaction does not change beyond being deleted. All the fields
   * are initially set with the constructor, which is called upon pressing the "Add Transaction"
   * button in either the money in or money out activity.
   */
  public boolean isPositive() {
    return this.isPositive;
  }

  public double getAmount() {
    return this.amount;
  }

  public String getLabel() {
    return this.label;
  }

  public String getSpecial() {
    return this.special;
  }

  public String getTag() {
    return this.tag;
  }
}
