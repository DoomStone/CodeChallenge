FROM tradeshift/jettybase
MAINTAINER Kasper Rune Søgaard <krs@tradeshift.com>

# Move the WAR file to jetty location
ADD root.war /opt/jetty/webapps/

# Start Jetty, cd to the jetty folder so it knows where to looks for jars
# This is the entry point
CMD cd /opt/jetty/ && java -jar start.jar