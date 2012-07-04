# zosc

An open sound control router and scheduler for local area networks

## Prerequisites

Java, Leiningen

## Usage

    git checkout https://github.com/samBiotic/zosc.git
    cd zosc
    lein deps
    lein run :zosc

send non-bundled osc messages to the network via localhost on port 8000

receive scheduled osc messages from the network on port 9000

## To do

- mdns peer discovery
- system clock offset calculations
- unicast correctly time tagged bundles to each peer
- handle future tagged osc bundles
- api on /app/zosc namespace
- arguments for inital config

## License

Copyright (C) 2012 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
