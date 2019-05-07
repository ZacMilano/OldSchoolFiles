# Zac Monroe
# B365 - 11946
# HW 7
# Problem 3
# See my pdf for non-code work/answers

data(nottem);
y = nottem;
n = length(y);
x = 1:n;

# Part (a)
plot(x, y, type='b', col='red');

# Part (b)
X = matrix(x, nrow=n, ncol=1);
X = cbind(X, rep(1,n));

coeffs = solve(t(X) %*% X, t(X) %*% y);

abline(coeffs[1], coeffs[2]);
cat('a =', coeffs[1], '\nb =', coeffs[2], '\n');

# Part (c)
X = cbind(cos(2 * pi * x / 12), sin(2 * pi * x / 12), rep(1, n));
coeffs = solve(t(X) %*% X, t(X) %*% y);
y_hat = X %*% coeffs;

cat('\nsse of model sans linear growth =', sum((y - y_hat)^2), '\n');

# Uncomment this line (and comment out the line in part (d) below) to see the
# model that doesn't include sales growth.
# points(x, y_hat, type='b', col='blue');

# Part (d)
X = cbind(cos(2 * pi * x / 12), sin(2 * pi * x / 12), rep(1, n), x);
coeffs = solve(t(X) %*% X, t(X) %*% y);
y_hat = X %*% coeffs;

cat('sse of model with linear growth =', sum((y - y_hat)^2), '\n');

# Uncomment this line (and comment out the line in part (c) above) to see the
# model that doesn't include sales growth.
points(x, y_hat, type='b', col='green');

cat('\nd =', coeffs[4], '\n');
