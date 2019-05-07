len = 100

x = runif(len,-.5,.4)
y = cumsum(x)

plot(exp(y))
title("My stock price");
