myInputFile = open("Y2015_D02_Input.txt" , "r")
myInputLines = myInputFile.readlines()

def processLine(line):
    length = ''
    width = ''
    height = ''
    dimensions = [length , width , height]
    index = 0
    for char in line:
        if char == "x":
            index += 1
        else:
            dimensions[index] += char
            
    length = int(dimensions[0])
    width = int(dimensions[1])
    height = int(dimensions[2])
    volume = length * width * height
    
    if length <= width:
        smallSide1 = length
        if width <= height:
            smallSide2 = width
        else:
            smallSide2 = height
    else:
        smallSide1 = width
        if length <= height:
            smallSide2 = length
        else:
            smallSide2 = height
            
    lengthByWidth = length * width
    widthByHeight = width * height
    heightByLength = height * length
    minSide = min(lengthByWidth , widthByHeight , heightByLength)

    totalPaper = (2 * lengthByWidth) + (2 * widthByHeight) + (2 * heightByLength) + minSide
    totalRibbon = (2 * smallSide1) + (2 * smallSide2) + volume
    return totalPaper , totalRibbon

def processAll(lines):
    totalPaper = 0
    totalRibbon = 0
    for line in lines:
        thisLinePaper , thisLineRibbon = processLine(line)
        totalPaper += thisLinePaper
        totalRibbon += thisLineRibbon
    return totalPaper , totalRibbon

totalPaper , totalRibbon = processAll(myInputLines)
print("The elves need " , totalPaper , "square feet.")
print("The elves need " , totalRibbon, "feet of ribbon.")

myInputFile.close()
