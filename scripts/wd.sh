#!/bin/sh
# start WritersDiarrhoea.jar

if [ -z "$JAVA_HOME" ] ; then
	/usr/bin/java -jar WritersDiarrhoea.jar
else 
	$JAVA_HOME/bin/java -jar WritersDiarrhoea.jar
fi

