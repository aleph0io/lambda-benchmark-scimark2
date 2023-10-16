#!/bin/bash

ARGS=("$@")

# Java 17: /var/lang/bin/java -XX:MaxHeapSize=3945472k -javaagent:/var/runtime/lib/Log4jHotPatch.jar=log4jFixerVerbose=false -XX:+UseSerialGC -Xshare:on -XX:SharedArchiveFile=/var/lang/lib/server/runtime.jsa -XX:+TieredCompilation -XX:TieredStopAtLevel=1 --add-opens=java.base/java.io=ALL-UNNAMED -Dorg.crac.Core.Compat=com.amazonaws.services.lambda.crac -Djava.net.preferIPv4Stack=true -XX:+ErrorFileToStderr -Dcom.amazonaws.services.lambda.runtime.api.client.runtimeapi.NativeClient.JNI=/var/runtime/lib/jni/libaws-lambda-jni.linux-x86_64.so -classpath /var/runtime/lib/aws-lambda-java-core-1.2.3.jar:/var/runtime/lib/aws-lambda-java-runtime-interface-client-2.4.1-linux-x86_64.jar:/var/runtime/lib/aws-lambda-java-serialization-1.1.2.jar com.amazonaws.services.lambda.runtime.api.client.AWSLambda com.sigpwned.lambdainternals.App::handleRequest 


NEWARGS=()
for (( i=0; i<${#ARGS[@]}; i++ ))
do
  if [ ${ARGS[$i]} = "-XX:+TieredCompilation" ]
  then
    # We actually do NOT want tiered compilation
    NEWARGS+=("-XX:-TieredCompilation")
  elif [ ${ARGS[$i]} = "-XX:TieredStopAtLevel=1" ]
  then
    # We don't want this, so skip it.
    true
  else
    NEWARGS+=(${ARGS[$i]})
  fi
done

# start the runtime with the extra options
exec "${NEWARGS[@]}"

