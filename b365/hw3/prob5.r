# Zac Monroe
# B365 - 11946
# HW 3
# Problem 5
# See hw3_submission.pdf for non-code work/answers

p_a = 0.5;
p_b = 0.25;
p_c = 0.25;

p_dice_non_cumul = c(p_a, p_b, p_c);

p_dice = cumsum(p_dice_non_cumul);


probs_a = cumsum(rep(1/6,6));
roll_a = function()
{
  return(min(which(probs_a >= runif(1)[1])));
}

probs_b = cumsum(c(1/9, 2/9, 1/9, 2/9, 1/9, 2/9));
roll_b = function()
{
  return(min(which(probs_b >= runif(1)[1])));
}

probs_c = cumsum(c(2/9, 2/9, 2/9, 1/9, 1/9, 1/9));
roll_c = function()
{
  return(min(which(probs_c >= runif(1)[1])));
}

choose_die = function()
{
  probs = p_dice;
  return(min(which(probs >= runif(1)[1])));
}

roll_die = function(die)
{
  # cat("Die #", die, "chosen (1 = A, 2 = B, 3 = C).\n");
  switch(die,
         c(roll_a(), roll_a(), roll_a()),
         c(roll_b(), roll_b(), roll_b()),
         c(roll_c(), roll_c(), roll_c()));
}

# Part (a)
print(roll_die(choose_die()));

# Part (d)
N = 1000;
correct = rep(0,N);

for (i in 1:N)
{
  # Choose a die
  die = choose_die();
  # Roll it 3 times
  rolls = roll_die(die);

  # To keep track of probability guesses
  probs_each_die = rep(FALSE, 3);

  # Denominator that will divide other terms
  den = (p_a * probs_a[rolls[1]] * probs_a[rolls[2]] * probs_a[rolls[3]]) +
    (p_b * probs_b[rolls[1]] * probs_b[rolls[2]] * probs_b[rolls[3]]) +
    (p_c * probs_c[rolls[1]] * probs_c[rolls[2]] * probs_c[rolls[3]]);

  # Likelihood that this outcome was from die A
  probs_each_die[1] = p_a * probs_a[rolls[1]] * probs_a[rolls[2]] *
    probs_a[rolls[3]] / den;

  # Likelihood that this outcome was from die B
  probs_each_die[2] = p_b * probs_b[rolls[1]] * probs_b[rolls[2]] *
    probs_b[rolls[3]] / den;

  # Likelihood that this outcome was from die C
  probs_each_die[3] = p_c * probs_c[rolls[1]] * probs_c[rolls[2]] *
    probs_c[rolls[3]] / den;

  # Is correct if the max probability's index (1, 2, or 3) is equal to the die
  # (1, 2, or 3)
  correct[i] = (die == which.max(probs_each_die));
}

cat("Proportion of correct guesses is", sum(correct)/N, "\b.\n");
