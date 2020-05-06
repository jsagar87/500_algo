# FROM golang:1.7.4-alpine
FROM golang:1.12.0-alpine3.9

# MAINTAINER Bhishma Jariwala

RUN mkdir Cloud-Native-Go

ENV SOURCES /Cloud-Native-Go/

COPY . ${SOURCES}
# RUN chmod +x /app/Cloud-Native-Go

# RUN cd ${SOURCES} && CGO_ENABLED=0 go install
WORKDIR /Cloud-Native-Go

RUN go build

ENV PORT 8080
EXPOSE 8080

CMD [ "Cloud-Native-Go" ]
