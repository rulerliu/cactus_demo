#!/bin/bash
ps -ef|grep cactus_demo-1.0-SNAPSHOT.jar|grep -v grep|awk '{print $2}'|xargs kill -9