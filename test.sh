#!/bin/bash

SRC_DIR=$(dirname $(readlink -f $0))

docker build --tag alice_test ${SRC_DIR}

docker run -v ${SRC_DIR}:/code alice_test
