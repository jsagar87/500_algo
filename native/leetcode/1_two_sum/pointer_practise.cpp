
#include <iostream>

using namespace std;

void Unknown(int *p, int num);
void HardToFollow(int *p, int q, int *num);

void Unknown(int *p, int num) {
  int *q;

  q = &num;
  *p = *q + 2;
  num = 7;
}

void HardToFollow(int *p, int q, int *num) {//(&(2),1,&(3))
  *p = q + *num; // trouble[1] = 1 + trouble[2] = 1+3 = 4
  *num = q;      // *num =  1 -> trouble[2] = 1
  num = p; // 
  p = &q;
  Unknown(num, *p);
}

int main() {
  int *q;
  int trouble[3];

  trouble[0] = 1;
  q = &trouble[1]; // Address of trouble[1]
  *q = 2; // Equivalent of : trouble[1] = 2;
  trouble[2] = 3;
 
  HardToFollow(q, trouble[0], &trouble[2]); // Equivalent of : HardToFollow(&trouble[1], trouble[0], &trouble[2]) ;
  // => HardToFollow(&(2),1,&(3)) ;
//   Unknown(&trouble[0], *q); //  Equivalent of : Unknown()

  cout << *q << " " << trouble[0] << " " << trouble[2]; // => 3  3  1
  return 0;
}

// trouble[0] = 3
// trouble[1] = 3 
// trouble[2] = 1
// *q = trouble[1]

// *p = trouble[0]
// num = 7

//*q' =&num
// trouble[0] = 1 +2 =3