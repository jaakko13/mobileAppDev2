import 'package:flutter/material.dart';

class BmiGui extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return (Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      mainAxisSize: MainAxisSize.min,
      children: const <Widget>[
        Text("Your Weight:"),
        TextField(
            decoration: InputDecoration(
          border: InputBorder.none,
          hintText: 'Enter weight',
        )),
      ],
    ));
  }
}
