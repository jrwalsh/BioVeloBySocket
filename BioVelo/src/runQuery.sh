#!/bin/bash

Query="[x^names:x<-CORN^^genes]"

javac Query/*.java

#can also use the following arguments:
#-verbose
#-log
java -Djava.library.path=. -cp . Query.Query $Query
