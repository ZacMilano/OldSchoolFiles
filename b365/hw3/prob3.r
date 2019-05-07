# Zac Monroe
# B365 - 11946
# HW 3
# Problem 3
# See hw3_submission.pdf for non-code work/answers

data("UCBAdmissions");
ucb = UCBAdmissions;

# Part (a)
# The data in question for this part
admit_vs_dept = t(apply(ucb, c("Admit", "Dept"), sum));
print(admit_vs_dept);
mosaicplot(admit_vs_dept);

# Part (b)
# The data in question for this part
gender_vs_dept = t(apply(ucb, c("Gender", "Dept"), sum));
print(gender_vs_dept);
mosaicplot(gender_vs_dept);

# Part (c)
# The data in question for this part
dept_f_gender_v_admit = t(ucb[,,"F"]);
print(dept_f_gender_v_admit);
mosaicplot(dept_f_gender_v_admit);

# Part (d)
# The data in question for this part
gender_of_admits = apply(ucb["Admitted",,], "Gender", sum);
print(gender_of_admits);
