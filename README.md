# SQLiteWrapper
I needed an easy iterface to sqlite db for an android application, since frameworks needs too many configuration i'm developing an easy class that wrap jdbc.
Obviusly you can change the driver and use it for other databases.

The main idea was make a method that take some sql code and extract an Object that represent the record... and another one that allow to insert an object.
I don't know how build a dinamic class in run time, i saw ByteBuddys library that make more or less this, but now i focalize my attenction to "close the circle".

All methods are static and i wrote some basic test.

External library used:
- Google Guava (for HashMap)

To make life more easy I committed also ".project" of Eclipse oxygen

**EVERY CONTRIBUTION IS WELCOME**
