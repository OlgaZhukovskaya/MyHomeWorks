#!/usr/bin/env bash
set -euo pipefail

export JAVA_HOME="/usr/lib/jvm/default-java"
export PATH="$JAVA_HOME/bin:$PATH"

ROOT="$(cd "$(dirname "$0")" && pwd)"

echo $JAVA_HOME
echo $ROOT

java -cp "$ROOT/out" com.example.dungeon.Main
