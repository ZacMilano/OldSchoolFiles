# Zac Monroe
# B365 - 11946
# HW 4
# Problem 2
# See my .pdf for non-code work/answers

x = read.csv2("chilean_voting.csv", stringsAsFactors=FALSE, sep=",");
region = 2;     # 2nd column
gender = 4;     # 4th column
age = 5;        # 5th column
education = 6;  # 6th column
vote = 9;       # 9th column

yes_votes = (x[,vote] == "Y" & !(is.na(x[,vote])));
no_votes = (x[,vote] == "N" & !(is.na(x[,vote])));

# Filter out undecided, abstain, NA
x = x[yes_votes | no_votes,];
# Turn ages to decades
x[,5] = floor(x[,5]/10);

# Part (a)
t = table(x[,c(age, education, vote)]);
print(t);

# Part (b)
# bayes_age_ed : [1-7], {P, PS, S} -> {Y, N}
# Given age decade and education level, output how the data would think that
# individual to vote
# I chose to represent the classifier as a function based around a table
bayes_age_ed = function(age_, education_)
{
  return(which.max(t[age_, education_,]));
}

# Part (c)
# bayes : [1-7], {P, PS, S}, {F. M}, {N, C, S, SA, M} -> {Y, N}
# Given age decade, education level, gender, and region, output how the data
# would think that individual to vote
bayes = function(age_, education_, gender_, region_)
{
  new_table = table(x[,c(age, education, gender, region, vote)])
  print(new_table);
  return(which.max(new_table[age_, education_, gender_, region_,]));
}
cat("The more general Bayes' classifier would classify the vote of a",
    "female, post-secondary-educated person from the SA region in their 50s",
    "as this type of voter:\n");
print(bayes(5,"PS","F","SA"));

# Part (e)
# Prior distribution on vote
vote_dist_prior = t(apply(t, "vote", sum));
print("Here's the prior vote distribution.");
print(vote_dist_prior);

# Part (f)
# I'm unsure of exactly how to grab individual values from tables, so I'm
# printing out each marginalized table and manually computing each
# class-conditional distribution.
new_t = table(x[,c(age, education, gender, region, vote)])

table_for_gender = t(apply(new_t, c("vote", "sex"), sum));
print("Use this table to calculate P(F|Y), ... , P(M|N).");
print(table_for_gender);
p_gender_given_vote = function(gender_, vote_)
{
  if (gender_ == "F")
  {
    if (vote_ == "Y")
    {
      return(476/(476+387));
    } else
    {
      return(361/(361+526));
    }
  } else
  {
    if (vote_ == "Y")
    {
      return(387/(476+387));
    } else
    {
      return(526/(361+526));
    }
  }
}

table_for_education = t(apply(new_t, c("vote", "education"), sum));
print("Use this table to calculate P(P|Y), ... , P(S|N).");
print(table_for_education);
p_education_given_vote = function(education_, vote_)
{
  if (vote_ == "Y")
  {
    return(switch(education_,
                  "P" = 422/(422+130+311),
                  "PS" = 130/(422+130+311),
                  "S" = 311/(422+130+311)));
  } else
  {
    return(switch(education_,
                  "P" = 266/(266+224+397),
                  "PS" = 224/(266+224+397),
                  "S" = 397/(266+224+397)));
  }
}

table_for_region = t(apply(new_t, c("vote", "region"), sum));
print("Use this table to calculate P(N|Y), ... , P(M|N).");
print(table_for_region);
p_region_given_vote = function(region_, vote_)
{
  if (vote_ == "Y")
  {
    return(switch(region_,
                  "C" = 173/(173+38+134+275+243),
                  "M" = 38/(173+38+134+275+243),
                  "N" = 134/(173+38+134+275+243),
                  "S" = 275/(173+38+134+275+243),
                  "SA" = 243/(173+38+134+275+243)));
  } else
  {
    return(switch(region_,
                  "C" = 210/(210+18+102+213+344),
                  "M" = 18/(210+18+102+213+344),
                  "N" = 102/(210+18+102+213+344),
                  "S" = 213/(210+18+102+213+344),
                  "SA" = 344/(210+18+102+213+344)));
  }
}

table_for_age = t(apply(new_t, c("vote", "age"), sum));
print("Use this table to calculate P(1|Y), ... , P(7|N).");
print(table_for_age);
p_age_given_vote = function(age_, vote_)
{
  min_age = 1;
  if (vote_ == "Y")
  {
    return(switch(age_,
                  "1" = 47/(47+220+188+156+122+109+21),
                  "2" = 220/(47+220+188+156+122+109+21),
                  "3" = 188/(47+220+188+156+122+109+21),
                  "4" = 156/(47+220+188+156+122+109+21),
                  "5" = 122/(47+220+188+156+122+109+21),
                  "6" = 109/(47+220+188+156+122+109+21),
                  "7" = 21/(47+220+188+156+122+109+21)));
  } else
  {
    return(switch(age_,
                  "1" = 68/(68+313+199+134+96+66+11),
                  "2" = 313/(68+313+199+134+96+66+11),
                  "3" = 199/(68+313+199+134+96+66+11),
                  "4" = 134/(68+313+199+134+96+66+11),
                  "5" = 96/(68+313+199+134+96+66+11),
                  "6" = 66/(68+313+199+134+96+66+11),
                  "7" = 11/(68+313+199+134+96+66+11)));
  }
}

# Part (g)
cat(
  "\nHere are the numbers for how the naive Bayes' classifier would classify",
  "a female, post-secondary-educated person from the SA region in their 50s",
  "\b:\n\n");

p_no = p_gender_given_vote("F", "N") * p_education_given_vote("PS", "N") *
  p_region_given_vote("SA", "N") * p_age_given_vote(5, "N");
p_yes = p_gender_given_vote("F", "Y") * p_education_given_vote("PS", "Y") *
  p_region_given_vote("SA", "Y") * p_age_given_vote(5, "Y");
normalization = p_no + p_yes;

p_no = p_no/normalization;
p_yes = p_yes/normalization;

cat("P(N | F,PS,SA,5) =", p_no, "\b; P(Y | F,PS,SA,5) =", p_yes, "\n");
