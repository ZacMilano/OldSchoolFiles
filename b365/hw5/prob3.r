# Zac Monroe
# B365 - 11946
# HW 5
# Problem 3
# See my .pdf for non-code work/answers

ketchup_data = read.csv2("Ketchup.csv", stringsAsFactors=FALSE, sep=",");

# Columns 5-8 are the price data of each of the brands of ketchup at the time
# of purchase
dist_matrix = as.matrix(dist(ketchup_data[,5:8]));

# Number of rows/data points
N = length(ketchup_data[,1]);

# Brand of ketchup purchased is 4th column
brand = 4;

# Part (a)
error_hat = 0;

for (i in 1:N)
{
  dists_to_current_point = dist_matrix[i,];

  # Don't want to classify self as nearest neighbor
  dists_to_current_point[i] = Inf;

  nn = ketchup_data[which.min(dists_to_current_point),];

  class = ketchup_data[i,brand];
  class_hat = nn[brand];

  error_hat = error_hat + (class != class_hat);
}

# This is also useful for Part (b)
cat("My error rate for parts (a)/(b) is", error_hat/N, "\n");

# Part (c)

# All unique entries in the brand column
brands = unique(ketchup_data[,brand])
# Set up prior probabilities as
priors = matrix(nrow = length(brands), ncol = 2);

for (i in 1:length(brands))
{
  priors[i,1] = brands[i];
  priors[i,2] = sum(ketchup_data[,brand] == brands[i]) / N;
}

cat("(Part (c)) Here are the prior distributions over the classes/brands",
    "of ketchup\n");
print(priors);

# For turning the brands into a unique plot character
plot_char = function(string)
{
  if (string == "heinz")
    return("h");
  if (string == "hunts")
    return("u");
  if (string == "stb")
    return("s");
  if (string == "delmonte")
    return("d");
}

# Pairs plot; shows lack of noticeable clustering; useful for part (d)
plot1 = pairs(t(apply(ketchup_data[,5:8], 1, as.numeric)),
              pch = as.vector(sapply(ketchup_data[,brand], plot_char)),
              col = factor(ketchup_data[,brand]),
              cex = 2);
cheapest_brands = rep("",N);
for (i in 1:N)
{
  # Cheapest brand is the name of the column with the lowest price
  brand_cols = dimnames(ketchup_data[,5:8])[[2]];
  # Index (1:4) of which column of prices has lowest price
  cheapest_price_indices = which.min(apply(ketchup_data[i,5:8], 1, as.numeric));
  # Cheapest brand is the name of the column with the lowest price
  cheapest_brands[i] = brand_cols[cheapest_price_indices];
  # Remove "price." from the beginning of each string
  cheapest_brands[i] = substr(cheapest_brands[i], 7, 1000);
}

cheapest_brand_chosen = sum(cheapest_brands == ketchup_data[,brand])/N;

cat("Customers purchased the cheapest brand of ketchup",
    cheapest_brand_chosen*100, "\b% of the time\n");
