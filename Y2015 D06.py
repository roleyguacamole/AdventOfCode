myInputFile = open("Y2015_D06_Input.txt" , "r")
myInputLines = myInputFile.readlines()
myInputFile.close()

myHouse = {}
myHouseBrightness = {}
for i in range(1000):
    for j in range(1000):
        myHouse[(i , j)] = 0
        myHouseBrightness[(i , j)] = 0

def processLine(inputLine):
    if inputLine[6] == ' ':
        instruction = 2
    elif inputLine[6] == 'f':
        instruction = 0
    else:
        instruction = 1
    x1 = ''
    x2 = ''
    y1 = ''
    y2 = ''
    ranges = [x1 , y1 , x2 , y2]
    index = 0
    oneH = 0
    for char in inputLine:
        if char == ',':
            index += 1
        elif char == 'h' and oneH == 0:
            index += 1
            oneH += 1
        else:
            try:
                inputNum = int(char)
                newNum = ranges[index] + char
                ranges[index] = newNum
            except:
                pass
    return instruction , int(ranges[0]) , int(ranges[1]) , int(ranges[2]) , int(ranges[3])

def processInput(inputLines):
    lineNum = 0
    for line in inputLines:
        lineNum += 1
        instruction , x1 , y1 , x2 , y2 = processLine(line)
        if instruction == 0:
            for i in range(x1 , x2 + 1):
                for j in range(y1 , y2 + 1):
                    myHouse[(i , j)] = 0
                    if myHouseBrightness[(i , j)] > 0:
                        myHouseBrightness[(i , j)] -= 1
                    else:
                        pass
        elif instruction == 1:
            for i in range(x1 , x2 + 1):
                for j in range(y1 , y2 + 1):
                    myHouse[(i , j)] = 1
                    myHouseBrightness[(i , j)] += 1
        else:
            for i in range(x1 , x2 + 1):
                for j in range(y1 , y2 + 1):
                    myHouse[(i , j)] = 1 - myHouse[(i , j)]
                    myHouseBrightness[(i , j)] += 2
    numLit = 0
    brightness = 0
    for light in myHouse:
        brightness += myHouseBrightness[light]
        if myHouse[light] == 1:
            numLit += 1
    return numLit , brightness

numLit , brightness = processInput(myInputLines)
print("The number of lit lights is" , numLit , ".")
print("The total brightness is" , brightness , ".")

