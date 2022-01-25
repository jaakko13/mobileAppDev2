import 'package:flutter/material.dart';
import 'components/bmiGui.dart';

void main() {
  runApp(new MyBmiApp());
}

class MyBmiApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // ignore: prefer_const_constructors
    return MaterialApp(
        title: "Bmi App",
        theme: ThemeData(primarySwatch: Colors.red),
        home: BmiGui());
  }
}
