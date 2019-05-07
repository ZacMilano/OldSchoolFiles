/* FirstRealAppPrefsActivity.java
 *
 * Contains the Java code for the settings activity
 *
 * Created by: Zac Monroe
 * Created on: 2/15/18
 * Last modified by: Zac Monroe
 * Last modified on: 2/17/18
 * Assignment/Project: A290 Android First Real App
 * Refers to:
 *  - settings.xml
 **/

package edu.indiana.zcmonroe.a290firstrealapplication;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class FirstRealAppPrefsActivity extends PreferenceActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.settings);
  }
}
