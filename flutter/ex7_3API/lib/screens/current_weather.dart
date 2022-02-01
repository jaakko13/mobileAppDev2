import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class weatherScreen extends StatefulWidget {
  const weatherScreen({Key? key}) : super(key: key);

  @override
  State<weatherScreen> createState() => _weatherScreenState();
}

class _weatherScreenState extends State<weatherScreen> {
  String currentWeather = "sunny";
  double temp = 0;
  double windSpeed = 0;
  String cityName = "Tampere";

  void getWeather() async {
    String api = '6c433438776b5be4ac86001dc88de74d';
    Uri url = Uri.parse("https://api.openweathermap.org/data/2.5/weather?q=tampere&units=metric&appid=$api");

    final res = await http.get(url);
    if (res.statusCode == 200) {
      var data = json.decode(res.body);
      setState(() {
        currentWeather = data['weather'][0]['description'];
        temp = data['main']['temp'];
        windSpeed = data['wind']['speed'];
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(cityName),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            Text(currentWeather, style: const TextStyle(fontSize: 40)),
            Text('$temp', style: const TextStyle(fontSize: 40)),
            Text('$windSpeed', style: const TextStyle(fontSize: 40)),
            ElevatedButton(
              child: const Text('Get weather data'),
              onPressed: () {
                getWeather();
              },
            ),
          ],
        ),
      ),
    );
  }
}
