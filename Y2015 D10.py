myInput = [1 , 3 , 2 , 1 , 1 , 3 , 1 , 1 , 1 , 2]

def nextIteration(inputList):
    index = 0
    nextList = []
    while  index < len(inputList):
        count = 0
        currentNum = inputList[index]
        while index < len(inputList) and inputList[index] == currentNum:
            count += 1
            index += 1
        nextList.append(count)
        nextList.append(currentNum)
    return nextList

def doNIterations(n):
    nextList = nextIteration(myInput)
    for i in range(n - 1):
        nextList = nextIteration(nextList)
    return len(nextList)
