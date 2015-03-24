# Start out with a ubuntu base
FROM ubuntu
MAINTAINER Kasper Rune Søgaard <krs@tradeshift.com>

# Update OS
RUN apt-get update

# Inform that the docker will be using port 8080
EXPOSE 8080

# Install wget, used to download jetty
RUN apt-get install -y wget

# Install java
RUN apt-get install -y openjdk-7-jdk

# Download JETTY
RUN wget http://download.eclipse.org/jetty/stable-9/dist/jetty-distribution-9.2.10.v20150310.tar.gz -O /tmp/jetty.tar.gz

# Unpack
RUN cd /opt && tar -xvzf /tmp/jetty.tar.gz
RUN mv /opt/jetty-distribution-9.2.10.v20150310 /opt/jetty
RUN rm /tmp/jetty.tar.gz

# Set paths for jetty to use
ENV JETTY_HOME /opt/jetty
ENV PATH $PATH:$JETTY_HOME/bin

# Move the WAR file to jetty location
ADD root.war /opt/jetty/webapps/

# Setup Jetty
RUN java -jar /opt/jetty/start.jar --add-to-startd=http,deploy

# Start Jetty, this is also the entry point
CMD cd /opt/jetty/ && java -jar start.jar