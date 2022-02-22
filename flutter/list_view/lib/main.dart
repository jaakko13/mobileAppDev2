import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;


void main() {
  runApp( MyApp());
}


class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: weatherScreen(),
    );
  }
}

class weatherScreen extends StatefulWidget {
  const weatherScreen({Key? key}) : super(key: key);

  @override
  State<weatherScreen> createState() => _weatherScreenState();
}


class _weatherScreenState extends State<weatherScreen>{
  List? todoData;

  @override
  void initState() {
    super.initState();
    fetchData();
  }

  void fetchData() async {
    Uri uri  = Uri.parse(
      'https://jsonplaceholder.typicode.com/todos');
      var response = await http.get(uri);
      if (response.statusCode == 200){
        todoData = json.decode(response.body);

        setState(() {
          todoData = json.decode(response.body);
        });
      }
  }

  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: const Text('Weather Screen'),
      ),
      
      body: 
      todoData != null ? ListView.separated(
      padding: const EdgeInsets.all(8),
      itemCount: todoData!.length,
      itemBuilder: (BuildContext context, int index){
        return Container(
          height: 50,
          color: Colors.blueGrey[100*index%800],
          child: Center(child: Text(todoData![index]["title"])),
        );
      },  
      separatorBuilder: (BuildContext context, int index) => const Divider(),
      ) : new Text("Waiting for data")
    );
  }

}