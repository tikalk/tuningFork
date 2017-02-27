#!/bin/bash

pwd=`pwd`
echo $pwd

java $JAVA_OPTS -jar ./tuningForkDemoApp*.jar --Dcsv.dir=$CVS_DIR
