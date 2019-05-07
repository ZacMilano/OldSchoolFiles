# Zac Monroe
# B365 - 11946
# HW 1
# Problem 7
# See hw1_submission.pdf for non-code work/answers

# This is the sample space
p = c(.1, .2, .3, .35, .02, .03);

# This is the vector containing the maximum number for each element in p that a
# random number must be less than in order for the index of that element in p
# to be returned.
p_cu = cumsum(p);

# This is the meat of the program. This function computes the number based on p
# for a large simulation (run M times), and it gives the user a chance to
which_num = function()
{
  return(min(which(runif(1)[1] < p_cu)));
}

# Prep an M-length vector
returned_nums = rep(0,M);

# I chose 100k trials because it's overkill, so a lot of the randomness is
# filtered out, but it doesn't take much time to run.
M = 100000;

for (i in 1:M)
{
  # The which() function returns the index (or indices) of a vector contained
  # inside the predicate argument for which the predicate holds true, and the
  # min() function is returning the first number of whatever which() returns,
  # so that it is the actual case that we want.
  returned_nums[i] = which_num();
}

# Here's a histogram of the numbers that were actually returned. It is clear
# by looking at it that the proportion of each number returned to the total
# quantity of numbers collected is very close to the ideal numbers in p.
hist(returned_nums);

# This is what I got as an estimation after running which_num() M times.
p_hat = c(sum(returned_nums == 1)/M,
          sum(returned_nums == 2)/M,
          sum(returned_nums == 3)/M,
          sum(returned_nums == 4)/M,
          sum(returned_nums == 5)/M,
          sum(returned_nums == 6)/M);

cat("My estimated probability of getting the numbers 1-6 is, in this order,", p_hat, "\n\n");

cat("Try it yourself. Call the function which_num().\n");
