// InstructionSet.cpp
// Compile by using: cl /EHsc /W4 InstructionSet.cpp
// processor: x86, x64
// Uses the __cpuid intrinsic to get information about
// CPU extended instruction set support.

#include "InstructionSet.h"
#define M 32

using namespace N;
using namespace std;

char const* InstructionSet::GetGreeting()
{
    return "Hello, C++ Programmers!";
}

char const* InstructionSet::Explore()
{
    return "We will start to explore";
}

char const* InstructionSet::SetTheBit()
{
    // default constructor initializes with all bits 0 
    bitset<M> bset1;
    if (!bset1.any()) 
        return "bset1 has no bit set.\n"; 
  
    // none function returns true, if none of the bit 
    // is set 
    if (!bset1.none()) 
        return "bset1 has some bit set\n"; 
  
    return "null";
}

int InstructionSet::OurCpuId()
{
    //int cpuInfo[4] = {-1};
    std::array<int, 4> cpui;

    // Calling __cpuid with 0x0 as the function_id argument
    // gets the number of the highest valid function ID.
    __cpuid(cpui.data(), 0);
    int nIds_ = cpui[0];

    for (int i = 0; i <= nIds_; ++i)
    {
        __cpuidex(cpui.data(), i, 0);
    }
    return nIds_;
}

InstructionSet::InstructionSet()
        : nIds_{ 0 },
        nExIds_{ 0 },
        isIntel_{ false },
        isAMD_{ false },
        f_1_ECX_{ 0 },
        f_1_EDX_{ 0 },
        f_7_EBX_{ 0 },
        f_7_ECX_{ 0 },
        f_81_ECX_{ 0 },
        f_81_EDX_{ 0 },
        data_{},
        extdata_{}
{
    //int cpuInfo[4] = {-1};
    std::array<int, 4> cpui;

    // Calling __cpuid with 0x0 as the function_id argument
    // gets the number of the highest valid function ID.
    __cpuid(cpui.data(), 0);
    nIds_ = cpui[0];

    for (int i = 0; i <= nIds_; ++i)
    {
        __cpuidex(cpui.data(), i, 0);
        data_.push_back(cpui);
    }
    // Capture vendor string
    char vendor[0x20];
    memset(vendor, 0, sizeof(vendor));
    *reinterpret_cast<int*>(vendor) = data_[0][1];
    *reinterpret_cast<int*>(vendor + 4) = data_[0][3];
    *reinterpret_cast<int*>(vendor + 8) = data_[0][2];
    vendor_ = vendor;
    if (vendor_ == "GenuineIntel")
    {
        isIntel_ = true;
    }
    else if (vendor_ == "AuthenticAMD")
    {
        isAMD_ = true;
    }
    // load bitset with flags for function 0x00000001
    if (nIds_ >= 1)
    {
        f_1_ECX_ = data_[1][2];
        f_1_EDX_ = data_[1][3];
    }

    // load bitset with flags for function 0x00000007
    if (nIds_ >= 7)
    {
        f_7_EBX_ = data_[7][1];
        f_7_ECX_ = data_[7][2];
    }

    // Calling __cpuid with 0x80000000 as the function_id argument
    // gets the number of the highest valid extended ID.
    __cpuid(cpui.data(), 0x80000000);
    nExIds_ = cpui[0];

    char brand[0x40];
    memset(brand, 0, sizeof(brand));

    for (int i = 0x80000000; i <= nExIds_; ++i)
    {
        __cpuidex(cpui.data(), i, 0);
        extdata_.push_back(cpui);
    }

    // load bitset with flags for function 0x80000001
    if (nExIds_ >= 0x80000001)
    {
        f_81_ECX_ = extdata_[1][2];
        f_81_EDX_ = extdata_[1][3];
    }

    // Interpret CPU brand string if reported
    if (nExIds_ >= 0x80000004)
    {
        memcpy(brand, extdata_[2].data(), sizeof(cpui));
        memcpy(brand + 16, extdata_[3].data(), sizeof(cpui));
        memcpy(brand + 32, extdata_[4].data(), sizeof(cpui));
        brand_ = brand;
    }
};

std::string InstructionSet::Vendor(void) { 
    return vendor_;
}
std::string InstructionSet::Brand(void) { 
    return brand_; 
}

