// get_input.cpp : Bhishma Jariwala
// Description : Illusstrate the use of cin to get input.

#include <iostream>

using namespace std;

int main() {
    int input_var = 0;
    // Enter the do while loop and stay there until either
    // a non-numeric is entered, or -1 is entered. Note that
    // cin will accept any integer, 4, 40, 400, etc.
    do {
        cout << "Enter a number (-1 = quit ) : " << endl ;
        // Following line accepts input from the standard input (keyBoard)
        // to input variable.
        // cin returns false if an input operation fails, that is, if
        // something other than an int ( the type of input_var) is entered.
        if (!(cin >> input_var)) {
            cout << "You entered a non-numeric. Exiting..." << endl ;
            break;
            // Exit the do while loop
        }
        if (input_var!=-1) {
            cout << " You entered " << input_var << endl ;
            break;
        }
    } while (input_var!=-1);
    cout << "All done." << endl ;
    return 0;
}