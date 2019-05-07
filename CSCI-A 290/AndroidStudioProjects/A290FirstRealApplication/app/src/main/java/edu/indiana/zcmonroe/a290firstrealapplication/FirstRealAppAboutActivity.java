/* FirstRealAppAboutActivity.java
 *
 * Contains the Java code for the "About" activity
 *
 * Created by: Zac Monroe
 * Created on: 2/15/18
 * Last modified by: Zac Monroe
 * Last modified on: 2/15/18
 * Assignment/Project: A290 Android First Real App
 * Refers to:
 *  - activity_first_real_app_about.xml
 **/

package edu.indiana.zcmonroe.a290firstrealapplication;

import android.app.Activity;
import android.os.Bundle;

public class FirstRealAppAboutActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first_real_app_about);
  }
}
