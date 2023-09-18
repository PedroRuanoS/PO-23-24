PO_UILIB_DIR=/usr/share/java
JARNAME=xxl-core

all:
	(cd src; javac -cp . -encoding UTF-8 `find . -name \*.java`)
	(cd src; jar cf ../$(JARNAME).jar `find . -name \*.class -o -name \*.java` )

clean:
	$(RM) $(JARNAME).jar `find . -name \*.class`
