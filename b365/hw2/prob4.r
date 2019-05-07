# Zac Monroe
# B365 - 11946
# HW 2
# Problem 4
# See hw2_submission.pdf for non-code work/answers

N = 1000000;

x = runif(N);

a = x < 0.5;
b = ((2 * x) %% 1) < .5;

cat("My 95% confidence interval for P(A) is", sum(a)/N, "+-",
    1.96/sqrt(4*N), "\b.\n");
cat("My 95% confidence interval for P(B) is", sum(b)/N, "+-",
    1.96/sqrt(4*N), "\b.\n");
cat("My 95% confidence interval for P(A,B) is", sum(a & b)/N, "+-",
    1.96/sqrt(4*N), "\b.\n");
