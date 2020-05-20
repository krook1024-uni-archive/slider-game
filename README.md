# slider-game [![Build Status](https://travis-ci.org/krook1024/slider-game.png?branch=master)](https://travis-ci.org/krook1024/slider-game)

*slider-game* is a rather small JavaFX project that aims to implement
a simple logic game.

Making this project is the final requirement for my *Software Develeopment* course. 


## Run the project

**Note:** Java 11 or later is required.

```bash
mvn clean compile exec:java
```

## Generate docs

```bash
mvn site
```

### Add Clover coverage report

```bash
mvn -P clover site
```
