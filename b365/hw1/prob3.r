# Zac Monroe
# B365 - 11946
# HW 1
# Problem 3
# See hw1_submission.pdf for non-code work/answers

# Part (a): Calculate |Omega|
abs_omega = choose(52,2);
cat("The size of my sample space is", abs_omega, "\n");

# Part (b): How many elements of Omega have both cards having same rank?
num_pairs_same_rank = 13 * choose(4,2);
cat("There are", num_pairs_same_rank, "elements in Omega whose cards have the same rank.\n");

# Part (c): Prob of drawing a pair
pair_prob = num_pairs_same_rank / abs_omega;
cat("The probability of drawing a pair, or two cards with the same rank, from a shuffled deck is", num_pairs_same_rank, "/", abs_omega, "\b, or", pair_prob, "\b.\n");
