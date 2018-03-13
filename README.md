# this template

This is a simple template with gradle runner and gatling. Its possible to run tests against different environments by loading different config files.

## Running

This performance testing project is managed using gradle. Most likely you will hook up this command to your CI server and will parameterize it to have different runners.

The command to run performance tests is:
`./gradlew loadTest  -Penv=ci -PsimulationClass=$SIMULATION -PrampUp=$RAMPUP -Pusers=$USERS -Pduration=$DURATION -PstartUsers=$START_USERS`

### Options

There are options to choose from when running the performance test as seen in the command above.

* `simulationClass` = name of load simulation to run (see Simulations section below)
* `startUsers` = number of requests per second to start the simulation with
* `users` = number of requests per second to finish the simulation with
* `rampUp` = number of seconds it takes to ramp up from `startUsers` to `users`
* `duration` = number of minutes to run the simulation after the ramp up to `users` is complete - must be > 0

## Simulations

* SendEventToTopic - Will send n valid events to the asb topic.

