# Data flow

- Coordinator boots up
- Coordinator creates a mapping of which map tasks need to be done and reduce tasks need to be done
- Worker asks for task
- Coordinator sends a map task
- Worker runs a map function on the map task (filename). Given a filename and contents the user defined
function will return an array of key val pairs.
- Worker divides these into n buckets, and writes their values into mapTaskid+n reduce number bucket.
    * example word count would run like this. filename1, "hi my name is cutie" => [{hi: 1}, {my: 1}, {name: 1}, {is: 1}, {cutie: 1}]
    * this would then be written to intermediate-${mapId}-${reduceId}
- a reduce job will later pick all intermediate files to run reduce on. It will then sort all the values by their keys and for each of those
    it will run a reduce job and write their output into the final output file for that n reduce task.   