#include "Graph.hpp"
#include <iostream>

using namespace std;
using namespace gp;

int main(int argc,char** argv){
    cout << "Hello " << argv[0] << endl;

    Graph g;
    g.simplePrint();

}

Graph::Graph(){
    cout << "Doing nothing as of now in default constructor" << endl;
}
Graph::Graph(int V){
    cout << "Doing nothing as of now in Graph(int V)" << endl;
}

void Graph::simplePrint(){
    cout << "Test method" << endl;
}