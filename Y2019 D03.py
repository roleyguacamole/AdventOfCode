input1 = open("Y2019_D03_Input1.txt")
input2 = open("Y2019_D03_Input2.txt")

firstWire = input1.read()
secondWire = input2.read()

input1.close()
input2.close()

def parseText(wire):
    wireSet = set()
    lastPos = (0 , 0)
    xDir = 0
    yDir = 0
    dirStr = ''
    for char in wire:
        if char == 'U':
            yDir = 1
            xDir = 0
        elif char == 'D':
            yDir = -1
            xDir = 0
        elif char == 'L':
            xDir = -1
            yDir = 0
        elif char == 'R':
            xDir = 1
            yDir = 0
        elif char == ',':
            dirNum = int(dirStr)
            for i in range(dirNum):
                newPos = (lastPos[0] + xDir , lastPos[1] + yDir)
                wireSet.add(newPos)
                lastPos = newPos 
            dirStr = ''
        else:
            dirStr += char
    return wireSet

wire1 = parseText(firstWire)
wire2 = parseText(secondWire)

def manhattan(point):
    return point[0] + point[1]

def answer(wire1 , wire2):
    distance = float('Inf')
    for point in wire1:
        thisDistance = manhattan(point)
        if thisDistance < distance:
            if point in wire2:
                distance = thisDistance
    return distance

theAnswer = answer(wire1 , wire2)
print("The shortest distance is:" , theAnswer)
        
