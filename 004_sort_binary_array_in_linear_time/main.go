package main

import (
	"fmt"
)

func main() {
	arr := []int{1, 0, 1, 0, 1, 0, 0, 1}
	fmt.Print("Array before sorting ")
	fmt.Println(arr)

	var countZero int
	for i := 0; i < len(arr); i++ {
		if arr[i] == 0 {
			countZero++
		}
	}
	arr2 := make([]int, len(arr))

	for k := 0; k < len(arr); k++ {
		if k < countZero {
			arr2[k] = 0
		} else {
			arr2[k] = 1
		}

	}
	fmt.Print("Array after sorting ")
	fmt.Println(arr2)
}
