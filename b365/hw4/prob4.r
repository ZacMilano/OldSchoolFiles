# Zac Monroe
# B365 - 11946
# HW 4
# Problem 4
# See my .pdf for non-code work/answers

N = 1000;
p1 = c(0.65, 0.60, 0.57, 0.62, 0.58, 0.64, 0.67, 0.58, 0.61, 0.60);
p2 = 1 - p1;

p_hat = rep(0.30, N);
not_p_hat = rep(0.70, N);
# Prior probability/"actual" classification of whether or not the i-th
# population member actually has the condition
has_condition = runif(N) < 0.30;
outcomes = rep(0,N);
correct = rep(0,N);

test_percents = rep(0,10);

for (i in 1:N)
{

  if (has_condition[i])
  {
    # test_results[k] is TRUE if the k-th test gives a positive result
    test_results = runif(1) < p1;
    test_percents = test_percents + test_results;
    for (j in 1:10)
    {
      # Probability that the condition is present
      p_hat[i] = p_hat[i] * p1[j];
      not_p_hat[i] = not_p_hat[i] * p2[j];
    }
  }
  else
  {
    # test_results[k] is TRUE if the k-th test gives a positive result
    test_results = runif(1) < p2;
    for (j in 1:10)
    {
      # Probability that the condition is present
      p_hat[i] = p_hat[i] * p1[j];
      not_p_hat[i] = not_p_hat[i] * p2[j];
    }
  }


  # Boolean vector indicating whether or not someone has the disease.
  # Say that trait is present if P(having condition) > P(not having condition),
  # or if p_hat > not_p_hat
  outcomes[i] = p_hat[i] > not_p_hat[i];

  # Tally of correctly-identified individuals
  correct[i] = (has_condition[i] == outcomes[i]);
}

test_percents = test_percents/(sum(has_condition));

cat("Here are some percentages for how each of the tests in p1 performed,",
    "where each number is the proportion of correct positive diagnoses to",
    "total number of cases in which the condition exists:\n");
print(test_percents);
cat("My naive Bayes classifier indicated that", 100*sum(outcomes)/N,
    "\b% of people had the disease.\n");
cat("My naive Bayes classifier correctly guessed whether or not a member",
    "of the population had the common condition", 100*sum(correct)/N,
    "\b% of the time, and so it had an error rate of", 100*(1-sum(correct)/N),
    "\b%.\n");
