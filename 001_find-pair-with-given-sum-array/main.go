package main

import (
	"fmt"
)

func main(){

	fmt.Println("Let's begin")

	arr := [6]int{8,7,2,5,3,1}
	var sum int = 10

	fmt.Println(arr[0])
	fmt.Println(sum)

	// Approach 1, sort the array 
	// complexity of mergesort + ( complexity of binary search ) * n/2
	sorted := merge(arr[:])
	fmt.Println(arr)
	fmt.Println(sorted)
	fmt.Println("============")
	fmt.Println(binarySearch(8,sorted))

	fmt.Println("============")
	//fmt.Println(binarySearch(1,sorted))

	fmt.Println("============")
	//fmt.Println(binarySearch(3,sorted))

	// Iterate and search
//	for i := 0 ; i<len(sorted)/2 ; i++ {
//		head := sorted[i]
//		if head < sum {
//			tailRequired := sum - head
//			var hit bool =  binarySearch(tailRequired, sorted)
//			if hit {
//				fmt.Printf("Pair %d - %d \n " , head , tailRequired )
//			}
//		}
//	}

}

func binarySearch(token int, sorted []int) (res bool) {
	mid := len(sorted) / 2
	fmt.Printf("Len %d\n",len(sorted))
	fmt.Printf("Mid %d\n",mid)
	fmt.Printf("Token %d\n",token)
//	var res bool = false
		if len(sorted) == 1 {
			fmt.Printf("last elem %d" , sorted[mid])
			fmt.Println("Not found")
			res = false
			return false // not found
		} else if sorted[mid] > token { // Search top/left half
			fmt.Println("Search left")
			binarySearch(token, sorted[mid+1:])
		} else if sorted[mid] < token {
			fmt.Println("Search right")
			binarySearch(token, sorted[:mid])
		} else { // a[mid] == search
			res = true
		        return true // found
		}
	return
}

func merge(ar []int) []int {
	if len(ar) < 2 {
		return ar
	}
	mid := len(ar)/2
	return mergeSort(merge(ar[mid:]),merge(ar[:mid]))
}

func mergeSort(left []int, right []int) []int {
	size, i ,j := len(left)+len(right), 0 , 0
	aux := make([]int,size,size)
	for k := 0; k < size; k++ {
		if i > len(left)-1 && j <= len(right)-1 {
			aux[k] = right[j]
			j++
		} else if j > len(right)-1 && i <= len(left)-1 {
			aux[k] = left[i]
			i++
		} else if left[i] < right[j] {
			aux[k] = left[i]
			i++
		} else {
			aux[k] = right[j]
			j++
		}
	}
	return aux
}
