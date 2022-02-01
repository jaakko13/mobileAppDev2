import 'package:flutter/cupertino.dart';

import 'package:flutter/material.dart';

class weatherGui extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return (Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.center,
      //mainAxisSize: MainAxisSize.min,
      children: <Widget>[
        Text("Description: Cloudy",
            style: TextStyle(
              fontSize: 24,
            )),
        Padding(
          padding: const EdgeInsets.all(8.0),
        ),
        Text("Temp: -9",
            style: TextStyle(
              fontSize: 24,
            )),
        Padding(
          padding: const EdgeInsets.all(8.0),
        ),
        Text("Wind Speed: 7kmh",
            style: TextStyle(
              fontSize: 24,
            )),
        Padding(
          padding: const EdgeInsets.all(10.0),
        ),
        ElevatedButton(
          onPressed: () {},
          child: Text('Get Weather'),
        )
      ],
    ));
  }
}
