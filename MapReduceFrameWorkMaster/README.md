# Master Node

Single Node that coordinates map and reduce execution
to workers.

Map is pinged by coordinators to return a map task or to run a reduce task

Workers also hit the master server to make it aware of completed map/reduce tasks.

## Unknowns:
- Does Springboot service persist state across requests. Yes services persist their state.

## Work to be done:
- the master maintains a status of all map jobs and their state
- the master can be queried for jobs to be done
- On query for job to be done the master will return either a map or reduce job
- master can be updated for finished map or reduce jobs 
- on booting up the master will read all the files from the input data directory and
create a corresponding mapTask for them.
- on booting up the master will also create a collection of n-reduce jobs that are unfinished.

### When a worker queries for a job:
- Scenario 1: We have unfinished Map jobs that exist.
Here we will go ahead and find a map job that is unfinished and mark it as in progress.

- Scenario 2: We have unfinished Reduce jobs that exist and all map jobs are finished
Find an unfinished reduce job and mark it as in progress and send it to worker.

- Scenario 3: We have finished all map jobs and all reduce jobs.
Notify the worker all jobs are completed. The worker will then shut down.
 
### When a worker notifies completion:
- We either mark the map job as finished, or we mark the reduce job as finished and return 201 since we have created an
entry.
