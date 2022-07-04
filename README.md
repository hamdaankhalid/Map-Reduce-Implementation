# Map-Reduce-Implementation
Map Reduce Framework Written in Java to run User defined Maps and Reduce Jobs on a virtual cluster with local filesystem instead of DFS

Local FS can be swapped for a DFS later on with an implementation of IFileINteractor.

Master Node is a Sprinboot server exposing public API's. Worker node's run a plain old Java application.

All inner workings are explained here: https://hamdaan-rails-personal.herokuapp.com/articles/17

