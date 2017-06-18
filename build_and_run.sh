#!/usr/bin/env bash
#JMX_OPTIONS="-Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.port=7778 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=paradigma.cursovertx "
mvn clean package -DskipTests 
java $JMX_OPTIONS -jar target/poc-grapql-api-gen-0.1.0-fat.jar
