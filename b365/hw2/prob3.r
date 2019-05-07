# Zac Monroe
# B365 - 11946
# HW 2
# Problem 3
# See hw2_submission.pdf for non-code work/answers

N = 1000;

x = runif(N);
y = runif(N);

a = x + y < 1;
b = x - y < 0;

plot_appearance = rep(0,N);

for (i in 1:N)
{
  #  a &&  b  =  black circles
  #  a && !b  =  red triangles
  # !a &&  b  =  green plus signs
  # !a && !b  =  blue Xs
  plot_appearance[i] =
    ifelse(a[i] && b[i], 1,
           ifelse(a[i] && !b[i], 2,
                  ifelse(!a[i] && b[i], 3, 4)));
}

plot(x, y, col=plot_appearance, pch=plot_appearance);

cat("My 95% confidence interval for P(A) is", sum(a)/N,
    "+-", 1.96/sqrt(4*N), "\b.\n",
    "\bMy 95% confidence interval for P(A|B) is", sum(a & b)/sum(b),
    "+-", 1.96/sqrt(sum(4*b)), "\b.\n");
