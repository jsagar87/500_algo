#ifndef INSTRUCTIONSET_H
#define INSTRUCTIONSET_H

#include <iostream>
#include <vector>
#include <array>
#include <string.h>
#include <bitset>

#include <intrin.h>

namespace N
{
    class InstructionSet{
        private:
            int nIds_;
            int nExIds_;
            std::string vendor_;
            std::string brand_;
            bool isIntel_;
            bool isAMD_;
            std::bitset<32> f_1_ECX_;
            std::bitset<32> f_1_EDX_;
            std::bitset<32> f_7_EBX_;
            std::bitset<32> f_7_ECX_;
            std::bitset<32> f_81_ECX_;
            std::bitset<32> f_81_EDX_;
            std::vector<std::array<int, 4>> data_;
            std::vector<std::array<int, 4>> extdata_;
        public:
            InstructionSet();
            // forward declarations
            char const* GetGreeting();
            char const* Explore();
            char const* SetTheBit();
            int OurCpuId();

            // Original code to check instruction set
            std::string Vendor(void);
            std::string Brand(void);
            bool InstructionSet::SSE3(void);
            bool InstructionSet::PCLMULQDQ(void);
            bool InstructionSet::MONITOR(void);
            bool InstructionSet::SSSE3(void);
            bool InstructionSet::FMA(void);
            bool InstructionSet::CMPXCHG16B(void);
            bool InstructionSet::SSE41(void);
            bool InstructionSet::SSE42(void);
            bool InstructionSet::MOVBE(void) ;
            bool InstructionSet::POPCNT(void);
            bool InstructionSet::AES(void);
            bool InstructionSet::XSAVE(void);
            bool InstructionSet::OSXSAVE(void);
            bool InstructionSet::AVX(void);
            bool InstructionSet::F16C(void);
            bool InstructionSet::RDRAND(void);

            bool InstructionSet::MSR(void);
            bool InstructionSet::CX8(void);
            bool InstructionSet::SEP(void);
            bool InstructionSet::CMOV(void);
            bool InstructionSet::CLFSH(void);
            bool InstructionSet::MMX(void);
            bool InstructionSet::FXSR(void);
            bool InstructionSet::SSE(void);
            bool InstructionSet::SSE2(void);

            bool InstructionSet::FSGSBASE(void);
            bool InstructionSet::BMI1(void);
            bool InstructionSet::HLE(void);
            bool InstructionSet::AVX2(void);
            bool InstructionSet::BMI2(void);
            bool InstructionSet::ERMS(void);
            bool InstructionSet::INVPCID(void);
            bool InstructionSet::RTM(void);
            bool InstructionSet::AVX512F(void);
            bool InstructionSet::RDSEED(void);
            bool InstructionSet::ADX(void);
            bool InstructionSet::AVX512PF(void);
            bool InstructionSet::AVX512ER(void);
            bool InstructionSet::AVX512CD(void);
            bool InstructionSet::SHA(void);

            bool InstructionSet::PREFETCHWT1(void);

            bool InstructionSet::LAHF(void);
            bool InstructionSet::LZCNT(void);
            bool InstructionSet::ABM(void);
            bool InstructionSet::SSE4a(void);
            bool InstructionSet::XOP(void);
            bool InstructionSet::TBM(void);

            bool InstructionSet::SYSCALL(void);
            bool InstructionSet::MMXEXT(void);
            bool InstructionSet::RDTSCP(void);
            bool InstructionSet::_3DNOWEXT(void);
            bool InstructionSet::_3DNOW(void);
    };

}
#endif