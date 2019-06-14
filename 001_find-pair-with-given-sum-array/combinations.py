a = [1, -3, -1, 2, 4]

s = [0]
k = 1
count = 0

for i in range(len(a)):
  m = len(s)
  for j in range(m):
    s.append(a[i]+s[j])
    if s[k] == 0:
      count += 1
    k += 1 
possible no. of subsets for a given sum