Basic Financial v1.0 README

- Made by Zac Monroe for A290 Android App Development at IUB

--------------------------------------------------------------------------------

  I ended up not using an SQLite database to store the user's data (I said I
would use one in MINI 3); I instead went with a SharedPreferences instance. the
database just didn't work for me, but the SharedPreferences did.

  In the budgeting activity (the main activity that is loaded when the app is
opened), please do not try to surpass 104 total "weeks" (i.e. if the top says
"Week 103", please do not press the "NEXT" button next to it). I have not tested
it past that quantity of weeks, and so I can not guarantee that it will work at
that extent.

  Some text entry fields (namely those whose inputs are numbers) only allow for
numeric entry as well as the decimal point. This is intentional (to prevent
crashing due to improper method calls). Pressing the enter key in the final text
entry fields of the investment mapping activity and the loan calculation area
produces a call to a method that I designed and thus does not call the function
to close the soft keyboard; to close the keyboard one must press the "back"
button (which normally takes one to the previous screen).

  Only the main three activities of Budgeting, Investment Mapping, and Settings
have the hamburger icon (and therefore navigation drawer) in the top left of the
screen. This is intentional, because every other screen in the app is meant to
be temporarily accessed, only to be left by cancelling what was just done (with
the "back" button) or advancing to a proper destination (by pressing appropriate
buttons and such to take the user back to a main screen).

  In the Settings activity, I changed the "Delete tags" option to "View tags"
because I thought it would be a bit more useful to be able to just SEE all the
tags created by oneself than to delete them (because the transactions are delet-
able, and those should be deleted instead of the tags that are associated with
them). I also changed it to a dialog, because that made a little more sense in
this setting. Pun not intended.

  Throughout the code, if there is a link to a webpage in a comment, that is an
indication that I used that source as help/a source of knowledge to help fix my
app in the way that immediately follows that comment. If there is no link for
something, I discovered/designed it myself.


  I made the icon/logo for the app as well as the image of three bars that
appears in the navigation drawer directly next to the logo. Both images were
made in MS Paint.

  Try to keep tags (associated with a transaction in the budgeting activity) to
a minimum length; tags are supposed to be short in this app (just another way to
characterize and describe the transaction). When they are especially long, it
makes the transaction viewed in the week a bit visually unappealing (irregularly
taller than the other ones). Good examples are "Necessity", "Bi-weekly",
"Savings", etc. Try to keep them at 10-12 characters at most to keep each tag on
one line.

  Everything else should look more or less as it does in my Phase 2 submission.
I didn't have to change much about my final product from what I originally had
planned, except for the method of storing user data, which I am doing with Gson
and SharedPreferences. Gson is a publicly available, free use Java library that
is useful for converting many types of Objects (even custom objects) into .json
files and/or strings in json format, as well as the other way around. Here is a
link to the license that says that I can use it:

  https://github.com/google/gson/blob/master/LICENSE

  I have also included a copy of this file in the .zip for this project, under
the name "apache license for gson.txt".

  Other than that, everything should be good to go and well-tested. I am very
proud of this app; I knew zero Java and only the HTML subset of XML before tak-
ing this class, and I learned 99% of what I know about each of them as well as
the Android environment because of this project. I've worked more than 50 hours
on this project and I wouldn't majorly change anything about it because it is
perfectly visually-appealing and well-functioning in my eyes. Enjoy!

  -Zac Monroe
