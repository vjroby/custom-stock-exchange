Custom Stock Exchange
=====================

Description
-----------

Stock Exchange example implemented in Java.
Gradle is used for build and package management.

prerequisities
--------------
- JDK 1.8+
- Gradle 2.9+

build
-----
```
gradle fatJar
```
A jar file will be crated in build/libs/jpm-tech-all-1.0-SNAPSHOT.jar

test
----
```
gradle test
```
Gradle will compile a jar file with the tests.


run
---
```
gradle runJar
```
The script will run until it's stopped. Ctrl + c on Unix.