# Zac Monroe
# B365 - 11946
# HW 1
# Problem 4
# See hw1_submission.pdf for non-code work/answers

# Part (a): P(0 bullseyes)
p_none = choose(10,0)/(2^10);
cat("The probability that the archer hits the bullseye zero times is", p_none, "\b.\n");
# Part (b): P(1 bullseyes)
p_one = choose(10,1)/(2^10);
cat("The probability that the archer hits the bullseye once is", p_one, "\b.\n");

# Part (c): P(2 bullseyes)
p_two = choose(10,2)/(2^10);
cat("The probability that the archer hits the bullseye twice is", p_two, "\b.\n");

# Part (d): P(3 bullseyes)
p_three = choose(10,3)/(2^10);
cat("The probability that the archer hits the bullseye three times is", p_three, "\b.\n");

