# Zac Monroe
# B365 - 11946
# HW 3
# Problem 1
# See hw3_submission.pdf for non-code work/answers

N = 10000;

p_t = 0.2;
p_s_given_t = 0.8;
p_s_given_not_t = 0.3;

# The first piece in each of the trials is whether or not that person has trait
# T; the second piece is whether or not the person has trait S
has_t = rep(0,N);
has_s = rep(0,N);

for (i in 1:N)
{
  has_t[i] = runif(1) < p_t;
  if (has_t[i])
  {
    has_s[i] = runif(1) < p_s_given_t;
  } else
  {
    has_s[i] = runif(1) < p_s_given_not_t;
  }
}

cat("", sum(has_t & has_s), "people have both T and S,\n", sum(has_t & !has_s),
    "people have T but not S,\n", sum(!has_t & has_s), "people have S but",
    "not T, and\n", sum(!has_t & !has_s), "people have neither T nor S, from",
    "this experiment of", N, "people.\n");

cat(" So, P(T | S) = P(S,T)/P(S) = ~", sum(has_s & has_t)/sum(has_s), "+-",
    1.96/sqrt(4*sum(has_s)), "\n");
