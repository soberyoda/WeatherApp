FROM ubuntu:latest
LABEL authors="agamm"

ENTRYPOINT ["top", "-b"]

FROM openjdk:17
