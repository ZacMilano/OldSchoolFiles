# Zac Monroe
# B365 - 11946
# HW 7
# Problem 2
# See my pdf for non-code work/answers

dat = read.csv("ais.csv", stringsAsFactors=FALSE, sep=",");

y = dat$rcc;

# Part (a)
X = as.matrix(cbind(dat[,3:12],
                    bias=rep(1, length(dat[,1]))));

coeffs = solve(t(X) %*% X, t(X) %*% y);
cat("These are my coefficients that I found (for part (a)):\n");
print(coeffs);

# Part (b)
y_hat = X %*% coeffs;
sse = sum((y - y_hat)^2);
cat("sse =", sse, "\n");

# Part (c)
sse_sans_var = rep(0,11); # will store sse from removing each of the variables
                          # in columns 3-12 as well as the bias

for (i in 3:13) {
  X_i = X[,-i];
  coeffs_i = solve(t(X_i) %*% X_i, t(X_i) %*% y);

  y_hat_i = X_i %*% coeffs_i;
  sse_sans_var[i-2] = sum((y - y_hat_i)^2);
}

cat("Variable #", 2+which.max(sse_sans_var),
                  # Add 2 to get true index of variable from "dat"
    "(", colnames(dat)[2+which.max(sse_sans_var)], ")", "seems to be the most",
    "influential because omitting it gives the biggest jump in sse, and that",
    "jump is", max(sse_sans_var) - sse, "\n");
