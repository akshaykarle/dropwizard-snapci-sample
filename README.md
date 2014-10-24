dropwizard-snapci-sample
========================

Hit the ground running with Dropwizard and Snap

## Setup:

* Run the unit tests and compile the jar into the target dir.

``` mvn test package ```

* To run the integration tests you need to start the server first.

``` foreman start & ```

* Wait a few seconds for the server to come up. Add a sleep.

``` sleep 5 ```

* Run the integration tests.

``` mvn failsafe:integration-test failsafe:verify ```

