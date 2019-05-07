# Demonstration of Simpson's paradox on the famous UCB graduate admissions data.

# first understand marginial tables and conditional tables

data("UCBAdmissions"); # import the data
ucb = UCBAdmissions;   # abbreviate "UCBAdmissions"
print(dimnames(ucb));  # 6 departments acceptance results by gender
print(ucb["Admitted",,]) # 2-way table of admitted students (gender and department)
print(t(ucb["Admitted",,])) # "transpose" of table (flip rows and cols)
print(ucb["Admitted","Male",]) # 1-way table of admitted male students (department)
print(ucb["Rejected",,"A"])   # 1-way table of rejected students from department A (gender)
print(apply(ucb,"Admit",sum));  # 1-way table of Admit status (summming out others)
print(apply(ucb,c("Admit","Dept"),sum));  # 2-way table of Admit status and Dept (summming out gender)
print(apply(ucb["Admitted",,],"Dept",sum));  # 1-way table Dept, restricted to Admitted students (summming out gender)
stopp;
print(apply(ucb,c("Gender","Admit"),sum));  # 2-way table of Gender x Admit
mosaicplot(apply(ucb,c("Gender","Admit"),sum));  # mosaic plot clearer ...


# table shows clear gender bias against Female students
























# ... or does it ...?  



mosaicplot(t(ucb[,,"A"]));  # department A seems to *favor* Females
mosaicplot(t(ucb[,,"B"]));  # B seems neutral  (admission indep of gender)m
mosaicplot(t(ucb[,,"C"]));  # so are others ...
mosaicplot(t(ucb[,,"D"]));  
mosaicplot(t(ucb[,,"E"]));  
mosaicplot(t(ucb[,,"F"]));  

# what is going on !?!?

mosaicplot(apply(ucb,c("Dept","Admit"),sum));  # depts accept rates differ

# to facilitate understanding departments are ordered A ... F from easier to harder

mosaicplot(apply(ucb,c("Dept","Gender"),sum));  # men apply more to easier depts A and B

# moral:  summing out (marginalizing) over variables can give misleading results




