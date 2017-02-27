#!/bin/bash

pwd=`pwd`
echo $pwd

java $JAVA_OPTS -jar ./tuningForkDemoApp*.jar --csv.dir=$CVS_DIR
