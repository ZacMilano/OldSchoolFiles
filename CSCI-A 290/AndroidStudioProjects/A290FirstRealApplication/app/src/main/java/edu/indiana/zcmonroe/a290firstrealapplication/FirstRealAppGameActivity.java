/* FirstRealAppGameActivity.java
 *
 * Contains the Java code for controlling the game activity
 *
 * Created by: Zac Monroe
 * Created on: 2/16/18
 * Last modified by: Zac Monroe
 * Last modified on: 2/17/18
 * Assignment/Project: A290 Android First Real App
 * Refers to:
 *  - FirstRealAppPuzzleView.java
 **/
package edu.indiana.zcmonroe.a290firstrealapplication;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class FirstRealAppGameActivity extends Activity {

  private static final String TAG = "FRAGameActivity";

  public static final String KEY_DIFFICULTY =
          "edu.indiana.zcmonroe.a290firstrealapplication.difficulty";

  private static final int DIFFICULTY_EASY = 0;
  private static final int DIFFICULTY_MEDIUM = 1;
  private static final int DIFFICULTY_HARD = 2;

  private int puzzle[];

  private FirstRealAppPuzzleView puzzleView;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "onCreate");

    int diff = getIntent().getIntExtra(KEY_DIFFICULTY, DIFFICULTY_EASY);
    puzzle = getPuzzle(diff);
    calculateUsedTiles();

    puzzleView = new FirstRealAppPuzzleView(this);
    setContentView(puzzleView);
    puzzleView.requestFocus();
  }

  private final int used[][][] = new int[9][9][];

  // "getter" method for the "used" array
  protected int[] getUsedTiles(int x, int y)
  {
    return used[x][y];
  }

  // calculateUseTiles determines which tiles have been used yet
  private void calculateUsedTiles()
  {
    for (int x = 0; x < 9; x++)
    {
      for (int y = 0; y < 9; y++)
      {
        used[x][y] = calculateUsedTiles(x, y);
        // do more here for LogCat and toPuzzleString (?)
      }
    }
  }

  private int[] calculateUsedTiles(int x, int y)
  {
    int c[] = new int[9];

    // Check rows of tiles first
    for (int i = 0; i < 9; i++)
    {
      if (i == x)
        continue;
      int t = getTile(i, y);
      if (t != 0)
        c[t-1] = t;
    }

    // Next check columns of tiles
    for (int i = 0; i < 9; i++)
    {
      if (i == y)
        continue;
      int t = getTile(i, x);
      if (t != 0)
        c[t-1] = t;
    }

    // Next check 3x3 blocks
    int startX = (x / 3) * 3;
    int startY = (y / 3) * 3;

    for (int i = startX; i < startX + 3; i++)
    {
      for (int j = startY; j < startY + 3; j++)
      {
        if (i == x && j == y)
          continue;

        int t = getTile(i, j);

        if (t!= 0)
          c[t-1] = t;
      }
    }

    // Compress(?) array
    int nused = 0;
    for (int t : c)
    {
      if (t != 0)
        nused++;
    }

    int c1[] = new int[nused];
    nused = 0;
    for (int t : c)
    {
      if (t != 0)
        c1[nused++] = t;
    }

    return c1;
  }

  private final String easyPuzzle =
          "360000000004230800000004200" +
                  "070460003820000014500013020" +
                  "001900000007048300000000045";
  private final String mediumPuzzle =
          "650000070000506000014000005" +
                  "007009000002314700000700800" +
                  "500000630000201000030000097";
  private final String hardPuzzle =
          "009000000080605020501078000" +
                  "000000700706040102004000000" +
                  "000720903090301080000000600";

  /*
   * getPuzzle comes up with a puzzle based on the difficulty level
   */
  private int[] getPuzzle(int diff)
  {
    String puz;
    // To do: Continue the last game with this method

    switch (diff)
    {
      case DIFFICULTY_HARD:
        puz = hardPuzzle;
        break;

      case DIFFICULTY_MEDIUM:
        puz = mediumPuzzle;
        break;

      case DIFFICULTY_EASY:
      default:
        puz = easyPuzzle;
        break;
    }
    return fromPuzzleString(puz);
  }

  /*
   * fromPuzzleString converts a puzzle string into an array
   */
  static protected int[] fromPuzzleString(String string)
  {
    int[] puz = new int[string.length()];

    for (int i = 0; i < puz.length; i++)
    {
      puz[i] = string.charAt(i) - '0';
    }
    return puz;
  }

  /*
   * getTile returns the tile at the given coordinates
   */
  private int getTile(int x, int y)
  {
    return puzzle[y * 9 + x];
  }

  /*
   * getTileString returns a string for the tile at the given coordinates
   */
  protected String getTileString(int x, int y)
  {
    int v = getTile(x, y);
    if (v == 0)
      return "";
    else
      return String.valueOf(v);
  }
}
