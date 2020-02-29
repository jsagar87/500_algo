// solution.cpp : Bhishma Jariwala
// Description : a program that finds two number for given sum

#include <iostream>
#include <vector>
#include <array>

using namespace std;

class Solution {
public:
    std::vector<int> twoSum(std::vector<int>& nums, int target)
    {
        vector<int> two;
        two.push_back(1);
        two.push_back(2);

        
        return two;
    }
};

void swap(int* arr, int a, int b)
{
    int temp = arr[b];
    arr[b] = arr[a];
    arr[a] = temp ;
}

void merge(int* arr,int * aux, int low, int mid, int hi) 
{ 
    // Copy
    for (int k = low; k <= hi ; k++){
        aux[k] = arr[k] ;
    }

    int i = low;     // Pointing to first pointer of left half
    int j = mid + 1; // Pointing to first pointer of right half

    for (int k = low; k <= hi; k++) {
        if (i>mid) {arr[k] = aux[j++];} // corner case where i reaches mid
        else if (j>hi){  arr[k] = aux[i++]  ;} // corner case where j reaches hi
        else if (j<i)  {  arr[k] = aux[j] ;}
        else{ arr[k] = aux[i];}
    }
} 

void printArray(int* arr, int size) {
    std::cout << "Arr -> " << std::endl ;
    for ( int i = 0; i < size; i++ ) {
        std::cout << "\t" << arr[i] << std::endl ;
    }
}

template <class T,std::size_t N>
ostream& operator<<(ostream& o,const array<T,N>& arr)
{
    copy(arr.cbegin(), arr.cend(), ostream_iterator<T>(o, " "));
    return o;
}

template <std::size_t N>
void printStdArray(std::array<int,N> arr) {
    std::cout << "passing parameter with template function -> " << std::endl ;
    for ( auto& i = std::begin(arr); i < std::end(arr); i++ ) 
    {
        std::cout << "\t" << *i << std::endl ;
    }
}

void mergeSort(int* arr, int low, int hi)
{
    // Boundary condition
    if ( low < hi ) {
        int mid = low + (hi -low) / 2;
        mergeSort(arr, low, mid  - 1);
        mergeSort(arr, mid, hi - 1);
        merge(arr, low, mid, hi);
    }
}

void sort(int* arr, int sizeArr)
{
    std::cout << "Sort method : Size arr " << sizeArr << std::endl ;
    printArray(arr, sizeArr);
    int mid = sizeArr / 2 ;
    
    // mergeSort(arr, 0, sizeArr - 1);
}

void testMergeSort()
{
    // --------------------------------------------------------------------
    // Input data building
    int arr[] = { 3, 1, 4, 5, 2 };
    int sizeArr = sizeof(arr) / sizeof(int);
    std::cout << "Size of Test Harness arr " << sizeArr << std::endl ;

    // --------------------------------------------------------------------
    // Sorting
    sort(arr, sizeArr);

    // --------------------------------------------------------------------
    // Testing
    std::array<int, 5>& originalArr = reinterpret_cast<std::array<int, 5>&>(arr);
    // std::array<int, 5> originalArr = {1,2,3,4,5};
    std::cout << "Lets print original array -> \n"<< originalArr << std::endl;
    printStdArray(originalArr);

    std::array<int, 5> testArr = { 1, 2, 3, 4, 5 } ;
    std::cout << "Lets print test array -> \n"<< testArr << std::endl;

    if (originalArr == testArr) {
        std::cout << "Test passes " << std::endl ;
    } else {
        std::cout << "Test fails "  << std::endl ;
    }

    // --------------------------------------------------------------------
}

int main(int argc, char const *argv[])
{
    testMergeSort();

    /* code */
    // int input[] = { 2, 7, 11, 15 } ; 
    // // int total_memory = sizeof(input);
    // // int n = sizeof(input) / sizeof(input[0]) ;

    // int total_memory   = sizeof(input);
    // int integer_memory = sizeof(int);
    // int array_length   = total_memory / integer_memory;

    // std::cout << "Sizeof input " << array_length << std::endl;
    // std::vector<int> nums(input, input + array_length);
    // std::cout << "Print vector" << std::endl;

    // Solution soln;
    // std::vector<int> result = soln.twoSum(nums, 9);
    // for (auto s = result.begin(); s != result.end(); s++) {
    //     std::cout << *s << std::endl ;
    // }

    return 0;
}
