all: scenario1 scenario2 scenario3 clean

scenario1: main/*/*.java tests/Scenario1.java
	@javac tests/Scenario1.java
	@java tests/Scenario1

scenario2: main/*/*.java tests/Scenario1.java
	@javac tests/Scenario2.java
	@java tests/Scenario2

scenario3: main/*/*.java tests/Scenario1.java
	@javac tests/Scenario3.java
	@java tests/Scenario3

clean:
	@rm -f main/*/*.class
	@rm -f tests/*.class
