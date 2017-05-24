# mm-nft

These are the [movemoney](https://github.com/) performance tests. 

## Running

This performance testing project is managed using gradle. There is a performance test job defined at [jenkins-url].

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

Used simulations are listed below.

* BaseLineReadAccountsSimulation - This reads user accounts

