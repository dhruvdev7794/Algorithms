from random import *

def bubbleSort(arr):
    while True:
        flag = 0
        for i in range(0, len(arr)-1):
            if arr[i]>arr[i+1]:
                temp = arr[i]
                arr[i] = arr[i+1]
                arr[i+1] = temp
                flag = 1
        if flag == 0:
            print(arr)
            return True

testArray = []
for i in range(0,5):
    testArray.append(randint(1,100))
bubbleSort(testArray)