bool InstructionSet::SSE3(void) { return f_1_ECX_[0]; }
bool InstructionSet::PCLMULQDQ(void) { return f_1_ECX_[1]; }
bool InstructionSet::MONITOR(void) { return f_1_ECX_[3]; }
bool InstructionSet::SSSE3(void) { return f_1_ECX_[9]; }
bool InstructionSet::FMA(void) { return f_1_ECX_[12]; }
bool InstructionSet::CMPXCHG16B(void) { return f_1_ECX_[13]; }
bool InstructionSet::SSE41(void) { return f_1_ECX_[19]; }
bool InstructionSet::SSE42(void) { return f_1_ECX_[20]; }
bool InstructionSet::MOVBE(void) { return f_1_ECX_[22]; }
bool InstructionSet::POPCNT(void) { return f_1_ECX_[23]; }
bool InstructionSet::AES(void) { return f_1_ECX_[25]; }
bool InstructionSet::XSAVE(void) { return f_1_ECX_[26]; }
bool InstructionSet::OSXSAVE(void) { return f_1_ECX_[27]; }
bool InstructionSet::AVX(void) { return f_1_ECX_[28]; }
bool InstructionSet::F16C(void) { return f_1_ECX_[29]; }
bool InstructionSet::RDRAND(void) { return f_1_ECX_[30]; }

bool InstructionSet::MSR(void) { return f_1_EDX_[5]; }
bool InstructionSet::CX8(void) { return f_1_EDX_[8]; }
bool InstructionSet::SEP(void) { return f_1_EDX_[11]; }
bool InstructionSet::CMOV(void) { return f_1_EDX_[15]; }
bool InstructionSet::CLFSH(void) { return f_1_EDX_[19]; }
bool InstructionSet::MMX(void) { return f_1_EDX_[23]; }
bool InstructionSet::FXSR(void) { return f_1_EDX_[24]; }
bool InstructionSet::SSE(void) { return f_1_EDX_[25]; }
bool InstructionSet::SSE2(void) { return f_1_EDX_[26]; }

bool InstructionSet::FSGSBASE(void) { return f_7_EBX_[0]; }
bool InstructionSet::BMI1(void) { return f_7_EBX_[3]; }
bool InstructionSet::HLE(void) { return isIntel_ && f_7_EBX_[4]; }
bool InstructionSet::AVX2(void) { return f_7_EBX_[5]; }
bool InstructionSet::BMI2(void) { return f_7_EBX_[8]; }
bool InstructionSet::ERMS(void) { return f_7_EBX_[9]; }
bool InstructionSet::INVPCID(void) { return f_7_EBX_[10]; }
bool InstructionSet::RTM(void) { return isIntel_ && f_7_EBX_[11]; }
bool InstructionSet::AVX512F(void) { return f_7_EBX_[16]; }
bool InstructionSet::RDSEED(void) { return f_7_EBX_[18]; }
bool InstructionSet::ADX(void) { return f_7_EBX_[19]; }
bool InstructionSet::AVX512PF(void) { return f_7_EBX_[26]; }
bool InstructionSet::AVX512ER(void) { return f_7_EBX_[27]; }
bool InstructionSet::AVX512CD(void) { return f_7_EBX_[28]; }
bool InstructionSet::SHA(void) { return f_7_EBX_[29]; }

bool InstructionSet::PREFETCHWT1(void) { return f_7_ECX_[0]; }

bool InstructionSet::LAHF(void) { return f_81_ECX_[0]; }
bool InstructionSet::LZCNT(void) { return isIntel_ && f_81_ECX_[5]; }
bool InstructionSet::ABM(void) { return isAMD_ && f_81_ECX_[5]; }
bool InstructionSet::SSE4a(void) { return isAMD_ && f_81_ECX_[6]; }
bool InstructionSet::XOP(void) { return isAMD_ && f_81_ECX_[11]; }
bool InstructionSet::TBM(void) { return isAMD_ && f_81_ECX_[21]; }

bool InstructionSet::SYSCALL(void) { return isIntel_ && f_81_EDX_[11]; }
bool InstructionSet::MMXEXT(void) { return isAMD_ && f_81_EDX_[22]; }
bool InstructionSet::RDTSCP(void) { return isIntel_ && f_81_EDX_[27]; }
bool InstructionSet::_3DNOWEXT(void) { return isAMD_ && f_81_EDX_[30]; }
bool InstructionSet::_3DNOW(void) { return isAMD_ && f_81_EDX_[31]; }