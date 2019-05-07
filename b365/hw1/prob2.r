# Zac Monroe
# B365 - 11946
# HW 1
# Problem 2
# See hw1_submission.pdf for non-code work/answers

M = 38416;

# match: return the index of the first instance of the first argument as it
# pertains to the second argument (or: the index location in the second
# argument of the first argument)

# runif(M): I am using a list of 100 numbers just so I am sure that there is
# eventually a heart drawn. (Theoretically, since A and B replace the cards
# after drawing them, it's totally possible that they never draw a heart, but
# the probability of that happening is 4^(-100) which is ~6.22e-61. Not very
# likely.)

# If the random number in runif is more than .75, count it as a heart, and make
# that value true.

x = rep(M);
error_2 = 1.96/sqrt(4*M);

for (i in 1:M)
{
  # '==0' at the end: If the index that match() returns is 0, then player B
  # was the first to draw a heart. If match() returns 1, then it was player A
  # who was the first to draw a heart. This is a bit confusing so I wanted to
  # explain it here.
  # So, if match() returns a 0, x[i] will be true, which means that player A
  # drew the first heart.
  x[i] = ( (match(TRUE, (runif(100) > .75)) %% 2) ) == 1;
}

cat("#2: The estimated probability that A draws the first heart is", sum(x)/M, "+-", error_2, "\n");
