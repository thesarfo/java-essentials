When you design a class, think about the objects that
will be created from that class type. Think about:

1. Things the object knows
2. Things the object does

For instance
Class - ShoppingCart
knows - cartContents
does - addToCart(), removeFromCart(), checkOut().

Class - Alarm
knows - alarmTime, alarmMode
does - setAlarmTime(), getAlarmTime(), isAlarmSet(), snooze()

3. Things an object knows about itself are called "instance variables", i.e state.
4. Things an object can do are called "methods", i.e behaviour.