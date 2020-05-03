cmake_minimum_required(VERSION 3.13.4)

project(Database_test VERSION 1.0.0)

add_definitions("-std=c++11")

include(${CMAKE_BINARY_DIR}/conanbuildinfo.cmake)
conan_basic_setup()

add_executable(dbtest md5.cpp)
target_link_libraries(dbtest ${CONAN_LIBS})