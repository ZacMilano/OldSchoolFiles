# Zac Monroe
# B365 - 11946
# HW 6
# Problem 4
# See my pdf for non-code work/answers

x = matrix(c(.7, .6, .5, .5, .5, .5, .7,
             .8, .7, .6, .7, .9, .8, .9),
           byrow=T, nrow=2);
z = array(0, c(2, 7, 2));

not_understand = 1;
understand = 2;
wrong = 1;
right = 2;

# Part (a)
for (i in 1:2) {
  for (j in 1:7) {
    z[i,j,right] = x[i,j];      # P(right | knowledge state i)
    z[i,j,wrong] = 1 - x[i,j];  # P(wrong | knowledge state i)
  }
}

# Use a Naive Bayes classifier to determine whether or not a student
# understands the topic given their answers to 7 questions
# answers : vector of {0,1} of length 7
understands = function(answers) {
  # P(the student understands)
  yes = 1;
  # P(the student does not understand)
  no = 1;

  for (j in 1:7) {
    # For each j, answers[j] is either 0 or 1. 0 indicates that the student
    # answered that question wrong. 1 indicates that the student answered that
    # question right. Adding 1 to each of these numbers (as below) gives the
    # proper index in z.
    yes = yes * z[understand, j, answers[j]+1];
    no = no * z[not_understand, j, answers[j]+1];
  }
  # Return the probability that the student understands the subject
  return(yes/(yes+no));
  # Return the most likely class
  # if (yes > no) {
  #   return(TRUE);
  # }
  # else return(FALSE);
}
