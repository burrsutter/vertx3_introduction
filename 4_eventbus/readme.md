Terminal Window 1:
vertx run MyPublisher.java -cluster

Terminal Window 2:
vertx run MyConsumer.java -cluster

Note: cluster.xml in the root folder, needed to make life easier on Mac OSX
you might remove it from the directory

High Availability (HA)
In 3 different terminal windows:
vertx run MyPublisher.java -ha
vertx run MyConsumer.java -ha
vertx run myconsumer.js -ha

In a 4th terminal window:
jps
find the process id of a Launcher
kill -9 65647
http://screencast.com/t/O4uKMRdKveS

and one of the remaining JVMs/nodes will start 
running the killed Verticle
http://screencast.com/t/CdZdP9qD5T7m




