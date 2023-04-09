all: Scenario1 clean

Scenario1: SecuritySystem/*.java Tests/Scenario1.java
	@javac Tests/Scenario1.java
	@java Tests/Scenario1
	
clean:
	@rm -f SecuritySystem/*.class
	@rm -f Tests/*.class
