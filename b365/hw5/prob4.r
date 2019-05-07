# Zac Monroe
# B365 - 11946
# HW 5
# Problem 4
# See my .pdf for non-code work/answers

data('iris');
N = nrow(iris);
type = rep(0,N);

class = 5;
type[iris[,class] == "setosa"] = "s";
type[iris[,class] == "versicolor"] = "c";
type[iris[,class] == "virginica"] = "v";

# type is now a vector of "s" or "c" or "v" for 3 types

# Here is a pairs plot of all variables
pairs(iris[,1:4],pch=type,col=factor(type),cex=2);

# # This is what I used to make the sketch for part (c). Remove one level of
# # uncommenting from here on to reproduce it.
# # Here is a plot of Petal.Length vs Petal Width, the two variables on which I
# # chose to split the data
# plot(iris[,3:4],pch=type,col=factor(type),cex=2);
# # Draw a segment for separating based on Petal.Length = 2.5
# segments(2.5, 0,
#          2.5, 3);
# # Draw a segment for separating based on Petal.Width = 1.65
# segments(2.5, 1.65,
#          8.0, 1.65);
# # Label for Setosa class
# text(1.5, 1, labels=c("Setosa"), col="red", cex = 2);
# # Label for Versicolor class
# text(4.5, 0.5, labels=c("Versicolor"), col="black", cex = 2);
# # Label for Virginica class
# text(3.5, 2.25, labels=c("Virginica"), col="green", cex = 2);
