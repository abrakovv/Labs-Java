#!/usr/bin/env sh
set -eu
cd "$(dirname "$0")"
mkdir -p target/classes
find src/main/java -name '*.java' -print > target/sources.txt
javac --release 17 -encoding UTF-8 -Xlint:all -Werror -d target/classes @target/sources.txt
exec java -cp target/classes ru.abrakov.main.Main "$@"
