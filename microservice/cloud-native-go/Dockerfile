# First build locally using
# GOOS=linux GOARCH=amd64 go build
FROM alpine:3.5

COPY ./cloud-native-go /app/cloud-native-go
RUN chmod +x /app/cloud-native-go


ENV PORT 8080
EXPOSE 8080

ENTRYPOINT /app/cloud-native-go