LOGIN=xgerge01
TESTS=ija/ija2022/homework1/Homework1Test.java
TESTS_PACKAGE=ija.ija2022.homework1.Homework1Test

.PHONY: all compile run clean zip

all: compile run

compile:
	javac -cp .:junit-platform-console-standalone-1.6.0.jar $(TESTS)

run: compile
	java -cp .:junit-platform-console-standalone-1.6.0.jar org.junit.runner.JUnitCore $(TESTS_PACKAGE)

clean:
	find . -name "*.class" -type f -delete
	rm -rf $(LOGIN).zip

zip: clean
	zip -r $(LOGIN).zip ija -x $(TESTS)