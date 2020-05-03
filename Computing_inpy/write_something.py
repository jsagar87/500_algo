inputs = list()

while (current := input("Write something: ")) != "quit" :
    inputs.append(current)

for i in inputs:
    print(i)
