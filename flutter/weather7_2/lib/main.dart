import 'package:flutter/material.dart';
import 'weatherGui.dart';
void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: "Weather App",
        theme: ThemeData(primarySwatch: Colors.blue),
        home: const Mypage());
  }
}

class Mypage extends StatelessWidget {
  const Mypage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Weather"),
        ),
        body: weatherGui(),
        );
  }
}
