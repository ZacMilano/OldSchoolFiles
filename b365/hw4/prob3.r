# Zac Monroe
# B365 - 11946
# HW 4
# Problem 3
# See my .pdf for non-code work/answers

N = 1000;
tests = c(0.65, 0.60, 0.57, 0.62, 0.58, 0.64, 0.67, 0.58, 0.61, 0.60);

p_hat = rep(0.30, N);
not_p_hat = rep(0.70, N);
outcomes = rep(0,N);
correct = rep(0,N);

for (i in 1:N)
{
  # Part (a)
  # Prior probability/"actual" classification of whether or not the i-th
  # population member actually has the condition
  has_condition = runif(1) < 0.30;

  # Part (b)
  # test_results[i] is TRUE if the i-th test gives a positive result
  if (has_condition)
  {
    test_results = runif(10) < tests;

    for (j in 1:10)
    {
      # Part (c)
      # Probability that the condition is present
      p_hat[i] = p_hat[i] * tests[j];
      not_p_hat[i] = not_p_hat[i] * (1-tests[j]);
    }
  }
  else
  {
    test_results = runif(10) < (1-tests);

    for (j in 1:10)
    {
      # Part (c)
      # Probability that the condition is present
      p_hat[i] = p_hat[i] * (1-tests[j]);
      not_p_hat[i] = not_p_hat[i] * tests[j];
    }
  }

  # Part (d)
  # Boolean vector indicating whether or not someone has the disease.
  # Say that trait is present if P(having condition) > P(not having condition),
  # or if p_hat > not_p_hat
  outcomes[i] = p_hat[i] > not_p_hat[i];

  # Part (e)
  # Tally of correctly-identified individuals
  correct[i] = (has_condition == outcomes[i]);
}

cat("My naive Bayes classifier indicated that", 100*sum(outcomes)/N,
    "\b% of people had the disease.\n");
cat("My naive Bayes classifier correctly guessed whether or not a member",
    "of the population had the common condition", 100*sum(correct)/N,
    "\b% of the time.\n");
