// Compile using cl /EHsc /W4 funcpoint.cpp

// using namespace std;
#include <iostream>
#include <string>

#include "InstructionSet.h"

// Initialize static member data
// const IS.InstructionSet_Internal IS.CPU_Rep;

using namespace N;

int main()
{
    auto& outstream = std::cout;
    auto support_message_func = [&outstream](std::string is_a_feature,bool is_supported){
        outstream << is_a_feature << (is_supported ? " supported":" not supported") << std::endl;
    };
    support_message_func("Hi ",true);

    InstructionSet IS;
    std::cout << IS.GetGreeting() <<std::endl;
    std::cout << IS.Explore() <<std::endl;
    std::cout << IS.SetTheBit() <<std::endl;
    std::cout << IS.OurCpuId() <<std::endl;
    std::cout << IS.Vendor() <<std::endl;
    std::cout << IS.AVX() <<std::endl;

    support_message_func("3DNOW",       IS._3DNOW());
    support_message_func("3DNOWEXT",    IS._3DNOWEXT());
    support_message_func("ABM",         IS.ABM());
    support_message_func("ADX",         IS.ADX());
    support_message_func("AES",         IS.AES());
    support_message_func("AVX",         IS.AVX());
    support_message_func("AVX2",        IS.AVX2());
    support_message_func("AVX512CD",    IS.AVX512CD());
    support_message_func("AVX512ER",    IS.AVX512ER());
    support_message_func("AVX512F",     IS.AVX512F());
    support_message_func("AVX512PF",    IS.AVX512PF());
    support_message_func("BMI1",        IS.BMI1());
    support_message_func("BMI2",        IS.BMI2());
    support_message_func("CLFSH",       IS.CLFSH());
    support_message_func("CMPXCHG16B",  IS.CMPXCHG16B());
    support_message_func("CX8",         IS.CX8());
    support_message_func("ERMS",        IS.ERMS());
    support_message_func("F16C",        IS.F16C());
    support_message_func("FMA",         IS.FMA());
    support_message_func("FSGSBASE",    IS.FSGSBASE());
    support_message_func("FXSR",        IS.FXSR());
    support_message_func("HLE",         IS.HLE());
    support_message_func("INVPCID",     IS.INVPCID());
    support_message_func("LAHF",        IS.LAHF());
    support_message_func("LZCNT",       IS.LZCNT());
    support_message_func("MMX",         IS.MMX());
    support_message_func("MMXEXT",      IS.MMXEXT());
    support_message_func("MONITOR",     IS.MONITOR());
    support_message_func("MOVBE",       IS.MOVBE());
    support_message_func("MSR",         IS.MSR());
    support_message_func("OSXSAVE",     IS.OSXSAVE());
    support_message_func("PCLMULQDQ",   IS.PCLMULQDQ());
    support_message_func("POPCNT",      IS.POPCNT());
    support_message_func("PREFETCHWT1", IS.PREFETCHWT1());
    support_message_func("RDRAND",      IS.RDRAND());
    support_message_func("RDSEED",      IS.RDSEED());
    support_message_func("RDTSCP",      IS.RDTSCP());
    support_message_func("RTM",         IS.RTM());
    support_message_func("SEP",         IS.SEP());
    support_message_func("SHA",         IS.SHA());
    support_message_func("SSE",         IS.SSE());
    support_message_func("SSE2",        IS.SSE2());
    support_message_func("SSE3",        IS.SSE3());
    support_message_func("SSE4.1",      IS.SSE41());
    support_message_func("SSE4.2",      IS.SSE42());
    support_message_func("SSE4a",       IS.SSE4a());
    support_message_func("SSSE3",       IS.SSSE3());
    support_message_func("SYSCALL",     IS.SYSCALL());
    support_message_func("TBM",         IS.TBM());
    support_message_func("XOP",         IS.XOP());
    support_message_func("XSAVE",       IS.XSAVE());

    return 0;
}