#ifndef GRAPH_H
#define GRAPH_H

#include <fstream>

using namespace std;

namespace gp
{
    class Graph
    {
        public:
            Graph();
            Graph(int V);
            // Graph(In)
            void simplePrint(void);
            void addEdge(int V, int w);
            
    };
}
#endif