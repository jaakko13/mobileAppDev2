import 'package:flutter/material.dart';

class Person {
  String mName;
  int mAge;
  int? mWeight;
  int? mHeight;

  String get getName {
    return mName;
  }

  int get getAge {
    return mAge;
  }

  int? get getWeight {
    return mWeight;
  }

  int? get getHeight {
    return mHeight;
  }

  set setName(String name) {
    mName = name;
  }

  set setAge(int age) {
    mAge = age;
  }

  set setWeight(int weight) {
    mWeight = weight;
  }

  set setHeight(int height) {
    mHeight = height;
  }

  Person(this.mName, this.mAge, {this.mHeight, this.mWeight});
  Person.verySmallPerson(this.mName, this.mAge,
      {this.mHeight = 50, this.mWeight});

  @override
  String toString() {
    return 'Person = name: $mName, age: $mAge, height: $mHeight, weight: $mWeight';
  }
}

class Student extends Person {
  int? mId;
  int? mCreditPoints;

  Student(mName, mAge, mHeight, mWeight, this.mId, this.mCreditPoints)
      : super(mName, mAge);

  @override
  String toString() {
    return 'Student = name: $mName, age: $mAge, height: $mHeight, weight: $mWeight, id: $mId, creditPoints: $mCreditPoints';
  }
}

void main() {
  Person jaakko = Person('Jaakko', 21, mHeight: 186, mWeight: 77);
  Student nam = Student('Nam', 20, 150, 50, 2312, 55);
  Person ferdous = Person.verySmallPerson('Ferdous', 22, mWeight: 60);

  var list = [jaakko, nam, ferdous];
  print(list);
}
