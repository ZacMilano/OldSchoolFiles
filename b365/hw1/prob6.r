# Zac Monroe
# B365 - 11946
# HW 1
# Problem 6
# See hw1_submission.pdf for non-code work/answers

bag = c(1.2, 1.5, 3.2, 3.3, 3.4, 5.3, 6.3, 7.2, 8.9, 9.1);

amount_of_orderings = factorial(length(bag))/(factorial(length(bag)-2));

a_greater_than_b = 0+1+2+3+4+5+6+7+8+9;

cat("There are", amount_of_orderings, "possible orderings in which person A and person B can draw numbers from the bag. The probability that A's number is greater than B's number is", a_greater_than_b, "/", amount_of_orderings, "\b, or", a_greater_than_b/amount_of_orderings, "\b.\n");
