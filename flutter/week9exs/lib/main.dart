import 'dart:async';
import 'package:flutter/material.dart';
import 'package:location/location.dart';
import 'package:geolocator/geolocator.dart';
import 'package:sensors_plus/sensors_plus.dart';

void main() {
  runApp(const week9App());
}

class week9App extends StatefulWidget {
  const week9App({Key? key}) : super(key: key);

  @override
  State<week9App> createState() => _week9AppState();
}

class _week9AppState extends State<week9App> {
  var location = "";
  var x, y, z;
  List<StreamSubscription<dynamic>> _streamSubscriptions =
      <StreamSubscription<dynamic>>[];

  void getLocation() async {
    Location validLocation = new Location();

    bool _serviceEnabled;
    PermissionStatus _permissionGranted;
    LocationData _locationData;

    _serviceEnabled = await validLocation.serviceEnabled();
    if (!_serviceEnabled) {
      _serviceEnabled = await validLocation.requestService();
      if (!_serviceEnabled) {
        return;
      }
    }

    _permissionGranted = await validLocation.hasPermission();
    if (_permissionGranted == PermissionStatus.denied) {
      _permissionGranted = await validLocation.requestPermission();
      if (_permissionGranted != PermissionStatus.granted) {
        return;
      }
    }

    var position = await Geolocator.getCurrentPosition();
    var lastPosition = await Geolocator.getLastKnownPosition();
    print(lastPosition);

    setState(() {
      location = "{$position}";
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Week 9 Exercises"),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Text("Current Location: $location",
                  style: const TextStyle(fontSize: 40)),
              ElevatedButton(
                  onPressed: () {
                    getLocation();
                  },
                  child: Text("Get Location")),
              Text("Accelerometer Values:",
                  style: const TextStyle(fontSize: 30)),
              Text("X: $x", style: const TextStyle(fontSize: 25)),
              Text("Y: $y", style: const TextStyle(fontSize: 25)),
              Text("Z: $z", style: const TextStyle(fontSize: 25)),
            ],
          ),
        ),
      ),
    );
  }

  void initState() {
    super.initState();
    _streamSubscriptions
        .add(accelerometerEvents.listen((AccelerometerEvent event) {
      setState(() {
        x = event.x;
        y = event.y;
        z = event.z;
      });
    }));
  }
}
