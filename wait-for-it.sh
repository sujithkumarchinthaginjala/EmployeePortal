#!/usr/bin/env bash
#   Use this script to test if a given TCP host/port are available
set -e

TIMEOUT=15
QUIET=0

hostport=$1
shift
cmd="$@"

IFS=: read host port <<< "$hostport"

echo "Waiting for $host:$port..."

for i in $(seq $TIMEOUT); do
  if nc -z "$host" "$port"; then
    echo "Service is up!"
    exec $cmd
    exit 0
  fi
  sleep 1
done

echo "Timed out waiting for $host:$port"
exit 1
