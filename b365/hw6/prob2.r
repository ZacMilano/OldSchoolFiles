# Zac Monroe
# B365 - 11946
# HW 6
# Problem 2
# See my pdf for non-code work/answers

# Part (a)
X = matrix(scan("tree_data.dat"), byrow=T, ncol=3);
N = X[1,2]; # The total number of data points

node_count = length(X[,1]); # 19

# Returns the i-th node of the tree.
# This should make later computation less cryptic.
# i : Integer
node = function(i) {
  return(X[i,]);
}

# Returns the error of the given node.
# This should make later computation less cryptic.
# n : c(Integer, Integer, [0,1])
p_err = function(n) {
  return(n[1]/n[2]);
}

# Indicates whether or not the given node is terminal. Return true if the given
# node is terminal and it exists.
# This should make later computation less cryptic.
# n : c(Integer, Integer, [0,1])
is_terminal = function(n) {
  return(n[3] == 1 ||
         n[2] == 0);
}

# Recursively generates the optimal risk associated with node i
# i : Integer
optimal_risk = function(i) {
  # alpha = 0.04;
  alpha = 0.0;
  ith_node = node(i);

  count_at_node = ith_node[2];
  p_i = count_at_node/N;

  if (is_terminal(ith_node)) {
    return(p_i * p_err(ith_node)); # p(i) * p(error | i)
  }
  else {
    return(min(p_i * p_err(ith_node),
               alpha + optimal_risk(2*i) + optimal_risk(2*i+1)));
  }
}
