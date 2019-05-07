# Zac Monroe
# B365 - 11946
# HW 1
# Problem 1
# See hw1_submission.pdf for non-code work/answers

M = 10000;
error = 1.96/sqrt(4*M);
# 38416

play_round = function()
{
  return(runif(3)>.5);
}

one_round = runif(3) > .5;

wins_for_a = 0;

for (i in 1:M)
{
  one_round = play_round();

  while (sum(one_round) %in% c(0,3))
  {
    one_round = play_round();
  }

  if (sum(one_round) == 1)
  {
    wins_for_a = wins_for_a + one_round[1];
  }
  if (sum(one_round) == 2)
  {
    wins_for_a = wins_for_a + !one_round[1];
  }
}

cat("The proportion of times that player A won this game is",
    wins_for_a, "/", M, "\b, so my estimate for P(A wins) is",
    wins_for_a/M, "+-", error, "\b.\n");
