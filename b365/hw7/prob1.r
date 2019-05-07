# Zac Monroe
# B365 - 11946
# HW 7
# Problem 1
# See my pdf for non-code work/answers

# Part (a)
data = read.csv("Vocab.csv", stringsAsFactors=FALSE, sep=",");

education_level = 4;  # The 4th column
score = 5;            # The 5th column

X = cbind(data[,education_level],   # First predictor is education level
          rep(1,length(data[,1]))); # Second is a bias
y = data[,score];

# Part (b)
a_b = solve(t(X) %*% X, t(X) %*% y);

alpha = a_b[1];
beta = a_b[2];

cat("My alpha value is", alpha, "and my beta value is", beta, "\n");
