@echo off

REM To activate 64 bits "C:\Program Files (x86)\Microsoft Visual Studio\2017\BuildTools\VC\Auxiliary\Build\vcvars64.bat"
REM To activate 32 bits "C:\Program Files (x86)\Microsoft Visual Studio\2017\BuildTools\VC\Auxiliary\Build\vcvars32.bat"

REM "C:\Program Files (x86)\Microsoft Visual Studio\2017\BuildTools\VC\Auxiliary\Build\vcvars32.bat"

SET COMMAND=%1
SET BUILD_BITS=%2
SET CLEAN=clean
SET SixtyFour=64
SET ThirtyTwo=32

IF %COMMAND%==%CLEAN% del greet.dll greet.exp greet.lib greet.obj
IF %BUILD_BITS%==%SixtyFour% goto :cond3
IF %BUILD_BITS%==%ThirtyTwo% goto :cond2
REM cl greet.cpp -I"C:\Program Files\OpenJDK\jdk-8\include" -I"C:\Program Files\OpenJDK\jdk-8\include\win32" -Fegreet.dll -MD -LD

:cond2
echo "Thirty two"
cl greet.cpp -I"C:\Program Files\OpenJDK\jdk-8\include" -I"C:\Program Files\OpenJDK\jdk-8\include\win32" -Fegreet.dll -MD -LD
goto :skip

:cond3
echo "Sixty four"
REM cl greet.cpp -I"C:\Program Files\OpenJDK\jdk-8\include" -I"C:\Program Files\OpenJDK\jdk-8\include\win32" -Fegreet.dll -MD -LD
goto :skip

:skip
echo "end"

REM "cl /EHsc funcpoint.cpp InstructionSet.cpp"