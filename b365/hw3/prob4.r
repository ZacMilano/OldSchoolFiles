# Zac Monroe
# B365 - 11946
# HW 3
# Problem 4
# See hw3_submission.pdf for non-code work/answers

data("iris");
print(dimnames(iris));
print(iris);

pairs(iris, pch=c(1,2,3)[iris$Species],
      col=iris$Species);
