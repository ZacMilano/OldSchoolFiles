# Zac Monroe
# B365 - 11946
# HW 1
# Problem 5
# See hw1_submission.pdf for non-code work/answers

# Part (a): Simulate 1000 trials
M = 1000;
prob_of_a = 0.1;

occurrences_of_a = sum(runif(M) < prob_of_a);
p_hat_of_a = occurrences_of_a / M;
error = 1.96 * sqrt(p_hat_of_a * (1 - p_hat_of_a)/M);

cat("My estimated probability of event A P(A) is", p_hat_of_a, "+-", error, "\b.\n");

# Part (b): Simulate 1000 experiments of part (a)
experiments = rep(0,M);

for (i in 1:M)
{
  occurrences_of_a = sum(runif(M) < prob_of_a);
  p_hat_of_a = occurrences_of_a / M;
  error = 1.96 * sqrt(p_hat_of_a * (1 - p_hat_of_a)/M);

  # The i-th element in experiments is a boolean value; if it is true, then the
  # predicted probability of event A is within the confidence interval. Else
  # the predicted value is not within the confidence interval.
  experiments[i] = ((p_hat_of_a + error) >= prob_of_a) &&
                   ((p_hat_of_a - error) <= prob_of_a);
}

cat("\nOn average, after 1000 experiments, the true P(A) is contained within my confidence interval with probability", sum(experiments)/M, "\b.\n");
