//
// Created by desaco on 2018/6/11.
// https://www.cnblogs.com/qyaizs/articles/2039101.html

#ifndef PRACTICEKNOWING_COMMON_H
#define PRACTICEKNOWING_COMMON_H

typedef struct C_Student {//C
    int age;
} Stu;

typedef struct {//C
    int age2;
} C_Stu2;

struct Cplus_Student {//C++
    int cplus_age;
};

//在C语言中，定义一个结构体类型要用typedef :
typedef struct Point {
    int x;
    int y;
}point_params;



#endif //PRACTICEKNOWING_COMMON_H
