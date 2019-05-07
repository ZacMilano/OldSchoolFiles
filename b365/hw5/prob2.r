# Zac Monroe
# B365 - 11946
# HW 5
# Problem 2
# See my .pdf for non-code work/answers

# Part (a)
N = 300;

instances = matrix(nrow = N, ncol = 4);

for (i in 1:N)
{
  x = runif(1);
  y = runif(1);
  z = runif(1);
  c = sum(z^2 < x^2 + y^2);

  instances[i, 1] = x;
  instances[i, 2] = y;
  instances[i, 3] = z;
  instances[i, 4] = c;
}

# Part (b)

error_hat = 0.0;
best_dist = Inf;
nearest_neighbor = NULL;

dist_matrix = as.matrix(dist(instances[,1:3]));

# Build N classifiers, each "training" off of N-1 data instances
# Leave out i-th element in instances
for (i in 1:N)
{
  dists_to_current_point = dist_matrix[i,];

  # Don't want to classify self as nearest neighbor
  dists_to_current_point[i] = Inf;

  nearest_neighbor = instances[which.min(dists_to_current_point),];

  error_hat = error_hat + (instances[i,4] != nearest_neighbor[4]);
}

# Estimation of "generalization error"
cat("My error rate for part (b) is", error_hat/N, "\n");

# Part (c)

M = 10000;
test_points = matrix(nrow = M, ncol = 4);
for (i in 1:M)
{
  x = runif(1);
  y = runif(1);
  z = runif(1);
  c = sum(z^2 < x^2 + y^2);

  test_points[i,1] = x;
  test_points[i,2] = y;
  test_points[i,3] = z;
  test_points[i,4] = c;
}

error_hat = 0.0;

for (i in 1:M)
{
  new_dist_matrix = as.matrix(dist(rbind(instances[,1:3],
                                         test_points[i,1:3])));

  dists_to_current_point = new_dist_matrix[N+1,1:N];

  nearest_neighbor = instances[which.min(dists_to_current_point),];
  class_hat = nearest_neighbor[4];
  class = test_points[i,4];

  error_hat = error_hat + (class != class_hat);
}

cat("My error rate for part (c) is", error_hat/M, "\n");
