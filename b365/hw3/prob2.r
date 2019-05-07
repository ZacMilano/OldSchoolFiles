# Zac Monroe
# B365 - 11946
# HW 3
# Problem 2
# See hw3_submission.pdf for non-code work/answers

N = 10000;

p_a = 0.20;
p_b = 0.30;
p_c = 0.50;

p_favors_given_a = 0.40;
p_favors_given_b = 0.60;
p_favors_given_c = 0.80;

p_a_given_favors = p_a * p_favors_given_a /
  (p_a * p_favors_given_a +
   p_b * p_favors_given_b +
   p_c * p_favors_given_c);


sum = 0;

for (i in 1:N)
{
  if (runif(1) < p_a_given_favors)
  {
    sum = sum + 1;
  }
}

cat("P(A | F) = ~", sum/N, "\n");
