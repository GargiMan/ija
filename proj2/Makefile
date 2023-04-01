LOGIN=xgerge01
TESTS=ija/ija2022/homework2/Homework2Test.java
TESTS_CLASS=ija.ija2022.homework2.Homework2Test
VISUAL=ija/ija2022/homework2/Homework2.java
VISUAL_CLASS=ija.ija2022.homework2.Homework2

.PHONY: all compile compile-visual run run-visual clean zip

all: run run-visual

compile:
	javac -cp .:ijatool.jar:junit-platform-console-standalone-1.6.0.jar $(TESTS)

compile-visual:
    javac -cp .:ijatool.jar -d build $(VISUAL)

run: compile
	java -cp ./build:ijatool.jar:junit-platform-console-standalone-1.6.0.jar org.junit.runner.JUnitCore $(TESTS_CLASS)

run-visual: compile-visual
    java -cp ./build:ijatool.jar $(VISUAL_CLASS)

clean:
	find . -name "*.class" -type f -delete
	rm -rf $(LOGIN).zip

zip: clean
	zip -r $(LOGIN).zip ija -x $(TESTS)