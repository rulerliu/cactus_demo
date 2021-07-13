#!/bin/bash
PWDPATH=`dirname $0`
PORTAL_HOME=`cd $PWDPATH && cd .. && pwd`
echo $PORTAL_HOME
cd $PORTAL_HOME
JVM_OPTS="
-server
 -Xms2g
 -Xmx2g
 -XX:+UseG1GC
 -XX:MaxGCPauseMillis=200
 -XX:+DisableExplicitGC
 -XX:+PrintGCDetails
 -XX:-UseGCOverheadLimit
 -XX:+HeapDumpOnOutOfMemoryError
 -XX:+PrintGCDateStamps
 -Xloggc:logs/gc.log
"

start() {
nohup java $JVM_OPTS -Dlogging.config=$PORTAL_HOME/config/log4j2-spring.xml -jar $PORTAL_HOME/lib/cactus_demo-1.0-SNAPSHOT.jar &
echo -e '\r'
}
logs_dir=$PORTAL_HOME/logs
if [ ! -d "$logs_dir" ]; then
        mkdir $logs_dir
fi
touch $PORTAL_HOME/logs/console.log
start >> $PORTAL_HOME/logs/console.log 2>> $PORTAL_HOME/logs/console.log
