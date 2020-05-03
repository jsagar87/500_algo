#FROM debian:sid

#RUN apt-get -y update
#RUN apt-get install nano

#CMD ["/bin/nano", "/tmp/notes"]

#######################

FROM example/nanoer
ADD notes.txt /notes.txt
CMD ["/bin/nano", "/notes.txt"]