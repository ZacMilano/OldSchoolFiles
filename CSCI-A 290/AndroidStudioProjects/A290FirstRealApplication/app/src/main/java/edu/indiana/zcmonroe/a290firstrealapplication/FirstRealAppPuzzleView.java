/* FirstRealAppPuzzleView.java
 *
 * Contains the graphics code for the game activity
 *
 * Created by: Zac Monroe
 * Created on: 2/17/18
 * Last modified by: Zac Monroe
 * Last modified on: 2/17/18
 * Assignment/Project: A290 Android First Real App
 * Refers to:
 *  - FirstRealAppGameActivity.java
 *  - colors.xml
 **/

package edu.indiana.zcmonroe.a290firstrealapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

public class FirstRealAppPuzzleView extends View {

  private static final String TAG = "FirstRealAppPuzzleView";

  private final FirstRealAppGameActivity game;

  public FirstRealAppPuzzleView(Context context)
  {
    super(context);
    this.game = (FirstRealAppGameActivity) context;
    setFocusable(true);
    setFocusableInTouchMode(true);
  }

  private float width; // of one tile
  private float height; // of one tile
  private int selX; // X index of selected tile
  private int selY; // Y index of selected tile
  private final Rect selRect = new Rect();

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh)
  {
    width = w/9f;
    height = h/9f;
    getRect(selX, selY, selRect);
    Log.d(TAG, "onSizeChanged: width " + width + ", height " + height);
    super.onSizeChanged(w, h, oldw, oldh);
  }

  /*
   * getRect is a helper function for onSizeChanged that changes the size of the rectangles.
   */

  private void getRect(int x, int y, Rect rect)
  {
    rect.set((int) (x * width), (int) (y * height),
            (int) (x * width + width), (int) (y * height + height));
  }

  protected void onDraw(Canvas canvas)
  {
    // First draw the background.
    Paint background = new Paint();
    background.setColor(getResources().getColor(R.color.PuzzleBackground));
    canvas.drawRect(0, 0, getWidth(), getHeight(), background);

    // Next draw the board
      // First we define the colors for the grid lines
    Paint dark = new Paint();
    dark.setColor(getResources().getColor(R.color.PuzzleDark));
    Paint hilite = new Paint();
    hilite.setColor(getResources().getColor(R.color.PuzzleHiLite));
    Paint light = new Paint();
    light.setColor(getResources().getColor(R.color.PuzzleLight));

      // Second draw the minor grid lines (the major grid lines
      // will cover up some of the minors, so order is important
    for (int i = 0; i < 9; i++)
    {
      canvas.drawLine(0, i * height, getWidth(), i * height, light);
      canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
      canvas.drawLine(i * width, 0, i * width, getHeight(), light);
      canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
    }

      // Third draw the major grid lines
    for (int i = 0; i < 9; i++)
    {
      if (i % 3 != 0)
        continue;
      canvas.drawLine(0, i * height, getWidth(), i * height, light);
      canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilite);
      canvas.drawLine(i * width, 0, i * width, getHeight(), light);
      canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilite);
    }

    // Here, draw the numbers based on the difficulty

      // Here the color and style for the numbers is defined
    Paint foreground = new Paint(Paint.ANTI_ALIAS_FLAG);
    foreground.setColor(getResources().getColor(R.color.PuzzleForeground));
    foreground.setStyle(Style.FILL);
    foreground.setTextSize(height * .75f);
    foreground.setTextScaleX(width / height);
    foreground.setTextAlign(Paint.Align.CENTER);

      // Here, draw the number in the tile's center
    FontMetrics fm = foreground.getFontMetrics();
        // Center in x-dimension
    float x = width / 2;
        // Center in y-dimension
    float y = height / 2 - (fm.ascent + fm.descent) / 2;

    for (int i = 0; i < 9; i++)
    {
      for (int j = 0; j < 9; j++)
      {
        canvas.drawText(this.game.getTileString(i, j),
                i * width + x, j * height + y, foreground);
      }
    }
    // Here, draw the hints (during the game?)

    // Here, draw the selected tile in a manner that is clearly distinguishable from the other tiles

  }
}
